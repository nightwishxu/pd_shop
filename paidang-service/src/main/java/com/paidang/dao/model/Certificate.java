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
@Table(name="p_certificate")
public class Certificate implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *名称
	 */
	@ApiModelProperty(value="名称")
	@ApiParam(value="名称")
	@Column(name = "name")
	private String name;

	/**
	 *编号
	 */
	@ApiModelProperty(value="编号")
	@ApiParam(value="编号")
	@Column(name = "code")
	private String code;

	/**
	 *尺寸--长
	 */
	@ApiModelProperty(value="尺寸--长")
	@ApiParam(value="尺寸--长")
	@Column(name = "length")
	private String length;

	/**
	 *尺寸--宽
	 */
	@ApiModelProperty(value="尺寸--宽")
	@ApiParam(value="尺寸--宽")
	@Column(name = "width")
	private String width;

	/**
	 *尺寸--高
	 */
	@ApiModelProperty(value="尺寸--高")
	@ApiParam(value="尺寸--高")
	@Column(name = "height")
	private String height;

	/**
	 *重量
	 */
	@ApiModelProperty(value="重量")
	@ApiParam(value="重量")
	@Column(name = "weight")
	private String weight;

	/**
	 *材质
	 */
	@ApiModelProperty(value="材质")
	@ApiParam(value="材质")
	@Column(name = "material")
	private String material;

	/**
	 *主体材质
	 */
	@ApiModelProperty(value="主体材质")
	@ApiParam(value="主体材质")
	@Column(name = "main_material")
	private String mainMaterial;

	/**
	 *其他辅材
	 */
	@ApiModelProperty(value="其他辅材")
	@ApiParam(value="其他辅材")
	@Column(name = "other_material")
	private String otherMaterial;

	/**
	 *创作年代
	 */
	@ApiModelProperty(value="创作年代")
	@ApiParam(value="创作年代")
	@Column(name = "create_year")
	private String createYear;

	/**
	 *其他
	 */
	@ApiModelProperty(value="其他")
	@ApiParam(value="其他")
	@Column(name = "other")
	private String other;

	/**
	 *图片概览
	 */
	@ApiModelProperty(value="图片概览")
	@ApiParam(value="图片概览")
	@Column(name = "imgs")
	private String imgs;

	/**
	 *市场流通性0-5分
	 */
	@ApiModelProperty(value="市场流通性0-5分")
	@ApiParam(value="市场流通性0-5分")
	@Column(name = "market_liquidity")
	private Integer marketLiquidity;

	/**
	 *价值稳定性0-5分
	 */
	@ApiModelProperty(value="价值稳定性0-5分")
	@ApiParam(value="价值稳定性0-5分")
	@Column(name = "value_stability")
	private Integer valueStability;

	/**
	 *材质易损性
	 */
	@ApiModelProperty(value="材质易损性")
	@ApiParam(value="材质易损性")
	@Column(name = "material_vulnerability")
	private Integer materialVulnerability;

	/**
	 *存放条件
	 */
	@ApiModelProperty(value="存放条件")
	@ApiParam(value="存放条件")
	@Column(name = "storage_condition")
	private String storageCondition;

	/**
	 *肉眼可见缺陷
	 */
	@ApiModelProperty(value="肉眼可见缺陷")
	@ApiParam(value="肉眼可见缺陷")
	@Column(name = "naked_eye_defect")
	private String nakedEyeDefect;

	/**
	 *金融记录
	 */
	@ApiModelProperty(value="金融记录")
	@ApiParam(value="金融记录")
	@Column(name = "finance_log")
	private String financeLog;

	/**
	 *其他事项
	 */
	@ApiModelProperty(value="其他事项")
	@ApiParam(value="其他事项")
	@Column(name = "other_business")
	private String otherBusiness;

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
	@Column(name = "user_goods_id")
	private Integer userGoodsId;

	/**
	 *鉴定托底价
	 */
	@ApiModelProperty(value="鉴定托底价")
	@ApiParam(value="鉴定托底价")
	@Column(name = "price")
	private java.math.BigDecimal price;

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

	public void setName(String name) {
		this.name=name == null ? name : name.trim();
	}

	public String getName() {
		return name;
	}

	public void setCode(String code) {
		this.code=code == null ? code : code.trim();
	}

	public String getCode() {
		return code;
	}

	public void setLength(String length) {
		this.length=length == null ? length : length.trim();
	}

	public String getLength() {
		return length;
	}

	public void setWidth(String width) {
		this.width=width == null ? width : width.trim();
	}

	public String getWidth() {
		return width;
	}

	public void setHeight(String height) {
		this.height=height == null ? height : height.trim();
	}

	public String getHeight() {
		return height;
	}

	public void setWeight(String weight) {
		this.weight=weight == null ? weight : weight.trim();
	}

	public String getWeight() {
		return weight;
	}

	public void setMaterial(String material) {
		this.material=material == null ? material : material.trim();
	}

	public String getMaterial() {
		return material;
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

	public void setOther(String other) {
		this.other=other == null ? other : other.trim();
	}

	public String getOther() {
		return other;
	}

	public void setImgs(String imgs) {
		this.imgs=imgs == null ? imgs : imgs.trim();
	}

	public String getImgs() {
		return imgs;
	}

	public void setMarketLiquidity(Integer marketLiquidity) {
		this.marketLiquidity=marketLiquidity;
	}

	public Integer getMarketLiquidity() {
		return marketLiquidity;
	}

	public void setValueStability(Integer valueStability) {
		this.valueStability=valueStability;
	}

	public Integer getValueStability() {
		return valueStability;
	}

	public void setMaterialVulnerability(Integer materialVulnerability) {
		this.materialVulnerability=materialVulnerability;
	}

	public Integer getMaterialVulnerability() {
		return materialVulnerability;
	}

	public void setStorageCondition(String storageCondition) {
		this.storageCondition=storageCondition == null ? storageCondition : storageCondition.trim();
	}

	public String getStorageCondition() {
		return storageCondition;
	}

	public void setNakedEyeDefect(String nakedEyeDefect) {
		this.nakedEyeDefect=nakedEyeDefect == null ? nakedEyeDefect : nakedEyeDefect.trim();
	}

	public String getNakedEyeDefect() {
		return nakedEyeDefect;
	}

	public void setFinanceLog(String financeLog) {
		this.financeLog=financeLog == null ? financeLog : financeLog.trim();
	}

	public String getFinanceLog() {
		return financeLog;
	}

	public void setOtherBusiness(String otherBusiness) {
		this.otherBusiness=otherBusiness == null ? otherBusiness : otherBusiness.trim();
	}

	public String getOtherBusiness() {
		return otherBusiness;
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

	public void setUserGoodsId(Integer userGoodsId) {
		this.userGoodsId=userGoodsId;
	}

	public Integer getUserGoodsId() {
		return userGoodsId;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price=price;
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setAppraisalDsc(String appraisalDsc) {
		this.appraisalDsc=appraisalDsc == null ? appraisalDsc : appraisalDsc.trim();
	}

	public String getAppraisalDsc() {
		return appraisalDsc;
	}

}
