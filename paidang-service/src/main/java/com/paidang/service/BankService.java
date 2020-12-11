package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.BankMapper;
import com.paidang.dao.model.Bank;
import com.paidang.dao.model.BankExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
	@Autowired
	private BankMapper bankMapper;

	public int countByExample(BankExample example) {
		return this.bankMapper.countByExample(example);
	}

	public Bank selectByPrimaryKey(String id) {
		return this.bankMapper.selectByPrimaryKey(id);
	}

	public List<Bank> selectByExample(BankExample example) {
		return this.bankMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(String id) {
		return this.bankMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Bank record) {
		return this.bankMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Bank record) {
		return this.bankMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(BankExample example) {
		return this.bankMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(Bank record, BankExample example) {
		return this.bankMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(Bank record, BankExample example) {
		return this.bankMapper.updateByExample(record, example);
	}

	public int insert(Bank record) {
		return this.bankMapper.insert(record);
	}

	public int insertSelective(Bank record) {
		return this.bankMapper.insertSelective(record);
	}

	public List<Bank> selectByWrapper(EntityWrapper<Bank> wrapper) {
		return this.bankMapper.selectByWrapper(wrapper);
	}

}
