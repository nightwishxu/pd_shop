package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.UserAmountLog;
import com.paidang.dao.model.UserAmountLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAmountLogMapper {
	int countByExample(UserAmountLogExample example);

	int deleteByExample(UserAmountLogExample example);

	int deleteByPrimaryKey(String id);

	int insert(UserAmountLog record);

	int insertSelective(UserAmountLog record);

	List<UserAmountLog> selectByExample(UserAmountLogExample example);

	UserAmountLog selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") UserAmountLog record, @Param("example") UserAmountLogExample example);

	int updateByExample(@Param("record") UserAmountLog record, @Param("example") UserAmountLogExample example);

	int updateByPrimaryKeySelective(UserAmountLog record);

	int updateByPrimaryKey(UserAmountLog record);

	List<UserAmountLog> selectByWrapper(EntityWrapper<UserAmountLog> wrapper);

}
