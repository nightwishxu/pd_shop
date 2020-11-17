package com.base.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ：xww
 * @date ：Created in 2020/8/9 0:14
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class CoreConstants {

    public static String getProperty(String name){
        return PropertySupport.getProperty(name);
    }

    public static String getProperty(String name,String def){
        return null;
    }


    public  static String JPUSH_APPKEY;

    public static String JPUSH_SECRET;

    public static boolean JPUSH_PRODUCT;

    public static String JPUSH_ORG_APPKEY;

    public static String JPUSH_ORG_SECRET;

    public static boolean JPUSH_ORG_PRODUCT;

    public static  String SERVER_URL;

    public static  String BOS_URL;

    public static String FILE_PATH;

    public static String FILE_MAXSIZE;


    @Value("${server.url}")
    public void setServerUrl(String serverUrl) {
        SERVER_URL = serverUrl;
    }

    @Value("${file.path}")
    public  void setFilePath(String filePath) {
        FILE_PATH = filePath;
    }

    @Value("${file.maxSize}")
    public  void setFileMaxsize(String fileMaxsize) {
        FILE_MAXSIZE = fileMaxsize;
    }

    @Value("${jpush.appkey}")
    public void setJPUSH_APPKEY(String jpushAppkey) {
        JPUSH_APPKEY = jpushAppkey;
    }

    @Value("${jpush.secret}")
    public void setJPUSH_APPSECRET(String jpushSeret) {
        JPUSH_SECRET = jpushSeret;
    }

    @Value("${jpush.product}")
    public void setJPUSH_PRODUCT(boolean jpushProduct) {
        JPUSH_PRODUCT = jpushProduct;
    }

    @Value("${jpush.org.appkey}")
    public void setJPUSH_ORG_APPKEY(String jpushOrgAppkey) {
       JPUSH_ORG_APPKEY = jpushOrgAppkey;
    }

    @Value("${jpush.org.secret}")
    public void setJPUSH_ORG_APPSECRET(String jpushOrgSeret) {
        JPUSH_ORG_SECRET = jpushOrgSeret;
    }

    @Value("${jpush.org.product}")
    public void setJPUSH_ORG_PRODUCT(boolean jpushOrgAppkey) {
        JPUSH_ORG_PRODUCT = jpushOrgAppkey;
    }


    @Value("${bos.url}")
    public void setBosUrl(String bosUrl) {
        BOS_URL = bosUrl;
    }
}
