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
@Table(name="p_user_bank_card")
public class UserBankCard implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
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
	 *银行图标
	 */
	@ApiModelProperty(value="银行图标")
	@ApiParam(value="银行图标")
	@Column(name = "bank_logo")
	private String bankLogo;

	/**
	 *银行卡号
	 */
	@ApiModelProperty(value="银行卡号")
	@ApiParam(value="银行卡号")
	@Column(name = "bank_card_no")
	private String bankCardNo;

	/**
	 *所属银行
	 */
	@ApiModelProperty(value="所属银行")
	@ApiParam(value="所属银行")
	@Column(name = "bank_card_name")
	private String bankCardName;

	/**
	 *姓名
	 */
	@ApiModelProperty(value="姓名")
	@ApiParam(value="姓名")
	@Column(name = "bank_card_user_name")
	private String bankCardUserName;

	/**
	 *预留手机号
	 */
	@ApiModelProperty(value="预留手机号")
	@ApiParam(value="预留手机号")
	@Column(name = "bank_card_phone")
	private String bankCardPhone;

	/**
	 *身份证号
	 */
	@ApiModelProperty(value="身份证号")
	@ApiParam(value="身份证号")
	@Column(name = "bank_card_id_card")
	private String bankCardIdCard;

	/**
	 *1储蓄卡2信用卡
	 */
	@ApiModelProperty(value="1储蓄卡2信用卡")
	@ApiParam(value="1储蓄卡2信用卡")
	@Column(name = "bank_card_type")
	private Integer bankCardType;

	/**
	 *1默认0否
	 */
	@ApiModelProperty(value="1默认0否")
	@ApiParam(value="1默认0否")
	@Column(name = "is_default")
	private Integer isDefault;

	/**
	 *创建时间
	 */
	@ApiModelProperty(value="创建时间")
	@ApiParam(value="创建时间")
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *银行编号
	 */
	@ApiModelProperty(value="银行编号")
	@ApiParam(value="银行编号")
	@Column(name = "bank_id")
	private String bankId;

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

	public void setBankLogo(String bankLogo) {
		this.bankLogo=bankLogo == null ? bankLogo : bankLogo.trim();
	}

	public String getBankLogo() {
		return bankLogo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo=bankCardNo == null ? bankCardNo : bankCardNo.trim();
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardName(String bankCardName) {
		this.bankCardName=bankCardName == null ? bankCardName : bankCardName.trim();
	}

	public String getBankCardName() {
		return bankCardName;
	}

	public void setBankCardUserName(String bankCardUserName) {
		this.bankCardUserName=bankCardUserName == null ? bankCardUserName : bankCardUserName.trim();
	}

	public String getBankCardUserName() {
		return bankCardUserName;
	}

	public void setBankCardPhone(String bankCardPhone) {
		this.bankCardPhone=bankCardPhone == null ? bankCardPhone : bankCardPhone.trim();
	}

	public String getBankCardPhone() {
		return bankCardPhone;
	}

	public void setBankCardIdCard(String bankCardIdCard) {
		this.bankCardIdCard=bankCardIdCard == null ? bankCardIdCard : bankCardIdCard.trim();
	}

	public String getBankCardIdCard() {
		return bankCardIdCard;
	}

	public void setBankCardType(Integer bankCardType) {
		this.bankCardType=bankCardType;
	}

	public Integer getBankCardType() {
		return bankCardType;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault=isDefault;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setBankId(String bankId) {
		this.bankId=bankId == null ? bankId : bankId.trim();
	}

	public String getBankId() {
		return bankId;
	}

}
