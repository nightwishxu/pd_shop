package com.base.util.http;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.base.exception.HttpException;
import com.base.util.Convert;
import com.base.util.IoUtil;
import com.base.util.LogKit;
import com.base.util.StringUtil;
import com.base.util.http.ssl.SSLSocketFactoryBuilder;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class HttpRequest extends HttpBase<HttpRequest> {
    private static final String BOUNDARY = "--------------------Hutool_" + RandomUtil.randomString(16);
    private static final byte[] BOUNDARY_END;
    private static final String CONTENT_DISPOSITION_TEMPLATE = "Content-Disposition: form-data; name=\"{}\"\r\n\r\n";
    private static final String CONTENT_DISPOSITION_FILE_TEMPLATE = "Content-Disposition: form-data; name=\"{}\"; filename=\"{}\"\r\n";
    private static final String CONTENT_TYPE_X_WWW_FORM_URLENCODED_PREFIX = "application/x-www-form-urlencoded;charset=";
    private static final String CONTENT_TYPE_MULTIPART_PREFIX = "multipart/form-data; boundary=";
    private static final String CONTENT_TYPE_FILE_TEMPLATE = "Content-Type: {}\r\n\r\n";
    private String url = "";
    private Method method;
    private int timeout;
    private Map<String, Object> form;
    private Map<String, File> fileForm;
    private String cookie;
    private HttpConnection httpConnection;
    private boolean isDisableCache;
    private Boolean isFollowRedirects;
    private int redirectCount;
    private Proxy proxy;
    private HostnameVerifier hostnameVerifier;
    private SSLSocketFactory ssf;

    public HttpRequest(String url) {
        this.method = Method.GET;
        this.timeout = -1;
        this.url = url;
    }

    public HttpRequest method(Method method) {
        this.method = method;
        return this;
    }

    public static HttpRequest post(String url) {
        return (new HttpRequest(url)).method(Method.POST);
    }

    public static HttpRequest get(String url) {
        return (new HttpRequest(url)).method(Method.GET);
    }

    public static HttpRequest head(String url) {
        return (new HttpRequest(url)).method(Method.HEAD);
    }

    public static HttpRequest options(String url) {
        return (new HttpRequest(url)).method(Method.OPTIONS);
    }

    public static HttpRequest put(String url) {
        return (new HttpRequest(url)).method(Method.PUT);
    }

    public static HttpRequest patch(String url) {
        return (new HttpRequest(url)).method(Method.PATCH);
    }

    public static HttpRequest delete(String url) {
        return (new HttpRequest(url)).method(Method.DELETE);
    }

    public static HttpRequest trace(String url) {
        return (new HttpRequest(url)).method(Method.TRACE);
    }

    public HttpRequest contentType(String contentType) {
        this.header(Header.CONTENT_TYPE, contentType);
        return this;
    }

    public HttpRequest keepAlive(boolean isKeepAlive) {
        this.header(Header.CONNECTION, isKeepAlive ? "Keep-Alive" : "Close");
        return this;
    }

    public boolean isKeepAlive() {
        String connection = this.header(Header.CONNECTION);
        if (connection == null) {
            return !this.httpVersion.equalsIgnoreCase("HTTP/1.0");
        } else {
            return !connection.equalsIgnoreCase("close");
        }
    }

    public String contentLength() {
        return this.header(Header.CONTENT_LENGTH);
    }

    public HttpRequest contentLength(int value) {
        this.header(Header.CONTENT_LENGTH, String.valueOf(value));
        return this;
    }

    public HttpRequest cookie(String cookie) {
        this.cookie = cookie;
        return this;
    }

    public HttpRequest disableCookie() {
        return this.cookie("");
    }

    public HttpRequest enableDefaultCookie() {
        return this.cookie((String)null);
    }

    public HttpRequest form(String name, Object value) {
        if (!StringUtil.isBlank(name) && !ObjectUtil.isNull(value)) {
            this.body = null;
            if (value instanceof File) {
                return this.form(name, (File)value);
            } else {
                if (this.form == null) {
                    this.form = new HashMap();
                }

                String strValue;
                if (value instanceof List) {
                    strValue = CollectionUtil.join((List)value, ",");
                } else if (ArrayUtil.isArray(value)) {
                    strValue = ArrayUtil.join((Object[])((Object[])value), ",");
                } else {
                    strValue = Convert.toStr(value, (String)null);
                }

                this.form.put(name, strValue);
                return this;
            }
        } else {
            return this;
        }
    }

    public HttpRequest form(String name, Object value, Object... parameters) {
        this.form(name, value);

        for(int i = 0; i < parameters.length; i += 2) {
            name = parameters[i].toString();
            this.form(name, parameters[i + 1]);
        }

        return this;
    }

    public HttpRequest form(Map<String, Object> formMap) {
        Iterator var2 = formMap.entrySet().iterator();

        while(var2.hasNext()) {
            Entry<String, Object> entry = (Entry)var2.next();
            this.form((String)entry.getKey(), entry.getValue());
        }

        return this;
    }

    public HttpRequest form(String name, File file) {
        if (null == file) {
            return this;
        } else {
            if (!this.isKeepAlive()) {
                this.keepAlive(true);
            }

            if (this.fileForm == null) {
                this.fileForm = new HashMap();
            }

            this.fileForm.put(name, file);
            return this;
        }
    }

    public Map<String, Object> form() {
        return this.form;
    }

    public HttpRequest body(String body) {
        this.body = body;
        this.form = null;
        this.contentLength(body.length());
        return this;
    }

    public HttpRequest body(String body, String contentType) {
        this.body(body);
        this.contentType(contentType);
        return this;
    }

    public HttpRequest body(byte[] bodyBytes) {
        return this.body(StringUtil.str(bodyBytes, this.charset));
    }

    public HttpRequest timeout(int milliseconds) {
        this.timeout = milliseconds;
        return this;
    }

    public HttpRequest disableCache() {
        this.isDisableCache = true;
        return this;
    }

    public HttpRequest setFollowRedirects(Boolean isFollowRedirects) {
        this.isFollowRedirects = isFollowRedirects;
        return this;
    }

    public HttpRequest setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    public HttpRequest setProxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    public HttpRequest setSSLSocketFactory(SSLSocketFactory ssf) {
        this.ssf = ssf;
        return this;
    }

    public HttpRequest setSSLProtocol(String protocol) {
        if (null == this.ssf) {
            try {
                this.ssf = SSLSocketFactoryBuilder.create().setProtocol(protocol).build();
            } catch (Exception var3) {
                throw new HttpException(var3);
            }
        }

        return this;
    }

    public HttpResponse execute() {
        return this.execute(false);
    }

    public HttpResponse executeAsync() {
        return this.execute(true);
    }

    public HttpResponse execute(boolean isAsync) {
        this.urlWithParamIfGet();
        this.initConnecton();
        this.send();
        HttpResponse httpResponse = this.sendRedirectIfPosible();
        if (null == httpResponse) {
            httpResponse = new HttpResponse(this.httpConnection, this.charset, isAsync);
        }

        return httpResponse;
    }

    /** @deprecated */
    @Deprecated
    public Future<HttpResponse> asyncExecute() {
        return ThreadUtil.execAsync(new Callable<HttpResponse>() {
            public HttpResponse call() throws Exception {
                return HttpRequest.this.execute();
            }
        });
    }

    public HttpRequest basicAuth(String username, String password) {
        String data = username.concat(":").concat(password);
        String base64 = Base64.encode(data, this.charset);
        this.header("Authorization", "Basic " + base64, true);
        return this;
    }

    private void initConnecton() {
        this.httpConnection = HttpConnection.create(this.url, this.method, this.hostnameVerifier, this.ssf, this.timeout, this.proxy).header(this.headers, true);
        if (null != this.cookie) {
            this.httpConnection.setCookie(this.cookie);
        }

        if (this.isDisableCache) {
            this.httpConnection.disableCache();
        }

        if (null != this.isFollowRedirects) {
            this.httpConnection.setInstanceFollowRedirects(this.isFollowRedirects);
        }

    }

    private void urlWithParamIfGet() {
        if (Method.GET.equals(this.method)) {
            if (StringUtil.isNotBlank(this.body)) {
                this.url = HttpUtil.urlWithForm(this.url, this.body);
            } else {
                this.url = HttpUtil.urlWithForm(this.url, this.form);
            }
        }

    }

    private HttpResponse sendRedirectIfPosible() {
        if (this.httpConnection.getHttpURLConnection().getInstanceFollowRedirects()) {
            int responseCode;
            try {
                responseCode = this.httpConnection.responseCode();
            } catch (IOException var3) {
                throw new HttpException(var3);
            }

            if (responseCode != 200 && (responseCode == 302 || responseCode == 301 || responseCode == 303)) {
                this.url = this.httpConnection.header(Header.LOCATION);
                if (this.redirectCount < 2) {
                    ++this.redirectCount;
                    return this.execute();
                }

                LogKit.warn("URL [{}] redirect count more than two !", new Object[]{this.url});
            }
        }

        return null;
    }

    private void send() throws HttpException {
        try {
            if (!Method.POST.equals(this.method) && !Method.PUT.equals(this.method)) {
                this.httpConnection.connect();
            } else if (CollectionUtil.isEmpty(this.fileForm)) {
                this.sendFormUrlEncoded();
            } else {
                this.sendMltipart();
            }

        } catch (IOException var2) {
            throw new HttpException(var2.getMessage(), var2);
        }
    }

    private void sendFormUrlEncoded() throws IOException {
        if (StringUtil.isBlank(this.header(Header.CONTENT_TYPE))) {
            this.httpConnection.header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=" + this.charset, true);
        }

        String content;
        if (StringUtil.isNotBlank(this.body)) {
            content = this.body;
        } else {
            content = HttpUtil.toParams(this.form, this.charset);
        }

        IoUtil.write(this.httpConnection.getOutputStream(), this.charset, true, new Object[]{content});
    }

    private void sendMltipart() throws IOException {
        this.setMultipart();
        OutputStream out = this.httpConnection.getOutputStream();

        try {
            this.writeFileForm(out);
            this.writeForm(out);
            this.formEnd(out);
        } catch (IOException var6) {
            throw var6;
        } finally {
            IoUtil.close(out);
        }

    }

    private void writeForm(OutputStream out) throws IOException {
        if (CollectionUtil.isNotEmpty(this.form)) {
            StringBuilder builder = StringUtil.builder();
            Iterator var3 = this.form.entrySet().iterator();

            while(var3.hasNext()) {
                Entry<String, Object> entry = (Entry)var3.next();
                builder.append("--").append(BOUNDARY).append("\r\n");
                builder.append(StringUtil.format("Content-Disposition: form-data; name=\"{}\"\r\n\r\n", new Object[]{entry.getKey()}));
                builder.append(entry.getValue()).append("\r\n");
            }

            IoUtil.write(out, this.charset, false, new Object[]{builder});
        }

    }

    private void writeFileForm(OutputStream out) throws IOException {
        Iterator var3 = this.fileForm.entrySet().iterator();

        while(var3.hasNext()) {
            Entry<String, File> entry = (Entry)var3.next();
            File file = (File)entry.getValue();
            StringBuilder builder = StringUtil.builder().append("--").append(BOUNDARY).append("\r\n");
            builder.append(StringUtil.format("Content-Disposition: form-data; name=\"{}\"; filename=\"{}\"\r\n", new Object[]{entry.getKey(), file.getName()}));
            builder.append(StringUtil.format("Content-Type: {}\r\n\r\n", new Object[]{HttpUtil.getMimeType(file.getName())}));
            IoUtil.write(out, this.charset, false, new Object[]{builder});
            FileUtil.writeToStream(file, out);
            IoUtil.write(out, this.charset, false, new Object[]{"\r\n"});
        }

    }

    private void formEnd(OutputStream out) throws IOException {
        out.write(BOUNDARY_END);
        out.flush();
    }

    private void setMultipart() {
        this.httpConnection.header(Header.CONTENT_TYPE, "multipart/form-data; boundary=" + BOUNDARY, true);
    }

    static {
        BOUNDARY_END = StringUtil.format("--{}--\r\n", new Object[]{BOUNDARY}).getBytes();
    }
}
