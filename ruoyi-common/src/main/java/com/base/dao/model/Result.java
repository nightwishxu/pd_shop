package com.base.dao.model;


import com.base.api.IMError;

public class Result<T> extends BaseResult {
    private static final long serialVersionUID = -8931309994379259791L;
    private T data;

    public Result() {
    }

    public Result(IMError error) {
        super(error);
    }

    public Result(int errorCode) {
        super(errorCode);
    }

    public Result(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(int errorCode, T data) {
        super(errorCode);
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
