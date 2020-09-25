package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.BusinessUserBalanceLog;
import com.paidang.dao.model.BusinessUserBalanceLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessUserBalanceLogMapper {
	int countByExample(BusinessUserBalanceLogExample example);

	int deleteByExample(BusinessUserBalanceLogExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(BusinessUserBalanceLog record);

	int insertSelective(BusinessUserBalanceLog record);

	List<BusinessUserBalanceLog> selectByExample(BusinessUserBalanceLogExample example);

	BusinessUserBalanceLog selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") BusinessUserBalanceLog record, @Param("example") BusinessUserBalanceLogExample example);

	int updateByExample(@Param("record") BusinessUserBalanceLog record, @Param("example") BusinessUserBalanceLogExample example);

	int updateByPrimaryKeySelective(BusinessUserBalanceLog record);

	int updateByPrimaryKey(BusinessUserBalanceLog record);

	List<BusinessUserBalanceLog> selectByWrapper(EntityWrapper<BusinessUserBalanceLog> wrapper);

}
