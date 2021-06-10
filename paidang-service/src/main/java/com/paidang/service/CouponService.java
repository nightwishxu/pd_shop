package com.paidang.service;

import com.paidang.dao.CouponMapper;
import com.paidang.dao.model.Coupon;
import com.paidang.daoEx.CouponMapperEx;
import com.paidang.daoEx.model.CouponEx;
import com.paidang.daoEx.model.CouponExList;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 优惠券Service业务层处理
 *
 * @author WBC
 * @date 2021-06-10
 */
@Service
public class CouponService {
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private CouponMapperEx couponMapperEx;

    /**
     * 查询优惠券
     *
     * @param id 优惠券ID
     * @return 优惠券
     */

    public Coupon selectCouponById(Integer id) {
        return couponMapper.selectCouponById(id);
    }

    /**
     * 查询优惠券列表
     *
     * @param coupon 优惠券
     * @return 优惠券
     */

    public List<Coupon> selectCouponList(Coupon coupon) {
        return couponMapper.selectCouponList(coupon);
    }

    /**
     * 新增优惠券
     *
     * @param coupon 优惠券
     * @return 结果
     */

    public int insertCoupon(Coupon coupon) {
        coupon.setCreateTime(DateUtils.getNowDate());
        return couponMapper.insertCoupon(coupon);
    }

    /**
     * 修改优惠券
     *
     * @param coupon 优惠券
     * @return 结果
     */

    public int updateCoupon(Coupon coupon) {
        return couponMapper.updateCoupon(coupon);
    }

    /**
     * 批量删除优惠券
     *
     * @param ids 需要删除的优惠券ID
     * @return 结果
     */

    public int deleteCouponByIds(Integer[] ids) {
        return couponMapper.deleteCouponByIds(ids);
    }

    /**
     * 删除优惠券信息
     *
     * @param id 优惠券ID
     * @return 结果
     */

    public int deleteCouponById(Integer id) {
        return couponMapper.deleteCouponById(id);
    }

    public List<CouponExList> selectByType(Map<String ,Object> map) {
        return couponMapperEx.selectByType(map);
    }

    public List<CouponEx> selectGoods(Map<String ,Object> map){
        return couponMapperEx.selectGoods(map);
    }
}