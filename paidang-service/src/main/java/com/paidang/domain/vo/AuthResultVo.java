package com.paidang.domain.vo;

import io.swagger.annotations.ApiModelProperty;

public class AuthResultVo {
    public AuthResultVo(Integer status,  String msg,Integer type) {
        this.status = status;
        this.type = type;
        this.msg = msg;
    }

    public AuthResultVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @ApiModelProperty(value = "状态 -1 未提交过认证  0 认证审核中  1认证审核通过 2 认证不通过")
    private Integer status;

    @ApiModelProperty(value = "类型 1个人认证 2企业认证")
    private Integer type;

    @ApiModelProperty(value = "内容")
    private String msg;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
