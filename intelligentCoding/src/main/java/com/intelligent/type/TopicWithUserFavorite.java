package com.intelligent.type;

import com.intelligent.model.*;

import java.util.List;

public class TopicWithUserFavorite {
    private Integer id;
    private String topicName;
    private Integer timeLimit;
    private Integer spaceLimit;
    private String topicIntro;
    private String inputIntro;
    private String outputIntro;
    private String topicPs;
    private int topicLevel;
    private int passCount;
    private int failCount;
    private double passRate;
    private List<Answer> answers;
    private List<Discussion> discussions;
    private List<KnowledgePointsTopics> knowledgePointsTopics;
    private List<Notes> notes;
    private List<Solution> solutions;
    private List<UseCaseTopic> useCaseTopics;
    private List<com.intelligent.model.UserFavoriteTopic> userFavoriteTopics;
    private Boolean isFavorite;

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

    public int getTopicLevel() {
        return topicLevel;
    }

    public void setTopicLevel(int topicLevel) {
        this.topicLevel = topicLevel;
    }

    public int getPassCount() {
        return passCount;
    }

    public void setPassCount(int passCount) {
        this.passCount = passCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public double getPassRate() {
        return passRate;
    }

    public void setPassRate(double passRate) {
        this.passRate = passRate;
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

    public List<com.intelligent.model.UserFavoriteTopic> getUserFavoriteTopics() {
        return userFavoriteTopics;
    }

    public void setUserFavoriteTopics(List<com.intelligent.model.UserFavoriteTopic> userFavoriteTopics) {
        this.userFavoriteTopics = userFavoriteTopics;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
