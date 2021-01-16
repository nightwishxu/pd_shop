package com.api.view.orgHome;

import io.swagger.annotations.ApiModelProperty;

public class OrgOperateNum
{
    @ApiModelProperty(value="赎当操作数量")
    private Integer redeemNum;

    @ApiModelProperty(value="续当操作数量")
    private Integer repawnNum;


    public Integer getRedeemNum() {
        return redeemNum;
    }

    public void setRedeemNum(Integer redeemNum) {
        this.redeemNum = redeemNum;
    }

    public Integer getRepawnNum() {
        return repawnNum;
    }

    public void setRepawnNum(Integer repawnNum) {
        this.repawnNum = repawnNum;
    }
}
