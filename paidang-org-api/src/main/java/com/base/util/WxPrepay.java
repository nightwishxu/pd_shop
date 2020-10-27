//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.util;

import com.base.pay.MPayType;
import com.base.pay.wx.client.TenpayHttpClient;
import com.base.pay.wx.util.MD5Util;
import com.base.pay.wx.util.WXUtil;
import com.base.pay.wx.util.WxPayConfig;
import com.base.pay.wx.util.XMLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

public class WxPrepay {
    private static Logger logger = LoggerFactory.getLogger(WxPrepay.class);
    private static TenpayHttpClient client = new TenpayHttpClient();
    private static final String API_URL = "https://api.mch.weixin.qq.com/";
    public static final String SANDBOXNEW = "sandboxnew/";
    private static final String unifiedorder = "pay/unifiedorder";
    private static final String refundorder = "secapi/pay/refund";
    private static final String refundquery = "pay/refundquery";
    private static final String transfers = "mmpaymkttransfers/promotion/transfers";
    private static final String gettransferinfo = "mmpaymkttransfers/gettransferinfo";
    private static final String sendredpack = "mmpaymkttransfers/sendredpack";
    private static final String gethbinfo = "mmpaymkttransfers/gethbinfo";
    private static final String getsignkey = "pay/getsignkey";
    private static String[] WAP_PARAMS = new String[]{"appid", "noncestr", "package", "prepayid", "sign", "timestamp"};
    private static final BigDecimal HUNDRED = new BigDecimal(100);

    public WxPrepay() {
    }

    private static String getUrl(String method) {
        return "https://api.mch.weixin.qq.com/" + (WxPayConfig.TEST ? "sandboxnew/" : "") + method;
    }

    private static String getSandboxKey(SortedMap<String, String> params) {
        try {
            if (client.callHttpPost(getUrl("pay/getsignkey"), getXml(params))) {
                String result = client.getResContent();
                Map<String, String> map = XMLUtil.doXMLParse(result);
                return (String)map.get("sandbox_signkey");
            }
        } catch (Exception var3) {
            logger.error("", var3);
        }

        return null;
    }

    private static String getPrepayId(SortedMap<String, String> params) {
        params.put("key", getApiKey((String)params.get("mch_id"), (String)params.get("key"), WxPayConfig.TEST));

        try {
            System.out.print("sign start****");
            if (client.callHttpPost(getUrl("pay/unifiedorder"), getXml(params))) {
                String result = client.getResContent();
                logger.debug(result);
                System.out.print("result:"+result);
                Map<String, String> map = XMLUtil.doXMLParse(result);
                return (String)map.get("prepay_id");
            }
        } catch (Exception var3) {
            logger.error("", var3);
        }
        return null;
    }

    private static String getXml(SortedMap<String, String> params) {
        String sign = createSign(params);
        params.remove("key");
        params.put("sign", sign);
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        Set set = params.entrySet();
        Iterator var4 = set.iterator();

        while(true) {
            while(var4.hasNext()) {
                Object o = var4.next();
                Entry object = (Entry)o;
                String k = (String)object.getKey();
                String v = (String)object.getValue();
                if (!"attach".equalsIgnoreCase(k) && !"body".equalsIgnoreCase(k) && !"sign".equalsIgnoreCase(k)) {
                    sb.append("<").append(k).append(">").append(v).append("</").append(k).append(">");
                } else {
                    sb.append("<").append(k).append(">").append("<![CDATA[").append(v).append("]]></").append(k).append(">");
                }
            }

            sb.append("</xml>");
            return sb.toString();
        }
    }

    public static String createSign(SortedMap<String, String> params) {
        StringBuilder sb = new StringBuilder();
        Set es = params.entrySet();
        Iterator var3 = es.iterator();

        while(var3.hasNext()) {
            Object e = var3.next();
            Entry entry = (Entry)e;
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k).append("=").append(v).append("&");
            }
        }

        sb.append("key=").append((String)params.get("key"));
        return MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
    }

    public static String wxJsPrepareId(BigDecimal price, String orderId, String info, String ip, String openId, MPayType attach) {
        SortedMap<String, String> params = new TreeMap();
        params.put("body", info);
        params.put("out_trade_no", orderId);
        params.put("total_fee", price.multiply(HUNDRED).intValue() + "");
        params.put("spbill_create_ip", ip);
        params.put("trade_type", "JSAPI");
        params.put("openid", openId);
        params.put("key", WxPayConfig.PAY_KEY);
        params.put("attach", attach.toString());
        return getPrepayId(wxWebPrepareId(params));
    }

    public static SortedMap<String, String> wxWebPrepareId(Map<String, String> map) {
        SortedMap<String, String> params = new TreeMap();
        params.put("appid", WxPayConfig.MINI_APP_ID);
        params.put("mch_id", WxPayConfig.MINI_PARTNER);
        params.put("nonce_str", WXUtil.getNonceStr());
//        params.put("body", "微信订单");
//        params.put("out_trade_no", WXUtil.getTimeStamp());
//        params.put("total_fee", "0.01");
//        params.put("spbill_create_ip", "8.8.8.8");
        params.put("notify_url", WxPayConfig.BACK_URL);
//        params.put("trade_type", "JSAPI");
        params.putAll(map);
        return params;
    }

//
    private static String getApiKey(String partner, String key, boolean test) {
        return test ? getSandboxSignKey(partner, key) : key;
    }

    private static String getSandboxSignKey(String partner, String key) {
        SortedMap<String, String> params = new TreeMap();
        params.put("mch_id", partner);
        params.put("nonce_str", WXUtil.getNonceStr());
        params.put("key", key);
        return getSandboxKey(params);
    }

//    public static void main(String[] args) {
//        System.out.println(getSandboxSignKey("1516230581", "RPhxHKKC2NNr8xefj0axClYALctreD4M"));
//    }
}
