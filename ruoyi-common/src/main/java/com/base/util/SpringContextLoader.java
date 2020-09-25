package com.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Map;

public class SpringContextLoader implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(SpringContextLoader.class);
    private static ApplicationContext applicationContext = null;

    public SpringContextLoader() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        try {
            logger.info("绑定RMI主机IP:" + SystemUtilsEx.getHostIp());
            System.setProperty("java.rmi.server.hostname", SystemUtilsEx.getHostIp());
            logger.info("系统绑定RMI主机IP成功:" + SystemUtilsEx.getHostIp());
        } catch (Exception var4) {
            logger.error("系统绑定RMI主机IP失败", var4);
        }

        try {
            applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
            logger.info("系统spring配置装载成功~");
        } catch (Exception var3) {
            logger.error("系统spring配置装载失败", var3);
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Enumeration<Driver> driverEnum = DriverManager.getDrivers();
            if (driverEnum != null) {
                while(driverEnum.hasMoreElements()) {
                    DriverManager.deregisterDriver((Driver)driverEnum.nextElement());
                }
            }

            logger.info("系统卸载库驱动成功~");
        } catch (Throwable var6) {
            logger.info("系统卸载库驱动出错:" + var6.getMessage());
        }

        try {
            Class<?> cls = Class.forName("com.mysql.jdbc.AbandonedConnectionCleanupThread");
            Method method = cls == null ? null : cls.getMethod("shutdown");
            if (method != null) {
                logger.info("系统卸载数据库驱动-开始清除MySQL线程...");
                method.invoke((Object)null);
                logger.info("系统卸载数据库驱动-清除MySQL线程成功~");
            }
        } catch (Throwable var5) {
            logger.info("系统卸载数据库驱动-清除MySQL线程出错:" + var5.getMessage());
        }

        try {
            applicationContext = null;
            logger.info("系统spring配置卸载成功~");
        } catch (Exception var4) {
            logger.error("系统spring配置卸载失败", var4);
        }

    }

    public static void setSpringContext(ApplicationContext applicationContext1) {
        applicationContext = applicationContext1;
    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            applicationContext = ContextLoader.getCurrentWebApplicationContext();
        }

        return applicationContext;
    }

    public static Object getBean(String beanName) {
        if (!getApplicationContext().containsBean(beanName)) {
            if (!beanName.substring(0, 1).toLowerCase().equals(beanName.substring(0, 1))) {
                beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
            } else if (!beanName.substring(0, 1).toUpperCase().equals(beanName.substring(0, 1))) {
                beanName = beanName.substring(0, 1).toUpperCase() + beanName.substring(1);
            }
        }

        return getApplicationContext().getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return (T)getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        String[] beans = getApplicationContext().getBeanNamesForType(clazz);
        return beans != null && beans.length > 0 ? getBean(beans[0], clazz) : null;
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> clazz) {
        return getApplicationContext().getBeansWithAnnotation(clazz);
    }
}
