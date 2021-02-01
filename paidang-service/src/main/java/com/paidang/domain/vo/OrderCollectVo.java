package com.paidang.domain.vo;

import io.swagger.annotations.ApiModelProperty;

public class OrderCollectVo {

    @ApiModelProperty("-1已取消1待付款2已付款(待发货)3已发货（待收货）4确认收货5已评价")
    private Integer state;

    @ApiModelProperty("数量")
    private Integer num;


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
