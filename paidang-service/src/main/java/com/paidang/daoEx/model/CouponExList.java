package com.paidang.daoEx.model;

import com.paidang.dao.model.Coupon;
import io.swagger.annotations.ApiModelProperty;

/**
@author sun
*/
public class CouponExList extends Coupon {


    @ApiModelProperty(value = "列表显示 领取数")
    private Integer bindNum;
    @ApiModelProperty(value = "列表显示 使用数")
    private Integer usedNum;

    public Integer getBindNum() {
        return bindNum;
    }

    public void setBindNum(Integer bindNum) {
        this.bindNum = bindNum;
    }

    public Integer getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
    }
}