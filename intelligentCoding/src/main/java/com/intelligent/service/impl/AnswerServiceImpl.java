package com.intelligent.service.impl;

import com.intelligent.controller.UserFavoriteTopicController;
import com.intelligent.controller.type.AnswerRequest;
import com.intelligent.controller.type.AnswerStatusResponse;
import com.intelligent.controller.type.Result;
import com.intelligent.dao.AnswerDao;
import com.intelligent.model.Answer;
import com.intelligent.service.AnswerService;
import com.intelligent.type.AnswerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;

@Service
public class AnswerServiceImpl implements AnswerService {
    private static final Logger log = Logger.getLogger(AnswerServiceImpl.class.getName());

    @Autowired
    private AnswerDao answerDao;


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
    }

    @Override
    public AnswerStatusResponse getAnswerStatus(int answerId) {
        AnswerStatusResponse answerStatusResponse = new AnswerStatusResponse();
        String status = answerDao.findById(answerId).map(Answer::getStatus).orElse(null);
        //todo 如果已经完毕，则还需要在返回中带上执行结果
        answerStatusResponse.setAnswerStatus((Objects.equals(status, AnswerStatus.COMPILE_FAILED.name())
                || Objects.equals(status, AnswerStatus.FINISH_SUCCESS.name())
                || Objects.equals(status, AnswerStatus.FINISH_FAILED.name())));

        return answerStatusResponse;
    }
}
