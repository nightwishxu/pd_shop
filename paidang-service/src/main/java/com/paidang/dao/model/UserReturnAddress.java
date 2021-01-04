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
@Table(name="p_user_return_address")
public class UserReturnAddress implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *机构编号
	 */
	@ApiModelProperty(value="机构编号")
	@ApiParam(value="机构编号")
	@Column(name = "org_id")
	private Integer orgId;

	/**
	 *用户id
	 */
	@ApiModelProperty(value="用户id")
	@ApiParam(value="用户id")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *1 用户个人 2 商户 3 机构
	 */
	@ApiModelProperty(value="1 用户个人 2 商户 3 机构")
	@ApiParam(value="1 用户个人 2 商户 3 机构")
	@Column(name = "type")
	private Integer type;

	/**
	 *收件人名字
	 */
	@ApiModelProperty(value="收件人名字")
	@ApiParam(value="收件人名字")
	@Column(name = "user_name")
	private String userName;

	/**
	 *地区
	 */
	@ApiModelProperty(value="地区")
	@ApiParam(value="地区")
	@Column(name = "area")
	private String area;

	/**
	 *详细地址
	 */
	@ApiModelProperty(value="详细地址")
	@ApiParam(value="详细地址")
	@Column(name = "address")
	private String address;

	/**
	 *1默认0否
	 */
	@ApiModelProperty(value="1默认0否")
	@ApiParam(value="1默认0否")
	@Column(name = "is_default")
	private Integer isDefault;

	/**
	 *电话
	 */
	@ApiModelProperty(value="电话")
	@ApiParam(value="电话")
	@Column(name = "phone")
	private String phone;

	/**
	 *创建时间
	 */
	@ApiModelProperty(value="创建时间")
	@ApiParam(value="创建时间")
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *更新时间
	 */
	@ApiModelProperty(value="更新时间")
	@ApiParam(value="更新时间")
	@Column(name = "modify_time")
	private java.util.Date modifyTime;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setOrgId(Integer orgId) {
		this.orgId=orgId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
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

	public void setArea(String area) {
		this.area=area == null ? area : area.trim();
	}

	public String getArea() {
		return area;
	}

	public void setAddress(String address) {
		this.address=address == null ? address : address.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault=isDefault;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setPhone(String phone) {
		this.phone=phone == null ? phone : phone.trim();
	}

	public String getPhone() {
		return phone;
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
