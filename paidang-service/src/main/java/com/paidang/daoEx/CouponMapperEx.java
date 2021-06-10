package com.paidang.daoEx;

import com.paidang.daoEx.model.CouponEx;
import com.paidang.daoEx.model.CouponExList;

import java.util.List;
import java.util.Map;

/**
@author sun
*/
public interface CouponMapperEx {
    List<CouponEx> selectGoods(Map<String ,Object> map);

    List<CouponExList> selectByType(Map<String ,Object> map);
}
