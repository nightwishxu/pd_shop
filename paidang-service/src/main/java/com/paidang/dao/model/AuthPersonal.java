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
@Table(name="b_auth_personal")
public class AuthPersonal implements Serializable {

	/**
	 *id
	 */
	@ApiModelProperty(value="id")
	@ApiParam(value="id")
	@Column(name = "id")
	private Integer id;

	/**
	 *logo图片
	 */
	@ApiModelProperty(value="logo图片")
	@ApiParam(value="logo图片")
	@Column(name = "logo")
	private String logo;

	/**
	 *店铺名称
	 */
	@ApiModelProperty(value="店铺名称")
	@ApiParam(value="店铺名称")
	@Column(name = "store_name")
	private String storeName;

	/**
	 *店铺介绍
	 */
	@ApiModelProperty(value="店铺介绍")
	@ApiParam(value="店铺介绍")
	@Column(name = "store_introduce")
	private String storeIntroduce;

	/**
	 *姓名
	 */
	@ApiModelProperty(value="姓名")
	@ApiParam(value="姓名")
	@Column(name = "name")
	private String name;

	/**
	 *身份证
	 */
	@ApiModelProperty(value="身份证")
	@ApiParam(value="身份证")
	@Column(name = "id_card")
	private String idCard;

	/**
	 *手机号码
	 */
	@ApiModelProperty(value="手机号码")
	@ApiParam(value="手机号码")
	@Column(name = "phone")
	private String phone;

	/**
	 *身份证照片正面
	 */
	@ApiModelProperty(value="身份证照片正面")
	@ApiParam(value="身份证照片正面")
	@Column(name = "id_card_front")
	private String idCardFront;

	/**
	 *身份证照片反面
	 */
	@ApiModelProperty(value="身份证照片反面")
	@ApiParam(value="身份证照片反面")
	@Column(name = "id_card_back")
	private String idCardBack;

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
	 *审核不通过原因
	 */
	@ApiModelProperty(value="审核不通过原因")
	@ApiParam(value="审核不通过原因")
	@Column(name = "refuse_info")
	private String refuseInfo;

	/**
	 *机构表id
	 */
	@ApiModelProperty(value="机构表id")
	@ApiParam(value="机构表id")
	@Column(name = "org_id")
	private Integer orgId;

	/**
	 *手持身份证正面
	 */
	@ApiModelProperty(value="手持身份证正面")
	@ApiParam(value="手持身份证正面")
	@Column(name = "hand_id_card_front")
	private String handIdCardFront;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setLogo(String logo) {
		this.logo=logo == null ? logo : logo.trim();
	}

	public String getLogo() {
		return logo;
	}

	public void setStoreName(String storeName) {
		this.storeName=storeName == null ? storeName : storeName.trim();
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreIntroduce(String storeIntroduce) {
		this.storeIntroduce=storeIntroduce == null ? storeIntroduce : storeIntroduce.trim();
	}

	public String getStoreIntroduce() {
		return storeIntroduce;
	}

	public void setName(String name) {
		this.name=name == null ? name : name.trim();
	}

	public String getName() {
		return name;
	}

	public void setIdCard(String idCard) {
		this.idCard=idCard == null ? idCard : idCard.trim();
	}

	public String getIdCard() {
		return idCard;
	}

	public void setPhone(String phone) {
		this.phone=phone == null ? phone : phone.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setIdCardFront(String idCardFront) {
		this.idCardFront=idCardFront == null ? idCardFront : idCardFront.trim();
	}

	public String getIdCardFront() {
		return idCardFront;
	}

	public void setIdCardBack(String idCardBack) {
		this.idCardBack=idCardBack == null ? idCardBack : idCardBack.trim();
	}

	public String getIdCardBack() {
		return idCardBack;
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

	public void setHandIdCardFront(String handIdCardFront) {
		this.handIdCardFront=handIdCardFront == null ? handIdCardFront : handIdCardFront.trim();
	}

	public String getHandIdCardFront() {
		return handIdCardFront;
	}

}
