package com.intelligent.dao;

import com.intelligent.controller.type.PageResult;
import com.intelligent.model.Topic;
import net.sf.jsqlparser.statement.select.Top;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("topicMapper")
public interface TopicMapper {
    List<Topic> selectAll();

    // 获取全部题目
    @Select(value = "select * from topic order by topic.id")
    List<Topic> getAll();

    // 根据关键词查询题目
    // 模糊查询很坑啊
    @Select(value = "select * from topic where topic.topic_name like CONCAT('%',#{keyword},'%')")
    List<Topic> getByKeyword(@Param("keyword")String keyword);

    // 通过题目难度查找题目
    @Select(value = "select * from topic where topic_level = #{level} order by topic.id")
    List<Topic> getTopicByLevel(int level);

    // 逆序查找
    @Select(value = "select * from topic order by topic.id desc")
    List<Topic> getTopicByDesc();

    // 按照知识点查询题目
    @Select(value = "select * from topic as t, knowledge_points_topics as kpt, knowledge_points as kp where t.id=kpt.topic_id and kpt.knowledge_points_id=kp.id and kp.id=#{type}")
    List<Topic> getTopicByTypes(int type);

    // 根据通过率排序
    @Select(value = "select * from topic as t order by t.pass_rate, t.id")
    List<Topic> getTopicByRate();
    // 根据通过率倒序排序
    @Select(value = "select * from topic as t order by t.pass_rate desc, t.id")
    List<Topic> getTopicByRateDesc();


    // 根据topic id筛选出该题目信息
    @Select(value = "select * from topic where topic.id =#{id}")
    Topic getById(@Param("id")Integer id);

    // 获取总题目数量
    @Select(value = "select count(t.*) as total from topic as t")
    int getTotal();

    // 获取挑战题目数量
    @Select(value = "select count (*) from users u, answer a where u.id=a.user_id and u.id=#{userId}")
    int getChallenge(int userId);

    // 获取通过题数
    @Select(value = "select count(*) from users u, answer a where u.id=a.user_id and u.id=#{userId} and u.is_pass=true")
    int getPass(int userId);

    // 获取提交总数
    @Select(value = "select sum(a.succ_count + a.fail_count) from users u, answer a where u.id=a.user_id and u.id=#{userId}")
    int getPost(int userId);

}
