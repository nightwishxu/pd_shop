package com.base.util.http;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReUtil;
import com.base.exception.HttpException;
import com.base.util.*;
import com.base.util.http.ssl.SSLSocketFactoryBuilder;
import com.base.util.http.ssl.TrustAnyHostnameVerifier;


import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class HttpUtil {
    public static final Pattern CHARSET_PATTERN = Pattern.compile("<meta.*?charset=(.*?)\"");

    private HttpUtil() {
    }

    public static String encode(String content, Charset charset) {
        return encode(content, charset.name());
    }

    public static String encode(String content, String charsetStr) throws HttpException {
        if (StringUtil.isBlank(content)) {
            return content;
        } else {
            String encodeContent = null;

            try {
                encodeContent = URLEncoder.encode(content, charsetStr);
                return encodeContent;
            } catch (UnsupportedEncodingException var4) {
                throw new HttpException(StringUtil.format("Unsupported encoding: [{}]", new Object[]{charsetStr}), var4);
            }
        }
    }

    public static String decode(String content, Charset charset) {
        return decode(content, charset.name());
    }

    public static String decode(String content, String charsetStr) {
        if (StringUtil.isBlank(content)) {
            return content;
        } else {
            String encodeContnt = null;

            try {
                encodeContnt = URLDecoder.decode(content, charsetStr);
                return encodeContnt;
            } catch (UnsupportedEncodingException var4) {
                throw new HttpException(StringUtil.format("Unsupported encoding: [{}]", new Object[]{charsetStr}), var4);
            }
        }
    }

    public static String getClientIP(HttpServletRequest request, String... otherHeaderNames) {
        String[] headers = new String[]{"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        if (ArrayUtil.isNotEmpty(otherHeaderNames)) {
            headers = (String[])ArrayUtil.addAll(new String[][]{headers, otherHeaderNames});
        }

        String[] var4 = headers;
        int var5 = headers.length;

        String ip;
        for(int var6 = 0; var6 < var5; ++var6) {
            String header = var4[var6];
            ip = request.getHeader(header);
            if (!isUnknow(ip)) {
                return getMultistageReverseProxyIp(ip);
            }
        }

        ip = request.getRemoteAddr();
        return getMultistageReverseProxyIp(ip);
    }

    public static boolean isHttps(String url) {
        return url.toLowerCase().startsWith("https");
    }

    public static HttpRequest createRequest(Method method, String url) {
        return (new HttpRequest(url)).method(method);
    }

    public static String get(String urlString, String customCharset) {
        return ((HttpRequest)HttpRequest.get(urlString).charset(customCharset)).execute().body();
    }

    public static String get(String urlString) {
        return HttpRequest.get(urlString).execute().body();
    }

    public static String get(String urlString, Map<String, Object> paramMap) {
        return HttpRequest.get(urlString).form(paramMap).execute().body();
    }

    public static String post(String urlString, Map<String, Object> paramMap) {
        return HttpRequest.post(urlString).form(paramMap).execute().body();
    }

    public static String post(String urlString, Map<String, Object> paramMap, boolean keepAlive) {
        return HttpRequest.post(urlString).keepAlive(keepAlive).form(paramMap).execute().body();
    }

    public static String post(String urlString, String params) {
        return HttpRequest.post(urlString).body(params).execute().body();
    }

    public static String downloadString(String url, String customCharsetName) {
        return downloadString(url, CharsetUtil.charset(customCharsetName), (StreamProgress)null);
    }

    public static String downloadString(String url, Charset customCharset) {
        return downloadString(url, customCharset, (StreamProgress)null);
    }

    public static String downloadString(String url, Charset customCharset, StreamProgress streamPress) {
        if (StringUtil.isBlank(url)) {
            throw new NullPointerException("[url] is null!");
        } else {
            FastByteArrayOutputStream out = new FastByteArrayOutputStream();
            download(url, out, true, streamPress);
            return null == customCharset ? out.toString() : out.toString(customCharset);
        }
    }

    public static long downloadFile(String url, String dest) {
        return downloadFile(url, FileUtil.file(dest));
    }

    public static long downloadFile(String url, File destFile) {
        return downloadFile(url, destFile, (StreamProgress)null);
    }

    public static long downloadFile(String url, File destFile, StreamProgress streamProgress) {
        if (StringUtil.isBlank(url)) {
            throw new NullPointerException("[url] is null!");
        } else if (null == destFile) {
            throw new NullPointerException("[destFile] is null!");
        } else {
            String fileName;
            if (destFile.isDirectory()) {
                fileName = StringUtil.subSuf(url, url.lastIndexOf(47) + 1);
                LogKit.debug("FileName: {}", new Object[]{fileName});
                if (StringUtil.isBlank(fileName)) {
                    fileName = encode(url, CharsetUtil.CHARSET_UTF_8);
                }

                destFile = FileUtil.file(destFile, fileName);
            }

            fileName = null;

            try {
                OutputStream out = FileUtil.getOutputStream(destFile);
                return download(url, out, true, streamProgress);
            } catch (IORuntimeException var5) {
                throw new HttpException(var5);
            }
        }
    }

    public static long download(String url, OutputStream out, boolean isCloseOut) {
        return download(url, out, isCloseOut, (StreamProgress)null);
    }

    public static long download(String url, OutputStream out, boolean isCloseOut, StreamProgress streamProgress) {
        if (StringUtil.isBlank(url)) {
            throw new NullPointerException("[url] is null!");
        } else if (null == out) {
            throw new NullPointerException("[out] is null!");
        } else {
            InputStream in = null;

            long var13;
            try {
                URLConnection conn = (new URL(url)).openConnection();
                if (isHttps(url)) {
                    HttpsURLConnection httpsConn = (HttpsURLConnection)conn;
                    httpsConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
                    httpsConn.setSSLSocketFactory(SSLSocketFactoryBuilder.create().build());
                }

                in = conn.getInputStream();
                var13 = IoUtil.copyByNIO(in, out, 1024, streamProgress);
            } catch (Exception var11) {
                throw new HttpException(var11);
            } finally {
                IoUtil.close(in);
                if (isCloseOut) {
                    IoUtil.close(out);
                }

            }

            return var13;
        }
    }

    public static String toParams(Map<String, ?> paramMap) {
        return toParams(paramMap, CharsetUtil.CHARSET_UTF_8);
    }

    public static String toParams(Map<String, Object> paramMap, String charsetName) {
        return toParams(paramMap, CharsetUtil.charset(charsetName));
    }

    public static String toParams(Map<String, ?> paramMap, Charset charset) {
        if (CollectionUtil.isEmpty(paramMap)) {
            return "";
        } else {
            if (null == charset) {
                charset = CharsetUtil.CHARSET_UTF_8;
            }

            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            Iterator var7 = paramMap.entrySet().iterator();

            while(var7.hasNext()) {
                Entry<String, ?> item = (Entry)var7.next();
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append("&");
                }

                String key = (String)item.getKey();
                Object value = item.getValue();
                if (value instanceof Iterable) {
                    value = CollectionUtil.join((Iterable)value, ",");
                } else if (value instanceof Iterator) {
                    value = CollectionUtil.join((Iterator)value, ",");
                }

                String valueStr = encode(Convert.toStr(value), charset);
                if (StringUtil.isNotEmpty(key)) {
                    sb.append(key).append("=");
                    if (StringUtil.isNotEmpty(valueStr)) {
                        sb.append(valueStr);
                    }
                }
            }

            return sb.toString();
        }
    }

    public static Map<String, List<String>> decodeParams(String paramsStr, String charset) {
        if (StringUtil.isBlank(paramsStr)) {
            return Collections.emptyMap();
        } else {
            int pathEndPos = paramsStr.indexOf(63);
            if (pathEndPos > 0) {
                paramsStr = StringUtil.subSuf(paramsStr, pathEndPos + 1);
            }

            Map<String, List<String>> params = new LinkedHashMap();
            int len = paramsStr.length();
            String name = null;
            int pos = 0;

            int i;
            for(i = 0; i < len; ++i) {
                char c = paramsStr.charAt(i);
                if (c == '=' && name == null) {
                    if (pos != i) {
                        name = paramsStr.substring(pos, i);
                    }

                    pos = i + 1;
                } else if (c == '&' || c == ';') {
                    if (name == null && pos != i) {
                        addParam(params, paramsStr.substring(pos, i), "", charset);
                    } else if (name != null) {
                        addParam(params, name, paramsStr.substring(pos, i), charset);
                        name = null;
                    }

                    pos = i + 1;
                }
            }

            if (pos != i) {
                if (name == null) {
                    addParam(params, paramsStr.substring(pos, i), "", charset);
                } else {
                    addParam(params, name, paramsStr.substring(pos, i), charset);
                }
            } else if (name != null) {
                addParam(params, name, "", charset);
            }

            return params;
        }
    }

    public static String urlWithForm(String url, Map<String, Object> form) {
        String queryString = toParams(form, "UTF-8");
        return urlWithForm(url, queryString);
    }

    public static String urlWithForm(String url, String queryString) {
        if (StringUtil.isNotBlank(queryString)) {
            if (url.contains("?")) {
                url = url + "&" + queryString;
            }

            url = url + (url.endsWith("?") ? queryString : "?" + queryString);
        }

        return url;
    }

    public static String getCharset(HttpURLConnection conn) {
        if (conn == null) {
            return null;
        } else {
            String charset = ReUtil.get(CHARSET_PATTERN, conn.getContentType(), 1);
            return charset;
        }
    }

    public static String getMultistageReverseProxyIp(String ip) {
        if (ip != null && ip.indexOf(",") > 0) {
            String[] ips = ip.trim().split(",");
            String[] var2 = ips;
            int var3 = ips.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String subIp = var2[var4];
                if (!isUnknow(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }

        return ip;
    }

    public static boolean isUnknow(String checkString) {
        return StringUtil.isBlank(checkString) || "unknown".equalsIgnoreCase(checkString);
    }

    public static String getString(InputStream in, Charset charset, boolean isGetCharsetFromContent) throws IOException {
        byte[] contentBytes = IoUtil.readBytes(in);
        return getString(contentBytes, charset, isGetCharsetFromContent);
    }

    public static String getString(byte[] contentBytes, Charset charset, boolean isGetCharsetFromContent) throws IOException {
        if (null == charset) {
            charset = CharsetUtil.CHARSET_UTF_8;
        }

        String content = new String(contentBytes, charset);
        if (isGetCharsetFromContent) {
            String charsetInContentStr = ReUtil.get(CHARSET_PATTERN, content, 1);
            if (StringUtil.isNotBlank(charsetInContentStr)) {
                Charset charsetInContent = null;

                try {
                    charsetInContent = Charset.forName(charsetInContentStr);
                } catch (UnsupportedCharsetException var7) {
                    ;
                }

                if (null != charsetInContent && !charset.equals(charsetInContent)) {
                    content = new String(contentBytes, charsetInContent);
                }
            }
        }

        return content;
    }

    public static String getMimeType(String filePath) {
        return URLConnection.getFileNameMap().getContentTypeFor(filePath);
    }

    private static void addParam(Map<String, List<String>> params, String name, String value, String charset) {
        List<String> values = (List)params.get(name);
        if (values == null) {
            values = new ArrayList(1);
            params.put(name, values);
        }

        ((List)values).add(decode(value, charset));
    }
}
