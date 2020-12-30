package com.item.dao.model;

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
@Table(name="b_user")
public class User implements Serializable {

	/**
	 *用户id
	 */
	@ApiModelProperty(value="用户id")
	@ApiParam(value="用户id")
	@Column(name = "id")
	private Integer id;

	/**
	 *用户账号(手机号)
	 */
	@ApiModelProperty(value="用户账号(手机号)")
	@ApiParam(value="用户账号(手机号)")
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
	 *用户昵称
	 */
	@ApiModelProperty(value="用户昵称")
	@ApiParam(value="用户昵称")
	@Column(name = "nick_name")
	private String nickName;

	/**
	 *头像
	 */
	@ApiModelProperty(value="头像")
	@ApiParam(value="头像")
	@Column(name = "head_img")
	private String headImg;

	/**
	 *性别 0:女 1:男 2:未知
	 */
	@ApiModelProperty(value="性别 0:女 1:男 2:未知")
	@ApiParam(value="性别 0:女 1:男 2:未知")
	@Column(name = "sex")
	private Integer sex;

	/**
	 *姓名
	 */
	@ApiModelProperty(value="姓名")
	@ApiParam(value="姓名")
	@Column(name = "name")
	private String name;

	/**
	 *手机号
	 */
	@ApiModelProperty(value="手机号")
	@ApiParam(value="手机号")
	@Column(name = "phone")
	private String phone;

	/**
	 *身份证
	 */
	@ApiModelProperty(value="身份证")
	@ApiParam(value="身份证")
	@Column(name = "id_card")
	private String idCard;

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
	 *身份证证件地址
	 */
	@ApiModelProperty(value="身份证证件地址")
	@ApiParam(value="身份证证件地址")
	@Column(name = "id_card_hand")
	private String idCardHand;

	/**
	 *摇一摇
	 */
	@ApiModelProperty(value="摇一摇")
	@ApiParam(value="摇一摇")
	@Column(name = "head_shake")
	private String headShake;

	/**
	 *点点头
	 */
	@ApiModelProperty(value="点点头")
	@ApiParam(value="点点头")
	@Column(name = "head_nod")
	private String headNod;

	/**
	 *眨眼睛
	 */
	@ApiModelProperty(value="眨眼睛")
	@ApiParam(value="眨眼睛")
	@Column(name = "head_eye")
	private String headEye;

	/**
	 *身份证人脸是否绑定(0:未绑定 1绑定)
	 */
	@ApiModelProperty(value="身份证人脸是否绑定(0:未绑定 1绑定)")
	@ApiParam(value="身份证人脸是否绑定(0:未绑定 1绑定)")
	@Column(name = "is_bind")
	private Integer isBind;

	/**
	 *0普通用户1机构员工账号
	 */
	@ApiModelProperty(value="0普通用户1机构员工账号")
	@ApiParam(value="0普通用户1机构员工账号")
	@Column(name = "type")
	private Integer type;

	/**
	 *机构id
	 */
	@ApiModelProperty(value="机构id")
	@ApiParam(value="机构id")
	@Column(name = "org_id")
	private Integer orgId;

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
	 *完善资料1是0否
	 */
	@ApiModelProperty(value="完善资料1是0否")
	@ApiParam(value="完善资料1是0否")
	@Column(name = "is_complete")
	private Integer isComplete;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "balance")
	private java.math.BigDecimal balance;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "state")
	private Integer state;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "credit")
	private Integer credit;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "login_time")
	private java.util.Date loginTime;

	/**
	 *智齿token
	 */
	@ApiModelProperty(value="智齿token")
	@ApiParam(value="智齿token")
	@Column(name = "sobot_token")
	private String sobotToken;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "last_sobot_token_time")
	private java.util.Date lastSobotTokenTime;

	/**
	 *融云token
	 */
	@ApiModelProperty(value="融云token")
	@ApiParam(value="融云token")
	@Column(name = "im_token")
	private String imToken;

	/**
	 *微信openid
	 */
	@ApiModelProperty(value="微信openid")
	@ApiParam(value="微信openid")
	@Column(name = "wx_openid")
	private String wxOpenid;

	/**
	 *QQopenid
	 */
	@ApiModelProperty(value="QQopenid")
	@ApiParam(value="QQopenid")
	@Column(name = "qq_openid")
	private String qqOpenid;

	/**
	 *推荐顺序
	 */
	@ApiModelProperty(value="推荐顺序")
	@ApiParam(value="推荐顺序")
	@Column(name = "recommend_sort")
	private Integer recommendSort;

	/**
	 *动态数量
	 */
	@ApiModelProperty(value="动态数量")
	@ApiParam(value="动态数量")
	@Column(name = "article_count")
	private Integer articleCount;

	/**
	 *关注数量
	 */
	@ApiModelProperty(value="关注数量")
	@ApiParam(value="关注数量")
	@Column(name = "follow_count")
	private Integer followCount;

	/**
	 *粉丝数量
	 */
	@ApiModelProperty(value="粉丝数量")
	@ApiParam(value="粉丝数量")
	@Column(name = "fans_count")
	private Integer fansCount;

	/**
	 *订单数量
	 */
	@ApiModelProperty(value="订单数量")
	@ApiParam(value="订单数量")
	@Column(name = "order_count")
	private Integer orderCount;

	/**
	 *0未绑定 1 绑定身份证成功 2 活动验证中 3活动验证失败 4活体验证成功
	 */
	@ApiModelProperty(value="0未绑定 1 绑定身份证成功 2 活动验证中 3活动验证失败 4活体验证成功")
	@ApiParam(value="0未绑定 1 绑定身份证成功 2 活动验证中 3活动验证失败 4活体验证成功")
	@Column(name = "auth_status")
	private Integer authStatus;

	/**
	 *验证流水号
	 */
	@ApiModelProperty(value="验证流水号")
	@ApiParam(value="验证流水号")
	@Column(name = "txsn")
	private String txsn;

	/**
	 *安心签用户编号
	 */
	@ApiModelProperty(value="安心签用户编号")
	@ApiParam(value="安心签用户编号")
	@Column(name = "anxinsign_id")
	private String anxinsignId;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
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

	public void setNickName(String nickName) {
		this.nickName=nickName == null ? nickName : nickName.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setHeadImg(String headImg) {
		this.headImg=headImg == null ? headImg : headImg.trim();
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setSex(Integer sex) {
		this.sex=sex;
	}

	public Integer getSex() {
		return sex;
	}

	public void setName(String name) {
		this.name=name == null ? name : name.trim();
	}

	public String getName() {
		return name;
	}

	public void setPhone(String phone) {
		this.phone=phone == null ? phone : phone.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setIdCard(String idCard) {
		this.idCard=idCard == null ? idCard : idCard.trim();
	}

	public String getIdCard() {
		return idCard;
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

	public void setIdCardHand(String idCardHand) {
		this.idCardHand=idCardHand == null ? idCardHand : idCardHand.trim();
	}

	public String getIdCardHand() {
		return idCardHand;
	}

	public void setHeadShake(String headShake) {
		this.headShake=headShake == null ? headShake : headShake.trim();
	}

	public String getHeadShake() {
		return headShake;
	}

	public void setHeadNod(String headNod) {
		this.headNod=headNod == null ? headNod : headNod.trim();
	}

	public String getHeadNod() {
		return headNod;
	}

	public void setHeadEye(String headEye) {
		this.headEye=headEye == null ? headEye : headEye.trim();
	}

	public String getHeadEye() {
		return headEye;
	}

	public void setIsBind(Integer isBind) {
		this.isBind=isBind;
	}

	public Integer getIsBind() {
		return isBind;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setOrgId(Integer orgId) {
		this.orgId=orgId;
	}

	public Integer getOrgId() {
		return orgId;
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

	public void setIsComplete(Integer isComplete) {
		this.isComplete=isComplete;
	}

	public Integer getIsComplete() {
		return isComplete;
	}

	public void setBalance(java.math.BigDecimal balance) {
		this.balance=balance;
	}

	public java.math.BigDecimal getBalance() {
		return balance;
	}

	public void setState(Integer state) {
		this.state=state;
	}

	public Integer getState() {
		return state;
	}

	public void setCredit(Integer credit) {
		this.credit=credit;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setLoginTime(java.util.Date loginTime) {
		this.loginTime=loginTime;
	}

	public java.util.Date getLoginTime() {
		return loginTime;
	}

	public void setSobotToken(String sobotToken) {
		this.sobotToken=sobotToken == null ? sobotToken : sobotToken.trim();
	}

	public String getSobotToken() {
		return sobotToken;
	}

	public void setLastSobotTokenTime(java.util.Date lastSobotTokenTime) {
		this.lastSobotTokenTime=lastSobotTokenTime;
	}

	public java.util.Date getLastSobotTokenTime() {
		return lastSobotTokenTime;
	}

	public void setImToken(String imToken) {
		this.imToken=imToken == null ? imToken : imToken.trim();
	}

	public String getImToken() {
		return imToken;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid=wxOpenid == null ? wxOpenid : wxOpenid.trim();
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setQqOpenid(String qqOpenid) {
		this.qqOpenid=qqOpenid == null ? qqOpenid : qqOpenid.trim();
	}

	public String getQqOpenid() {
		return qqOpenid;
	}

	public void setRecommendSort(Integer recommendSort) {
		this.recommendSort=recommendSort;
	}

	public Integer getRecommendSort() {
		return recommendSort;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount=articleCount;
	}

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount=followCount;
	}

	public Integer getFollowCount() {
		return followCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount=fansCount;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount=orderCount;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus=authStatus;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setTxsn(String txsn) {
		this.txsn=txsn == null ? txsn : txsn.trim();
	}

	public String getTxsn() {
		return txsn;
	}

	public void setAnxinsignId(String anxinsignId) {
		this.anxinsignId=anxinsignId == null ? anxinsignId : anxinsignId.trim();
	}

	public String getAnxinsignId() {
		return anxinsignId;
	}

}
