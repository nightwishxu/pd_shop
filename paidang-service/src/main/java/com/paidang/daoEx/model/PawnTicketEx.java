package com.paidang.daoEx.model;

import com.paidang.dao.model.PawnTicket;
import lombok.Data;

/**
@author sun
*/
@Data
public class PawnTicketEx extends PawnTicket {

    private Integer pawnId;

    private Integer repawnId;


    public Integer getPawnId() {
        return pawnId;
    }

    public void setPawnId(Integer pawnId) {
        this.pawnId = pawnId;
    }

    public Integer getRepawnId() {
        return repawnId;
    }

    public void setRepawnId(Integer repawnId) {
        this.repawnId = repawnId;
    }
}
