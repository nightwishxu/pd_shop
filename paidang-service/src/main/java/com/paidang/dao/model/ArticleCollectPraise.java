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
@Table(name="t_article_collect_praise")
public class ArticleCollectPraise implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "article_id")
	private Integer articleId;

	/**
	 *动态作者id
	 */
	@ApiModelProperty(value="动态作者id")
	@ApiParam(value="动态作者id")
	@Column(name = "author_id")
	private Integer authorId;

	/**
	 *头像
	 */
	@ApiModelProperty(value="头像")
	@ApiParam(value="头像")
	@Column(name = "icon")
	private String icon;

	/**
	 *昵称
	 */
	@ApiModelProperty(value="昵称")
	@ApiParam(value="昵称")
	@Column(name = "nickname")
	private String nickname;

	/**
	 *动态图片
	 */
	@ApiModelProperty(value="动态图片")
	@ApiParam(value="动态图片")
	@Column(name = "img")
	private String img;

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "status")
	private Integer status;

	/**
	 *0点赞1收藏
	 */
	@ApiModelProperty(value="0点赞1收藏")
	@ApiParam(value="0点赞1收藏")
	@Column(name = "type")
	private Integer type;

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

	public void setAuthorId(Integer authorId) {
		this.authorId=authorId;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setIcon(String icon) {
		this.icon=icon == null ? icon : icon.trim();
	}

	public String getIcon() {
		return icon;
	}

	public void setNickname(String nickname) {
		this.nickname=nickname == null ? nickname : nickname.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setImg(String img) {
		this.img=img == null ? img : img.trim();
	}

	public String getImg() {
		return img;
	}

	public void setStatus(Integer status) {
		this.status=status;
	}

	public Integer getStatus() {
		return status;
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

}
