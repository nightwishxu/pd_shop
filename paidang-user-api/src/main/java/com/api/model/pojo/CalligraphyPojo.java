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
 * 书法鉴定
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("mini_appraisal_calligraphy")
@ApiModel
public class CalligraphyPojo implements Serializable {

    private static final long serialVersionUID = -975614642570571910L;
    @ApiModelProperty(value="id")
    @ApiParam(value="id")
    @TableId
    private Long id;

    @ApiModelProperty(value="商品编号")
    @ApiParam(value="商品编号")
    private String goodsCode;

    @ApiModelProperty(value="正视图")
    @ApiParam(value="正视图")
    private String frontImg;

    @ApiModelProperty(value="题跋图")
    @ApiParam(value="题跋图")
    private String postscriptImg;

    @ApiModelProperty(value="落款图")
    @ApiParam(value="落款图")
    private String settlementImg;

    @ApiModelProperty(value="局部图")
    @ApiParam(value="局部图")
    private String localImg;

    @ApiModelProperty(value="印鉴或签名图")
    @ApiParam(value="印鉴或签名图")
    private String autographImg;

    @ApiModelProperty(value="备注")
    @ApiParam(value="备注")
    private String remark;

    @ApiModelProperty(value="")
    @ApiParam(value="")
    private Integer userGoodsId;


    public CalligraphyPojo(List<ContentDetail> list,String info,Integer userGoodsId){
        this.remark = info;
        this.userGoodsId = userGoodsId;
        for (ContentDetail detail : list) {
            switch (detail.getName()){
                case "正视图":
                    this.frontImg = detail.getContent();
                    break;
                case "题跋图":
                    this.postscriptImg = detail.getContent();
                    break;
                case "落款图":
                    this.settlementImg = detail.getContent();
                    break;
                case "局部图":
                    this.localImg = detail.getContent();
                    break;
                case "签名图":
                    this.autographImg = detail.getContent();
                    break;
            }
        }

    }

}
