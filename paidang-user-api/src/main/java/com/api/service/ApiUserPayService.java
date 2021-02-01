package com.api.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.api.MEnumError;
import com.api.view.pay.PayResult;
import com.api.view.pay.WxUnifiedResult;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.pay.MFramePayType;
import com.base.pay.PayMethod;
import com.base.pay.PayPropertySupport;
import com.base.pay.ali.AliPayOpenSubmit;
import com.base.pay.ali.AlipayConfig;
import com.base.pay.wx.util.WxPayConfig;
import com.base.util.*;
import com.demo.constant.DSPConsts;
import com.ijpay.alipay.AliPayApi;
import com.ijpay.alipay.AliPayApiConfig;
import com.ijpay.alipay.AliPayApiConfigKit;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.enums.TradeType;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import com.item.dao.model.*;
import com.item.service.CodeService;
import com.item.service.PayLogService;
import com.item.service.ShopCartService;
import com.paidang.dao.model.*;
import com.paidang.service.*;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.util.MPaidangPayType;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;


@Service
public class ApiUserPayService {
    private static final Logger logger = LoggerFactory.getLogger(ApiUserPayService.class);


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

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private CodeService codeService;



    public WxPayApiConfig getApiConfig() {
        WxPayApiConfig apiConfig;

        try {
            apiConfig = WxPayApiConfigKit.getApiConfig(CoreConstants.getProperty("wxpay.appId"));
        } catch (Exception e) {
            apiConfig = WxPayApiConfig.builder()
                    .appId(CoreConstants.getProperty("wxpay.appId"))
                    .mchId(CoreConstants.getProperty("wxpay.mchId"))
                    .partnerKey(CoreConstants.getProperty("wxpay.partnerKey"))
                    .certPath(CoreConstants.getProperty("wxpay.certPath"))
                    .domain(CoreConstants.getProperty("wxpay.domain"))
                    .build();
            WxPayApiConfigKit.putApiConfig(apiConfig);
        }
//        notifyUrl = apiConfig.getDomain().concat("/wxPay/payNotify");
//        refundNotifyUrl = apiConfig.getDomain().concat("/wxPay/refundNotify");
        return apiConfig;
    }

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
//        [{"goodsId":"10","num":"2"},{"goodsId":"10","num":"2"}]
        int i=0;
        String randomCode = BaseUtils.getRandomNum(6);
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
            if (StringUtils.isNoneBlank(goods.getImgs())){
                String[] split = goods.getImgs().split(",");
                order.setGoodsImg(split[0]);
            }else {
                order.setGoodsImg(goods.getImg());
            }

            order.setGoodsSource(goods.getSource());
            order.setOrgId(goods.getOrgId());
            order.setGoodsPrice(goods.getPrice());
            order.setGoodsCost(goods.getCost());
            order.setCommentState(0);
            order.setRandomCode(randomCode);
            order.setCreateTime(date);
            BigDecimal finalPrice = null;
            i++;
            if(null == userCoupon){
                //没有优惠券
                finalPrice = goods.getPrice().multiply(new BigDecimal(num));
            }else{
                finalPrice = goods.getPrice().multiply(new BigDecimal(num)).subtract(userCoupon.getFull());
            }
            order.setGoodsNum(num);
            order.setAddressId(addressId);
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
            order.setCreateTime(new Date());
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
    public PayResult buyShopCartPay(MobileInfo mobileInfo, Integer platform, String orderIds) throws Exception{
        String logIds="";
        List<PayLog> logList=new ArrayList<>();
        BigDecimal price=new BigDecimal(0);
        String[] orderArray=orderIds.split(",");
        String code = "";
        Date date= new Date();
        boolean tmp = false;
        Map<String,PayLog> map = new HashMap<>();
        Map<String,Order> orderMap = new HashMap<>();
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
            if (platform==1){
                order.setPayType(2);
            }else if (platform==2){
                order.setPayType(1);
            }else {
                order.setPayType(platform);
            }

            orderService.updateByPrimaryKeySelective(order);
            PayLog log = new PayLog();
            log.setOrderId(order.getId());
            log.setUserId(mobileInfo.getUserId());
            log.setState(0);
            log.setMoney(order.getPrice());
            log.setParam(MPaidangPayType.NORMAL_BUY.name());
            log.setCreateTime(new Date());
            payLogService.insertSelective(log);
            price=price.add(order.getPrice());
            logList.add(log);
            logIds+=log.getId()+"-";
            map.put(id,log);
            orderMap.put(id,order);

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
                //                result.setId(outOrder.getOutOrderId()+"_"+ MPaidangPayType.NORMAL_BUY.name());
//                result.setMoney(price.toString());
//                result.setBackUrl(CoreConstants.ALI_NOTIFY_URL);
                AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
                //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
//                model.setBody("我是测试数据-By Javen");
                model.setSubject("订单支付");
                model.setOutTradeNo(outOrder.getOutOrderId() + "_" + MPaidangPayType.NORMAL_BUY.name());
                model.setTimeoutExpress("30m");
                model.setTotalAmount(price.toString());
                String orderInfo = AliPayApi.appPayToResponse(model, CoreConstants.ALI_NOTIFY_URL).getBody();

                result.setAliPay(orderInfo);
                break;
            case 2:
                //微信
//                String wxId = PayMethod.wxPrepayId(price, outOrder.getOutOrderId(), "订单支付", MPaidangPayType.NORMAL_BUY);
//                if (StringUtil.isBlank(wxId)){
//                    throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
//                }
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String ip = IPUtil.getClientIP(request);
                if (StringUtils.isBlank(ip)) {
                    ip = "127.0.0.1";
                }
                WxPayApiConfig wxPayApiConfig = getApiConfig();

                Map<String, String> params = UnifiedOrderModel
                        .builder()
                        .appid(wxPayApiConfig.getAppId())
                        .mch_id(wxPayApiConfig.getMchId())
                        .nonce_str(WxPayKit.generateStr())
                        .body("订单支付")
//                    .body("App")
                        .attach(MPaidangPayType.NORMAL_BUY.name())
                        .out_trade_no(outOrder.getOutOrderId())
                        .total_fee(getMoney(price.toString()))
                        .spbill_create_ip(ip)
                        .notify_url(wxPayApiConfig.getDomain()+"")
                        .trade_type(TradeType.APP.getTradeType())
                        .build()
                        .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

                String xmlResult = WxPayApi.pushOrder(false, params);

                logger.info(xmlResult);
                Map<String, String> wxRes = WxPayKit.xmlToMap(xmlResult);

                String returnCode = wxRes.get("return_code");
                String returnMsg = wxRes.get("return_msg");
                if (!WxPayKit.codeIsOk(returnCode)) {
                    throw new ApiException(returnMsg);
                }
                String resultCode = wxRes.get("result_code");
                if (!WxPayKit.codeIsOk(resultCode)) {
                    throw new ApiException(returnMsg);
                }
                // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回
                String prepayId = wxRes.get("prepay_id");

                Map<String, String> packageParams = WxPayKit.appPrepayIdCreateSign(wxPayApiConfig.getAppId(), wxPayApiConfig.getMchId(), prepayId,
                        wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

                String jsonStr = com.alibaba.druid.support.json.JSONUtils.toJSONString(packageParams);
                logger.info("返回apk的参数:" + jsonStr);
//                resultPay.put("weixinParam",jsonStr);

//                Map<String, String> map = PayMethod.wxAppPayInfo(order.getPrice(), log.getId().toString(), "订单支付", IpUtils.getIpAddr(request), MFramePayType.NORMAL_BUY);
                result.setWxPay(jsonStr);
                break;
            case 4:
                for (String s : orderArray) {
                    afterTransfer(Integer.valueOf(s),orderMap.get(s),map.get(s));
                }
                break;
            default:
                throw new ApiException(MEnumError.PAY_TYPE_ERRPR);
        }
        outOrderService.insert(outOrder);
        return result;
    }


    public static void payTest(){
        String notifyUrl = CoreConstants.ALI_NOTIFY_URL;
        Map<String, Object> pay =PayMethod.aliAppPay("1223",BigDecimal.ONE,"订单",notifyUrl,AlipayConfig.app_id,
                AlipayConfig.seller_id,AlipayConfig.payKey,null);
        System.out.println(JSONUtils.serialize(pay));
//        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        Map<String, String> wxMap = PayMethod.wxAppPayInfo(BigDecimal.ONE, "222", "订单", IpUtils.getIpAddr(request), MFramePayType.NORMAL_BUY);
//        System.out.println(JSONUtils.serialize(wxMap));

    }


    /**
     * 下单
     * @param type 1 竞拍商品自动下订单
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public PayResult createOrder(Integer userId, Integer goodsId, Integer couponId, Integer addressId,Integer type){
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
//        if(null == userAddress){
//            throw new ApiException(MEnumError.ADDRESS_NOT_EXIST);
//        }
        BigDecimal goodsPridce = goods.getPrice();
        if (type!=null && type==1){
            goodsPridce = goods.getMaxAuction();
        }
        Order order = new Order();
        long code = System.currentTimeMillis();
        //订单号生成规则：时间戳加商品编号
        order.setCode(code + goodsId.toString());
        order.setUserId(userId);
        order.setGoodsId(goods.getId());
        order.setGoodsName(goods.getName());
        if (StringUtils.isNoneBlank(goods.getImgs())){
            String[] split = goods.getImgs().split(",");
            order.setGoodsImg(split[0]);
        }else {
            order.setGoodsImg(goods.getImg());
        }
        order.setGoodsSource(goods.getSource());
        order.setOrgId(goods.getOrgId());
        order.setGoodsCost(goods.getCost());
        order.setGoodsPrice(goodsPridce);
        order.setGoodsNum(1);
        //优惠券

        BigDecimal finalPrice = null;
        if(null == userCoupon){
            //没有优惠券
            finalPrice = goodsPridce;
        }else{
            finalPrice = goodsPridce.subtract(userCoupon.getFull());
        }
        order.setPrice(finalPrice);
        order.setState(1);
        order.setIsBalance(0);
        if (userAddress!=null){
            order.setShipUser(userAddress.getUserName());
            order.setShipPhone(userAddress.getPhone());
            order.setShipAddress(userAddress.getArea()+userAddress.getAddress());
            order.setAddressId(addressId);
        }
        order.setRefState(0);
        order.setCommentState(0);
        if(null != userCoupon){
            order.setCouponValue(userCoupon.getFull());
        }
        order.setCouponId(couponId);
        order.setIsDel(0);
        order.setRandomCode(BaseUtils.getRandomNum(6));
        order.setCreateTime(new Date());
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
    public PayResult buyPay(MobileInfo mobileInfo, Integer platform, Integer orderId,Integer addressId) throws Exception{

        if (platform == null){
            throw new ApiException("platform");
        }
        if (orderId == null){
            throw new ApiException("orderId");
        }

        Order order = orderService.selectByPrimaryKey(orderId);
        if (order.getState()>=2){
            throw new ApiException(400,"订单已付款");
        }
        if (StringUtils.isAnyBlank(order.getShipUser(), order.getShipAddress(), order.getShipPhone()) && addressId==null){
            throw new ApiException(400,"请选择收货地址");
        }
        if (addressId!=null){
            UserAddress userAddress = userAddressService.selectByPrimaryKey(addressId);
            order.setShipUser(userAddress.getUserName());
            order.setShipPhone(userAddress.getPhone());
            order.setShipAddress(userAddress.getArea()+userAddress.getAddress());
            order.setAddressId(addressId);
        }
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
        log.setCreateTime(new Date());
        payLogService.insertSelective(log);

        PayResult result = new PayResult();
        result.setPlatform(platform);
        switch (platform) {
            case 1:
                //支付宝
//                result.setId(log.getId().toString()+"_"+ MPaidangPayType.NORMAL_BUY.name());
////                result.setMoney(order.getPrice().toString());
////                result.setBackUrl(CoreConstants.ALI_NOTIFY_URL);
//                Map<String, Object> payParam = PayMethod.aliAppPay(log.getId() + "_" + MPaidangPayType.NORMAL_BUY.name(), order.getPrice(), "订单支付", CoreConstants.ALI_NOTIFY_URL, AlipayConfig.app_id,
//                        AlipayConfig.seller_id, AlipayConfig.payKey, null);
                AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
                //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
//                model.setBody("我是测试数据-By Javen");
                model.setSubject("订单支付");
                model.setOutTradeNo(log.getId() + "_" + MPaidangPayType.NORMAL_BUY.name());
                model.setTimeoutExpress("30m");
                model.setTotalAmount(order.getPrice().toString());
                logger.info( CoreConstants.ALI_NOTIFY_URL);
                String orderInfo = AliPayApi.appPayToResponse(model, CoreConstants.ALI_NOTIFY_URL).getBody();

                result.setAliPay(orderInfo);
                break;
            case 2:
                //微信
//                String wxId = PayMethod.wxPrepayId(order.getPrice(), log.getId().toString(), "订单支付", MPaidangPayType.NORMAL_BUY);
//                if (StringUtil.isBlank(wxId)){
//                    throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
//                }
//                result.setId(wxId);

                //微信
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String ip = IPUtil.getClientIP(request);
                if (StringUtils.isBlank(ip)) {
                    ip = "127.0.0.1";
                }
                WxPayApiConfig wxPayApiConfig = getApiConfig();

                Map<String, String> params = UnifiedOrderModel
                        .builder()
                        .appid(wxPayApiConfig.getAppId())
                        .mch_id(wxPayApiConfig.getMchId())
                        .nonce_str(WxPayKit.generateStr())
                        .body("订单支付")
//                    .body("App")
                    .attach(MPaidangPayType.NORMAL_BUY.name())
                        .out_trade_no(log.getId().toString())
                        .total_fee(getMoney(order.getPrice().toString()))
                        .spbill_create_ip(ip)
                        .notify_url(wxPayApiConfig.getDomain()+"")
                        .trade_type(TradeType.APP.getTradeType())
                        .build()
                        .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

                String xmlResult = WxPayApi.pushOrder(false, params);

                logger.info(xmlResult);
                Map<String, String> wxRes = WxPayKit.xmlToMap(xmlResult);

                String returnCode = wxRes.get("return_code");
                String returnMsg = wxRes.get("return_msg");
                if (!WxPayKit.codeIsOk(returnCode)) {
                    throw new ApiException(returnMsg);
                }
                String resultCode = wxRes.get("result_code");
                if (!WxPayKit.codeIsOk(resultCode)) {
                    throw new ApiException(returnMsg);
                }
                // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回
                String prepayId = wxRes.get("prepay_id");

                Map<String, String> packageParams = WxPayKit.appPrepayIdCreateSign(wxPayApiConfig.getAppId(), wxPayApiConfig.getMchId(), prepayId,
                        wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

                String jsonStr = com.alibaba.druid.support.json.JSONUtils.toJSONString(packageParams);
                logger.info("返回apk的参数:" + jsonStr);
//                resultPay.put("weixinParam",jsonStr);

//                Map<String, String> map = PayMethod.wxAppPayInfo(order.getPrice(), log.getId().toString(), "订单支付", IpUtils.getIpAddr(request), MFramePayType.NORMAL_BUY);
                result.setWxPay(jsonStr);
                break;
            case 4:
                afterTransfer(orderId,order,log);
                break;
            default:
                throw new ApiException(MEnumError.PAY_TYPE_ERRPR);
        }
        return result;
    }


    public void afterTransfer(Integer orderId,Order order,PayLog payLog){
        Order orderUpdate = orderService.selectByPrimaryKey(orderId);
        orderUpdate.setState(2);
        orderUpdate.setPayType(4);
        orderUpdate.setIsConfirm(0);
        orderUpdate.setPayTime(new Date());
        int update = orderService.updateByPrimaryKeySelective(orderUpdate);
//        if (update == 0) {
//            logger.info("$$$$业务订单没有更新成功!——商户订单号:" + no + ";交易号:" + trade_no + ",交易状态:" + trade_status);
//        }
        Goods goods = goodsService.selectByPrimaryKey(order.getGoodsId());

        //商品
        Goods goodsUpdate = new Goods();
        goodsUpdate.setId(order.getGoodsId());
        Goods old = goodsService.selectByPrimaryKey(order.getGoodsId());
        if (old.getTotal() == 0) {
            goodsUpdate.setState(-1);
            goodsUpdate.setIsOnline(0);
            update = goodsService.updateByPrimaryKeySelective(goodsUpdate);
        }
        payLog.setState(9);
        payLog.setModifyTime(new Date());
        payLog.setPlatform(4); // 1支付宝2银联3微信
//        payLog.setRealPay(total);
//        payLog.setTradeNo(trade_no);
//        payLog.setBuyer(buyerEmail);
        payLogService.updateByPrimaryKeySelective(payLog);
        if (update == 0) {
//            logger.info("$$$$商品没有更新成功!——商户订单号:" + no + ";交易号:" + trade_no + ",交易状态:" + trade_status);
        }
        //藏品更新
        if (goods.getSource() == 2) {
            //绝当品归属权
            UserGoods userGoods = new UserGoods();
            userGoods.setId(goods.getGoodsId());
            userGoods.setBelongType(1);
            userGoods.setBelongId(order.getUserId());
            update = userGoodsService.updateByPrimaryKeySelective(userGoods);
//            if (update == 0) {
//                logger.info("$$$$藏品没有更新成功!——商户订单号:" + no + ";交易号:" + trade_no + ",交易状态:" + trade_status);
//            }
        }
    }

    /**
     * 元转换成分
     * @param amount
     * @return
     */
    public static String getMoney(String amount) {
        if(amount==null){
            return "";
        }
        // 金额转化为分为单位
        // 处理包含, ￥ 或者$的金额
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(currency+"00");
        }else if(length - index >= 3){
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));
        }else if(length - index == 2){
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);
        }else{
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");
        }
        return amLong.toString();
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
            log.setCreateTime(new Date());
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

    public Map getOrderTransferInfo(Integer userId,
                                       Integer orderId) throws Exception{
        Code c = codeService.selectByPrimaryKey("orderTransferInfo");
        Map map = JSONUtils.deserialize(c.getValue(), Map.class);
        Order order = orderService.selectByPrimaryKey(orderId);
        map.put("randomCode",order.getRandomCode());
        return map;
    }

}
