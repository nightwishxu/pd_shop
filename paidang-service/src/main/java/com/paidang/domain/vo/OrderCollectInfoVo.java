package com.paidang.domain.vo;

import io.swagger.annotations.ApiModelProperty;

public class OrderCollectInfoVo {
    public OrderCollectInfoVo() {
        this.stateCancel = 0;
        this.state_1 = 0;
        this.state_2 = 0;
        this.state_3 = 0;
        this.state_4 = 0;
        this.state_5 = 0;
        this.stateRefund = 0;
        this.orderAfter = 0;
        this.orderTotal = 0;
    }


    //-1已取消1待付款2已付款(待发货)3已发货（待收货）4确认收货5已评价

    @ApiModelProperty("-1已取消")
    private Integer stateCancel;

    @ApiModelProperty("1待付款")
    private Integer state_1;

    @ApiModelProperty("2已付款(待发货)")
    private Integer state_2;

    @ApiModelProperty("3已发货（待收货）")
    private Integer state_3;

    @ApiModelProperty("4确认收货")
    private Integer state_4;

    @ApiModelProperty("5已评价")
    private Integer state_5;

    @ApiModelProperty("退款")
    private Integer stateRefund;

    @ApiModelProperty("总量")
    private Integer orderTotal;

    @ApiModelProperty("售后")
    private Integer orderAfter;


    public Integer getStateCancel() {
        return stateCancel;
    }

    public void setStateCancel(Integer stateCancel) {
        this.stateCancel = stateCancel;
    }

    public Integer getState_1() {
        return state_1;
    }

    public void setState_1(Integer state_1) {
        this.state_1 = state_1;
    }

    public Integer getState_2() {
        return state_2;
    }

    public void setState_2(Integer state_2) {
        this.state_2 = state_2;
    }

    public Integer getState_3() {
        return state_3;
    }

    public void setState_3(Integer state_3) {
        this.state_3 = state_3;
    }

    public Integer getState_4() {
        return state_4;
    }

    public void setState_4(Integer state_4) {
        this.state_4 = state_4;
    }

    public Integer getState_5() {
        return state_5;
    }

    public void setState_5(Integer state_5) {
        this.state_5 = state_5;
    }

    public Integer getStateRefund() {
        return stateRefund;
    }

    public void setStateRefund(Integer stateRefund) {
        this.stateRefund = stateRefund;
    }

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Integer getOrderAfter() {
        return orderAfter;
    }

    public void setOrderAfter(Integer orderAfter) {
        this.orderAfter = orderAfter;
    }
}
