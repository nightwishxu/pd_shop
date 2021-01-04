package com.paidang.daoEx;

import com.paidang.dao.model.PawnTicket;
import com.paidang.daoEx.model.PawnTicketEx;
import com.paidang.domain.qo.PawnTicketQo;

import java.util.List;

/**
@author sun
*/
public interface PawnTicketMapperEx {

    List<PawnTicketEx> findList(PawnTicketQo qo);
}
