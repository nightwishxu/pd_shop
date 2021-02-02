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
@Table(name="p_user_pawn")
public class UserPawn implements Serializable {

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
	 *当品id
	 */
	@ApiModelProperty(value="当品id")
	@ApiParam(value="当品id")
	@Column(name = "goods_id")
	private Integer goodsId;

	/**
	 *贷款额度
	 */
	@ApiModelProperty(value="贷款额度")
	@ApiParam(value="贷款额度")
	@Column(name = "loans_price")
	private java.math.BigDecimal loansPrice;

	/**
	 *期望利率
	 */
	@ApiModelProperty(value="期望利率")
	@ApiParam(value="期望利率")
	@Column(name = "loans_rate")
	private java.math.BigDecimal loansRate;

	/**
	 *是否确认(0:未确认  1:确认) 
	 */
	@ApiModelProperty(value="是否确认(0:未确认  1:确认) ")
	@ApiParam(value="是否确认(0:未确认  1:确认) ")
	@Column(name = "is_verify")
	private Integer isVerify;

	/**
	 *确认的机构id
	 */
	@ApiModelProperty(value="确认的机构id")
	@ApiParam(value="确认的机构id")
	@Column(name = "org_id")
	private Integer orgId;

	/**
	 *出价人
	 */
	@ApiModelProperty(value="出价人")
	@ApiParam(value="出价人")
	@Column(name = "org_user_id")
	private Integer orgUserId;

	/**
	 *用户选择中标机构时间
	 */
	@ApiModelProperty(value="用户选择中标机构时间")
	@ApiParam(value="用户选择中标机构时间")
	@Column(name = "org_selected_time")
	private java.util.Date orgSelectedTime;

	/**
	 *收款人
	 */
	@ApiModelProperty(value="收款人")
	@ApiParam(value="收款人")
	@Column(name = "payee_name")
	private String payeeName;

	/**
	 *收款人电话
	 */
	@ApiModelProperty(value="收款人电话")
	@ApiParam(value="收款人电话")
	@Column(name = "payee_phone")
	private String payeePhone;

	/**
	 *收款人银行
	 */
	@ApiModelProperty(value="收款人银行")
	@ApiParam(value="收款人银行")
	@Column(name = "payee_bank_name")
	private String payeeBankName;

	/**
	 *收款人银行卡号
	 */
	@ApiModelProperty(value="收款人银行卡号")
	@ApiParam(value="收款人银行卡号")
	@Column(name = "payee_bank_card_code")
	private String payeeBankCardCode;

	/**
	 *机构打款凭证
	 */
	@ApiModelProperty(value="机构打款凭证")
	@ApiParam(value="机构打款凭证")
	@Column(name = "payee_ticket")
	private String payeeTicket;

	/**
	 *0无1已到账
	 */
	@ApiModelProperty(value="0无1已到账")
	@ApiParam(value="0无1已到账")
	@Column(name = "payee_state")
	private Integer payeeState;

	/**
	 *打款人
	 */
	@ApiModelProperty(value="打款人")
	@ApiParam(value="打款人")
	@Column(name = "pay_name")
	private String payName;

	/**
	 *打款人电话
	 */
	@ApiModelProperty(value="打款人电话")
	@ApiParam(value="打款人电话")
	@Column(name = "pay_phone")
	private String payPhone;

	/**
	 *打款人银行名称
	 */
	@ApiModelProperty(value="打款人银行名称")
	@ApiParam(value="打款人银行名称")
	@Column(name = "pay_bank_name")
	private String payBankName;

	/**
	 *打款人银行卡号
	 */
	@ApiModelProperty(value="打款人银行卡号")
	@ApiParam(value="打款人银行卡号")
	@Column(name = "pay_bacnk_card_code")
	private String payBacnkCardCode;

	/**
	 *当号
	 */
	@ApiModelProperty(value="当号")
	@ApiParam(value="当号")
	@Column(name = "pawn_ticket_code")
	private String pawnTicketCode;

	/**
	 *当票
	 */
	@ApiModelProperty(value="当票")
	@ApiParam(value="当票")
	@Column(name = "pawn_ticket")
	private String pawnTicket;

	/**
	 *-1已取消1竞拍中  2已典当 3赎当 4绝当 5宝祥兜底
	 */
	@ApiModelProperty(value="-1已取消1竞拍中  2已典当 3赎当 4绝当 5宝祥兜底")
	@ApiParam(value="-1已取消1竞拍中  2已典当 3赎当 4绝当 5宝祥兜底")
	@Column(name = "state")
	private Integer state;

	/**
	 *0.无1.用户主动放弃本次竞拍 2.用户自主选择中标机构 3.跟随系统 4.机构拒绝（或机构未能在中标后规定时间内支付当款）
	 */
	@ApiModelProperty(value="0.无1.用户主动放弃本次竞拍 2.用户自主选择中标机构 3.跟随系统 4.机构拒绝（或机构未能在中标后规定时间内支付当款）")
	@ApiParam(value="0.无1.用户主动放弃本次竞拍 2.用户自主选择中标机构 3.跟随系统 4.机构拒绝（或机构未能在中标后规定时间内支付当款）")
	@Column(name = "user_state")
	private Integer userState;

	/**
	 *典当开始
	 */
	@ApiModelProperty(value="典当开始")
	@ApiParam(value="典当开始")
	@Column(name = "pawn_begin_time")
	private java.util.Date pawnBeginTime;

	/**
	 *典当结束
	 */
	@ApiModelProperty(value="典当结束")
	@ApiParam(value="典当结束")
	@Column(name = "pawn_end_time")
	private java.util.Date pawnEndTime;

	/**
	 *初始当金
	 */
	@ApiModelProperty(value="初始当金")
	@ApiParam(value="初始当金")
	@Column(name = "begin_money")
	private java.math.BigDecimal beginMoney;

	/**
	 *初始当期
	 */
	@ApiModelProperty(value="初始当期")
	@ApiParam(value="初始当期")
	@Column(name = "begin_pawn_month")
	private Integer beginPawnMonth;

	/**
	 *初始典当结束时间
	 */
	@ApiModelProperty(value="初始典当结束时间")
	@ApiParam(value="初始典当结束时间")
	@Column(name = "begin_pawn_end_time")
	private java.util.Date beginPawnEndTime;

	/**
	 *当金
	 */
	@ApiModelProperty(value="当金")
	@ApiParam(value="当金")
	@Column(name = "money")
	private java.math.BigDecimal money;

	/**
	 *当期(半月)
	 */
	@ApiModelProperty(value="当期(半月)")
	@ApiParam(value="当期(半月)")
	@Column(name = "pawn_time")
	private Integer pawnTime;

	/**
	 *最新一期当金
	 */
	@ApiModelProperty(value="最新一期当金")
	@ApiParam(value="最新一期当金")
	@Column(name = "last_money")
	private java.math.BigDecimal lastMoney;

	/**
	 *最新一期当期
	 */
	@ApiModelProperty(value="最新一期当期")
	@ApiParam(value="最新一期当期")
	@Column(name = "last_pawn_month")
	private Integer lastPawnMonth;

	/**
	 *最新一期时间
	 */
	@ApiModelProperty(value="最新一期时间")
	@ApiParam(value="最新一期时间")
	@Column(name = "last_pawn_time")
	private java.util.Date lastPawnTime;

	/**
	 *综合利率(%) 最终中标的综合利率
	 */
	@ApiModelProperty(value="综合利率(%) 最终中标的综合利率")
	@ApiParam(value="综合利率(%) 最终中标的综合利率")
	@Column(name = "rate")
	private java.math.BigDecimal rate;

	/**
	 *利息利率(%) 最终中标的利率
	 */
	@ApiModelProperty(value="利息利率(%) 最终中标的利率")
	@ApiParam(value="利息利率(%) 最终中标的利率")
	@Column(name = "money_rate")
	private java.math.BigDecimal moneyRate;

	/**
	 *机构逾期利率（百分之）
	 */
	@ApiModelProperty(value="机构逾期利率（百分之）")
	@ApiParam(value="机构逾期利率（百分之）")
	@Column(name = "overdue_rate")
	private java.math.BigDecimal overdueRate;

	/**
	 *首期综合费
	 */
	@ApiModelProperty(value="首期综合费")
	@ApiParam(value="首期综合费")
	@Column(name = "pawn_money")
	private java.math.BigDecimal pawnMoney;

	/**
	 *用户实际得款
	 */
	@ApiModelProperty(value="用户实际得款")
	@ApiParam(value="用户实际得款")
	@Column(name = "user_money")
	private java.math.BigDecimal userMoney;

	/**
	 *机构服务费率(%)
	 */
	@ApiModelProperty(value="机构服务费率(%)")
	@ApiParam(value="机构服务费率(%)")
	@Column(name = "platform_rate")
	private java.math.BigDecimal platformRate;

	/**
	 *平台服务费
	 */
	@ApiModelProperty(value="平台服务费")
	@ApiParam(value="平台服务费")
	@Column(name = "platform_money")
	private java.math.BigDecimal platformMoney;

	/**
	 *0未缴纳1已缴纳平台服务费
	 */
	@ApiModelProperty(value="0未缴纳1已缴纳平台服务费")
	@ApiParam(value="0未缴纳1已缴纳平台服务费")
	@Column(name = "platform_state")
	private Integer platformState;

	/**
	 *0-默认值1申请赎当2用户已打款3机构确认收款
	 */
	@ApiModelProperty(value="0-默认值1申请赎当2用户已打款3机构确认收款")
	@ApiParam(value="0-默认值1申请赎当2用户已打款3机构确认收款")
	@Column(name = "redeem_state")
	private Integer redeemState;

	/**
	 *用户赎当打款凭证
	 */
	@ApiModelProperty(value="用户赎当打款凭证")
	@ApiParam(value="用户赎当打款凭证")
	@Column(name = "redeem_ticket")
	private String redeemTicket;

	/**
	 *用户赎当时间
	 */
	@ApiModelProperty(value="用户赎当时间")
	@ApiParam(value="用户赎当时间")
	@Column(name = "redeem_time")
	private java.util.Date redeemTime;

	/**
	 *赎当滞纳金
	 */
	@ApiModelProperty(value="赎当滞纳金")
	@ApiParam(value="赎当滞纳金")
	@Column(name = "redeem_overdue")
	private java.math.BigDecimal redeemOverdue;

	/**
	 *赎当应付利息
	 */
	@ApiModelProperty(value="赎当应付利息")
	@ApiParam(value="赎当应付利息")
	@Column(name = "redeem_interest")
	private java.math.BigDecimal redeemInterest;

	/**
	 *赎当当票
	 */
	@ApiModelProperty(value="赎当当票")
	@ApiParam(value="赎当当票")
	@Column(name = "redeem_pawn_ticket")
	private String redeemPawnTicket;

	/**
	 *赎当综合费
	 */
	@ApiModelProperty(value="赎当综合费")
	@ApiParam(value="赎当综合费")
	@Column(name = "redeem_money")
	private java.math.BigDecimal redeemMoney;

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
	 *典当行名称
	 */
	@ApiModelProperty(value="典当行名称")
	@ApiParam(value="典当行名称")
	@Column(name = "org_name")
	private String orgName;

	/**
	 *典当行电话
	 */
	@ApiModelProperty(value="典当行电话")
	@ApiParam(value="典当行电话")
	@Column(name = "org_tel")
	private String orgTel;

	/**
	 *典当行地址
	 */
	@ApiModelProperty(value="典当行地址")
	@ApiParam(value="典当行地址")
	@Column(name = "org_address")
	private String orgAddress;

	/**
	 *典当行许可证编号
	 */
	@ApiModelProperty(value="典当行许可证编号")
	@ApiParam(value="典当行许可证编号")
	@Column(name = "org_license")
	private String orgLicense;

	/**
	 *机构印章
	 */
	@ApiModelProperty(value="机构印章")
	@ApiParam(value="机构印章")
	@Column(name = "org_seal")
	private String orgSeal;

	/**
	 *用户名称
	 */
	@ApiModelProperty(value="用户名称")
	@ApiParam(value="用户名称")
	@Column(name = "user_name")
	private String userName;

	/**
	 *用户电话
	 */
	@ApiModelProperty(value="用户电话")
	@ApiParam(value="用户电话")
	@Column(name = "user_phone")
	private String userPhone;

	/**
	 *用户地址
	 */
	@ApiModelProperty(value="用户地址")
	@ApiParam(value="用户地址")
	@Column(name = "user_address")
	private String userAddress;

	/**
	 *用户身份证
	 */
	@ApiModelProperty(value="用户身份证")
	@ApiParam(value="用户身份证")
	@Column(name = "user_id_card")
	private String userIdCard;

	/**
	 *当品名称
	 */
	@ApiModelProperty(value="当品名称")
	@ApiParam(value="当品名称")
	@Column(name = "goods_name")
	private String goodsName;

	/**
	 *最新续当id
	 */
	@ApiModelProperty(value="最新续当id")
	@ApiParam(value="最新续当id")
	@Column(name = "last_pawn_continue_id")
	private Integer lastPawnContinueId;

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
	 * 0 无 1 待完善信息 2待签署 3 待上传凭证 4已完成
	 */
	@ApiModelProperty(value=" 0 无 1 待完善信息 2待签署 3 待上传凭证 4已完成")
	@ApiParam(value=" 0 无 1 待完善信息 2待签署 3 待上传凭证 4已完成")
	@Column(name = "pawn_status")
	private Integer pawnStatus;

	/**
	 *最新续当项目号
	 */
	@ApiModelProperty(value="最新续当项目号")
	@ApiParam(value="最新续当项目号")
	@Column(name = "last_continue_project_code")
	private String lastContinueProjectCode;

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

	public void setGoodsId(Integer goodsId) {
		this.goodsId=goodsId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setLoansPrice(java.math.BigDecimal loansPrice) {
		this.loansPrice=loansPrice;
	}

	public java.math.BigDecimal getLoansPrice() {
		return loansPrice;
	}

	public void setLoansRate(java.math.BigDecimal loansRate) {
		this.loansRate=loansRate;
	}

	public java.math.BigDecimal getLoansRate() {
		return loansRate;
	}

	public void setIsVerify(Integer isVerify) {
		this.isVerify=isVerify;
	}

	public Integer getIsVerify() {
		return isVerify;
	}

	public void setOrgId(Integer orgId) {
		this.orgId=orgId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgUserId(Integer orgUserId) {
		this.orgUserId=orgUserId;
	}

	public Integer getOrgUserId() {
		return orgUserId;
	}

	public void setOrgSelectedTime(java.util.Date orgSelectedTime) {
		this.orgSelectedTime=orgSelectedTime;
	}

	public java.util.Date getOrgSelectedTime() {
		return orgSelectedTime;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName=payeeName == null ? payeeName : payeeName.trim();
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeePhone(String payeePhone) {
		this.payeePhone=payeePhone == null ? payeePhone : payeePhone.trim();
	}

	public String getPayeePhone() {
		return payeePhone;
	}

	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName=payeeBankName == null ? payeeBankName : payeeBankName.trim();
	}

	public String getPayeeBankName() {
		return payeeBankName;
	}

	public void setPayeeBankCardCode(String payeeBankCardCode) {
		this.payeeBankCardCode=payeeBankCardCode == null ? payeeBankCardCode : payeeBankCardCode.trim();
	}

	public String getPayeeBankCardCode() {
		return payeeBankCardCode;
	}

	public void setPayeeTicket(String payeeTicket) {
		this.payeeTicket=payeeTicket == null ? payeeTicket : payeeTicket.trim();
	}

	public String getPayeeTicket() {
		return payeeTicket;
	}

	public void setPayeeState(Integer payeeState) {
		this.payeeState=payeeState;
	}

	public Integer getPayeeState() {
		return payeeState;
	}

	public void setPayName(String payName) {
		this.payName=payName == null ? payName : payName.trim();
	}

	public String getPayName() {
		return payName;
	}

	public void setPayPhone(String payPhone) {
		this.payPhone=payPhone == null ? payPhone : payPhone.trim();
	}

	public String getPayPhone() {
		return payPhone;
	}

	public void setPayBankName(String payBankName) {
		this.payBankName=payBankName == null ? payBankName : payBankName.trim();
	}

	public String getPayBankName() {
		return payBankName;
	}

	public void setPayBacnkCardCode(String payBacnkCardCode) {
		this.payBacnkCardCode=payBacnkCardCode == null ? payBacnkCardCode : payBacnkCardCode.trim();
	}

	public String getPayBacnkCardCode() {
		return payBacnkCardCode;
	}

	public void setPawnTicketCode(String pawnTicketCode) {
		this.pawnTicketCode=pawnTicketCode == null ? pawnTicketCode : pawnTicketCode.trim();
	}

	public String getPawnTicketCode() {
		return pawnTicketCode;
	}

	public void setPawnTicket(String pawnTicket) {
		this.pawnTicket=pawnTicket == null ? pawnTicket : pawnTicket.trim();
	}

	public String getPawnTicket() {
		return pawnTicket;
	}

	public void setState(Integer state) {
		this.state=state;
	}

	public Integer getState() {
		return state;
	}

	public void setUserState(Integer userState) {
		this.userState=userState;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setPawnBeginTime(java.util.Date pawnBeginTime) {
		this.pawnBeginTime=pawnBeginTime;
	}

	public java.util.Date getPawnBeginTime() {
		return pawnBeginTime;
	}

	public void setPawnEndTime(java.util.Date pawnEndTime) {
		this.pawnEndTime=pawnEndTime;
	}

	public java.util.Date getPawnEndTime() {
		return pawnEndTime;
	}

	public void setBeginMoney(java.math.BigDecimal beginMoney) {
		this.beginMoney=beginMoney;
	}

	public java.math.BigDecimal getBeginMoney() {
		return beginMoney;
	}

	public void setBeginPawnMonth(Integer beginPawnMonth) {
		this.beginPawnMonth=beginPawnMonth;
	}

	public Integer getBeginPawnMonth() {
		return beginPawnMonth;
	}

	public void setBeginPawnEndTime(java.util.Date beginPawnEndTime) {
		this.beginPawnEndTime=beginPawnEndTime;
	}

	public java.util.Date getBeginPawnEndTime() {
		return beginPawnEndTime;
	}

	public void setMoney(java.math.BigDecimal money) {
		this.money=money;
	}

	public java.math.BigDecimal getMoney() {
		return money;
	}

	public void setPawnTime(Integer pawnTime) {
		this.pawnTime=pawnTime;
	}

	public Integer getPawnTime() {
		return pawnTime;
	}

	public void setLastMoney(java.math.BigDecimal lastMoney) {
		this.lastMoney=lastMoney;
	}

	public java.math.BigDecimal getLastMoney() {
		return lastMoney;
	}

	public void setLastPawnMonth(Integer lastPawnMonth) {
		this.lastPawnMonth=lastPawnMonth;
	}

	public Integer getLastPawnMonth() {
		return lastPawnMonth;
	}

	public void setLastPawnTime(java.util.Date lastPawnTime) {
		this.lastPawnTime=lastPawnTime;
	}

	public java.util.Date getLastPawnTime() {
		return lastPawnTime;
	}

	public void setRate(java.math.BigDecimal rate) {
		this.rate=rate;
	}

	public java.math.BigDecimal getRate() {
		return rate;
	}

	public void setMoneyRate(java.math.BigDecimal moneyRate) {
		this.moneyRate=moneyRate;
	}

	public java.math.BigDecimal getMoneyRate() {
		return moneyRate;
	}

	public void setOverdueRate(java.math.BigDecimal overdueRate) {
		this.overdueRate=overdueRate;
	}

	public java.math.BigDecimal getOverdueRate() {
		return overdueRate;
	}

	public void setPawnMoney(java.math.BigDecimal pawnMoney) {
		this.pawnMoney=pawnMoney;
	}

	public java.math.BigDecimal getPawnMoney() {
		return pawnMoney;
	}

	public void setUserMoney(java.math.BigDecimal userMoney) {
		this.userMoney=userMoney;
	}

	public java.math.BigDecimal getUserMoney() {
		return userMoney;
	}

	public void setPlatformRate(java.math.BigDecimal platformRate) {
		this.platformRate=platformRate;
	}

	public java.math.BigDecimal getPlatformRate() {
		return platformRate;
	}

	public void setPlatformMoney(java.math.BigDecimal platformMoney) {
		this.platformMoney=platformMoney;
	}

	public java.math.BigDecimal getPlatformMoney() {
		return platformMoney;
	}

	public void setPlatformState(Integer platformState) {
		this.platformState=platformState;
	}

	public Integer getPlatformState() {
		return platformState;
	}

	public void setRedeemState(Integer redeemState) {
		this.redeemState=redeemState;
	}

	public Integer getRedeemState() {
		return redeemState;
	}

	public void setRedeemTicket(String redeemTicket) {
		this.redeemTicket=redeemTicket == null ? redeemTicket : redeemTicket.trim();
	}

	public String getRedeemTicket() {
		return redeemTicket;
	}

	public void setRedeemTime(java.util.Date redeemTime) {
		this.redeemTime=redeemTime;
	}

	public java.util.Date getRedeemTime() {
		return redeemTime;
	}

	public void setRedeemOverdue(java.math.BigDecimal redeemOverdue) {
		this.redeemOverdue=redeemOverdue;
	}

	public java.math.BigDecimal getRedeemOverdue() {
		return redeemOverdue;
	}

	public void setRedeemInterest(java.math.BigDecimal redeemInterest) {
		this.redeemInterest=redeemInterest;
	}

	public java.math.BigDecimal getRedeemInterest() {
		return redeemInterest;
	}

	public void setRedeemPawnTicket(String redeemPawnTicket) {
		this.redeemPawnTicket=redeemPawnTicket == null ? redeemPawnTicket : redeemPawnTicket.trim();
	}

	public String getRedeemPawnTicket() {
		return redeemPawnTicket;
	}

	public void setRedeemMoney(java.math.BigDecimal redeemMoney) {
		this.redeemMoney=redeemMoney;
	}

	public java.math.BigDecimal getRedeemMoney() {
		return redeemMoney;
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

	public void setOrgName(String orgName) {
		this.orgName=orgName == null ? orgName : orgName.trim();
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgTel(String orgTel) {
		this.orgTel=orgTel == null ? orgTel : orgTel.trim();
	}

	public String getOrgTel() {
		return orgTel;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress=orgAddress == null ? orgAddress : orgAddress.trim();
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgLicense(String orgLicense) {
		this.orgLicense=orgLicense == null ? orgLicense : orgLicense.trim();
	}

	public String getOrgLicense() {
		return orgLicense;
	}

	public void setOrgSeal(String orgSeal) {
		this.orgSeal=orgSeal == null ? orgSeal : orgSeal.trim();
	}

	public String getOrgSeal() {
		return orgSeal;
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

	public void setUserAddress(String userAddress) {
		this.userAddress=userAddress == null ? userAddress : userAddress.trim();
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserIdCard(String userIdCard) {
		this.userIdCard=userIdCard == null ? userIdCard : userIdCard.trim();
	}

	public String getUserIdCard() {
		return userIdCard;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName=goodsName == null ? goodsName : goodsName.trim();
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setLastPawnContinueId(Integer lastPawnContinueId) {
		this.lastPawnContinueId=lastPawnContinueId;
	}

	public Integer getLastPawnContinueId() {
		return lastPawnContinueId;
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

	public void setPawnStatus(Integer pawnStatus) {
		this.pawnStatus=pawnStatus;
	}

	public Integer getPawnStatus() {
		return pawnStatus;
	}

	public void setLastContinueProjectCode(String lastContinueProjectCode) {
		this.lastContinueProjectCode=lastContinueProjectCode == null ? lastContinueProjectCode : lastContinueProjectCode.trim();
	}

	public String getLastContinueProjectCode() {
		return lastContinueProjectCode;
	}

}
