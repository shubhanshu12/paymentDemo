package com.shubhanshu.paytmdemo.dto;

public class SuccessDTO implements DTO {

    private String msg;

    public SuccessDTO(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SuccessDTO{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
