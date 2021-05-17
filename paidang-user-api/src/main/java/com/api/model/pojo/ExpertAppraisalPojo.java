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
import java.math.BigDecimal;
import java.util.Date;

/**
 * 专家鉴定记录表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("mini_expert_appraisal")
@ApiModel
public class ExpertAppraisalPojo implements Serializable {

    private static final long serialVersionUID = -1808713308791989605L;
    @ApiModelProperty(value="id")
    @ApiParam(value="id")
    @TableId
    private Long id;

    @ApiModelProperty(value="鉴定记录id")
    @ApiParam(value="鉴定记录id")
    private String appraisalId;

    @ApiModelProperty(value="1等待鉴定2鉴定完成3无法鉴定")
    @ApiParam(value="1等待鉴定2鉴定完成3无法鉴定")
    private String state;

    @ApiModelProperty(value="分配的时间")
    @ApiParam(value="分配的时间")
    private Date allocateTime;

    @ApiModelProperty(value="鉴定时间")
    @ApiParam(value="鉴定时间")
    private Date time;

    @ApiModelProperty(value="名称（专家鉴定填写）")
    @ApiParam(value="名称（专家鉴定填写）")
    private String name;

    @ApiModelProperty(value="尺寸（专家鉴定填写）")
    @ApiParam(value="尺寸（专家鉴定填写）")
    private String size;

    @ApiModelProperty(value="重量（专家鉴定填写）")
    @ApiParam(value="重量（专家鉴定填写）")
    private String weight;

    @ApiModelProperty(value="主材质（专家鉴定填写）")
    @ApiParam(value="主材质（专家鉴定填写）")
    private String mainMaterial;

    @ApiModelProperty(value="副材质（专家鉴定填写）")
    @ApiParam(value="副材质（专家鉴定填写）")
    private String subMaterial;

    @ApiModelProperty(value="年代（专家鉴定填写）")
    @ApiParam(value="年代（专家鉴定填写）")
    private String years;

    @ApiModelProperty(value="其他（专家鉴定填写）")
    @ApiParam(value="其他（专家鉴定填写）")
    private String other;

    @ApiModelProperty(value="市场流通性（专家鉴定填写）")
    @ApiParam(value="市场流通性（专家鉴定填写）")
    private String marketLiquidity;

    @ApiModelProperty(value="价值稳定性（专家鉴定填写）")
    @ApiParam(value="价值稳定性（专家鉴定填写）")
    private String valueStability;

    @ApiModelProperty(value="材质易损性（专家鉴定填写）")
    @ApiParam(value="材质易损性（专家鉴定填写）")
    private String materialVulnerability;

    @ApiModelProperty(value="典当价格（专家鉴定填写）")
    @ApiParam(value="典当价格（专家鉴定填写）")
    private BigDecimal pawnPrice;

    @ApiModelProperty(value="鉴定人code（专家）")
    @ApiParam(value="鉴定人code（专家）")
    private String appraisalUser;

    @ApiModelProperty(value="无法鉴定原因（专家无法鉴定时填写）")
    @ApiParam(value="无法鉴定原因（专家无法鉴定时填写）")
    private String reason;

    @ApiModelProperty(value="是否选用1选用 0不选用")
    @ApiParam(value="是否选用1选用 0不选用")
    private String isSelection;

    @ApiModelProperty(value="userGoodsId")
    @ApiParam(value="userGoodsId")
    private Integer userGoodsId;
}
