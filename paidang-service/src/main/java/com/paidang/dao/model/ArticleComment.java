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
@Table(name="t_article_comment")
public class ArticleComment implements Serializable {

	/**
	 *
	 */
	@ApiModelProperty(value="")
	@ApiParam(value="")
	@Column(name = "id")
	private Integer id;

	/**
	 *主题id
	 */
	@ApiModelProperty(value="主题id")
	@ApiParam(value="主题id")
	@Column(name = "article_id")
	private Integer articleId;

	/**
	 *主题作者id
	 */
	@ApiModelProperty(value="主题作者id")
	@ApiParam(value="主题作者id")
	@Column(name = "author_id")
	private Integer authorId;

	/**
	 *1为文章
	 */
	@ApiModelProperty(value="1为文章")
	@ApiParam(value="1为文章")
	@Column(name = "type")
	private Integer type;

	/**
	 *评论内容
	 */
	@ApiModelProperty(value="评论内容")
	@ApiParam(value="评论内容")
	@Column(name = "content")
	private String content;

	/**
	 *评论者id，一般为会员表的id
	 */
	@ApiModelProperty(value="评论者id，一般为会员表的id")
	@ApiParam(value="评论者id，一般为会员表的id")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *冗余用户昵称
	 */
	@ApiModelProperty(value="冗余用户昵称")
	@ApiParam(value="冗余用户昵称")
	@Column(name = "nick_name")
	private String nickName;

	/**
	 *冗余用户头像
	 */
	@ApiModelProperty(value="冗余用户头像")
	@ApiParam(value="冗余用户头像")
	@Column(name = "head_img")
	private String headImg;

	/**
	 *是否置顶评论，1为置顶，0为不置顶
	 */
	@ApiModelProperty(value="是否置顶评论，1为置顶，0为不置顶")
	@ApiParam(value="是否置顶评论，1为置顶，0为不置顶")
	@Column(name = "is_top")
	private Integer isTop;

	/**
	 *是否为热评，1为热评
	 */
	@ApiModelProperty(value="是否为热评，1为热评")
	@ApiParam(value="是否为热评，1为热评")
	@Column(name = "is_hot")
	private Integer isHot;

	/**
	 *评论被点赞的次数
	 */
	@ApiModelProperty(value="评论被点赞的次数")
	@ApiParam(value="评论被点赞的次数")
	@Column(name = "like_num")
	private Integer likeNum;

	/**
	 *评论被回复的次数
	 */
	@ApiModelProperty(value="评论被回复的次数")
	@ApiParam(value="评论被回复的次数")
	@Column(name = "reply_num")
	private Integer replyNum;

	/**
	 *是否回复0没有，1已回复
	 */
	@ApiModelProperty(value="是否回复0没有，1已回复")
	@ApiParam(value="是否回复0没有，1已回复")
	@Column(name = "is_reply")
	private Integer isReply;

	/**
	 *评论状态，-1为删除，0为待审核，1为已发布
	 */
	@ApiModelProperty(value="评论状态，-1为删除，0为待审核，1为已发布")
	@ApiParam(value="评论状态，-1为删除，0为待审核，1为已发布")
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
	 *创建时间1
	 */
	@ApiModelProperty(value="创建时间1")
	@ApiParam(value="创建时间1")
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 *0为普通回复，1为后台管理员回复,2作者回复
	 */
	@ApiModelProperty(value="0为普通回复，1为后台管理员回复,2作者回复")
	@ApiParam(value="0为普通回复，1为后台管理员回复,2作者回复")
	@Column(name = "is_author")
	private Integer isAuthor;

	/**
	 *举报状态 0未举报 1举报
	 */
	@ApiModelProperty(value="举报状态 0未举报 1举报")
	@ApiParam(value="举报状态 0未举报 1举报")
	@Column(name = "is_report")
	private Integer isReport;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
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

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setContent(String content) {
		this.content=content == null ? content : content.trim();
	}

	public String getContent() {
		return content;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setNickName(String nickName) {
		this.nickName=nickName == null ? nickName : nickName.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setHeadImg(String headImg) {
		this.headImg=headImg == null ? headImg : headImg.trim();
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setIsTop(Integer isTop) {
		this.isTop=isTop;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsHot(Integer isHot) {
		this.isHot=isHot;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum=likeNum;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum=replyNum;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

	public void setIsReply(Integer isReply) {
		this.isReply=isReply;
	}

	public Integer getIsReply() {
		return isReply;
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

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setIsAuthor(Integer isAuthor) {
		this.isAuthor=isAuthor;
	}

	public Integer getIsAuthor() {
		return isAuthor;
	}

	public void setIsReport(Integer isReport) {
		this.isReport=isReport;
	}

	public Integer getIsReport() {
		return isReport;
	}

}
