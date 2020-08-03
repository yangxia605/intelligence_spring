package com.intelligent.service;

import com.intelligent.controller.type.PageRequest;
import com.intelligent.controller.type.Result;
import com.intelligent.model.Topic;

import java.util.List;

public interface TopicService {

    List<Topic> findAll();

    Result getAll(PageRequest pageRequest);

    // 根据关键词查询题目
    Result getByKeyword(String keyword, PageRequest pageRequest);

    // tid查询题目完整信息
    Topic getTopicByTid(int tid);

    // 通过题目难度查找题目
    Result getTopicByLevel(int level, PageRequest pageRequest);

    // 逆序查找
    Result getTopicByDesc(PageRequest pageRequest);

    // 按照知识点查询题目
    List<Topic> getTopicByTypes(int type, PageRequest pageRequest);

    // 根据通过率排序
    Result getTopicByRate(PageRequest pageRequest);
    // 根据通过率逆序
    Result getTopicByRateDesc(PageRequest pageRequest);
}
