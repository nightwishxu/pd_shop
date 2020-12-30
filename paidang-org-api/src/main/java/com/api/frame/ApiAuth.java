package com.api.frame;

import com.api.MEnumError;
import com.api.MErrorEnum;
import com.base.Const;
import com.base.annotation.ApiMethod;
import com.base.api.ApiException;

import com.base.api.SignAuth;
import com.base.api.interceptor.AuthValidator;
import com.base.util.DateUtil;
import com.base.web.ParameterRequestWrapper;
import com.item.dao.model.MobileVerify;
import com.item.service.MobileVerifyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component("validator")
public class ApiAuth extends SignAuth implements AuthValidator {
    @Autowired
    private MobileVerifyService mobileVerifyService;

    private static final int NICK_LENGTH = 30;

    private static final String TOKEN = "token";
    private static final String USER_ID = "userid";
    private static final String DEVICE_ID = "deviceid";
    private static final String DEVICE_TYPE = "deviceType";

    @Override
    public boolean validate(ApiMethod annotation, HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) {
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
        }

        MobileVerify mobileVerify = null;

        if (StringUtils.isNotBlank(token)){
            mobileVerify = mobileVerifyService.queryByToken(token);
        }

        //登陆
        if(annotation.isLogin()){
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
            ParameterRequestWrapper wrapper =  (ParameterRequestWrapper) request;
            wrapper.addParameter(USER_ID, mobileVerify.getUserId());
            wrapper.addParameter(DEVICE_ID, mobileVerify.getDeviceId());
            wrapper.addParameter(DEVICE_TYPE, mobileVerify.getDeviceType());
            wrapper.addParameter(TOKEN, token);
        }
        return true;
    }

//    @Override
//    public boolean validateWx(WxMethod apiMethod, HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) {
//        return true;
//    }
}
