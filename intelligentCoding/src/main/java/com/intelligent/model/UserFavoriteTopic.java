package com.intelligent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "user_favorite_topic_users_topic", columnNames = {"userId", "topicId"}))
public class UserFavoriteTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer topicId;

    @ManyToOne
    @JsonIgnoreProperties({"userFavoriteTopics"})
    @JoinColumn(name = "userId", insertable = false, updatable = false, nullable = false)
    private Users users;

    @ManyToOne
    @JsonIgnoreProperties({"userFavoriteTopics", "answers", "discussions", "solutions"})
    @JoinColumn(name = "topicId", insertable = false, updatable = false, nullable = false)
    private Topic topic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users user) {
        this.users = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "UserFavoriteTopic{" +
                "id=" + id +
                ", userId=" + userId +
                ", topicId=" + topicId +
                ", users=" + users +
                ", topic=" + topic +
                '}';
    }
}
