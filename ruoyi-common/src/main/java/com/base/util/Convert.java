package com.base.util;

import cn.hutool.core.convert.BasicType;
import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.util.ArrayUtil;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class Convert {
    private Convert() {
    }

    public static String toStr(Object value, String defaultValue) {
        return (String)convert(String.class, value, defaultValue);
    }

    public static String toStr(Object value) {
        return toStr(value, (String)null);
    }

    public static Character toChar(Object value, Character defaultValue) {
        return (Character)convert(Character.class, value, defaultValue);
    }

    public static Character toChar(Object value) {
        return toChar(value, (Character)null);
    }

    public static Byte toByte(Object value, Byte defaultValue) {
        return (Byte)convert(Byte.class, value, defaultValue);
    }

    public static Byte toByte(Object value) {
        return toByte(value, null);
    }

    public static Short toShort(Object value, Short defaultValue) {
        return (Short)convert(Short.class, value, defaultValue);
    }

    public static Short toShort(Object value) {
        return toShort(value, null);
    }

    public static Number toNumber(Object value, Number defaultValue) {
        return (Number)convert(Number.class, value, defaultValue);
    }

    public static Number toNumber(Object value) {
        return toNumber(value, (Number)null);
    }

    public static Integer toInt(Object value, Integer defaultValue) {
        return (Integer)convert(Integer.class, value, defaultValue);
    }

    public static Integer toInt(Object value) {
        return toInt(value, (Integer)null);
    }

    public static Integer[] toIntArray(boolean isIgnoreConvertError, Object... values) {
        if (ArrayUtil.isEmpty(values)) {
            return new Integer[0];
        } else {
            Integer[] ints = new Integer[values.length];

            for(int i = 0; i < values.length; ++i) {
                Integer v = toInt(values[i], (Integer)null);
                if (null == v && !isIgnoreConvertError) {
                    throw new ConvertException(StringUtil.format("Convert [{}] to Integer error!", new Object[]{values[i]}));
                }

                ints[i] = v;
            }

            return ints;
        }
    }

    public static Long toLong(Object value, Long defaultValue) {
        return (Long)convert(Long.class, value, defaultValue);
    }

    public static Long toLong(Object value) {
        return toLong(value, (Long)null);
    }

    public static Long[] toLongArray(boolean isIgnoreConvertError, Object... values) {
        if (ArrayUtil.isEmpty(values)) {
            return new Long[0];
        } else {
            Long[] longs = new Long[values.length];

            for(int i = 0; i < values.length; ++i) {
                Long v = toLong(values[i], (Long)null);
                if (null == v && !isIgnoreConvertError) {
                    throw new ConvertException(StringUtil.format("Convert [{}] to Long error!", new Object[]{values[i]}));
                }

                longs[i] = v;
            }

            return longs;
        }
    }

    public static Double toDouble(Object value, Double defaultValue) {
        return (Double)convert(Double.class, value, defaultValue);
    }

    public static Double toDouble(Object value) {
        return toDouble(value, (Double)null);
    }

    public static Double[] toDoubleArray(boolean isIgnoreConvertError, Object... values) {
        if (ArrayUtil.isEmpty(values)) {
            return new Double[0];
        } else {
            Double[] doubles = new Double[values.length];

            for(int i = 0; i < values.length; ++i) {
                Double v = toDouble(values[i], (Double)null);
                if (null == v && !isIgnoreConvertError) {
                    throw new ConvertException(StringUtil.format("Convert [{}] to Double error!", new Object[]{values[i]}));
                }

                doubles[i] = v;
            }

            return doubles;
        }
    }

    public static Float toFloat(Object value, Float defaultValue) {
        return (Float)convert(Float.class, value, defaultValue);
    }

    public static Float toFloat(Object value) {
        return toFloat(value, (Float)null);
    }

    public static <T> Float[] toFloatArray(boolean isIgnoreConvertError, Object... values) {
        if (ArrayUtil.isEmpty(values)) {
            return new Float[0];
        } else {
            Float[] floats = new Float[values.length];

            for(int i = 0; i < values.length; ++i) {
                Float v = toFloat(values[i], (Float)null);
                if (null == v && !isIgnoreConvertError) {
                    throw new ConvertException(StringUtil.format("Convert [{}] to Float error!", new Object[]{values[i]}));
                }

                floats[i] = v;
            }

            return floats;
        }
    }

    public static Boolean toBool(Object value, Boolean defaultValue) {
        return (Boolean)convert(Boolean.class, value, defaultValue);
    }

    public static Boolean toBool(Object value) {
        return toBool(value, (Boolean)null);
    }

    public static Boolean[] toBooleanArray(boolean isIgnoreConvertError, Object... values) {
        if (ArrayUtil.isEmpty(values)) {
            return new Boolean[0];
        } else {
            Boolean[] bools = new Boolean[values.length];

            for(int i = 0; i < values.length; ++i) {
                Boolean v = toBool(values[i], (Boolean)null);
                if (null == v && !isIgnoreConvertError) {
                    throw new ConvertException(StringUtil.format("Convert [{}] to Boolean error!", new Object[]{values[i]}));
                }

                bools[i] = v;
            }

            return bools;
        }
    }

    public static BigInteger toBigInteger(Object value, BigInteger defaultValue) {
        return (BigInteger)convert(BigInteger.class, defaultValue, defaultValue);
    }

    public static BigInteger toBigInteger(Object value) {
        return toBigInteger(value, (BigInteger)null);
    }

    public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {
        return (BigDecimal)convert(BigDecimal.class, defaultValue, defaultValue);
    }

    public static BigDecimal toBigDecimal(Object value) {
        return toBigDecimal(value, (BigDecimal)null);
    }

    public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value, E defaultValue) {
        if (value == null) {
            return defaultValue;
        } else if (clazz.isAssignableFrom(value.getClass())) {
            E myE = (E)value;
            return myE;
        } else {
            String valueStr = toStr(value, (String)null);
            if (StringUtil.isBlank(valueStr)) {
                return defaultValue;
            } else {
                try {
                    return Enum.valueOf(clazz, valueStr);
                } catch (Exception var5) {
                    return defaultValue;
                }
            }
        }
    }

    public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value) {
        return toEnum(clazz, value, null);
    }

    public static <T> T convert(Class<T> type, Object value) {
        return convert(type, value, null);
    }

    public static <T> T convert(Class<T> type, Object value, T defaultValue) throws ConvertException {
        return ConverterRegistry.getInstance().convert(type, value, defaultValue);
    }

    public static String toSBC(String input) {
        return toSBC(input, (Set)null);
    }

    public static String toSBC(String input, Set<Character> notConvertSet) {
        char[] c = input.toCharArray();

        for(int i = 0; i < c.length; ++i) {
            if (null == notConvertSet || !notConvertSet.contains(c[i])) {
                if (c[i] == ' ') {
                    c[i] = 12288;
                } else if (c[i] < 127) {
                    c[i] += 'ﻠ';
                }
            }
        }

        return new String(c);
    }

    public static String toDBC(String input) {
        return toDBC(input, (Set)null);
    }

    public static String toDBC(String text, Set<Character> notConvertSet) {
        char[] c = text.toCharArray();

        for(int i = 0; i < c.length; ++i) {
            if (null == notConvertSet || !notConvertSet.contains(c[i])) {
                if (c[i] == 12288) {
                    c[i] = ' ';
                } else if (c[i] > '\uff00' && c[i] < '｟') {
                    c[i] -= 'ﻠ';
                }
            }
        }

        String returnString = new String(c);
        return returnString;
    }

    public static String toHex(String str, Charset charset) {
        return HexUtil.encodeHexStr(str.getBytes(charset));
    }

    public static String toHex(byte[] bytes) {
        return HexUtil.encodeHexStr(bytes);
    }

    public static byte[] hexToBytes(String src) {
        return HexUtil.decodeHex(src.toCharArray());
    }

    public static String hexStrToStr(String hexStr, Charset charset) {
        return HexUtil.decodeHexStr(hexStr, charset);
    }

    public static String strToUnicode(String strText) {
        StringBuilder str = new StringBuilder();

        for(int i = 0; i < strText.length(); ++i) {
            char c = strText.charAt(i);
            String strHex = Integer.toHexString(c);
            if (c > 128) {
                str.append("\\u" + strHex);
            } else {
                str.append("\\u00" + strHex);
            }
        }

        return str.toString();
    }

    public static String unicodeToStr(String hex) {
        int t = hex.length() / 6;
        StringBuilder str = new StringBuilder();

        for(int i = 0; i < t; ++i) {
            String s = hex.substring(i * 6, (i + 1) * 6);
            String s1 = s.substring(2, 4) + "00";
            String s2 = s.substring(4);
            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
            char[] chars = Character.toChars(n);
            str.append(new String(chars));
        }

        return str.toString();
    }

    public static String convertCharset(String str, String sourceCharset, String destCharset) {
        return StringUtil.hasBlank(new CharSequence[]{str, sourceCharset, destCharset}) ? str : CharsetUtil.convert(str, sourceCharset, destCharset);
    }

    public static long convertTime(long sourceDuration, TimeUnit sourceUnit, TimeUnit destUnit) {
        Assert.notNull(sourceUnit, "sourceUnit is null !");
        Assert.notNull(destUnit, "destUnit is null !");
        return destUnit.convert(sourceDuration, sourceUnit);
    }

    public static String digitUppercase(double n) {
        String[] fraction = new String[]{"角", "分"};
        String[] digit = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[][] unit = new String[][]{{"元", "万", "亿"}, {"", "拾", "佰", "仟"}};
        String head = n < 0.0D ? "负" : "";
        n = Math.abs(n);
        String s = "";

        int integerPart;
        for(integerPart = 0; integerPart < fraction.length; ++integerPart) {
            s = s + (digit[(int)(Math.floor(n * 10.0D * Math.pow(10.0D, (double)integerPart)) % 10.0D)] + fraction[integerPart]).replaceAll("(零.)+", "");
        }

        if (s.length() < 1) {
            s = "整";
        }

        integerPart = (int)Math.floor(n);

        for(int i = 0; i < unit[0].length && integerPart > 0; ++i) {
            String p = "";

            for(int j = 0; j < unit[1].length && n > 0.0D; ++j) {
                p = digit[integerPart % 10] + unit[1][j] + p;
                integerPart /= 10;
            }

            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }

        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }

    public static Class<?> wrap(Class<?> clazz) {
        return BasicType.wrap(clazz);
    }

    public static Class<?> unWrap(Class<?> clazz) {
        return BasicType.unWrap(clazz);
    }
}
