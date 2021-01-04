package com.paidang.daoEx.model;

import com.paidang.dao.model.VideoOnline;

/**
@author sun
*/
public class VideoOnlineEx extends VideoOnline {
    private Integer commCount;

    private Integer praiseStatus;

    public Integer getCommCount() {
        return commCount;
    }

    public void setCommCount(Integer commCount) {
        this.commCount = commCount;
    }


    public Integer getPraiseStatus() {
        return praiseStatus;
    }

    public void setPraiseStatus(Integer praiseStatus) {
        this.praiseStatus = praiseStatus;
    }
}