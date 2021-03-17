package com.paidang.daoEx;

import com.paidang.dao.model.AuthPersonal;
import com.paidang.daoEx.model.AuthPersonalEx;
import com.paidang.domain.qo.AuthPersonalQo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
@author sun
*/
public interface AuthPersonalMapperEx {
    int changeStateByPrimaryKey(AuthPersonal authPersonal);
    int setAuthInvalid(@Param("userId") int userId);

    List<AuthPersonalEx> findList(AuthPersonalQo qo);
}
