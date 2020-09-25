package com.util.WxBank;

public class BankCardApiResult {
    private String code;
    private Boolean charge;
    private Long remain;
    private String msg;
    private BankCardResult result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getCharge() {
        return charge;
    }

    public void setCharge(Boolean charge) {
        this.charge = charge;
    }

    public Long getRemain() {
        return remain;
    }

    public void setRemain(Long remain) {
        this.remain = remain;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BankCardResult getResult() {
        return result;
    }

    public void setResult(BankCardResult result) {
        this.result = result;
    }
}
