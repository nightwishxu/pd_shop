package com.api.view.myPawn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 赎当详情
 * @author vonkira
 *
 */
@ApiModel
public class AppMyPawnGetBack {
    @ApiModelProperty(value="id")
    private Integer id;
    @ApiModelProperty(value="当品名称")
    private String name;
    @ApiModelProperty(value="鉴定价")
    private String authPrice;
    @ApiModelProperty(value="当号")
    private String pawnticketCode;
    @ApiModelProperty(value="图片")
    private String images;
    @ApiModelProperty(value="已发放当金")
    private String money;
    @ApiModelProperty(value="上期典当期限")
    private Integer pawnTime;
    @ApiModelProperty(value="综合利率")
    private String rate;
    @ApiModelProperty(value="利息利率")
    private String redeemRate;
    @ApiModelProperty(value="滞纳金利率")
    private String monetRate;
    @ApiModelProperty(value="贷款日期")
    private String beginDate;
    @ApiModelProperty(value="应还款日期")
    private String endDate;
    @ApiModelProperty(value="逾期天数")
    private Integer outTime;
    @ApiModelProperty(value="应还款额")
    private String totalBackMoney;
    @ApiModelProperty(value="还款明细--本金")
    private String beginMoney;
    @ApiModelProperty(value="综合利息")
    private String totalMoney;
    @ApiModelProperty(value="逾期滞纳金")
    private String redeemOverdue;
    @ApiModelProperty(value="总计金额")
    private String allMoney;
    @ApiModelProperty(value="收款账户--户名")
    private String payeeName;
    @ApiModelProperty(value="收款账户--银行卡名字")
    private String payeeBankName;
    @ApiModelProperty(value="收款账户--银行卡号")
    private String payeeBankCardCode;

    @ApiModelProperty(value="当户电话")
    private String pawnerPhone;

    @ApiModelProperty(value="赎当付款凭证")
    private String redeemTicket;

    @ApiModelProperty(value="当户名称")
    private String pawnerName;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthPrice() {
        return authPrice;
    }

    public void setAuthPrice(String authPrice) {
        this.authPrice = authPrice;
    }

    public String getPawnticketCode() {
        return pawnticketCode;
    }

    public String getRedeemRate() {
        return redeemRate;
    }

    public void setRedeemRate(String redeemRate) {
        this.redeemRate = redeemRate;
    }

    public void setPawnticketCode(String pawnticketCode) {
        this.pawnticketCode = pawnticketCode;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getPawnTime() {
        return pawnTime;
    }

    public void setPawnTime(Integer pawnTime) {
        this.pawnTime = pawnTime;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getMonetRate() {
        return monetRate;
    }

    public void setMonetRate(String monetRate) {
        this.monetRate = monetRate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getOutTime() {
        return outTime;
    }

    public void setOutTime(Integer outTime) {
        this.outTime = outTime;
    }

    public String getTotalBackMoney() {
        return totalBackMoney;
    }

    public void setTotalBackMoney(String totalBackMoney) {
        this.totalBackMoney = totalBackMoney;
    }

    public String getBeginMoney() {
        return beginMoney;
    }

    public void setBeginMoney(String beginMoney) {
        this.beginMoney = beginMoney;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getRedeemOverdue() {
        return redeemOverdue;
    }

    public void setRedeemOverdue(String redeemOverdue) {
        this.redeemOverdue = redeemOverdue;
    }

    public String getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(String allMoney) {
        this.allMoney = allMoney;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeBankName() {
        return payeeBankName;
    }

    public void setPayeeBankName(String payeeBankName) {
        this.payeeBankName = payeeBankName;
    }

    public String getPayeeBankCardCode() {
        return payeeBankCardCode;
    }

    public void setPayeeBankCardCode(String payeeBankCardCode) {
        this.payeeBankCardCode = payeeBankCardCode;
    }


    public String getPawnerPhone() {
        return pawnerPhone;
    }

    public void setPawnerPhone(String pawnerPhone) {
        this.pawnerPhone = pawnerPhone;
    }

    public String getRedeemTicket() {
        return redeemTicket;
    }

    public void setRedeemTicket(String redeemTicket) {
        this.redeemTicket = redeemTicket;
    }

    public String getPawnerName() {
        return pawnerName;
    }

    public void setPawnerName(String pawnerName) {
        this.pawnerName = pawnerName;
    }
}
