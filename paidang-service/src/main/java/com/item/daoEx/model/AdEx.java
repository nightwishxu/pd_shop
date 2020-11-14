package com.item.daoEx.model;

import com.item.dao.model.Ad;
import io.swagger.annotations.ApiModelProperty;

/**
@author sun
*/
public class AdEx extends Ad {
	private Integer goodsId;

	private String cost;

    @ApiModelProperty(value="类型1绝当竞拍  2绝当普通拍卖")
    private Integer cstate;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Integer getCstate() {
        return cstate;
    }

    public void setCstate(Integer cstate) {
        this.cstate = cstate;
    }
}