package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.UserReturnAddressMapper;
import com.paidang.dao.model.UserReturnAddress;
import com.paidang.dao.model.UserReturnAddressExample;
import com.paidang.daoEx.UserReturnAddressMapperEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReturnAddressService {
	@Autowired
	private UserReturnAddressMapper userReturnAddressMapper;
	@Autowired
	private UserReturnAddressMapperEx userReturnAddressMapperEx;

	public int countByExample(UserReturnAddressExample example) {
		return this.userReturnAddressMapper.countByExample(example);
	}

	public UserReturnAddress selectByPrimaryKey(Integer id) {
		return this.userReturnAddressMapper.selectByPrimaryKey(id);
	}

	public List<UserReturnAddress> selectByExample(UserReturnAddressExample example) {
		return this.userReturnAddressMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.userReturnAddressMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(UserReturnAddress record) {
		return this.userReturnAddressMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(UserReturnAddress record) {
		return this.userReturnAddressMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(UserReturnAddressExample example) {
		return this.userReturnAddressMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(UserReturnAddress record, UserReturnAddressExample example) {
		return this.userReturnAddressMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(UserReturnAddress record, UserReturnAddressExample example) {
		return this.userReturnAddressMapper.updateByExample(record, example);
	}

	public int insert(UserReturnAddress record) {
		return this.userReturnAddressMapper.insert(record);
	}

	public int insertSelective(UserReturnAddress record) {
		return this.userReturnAddressMapper.insertSelective(record);
	}

	public List<UserReturnAddress> selectByWrapper(EntityWrapper<UserReturnAddress> wrapper) {
		return this.userReturnAddressMapper.selectByWrapper(wrapper);
	}

	public int updateNotDefault(int userId){
		return this.userReturnAddressMapperEx.updateNotDefault(userId);
	}

	public int updateNotDefaultOrg(int orgId){
		return this.userReturnAddressMapperEx.updateNotDefaultOrg(orgId);
	}

	public int updateDefault(int addressId){
		return this.userReturnAddressMapperEx.updateDefault(addressId);
	}

}
