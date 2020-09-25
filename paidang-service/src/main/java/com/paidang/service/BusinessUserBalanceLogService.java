package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.BusinessUserBalanceLogMapper;
import com.paidang.dao.model.BusinessUserBalanceLog;
import com.paidang.dao.model.BusinessUserBalanceLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessUserBalanceLogService {
	@Autowired
	private BusinessUserBalanceLogMapper businessUserBalanceLogMapper;

	public int countByExample(BusinessUserBalanceLogExample example) {
		return this.businessUserBalanceLogMapper.countByExample(example);
	}

	public BusinessUserBalanceLog selectByPrimaryKey(Integer id) {
		return this.businessUserBalanceLogMapper.selectByPrimaryKey(id);
	}

	public List<BusinessUserBalanceLog> selectByExample(BusinessUserBalanceLogExample example) {
		return this.businessUserBalanceLogMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.businessUserBalanceLogMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(BusinessUserBalanceLog record) {
		return this.businessUserBalanceLogMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(BusinessUserBalanceLog record) {
		return this.businessUserBalanceLogMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(BusinessUserBalanceLogExample example) {
		return this.businessUserBalanceLogMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(BusinessUserBalanceLog record, BusinessUserBalanceLogExample example) {
		return this.businessUserBalanceLogMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(BusinessUserBalanceLog record, BusinessUserBalanceLogExample example) {
		return this.businessUserBalanceLogMapper.updateByExample(record, example);
	}

	public int insert(BusinessUserBalanceLog record) {
		return this.businessUserBalanceLogMapper.insert(record);
	}

	public int insertSelective(BusinessUserBalanceLog record) {
		return this.businessUserBalanceLogMapper.insertSelective(record);
	}

	public List<BusinessUserBalanceLog> selectByWrapper(EntityWrapper<BusinessUserBalanceLog> wrapper) {
		return this.businessUserBalanceLogMapper.selectByWrapper(wrapper);
	}

}
