package com.paidang.daoEx;

import com.paidang.dao.model.AuthEnterprise;
import org.apache.ibatis.annotations.Param;

/**
@author sun
*/
public interface AuthEnterpriseMapperEx {
    int changeStateByPrimaryKey(AuthEnterprise authEnterprise);
    void setAuthInvalid(@Param("userId") int userId);
}
