package com.paidang.daoEx.model;

import com.paidang.domain.enums.OrgIntegralEnum;
import com.paidang.dao.model.PawnOrg;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
@author sun
*/
public class PawnOrgEx extends PawnOrg {

    //机构用户电话
    private String orgUserPhone;

    //机构用户id
    private Integer orgUserId;

    //该机构在平台成功典当的单数
    private String dealAmount;

    //机构商品照片
    private String goodsImgs;


    @ApiModelProperty(value = "店铺等级")
    private String orgLevel;

    private Integer isPersonal;

    private Integer isEnterprise;

    private Integer auctionCount;

    public Integer getIsEnterprise() {
        return isEnterprise;
    }

    public void setIsEnterprise(Integer isEnterprise) {
        this.isEnterprise = isEnterprise;
    }

    public String getOrgLevel() {
        BigDecimal integral = getIntegral()==null?BigDecimal.ZERO:getIntegral();
        OrgIntegralEnum integralEnum = OrgIntegralEnum.getLevel(integral);
        return integralEnum.getDesc();
//		return orgLevel;
    }




    public String getGoodsImgs() {
        return goodsImgs;
    }

    public void setGoodsImgs(String goodsImgs) {
        this.goodsImgs = goodsImgs;
    }

    public String getOrgUserPhone() {
        return orgUserPhone;
    }

    public void setOrgUserPhone(String orgUserPhone) {
        this.orgUserPhone = orgUserPhone;
    }

    public Integer getOrgUserId() {
        return orgUserId;
    }

    public void setOrgUserId(Integer orgUserId) {
        this.orgUserId = orgUserId;
    }

    public String getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }

    public Integer getIsPersonal() {
        return isPersonal;
    }

    public void setIsPersonal(Integer isPersonal) {
        this.isPersonal = isPersonal;
    }

    public Integer getAuctionCount() {
        return auctionCount;
    }

    public void setAuctionCount(Integer auctionCount) {
        this.auctionCount = auctionCount;
    }
}