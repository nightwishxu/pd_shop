package com.base.api;

import com.base.api.base.BaseException;

public class ApiException extends BaseException {
    private static final long serialVersionUID = 1L;
    private IMError error;

    public IMError getError() {
        return this.error;
    }

    public void setError(IMError error) {
        this.error = error;
    }

    public ApiException(IMError error) {
        super(error.getCode(), error.getMsg());
        this.error = error;
    }

    public ApiException(final int code, final String msg) {
        super(code, msg);
        this.error = new IMError() {
            public String getMsg() {
                return msg;
            }

            public int getCode() {
                return code;
            }
        };
    }

    public ApiException(final String name) {
        super(1006, "缺失必要参数:" + name);
        this.error = new IMError() {
            public String getMsg() {
                return "缺失必要参数:" + name;
            }

            public int getCode() {
                return 1006;
            }
        };
    }
}
