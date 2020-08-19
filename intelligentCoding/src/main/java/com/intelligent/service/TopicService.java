package com.intelligent.service;

import com.intelligent.controller.type.PageRequest;
import com.intelligent.controller.type.Result;
import com.intelligent.controller.type.UserPassMessage;
import com.intelligent.model.Topic;
import com.intelligent.type.TopicWithUserFavorite;

import java.util.List;
import java.util.Map;

public interface TopicService {

    List<Topic> findAll();

    Result getAll(PageRequest pageRequest);

    // 根据关键词查询题目
    Result getByKeyword(String keyword, PageRequest pageRequest);

    // tid查询题目完整信息
    TopicWithUserFavorite getTopicByTid(int userId, int tid);

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

    // 获取用户的提交信息
    UserPassMessage getUserPass(int uid);
}
