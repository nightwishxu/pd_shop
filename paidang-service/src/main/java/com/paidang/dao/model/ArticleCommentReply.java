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
@Table(name="t_article_comment_reply")
public class ArticleCommentReply implements Serializable {

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
	 *1为回复评论，2为回复别人的回复
	 */
	@ApiModelProperty(value="1为回复评论，2为回复别人的回复")
	@ApiParam(value="1为回复评论，2为回复别人的回复")
	@Column(name = "reply_type")
	private Integer replyType;

	/**
	 *回复目标id，reply_type为1时，是comment_id，reply_type为2时为回复表的id
	 */
	@ApiModelProperty(value="回复目标id，reply_type为1时，是comment_id，reply_type为2时为回复表的id")
	@ApiParam(value="回复目标id，reply_type为1时，是comment_id，reply_type为2时为回复表的id")
	@Column(name = "reply_id")
	private Integer replyId;

	/**
	 *回复内容
	 */
	@ApiModelProperty(value="回复内容")
	@ApiParam(value="回复内容")
	@Column(name = "content")
	private String content;

	/**
	 *回复目标id
	 */
	@ApiModelProperty(value="回复目标id")
	@ApiParam(value="回复目标id")
	@Column(name = "to_uid")
	private Integer toUid;

	/**
	 *回复用户id
	 */
	@ApiModelProperty(value="回复用户id")
	@ApiParam(value="回复用户id")
	@Column(name = "from_uid")
	private Integer fromUid;

	/**
	 *回复者的头像
	 */
	@ApiModelProperty(value="回复者的头像")
	@ApiParam(value="回复者的头像")
	@Column(name = "from_thumb_img")
	private String fromThumbImg;

	/**
	 *回复者的昵称
	 */
	@ApiModelProperty(value="回复者的昵称")
	@ApiParam(value="回复者的昵称")
	@Column(name = "from_nickname")
	private String fromNickname;

	/**
	 *评论时间
	 */
	@ApiModelProperty(value="评论时间")
	@ApiParam(value="评论时间")
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *冗余回复对象的昵称
	 */
	@ApiModelProperty(value="冗余回复对象的昵称")
	@ApiParam(value="冗余回复对象的昵称")
	@Column(name = "to_nickname")
	private String toNickname;

	/**
	 *0隐藏1展示
	 */
	@ApiModelProperty(value="0隐藏1展示")
	@ApiParam(value="0隐藏1展示")
	@Column(name = "is_show")
	private Integer isShow;

	/**
	 *0为普通回复，1为后台管理员回复，2作者回复
	 */
	@ApiModelProperty(value="0为普通回复，1为后台管理员回复，2作者回复")
	@ApiParam(value="0为普通回复，1为后台管理员回复，2作者回复")
	@Column(name = "is_author")
	private Integer isAuthor;

	/**
	 *点赞数
	 */
	@ApiModelProperty(value="点赞数")
	@ApiParam(value="点赞数")
	@Column(name = "like_num")
	private Integer likeNum;

	/**
	 *评论状态，-1为删除，1为已发布
	 */
	@ApiModelProperty(value="评论状态，-1为删除，1为已发布")
	@ApiParam(value="评论状态，-1为删除，1为已发布")
	@Column(name = "status")
	private Integer status;

	/**
	 *举报状态 0未举报 1举报
	 */
	@ApiModelProperty(value="举报状态 0未举报 1举报")
	@ApiParam(value="举报状态 0未举报 1举报")
	@Column(name = "is_report")
	private Integer isReport;

	/**
	 *评论被回复的次数
	 */
	@ApiModelProperty(value="评论被回复的次数")
	@ApiParam(value="评论被回复的次数")
	@Column(name = "reply_num")
	private Integer replyNum;

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

	public void setReplyType(Integer replyType) {
		this.replyType=replyType;
	}

	public Integer getReplyType() {
		return replyType;
	}

	public void setReplyId(Integer replyId) {
		this.replyId=replyId;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setContent(String content) {
		this.content=content == null ? content : content.trim();
	}

	public String getContent() {
		return content;
	}

	public void setToUid(Integer toUid) {
		this.toUid=toUid;
	}

	public Integer getToUid() {
		return toUid;
	}

	public void setFromUid(Integer fromUid) {
		this.fromUid=fromUid;
	}

	public Integer getFromUid() {
		return fromUid;
	}

	public void setFromThumbImg(String fromThumbImg) {
		this.fromThumbImg=fromThumbImg == null ? fromThumbImg : fromThumbImg.trim();
	}

	public String getFromThumbImg() {
		return fromThumbImg;
	}

	public void setFromNickname(String fromNickname) {
		this.fromNickname=fromNickname == null ? fromNickname : fromNickname.trim();
	}

	public String getFromNickname() {
		return fromNickname;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setToNickname(String toNickname) {
		this.toNickname=toNickname == null ? toNickname : toNickname.trim();
	}

	public String getToNickname() {
		return toNickname;
	}

	public void setIsShow(Integer isShow) {
		this.isShow=isShow;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsAuthor(Integer isAuthor) {
		this.isAuthor=isAuthor;
	}

	public Integer getIsAuthor() {
		return isAuthor;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum=likeNum;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setStatus(Integer status) {
		this.status=status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setIsReport(Integer isReport) {
		this.isReport=isReport;
	}

	public Integer getIsReport() {
		return isReport;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum=replyNum;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

}
