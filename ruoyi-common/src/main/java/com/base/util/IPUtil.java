package com.base.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class IPUtil {
    private static final String MAC_ADDRESS_PREFIX = "MAC Address = ";
    private static final String LOOPBACK_ADDRESS = "127.0.0.1";

    public IPUtil() {
    }

//    public static String getClientIP(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//
//        return ip;
//    }


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     */
    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        String unknown = "unknown";
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getClientMacAddress(HttpServletRequest request) {
        try {
            return getMACAddress(getClientIP(request));
        } catch (Exception var2) {
            return null;
        }
    }

    public static String getMACAddress(String ip) throws Exception {
        String line = "";
        String macAddress = "";
        int index;
        if ("127.0.0.1".equals(ip)) {
            InetAddress inetAddress = InetAddress.getLocalHost();
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            StringBuilder sb = new StringBuilder();

            for(index = 0; index < mac.length; ++index) {
                if (index != 0) {
                    sb.append("-");
                }

                String s = Integer.toHexString(mac[index] & 255);
                sb.append(s.length() == 1 ? 0 + s : s);
            }

            macAddress = sb.toString().trim().toUpperCase();
            return macAddress;
        } else {
            try {
                Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
                InputStreamReader isr = new InputStreamReader(p.getInputStream());
                BufferedReader br = new BufferedReader(isr);

                while((line = br.readLine()) != null) {
                    if (line != null) {
                        index = line.indexOf("MAC Address = ");
                        if (index != -1) {
                            macAddress = line.substring(index + "MAC Address = ".length()).trim().toUpperCase();
                        }
                    }
                }

                br.close();
            } catch (IOException var8) {
                var8.printStackTrace(System.out);
            }

            return macAddress;
        }
    }

    public static long ipToLong(String strIP) {
        long[] ip = new long[4];
        int position1 = strIP.indexOf(".");
        int position2 = strIP.indexOf(".", position1 + 1);
        int position3 = strIP.indexOf(".", position2 + 1);
        ip[0] = Long.parseLong(strIP.substring(0, position1));
        ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIP.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    public static boolean isLocal(String strIp) {
        if ("127.0.0.1".equals(strIp)) {
            return true;
        } else {
            long l = ipToLong(strIp);
            if (l >= 3232235520L) {
                return l <= 3232301055L;
            } else {
                return l >= 167772160L && l <= 184549375L;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("202.102.249.59:" + Long.toString(ipToLong("202.102.249.59")));
        System.out.println("202.102.249.59 is local ip:" + Boolean.toString(isLocal("202.102.249.59")));
        System.out.println("127.0.0.1:" + Long.toString(ipToLong("127.0.0.1")));
        System.out.println("127.0.0.1 is local ip:" + Boolean.toString(isLocal("127.0.0.1")));
        System.out.println("10.9.1.134:" + Long.toString(ipToLong("10.9.1.134")));
        System.out.println("192.168.1.183 is local ip:" + Boolean.toString(isLocal("192.168.1.183")));
    }
}
