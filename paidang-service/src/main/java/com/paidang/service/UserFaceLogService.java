package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.UserFaceLogMapper;
import com.paidang.dao.model.UserFaceLog;
import com.paidang.dao.model.UserFaceLogExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFaceLogService {
	@Autowired
	private UserFaceLogMapper userFaceLogMapper;

	public int countByExample(UserFaceLogExample example) {
		return this.userFaceLogMapper.countByExample(example);
	}

	public UserFaceLog selectByPrimaryKey(Integer id) {
		return this.userFaceLogMapper.selectByPrimaryKey(id);
	}

	public List<UserFaceLog> selectByExample(UserFaceLogExample example) {
		return this.userFaceLogMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.userFaceLogMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(UserFaceLog record) {
		return this.userFaceLogMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(UserFaceLog record) {
		return this.userFaceLogMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(UserFaceLogExample example) {
		return this.userFaceLogMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(UserFaceLog record, UserFaceLogExample example) {
		return this.userFaceLogMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(UserFaceLog record, UserFaceLogExample example) {
		return this.userFaceLogMapper.updateByExample(record, example);
	}

	public int insert(UserFaceLog record) {
		return this.userFaceLogMapper.insert(record);
	}

	public int insertSelective(UserFaceLog record) {
		return this.userFaceLogMapper.insertSelective(record);
	}

	public List<UserFaceLog> selectByWrapper(EntityWrapper<UserFaceLog> wrapper) {
		return this.userFaceLogMapper.selectByWrapper(wrapper);
	}


	public UserFaceLog getByTxsn(String txsn){
		UserFaceLogExample example = new UserFaceLogExample();
		example.createCriteria().andTxsnEqualTo(txsn);
		List<UserFaceLog> userFaceLogs = userFaceLogMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(userFaceLogs)){
			return userFaceLogs.get(0);
		}
		return null;
	}

}
