package com.base.util;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.annotation.Annotation;
import java.util.Map;

public class SpringContextUtil implements ServletContextListener {
    private static ApplicationContext applicationContext;

    public SpringContextUtil() {
    }

    public static void setSpringContext(ApplicationContext applicationContext) {
        applicationContext = applicationContext;
    }

    public static Object getBean(String beanName) {
        if (!applicationContext.containsBean(beanName)) {
            if (!beanName.substring(0, 1).toLowerCase().equals(beanName.substring(0, 1))) {
                beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
            } else if (!beanName.substring(0, 1).toUpperCase().equals(beanName.substring(0, 1))) {
                beanName = beanName.substring(0, 1).toUpperCase() + beanName.substring(1);
            }
        }

        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return (T)getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        String[] beans = applicationContext.getBeanNamesForType(clazz);
        return beans != null && beans.length > 0 ? getBean(beans[0], clazz) : null;
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> clazz) {
        return applicationContext.getBeansWithAnnotation(clazz);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            applicationContext = null;
            LogKit.info("spring配置卸载成功");
        } catch (Exception var3) {
            LogKit.error("spring配置卸载失败", var3);
        }

    }

    public void contextInitialized(ServletContextEvent sce) {
        try {
            applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
            LogKit.info("spring配置装载成功");
        } catch (Exception var4) {
            LogKit.error("spring配置装载失败", var4);
        }

//        try {
//            CronManager.schedule();
//        } catch (Exception var3) {
//            LogKit.error("装载定时任务失败", var3);
//        }

    }
}
