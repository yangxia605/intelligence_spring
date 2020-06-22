package com.intelligent.dao;

import com.intelligent.model.UserFavoriteTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFavoriteTopicDao extends JpaRepository<UserFavoriteTopic, Integer> {
}
