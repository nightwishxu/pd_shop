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
@Table(name="p_experter_info")
public class ExperterInfo implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *标题
	 */
	@ApiModelProperty(value="标题")
	@ApiParam(value="标题")
	@Column(name = "title")
	private String title;

	/**
	 *专家id
	 */
	@ApiModelProperty(value="专家id")
	@ApiParam(value="专家id")
	@Column(name = "experter_id")
	private Integer experterId;

	/**
	 *商品id
	 */
	@ApiModelProperty(value="商品id")
	@ApiParam(value="商品id")
	@Column(name = "goods_id")
	private Integer goodsId;

	/**
	 *专家是否已经评定 0未评定 1已评定
	 */
	@ApiModelProperty(value="专家是否已经评定 0未评定 1已评定")
	@ApiParam(value="专家是否已经评定 0未评定 1已评定")
	@Column(name = "state")
	private Integer state;

	/**
	 *专家意见
	 */
	@ApiModelProperty(value="专家意见")
	@ApiParam(value="专家意见")
	@Column(name = "info")
	private String info;

	/**
	 *名称
	 */
	@ApiModelProperty(value="名称")
	@ApiParam(value="名称")
	@Column(name = "name")
	private String name;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *尺寸
	 */
	@ApiModelProperty(value="尺寸")
	@ApiParam(value="尺寸")
	@Column(name = "cc")
	private String cc;

	/**
	 *重量
	 */
	@ApiModelProperty(value="重量")
	@ApiParam(value="重量")
	@Column(name = "weight")
	private String weight;

	/**
	 *主材质
	 */
	@ApiModelProperty(value="主材质")
	@ApiParam(value="主材质")
	@Column(name = "main_material")
	private String mainMaterial;

	/**
	 *副材质
	 */
	@ApiModelProperty(value="副材质")
	@ApiParam(value="副材质")
	@Column(name = "other_material")
	private String otherMaterial;

	/**
	 *年代
	 */
	@ApiModelProperty(value="年代")
	@ApiParam(value="年代")
	@Column(name = "create_year")
	private String createYear;

	/**
	 *市场流通性
	 */
	@ApiModelProperty(value="市场流通性")
	@ApiParam(value="市场流通性")
	@Column(name = "market_circulation")
	private String marketCirculation;

	/**
	 *价值稳定性
	 */
	@ApiModelProperty(value="价值稳定性")
	@ApiParam(value="价值稳定性")
	@Column(name = "stability_of_price")
	private String stabilityOfPrice;

	/**
	 *材质易损性
	 */
	@ApiModelProperty(value="材质易损性")
	@ApiParam(value="材质易损性")
	@Column(name = "material_vulnerable")
	private String materialVulnerable;

	/**
	 *鉴定托低价
	 */
	@ApiModelProperty(value="鉴定托低价")
	@ApiParam(value="鉴定托低价")
	@Column(name = "experter_price")
	private String experterPrice;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title=title == null ? title : title.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setExperterId(Integer experterId) {
		this.experterId=experterId;
	}

	public Integer getExperterId() {
		return experterId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId=goodsId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setState(Integer state) {
		this.state=state;
	}

	public Integer getState() {
		return state;
	}

	public void setInfo(String info) {
		this.info=info == null ? info : info.trim();
	}

	public String getInfo() {
		return info;
	}

	public void setName(String name) {
		this.name=name == null ? name : name.trim();
	}

	public String getName() {
		return name;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCc(String cc) {
		this.cc=cc == null ? cc : cc.trim();
	}

	public String getCc() {
		return cc;
	}

	public void setWeight(String weight) {
		this.weight=weight == null ? weight : weight.trim();
	}

	public String getWeight() {
		return weight;
	}

	public void setMainMaterial(String mainMaterial) {
		this.mainMaterial=mainMaterial == null ? mainMaterial : mainMaterial.trim();
	}

	public String getMainMaterial() {
		return mainMaterial;
	}

	public void setOtherMaterial(String otherMaterial) {
		this.otherMaterial=otherMaterial == null ? otherMaterial : otherMaterial.trim();
	}

	public String getOtherMaterial() {
		return otherMaterial;
	}

	public void setCreateYear(String createYear) {
		this.createYear=createYear == null ? createYear : createYear.trim();
	}

	public String getCreateYear() {
		return createYear;
	}

	public void setMarketCirculation(String marketCirculation) {
		this.marketCirculation=marketCirculation == null ? marketCirculation : marketCirculation.trim();
	}

	public String getMarketCirculation() {
		return marketCirculation;
	}

	public void setStabilityOfPrice(String stabilityOfPrice) {
		this.stabilityOfPrice=stabilityOfPrice == null ? stabilityOfPrice : stabilityOfPrice.trim();
	}

	public String getStabilityOfPrice() {
		return stabilityOfPrice;
	}

	public void setMaterialVulnerable(String materialVulnerable) {
		this.materialVulnerable=materialVulnerable == null ? materialVulnerable : materialVulnerable.trim();
	}

	public String getMaterialVulnerable() {
		return materialVulnerable;
	}

	public void setExperterPrice(String experterPrice) {
		this.experterPrice=experterPrice == null ? experterPrice : experterPrice.trim();
	}

	public String getExperterPrice() {
		return experterPrice;
	}

}
