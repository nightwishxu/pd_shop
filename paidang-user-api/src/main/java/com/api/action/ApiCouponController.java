package com.api.action;

import com.base.annotation.ApiMethod;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.DateUtil;
import com.base.util.StringUtil;
import com.item.dao.model.User;
import com.item.service.UserService;
import com.paidang.dao.model.Coupon;
import com.paidang.dao.model.PawnOrg;
import com.paidang.dao.model.UserCoupon;
import com.paidang.daoEx.model.CouponExList;
import com.paidang.service.CouponService;
import com.paidang.service.PawnOrgService;
import com.paidang.service.UserCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WBC
 * @date 2021/6/10 10:14
 * @title ApiCouponController
 */
@RestController
@RequestMapping(value = "/api/coupon", produces = {"application/json;charset=UTF-8"})
@Api(tags = "优惠券")
public class ApiCouponController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private UserCouponService userCouponService;

    @Autowired
    private PawnOrgService pawnOrgService;

    @Autowired
    private UserService userService;

    /**
     * 我的优惠券相关接口
     */
    @ApiOperation(value = "我的优惠券")
    @GetMapping("/mine")
    @ApiMethod(isLogin = true)
    @ResponseBody
    public List<Coupon> mine(@ApiParam(name = "type", value = "0待使用，1已使用，2已过期", required = true) Integer type,
                             MobileInfo mobileInfo) {
        Coupon coupon = new Coupon();
        coupon.setStatus(0);
        if (type == 0) {
            coupon.setToday(DateUtil.dateToStr(new Date(), "yyyy-MM-dd"));
        }
        coupon.setUserId(mobileInfo.getUserId());
        coupon.setUserState(type);
        return couponService.selectCouponList(coupon);
    }

    @ApiOperation(value = "根据商户id查询可用优惠券")
    @GetMapping("/findByOrgId")
    @ApiMethod(isLogin = true)
    @ResponseBody
    public List<Coupon> findByOrgId(@ApiParam(name = "orgId", value = "商户id", required = true) Integer orgId) {
        Coupon coupon = new Coupon();
        coupon.setOrgId(orgId);
        coupon.setStatus(0);
        coupon.setToday(DateUtil.dateToStr(new Date(), "yyyy-MM-dd"));
        return couponService.selectCouponList(coupon);
    }

    @ApiOperation(value = "领取优惠券")
    @PutMapping("/bind")
    @ApiMethod(isLogin = true)
    @ResponseBody
    public int bind(MobileInfo mobileInfo, @ApiParam(name = "id", value = "优惠券id", required = true) Integer id) {
        Coupon coupon = couponService.selectCouponById(id);
        if (coupon == null) {
            throw new ApiException("优惠券不存在");
        }
        if (coupon.getEndDay().getTime() < new Date().getTime()) {
            throw new ApiException("优惠券已过期");
        }
        if (coupon.getStatus().intValue() == 1) {
            throw new ApiException("优惠券已停用");
        }

        int num = userCouponService.countByParam(1, id);
        if (num >= coupon.getNum()) {
            throw new ApiException("优惠券已领完");
        }
        bindByUser(mobileInfo.getUserId(), coupon);
        return 1;
    }

    @ApiOperation(value = "根据商户id查询我的优惠券")
    @GetMapping("/findMineByOrgId")
    @ApiMethod(isLogin = true)
    @ResponseBody
    public List<Coupon> findMineByOrgId(MobileInfo mobileInfo,
                                    @ApiParam(name = "orgId", value = "商户id", required = true) Integer orgId) {
        Coupon coupon = new Coupon();
        coupon.setOrgId(orgId);
        coupon.setStatus(0);
        coupon.setToday(DateUtil.dateToStr(new Date(), "yyyy-MM-dd"));
        coupon.setUserId(mobileInfo.getUserId());
        return couponService.selectCouponList(coupon);
    }
    /**
     * 我的优惠券相关接口 end
     */

    private void bindByUser(Integer userId, Coupon coupon) {
        List<UserCoupon> userCouponList = userCouponService.selectByCouponIdAndUserId(userId, coupon.getId());
        if (userCouponList == null || userCouponList.size() == 0) {
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setUserId(userId);
            userCoupon.setCouponId(coupon.getId());
            userCoupon.setFull(coupon.getFull());
            userCoupon.setValue(coupon.getValue());
            userCoupon.setEndTime(coupon.getEndDay());
            userCoupon.setUseTime(null);
            userCoupon.setState(1);
            userCoupon.setSource(coupon.getType());
            userCoupon.setFid(coupon.getFid());
            userCouponService.insert(userCoupon);
        } else if (userCouponList.size() >= coupon.getPerNum()) {
            throw new ApiException("超出每人可领取数量");
        }
    }
}
