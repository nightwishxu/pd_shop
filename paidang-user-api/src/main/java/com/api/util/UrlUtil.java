package com.api.util;


import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;

public class UrlUtil {
    private static Logger log = Logger.getLogger(UrlUtil.class);
    //连接超时时间，默认10秒
    private static final int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private static final int connectTimeout = 30000;

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
//    public static String sendGet(String url, String param) {
//        String result = "";
//        BufferedReader in = null;
//        try {
//            String urlNameString = url + "?" + param;
//            URL realUrl = new URL(urlNameString);
//            // 打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection();
//            // 设置通用的请求属性
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 建立实际的连接
//            connection.connect();
//            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
//            // 定义 BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(
//                    connection.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.out.println("发送GET请求出现异常！" + e);
//            e.printStackTrace();
//        }
//        // 使用finally块来关闭输入流
//        finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//        return result;
//    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param paramMap 请求参数
     * @return 所代表远程资源的响应结果
     */
//    public static String sendPost(String url, Map<String, ?> paramMap) {
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//
//        String param = "";
//        Iterator<String> it = paramMap.keySet().iterator();
//
//        while(it.hasNext()) {
//            String key = it.next();
//            param += key + "=" + paramMap.get(key) + "&";
//        }
//
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("Accept-Charset", "utf-8");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//            // 发送请求参数
//            out.print(param);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.out.println("发送 POST 请求出现异常！"+e);
//            log.error(e.getMessage(), e);
//        }
//        //使用finally块来关闭输出流、输入流
//        finally{
//            try{
//                if(out!=null){
//                    out.close();
//                }
//                if(in!=null){
//                    in.close();
//                }
//            }
//            catch(IOException ex){
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }


    /**
     * post请求
     * @throws IOException
     * @throws ClientProtocolException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     */
    public static String doPost(Map<String,String> map)  {
        String appId = map.get("appid");
        String appSecret = map.get("secret");
        String code = map.get("js_code");
        String grantType = map.get("grant_type");

        String strURL = "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+appSecret+"&js_code="+code+"&grant_type=" + grantType;

        String result = "";
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            //开始发送请求
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.flush();
            out.close();
            int ResponseCode = connection.getResponseCode();
            InputStream is = null;
            if (ResponseCode == 200) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            // 第三步：读取响应
            ///在这里说一下，获取微信返回的方式有两种，随便挑一种即可
            //第一种：获取返回信息方式【比较骚气，用byte解析后获取】
            int length = (int) connection.getContentLength();// 获取长度
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }

                result = new String(data, "UTF-8"); // utf-8编码
//                Map<String, Object> resultMap = com.alibaba.fastjson.JSONObject.parseObject(result);
                //【这里是你获取到openId的逻辑判断】

            }
            //第二种：获取返回信息方式【更简单一点吧，但是第一种更骚气点】
                /*String contentEncoding = connection.getContentEncoding();
                String body= IOUtils.toString(is, contentEncoding);
                System.out.println(body);
                org.json.JSONObject jsonObject=new org.json.JSONObject(body);
                String openId=jsonObject.getString("openid");
                //【这里是你获取到openId的逻辑判断】
                //【plifeUser是我当时的用户实体类，保存openId，复制到你的项目可以删掉】
           */
        } catch (IOException e) {
            //  LOG.error("Exception occur when send http post request!", e);
        }
        return  result;
    }

    /**
     * 自定义证书管理器，信任所有证书
     * @author pc
     *
     */
    public static class MyX509TrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(
                java.security.cert.X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // TODO Auto-generated method stub

        }
        @Override
        public void checkServerTrusted(
                java.security.cert.X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // TODO Auto-generated method stub

        }
        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
