package com.paidang.domain.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class MyCommentVo {

//    SELECT t.id,t.comment_id,a.article_id,t.content,t.from_thumb_img as icon,
// t.from_nickname as nickname,t.to_nickname as toNickname,t.create_time, 2 as commentType  from t_comment_reply t left join t.comment a on t.comment_id = a.id


    @ApiModelProperty(value = "主题id")
    private Long id;

    @ApiModelProperty(value = "回复评论id,commentType为2才有")
    private Long commentId;

    @ApiModelProperty(value = "动态id")
    private Long articleId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论用户头像")
    private String icon;

    @ApiModelProperty(value = "评论用户昵称")
    private String nickname;

    @ApiModelProperty(value = "被评论用户昵称")
    private String toNickName;

    @ApiModelProperty(value = "评论时间")
    private Date createTime;

    @ApiModelProperty(value = "1 评论 2回复评论")
    private Integer commentType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToNickName() {
        return toNickName;
    }

    public void setToNickName(String toNickName) {
        this.toNickName = toNickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }
}
