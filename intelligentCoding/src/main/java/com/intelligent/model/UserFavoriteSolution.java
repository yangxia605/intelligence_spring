package com.intelligent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.UndeclaredThrowableException;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "user_favorite_solution_users_topic", columnNames = {"userId", "solutionId"}))
public class UserFavoriteSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer topicId;

    @ManyToOne
    @JsonIgnoreProperties({})
    @JoinColumn(name = "userId", insertable = false, updatable = false, nullable = false)
    private Users users;

    @ManyToOne
    @JsonIgnoreProperties({})
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

    public void setUsers(Users users) {
        this.users = users;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "UserFavoriteSolution{" +
                "id=" + id +
                ", userId=" + userId +
                ", topicId=" + topicId +
                ", users=" + users +
                ", topic=" + topic +
                '}';
    }
}
