package com.intelligent.service.impl;

import com.intelligent.dao.UserFavoriteTopicDao;
import com.intelligent.model.UserFavoriteTopic;
import com.intelligent.service.UserFavoriteTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFavoriteTopicServiceImpl implements UserFavoriteTopicService {
    @Autowired
    private UserFavoriteTopicDao userFavoriteTopicDao;

    @Override
    public UserFavoriteTopic addFavoriteTopicByTid(int tid, int uid) {
        UserFavoriteTopic userFavoriteTopic = new UserFavoriteTopic();
        userFavoriteTopic.setTopicId(tid);
        userFavoriteTopic.setUserId(uid);
        return userFavoriteTopicDao.save(userFavoriteTopic);
    }

    @Override
    public void cancelFavoriteTopicByTid(int tid, int uid) {
        UserFavoriteTopic userFavoriteTopic = userFavoriteTopicDao.findByUserIdAndTopicId(uid, tid);
        userFavoriteTopicDao.delete(userFavoriteTopic);
    }

}
