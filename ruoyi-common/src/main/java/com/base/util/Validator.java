package com.base.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import com.base.exception.ValidateException;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public final class Validator {
    public static final Pattern GENERAL = Pattern.compile("^\\w+$");
    public static final Pattern NUMBERS = Pattern.compile("\\d+");
    public static final Pattern GROUP_VAR = Pattern.compile("\\$(\\d+)");
    public static final Pattern IPV4 = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
    public static final Pattern MONEY = Pattern.compile("^(\\d+(?:\\.\\d+)?)$");
    public static final Pattern EMAIL = Pattern.compile("(\\w|.)+@\\w+(\\.\\w+){1,2}");
    public static final Pattern MOBILE = Pattern.compile("1\\d{10}");
    public static final Pattern CITIZEN_ID = Pattern.compile("[1-9]\\d{5}[1-2]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}(\\d|X|x)");
    public static final Pattern ZIP_CODE = Pattern.compile("\\d{6}");
    public static final Pattern BIRTHDAY = Pattern.compile("(\\d{4})(/|-|\\.)(\\d{1,2})(/|-|\\.)(\\d{1,2})日?$");
    public static final Pattern URL = Pattern.compile("(https://|http://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
    public static final Pattern GENERAL_WITH_CHINESE = Pattern.compile("^[\\u0391-\\uFFE5\\w]+$");
    public static final Pattern UUID = Pattern.compile("^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$");
    public static final Pattern UUID_SIMPLE = Pattern.compile("^[0-9a-z]{32}$");

    private Validator() {
    }

    public static <T> boolean isEmpty(T value) {
        return null == value || value instanceof String && StringUtil.isEmpty((String)value);
    }

    public static <T> boolean isNotEmpty(T value) {
        return !isEmpty(value);
    }

    public static <T> void validateNotEmpty(T value, String errorMsg) throws ValidateException {
        if (isEmpty(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean equal(Object t1, Object t2) {
        return ObjectUtil.equal(t1, t2);
    }

    public static <T> void validateEqual(Object t1, Object t2, String errorMsg) throws ValidateException {
        if (!equal(t1, t2)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static <T> void validateNotEqual(Object t1, Object t2, String errorMsg) throws ValidateException {
        if (equal(t1, t2)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static <T> void validateNotEmptyAndEqual(Object t1, Object t2, String errorMsg) throws ValidateException {
        validateNotEmpty(t1, errorMsg);
        validateEqual(t1, t2, errorMsg);
    }

    public static <T> void validateNotEmptyAndNotEqual(Object t1, Object t2, String errorMsg) throws ValidateException {
        validateNotEmpty(t1, errorMsg);
        validateNotEqual(t1, t2, errorMsg);
    }

    public static boolean isMactchRegex(String regex, String value) {
        return ReUtil.isMatch(regex, value);
    }

    public static void validateMatchRegex(String regex, String value, String errorMsg) throws ValidateException {
        if (!isMactchRegex(regex, value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isMactchRegex(Pattern pattern, String value) {
        return ReUtil.isMatch(pattern, value);
    }

    public static boolean isGeneral(String value) {
        return isMactchRegex(GENERAL, value);
    }

    public static void validateGeneral(String value, String errorMsg) throws ValidateException {
        if (!isGeneral(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isGeneral(String value, int min, int max) {
        String reg = "^\\w{" + min + "," + max + "}$";
        if (min < 0) {
            min = 0;
        }

        if (max <= 0) {
            reg = "^\\w{" + min + ",}$";
        }

        return isMactchRegex(reg, value);
    }

    public static void validateGeneral(String value, int min, int max, String errorMsg) throws ValidateException {
        if (!isGeneral(value, min, max)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isGeneral(String value, int min) {
        return isGeneral(value, min, 0);
    }

    public static void validateGeneral(String value, int min, String errorMsg) throws ValidateException {
        validateGeneral(value, min, 0, errorMsg);
    }

    public static boolean isNumber(String value) {
        return StringUtil.isBlank(value) ? false : isMactchRegex(NUMBERS, value);
    }

    public static void validateNumbers(String value, String errorMsg) throws ValidateException {
        if (!isNumber(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isMoney(String value) {
        return isMactchRegex(MONEY, value);
    }

    public static void validateMoney(String value, String errorMsg) throws ValidateException {
        if (!isMoney(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isZipCode(String value) {
        return isMactchRegex(ZIP_CODE, value);
    }

    public static void validateZipCode(String value, String errorMsg) throws ValidateException {
        if (!isZipCode(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isEmail(String value) {
        return isMactchRegex(EMAIL, value);
    }

    public static void validateEmail(String value, String errorMsg) throws ValidateException {
        if (!isEmail(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isMobile(String value) {
        return isMactchRegex(MOBILE, value);
    }

    public static void validateMobile(String value, String errorMsg) throws ValidateException {
        if (!isMobile(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isCitizenId(String value) {
        return isMactchRegex(CITIZEN_ID, value);
    }

    public static void validateCitizenIdNumber(String value, String errorMsg) throws ValidateException {
        if (!isCitizenId(value)) {
            throw new ValidateException(errorMsg);
        }
    }

//    public static boolean isBirthday(int year, int month, int day) {
//        int thisYear = DateUtil.thisYear();
//        if (year >= 1930 && year <= thisYear) {
//            if (month >= 1 && month <= 12) {
//                if (day >= 1 && day <= 31) {
//                    if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
//                        return false;
//                    } else {
//                        return month != 2 || day <= 29 && (day != 29 || DateUtil.isLeapYear(year));
//                    }
//                } else {
//                    return false;
//                }
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

    public static boolean isBirthday(String value) {
        if (isMactchRegex(BIRTHDAY, value)) {
            int year = Integer.parseInt(value.substring(0, 4));
            int month = Integer.parseInt(value.substring(5, 7));
            int day = Integer.parseInt(value.substring(8, 10));
            if (month < 1 || month > 12) {
                return false;
            }

            if (day < 1 || day > 31) {
                return false;
            }

            if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
                return false;
            }

            if (month == 2) {
                boolean isleap = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
                if (day > 29 || day == 29 && !isleap) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void validateBirthday(String value, String errorMsg) throws ValidateException {
        if (!isBirthday(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isIpv4(String value) {
        return isMactchRegex(IPV4, value);
    }

    public static void validateIpv4(String value, String errorMsg) throws ValidateException {
        if (!isIpv4(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isUrl(String value) {
        try {
            new URL(value);
            return true;
        } catch (MalformedURLException var2) {
            return false;
        }
    }

    public static void validateUrl(String value, String errorMsg) throws ValidateException {
        if (!isUrl(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isChinese(String value) {
        return isMactchRegex("^[一-\u9fff]+$", value);
    }

    public static void validateChinese(String value, String errorMsg) throws ValidateException {
        if (!isChinese(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isGeneralWithChinese(String value) {
        return isMactchRegex(GENERAL_WITH_CHINESE, value);
    }

    public static void validateGeneralWithChinese(String value, String errorMsg) throws ValidateException {
        if (!isGeneralWithChinese(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isUUID(String value) {
        return isMactchRegex(UUID, value) || isMactchRegex(UUID_SIMPLE, value);
    }

    public static void validateUUID(String value, String errorMsg) throws ValidateException {
        if (!isUUID(value)) {
            throw new ValidateException(errorMsg);
        }
    }
}
