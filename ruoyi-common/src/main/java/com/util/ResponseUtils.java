package com.util;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.base.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseUtils {
    private static final ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal();
    private static Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    private ResponseUtils() {
    }

    public static void setResponse(HttpServletResponse response) {
        responseLocal.set(response);
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse)responseLocal.get();
    }

    public static void renderJson(Object object) {
        renderJson(getResponse(), object);
    }

    public static void renderJson(HttpServletResponse response, Object object) {
        render(response, JSONUtils.serialize(object), "application/json;charset=UTF-8");
    }

    public static void renderJsonp(Object object) {
        renderJsonp(getResponse(), object);
    }

    public static void renderJsonp(HttpServletResponse response, Object object) {
        render(response, "jsonpcallback(" + JSONUtils.serialize(object) + ")", "application/json;charset=UTF-8");
    }

    public static void render(String text, String contentType) {
        render(getResponse(), text, contentType);
    }

    public static void render(HttpServletResponse response, String text, String contentType) {
        try {
            response.setContentType(contentType);
            response.getWriter().write(text);
        } catch (IOException var4) {
            logger.error(var4.getMessage(), var4);
        }

    }

    public static void renderText(String text) {
        renderText(getResponse(), text);
    }

    public static void renderText(HttpServletResponse response, String text) {
        try {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(text);
        } catch (IOException var3) {
            logger.error(var3.getMessage(), var3);
        }

    }
}
