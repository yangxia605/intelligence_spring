package com.intelligent.controller;


import com.intelligent.controller.type.AnswerRequest;
import com.intelligent.controller.type.AnswerStatusResponse;
import com.intelligent.controller.type.DiscussionRequest;
import com.intelligent.controller.type.Result;
import com.intelligent.model.Discussion;
import com.intelligent.model.Users;
import com.intelligent.service.DiscussionService;
import com.intelligent.type.TopicWithUserFavorite;
import com.intelligent.util.UserContext;
import com.sun.javafx.collections.MappingChange;
import org.apache.ibatis.jdbc.Null;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;
    private static final Logger log = Logger.getLogger(DiscussionController.class.getName());

    // 通过题目ID获取讨论区内容
    @RequestMapping(value = "getDiscussionByTopicId", method = RequestMethod.GET)
    public Result<Map<Discussion,List<Discussion>>> getDiscussionStatus(@RequestParam("topicId") int topicId){
        Map<Discussion,List<Discussion>> discussionListMap = new HashMap<>();
        //List<Discussion> discussionsList = new ArrayList<>();
        discussionListMap = discussionService.getDiscussionByTopicId(topicId);
        Result<Map<Discussion,List<Discussion>>> resultDiscussion = new Result<>();
        resultDiscussion.setSuccess(true);
        resultDiscussion.setData(discussionListMap);
        resultDiscussion.setCode(200);
        resultDiscussion.setMessage("success");
        return resultDiscussion;
    }


    // 添加新的讨论
    @RequestMapping(value = "addNewDiscussion",method = RequestMethod.POST)
    public Result<Integer> submitDiscussion1(@RequestParam("parentId") int parentId,
                                             @RequestParam("topicId") int topicId,@RequestParam("content") String content){
        Users user = UserContext.getCurrentUser();
        discussionService.addNewDiscussion(user.getId(),parentId,topicId,content);
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
