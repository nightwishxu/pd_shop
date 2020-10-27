package com.api.frame;

import com.api.MEnumError;
import com.base.api.ApiException;
import com.base.pay.PayFilter;
import com.item.dao.model.PayLog;
import com.item.service.BaseService;
import com.paidang.dao.model.OrgBalanceLog;
import com.paidang.dao.model.PawnContinue;
import com.paidang.dao.model.PlatformBalanceLog;
import com.paidang.dao.model.UserPawn;
import com.paidang.service.OrgBalanceLogService;
import com.paidang.service.PawnContinueService;
import com.paidang.service.PlatformBalanceLogService;
import com.paidang.service.UserPawnService;
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
public class OrgPay implements PayFilter {
    private static final Logger logger = LoggerFactory.getLogger(OrgPay.class);

    private static final String[] PAY_PLAT = new String[]{"支付宝","微信"};

    @Autowired
    private com.item.service.PayLogService payLogService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private PlatformBalanceLogService platformBalanceLogService;
    @Autowired
    private OrgBalanceLogService orgBalanceLogService;
    @Autowired
    private UserPawnService userPawnService;
    @Autowired
    private PawnContinueService pawnContinueService;

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
        if (MPaidangPayType.ORG_RATE.name().equals(extraParam)) {
            afterOrgPay(out_trade_no, trade_no, trade_status, buyerEmail, total, payPlatform);
        } else if(MPaidangPayType.ORG_CONTINUE.name().equals(extraParam)){
            afterOrgContinuePay(out_trade_no, trade_no, trade_status, buyerEmail, total, payPlatform);
        }
    }

    /**
     * 充值回掉
     * @param out_trade_no
     * @param trade_no
     * @param trade_status
     * @param buyerEmail
     * @param total
     * @param payPlatform
     * @throws Exception
     */
    private void afterOrgPay(String out_trade_no, String trade_no, String trade_status, String buyerEmail, BigDecimal total,
                             Integer payPlatform) throws Exception {
        synchronized (this) {
            logger.info("进入{}典当手续费支付回调",PAY_PLAT[payPlatform-1]);
            PayLog payLog = null;
            // 重新查找订单状态信息
            BigDecimal tradeStatus = null;
            Integer userId = null;
            try {
                payLog = payLogService.selectByPrimaryKey(Long.parseLong(out_trade_no));
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

                UserPawn pawn = userPawnService.selectByPrimaryKey(payLog.getOrderId());
                if (pawn == null){
                    throw new ApiException(MEnumError.CONTENT_NOEXIST_ERROR);
                }

                //修改平台服务费支付状态
                pawn.setPlatformState(1);
                userPawnService.updateByPrimaryKey(pawn);

                PlatformBalanceLog platformBalanceLog = new PlatformBalanceLog();
                platformBalanceLog.setAmount(total);
                platformBalanceLog.setInfo("机构"+pawn.getOrgId()+"支付典当id="+pawn.getId()+"的典当平台服务费"+total);
                //	platformBalanceLog.setItem("");
                platformBalanceLog.setType(1);//1-典当 2-续当
                platformBalanceLog.setFid(pawn.getId());//典当id
                platformBalanceLog.setPayLogId(payLog.getId());
                platformBalanceLogService.insert(platformBalanceLog);

                OrgBalanceLog orgBalanceLog = new OrgBalanceLog();
                orgBalanceLog.setOrgId(pawn.getOrgId());
                orgBalanceLog.setMoney(total);
                orgBalanceLog.setType(1);//1支出2收入
                orgBalanceLog.setItem("5");//1用户典当  2用户续当 3用户赎当 4卖给平台 5典当平台服务费 6续当平台服务费
                orgBalanceLog.setInfo("机构"+pawn.getOrgId()+"支付典当id="+pawn.getId()+"的典当平台服务费"+total);
                orgBalanceLog.setPawnCode(pawn.getPawnTicketCode());
                orgBalanceLog.setFid(pawn.getId());
                orgBalanceLog.setPayLogId(payLog.getId());
                orgBalanceLog.setTradeType(2);//0余额1支付宝2微信10线下银行卡
                orgBalanceLog.setTradeNo(trade_no);//支付宝微信流水号
                orgBalanceLog.setUserId(pawn.getUserId());
                orgBalanceLog.setUserName(pawn.getUserName());
                orgBalanceLog.setUserPhone(pawn.getUserPhone());
                orgBalanceLogService.insert(orgBalanceLog);
//				RechargeOrder order = rechargeOrderService.selectByPrimaryKey(payLog.getOrderId());
//				if (order == null){
//					logger.info("$$$$业务订单没有找到!——订单号:" + payLog.getOrderId() + ";交易号:" + trade_no + ",交易状态:" + trade_status);
//					return;
//				}
//
//				if (order.getState() != 0){
//					logger.info("$$$$业务订单状态错误!——订单号:" + payLog.getOrderId() + ";交易号:" + trade_no + ",交易状态:" + trade_status);
//					return;
//				}
//
//				int update = baseService.updateNumById("b_user", "balance", total, userId);
//
//				if (update > 0){
//					// 余额日志
//					UserBalanceLog balanceLog = new UserBalanceLog();
//					balanceLog.setAmount(total);
//					balanceLog.setItem("充值");
//					balanceLog.setInfo("充值金额:"+toString());
//					balanceLog.setType(1);
//					balanceLog.setUserId(userId);
//					balanceLog.setTradeType(payPlatform);
//					balanceLogService.insert(balanceLog);
//				}else{
//					logger.info("$$$$业务订单没有更新成功!——商户订单号:" + out_trade_no + ";交易号:" + trade_no + ",交易状态:" + trade_status);
//					return;
//				}
//
                payLog.setState(10);
                payLogService.updateByPrimaryKeySelective(payLog);

//				order.setTradeNo(trade_no);
//				order.setBuyer(buyerEmail);
//				order.setRealPay(total);
//				order.setPlatform(payPlatform);
//				order.setState(1);
//				update = rechargeOrderService.updateByPrimaryKey(order);
//
//				if (update == 0) {
//					logger.info("$$$$业务订单没有更新成功!——商户订单号:" + out_trade_no + ";交易号:" + trade_no + ",交易状态:" + trade_status);
//				}

            }
        }
    }

    /**
     * 充值回掉
     * @param out_trade_no
     * @param trade_no
     * @param trade_status
     * @param buyerEmail
     * @param total
     * @param payPlatform
     * @throws Exception
     */
    private void afterOrgContinuePay(String out_trade_no, String trade_no, String trade_status, String buyerEmail, BigDecimal total,
                                     Integer payPlatform) throws Exception {
        synchronized (this) {
            logger.info("进入{}续当手续费支付回调",PAY_PLAT[payPlatform-1]);
            PayLog payLog = null;
            // 重新查找订单状态信息
            BigDecimal tradeStatus = null;
            Integer userId = null;
            try {
                payLog = payLogService.selectByPrimaryKey(Long.parseLong(out_trade_no));
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

                PawnContinue pawnContinue = pawnContinueService.selectByPrimaryKey(payLog.getOrderId());
                if (pawnContinue == null){
                    throw new ApiException(MEnumError.CONTENT_NOEXIST_ERROR);
                }
                //原首次典当信息
                UserPawn userPawn = userPawnService.selectByPrimaryKey(pawnContinue.getPawnId());
                if (userPawn == null){
                    throw new ApiException(MEnumError.CONTENT_NOEXIST_ERROR);
                }

                //修改续当平台服务费
                pawnContinue.setPlatformState(1);
                pawnContinueService.updateByPrimaryKey(pawnContinue);

                PlatformBalanceLog platformBalanceLog = new PlatformBalanceLog();
                platformBalanceLog.setAmount(total);
                platformBalanceLog.setInfo("机构"+userPawn.getOrgId()+"支付续当id="+pawnContinue.getId()+"的续当平台服务费"+total);
                //	platformBalanceLog.setItem("");
                platformBalanceLog.setType(2);//1-典当 2-续当
                platformBalanceLog.setFid(pawnContinue.getId());//续当id
                platformBalanceLog.setPayLogId(payLog.getId());
                platformBalanceLogService.insert(platformBalanceLog);

                OrgBalanceLog orgBalanceLog = new OrgBalanceLog();
                orgBalanceLog.setOrgId(userPawn.getOrgId());
                orgBalanceLog.setMoney(total);
                orgBalanceLog.setType(1);//1支出2收入
                orgBalanceLog.setItem("6");//1用户典当  2用户续当 3用户赎当 4卖给平台 5典当平台服务费 6续当平台服务费
                orgBalanceLog.setInfo("机构"+userPawn.getOrgId()+"支付续当id="+pawnContinue.getId()+"的续当平台服务费"+total);
                orgBalanceLog.setPawnCode(pawnContinue.getPawnTicketCode());
                orgBalanceLog.setFid(pawnContinue.getId());
                orgBalanceLog.setPayLogId(payLog.getId());
                orgBalanceLog.setTradeType(2);//0余额1支付宝2微信10线下银行卡
                orgBalanceLog.setTradeNo(trade_no);//支付宝微信流水号
                orgBalanceLog.setUserId(userPawn.getUserId());
                orgBalanceLog.setUserName(pawnContinue.getUserName());
                orgBalanceLog.setUserPhone(userPawn.getUserPhone());
                orgBalanceLogService.insert(orgBalanceLog);
//				RechargeOrder order = rechargeOrderService.selectByPrimaryKey(payLog.getOrderId());
//				if (order == null){
//					logger.info("$$$$业务订单没有找到!——订单号:" + payLog.getOrderId() + ";交易号:" + trade_no + ",交易状态:" + trade_status);
//					return;
//				}
//
//				if (order.getState() != 0){
//					logger.info("$$$$业务订单状态错误!——订单号:" + payLog.getOrderId() + ";交易号:" + trade_no + ",交易状态:" + trade_status);
//					return;
//				}
//
//				int update = baseService.updateNumById("b_user", "balance", total, userId);
//
//				if (update > 0){
//					// 余额日志
//					UserBalanceLog balanceLog = new UserBalanceLog();
//					balanceLog.setAmount(total);
//					balanceLog.setItem("充值");
//					balanceLog.setInfo("充值金额:"+toString());
//					balanceLog.setType(1);
//					balanceLog.setUserId(userId);
//					balanceLog.setTradeType(payPlatform);
//					balanceLogService.insert(balanceLog);
//				}else{
//					logger.info("$$$$业务订单没有更新成功!——商户订单号:" + out_trade_no + ";交易号:" + trade_no + ",交易状态:" + trade_status);
//					return;
//				}
//
                payLog.setState(10);
                payLogService.updateByPrimaryKeySelective(payLog);
//
//				order.setTradeNo(trade_no);
//				order.setBuyer(buyerEmail);
//				order.setRealPay(total);
//				order.setPlatform(payPlatform);
//				order.setState(1);
//				update = rechargeOrderService.updateByPrimaryKey(order);
//
//				if (update == 0) {
//					logger.info("$$$$业务订单没有更新成功!——商户订单号:" + out_trade_no + ";交易号:" + trade_no + ",交易状态:" + trade_status);
//				}

            }
        }
    }
}
