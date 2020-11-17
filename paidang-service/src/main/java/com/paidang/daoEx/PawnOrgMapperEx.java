package com.paidang.daoEx;

import com.paidang.dao.model.PawnOrg;
import com.paidang.daoEx.model.PawnOrgEx;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
@author sun
*/
public interface PawnOrgMapperEx {

    PawnOrgEx getOrgIntroduction(Integer orgId);

    List<PawnOrgEx> selectList(Map<String, Object> map);

    PawnOrgEx selectById(@Param("orgUserId") String orgUserId);

    Integer changeState(PawnOrg pawnOrg);

    int updateCommentCount(@Param("orgId") Integer orgId);

    int updateCommentScore(@Param("orgId") Integer orgId);

    int addIntegral(@Param("orgId") Integer orgId, @Param("integral")BigDecimal integral);

}
