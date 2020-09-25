package com.paidang.dao;

import com.item.daoEx.model.UserEx;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AuthUserMapper {

	List<UserEx> authUserList(@Param("nickOrMobile") String nickOrMobile);

} 