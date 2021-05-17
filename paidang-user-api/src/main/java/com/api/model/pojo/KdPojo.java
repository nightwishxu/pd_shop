package com.api.model.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 快递结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("mini_appraisal_kd")
@ApiModel
public class KdPojo implements Serializable {
    private static final long serialVersionUID = 7605490675479060282L;

    @ApiModelProperty(value="id")
    @ApiParam(value="id")
    @TableId
    private Long id;

    @ApiModelProperty(value="商品编号")
    @ApiParam(value="商品编号")
    private String goodsCode;

    @ApiModelProperty(value="外观照片")
    @ApiParam(value="外观照片")
    private String photos;

    @ApiModelProperty(value="打包视频")
    @ApiParam(value="打包视频")
    private String video;

    @ApiModelProperty(value="快递公司")
    @ApiParam(value="快递公司")
    private String expressCompany;

    @ApiModelProperty(value="快递单号")
    @ApiParam(value="快递单号")
    private String expressCode;

    @ApiModelProperty(value="保价金额")
    @ApiParam(value="保价金额")
    private String money;

    @ApiModelProperty(value="")
    @ApiParam(value="")
    private Integer userGoodsId;
}
