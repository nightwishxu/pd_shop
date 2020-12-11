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
@Table(name="p_goods_auction_online_log")
public class GoodsAuctionOnlineLog implements Serializable {

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
	@Column(name = "goods_id")
	private Integer goodsId;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "status")
	private Integer status;

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
	@Column(name = "modify_account")
	private String modifyAccount;

	/**
	 *拍卖开始时间
	 */
	@ApiModelProperty(value="拍卖开始时间")
	@ApiParam(value="拍卖开始时间")
	@Column(name = "auction_start_time")
	private java.util.Date auctionStartTime;

	/**
	 *拍卖结束时间
	 */
	@ApiModelProperty(value="拍卖结束时间")
	@ApiParam(value="拍卖结束时间")
	@Column(name = "auction_end_time")
	private java.util.Date auctionEndTime;

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

	public void setStatus(Integer status) {
		this.status=status;
	}

	public Integer getStatus() {
		return status;
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

	public void setCreateAccount(String createAccount) {
		this.createAccount=createAccount == null ? createAccount : createAccount.trim();
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setModifyAccount(String modifyAccount) {
		this.modifyAccount=modifyAccount == null ? modifyAccount : modifyAccount.trim();
	}

	public String getModifyAccount() {
		return modifyAccount;
	}

	public void setAuctionStartTime(java.util.Date auctionStartTime) {
		this.auctionStartTime=auctionStartTime;
	}

	public java.util.Date getAuctionStartTime() {
		return auctionStartTime;
	}

	public void setAuctionEndTime(java.util.Date auctionEndTime) {
		this.auctionEndTime=auctionEndTime;
	}

	public java.util.Date getAuctionEndTime() {
		return auctionEndTime;
	}

}
