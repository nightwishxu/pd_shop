package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.IntegralLog;
import com.paidang.dao.model.IntegralLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntegralLogMapper {
	int countByExample(IntegralLogExample example);

	int deleteByExample(IntegralLogExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(IntegralLog record);

	int insertSelective(IntegralLog record);

	List<IntegralLog> selectByExample(IntegralLogExample example);

	IntegralLog selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") IntegralLog record,@Param("example") IntegralLogExample example);

	int updateByExample(@Param("record") IntegralLog record,@Param("example") IntegralLogExample example);

	int updateByPrimaryKeySelective(IntegralLog record);

	int updateByPrimaryKey(IntegralLog record);

	List<IntegralLog> selectByWrapper(EntityWrapper<IntegralLog> wrapper);

}
