package com.item.dao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 */
@ApiModel
@Table(name="b_pay_log")
public class PayLog implements Serializable {

	/**
	 *UUID
	 */
	@ApiModelProperty(value="UUID")
	@ApiParam(value="UUID")
	@Column(name = "id")
	private String id;

	/**
	 *用户id
	 */
	@ApiModelProperty(value="用户id")
	@ApiParam(value="用户id")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *订单id
	 */
	@ApiModelProperty(value="订单id")
	@ApiParam(value="订单id")
	@Column(name = "order_id")
	private Integer orderId;

	/**
	 *应付额
	 */
	@ApiModelProperty(value="应付额")
	@ApiParam(value="应付额")
	@Column(name = "money")
	private java.math.BigDecimal money;

	/**
	 *实付款
	 */
	@ApiModelProperty(value="实付款")
	@ApiParam(value="实付款")
	@Column(name = "real_pay")
	private java.math.BigDecimal realPay;

	/**
	 *买家
	 */
	@ApiModelProperty(value="买家")
	@ApiParam(value="买家")
	@Column(name = "buyer")
	private String buyer;

	/**
	 *卖家
	 */
	@ApiModelProperty(value="卖家")
	@ApiParam(value="卖家")
	@Column(name = "seller")
	private String seller;

	/**
	 *流水号
	 */
	@ApiModelProperty(value="流水号")
	@ApiParam(value="流水号")
	@Column(name = "trade_no")
	private String tradeNo;

	/**
	 *0:待付款 9:付款成功 10:订单更新成功
	 */
	@ApiModelProperty(value="0:待付款 9:付款成功 10:订单更新成功")
	@ApiParam(value="0:待付款 9:付款成功 10:订单更新成功")
	@Column(name = "state")
	private Integer state;

	/**
	 *0:余额1:支付宝2：微信
	 */
	@ApiModelProperty(value="0:余额1:支付宝2：微信")
	@ApiParam(value="0:余额1:支付宝2：微信")
	@Column(name = "platform")
	private Integer platform;

	/**
	 *创建时间
	 */
	@ApiModelProperty(value="创建时间")
	@ApiParam(value="创建时间")
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *修改时间
	 */
	@ApiModelProperty(value="修改时间")
	@ApiParam(value="修改时间")
	@Column(name = "modify_time")
	private java.util.Date modifyTime;

	/**
	 *微信支付key
	 */
	@ApiModelProperty(value="微信支付key")
	@ApiParam(value="微信支付key")
	@Column(name = "wx_key")
	private String wxKey;

	/**
	 *支付宝商户号
	 */
	@ApiModelProperty(value="支付宝商户号")
	@ApiParam(value="支付宝商户号")
	@Column(name = "alipay_partner")
	private String alipayPartner;

	/**
	 *支付额外参数（支付/充值等等）
	 */
	@ApiModelProperty(value="支付额外参数（支付/充值等等）")
	@ApiParam(value="支付额外参数（支付/充值等等）")
	@Column(name = "param")
	private String param;

	public void setId(String id) {
		this.id=id == null ? id : id.trim();
	}

	public String getId() {
		return id;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId=orderId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setMoney(java.math.BigDecimal money) {
		this.money=money;
	}

	public java.math.BigDecimal getMoney() {
		return money;
	}

	public void setRealPay(java.math.BigDecimal realPay) {
		this.realPay=realPay;
	}

	public java.math.BigDecimal getRealPay() {
		return realPay;
	}

	public void setBuyer(String buyer) {
		this.buyer=buyer == null ? buyer : buyer.trim();
	}

	public String getBuyer() {
		return buyer;
	}

	public void setSeller(String seller) {
		this.seller=seller == null ? seller : seller.trim();
	}

	public String getSeller() {
		return seller;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo=tradeNo == null ? tradeNo : tradeNo.trim();
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setState(Integer state) {
		this.state=state;
	}

	public Integer getState() {
		return state;
	}

	public void setPlatform(Integer platform) {
		this.platform=platform;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime=modifyTime;
	}

	public java.util.Date getModifyTime() {
		return modifyTime;
	}

	public void setWxKey(String wxKey) {
		this.wxKey=wxKey == null ? wxKey : wxKey.trim();
	}

	public String getWxKey() {
		return wxKey;
	}

	public void setAlipayPartner(String alipayPartner) {
		this.alipayPartner=alipayPartner == null ? alipayPartner : alipayPartner.trim();
	}

	public String getAlipayPartner() {
		return alipayPartner;
	}

	public void setParam(String param) {
		this.param=param == null ? param : param.trim();
	}

	public String getParam() {
		return param;
	}

}
