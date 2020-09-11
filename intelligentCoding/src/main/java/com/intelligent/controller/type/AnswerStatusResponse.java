package com.intelligent.controller.type;


public class AnswerStatusResponse {
    private Boolean answerStatus;//提交的答案在后端有没有执行完成

    private String executeDetailMsg;//执行完毕后获得答案的执行信息
    private String answerStatusMsg;//执行完毕后获得答案的执行最终状态

    public Boolean getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(Boolean answerStatus) {
        this.answerStatus = answerStatus;
    }

    public String getExecuteDetailMsg() {
        return executeDetailMsg;
    }

    public void setExecuteDetailMsg(String executeDetailMsg) {
        this.executeDetailMsg = executeDetailMsg;
    }

    public String getAnswerStatusMsg() {
        return answerStatusMsg;
    }

    public void setAnswerStatusMsg(String answerStatusMsg) {
        this.answerStatusMsg = answerStatusMsg;
    }
}

