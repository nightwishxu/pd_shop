package com.base.util.http.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class TrustAnyHostnameVerifier implements HostnameVerifier {
    public TrustAnyHostnameVerifier() {
    }

    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
