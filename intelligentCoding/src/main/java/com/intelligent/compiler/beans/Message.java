package com.intelligent.compiler.beans;

import java.time.LocalDateTime;
import java.util.Date;

public class Message {
    private Integer answerId;
    private LocalDateTime sendTime;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }
}
