package com.api.view.video;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AppVideoOnline {
    @ApiModelProperty(value="id")
    private Integer id;
    @ApiModelProperty(value="标题")
    private String title;
    @ApiModelProperty(value="封面")
    private String img;
    @ApiModelProperty(value="视频")
    private String video;
    @ApiModelProperty(value="观看人数")
    private Integer viewCnt;
    @ApiModelProperty(value="评论数量")
    private Integer comCnt;
    @ApiModelProperty(value="是否是首页视频0不是 1是")
    private Integer state;

    @ApiModelProperty(value="视频标签 ,分割")
    private String labels;
    @ApiModelProperty(value="点赞状态")
    private Integer praiseStatus;
    @ApiModelProperty(value="点赞数量")
    private Integer praiseCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(Integer viewCnt) {
        this.viewCnt = viewCnt;
    }

    public Integer getComCnt() {
        return comCnt;
    }

    public void setComCnt(Integer comCnt) {
        this.comCnt = comCnt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public Integer getPraiseStatus() {
        return praiseStatus;
    }

    public void setPraiseStatus(Integer praiseStatus) {
        this.praiseStatus = praiseStatus;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }
}
