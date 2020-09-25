package com.paidang.daoEx.model;

import com.paidang.dao.model.Order;

/**
@author sun
*/
public class OrderEx extends Order {

    private String estate;
    private String eIsBalance;
    private String userName;

    private Integer expressId;

    private String orgName;

    private String tradNo;

    private Integer userGoodsId;

    private Integer isSell;
    private String goodsOwnerAccount;
    private String goodsOwnerNick;
    private String goodsOwnerName;

    public String getGoodsOwnerAccount() {
        return goodsOwnerAccount;
    }

    public void setGoodsOwnerAccount(String goodsOwnerAccount) {
        this.goodsOwnerAccount = goodsOwnerAccount;
    }

    public String getGoodsOwnerNick() {
        return goodsOwnerNick;
    }

    public void setGoodsOwnerNick(String goodsOwnerNick) {
        this.goodsOwnerNick = goodsOwnerNick;
    }

    public String getGoodsOwnerName() {
        return goodsOwnerName;
    }

    public void setGoodsOwnerName(String goodsOwnerName) {
        this.goodsOwnerName = goodsOwnerName;
    }

    public Integer getUserGoodsId() {
        return userGoodsId;
    }

    public void setUserGoodsId(Integer userGoodsId) {
        this.userGoodsId = userGoodsId;
    }

    public Integer getIsSell() {
        return isSell;
    }

    public void setIsSell(Integer isSell) {
        this.isSell = isSell;
    }

    public String getTradNo() {
        return tradNo;
    }

    public void setTradNo(String tradNo) {
        this.tradNo = tradNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String geteIsBalance() {
        return eIsBalance;
    }

    public void seteIsBalance(String eIsBalance) {
        this.eIsBalance = eIsBalance;
    }

    //0：在途1：揽件2：疑难3：签收4：退签或异常签收5：派件6：退回
    private Integer expressState;

    //物流数据
    private String expressData;

    public String getEstate() {
        return estate;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }

    public Integer getExpressState() {
        return expressState;
    }

    public void setExpressState(Integer expressState) {
        this.expressState = expressState;
    }

    public Integer getExpressId() {
        return expressId;
    }

    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getExpressData() {
        return expressData;
    }

    public void setExpressData(String expressData) {
        this.expressData = expressData;
    }
}