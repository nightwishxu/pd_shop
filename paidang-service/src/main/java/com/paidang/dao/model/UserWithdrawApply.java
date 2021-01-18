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
@Table(name="p_user_withdraw_apply")
public class UserWithdrawApply implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *用户编号
	 */
	@ApiModelProperty(value="用户编号")
	@ApiParam(value="用户编号")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *金额
	 */
	@ApiModelProperty(value="金额")
	@ApiParam(value="金额")
	@Column(name = "amount")
	private java.math.BigDecimal amount;

	/**
	 *状态 0 待审核 1审核成功 2审核失败
	 */
	@ApiModelProperty(value="状态 0 待审核 1审核成功 2审核失败")
	@ApiParam(value="状态 0 待审核 1审核成功 2审核失败")
	@Column(name = "status")
	private Integer status;

	/**
	 *0余额1支付宝2微信10线下银行卡
	 */
	@ApiModelProperty(value="0余额1支付宝2微信10线下银行卡")
	@ApiParam(value="0余额1支付宝2微信10线下银行卡")
	@Column(name = "trade_type")
	private Integer tradeType;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "create_account")
	private String createAccount;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "modify_time")
	private java.util.Date modifyTime;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "modify_account")
	private String modifyAccount;

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

	public void setAmount(java.math.BigDecimal amount) {
		this.amount=amount;
	}

	public java.math.BigDecimal getAmount() {
		return amount;
	}

	public void setStatus(Integer status) {
		this.status=status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType=tradeType;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount=createAccount == null ? createAccount : createAccount.trim();
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime=modifyTime;
	}

	public java.util.Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyAccount(String modifyAccount) {
		this.modifyAccount=modifyAccount == null ? modifyAccount : modifyAccount.trim();
	}

	public String getModifyAccount() {
		return modifyAccount;
	}

}
