package com.shubhanshu.paytmdemo.dto;

public class ErrorDTO implements DTO {
    private String errorMsg;
    private String exception;

    public ErrorDTO(String msg) {
        this.errorMsg = msg;
        this.exception = null;
    }

    public ErrorDTO(String msg, String exception) {
        this.errorMsg = msg;
        this.exception = exception;
    }

    public String getMsg() {
        return errorMsg;
    }

    public void setMsg(String msg) {
        this.errorMsg = msg;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "msg='" + errorMsg + '\'' +
                ", exception='" + exception + '\'' +
                '}';
    }
}
