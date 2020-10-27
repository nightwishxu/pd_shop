package com.api.service;

import com.alibaba.fastjson.JSONObject;
import com.api.MEnumError;
import com.api.view.pay.PayResult;
import com.api.view.pay.WxUnifiedResult;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.pay.PayMethod;
import com.base.pay.ali.AlipayConfig;
import com.base.pay.wx.util.WxPayConfig;
import com.base.util.StringUtil;
import com.item.dao.model.PayLog;
import com.item.dao.model.PayLogExample;
import com.item.dao.model.ShopCart;
import com.item.dao.model.ShopCartExample;
import com.item.service.PayLogService;
import com.item.service.ShopCartService;
import com.paidang.dao.model.*;
import com.paidang.service.*;
import com.util.MPaidangPayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


@Service
public class ApiUserPayService {

    @Autowired
    private PayLogService payLogService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private UserCouponService userCouponService;

    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private OutOrderService outOrderService;

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public List<PayResult> createShopCartOrder(MobileInfo mobileInfo, String data, Integer addressId){
        List<Map> mapList = JSONObject.parseArray(data, Map.class);
        long code = System.currentTimeMillis();

        UserAddress userAddress = userAddressService.selectByPrimaryKey(addressId);
        if(null == userAddress){
            throw new ApiException(MEnumError.ADDRESS_NOT_EXIST);
        }
        List<PayResult> results=new ArrayList<>();
        Date date=new Date();
        int i=0;
        for (Map map:mapList){
            PayResult payResult = new PayResult();
            Integer goodsId=Integer.valueOf((String)map.get("goodsId"));
            Integer num=Integer.valueOf(map.get("num")==null?"1":(String)map.get("num"));
            String str =(String)map.get("couponId");
            Integer couponId=null;
            if(StringUtil.isNotBlank(str)){
                couponId=Integer.valueOf(str);
            }

            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andTotalGreaterThanOrEqualTo(1).andIdEqualTo(goodsId);//.andStateEqualTo(1);
            List<Goods> list = goodsService.selectByExample(goodsExample);
            if(null == list || list.size() ==0) {
                throw new ApiException(MEnumError.GOODS_NOT_EXIST);

            }
            Goods goods = list.get(0);
            UserCoupon userCoupon = null;
            //用户使用优惠券
            if(null != couponId){
                UserCouponExample userCouponExample = new UserCouponExample();
                userCouponExample.createCriteria().andEndTimeGreaterThanOrEqualTo(new Date()).andIdEqualTo(couponId).andStateEqualTo(1);
                List<UserCoupon> userCouponList = userCouponService.selectByExample(userCouponExample);
                if(null == userCouponList || list.size() ==0){
                    throw new ApiException(MEnumError.COUPON_TYPE_EXIST);
                }
                userCoupon = userCouponList.get(0);
            }
            Order order = new Order();

            //订单号生成规则：时间戳加商品编号
            order.setCode((code+i) + goodsId.toString());
            order.setUserId(mobileInfo.getUserId());
            order.setGoodsId(goods.getId());
            order.setGoodsName(goods.getName());
            order.setGoodsImg(goods.getImg());
            order.setGoodsSource(goods.getSource());
            order.setOrgId(goods.getOrgId());
            order.setGoodsPrice(goods.getPrice());
            order.setGoodsCost(goods.getCost());
            order.setCommentState(0);
            order.setCreateTime(date);
            BigDecimal finalPrice = null;
            i++;
            if(null == userCoupon){
                //没有优惠券
                finalPrice = goods.getPrice().multiply(new BigDecimal(num));
            }else{
                finalPrice = goods.getPrice().multiply(new BigDecimal(num)).subtract(userCoupon.getFull());
            }
            order.setPrice(finalPrice);
            order.setState(1);
            order.setIsBalance(0);
            order.setShipUser(userAddress.getUserName());
            order.setShipPhone(userAddress.getPhone());
            order.setShipAddress(userAddress.getArea()+userAddress.getAddress());
            order.setRefState(0);
            if(null != userCoupon){
                order.setCouponValue(userCoupon.getFull());
            }
            order.setCouponId(couponId);
            order.setIsDel(0);
            int result = orderService.insert(order);
            if(0 == result){
                throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
            }

            //商品数量-1
            goods.setTotal(goods.getTotal() - num);
            goods.setSoldOut(goods.getSoldOut() + num);
            int reuslt2 = goodsService.updateByPrimaryKeySelective(goods);

            if(0 == reuslt2){
                throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
            }

            //优惠券不可用
            if(null != userCoupon){
                userCoupon.setState(0);
                int result3 = userCouponService.updateByPrimaryKeySelective(userCoupon);
                if(0 == result3){
                    throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
                }
            }

            payResult.setId(order.getId().toString());
            results.add(payResult);
            ShopCartExample example=new ShopCartExample();
            example.createCriteria().andGoodsIdEqualTo(goodsId).andUserIdEqualTo(mobileInfo.getUserId());
            List<ShopCart> shopCarts=shopCartService.selectByExample(example);
            if (shopCarts==null || shopCarts.size()==0){
                throw new ApiException(-1,"购物车商品不存在");
            }
            ShopCart cart=shopCarts.get(0);
            if(cart.getNum()>=1){
                shopCartService.deleteByPrimaryKey(shopCarts.get(0).getId());
            }
//            else if (cart.getNum()>1){
//                cart.setNum(cart.getNum()-1);
//                shopCartService.updateByPrimaryKeySelective(cart);
//            }

        }
        return results;
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public PayResult buyShopCartPay(MobileInfo mobileInfo, Integer platform, String orderIds){
        String logIds="";
        List<PayLog> logList=new ArrayList<>();
        BigDecimal price=new BigDecimal(0);
        String[] orderArray=orderIds.split(",");
        String code = "";
        Date date= new Date();
        boolean tmp = false;

        for (String id:orderArray){
            Integer orderId=Integer.valueOf(id);
            Order order = orderService.selectByPrimaryKey(orderId);
            if (order == null){
                throw new ApiException(MEnumError.WM_ORDER_NOTEXISTS);
            }
            if (!tmp){
                code = order.getCode();
                date= order.getCreateTime();
                tmp = true;
            }
            order.setPayType(platform==1?2:1);
            orderService.updateByPrimaryKeySelective(order);
            PayLog log = new PayLog();
            log.setOrderId(order.getId());
            log.setUserId(mobileInfo.getUserId());
            log.setState(0);
            log.setMoney(order.getPrice());
            log.setParam(MPaidangPayType.NORMAL_BUY.name());
            payLogService.insertSelective(log);
            price=price.add(order.getPrice());
            logList.add(log);
            logIds+=log.getId()+"-";

        }
        boolean flag=logIds.charAt(logIds.length()-1)=='-';
        if (flag){
            logIds=logIds.substring(0,logIds.length()-1);
        }

        OutOrder outOrder = new OutOrder();
        outOrder.setOrderId(logIds);
        outOrder.setUserId(mobileInfo.getUserId());
        outOrder.setCreateTime(date);
        outOrder.setOutOrderId("shop"+code);

        PayResult result = new PayResult();
        result.setPlatform(platform);
        switch (platform) {
            case 1:
                //支付宝
                result.setId(outOrder.getOutOrderId()+"_"+ MPaidangPayType.NORMAL_BUY.name());
                result.setMoney(price.toString());
                result.setBackUrl(PayMethod.urlToUrl(AlipayConfig.notify_url));
                break;
            case 2:
                //微信
                String wxId = PayMethod.wxPrepayId(price, outOrder.getOutOrderId(), "订单支付", MPaidangPayType.NORMAL_BUY);
                if (StringUtil.isBlank(wxId)){
                    throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
                }
                result.setId(wxId);
                break;
            default:
                throw new ApiException(MEnumError.PAY_TYPE_ERRPR);
        }

        outOrderService.insert(outOrder);
        return result;
    }


    /**
     * 下单
     * @return
     */
    public PayResult createOrder(Integer userId, Integer goodsId, Integer couponId, Integer addressId){
        PayResult payResult = new PayResult();

        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andTotalGreaterThanOrEqualTo(1).andIdEqualTo(goodsId);//.andStateEqualTo(1);
        List<Goods> list = goodsService.selectByExample(goodsExample);
        if(null == list || list.size() ==0) {
            throw new ApiException(MEnumError.GOODS_NOT_EXIST);
        }
        Goods goods = list.get(0);
        if (goods.getSource()!=3 && couponId!=null){
            throw new ApiException(-1,"该商品无法使用优惠券！");
        }
        UserCoupon userCoupon = null;
        //用户使用优惠券
        if(null != couponId){
            UserCouponExample userCouponExample = new UserCouponExample();
            userCouponExample.createCriteria().andEndTimeGreaterThanOrEqualTo(new Date()).andIdEqualTo(couponId).andStateEqualTo(1);
            List<UserCoupon> userCouponList = userCouponService.selectByExample(userCouponExample);
            if(null == userCouponList || list.size() ==0){
                throw new ApiException(MEnumError.COUPON_TYPE_EXIST);
            }
            userCoupon = userCouponList.get(0);
        }


        UserAddress userAddress = userAddressService.selectByPrimaryKey(addressId);
        if(null == userAddress){
            throw new ApiException(MEnumError.ADDRESS_NOT_EXIST);
        }

        Order order = new Order();
        long code = System.currentTimeMillis();
        //订单号生成规则：时间戳加商品编号
        order.setCode(code + goodsId.toString());
        order.setUserId(userId);
        order.setGoodsId(goods.getId());
        order.setGoodsName(goods.getName());
        order.setGoodsImg(goods.getImg());
        order.setGoodsSource(goods.getSource());
        order.setOrgId(goods.getOrgId());
        order.setGoodsPrice(goods.getPrice());
        order.setGoodsCost(goods.getCost());
        //优惠券

        BigDecimal finalPrice = null;
        if(null == userCoupon){
            //没有优惠券
            finalPrice = goods.getPrice();
        }else{
            finalPrice = goods.getPrice().subtract(userCoupon.getFull());
        }
        order.setPrice(finalPrice);
        order.setState(1);
        order.setIsBalance(0);
        order.setShipUser(userAddress.getUserName());
        order.setShipPhone(userAddress.getPhone());
        order.setShipAddress(userAddress.getArea()+userAddress.getAddress());
        order.setRefState(0);
        order.setCommentState(0);
        if(null != userCoupon){
            order.setCouponValue(userCoupon.getFull());
        }
        order.setCouponId(couponId);
        order.setIsDel(0);
        int result = orderService.insert(order);
        if(0 == result){
            throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
        }

        //商品数量-1
        goods.setTotal(goods.getTotal() - 1);
        goods.setSoldOut(goods.getSoldOut() + 1);
        int reuslt2 = goodsService.updateByPrimaryKeySelective(goods);

        if(0 == reuslt2){
            throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
        }

        //优惠券不可用
        if(null != userCoupon){
            userCoupon.setState(0);
            int result3 = userCouponService.updateByPrimaryKeySelective(userCoupon);
            if(0 == result3){
                throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
            }
        }

        payResult.setId(order.getId().toString());
        return payResult;
    }


    /**
     * 支付
     * @param mobileInfo
     * @param platform
     * @param orderId
     * @return
     */
    public PayResult buyPay(MobileInfo mobileInfo, Integer platform, Integer orderId){

        if (platform == null){
            throw new ApiException("platform");
        }
        if (orderId == null){
            throw new ApiException("orderId");
        }

        Order order = orderService.selectByPrimaryKey(orderId);
        order.setPayType(platform==1?2:1);
        orderService.updateByPrimaryKeySelective(order);
        if (order == null){
            throw new ApiException(MEnumError.WM_ORDER_NOTEXISTS);
        }

//		if (order.getState() != 2){
//			throw new ApiException(MEnumError.WM_ORDER_NOTEXISTS);
//		}

        PayLog log = new PayLog();
        log.setOrderId(order.getId());
        log.setUserId(mobileInfo.getUserId());
        log.setState(0);
        log.setMoney(order.getPrice());
        log.setParam(MPaidangPayType.NORMAL_BUY.name());
        payLogService.insertSelective(log);

        PayResult result = new PayResult();
        result.setPlatform(platform);
        switch (platform) {
            case 1:
                //支付宝
                result.setId(log.getId().toString()+"_"+ MPaidangPayType.NORMAL_BUY.name());
                result.setMoney(order.getPrice().toString());
                result.setBackUrl(PayMethod.urlToUrl(AlipayConfig.notify_url));
                break;
            case 2:
                //微信
                String wxId = PayMethod.wxPrepayId(order.getPrice(), log.getId().toString(), "订单支付", MPaidangPayType.NORMAL_BUY);
                if (StringUtil.isBlank(wxId)){
                    throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
                }
                result.setId(wxId);
                break;
            default:
                throw new ApiException(MEnumError.PAY_TYPE_ERRPR);
        }
        return result;
    }

    /**
     * 小程序支付 (2019-07-29 add by laria)
     * @param mobileInfo
     * @param platform
     * @param orderId
     * @return
     */
    public WxUnifiedResult miniBuyPay(MobileInfo mobileInfo, Integer platform, Integer orderId, String openId){

        if (platform == null){
            throw new ApiException("platform");
        }
        if (orderId == null){
            throw new ApiException("orderId");
        }

        Order order = orderService.selectByPrimaryKey(orderId);
        order.setPayType(platform==1?2:1);
        orderService.updateByPrimaryKeySelective(order);
        if (order == null){
            throw new ApiException(MEnumError.WM_ORDER_NOTEXISTS);
        }

//		if (order.getState() != 2){
//			throw new ApiException(MEnumError.WM_ORDER_NOTEXISTS);
//		}

        String out_trade_no = "";

        PayLogExample logExample = new PayLogExample();
        logExample.createCriteria().andOrderIdEqualTo(order.getId()).andUserIdEqualTo(mobileInfo.getUserId()).andStateEqualTo(0);
        List<PayLog> list = payLogService.selectByExample(logExample);
        if(list.size() <= 0){
            PayLog log = new PayLog();
            log.setOrderId(order.getId());
            log.setUserId(mobileInfo.getUserId());
            log.setState(0);
            log.setMoney(order.getPrice());
            log.setPlatform(2);//微信
            log.setParam(MPaidangPayType.NORMAL_BUY.name());
            payLogService.insertSelective(log);

            out_trade_no = log.getId().toString();
        }else{
            PayLog payLog = list.get(0);
            out_trade_no = payLog.getId().toString();
        }

        WxUnifiedResult unifiedResult = new WxUnifiedResult();
        switch (platform) {
            case 2:
                //小程序里微信支付
                String prepay_id = com.base.util.WxPrepay.wxJsPrepareId(order.getPrice(), out_trade_no, "订单支付", "127.0.0.1", openId, MPaidangPayType.NORMAL_BUY);
                System.out.println("prepay_id:" + prepay_id);

                SortedMap<String, String> signParams = new TreeMap<String, String>();

                signParams.put("appId", WxPayConfig.MINI_APP_ID);//app_id
                signParams.put("key",WxPayConfig.PAY_KEY);//key

                String nonstr = StringUtil.getRandomString(32);
                signParams.put("nonceStr", nonstr);//自定义不重复的长度不长于32位

                signParams.put("package","prepay_id=" + prepay_id);//预付订单id

                signParams.put("signType","MD5");

                String timeStramp = String.valueOf(System.currentTimeMillis());
                signParams.put("timeStamp", timeStramp);//北京时间时间戳

                String signAgain = com.base.util.WxPrepay.createSign(signParams);

                //返回结果
                unifiedResult.setAppId(WxPayConfig.MINI_APP_ID);
                unifiedResult.setPrepayId(prepay_id);
                unifiedResult.setNonceStr(nonstr);
                unifiedResult.setSignType("MD5");
                unifiedResult.setTimeStamp(timeStramp);
                unifiedResult.setPaySign(signAgain);

                break;
            default:
                throw new ApiException(MEnumError.PAY_TYPE_ERRPR);
        }
        return unifiedResult;
    }
}
