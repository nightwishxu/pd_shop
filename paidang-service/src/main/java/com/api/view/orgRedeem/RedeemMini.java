package com.api.view.orgRedeem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Lee on 2017/10/22.
 */
@ApiModel
public class RedeemMini {

    @ApiModelProperty("典当表id")
    private String pawnId;

    @ApiModelProperty("当品id")
    private String goodsId;

    @ApiModelProperty("当品名称")
    private String title;

    @ApiModelProperty("当号")
    private String pawnTicketCode;

    @ApiModelProperty("鉴定价")
    private String authPrice;

    @ApiModelProperty("图片")
    private String images;

    @ApiModelProperty("已发放当金")
    private String userMoney;

    @ApiModelProperty("当品状态 1-已赎回 2-赎当申请处理中")
    private Integer redeemState;

    public String getPawnId() {
        return pawnId;
    }

    public void setPawnId(String pawnId) {
        this.pawnId = pawnId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPawnTicketCode() {
        return pawnTicketCode;
    }

    public void setPawnTicketCode(String pawnTicketCode) {
        this.pawnTicketCode = pawnTicketCode;
    }

    public String getAuthPrice() {
        return authPrice;
    }

    public void setAuthPrice(String authPrice) {
        this.authPrice = authPrice;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(String userMoney) {
        this.userMoney = userMoney;
    }

    public Integer getRedeemState() {
        return redeemState;
    }

    public void setRedeemState(Integer redeemState) {
        this.redeemState = redeemState;
    }
}
