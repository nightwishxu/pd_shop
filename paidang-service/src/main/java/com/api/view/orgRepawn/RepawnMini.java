package com.api.view.orgRepawn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Lee on 2017/9/26.
 * 续当列表里每个元素返回model
 */
@ApiModel
public class RepawnMini {

    @ApiModelProperty("典当id")
    private String id;

    @ApiModelProperty("续当id")
    private String repawnId;

    @ApiModelProperty("当品id")
    private String goodsid;

    @ApiModelProperty("当号")
    private String pawnCode;

    @ApiModelProperty("当品名称")
    private String goodsName;

    @ApiModelProperty("当品图片")
    private String images;

    @ApiModelProperty("鉴定价格")
    private String authPrice;

    @ApiModelProperty("已贷额度：本金")
    private String loanMoney;

    @ApiModelProperty("已发放当金")
    private String userMoney;

    @ApiModelProperty("是否显示【确认收款】按钮  1-显示 0-否")
    private String showButton;

    @ApiModelProperty("是否显示【完善当票】按钮  1-显示 0-否")
    private String completeTicketButton;

    @ApiModelProperty("是否显示【签署】按钮  1-显示 0-否")
    private String signButton;

    @ApiModelProperty("典当/续当日期至：当repawnId为空时，该字段应呈现为【典当日期至yyyy-MM-dd HH：mm】，否则【续当日期至yyyy-MM-dd HH：mm】，其中yyyy-MM-dd HH：mm为本字段值 ")
    private String dateRepawnTo;

    @ApiModelProperty(value = "项目编号")

    private String projectCode;

    @ApiModelProperty(value = "用户 0未生成合同1待签署2已签署3拒签4作废")
    private Integer userStatus;

    @ApiModelProperty(value = "机构 0未生成合同1待签署2已签署3拒签4作废")
    private Integer orgStatus;

    @ApiModelProperty(value = "最新续当id")
    private Integer  lastPawnContinueId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepawnId() {
        return repawnId;
    }

    public void setRepawnId(String repawnId) {
        this.repawnId = repawnId;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getPawnCode() {
        return pawnCode;
    }

    public void setPawnCode(String pawnCode) {
        this.pawnCode = pawnCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getAuthPrice() {
        return authPrice;
    }

    public void setAuthPrice(String authPrice) {
        this.authPrice = authPrice;
    }

    public String getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(String loanMoney) {
        this.loanMoney = loanMoney;
    }

    public String getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(String userMoney) {
        this.userMoney = userMoney;
    }

    public String getShowButton() {
        return showButton;
    }

    public void setShowButton(String showButton) {
        this.showButton = showButton;
    }

    public String getDateRepawnTo() {
        return dateRepawnTo;
    }

    public void setDateRepawnTo(String dateRepawnTo) {
        this.dateRepawnTo = dateRepawnTo;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(Integer orgStatus) {
        this.orgStatus = orgStatus;
    }

    public String getCompleteTicketButton() {
        return completeTicketButton;
    }

    public void setCompleteTicketButton(String completeTicketButton) {
        this.completeTicketButton = completeTicketButton;
    }

    public String getSignButton() {
        return signButton;
    }

    public void setSignButton(String signButton) {
        this.signButton = signButton;
    }

    public Integer getLastPawnContinueId() {
        return lastPawnContinueId;
    }

    public void setLastPawnContinueId(Integer lastPawnContinueId) {
        this.lastPawnContinueId = lastPawnContinueId;
    }
}
