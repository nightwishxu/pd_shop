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
@Table(name="p_pawn_org")
public class PawnOrg implements Serializable {

	/**
	 *机构编号
	 */
	@ApiModelProperty(value="机构编号")
	@ApiParam(value="机构编号")
	@Column(name = "id")
	private Integer id;

	/**
	 *角色
	 */
	@ApiModelProperty(value="角色")
	@ApiParam(value="角色")
	@Column(name = "role_code")
	private String roleCode;

	/**
	 *1典当机构2服务商  6商户（个人企业认证）
	 */
	@ApiModelProperty(value="1典当机构2服务商  6商户（个人企业认证）")
	@ApiParam(value="1典当机构2服务商  6商户（个人企业认证）")
	@Column(name = "type")
	private Integer type;

	/**
	 *账号
	 */
	@ApiModelProperty(value="账号")
	@ApiParam(value="账号")
	@Column(name = "account")
	private String account;

	/**
	 *密码
	 */
	@ApiModelProperty(value="密码")
	@ApiParam(value="密码")
	@Column(name = "password")
	private String password;

	/**
	 *机构名称
	 */
	@ApiModelProperty(value="机构名称")
	@ApiParam(value="机构名称")
	@Column(name = "name")
	private String name;

	/**
	 *机构法人
	 */
	@ApiModelProperty(value="机构法人")
	@ApiParam(value="机构法人")
	@Column(name = "legal_person")
	private String legalPerson;

	/**
	 *注册资金
	 */
	@ApiModelProperty(value="注册资金")
	@ApiParam(value="注册资金")
	@Column(name = "registered_capital")
	private String registeredCapital;

	/**
	 *机构地址
	 */
	@ApiModelProperty(value="机构地址")
	@ApiParam(value="机构地址")
	@Column(name = "adress")
	private String adress;

	/**
	 *工商许可证号
	 */
	@ApiModelProperty(value="工商许可证号")
	@ApiParam(value="工商许可证号")
	@Column(name = "business_license_code")
	private String businessLicenseCode;

	/**
	 *工商许可证图片
	 */
	@ApiModelProperty(value="工商许可证图片")
	@ApiParam(value="工商许可证图片")
	@Column(name = "business_license")
	private String businessLicense;

	/**
	 *到期提醒设置 1.每天2.每周3.到期前3天
	 */
	@ApiModelProperty(value="到期提醒设置 1.每天2.每周3.到期前3天")
	@ApiParam(value="到期提醒设置 1.每天2.每周3.到期前3天")
	@Column(name = "expiration_remind_type")
	private Integer expirationRemindType;

	/**
	 *机构电话
	 */
	@ApiModelProperty(value="机构电话")
	@ApiParam(value="机构电话")
	@Column(name = "phone")
	private String phone;

	/**
	 *余额
	 */
	@ApiModelProperty(value="余额")
	@ApiParam(value="余额")
	@Column(name = "balance")
	private java.math.BigDecimal balance;

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
	 *1启动0禁用2待审核3审核失败
	 */
	@ApiModelProperty(value="1启动0禁用2待审核3审核失败")
	@ApiParam(value="1启动0禁用2待审核3审核失败")
	@Column(name = "state")
	private Integer state;

	/**
	 *1正常0不允许放贷
	 */
	@ApiModelProperty(value="1正常0不允许放贷")
	@ApiParam(value="1正常0不允许放贷")
	@Column(name = "pawn_state")
	private Integer pawnState;

	/**
	 *机构逾期利率（千分之）
	 */
	@ApiModelProperty(value="机构逾期利率（千分之）")
	@ApiParam(value="机构逾期利率（千分之）")
	@Column(name = "overdue_rate")
	private java.math.BigDecimal overdueRate;

	/**
	 *印章
	 */
	@ApiModelProperty(value="印章")
	@ApiParam(value="印章")
	@Column(name = "seal")
	private String seal;

	/**
	 *公司简介
	 */
	@ApiModelProperty(value="公司简介")
	@ApiParam(value="公司简介")
	@Column(name = "introduction")
	private String introduction;

	/**
	 *公司环境图片，多张逗号隔开
	 */
	@ApiModelProperty(value="公司环境图片，多张逗号隔开")
	@ApiParam(value="公司环境图片，多张逗号隔开")
	@Column(name = "org_images")
	private String orgImages;

	/**
	 *机构LOGO图片
	 */
	@ApiModelProperty(value="机构LOGO图片")
	@ApiParam(value="机构LOGO图片")
	@Column(name = "org_logo")
	private String orgLogo;

	/**
	 *逾期滞纳利率
	 */
	@ApiModelProperty(value="逾期滞纳利率")
	@ApiParam(value="逾期滞纳利率")
	@Column(name = "redeem_overrate")
	private java.math.BigDecimal redeemOverrate;

	/**
	 *标签
	 */
	@ApiModelProperty(value="标签")
	@ApiParam(value="标签")
	@Column(name = "labels")
	private String labels;

	/**
	 *积分
	 */
	@ApiModelProperty(value="积分")
	@ApiParam(value="积分")
	@Column(name = "integral")
	private java.math.BigDecimal integral;

	/**
	 *评分
	 */
	@ApiModelProperty(value="评分")
	@ApiParam(value="评分")
	@Column(name = "score")
	private java.math.BigDecimal score;

	/**
	 *评论数量
	 */
	@ApiModelProperty(value="评论数量")
	@ApiParam(value="评论数量")
	@Column(name = "comment_count")
	private Integer commentCount;

	/**
	 *物流评分
	 */
	@ApiModelProperty(value="物流评分")
	@ApiParam(value="物流评分")
	@Column(name = "express_score")
	private java.math.BigDecimal expressScore;

	/**
	 *服务评分
	 */
	@ApiModelProperty(value="服务评分")
	@ApiParam(value="服务评分")
	@Column(name = "service_score")
	private java.math.BigDecimal serviceScore;

	/**
	 *身份证正面
	 */
	@ApiModelProperty(value="身份证正面")
	@ApiParam(value="身份证正面")
	@Column(name = "id_card_img")
	private String idCardImg;

	/**
	 *身份证反面
	 */
	@ApiModelProperty(value="身份证反面")
	@ApiParam(value="身份证反面")
	@Column(name = "id_card_reverse")
	private String idCardReverse;

	/**
	 *身份证号
	 */
	@ApiModelProperty(value="身份证号")
	@ApiParam(value="身份证号")
	@Column(name = "id_card")
	private String idCard;

	/**
	 *典当许可证
	 */
	@ApiModelProperty(value="典当许可证")
	@ApiParam(value="典当许可证")
	@Column(name = "pawn_exequatur")
	private String pawnExequatur;

	/**
	 *其他辅助文件
	 */
	@ApiModelProperty(value="其他辅助文件")
	@ApiParam(value="其他辅助文件")
	@Column(name = "other_file")
	private String otherFile;

	/**
	 *法人电话
	 */
	@ApiModelProperty(value="法人电话")
	@ApiParam(value="法人电话")
	@Column(name = "legal_person_phone")
	private String legalPersonPhone;

	/**
	 *0 未缴纳店铺保证金 1缴纳
	 */
	@ApiModelProperty(value="0 未缴纳店铺保证金 1缴纳")
	@ApiParam(value="0 未缴纳店铺保证金 1缴纳")
	@Column(name = "is_store_deposit")
	private Integer isStoreDeposit;

	/**
	 *保证金金额
	 */
	@ApiModelProperty(value="保证金金额")
	@ApiParam(value="保证金金额")
	@Column(name = "deposit_amount")
	private java.math.BigDecimal depositAmount;

	/**
	 *签名
	 */
	@ApiModelProperty(value="签名")
	@ApiParam(value="签名")
	@Column(name = "signature")
	private String signature;

	/**
	 *店铺联系人
	 */
	@ApiModelProperty(value="店铺联系人")
	@ApiParam(value="店铺联系人")
	@Column(name = "store_contacts")
	private String storeContacts;

	/**
	 *店铺联系电话
	 */
	@ApiModelProperty(value="店铺联系电话")
	@ApiParam(value="店铺联系电话")
	@Column(name = "store_phone")
	private String storePhone;

	/**
	 *余额
	 */
	@ApiModelProperty(value="余额")
	@ApiParam(value="余额")
	@Column(name = "amount")
	private java.math.BigDecimal amount;

	/**
	 *安心签编号
	 */
	@ApiModelProperty(value="安心签编号")
	@ApiParam(value="安心签编号")
	@Column(name = "anxinsign_id")
	private String anxinsignId;

	/**
	 *固定电话
	 */
	@ApiModelProperty(value="固定电话")
	@ApiParam(value="固定电话")
	@Column(name = "land_line_phone")
	private String landLinePhone;

	/**
	 *手续费率
	 */
	@ApiModelProperty(value="手续费率")
	@ApiParam(value="手续费率")
	@Column(name = "service_rates")
	private java.math.BigDecimal serviceRates;

	/**
	 *安心签经办人电话
	 */
	@ApiModelProperty(value="安心签经办人电话")
	@ApiParam(value="安心签经办人电话")
	@Column(name = "anxin_phone")
	private String anxinPhone;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode=roleCode == null ? roleCode : roleCode.trim();
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setAccount(String account) {
		this.account=account == null ? account : account.trim();
	}

	public String getAccount() {
		return account;
	}

	public void setPassword(String password) {
		this.password=password == null ? password : password.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name=name == null ? name : name.trim();
	}

	public String getName() {
		return name;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson=legalPerson == null ? legalPerson : legalPerson.trim();
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital=registeredCapital == null ? registeredCapital : registeredCapital.trim();
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setAdress(String adress) {
		this.adress=adress == null ? adress : adress.trim();
	}

	public String getAdress() {
		return adress;
	}

	public void setBusinessLicenseCode(String businessLicenseCode) {
		this.businessLicenseCode=businessLicenseCode == null ? businessLicenseCode : businessLicenseCode.trim();
	}

	public String getBusinessLicenseCode() {
		return businessLicenseCode;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense=businessLicense == null ? businessLicense : businessLicense.trim();
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setExpirationRemindType(Integer expirationRemindType) {
		this.expirationRemindType=expirationRemindType;
	}

	public Integer getExpirationRemindType() {
		return expirationRemindType;
	}

	public void setPhone(String phone) {
		this.phone=phone == null ? phone : phone.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setBalance(java.math.BigDecimal balance) {
		this.balance=balance;
	}

	public java.math.BigDecimal getBalance() {
		return balance;
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

	public void setState(Integer state) {
		this.state=state;
	}

	public Integer getState() {
		return state;
	}

	public void setPawnState(Integer pawnState) {
		this.pawnState=pawnState;
	}

	public Integer getPawnState() {
		return pawnState;
	}

	public void setOverdueRate(java.math.BigDecimal overdueRate) {
		this.overdueRate=overdueRate;
	}

	public java.math.BigDecimal getOverdueRate() {
		return overdueRate;
	}

	public void setSeal(String seal) {
		this.seal=seal == null ? seal : seal.trim();
	}

	public String getSeal() {
		return seal;
	}

	public void setIntroduction(String introduction) {
		this.introduction=introduction == null ? introduction : introduction.trim();
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setOrgImages(String orgImages) {
		this.orgImages=orgImages == null ? orgImages : orgImages.trim();
	}

	public String getOrgImages() {
		return orgImages;
	}

	public void setOrgLogo(String orgLogo) {
		this.orgLogo=orgLogo == null ? orgLogo : orgLogo.trim();
	}

	public String getOrgLogo() {
		return orgLogo;
	}

	public void setRedeemOverrate(java.math.BigDecimal redeemOverrate) {
		this.redeemOverrate=redeemOverrate;
	}

	public java.math.BigDecimal getRedeemOverrate() {
		return redeemOverrate;
	}

	public void setLabels(String labels) {
		this.labels=labels == null ? labels : labels.trim();
	}

	public String getLabels() {
		return labels;
	}

	public void setIntegral(java.math.BigDecimal integral) {
		this.integral=integral;
	}

	public java.math.BigDecimal getIntegral() {
		return integral;
	}

	public void setScore(java.math.BigDecimal score) {
		this.score=score;
	}

	public java.math.BigDecimal getScore() {
		return score;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount=commentCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setExpressScore(java.math.BigDecimal expressScore) {
		this.expressScore=expressScore;
	}

	public java.math.BigDecimal getExpressScore() {
		return expressScore;
	}

	public void setServiceScore(java.math.BigDecimal serviceScore) {
		this.serviceScore=serviceScore;
	}

	public java.math.BigDecimal getServiceScore() {
		return serviceScore;
	}

	public void setIdCardImg(String idCardImg) {
		this.idCardImg=idCardImg == null ? idCardImg : idCardImg.trim();
	}

	public String getIdCardImg() {
		return idCardImg;
	}

	public void setIdCardReverse(String idCardReverse) {
		this.idCardReverse=idCardReverse == null ? idCardReverse : idCardReverse.trim();
	}

	public String getIdCardReverse() {
		return idCardReverse;
	}

	public void setIdCard(String idCard) {
		this.idCard=idCard == null ? idCard : idCard.trim();
	}

	public String getIdCard() {
		return idCard;
	}

	public void setPawnExequatur(String pawnExequatur) {
		this.pawnExequatur=pawnExequatur == null ? pawnExequatur : pawnExequatur.trim();
	}

	public String getPawnExequatur() {
		return pawnExequatur;
	}

	public void setOtherFile(String otherFile) {
		this.otherFile=otherFile == null ? otherFile : otherFile.trim();
	}

	public String getOtherFile() {
		return otherFile;
	}

	public void setLegalPersonPhone(String legalPersonPhone) {
		this.legalPersonPhone=legalPersonPhone == null ? legalPersonPhone : legalPersonPhone.trim();
	}

	public String getLegalPersonPhone() {
		return legalPersonPhone;
	}

	public void setIsStoreDeposit(Integer isStoreDeposit) {
		this.isStoreDeposit=isStoreDeposit;
	}

	public Integer getIsStoreDeposit() {
		return isStoreDeposit;
	}

	public void setDepositAmount(java.math.BigDecimal depositAmount) {
		this.depositAmount=depositAmount;
	}

	public java.math.BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setSignature(String signature) {
		this.signature=signature == null ? signature : signature.trim();
	}

	public String getSignature() {
		return signature;
	}

	public void setStoreContacts(String storeContacts) {
		this.storeContacts=storeContacts == null ? storeContacts : storeContacts.trim();
	}

	public String getStoreContacts() {
		return storeContacts;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone=storePhone == null ? storePhone : storePhone.trim();
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setAmount(java.math.BigDecimal amount) {
		this.amount=amount;
	}

	public java.math.BigDecimal getAmount() {
		return amount;
	}

	public void setAnxinsignId(String anxinsignId) {
		this.anxinsignId=anxinsignId == null ? anxinsignId : anxinsignId.trim();
	}

	public String getAnxinsignId() {
		return anxinsignId;
	}

	public void setLandLinePhone(String landLinePhone) {
		this.landLinePhone=landLinePhone == null ? landLinePhone : landLinePhone.trim();
	}

	public String getLandLinePhone() {
		return landLinePhone;
	}

	public void setServiceRates(java.math.BigDecimal serviceRates) {
		this.serviceRates=serviceRates;
	}

	public java.math.BigDecimal getServiceRates() {
		return serviceRates;
	}

	public void setAnxinPhone(String anxinPhone) {
		this.anxinPhone=anxinPhone == null ? anxinPhone : anxinPhone.trim();
	}

	public String getAnxinPhone() {
		return anxinPhone;
	}

}
