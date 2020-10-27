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
@Table(name="t_private_message")
public class PrivateMessage implements Serializable {

	/**
	 *主键Id
	 */
	@ApiModelProperty(value="主键Id")
	@ApiParam(value="主键Id")
	@Column(name = "id")
	private Integer id;

	/**
	 *发送者Id
	 */
	@ApiModelProperty(value="发送者Id")
	@ApiParam(value="发送者Id")
	@Column(name = "user_id")
	private Integer userId;

	/**
	 *接受者Id
	 */
	@ApiModelProperty(value="接受者Id")
	@ApiParam(value="接受者Id")
	@Column(name = "friend_id")
	private Integer friendId;

	/**
	 *发送者id
	 */
	@ApiModelProperty(value="发送者id")
	@ApiParam(value="发送者id")
	@Column(name = "sender_id")
	private Integer senderId;

	/**
	 *接受者Id
	 */
	@ApiModelProperty(value="接受者Id")
	@ApiParam(value="接受者Id")
	@Column(name = "receiver_id")
	private Integer receiverId;

	/**
	 *1 文字 2图片
	 */
	@ApiModelProperty(value="1 文字 2图片")
	@ApiParam(value="1 文字 2图片")
	@Column(name = "type")
	private Integer type;

	/**
	 *消息类型,1：普通消息 2：系统消息
	 */
	@ApiModelProperty(value="消息类型,1：普通消息 2：系统消息")
	@ApiParam(value="消息类型,1：普通消息 2：系统消息")
	@Column(name = "message_type")
	private Integer messageType;

	/**
	 *消息内容
	 */
	@ApiModelProperty(value="消息内容")
	@ApiParam(value="消息内容")
	@Column(name = "message_content")
	private String messageContent;

	/**
	 *消息发送时间
	 */
	@ApiModelProperty(value="消息发送时间")
	@ApiParam(value="消息发送时间")
	@Column(name = "send_time")
	private java.util.Date sendTime;

	/**
	 *消息状态 1：未读 2：已读 3：删除
	 */
	@ApiModelProperty(value="消息状态 1：未读 2：已读 3：删除")
	@ApiParam(value="消息状态 1：未读 2：已读 3：删除")
	@Column(name = "status")
	private Integer status;

	/**
	 *删除时间
	 */
	@ApiModelProperty(value="删除时间")
	@ApiParam(value="删除时间")
	@Column(name = "delete_time")
	private java.util.Date deleteTime;

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

	public void setFriendId(Integer friendId) {
		this.friendId=friendId;
	}

	public Integer getFriendId() {
		return friendId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId=senderId;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId=receiverId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setMessageType(Integer messageType) {
		this.messageType=messageType;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent=messageContent == null ? messageContent : messageContent.trim();
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setSendTime(java.util.Date sendTime) {
		this.sendTime=sendTime;
	}

	public java.util.Date getSendTime() {
		return sendTime;
	}

	public void setStatus(Integer status) {
		this.status=status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setDeleteTime(java.util.Date deleteTime) {
		this.deleteTime=deleteTime;
	}

	public java.util.Date getDeleteTime() {
		return deleteTime;
	}

}
