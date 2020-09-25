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
@Table(name="p_business_user_info")
public class BusinessUserInfo implements Serializable {

	/**
	 *id
	 */
	@ApiModelProperty(value="id")
	@ApiParam(value="id")
	@Column(name = "id")
	private Integer id;

	/**
	 *用户ID
	 */
	@ApiModelProperty(value="用户ID")
	@ApiParam(value="用户ID")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *总资产
	 */
	@ApiModelProperty(value="总资产")
	@ApiParam(value="总资产")
	@Column(name = "total")
	private java.math.BigDecimal total;

	/**
	 *支付宝账号
	 */
	@ApiModelProperty(value="支付宝账号")
	@ApiParam(value="支付宝账号")
	@Column(name = "ali_account")
	private String aliAccount;

	/**
	 *微信账号
	 */
	@ApiModelProperty(value="微信账号")
	@ApiParam(value="微信账号")
	@Column(name = "wx_account")
	private String wxAccount;

	/**
	 *支付密码
	 */
	@ApiModelProperty(value="支付密码")
	@ApiParam(value="支付密码")
	@Column(name = "pay_password")
	private String payPassword;

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

	public void setTotal(java.math.BigDecimal total) {
		this.total=total;
	}

	public java.math.BigDecimal getTotal() {
		return total;
	}

	public void setAliAccount(String aliAccount) {
		this.aliAccount=aliAccount == null ? aliAccount : aliAccount.trim();
	}

	public String getAliAccount() {
		return aliAccount;
	}

	public void setWxAccount(String wxAccount) {
		this.wxAccount=wxAccount == null ? wxAccount : wxAccount.trim();
	}

	public String getWxAccount() {
		return wxAccount;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword=payPassword == null ? payPassword : payPassword.trim();
	}

	public String getPayPassword() {
		return payPassword;
	}

}
