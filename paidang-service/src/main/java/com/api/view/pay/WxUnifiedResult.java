package com.api.view.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class WxUnifiedResult {
    @ApiModelProperty(value="小程序ID")
    private String appId;

    @ApiModelProperty(value="时间戳")
    private String timeStamp;

    @ApiModelProperty(value="随机串")
    private String nonceStr;

    @ApiModelProperty(value="预支付交易会话标识")
    private String prepayId;

    @ApiModelProperty(value="签名方式")
    private String signType;

    @ApiModelProperty(value="签名")
    private String paySign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
