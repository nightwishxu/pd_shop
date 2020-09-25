package com.paidang.dao;

import java.util.List;
import com.base.mybatis.plus.EntityWrapper;
import org.apache.ibatis.annotations.Param;

import com.paidang.dao.model.AuthPersonal;
import com.paidang.dao.model.AuthPersonalExample;

public interface AuthPersonalMapper {
	int countByExample(AuthPersonalExample example);

	int deleteByExample(AuthPersonalExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(AuthPersonal record);

	int insertSelective(AuthPersonal record);

	List<AuthPersonal> selectByExample(AuthPersonalExample example);

	AuthPersonal selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") AuthPersonal record,@Param("example") AuthPersonalExample example);

	int updateByExample(@Param("record") AuthPersonal record,@Param("example") AuthPersonalExample example);

	int updateByPrimaryKeySelective(AuthPersonal record);

	int updateByPrimaryKey(AuthPersonal record);

	List<AuthPersonal> selectByWrapper(EntityWrapper<AuthPersonal> wrapper);

}
