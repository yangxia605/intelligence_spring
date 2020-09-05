package com.intelligent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    private LocalDateTime submitTime;
    private Integer memory;
    private String status;
    private String executeDetailMsg;

    @Column(insertable = false, columnDefinition = "boolean default false")
    private Boolean isPass;
    @Column(insertable = false, columnDefinition = "int default 0")
    private int succCount = 0;
    @Column(insertable = false, columnDefinition = "int default 0")
    private int failCount = 0;

    @ManyToOne
    @JsonIgnoreProperties({"answers", "discussions", "solutions", "userFavoriteSolutions", "userFavoriteTopics", "note"})
    @JoinColumn(name = "userId", insertable = false, updatable = false, nullable = false)
    private Users users;

    @ManyToOne
    @JsonIgnoreProperties({"answers", "discussions", "solutions", "userFavoriteTopics",  "note"})
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

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
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

    public Boolean getPass() {
        return isPass;
    }

    public void setPass(Boolean pass) {
        isPass = pass;
    }

    public int getSuccCount() {
        return succCount;
    }

    public void setSuccCount(int succCount) {
        this.succCount = succCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public String getExecuteDetailMsg() {
        return executeDetailMsg;
    }

    public void setExecuteDetailMsg(String executeDetailMsg) {
        this.executeDetailMsg = executeDetailMsg;
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
                ", isPass=" + isPass +
                ", executeDetailMsg=" + executeDetailMsg +
                ", succCount=" + succCount +
                ", failCount=" + failCount +
                ", users=" + users +
                ", topic=" + topic +
                '}';
    }
}
