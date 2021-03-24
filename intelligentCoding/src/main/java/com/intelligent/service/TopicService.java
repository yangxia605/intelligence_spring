package com.intelligent.service;

import com.github.pagehelper.PageInfo;
import com.intelligent.controller.type.PageRequest;
import com.intelligent.controller.type.Result;
import com.intelligent.controller.type.UserPassMessage;
import com.intelligent.model.Topic;
import com.intelligent.type.TopicWithUserFavorite;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

public interface TopicService {

    List<Topic> findAll();

    PageInfo getAll(PageRequest pageRequest);

    // 根据关键词查询题目
    PageInfo getByKeyword(String keyword, PageRequest pageRequest);

    // tid查询题目完整信息
    TopicWithUserFavorite getTopicByTid(int userId, int tid);

    // 通过题目难度查找题目
    PageInfo getTopicByLevel(int level, PageRequest pageRequest);

    // 逆序查找
    PageInfo getTopicByDesc(PageRequest pageRequest);

    // 按照知识点查询题目
    PageInfo getTopicByTypes(List<Integer> type, PageRequest pageRequest);

    // 根据通过率排序
    PageInfo getTopicByRate(PageRequest pageRequest);
    // 根据通过率逆序
    PageInfo getTopicByRateDesc(PageRequest pageRequest);

    // 获取用户的提交信息
    UserPassMessage getUserPass(int uid);
}
