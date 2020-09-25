package com.base;

import com.base.util.PropertySupport;

import java.math.BigDecimal;

public class CoreConstants extends PropertySupport {
    public static String PAGE_LIMIT_PAGESIZE = getProperty("page.limit.pageSize");
//    public static Boolean TASK_ENABLE = getBoolean("task.enable", false);
    public static String PAY_PATH = getProperty("pay.path", "pay/");
    public static String SENDSMS_ACCOUNT = getProperty("sms.account", "");
    public static String SENDSMS_PWD = getProperty("sms.pwd", "");
    public static String SERVER_URL = getProperty("server.url", "");
//    public static Boolean SESSION_ENABLE = getBoolean("session.enable", false);
    public static String SESSION_METHOD = getProperty("session.method");
    public static final String SIGN = "sign";
    public static final String TIMESTAMP = "timestamp";
    public static final String NONCE = "nonce";
    public static final String WEB_APPID = "WEB";
    public static String PROJECT_NAME = getProperty("project.name", "");
//    public static boolean DEBUG = getBoolean("api.debug", true);
//    public static boolean AJAX_DEBUG = getBoolean("api.ajax.debug", true);
    public static String ENCRYPT = getProperty("api.encrypt", "DES");
    public static String ENCRYPT_PRIVATE_KEY = getProperty("api.encrypt.privateKey", "");
    public static String ENCRYPT_PUBLIC_KEY = getProperty("api.encrypt.publicKey", "");
    public static String API_PATH = getProperty("api.path", "/api/");
    public static String LOGBACK_CONFIG = getProperty("logback.config", "classpath:logback.xml");
    public static final BigDecimal HUNDRED = new BigDecimal(100);
    public static String DEFAULT_OUT_CLASSES = getProperty("project.class.output", "/target/classes/");
    public static String PROJECT_PATH = getProperty("project.path");
    public static final String BASE_SCAN_PACKAGE = "com";
    public static final String CONFIG_NAME = "project.config";
    public static String CONFIG_LOCATION = getProperty("project.config");
//    public static String PROFILE = getProfileActive();
//    public static int TASK_POOL_NUM = getInt("task.pool", 30);
//    public static Long SHIRO_SESSION_TIMOUT = getLong("shiro.session.time", 1800L);
//    public static final String AUTHENTICATION_CACHE_NAME = "authenticationCache";
    public static final String AUTHORIZATION_CACHE_NAME = "authorizationCache";
    public static final String ACTIVE_SESSION_CACHE_NAME = "activeSessionCache";

    protected CoreConstants() {
    }
}
