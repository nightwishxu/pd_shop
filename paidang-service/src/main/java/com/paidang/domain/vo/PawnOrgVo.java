package com.paidang.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

public class PawnOrgVo {


    /**
     *机构编号
     */
    @ApiModelProperty(value="机构编号")
    @ApiParam(value="机构编号")
    @Column(name = "id")
    private Integer id;



    /**
     *1典当机构2服务商  6商户（个人企业认证）
     */
    @ApiModelProperty(value="1典当机构2服务商  6商户（个人企业认证）")
    @ApiParam(value="1典当机构2服务商  6商户（个人企业认证）")
    @Column(name = "type")
    private Integer type;


    /**
     *机构名称
     */
    @ApiModelProperty(value="机构名称")
    @ApiParam(value="机构名称")
    @Column(name = "name")
    private String name;

    /**
     *机构法人
     */
    @ApiModelProperty(value="机构法人")
    @ApiParam(value="机构法人")
    @Column(name = "legal_person")
    private String legalPerson;

    /**
     *注册资金
     */
    @ApiModelProperty(value="注册资金")
    @ApiParam(value="注册资金")
    @Column(name = "registered_capital")
    private String registeredCapital;

    /**
     *机构地址
     */
    @ApiModelProperty(value="机构地址")
    @ApiParam(value="机构地址")
    @Column(name = "adress")
    private String adress;

    /**
     *工商许可证号
     */
    @ApiModelProperty(value="工商许可证号")
    @ApiParam(value="工商许可证号")
    @Column(name = "business_license_code")
    private String businessLicenseCode;

    /**
     *工商许可证图片
     */
    @ApiModelProperty(value="工商许可证图片")
    @ApiParam(value="工商许可证图片")
    @Column(name = "business_license")
    private String businessLicense;


    /**
     *机构电话
     */
    @ApiModelProperty(value="机构电话")
    @ApiParam(value="机构电话")
    @Column(name = "phone")
    private String phone;

    /**
     *余额
     */
    @ApiModelProperty(value="余额")
    @ApiParam(value="余额")
    @Column(name = "balance")
    private java.math.BigDecimal balance;

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
     *1启动0禁用2待审核3审核失败
     */
    @ApiModelProperty(value="1启动0禁用2待审核3审核失败")
    @ApiParam(value="1启动0禁用2待审核3审核失败")
    @Column(name = "state")
    private Integer state;


    /**


    /**
     *公司简介
     */
    @ApiModelProperty(value="公司简介")
    @ApiParam(value="公司简介")
    @Column(name = "introduction")
    private String introduction;

    /**
     *公司环境图片，多张逗号隔开
     */
    @ApiModelProperty(value="公司环境图片，多张逗号隔开")
    @ApiParam(value="公司环境图片，多张逗号隔开")
    @Column(name = "org_images")
    private String orgImages;

    /**
     *机构LOGO图片
     */
    @ApiModelProperty(value="机构LOGO图片")
    @ApiParam(value="机构LOGO图片")
    @Column(name = "org_logo")
    private String orgLogo;


    /**
     *标签
     */
    @ApiModelProperty(value="标签")
    @ApiParam(value="标签")
    @Column(name = "labels")
    private String labels;

    /**
     *积分
     */
    @ApiModelProperty(value="积分")
    @ApiParam(value="积分")
    @Column(name = "integral")
    private java.math.BigDecimal integral;

    /**
     *评分
     */
    @ApiModelProperty(value="评分")
    @ApiParam(value="评分")
    @Column(name = "score")
    private java.math.BigDecimal score;

    /**
     *评论数量
     */
    @ApiModelProperty(value="评论数量")
    @ApiParam(value="评论数量")
    @Column(name = "comment_count")
    private Integer commentCount;

    /**
     *物流评分
     */
    @ApiModelProperty(value="物流评分")
    @ApiParam(value="物流评分")
    @Column(name = "express_score")
    private java.math.BigDecimal expressScore;

    /**
     *服务评分
     */
    @ApiModelProperty(value="服务评分")
    @ApiParam(value="服务评分")
    @Column(name = "service_score")
    private java.math.BigDecimal serviceScore;



    /**
     *典当许可证
     */
    @ApiModelProperty(value="典当许可证")
    @ApiParam(value="典当许可证")
    @Column(name = "pawn_exequatur")
    private String pawnExequatur;

    /**
     *其他辅助文件
     */
    @ApiModelProperty(value="其他辅助文件")
    @ApiParam(value="其他辅助文件")
    @Column(name = "other_file")
    private String otherFile;



    /**
     *0 未缴纳店铺保证金 1缴纳
     */
    @ApiModelProperty(value="0 未缴纳店铺保证金 1缴纳")
    @ApiParam(value="0 未缴纳店铺保证金 1缴纳")
    @Column(name = "is_store_deposit")
    private Integer isStoreDeposit;

    /**
     *保证金金额
     */
    @ApiModelProperty(value="保证金金额")
    @ApiParam(value="保证金金额")
    @Column(name = "deposit_amount")
    private java.math.BigDecimal depositAmount;

    /**
     *签名
     */
    @ApiModelProperty(value="签名")
    @ApiParam(value="签名")
    @Column(name = "signature")
    private String signature;

    /**
     *店铺联系人
     */
    @ApiModelProperty(value="店铺联系人")
    @ApiParam(value="店铺联系人")
    @Column(name = "store_contacts")
    private String storeContacts;

    /**
     *店铺联系电话
     */
    @ApiModelProperty(value="店铺联系电话")
    @ApiParam(value="店铺联系电话")
    @Column(name = "store_phone")
    private String storePhone;


    /**
     *固定电话
     */
    @ApiModelProperty(value="固定电话")
    @ApiParam(value="固定电话")
    @Column(name = "land_line_phone")
    private String landLinePhone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getBusinessLicenseCode() {
        return businessLicenseCode;
    }

    public void setBusinessLicenseCode(String businessLicenseCode) {
        this.businessLicenseCode = businessLicenseCode;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getOrgImages() {
        return orgImages;
    }

    public void setOrgImages(String orgImages) {
        this.orgImages = orgImages;
    }

    public String getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(String orgLogo) {
        this.orgLogo = orgLogo;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public BigDecimal getExpressScore() {
        return expressScore;
    }

    public void setExpressScore(BigDecimal expressScore) {
        this.expressScore = expressScore;
    }

    public BigDecimal getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(BigDecimal serviceScore) {
        this.serviceScore = serviceScore;
    }

    public String getPawnExequatur() {
        return pawnExequatur;
    }

    public void setPawnExequatur(String pawnExequatur) {
        this.pawnExequatur = pawnExequatur;
    }

    public String getOtherFile() {
        return otherFile;
    }

    public void setOtherFile(String otherFile) {
        this.otherFile = otherFile;
    }

    public Integer getIsStoreDeposit() {
        return isStoreDeposit;
    }

    public void setIsStoreDeposit(Integer isStoreDeposit) {
        this.isStoreDeposit = isStoreDeposit;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getStoreContacts() {
        return storeContacts;
    }

    public void setStoreContacts(String storeContacts) {
        this.storeContacts = storeContacts;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getLandLinePhone() {
        return landLinePhone;
    }

    public void setLandLinePhone(String landLinePhone) {
        this.landLinePhone = landLinePhone;
    }
}
