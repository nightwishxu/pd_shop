package com.api;

import com.base.api.IMError;

public enum MEnumError implements IMError {
	SERVER_BUSY_ERROR(10000,"服务器忙，请稍后再试"),
	UPLOAD_FAILURE_ERROR(10001,"文件上传失败"),
	USER_NOEXIST_ERROR(10002,"用户不存在"),
	LOGIN_FAILURE_ERROR(10003,"用户名或密码不正确"),
	PHONE_EXISTS_ERROR(10004,"手机号已存在"),
	NICK_EXISTS_ERROR(10004,"昵称已存在"),
	PHONE_NOTEXISTS_ERROR(10005,"手机号不存在"),
	MOBILE_CODE_ERROR(10006,"短信验证码错误"),
	CREATE_ACCOUNT_ERROR(10007,"注册失败"),
	ACCOUNT_STOP_ERROR(10008,"账号已被封停"),
	UPDATE_ACCOUNT_ERROR(10009,"账号更新失败"),
	PASSWORD_FALSE_ERROR(10010,"密码错误"),
	OAUTHLOGIN_FAILURE_ERROR(10011,"登录失败"),
	CONTENT_NOEXIST_ERROR(10012,"内容不存在"),
	NICK_LEN_ERROR(10013,"昵称太长了"),
	PAY_TYPE_ERRPR(10014,"支付方式错误"),
	OPEN_ERROR(-1,"开发中"),
	APP_VERSION_NULL(1005,"APP版本不存在"),



	SMS_ERROR_4086(10100,"发送失败，短时间内该号码提交次数过多，如有疑问请联系客服。"),
	SMS_ERROR(20101,"短信发送失败"),

	CASH_NOT_ENOUGHT(10901,"余额不足"),
	WM_ORDER_NOTEXISTS(10902,"订单不存在"),
	WM_ORDER_CANDOTHIS(10903,"订单无法做此项操作"),

	PRAISE_AGAIN_ERROR(20001,"您已经赞过"),

	NEWS_NOT_EXIST(20002,"新闻不存在"),


	//商家端
	MULTIPLE_SUBMISSIONS(40001,"存在多次提交认证"),
	NONE_SUBMISSIONS(40002,"您还未提交认证"),
	APPROVAL_ERROR(40003,"申请审核发生异常"),
	DISMOUNT_STATE(40004,"商品已经处于下架状态或者还是待上架状态"),
	ONLINE_STATE(40005,"商品已经处于上架状态"),
	DELETE_STATE(40006,"商品已经已被删除"),



	/*
	 * 机构
	 * */
	OPER_FAILURE_ERROE(30000,"操作失败"),
	NUMINPUT_ILLEGAL_ERROR(30001,"您的输入不正确"),
	PAWNTICKETCODE_OCCUPPIED(30002,"您输入的当号已被使用"),
	PAWNTICKETCODE_NOTSAME(30006,"对同一物品的竞拍当号要保持一致"),
	PLATFORM_ALREADY_PAY(30003,"服务费已缴纳"),
	BANKCARD_BIND_ERROR(30004,"银行卡绑定失败"),
	NOTIFY_ADMIN_ERROR(30005,"通知平台邮寄物品出错，请联系开发人员"),
	BANKCARD_ALREADY_BIND(30007,"此卡已被其他机构绑定！"),
	BANKCARD_ALREADY_INSERT(30008,"您已经绑定过该卡"),
	PT_ACCOUNT_CANNOT_BID(30009,"您是登陆的是平台账号，无法参与竞拍"),
	NO_ORG_BANKCARD_BIND(30010,"您还未绑定银行卡，请先去绑定银行卡"),
	PAY_OUT_OF_TERM(30011,"您未能在中标后规定时间内支付贷款，物品已失效！"),
	BID_OUT_OF_TIME(30022,"该当品已过期，不能再对其竞拍！"),
	ORG_TOO_MANY_BANK_CARD(30012,"您只能绑定一张银行卡"),
	PAY_PLATFORM_ZERO(30013,"您的金额是0元，无法支付"),

	;



	/** 错误编码 */
	private int errorCode;

	/** 错误信息 */
	private String errorMsg;
	
	/**
	 * 构造函数
	 */
	private MEnumError(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * @return int 错误代码
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @return String 错误信息
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
}
