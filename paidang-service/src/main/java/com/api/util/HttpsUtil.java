package com.api.util;


import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @ClassName: HttpsUtil
 * @Description: Http请求工具类
 * @Author: chengzi
 * @Date: 2020/7/2 0002 13:02
 * @Version: v1.0 文件初始创建
 */
public final class HttpsUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static String outputCharset = "UTF-8";
    private static String inputCharset = "UTF-8";
    private static String contentType = "application/x-www-form-urlencoded";
    private static String multipartFromData = "multipart/form-data";
    private HttpsUtil() { }

    //发送post请求
    public static String post(String url, String requestString, String type) throws Exception {

        InputStream is = null;
        OutputStream out = null;
        int responseCode = 0;
        HttpsURLConnection conn = null;
        try {
            if (url.startsWith("https")) {
                // 初始化Https信任所有证书
                conn = initHttps(url, "POST", null);
                conn.connect();
                out = conn.getOutputStream();
                out.write(requestString.getBytes(outputCharset));
                out.flush();
                out.close();
                responseCode = conn.getResponseCode();
                is = conn.getInputStream();
            } else {
                URL uri = new URL(url);
                HttpURLConnection con = (HttpURLConnection) uri.openConnection();

                // 连接超时
                con.setConnectTimeout(10000);
                // 读取超时
                con.setReadTimeout(10000);
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", contentType);
                con.setUseCaches(false);
                con.setDoOutput(true);
                con.setDoInput(true);
                con.connect();
                out = con.getOutputStream();
                out.write(requestString.getBytes(outputCharset));
                out.flush();
                out.close();
                responseCode = con.getResponseCode();
                is = con.getInputStream();
            }
            if (200 == responseCode) {
                BufferedReader read = new BufferedReader(new InputStreamReader(is, DEFAULT_CHARSET));
                String valueString = null;
                StringBuffer bufferRes = new StringBuffer();
                while ((valueString = read.readLine()) != null) {
                    bufferRes.append(valueString);
                }
                is.close();
                if (conn != null) {
                    conn.disconnect();// 关闭连接
                }
                return bufferRes.toString();
            } else {
                throw new Exception("请求出错，http状态码：" + responseCode);
            }

        }catch (Exception e){
            throw e;
        } finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                }
                if (null != is) {
                    is.close();
                }
                if (null != out) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //初始化https
    private static HttpsURLConnection initHttps(String url, String method, Map<String, String> headers) throws Exception {
        TrustManager[] tm = { new miTM() };
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        URL _url = new URL(url);
        HttpsURLConnection http = (HttpsURLConnection) _url.openConnection();
        // 设置域名校验
        http.setHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                return true;
            }

        });
        // 连接超时
        http.setConnectTimeout(60000);
        // 读取超时
        http.setReadTimeout(60000);
        http.setUseCaches(false);
        http.setRequestMethod(method);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        if (null != headers && !headers.isEmpty()) {
            for (Entry<String, String> entry : headers.entrySet()) {
                http.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        http.setSSLSocketFactory(ssf);
        http.setDoOutput(true);
        http.setDoInput(true);
        return http;
    }
    //静态内部类
    static class miTM implements TrustManager,X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }
        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }
        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
    }
}
