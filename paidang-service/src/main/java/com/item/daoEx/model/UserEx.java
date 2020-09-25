package com.item.daoEx.model;

import com.item.dao.model.User;

public class UserEx extends User{
    private int authId;
    private String authType;

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }
}