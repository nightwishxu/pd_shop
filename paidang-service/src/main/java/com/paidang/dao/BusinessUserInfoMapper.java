package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.BusinessUserInfo;
import com.paidang.dao.model.BusinessUserInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessUserInfoMapper {
	int countByExample(BusinessUserInfoExample example);

	int deleteByExample(BusinessUserInfoExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(BusinessUserInfo record);

	int insertSelective(BusinessUserInfo record);

	List<BusinessUserInfo> selectByExample(BusinessUserInfoExample example);

	BusinessUserInfo selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") BusinessUserInfo record, @Param("example") BusinessUserInfoExample example);

	int updateByExample(@Param("record") BusinessUserInfo record, @Param("example") BusinessUserInfoExample example);

	int updateByPrimaryKeySelective(BusinessUserInfo record);

	int updateByPrimaryKey(BusinessUserInfo record);

	List<BusinessUserInfo> selectByWrapper(EntityWrapper<BusinessUserInfo> wrapper);

}
