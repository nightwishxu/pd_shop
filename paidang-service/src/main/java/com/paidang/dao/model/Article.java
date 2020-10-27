package com.paidang.dao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 */
@ApiModel
@Table(name="t_article")
public class Article implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *用户编号
	 */
	@ApiModelProperty(value="用户编号")
	@ApiParam(value="用户编号")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *封面
	 */
	@ApiModelProperty(value="封面")
	@ApiParam(value="封面")
	@Column(name = "cover")
	private String cover;

	/**
	 *类型默认1用户帖子 2后台帖子
	 */
	@ApiModelProperty(value="类型默认1用户帖子 2后台帖子")
	@ApiParam(value="类型默认1用户帖子 2后台帖子")
	@Column(name = "type")
	private Integer type;

	/**
	 *0删除1草稿2发布
	 */
	@ApiModelProperty(value="0删除1草稿2发布")
	@ApiParam(value="0删除1草稿2发布")
	@Column(name = "status")
	private Integer status;

	/**
	 *0隐藏1展示
	 */
	@ApiModelProperty(value="0隐藏1展示")
	@ApiParam(value="0隐藏1展示")
	@Column(name = "is_show")
	private Integer isShow;

	/**
	 *标题
	 */
	@ApiModelProperty(value="标题")
	@ApiParam(value="标题")
	@Column(name = "title")
	private String title;

	/**
	 *文章内容
	 */
	@ApiModelProperty(value="文章内容")
	@ApiParam(value="文章内容")
	@Column(name = "content")
	private String content;

	/**
	 *图片
	 */
	@ApiModelProperty(value="图片")
	@ApiParam(value="图片")
	@Column(name = "imgs")
	private String imgs;

	/**
	 *视频
	 */
	@ApiModelProperty(value="视频")
	@ApiParam(value="视频")
	@Column(name = "video")
	private String video;

	/**
	 *评论数
	 */
	@ApiModelProperty(value="评论数")
	@ApiParam(value="评论数")
	@Column(name = "comment_count")
	private Integer commentCount;

	/**
	 *收藏数
	 */
	@ApiModelProperty(value="收藏数")
	@ApiParam(value="收藏数")
	@Column(name = "collect_count")
	private Integer collectCount;

	/**
	 *点赞数
	 */
	@ApiModelProperty(value="点赞数")
	@ApiParam(value="点赞数")
	@Column(name = "praise_count")
	private Integer praiseCount;

	/**
	 *点击数
	 */
	@ApiModelProperty(value="点击数")
	@ApiParam(value="点击数")
	@Column(name = "click_count")
	private Integer clickCount;

	/**
	 *举报数
	 */
	@ApiModelProperty(value="举报数")
	@ApiParam(value="举报数")
	@Column(name = "report_count")
	private Integer reportCount;

	/**
	 *标签
	 */
	@ApiModelProperty(value="标签")
	@ApiParam(value="标签")
	@Column(name = "labels")
	private String labels;

	/**
	 *发布时间
	 */
	@ApiModelProperty(value="发布时间")
	@ApiParam(value="发布时间")
	@Column(name = "release_time")
	private java.util.Date releaseTime;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "create_name")
	private String createName;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "update_name")
	private String updateName;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "update_time")
	private java.util.Date updateTime;

	/**
	 *排序
	 */
	@ApiModelProperty(value="排序")
	@ApiParam(value="排序")
	@Column(name = "sort")
	private Integer sort;

	/**
	 *公告描述
	 */
	@ApiModelProperty(value="公告描述")
	@ApiParam(value="公告描述")
	@Column(name = "description")
	private String description;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setCover(String cover) {
		this.cover=cover == null ? cover : cover.trim();
	}

	public String getCover() {
		return cover;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setStatus(Integer status) {
		this.status=status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setIsShow(Integer isShow) {
		this.isShow=isShow;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setTitle(String title) {
		this.title=title == null ? title : title.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content=content == null ? content : content.trim();
	}

	public String getContent() {
		return content;
	}

	public void setImgs(String imgs) {
		this.imgs=imgs == null ? imgs : imgs.trim();
	}

	public String getImgs() {
		return imgs;
	}

	public void setVideo(String video) {
		this.video=video == null ? video : video.trim();
	}

	public String getVideo() {
		return video;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount=commentCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount=collectCount;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount=praiseCount;
	}

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount=clickCount;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setReportCount(Integer reportCount) {
		this.reportCount=reportCount;
	}

	public Integer getReportCount() {
		return reportCount;
	}

	public void setLabels(String labels) {
		this.labels=labels == null ? labels : labels.trim();
	}

	public String getLabels() {
		return labels;
	}

	public void setReleaseTime(java.util.Date releaseTime) {
		this.releaseTime=releaseTime;
	}

	public java.util.Date getReleaseTime() {
		return releaseTime;
	}

	public void setCreateName(String createName) {
		this.createName=createName == null ? createName : createName.trim();
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setUpdateName(String updateName) {
		this.updateName=updateName == null ? updateName : updateName.trim();
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime=updateTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setSort(Integer sort) {
		this.sort=sort;
	}

	public Integer getSort() {
		return sort;
	}

	public void setDescription(String description) {
		this.description=description == null ? description : description.trim();
	}

	public String getDescription() {
		return description;
	}

}
