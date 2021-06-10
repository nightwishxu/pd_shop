package com.paidang.dao;

import com.paidang.dao.model.Coupon;
import com.paidang.daoEx.model.CouponExList;

import java.util.List;
import java.util.Map;

/**
 * 优惠券Mapper接口
 *
 * @author WBC
 * @date 2021-06-10
 */
public interface CouponMapper {
	/**
	 * 查询优惠券
	 *
	 * @param id 优惠券ID
	 * @return 优惠券
	 */
	Coupon selectCouponById(Integer id);

	/**
	 * 查询优惠券列表
	 *
	 * @param coupon 优惠券
	 * @return 优惠券集合
	 */
	List<Coupon> selectCouponList(Coupon coupon);

	/**
	 * 新增优惠券
	 *
	 * @param coupon 优惠券
	 * @return 结果
	 */
	int insertCoupon(Coupon coupon);

	/**
	 * 修改优惠券
	 *
	 * @param coupon 优惠券
	 * @return 结果
	 */
	int updateCoupon(Coupon coupon);

	/**
	 * 删除优惠券
	 *
	 * @param id 优惠券ID
	 * @return 结果
	 */
	int deleteCouponById(Integer id);

	/**
	 * 批量删除优惠券
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	int deleteCouponByIds(Integer[] ids);
}