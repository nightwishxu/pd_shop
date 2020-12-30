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
@Table(name="p_pawn_continue")
public class PawnContinue implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *典当id
	 */
	@ApiModelProperty(value="典当id")
	@ApiParam(value="典当id")
	@Column(name = "pawn_id")
	private Integer pawnId;

	/**
	 *当号
	 */
	@ApiModelProperty(value="当号")
	@ApiParam(value="当号")
	@Column(name = "pawn_ticket_code")
	private String pawnTicketCode;

	/**
	 *上期截至日期
	 */
	@ApiModelProperty(value="上期截至日期")
	@ApiParam(value="上期截至日期")
	@Column(name = "pawn_last_end_time")
	private java.util.Date pawnLastEndTime;

	/**
	 *续当至日期
	 */
	@ApiModelProperty(value="续当至日期")
	@ApiParam(value="续当至日期")
	@Column(name = "pawn_end_time")
	private java.util.Date pawnEndTime;

	/**
	 *续当月份(半月)
	 */
	@ApiModelProperty(value="续当月份(半月)")
	@ApiParam(value="续当月份(半月)")
	@Column(name = "pawn_month")
	private Integer pawnMonth;

	/**
	 *本期综合费
	 */
	@ApiModelProperty(value="本期综合费")
	@ApiParam(value="本期综合费")
	@Column(name = "pawn_money")
	private java.math.BigDecimal pawnMoney;

	/**
	 *上期利息
	 */
	@ApiModelProperty(value="上期利息")
	@ApiParam(value="上期利息")
	@Column(name = "pawn_interest")
	private java.math.BigDecimal pawnInterest;

	/**
	 *滞纳金，默认0
	 */
	@ApiModelProperty(value="滞纳金，默认0")
	@ApiParam(value="滞纳金，默认0")
	@Column(name = "pawn_overdue")
	private java.math.BigDecimal pawnOverdue;

	/**
	 *平台服务费
	 */
	@ApiModelProperty(value="平台服务费")
	@ApiParam(value="平台服务费")
	@Column(name = "platform_money")
	private java.math.BigDecimal platformMoney;

	/**
	 *-1机构拒绝1待签署 2签署合同完成待上传打款凭证 3用户打款并上传凭证4机构确认5机构未收到
	 */
	@ApiModelProperty(value="-1机构拒绝1待签署 2签署合同完成待上传打款凭证 3用户打款并上传凭证4机构确认5机构未收到")
	@ApiParam(value="-1机构拒绝1待签署 2签署合同完成待上传打款凭证 3用户打款并上传凭证4机构确认5机构未收到")
	@Column(name = "state")
	private Integer state;

	/**
	 *0未缴纳1已缴纳平台服务费
	 */
	@ApiModelProperty(value="0未缴纳1已缴纳平台服务费")
	@ApiParam(value="0未缴纳1已缴纳平台服务费")
	@Column(name = "platform_state")
	private Integer platformState;

	/**
	 *打款凭证
	 */
	@ApiModelProperty(value="打款凭证")
	@ApiParam(value="打款凭证")
	@Column(name = "pay_ticket")
	private String payTicket;

	/**
	 *当票
	 */
	@ApiModelProperty(value="当票")
	@ApiParam(value="当票")
	@Column(name = "pawn_ticket")
	private String pawnTicket;

	/**
	 *上传凭证时间
	 */
	@ApiModelProperty(value="上传凭证时间")
	@ApiParam(value="上传凭证时间")
	@Column(name = "pay_time")
	private java.util.Date payTime;

	/**
	 *用户姓名
	 */
	@ApiModelProperty(value="用户姓名")
	@ApiParam(value="用户姓名")
	@Column(name = "user_name")
	private String userName;

	/**
	 *用户银行
	 */
	@ApiModelProperty(value="用户银行")
	@ApiParam(value="用户银行")
	@Column(name = "user_bank")
	private String userBank;

	/**
	 *用户银行卡号
	 */
	@ApiModelProperty(value="用户银行卡号")
	@ApiParam(value="用户银行卡号")
	@Column(name = "user_bank_card")
	private String userBankCard;

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
	@Column(name = "modify_time")
	private java.util.Date modifyTime;

	/**
	 *合同id
	 */
	@ApiModelProperty(value="合同id")
	@ApiParam(value="合同id")
	@Column(name = "contract_id")
	private String contractId;

	/**
	 *项目编号
	 */
	@ApiModelProperty(value="项目编号")
	@ApiParam(value="项目编号")
	@Column(name = "project_code")
	private String projectCode;

	/**
	 *用户 0未生成合同1待签署2已签署3拒签4作废
	 */
	@ApiModelProperty(value="用户 0未生成合同1待签署2已签署3拒签4作废")
	@ApiParam(value="用户 0未生成合同1待签署2已签署3拒签4作废")
	@Column(name = "user_status")
	private Integer userStatus;

	/**
	 *机构 0未生成合同1待签署2已签署3拒签4作废
	 */
	@ApiModelProperty(value="机构 0未生成合同1待签署2已签署3拒签4作废")
	@ApiParam(value="机构 0未生成合同1待签署2已签署3拒签4作废")
	@Column(name = "org_status")
	private Integer orgStatus;

	/**
	 *续票当号
	 */
	@ApiModelProperty(value="续票当号")
	@ApiParam(value="续票当号")
	@Column(name = "continue_pawn_ticket_code")
	private String continuePawnTicketCode;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setPawnId(Integer pawnId) {
		this.pawnId=pawnId;
	}

	public Integer getPawnId() {
		return pawnId;
	}

	public void setPawnTicketCode(String pawnTicketCode) {
		this.pawnTicketCode=pawnTicketCode == null ? pawnTicketCode : pawnTicketCode.trim();
	}

	public String getPawnTicketCode() {
		return pawnTicketCode;
	}

	public void setPawnLastEndTime(java.util.Date pawnLastEndTime) {
		this.pawnLastEndTime=pawnLastEndTime;
	}

	public java.util.Date getPawnLastEndTime() {
		return pawnLastEndTime;
	}

	public void setPawnEndTime(java.util.Date pawnEndTime) {
		this.pawnEndTime=pawnEndTime;
	}

	public java.util.Date getPawnEndTime() {
		return pawnEndTime;
	}

	public void setPawnMonth(Integer pawnMonth) {
		this.pawnMonth=pawnMonth;
	}

	public Integer getPawnMonth() {
		return pawnMonth;
	}

	public void setPawnMoney(java.math.BigDecimal pawnMoney) {
		this.pawnMoney=pawnMoney;
	}

	public java.math.BigDecimal getPawnMoney() {
		return pawnMoney;
	}

	public void setPawnInterest(java.math.BigDecimal pawnInterest) {
		this.pawnInterest=pawnInterest;
	}

	public java.math.BigDecimal getPawnInterest() {
		return pawnInterest;
	}

	public void setPawnOverdue(java.math.BigDecimal pawnOverdue) {
		this.pawnOverdue=pawnOverdue;
	}

	public java.math.BigDecimal getPawnOverdue() {
		return pawnOverdue;
	}

	public void setPlatformMoney(java.math.BigDecimal platformMoney) {
		this.platformMoney=platformMoney;
	}

	public java.math.BigDecimal getPlatformMoney() {
		return platformMoney;
	}

	public void setState(Integer state) {
		this.state=state;
	}

	public Integer getState() {
		return state;
	}

	public void setPlatformState(Integer platformState) {
		this.platformState=platformState;
	}

	public Integer getPlatformState() {
		return platformState;
	}

	public void setPayTicket(String payTicket) {
		this.payTicket=payTicket == null ? payTicket : payTicket.trim();
	}

	public String getPayTicket() {
		return payTicket;
	}

	public void setPawnTicket(String pawnTicket) {
		this.pawnTicket=pawnTicket == null ? pawnTicket : pawnTicket.trim();
	}

	public String getPawnTicket() {
		return pawnTicket;
	}

	public void setPayTime(java.util.Date payTime) {
		this.payTime=payTime;
	}

	public java.util.Date getPayTime() {
		return payTime;
	}

	public void setUserName(String userName) {
		this.userName=userName == null ? userName : userName.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserBank(String userBank) {
		this.userBank=userBank == null ? userBank : userBank.trim();
	}

	public String getUserBank() {
		return userBank;
	}

	public void setUserBankCard(String userBankCard) {
		this.userBankCard=userBankCard == null ? userBankCard : userBankCard.trim();
	}

	public String getUserBankCard() {
		return userBankCard;
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

	public void setContractId(String contractId) {
		this.contractId=contractId == null ? contractId : contractId.trim();
	}

	public String getContractId() {
		return contractId;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode=projectCode == null ? projectCode : projectCode.trim();
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus=userStatus;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setOrgStatus(Integer orgStatus) {
		this.orgStatus=orgStatus;
	}

	public Integer getOrgStatus() {
		return orgStatus;
	}

	public void setContinuePawnTicketCode(String continuePawnTicketCode) {
		this.continuePawnTicketCode=continuePawnTicketCode == null ? continuePawnTicketCode : continuePawnTicketCode.trim();
	}

	public String getContinuePawnTicketCode() {
		return continuePawnTicketCode;
	}

}
