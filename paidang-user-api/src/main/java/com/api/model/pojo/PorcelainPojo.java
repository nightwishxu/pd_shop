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
 * 瓷器鉴定
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("mini_appraisal_porcelain")
@ApiModel
public class PorcelainPojo implements Serializable {

    private static final long serialVersionUID = -8828408147423284678L;
    @ApiModelProperty(value="id")
    @ApiParam(value="id")
    @TableId
    private Long id;

    @ApiModelProperty(value="商品编号")
    @ApiParam(value="商品编号")
    private String goodsCode;

    @ApiModelProperty(value="正面图")
    @ApiParam(value="正面图")
    private String frontImg;

    @ApiModelProperty(value="侧面图")
    @ApiParam(value="侧面图")
    private String sideImg;

    @ApiModelProperty(value="背面图")
    @ApiParam(value="背面图")
    private String backImg;

    @ApiModelProperty(value="口沿图")
    @ApiParam(value="口沿图")
    private String mouthImg;

    @ApiModelProperty(value="底面图")
    @ApiParam(value="底面图")
    private String bottomImg;

    @ApiModelProperty(value="备注")
    @ApiParam(value="备注")
    private String remark;

    @ApiModelProperty(value="")
    @ApiParam(value="")
    private Integer userGoodsId;

    public PorcelainPojo(List<ContentDetail> list,String info,Integer userGoodsId){
        for (ContentDetail detail : list) {
            switch (detail.getName()){
                case "正面图":
                    this.frontImg = detail.getContent();
                    break;
                case "侧面图":
                    this.sideImg = detail.getContent();
                    break;
                case "背面图":
                    this.backImg = detail.getContent();
                    break;
                case "口沿图":
                    this.mouthImg = detail.getContent();
                    break;
                case "底面图":
                    this.bottomImg = detail.getContent();
                    break;
            }
        }
        this.remark = info;
        this.userGoodsId = userGoodsId;

    }

}
