package com.intelligent.service;

import com.github.pagehelper.PageInfo;
import com.intelligent.controller.type.DiscussionRequest;
import com.intelligent.controller.type.PageRequest;
import com.intelligent.model.Discussion;
import com.intelligent.model.Topic;

import java.util.List;

public interface DiscussionService {
    List<Discussion> findAll();

    // 通过题目ID获取讨论区信息
    List<Discussion> getDisscussionByTopicId(int TopicId);

    // 添加讨论
    void addNewDiscussion(DiscussionRequest discussionRequest,int userId);

    // 点赞
    void giveOneLike(DiscussionRequest discussionRequest);

    // 点赞
    void giveOneLike1(int discussionId);
}
