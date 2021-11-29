package com.intelligent.service.impl;


import com.intelligent.type.DiscussionResponse;
import com.intelligent.dao.DiscussionDao;
import com.intelligent.model.Discussion;
import com.intelligent.service.DiscussionService;
import com.intelligent.type.Discussions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    private static final Logger Log = Logger.getLogger(DiscussionServiceImpl.class.getName());

    @Autowired
    private DiscussionDao discussionDao;


    // 获取所有讨论内容
    @Override
    public List<Discussion> findAll() {
        return discussionDao.findAll();
    }

    /*
    // 根据题目ID查找讨论区内容
    @Override
    public List<Discussion> getDiscussionByTopicId(int TopicId) {
        List<Discussion> discussions = discussionDao.getDiscussionByTopicId(TopicId);
        return discussions;
    }

    // 根据题目ID查找讨论区全部内容，没有分楼主还是回复
    @Override
    public List<DiscussionResponse> getDiscussionByTopicId1(int TopicId) {
        List<Discussion> discussions = discussionDao.getDiscussionByTopicId(TopicId);
        List<DiscussionResponse> discussionResponses = new ArrayList<>();
        int total=discussions.size();
        if(total>0) {
            for (int i = 0; i < total; i++) {
                DiscussionResponse discussionResponse = new DiscussionResponse();
                Discussion discussion = discussions.get(i);
                discussionResponse.setId(discussion.getId());
                discussionResponse.setParentId(discussion.getParentId());
                discussionResponse.setContent(discussion.getContent());
                discussionResponse.setTopicId(discussion.getTopicId());
                discussionResponse.setUserId(discussion.getUserId());
                discussionResponse.setSubmitTime(discussion.getSubmitTime());
                discussionResponse.setLikeNum(discussion.getLikeNum());
                discussionResponses.add(discussionResponse);
            }
        }
        else{
            discussionResponses=null;
        }
        return discussionResponses;
    }

    @Override
    public Map<DiscussionResponse,List<DiscussionResponse>> getDiscussionByTopicId2(int TopicId) {
        List<DiscussionResponse> discussionResponses = new ArrayList<>();
        discussionResponses = getDiscussionsByTopicId(TopicId);
        Map<DiscussionResponse,List<DiscussionResponse>> Result = new HashMap<>();
        int total=discussionResponses.size();
        if(total<1){
            Result = null;
        }
        else{
            for(int i=0;i<total;i++){
                DiscussionResponse discussionResponsei = discussionResponses.get(i);
                if(discussionResponsei.getParentId()==-1){// 此时为楼主
                    List<DiscussionResponse> discussionResponsesi=new ArrayList<>();
                    discussionResponsesi= getDiscussionsByParentId(discussionResponsei.getId());
                    Result.put(discussionResponsei,discussionResponsesi);
                }
            }
        }
        return Result;
    }

    @Override
    public Discussions<List<DiscussionResponse>> getDiscussionByTopicId3(int TopicId) {
        List<DiscussionResponse> discussionResponses = new ArrayList<>();
        discussionResponses = getDiscussionsByTopicId(TopicId);
        Discussions<List<DiscussionResponse>> Result = new Discussions<>();
        int total=discussionResponses.size();
        if(total<1){
            Result.setDiscussionResponse(null);
            Result.setRaply(null);;
        }
        else{
            for(int i=0;i<total;i++){
                DiscussionResponse discussionResponsei = discussionResponses.get(i);
                if(discussionResponsei.getParentId()==-1){// 此时为楼主
                    List<DiscussionResponse> discussionResponsesi=new ArrayList<>();
                    discussionResponsesi= getDiscussionsByParentId(discussionResponsei.getId());// 此时为回复的讨论
                    if(discussionResponsesi==null){
                        Result.setDiscussionResponse(discussionResponses.get(i));
                        Result.setRaply(null);
                    }
                    else {
                        Result.setDiscussionResponse(discussionResponses.get(i));
                        Result.setRaply(discussionResponsesi);
                    }
                }
            }
        }
        return Result;
    }
     */
    @Override
    public List<Discussions<List<DiscussionResponse>>> getDiscussionByTopicId(int TopicId) {
        List<DiscussionResponse> discussionResponses = new ArrayList<>();
        discussionResponses = getDiscussionsByTopicId(TopicId);
        List<Discussions<List<DiscussionResponse>>> Result = new ArrayList<>();
        if(discussionResponses==null) {
            Result = null;
            ;
        }
        else{
            int total=discussionResponses.size();
            for(int i=0;i<total;i++){
                DiscussionResponse discussionResponsei = discussionResponses.get(i);
                Discussions<List<DiscussionResponse>> Resulti = new Discussions<>();
                if(discussionResponsei.getParentId()==-1){// 此时为楼主
                    List<DiscussionResponse> discussionResponsesi=new ArrayList<>();
                    discussionResponsesi= getDiscussionsByParentId(discussionResponsei.getId());// 此时为回复的讨论
                    if(discussionResponsesi==null){
                        Resulti.setDiscussionResponse(discussionResponses.get(i));
                        Resulti.setReply(null);
                    }
                    else {
                        Resulti.setDiscussionResponse(discussionResponses.get(i));
                        Resulti.setReply(discussionResponsesi);
                    }
                }
                else {
                    continue;
                }
                Result.add(Resulti);
            }
        }
        return Result;
    }

    // 根据题目ID查找讨论区全部内容，没有分楼主还是回复
    private List<DiscussionResponse> getDiscussionsByTopicId(int topicId) {
        List<Discussion> discussions = discussionDao.getDiscussionByTopicId(topicId);
        List<DiscussionResponse> discussionResponses = new ArrayList<>();
        int total=discussions.size();
        if(total>0) {
            for (int i = 0; i < total; i++) {
                DiscussionResponse discussionResponse = new DiscussionResponse();
                Discussion discussion = discussions.get(i);
                discussionResponse.setId(discussion.getId());
                discussionResponse.setParentId(discussion.getParentId());
                discussionResponse.setContent(discussion.getContent());
                discussionResponse.setTopicId(discussion.getTopicId());
                discussionResponse.setUserId(discussion.getUserId());
                discussionResponse.setSubmitTime(discussion.getSubmitTime());
                discussionResponse.setLikeNum(discussion.getLikeNum());
                discussionResponses.add(discussionResponse);
            }
        }
        else{
            discussionResponses=null;
        }
        return discussionResponses;
    }

    // 根据父亲ID获取讨论内容
    private List<DiscussionResponse> getDiscussionsByParentId(int parentId){
        List<Discussion> discussions = discussionDao.getDiscussionByParentId(parentId);
        List<DiscussionResponse> discussionResponses = new ArrayList<>();
        int total=discussions.size();
        if(total>0) {
            for (int i = 0; i < total; i++) {
                DiscussionResponse discussionResponse = new DiscussionResponse();
                Discussion discussion = discussions.get(i);
                discussionResponse.setId(discussion.getId());
                discussionResponse.setParentId(discussion.getParentId());
                discussionResponse.setContent(discussion.getContent());
                discussionResponse.setTopicId(discussion.getTopicId());
                discussionResponse.setUserId(discussion.getUserId());
                discussionResponse.setSubmitTime(discussion.getSubmitTime());
                discussionResponse.setLikeNum(discussion.getLikeNum());
                discussionResponses.add(discussionResponse);
            }
        }
        else{
            discussionResponses=null;
        }
        return discussionResponses;
    }

    // 获取新的讨论
    @Override
    public void addNewDiscussion(int userId,int parentId,int topicId,String content,Timestamp submit_time){
        Discussion discussion = new Discussion();
        discussion.setUserId(userId);
        discussion.setContent(content);
        discussion.setParentId(parentId);
        discussion.setTopicId(topicId);
        discussion.setLikeNum(0);
        discussion.setSubmitTime(submit_time);
        discussionDao.save(discussion);
    }

    // 点赞
    @Override
    public void giveOneLike(int discussionId) {
        Discussion discussion = discussionDao.getDisscussionById(discussionId);
        discussion.setLikeNum(discussion.getLikeNum() + 1);
        discussionDao.save(discussion);
    }
}
