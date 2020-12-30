package com.api.view.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

@ApiModel
public class PayResult {

	@ApiModelProperty(value="支付订单id")
	private String id;
	@ApiModelProperty(value="支付宝支付金额")
	private String money;
	@ApiModelProperty(value="平台1:支付宝2：微信3:银联")
	private Integer platform;
	@ApiModelProperty(value="支付宝支付回调地址")
	private String backUrl;

	@ApiModelProperty(value="支付宝支付参数")
	private String aliPay;
	@ApiModelProperty(value="微信支付参数")
	private String wxPay;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public Integer getPlatform() {
		return platform;
	}
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getAliPay() {
		return aliPay;
	}

	public void setAliPay(String aliPay) {
		this.aliPay = aliPay;
	}

	public String getWxPay() {
		return wxPay;
	}

	public void setWxPay(String wxPay) {
		this.wxPay = wxPay;
	}
}
