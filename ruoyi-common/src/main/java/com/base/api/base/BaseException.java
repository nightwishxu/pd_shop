package com.base.api.base;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private int code = 0;

    public BaseException(int errorcode, String msg) {
        super(msg);
        this.code = errorcode;
    }

    public BaseException(Exception e) {
        super(e);
    }

    public BaseException(int errorcode) {
        this.code = errorcode;
    }

    public int getCode() {
        return this.code;
    }
}
