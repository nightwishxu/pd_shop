package com.base.util;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.*;
import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.Manifest;

public class SystemUtilsEx {
    private static Log logger = LogFactory.getLog(SystemUtilsEx.class);
    private static Boolean isInitJCE = false;
    private static String hostId;
    private static String hostIp;
    private static String hostName;
    private static Boolean hasMagickImage = null;
    public static String dockerParentHostIp;

    public SystemUtilsEx() {
    }

    public static boolean isIBM() {
        return SystemUtils.JAVA_VM_VENDOR.contains("IBM");
    }

    public static String getJCEName() {
        return isIBM() ? "IBMJCE" : "SunJCE";
    }

    public static void initJCE() {
        if (!isInitJCE) {
            Boolean var0 = isInitJCE;
            synchronized(isInitJCE) {
                if (!isInitJCE) {
                    Class providerClz = null;

                    try {
                        if (isIBM()) {
                            providerClz = Class.forName("com.ibm.crypto.provider.IBMJCE");
                        } else {
                            providerClz = Class.forName("com.sun.crypto.provider.SunJCE");
                        }

                        logger.info("系统使用的JDK:" + SystemUtils.JAVA_VM_VENDOR + ",使用JCE:" + providerClz.getName());
                    } catch (ClassNotFoundException var6) {
                        logger.error("系统使用的JDK:" + SystemUtils.JAVA_VM_VENDOR + ",无法找到对应的JCE类");
                        throw new RuntimeException("系统使用的JDK:" + SystemUtils.JAVA_VM_VENDOR + ",无法找到对应的JCE类");
                    }

                    Provider provider = null;

                    try {
                        provider = (Provider)providerClz.newInstance();
                    } catch (Exception var5) {
                        logger.error("创建对象:" + providerClz.getName() + "异常:" + var5.getMessage());
                        throw new RuntimeException("创建对象:" + providerClz.getName() + "异常:" + var5.getMessage());
                    }

                    Security.addProvider(provider);
                    isInitJCE = true;
                }
            }
        }

    }

    public static String getHostIp() {
        if (hostIp == null) {
            hostIp = "0.0.0.0";
            Enumeration netInterfaces = null;

            try {
                netInterfaces = NetworkInterface.getNetworkInterfaces();

                while(netInterfaces.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
                    Enumeration ips = ni.getInetAddresses();

                    while(ips.hasMoreElements()) {
                        String ip = ((InetAddress)ips.nextElement()).getHostAddress();
                        if (!"127.0.0.1".equals(ip) && ip.length() <= 15 && ip.split("\\.").length == 4) {
                            hostIp = ip;
                            return hostIp;
                        }
                    }
                }
            } catch (SocketException var4) {
                logger.error(var4.getMessage(), var4);
            }
        }

        return hostIp;
    }

//    public static String getHostId() {
//        if (hostId == null) {
//            hostId = getHostIp() + "#" + StringUtilsEx.getUUID();
//        }
//
//        return hostId;
//    }

    public static String getHostName() {
        if (hostName == null) {
            hostName = "";

            try {
                InetAddress addr = InetAddress.getLocalHost();
                hostName = addr.getHostName();
            } catch (UnknownHostException var2) {
                logger.error(var2.getMessage(), var2);
            }
        }

        return hostName;
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public static String getClientIpSingle(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null) {
            String[] ipArray = ip.trim().split(",");
            ip = ipArray[ipArray.length - 1];
        }

        return ip;
    }

    public static String getOSName() {
        return SystemUtils.OS_NAME;
    }

    public static boolean hasMagickImage() {
        return hasMagickImage;
    }

    public static String getMainestInfoInJar(Class<?> clz, String attrType, String attrName, String attr) {
        Attributes attributes = null;

        try {
            Enumeration resources = clz.getClassLoader().getResources("META-INF/MANIFEST.MF");

            while(resources.hasMoreElements()) {
                try {
                    attributes = (new Manifest(((URL)resources.nextElement()).openStream())).getMainAttributes();
                    if (attrName.equals(attributes.get(new Name(attrType)))) {
                        return attributes.get(new Name(attr)).toString();
                    }
                } catch (IOException var7) {
                    ;
                }
            }
        } catch (Exception var8) {
            ;
        }

        return null;
    }

    public static String getDockerParentHostIp(String startsWith) {
        if (dockerParentHostIp == null) {
            try {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();

                while(true) {
                    while(networkInterfaces.hasMoreElements()) {
                        NetworkInterface networkInterface = (NetworkInterface)networkInterfaces.nextElement();
                        Enumeration inetAddresses = networkInterface.getInetAddresses();

                        while(inetAddresses.hasMoreElements()) {
                            InetAddress inetAddress = (InetAddress)inetAddresses.nextElement();
                            if (inetAddress != null && inetAddress instanceof Inet4Address) {
                                String ip = inetAddress.getHostAddress();
                                if (ip.startsWith(startsWith)) {
                                    dockerParentHostIp = ip;
                                    break;
                                }
                            }
                        }
                    }

                    return dockerParentHostIp;
                }
            } catch (SocketException var6) {
                logger.error("获取docker宿主机ip异常", var6);
            }
        }

        return dockerParentHostIp;
    }

    public static void main(String[] args) {
        System.out.println(getDockerParentHostIp(""));
    }

    static {
        hasMagickImage = false;

        try {
            System.setProperty("jmagick.systemclassloader", "no");
            System.loadLibrary("JMagick");
            hasMagickImage = true;
            logger.info("系统已安装图形处理MagickImage...");
        } catch (Throwable var1) {
            logger.info("系统未安装图形处理MagickImage,会使用默认图形处理,Linux下压缩图片可能会变红..." + var1.getMessage());
        }

    }
}
