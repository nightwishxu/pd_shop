package com.util;

import com.base.util.JSONUtils;
import com.util.WxBank.BankCardApiResult;
import com.wxapi.WxApiCall.WxApiCall;
//import com.wxapi.WxApiCall.WxApiCall;

import java.util.HashMap;
import java.util.Map;

public class WxBankUtil {
    private static final String API_URL = "https://way.jd.com/DetectionScience/bank4";
    private static final String API_KEY = "8d168c5d935604dfe97a0619ea3ed84b ";
    private static final boolean TEST = false;


    /**
     * 银行卡验证
     * @param name
     * @param phone
     * @param idCard
     * @param bankCard
     * @return BankCardResult
     */
    public static BankCardApiResult verify(String name, String phone, String idCard, String bankCard){
        com.wxapi.model.RequestModel model = new com.wxapi.model.RequestModel();
        model.setGwUrl(API_URL);
        model.setAppkey(API_KEY);
        Map queryMap = new HashMap();
        queryMap.put("name",name); //真实姓名
        queryMap.put("mobileNo",phone); //手机号
        queryMap.put("identityNo",idCard); //身份证号
        queryMap.put("bankCardNo",bankCard); //银行卡号
        model.setQueryParams(queryMap);
        WxApiCall call = new WxApiCall();
        call.setModel(model);
        String result_json = call.request();
        System.out.println("调用接口数据反馈：");
        System.out.println(result_json);

        BankCardApiResult cardApiResult = JSONUtils.deserialize(result_json, BankCardApiResult.class);
        return cardApiResult;
    }
}
