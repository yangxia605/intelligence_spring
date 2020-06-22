package com.intelligent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "notes_users_topic", columnNames = {"userId", "topicId"}))
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer topicId;
    @NotNull
    private String content;
    @NotNull
    private Timestamp submitTime;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
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
        return "Notes{" +
                "id=" + id +
                ", userId=" + userId +
                ", topicId=" + topicId +
                ", content='" + content + '\'' +
                ", submitTime=" + submitTime +
                ", users=" + users +
                ", topic=" + topic +
                '}';
    }
}
