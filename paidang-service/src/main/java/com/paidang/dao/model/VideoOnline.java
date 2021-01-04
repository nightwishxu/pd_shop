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
@Table(name="p_video_online")
public class VideoOnline implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *标题
	 */
	@ApiModelProperty(value="标题")
	@ApiParam(value="标题")
	@Column(name = "title")
	private String title;

	/**
	 *封面
	 */
	@ApiModelProperty(value="封面")
	@ApiParam(value="封面")
	@Column(name = "img")
	private String img;

	/**
	 *视频
	 */
	@ApiModelProperty(value="视频")
	@ApiParam(value="视频")
	@Column(name = "video")
	private String video;

	/**
	 *观看人数
	 */
	@ApiModelProperty(value="观看人数")
	@ApiParam(value="观看人数")
	@Column(name = "view_cnt")
	private Integer viewCnt;

	/**
	 *是否首页展示 0:否 1:是
	 */
	@ApiModelProperty(value="是否首页展示 0:否 1:是")
	@ApiParam(value="是否首页展示 0:否 1:是")
	@Column(name = "state")
	private Integer state;

	/**
	 *创建时间
	 */
	@ApiModelProperty(value="创建时间")
	@ApiParam(value="创建时间")
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *是否设置为视频推荐 0不设置 1设置
	 */
	@ApiModelProperty(value="是否设置为视频推荐 0不设置 1设置")
	@ApiParam(value="是否设置为视频推荐 0不设置 1设置")
	@Column(name = "is_suggest")
	private Integer isSuggest;

	/**
	 *标签
	 */
	@ApiModelProperty(value="标签")
	@ApiParam(value="标签")
	@Column(name = "labels")
	private String labels;

	/**
	 *点赞数
	 */
	@ApiModelProperty(value="点赞数")
	@ApiParam(value="点赞数")
	@Column(name = "praise_num")
	private Integer praiseNum;

	/**
	 *收藏数
	 */
	@ApiModelProperty(value="收藏数")
	@ApiParam(value="收藏数")
	@Column(name = "collect_num")
	private Integer collectNum;

	/**
	 *喜欢数
	 */
	@ApiModelProperty(value="喜欢数")
	@ApiParam(value="喜欢数")
	@Column(name = "like_num")
	private Integer likeNum;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title=title == null ? title : title.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setImg(String img) {
		this.img=img == null ? img : img.trim();
	}

	public String getImg() {
		return img;
	}

	public void setVideo(String video) {
		this.video=video == null ? video : video.trim();
	}

	public String getVideo() {
		return video;
	}

	public void setViewCnt(Integer viewCnt) {
		this.viewCnt=viewCnt;
	}

	public Integer getViewCnt() {
		return viewCnt;
	}

	public void setState(Integer state) {
		this.state=state;
	}

	public Integer getState() {
		return state;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setIsSuggest(Integer isSuggest) {
		this.isSuggest=isSuggest;
	}

	public Integer getIsSuggest() {
		return isSuggest;
	}

	public void setLabels(String labels) {
		this.labels=labels == null ? labels : labels.trim();
	}

	public String getLabels() {
		return labels;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum=praiseNum;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum=collectNum;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum=likeNum;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

}
