package com.intelligent.service;

import com.intelligent.controller.type.AnswerRequest;
import com.intelligent.controller.type.AnswerStatusResponse;
import com.intelligent.controller.type.PageRequest;
import com.intelligent.controller.type.Result;


public interface AnswerService {
    Integer saveAnswer(AnswerRequest answerRequest, int userId);
    void sendMessageToMq(int answerId);
    // 通过答案ID获得当前答案执行情况
    AnswerStatusResponse getAnswerStatus(int answerId);

}
