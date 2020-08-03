package com.intelligent.service.impl;

import com.github.pagehelper.PageHelper;
import com.intelligent.controller.type.PageRequest;
import com.intelligent.controller.type.Result;
import com.intelligent.dao.TopicMapper;
import com.intelligent.model.Topic;
import com.intelligent.service.TopicService;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<Topic> findAll() {
        return topicMapper.selectAll();
    }

    @Override
    public Result getAll(PageRequest pageRequest) {
        Result result = new Result();
        int page = pageRequest.getPage();
        int offset = pageRequest.getOffset();
        PageHelper.startPage(page, offset);
        List<Topic> list = topicMapper.getAll();
        result.setData(list);
        return result;
    }

    @Override
    public Result getByKeyword(String keyword, PageRequest pageRequest) {
        Result result = new Result();
        int page = pageRequest.getPage();
        int offset = pageRequest.getOffset();
        List<Topic> list = new ArrayList<>();
        PageHelper.startPage(page, offset);
        list = topicMapper.getByKeyword(keyword);
        result.setData(list);
        return result;
    }

    @Override
    public Topic getTopicByTid(int tid) {
        return topicMapper.getById(tid);
    }

    @Override
    public Result getTopicByLevel(int level, PageRequest pageRequest) {
        Result result = new Result();
        List<Topic> list = new ArrayList<>();
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getOffset());
        list = topicMapper.getTopicByLevel(level);
        result.setData(list);
        return result;
    }

    @Override
    public Result getTopicByDesc(PageRequest pageRequest) {
        Result result = new Result();
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
    public Result getTopicByRate(PageRequest pageRequest) {
        Result result = new Result();
        List<Topic> list = new ArrayList<>();
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getOffset());
        list = topicMapper.getTopicByRate();
        result.setData(list);
        return result;
    }

    @Override
    public Result getTopicByRateDesc(PageRequest pageRequest) {
        Result result = new Result();
        List<Topic> list = new ArrayList<>();
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getOffset());
        list = topicMapper.getTopicByRateDesc();
        result.setData(list);
        return result;
    }
}
