package com.intelligent.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String topicName;
    private Integer timeLimit;
    private Integer spaceLimit;
    private String topicIntro;
    private String inputIntro;
    private String outputIntro;
    private String topicPs;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("topic")
    private List<Answer> answers;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("topic")
    private List<Discussion> discussions;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("topic")
    private List<KnowledgePointsTopics> knowledgePointsTopics;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("topic")
    private List<Notes> notes;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("topic")
    private List<Solution> solutions;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("topic")
    private List<UseCaseTopic> useCaseTopics;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("topic")
    private List<UserFavoriteSolution> userFavoriteSolutions;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("topic")
    private List<UserFavoriteTopic> userFavoriteTopics;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getSpaceLimit() {
        return spaceLimit;
    }

    public void setSpaceLimit(Integer spaceLimit) {
        this.spaceLimit = spaceLimit;
    }

    public String getTopicIntro() {
        return topicIntro;
    }

    public void setTopicIntro(String topicIntro) {
        this.topicIntro = topicIntro;
    }

    public String getInputIntro() {
        return inputIntro;
    }

    public void setInputIntro(String inputIntro) {
        this.inputIntro = inputIntro;
    }

    public String getOutputIntro() {
        return outputIntro;
    }

    public void setOutputIntro(String outputIntro) {
        this.outputIntro = outputIntro;
    }

    public String getTopicPs() {
        return topicPs;
    }

    public void setTopicPs(String topicPs) {
        this.topicPs = topicPs;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(List<Discussion> discussions) {
        this.discussions = discussions;
    }

    public List<KnowledgePointsTopics> getKnowledgePointsTopics() {
        return knowledgePointsTopics;
    }

    public void setKnowledgePointsTopics(List<KnowledgePointsTopics> knowledgePointsTopics) {
        this.knowledgePointsTopics = knowledgePointsTopics;
    }

    public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public List<UseCaseTopic> getUseCaseTopics() {
        return useCaseTopics;
    }

    public void setUseCaseTopics(List<UseCaseTopic> useCaseTopics) {
        this.useCaseTopics = useCaseTopics;
    }

    public List<UserFavoriteSolution> getUserFavoriteSolutions() {
        return userFavoriteSolutions;
    }

    public void setUserFavoriteSolutions(List<UserFavoriteSolution> userFavoriteSolutions) {
        this.userFavoriteSolutions = userFavoriteSolutions;
    }

    public List<UserFavoriteTopic> getUserFavoriteTopics() {
        return userFavoriteTopics;
    }

    public void setUserFavoriteTopics(List<UserFavoriteTopic> userFavoriteTopics) {
        this.userFavoriteTopics = userFavoriteTopics;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", topicName='" + topicName + '\'' +
                ", timeLimit=" + timeLimit +
                ", spaceLimit=" + spaceLimit +
                ", topicIntro='" + topicIntro + '\'' +
                ", inputIntro='" + inputIntro + '\'' +
                ", outputIntro='" + outputIntro + '\'' +
                ", topicPs='" + topicPs + '\'' +
                ", answers=" + answers +
                ", discussions=" + discussions +
                ", knowledgePointsTopics=" + knowledgePointsTopics +
                ", notes=" + notes +
                ", solutions=" + solutions +
                ", useCaseTopics=" + useCaseTopics +
                ", userFavoriteSolutions=" + userFavoriteSolutions +
                ", userFavoriteTopics=" + userFavoriteTopics +
                '}';
    }
}
