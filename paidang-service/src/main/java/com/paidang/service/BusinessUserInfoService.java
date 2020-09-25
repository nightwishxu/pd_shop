package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.BusinessUserInfoMapper;
import com.paidang.dao.model.BusinessUserInfo;
import com.paidang.dao.model.BusinessUserInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessUserInfoService {
	@Autowired
	private BusinessUserInfoMapper businessUserInfoMapper;

	public int countByExample(BusinessUserInfoExample example) {
		return this.businessUserInfoMapper.countByExample(example);
	}

	public BusinessUserInfo selectByPrimaryKey(Integer id) {
		return this.businessUserInfoMapper.selectByPrimaryKey(id);
	}

	public List<BusinessUserInfo> selectByExample(BusinessUserInfoExample example) {
		return this.businessUserInfoMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.businessUserInfoMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(BusinessUserInfo record) {
		return this.businessUserInfoMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(BusinessUserInfo record) {
		return this.businessUserInfoMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(BusinessUserInfoExample example) {
		return this.businessUserInfoMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(BusinessUserInfo record, BusinessUserInfoExample example) {
		return this.businessUserInfoMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(BusinessUserInfo record, BusinessUserInfoExample example) {
		return this.businessUserInfoMapper.updateByExample(record, example);
	}

	public int insert(BusinessUserInfo record) {
		return this.businessUserInfoMapper.insert(record);
	}

	public int insertSelective(BusinessUserInfo record) {
		return this.businessUserInfoMapper.insertSelective(record);
	}

	public List<BusinessUserInfo> selectByWrapper(EntityWrapper<BusinessUserInfo> wrapper) {
		return this.businessUserInfoMapper.selectByWrapper(wrapper);
	}

}
