package com.paidang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.mybatis.plus.EntityWrapper;

import com.paidang.dao.OutOrderMapper;
import com.paidang.dao.model.OutOrder;
import com.paidang.dao.model.OutOrderExample;

@Service
public class OutOrderService {
	@Autowired
	private OutOrderMapper outOrderMapper;

	public int countByExample(OutOrderExample example) {
		return this.outOrderMapper.countByExample(example);
	}

	public OutOrder selectByPrimaryKey(Integer id) {
		return this.outOrderMapper.selectByPrimaryKey(id);
	}

	public List<OutOrder> selectByExample(OutOrderExample example) {
		return this.outOrderMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.outOrderMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(OutOrder record) {
		return this.outOrderMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(OutOrder record) {
		return this.outOrderMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(OutOrderExample example) {
		return this.outOrderMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(OutOrder record, OutOrderExample example) {
		return this.outOrderMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(OutOrder record, OutOrderExample example) {
		return this.outOrderMapper.updateByExample(record, example);
	}

	public int insert(OutOrder record) {
		return this.outOrderMapper.insert(record);
	}

	public int insertSelective(OutOrder record) {
		return this.outOrderMapper.insertSelective(record);
	}

	public List<OutOrder> selectByWrapper(EntityWrapper<OutOrder> wrapper) {
		return this.outOrderMapper.selectByWrapper(wrapper);
	}

}
