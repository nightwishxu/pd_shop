package com.base.util;

import com.base.support.Props;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;

/**
 * @author ：xww
 * @date ：Created in 2020/8/9 0:12
 * @description：
 * @modified By：
 * @version:
 */
public class PropertySupport {
    private static final String CONFIG_FILE = "application.properties";
    public static final String PROFILE_ACTIVE = "spring.profiles.active";
    public static String active = null;
    protected static Properties p = new Properties();

    protected PropertySupport() {
    }

    protected static void init(String propertyFileName) {
        p = getProperties(propertyFileName);
        String profile = getProfileActive();
        if (StringUtil.isNotBlank(profile)) {
            initProfile(profile);
        }

    }

    protected static String getProfileActive() {
        return StringUtils.isNoneBlank(active)?active:getProperty("spring.profiles.active");
    }

    public static void initProfile(String profile) {
        Properties active = getProperties("application-" + profile + ".properties");
        put(active);
    }

    public static String getProperty(String key, String defaultValue) {
        return StringUtil.isEmpty(getProperty(key)) ? defaultValue : getProperty(key);
    }

    public static String getProperty(String key) {
        String value = p.getProperty(key);
        return StringUtil.isBlank(value) ? null : value.trim();
    }

    public static String get(String... properties) {
        String[] var1 = properties;
        int var2 = properties.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String property = var1[var3];

            try {
                String override = System.getProperty(property);
                override = override != null ? override : System.getenv(property);
                if (override != null) {
                    return override;
                }
            } catch (Throwable var6) {
                LogKit.error("Could not resolve '" + property + "' as system property: " + var6);
            }
        }

        return null;
    }

    public static Integer getInt(String key) {
        return getInt(key, (Integer)null);
    }

    public static Integer getInt(String key, Integer defaultValue) {
        String value = getProperty(key);
        return value != null ? Integer.parseInt(value.trim()) : defaultValue;
    }

    public static Long getLong(String key) {
        return getLong(key, (Long)null);
    }

    public static Long getLong(String key, Long defaultValue) {
        String value = getProperty(key);
        return value != null ? Long.parseLong(value.trim()) : defaultValue;
    }

    public static Float getFloat(String key) {
        return getFloat(key, (Float)null);
    }

    public static Float getFloat(String key, Float defaultValue) {
        String value = getProperty(key);
        return value != null ? Float.parseFloat(value) : defaultValue;
    }

    public static Double getDouble(String key) {
        return getDouble(key, (Double)null);
    }

    public static Double getDouble(String key, Double defaultValue) {
        String value = getProperty(key);
        return value != null ? Double.parseDouble(value) : defaultValue;
    }

    public static Boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        String value = getProperty(key);
        if (value != null) {
            value = value.toLowerCase().trim();
            if ("true".equals(value)) {
                return true;
            } else if ("false".equals(value)) {
                return false;
            } else {
                throw new RuntimeException("The value can not parse to Boolean : " + value);
            }
        } else {
            return defaultValue;
        }
    }

    public static BigDecimal getBigDecimal(String key) {
        return getBigDecimal(key, (BigDecimal)null);
    }

    public static BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        String value = getProperty(key);
        return value != null ? new BigDecimal(value) : defaultValue;
    }

    public static void reload() {
        reload("application.properties");
    }

    private static void reload(String filename) {
        if (p != null) {
            p.clear();
        }

        init(filename);
    }

    public static Properties putParams(Map<String, Object> params) {
        return putParam(params);
    }

    public static Properties putParam(Map<String, Object> params) {
        if (p != null) {
            p.putAll(params);
        }

        return p;
    }

    static Properties put(Properties properties) {
        if (properties != null) {
            p.putAll(properties);
        }

        return p;
    }

    public static Properties getProfile() {
        return p;
    }

    private static Properties getProperties(String file) {
        return Props.getProp(file);
    }

    static {
        init("application.properties");
    }
}
