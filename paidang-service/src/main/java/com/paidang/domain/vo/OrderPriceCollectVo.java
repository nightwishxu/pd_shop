package com.paidang.domain.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class OrderPriceCollectVo {

    @ApiModelProperty(value = "待打款")
    private BigDecimal toBePay;

    @ApiModelProperty(value = "待发货")
    private BigDecimal toBeDeliver;

    @ApiModelProperty(value = "待收货")
    private BigDecimal toBeReceipt;

    @ApiModelProperty(value = "总金额")
    private BigDecimal total;

    public BigDecimal getToBePay() {
        return toBePay;
    }

    public void setToBePay(BigDecimal toBePay) {
        this.toBePay = toBePay;
    }

    public BigDecimal getToBeDeliver() {
        return toBeDeliver;
    }

    public void setToBeDeliver(BigDecimal toBeDeliver) {
        this.toBeDeliver = toBeDeliver;
    }

    public BigDecimal getToBeReceipt() {
        return toBeReceipt;
    }

    public void setToBeReceipt(BigDecimal toBeReceipt) {
        this.toBeReceipt = toBeReceipt;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
