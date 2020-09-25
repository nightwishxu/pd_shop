package com.api.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.util.UrlUtil;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.pay.wx.util.WxPayConfig;
import com.base.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/wx",produces = {"application/json;charset=UTF-8"})
@Api(tags = "微信小程序信息获取")
public class ApiWxController extends ApiBaseController {

    @ApiOperation(value = "微信小程序--获取小程序的openid", notes = "不需要登录")
    @RequestMapping(value = "/getXCXOpenId", method = RequestMethod.POST)
    @ApiMethod()
    public Map getXCXOpenId(String code) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {

        Map map = new HashMap();
        Map<String, String> wxConfig = new HashMap<>(4);
        if(StringUtil.isEmpty(code)) {
//            throw new ApiException(-1, "缺少code");
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }
        //微信端登录code值
        String wxCode = code;
        logger.info("code=" + code);

        wxConfig.put("appid", WxPayConfig.MINI_APP_ID);
        wxConfig.put("secret", WxPayConfig.MINI_SECRET);
        wxConfig.put("grant_type", "authorization_code");
        wxConfig.put("js_code", wxCode);

        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        // 解析相应内容（转换成json对象）
        JSONObject jsonObject = JSON.parseObject(UrlUtil.doPost(wxConfig));

        map.put("openid",jsonObject.get("openid"));
        //map.put("session_key",jsonObject.get("session_key").toString());

        return map;
    }
}
