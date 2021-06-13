package com.paidang.daoEx;

import com.paidang.daoEx.model.UserWithdrawApplyEx;
import com.paidang.domain.qo.UserWithdrawApplyQo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
@author sun
*/
@Mapper
public interface UserWithdrawApplyMapperEx {

    List<UserWithdrawApplyEx> findList(UserWithdrawApplyQo qo);
}
