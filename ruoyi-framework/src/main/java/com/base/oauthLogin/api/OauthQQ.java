//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.oauthLogin.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.oauthLogin.util.TokenUtil;
import com.base.util.StringUtil;
import java.util.HashMap;
import java.util.Map;

public class OauthQQ extends Oauth {
    private static final String AUTH_URL = "https://graph.qq.com/oauth2.0/authorize";
    private static final String TOKEN_URL = "https://graph.qq.com/oauth2.0/token";
    private static final String TOKEN_INFO_URL = "https://graph.qq.com/oauth2.0/me";
    private static final String USER_INFO_URL = "https://graph.qq.com/user/get_user_info";
    private static OauthQQ oauthQQ = new OauthQQ();

    private OauthQQ() {
    }

    public static OauthQQ me() {
        return oauthQQ;
    }

    public String getAuthorizeUrl(String state) {
        return super.getAuthorizeUrl("https://graph.qq.com/oauth2.0/authorize", state);
    }

    public String getTokenByCode(String code) {
        Map<String, String> params = new HashMap();
        params.put("code", code);
        params.put("client_id", this.getClientId());
        params.put("client_secret", this.getClientSecret());
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", this.getRedirectUri());
        String token = TokenUtil.getAccessToken(super.doGet("https://graph.qq.com/oauth2.0/token", params));
        this.logger.debug(token);
        return token;
    }

    public String getTokenInfo(String accessToken) {
        Map<String, String> params = new HashMap();
        params.put("access_token", accessToken);
        String openid = TokenUtil.getOpenId(super.doGet("https://graph.qq.com/oauth2.0/me", params));
        this.logger.debug(openid);
        return openid;
    }

    public String getUserInfo(String accessToken, String uid) {
        Map<String, String> params = new HashMap();
        params.put("access_token", accessToken);
        params.put("oauth_consumer_key", this.getClientId());
        params.put("openid", uid);
        params.put("format", "json");
        String userinfo = super.doGet("https://graph.qq.com/user/get_user_info", params);
        this.logger.debug(userinfo);
        return userinfo;
    }

    public JSONObject getUserInfoByCode(String code) {
        String accessToken = this.getTokenByCode(code);
        if (StringUtil.isBlank(accessToken)) {
            throw new RuntimeException("accessToken is Blank!");
        } else {
            String openId = this.getTokenInfo(accessToken);
            if (StringUtil.isNotBlank(openId)) {
                throw new RuntimeException("openId is Blank!");
            } else {
                JSONObject dataMap = JSON.parseObject(this.getUserInfo(accessToken, openId));
                dataMap.put("openid", openId);
                dataMap.put("access_token", accessToken);
                return dataMap;
            }
        }
    }

    public Oauth getSelf() {
        return this;
    }

    public static void main(String[] args) {
        System.out.println(me().getAuthorizeUrl("get_user_info"));
    }
}
