//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.oauthLogin.util;

import com.alibaba.fastjson.JSONObject;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenUtil {
    private static final String STR_S = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final Pattern ACCESS_TOKEN = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$");
    private static final Pattern ACCESS_TOKEN_REFRESH = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)$");
    private static final Pattern OPEN_ID = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"");
    private static final Random RANDOM = new Random();

    public TokenUtil() {
    }

    public static String getAccessToken(String string) {
        String accessToken = "";

        try {
            JSONObject json = JSONObject.parseObject(string);
            if (null != json) {
                accessToken = json.getString("access_token");
            }
        } catch (Exception var5) {
            Matcher m = ACCESS_TOKEN.matcher(string);
            if (m.find()) {
                accessToken = m.group(1);
            } else {
                Matcher m2 = ACCESS_TOKEN_REFRESH.matcher(string);
                if (m2.find()) {
                    accessToken = m2.group(1);
                }
            }
        }

        return accessToken;
    }

    public static String getOpenId(String string) {
        String openid = null;
        Matcher m = OPEN_ID.matcher(string);
        if (m.find()) {
            openid = m.group(1);
        }

        return openid;
    }

    public static String randomState() {
        int count = 24;
        char[] buffer = new char[count];

        for(int i = 0; i < count; ++i) {
            buffer[i] = "abcdefghijklmnopqrstuvwxyz0123456789".charAt(RANDOM.nextInt("abcdefghijklmnopqrstuvwxyz0123456789".length()));
        }

        return new String(buffer);
    }
}
