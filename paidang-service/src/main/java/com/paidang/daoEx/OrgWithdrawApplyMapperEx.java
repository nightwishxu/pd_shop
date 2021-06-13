package com.paidang.daoEx;

import com.paidang.dao.model.OrgWithdrawApply;
import com.paidang.daoEx.model.OrgWithdrawApplyEx;
import com.paidang.domain.qo.OrgWithdrawApplyQo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
@author sun
*/
@Mapper
public interface OrgWithdrawApplyMapperEx {

    List<OrgWithdrawApplyEx> findList(OrgWithdrawApplyQo qo);
}
