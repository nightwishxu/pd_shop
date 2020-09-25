package com.paidang.dao.model;

import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.Column;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
/**
 *
 */
@ApiModel
@Table(name="p_pawn_ticket")
public class PawnTicket implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *当票号
	 */
	@ApiModelProperty(value="当票号")
	@ApiParam(value="当票号")
	@Column(name = "pawn_ticket_code")
	private String pawnTicketCode;

	/**
	 *机构名称
	 */
	@ApiModelProperty(value="机构名称")
	@ApiParam(value="机构名称")
	@Column(name = "org_name")
	private String orgName;

	/**
	 *机构电话
	 */
	@ApiModelProperty(value="机构电话")
	@ApiParam(value="机构电话")
	@Column(name = "org_tel")
	private String orgTel;

	/**
	 *机构地址
	 */
	@ApiModelProperty(value="机构地址")
	@ApiParam(value="机构地址")
	@Column(name = "org_adress")
	private String orgAdress;

	/**
	 *机构证书号
	 */
	@ApiModelProperty(value="机构证书号")
	@ApiParam(value="机构证书号")
	@Column(name = "business_license")
	private String businessLicense;

	/**
	 *当户名称
	 */
	@ApiModelProperty(value="当户名称")
	@ApiParam(value="当户名称")
	@Column(name = "pawner_name")
	private String pawnerName;

	/**
	 *联系电话
	 */
	@ApiModelProperty(value="联系电话")
	@ApiParam(value="联系电话")
	@Column(name = "pawner_tel")
	private String pawnerTel;

	/**
	 *联系人银行卡号
	 */
	@ApiModelProperty(value="联系人银行卡号")
	@ApiParam(value="联系人银行卡号")
	@Column(name = "pawner_id_card")
	private String pawnerIdCard;

	/**
	 *证件类型（身份证）
	 */
	@ApiModelProperty(value="证件类型（身份证）")
	@ApiParam(value="证件类型（身份证）")
	@Column(name = "pawner_cert")
	private String pawnerCert;

	/**
	 *联系人
	 */
	@ApiModelProperty(value="联系人")
	@ApiParam(value="联系人")
	@Column(name = "contactor")
	private String contactor;

	/**
	 *地址
	 */
	@ApiModelProperty(value="地址")
	@ApiParam(value="地址")
	@Column(name = "pawner_addr")
	private String pawnerAddr;

	/**
	 *商品名称
	 */
	@ApiModelProperty(value="商品名称")
	@ApiParam(value="商品名称")
	@Column(name = "goods_name")
	private String goodsName;

	/**
	 *估价
	 */
	@ApiModelProperty(value="估价")
	@ApiParam(value="估价")
	@Column(name = "auth_price_test")
	private String authPriceTest;

	/**
	 *鉴定价
	 */
	@ApiModelProperty(value="鉴定价")
	@ApiParam(value="鉴定价")
	@Column(name = "auth_price")
	private java.math.BigDecimal authPrice;

	/**
	 *折当率
	 */
	@ApiModelProperty(value="折当率")
	@ApiParam(value="折当率")
	@Column(name = "equivalent_ratio")
	private String equivalentRatio;

	/**
	 *合计
	 */
	@ApiModelProperty(value="合计")
	@ApiParam(value="合计")
	@Column(name = "loan_money")
	private String loanMoney;

	/**
	 *实付金额（小写）
	 */
	@ApiModelProperty(value="实付金额（小写）")
	@ApiParam(value="实付金额（小写）")
	@Column(name = "user_money")
	private String userMoney;

	/**
	 *综合费用（小写）
	 */
	@ApiModelProperty(value="综合费用（小写）")
	@ApiParam(value="综合费用（小写）")
	@Column(name = "pawn_money")
	private String pawnMoney;

	/**
	 *典当金额（大写）
	 */
	@ApiModelProperty(value="典当金额（大写）")
	@ApiParam(value="典当金额（大写）")
	@Column(name = "loan_money_c_n")
	private String loanMoneyCN;

	/**
	 *实付金额（大写）
	 */
	@ApiModelProperty(value="实付金额（大写）")
	@ApiParam(value="实付金额（大写）")
	@Column(name = "user_money_c_n")
	private String userMoneyCN;

	/**
	 *综合费用（大写）
	 */
	@ApiModelProperty(value="综合费用（大写）")
	@ApiParam(value="综合费用（大写）")
	@Column(name = "pawn_money_c_n")
	private String pawnMoneyCN;

	/**
	 *典当开始时间
	 */
	@ApiModelProperty(value="典当开始时间")
	@ApiParam(value="典当开始时间")
	@Column(name = "pawn_begin_time")
	private String pawnBeginTime;

	/**
	 *典当结束时间
	 */
	@ApiModelProperty(value="典当结束时间")
	@ApiParam(value="典当结束时间")
	@Column(name = "pawn_end_time")
	private String pawnEndTime;

	/**
	 *首期典当结束时间
	 */
	@ApiModelProperty(value="首期典当结束时间")
	@ApiParam(value="首期典当结束时间")
	@Column(name = "begin_pawn_end_time")
	private String beginPawnEndTime;

	/**
	 *月费率
	 */
	@ApiModelProperty(value="月费率")
	@ApiParam(value="月费率")
	@Column(name = "rate")
	private String rate;

	/**
	 *月利率
	 */
	@ApiModelProperty(value="月利率")
	@ApiParam(value="月利率")
	@Column(name = "money_rate")
	private String moneyRate;

	/**
	 *续当综合费用（小写）
	 */
	@ApiModelProperty(value="续当综合费用（小写）")
	@ApiParam(value="续当综合费用（小写）")
	@Column(name = "cost")
	private String cost;

	/**
	 *当户应付上期利息（小写）
	 */
	@ApiModelProperty(value="当户应付上期利息（小写）")
	@ApiParam(value="当户应付上期利息（小写）")
	@Column(name = "money_cost")
	private String moneyCost;

	/**
	 *当户总计交付金额（小写）
	 */
	@ApiModelProperty(value="当户总计交付金额（小写）")
	@ApiParam(value="当户总计交付金额（小写）")
	@Column(name = "repawn_total")
	private String repawnTotal;

	/**
	 *续当综合费用（大写）
	 */
	@ApiModelProperty(value="续当综合费用（大写）")
	@ApiParam(value="续当综合费用（大写）")
	@Column(name = "cost_c_n")
	private String costCN;

	/**
	 *当户总计交付金额（大写）
	 */
	@ApiModelProperty(value="当户总计交付金额（大写）")
	@ApiParam(value="当户总计交付金额（大写）")
	@Column(name = "repawn_total_c_n")
	private String repawnTotalCN;

	/**
	 *当户应付上期利息（大写）
	 */
	@ApiModelProperty(value="当户应付上期利息（大写）")
	@ApiParam(value="当户应付上期利息（大写）")
	@Column(name = "money_cost_c_n")
	private String moneyCostCN;

	/**
	 *续当开始时间
	 */
	@ApiModelProperty(value="续当开始时间")
	@ApiParam(value="续当开始时间")
	@Column(name = "repawn_begin_time")
	private String repawnBeginTime;

	/**
	 *续当结束时间
	 */
	@ApiModelProperty(value="续当结束时间")
	@ApiParam(value="续当结束时间")
	@Column(name = "repawn_end_time")
	private String repawnEndTime;

	/**
	 *复核
	 */
	@ApiModelProperty(value="复核")
	@ApiParam(value="复核")
	@Column(name = "checker")
	private String checker;

	/**
	 *经办
	 */
	@ApiModelProperty(value="经办")
	@ApiParam(value="经办")
	@Column(name = "handler")
	private String handler;

	/**
	 *用户id
	 */
	@ApiModelProperty(value="用户id")
	@ApiParam(value="用户id")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *备注
	 */
	@ApiModelProperty(value="备注")
	@ApiParam(value="备注")
	@Column(name = "remark")
	private String remark;

	/**
	 *其他约定 1无2有
	 */
	@ApiModelProperty(value="其他约定 1无2有")
	@ApiParam(value="其他约定 1无2有")
	@Column(name = "other_order")
	private String otherOrder;

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
	 *1典当2续当
	 */
	@ApiModelProperty(value="1典当2续当")
	@ApiParam(value="1典当2续当")
	@Column(name = "type")
	private String type;

	/**
	 *合同文档Id
	 */
	@ApiModelProperty(value="合同文档Id")
	@ApiParam(value="合同文档Id")
	@Column(name = "contract_id")
	private String contractId;

	/**
	 *0未生成合同1待签署2已签署3拒签4作废
	 */
	@ApiModelProperty(value="0未生成合同1待签署2已签署3拒签4作废")
	@ApiParam(value="0未生成合同1待签署2已签署3拒签4作废")
	@Column(name = "status")
	private String status;

	/**
	 *签署时间
	 */
	@ApiModelProperty(value="签署时间")
	@ApiParam(value="签署时间")
	@Column(name = "sign_time")
	private java.util.Date signTime;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setPawnTicketCode(String pawnTicketCode) {
		this.pawnTicketCode=pawnTicketCode == null ? pawnTicketCode : pawnTicketCode.trim();
	}

	public String getPawnTicketCode() {
		return pawnTicketCode;
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

	public void setOrgAdress(String orgAdress) {
		this.orgAdress=orgAdress == null ? orgAdress : orgAdress.trim();
	}

	public String getOrgAdress() {
		return orgAdress;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense=businessLicense == null ? businessLicense : businessLicense.trim();
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setPawnerName(String pawnerName) {
		this.pawnerName=pawnerName == null ? pawnerName : pawnerName.trim();
	}

	public String getPawnerName() {
		return pawnerName;
	}

	public void setPawnerTel(String pawnerTel) {
		this.pawnerTel=pawnerTel == null ? pawnerTel : pawnerTel.trim();
	}

	public String getPawnerTel() {
		return pawnerTel;
	}

	public void setPawnerIdCard(String pawnerIdCard) {
		this.pawnerIdCard=pawnerIdCard == null ? pawnerIdCard : pawnerIdCard.trim();
	}

	public String getPawnerIdCard() {
		return pawnerIdCard;
	}

	public void setPawnerCert(String pawnerCert) {
		this.pawnerCert=pawnerCert == null ? pawnerCert : pawnerCert.trim();
	}

	public String getPawnerCert() {
		return pawnerCert;
	}

	public void setContactor(String contactor) {
		this.contactor=contactor == null ? contactor : contactor.trim();
	}

	public String getContactor() {
		return contactor;
	}

	public void setPawnerAddr(String pawnerAddr) {
		this.pawnerAddr=pawnerAddr == null ? pawnerAddr : pawnerAddr.trim();
	}

	public String getPawnerAddr() {
		return pawnerAddr;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName=goodsName == null ? goodsName : goodsName.trim();
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setAuthPriceTest(String authPriceTest) {
		this.authPriceTest=authPriceTest == null ? authPriceTest : authPriceTest.trim();
	}

	public String getAuthPriceTest() {
		return authPriceTest;
	}

	public void setAuthPrice(java.math.BigDecimal authPrice) {
		this.authPrice=authPrice;
	}

	public java.math.BigDecimal getAuthPrice() {
		return authPrice;
	}

	public void setEquivalentRatio(String equivalentRatio) {
		this.equivalentRatio=equivalentRatio == null ? equivalentRatio : equivalentRatio.trim();
	}

	public String getEquivalentRatio() {
		return equivalentRatio;
	}

	public void setLoanMoney(String loanMoney) {
		this.loanMoney=loanMoney == null ? loanMoney : loanMoney.trim();
	}

	public String getLoanMoney() {
		return loanMoney;
	}

	public void setUserMoney(String userMoney) {
		this.userMoney=userMoney == null ? userMoney : userMoney.trim();
	}

	public String getUserMoney() {
		return userMoney;
	}

	public void setPawnMoney(String pawnMoney) {
		this.pawnMoney=pawnMoney == null ? pawnMoney : pawnMoney.trim();
	}

	public String getPawnMoney() {
		return pawnMoney;
	}

	public void setLoanMoneyCN(String loanMoneyCN) {
		this.loanMoneyCN=loanMoneyCN == null ? loanMoneyCN : loanMoneyCN.trim();
	}

	public String getLoanMoneyCN() {
		return loanMoneyCN;
	}

	public void setUserMoneyCN(String userMoneyCN) {
		this.userMoneyCN=userMoneyCN == null ? userMoneyCN : userMoneyCN.trim();
	}

	public String getUserMoneyCN() {
		return userMoneyCN;
	}

	public void setPawnMoneyCN(String pawnMoneyCN) {
		this.pawnMoneyCN=pawnMoneyCN == null ? pawnMoneyCN : pawnMoneyCN.trim();
	}

	public String getPawnMoneyCN() {
		return pawnMoneyCN;
	}

	public void setPawnBeginTime(String pawnBeginTime) {
		this.pawnBeginTime=pawnBeginTime == null ? pawnBeginTime : pawnBeginTime.trim();
	}

	public String getPawnBeginTime() {
		return pawnBeginTime;
	}

	public void setPawnEndTime(String pawnEndTime) {
		this.pawnEndTime=pawnEndTime == null ? pawnEndTime : pawnEndTime.trim();
	}

	public String getPawnEndTime() {
		return pawnEndTime;
	}

	public void setBeginPawnEndTime(String beginPawnEndTime) {
		this.beginPawnEndTime=beginPawnEndTime == null ? beginPawnEndTime : beginPawnEndTime.trim();
	}

	public String getBeginPawnEndTime() {
		return beginPawnEndTime;
	}

	public void setRate(String rate) {
		this.rate=rate == null ? rate : rate.trim();
	}

	public String getRate() {
		return rate;
	}

	public void setMoneyRate(String moneyRate) {
		this.moneyRate=moneyRate == null ? moneyRate : moneyRate.trim();
	}

	public String getMoneyRate() {
		return moneyRate;
	}

	public void setCost(String cost) {
		this.cost=cost == null ? cost : cost.trim();
	}

	public String getCost() {
		return cost;
	}

	public void setMoneyCost(String moneyCost) {
		this.moneyCost=moneyCost == null ? moneyCost : moneyCost.trim();
	}

	public String getMoneyCost() {
		return moneyCost;
	}

	public void setRepawnTotal(String repawnTotal) {
		this.repawnTotal=repawnTotal == null ? repawnTotal : repawnTotal.trim();
	}

	public String getRepawnTotal() {
		return repawnTotal;
	}

	public void setCostCN(String costCN) {
		this.costCN=costCN == null ? costCN : costCN.trim();
	}

	public String getCostCN() {
		return costCN;
	}

	public void setRepawnTotalCN(String repawnTotalCN) {
		this.repawnTotalCN=repawnTotalCN == null ? repawnTotalCN : repawnTotalCN.trim();
	}

	public String getRepawnTotalCN() {
		return repawnTotalCN;
	}

	public void setMoneyCostCN(String moneyCostCN) {
		this.moneyCostCN=moneyCostCN == null ? moneyCostCN : moneyCostCN.trim();
	}

	public String getMoneyCostCN() {
		return moneyCostCN;
	}

	public void setRepawnBeginTime(String repawnBeginTime) {
		this.repawnBeginTime=repawnBeginTime == null ? repawnBeginTime : repawnBeginTime.trim();
	}

	public String getRepawnBeginTime() {
		return repawnBeginTime;
	}

	public void setRepawnEndTime(String repawnEndTime) {
		this.repawnEndTime=repawnEndTime == null ? repawnEndTime : repawnEndTime.trim();
	}

	public String getRepawnEndTime() {
		return repawnEndTime;
	}

	public void setChecker(String checker) {
		this.checker=checker == null ? checker : checker.trim();
	}

	public String getChecker() {
		return checker;
	}

	public void setHandler(String handler) {
		this.handler=handler == null ? handler : handler.trim();
	}

	public String getHandler() {
		return handler;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setRemark(String remark) {
		this.remark=remark == null ? remark : remark.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setOtherOrder(String otherOrder) {
		this.otherOrder=otherOrder == null ? otherOrder : otherOrder.trim();
	}

	public String getOtherOrder() {
		return otherOrder;
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

	public void setType(String type) {
		this.type=type == null ? type : type.trim();
	}

	public String getType() {
		return type;
	}

	public void setContractId(String contractId) {
		this.contractId=contractId == null ? contractId : contractId.trim();
	}

	public String getContractId() {
		return contractId;
	}

	public void setStatus(String status) {
		this.status=status == null ? status : status.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setSignTime(java.util.Date signTime) {
		this.signTime=signTime;
	}

	public java.util.Date getSignTime() {
		return signTime;
	}

}
