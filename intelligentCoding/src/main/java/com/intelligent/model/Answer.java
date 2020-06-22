package com.intelligent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "user_topic", columnNames = {"user_id", "topic_id"}))
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer topicId;
    @NotNull
    private String code;
    @NotNull
    private Timestamp submitTime;
    private Integer memory;
    private String status;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Answer{" +
                "id=" + id +
                ", userId=" + userId +
                ", topicId=" + topicId +
                ", code='" + code + '\'' +
                ", submitTime=" + submitTime +
                ", memory=" + memory +
                ", status='" + status + '\'' +
                ", users=" + users +
                ", topic=" + topic +
                '}';
    }
}
