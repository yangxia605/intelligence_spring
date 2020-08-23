package com.intelligent.dao;

import com.intelligent.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerDao extends JpaRepository<Answer, Integer>{
    /**
     * 数据库sql操作
     */
    Answer findByUserIdAndTopicId(int userId, int topicId);
}
