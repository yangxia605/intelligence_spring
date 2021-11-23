package com.intelligent.dao;

import com.intelligent.model.Answer;
import com.intelligent.model.Discussion;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscussionDao extends JpaRepository<Discussion, Integer> {

    Discussion findByUserIdAndTopicId(int userId, int topicId);

//    List<Discussion> selectAll();

    // 获取全部讨论
    @Query(nativeQuery = true, value ="select * from discussion order by id")
    List<Discussion> getAll();

    //获取指定讨论ID的讨论
    @Query(nativeQuery = true, value ="select * from discussion where id=?1")
    Discussion getDisscussionById(int discussionId);

    // 根据题目ID查找讨论
    @Query(nativeQuery = true, value ="select * from discussion where topic_id=?1")
    List<Discussion> getDiscussionByTopicId(int topicId);

    // 根据楼主ID获取讨论
    @Query(nativeQuery = true, value ="select * from discussion where parent_id=?1")
    List<Discussion> getDiscussionByParentId(int parentId);

}
