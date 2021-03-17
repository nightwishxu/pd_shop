package com.paidang.daoEx;

import com.paidang.dao.model.AuthEnterprise;
import com.paidang.daoEx.model.AuthEnterpriseEx;
import com.paidang.domain.qo.AuthEnterpriseQo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
@author sun
*/
public interface AuthEnterpriseMapperEx {
    int changeStateByPrimaryKey(AuthEnterprise authEnterprise);
    void setAuthInvalid(@Param("userId") int userId);

    List<AuthEnterpriseEx> findList(AuthEnterpriseQo qo);
}
