package com.base.util.http;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.base.expection.HttpException;
import com.base.util.LogKit;
import com.base.util.StringUtil;
import com.base.util.URLUtil;
import com.base.util.Validator;
import com.base.util.http.ssl.SSLSocketFactoryBuilder;
import com.base.util.http.ssl.TrustAnyHostnameVerifier;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpConnection {
    private URL url;
    private Method method;
    private Proxy proxy;
    private HttpURLConnection conn;

    public static HttpConnection create(String urlStr, Method method) {
        return new HttpConnection(urlStr, method);
    }

    public static HttpConnection create(String urlStr, Method method, int timeout) {
        return new HttpConnection(urlStr, method, timeout);
    }

    public static HttpConnection create(String urlStr, Method method, HostnameVerifier hostnameVerifier, SSLSocketFactory ssf) {
        return new HttpConnection(urlStr, method, hostnameVerifier, ssf, 0, (Proxy)null);
    }

    public static HttpConnection create(String urlStr, Method method, HostnameVerifier hostnameVerifier, SSLSocketFactory ssf, int timeout, Proxy proxy) {
        return new HttpConnection(urlStr, method, hostnameVerifier, ssf, timeout, proxy);
    }

    public HttpConnection(String urlStr, Method method) {
        this(urlStr, method, (HostnameVerifier)null, (SSLSocketFactory)null, 0, (Proxy)null);
    }

    public HttpConnection(String urlStr, Method method, int timeout) {
        this(urlStr, method, (HostnameVerifier)null, (SSLSocketFactory)null, timeout, (Proxy)null);
    }

    public HttpConnection(String urlStr, Method method, HostnameVerifier hostnameVerifier, SSLSocketFactory ssf, int timeout, Proxy proxy) {
        if (StringUtil.isBlank(urlStr)) {
            throw new HttpException("Url is blank !");
        } else if (!Validator.isUrl(urlStr)) {
            throw new HttpException("{} is not a url !", new Object[]{urlStr});
        } else {
            this.url = URLUtil.url(urlStr);
            this.method = ObjectUtil.isNull(method) ? Method.GET : method;
            this.proxy = proxy;

            try {
                this.conn = (HttpURLConnection)(HttpUtil.isHttps(urlStr) ? this.openHttps(hostnameVerifier, ssf) : this.openHttp());
            } catch (Exception var8) {
                throw new HttpException(var8.getMessage(), var8);
            }

            if (timeout > 0) {
                this.setConnectionAndReadTimeout(timeout);
            }

            this.initConn();
        }
    }

    public HttpConnection initConn() {
        try {
            this.conn.setRequestMethod(this.method.toString());
        } catch (ProtocolException var2) {
            throw new HttpException(var2.getMessage(), var2);
        }

        this.conn.setDoInput(true);
        if (Method.POST.equals(this.method) || Method.PUT.equals(this.method) || Method.PATCH.equals(this.method) || Method.DELETE.equals(this.method)) {
            this.conn.setDoOutput(true);
            this.conn.setUseCaches(false);
        }

        this.header(Header.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8", true);
        this.header(Header.ACCEPT_ENCODING, "gzip", true);
        this.header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded", true);
        this.header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:36.0) Gecko/20100101 Firefox/36.0 Hutool", true);
        this.setCookie(CookiePool.get(this.url.getHost()));
        return this;
    }

    public Method getMethod() {
        return this.method;
    }

    public HttpConnection setMethod(Method method) {
        this.method = method;
        return this;
    }

    public URL getUrl() {
        return this.url;
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public HttpURLConnection getHttpURLConnection() {
        return this.conn;
    }

    public HttpConnection header(String header, String value, boolean isOverride) {
        if (null != this.conn) {
            if (isOverride) {
                this.conn.setRequestProperty(header, value);
            } else {
                this.conn.addRequestProperty(header, value);
            }
        }

        return this;
    }

    public HttpConnection header(Header header, String value, boolean isOverride) {
        return this.header(header.toString(), value, isOverride);
    }

    public HttpConnection header(Map<String, List<String>> headers, boolean isOverride) {
        if (CollectionUtil.isNotEmpty(headers)) {
            Iterator var4 = headers.entrySet().iterator();

            while(var4.hasNext()) {
                Entry<String, List<String>> entry = (Entry)var4.next();
                String name = (String)entry.getKey();
                Iterator var6 = ((List)entry.getValue()).iterator();

                while(var6.hasNext()) {
                    String value = (String)var6.next();
                    this.header(name, StringUtil.nullToEmpty(value), isOverride);
                }
            }
        }

        return this;
    }

    public String header(String name) {
        return this.conn.getHeaderField(name);
    }

    public String header(Header name) {
        return this.header(name.toString());
    }

    public Map<String, List<String>> headers() {
        return this.conn.getHeaderFields();
    }

    public HttpConnection disableCache() {
        this.conn.setUseCaches(false);
        return this;
    }

    public HttpConnection setConnectTimeout(int timeout) {
        if (timeout > 0 && null != this.conn) {
            this.conn.setConnectTimeout(timeout);
        }

        return this;
    }

    public HttpConnection setReadTimeout(int timeout) {
        if (timeout > 0 && null != this.conn) {
            this.conn.setReadTimeout(timeout);
        }

        return this;
    }

    public HttpConnection setConnectionAndReadTimeout(int timeout) {
        this.setConnectTimeout(timeout);
        this.setReadTimeout(timeout);
        return this;
    }

    public HttpConnection setCookie(String cookie) {
        if (cookie != null) {
            this.header(Header.COOKIE, cookie, true);
        }

        return this;
    }

    public HttpConnection setChunkedStreamingMode(int blockSize) {
        this.conn.setChunkedStreamingMode(blockSize);
        return this;
    }

    public HttpConnection setInstanceFollowRedirects(boolean isInstanceFollowRedirects) {
        this.conn.setInstanceFollowRedirects(isInstanceFollowRedirects);
        return this;
    }

    public HttpConnection connect() throws IOException {
        if (null != this.conn) {
            this.conn.connect();
        }

        return this;
    }

    public HttpConnection disconnect() {
        if (null != this.conn) {
            this.conn.disconnect();
        }

        return this;
    }

    public InputStream getInputStream() throws IOException {
        this.storeCookie();
        return null != this.conn ? this.conn.getInputStream() : null;
    }

    public InputStream getErrorStream() throws IOException {
        this.storeCookie();
        return null != this.conn ? this.conn.getErrorStream() : null;
    }

    public OutputStream getOutputStream() throws IOException {
        if (null == this.conn) {
            throw new IOException("HttpURLConnection has not been initialized.");
        } else {
            return this.conn.getOutputStream();
        }
    }

    public int responseCode() throws IOException {
        return null != this.conn ? this.conn.getResponseCode() : 0;
    }

    public String getCharsetName() {
        return HttpUtil.getCharset(this.conn);
    }

    public Charset getCharset() {
        Charset charset = null;
        String charsetName = this.getCharsetName();
        if (StringUtil.isNotBlank(charsetName)) {
            try {
                charset = Charset.forName(charsetName);
            } catch (UnsupportedCharsetException var4) {
                ;
            }
        }

        return charset;
    }

    public String toString() {
        StringBuilder sb = StringUtil.builder();
        sb.append("Request URL: ").append(this.url).append("\r\n");
        sb.append("Request Method: ").append(this.method).append("\r\n");
        return sb.toString();
    }

    private HttpURLConnection openHttp() throws IOException {
        return (HttpURLConnection)this.openConnection();
    }

    private HttpsURLConnection openHttps(HostnameVerifier hostnameVerifier, SSLSocketFactory ssf) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection)this.openConnection();
        httpsURLConnection.setHostnameVerifier((HostnameVerifier)(null != hostnameVerifier ? hostnameVerifier : new TrustAnyHostnameVerifier()));
        httpsURLConnection.setSSLSocketFactory(null != ssf ? ssf : SSLSocketFactoryBuilder.create().build());
        return httpsURLConnection;
    }

    private URLConnection openConnection() throws IOException {
        return null == this.proxy ? this.url.openConnection() : this.url.openConnection(this.proxy);
    }

    private void storeCookie() {
        String setCookie = this.header(Header.SET_COOKIE);
        if (!StringUtil.isBlank(setCookie)) {
            LogKit.debug("Set cookie: [{}]", new Object[]{setCookie});
            CookiePool.put(this.url.getHost(), setCookie);
        }

    }
}
