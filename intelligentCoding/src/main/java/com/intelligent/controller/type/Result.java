package com.intelligent.controller.type;

import java.util.List;

public class Result {
    private boolean success;
    private String message;
    private Integer code;
    private List<Object> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
