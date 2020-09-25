package com.util.WxBank;

public class BankCardResult {
    private String code; //状态码
    private String msg; //验证通过,验证说明
    private String token;
    private BankCardInfo data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BankCardInfo getData() {
        return data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setData(BankCardInfo data) {
        this.data = data;
    }
}
