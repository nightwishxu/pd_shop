package com.api.view.store;

import com.paidang.daoEx.model.GoodsAuctionEx;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 拍档商城商品详情
 * @author vonkira
 *
 */
@ApiModel
public class AppStoreGoodsDetail {

	@ApiModelProperty(value="商品id")
	private Integer id;
	@ApiModelProperty(value="商品封面")
	private String img;
	@ApiModelProperty(value="商品图片")
	private String images;
	@ApiModelProperty(value="商品图片宽度")
	private String width;
	@ApiModelProperty(value="商品图片长度")
	private String height;
	@ApiModelProperty(value="商品名称")
	private String title;
	@ApiModelProperty(value="商品名称--绝当品")
	private String property;
	@ApiModelProperty(value="鉴定价格")
	private String authPrice;
	@ApiModelProperty(value="当前售价")
	private String price;
	@ApiModelProperty(value="市场预估文本")
	private String description;
	@ApiModelProperty(value="商品描述")
	private String goodsDescription;

	@ApiModelProperty(value="收货地址code")
	private Integer addressCode;
	@ApiModelProperty(value="购买声明")
	private String declare;
	@ApiModelProperty(value=" 优惠券减免金额")
	private String couponPrice;
	@ApiModelProperty(value="支付方式(1:支付宝  2;微信)")
	private Integer payType;
	@ApiModelProperty(value="竞拍次数")
	private Integer count;
	@ApiModelProperty(value="是否是竞拍商品  0不是  1是")
	private Integer type;
	@ApiModelProperty(value="机构id")
	private Integer orgId;
	@ApiModelProperty(value="机构名称")
	private String orgName;
	@ApiModelProperty(value="机构简介")
	private String orgIntroduction;
	@ApiModelProperty(value="机构logo")
	private String orgLogo;
	@ApiModelProperty(value="商品来源2机构3服务商4供应商")
	private Integer source;
	@ApiModelProperty(value="轮播图视频")
	private String bannerVideo;
	@ApiModelProperty(value="轮播图视频封面")
	private String bannerVideoFace;

	@ApiModelProperty(value="分类code")
	private Integer cateCode;
	@ApiModelProperty(value="子分类")
	private String cateCodeSon;
	@ApiModelProperty(value="重量")
	private String weight;
	@ApiModelProperty(value="材质")
	private String material;
	@ApiModelProperty(value="主材")
	private String mainMaterial;
	@ApiModelProperty(value="辅材")
	private String otherMaterial;
	@ApiModelProperty(value="尺寸（长*宽*高）")
	private String ccAll;
	@ApiModelProperty(value="创作年代")
	private String createYear;
	@ApiModelProperty(value="品牌")
	private String brand;
	@ApiModelProperty(value="新旧程度")
	private String newPercent;
	@ApiModelProperty(value="题材")
	private String theme;
	@ApiModelProperty(value="样式")
	private String style;
	@ApiModelProperty(value="种地")
	private String materialName;
	@ApiModelProperty(value="文字描述")
	private String wordDescript;
	@ApiModelProperty(value="下架原因")
	private String reasonOfDismounting;


	/**
	 *简介
	 */
	@ApiModelProperty(value="简介")
	private String introduction;

	/**
	 *商品属性
	 */
	@ApiModelProperty(value="商品属性")
	private String goodsAttribute;

	/**
	 *1 一口价 2 竞拍
	 */
	@ApiModelProperty(value="1 一口价 2 竞拍")
	private Integer dealType;

	/**
	 *定时上架时间
	 */
	@ApiModelProperty(value="定时上架时间")
	private java.util.Date onlineTime;

	/**
	 *拍卖开始时间
	 */
	@ApiModelProperty(value="拍卖开始时间")
	private java.util.Date auctionStartTime;

	/**
	 *拍卖结束时间
	 */
	@ApiModelProperty(value="拍卖结束时间")
	private java.util.Date auctionEndTime;

	/**
	 *起拍价
	 */
	@ApiModelProperty(value="起拍价")
	private java.math.BigDecimal startPrice;

	/**
	 *加价幅度
	 */
	@ApiModelProperty(value="加价幅度")
	private java.math.BigDecimal raisePriceRange;

	/**
	 *标签
	 */
	@ApiModelProperty(value="标签")
	private String labels;

	/**
	 *最新出价id
	 */
	@ApiModelProperty(value="最新出价id")
	private Integer maxAutionId;

	/**
	 *竞拍(最新出价)
	 */
	@ApiModelProperty(value="竞拍(最新出价)")
	private java.math.BigDecimal maxAuction;

	@ApiModelProperty(value="竞拍出价列表")
	private List<GoodsAuctionEx> goodsAuctionList;

	@ApiModelProperty(value="竞拍商品距离竞拍结束时间(秒)")
	private Long leftTime;


	public String getBannerVideo() {
		return bannerVideo;
	}

	public void setBannerVideo(String bannerVideo) {
		this.bannerVideo = bannerVideo;
	}

	public String getBannerVideoFace() {
		return bannerVideoFace;
	}

	public void setBannerVideoFace(String bannerVideoFace) {
		this.bannerVideoFace = bannerVideoFace;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgIntroduction() {
		return orgIntroduction;
	}

	public void setOrgIntroduction(String orgIntroduction) {
		this.orgIntroduction = orgIntroduction;
	}

	public String getOrgLogo() {
		return orgLogo;
	}

	public void setOrgLogo(String orgLogo) {
		this.orgLogo = orgLogo;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getAuthPrice() {
		return authPrice;
	}

	public void setAuthPrice(String authPrice) {
		this.authPrice = authPrice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public Integer getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(Integer addressCode) {
		this.addressCode = addressCode;
	}

	public String getDeclare() {
		return declare;
	}

	public void setDeclare(String declare) {
		this.declare = declare;
	}

	public String getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(String couponPrice) {
		this.couponPrice = couponPrice;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getCateCode() {
		return cateCode;
	}

	public void setCateCode(Integer cateCode) {
		this.cateCode = cateCode;
	}

	public String getCateCodeSon() {
		return cateCodeSon;
	}

	public void setCateCodeSon(String cateCodeSon) {
		this.cateCodeSon = cateCodeSon;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMainMaterial() {
		return mainMaterial;
	}

	public void setMainMaterial(String mainMaterial) {
		this.mainMaterial = mainMaterial;
	}

	public String getOtherMaterial() {
		return otherMaterial;
	}

	public void setOtherMaterial(String otherMaterial) {
		this.otherMaterial = otherMaterial;
	}

	public String getCcAll() {
		return ccAll;
	}

	public void setCcAll(String ccAll) {
		this.ccAll = ccAll;
	}

	public String getCreateYear() {
		return createYear;
	}

	public void setCreateYear(String createYear) {
		this.createYear = createYear;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getNewPercent() {
		return newPercent;
	}

	public void setNewPercent(String newPercent) {
		this.newPercent = newPercent;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getWordDescript() {
		return wordDescript;
	}

	public void setWordDescript(String wordDescript) {
		this.wordDescript = wordDescript;
	}

	public String getReasonOfDismounting() {
		return reasonOfDismounting;
	}

	public void setReasonOfDismounting(String reasonOfDismounting) {
		this.reasonOfDismounting = reasonOfDismounting;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getGoodsAttribute() {
		return goodsAttribute;
	}

	public void setGoodsAttribute(String goodsAttribute) {
		this.goodsAttribute = goodsAttribute;
	}

	public Integer getDealType() {
		return dealType;
	}

	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Date getAuctionStartTime() {
		return auctionStartTime;
	}

	public void setAuctionStartTime(Date auctionStartTime) {
		this.auctionStartTime = auctionStartTime;
	}

	public Date getAuctionEndTime() {
		return auctionEndTime;
	}

	public void setAuctionEndTime(Date auctionEndTime) {
		this.auctionEndTime = auctionEndTime;
	}

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}

	public BigDecimal getRaisePriceRange() {
		return raisePriceRange;
	}

	public void setRaisePriceRange(BigDecimal raisePriceRange) {
		this.raisePriceRange = raisePriceRange;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public Integer getMaxAutionId() {
		return maxAutionId;
	}

	public void setMaxAutionId(Integer maxAutionId) {
		this.maxAutionId = maxAutionId;
	}

	public BigDecimal getMaxAuction() {
		return maxAuction;
	}

	public void setMaxAuction(BigDecimal maxAuction) {
		this.maxAuction = maxAuction;
	}

	public List<GoodsAuctionEx> getGoodsAuctionList() {
		return goodsAuctionList;
	}

	public void setGoodsAuctionList(List<GoodsAuctionEx> goodsAuctionList) {
		this.goodsAuctionList = goodsAuctionList;
	}

	public Long getLeftTime() {
		Date date = new Date();
		if (getAuctionEndTime()!=null && getAuctionStartTime()!=null &&
		date.compareTo(getAuctionStartTime())>=0 && date.compareTo(getAuctionEndTime())<=0){
			long l = (getAuctionEndTime().getTime() - System.currentTimeMillis()) / 1000;
			return l;
		}
		return leftTime;
	}

	public void setLeftTime(Long leftTime) {
		this.leftTime = leftTime;
	}
}
