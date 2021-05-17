package com.api.model.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.base.util.DateUtil;
import com.ruoyi.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 文玩杂项鉴定
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("mini_appraisal_wenwan")
@ApiModel
public class WenwanPojo implements Serializable {

    private static final long serialVersionUID = -8965159234775421933L;
    @ApiModelProperty(value="id")
    @ApiParam(value="id")
    @TableId
    private Long id;

    @ApiModelProperty(value="商品编号")
    @ApiParam(value="商品编号")
    private String goodsCode;

    @ApiModelProperty(value="整体图")
    @ApiParam(value="整体图")
    private String wholeImg;

    @ApiModelProperty(value="45度图")
    @ApiParam(value="45度图")
    private String angleImg;

    @ApiModelProperty(value="侧面图")
    @ApiParam(value="侧面图")
    private String sideImg;

    @ApiModelProperty(value="局部图")
    @ApiParam(value="局部图")
    private String localImg;

    @ApiModelProperty(value="背面图")
    @ApiParam(value="背面图")
    private String backImg;

    @ApiModelProperty(value="底面图")
    @ApiParam(value="底面图")
    private String bottomImg;

    @ApiModelProperty(value="备注")
    @ApiParam(value="备注")
    private String remark;

    @ApiModelProperty(value="")
    @ApiParam(value="")
    private Integer userGoodsId;

    public WenwanPojo(List<ContentDetail> list,String info,Integer userGoodsId){
        this.remark = info;
        this.userGoodsId = userGoodsId;
        for (ContentDetail detail : list) {
            switch (detail.getName()){
                case "整体图":
                    this.wholeImg = detail.getContent();
                    break;
                case "45度图":
                    this.angleImg = detail.getContent();
                    break;
                case "侧面图":
                    this.sideImg = detail.getContent();
                    break;
                case "局部图":
                    this.localImg = detail.getContent();
                    break;
                case "背面图":
                    this.backImg = detail.getContent();
                    break;
                case "底面图":
                    this.bottomImg = detail.getContent();
                    break;
            }
        }

    }

}
