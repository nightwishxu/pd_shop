package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.AuthEnterpriseMapper;
import com.paidang.dao.model.AuthEnterprise;
import com.paidang.dao.model.AuthEnterpriseExample;
import com.paidang.daoEx.AuthEnterpriseMapperEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthEnterpriseService {
	@Autowired
	private AuthEnterpriseMapper authEnterpriseMapper;
	@Autowired
	private AuthEnterpriseMapperEx authEnterpriseMapperEx;

	public int countByExample(AuthEnterpriseExample example) {
		return this.authEnterpriseMapper.countByExample(example);
	}

	public AuthEnterprise selectByPrimaryKey(Integer id) {
		return this.authEnterpriseMapper.selectByPrimaryKey(id);
	}

	public List<AuthEnterprise> selectByExample(AuthEnterpriseExample example) {
		return this.authEnterpriseMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.authEnterpriseMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(AuthEnterprise record) {
		return this.authEnterpriseMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(AuthEnterprise record) {
		return this.authEnterpriseMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(AuthEnterpriseExample example) {
		return this.authEnterpriseMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(AuthEnterprise record, AuthEnterpriseExample example) {
		return this.authEnterpriseMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(AuthEnterprise record, AuthEnterpriseExample example) {
		return this.authEnterpriseMapper.updateByExample(record, example);
	}

	public int insert(AuthEnterprise record) {
		return this.authEnterpriseMapper.insert(record);
	}

	public int insertSelective(AuthEnterprise record) {
		return this.authEnterpriseMapper.insertSelective(record);
	}

	public List<AuthEnterprise> selectByWrapper(EntityWrapper<AuthEnterprise> wrapper) {
		return this.authEnterpriseMapper.selectByWrapper(wrapper);
	}

	public int changeStateByPrimaryKey(AuthEnterprise authEnterprise) {
		return this.authEnterpriseMapperEx.changeStateByPrimaryKey(authEnterprise);
	}

	/**
	 * 设置当前人之前的认证无效
	 * @param userId
	 */
	public void setAuthInvalid(int userId){
		this.authEnterpriseMapperEx.setAuthInvalid(userId);
	}
}
