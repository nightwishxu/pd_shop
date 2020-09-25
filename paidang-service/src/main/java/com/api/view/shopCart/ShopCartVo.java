package com.api.view.shopCart;

import com.item.daoEx.model.ShopCartEx;

import java.util.List;


public class ShopCartVo {
    private Integer orgId;
    //机构名称
    private String orgName;
    //机构logo
    private String orgLogo;

    private List<ShopCartEx> goods;


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
}
