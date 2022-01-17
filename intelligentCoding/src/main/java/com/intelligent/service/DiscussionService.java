package com.intelligent.service;

import com.intelligent.model.Discussion;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import com.intelligent.type.DiscussionResponse;
import com.intelligent.type.Discussions;

public interface DiscussionService {
    List<Discussion> findAll();

    // 通过题目ID获取讨论区信息
    /*
    List<Discussion> getDiscussionByTopicId(int TopicId);

    // 通过题目ID获取讨论区信息
    List<DiscussionResponse> getDiscussionByTopicId1(int TopicId);

    // 通过题目ID获取讨论区信息
    Map<DiscussionResponse,List<DiscussionResponse>> getDiscussionByTopicId2(int TopicId);

    Discussions<List<DiscussionResponse>> getDiscussionByTopicId3(int TopicId);

     */


    List<Discussions<List<DiscussionResponse>>> getDiscussionByTopicId(int TopicId);

    // 添加讨论
    void addNewDiscussion(int userId, int parentId, int topicId, String content, Timestamp submit_time);

    // 点赞
    void giveOneLike(int discussionId);
}
