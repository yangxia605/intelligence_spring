package com.intelligent.service;

import com.intelligent.model.UserFavoriteTopic;

public interface UserFavoriteTopicService {
    // tid查询题目完整信息
    UserFavoriteTopic addFavoriteTopicByTid(int tid, int uid);
    void  cancelFavoriteTopicByTid(int tid, int uid);
}
