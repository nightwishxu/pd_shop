package com.api.action;

import com.api.service.ApiUserPayService;
import com.api.view.pay.PayResult;
import com.api.view.pay.WxUnifiedResult;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.item.service.PayLogService;
import com.paidang.service.GoodsService;
import com.paidang.service.OrderService;
import com.paidang.service.UserAddressService;
import com.paidang.service.UserCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value="/api/userPay", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
@Api(tags = "支付接口")
public class ApiUserPayController extends ApiBaseController{

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
	private ApiUserPayService apiUserPayService;

	@ApiOperation(value = "下单", notes = "登陆")
	@RequestMapping("/createOrder")
	@ApiMethod(isLogin = true)
	public PayResult createOrder(MobileInfo mobileInfo,
                                 @ApiParam(value = "商品id", required = true)Integer goodsId,
                                 @ApiParam(value = "优惠券id", required = false)Integer couponId,
                                 @ApiParam(value = "地址id", required = true)Integer addressId){
		logger.info("下单");
		return apiUserPayService.createOrder(mobileInfo.getUserId(),goodsId,couponId,addressId,null);

	}
	
	@ApiOperation(value = "商城支付", notes = "登陆")
	@RequestMapping("/buyPay")
	@ApiMethod(isLogin = true)
	public PayResult buyPay(MobileInfo mobileInfo,
                            @ApiParam(value = "支付方式:1:支付宝 2:微信", required = true)Integer platform,
							@ApiParam(value = "地址id", required = false)Integer addressId,
                            @ApiParam(value = "订单id", required = true)Integer orderId){

		return apiUserPayService.buyPay(mobileInfo,platform,orderId,addressId);
	}

	//(2019-07-29 add by laria)
	@ApiOperation(value = "小程序商城支付", notes = "登陆")
	@RequestMapping("/miniBuyPay")
	@ApiMethod(isLogin = true)
	public WxUnifiedResult miniBuyPay(MobileInfo mobileInfo,
                                      @ApiParam(value = "支付方式:2.微信", required = true)Integer platform,
                                      @ApiParam(value = "订单id", required = true)Integer orderId,
                                      @ApiParam(value = "微信唯一标识openId", required = true)String openId){

		return apiUserPayService.miniBuyPay(mobileInfo,platform,orderId,openId);
	}

	@ApiOperation(value = "购物车下单", notes = "登陆")
	@RequestMapping("/createShopCartOrder")
	@ApiMethod(isLogin = true)
	public List<PayResult> createShopCartOrder(MobileInfo mobileInfo,
                                               @ApiParam(value = "json参数", required = true)String data,
                                               @ApiParam(value = "地址id", required = true)Integer addressId){
//		MobileInfo mobileInfo=new MobileInfo();
//		mobileInfo.setUserid(userId);
		return apiUserPayService.createShopCartOrder(mobileInfo,data,addressId);
	}


	@ApiOperation(value = "商城购物车支付", notes = "登陆")
	@RequestMapping("/buyShopCartPay")
	@ApiMethod(isLogin = true)
	public PayResult buyShopCartPay(MobileInfo mobileInfo,
                                    @ApiParam(value = "支付方式:1:支付宝 2:微信", required = true)Integer platform,
                                    @ApiParam(value = "订单id,以,相隔", required = true)String orderIds){
		if (platform == null){
			throw new ApiException("platform");
		}
		if (orderIds == null){
			throw new ApiException("orderIds不能为空");
		}
//		MobileInfo mobileInfo=new MobileInfo();
//		mobileInfo.setUserid(userId);
		return apiUserPayService.buyShopCartPay(mobileInfo,platform,orderIds);


//		if (order.getState() != 2){
//			throw new ApiException(MEnumError.WM_ORDER_NOTEXISTS);
//		}



	}


}
