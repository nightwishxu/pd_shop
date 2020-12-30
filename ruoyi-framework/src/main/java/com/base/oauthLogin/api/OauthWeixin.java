//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.oauthLogin.api;

import com.base.oauthLogin.util.HttpUtilExt;
import com.base.util.StringUtil;
import java.util.HashMap;
import java.util.Map;

public class OauthWeixin extends Oauth {
    private static final String AUTH_URL = "https://open.weixin.qq.com/connect/qrconnect";
    private static final String TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    private static OauthWeixin oauthWeixin = new OauthWeixin();

    private OauthWeixin() {
    }

    public static OauthWeixin me() {
        return oauthWeixin;
    }

    public String getAuthorizeUrl(String state) {
        Map<String, String> params = new HashMap();
        params.put("appid", this.getClientId());
        params.put("response_type", "code");
        params.put("redirect_uri", this.getRedirectUri());
        params.put("scope", "snsapi_login");
        if (StringUtil.isBlank(state)) {
            params.put("state", "wx#wechat_redirect");
        } else {
            params.put("state", state.concat("#wechat_redirect"));
        }

        return HttpUtilExt.initParams("https://open.weixin.qq.com/connect/qrconnect", params);
    }

    public String getTokenByCode(String code) {
        Map<String, String> params = new HashMap();
        params.put("code", code);
        params.put("appid", this.getClientId());
        params.put("secret", this.getClientSecret());
        params.put("grant_type", "authorization_code");
        return super.doPost("https://api.weixin.qq.com/sns/oauth2/access_token", params);
    }

    public String getUserInfo(String accessToken, String openId) {
        Map<String, String> params = new HashMap();
        params.put("access_token", accessToken);
        params.put("openid", openId);
        return super.doPost("https://api.weixin.qq.com/sns/userinfo", params);
    }

    public Oauth getSelf() {
        return this;
    }

    public static void main(String[] args) {
        String ret = me().getUserInfo("WjLad5v1JpG6SP7NB8Ds9HQtCWjJDlBCOhoppSKo0OldT7S8beDP0LN1JkbAWYwlUr9BzavmJlm5ktDrMW2T0XNGNQxU16fEaWMbHpRS99o", "o5Shcw1WlfPnBYY06OWqUL7LnqjQ");
        System.out.println(ret);
    }
}
