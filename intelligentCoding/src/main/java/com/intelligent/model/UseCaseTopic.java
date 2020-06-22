package com.intelligent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.lang.annotation.Target;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "use_cases_topic", columnNames = {"userCaseId", "topicId"}))
public class UseCaseTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer useCaseId;
    private Integer topicId;

    @ManyToOne
    @JsonIgnoreProperties({})
    @JoinColumn(name = "useCaseId", insertable = false, updatable = false, nullable = false)
    private UseCase useCase;

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

    public Integer getUseCaseId() {
        return useCaseId;
    }

    public void setUseCaseId(Integer useCaseId) {
        this.useCaseId = useCaseId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public UseCase getUseCase() {
        return useCase;
    }

    public void setUseCase(UseCase useCase) {
        this.useCase = useCase;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "UseCaseTopic{" +
                "id=" + id +
                ", userCaseId=" + useCaseId +
                ", topicId=" + topicId +
                ", useCase=" + useCase +
                ", topic=" + topic +
                '}';
    }
}
