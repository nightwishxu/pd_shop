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
@Table(name="p_org_balance_log")
public class OrgBalanceLog implements Serializable {

	/**
	 *主键
	 */
	@ApiModelProperty(value="主键")
	@ApiParam(value="主键")
	@Column(name = "id")
	private Integer id;

	/**
	 *机构id
	 */
	@ApiModelProperty(value="机构id")
	@ApiParam(value="机构id")
	@Column(name = "org_id")
	private Integer orgId;

	/**
	 *金额
	 */
	@ApiModelProperty(value="金额")
	@ApiParam(value="金额")
	@Column(name = "money")
	private java.math.BigDecimal money;

	/**
	 *1支出2收入
	 */
	@ApiModelProperty(value="1支出2收入")
	@ApiParam(value="1支出2收入")
	@Column(name = "type")
	private Integer type;

	/**
	 *1用户典当  2用户续当 3用户赎当 4卖给平台 5典当平台服务费 6续当平台服务费
	 */
	@ApiModelProperty(value="1用户典当  2用户续当 3用户赎当 4卖给平台 5典当平台服务费 6续当平台服务费")
	@ApiParam(value="1用户典当  2用户续当 3用户赎当 4卖给平台 5典当平台服务费 6续当平台服务费")
	@Column(name = "item")
	private String item;

	/**
	 *描述
	 */
	@ApiModelProperty(value="描述")
	@ApiParam(value="描述")
	@Column(name = "info")
	private String info;

	/**
	 *当号
	 */
	@ApiModelProperty(value="当号")
	@ApiParam(value="当号")
	@Column(name = "pawn_code")
	private String pawnCode;

	/**
	 *相关id（典当表id或续当表id item=2,6时）
	 */
	@ApiModelProperty(value="相关id（典当表id或续当表id item=2,6时）")
	@ApiParam(value="相关id（典当表id或续当表id item=2,6时）")
	@Column(name = "fid")
	private Integer fid;

	/**
	 *支付流水表id
	 */
	@ApiModelProperty(value="支付流水表id")
	@ApiParam(value="支付流水表id")
	@Column(name = "pay_log_id")
	private String payLogId;

	/**
	 *0余额1支付宝2微信10线下银行卡
	 */
	@ApiModelProperty(value="0余额1支付宝2微信10线下银行卡")
	@ApiParam(value="0余额1支付宝2微信10线下银行卡")
	@Column(name = "trade_type")
	private Integer tradeType;

	/**
	 *支付宝微信流水号
	 */
	@ApiModelProperty(value="支付宝微信流水号")
	@ApiParam(value="支付宝微信流水号")
	@Column(name = "trade_no")
	private String tradeNo;

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
	 *线下打款凭证
	 */
	@ApiModelProperty(value="线下打款凭证")
	@ApiParam(value="线下打款凭证")
	@Column(name = "trade_ticket")
	private String tradeTicket;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *用户姓名
	 */
	@ApiModelProperty(value="用户姓名")
	@ApiParam(value="用户姓名")
	@Column(name = "user_name")
	private String userName;

	/**
	 *用户手机号
	 */
	@ApiModelProperty(value="用户手机号")
	@ApiParam(value="用户手机号")
	@Column(name = "user_phone")
	private String userPhone;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *开始日期
	 */
	@ApiModelProperty(value="开始日期")
	@ApiParam(value="开始日期")
	@Column(name = "begin_time")
	private java.util.Date beginTime;

	/**
	 *结束日期
	 */
	@ApiModelProperty(value="结束日期")
	@ApiParam(value="结束日期")
	@Column(name = "end_time")
	private java.util.Date endTime;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setOrgId(Integer orgId) {
		this.orgId=orgId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setMoney(java.math.BigDecimal money) {
		this.money=money;
	}

	public java.math.BigDecimal getMoney() {
		return money;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
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

	public void setPawnCode(String pawnCode) {
		this.pawnCode=pawnCode == null ? pawnCode : pawnCode.trim();
	}

	public String getPawnCode() {
		return pawnCode;
	}

	public void setFid(Integer fid) {
		this.fid=fid;
	}

	public Integer getFid() {
		return fid;
	}

	public void setPayLogId(String payLogId) {
		this.payLogId=payLogId == null ? payLogId : payLogId.trim();
	}

	public String getPayLogId() {
		return payLogId;
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

	public void setTradeTicket(String tradeTicket) {
		this.tradeTicket=tradeTicket == null ? tradeTicket : tradeTicket.trim();
	}

	public String getTradeTicket() {
		return tradeTicket;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserName(String userName) {
		this.userName=userName == null ? userName : userName.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone=userPhone == null ? userPhone : userPhone.trim();
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setBeginTime(java.util.Date beginTime) {
		this.beginTime=beginTime;
	}

	public java.util.Date getBeginTime() {
		return beginTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime=endTime;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

}
