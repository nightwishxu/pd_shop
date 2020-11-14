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
@Table(name="p_goods_attribute")
public class GoodsAttribute implements Serializable {

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
	@Column(name = "attribute")
	private String attribute;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "name")
	private String name;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "belongTo")
	private Integer belongTo;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setAttribute(String attribute) {
		this.attribute=attribute == null ? attribute : attribute.trim();
	}

	public String getAttribute() {
		return attribute;
	}

	public void setName(String name) {
		this.name=name == null ? name : name.trim();
	}

	public String getName() {
		return name;
	}

	public void setBelongTo(Integer belongTo) {
		this.belongTo=belongTo;
	}

	public Integer getBelongTo() {
		return belongTo;
	}

}
