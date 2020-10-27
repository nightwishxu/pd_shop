package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.UserBlackMapper;
import com.paidang.dao.model.UserBlack;
import com.paidang.dao.model.UserBlackExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBlackService {
	@Autowired
	private UserBlackMapper userBlackMapper;

	public int countByExample(UserBlackExample example) {
		return this.userBlackMapper.countByExample(example);
	}

	public UserBlack selectByPrimaryKey(Integer id) {
		return this.userBlackMapper.selectByPrimaryKey(id);
	}

	public List<UserBlack> selectByExample(UserBlackExample example) {
		return this.userBlackMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.userBlackMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(UserBlack record) {
		return this.userBlackMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(UserBlack record) {
		return this.userBlackMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(UserBlackExample example) {
		return this.userBlackMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(UserBlack record, UserBlackExample example) {
		return this.userBlackMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(UserBlack record, UserBlackExample example) {
		return this.userBlackMapper.updateByExample(record, example);
	}

	public int insert(UserBlack record) {
		return this.userBlackMapper.insert(record);
	}

	public int insertSelective(UserBlack record) {
		return this.userBlackMapper.insertSelective(record);
	}

	public List<UserBlack> selectByWrapper(EntityWrapper<UserBlack> wrapper) {
		return this.userBlackMapper.selectByWrapper(wrapper);
	}

}
