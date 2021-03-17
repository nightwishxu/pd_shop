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
 *其他鉴定
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("mini_appraisal_other")
@ApiModel
public class OtherPojo implements Serializable {


    private static final long serialVersionUID = 6813677747779988939L;
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

    @ApiModelProperty(value="描述补充")
    @ApiParam(value="描述补充")
    private String remark;

    public OtherPojo(List<ContentDetail> list){
        for (ContentDetail detail : list) {
            switch (detail.getName()){
                case "外观照片":
                    this.photos = detail.getContent();
                    break;
                case "视频":
                    this.video = detail.getContent();
                    break;
                case "描述补充":
                    this.remark = detail.getContent();
                    break;
            }
        }

    }

}
