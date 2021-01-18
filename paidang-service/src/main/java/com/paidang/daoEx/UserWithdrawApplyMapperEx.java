package com.paidang.daoEx;

import com.paidang.dao.model.UserWithdrawApply;
import com.paidang.daoEx.model.UserWithdrawApplyEx;
import com.paidang.domain.qo.UserWithdrawApplyQo;

import java.util.List;

/**
@author sun
*/
public interface UserWithdrawApplyMapperEx {

    List<UserWithdrawApplyEx> findList(UserWithdrawApplyQo qo);
}
