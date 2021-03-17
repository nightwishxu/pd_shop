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
import java.util.Date;
import java.util.List;

/**
 * 手表鉴定
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("mini_appraisal_watch")
@ApiModel
public class WatchPojo implements Serializable {


    private static final long serialVersionUID = 1114642797036622515L;
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

    @ApiModelProperty(value="视频")
    @ApiParam(value="视频")
    private String video;

    @ApiModelProperty(value="品牌")
    @ApiParam(value="品牌")
    private String brand;

    @ApiModelProperty(value="主体材质")
    @ApiParam(value="主体材质")
    private String subjectMaterial;

    @ApiModelProperty(value="款式（男/女）")
    @ApiParam(value="款式（男/女）")
    private String style;

    @ApiModelProperty(value="机芯类型")
    @ApiParam(value="机芯类型")
    private String movementType;

    @ApiModelProperty(value="使用情况")
    @ApiParam(value="使用情况")
    private String useCase;

    @ApiModelProperty(value="配件")
    @ApiParam(value="配件")
    private String enclosure;

    @ApiModelProperty(value="配件照（多个）")
    @ApiParam(value="配件照（多个）")
    private String enclosureFile;

    @ApiModelProperty(value="购买价格")
    @ApiParam(value="购买价格")
    private BigDecimal buyPrice;

    @ApiModelProperty(value="购买时间")
    @ApiParam(value="购买时间")
    private Date buyTime;

    @ApiModelProperty(value="备注")
    @ApiParam(value="备注")
    private String remark;

    public WatchPojo(List<ContentDetail> list){
        for (ContentDetail detail : list) {
            switch (detail.getName()){
                case "品牌":
                    this.brand = detail.getContent();
                    break;
                case "外观照片":
                    this.photos = detail.getContent();
                    break;
                case "视频":
                    this.video = detail.getContent();
                    break;
                case "主体材质":
                    this.subjectMaterial = detail.getContent();
                    break;
                case "款式（男/女）":
                    this.style = detail.getContent();
                    break;
                case "使用情况":
                    this.useCase = detail.getContent();
                    break;
                case "配件":
                    this.enclosure = detail.getContent();
                    break;
                case "配件照":
                    this.enclosureFile = detail.getContent();
                    break;
                case "购买价格":
                    if (StringUtils.isNotBlank(detail.getContent())){
                        this.buyPrice = new BigDecimal(detail.getContent());
                    }
                    break;
                case "购买时间":
                    if (StringUtils.isNotBlank(detail.getContent())){
                        this.buyTime = DateUtil.parse(detail.getContent());
                    }
                    break;
                case "备注":
                    this.remark = detail.getContent();
                    break;
            }
        }

    }
}
