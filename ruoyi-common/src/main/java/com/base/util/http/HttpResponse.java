package com.base.util.http;


import com.base.exception.HttpException;
import com.base.util.Convert;
import com.base.util.FastByteArrayOutputStream;
import com.base.util.IoUtil;
import com.base.util.StringUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

public class HttpResponse extends HttpBase<HttpResponse> {
    private HttpConnection httpConnection;
    private InputStream in;
    private volatile boolean isAsync;
    private FastByteArrayOutputStream out;
    private int status;

    protected HttpResponse(HttpConnection httpConnection, Charset charset, boolean isAsync) {
        this.httpConnection = httpConnection;
        this.charset = charset;
        this.isAsync = isAsync;
        this.init();
    }

    public int getStatus() {
        return this.status;
    }

    public HttpResponse sync() throws HttpException {
        return this.isAsync ? this.forceSync() : this;
    }

    public String contentEncoding() {
        return this.header(Header.CONTENT_ENCODING);
    }

    public boolean isGzip() {
        String contentEncoding = this.contentEncoding();
        return contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip");
    }

    public InputStream bodyStream() {
        return (InputStream)(this.isAsync ? this.in : new ByteArrayInputStream(this.out.toByteArray()));
    }

    public byte[] bodyBytes() {
        this.sync();
        return null == this.out ? null : this.out.toByteArray();
    }

    public String body() throws HttpException {
        try {
            return HttpUtil.getString(this.bodyBytes(), this.charset, null == this.charset);
        } catch (IOException var2) {
            throw new HttpException(var2);
        }
    }

    public String toString() {
        StringBuilder sb = StringUtil.builder();
        sb.append("Response Headers: ").append("\r\n");
        Iterator var2 = this.headers.entrySet().iterator();

        while(var2.hasNext()) {
            Entry<String, List<String>> entry = (Entry)var2.next();
            sb.append("    ").append(entry).append("\r\n");
        }

        sb.append("Response Body: ").append("\r\n");
        sb.append("    ").append(this.body()).append("\r\n");
        return sb.toString();
    }

    private HttpResponse init() throws HttpException {
        try {
            this.status = this.httpConnection.responseCode();
            this.headers = this.httpConnection.headers();
            Charset charset = this.httpConnection.getCharset();
            if (null != charset) {
                this.charset = charset;
            }

            this.in = this.status < 400 ? this.httpConnection.getInputStream() : this.httpConnection.getErrorStream();
        } catch (IOException var2) {
            if (!(var2 instanceof FileNotFoundException)) {
                throw new HttpException(var2.getMessage(), var2);
            }
        }

        return this.isAsync ? this : this.forceSync();
    }

    private void readBody(InputStream in) throws IOException {
        if (this.isGzip() && !(in instanceof GZIPInputStream)) {
            in = new GZIPInputStream((InputStream)in);
        }

        int contentLength = Convert.toInt(this.header(Header.CONTENT_LENGTH), 0);
        this.out = contentLength > 0 ? new FastByteArrayOutputStream(contentLength) : new FastByteArrayOutputStream();

        try {
            IoUtil.copy((InputStream)in, this.out);
        } catch (EOFException var4) {
            ;
        }

    }

    private HttpResponse forceSync() {
        try {
            this.readBody(this.in);
        } catch (IOException var5) {
            if (!(var5 instanceof FileNotFoundException)) {
                throw new HttpException(var5);
            }
        } finally {
            if (this.isAsync) {
                this.isAsync = false;
            }

            IoUtil.close(this.in);
            this.httpConnection.disconnect();
        }

        return this;
    }
}

