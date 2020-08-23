package com.intelligent.controller.type;


public class AnswerStatusResponse {
    private Boolean answerStatus;//提交的答案在后端有没有执行完成

    public Boolean getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(Boolean answerStatus) {
        this.answerStatus = answerStatus;
    }
}

