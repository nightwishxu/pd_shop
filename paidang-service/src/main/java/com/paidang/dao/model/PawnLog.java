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
@Table(name="p_pawn_log")
public class PawnLog implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *典当id等
	 */
	@ApiModelProperty(value="典当id等")
	@ApiParam(value="典当id等")
	@Column(name = "ref_id")
	private Integer refId;

	/**
	 *藏品id
	 */
	@ApiModelProperty(value="藏品id")
	@ApiParam(value="藏品id")
	@Column(name = "goods_id")
	private Integer goodsId;

	/**
	 *用户
	 */
	@ApiModelProperty(value="用户")
	@ApiParam(value="用户")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *机构id
	 */
	@ApiModelProperty(value="机构id")
	@ApiParam(value="机构id")
	@Column(name = "org_id")
	private Integer orgId;

	/**
	 *藏品名称
	 */
	@ApiModelProperty(value="藏品名称")
	@ApiParam(value="藏品名称")
	@Column(name = "goods_name")
	private String goodsName;

	/**
	 *鉴定价格
	 */
	@ApiModelProperty(value="鉴定价格")
	@ApiParam(value="鉴定价格")
	@Column(name = "auth_price")
	private java.math.BigDecimal authPrice;

	/**
	 *藏品类别
	 */
	@ApiModelProperty(value="藏品类别")
	@ApiParam(value="藏品类别")
	@Column(name = "goods_cate")
	private String goodsCate;

	/**
	 *藏品类别id
	 */
	@ApiModelProperty(value="藏品类别id")
	@ApiParam(value="藏品类别id")
	@Column(name = "goods_cate_id")
	private Integer goodsCateId;

	/**
	 *机构名称
	 */
	@ApiModelProperty(value="机构名称")
	@ApiParam(value="机构名称")
	@Column(name = "org_name")
	private String orgName;

	/**
	 *金额
	 */
	@ApiModelProperty(value="金额")
	@ApiParam(value="金额")
	@Column(name = "money")
	private java.math.BigDecimal money;

	/**
	 *时间
	 */
	@ApiModelProperty(value="时间")
	@ApiParam(value="时间")
	@Column(name = "pawn_month")
	private Integer pawnMonth;

	/**
	 *0鉴定真品1典当2续当3赎当4绝当5交易6卖给平台
	 */
	@ApiModelProperty(value="0鉴定真品1典当2续当3赎当4绝当5交易6卖给平台")
	@ApiParam(value="0鉴定真品1典当2续当3赎当4绝当5交易6卖给平台")
	@Column(name = "type")
	private Integer type;

	/**
	 *用户姓名
	 */
	@ApiModelProperty(value="用户姓名")
	@ApiParam(value="用户姓名")
	@Column(name = "user_name")
	private String userName;

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

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setRefId(Integer refId) {
		this.refId=refId;
	}

	public Integer getRefId() {
		return refId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId=goodsId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId=orgId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName=goodsName == null ? goodsName : goodsName.trim();
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setAuthPrice(java.math.BigDecimal authPrice) {
		this.authPrice=authPrice;
	}

	public java.math.BigDecimal getAuthPrice() {
		return authPrice;
	}

	public void setGoodsCate(String goodsCate) {
		this.goodsCate=goodsCate == null ? goodsCate : goodsCate.trim();
	}

	public String getGoodsCate() {
		return goodsCate;
	}

	public void setGoodsCateId(Integer goodsCateId) {
		this.goodsCateId=goodsCateId;
	}

	public Integer getGoodsCateId() {
		return goodsCateId;
	}

	public void setOrgName(String orgName) {
		this.orgName=orgName == null ? orgName : orgName.trim();
	}

	public String getOrgName() {
		return orgName;
	}

	public void setMoney(java.math.BigDecimal money) {
		this.money=money;
	}

	public java.math.BigDecimal getMoney() {
		return money;
	}

	public void setPawnMonth(Integer pawnMonth) {
		this.pawnMonth=pawnMonth;
	}

	public Integer getPawnMonth() {
		return pawnMonth;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setUserName(String userName) {
		this.userName=userName == null ? userName : userName.trim();
	}

	public String getUserName() {
		return userName;
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

	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime=modifyTime;
	}

	public java.util.Date getModifyTime() {
		return modifyTime;
	}

}
