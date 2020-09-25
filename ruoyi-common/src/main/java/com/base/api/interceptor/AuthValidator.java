package com.base.api.interceptor;

import com.base.annotation.ApiMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;

public interface AuthValidator {
    boolean validate(ApiMethod var1, HttpServletRequest var2, HttpServletResponse var3, HandlerMethod var4);

//    boolean validateWx(WxMethod var1, HttpServletRequest var2, HttpServletResponse var3, HandlerMethod var4);
}
