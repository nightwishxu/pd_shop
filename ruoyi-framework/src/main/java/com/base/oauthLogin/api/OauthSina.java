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

public class OauthSina extends Oauth {
    private static final String AUTH_URL = "https://api.weibo.com/oauth2/authorize";
    private static final String TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
    private static final String TOKEN_INFO_URL = "https://api.weibo.com/oauth2/get_token_info";
    private static final String USER_INFO_URL = "https://api.weibo.com/2/users/show.json";
    private static OauthSina oauthSina = new OauthSina();

    private OauthSina() {
    }

    public static OauthSina me() {
        return oauthSina;
    }

    public String getAuthorizeUrl(String state) {
        return super.getAuthorizeUrl("https://api.weibo.com/oauth2/authorize", state);
    }

    public String getTokenByCode(String code) {
        Map<String, String> params = new HashMap();
        params.put("code", code);
        params.put("client_id", this.getClientId());
        params.put("client_secret", this.getClientSecret());
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", this.getRedirectUri());
        String token = TokenUtil.getAccessToken(super.doPost("https://api.weibo.com/oauth2/access_token", params));
        this.logger.debug(token);
        return token;
    }

    public String getTokenInfo(String accessToken) {
        Map<String, String> params = new HashMap();
        params.put("access_token", accessToken);
        String jsonStr = super.doPost("https://api.weibo.com/oauth2/get_token_info", params);
        JSONObject json = JSONObject.parseObject(jsonStr);
        String openid = json.getString("uid");
        this.logger.debug(openid);
        return openid;
    }

    public String getUserInfo(String accessToken, String uid) {
        Map<String, String> params = new HashMap();
        params.put("uid", uid);
        params.put("access_token", accessToken);
        String userInfo = super.doGet("https://api.weibo.com/2/users/show.json", params);
        this.logger.debug(userInfo);
        return userInfo;
    }

    public JSONObject getUserInfoByCode(String code) {
        String accessToken = this.getTokenByCode(code);
        if (StringUtil.isBlank(accessToken)) {
            throw new RuntimeException("Token is Blank!");
        } else {
            String uid = this.getTokenInfo(accessToken);
            if (StringUtil.isBlank(uid)) {
                throw new RuntimeException("accessToken is Blank!");
            } else {
                JSONObject dataMap = JSON.parseObject(this.getUserInfo(accessToken, uid));
                dataMap.put("access_token", accessToken);
                return dataMap;
            }
        }
    }

    public Oauth getSelf() {
        return this;
    }
}
