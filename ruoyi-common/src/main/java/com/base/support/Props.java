//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.support;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.getter.BasicTypeGetter;
import cn.hutool.core.getter.OptBasicTypeGetter;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.UrlResource;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.setting.SettingRuntimeException;
import com.base.exception.SystemException;
import com.base.util.Convert;
import com.base.util.IoUtil;
import com.base.util.StringUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.util.Date;
import java.util.Properties;

public final class Props extends Properties implements BasicTypeGetter<String>, OptBasicTypeGetter<String> {
    private static final long serialVersionUID = 1935981579709590740L;
    private URL propertiesFileUrl;
    private WatchMonitor watchMonitor;

    public static Properties getProp(String resource) {
        return new Props(resource);
    }

    public Props() {
    }

    public Props(String pathBaseClassLoader) {
        this.load(new ClassPathResource(pathBaseClassLoader));
    }

    public Props(File propertiesFile) {
        this.load(new UrlResource(propertiesFile));
    }

    public Props(String path, Class<?> clazz) {
        this.load(new ClassPathResource(path, clazz));
    }

    public Props(URL propertiesUrl) {
        this.load(new UrlResource(propertiesUrl));
    }

    public Props(Properties properties) {
        if (CollectionUtil.isNotEmpty(properties)) {
            this.putAll(properties);
        }

    }

    public void load(UrlResource urlResource) {
        this.propertiesFileUrl = urlResource.getUrl();
        if (null == this.propertiesFileUrl) {
            throw new SettingRuntimeException("Can not find properties file: [{}]", new Object[]{urlResource});
        } else {
            InputStream in = null;

            try {
                in = urlResource.getStream();
                super.load(new InputStreamReader(in, "utf-8"));
            } catch (Exception var7) {
                throw new SystemException("Load properties error!");
            } finally {
                IoUtil.close(in);
            }

        }
    }

    public void load() {
        this.load(new UrlResource(this.propertiesFileUrl));
    }

    public void autoLoad(boolean autoReload) {
        if (autoReload) {
            if (null != this.watchMonitor) {
                this.watchMonitor.close();

                try {
                    this.watchMonitor = WatchMonitor.create(Paths.get(this.propertiesFileUrl.toURI()), new Kind[0]);
                    this.watchMonitor.setWatcher(new SimpleWatcher() {
                        public void onModify(WatchEvent<?> event) {
                            Props.this.load();
                        }
                    }).start();
                } catch (Exception var3) {
                    throw new SettingRuntimeException(var3, "Setting auto load not support url: [{}]", new Object[]{this.propertiesFileUrl});
                }
            }
        } else {
            IoUtil.close(this.watchMonitor);
            this.watchMonitor = null;
        }

    }

    @Override
    public Object getObj(String key, Object defaultValue) {
        return this.getStr(key, null == defaultValue ? null : defaultValue.toString());
    }

    @Override
    public Object getObj(String key) {
        return this.getObj((String)key, (Object)null);
    }

    @Override
    public String getStr(String key, String defaultValue) {
        return super.getProperty(key, defaultValue);
    }

    @Override
    public String getStr(String key) {
        return super.getProperty(key);
    }

    @Override
    public Integer getInt(String key, Integer defaultValue) {
        return Convert.toInt(this.getStr(key), defaultValue);
    }

    @Override
    public Integer getInt(String key) {
        return this.getInt((String)key, (Integer)null);
    }

    @Override
    public Boolean getBool(String key, Boolean defaultValue) {
        return Convert.toBool(this.getStr(key), defaultValue);
    }

    @Override
    public Boolean getBool(String key) {
        return this.getBool((String)key, (Boolean)null);
    }

    @Override
    public Long getLong(String key, Long defaultValue) {
        return Convert.toLong(this.getStr(key), defaultValue);
    }

    @Override
    public Long getLong(String key) {
        return this.getLong((String)key, (Long)null);
    }

    @Override
    public Character getChar(String key, Character defaultValue) {
        String value = this.getStr(key);
        return StringUtil.isBlank(value) ? defaultValue : value.charAt(0);
    }

    @Override
    public Character getChar(String key) {
        return this.getChar((String)key, (Character)null);
    }

    @Override
    public Float getFloat(String key) {
        return this.getFloat((String)key, (Float)null);
    }

    @Override
    public Float getFloat(String key, Float defaultValue) {
        return Convert.toFloat(this.getStr(key), defaultValue);
    }

    @Override
    public Double getDouble(String key, Double defaultValue) throws NumberFormatException {
        return Convert.toDouble(this.getStr(key), defaultValue);
    }

    @Override
    public Double getDouble(String key) throws NumberFormatException {
        return this.getDouble((String)key, (Double)null);
    }

    @Override
    public Short getShort(String key, Short defaultValue) {
        return Convert.toShort(this.getStr(key), defaultValue);
    }

    @Override
    public Short getShort(String key) {
        return this.getShort((String)key, null);
    }

    @Override
    public Byte getByte(String key, Byte defaultValue) {
        return Convert.toByte(this.getStr(key), defaultValue);
    }

    @Override
    public Byte getByte(String key) {
        return this.getByte((String)key, null);
    }

    @Override
    public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        String valueStr = this.getStr(key);
        if (StringUtil.isBlank(valueStr)) {
            return defaultValue;
        } else {
            try {
                return new BigDecimal(valueStr);
            } catch (Exception var5) {
                return defaultValue;
            }
        }
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return this.getBigDecimal((String)key, (BigDecimal)null);
    }

    @Override
    public BigInteger getBigInteger(String key, BigInteger defaultValue) {
        String valueStr = this.getStr(key);
        if (StringUtil.isBlank(valueStr)) {
            return defaultValue;
        } else {
            try {
                return new BigInteger(valueStr);
            } catch (Exception var5) {
                return defaultValue;
            }
        }
    }

    @Override
    public BigInteger getBigInteger(String key) {
        return this.getBigInteger((String)key, (BigInteger)null);
    }

    @Override
    public <E extends Enum<E>> E getEnum(Class<E> clazz, String key, E defaultValue) {
        return Convert.toEnum(clazz, this.getStr(key), defaultValue);
    }

    @Override
    public Date getDate(String s, Date date) {
        return null;
    }

    @Override
    public <E extends Enum<E>> E getEnum(Class<E> clazz, String key) {
        return this.getEnum(clazz,key, null);
    }

    @Override
    public Date getDate(String s) {
        return null;
    }

    public void setProperty(String key, Object value) {
        super.setProperty(key, value.toString());
    }

    public void store(String absolutePath) {
        try {
            FileUtil.touch(absolutePath);
            super.store(FileUtil.getOutputStream(absolutePath), (String)null);
        } catch (FileNotFoundException var3) {
            ;
        } catch (IOException var4) {
            throw new SystemException("Store properties to [{}] error!", var4);
        }

    }

    public void store(String path, Class<?> clazz) {
        this.store(FileUtil.getAbsolutePath(path, clazz));
    }
}
