package com.paidang.daoEx;

import com.paidang.dao.model.AuthPersonal;
import org.apache.ibatis.annotations.Param;

/**
@author sun
*/
public interface AuthPersonalMapperEx {
    int changeStateByPrimaryKey(AuthPersonal authPersonal);
    int setAuthInvalid(@Param("userId") int userId);
}
