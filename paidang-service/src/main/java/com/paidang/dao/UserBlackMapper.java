package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.UserBlack;
import com.paidang.dao.model.UserBlackExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserBlackMapper {
	int countByExample(UserBlackExample example);

	int deleteByExample(UserBlackExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(UserBlack record);

	int insertSelective(UserBlack record);

	List<UserBlack> selectByExample(UserBlackExample example);

	UserBlack selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") UserBlack record,@Param("example") UserBlackExample example);

	int updateByExample(@Param("record") UserBlack record,@Param("example") UserBlackExample example);

	int updateByPrimaryKeySelective(UserBlack record);

	int updateByPrimaryKey(UserBlack record);

	List<UserBlack> selectByWrapper(EntityWrapper<UserBlack> wrapper);

}
