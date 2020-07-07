package com.intelligent.dao;

import com.intelligent.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface TopicDao extends JpaRepository<Topic, Integer> {
    // 查询全部题库
    @Query(nativeQuery = true, value = "select id,topic_name,time_limit,space_limit from topic")
    List<Map<String,Object>> getAllTopics();
}
