package com.base.action;


import com.base.web.annotation.LoginMethod;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.RequestUtils;
import com.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController extends com.ruoyi.common.core.controller.BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseController() {
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//    }

    protected HttpSession getSession() {
        return this.getRequest().getSession();
    }

    protected Map<String, Object> getSessionAttribute(LoginMethod loginMethod) {
        Object obj = this.getSession().getAttribute(loginMethod.getName());
        return obj == null ? null : (Map)obj;
    }

    protected void setSessinAttribute(Map<String, Object> obj, LoginMethod loginMethod) {
        this.getSession().setAttribute(loginMethod.getName(), obj);
    }

    protected HttpServletRequest getRequest() {
        return RequestUtils.getRequest();
    }

    protected HttpServletResponse getResponse() {
        return ResponseUtils.getResponse();
    }
}
