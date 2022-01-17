package com.intelligent.controller;

import com.intelligent.controller.type.Result;
import com.intelligent.model.Discussion;
import com.intelligent.model.Users;
import com.intelligent.service.DiscussionService;
import com.intelligent.type.Discussions;
import com.intelligent.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import com.intelligent.type.DiscussionResponse;

@RestController
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;
    private static final Logger log = Logger.getLogger(DiscussionController.class.getName());

    // 通过题目ID获取讨论区内容
    /*
    @RequestMapping(value = "getDiscussionByTopicId", method = RequestMethod.GET)
    public Result<List<Discussion>> getDiscussionStatus(@RequestParam("topicId") int topicId){
        List<Discussion> discussionsList = new ArrayList<>();
        discussionsList = discussionService.getDiscussionByTopicId(topicId);
        Result<List<Discussion>> resultDiscussion = new Result<>();
        resultDiscussion.setSuccess(true);
        resultDiscussion.setData(discussionsList);
        resultDiscussion.setCode(200);
        resultDiscussion.setMessage("success");
        return resultDiscussion;
    }

    // 通过题目ID获取讨论区内容
    @RequestMapping(value = "getDiscussionByTopicId1", method = RequestMethod.GET)
    public Result<List<DiscussionResponse>> getDiscussionStatus1(@RequestParam("topicId") int topicId){
        List<DiscussionResponse> discussionResponsesList = new ArrayList<>();
        discussionResponsesList = discussionService.getDiscussionByTopicId1(topicId);
        Result<List<DiscussionResponse>> resultDiscussion = new Result<>();
        resultDiscussion.setSuccess(true);
        resultDiscussion.setData(discussionResponsesList);
        resultDiscussion.setCode(200);
        resultDiscussion.setMessage("success");
        return resultDiscussion;
    }


    @RequestMapping(value = "getDiscussionByTopicId2", method = RequestMethod.GET)
    public Result<Map<DiscussionResponse,List<DiscussionResponse>>> getDiscussionStatus2(@RequestParam("topicId") int topicId){
        Map<DiscussionResponse,List<DiscussionResponse>> discussionResponsesMap = new HashMap<>();
        discussionResponsesMap = discussionService.getDiscussionByTopicId2(topicId);
        Result<Map<DiscussionResponse,List<DiscussionResponse>>> resultDiscussion = new Result<>();
        resultDiscussion.setSuccess(true);
        resultDiscussion.setData(discussionResponsesMap);
        resultDiscussion.setCode(200);
        resultDiscussion.setMessage("success");
        return resultDiscussion;
    }

    @RequestMapping(value = "getDiscussionByTopicId3", method = RequestMethod.GET)
    public Result<Discussions<List<DiscussionResponse>>> getDiscussionStatus3(@RequestParam("topicId") int topicId){
        Discussions<List<DiscussionResponse>> discussionResponses = new Discussions<>();
        discussionResponses = discussionService.getDiscussionByTopicId3(topicId);
        Result<Discussions<List<DiscussionResponse>>> resultDiscussion = new Result<>();
        if(discussionResponses==null){
            resultDiscussion.setSuccess(false);
            resultDiscussion.setData(null);
            resultDiscussion.setCode(500);
            resultDiscussion.setMessage("false");
        }
        else {
            resultDiscussion.setSuccess(true);
            resultDiscussion.setData(discussionResponses);
            resultDiscussion.setCode(200);
            resultDiscussion.setMessage("success");
        }
        return resultDiscussion;
    }
     */

    @RequestMapping(value = "getDiscussionByTopicId", method = RequestMethod.GET)
    public Result<List<Discussions<List<DiscussionResponse>>>> getDiscussionStatus(@RequestParam("topicId") int topicId){
        List<Discussions<List<DiscussionResponse>>> discussionResponses = new ArrayList<>();
        discussionResponses = discussionService.getDiscussionByTopicId(topicId);
        Result<List<Discussions<List<DiscussionResponse>>>> resultDiscussion = new Result<>();
        if(discussionResponses==null){
            // int[] array = new int[0];
            discussionResponses = new ArrayList<>(0);
            resultDiscussion.setSuccess(true);
            resultDiscussion.setData(discussionResponses);
            resultDiscussion.setCode(200);
            resultDiscussion.setMessage("true");
        }
        else {
            resultDiscussion.setSuccess(true);
            resultDiscussion.setData(discussionResponses);
            resultDiscussion.setCode(200);
            resultDiscussion.setMessage("success");
        }
        return resultDiscussion;
    }

    // 添加新的讨论
    @RequestMapping(value = "addNewDiscussion",method = RequestMethod.POST)
    public Result<Integer> submitDiscussion(@RequestParam("parentId") int parentId, @RequestParam("topicId") int topicId,
                                            @RequestParam("content") String content, @RequestParam("submit_time")Timestamp submit_time){
        Users user = UserContext.getCurrentUser();
        discussionService.addNewDiscussion(user.getId(),parentId,topicId,content,submit_time);
        Result<Integer> resultNewDiscussion = new Result<>();
        resultNewDiscussion.setMessage("success");
        resultNewDiscussion.setCode(200);
        resultNewDiscussion.setData(null);
        resultNewDiscussion.setSuccess(true);
        return resultNewDiscussion;
    }
    // 点赞
    @RequestMapping(value = "giveOneLike", method = RequestMethod.POST)
    public Result<Integer> updataLikeNum(@RequestParam("discussionId") int discussionId){
        discussionService.giveOneLike(discussionId);
        Result<Integer> resultLikeNum = new Result<>();
        resultLikeNum.setSuccess(true);
        resultLikeNum.setCode(200);
        resultLikeNum.setMessage("success");
        resultLikeNum.setData(null);
        return resultLikeNum;
    }
}
