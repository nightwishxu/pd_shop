package com.base.api;

import com.base.util.Md5;

import java.util.*;
import java.util.Map.Entry;

public class SignUtil {
    public SignUtil() {
    }

    public static String createSign(Map<String, String> param, String key) {
        SortedMap<String, String> params = new TreeMap(param);
        return createSign((SortedMap)params, key);
    }

    public static String createSign(SortedMap<String, String> params, String key) {
        StringBuilder sb = new StringBuilder();
        Set es = params.entrySet();
        Iterator it = es.iterator();

        while(it.hasNext()) {
            Entry entry = (Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }

        if (key != null) {
            sb.append("key=").append(key);
        }

        return Md5.mD5(sb.toString()).toUpperCase();
    }
}
