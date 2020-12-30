package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.OrgAmountLog;
import com.paidang.dao.model.OrgAmountLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrgAmountLogMapper {
	int countByExample(OrgAmountLogExample example);

	int deleteByExample(OrgAmountLogExample example);

	int deleteByPrimaryKey(String id);

	int insert(OrgAmountLog record);

	int insertSelective(OrgAmountLog record);

	List<OrgAmountLog> selectByExample(OrgAmountLogExample example);

	OrgAmountLog selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") OrgAmountLog record, @Param("example") OrgAmountLogExample example);

	int updateByExample(@Param("record") OrgAmountLog record, @Param("example") OrgAmountLogExample example);

	int updateByPrimaryKeySelective(OrgAmountLog record);

	int updateByPrimaryKey(OrgAmountLog record);

	List<OrgAmountLog> selectByWrapper(EntityWrapper<OrgAmountLog> wrapper);

}
