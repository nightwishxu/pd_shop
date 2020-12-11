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
@Table(name="p_goods_auction")
public class GoodsAuction implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *p_goods表主键id
	 */
	@ApiModelProperty(value="p_goods表主键id")
	@ApiParam(value="p_goods表主键id")
	@Column(name = "goods_id")
	private Integer goodsId;

	/**
	 *p_goods_auction_online_id
	 */
	@ApiModelProperty(value="p_goods_auction_online_id")
	@ApiParam(value="p_goods_auction_online_id")
	@Column(name = "online_log_id")
	private Integer onlineLogId;

	/**
	 *用户
	 */
	@ApiModelProperty(value="用户")
	@ApiParam(value="用户")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *出价
	 */
	@ApiModelProperty(value="出价")
	@ApiParam(value="出价")
	@Column(name = "price")
	private java.math.BigDecimal price;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "create_time")
	private java.util.Date createTime;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId=goodsId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setOnlineLogId(Integer onlineLogId) {
		this.onlineLogId=onlineLogId;
	}

	public Integer getOnlineLogId() {
		return onlineLogId;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price=price;
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

}
