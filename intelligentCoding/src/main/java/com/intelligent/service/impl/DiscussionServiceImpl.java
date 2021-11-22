package com.intelligent.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intelligent.controller.type.AnswerRequest;
import com.intelligent.controller.type.DiscussionRequest;
import com.intelligent.controller.type.PageRequest;
import com.intelligent.dao.DiscussionDao;
import com.intelligent.dao.DiscussionMapper;
import com.intelligent.model.Answer;
import com.intelligent.model.Discussion;
import com.intelligent.model.Topic;
import com.intelligent.service.DiscussionService;
import com.intelligent.type.AnswerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.time.LocalDateTime;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    private static final Logger Log = Logger.getLogger(DiscussionServiceImpl.class.getName());

    @Autowired
    private DiscussionDao discussionDao;
    @Autowired
    private DiscussionMapper discussionMapper;


    // 获取所有讨论内容
    @Override
    public List<Discussion> findAll() {
        return discussionMapper.selectAll();
    }

    // 根据题目ID查找讨论区内容
    @Override
    public List<Discussion> getDisscussionByTopicId(int TopicId){
        List<Discussion> discussions = discussionDao.getDisscussionByTopicId(TopicId);
        return discussions;
    }

    // 添加新的讨论
    @Override
    public void addNewDiscussion(DiscussionRequest discussionRequest,int userId) {
        Discussion exists;
        Discussion discussion=new Discussion();
        // 获取讨论的题目ID
        discussion.setTopicId(discussionRequest.getTopicId());
        // 加入当前用户ID
        discussion.setUserId(userId);
        // 获取讨论的楼主讨论的ID
        discussion.setParentId(discussionRequest.getParentId());
        setDiscussion(discussion,discussionRequest.getContent());
        try{
            discussionDao.save(discussion);
        }catch (DataIntegrityViolationException e){
            exists = discussionDao.findByUserIdAndTopicId(userId,discussionRequest.getTopicId());
            setDiscussion(exists,discussionRequest.getContent());
            discussionDao.save(exists);
        }

    }
    private void setDiscussion(Discussion discussion, String content) {
        discussion.setContent(content);
        discussion.setLikeNum(0);
        // 获得当前时间
        discussion.setSubmitTime(new Timestamp(System.currentTimeMillis()));
    }

    // 点赞
    @Override
    public void giveOneLike(DiscussionRequest discussionRequest) {
        int discussion_id = discussionRequest.getId();
        Discussion discussion = discussionMapper.getDisscussionById(discussion_id);
        discussion.setLikeNum(discussion.getLikeNum() + 1);
        discussionDao.save(discussion);

    }

    @Override
    public void giveOneLike1(int discussionId) {
        Discussion discussion = discussionMapper.getDisscussionById(discussionId);
        discussion.setLikeNum(discussion.getLikeNum() + 1);
        discussionDao.save(discussion);
    }
}
