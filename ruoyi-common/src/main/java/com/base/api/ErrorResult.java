package com.base.api;

import java.io.Serializable;

public class ErrorResult implements Serializable {
    private static final long serialVersionUID = -1593199144254776752L;
    private int errorCode;
    private String errorMsg;

    public ErrorResult(IMError error) {
        this.errorCode = error.getErrorCode();
        this.errorMsg = error.getErrorMsg();
    }

    public ErrorResult(int errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorResult(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
