//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.util;


import cn.hutool.core.io.resource.ResourceUtil;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

public class ClientCustomSSL {
    public ClientCustomSSL() {
    }

    public static SSLContext init(String file, String mchId) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        InputStream inputStream;
        if (file.startsWith("classpath")){
            inputStream = ResourceUtil.getStream(file);
        }else{
            inputStream = new FileInputStream(new File(file));
        }
        try {
            keyStore.load(inputStream, mchId.toCharArray());
        } finally {
            inputStream.close();
        }

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, mchId.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(keyStore);
        TrustManager[] var6 = tmf.getTrustManagers();
        SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
        sslcontext.init(kmf.getKeyManagers(), (TrustManager[])null, new SecureRandom());
        return sslcontext;
    }
}
