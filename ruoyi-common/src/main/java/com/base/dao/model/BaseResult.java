package com.base.dao.model;

import com.base.api.IMError;
import com.base.util.JSONUtils;
import java.io.Serializable;

public class BaseResult implements Serializable {
    private static final long serialVersionUID = 783054937443211810L;
    private int errorCode;
    private String errorMsg;

    public BaseResult() {
    }

    public BaseResult(IMError error) {
        this.errorCode = error.getErrorCode();
        this.errorMsg = error.getErrorMsg();
    }

    public BaseResult(int errorCode) {
        this.errorCode = errorCode;
    }

    public BaseResult(int errorCode, String errorMsg) {
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

    public String toString() {
        return JSONUtils.serialize(this);
    }
}
