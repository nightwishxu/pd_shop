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
@Table(name="t_user_report")
public class UserReport implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *手机号
	 */
	@ApiModelProperty(value="手机号")
	@ApiParam(value="手机号")
	@Column(name = "account")
	private String account;

	/**
	 *昵称
	 */
	@ApiModelProperty(value="昵称")
	@ApiParam(value="昵称")
	@Column(name = "nick_name")
	private String nickName;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "target_id")
	private Integer targetId;

	/**
	 *1 动态举报 2 商品举报
	 */
	@ApiModelProperty(value="1 动态举报 2 商品举报")
	@ApiParam(value="1 动态举报 2 商品举报")
	@Column(name = "type")
	private Integer type;

	/**
	 *举报信息
	 */
	@ApiModelProperty(value="举报信息")
	@ApiParam(value="举报信息")
	@Column(name = "info")
	private String info;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "imgs")
	private String imgs;

	/**
	 *0删除1未处理2已处理
	 */
	@ApiModelProperty(value="0删除1未处理2已处理")
	@ApiParam(value="0删除1未处理2已处理")
	@Column(name = "status")
	private Integer status;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "create_account")
	private String createAccount;

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
	@Column(name = "modify_account")
	private String modifyAccount;

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

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setAccount(String account) {
		this.account=account == null ? account : account.trim();
	}

	public String getAccount() {
		return account;
	}

	public void setNickName(String nickName) {
		this.nickName=nickName == null ? nickName : nickName.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setTargetId(Integer targetId) {
		this.targetId=targetId;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setInfo(String info) {
		this.info=info == null ? info : info.trim();
	}

	public String getInfo() {
		return info;
	}

	public void setImgs(String imgs) {
		this.imgs=imgs == null ? imgs : imgs.trim();
	}

	public String getImgs() {
		return imgs;
	}

	public void setStatus(Integer status) {
		this.status=status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount=createAccount == null ? createAccount : createAccount.trim();
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setModifyAccount(String modifyAccount) {
		this.modifyAccount=modifyAccount == null ? modifyAccount : modifyAccount.trim();
	}

	public String getModifyAccount() {
		return modifyAccount;
	}

	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime=modifyTime;
	}

	public java.util.Date getModifyTime() {
		return modifyTime;
	}

}
