package com.api.model.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.base.util.DateUtil;
import com.base.util.StringUtil;
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
 * 奢侈品
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("mini_appraisal_luxuries")
@ApiModel
public class LuxuriesPojo implements Serializable {

    private static final long serialVersionUID = -4798393799143179465L;
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

    @ApiModelProperty(value="镶石材质")
    @ApiParam(value="镶石材质")
    private String inlaidStoneMaterial;

    @ApiModelProperty(value="使用情况")
    @ApiParam(value="使用情况")
    private String useCase;

    @ApiModelProperty(value="附件")
    @ApiParam(value="附件")
    private String enclosure;

    @ApiModelProperty(value="附件照（多个）")
    @ApiParam(value="附件照（多个）")
    private String enclosureFile;

    @ApiModelProperty(value="购买价格")
    @ApiParam(value="购买价格")
    private BigDecimal buyPrice;

    @ApiModelProperty(value="购买时间")
    @ApiParam(value="购买时间")
    private Date buyTime;

    @ApiModelProperty(value="描述补充")
    @ApiParam(value="描述补充")
    private String remark;


    public LuxuriesPojo(List<ContentDetail> list){
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
                case "镶石材质":
                    this.inlaidStoneMaterial = detail.getContent();
                    break;
                case "使用情况":
                    this.useCase = detail.getContent();
                    break;
                case "附件":
                    this.enclosure = detail.getContent();
                    break;
                case "附件照":
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
                case "描述补充":
                    this.remark = detail.getContent();
                    break;
            }
        }

    }

}
