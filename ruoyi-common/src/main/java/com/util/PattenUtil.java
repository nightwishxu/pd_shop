package com.util;


import com.base.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PattenUtil {
    private static final PattenUtil INSTANCE = new PattenUtil();
    public static final String ALIAS = "[\\w_一-龥]+";

    public PattenUtil() {
    }

    public static PattenUtil getInstance() {
        return INSTANCE;
    }

    public static String match(String regex, String str) {
        if (StringUtil.isBlank(str)) {
            return null;
        } else {
            StringBuffer temp = new StringBuffer();
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str);

            while(m.find()) {
                temp.append(m.group());
            }

            return temp.toString();
        }
    }

    public static String matchFirst(String regex, String str) {
        if (StringUtil.isBlank(str)) {
            return null;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str);
            return m.find() ? m.group() : null;
        }
    }

    public static String matchAlias(String id) {
        return match("[\\w_一-龥]+", id);
    }

    public static boolean matches(String pattern, String source) {
        if (pattern != null && source != null) {
            pattern = pattern.trim();
            source = source.trim();
            int start;
            if (pattern.endsWith("*")) {
                start = pattern.length() - 1;
                if (source.length() >= start && pattern.substring(0, start).equals(source.substring(0, start))) {
                    return true;
                }
            } else if (pattern.startsWith("*")) {
                start = pattern.length() - 1;
                if (source.length() >= start && source.endsWith(pattern.substring(1))) {
                    return true;
                }
            } else if (pattern.contains("*")) {
                start = pattern.indexOf("*");
                int end = pattern.lastIndexOf("*");
                if (source.startsWith(pattern.substring(0, start)) && source.endsWith(pattern.substring(end + 1))) {
                    return true;
                }
            } else if (pattern.equals(source)) {
                return true;
            }

            return false;
        } else {
            return false;
        }
    }

    public static Boolean isMatcher(String val, String matcher) {
        Pattern p = Pattern.compile(matcher);
        Matcher m = p.matcher(val);
        return m.matches();
    }
}
