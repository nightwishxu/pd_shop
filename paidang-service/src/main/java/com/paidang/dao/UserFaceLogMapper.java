package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.UserFaceLog;
import com.paidang.dao.model.UserFaceLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFaceLogMapper {
	int countByExample(UserFaceLogExample example);

	int deleteByExample(UserFaceLogExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(UserFaceLog record);

	int insertSelective(UserFaceLog record);

	List<UserFaceLog> selectByExample(UserFaceLogExample example);

	UserFaceLog selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") UserFaceLog record, @Param("example") UserFaceLogExample example);

	int updateByExample(@Param("record") UserFaceLog record, @Param("example") UserFaceLogExample example);

	int updateByPrimaryKeySelective(UserFaceLog record);

	int updateByPrimaryKey(UserFaceLog record);

	List<UserFaceLog> selectByWrapper(EntityWrapper<UserFaceLog> wrapper);

}
