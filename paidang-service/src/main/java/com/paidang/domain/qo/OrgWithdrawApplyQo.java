package com.paidang.domain.qo;

import com.paidang.dao.model.OrgWithdrawApply;

import java.util.Date;

public class OrgWithdrawApplyQo extends OrgWithdrawApply
{
    private Date startTime;

    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
