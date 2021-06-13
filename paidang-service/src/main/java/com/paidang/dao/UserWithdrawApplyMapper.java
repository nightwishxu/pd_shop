package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.UserWithdrawApply;
import com.paidang.dao.model.UserWithdrawApplyExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserWithdrawApplyMapper {
	int countByExample(UserWithdrawApplyExample example);

	int deleteByExample(UserWithdrawApplyExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(UserWithdrawApply record);

	int insertSelective(UserWithdrawApply record);

	List<UserWithdrawApply> selectByExample(UserWithdrawApplyExample example);

	UserWithdrawApply selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") UserWithdrawApply record, @Param("example") UserWithdrawApplyExample example);

	int updateByExample(@Param("record") UserWithdrawApply record, @Param("example") UserWithdrawApplyExample example);

	int updateByPrimaryKeySelective(UserWithdrawApply record);

	int updateByPrimaryKey(UserWithdrawApply record);

	List<UserWithdrawApply> selectByWrapper(EntityWrapper<UserWithdrawApply> wrapper);

}
