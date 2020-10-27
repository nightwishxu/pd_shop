package com.base.util;


import cn.hutool.core.util.ClassUtil;
import com.base.exception.UtilException;
import org.springframework.util.Assert;

import java.io.*;
import java.net.*;

public final class URLUtil {
    private URLUtil() {
    }

    public static URL url(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException var2) {
            throw new UtilException(var2.getMessage(), var2);
        }
    }

    public static byte[] download(String urlString) throws MalformedURLException, IOException {
        URL url = url(urlString);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        byte[] bs = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);

        int len;
        while((len = is.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }

        bos.close();
        is.close();
        return bos.toByteArray();
    }

    public static URL getURL(String pathBaseClassLoader) {
        return ClassUtil.getClassLoader().getResource(pathBaseClassLoader);
    }

    public static URL getURL(String path, Class<?> clazz) {
        return clazz.getResource(path);
    }

    public static URL getURL(File file) {
        Assert.notNull(file, "File is null !");

        try {
            return file.toURI().toURL();
        } catch (MalformedURLException var2) {
            throw new UtilException("Error occured when get URL!", var2);
        }
    }

    public static URL[] getURLs(File... files) {
        URL[] urls = new URL[files.length];

        try {
            for(int i = 0; i < files.length; ++i) {
                urls[i] = files[i].toURI().toURL();
            }

            return urls;
        } catch (MalformedURLException var3) {
            throw new UtilException("Error occured when get URL!", var3);
        }
    }

    public static String formatUrl(String url) {
        if (StringUtil.isBlank(url)) {
            return null;
        } else {
            return !url.startsWith("http://") && !url.startsWith("https://") ? "http://" + url : url;
        }
    }

    public static String complateUrl(String baseUrl, String relativePath) {
        baseUrl = formatUrl(baseUrl);
        if (StringUtil.isBlank(baseUrl)) {
            return null;
        } else {
            try {
                URL absoluteUrl = new URL(baseUrl);
                URL parseUrl = new URL(absoluteUrl, relativePath);
                return parseUrl.toString();
            } catch (MalformedURLException var4) {
                throw new UtilException(var4);
            }
        }
    }

    public static String encode(String url, String charset) {
        try {
            return URLEncoder.encode(url, charset);
        } catch (UnsupportedEncodingException var3) {
            throw new UtilException(var3);
        }
    }

    public static String decode(String url, String charset) {
        try {
            return URLDecoder.decode(url, charset);
        } catch (UnsupportedEncodingException var3) {
            throw new UtilException(var3);
        }
    }

    public static String getPath(String uriStr) {
        URI uri = null;

        try {
            uri = new URI(uriStr);
        } catch (URISyntaxException var3) {
            throw new UtilException(var3);
        }

        return uri == null ? null : uri.getPath();
    }

    public static URI toURI(URL url) {
        return toURI(url.toString());
    }

    public static URI toURI(String location) {
        try {
            return new URI(location.replace(" ", "%20"));
        } catch (URISyntaxException var2) {
            throw new UtilException(var2);
        }
    }
}
