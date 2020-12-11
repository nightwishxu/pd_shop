package com.base.api;

import java.io.Serializable;

public class ErrorResult implements Serializable {
    private static final long serialVersionUID = -1593199144254776752L;
    private int code;
    private String msg;

    public ErrorResult(IMError error) {
        this.code = error.getCode();
        this.msg = error.getMsg();
    }

    public ErrorResult(int code) {
        this.code = code;
    }

    public ErrorResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
