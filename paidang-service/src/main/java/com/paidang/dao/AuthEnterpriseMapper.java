package com.paidang.dao;

import java.util.List;
import com.base.mybatis.plus.EntityWrapper;
import org.apache.ibatis.annotations.Param;

import com.paidang.dao.model.AuthEnterprise;
import com.paidang.dao.model.AuthEnterpriseExample;

public interface AuthEnterpriseMapper {
	int countByExample(AuthEnterpriseExample example);

	int deleteByExample(AuthEnterpriseExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(AuthEnterprise record);

	int insertSelective(AuthEnterprise record);

	List<AuthEnterprise> selectByExample(AuthEnterpriseExample example);

	AuthEnterprise selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") AuthEnterprise record,@Param("example") AuthEnterpriseExample example);

	int updateByExample(@Param("record") AuthEnterprise record,@Param("example") AuthEnterpriseExample example);

	int updateByPrimaryKeySelective(AuthEnterprise record);

	int updateByPrimaryKey(AuthEnterprise record);

	List<AuthEnterprise> selectByWrapper(EntityWrapper<AuthEnterprise> wrapper);

}
