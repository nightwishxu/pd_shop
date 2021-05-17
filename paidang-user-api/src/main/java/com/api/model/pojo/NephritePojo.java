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
 * 和田玉
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("mini_appraisal_nephrite")
@ApiModel
public class NephritePojo implements Serializable {
    private static final long serialVersionUID = -7991541807373974883L;
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

    @ApiModelProperty(value="和田玉颜色")
    @ApiParam(value="和田玉颜色")
    private String color;

    @ApiModelProperty(value="尺寸")
    @ApiParam(value="尺寸")
    private String size;

    @ApiModelProperty(value="备注")
    @ApiParam(value="备注")
    private String remark;

    @ApiModelProperty(value="")
    @ApiParam(value="")
    private Integer userGoodsId;

    public NephritePojo(List<ContentDetail> list,String images,String video,String info,Integer userGoodsId){
        this.photos = images;
        this.video = video;
        this.remark = info;
        this.userGoodsId = userGoodsId;
        for (ContentDetail detail : list) {
            switch (detail.getName()){
                case "和田玉颜色":
                    this.color = detail.getContent();
                    break;
                case "和田玉尺寸":
                    this.size = detail.getContent();
                    break;

            }
        }

    }
}
