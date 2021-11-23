package com.intelligent.service.impl;


import com.intelligent.controller.type.DiscussionRequest;
import com.intelligent.dao.DiscussionDao;
import com.intelligent.model.Discussion;
import com.intelligent.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
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

    // 根据题目ID查找讨论区内容
    @Override
    public Map<Discussion,List<Discussion>> getDiscussionByTopicId(int TopicId){
        List<Discussion> discussions = discussionDao.getDiscussionByTopicId(TopicId);
        int total=discussions.size();
        Map<Discussion,List<Discussion>> discussionListMap = new HashMap<>();
        /*
        for(int i=0;i<total;i++){
            if(discussions.get(i).getParentId()==-1){
                List<Discussion> discussions_i = getDiscussionByParentId(discussions.get(i).getId());
                int replyTotal=discussions_i.size();
                Discussion discusion = discussions.get(i);
                discussionListMap.put(discusion,discussions_i);
            }
        }

         */
        for(int i=0;i<total;i++){
            if(discussions.get(i).getParentId()==-1){
                Discussion discussion_i=discussions.get(i);
                discussionListMap.put(discussion_i,null);
            }
        }
        return discussionListMap;
    }

    private List<Discussion> getDiscussionByParentId(int parentId){
        List<Discussion> discussions = discussionDao.getDiscussionByParentId(parentId);
        return discussions;
    }

    // 获取新的讨论
    @Override
    public void addNewDiscussion(int userId,int parentId,int topicId,String content){
        Discussion discussion = new Discussion();
        discussion.setUserId(userId);
        discussion.setContent(content);
        discussion.setParentId(parentId);
        discussion.setTopicId(topicId);
        discussion.setLikeNum(0);
        discussion.setSubmitTime(new Timestamp(System.currentTimeMillis()));
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
