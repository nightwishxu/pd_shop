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
@Table(name="p_user_goods")
public class UserGoods implements Serializable {

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
	 *所有人id
	 */
	@ApiModelProperty(value="所有人id")
	@ApiParam(value="所有人id")
	@Column(name = "belong_id")
	private Integer belongId;

	/**
	 *所属类型0平台1用户2机构
	 */
	@ApiModelProperty(value="所属类型0平台1用户2机构")
	@ApiParam(value="所属类型0平台1用户2机构")
	@Column(name = "belong_type")
	private Integer belongType;

	/**
	 *名称
	 */
	@ApiModelProperty(value="名称")
	@ApiParam(value="名称")
	@Column(name = "name")
	private String name;

	/**
	 *宝贝图片
	 */
	@ApiModelProperty(value="宝贝图片")
	@ApiParam(value="宝贝图片")
	@Column(name = "images")
	private String images;

	/**
	 *宝贝附件照
	 */
	@ApiModelProperty(value="宝贝附件照")
	@ApiParam(value="宝贝附件照")
	@Column(name = "goods_imgs")
	private String goodsImgs;

	/**
	 *宝贝视频
	 */
	@ApiModelProperty(value="宝贝视频")
	@ApiParam(value="宝贝视频")
	@Column(name = "video")
	private String video;

	/**
	 *购买时间
	 */
	@ApiModelProperty(value="购买时间")
	@ApiParam(value="购买时间")
	@Column(name = "buy_time")
	private java.util.Date buyTime;

	/**
	 *购买价格
	 */
	@ApiModelProperty(value="购买价格")
	@ApiParam(value="购买价格")
	@Column(name = "buy_price")
	private java.math.BigDecimal buyPrice;

	/**
	 *宝贝备注
	 */
	@ApiModelProperty(value="宝贝备注")
	@ApiParam(value="宝贝备注")
	@Column(name = "info")
	private String info;

	/**
	 *宝贝属性(json[{name:'',content:'',contentType:1}])contentType:1普通2时间3图片4视频
	 */
	@ApiModelProperty(value="宝贝属性(json[{name:'',content:'',contentType:1}])contentType:1普通2时间3图片4视频")
	@ApiParam(value="宝贝属性(json[{name:'',content:'',contentType:1}])contentType:1普通2时间3图片4视频")
	@Column(name = "content")
	private String content;

	/**
	 *所在城市
	 */
	@ApiModelProperty(value="所在城市")
	@ApiParam(value="所在城市")
	@Column(name = "city")
	private String city;

	/**
	 *藏友姓名
	 */
	@ApiModelProperty(value="藏友姓名")
	@ApiParam(value="藏友姓名")
	@Column(name = "user_name")
	private String userName;

	/**
	 *手机号码
	 */
	@ApiModelProperty(value="手机号码")
	@ApiParam(value="手机号码")
	@Column(name = "user_phone")
	private String userPhone;

	/**
	 *鉴定要求
	 */
	@ApiModelProperty(value="鉴定要求")
	@ApiParam(value="鉴定要求")
	@Column(name = "authenticate_require")
	private String authenticateRequire;

	/**
	 *鉴定类别id
	 */
	@ApiModelProperty(value="鉴定类别id")
	@ApiParam(value="鉴定类别id")
	@Column(name = "cate_id")
	private Integer cateId;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "cate_code")
	private String cateCode;

	/**
	 *鉴定结果(-1:无0:未鉴定 1:鉴定中 2:无法鉴定 3:赝品 4:真品)
	 */
	@ApiModelProperty(value="鉴定结果(-1:无0:未鉴定 1:鉴定中 2:无法鉴定 3:赝品 4:真品)")
	@ApiParam(value="鉴定结果(-1:无0:未鉴定 1:鉴定中 2:无法鉴定 3:赝品 4:真品)")
	@Column(name = "auth_result")
	private Integer authResult;

	/**
	 *估价
	 */
	@ApiModelProperty(value="估价")
	@ApiParam(value="估价")
	@Column(name = "auth_price_test")
	private java.math.BigDecimal authPriceTest;

	/**
	 *鉴定价
	 */
	@ApiModelProperty(value="鉴定价")
	@ApiParam(value="鉴定价")
	@Column(name = "auth_price")
	private java.math.BigDecimal authPrice;

	/**
	 *取回时，平台拍摄的图片
	 */
	@ApiModelProperty(value="取回时，平台拍摄的图片")
	@ApiParam(value="取回时，平台拍摄的图片")
	@Column(name = "back_images")
	private String backImages;

	/**
	 *取回时，平台拍摄的视频
	 */
	@ApiModelProperty(value="取回时，平台拍摄的视频")
	@ApiParam(value="取回时，平台拍摄的视频")
	@Column(name = "back_video")
	private String backVideo;

	/**
	 *平台收货时拆箱视频
	 */
	@ApiModelProperty(value="平台收货时拆箱视频")
	@ApiParam(value="平台收货时拆箱视频")
	@Column(name = "open_goods_video")
	private String openGoodsVideo;

	/**
	 *平台收获视频
	 */
	@ApiModelProperty(value="平台收获视频")
	@ApiParam(value="平台收获视频")
	@Column(name = "plat_goods_receive_video")
	private String platGoodsReceiveVideo;

	/**
	 *平台鉴定视频
	 */
	@ApiModelProperty(value="平台鉴定视频")
	@ApiParam(value="平台鉴定视频")
	@Column(name = "plat_goods_auth_video")
	private String platGoodsAuthVideo;

	/**
	 *平台拒绝理由
	 */
	@ApiModelProperty(value="平台拒绝理由")
	@ApiParam(value="平台拒绝理由")
	@Column(name = "not_verify_reason")
	private String notVerifyReason;

	/**
	 *拍摄去邮寄的宝贝视频
	 */
	@ApiModelProperty(value="拍摄去邮寄的宝贝视频")
	@ApiParam(value="拍摄去邮寄的宝贝视频")
	@Column(name = "go_video")
	private String goVideo;

	/**
	 *是否去典当0否  1是
	 */
	@ApiModelProperty(value="是否去典当0否  1是")
	@ApiParam(value="是否去典当0否  1是")
	@Column(name = "goto_pawn")
	private Integer gotoPawn;

	/**
	 *0无1申请卖给平台2已卖出
	 */
	@ApiModelProperty(value="0无1申请卖给平台2已卖出")
	@ApiParam(value="0无1申请卖给平台2已卖出")
	@Column(name = "go_sell")
	private Integer goSell;

	/**
	 *0用户1平台
	 */
	@ApiModelProperty(value="0用户1平台")
	@ApiParam(value="0用户1平台")
	@Column(name = "location")
	private Integer location;

	/**
	 *1未邮寄2邮寄中3平台确认4已经到货
	 */
	@ApiModelProperty(value="1未邮寄2邮寄中3平台确认4已经到货")
	@ApiParam(value="1未邮寄2邮寄中3平台确认4已经到货")
	@Column(name = "post_state")
	private Integer postState;

	/**
	 *寄件人
	 */
	@ApiModelProperty(value="寄件人")
	@ApiParam(value="寄件人")
	@Column(name = "post_user_name")
	private String postUserName;

	/**
	 *寄件人电话
	 */
	@ApiModelProperty(value="寄件人电话")
	@ApiParam(value="寄件人电话")
	@Column(name = "post_user_phone")
	private String postUserPhone;

	/**
	 *寄件快递
	 */
	@ApiModelProperty(value="寄件快递")
	@ApiParam(value="寄件快递")
	@Column(name = "post_express")
	private String postExpress;

	/**
	 *寄件单号
	 */
	@ApiModelProperty(value="寄件单号")
	@ApiParam(value="寄件单号")
	@Column(name = "post_express_code")
	private String postExpressCode;

	/**
	 *收件人
	 */
	@ApiModelProperty(value="收件人")
	@ApiParam(value="收件人")
	@Column(name = "back_user_name")
	private String backUserName;

	/**
	 *收件人电话
	 */
	@ApiModelProperty(value="收件人电话")
	@ApiParam(value="收件人电话")
	@Column(name = "back_user_phone")
	private String backUserPhone;

	/**
	 *收件人地址
	 */
	@ApiModelProperty(value="收件人地址")
	@ApiParam(value="收件人地址")
	@Column(name = "back_user_express")
	private String backUserExpress;

	/**
	 *收件人快递
	 */
	@ApiModelProperty(value="收件人快递")
	@ApiParam(value="收件人快递")
	@Column(name = "back_express")
	private String backExpress;

	/**
	 *收件人快递单号
	 */
	@ApiModelProperty(value="收件人快递单号")
	@ApiParam(value="收件人快递单号")
	@Column(name = "back_express_code")
	private String backExpressCode;

	/**
	 *0默认1申请取回2取回中3确认收货
	 */
	@ApiModelProperty(value="0默认1申请取回2取回中3确认收货")
	@ApiParam(value="0默认1申请取回2取回中3确认收货")
	@Column(name = "back_state")
	private Integer backState;

	/**
	 *邮寄鉴定时间
	 */
	@ApiModelProperty(value="邮寄鉴定时间")
	@ApiParam(value="邮寄鉴定时间")
	@Column(name = "post_time")
	private java.util.Date postTime;

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
	 *1上架0下架
	 */
	@ApiModelProperty(value="1上架0下架")
	@ApiParam(value="1上架0下架")
	@Column(name = "is_online")
	private Integer isOnline;

	/**
	 *综合利率(%)
	 */
	@ApiModelProperty(value="综合利率(%)")
	@ApiParam(value="综合利率(%)")
	@Column(name = "rate")
	private java.math.BigDecimal rate;

	/**
	 *利息利率(%)
	 */
	@ApiModelProperty(value="利息利率(%)")
	@ApiParam(value="利息利率(%)")
	@Column(name = "money_rate")
	private java.math.BigDecimal moneyRate;

	/**
	 *平台(平台发送机构)
	 */
	@ApiModelProperty(value="平台(平台发送机构)")
	@ApiParam(value="平台(平台发送机构)")
	@Column(name = "plat_user_name")
	private String platUserName;

	/**
	 *平台发货人
	 */
	@ApiModelProperty(value="平台发货人")
	@ApiParam(value="平台发货人")
	@Column(name = "plat_user_phone")
	private String platUserPhone;

	/**
	 *平台地址
	 */
	@ApiModelProperty(value="平台地址")
	@ApiParam(value="平台地址")
	@Column(name = "plat_user_address")
	private String platUserAddress;

	/**
	 *机构收件人名称
	 */
	@ApiModelProperty(value="机构收件人名称")
	@ApiParam(value="机构收件人名称")
	@Column(name = "org_user_name")
	private String orgUserName;

	/**
	 *机构收件人地址
	 */
	@ApiModelProperty(value="机构收件人地址")
	@ApiParam(value="机构收件人地址")
	@Column(name = "org_user_address")
	private String orgUserAddress;

	/**
	 *机构收件人联系电话
	 */
	@ApiModelProperty(value="机构收件人联系电话")
	@ApiParam(value="机构收件人联系电话")
	@Column(name = "org_user_phone")
	private String orgUserPhone;

	/**
	 *机构取回的状态(0默认1申请取回2取回中3确认收货)
	 */
	@ApiModelProperty(value="机构取回的状态(0默认1申请取回2取回中3确认收货)")
	@ApiParam(value="机构取回的状态(0默认1申请取回2取回中3确认收货)")
	@Column(name = "plat_org_state")
	private Integer platOrgState;

	/**
	 *机构取回，平台所拍摄的装箱视频
	 */
	@ApiModelProperty(value="机构取回，平台所拍摄的装箱视频")
	@ApiParam(value="机构取回，平台所拍摄的装箱视频")
	@Column(name = "plat_org_video")
	private String platOrgVideo;

	/**
	 *机构取回时 0未确认 1确认宝贝完好
	 */
	@ApiModelProperty(value="机构取回时 0未确认 1确认宝贝完好")
	@ApiParam(value="机构取回时 0未确认 1确认宝贝完好")
	@Column(name = "plat_org_verify")
	private Integer platOrgVerify;

	/**
	 *机构取回时的快递单号
	 */
	@ApiModelProperty(value="机构取回时的快递单号")
	@ApiParam(value="机构取回时的快递单号")
	@Column(name = "plat_org_express_code")
	private String platOrgExpressCode;

	/**
	 *宝贝绝当时间
	 */
	@ApiModelProperty(value="宝贝绝当时间")
	@ApiParam(value="宝贝绝当时间")
	@Column(name = "plat_org_time")
	private java.util.Date platOrgTime;

	/**
	 *鉴宝审核是否复审0:否 1有
	 */
	@ApiModelProperty(value="鉴宝审核是否复审0:否 1有")
	@ApiParam(value="鉴宝审核是否复审0:否 1有")
	@Column(name = "is_verify")
	private Integer isVerify;

	/**
	 *专家意见表id
	 */
	@ApiModelProperty(value="专家意见表id")
	@ApiParam(value="专家意见表id")
	@Column(name = "experter_info_id")
	private Integer experterInfoId;

	/**
	 *顺风保价
	 */
	@ApiModelProperty(value="顺风保价")
	@ApiParam(value="顺风保价")
	@Column(name = "sf_protect_price")
	private java.math.BigDecimal sfProtectPrice;

	/**
	 *是否寄拍 0未寄拍1寄拍
	 */
	@ApiModelProperty(value="是否寄拍 0未寄拍1寄拍")
	@ApiParam(value="是否寄拍 0未寄拍1寄拍")
	@Column(name = "is_sell")
	private Integer isSell;

	/**
	 *0未上架，1上架，2已卖出
	 */
	@ApiModelProperty(value="0未上架，1上架，2已卖出")
	@ApiParam(value="0未上架，1上架，2已卖出")
	@Column(name = "sell_status")
	private Integer sellStatus;

	/**
	 *寄卖审核 0不通过1通过2审核中
	 */
	@ApiModelProperty(value="寄卖审核 0不通过1通过2审核中")
	@ApiParam(value="寄卖审核 0不通过1通过2审核中")
	@Column(name = "sell_check")
	private Integer sellCheck;

	/**
	 *一口价
	 */
	@ApiModelProperty(value="一口价")
	@ApiParam(value="一口价")
	@Column(name = "sell_price")
	private java.math.BigDecimal sellPrice;

	/**
	 *寄拍商品描述
	 */
	@ApiModelProperty(value="寄拍商品描述")
	@ApiParam(value="寄拍商品描述")
	@Column(name = "sell_info")
	private String sellInfo;

	/**
	 *寄拍展示图片
	 */
	@ApiModelProperty(value="寄拍展示图片")
	@ApiParam(value="寄拍展示图片")
	@Column(name = "sell_imgs")
	private String sellImgs;

	/**
	 *寄拍展示视频
	 */
	@ApiModelProperty(value="寄拍展示视频")
	@ApiParam(value="寄拍展示视频")
	@Column(name = "sell_video")
	private String sellVideo;

	/**
	 *寄拍上架时间
	 */
	@ApiModelProperty(value="寄拍上架时间")
	@ApiParam(value="寄拍上架时间")
	@Column(name = "sell_start_time")
	private java.util.Date sellStartTime;

	/**
	 *寄拍到期时间
	 */
	@ApiModelProperty(value="寄拍到期时间")
	@ApiParam(value="寄拍到期时间")
	@Column(name = "sell_end_time")
	private java.util.Date sellEndTime;

	/**
	 *寄拍类别
	 */
	@ApiModelProperty(value="寄拍类别")
	@ApiParam(value="寄拍类别")
	@Column(name = "sell_pawn_code")
	private String sellPawnCode;

	/**
	 *评论数
	 */
	@ApiModelProperty(value="评论数")
	@ApiParam(value="评论数")
	@Column(name = "comment_count")
	private Integer commentCount;

	/**
	 *收藏数
	 */
	@ApiModelProperty(value="收藏数")
	@ApiParam(value="收藏数")
	@Column(name = "collect_count")
	private Integer collectCount;

	/**
	 *点赞数
	 */
	@ApiModelProperty(value="点赞数")
	@ApiParam(value="点赞数")
	@Column(name = "praise_count")
	private Integer praiseCount;

	/**
	 *点击数
	 */
	@ApiModelProperty(value="点击数")
	@ApiParam(value="点击数")
	@Column(name = "click_count")
	private Integer clickCount;

	/**
	 *举报数
	 */
	@ApiModelProperty(value="举报数")
	@ApiParam(value="举报数")
	@Column(name = "report_count")
	private Integer reportCount;

	/**
	 *是否赎回 0否 1是
	 */
	@ApiModelProperty(value="是否赎回 0否 1是")
	@ApiParam(value="是否赎回 0否 1是")
	@Column(name = "is_redeem")
	private Integer isRedeem;

	/**
	 *0未结算1结算
	 */
	@ApiModelProperty(value="0未结算1结算")
	@ApiParam(value="0未结算1结算")
	@Column(name = "settle_status")
	private Integer settleStatus;

	/**
	 *结算金额
	 */
	@ApiModelProperty(value="结算金额")
	@ApiParam(value="结算金额")
	@Column(name = "settle_money")
	private java.math.BigDecimal settleMoney;

	/**
	 *备注
	 */
	@ApiModelProperty(value="备注")
	@ApiParam(value="备注")
	@Column(name = "sell_remark")
	private String sellRemark;

	/**
	 *鉴定说明
	 */
	@ApiModelProperty(value="鉴定说明")
	@ApiParam(value="鉴定说明")
	@Column(name = "appraisal_dsc")
	private String appraisalDsc;

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

	public void setBelongId(Integer belongId) {
		this.belongId=belongId;
	}

	public Integer getBelongId() {
		return belongId;
	}

	public void setBelongType(Integer belongType) {
		this.belongType=belongType;
	}

	public Integer getBelongType() {
		return belongType;
	}

	public void setName(String name) {
		this.name=name == null ? name : name.trim();
	}

	public String getName() {
		return name;
	}

	public void setImages(String images) {
		this.images=images == null ? images : images.trim();
	}

	public String getImages() {
		return images;
	}

	public void setGoodsImgs(String goodsImgs) {
		this.goodsImgs=goodsImgs == null ? goodsImgs : goodsImgs.trim();
	}

	public String getGoodsImgs() {
		return goodsImgs;
	}

	public void setVideo(String video) {
		this.video=video == null ? video : video.trim();
	}

	public String getVideo() {
		return video;
	}

	public void setBuyTime(java.util.Date buyTime) {
		this.buyTime=buyTime;
	}

	public java.util.Date getBuyTime() {
		return buyTime;
	}

	public void setBuyPrice(java.math.BigDecimal buyPrice) {
		this.buyPrice=buyPrice;
	}

	public java.math.BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setInfo(String info) {
		this.info=info == null ? info : info.trim();
	}

	public String getInfo() {
		return info;
	}

	public void setContent(String content) {
		this.content=content == null ? content : content.trim();
	}

	public String getContent() {
		return content;
	}

	public void setCity(String city) {
		this.city=city == null ? city : city.trim();
	}

	public String getCity() {
		return city;
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

	public void setAuthenticateRequire(String authenticateRequire) {
		this.authenticateRequire=authenticateRequire == null ? authenticateRequire : authenticateRequire.trim();
	}

	public String getAuthenticateRequire() {
		return authenticateRequire;
	}

	public void setCateId(Integer cateId) {
		this.cateId=cateId;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateCode(String cateCode) {
		this.cateCode=cateCode == null ? cateCode : cateCode.trim();
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setAuthResult(Integer authResult) {
		this.authResult=authResult;
	}

	public Integer getAuthResult() {
		return authResult;
	}

	public void setAuthPriceTest(java.math.BigDecimal authPriceTest) {
		this.authPriceTest=authPriceTest;
	}

	public java.math.BigDecimal getAuthPriceTest() {
		return authPriceTest;
	}

	public void setAuthPrice(java.math.BigDecimal authPrice) {
		this.authPrice=authPrice;
	}

	public java.math.BigDecimal getAuthPrice() {
		return authPrice;
	}

	public void setBackImages(String backImages) {
		this.backImages=backImages == null ? backImages : backImages.trim();
	}

	public String getBackImages() {
		return backImages;
	}

	public void setBackVideo(String backVideo) {
		this.backVideo=backVideo == null ? backVideo : backVideo.trim();
	}

	public String getBackVideo() {
		return backVideo;
	}

	public void setOpenGoodsVideo(String openGoodsVideo) {
		this.openGoodsVideo=openGoodsVideo == null ? openGoodsVideo : openGoodsVideo.trim();
	}

	public String getOpenGoodsVideo() {
		return openGoodsVideo;
	}

	public void setPlatGoodsReceiveVideo(String platGoodsReceiveVideo) {
		this.platGoodsReceiveVideo=platGoodsReceiveVideo == null ? platGoodsReceiveVideo : platGoodsReceiveVideo.trim();
	}

	public String getPlatGoodsReceiveVideo() {
		return platGoodsReceiveVideo;
	}

	public void setPlatGoodsAuthVideo(String platGoodsAuthVideo) {
		this.platGoodsAuthVideo=platGoodsAuthVideo == null ? platGoodsAuthVideo : platGoodsAuthVideo.trim();
	}

	public String getPlatGoodsAuthVideo() {
		return platGoodsAuthVideo;
	}

	public void setNotVerifyReason(String notVerifyReason) {
		this.notVerifyReason=notVerifyReason == null ? notVerifyReason : notVerifyReason.trim();
	}

	public String getNotVerifyReason() {
		return notVerifyReason;
	}

	public void setGoVideo(String goVideo) {
		this.goVideo=goVideo == null ? goVideo : goVideo.trim();
	}

	public String getGoVideo() {
		return goVideo;
	}

	public void setGotoPawn(Integer gotoPawn) {
		this.gotoPawn=gotoPawn;
	}

	public Integer getGotoPawn() {
		return gotoPawn;
	}

	public void setGoSell(Integer goSell) {
		this.goSell=goSell;
	}

	public Integer getGoSell() {
		return goSell;
	}

	public void setLocation(Integer location) {
		this.location=location;
	}

	public Integer getLocation() {
		return location;
	}

	public void setPostState(Integer postState) {
		this.postState=postState;
	}

	public Integer getPostState() {
		return postState;
	}

	public void setPostUserName(String postUserName) {
		this.postUserName=postUserName == null ? postUserName : postUserName.trim();
	}

	public String getPostUserName() {
		return postUserName;
	}

	public void setPostUserPhone(String postUserPhone) {
		this.postUserPhone=postUserPhone == null ? postUserPhone : postUserPhone.trim();
	}

	public String getPostUserPhone() {
		return postUserPhone;
	}

	public void setPostExpress(String postExpress) {
		this.postExpress=postExpress == null ? postExpress : postExpress.trim();
	}

	public String getPostExpress() {
		return postExpress;
	}

	public void setPostExpressCode(String postExpressCode) {
		this.postExpressCode=postExpressCode == null ? postExpressCode : postExpressCode.trim();
	}

	public String getPostExpressCode() {
		return postExpressCode;
	}

	public void setBackUserName(String backUserName) {
		this.backUserName=backUserName == null ? backUserName : backUserName.trim();
	}

	public String getBackUserName() {
		return backUserName;
	}

	public void setBackUserPhone(String backUserPhone) {
		this.backUserPhone=backUserPhone == null ? backUserPhone : backUserPhone.trim();
	}

	public String getBackUserPhone() {
		return backUserPhone;
	}

	public void setBackUserExpress(String backUserExpress) {
		this.backUserExpress=backUserExpress == null ? backUserExpress : backUserExpress.trim();
	}

	public String getBackUserExpress() {
		return backUserExpress;
	}

	public void setBackExpress(String backExpress) {
		this.backExpress=backExpress == null ? backExpress : backExpress.trim();
	}

	public String getBackExpress() {
		return backExpress;
	}

	public void setBackExpressCode(String backExpressCode) {
		this.backExpressCode=backExpressCode == null ? backExpressCode : backExpressCode.trim();
	}

	public String getBackExpressCode() {
		return backExpressCode;
	}

	public void setBackState(Integer backState) {
		this.backState=backState;
	}

	public Integer getBackState() {
		return backState;
	}

	public void setPostTime(java.util.Date postTime) {
		this.postTime=postTime;
	}

	public java.util.Date getPostTime() {
		return postTime;
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

	public void setIsOnline(Integer isOnline) {
		this.isOnline=isOnline;
	}

	public Integer getIsOnline() {
		return isOnline;
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

	public void setPlatUserName(String platUserName) {
		this.platUserName=platUserName == null ? platUserName : platUserName.trim();
	}

	public String getPlatUserName() {
		return platUserName;
	}

	public void setPlatUserPhone(String platUserPhone) {
		this.platUserPhone=platUserPhone == null ? platUserPhone : platUserPhone.trim();
	}

	public String getPlatUserPhone() {
		return platUserPhone;
	}

	public void setPlatUserAddress(String platUserAddress) {
		this.platUserAddress=platUserAddress == null ? platUserAddress : platUserAddress.trim();
	}

	public String getPlatUserAddress() {
		return platUserAddress;
	}

	public void setOrgUserName(String orgUserName) {
		this.orgUserName=orgUserName == null ? orgUserName : orgUserName.trim();
	}

	public String getOrgUserName() {
		return orgUserName;
	}

	public void setOrgUserAddress(String orgUserAddress) {
		this.orgUserAddress=orgUserAddress == null ? orgUserAddress : orgUserAddress.trim();
	}

	public String getOrgUserAddress() {
		return orgUserAddress;
	}

	public void setOrgUserPhone(String orgUserPhone) {
		this.orgUserPhone=orgUserPhone == null ? orgUserPhone : orgUserPhone.trim();
	}

	public String getOrgUserPhone() {
		return orgUserPhone;
	}

	public void setPlatOrgState(Integer platOrgState) {
		this.platOrgState=platOrgState;
	}

	public Integer getPlatOrgState() {
		return platOrgState;
	}

	public void setPlatOrgVideo(String platOrgVideo) {
		this.platOrgVideo=platOrgVideo == null ? platOrgVideo : platOrgVideo.trim();
	}

	public String getPlatOrgVideo() {
		return platOrgVideo;
	}

	public void setPlatOrgVerify(Integer platOrgVerify) {
		this.platOrgVerify=platOrgVerify;
	}

	public Integer getPlatOrgVerify() {
		return platOrgVerify;
	}

	public void setPlatOrgExpressCode(String platOrgExpressCode) {
		this.platOrgExpressCode=platOrgExpressCode == null ? platOrgExpressCode : platOrgExpressCode.trim();
	}

	public String getPlatOrgExpressCode() {
		return platOrgExpressCode;
	}

	public void setPlatOrgTime(java.util.Date platOrgTime) {
		this.platOrgTime=platOrgTime;
	}

	public java.util.Date getPlatOrgTime() {
		return platOrgTime;
	}

	public void setIsVerify(Integer isVerify) {
		this.isVerify=isVerify;
	}

	public Integer getIsVerify() {
		return isVerify;
	}

	public void setExperterInfoId(Integer experterInfoId) {
		this.experterInfoId=experterInfoId;
	}

	public Integer getExperterInfoId() {
		return experterInfoId;
	}

	public void setSfProtectPrice(java.math.BigDecimal sfProtectPrice) {
		this.sfProtectPrice=sfProtectPrice;
	}

	public java.math.BigDecimal getSfProtectPrice() {
		return sfProtectPrice;
	}

	public void setIsSell(Integer isSell) {
		this.isSell=isSell;
	}

	public Integer getIsSell() {
		return isSell;
	}

	public void setSellStatus(Integer sellStatus) {
		this.sellStatus=sellStatus;
	}

	public Integer getSellStatus() {
		return sellStatus;
	}

	public void setSellCheck(Integer sellCheck) {
		this.sellCheck=sellCheck;
	}

	public Integer getSellCheck() {
		return sellCheck;
	}

	public void setSellPrice(java.math.BigDecimal sellPrice) {
		this.sellPrice=sellPrice;
	}

	public java.math.BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellInfo(String sellInfo) {
		this.sellInfo=sellInfo == null ? sellInfo : sellInfo.trim();
	}

	public String getSellInfo() {
		return sellInfo;
	}

	public void setSellImgs(String sellImgs) {
		this.sellImgs=sellImgs == null ? sellImgs : sellImgs.trim();
	}

	public String getSellImgs() {
		return sellImgs;
	}

	public void setSellVideo(String sellVideo) {
		this.sellVideo=sellVideo == null ? sellVideo : sellVideo.trim();
	}

	public String getSellVideo() {
		return sellVideo;
	}

	public void setSellStartTime(java.util.Date sellStartTime) {
		this.sellStartTime=sellStartTime;
	}

	public java.util.Date getSellStartTime() {
		return sellStartTime;
	}

	public void setSellEndTime(java.util.Date sellEndTime) {
		this.sellEndTime=sellEndTime;
	}

	public java.util.Date getSellEndTime() {
		return sellEndTime;
	}

	public void setSellPawnCode(String sellPawnCode) {
		this.sellPawnCode=sellPawnCode == null ? sellPawnCode : sellPawnCode.trim();
	}

	public String getSellPawnCode() {
		return sellPawnCode;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount=commentCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount=collectCount;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount=praiseCount;
	}

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount=clickCount;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setReportCount(Integer reportCount) {
		this.reportCount=reportCount;
	}

	public Integer getReportCount() {
		return reportCount;
	}

	public void setIsRedeem(Integer isRedeem) {
		this.isRedeem=isRedeem;
	}

	public Integer getIsRedeem() {
		return isRedeem;
	}

	public void setSettleStatus(Integer settleStatus) {
		this.settleStatus=settleStatus;
	}

	public Integer getSettleStatus() {
		return settleStatus;
	}

	public void setSettleMoney(java.math.BigDecimal settleMoney) {
		this.settleMoney=settleMoney;
	}

	public java.math.BigDecimal getSettleMoney() {
		return settleMoney;
	}

	public void setSellRemark(String sellRemark) {
		this.sellRemark=sellRemark == null ? sellRemark : sellRemark.trim();
	}

	public String getSellRemark() {
		return sellRemark;
	}

	public void setAppraisalDsc(String appraisalDsc) {
		this.appraisalDsc=appraisalDsc == null ? appraisalDsc : appraisalDsc.trim();
	}

	public String getAppraisalDsc() {
		return appraisalDsc;
	}

}
