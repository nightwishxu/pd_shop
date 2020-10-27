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
@Table(name="t_comment_praise")
public class CommentPraise implements Serializable {

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
	 *用户id
	 */
	@ApiModelProperty(value="用户id")
	@ApiParam(value="用户id")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *动态id
	 */
	@ApiModelProperty(value="动态id")
	@ApiParam(value="动态id")
	@Column(name = "article_id")
	private Integer articleId;

	/**
	 *评论的用户id
	 */
	@ApiModelProperty(value="评论的用户id")
	@ApiParam(value="评论的用户id")
	@Column(name = "comment_user_id")
	private Integer commentUserId;

	/**
	 *评论类型 1 评论2评论回复
	 */
	@ApiModelProperty(value="评论类型 1 评论2评论回复")
	@ApiParam(value="评论类型 1 评论2评论回复")
	@Column(name = "type")
	private Integer type;

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
	@Column(name = "status")
	private Integer status;

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

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId=articleId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setCommentUserId(Integer commentUserId) {
		this.commentUserId=commentUserId;
	}

	public Integer getCommentUserId() {
		return commentUserId;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setStatus(Integer status) {
		this.status=status;
	}

	public Integer getStatus() {
		return status;
	}

}
