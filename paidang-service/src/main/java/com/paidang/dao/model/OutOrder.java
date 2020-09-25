package com.paidang.dao.model;

import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.Column;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
/**
 *
 */
@ApiModel
@Table(name="p_out_order")
public class OutOrder implements Serializable {

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
	 *购物车订单支付同一id
	 */
	@ApiModelProperty(value="购物车订单支付同一id")
	@ApiParam(value="购物车订单支付同一id")
	@Column(name = "out_order_id")
	private String outOrderId;

	/**
	 *订单id
	 */
	@ApiModelProperty(value="订单id")
	@ApiParam(value="订单id")
	@Column(name = "order_id")
	private String orderId;

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

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId=outOrderId == null ? outOrderId : outOrderId.trim();
	}

	public String getOutOrderId() {
		return outOrderId;
	}

	public void setOrderId(String orderId) {
		this.orderId=orderId == null ? orderId : orderId.trim();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

}
