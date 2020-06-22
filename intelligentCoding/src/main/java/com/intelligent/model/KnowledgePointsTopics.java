package com.intelligent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "knowledge_points_topic", columnNames = {"knowledgePointsId", "topicId"}))
public class KnowledgePointsTopics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer knowledgePointsId;
    @NotNull
    private Integer topicId;

    @ManyToOne
    @JsonIgnoreProperties({})
    @JoinColumn(name = "knowledgePointsId",insertable = false,updatable = false,nullable = false)
    private KnowledgePoints knowledgePoints;

    @ManyToOne
    @JsonIgnoreProperties({})
    @JoinColumn(name = "topicId",insertable = false,updatable = false,nullable = false)
    private Topic topic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKnowledgePointsId() {
        return knowledgePointsId;
    }

    public void setKnowledgePointsId(Integer knowledgePointsId) {
        this.knowledgePointsId = knowledgePointsId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public KnowledgePoints getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(KnowledgePoints knowledgePoints) {
        this.knowledgePoints = knowledgePoints;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "KnowledgePointsTopics{" +
                "id=" + id +
                ", knowledgePointsId=" + knowledgePointsId +
                ", topicId=" + topicId +
                ", knowledgePoints=" + knowledgePoints +
                ", topic=" + topic +
                '}';
    }
}
