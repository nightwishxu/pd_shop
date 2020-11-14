package com.paidang.daoEx.model;

import com.paidang.dao.model.GoodsAuction;

/**
@author sun
*/
public class GoodsAuctionEx extends GoodsAuction {
    private String maxPrice;
    private String userName;
    private String headImg;

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}