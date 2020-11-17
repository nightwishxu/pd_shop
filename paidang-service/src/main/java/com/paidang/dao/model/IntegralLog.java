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
@Table(name="t_integral_log")
public class IntegralLog implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "integral")
	private java.math.BigDecimal integral;

	/**
	 *0店铺积分
	 */
	@ApiModelProperty(value="0店铺积分")
	@ApiParam(value="0店铺积分")
	@Column(name = "type")
	private Integer type;

	/**
	 *订单号
	 */
	@ApiModelProperty(value="订单号")
	@ApiParam(value="订单号")
	@Column(name = "log_id")
	private String logId;

	/**
	 *用于活动等
	 */
	@ApiModelProperty(value="用于活动等")
	@ApiParam(value="用于活动等")
	@Column(name = "info")
	private String info;

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
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *账户类型： 0 个人用户  1 店铺
	 */
	@ApiModelProperty(value="账户类型： 0 个人用户  1 店铺")
	@ApiParam(value="账户类型： 0 个人用户  1 店铺")
	@Column(name = "account_type")
	private Integer accountType;

	/**
	 *金额（负数为支出。正数为收入）
	 */
	@ApiModelProperty(value="金额（负数为支出。正数为收入）")
	@ApiParam(value="金额（负数为支出。正数为收入）")
	@Column(name = "integral_new")
	private java.math.BigDecimal integralNew;

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

	public void setIntegral(java.math.BigDecimal integral) {
		this.integral=integral;
	}

	public java.math.BigDecimal getIntegral() {
		return integral;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setLogId(String logId) {
		this.logId=logId == null ? logId : logId.trim();
	}

	public String getLogId() {
		return logId;
	}

	public void setInfo(String info) {
		this.info=info == null ? info : info.trim();
	}

	public String getInfo() {
		return info;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount=createAccount == null ? createAccount : createAccount.trim();
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setAccountType(Integer accountType) {
		this.accountType=accountType;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setIntegralNew(java.math.BigDecimal integralNew) {
		this.integralNew=integralNew;
	}

	public java.math.BigDecimal getIntegralNew() {
		return integralNew;
	}

}
