package com.util.WxBank;

public class BankCardInfo {
    private String identityNo; //身份证号
    private String bankCardNo; //银行卡号
    private String name; //姓名
    private String mobile; //手机号
    private String resultCode; //业务结果状态码
    private String resultMsg; //业务结果中文描述
    private String remark;
//    private String channel; //数据渠道
    private BankCardBin bankCardBin;  //银行卡卡bin
//    private BankThirdParty bankThirdParty; //附加信息

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

//    public String getChannel() {
//        return channel;
//    }
//
//    public void setChannel(String channel) {
//        this.channel = channel;
//    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BankCardBin getBankCardBin() {
        return bankCardBin;
    }

    public void setBankCardBin(BankCardBin bankCardBin) {
        this.bankCardBin = bankCardBin;
    }

//    public BankThirdParty getBankThirdParty() {
//        return bankThirdParty;
//    }
//
//    public void setBankThirdParty(BankThirdParty bankThirdParty) {
//        this.bankThirdParty = bankThirdParty;
//    }
}
