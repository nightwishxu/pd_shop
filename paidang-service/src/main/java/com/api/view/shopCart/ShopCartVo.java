package com.api.view.shopCart;

import com.item.daoEx.model.ShopCartEx;
import com.paidang.domain.enums.OrgIntegralEnum;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;


public class ShopCartVo {
    private Integer orgId;
    //机构名称
    private String orgName;
    //机构logo
    private String orgLogo;

    private List<ShopCartEx> goods;



    @ApiModelProperty(value = "店铺积分")
    private BigDecimal orgIntegral;

    @ApiModelProperty(value = "店铺等级")
    private String orgLevel;



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

    public String getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(String orgLogo) {
        this.orgLogo = orgLogo;
    }

    public List<ShopCartEx> getGoods() {
        return goods;
    }

    public void setGoods(List<ShopCartEx> goods) {
        this.goods = goods;
    }

    public BigDecimal getOrgIntegral() {
        return orgIntegral;
    }

    public void setOrgIntegral(BigDecimal orgIntegral) {
        this.orgIntegral = orgIntegral;
    }

    public String getOrgLevel() {
        BigDecimal integral = getOrgIntegral()==null?BigDecimal.ZERO:getOrgIntegral();
        OrgIntegralEnum integralEnum = OrgIntegralEnum.getLevel(integral);
        return integralEnum.getDesc();
    }
}
