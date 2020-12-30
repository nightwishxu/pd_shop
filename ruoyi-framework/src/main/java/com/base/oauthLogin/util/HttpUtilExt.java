//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.oauthLogin.util;

import com.base.util.StringUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtilExt {
    public HttpUtilExt() {
    }

    public static String initParams(String url, Map<String, String> params) {
        if (null != params && !params.isEmpty()) {
            StringBuilder sb = new StringBuilder(url);
            if (url.indexOf("?") == -1) {
                sb.append("?");
            }

            sb.append(map2Url(params));
            return sb.toString();
        } else {
            return url;
        }
    }

    public static String map2Url(Map<String, String> paramToMap) {
        if (null != paramToMap && !paramToMap.isEmpty()) {
            StringBuffer url = new StringBuffer();
            boolean isfist = true;

            String value;
            for(Iterator var3 = paramToMap.entrySet().iterator(); var3.hasNext(); url.append(value)) {
                Entry<String, String> entry = (Entry)var3.next();
                if (isfist) {
                    isfist = false;
                } else {
                    url.append("&");
                }

                url.append((String)entry.getKey()).append("=");
                value = (String)entry.getValue();
                if (StringUtil.isNotBlank(value)) {
                    try {
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (UnsupportedEncodingException var7) {
                        throw new RuntimeException(var7);
                    }
                }
            }

            return url.toString();
        } else {
            return null;
        }
    }
}
