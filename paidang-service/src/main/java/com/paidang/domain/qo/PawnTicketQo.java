package com.paidang.domain.qo;

import com.paidang.dao.model.PawnTicket;

import java.util.List;

public class PawnTicketQo  extends PawnTicket {

    private List<String> statusList;


    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }
}
