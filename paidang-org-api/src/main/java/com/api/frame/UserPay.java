package com.api.frame;

import com.base.pay.PayFilter;
import com.base.pay.PayKeyFilter;
import com.base.pay.ali.AlipayConfig;
import com.base.pay.wx.util.WxPayConfig;
import com.item.dao.model.PayLog;
import com.item.service.BaseService;
import com.paidang.dao.model.Goods;
import com.paidang.dao.model.Order;
import com.paidang.dao.model.UserGoods;
import com.paidang.service.GoodsService;
import com.paidang.service.OrderService;
import com.paidang.service.UserBalanceLogService;
import com.paidang.service.UserGoodsService;
import com.util.MPaidangPayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: Sun
 * @date: 2018/12/29
 */
@Component
public class UserPay implements PayFilter,PayKeyFilter {
    private static final Logger logger = LoggerFactory.getLogger(UserPay.class);
    private static final String[] PAY_PLAT = new String[]{"支付宝","微信"};

    private static final String WEIXIN_RETURN_SUCCESS = "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";
    private static final String WEIXIN_RETURN_FAIL = "<xml><return_code><![CDATA[FAIL]]></return_code></xml>";

    @Autowired
    private com.item.service.PayLogService payLogService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserGoodsService userGoodsService;
    @Autowired
    private UserBalanceLogService userBalanceLogService;


    @Override
    public String aliPayReturn(HttpServletRequest request, HttpServletResponse response, String extraParam) {
        return null;
    }

    @Override
    public String upmpReturn(HttpServletRequest request, HttpServletResponse response, String extraParam) {
        return null;
    }

    @Override
    public void afterPay(String out_trade_no, String trade_no, String trade_status, String buyerEmail, BigDecimal total, Integer payPlatform, String extraParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (MPaidangPayType.NORMAL_BUY.name().equals(extraParam)) {
            afterBuy(out_trade_no, trade_no, trade_status, buyerEmail, total, payPlatform);
        } else {
            //afterBuyPay(out_trade_no, trade_no, trade_status, buyerEmail, total, payPlatform);
        }
    }

    /**
     * 支付回调
     * @param out_trade_no
     * @param trade_no
     * @param trade_status
     * @param buyerEmail
     * @param total
     * @param payPlatform
     * @throws Exception
     */
    private void afterBuy(String out_trade_no, String trade_no, String trade_status, String buyerEmail, BigDecimal total,
                          Integer payPlatform) throws Exception {
        synchronized (this) {
            logger.info("进入{}商城购物回调",PAY_PLAT[payPlatform-1]);
            PayLog payLog = null;
            // 重新查找订单状态信息
            BigDecimal tradeStatus = null;
            Integer userId = null;
            try {
                payLog = payLogService.selectByPrimaryKey(out_trade_no);
                tradeStatus = new BigDecimal(payLog.getState());
                userId = payLog.getUserId();
            } catch (Exception e) {
                logger.info("$$$$支付更新没找到记录或找到多条!——商户订单号:" + out_trade_no + ";交易号:" + trade_no + ",交易状态:" + trade_status);
            }

            if (tradeStatus.intValue() == 0) {
                payLog.setState(9);
                payLog.setModifyTime(new Date());
                payLog.setPlatform(payPlatform); // 1支付宝2银联3微信
                payLog.setRealPay(total);
                payLog.setTradeNo(trade_no);
                payLog.setBuyer(buyerEmail);
                payLogService.updateByPrimaryKeySelective(payLog);

                tradeStatus = new BigDecimal(9);
            }

            if (tradeStatus.intValue() == 9) {
                if (total.compareTo(payLog.getMoney()) < 0) {
                    payLog.setState(-1);
                    payLogService.updateByPrimaryKeySelective(payLog);
                    return;
                }

                Order order = orderService.selectByPrimaryKey(payLog.getOrderId());
                if (order == null){
                    logger.info("$$$$业务订单没有找到!——订单号:" + payLog.getOrderId() + ";交易号:" + trade_no + ",交易状态:" + trade_status);
                    return;
                }

                if (order.getState() != 1){
                    logger.info("$$$$业务订单状态错误!——订单号:" + payLog.getOrderId() + ";交易号:" + trade_no + ",交易状态:" + trade_status);
                    return;
                }
                Order orderUpdate = orderService.selectByPrimaryKey(payLog.getOrderId());
                orderUpdate.setState(2);
                orderUpdate.setPayLogId(payLog.getTradeNo());
                orderUpdate.setPayTime(new Date());
                int update = orderService.updateByPrimaryKeySelective(orderUpdate);
                if (update == 0) {
                    logger.info("$$$$业务订单没有更新成功!——商户订单号:" + out_trade_no + ";交易号:" + trade_no + ",交易状态:" + trade_status);
                }
                Goods goods = goodsService.selectByPrimaryKey(order.getGoodsId());

                //商品
                Goods goodsUpdate = new Goods();
                goodsUpdate.setId(order.getGoodsId());
                goodsUpdate.setState(-1);
                goodsUpdate.setIsOnline(0);
                update = goodsService.updateByPrimaryKeySelective(goodsUpdate);
                if (update == 0) {
                    logger.info("$$$$商品没有更新成功!——商户订单号:" + out_trade_no + ";交易号:" + trade_no + ",交易状态:" + trade_status);
                }
                //藏品更新
                if (goods.getSource() == 2){
                    //绝当品归属权
                    UserGoods userGoods = new UserGoods();
                    userGoods.setId(goods.getGoodsId());
                    userGoods.setBelongType(1);
                    userGoods.setBelongId(order.getUserId());
                    update = userGoodsService.updateByPrimaryKeySelective(userGoods);
                    if (update == 0) {
                        logger.info("$$$$藏品没有更新成功!——商户订单号:" + out_trade_no + ";交易号:" + trade_no + ",交易状态:" + trade_status);
                    }
                }

                /**
                 * 用户余额日志  --暂时不用
                 */
//				UserBalanceLog userBalanceLog = new UserBalanceLog();
//				userBalanceLog.setAmount(total);
//				userBalanceLog.setType(2);
//				userBalanceLog.setTradeNo(trade_no);
//				userBalanceLog.setTradeType(payPlatform);
//				userBalanceLog.setUserId(userId);
//				userBalanceLog.setCid(goods.getId());
//				userBalanceLog.setItem("6");
//				userBalanceLog.setInfo("消费:"+total.toString());
//				userBalanceLogService.insert(userBalanceLog);

                payLog.setState(10);
                update = payLogService.updateByPrimaryKeySelective(payLog);
                if (update == 0) {
                    logger.info("$$$$支付流水没有更新成功!——商户订单号:" + out_trade_no + ";交易号:" + trade_no + ",交易状态:" + trade_status);
                }
            }
        }
    }

    @Override
    public String[] getAliKey(String appId, String partner, String outTradeNo, String tradeNo, HttpServletRequest request, HttpServletResponse response) {
        return new String[]{AlipayConfig.ali_public_key};
    }

    @Override
    public String getWxKey(String appId, String partner, String outTradeNo, String tradeNo, HttpServletRequest request, HttpServletResponse response) {
        return WxPayConfig.PAY_KEY;
    }
}
