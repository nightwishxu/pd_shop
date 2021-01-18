package com.paidang.daoEx.model;

import com.paidang.dao.model.UserWithdrawApply;

/**
@author sun
*/
public class UserWithdrawApplyEx extends UserWithdrawApply {
	private String userName;

	private String nickName;

	private String account;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}