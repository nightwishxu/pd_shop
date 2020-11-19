package com.item.daoEx;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.item.dao.model.User;
import org.apache.ibatis.annotations.Param;

import com.item.daoEx.model.UserEx;


public interface UserMapperEx{

	List<UserEx> selectList(User user);

	void updateBalance(Map<String, Object> map);

	List<Integer> selectIdByAccounts(@Param("accounts")List<String> accounts);
	
	Integer selectIdByAccount(String account);
	
	String selectAccountById(String id);

	//机构后台，用户列表
	List<UserEx> selectOrgUsersList(Map<String, Object> map);

	UserEx selectByAccount(String record);

	List<UserEx> findList(@Param("userId")Integer userId,@Param("keyword")String keyword);


	int updatePokeAmount(@Param("id") Integer userId,@Param("amount") BigDecimal amount);

	User getByInviteCode(@Param("inviteCode")String inviteCode);

	int reSumPokeAmount(@Param("id") Integer userId);

	List<UserEx> recommendList(@Param("userId") Integer userId,@Param("limit") Integer limit);

	int updateUserCount(@Param("id") Integer id,@Param("type")Integer type);


	List<UserEx> findListBack(@Param("nickName")String nickName,@Param("username")String username
			,@Param("isHealthCounselor")String isHealthCounselor,@Param("isInsuranceCounselor") String isInsuranceCounselor);

	UserEx getCounselor(@Param("type")String type);

	/**
	 * 关注用户列表
	 * @param userId
	 * @return
	 */
	List<UserEx> findFollowList(@Param("userId")Integer userId);

	List<UserEx> findFansList(@Param("userId")Integer userId);

} 