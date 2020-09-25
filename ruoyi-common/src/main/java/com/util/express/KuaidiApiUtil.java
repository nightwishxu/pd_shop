package com.util.express;

import com.alibaba.fastjson.JSONObject;
import com.base.util.JSONUtils;
import com.base.util.LogKit;
import com.base.util.http.HttpUtil;
import com.util.express.core.KuaidiParam;
import com.util.express.core.KuaidiResult;
import com.util.express.core.MD5;

import java.util.HashMap;
import java.util.Map;

public class KuaidiApiUtil {
    private static final String API_URL = "http://api.kuaidi100.com/api?id=ID&com=COM&nu=NU&show=json";
    private static final String API_POST_URL = "https://poll.kuaidi100.com/poll/query.do";
    private static final String API_PUSH_URL = "https://poll.kuaidi100.com/poll";
    private static final String API_KEY = "JNVdxSqH4104";
    private static final String COM = "shunfeng";
    private static final String API_CUSTOMER = "5FBA04A8FDB4B9E7998A7ABF7FB851C1";
    private static final String PUSH_URL = "expressPush";

    /**
     * 快递查询
     * @param code
     * @return
     */
//    public static KuaidiResult query(String code){
//        String url = API_URL.replace("NU",code).replace("COM",COM).replace("ID",API_KEY);
//        String result = HttpUtil.get(url);
//        KuaidiResult ret = JSONUtils.deserialize(result,KuaidiResult.class);
//        if (ret != null && ret.getStatus() == 1){
//            return ret;
//        }
//        return null;
//    }

    public static KuaidiResult query(String code){
        KuaidiParam kuaidiParam = new KuaidiParam();
        kuaidiParam.setCom(COM.toLowerCase());
        kuaidiParam.setNum(code);
        String param = JSONUtils.serialize(kuaidiParam);
        Map<String,Object> params = new HashMap<>();
        params.put("param",param);
        params.put("sign", MD5.encode(param + API_KEY + API_CUSTOMER));
        params.put("customer",API_CUSTOMER);
        String result = HttpUtil.post(API_POST_URL,params);
        LogKit.info("快递查询结果:{}",result);
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
        KuaidiResult result = query("SF1005172400202");
        System.out.println(result.toString());
    }
}
