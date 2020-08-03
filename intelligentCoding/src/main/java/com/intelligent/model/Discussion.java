package com.intelligent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer parentId;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer topicId;
    @NotNull
    private String content;
    @NotNull
    private Timestamp submitTime;
    @NotNull
    private Integer likeNum = 0;

    @ManyToOne
    @JsonIgnoreProperties({"discussions", "answers", "solutions", "userFavoriteSolutions", "userFavoriteTopics", "note"})
    @JoinColumn(name = "userId", insertable = false, updatable = false, nullable = false)
    private Users users;

    @ManyToOne
    @JsonIgnoreProperties({"discussions", "answers", "solutions", "userFavoriteTopics",  "note"})
    @JoinColumn(name = "topicId", insertable = false, updatable = false, nullable = false)
    private Topic topic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
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
        return "Discussion{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", userId=" + userId +
                ", topicId=" + topicId +
                ", content='" + content + '\'' +
                ", submitTime=" + submitTime +
                ", likeNum=" + likeNum +
                ", users=" + users +
                ", topic=" + topic +
                '}';
    }
}
