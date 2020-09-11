package com.intelligent.controller;

import com.intelligent.controller.type.AnswerExecuteDetailResponse;
import com.intelligent.controller.type.AnswerRequest;
import com.intelligent.controller.type.AnswerStatusResponse;
import com.intelligent.controller.type.Result;
import com.intelligent.model.Answer;
import com.intelligent.model.Users;
import com.intelligent.service.AnswerService;
import com.intelligent.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    // 用户提交答案，首先将答案保存到数据库
    @PostMapping(path = "answer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<Map<String, Integer>> submitAnswer(@ModelAttribute AnswerRequest answerRequest) {
        //提交是否成功和答案ID
        Users user = UserContext.getCurrentUser();
        Result<Map<String, Integer>> answerResult = new Result<>();
        int id = answerService.saveAnswer(answerRequest, user.getId());
        answerService.sendMessageToMq(id);
        answerResult.setSuccess(true);
        Map<String, Integer> map = new HashMap<>();
        map.put("answerId", id);
        answerResult.setData(map);
        return answerResult;
    }

    //传入当前答案ID，获得当前答案执行情况
    @GetMapping(path = "answer-status/{answerId}")
    public Result<AnswerStatusResponse> getAnswerStatus(@PathVariable("answerId") int answerId) {
        Result<AnswerStatusResponse> result = new Result<>();
        AnswerStatusResponse answerStatusResponse = answerService.getAnswerStatus(answerId);
        result.setData(answerStatusResponse);
        result.setSuccess(true);
        return result;
    }

    //传入当前答案ID，获得当前答案执行信息（编译失败、执行失败）
    @GetMapping(path = "answer-execute-detail-msg/{answerId}")
    public Result<AnswerExecuteDetailResponse> getAnswerExecuteMsg(@PathVariable("answerId") int answerId) {
        Result<AnswerExecuteDetailResponse> result = new Result<>();
        AnswerExecuteDetailResponse answerExecuteResponse = answerService.getAnswerExecuteMsg(answerId);
        result.setData(answerExecuteResponse);
        result.setSuccess(true);
        return result;
    }


}
