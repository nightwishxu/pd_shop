package com.item.dao;

import com.base.mybatis.plus.EntityWrapper;

import com.item.dao.model.PayLog;
import com.item.dao.model.PayLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayLogMapper {
	int countByExample(PayLogExample example);

	int deleteByExample(PayLogExample example);

	int deleteByPrimaryKey(String id);

	int insert(PayLog record);

	int insertSelective(PayLog record);

	List<PayLog> selectByExample(PayLogExample example);

	PayLog selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") PayLog record, @Param("example") PayLogExample example);

	int updateByExample(@Param("record") PayLog record, @Param("example") PayLogExample example);

	int updateByPrimaryKeySelective(PayLog record);

	int updateByPrimaryKey(PayLog record);

	List<PayLog> selectByWrapper(EntityWrapper<PayLog> wrapper);

}
