package com.intelligent.service.impl;

import com.github.pagehelper.PageHelper;
import com.intelligent.controller.TopicController;
import com.intelligent.controller.type.PageRequest;
import com.intelligent.controller.type.Result;
import com.intelligent.controller.type.UserPassMessage;
import com.intelligent.dao.TopicMapper;
import com.intelligent.dao.UserFavoriteTopicDao;
import com.intelligent.model.Topic;
import com.intelligent.model.UserFavoriteTopic;
import com.intelligent.service.TopicService;
import com.intelligent.type.TopicWithUserFavorite;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class TopicServiceImpl implements TopicService {
    private static final Logger log = Logger.getLogger(TopicServiceImpl.class.getName());
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private UserFavoriteTopicDao userFavoriteTopicDao;

    @Override
    public List<Topic> findAll() {
        return topicMapper.selectAll();
    }

    @Override
    public Result<List<Topic>> getAll(PageRequest pageRequest) {
        Result<List<Topic>> result = new Result<>();
        int page = pageRequest.getPage();
        int offset = pageRequest.getOffset();
        PageHelper.startPage(page, offset);
        List<Topic> list = topicMapper.getAll();
        result.setData(list);
        return result;
    }

    @Override
    public Result<List<Topic>> getByKeyword(String keyword, PageRequest pageRequest) {
        Result<List<Topic>> result = new Result<>();
        int page = pageRequest.getPage();
        int offset = pageRequest.getOffset();
        List<Topic> list = new ArrayList<>();
        PageHelper.startPage(page, offset);
        list = topicMapper.getByKeyword(keyword);
        result.setData(list);
        return result;
    }

    @Override
    public TopicWithUserFavorite getTopicByTid(int userId, int tid) {
        //1.cha xun ti mu xin  xi
        Topic topic = topicMapper.getById(tid);
        //2.gen ju tid he user id cha xun shoucangbiao kan youmeiyou jilu
        UserFavoriteTopic userFavorite = userFavoriteTopicDao.findByUserIdAndTopicId(userId, tid);
        //3.you jilu jiushi bei shoucang fouze meiyou
        TopicWithUserFavorite topicWithUserFavorite = new TopicWithUserFavorite();
        BeanUtils.copyProperties(topic, topicWithUserFavorite);
        topicWithUserFavorite.setFavorite(userFavorite != null);
        return topicWithUserFavorite;
    }

    @Override
    public Result<List<Topic>> getTopicByLevel(int level, PageRequest pageRequest) {
        Result<List<Topic>> result = new Result<>();
        List<Topic> list = new ArrayList<>();
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getOffset());
        list = topicMapper.getTopicByLevel(level);
        result.setData(list);
        return result;
    }

    @Override
    public Result<List<Topic>> getTopicByDesc(PageRequest pageRequest) {
        Result<List<Topic>> result = new Result<>();
        List<Topic> list = new ArrayList<>();
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getOffset());
        list = topicMapper.getTopicByDesc();
        result.setData(list);
        return result;
    }

    @Override
    public List<Topic> getTopicByTypes(int type, PageRequest pageRequest) {
        List<Topic> list = new ArrayList<>();
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getOffset());
        list = topicMapper.getTopicByTypes(type);
        return list;
    }

    @Override
    public Result<List<Topic>> getTopicByRate(PageRequest pageRequest) {
        Result<List<Topic>> result = new Result<>();
        List<Topic> list = new ArrayList<>();
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getOffset());
        list = topicMapper.getTopicByRate();
        result.setData(list);
        return result;
    }

    @Override
    public Result<List<Topic>> getTopicByRateDesc(PageRequest pageRequest) {
        Result<List<Topic>> result = new Result<>();
        List<Topic> list = new ArrayList<>();
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getOffset());
        list = topicMapper.getTopicByRateDesc();
        result.setData(list);
        return result;
    }

    @Override
    public UserPassMessage getUserPass(int uid) {
        UserPassMessage userPassMessage = new UserPassMessage();
        userPassMessage.setTotalTopics(topicMapper.getTotal());
        userPassMessage.setChallengeCount(topicMapper.getChallenge(uid));
        userPassMessage.setPassCount(topicMapper.getPass(uid));
        userPassMessage.setPostCount(topicMapper.getPost(uid));
        return userPassMessage;
    }
}
