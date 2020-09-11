package com.intelligent.service;

import com.intelligent.controller.type.AnswerExecuteDetailResponse;
import com.intelligent.controller.type.AnswerRequest;
import com.intelligent.controller.type.AnswerStatusResponse;


public interface AnswerService {
    Integer saveAnswer(AnswerRequest answerRequest, int userId);

    void sendMessageToMq(int answerId);

    // 通过答案ID获得当前答案执行情况
    AnswerStatusResponse getAnswerStatus(int answerId);

    // 通过答案ID获得当前答案执行情况
    AnswerExecuteDetailResponse getAnswerExecuteMsg(int answerId);

}
