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
@Table(name="p_platform_balance_log")
public class PlatformBalanceLog implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *金额
	 */
	@ApiModelProperty(value="金额")
	@ApiParam(value="金额")
	@Column(name = "amount")
	private java.math.BigDecimal amount;

	/**
	 *项目
	 */
	@ApiModelProperty(value="项目")
	@ApiParam(value="项目")
	@Column(name = "item")
	private String item;

	/**
	 *描述
	 */
	@ApiModelProperty(value="描述")
	@ApiParam(value="描述")
	@Column(name = "info")
	private String info;

	/**
	 *创建时间
	 */
	@ApiModelProperty(value="创建时间")
	@ApiParam(value="创建时间")
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *1-典当 2-续当
	 */
	@ApiModelProperty(value="1-典当 2-续当")
	@ApiParam(value="1-典当 2-续当")
	@Column(name = "type")
	private Integer type;

	/**
	 *典当/续当 id
	 */
	@ApiModelProperty(value="典当/续当 id")
	@ApiParam(value="典当/续当 id")
	@Column(name = "fid")
	private Integer fid;

	/**
	 *支付流水表id
	 */
	@ApiModelProperty(value="支付流水表id")
	@ApiParam(value="支付流水表id")
	@Column(name = "pay_log_id")
	private String payLogId;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setAmount(java.math.BigDecimal amount) {
		this.amount=amount;
	}

	public java.math.BigDecimal getAmount() {
		return amount;
	}

	public void setItem(String item) {
		this.item=item == null ? item : item.trim();
	}

	public String getItem() {
		return item;
	}

	public void setInfo(String info) {
		this.info=info == null ? info : info.trim();
	}

	public String getInfo() {
		return info;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setFid(Integer fid) {
		this.fid=fid;
	}

	public Integer getFid() {
		return fid;
	}

	public void setPayLogId(String payLogId) {
		this.payLogId=payLogId == null ? payLogId : payLogId.trim();
	}

	public String getPayLogId() {
		return payLogId;
	}

}
