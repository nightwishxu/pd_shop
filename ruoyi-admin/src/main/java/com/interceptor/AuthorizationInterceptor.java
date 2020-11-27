package com.interceptor;


import com.base.Const;
import com.base.annotation.ApiMethod;
import com.base.api.ApiException;
import com.base.util.DateUtil;
import com.base.util.IPUtil;
import com.base.util.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.item.dao.model.MobileVerify;
import com.item.service.MobileVerifyService;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.sql.SqlUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限(Token)验证
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String url = request.getRequestURI();
        // 获取客户端IP
        String startIp = IPUtil.getClientIP(request);

        // 获取所有参数名称
        Enumeration<String> enu = request.getParameterNames();
        // 获取所有参数的集合
        Map<String, Object> paramsMap = new HashMap<>();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            paramsMap.put(paraName, request.getParameter(paraName));
        }

        String time = DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");
        logger.info("[限流入口统计] |date={}|apiName={}|params={}|jsonParam={}|startIp={}", time, url, JSONUtils.serialize(paramsMap), null,startIp);

        return true;
    }

}
