//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.oauthLogin.api;

import com.base.CoreConstants;
import com.base.oauthLogin.util.HttpUtilExt;
import com.base.util.HttpUtil;
import com.base.util.StringUtil;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public abstract class Oauth {
    protected final Logger logger = Logger.getLogger(Oauth.class);
    private String clientId;
    private String clientSecret;
    private String redirectUri;

    public Oauth() {
        String name = this.getSelf().getClass().getSimpleName();
        name = StringUtil.firstCharToLowerCase(name);
        this.clientId = CoreConstants.getProperty(name + ".openid");
        this.clientSecret = CoreConstants.getProperty(name + ".openkey");
        this.redirectUri = CoreConstants.getProperty(name + ".redirect");
    }

    public abstract Oauth getSelf();

    protected String getAuthorizeUrl(String authorize, String state) {
        Map<String, String> params = new HashMap();
        params.put("response_type", "code");
        params.put("client_id", this.getClientId());
        params.put("redirect_uri", this.getRedirectUri());
        if (StringUtil.isNotBlank(state)) {
            params.put("state", state);
        }

        return HttpUtilExt.initParams(authorize, params);
    }

    protected String doPost(String url, Map<String, String> params) {
        return HttpUtil.post(url, HttpUtilExt.map2Url(params));
    }

    protected String doGet(String url, Map<String, String> params) {
        return HttpUtil.get(url, params);
    }

    protected String doGetWithHeaders(String url, Map<String, String> headers) {
        return HttpUtil.get(url, null, headers);
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }
}
