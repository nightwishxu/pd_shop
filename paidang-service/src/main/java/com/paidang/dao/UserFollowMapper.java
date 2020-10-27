package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.UserFollow;
import com.paidang.dao.model.UserFollowExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFollowMapper {
	int countByExample(UserFollowExample example);

	int deleteByExample(UserFollowExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(UserFollow record);

	int insertSelective(UserFollow record);

	List<UserFollow> selectByExample(UserFollowExample example);

	UserFollow selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") UserFollow record,@Param("example") UserFollowExample example);

	int updateByExample(@Param("record") UserFollow record,@Param("example") UserFollowExample example);

	int updateByPrimaryKeySelective(UserFollow record);

	int updateByPrimaryKey(UserFollow record);

	List<UserFollow> selectByWrapper(EntityWrapper<UserFollow> wrapper);

}
