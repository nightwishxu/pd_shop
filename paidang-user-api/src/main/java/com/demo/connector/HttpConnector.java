package com.demo.connector;

import com.base.util.CoreConstants;
import com.demo.constant.MIMEType;
import com.demo.constant.Request;
import com.demo.constant.SystemConst;
import com.demo.util.CommonUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;


public class HttpConnector {
    public static String JKS_PATH = CoreConstants.getProperty("jks.path");;
    public static String JKS_PWD = "paidang";
    public static String ALIAS = "anxinsign";

    // public static String JKS_PATH = "./jks/clientceshi.jks";
    // public static String JKS_PWD = "cfca1234";
    // public static String ALIAS = "安心签业务监控nagios用@11111111 test oca1 (cfca test oca1)";

    // public static String JKS_PATH = "./jks/client_shengchan.jks";
    // public static String JKS_PWD = "cfca1234";
    // public static String ALIAS = "051@安心签测试员@z123@2 (cfca acs oca31)";

    // public static String JKS_PATH = "./jks/平台用户_安心签贺伟版_通信证书.jks";
    // public static String JKS_PWD = "cfca1234";
    // public static String ALIAS = "hewei@安心签贺伟版@3123456789@1 (cfca test oca1)";

    public String url = "https://210.74.42.33:9443/FEP/";
    // public String url = "https://192.168.123.177:8473/FEP/";
    // public String url = "https://192.168.113.121:8443/FEP/";
    // public String url = "https://210.74.42.33:9443/FEP/";
    // public String url = "https://cs.anxinsign.com/FEP/";
    // public String url = "https://210.74.40.8:443/FEP/";
    // public static String url = "https://192.168.113.74:8245/FEP/";//贺伟
    public int connectTimeout = 3000;
    public int readTimeout = 10000;
    public String channel = "Test";
    public boolean isSSL = true;
    public String keyStorePath = JKS_PATH;
    public String keyStorePassword = JKS_PWD;
    public String trustStorePath = JKS_PATH;
    public String trustStorePassword = JKS_PWD;

    private HttpClient httpClient;

    public void init() {
        httpClient = new HttpClient();
        httpClient.config.connectTimeout = connectTimeout;
        httpClient.config.readTimeout = readTimeout;
        httpClient.httpConfig.userAgent = "TrustSign FEP";
        httpClient.httpConfig.contentType = MIMEType.FORM;
        httpClient.httpConfig.accept = MIMEType.JSON;
        try {
            if (isSSL) {
                httpClient.initSSL(keyStorePath, keyStorePassword.toCharArray(), trustStorePath, trustStorePassword.toCharArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!url.endsWith("/")) {
            url += "/";
        }
    }

    public String post(String uri, String data, String signature) {
        return deal(uri, "POST", prepare(data, signature, null));
    }

    public String post(String uri, String data, String signature, Map<String, String> map) {
        return deal(uri, "POST", prepare(data, signature, map));
    }

    public String post(String uri, String data, String signature, File file) {
        return deal(uri, "POST", data, file, signature);
    }

    public byte[] getFile(String uri) {
        HttpURLConnection connection = null;
        try {
            connection = httpClient.connect(url + uri, "GET");
            int responseCode = httpClient.send(connection, null);
            System.out.println("responseCode:" + responseCode);
            if (responseCode != 200) {
                System.out.println(CommonUtil.getString(httpClient.receive(connection)));
                return null;
            }
            return httpClient.receive(connection);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            httpClient.disconnect(connection);
        }
    }

    private String prepare(String data, String signature, Map<String, String> map) {
        try {
            StringBuilder request = new StringBuilder();
            request.append(Request.CHANNEL).append("=").append(URLEncoder.encode(channel, SystemConst.DEFAULT_CHARSET));
            if (CommonUtil.isNotEmpty(data)) {
                request.append("&").append(Request.DATA).append("=").append(URLEncoder.encode(data, SystemConst.DEFAULT_CHARSET));
            }
            if (CommonUtil.isNotEmpty(signature)) {
                request.append("&").append(Request.SIGNATURE).append("=").append(URLEncoder.encode(signature, SystemConst.DEFAULT_CHARSET));
            }
            if (CommonUtil.isNotEmpty(map)) {
                for (Entry<String, String> pair : map.entrySet()) {
                    request.append("&").append(pair.getKey()).append("=")
                            .append(pair.getValue() == null ? "" : URLEncoder.encode(pair.getValue(), SystemConst.DEFAULT_CHARSET));
                }
            }
            return request.toString();
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private String deal(String uri, String method, String request) {
        HttpURLConnection connection = null;
        try {
            connection = httpClient.connect(url + uri, method);
            System.out.println(url + uri);
            System.out.println(method);
            System.out.println(request);
            int responseCode = httpClient.send(connection, request == null ? null : CommonUtil.getBytes(request));
            System.out.println("responseCode:" + responseCode);
            System.out.println(connection.getHeaderFields());
            return CommonUtil.getString(httpClient.receive(connection));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            httpClient.disconnect(connection);
        }
    }

    private String deal(String uri, String method, String request, File file, String signature) {
        HttpURLConnection connection = null;
        try {
            connection = httpClient.connect(url + uri, method);
            System.out.println(url + uri);
            System.out.println(method);
            System.out.println(request);
            int responseCode = httpClient.send(connection, request == null ? null : request, file, signature);
            System.out.println("responseCode:" + responseCode);
            return CommonUtil.getString(httpClient.receive(connection));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            httpClient.disconnect(connection);
        }
    }

}
