package com.api.view.home;

import com.base.util.DFA;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

@ApiModel(value="userInfo")
public class UserInfo {

	@ApiModelProperty(value="用户id")
	@ApiParam(hidden=true)
	private Integer id;
	@ApiModelProperty(value="账号")
	@ApiParam(hidden=true)
	private String account;
	@ApiModelProperty(value="昵称")
	private String nickName;
	@ApiModelProperty(value="头像")
	private String headImg;
	@ApiModelProperty(value="性别0:女 1:男")
	private Integer sex;
	@ApiModelProperty(value="用户余额")
	private String balance;
	@ApiModelProperty(value="地址")
	private String address;
	@ApiModelProperty(value="身份证正面")
	private String idCardImg;
	@ApiModelProperty(value="身份证反面")
	private String idCardReverse;
	@ApiModelProperty(value="身份证是否绑定0:未绑定 1绑定")
	private Integer isBind;
	public UserInfo(){};

	public UserInfo(Integer id,String nickName){
		this.id = id;
		this.nickName = nickName;
		this.sex = 0;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNickName() {
		return DFA.replaceKeys(nickName);
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdCardImg() {
		return idCardImg;
	}

	public void setIdCardImg(String idCardImg) {
		this.idCardImg = idCardImg;
	}

	public String getIdCardReverse() {
		return idCardReverse;
	}

	public void setIdCardReverse(String idCardReverse) {
		this.idCardReverse = idCardReverse;
	}

	public Integer getIsBind() {
		return isBind;
	}

	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}
}
