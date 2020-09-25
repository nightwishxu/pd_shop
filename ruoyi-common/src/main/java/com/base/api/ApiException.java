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
        super(error.getErrorCode(), error.getErrorMsg());
        this.error = error;
    }

    public ApiException(final int code, final String msg) {
        super(code, msg);
        this.error = new IMError() {
            public String getErrorMsg() {
                return msg;
            }

            public int getErrorCode() {
                return code;
            }
        };
    }

    public ApiException(final String name) {
        super(1006, "缺失必要参数:" + name);
        this.error = new IMError() {
            public String getErrorMsg() {
                return "缺失必要参数:" + name;
            }

            public int getErrorCode() {
                return 1006;
            }
        };
    }
}
