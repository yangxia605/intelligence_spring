package com.intelligent.dao;

import com.intelligent.model.UserFavoriteTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserFavoriteTopicDao extends JpaRepository<UserFavoriteTopic, Integer> {
    // 用户id查询收藏题目
    @Query(nativeQuery = true, value = "select topicID from userfavoritetopic where userId=?1")
    List<Map<String,Object>> getFavoriteTidByuid(int uid);
}
