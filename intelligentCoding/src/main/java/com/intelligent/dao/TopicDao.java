package com.intelligent.dao;

import com.intelligent.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface TopicDao extends JpaRepository<Topic, Integer> {

    // 查询全部题目id,topic_name,time_limit,space_limit
    @Query(nativeQuery = true, value = "select id,topic_name,time_limit,space_limit from topic")
    List<Map<String,Object>> getAllTopicsInfo();

    // 查询全部题目
    @Query(nativeQuery = true, value = "select * from topic order by topic.id asc")
    List<Topic> getAll();

    // 根据关键词查询题目
    @Query(nativeQuery = true, value = "select * from topic where topic_name like %?1%")
    List<Topic> getByKeyword(String keyword);

    // tid查询题目id,topic_name,time_limit,space_limit
    @Query(nativeQuery = true, value = "select id,topic_name,time_limit,space_limit from topic where id = ?1")
    Map<String,Object> getTopicInfobyTid(int tid);

    // tid查询题目完整信息
    @Query(nativeQuery = true, value = "select * from topic where id = ?1")
    Topic getTopicbyTid(int tid);

    // 通过题目难度查找题目
    @Query(nativeQuery = true, value = "select * from topic where topic_level = ?1")
    List<Topic> getTopicByLevel(int level);

    // 逆序查找
    @Query(nativeQuery = true, value = "select * from topic order by topic.id desc")
    List<Topic> getTopicByDesc();

    // 按照知识点查询题目
    @Query(nativeQuery = true, value = "select * from topic as t, knowledge_points_topics as kpt, knowledge_points as kp where t.id=kpt.topic_id and kpt.knowledge_points_id=kp.id and kp.id=?1")
    List<Topic> getTopicByTypes(int type);

    // 根据通过率排序
    @Query(nativeQuery = true, value = "select * from topic as t order by t.pass_rate, t.id")
    List<Topic> getTopicByRate();
    // 根据通过率排序
    @Query(nativeQuery = true, value = "select * from topic as t order by t.pass_rate desc, t.id desc")
    List<Topic> getTopicByRateDesc();

}
