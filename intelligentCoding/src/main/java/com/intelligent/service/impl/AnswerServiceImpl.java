package com.intelligent.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelligent.compiler.beans.Message;
import com.intelligent.controller.type.AnswerExecuteDetailResponse;
import com.intelligent.controller.type.AnswerRequest;
import com.intelligent.controller.type.AnswerStatusResponse;
import com.intelligent.dao.AnswerDao;
import com.intelligent.model.Answer;
import com.intelligent.service.AnswerService;
import com.intelligent.type.AnswerStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {
    private static final Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class.getName());

    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private KafkaTemplate<String, String> template;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Integer saveAnswer(AnswerRequest answerRequest, int userId) {
        Answer exists;
        Answer answer = new Answer();
        answer.setTopicId(answerRequest.getTopicId());
        answer.setUserId(userId);
        setAnswer(answer, answerRequest.getContent());
        try {
            exists = answerDao.save(answer);
        } catch (DataIntegrityViolationException e) {
            exists = answerDao.findByUserIdAndTopicId(userId, answerRequest.getTopicId());
            setAnswer(exists, answerRequest.getContent());
            answerDao.save(exists);
        }
        return exists.getId();
    }

    private void setAnswer(Answer answer, String content) {
        answer.setCode(content);
        answer.setStatus(AnswerStatus.SAVE.name());
        answer.setPass(false);
        answer.setFailCount(0);
        answer.setSuccCount(0);
        answer.setSubmitTime(LocalDateTime.now());
    }

    @Override
    public void sendMessageToMq(int answerId) {
        //把answer id发送kafka，通知compiler对这个answer进行编译
        Message message = new Message();
        message.setAnswerId(answerId);
        message.setSendTime(LocalDateTime.now());
        try {
            template.send("program", objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException ignored) {
        }
    }

    @Override
    public AnswerStatusResponse getAnswerStatus(int answerId) {
        AnswerStatusResponse answerStatusResponse = new AnswerStatusResponse();
        Answer answer = answerDao.findById(answerId).orElse(null);
        //todo 如果已经完毕，则还需要在返回中带上执行结果
        if (answer != null && (Objects.equals(answer.getStatus(), AnswerStatus.COMPILE_FAILED.name())
                || Objects.equals(answer.getStatus(), AnswerStatus.FINISH_SUCCESS.name())
                || Objects.equals(answer.getStatus(), AnswerStatus.FINISH_FAILED.name()))) {
            answerStatusResponse.setAnswerStatus(true);
            answerStatusResponse.setExecuteDetailMsg(answer.getExecuteDetailMsg());
            answerStatusResponse.setAnswerStatusMsg(answer.getStatus());
        } else {
            answerStatusResponse.setAnswerStatus(false);
        }

        return answerStatusResponse;
    }

    @Override
    public AnswerExecuteDetailResponse getAnswerExecuteMsg(int answerId) {
        AnswerExecuteDetailResponse answerExecuteDetailResponse = new AnswerExecuteDetailResponse();
        Optional<Answer> answer = answerDao.findById(answerId);
        String msg = answer.map(Answer::getExecuteDetailMsg).orElse("未完成执行");
        answerExecuteDetailResponse.setExecuteDetailMsg(msg);
        return answerExecuteDetailResponse;
    }
}
