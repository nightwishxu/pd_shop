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
@Table(name="b_auth_enterprise")
public class AuthEnterprise implements Serializable {

	/**
	 *id
	 */
	@ApiModelProperty(value="id")
	@ApiParam(value="id")
	@Column(name = "id")
	private Integer id;

	/**
	 *店铺名称
	 */
	@ApiModelProperty(value="店铺名称")
	@ApiParam(value="店铺名称")
	@Column(name = "store_name")
	private String storeName;

	/**
	 *店铺logo
	 */
	@ApiModelProperty(value="店铺logo")
	@ApiParam(value="店铺logo")
	@Column(name = "logo")
	private String logo;

	/**
	 *店铺介绍
	 */
	@ApiModelProperty(value="店铺介绍")
	@ApiParam(value="店铺介绍")
	@Column(name = "store_introduce")
	private String storeIntroduce;

	/**
	 *企业名称
	 */
	@ApiModelProperty(value="企业名称")
	@ApiParam(value="企业名称")
	@Column(name = "enterprise_name")
	private String enterpriseName;

	/**
	 *法人姓名
	 */
	@ApiModelProperty(value="法人姓名")
	@ApiParam(value="法人姓名")
	@Column(name = "legal_person_name")
	private String legalPersonName;

	/**
	 *法人身份证
	 */
	@ApiModelProperty(value="法人身份证")
	@ApiParam(value="法人身份证")
	@Column(name = "legal_person_card")
	private String legalPersonCard;

	/**
	 *营业执照副本照片
	 */
	@ApiModelProperty(value="营业执照副本照片")
	@ApiParam(value="营业执照副本照片")
	@Column(name = "business_license_photo")
	private String businessLicensePhoto;

	/**
	 *法人身份证正面照片
	 */
	@ApiModelProperty(value="法人身份证正面照片")
	@ApiParam(value="法人身份证正面照片")
	@Column(name = "legal_person_card_front")
	private String legalPersonCardFront;

	/**
	 *法人身份证反面照片
	 */
	@ApiModelProperty(value="法人身份证反面照片")
	@ApiParam(value="法人身份证反面照片")
	@Column(name = "legal_person_card_back")
	private String legalPersonCardBack;

	/**
	 *创建人
	 */
	@ApiModelProperty(value="创建人")
	@ApiParam(value="创建人")
	@Column(name = "create_user")
	private Integer createUser;

	/**
	 *创建时间
	 */
	@ApiModelProperty(value="创建时间")
	@ApiParam(value="创建时间")
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *0审核中，1审核成功 2审核失败 3无效
	 */
	@ApiModelProperty(value="0审核中，1审核成功 2审核失败 3无效")
	@ApiParam(value="0审核中，1审核成功 2审核失败 3无效")
	@Column(name = "state")
	private String state;

	/**
	 *不通过原因
	 */
	@ApiModelProperty(value="不通过原因")
	@ApiParam(value="不通过原因")
	@Column(name = "refuse_info")
	private String refuseInfo;

	/**
	 *机构表id
	 */
	@ApiModelProperty(value="机构表id")
	@ApiParam(value="机构表id")
	@Column(name = "org_id")
	private Integer orgId;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setStoreName(String storeName) {
		this.storeName=storeName == null ? storeName : storeName.trim();
	}

	public String getStoreName() {
		return storeName;
	}

	public void setLogo(String logo) {
		this.logo=logo == null ? logo : logo.trim();
	}

	public String getLogo() {
		return logo;
	}

	public void setStoreIntroduce(String storeIntroduce) {
		this.storeIntroduce=storeIntroduce == null ? storeIntroduce : storeIntroduce.trim();
	}

	public String getStoreIntroduce() {
		return storeIntroduce;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName=enterpriseName == null ? enterpriseName : enterpriseName.trim();
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName=legalPersonName == null ? legalPersonName : legalPersonName.trim();
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonCard(String legalPersonCard) {
		this.legalPersonCard=legalPersonCard == null ? legalPersonCard : legalPersonCard.trim();
	}

	public String getLegalPersonCard() {
		return legalPersonCard;
	}

	public void setBusinessLicensePhoto(String businessLicensePhoto) {
		this.businessLicensePhoto=businessLicensePhoto == null ? businessLicensePhoto : businessLicensePhoto.trim();
	}

	public String getBusinessLicensePhoto() {
		return businessLicensePhoto;
	}

	public void setLegalPersonCardFront(String legalPersonCardFront) {
		this.legalPersonCardFront=legalPersonCardFront == null ? legalPersonCardFront : legalPersonCardFront.trim();
	}

	public String getLegalPersonCardFront() {
		return legalPersonCardFront;
	}

	public void setLegalPersonCardBack(String legalPersonCardBack) {
		this.legalPersonCardBack=legalPersonCardBack == null ? legalPersonCardBack : legalPersonCardBack.trim();
	}

	public String getLegalPersonCardBack() {
		return legalPersonCardBack;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser=createUser;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setState(String state) {
		this.state=state == null ? state : state.trim();
	}

	public String getState() {
		return state;
	}

	public void setRefuseInfo(String refuseInfo) {
		this.refuseInfo=refuseInfo == null ? refuseInfo : refuseInfo.trim();
	}

	public String getRefuseInfo() {
		return refuseInfo;
	}

	public void setOrgId(Integer orgId) {
		this.orgId=orgId;
	}

	public Integer getOrgId() {
		return orgId;
	}

}
