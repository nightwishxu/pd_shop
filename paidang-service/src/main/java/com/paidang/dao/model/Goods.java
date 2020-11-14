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
@Table(name="p_goods")
public class Goods implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *1平台2机构3服务商4供应商5寄拍6个人7企业8龙城画库
	 */
	@ApiModelProperty(value="1平台2机构3服务商4供应商5寄拍6个人7企业8龙城画库")
	@ApiParam(value="1平台2机构3服务商4供应商5寄拍6个人7企业8龙城画库")
	@Column(name = "source")
	private Integer source;

	/**
	 *1新品2绝当品
	 */
	@ApiModelProperty(value="1新品2绝当品")
	@ApiParam(value="1新品2绝当品")
	@Column(name = "type")
	private Integer type;

	/**
	 *商品所属人，只针对认证过后商家的商品
	 */
	@ApiModelProperty(value="商品所属人，只针对认证过后商家的商品")
	@ApiParam(value="商品所属人，只针对认证过后商家的商品")
	@Column(name = "goods_owner")
	private Integer goodsOwner;

	/**
	 *分类code
	 */
	@ApiModelProperty(value="分类code,101:玉翠珠宝,102:工艺作品,103:文玩杂项,104:紫砂陶瓷,105:钱币邮票,106:书画篆刻,107:花鸟文娱,108:其他")
	@ApiParam(value="分类code")
	@Column(name = "cate_code")
	private Integer cateCode;

	/**
	 *子分类
	 */
	@ApiModelProperty(value="子分类")
	@ApiParam(value="子分类")
	@Column(name = "cate_code_son")
	private String cateCodeSon;

	/**
	 *机构id,默认0
	 */
	@ApiModelProperty(value="机构id,默认0")
	@ApiParam(value="机构id,默认0")
	@Column(name = "org_id")
	private Integer orgId;

	/**
	 *绝当品id（p_user_goods用户藏品表的主键id）
	 */
	@ApiModelProperty(value="绝当品id（p_user_goods用户藏品表的主键id）")
	@ApiParam(value="绝当品id（p_user_goods用户藏品表的主键id）")
	@Column(name = "goods_id")
	private Integer goodsId;

	/**
	 *商品名称
	 */
	@ApiModelProperty(value="商品名称")
	@ApiParam(value="商品名称")
	@Column(name = "name")
	private String name;

	/**
	 *成本（当type=2绝当品，该字段表示该绝当品的起拍价）
	 */
	@ApiModelProperty(value="成本（当type=2绝当品，该字段表示该绝当品的起拍价）")
	@ApiParam(value="成本（当type=2绝当品，该字段表示该绝当品的起拍价）")
	@Column(name = "cost")
	private java.math.BigDecimal cost;

	/**
	 *售价
	 */
	@ApiModelProperty(value="售价")
	@ApiParam(value="售价")
	@Column(name = "price")
	private java.math.BigDecimal price;

	/**
	 *库存
	 */
	@ApiModelProperty(value="库存")
	@ApiParam(value="库存")
	@Column(name = "total")
	private Integer total;

	/**
	 *已售
	 */
	@ApiModelProperty(value="已售")
	@ApiParam(value="已售")
	@Column(name = "sold_out")
	private Integer soldOut;

	/**
	 *1上架0下架-1删除 2新增待上架3定时上架
	 */
	@ApiModelProperty(value="1上架0下架-1删除 2新增待上架3定时上架")
	@ApiParam(value="1上架0下架-1删除 2新增待上架3定时上架")
	@Column(name = "is_online")
	private Integer isOnline;

	/**
	 *下架原因
	 */
	@ApiModelProperty(value="下架原因")
	@ApiParam(value="下架原因")
	@Column(name = "reason_of_dismounting")
	private String reasonOfDismounting;

	/**
	 *1审核中2通过3不通过
	 */
	@ApiModelProperty(value="1审核中2通过3不通过")
	@ApiParam(value="1审核中2通过3不通过")
	@Column(name = "is_verfiy")
	private Integer isVerfiy;

	/**
	 *图片
	 */
	@ApiModelProperty(value="图片")
	@ApiParam(value="图片")
	@Column(name = "imgs")
	private String imgs;

	/**
	 *商品封面
	 */
	@ApiModelProperty(value="商品封面")
	@ApiParam(value="商品封面")
	@Column(name = "img")
	private String img;

	/**
	 *封面宽度
	 */
	@ApiModelProperty(value="封面宽度")
	@ApiParam(value="封面宽度")
	@Column(name = "width")
	private String width;

	/**
	 *封面高度
	 */
	@ApiModelProperty(value="封面高度")
	@ApiParam(value="封面高度")
	@Column(name = "height")
	private String height;

	/**
	 *商品描述
	 */
	@ApiModelProperty(value="商品描述")
	@ApiParam(value="商品描述")
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
	 *更新时间
	 */
	@ApiModelProperty(value="更新时间")
	@ApiParam(value="更新时间")
	@Column(name = "modify_time")
	private java.util.Date modifyTime;

	/**
	 *(针对竞拍)- 0已失效 1有效
	 */
	@ApiModelProperty(value="(针对竞拍)- 0已失效 1有效")
	@ApiParam(value="(针对竞拍)- 0已失效 1有效")
	@Column(name = "state")
	private Integer state;

	/**
	 *最新出价id
	 */
	@ApiModelProperty(value="最新出价id")
	@ApiParam(value="最新出价id")
	@Column(name = "max_aution_id")
	private Integer maxAutionId;

	/**
	 *竞拍(最新出价)
	 */
	@ApiModelProperty(value="竞拍(最新出价)")
	@ApiParam(value="竞拍(最新出价)")
	@Column(name = "max_auction")
	private java.math.BigDecimal maxAuction;

	/**
	 *中标人
	 */
	@ApiModelProperty(value="中标人")
	@ApiParam(value="中标人")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *平台服务费率
	 */
	@ApiModelProperty(value="平台服务费率")
	@ApiParam(value="平台服务费率")
	@Column(name = "platform_rate")
	private java.math.BigDecimal platformRate;

	/**
	 *平台服务费
	 */
	@ApiModelProperty(value="平台服务费")
	@ApiParam(value="平台服务费")
	@Column(name = "platform_money")
	private java.math.BigDecimal platformMoney;

	/**
	 *0未缴纳1已缴纳平台服务费
	 */
	@ApiModelProperty(value="0未缴纳1已缴纳平台服务费")
	@ApiParam(value="0未缴纳1已缴纳平台服务费")
	@Column(name = "platform_state")
	private Integer platformState;

	/**
	 *排序(倒序)
	 */
	@ApiModelProperty(value="排序(倒序)")
	@ApiParam(value="排序(倒序)")
	@Column(name = "sort_order")
	private Integer sortOrder;

	/**
	 *是否设置为推荐 0不设置 1设置
	 */
	@ApiModelProperty(value="是否设置为推荐 0不设置 1设置")
	@ApiParam(value="是否设置为推荐 0不设置 1设置")
	@Column(name = "is_suggest")
	private Integer isSuggest;

	/**
	 *审核不通过原因
	 */
	@ApiModelProperty(value="审核不通过原因")
	@ApiParam(value="审核不通过原因")
	@Column(name = "refuse_info")
	private String refuseInfo;

	/**
	 *规格
	 */
	@ApiModelProperty(value="规格")
	@ApiParam(value="规格")
	@Column(name = "spec")
	private String spec;

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
	 *尺寸--宽
	 */
	@ApiModelProperty(value="尺寸--宽")
	@ApiParam(value="尺寸--宽")
	@Column(name = "cc_width")
	private String ccWidth;

	/**
	 *尺寸--高
	 */
	@ApiModelProperty(value="尺寸--高")
	@ApiParam(value="尺寸--高")
	@Column(name = "cc_height")
	private String ccHeight;

	/**
	 *尺寸（长*宽*高）
	 */
	@ApiModelProperty(value="尺寸（长*宽*高）")
	@ApiParam(value="尺寸（长*宽*高）")
	@Column(name = "cc_all")
	private String ccAll;

	/**
	 *主材
	 */
	@ApiModelProperty(value="主材")
	@ApiParam(value="主材")
	@Column(name = "main_material")
	private String mainMaterial;

	/**
	 *辅材
	 */
	@ApiModelProperty(value="辅材")
	@ApiParam(value="辅材")
	@Column(name = "other_material")
	private String otherMaterial;

	/**
	 *尺寸--长
	 */
	@ApiModelProperty(value="尺寸--长")
	@ApiParam(value="尺寸--长")
	@Column(name = "cc_length")
	private String ccLength;

	/**
	 *创作年代
	 */
	@ApiModelProperty(value="创作年代")
	@ApiParam(value="创作年代")
	@Column(name = "create_year")
	private String createYear;

	/**
	 *品牌
	 */
	@ApiModelProperty(value="品牌")
	@ApiParam(value="品牌")
	@Column(name = "brand")
	private String brand;

	/**
	 *新旧程度
	 */
	@ApiModelProperty(value="新旧程度")
	@ApiParam(value="新旧程度")
	@Column(name = "new_percent")
	private String newPercent;

	/**
	 *题材
	 */
	@ApiModelProperty(value="题材")
	@ApiParam(value="题材")
	@Column(name = "theme")
	private String theme;

	/**
	 *样式
	 */
	@ApiModelProperty(value="样式")
	@ApiParam(value="样式")
	@Column(name = "style")
	private String style;

	/**
	 *种地
	 */
	@ApiModelProperty(value="种地")
	@ApiParam(value="种地")
	@Column(name = "material_name")
	private String materialName;

	/**
	 *文字描述
	 */
	@ApiModelProperty(value="文字描述")
	@ApiParam(value="文字描述")
	@Column(name = "word_descript")
	private String wordDescript;

	/**
	 *轮播图视频
	 */
	@ApiModelProperty(value="轮播图视频")
	@ApiParam(value="轮播图视频")
	@Column(name = "banner_video")
	private String bannerVideo;

	/**
	 *轮播图视频封面
	 */
	@ApiModelProperty(value="轮播图视频封面")
	@ApiParam(value="轮播图视频封面")
	@Column(name = "banner_video_face")
	private String bannerVideoFace;

	/**
	 *简介
	 */
	@ApiModelProperty(value="简介")
	@ApiParam(value="简介")
	@Column(name = "introduction")
	private String introduction;

	/**
	 *商品属性
	 */
	@ApiModelProperty(value="商品属性")
	@ApiParam(value="商品属性")
	@Column(name = "goods_attribute")
	private String goodsAttribute;

	/**
	 *1 一口价 2 竞拍
	 */
	@ApiModelProperty(value="1 一口价 2 竞拍")
	@ApiParam(value="1 一口价 2 竞拍")
	@Column(name = "deal_type")
	private Integer dealType;

	/**
	 *定时上架时间
	 */
	@ApiModelProperty(value="定时上架时间")
	@ApiParam(value="定时上架时间")
	@Column(name = "online_time")
	private java.util.Date onlineTime;

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

	/**
	 *起拍价
	 */
	@ApiModelProperty(value="起拍价")
	@ApiParam(value="起拍价")
	@Column(name = "start_price")
	private java.math.BigDecimal startPrice;

	/**
	 *加价幅度
	 */
	@ApiModelProperty(value="加价幅度")
	@ApiParam(value="加价幅度")
	@Column(name = "raise_price_range")
	private java.math.BigDecimal raisePriceRange;

	/**
	 *标签
	 */
	@ApiModelProperty(value="标签")
	@ApiParam(value="标签")
	@Column(name = "labels")
	private String labels;

	/**
	 *访问量
	 */
	@ApiModelProperty(value="访问量")
	@ApiParam(value="访问量")
	@Column(name = "pv")
	private Integer pv;

	/**
	 *独立用户
	 */
	@ApiModelProperty(value="独立用户")
	@ApiParam(value="独立用户")
	@Column(name = "uv")
	private Integer uv;

	/**
	 *新用户
	 */
	@ApiModelProperty(value="新用户")
	@ApiParam(value="新用户")
	@Column(name = "nv")
	private Integer nv;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setSource(Integer source) {
		this.source=source;
	}

	public Integer getSource() {
		return source;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setGoodsOwner(Integer goodsOwner) {
		this.goodsOwner=goodsOwner;
	}

	public Integer getGoodsOwner() {
		return goodsOwner;
	}

	public void setCateCode(Integer cateCode) {
		this.cateCode=cateCode;
	}

	public Integer getCateCode() {
		return cateCode;
	}

	public void setCateCodeSon(String cateCodeSon) {
		this.cateCodeSon=cateCodeSon == null ? cateCodeSon : cateCodeSon.trim();
	}

	public String getCateCodeSon() {
		return cateCodeSon;
	}

	public void setOrgId(Integer orgId) {
		this.orgId=orgId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId=goodsId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setName(String name) {
		this.name=name == null ? name : name.trim();
	}

	public String getName() {
		return name;
	}

	public void setCost(java.math.BigDecimal cost) {
		this.cost=cost;
	}

	public java.math.BigDecimal getCost() {
		return cost;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price=price;
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setTotal(Integer total) {
		this.total=total;
	}

	public Integer getTotal() {
		return total;
	}

	public void setSoldOut(Integer soldOut) {
		this.soldOut=soldOut;
	}

	public Integer getSoldOut() {
		return soldOut;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline=isOnline;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setReasonOfDismounting(String reasonOfDismounting) {
		this.reasonOfDismounting=reasonOfDismounting == null ? reasonOfDismounting : reasonOfDismounting.trim();
	}

	public String getReasonOfDismounting() {
		return reasonOfDismounting;
	}

	public void setIsVerfiy(Integer isVerfiy) {
		this.isVerfiy=isVerfiy;
	}

	public Integer getIsVerfiy() {
		return isVerfiy;
	}

	public void setImgs(String imgs) {
		this.imgs=imgs == null ? imgs : imgs.trim();
	}

	public String getImgs() {
		return imgs;
	}

	public void setImg(String img) {
		this.img=img == null ? img : img.trim();
	}

	public String getImg() {
		return img;
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

	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime=modifyTime;
	}

	public java.util.Date getModifyTime() {
		return modifyTime;
	}

	public void setState(Integer state) {
		this.state=state;
	}

	public Integer getState() {
		return state;
	}

	public void setMaxAutionId(Integer maxAutionId) {
		this.maxAutionId=maxAutionId;
	}

	public Integer getMaxAutionId() {
		return maxAutionId;
	}

	public void setMaxAuction(java.math.BigDecimal maxAuction) {
		this.maxAuction=maxAuction;
	}

	public java.math.BigDecimal getMaxAuction() {
		return maxAuction;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setPlatformRate(java.math.BigDecimal platformRate) {
		this.platformRate=platformRate;
	}

	public java.math.BigDecimal getPlatformRate() {
		return platformRate;
	}

	public void setPlatformMoney(java.math.BigDecimal platformMoney) {
		this.platformMoney=platformMoney;
	}

	public java.math.BigDecimal getPlatformMoney() {
		return platformMoney;
	}

	public void setPlatformState(Integer platformState) {
		this.platformState=platformState;
	}

	public Integer getPlatformState() {
		return platformState;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder=sortOrder;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setIsSuggest(Integer isSuggest) {
		this.isSuggest=isSuggest;
	}

	public Integer getIsSuggest() {
		return isSuggest;
	}

	public void setRefuseInfo(String refuseInfo) {
		this.refuseInfo=refuseInfo == null ? refuseInfo : refuseInfo.trim();
	}

	public String getRefuseInfo() {
		return refuseInfo;
	}

	public void setSpec(String spec) {
		this.spec=spec == null ? spec : spec.trim();
	}

	public String getSpec() {
		return spec;
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

	public void setCcWidth(String ccWidth) {
		this.ccWidth=ccWidth == null ? ccWidth : ccWidth.trim();
	}

	public String getCcWidth() {
		return ccWidth;
	}

	public void setCcHeight(String ccHeight) {
		this.ccHeight=ccHeight == null ? ccHeight : ccHeight.trim();
	}

	public String getCcHeight() {
		return ccHeight;
	}

	public void setCcAll(String ccAll) {
		this.ccAll=ccAll == null ? ccAll : ccAll.trim();
	}

	public String getCcAll() {
		return ccAll;
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

	public void setCcLength(String ccLength) {
		this.ccLength=ccLength == null ? ccLength : ccLength.trim();
	}

	public String getCcLength() {
		return ccLength;
	}

	public void setCreateYear(String createYear) {
		this.createYear=createYear == null ? createYear : createYear.trim();
	}

	public String getCreateYear() {
		return createYear;
	}

	public void setBrand(String brand) {
		this.brand=brand == null ? brand : brand.trim();
	}

	public String getBrand() {
		return brand;
	}

	public void setNewPercent(String newPercent) {
		this.newPercent=newPercent == null ? newPercent : newPercent.trim();
	}

	public String getNewPercent() {
		return newPercent;
	}

	public void setTheme(String theme) {
		this.theme=theme == null ? theme : theme.trim();
	}

	public String getTheme() {
		return theme;
	}

	public void setStyle(String style) {
		this.style=style == null ? style : style.trim();
	}

	public String getStyle() {
		return style;
	}

	public void setMaterialName(String materialName) {
		this.materialName=materialName == null ? materialName : materialName.trim();
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setWordDescript(String wordDescript) {
		this.wordDescript=wordDescript == null ? wordDescript : wordDescript.trim();
	}

	public String getWordDescript() {
		return wordDescript;
	}

	public void setBannerVideo(String bannerVideo) {
		this.bannerVideo=bannerVideo == null ? bannerVideo : bannerVideo.trim();
	}

	public String getBannerVideo() {
		return bannerVideo;
	}

	public void setBannerVideoFace(String bannerVideoFace) {
		this.bannerVideoFace=bannerVideoFace == null ? bannerVideoFace : bannerVideoFace.trim();
	}

	public String getBannerVideoFace() {
		return bannerVideoFace;
	}

	public void setIntroduction(String introduction) {
		this.introduction=introduction == null ? introduction : introduction.trim();
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setGoodsAttribute(String goodsAttribute) {
		this.goodsAttribute=goodsAttribute == null ? goodsAttribute : goodsAttribute.trim();
	}

	public String getGoodsAttribute() {
		return goodsAttribute;
	}

	public void setDealType(Integer dealType) {
		this.dealType=dealType;
	}

	public Integer getDealType() {
		return dealType;
	}

	public void setOnlineTime(java.util.Date onlineTime) {
		this.onlineTime=onlineTime;
	}

	public java.util.Date getOnlineTime() {
		return onlineTime;
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

	public void setStartPrice(java.math.BigDecimal startPrice) {
		this.startPrice=startPrice;
	}

	public java.math.BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setRaisePriceRange(java.math.BigDecimal raisePriceRange) {
		this.raisePriceRange=raisePriceRange;
	}

	public java.math.BigDecimal getRaisePriceRange() {
		return raisePriceRange;
	}

	public void setLabels(String labels) {
		this.labels=labels == null ? labels : labels.trim();
	}

	public String getLabels() {
		return labels;
	}

	public void setPv(Integer pv) {
		this.pv=pv;
	}

	public Integer getPv() {
		return pv;
	}

	public void setUv(Integer uv) {
		this.uv=uv;
	}

	public Integer getUv() {
		return uv;
	}

	public void setNv(Integer nv) {
		this.nv=nv;
	}

	public Integer getNv() {
		return nv;
	}

}
