package com.paidang.dao;

import java.util.List;
import com.base.mybatis.plus.EntityWrapper;
import org.apache.ibatis.annotations.Param;

import com.paidang.dao.model.PawnTicket;
import com.paidang.dao.model.PawnTicketExample;

public interface PawnTicketMapper {
	int countByExample(PawnTicketExample example);

	int deleteByExample(PawnTicketExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(PawnTicket record);

	int insertSelective(PawnTicket record);

	List<PawnTicket> selectByExample(PawnTicketExample example);

	PawnTicket selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") PawnTicket record,@Param("example") PawnTicketExample example);

	int updateByExample(@Param("record") PawnTicket record,@Param("example") PawnTicketExample example);

	int updateByPrimaryKeySelective(PawnTicket record);

	int updateByPrimaryKey(PawnTicket record);

	List<PawnTicket> selectByWrapper(EntityWrapper<PawnTicket> wrapper);

}
