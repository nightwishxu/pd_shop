package com.base.util.http.ssl;

import cn.hutool.core.util.ArrayUtil;
import com.base.util.StringUtil;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SSLSocketFactoryBuilder {
    public static final String SSL = "SSL";
    public static final String SSLv2 = "SSLv2";
    public static final String SSLv3 = "SSLv3";
    public static final String TLS = "TLS";
    public static final String TLSv1 = "TLSv1";
    public static final String TLSv11 = "TLSv1.1";
    public static final String TLSv12 = "TLSv1.2";
    private String protocol = "TLS";
    private KeyManager[] keyManagers;
    private TrustManager[] trustManagers = new TrustManager[]{new DefaultTrustManager()};
    private SecureRandom secureRandom = new SecureRandom();

    public SSLSocketFactoryBuilder() {
    }

    public static SSLSocketFactoryBuilder create() {
        return new SSLSocketFactoryBuilder();
    }

    public SSLSocketFactoryBuilder setProtocol(String protocol) {
        if (StringUtil.isNotBlank(protocol)) {
            this.protocol = protocol;
        }

        return this;
    }

    public SSLSocketFactoryBuilder setTrustManagers(TrustManager... trustManagers) {
        if (ArrayUtil.isNotEmpty(trustManagers)) {
            this.trustManagers = trustManagers;
        }

        return this;
    }

    public SSLSocketFactoryBuilder setKeyManagers(KeyManager... keyManagers) {
        if (ArrayUtil.isNotEmpty(keyManagers)) {
            this.keyManagers = keyManagers;
        }

        return this;
    }

    public SSLSocketFactoryBuilder setSecureRandom(SecureRandom secureRandom) {
        if (null != secureRandom) {
            this.secureRandom = secureRandom;
        }

        return this;
    }

    public SSLSocketFactory build() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance(this.protocol);
        sslContext.init(this.keyManagers, this.trustManagers, this.secureRandom);
        return sslContext.getSocketFactory();
    }
}
