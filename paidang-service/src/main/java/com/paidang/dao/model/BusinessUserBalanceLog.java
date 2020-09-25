package com.paidang.dao.model;

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
@Table(name="p_business_user_balance_log")
public class BusinessUserBalanceLog implements Serializable {

	/**
	 *UUID
	 */
	@ApiModelProperty(value="UUID")
	@ApiParam(value="UUID")
	@Column(name = "id")
	private Integer id;

	/**
	 *用户id
	 */
	@ApiModelProperty(value="用户id")
	@ApiParam(value="用户id")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *1增2减
	 */
	@ApiModelProperty(value="1增2减")
	@ApiParam(value="1增2减")
	@Column(name = "type")
	private Integer type;

	/**
	 *金额
	 */
	@ApiModelProperty(value="金额")
	@ApiParam(value="金额")
	@Column(name = "amount")
	private java.math.BigDecimal amount;

	/**
	 *1商城收入，2退款，3提现
	 */
	@ApiModelProperty(value="1商城收入，2退款，3提现")
	@ApiParam(value="1商城收入，2退款，3提现")
	@Column(name = "item")
	private String item;

	/**
	 *备注
	 */
	@ApiModelProperty(value="备注")
	@ApiParam(value="备注")
	@Column(name = "info")
	private String info;

	/**
	 *0余额1支付宝2微信10线下银行卡
	 */
	@ApiModelProperty(value="0余额1支付宝2微信10线下银行卡")
	@ApiParam(value="0余额1支付宝2微信10线下银行卡")
	@Column(name = "trade_type")
	private Integer tradeType;

	/**
	 *支付宝微信流水号(线下打款当号)
	 */
	@ApiModelProperty(value="支付宝微信流水号(线下打款当号)")
	@ApiParam(value="支付宝微信流水号(线下打款当号)")
	@Column(name = "trade_no")
	private String tradeNo;

	/**
	 *线下打款凭证
	 */
	@ApiModelProperty(value="线下打款凭证")
	@ApiParam(value="线下打款凭证")
	@Column(name = "trade_ticket")
	private String tradeTicket;

	/**
	 *银行
	 */
	@ApiModelProperty(value="银行")
	@ApiParam(value="银行")
	@Column(name = "trade_card_bank")
	private String tradeCardBank;

	/**
	 *交易所用银行卡卡号
	 */
	@ApiModelProperty(value="交易所用银行卡卡号")
	@ApiParam(value="交易所用银行卡卡号")
	@Column(name = "trade_card_code")
	private String tradeCardCode;

	/**
	 *创建时间
	 */
	@ApiModelProperty(value="创建时间")
	@ApiParam(value="创建时间")
	@Column(name = "create_time")
	private java.util.Date createTime;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setAmount(java.math.BigDecimal amount) {
		this.amount=amount;
	}

	public java.math.BigDecimal getAmount() {
		return amount;
	}

	public void setItem(String item) {
		this.item=item == null ? item : item.trim();
	}

	public String getItem() {
		return item;
	}

	public void setInfo(String info) {
		this.info=info == null ? info : info.trim();
	}

	public String getInfo() {
		return info;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType=tradeType;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo=tradeNo == null ? tradeNo : tradeNo.trim();
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeTicket(String tradeTicket) {
		this.tradeTicket=tradeTicket == null ? tradeTicket : tradeTicket.trim();
	}

	public String getTradeTicket() {
		return tradeTicket;
	}

	public void setTradeCardBank(String tradeCardBank) {
		this.tradeCardBank=tradeCardBank == null ? tradeCardBank : tradeCardBank.trim();
	}

	public String getTradeCardBank() {
		return tradeCardBank;
	}

	public void setTradeCardCode(String tradeCardCode) {
		this.tradeCardCode=tradeCardCode == null ? tradeCardCode : tradeCardCode.trim();
	}

	public String getTradeCardCode() {
		return tradeCardCode;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

}
