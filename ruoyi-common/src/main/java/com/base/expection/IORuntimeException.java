package com.base.expection;


import com.base.util.StringUtil;

public class IORuntimeException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public IORuntimeException(Throwable e) {
        super(e.getMessage(), e);
    }

    public IORuntimeException(String message) {
        super(message);
    }

    public IORuntimeException(String messageTemplate, Object... params) {
        super(StringUtil.format(messageTemplate, params));
    }

    public IORuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IORuntimeException(Throwable throwable, String messageTemplate, Object... params) {
        super(StringUtil.format(messageTemplate, params), throwable);
    }

    public boolean causeInstanceOf(Class<? extends Throwable> clazz) {
        Throwable cause = this.getCause();
        return null != cause && clazz.isInstance(cause);
    }
}
