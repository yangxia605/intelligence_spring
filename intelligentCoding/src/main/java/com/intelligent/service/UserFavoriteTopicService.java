package com.intelligent.service;

import com.intelligent.model.UserFavoriteTopic;

public interface UserFavoriteTopicService {
    UserFavoriteTopic addFavoriteTopicByTid(int tid, int uid);
    void  cancelFavoriteTopicByTid(int tid, int uid);
}
