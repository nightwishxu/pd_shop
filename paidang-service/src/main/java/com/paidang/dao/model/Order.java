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
@Table(name="p_order")
public class Order implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *订单号
	 */
	@ApiModelProperty(value="订单号")
	@ApiParam(value="订单号")
	@Column(name = "code")
	private String code;

	/**
	 *用户
	 */
	@ApiModelProperty(value="用户")
	@ApiParam(value="用户")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *p_goods表主键id
	 */
	@ApiModelProperty(value="p_goods表主键id")
	@ApiParam(value="p_goods表主键id")
	@Column(name = "goods_id")
	private Integer goodsId;

	/**
	 *商品名称
	 */
	@ApiModelProperty(value="商品名称")
	@ApiParam(value="商品名称")
	@Column(name = "goods_name")
	private String goodsName;

	/**
	 *商品封面
	 */
	@ApiModelProperty(value="商品封面")
	@ApiParam(value="商品封面")
	@Column(name = "goods_img")
	private String goodsImg;

	/**
	 *1平台2机构3服务商4供应商5寄拍
	 */
	@ApiModelProperty(value="1平台2机构3服务商4供应商5寄拍")
	@ApiParam(value="1平台2机构3服务商4供应商5寄拍")
	@Column(name = "goods_source")
	private Integer goodsSource;

	/**
	 *0平台
	 */
	@ApiModelProperty(value="0平台")
	@ApiParam(value="0平台")
	@Column(name = "org_id")
	private Integer orgId;

	/**
	 *商品价格
	 */
	@ApiModelProperty(value="商品价格")
	@ApiParam(value="商品价格")
	@Column(name = "goods_price")
	private java.math.BigDecimal goodsPrice;

	/**
	 *商品成本
	 */
	@ApiModelProperty(value="商品成本")
	@ApiParam(value="商品成本")
	@Column(name = "goods_cost")
	private java.math.BigDecimal goodsCost;

	/**
	 *订单总金额
	 */
	@ApiModelProperty(value="订单总金额")
	@ApiParam(value="订单总金额")
	@Column(name = "total_price")
	private java.math.BigDecimal totalPrice;

	/**
	 *订单金额
	 */
	@ApiModelProperty(value="订单金额")
	@ApiParam(value="订单金额")
	@Column(name = "price")
	private java.math.BigDecimal price;

	/**
	 *物品打包视频
	 */
	@ApiModelProperty(value="物品打包视频")
	@ApiParam(value="物品打包视频")
	@Column(name = "video")
	private String video;

	/**
	 *订单状态-1已取消1待付款2已付款3已发货4确认收货5已评价
	 */
	@ApiModelProperty(value="订单状态-1已取消1待付款2已付款3已发货4确认收货5已评价")
	@ApiParam(value="订单状态-1已取消1待付款2已付款3已发货4确认收货5已评价")
	@Column(name = "state")
	private Integer state;

	/**
	 *退款状态 0未退款 1申请退款 2同意退款 3提交单号 4已退款 5拒绝退款
	 */
	@ApiModelProperty(value="退款状态 0未退款 1申请退款 2同意退款 3提交单号 4已退款 5拒绝退款")
	@ApiParam(value="退款状态 0未退款 1申请退款 2同意退款 3提交单号 4已退款 5拒绝退款")
	@Column(name = "ref_state")
	private Integer refState;

	/**
	 *支付流水
	 */
	@ApiModelProperty(value="支付流水")
	@ApiParam(value="支付流水")
	@Column(name = "pay_log_id")
	private String payLogId;

	/**
	 *1已结算0未结算
	 */
	@ApiModelProperty(value="1已结算0未结算")
	@ApiParam(value="1已结算0未结算")
	@Column(name = "is_balance")
	private Integer isBalance;

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

	/**
	 *结算日期
	 */
	@ApiModelProperty(value="结算日期")
	@ApiParam(value="结算日期")
	@Column(name = "balance_time")
	private java.util.Date balanceTime;

	/**
	 *支付日期
	 */
	@ApiModelProperty(value="支付日期")
	@ApiParam(value="支付日期")
	@Column(name = "pay_time")
	private java.util.Date payTime;

	/**
	 *收件人姓名
	 */
	@ApiModelProperty(value="收件人姓名")
	@ApiParam(value="收件人姓名")
	@Column(name = "ship_user")
	private String shipUser;

	/**
	 *快递公司
	 */
	@ApiModelProperty(value="快递公司")
	@ApiParam(value="快递公司")
	@Column(name = "ship_firm")
	private String shipFirm;

	/**
	 *快递编号
	 */
	@ApiModelProperty(value="快递编号")
	@ApiParam(value="快递编号")
	@Column(name = "ship_code")
	private String shipCode;

	/**
	 *收件人地址
	 */
	@ApiModelProperty(value="收件人地址")
	@ApiParam(value="收件人地址")
	@Column(name = "ship_address")
	private String shipAddress;

	/**
	 *收件人电话
	 */
	@ApiModelProperty(value="收件人电话")
	@ApiParam(value="收件人电话")
	@Column(name = "ship_phone")
	private String shipPhone;

	/**
	 *用户优惠券id
	 */
	@ApiModelProperty(value="用户优惠券id")
	@ApiParam(value="用户优惠券id")
	@Column(name = "coupon_id")
	private Integer couponId;

	/**
	 *优惠券金额
	 */
	@ApiModelProperty(value="优惠券金额")
	@ApiParam(value="优惠券金额")
	@Column(name = "coupon_value")
	private java.math.BigDecimal couponValue;

	/**
	 *满xx减xx
	 */
	@ApiModelProperty(value="满xx减xx")
	@ApiParam(value="满xx减xx")
	@Column(name = "coupon_info")
	private String couponInfo;

	/**
	 *0未删除1已删除
	 */
	@ApiModelProperty(value="0未删除1已删除")
	@ApiParam(value="0未删除1已删除")
	@Column(name = "is_del")
	private Integer isDel;

	/**
	 *退款原因
	 */
	@ApiModelProperty(value="退款原因")
	@ApiParam(value="退款原因")
	@Column(name = "refund_reason")
	private String refundReason;

	/**
	 *退款审核不通过理由
	 */
	@ApiModelProperty(value="退款审核不通过理由")
	@ApiParam(value="退款审核不通过理由")
	@Column(name = "refund_not_verify_reason")
	private String refundNotVerifyReason;

	/**
	 *退款回寄单号
	 */
	@ApiModelProperty(value="退款回寄单号")
	@ApiParam(value="退款回寄单号")
	@Column(name = "back_code")
	private String backCode;

	/**
	 *退款回寄地址
	 */
	@ApiModelProperty(value="退款回寄地址")
	@ApiParam(value="退款回寄地址")
	@Column(name = "back_address")
	private String backAddress;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "back_user")
	private String backUser;

	/**
	 *退款回寄联系电话
	 */
	@ApiModelProperty(value="退款回寄联系电话")
	@ApiParam(value="退款回寄联系电话")
	@Column(name = "back_phone")
	private String backPhone;

	/**
	 *退款物流状态 0初始化 1后台已签收
	 */
	@ApiModelProperty(value="退款物流状态 0初始化 1后台已签收")
	@ApiParam(value="退款物流状态 0初始化 1后台已签收")
	@Column(name = "back_state")
	private Integer backState;

	/**
	 *支付方式 1微信 2支付宝 3线下
	 */
	@ApiModelProperty(value="支付方式 1微信 2支付宝 3线下")
	@ApiParam(value="支付方式 1微信 2支付宝 3线下")
	@Column(name = "pay_type")
	private Integer payType;

	/**
	 *评论状态0未评价，1已评价
	 */
	@ApiModelProperty(value="评论状态0未评价，1已评价")
	@ApiParam(value="评论状态0未评价，1已评价")
	@Column(name = "comment_state")
	private Integer commentState;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setCode(String code) {
		this.code=code == null ? code : code.trim();
	}

	public String getCode() {
		return code;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId=goodsId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName=goodsName == null ? goodsName : goodsName.trim();
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg=goodsImg == null ? goodsImg : goodsImg.trim();
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsSource(Integer goodsSource) {
		this.goodsSource=goodsSource;
	}

	public Integer getGoodsSource() {
		return goodsSource;
	}

	public void setOrgId(Integer orgId) {
		this.orgId=orgId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setGoodsPrice(java.math.BigDecimal goodsPrice) {
		this.goodsPrice=goodsPrice;
	}

	public java.math.BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsCost(java.math.BigDecimal goodsCost) {
		this.goodsCost=goodsCost;
	}

	public java.math.BigDecimal getGoodsCost() {
		return goodsCost;
	}

	public void setTotalPrice(java.math.BigDecimal totalPrice) {
		this.totalPrice=totalPrice;
	}

	public java.math.BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price=price;
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setVideo(String video) {
		this.video=video == null ? video : video.trim();
	}

	public String getVideo() {
		return video;
	}

	public void setState(Integer state) {
		this.state=state;
	}

	public Integer getState() {
		return state;
	}

	public void setRefState(Integer refState) {
		this.refState=refState;
	}

	public Integer getRefState() {
		return refState;
	}

	public void setPayLogId(String payLogId) {
		this.payLogId=payLogId == null ? payLogId : payLogId.trim();
	}

	public String getPayLogId() {
		return payLogId;
	}

	public void setIsBalance(Integer isBalance) {
		this.isBalance=isBalance;
	}

	public Integer getIsBalance() {
		return isBalance;
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

	public void setBalanceTime(java.util.Date balanceTime) {
		this.balanceTime=balanceTime;
	}

	public java.util.Date getBalanceTime() {
		return balanceTime;
	}

	public void setPayTime(java.util.Date payTime) {
		this.payTime=payTime;
	}

	public java.util.Date getPayTime() {
		return payTime;
	}

	public void setShipUser(String shipUser) {
		this.shipUser=shipUser == null ? shipUser : shipUser.trim();
	}

	public String getShipUser() {
		return shipUser;
	}

	public void setShipFirm(String shipFirm) {
		this.shipFirm=shipFirm == null ? shipFirm : shipFirm.trim();
	}

	public String getShipFirm() {
		return shipFirm;
	}

	public void setShipCode(String shipCode) {
		this.shipCode=shipCode == null ? shipCode : shipCode.trim();
	}

	public String getShipCode() {
		return shipCode;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress=shipAddress == null ? shipAddress : shipAddress.trim();
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipPhone(String shipPhone) {
		this.shipPhone=shipPhone == null ? shipPhone : shipPhone.trim();
	}

	public String getShipPhone() {
		return shipPhone;
	}

	public void setCouponId(Integer couponId) {
		this.couponId=couponId;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponValue(java.math.BigDecimal couponValue) {
		this.couponValue=couponValue;
	}

	public java.math.BigDecimal getCouponValue() {
		return couponValue;
	}

	public void setCouponInfo(String couponInfo) {
		this.couponInfo=couponInfo == null ? couponInfo : couponInfo.trim();
	}

	public String getCouponInfo() {
		return couponInfo;
	}

	public void setIsDel(Integer isDel) {
		this.isDel=isDel;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason=refundReason == null ? refundReason : refundReason.trim();
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundNotVerifyReason(String refundNotVerifyReason) {
		this.refundNotVerifyReason=refundNotVerifyReason == null ? refundNotVerifyReason : refundNotVerifyReason.trim();
	}

	public String getRefundNotVerifyReason() {
		return refundNotVerifyReason;
	}

	public void setBackCode(String backCode) {
		this.backCode=backCode == null ? backCode : backCode.trim();
	}

	public String getBackCode() {
		return backCode;
	}

	public void setBackAddress(String backAddress) {
		this.backAddress=backAddress == null ? backAddress : backAddress.trim();
	}

	public String getBackAddress() {
		return backAddress;
	}

	public void setBackUser(String backUser) {
		this.backUser=backUser == null ? backUser : backUser.trim();
	}

	public String getBackUser() {
		return backUser;
	}

	public void setBackPhone(String backPhone) {
		this.backPhone=backPhone == null ? backPhone : backPhone.trim();
	}

	public String getBackPhone() {
		return backPhone;
	}

	public void setBackState(Integer backState) {
		this.backState=backState;
	}

	public Integer getBackState() {
		return backState;
	}

	public void setPayType(Integer payType) {
		this.payType=payType;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setCommentState(Integer commentState) {
		this.commentState=commentState;
	}

	public Integer getCommentState() {
		return commentState;
	}

}
