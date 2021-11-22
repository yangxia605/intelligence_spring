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
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<Discussion> getDiscussionStatus(@RequestParam("topicId") int topicId){
        List<Discussion> discussionsList = new ArrayList<>();
        discussionsList = discussionService.getDisscussionByTopicId(topicId);
        return discussionsList;
    }

    // 添加新的讨论区内容，首先将新的讨论放到数据库中
    @PostMapping(path = "addNewDiscussion", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void submitDiscussion(@ModelAttribute DiscussionRequest discussionRequest){
        // 用户即当前用户
        Users user = UserContext.getCurrentUser();
        discussionService.addNewDiscussion(discussionRequest,user.getId());
    }

    // 点赞
    @PostMapping(path = "giveOneLike", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updataLikeNum(@ModelAttribute DiscussionRequest discussionRequest){
        discussionService.giveOneLike(discussionRequest);
    }

    @PostMapping(path = "giveOneLike1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updataLikeNum1(@RequestParam("discussionId") int discussionId){
        discussionService.giveOneLike1(discussionId);
    }
}
