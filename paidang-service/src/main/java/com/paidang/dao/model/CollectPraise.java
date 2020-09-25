package com.paidang.dao.model;



/**
 *
 */
public class CollectPraise {

	/**
	 *
	 */
	private Integer id;

	/**
	 *
	 */
	private Integer userId;

	/**
	 *
	 */
	private Integer userGoodsId;

	/**
	 *
	 */
	private Integer ownerId;

	/**
	 *图片
	 */
	private String img;

	/**
	 *
	 */
	private Integer status;

	/**
	 *0点赞1收藏
	 */
	private Integer type;

	/**
	 *
	 */
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

	public void setUserGoodsId(Integer userGoodsId) {
		this.userGoodsId=userGoodsId;
	}

	public Integer getUserGoodsId() {
		return userGoodsId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId=ownerId;
	}

	public Integer getOwnerId() {
		return ownerId;
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
