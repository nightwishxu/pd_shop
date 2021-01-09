package com.ruoyi.web.core.config;

import com.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: cxy
 * @Date: 2019/1/10
 * @Description: 在web的配置文件中，实例化登陆的拦截器，并添加规则
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问资源定义： /api/ 所有资源
        registry.addMapping("/**")
                // 只允许本地的9000端口访问
                .allowedOrigins("*")
                // 允许所有方法
                .allowedMethods("*")
                .allowedHeaders("*");

    }
}