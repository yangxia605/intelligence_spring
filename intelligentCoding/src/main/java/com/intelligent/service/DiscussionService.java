package com.intelligent.service;

import com.github.pagehelper.PageInfo;
import com.intelligent.controller.type.DiscussionRequest;
import com.intelligent.controller.type.PageRequest;
import com.intelligent.model.Discussion;
import com.intelligent.model.Topic;

import java.util.List;
import java.util.Map;

public interface DiscussionService {
    List<Discussion> findAll();

    // 通过题目ID获取讨论区信息
    Map<Discussion,List<Discussion>> getDiscussionByTopicId(int TopicId);

    // 添加讨论
    void addNewDiscussion(int userId,int parentId,int topicId,String content);

    // 点赞
    void giveOneLike(int discussionId);
}
