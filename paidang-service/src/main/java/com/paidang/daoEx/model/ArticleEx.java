package com.paidang.daoEx.model;

import com.item.daoEx.model.UserEx;
import com.paidang.dao.model.Article;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
@author sun
*/
public class ArticleEx extends Article {

    private Date startTime;

    private Date endTime;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    private String account;

    private String username;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "发布时间中文格式")
    private String releaseTimeInfo;

    @ApiModelProperty(value = "评论列表")
    private List<ArticleCommentEx> commentList;

    @ApiModelProperty(value = "精彩评论")
    private List<ArticleCommentEx> wonderfulCommentList;

    @ApiModelProperty(value = "1已关注")
    private Integer followStatus;

    @ApiModelProperty(value = "1已点赞")
    private Integer praiseStatus;

    @ApiModelProperty(value = "最新点赞的用户头像")
    private List<String> lastPraiseIcon;

    @ApiModelProperty(value = "举报信息")
    private String reportInfo;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "动态")
    List<ArticleEx> noticeList;

    @ApiModelProperty(value = "推荐用户")
    List<UserEx> recommendUserList;

    @ApiModelProperty(value = "动态作者id 后台搜索用")
    private Integer authorId;


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getReleaseTimeInfo() {
        return releaseTimeInfo;
    }

    public void setReleaseTimeInfo(String releaseTimeInfo) {
        this.releaseTimeInfo = releaseTimeInfo;
    }


    public List<ArticleCommentEx> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<ArticleCommentEx> commentList) {
        this.commentList = commentList;
    }

    public List<ArticleCommentEx> getWonderfulCommentList() {
        return wonderfulCommentList;
    }

    public void setWonderfulCommentList(List<ArticleCommentEx> wonderfulCommentList) {
        this.wonderfulCommentList = wonderfulCommentList;
    }

    public Integer getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Integer followStatus) {
        this.followStatus = followStatus;
    }

    public Integer getPraiseStatus() {
        return praiseStatus;
    }

    public void setPraiseStatus(Integer praiseStatus) {
        this.praiseStatus = praiseStatus;
    }

    public List<String> getLastPraiseIcon() {
        return lastPraiseIcon;
    }

    public void setLastPraiseIcon(List<String> lastPraiseIcon) {
        this.lastPraiseIcon = lastPraiseIcon;
    }

    public String getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(String reportInfo) {
        this.reportInfo = reportInfo;
    }

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public void setType(Integer type) {
        this.type = type;
    }

    public List<ArticleEx> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<ArticleEx> noticeList) {
        this.noticeList = noticeList;
    }

    public List<UserEx> getRecommendUserList() {
        return recommendUserList;
    }

    public void setRecommendUserList(List<UserEx> recommendUserList) {
        this.recommendUserList = recommendUserList;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}