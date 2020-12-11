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

    public Result(int code) {
        super(code);
    }

    public Result(int code, String errorMsg) {
        super(code, errorMsg);
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(int code, T data) {
        super(code);
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
