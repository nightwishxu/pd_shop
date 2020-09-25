package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.UserReturnAddress;
import com.paidang.dao.model.UserReturnAddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserReturnAddressMapper {
	int countByExample(UserReturnAddressExample example);

	int deleteByExample(UserReturnAddressExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(UserReturnAddress record);

	int insertSelective(UserReturnAddress record);

	List<UserReturnAddress> selectByExample(UserReturnAddressExample example);

	UserReturnAddress selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") UserReturnAddress record, @Param("example") UserReturnAddressExample example);

	int updateByExample(@Param("record") UserReturnAddress record, @Param("example") UserReturnAddressExample example);

	int updateByPrimaryKeySelective(UserReturnAddress record);

	int updateByPrimaryKey(UserReturnAddress record);

	List<UserReturnAddress> selectByWrapper(EntityWrapper<UserReturnAddress> wrapper);

}
