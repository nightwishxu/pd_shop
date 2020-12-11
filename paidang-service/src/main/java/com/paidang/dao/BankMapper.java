package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.Bank;
import com.paidang.dao.model.BankExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankMapper {
	int countByExample(BankExample example);

	int deleteByExample(BankExample example);

	int deleteByPrimaryKey(String id);

	int insert(Bank record);

	int insertSelective(Bank record);

	List<Bank> selectByExample(BankExample example);

	Bank selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") Bank record, @Param("example") BankExample example);

	int updateByExample(@Param("record") Bank record, @Param("example") BankExample example);

	int updateByPrimaryKeySelective(Bank record);

	int updateByPrimaryKey(Bank record);

	List<Bank> selectByWrapper(EntityWrapper<Bank> wrapper);

}
