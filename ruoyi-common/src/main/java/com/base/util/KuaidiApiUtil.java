package com.base.util;

import com.alibaba.fastjson.JSONObject;
import com.base.util.express.core.KuaidiParam;
import com.base.util.express.core.KuaidiResult;
import com.base.util.express.core.MD5;
import com.base.util.http.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.base.http.HttpUtil;
//import com.base.support.LogKit;
//import com.base.util.JSONUtils;
//import com.paidang.service.CacheService;
//import com.util.express.core.KuaidiParam;
//import com.util.express.core.KuaidiResult;
//import com.util.express.core.MD5;
//import org.apache.commons.lang.StringUtils;

public class KuaidiApiUtil {
    private static final String API_URL = "http://api.kuaidi100.com/api?id=ID&com=COM&nu=NU&show=json";
    private static final String API_POST_URL = "https://poll.kuaidi100.com/poll/query.do";
    private static final String API_PUSH_URL = "https://poll.kuaidi100.com/poll";
    private static final String COM = "shunfeng";
//    private static final String API_KEY = "JNVdxSqH4104";
//    private static final String API_CUSTOMER = "5FBA04A8FDB4B9E7998A7ABF7FB851C1";
    private static final String API_KEY = "VtuXRIVz5029";
    private static final String API_CUSTOMER = "71574EBF7B029E3B5C8F36D5A1E46D7A";
    private static final String PUSH_URL = "expressPush";


    /**
     * 快递公司map集合
     */
    private static Map<String, String> comMap;

    public static String[] expressArr ={"顺丰快递","中通快递","ems","圆通快递","韵达快递","百世汇通","申通快递"};

    /**
     * 默认构造函数
     */
    static {
        comMap = new HashMap<String, String>();
        comMap.put("yuantong", "yuantong");
        comMap.put("yunda", "yunda");
        comMap.put("huitongkuaidi", "huitongkuaidi");
        comMap.put("zhongtong", "zhongtong");
        comMap.put("shentong", "shentong");
        comMap.put("ems", "ems");
        comMap.put("中通速递", "zhongtong");
        comMap.put("中通快递", "zhongtong");
        comMap.put("邮政速递", "ems");
        comMap.put("圆通速递", "yuantong");
        comMap.put("圆通快递", "yuantong");
        comMap.put("韵达快运", "yunda");
        comMap.put("韵达快递", "yunda");
        comMap.put("韵达速递", "yunda");
        comMap.put("汇通快运", "huitongkuaidi");
        comMap.put("百世汇通", "huitongkuaidi");
        comMap.put("申通快递", "shentong");
        comMap.put("顺丰", "shunfeng");
        comMap.put("顺丰快递", "shunfeng");
    }

//    顺丰快递","圆通快递","中通快递","韵达快递
//    {"申通快递":"shentong","shunfeng":"shunfeng","百世汇通":"huitongkuaidi","韵达快运":"yunda","邮政速递":"ems","中通速递":"zhongtong","汇通快运":"huitongkuaidi","圆通速递":"yuantong","顺丰快递":"shunfeng","韵达速递":"yunda"}


    /**
     * 快递查询
     * @param code
     * @return
     */
    public static KuaidiResult query(String code){
        String url = API_URL.replace("NU",code).replace("COM",COM).replace("ID",API_KEY);
        String result = HttpUtil.get(url);
        KuaidiResult ret = JSONUtils.deserialize(result,KuaidiResult.class);
        if (ret != null && ret.getStatus() == 1){
            return ret;
        }
        return null;
    }

    public static KuaidiResult query(String com, String code){
        KuaidiParam kuaidiParam = new KuaidiParam();
        if (StringUtils.isBlank(com)){
            kuaidiParam.setCom(COM.toLowerCase());
        }else {
            kuaidiParam.setCom(comMap.get(com).toLowerCase());
        }
        kuaidiParam.setNum(code);
        String param = JSONUtils.serialize(kuaidiParam);
        Map<String,Object> params = new HashMap<>();
        params.put("param",param);
        params.put("sign", MD5.encode(param + API_KEY + API_CUSTOMER));
        params.put("customer",API_CUSTOMER);
        String result = HttpUtil.post(API_POST_URL,params);
        LogKit.debug("快递查询结果:{}",result);
        KuaidiResult ret = JSONUtils.deserialize(result,KuaidiResult.class);
        if (ret != null && ret.getStatus() == 200){
            return ret;
        }
        return null;
    }

    public static boolean push(String code){
        JSONObject parameters = new JSONObject();
        parameters.put("callbackurl",PUSH_URL);
        JSONObject param = new JSONObject();
        param.put("company",COM.toLowerCase());
        param.put("number",code);
        param.put("key",API_KEY);
        param.put("parameters",parameters.toJSONString());
        Map<String,Object> params = new HashMap<>();
        params.put("schema","JSON");
        params.put("param",param.toJSONString());
        String result = HttpUtil.post(API_PUSH_URL,params);
        JSONObject ret = JSONObject.parseObject(result);
        if (ret != null){
            if (ret.getIntValue("returnCode") == 200 && ret.getBoolean("result")){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        KuaidiResult result = query("shentong","773008419813563");
        System.out.println(result.toString());
    }
}
