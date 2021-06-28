package com.api.action;

import com.base.annotation.ApiMethod;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.DateUtil;
import com.base.util.StringUtil;
import com.item.dao.model.User;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.CouponExList;
import com.paidang.service.*;
import io.swagger.annotations.*;
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

    @Autowired
    private AuthPersonalService authPersonalService;

    @Autowired
    private AuthEnterpriseService authEnterpriseService;

    // 商户优惠券相关接口

    @ApiOperation(value = "店铺查看优惠券")
    @ApiMethod(isLogin = true)
    @RequestMapping(method = RequestMethod.POST, value="/list")
    public List<CouponExList> list(MobileInfo mobileInfo,
                                   @ApiParam(required = true, name = "type", value = "0发放中，1停用") Integer type) {
        PawnOrg pawnOrg = findByMobileInfo(mobileInfo);
        Map<String ,Object> map = new HashMap<>();
        map.put("type", type);
        map.put("org_id", pawnOrg.getId());
        List<CouponExList> list = couponService.selectByType(map);
        list.forEach(item -> {
            item.setBindNum(userCouponService.countByParam(1, item.getId()));
            item.setUsedNum(userCouponService.countByParam(0, item.getId()));
        });
        return list;
    }


    @ApiOperation(value = "创建优惠券")
    @ApiMethod(isLogin = true)
    @RequestMapping(method = RequestMethod.POST, value="/save")
    @ResponseBody
    public int save(MobileInfo mobileInfo, Coupon coupon) {
        if (coupon.getId() == null) {
            PawnOrg pawnOrg = findByMobileInfo(mobileInfo);
            coupon.setStatus(0);
            coupon.setStartDay(DateUtil.strToDate(coupon.getStartDayStr(), "yyyy-MM-dd"));
            coupon.setEndDay(DateUtil.strToDate(coupon.getEndDayStr(), "yyyy-MM-dd"));
            coupon.setOrgId(pawnOrg.getId());
            coupon.setOrgName(pawnOrg.getName());
            coupon.setCreateTime(new Date());
            if (coupon.getForm().intValue() == 1) {
                // 私密券
                if (StringUtil.isEmpty(coupon.getPhone())) {
                    throw new ApiException(500, "私密券，输入手机号");
                }

            }
            if (coupon.getForm().intValue() == 1) {
                User user = userService.selectByAccount(coupon.getPhone());
                if (user != null) {
                    int id = couponService.insertCoupon(coupon);
                    coupon.setId(id);
                    bindByUser(user.getId(), coupon);
                } else {
                    throw new ApiException(500, "手机号不存在");
                }
            } else {
                couponService.insertCoupon(coupon);
            }
        } else {
            throw new ApiException(500, "不允许修改");
        }
        return 1;
    }

    @ApiOperation(value = "追加张数")
    @ApiMethod(isLogin = true)
    @RequestMapping(method = RequestMethod.POST, value="/addMore")
    public int addMore(MobileInfo mobileInfo,
                       @ApiParam(name = "id", value = "优惠券id", required = true) Integer id,
                       @ApiParam(name = "num", value = "追加的数量", required = true) Integer num) {
        Coupon coupon = couponService.selectCouponById(id);
        checkOrg(findByMobileInfo(mobileInfo), coupon);
        coupon.setNum(coupon.getNum() + num);
        couponService.updateCoupon(coupon);
        return 1;
    }

    @ApiOperation(value = "停用")
    @RequestMapping(method = RequestMethod.POST, value="/stop")
    @ApiMethod(isLogin = true)
    public int stop(@ApiParam(name = "id", value = "优惠券id", required = true) Integer id,
                    MobileInfo mobileInfo) {
        Coupon coupon = couponService.selectCouponById(id);
        checkOrg(findByMobileInfo(mobileInfo), coupon);
        coupon.setStatus(1);
        couponService.updateCoupon(coupon);
        return 1;
    }

    @ApiOperation(value = "删除")
    @RequestMapping(method = RequestMethod.POST, value="/delete")
    @ApiMethod(isLogin = true)
    public int delete(@ApiParam(name = "id", value = "优惠券id", required = true) Integer id,
                      MobileInfo mobileInfo) {
        Coupon coupon = couponService.selectCouponById(id);
        if (coupon.getStatus() == 1 || coupon.getEndDay().getTime() < new Date().getTime()) {
            // 停用状态，或者过期，才能删除
            checkOrg(findByMobileInfo(mobileInfo), coupon);
            couponService.deleteCouponById(id);
            userCouponService.deleteByCouponId(id);
        } else {
            throw new ApiException(500, "未失效的优惠券不允许删除");
        }
        return 1;
    }

    // 商户优惠券相关接口 end


    private void checkOrg(PawnOrg org, Coupon coupon) {
        if (coupon.getOrgId().intValue() != org.getId().intValue()) {
            throw new ApiException(500, "与此优惠券商户不符，不允许修操作c");
        }
    }

    private PawnOrg findByMobileInfo(MobileInfo info) {
        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(info.getUserId());
        if (pawnOrg == null) {
            throw new ApiException(500, "商户不存在");
        }
        return pawnOrg;
    }

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
            throw new ApiException(500, "超出每人可领取数量");
        }
    }
}
