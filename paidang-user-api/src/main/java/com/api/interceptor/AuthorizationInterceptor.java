package com.api.interceptor;

import com.api.MEnumError;
import com.api.MErrorEnum;
import com.base.Const;
import com.base.annotation.ApiMethod;
import com.base.api.ApiException;

import com.base.util.DateUtil;
import com.base.util.IPUtil;
import com.base.util.JSONUtils;
import com.base.web.ParameterRequestWrapper;
import com.github.pagehelper.PageHelper;
import com.item.dao.model.MobileVerify;
import com.item.service.MobileVerifyService;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.sql.SqlUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private MobileVerifyService mobileVerifyService;
    
    private static final int NICK_LENGTH = 30;
    
    private static final String TOKEN = "token";
    private static final String USER_ID = "userid";
    private static final String DEVICE_ID = "deviceid";
    private static final String DEVICE_TYPE = "deviceType";
    private static final String APP_VERSION = "appVersion";

    private static Map<String,String> signMap = new HashMap<>();
    static {
        signMap.put("3.0.0.0","");
        signMap.put("3.0.0.1","");
    }


    public void checkoutVersion(String appVersion){
        if (StringUtils.isBlank(appVersion)){
            throw new ApiException(400, "为了您更好体验，请升级至最新的版本");
        }
        String[] split = appVersion.split("\\.");
        if(split.length!=4){
            throw new ApiException(400, "为了您更好体验，请升级至最新的版本");
        }
        StringBuilder builder = new StringBuilder();
        builder.append(split[0]).append(".").append(split[1]).append(".0.").append(split[3]);
        if (!signMap.containsKey(builder.toString())){
            throw new ApiException(400, "为了您更好体验，请升级至最新的版本");
        }
    }


    public void signCheck(){

    }

    @Value("${token.auth}")
    private Boolean TOKEN_AUTH;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String appVersion = request.getParameter(APP_VERSION);
//        checkoutVersion(appVersion);
        String userId = request.getParameter(USER_ID);
        if(StringUtils.isBlank(userId)){
            userId = request.getHeader(USER_ID);
        }
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


            if(handler instanceof HandlerMethod) {
        	ApiMethod annotation = ((HandlerMethod) handler).getMethodAnnotation(ApiMethod.class);
            if (annotation == null) return true;

            String time = DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");
            logger.info("[限流入口统计] |date={}|appVersion={}|memberId={}|apiName={}|params={}|jsonParam={}|startIp={}", time,appVersion, userId, url, JSONUtils.serialize(paramsMap), null,startIp);

            //从header中获取token
            String token = request.getHeader(TOKEN);
            //如果header中不存在token，则从参数中获取token
            if(StringUtils.isBlank(token)){
                token = request.getParameter(TOKEN);
            }
            
            String nickName = request.getParameter("nickName");
            if (StringUtils.isNotBlank(nickName)){
            	if (nickName.length() > NICK_LENGTH){
            		throw new ApiException(MEnumError.NICK_LEN_ERROR);
            	}
            }
            
            //分页
            if (annotation.isPage()){
            	String p = request.getParameter(Const.PAGE);
            	String l = request.getParameter(Const.LIMIT);
            	if (StringUtils.isBlank(p) || StringUtils.isBlank(l)){
            		throw new ApiException(MErrorEnum.PAGE_LIMIT_NONG);
            	}
//                PageDomain pageDomain = TableSupport.buildPageRequest();
//                Integer pageNum = pageDomain.getPageNum();
//                Integer pageSize = pageDomain.getPageSize();
//                String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
//                PageHelper.startPage(pageNum, pageSize, orderBy);
            }
            
            MobileVerify mobileVerify = null;
            
            if (StringUtils.isNotBlank(token)){
                mobileVerify = mobileVerifyService.queryByToken(token);
            }
            
            //登陆
            if(annotation.isLogin()  && TOKEN_AUTH){
                if(mobileVerify == null || (mobileVerify.getExpireTime() != null && mobileVerify.getExpireTime().getTime() < System.currentTimeMillis())){
            		throw new ApiException(MErrorEnum.LOGIN_FAIL_ERROR);
                }
            }
            
            if (mobileVerify != null){
        		if(mobileVerify.getExpireTime() != null){
            		Date date = new Date();
            		mobileVerify.setExpireTime(DateUtil.addMinute(date, 30));
            		mobileVerify.setUpdateTime(date);
            		mobileVerifyService.updateByPrimaryKey(mobileVerify);
            	}
//        		ParameterRequestWrapper wrapper =  (ParameterRequestWrapper) request;
//            	wrapper.addParameter(USER_ID, mobileVerify.getUserId());
//            	wrapper.addParameter(DEVICE_ID, mobileVerify.getDeviceId());
//            	wrapper.addParameter(DEVICE_TYPE, mobileVerify.getDeviceType());
//            	wrapper.addParameter(TOKEN, token);
        	}
           
        }
        return true;
    }
    
}
