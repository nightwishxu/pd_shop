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



    // 商户优惠券相关接口

    @ApiOperation(value = "店铺查看优惠券")
    @ApiMethod(isLogin = true)
    @GetMapping("/list")
    @ResponseBody
    public List<CouponExList> list(@ApiParam(required = true, name = "type", value = "0发放中，1停用") Integer type, MobileInfo mobileInfo) {
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
    @PostMapping("/save")
    @ResponseBody
    public int save(MobileInfo mobileInfo, Coupon coupon) {
        if (coupon.getId() == null) {
            PawnOrg pawnOrg = findByMobileInfo(mobileInfo);
            coupon.setStatus(0);
            coupon.setOrgId(pawnOrg.getId());
            coupon.setOrgName(pawnOrg.getName());
            coupon.setCreateTime(new Date());
            if (coupon.getForm().intValue() == 1) {
                // 私密券
                if (StringUtil.isEmpty(coupon.getPhone())) {
                    throw new ApiException("私密券，输入手机号");
                }

            }
            if (coupon.getForm().intValue() == 1) {
                User user = userService.selectByAccount(coupon.getPhone());
                if (user != null) {
                    int id = couponService.insertCoupon(coupon);
                    coupon.setId(id);
                    bindByUser(user.getId(), coupon);
                } else {
                    throw new ApiException("手机号不存在");
                }
            } else {
                couponService.insertCoupon(coupon);
            }
        } else {
            throw new ApiException("不允许修改");
        }
        return 1;
    }

    @ApiOperation(value = "追加张数")
    @ApiMethod(isLogin = true)
    @PutMapping("/addMore")
    @ResponseBody
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
    @PutMapping("/stop")
    @ApiMethod(isLogin = true)
    @ResponseBody
    public int stop(@ApiParam(name = "id", value = "优惠券id", required = true) Integer id,
                    MobileInfo mobileInfo) {
        Coupon coupon = couponService.selectCouponById(id);
        checkOrg(findByMobileInfo(mobileInfo), coupon);
        coupon.setStatus(1);
        couponService.updateCoupon(coupon);
        return 1;
    }

    // 商户优惠券相关接口 end

    private void checkOrg(PawnOrg org, Coupon coupon) {
        if (coupon.getOrgId().intValue() != org.getId().intValue()) {
            throw new ApiException("与此优惠券商户不符，不允许修改");
        }
    }

    private PawnOrg findByMobileInfo(MobileInfo info) {
        User user = userService.selectByPrimaryKey(info.getUserId());
        if (user == null) {
            throw new ApiException("账号不存在");
        } else if (user.getOrgId() == null){
            throw new ApiException("非商户账户");
        } else {
            PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(user.getOrgId());
            if (pawnOrg == null) {
                throw new ApiException("商户不存在");
            }
            return pawnOrg;
        }
    }

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
