package com.intelligent.dao;

import com.intelligent.model.Answer;
import com.intelligent.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscussionDao extends JpaRepository<Discussion, Integer> {

    Discussion findByUserIdAndTopicId(int userId, int topicId);

    @Query(nativeQuery = true, value ="select * from discussion where topic_id=?1")
    List<Discussion> getDisscussionByTopicId(int TopicId);

}
