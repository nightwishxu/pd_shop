package com.paidang.daoEx;

import com.paidang.dao.model.OrgWithdrawApply;
import com.paidang.daoEx.model.OrgWithdrawApplyEx;
import com.paidang.domain.qo.OrgWithdrawApplyQo;

import java.util.List;

/**
@author sun
*/
public interface OrgWithdrawApplyMapperEx {

    List<OrgWithdrawApplyEx> findList(OrgWithdrawApplyQo qo);
}
