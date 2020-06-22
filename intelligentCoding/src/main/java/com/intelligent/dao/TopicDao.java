package com.intelligent.dao;

import com.intelligent.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicDao extends JpaRepository<Topic, Integer> {
}
