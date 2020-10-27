package com.base.exception;


import com.base.api.IMError;

public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer code;

    public Integer getCode() {
        return this.code;
    }

    public SystemException(Exception e) {
        super(e);
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(int code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(String message, Throwable e) {
        super(message, e);
    }

    public SystemException(IMError error) {
        super(error.getErrorMsg());
        this.code = error.getErrorCode();
    }
}
