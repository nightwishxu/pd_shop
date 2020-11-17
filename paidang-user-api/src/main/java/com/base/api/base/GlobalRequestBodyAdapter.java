package com.base.api.base;

import com.base.annotation.ApiMethod;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author: xww
 * @create: 2020-11-16 21:47
 * @description:
 * @modified By:
 * @version:
 **/
@ControllerAdvice//此处设置需要当前Advice执行的域 , 省略默认全局生效
public class GlobalRequestBodyAdapter  extends RequestBodyAdviceAdapter  {
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        ApiMethod annotation = parameter.getMethod().getAnnotation(ApiMethod.class);
        if (annotation!=null && annotation.isPage()==true){
            PageDomain pageDomain = TableSupport.buildPageRequest();
            Integer pageNum = pageDomain.getPageNum();
            Integer pageSize = pageDomain.getPageSize();
            if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
            {
                String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
                PageHelper.startPage(pageNum, pageSize, orderBy);
            }
        }
        return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        ApiMethod annotation = parameter.getMethod().getAnnotation(ApiMethod.class);
        if (annotation!=null && annotation.isPage()==true){
            PageDomain pageDomain = TableSupport.buildPageRequest();
            Integer pageNum = pageDomain.getPageNum();
            Integer pageSize = pageDomain.getPageSize();
            if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
            {
                String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
                PageHelper.startPage(pageNum, pageSize, orderBy);
            }
        }
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
