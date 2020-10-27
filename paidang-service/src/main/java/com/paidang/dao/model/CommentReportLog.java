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
@Table(name="t_comment_report_log")
public class CommentReportLog implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *评论id
	 */
	@ApiModelProperty(value="评论id")
	@ApiParam(value="评论id")
	@Column(name = "comment_id")
	private Integer commentId;

	/**
	 *评论类型1回复动态评论 2 回复评论
	 */
	@ApiModelProperty(value="评论类型1回复动态评论 2 回复评论")
	@ApiParam(value="评论类型1回复动态评论 2 回复评论")
	@Column(name = "comment_type")
	private Integer commentType;

	/**
	 *用户id
	 */
	@ApiModelProperty(value="用户id")
	@ApiParam(value="用户id")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *1正常
	 */
	@ApiModelProperty(value="1正常")
	@ApiParam(value="1正常")
	@Column(name = "status")
	private Integer status;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "info")
	private String info;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "create_time")
	private java.util.Date createTime;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setCommentId(Integer commentId) {
		this.commentId=commentId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentType(Integer commentType) {
		this.commentType=commentType;
	}

	public Integer getCommentType() {
		return commentType;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setStatus(Integer status) {
		this.status=status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setInfo(String info) {
		this.info=info == null ? info : info.trim();
	}

	public String getInfo() {
		return info;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

}
