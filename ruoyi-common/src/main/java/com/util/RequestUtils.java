package com.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtils {
    private static final Logger log = LoggerFactory.getLogger(RequestUtils.class);
    private static final ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal();
    private static final String DEFAULT_STATICFILES = ".css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk,.jsp,.html";
    public static final String AJAX_HEAD = "x-requested-with";
    public static final String AJAX_HEAD_STRING = "XMLHttpRequest";
    public static final int HTTP_PORT = 80;
    public static final int HTTPS_PORT = 443;
//    public static String[] staticFiles = StringUtil.split(CoreConstants.getProperty("web.staticFile", ".css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk,.jsp,.html"), ",");
    public static String[] staticFiles = StringUtil.split(".css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk,.jsp,.html", ",");
    public static String[] API_PATHS;

    private RequestUtils() {
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest)requestLocal.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestLocal.set(request);
    }

    public static HttpServletRequest getThisRequest() {
        try {
            return getRequest();
        } catch (Exception var1) {
            return null;
        }
    }

    public static Map<String, String> getFromRequest() {
        return getFromRequest(getRequest());
    }

    public static Map<String, String> getFromRequest(HttpServletRequest request) {
        Map<String, String> params = new HashMap();
        Map requestParams = request.getParameterMap();
        Iterator iter = requestParams.keySet().iterator();

        while(iter.hasNext()) {
            String name = (String)iter.next();
            String[] values = (String[])((String[])requestParams.get(name));
            String valueStr = "";

            for(int i = 0; i < values.length; ++i) {
                valueStr = i == values.length - 1 ? valueStr + values[i] : valueStr + values[i] + ",";
            }

            params.put(name, valueStr);
        }

        return params;
    }

    public static String getUri(HttpServletRequest request) {
        String result = request.getPathInfo();
        if (result == null) {
            result = request.getServletPath();
        } else {
            result = request.getServletPath() + result;
        }

        if (StringUtil.isBlank(result)) {
            result = "/";
        }

        return filterMore(result);
    }

    public static String filterMore(String path) {
        return path.replaceAll("/+", "/");
    }

    public static boolean isStaticFile(String uri) {
        if (staticFiles == null) {
            try {
                throw new Exception("检测到“applitcation.properties”中没有配置“web.staticFile”属性。配置示例：\n#静态文件后缀\nweb.staticFile=.css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk");
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

        return StringUtil.endWithAny(uri, staticFiles);
    }

    public static boolean isStaticFile(HttpServletRequest request) {
        if (request == null) {
            return false;
        } else {
            String uri = getUri(request);
            return isStaticFile(uri);
        }
    }

    public static boolean isStaticFile() {
        return isStaticFile(getRequest());
    }

    public static boolean isAjax() {
        return isAjax(getRequest());
    }

    public static boolean isAjax(HttpServletRequest request) {
        return request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");
    }

    public static String getBasePath() {
        return getBasePath(getRequest());
    }

    public static String getBasePath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + (request.getServerPort() != 80 && request.getServerPort() != 443 ? ":" + request.getServerPort() : "") + request.getContextPath();
    }

    public static boolean isApiUrl() {
        return isApiUrl(getRequest());
    }

    public static boolean isApiUrl(HttpServletRequest request) {
        return isApiPath(getUri(request));
    }

    private static boolean isApiPath(String servletPath) {
        servletPath = filterPath(servletPath);
        String[] var1 = API_PATHS;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String api = var1[var3];
            if (servletPath.startsWith(filterPath(api))) {
                return true;
            }
        }

        return false;
    }

    public static boolean matchApiUrl(String url, String basePath) {
        String[] var2 = API_PATHS;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String api = var2[var4];
            boolean isApi = matchUrl(url, basePath + "/*" + filterPath(api));
            if (isApi) {
                return true;
            }
        }

        return false;
    }

    public static boolean matchUrl(String url, String path) {
        String str = PattenUtil.matchFirst(path, url);
        return str != null && url.indexOf(str) == 0;
    }

    public static String filterPath(String path) {
        path = filterMore(path);
        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        if (!path.endsWith("/")) {
            path = path + "/";
        }

        return path;
    }

//    static {
//        API_PATHS = StringUtil.split(CoreConstants.API_PATH, ",");
//    }
}
