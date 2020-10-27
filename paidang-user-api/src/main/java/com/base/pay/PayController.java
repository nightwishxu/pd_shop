//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.base.pay;

import com.api.frame.UserPay;
import com.base.CoreConstants;
import com.base.pay.ali.AlipayConfig;
import com.base.pay.ali.AlipayNotify;
import com.base.pay.wx.WxPrepay;
import com.base.pay.wx.util.XMLUtil;
import com.base.util.StringUtil;
import com.unionpay.acp.sdk.LogUtil;
import com.unionpay.acp.sdk.SDKUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.RequestUtils;
import com.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"payNotify"})
public class PayController {
    private static final Logger logger = LoggerFactory.getLogger(PayController.class);
    private static final String WEIXIN_RETURN_SUCCESS = "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";
    private static final String WEIXIN_RETURN_FAIL = "<xml><return_code><![CDATA[FAIL]]></return_code></xml>";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    @Autowired
    private UserPay payFilter;
    @Autowired
    private UserPay payKeyFilter;

    public PayController() {
    }

    @RequestMapping({"/aliPayReturn"})
    public String aliPayWebReturn(HttpServletRequest request, HttpServletResponse response) {
        logger.info("$$$$alipayWebReturn进入!");

        try {
            Map<String, String> params = RequestUtils.getFromRequest(request);
            String outTradeNo = (String)params.get("out_trade_no");
            String tradeNo = (String)params.get("trade_no");
            String tradeStatus = (String)params.get("trade_status");
            String appId = (String)params.get("app_id");
            String extraParam = "";
            if (StringUtil.isNotBlank(appId)) {
                extraParam = (String)params.get("passback_params");
            } else {
                int offset = outTradeNo.indexOf("_");
                if (offset > -1) {
                    extraParam = outTradeNo.substring(offset + 1);
                    outTradeNo = outTradeNo.substring(0, offset);
                }
            }

            boolean verifyResult = AlipayNotify.verify(params, this.payKeyFilter.getAliKey(appId, (String)params.get("seller_id"), outTradeNo, tradeNo, request, response));
            if (verifyResult) {
                if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {
                    logger.info("$$$$alipayReturn验证成功-开始进行关闭跳转extraParam:" + extraParam);
                    String page = this.payFilter.aliPayReturn(request, response, extraParam);
                    return "redirect:" + page;
                }
            } else {
                logger.info("$$$$alipayReturn验证失败extraParam:" + extraParam + "——商户订单号:" + outTradeNo + ";支付宝交易号:" + tradeNo + ",交易状态:" + tradeStatus);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("验证失败");
            }
        } catch (Exception var12) {
            logger.info("$$$$alipayReturn异常:" + var12.getMessage(), var12);

            try {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("alipayWebReturn异常");
            } catch (IOException var11) {
            }
        }

        return null;
    }

    @RequestMapping({"/aliPayNotify"})
    public void aliPayNotify(HttpServletRequest request, HttpServletResponse response) {
        logger.info("$$$$alipayNotify进入!");

        try {
            Map<String, String> params = RequestUtils.getFromRequest(request);
            String buyerEmail = (String)params.get(AlipayConfig.BUYER_ID);
            BigDecimal total = new BigDecimal((String)params.get(AlipayConfig.AMOUNT));
            String outTradeNo = (String)params.get("out_trade_no");
            String tradeNo = (String)params.get("trade_no");
            String tradeStatus = (String)params.get("trade_status");
            String extraParam = "";
            String appId = (String)params.get("app_id");
            int offset = outTradeNo.indexOf("_");
            if (offset > -1) {
                extraParam = outTradeNo.substring(offset + 1);
                outTradeNo = outTradeNo.substring(0, offset);
            }

            if (StringUtil.isNotBlank(appId)) {
                String extra = (String)params.get("passback_params");
                if (StringUtil.isNotBlank(extra)) {
                    extraParam = extra;
                }
            }

            if (AlipayNotify.verify(params, this.payKeyFilter.getAliKey(appId, (String)params.get("seller_id"), outTradeNo, tradeNo, request, response))) {
                logger.info("$$$$alipayNotify验证成功");
                if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {
                    this.payFilter.afterPay(outTradeNo, tradeNo, tradeStatus, buyerEmail, total, 1, extraParam, request, response);
                }
            } else {
                logger.info("$$$$alipayNotify验证失败——商户订单号:" + outTradeNo + ";支付宝交易号:" + tradeNo + ",交易状态:" + tradeStatus);
            }

            ResponseUtils.renderText(response, "success");
        } catch (Exception var13) {
            logger.info("$$$$alipayNotify业务逻辑异常:" + var13.getMessage(), var13);
            ResponseUtils.renderText(response, "fail");
        }

    }

    @RequestMapping({"/wxMobileNotify"})
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("@@@@收到微信支付信息,进入notify流程@@@@");

        try {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();

            String s;
            while((s = br.readLine()) != null) {
                sb.append(s);
            }

            br.close();
            in.close();
            Map<String, String> params = XMLUtil.doXMLParse(sb.toString());
            if (params == null) {
                return;
            }

            SortedMap<String, String> newParams = new TreeMap(params);
            String outTradeNo = (String)params.get("out_trade_no");
            String tradeNo = (String)params.get("transaction_id");
            String total = (String)params.get("total_fee");
            String respCode = (String)params.get("result_code");
            String openId = (String)params.get("openid");
            String tradeType = (String)params.get("trade_type");
            int payPlatform = StringUtil.equalsIgnoreCase("app", tradeType) ? 2 : 4;
            String extraParam = (String)params.get("attach");
            newParams.put("key", this.payKeyFilter.getWxKey((String)params.get("appid"), (String)params.get("mch_id"), outTradeNo, tradeNo, request, response));
            if (WxPrepay.isValiSign(newParams)) {
                logger.info("@@@@验证成功@@@@");
                if (StringUtil.equals(respCode, "SUCCESS")) {
                    this.payFilter.afterPay(outTradeNo, tradeNo, respCode, openId, (new BigDecimal(total)).divide(CoreConstants.HUNDRED, 2, 4), payPlatform, extraParam, request, response);
                } else {
                    logger.info("@@@@支付交易状态未知——订单号:" + outTradeNo + ";交易状态:" + respCode + ";微信支付订单号:" + tradeNo);
                }
            } else {
                logger.info("@@@@验证失败@@@@");
            }

            response.setContentType("text/xml;charset=utf-8");
            response.getWriter().println("<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>");
        } catch (Exception var17) {
            logger.info("@@@@支付后业务逻辑异常" + var17.getMessage() + "@@@@", var17);
            response.setContentType("text/xml;charset=utf-8");
            response.getWriter().println("<xml><return_code><![CDATA[FAIL]]></return_code></xml>");
        }

    }

    @RequestMapping(
            value = {"/upmpReturn"},
            method = {RequestMethod.POST}
    )
    public String upmpReturn(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("@@@@收到upmpReturn通知信息,进入return流程@@@@");

        try {
            req.setCharacterEncoding("ISO-8859-1");
            String encoding = req.getParameter("encoding");
            Map<String, String> respParam = getAllRequestParam(req);
            LogUtil.printRequestLog(respParam);
            Map<String, String> valideData = validata(respParam, encoding);
            if (!SDKUtil.validate(valideData, encoding)) {
                LogUtil.writeLog("@@@验证签名结果[失败].");
            } else {
                LogUtil.writeLog("@@@验证签名结果[成功].");
            }

            LogUtil.writeLog("@@@upmpReturn前台接收报文返回结束");
            String extraParam = (String)respParam.get("reqReserved");
            String page = this.payFilter.upmpReturn(req, resp, extraParam);
            return "redirect:" + page;
        } catch (Exception var8) {
            LogUtil.writeLog("@@@upmpReturn前台接收报文返回失败");
            return null;
        }
    }

    @RequestMapping({"/upmpNotify"})
    public void upmpNotify(HttpServletRequest request, HttpServletResponse response) {
        LogUtil.writeLog("@@@@upmpMobileNotify接收后台通知开始");

        try {
            request.setCharacterEncoding("ISO-8859-1");
            String encoding = request.getParameter("encoding");
            Map<String, String> reqParam = getAllRequestParam(request);
            LogUtil.printRequestLog(reqParam);
            Map<String, String> valideData = validata(reqParam, encoding);
            String outTradeNo = new String(request.getParameter("orderId").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String tradeNo = new String(request.getParameter("queryId").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String respCode = new String(request.getParameter("respCode").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String transStatus = new String(request.getParameter("respMsg").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            BigDecimal total = (new BigDecimal((String)valideData.get("txnAmt"))).divide(CoreConstants.HUNDRED, 2, 4);
            String extraParam = new String(request.getParameter("reqReserved").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            if (!SDKUtil.validate(valideData, encoding)) {
                LogUtil.writeLog("@@@@验证签名结果[失败].");
            } else {
                LogUtil.writeLog("@@@@验证签名结果[成功].");
                if (StringUtil.equals(transStatus, "Success!")) {
                    this.payFilter.afterPay(outTradeNo, tradeNo, transStatus, "", total, 3, extraParam, request, response);
                } else {
                    logger.info("@@@@支付交易状态未知——订单号:" + outTradeNo + ";响应码:" + respCode + ";银联查询流水号:" + tradeNo + ",交易状态:" + transStatus);
                }
            }

            LogUtil.writeLog("BackRcvResponse接收后台通知结束");
            ResponseUtils.renderText(response, "success");
        } catch (Exception var12) {
            logger.info("$$$$支付后业务逻辑异常:" + var12.getMessage(), var12);
            ResponseUtils.renderText(response, "fail");
        }

    }

    private static Map<String, String> validata(Map<String, String> reqParam, String encoding) throws Exception {
        Map<String, String> valideData = null;
        if (null != reqParam && !reqParam.isEmpty()) {
            Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
            valideData = new HashMap(reqParam.size());

            while(it.hasNext()) {
                Entry<String, String> e = (Entry)it.next();
                String key = (String)e.getKey();
                String value = (String)e.getValue();
                value = new String(value.getBytes(StandardCharsets.ISO_8859_1), encoding);
                valideData.put(key, value);
            }
        }

        return valideData;
    }

    private static Map<String, String> getAllRequestParam(HttpServletRequest request) {
        Map<String, String> res = new HashMap();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while(true) {
                String en;
                do {
                    if (!temp.hasMoreElements()) {
                        return res;
                    }

                    en = (String)temp.nextElement();
                    String value = request.getParameter(en);
                    res.put(en, value);
                } while(null != res.get(en) && !"".equals(res.get(en)));

                res.remove(en);
            }
        } else {
            return res;
        }
    }
}
