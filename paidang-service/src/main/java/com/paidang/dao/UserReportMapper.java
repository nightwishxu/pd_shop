package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.UserReport;
import com.paidang.dao.model.UserReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserReportMapper {
	int countByExample(UserReportExample example);

	int deleteByExample(UserReportExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(UserReport record);

	int insertSelective(UserReport record);

	List<UserReport> selectByExample(UserReportExample example);

	UserReport selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") UserReport record,@Param("example") UserReportExample example);

	int updateByExample(@Param("record") UserReport record,@Param("example") UserReportExample example);

	int updateByPrimaryKeySelective(UserReport record);

	int updateByPrimaryKey(UserReport record);

	List<UserReport> selectByWrapper(EntityWrapper<UserReport> wrapper);

}
