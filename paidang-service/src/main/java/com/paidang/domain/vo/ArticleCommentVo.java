package com.paidang.domain.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ArticleCommentVo {

    private Integer id;

    @ApiModelProperty(value = "主题id")
    private Integer topicId;

    @ApiModelProperty(value = "主题作者id")
    private Integer topicUserId;

    @ApiModelProperty(value = "1为文章")
    private Integer type;

    @ApiModelProperty(value = "评论者id，一般为会员表的id")
    private Integer userId;

    @ApiModelProperty(value = "冗余用户昵称")
    private String nickName;

    @ApiModelProperty(value = "冗余用户头像")
    private String icon;

    @ApiModelProperty(value = "是否为热评，1为热评")
    private Integer isHot;

    @ApiModelProperty(value = "评论被点赞的次数")
    private Integer likeNum;

    @ApiModelProperty(value = "评论被回复的次数")
    private Integer replyNum;

    @ApiModelProperty(value = "是否回复0没有，1已回复")
    private Integer isReply;

    @ApiModelProperty(value = "评论状态，-1为删除，0为待审核，1为已发布")
    private Integer status;

    @ApiModelProperty(value = "0隐藏1展示")
    private Integer isShow;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "0为普通回复，1为后台管理员回复,2作者回复")
    private Integer istopicUser;

    @ApiModelProperty(value = "评论内容")
    private String content;


    @ApiModelProperty(value = "0动态评论1回复评论2回复评论的回复")
    private Integer commentType;

    private Integer isReport;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getTopicUserId() {
        return topicUserId;
    }

    public void setTopicUserId(Integer topicUserId) {
        this.topicUserId = topicUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public Integer getIsReply() {
        return isReply;
    }

    public void setIsReply(Integer isReply) {
        this.isReply = isReply;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIstopicUser() {
        return istopicUser;
    }

    public void setIstopicUser(Integer istopicUser) {
        this.istopicUser = istopicUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public Integer getIsReport() {
        return isReport;
    }

    public void setIsReport(Integer isReport) {
        this.isReport = isReport;
    }
}
