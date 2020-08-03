package com.intelligent.dao;

import com.intelligent.model.UserFavoriteTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserFavoriteTopicDao extends JpaRepository<UserFavoriteTopic, Integer> {
    // 用户id查询收藏题目id
    @Query(nativeQuery = true, value = "select topic_id from user_favorite_topic where user_id=?1")
    int[] getFavoriteTidByuid(int uid);

}
