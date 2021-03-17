package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.AuthPersonalMapper;
import com.paidang.dao.model.AuthPersonal;
import com.paidang.dao.model.AuthPersonalExample;
import com.paidang.daoEx.AuthPersonalMapperEx;
import com.paidang.daoEx.model.AuthPersonalEx;
import com.paidang.domain.qo.AuthPersonalQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthPersonalService {
	@Autowired
	private AuthPersonalMapper authPersonalMapper;
	@Autowired
	private AuthPersonalMapperEx authPersonalMapperEx;

	public int countByExample(AuthPersonalExample example) {
		return this.authPersonalMapper.countByExample(example);
	}

	public AuthPersonal selectByPrimaryKey(Integer id) {
		return this.authPersonalMapper.selectByPrimaryKey(id);
	}

	public List<AuthPersonal> selectByExample(AuthPersonalExample example) {
		return this.authPersonalMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.authPersonalMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(AuthPersonal record) {
		return this.authPersonalMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(AuthPersonal record) {
		return this.authPersonalMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(AuthPersonalExample example) {
		return this.authPersonalMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(AuthPersonal record, AuthPersonalExample example) {
		return this.authPersonalMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(AuthPersonal record, AuthPersonalExample example) {
		return this.authPersonalMapper.updateByExample(record, example);
	}

	public int insert(AuthPersonal record) {
		return this.authPersonalMapper.insert(record);
	}

	public int insertSelective(AuthPersonal record) {
		return this.authPersonalMapper.insertSelective(record);
	}

	public List<AuthPersonal> selectByWrapper(EntityWrapper<AuthPersonal> wrapper) {
		return this.authPersonalMapper.selectByWrapper(wrapper);
	}

	public int changeStateByPrimaryKey(AuthPersonal authPersonal) {
		return this.authPersonalMapperEx.changeStateByPrimaryKey(authPersonal);
	}

	/**
	 * 设置当前人之前的认证无效
	 * @param userId
	 */
	public void setAuthInvalid(int userId){
		this.authPersonalMapperEx.setAuthInvalid(userId);
	}

	public List<AuthPersonalEx> findList(AuthPersonalQo qo){
		return authPersonalMapperEx.findList(qo);
	}

}
