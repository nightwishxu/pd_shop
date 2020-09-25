package com.base.util.http;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CookiePool {
    private static Map<String, String> cookies = new ConcurrentHashMap();

    public CookiePool() {
    }

    public static String get(String host) {
        return (String)cookies.get(host);
    }

    public static void put(String host, String cookie) {
        cookies.put(host, cookie);
    }

    public static void clear() {
        cookies.clear();
    }
}
