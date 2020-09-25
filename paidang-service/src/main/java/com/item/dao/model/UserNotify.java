package com.item.dao.model;



public class UserNotify {

	private Integer id;

	private Integer userId;

	private Integer nid;

	private Integer type;

	private String title;

	private String content;

	private Integer redirectType;

	private String redirectContent;

	private Integer isRead;

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

	public void setNid(Integer nid) {
		this.nid=nid;
	}

	public Integer getNid() {
		return nid;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
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

	public void setRedirectType(Integer redirectType) {
		this.redirectType=redirectType;
	}

	public Integer getRedirectType() {
		return redirectType;
	}

	public void setRedirectContent(String redirectContent) {
		this.redirectContent=redirectContent == null ? redirectContent : redirectContent.trim();
	}

	public String getRedirectContent() {
		return redirectContent;
	}

	public void setIsRead(Integer isRead) {
		this.isRead=isRead;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

}
