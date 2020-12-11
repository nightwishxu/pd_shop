package com.base.dao.model;

import com.base.api.IMError;
import com.base.util.JSONUtils;

import java.io.Serializable;

public class BaseResult implements Serializable {
    private static final long serialVersionUID = 783054937443211810L;
    private int code;
    private String msg;

    private Long total;

    public BaseResult() {
    }

    public BaseResult(IMError error) {
        this.code = error.getCode();
        this.msg = error.getMsg();
    }

    public BaseResult(int code) {
        this.code = code;
    }

    public BaseResult(int code, String msg) {
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



    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String toString() {
        return JSONUtils.serialize(this);
    }
}
