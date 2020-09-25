package com.base.api;

import com.base.util.SignUtil;

import java.util.Map;
import java.util.SortedMap;

public class SignAuth {
    public SignAuth() {
    }

    protected String createSign(Map<String, String> param, String key) {
        return SignUtil.createSign(param, key);
    }

    private String createSign(SortedMap<String, String> params, String key) {
        return SignUtil.createSign(params, key);
    }
}
