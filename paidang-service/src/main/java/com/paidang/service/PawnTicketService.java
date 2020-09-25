package com.paidang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.mybatis.plus.EntityWrapper;

import com.paidang.dao.PawnTicketMapper;
import com.paidang.dao.model.PawnTicket;
import com.paidang.dao.model.PawnTicketExample;

@Service
public class PawnTicketService {
	@Autowired
	private PawnTicketMapper pawnTicketMapper;

	public int countByExample(PawnTicketExample example) {
		return this.pawnTicketMapper.countByExample(example);
	}

	public PawnTicket selectByPrimaryKey(Integer id) {
		return this.pawnTicketMapper.selectByPrimaryKey(id);
	}

	public List<PawnTicket> selectByExample(PawnTicketExample example) {
		return this.pawnTicketMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.pawnTicketMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(PawnTicket record) {
		return this.pawnTicketMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(PawnTicket record) {
		return this.pawnTicketMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(PawnTicketExample example) {
		return this.pawnTicketMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(PawnTicket record, PawnTicketExample example) {
		return this.pawnTicketMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(PawnTicket record, PawnTicketExample example) {
		return this.pawnTicketMapper.updateByExample(record, example);
	}

	public int insert(PawnTicket record) {
		return this.pawnTicketMapper.insert(record);
	}

	public int insertSelective(PawnTicket record) {
		return this.pawnTicketMapper.insertSelective(record);
	}

	public List<PawnTicket> selectByWrapper(EntityWrapper<PawnTicket> wrapper) {
		return this.pawnTicketMapper.selectByWrapper(wrapper);
	}

}
