package com.base.util.http;


import cn.hutool.core.collection.CollectionUtil;
import com.base.util.CharsetUtil;
import com.base.util.StringUtil;

import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;

public abstract class HttpBase<T> {
    public static final String HTTP_1_0 = "HTTP/1.0";
    public static final String HTTP_1_1 = "HTTP/1.1";
    protected Map<String, List<String>> headers = new HashMap();
    protected Charset charset;
    protected String httpVersion;
    protected String body;

    public HttpBase() {
        this.charset = CharsetUtil.CHARSET_UTF_8;
        this.httpVersion = "HTTP/1.1";
    }

    public String header(String name) {
        if (StringUtil.isBlank(name)) {
            return null;
        } else {
            List<String> values = (List)this.headers.get(name.trim());
            return CollectionUtil.isEmpty(values) ? null : (String)values.get(0);
        }
    }

    public String header(Header name) {
        return this.header(name.toString());
    }

    public T header(String name, String value, boolean isOverride) {
        if (null != name && null != value) {
            List<String> values = (List)this.headers.get(name.trim());
            if (!isOverride && !CollectionUtil.isEmpty(values)) {
                values.add(value.trim());
            } else {
                ArrayList<String> valueList = new ArrayList();
                valueList.add(value);
                this.headers.put(name.trim(), valueList);
            }
        }

        return (T)this;
    }

    public T header(Header name, String value, boolean isOverride) {
        return this.header(name.toString(), value, isOverride);
    }

    public T header(Header name, String value) {
        return this.header(name.toString(), value, true);
    }

    public T header(String name, String value) {
        return this.header(name, value, true);
    }

    public T header(Map<String, List<String>> headers) {
        if (CollectionUtil.isEmpty(headers)) {
            return (T)this;
        } else {
            Iterator var3 = headers.entrySet().iterator();

            while(var3.hasNext()) {
                Entry<String, List<String>> entry = (Entry)var3.next();
                String name = (String)entry.getKey();
                Iterator var5 = ((List)entry.getValue()).iterator();

                while(var5.hasNext()) {
                    String value = (String)var5.next();
                    this.header(name, StringUtil.nullToEmpty(value), false);
                }
            }

            return (T)this;
        }
    }

    public T removeHeader(String name) {
        if (name != null) {
            this.headers.remove(name.trim());
        }

        return (T)this;
    }

    public T removeHeader(Header name) {
        return this.removeHeader(name.toString());
    }

    public Map<String, List<String>> headers() {
        return Collections.unmodifiableMap(this.headers);
    }

    public String httpVersion() {
        return this.httpVersion;
    }

    public T httpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
        return (T)this;
    }

    public String charset() {
        return this.charset.name();
    }

    public T charset(String charset) {
        if (StringUtil.isNotBlank(charset)) {
            this.charset = Charset.forName(charset);
        }

        return (T)this;
    }

    public T charset(Charset charset) {
        if (null != charset) {
            this.charset = charset;
        }

        return (T)this;
    }

    public String toString() {
        StringBuilder sb = StringUtil.builder();
        sb.append("Request Headers: ").append("\r\n");
        Iterator var2 = this.headers.entrySet().iterator();

        while(var2.hasNext()) {
            Entry<String, List<String>> entry = (Entry)var2.next();
            sb.append("    ").append(entry).append("\r\n");
        }

        sb.append("Request Body: ").append("\r\n");
        sb.append("    ").append(this.body).append("\r\n");
        return sb.toString();
    }
}
