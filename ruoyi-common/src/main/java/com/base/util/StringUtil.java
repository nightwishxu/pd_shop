package com.base.util;


import cn.hutool.core.util.ArrayUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.Character.UnicodeBlock;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static final char C_SPACE = ' ';
    public static final char C_TAB = '\t';
    public static final char C_DOT = '.';
    public static final char C_SLASH = '/';
    public static final char C_BACKSLASH = '\\';
    public static final char C_CR = '\r';
    public static final char C_LF = '\n';
    public static final char C_UNDERLINE = '_';
    public static final char C_COMMA = ',';
    public static final char C_DELIM_START = '{';
    public static final char C_DELIM_END = '}';
    public static final char C_COLON = ':';
    public static final String SPACE = " ";
    public static final String TAB = "\t";
    public static final String DOT = ".";
    public static final String DOUBLE_DOT = "..";
    public static final String SLASH = "/";
    public static final String BACKSLASH = "\\";
    public static final String EMPTY = "";
    public static final String CR = "\r";
    public static final String LF = "\n";
    public static final String CRLF = "\r\n";
    public static final String UNDERLINE = "_";
    public static final String COMMA = ",";
    public static final String DELIM_START = "{";
    public static final String DELIM_END = "}";
    public static final String COLON = ":";
    public static final String EMPTY_JSON = "{}";
    public static final String HTML_NBSP = "&nbsp;";
    public static final String HTML_AMP = "&amp";
    public static final String HTML_QUOTE = "&quot;";
    public static final String HTML_LT = "&lt;";
    public static final String HTML_GT = "&gt;";
    private static final String DEFAULT_DATE_PATTTERN = "yyyy-MM-dd";
    private static final String[] LOWERCASES = new String[]{"O", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
    private static final String[] UPPERCASES = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"};
    private static final String[] SIGN_GENERAL = new String[]{"&", "'", ">", "<", "\""};
    private static final String[] SIGN_TRANSLATE = new String[]{"&amp;", "&apos;", "&gt;", "&lt;", "&quot;"};
    private static LinkedList<Character> stack = new LinkedList();

    private StringUtil() {
    }

    public static boolean isBlank(CharSequence str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for(int i = 0; i < length; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    public static boolean hasBlank(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return true;
        } else {
            CharSequence[] var1 = strs;
            int var2 = strs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence str = var1[var3];
                if (isBlank(str)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isAllBlank(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return true;
        } else {
            CharSequence[] var1 = strs;
            int var2 = strs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence str = var1[var3];
                if (isNotBlank(str)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    public static String nullToEmpty(String str) {
        return nullToDefault(str, "");
    }

    public static String nullToDefault(String str, String defaultStr) {
        return str == null ? defaultStr : str;
    }

    public static String emptyToNull(String str) {
        return isEmpty(str) ? null : str;
    }

    public static boolean hasEmpty(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return true;
        } else {
            CharSequence[] var1 = strs;
            int var2 = strs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence str = var1[var3];
                if (isEmpty(str)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isAllEmpty(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return true;
        } else {
            CharSequence[] var1 = strs;
            int var2 = strs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence str = var1[var3];
                if (isNotEmpty(str)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String trim(String str) {
        return null == str ? null : trim(str, 0);
    }

    public static void trim(String[] strs) {
        if (null != strs) {
            for(int i = 0; i < strs.length; ++i) {
                String str = strs[i];
                if (null != str) {
                    strs[i] = str.trim();
                }
            }

        }
    }

    public static String trimStart(String str) {
        return trim(str, -1);
    }

    public static String trimEnd(String str) {
        return trim(str, 1);
    }

    public static String trim(String str, int mode) {
        if (str == null) {
            return null;
        } else {
            int length = str.length();
            int start = 0;
            int end = length;
            if (mode <= 0) {
                while(start < end && Character.isWhitespace(str.charAt(start))) {
                    ++start;
                }
            }

            if (mode >= 0) {
                while(start < end && Character.isWhitespace(str.charAt(end - 1))) {
                    --end;
                }
            }

            return start <= 0 && end >= length ? str : str.substring(start, end);
        }
    }

    public static boolean startWith(String str, String prefix, boolean isIgnoreCase) {
        return isIgnoreCase ? str.toLowerCase().startsWith(prefix.toLowerCase()) : str.startsWith(prefix);
    }

    public static boolean startWithIgnoreCase(String str, String prefix) {
        return startWith(str, prefix, true);
    }

    public static boolean endWith(String str, String suffix, boolean isIgnoreCase) {
        return isIgnoreCase ? str.toLowerCase().endsWith(suffix.toLowerCase()) : str.endsWith(suffix);
    }

    public static boolean endWithIgnoreCase(String str, String suffix) {
        return endWith(str, suffix, true);
    }

    public static boolean endWithAny(String str, String... suffix) {
        if (ArrayUtil.isEmpty(suffix)) {
            return true;
        } else {
            String[] var2 = suffix;
            int var3 = suffix.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String s = var2[var4];
                if (endWith(str, s, true)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean containsIgnoreCase(String str, String testStr) {
        if (null == str) {
            return null == testStr;
        } else {
            return str.toLowerCase().contains(testStr.toLowerCase());
        }
    }

    public static String getGeneralField(String getOrSetMethodName) {
        return !getOrSetMethodName.startsWith("get") && !getOrSetMethodName.startsWith("set") ? null : removePreAndLowerFirst(getOrSetMethodName, 3);
    }

    public static String genSetter(String fieldName) {
        return upperFirstAndAddPre(fieldName, "set");
    }

    public static String genGetter(String fieldName) {
        return upperFirstAndAddPre(fieldName, "get");
    }

    public static String removeAll(String str, CharSequence strToRemove) {
        return str.replace(strToRemove, "");
    }

    public static String removePreAndLowerFirst(String str, int preLength) {
        if (str == null) {
            return null;
        } else if (str.length() > preLength) {
            char first = Character.toLowerCase(str.charAt(preLength));
            return str.length() > preLength + 1 ? first + str.substring(preLength + 1) : String.valueOf(first);
        } else {
            return str;
        }
    }

    public static String removePreAndLowerFirst(String str, String prefix) {
        return lowerFirst(removePrefix(str, prefix));
    }

    public static String upperFirstAndAddPre(String str, String preString) {
        return str != null && preString != null ? preString + upperFirst(str) : null;
    }

    public static String upperFirst(String str) {
        return isBlank(str) ? str : Character.toUpperCase(str.charAt(0)) + subSuf(str, 1);
    }

    public static String lowerFirst(String str) {
        return isBlank(str) ? str : Character.toLowerCase(str.charAt(0)) + subSuf(str, 1);
    }

    public static String removePrefix(String str, String prefix) {
        if (!isEmpty(str) && !isEmpty(prefix)) {
            return str.startsWith(prefix) ? subSuf(str, prefix.length()) : str;
        } else {
            return str;
        }
    }

    public static String removePrefixIgnoreCase(String str, String prefix) {
        if (!isEmpty(str) && !isEmpty(prefix)) {
            return str.toLowerCase().startsWith(prefix.toLowerCase()) ? subSuf(str, prefix.length()) : str;
        } else {
            return str;
        }
    }

    public static String removeSuffix(String str, String suffix) {
        if (!isEmpty(str) && !isEmpty(suffix)) {
            return str.endsWith(suffix) ? subPre(str, str.length() - suffix.length()) : str;
        } else {
            return str;
        }
    }

    public static String removeSufAndLowerFirst(String str, String suffix) {
        return lowerFirst(removeSuffix(str, suffix));
    }

    public static String removeSuffixIgnoreCase(String str, String suffix) {
        if (!isEmpty(str) && !isEmpty(suffix)) {
            return str.toLowerCase().endsWith(suffix.toLowerCase()) ? subPre(str, str.length() - suffix.length()) : str;
        } else {
            return str;
        }
    }

    public static String addPrefixIfNot(String str, String prefix) {
        if (!isEmpty(str) && !isEmpty(prefix)) {
            if (!str.startsWith(prefix)) {
                str = prefix + str;
            }

            return str;
        } else {
            return str;
        }
    }

    public static String addSuffixIfNot(String str, String suffix) {
        if (!isEmpty(str) && !isEmpty(suffix)) {
            if (!str.endsWith(suffix)) {
                str = str + suffix;
            }

            return str;
        } else {
            return str;
        }
    }

    public static String cleanBlank(String str) {
        if (str == null) {
            return null;
        } else {
            int len = str.length();
            StringBuilder sb = new StringBuilder(str.length());

            for(int i = 0; i < len; ++i) {
                char c = str.charAt(i);
                if (!Character.isWhitespace(c)) {
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }

    public static String[] splitToArray(String str, char separator) {
        List<String> result = split(str, separator);
        return (String[])result.toArray(new String[result.size()]);
    }

    public static List<String> split(String str, char separator) {
        return split(str, separator, 0);
    }

    public static String[] splitToArray(String str, char separator, int limit) {
        List<String> result = split(str, separator, limit);
        return (String[])result.toArray(new String[result.size()]);
    }

    public static List<String> split(String str, char separator, int limit) {
        if (str == null) {
            return null;
        } else {
            List<String> list = new ArrayList(limit == 0 ? 16 : limit);
            if (limit == 1) {
                list.add(str);
                return list;
            } else {
                boolean isNotEnd = true;
                int strLen = str.length();
                StringBuilder sb = new StringBuilder(strLen);

                for(int i = 0; i < strLen; ++i) {
                    char c = str.charAt(i);
                    if (isNotEnd && c == separator) {
                        list.add(sb.toString());
                        sb.delete(0, sb.length());
                        if (limit != 0 && list.size() == limit - 1) {
                            isNotEnd = false;
                        }
                    } else {
                        sb.append(c);
                    }
                }

                list.add(sb.toString());
                return list;
            }
        }
    }

    public static String[] split(String str, String delimiter) {
        if (str == null) {
            return null;
        } else if (str.trim().length() == 0) {
            return new String[]{str};
        } else {
            int dellen = delimiter.length();
            int maxparts = str.length() / dellen + 2;
            int[] positions = new int[maxparts];
            int j = 0;
            int count = 0;

            int i;
            for(positions[0] = -dellen; (i = str.indexOf(delimiter, j)) != -1; j = i + dellen) {
                ++count;
                positions[count] = i;
            }

            ++count;
            positions[count] = str.length();
            String[] result = new String[count];

            for(i = 0; i < count; ++i) {
                result[i] = str.substring(positions[i] + dellen, positions[i + 1]);
            }

            return result;
        }
    }

    public static String[] split(String str, int len) {
        int partCount = str.length() / len;
        int lastPartCount = str.length() % len;
        int fixPart = 0;
        if (lastPartCount != 0) {
            fixPart = 1;
        }

        String[] strs = new String[partCount + fixPart];

        for(int i = 0; i < partCount + fixPart; ++i) {
            if (i == partCount + fixPart - 1 && lastPartCount != 0) {
                strs[i] = str.substring(i * len, i * len + lastPartCount);
            } else {
                strs[i] = str.substring(i * len, i * len + len);
            }
        }

        return strs;
    }

    public static String sub(String string, int fromIndex, int toIndex) {
        int len = string.length();
        if (fromIndex < 0) {
            fromIndex += len;
            if (fromIndex < 0) {
                fromIndex = 0;
            }
        } else if (fromIndex > len) {
            fromIndex = len;
        }

        if (toIndex < 0) {
            toIndex += len;
            if (toIndex < 0) {
                toIndex = len;
            }
        } else if (toIndex > len) {
            toIndex = len;
        }

        if (toIndex < fromIndex) {
            int tmp = fromIndex;
            fromIndex = toIndex;
            toIndex = tmp;
        }

        return fromIndex == toIndex ? "" : string.substring(fromIndex, toIndex);
    }

    public static String subPre(String string, int toIndex) {
        return sub(string, 0, toIndex);
    }

    public static String subSuf(String string, int fromIndex) {
        return isEmpty(string) ? null : sub(string, fromIndex, string.length());
    }

    public static boolean isSurround(String str, String prefix, String suffix) {
        if (isBlank(str)) {
            return false;
        } else if (str.length() < prefix.length() + suffix.length()) {
            return false;
        } else {
            return str.startsWith(prefix) && str.endsWith(suffix);
        }
    }

    public static boolean isSurround(CharSequence str, char prefix, char suffix) {
        if (isBlank(str)) {
            return false;
        } else if (str.length() < 2) {
            return false;
        } else {
            return str.charAt(0) == prefix && str.charAt(str.length() - 1) == suffix;
        }
    }

    public static String repeat(char c, int count) {
        char[] result = new char[count];

        for(int i = 0; i < count; ++i) {
            result[i] = c;
        }

        return new String(result);
    }

    public static String repeat(String str, int count) {
        int len = str.length();
        long longSize = (long)len * (long)count;
        int size = (int)longSize;
        if ((long)size != longSize) {
            throw new ArrayIndexOutOfBoundsException("Required String length is too large: " + longSize);
        } else {
            char[] array = new char[size];
            str.getChars(0, len, array, 0);

            int n;
            for(n = len; n < size - n; n <<= 1) {
                System.arraycopy(array, 0, array, n, n);
            }

            System.arraycopy(array, 0, array, n, size - n);
            return new String(array);
        }
    }

    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        } else {
            return str1.equals(str2);
        }
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        } else {
            return str1.equalsIgnoreCase(str2);
        }
    }

    public static String format(String template, Object... params) {
        return !ArrayUtil.isEmpty(params) && !isBlank(template) ? StringFormatter.format(template, params) : template;
    }

    public static String indexedFormat(String pattern, Object... arguments) {
        return MessageFormat.format(pattern, arguments);
    }

    public static String format(String template, Map<?, ?> map) {
        if (null != map && !map.isEmpty()) {
            Entry entry;
            for(Iterator var2 = map.entrySet().iterator(); var2.hasNext(); template = template.replace("{" + entry.getKey() + "}", utf8Str(entry.getValue()))) {
                entry = (Entry)var2.next();
            }

            return template;
        } else {
            return template;
        }
    }

    public static byte[] utf8Bytes(String str) {
        return bytes(str, CharsetUtil.CHARSET_UTF_8);
    }

    public static byte[] bytes(String str) {
        return bytes(str, Charset.defaultCharset());
    }

    public static byte[] bytes(String str, String charset) {
        return bytes(str, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    public static byte[] bytes(String str, Charset charset) {
        if (str == null) {
            return null;
        } else {
            return null == charset ? str.getBytes() : str.getBytes(charset);
        }
    }

    public static String utf8Str(Object obj) {
        return str(obj, CharsetUtil.CHARSET_UTF_8);
    }

    public static String str(Object obj, String charsetName) {
        return str(obj, Charset.forName(charsetName));
    }

    public static String str(Object obj, Charset charset) {
        if (null == obj) {
            return null;
        } else if (obj instanceof String) {
            return (String)obj;
        } else if (obj instanceof byte[]) {
            return str((byte[])((byte[])obj), charset);
        } else if (obj instanceof Byte[]) {
            return str((Byte[])((Byte[])obj), charset);
        } else if (obj instanceof ByteBuffer) {
            return str((ByteBuffer)obj, charset);
        } else {
            return ArrayUtil.isArray(obj) ? ArrayUtil.toString(obj) : obj.toString();
        }
    }

    public static String str(byte[] bytes, String charset) {
        return str(bytes, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    public static String str(byte[] data, Charset charset) {
        if (data == null) {
            return null;
        } else {
            return null == charset ? new String(data) : new String(data, charset);
        }
    }

    public static String str(Byte[] bytes, String charset) {
        return str(bytes, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    public static String str(Byte[] data, Charset charset) {
        if (data == null) {
            return null;
        } else {
            byte[] bytes = new byte[data.length];

            for(int i = 0; i < data.length; ++i) {
                Byte dataByte = data[i];
                bytes[i] = null == dataByte ? -1 : dataByte;
            }

            return str(bytes, charset);
        }
    }

    public static String str(ByteBuffer data, String charset) {
        return data == null ? null : str(data, Charset.forName(charset));
    }

    public static String str(ByteBuffer data, Charset charset) {
        if (null == charset) {
            charset = Charset.defaultCharset();
        }

        return charset.decode(data).toString();
    }

    public static ByteBuffer byteBuffer(String str, String charset) {
        return ByteBuffer.wrap(bytes(str, charset));
    }

    public static String join(String conjunction, Object... objs) {
        return ArrayUtil.join(objs, conjunction);
    }

    public static String toUnderlineCase(String camelCaseStr) {
        if (camelCaseStr == null) {
            return null;
        } else {
            int length = camelCaseStr.length();
            StringBuilder sb = new StringBuilder();
            boolean isPreUpperCase = false;

            for(int i = 0; i < length; ++i) {
                char c = camelCaseStr.charAt(i);
                boolean isNextUpperCase = true;
                if (i < length - 1) {
                    isNextUpperCase = Character.isUpperCase(camelCaseStr.charAt(i + 1));
                }

                if (!Character.isUpperCase(c)) {
                    isPreUpperCase = false;
                } else {
                    if ((!isPreUpperCase || !isNextUpperCase) && i > 0) {
                        sb.append("_");
                    }

                    isPreUpperCase = true;
                }

                sb.append(Character.toLowerCase(c));
            }

            return sb.toString();
        }
    }

    public static String toCamelCase(String name) {
        if (name == null) {
            return null;
        } else if (name.contains("_")) {
            name = name.toLowerCase();
            StringBuilder sb = new StringBuilder(name.length());
            boolean upperCase = false;

            for(int i = 0; i < name.length(); ++i) {
                char c = name.charAt(i);
                if (c == '_') {
                    upperCase = true;
                } else if (upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return name;
        }
    }

    public static String wrap(String str, String prefix, String suffix) {
        return format("{}{}{}", prefix, str, suffix);
    }

    public static boolean isWrap(String str, String prefix, String suffix) {
        return str.startsWith(prefix) && str.endsWith(suffix);
    }

    public static boolean isWrap(String str, String wrapper) {
        return isWrap(str, wrapper, wrapper);
    }

    public static boolean isWrap(String str, char wrapper) {
        return isWrap(str, wrapper, wrapper);
    }

    public static boolean isWrap(String str, char prefixChar, char suffixChar) {
        return str.charAt(0) == prefixChar && str.charAt(str.length() - 1) == suffixChar;
    }

    public static String padPre(String str, int minLength, char padChar) {
        if (str.length() >= minLength) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(minLength);

            for(int i = str.length(); i < minLength; ++i) {
                sb.append(padChar);
            }

            sb.append(str);
            return sb.toString();
        }
    }

    public static String padEnd(String str, int minLength, char padChar) {
        if (str.length() >= minLength) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(minLength);
            sb.append(str);

            for(int i = str.length(); i < minLength; ++i) {
                sb.append(padChar);
            }

            return sb.toString();
        }
    }

    public static StringBuilder builder() {
        return new StringBuilder();
    }

    public static StringBuilder builder(int capacity) {
        return new StringBuilder(capacity);
    }

    public static StringBuilder builder(String... strs) {
        StringBuilder sb = new StringBuilder();
        String[] var2 = strs;
        int var3 = strs.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String str = var2[var4];
            sb.append(str);
        }

        return sb;
    }

    public static StringReader getReader(String str) {
        return new StringReader(str);
    }

    public static StringWriter getWriter() {
        return new StringWriter();
    }

    public static int count(String content, String strForSearch) {
        if (!hasEmpty(content, strForSearch) && strForSearch.length() <= content.length()) {
            int count = 0;

            for(int idx = 0; (idx = content.indexOf(strForSearch, idx)) > -1; idx += strForSearch.length()) {
                ++count;
            }

            return count;
        } else {
            return 0;
        }
    }

    public static int count(String content, char charForSearch) {
        int count = 0;
        if (isEmpty(content)) {
            return 0;
        } else {
            int contentLength = content.length();

            for(int i = 0; i < contentLength; ++i) {
                if (charForSearch == content.charAt(i)) {
                    ++count;
                }
            }

            return count;
        }
    }

    public static String emptyIfBlank(String s) {
        return isEmpty(s) ? "" : s;
    }

    public static boolean compare(String a, String b) {
        return a == null ? b == null : a.equals(b);
    }

    public static boolean compareIgnoreCase(String a, String b) {
        return a == null ? b == null : a.equalsIgnoreCase(b);
    }

    public static String copy(String src, int len) {
        if (src == null) {
            return null;
        } else if (src.length() > len) {
            return len <= 0 ? null : src.substring(0, len);
        } else {
            return src;
        }
    }

    public static String delete(String src, String delStr) {
        if (!isEmpty(src) && !isEmpty(delStr)) {
            StringBuffer buffer = new StringBuffer(src);

            for(int index = src.length(); (index = src.lastIndexOf(delStr, index)) >= 0; index -= delStr.length()) {
                buffer.delete(index, index + delStr.length());
            }

            return buffer.toString();
        } else {
            return src;
        }
    }

    public static String insert(String src, String anotherStr, int offset) {
        if (!isEmpty(src) && !isEmpty(anotherStr)) {
            StringBuffer buffer = new StringBuffer(src);
            if (offset >= 0 && offset <= src.length()) {
                buffer.insert(offset, anotherStr);
            }

            return buffer.toString();
        } else {
            return src;
        }
    }

    public static String append(String src, String appendStr) {
        if (!isEmpty(src) && !isEmpty(appendStr)) {
            StringBuffer buffer = new StringBuffer(src);
            buffer.append(appendStr);
            return buffer.toString();
        } else {
            return src;
        }
    }

    public static String replace(String src, String oldStr, String newStr, boolean isCaseSensitive) {
        if (!isEmpty(src) && !isEmpty(oldStr) && newStr != null) {
            String s = isCaseSensitive ? src : src.toLowerCase();
            String o = isCaseSensitive ? oldStr : oldStr.toLowerCase();
            StringBuffer buffer = new StringBuffer(src);

            for(int index = s.length(); (index = s.lastIndexOf(o, index)) >= 0; index -= o.length()) {
                buffer.replace(index, index + o.length(), newStr);
            }

            return buffer.toString();
        } else {
            return src;
        }
    }

    public static String replace(String src, String oldStr, String newStr, boolean isCaseSensitive, int index) {
        if (src != null && src.length() != 0 && oldStr != null && oldStr.length() != 0 && index > 0) {
            if (newStr == null) {
                newStr = "";
            }

            String s = isCaseSensitive ? src : src.toLowerCase();
            String old = isCaseSensitive ? oldStr : oldStr.toLowerCase();
            StringBuffer buffer = new StringBuffer(src);
            int length = old.length();

            int pos;
            for(pos = s.indexOf(old, 0); pos >= 0; pos = s.indexOf(old, pos + length)) {
                --index;
                if (index == 0) {
                    break;
                }
            }

            if (pos >= 0 && index == 0) {
                buffer.replace(pos, pos + length, newStr);
            }

            return buffer.toString();
        } else {
            return src;
        }
    }

    public static String replaceBlank(String str) {
        if (str != null) {
            str = str.replaceAll("\\s*|\t|\r|\n", "");
        }

        return str;
    }

    public static String quote(String str) {
        if (isEmpty(str)) {
            return "\"\"";
        } else {
            StringBuffer buffer = new StringBuffer(str);
            if (!str.startsWith("\"")) {
                buffer.insert(0, "\"");
            }

            if (!str.endsWith("\"")) {
                buffer.append("\"");
            }

            return buffer.toString();
        }
    }

    public static String extractQuotedStr(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            StringBuffer buffer = new StringBuffer(str);
            int index = str.length();

            while(buffer.charAt(buffer.length() - 1) == '"') {
                buffer.deleteCharAt(buffer.length() - 1);
                index = buffer.length();
                if (index <= 0) {
                    break;
                }
            }

            if (buffer.length() == 0) {
                return "";
            } else {
                while(buffer.charAt(0) == '"') {
                    buffer.deleteCharAt(0);
                    index = buffer.length();
                    if (index <= 0) {
                        break;
                    }
                }

                return buffer.toString();
            }
        }
    }

    public static String strChar(String str, char c) {
        if (str != null && str.length() != 0) {
            for(int i = 0; i < str.length(); ++i) {
                if (str.charAt(i) == c) {
                    return str.substring(i);
                }
            }

            return null;
        } else {
            return null;
        }
    }

    public static String strRChar(String str, char c) {
        if (str != null && str.length() != 0) {
            for(int i = str.length() - 1; i >= 0; --i) {
                if (str.charAt(i) == c) {
                    return str.substring(i);
                }
            }

            return null;
        } else {
            return null;
        }
    }

    public static boolean isValidEmail(String theEmail) {
        boolean isEmail = false;

        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(theEmail);
            boolean isMatched = matcher.matches();
            if (isMatched) {
                isEmail = true;
            }
        } catch (Exception var6) {
            ;
        }

        return isEmail;
    }

    public static String trimLeft(String str) {
        if (str == null) {
            return null;
        } else {
            int length = str.length();
            if (length == 0) {
                return "";
            } else {
                StringBuffer buffer = new StringBuffer(str);

                int index;
                for(index = 0; index < length && buffer.charAt(index) == ' '; ++index) {
                    index += 0;
                }

                return index == length ? "" : buffer.substring(index);
            }
        }
    }

    public static String trimRight(String str) {
        if (str == null) {
            return null;
        } else {
            int length = str.length();
            if (length == 0) {
                return "";
            } else {
                StringBuffer buffer = new StringBuffer(str);

                int index;
                for(index = length - 1; index >= 0 && buffer.charAt(index) == ' '; --index) {
                    index += 0;
                }

                return index < 0 && buffer.charAt(0) == ' ' ? "" : buffer.substring(0, index + 1);
            }
        }
    }

    public static boolean idCardVerify(String idcard) {
        if (idcard.length() == 15) {
            idcard = idCardUptoeighteen(idcard);
        }

        if (idcard.length() != 18) {
            return false;
        } else {
            String verify = idcard.substring(17, 18);
            return verify.equals(getIdCardVerify(idcard));
        }
    }

    public static String getIdCardVerify(String eightcardid) {
        int[] wi = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
        int[] vi = new int[]{1, 0, 88, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] ai = new int[18];
        int remaining = 0;
        if (eightcardid.length() == 18) {
            eightcardid = eightcardid.substring(0, 17);
        }

        if (eightcardid.length() == 17) {
            int sum = 0;

            int i;
            for(i = 0; i < 17; ++i) {
                String k = eightcardid.substring(i, i + 1);
                ai[i] = Integer.parseInt(k);
            }

            for(i = 0; i < 17; ++i) {
                sum += wi[i] * ai[i];
            }

            remaining = sum % 11;
        }

        return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
    }

    public static String idCardUptoeighteen(String fifteencardid) {
        if (fifteencardid.length() != 15) {
            return null;
        } else {
            String eightcardid = fifteencardid.substring(0, 6);
            eightcardid = eightcardid + "19";
            eightcardid = eightcardid + fifteencardid.substring(6, 15);
            eightcardid = eightcardid + getIdCardVerify(eightcardid);
            return eightcardid;
        }
    }

    public static boolean isPhoneNum(String phoneCode) {
        Pattern p = Pattern.compile("[0][1-9]{2,3}[1-9]{6,8}");
        Matcher m = p.matcher(phoneCode);
        boolean b = m.matches();
        return b;
    }

    public static BigDecimal asBigDecimal(String str) {
        return asBigDecimal(str, new BigDecimal(BigInteger.ZERO));
    }

    public static BigDecimal asBigDecimal(String str, BigDecimal defaultValue) {
        try {
            return new BigDecimal(str.trim());
        } catch (NullPointerException var3) {
            return defaultValue;
        } catch (NumberFormatException var4) {
            return defaultValue;
        } catch (RuntimeException var5) {
            return defaultValue;
        }
    }

    public static BigInteger asBigInteger(String str) {
        return asBigInteger(str, BigInteger.ZERO);
    }

    public static BigInteger asBigInteger(String str, BigInteger defaultValue) {
        try {
            return new BigInteger(str.trim());
        } catch (NullPointerException var3) {
            return defaultValue;
        } catch (NumberFormatException var4) {
            return defaultValue;
        }
    }

    public static boolean asBoolean(String str) {
        return asBoolean(str, false);
    }

    public static boolean asBoolean(String str, boolean defaultValue) {
        try {
            str = str.trim();
            return Boolean.parseBoolean(str);
        } catch (Exception var3) {
            return defaultValue;
        }
    }

    public static byte asByte(String str) {
        return asByte(str, (byte)0);
    }

    public static byte asByte(String str, byte defaultValue) {
        try {
            return Byte.decode(str.trim());
        } catch (NullPointerException var3) {
            return defaultValue;
        } catch (NumberFormatException var4) {
            return defaultValue;
        }
    }

    public static char asCharacter(String str) {
        return asCharacter(str, '\u0000');
    }

    public static char asCharacter(String str, char defaultValue) {
        try {
            return str.trim().charAt(0);
        } catch (NullPointerException var3) {
            return defaultValue;
        } catch (IndexOutOfBoundsException var4) {
            return defaultValue;
        }
    }

    public static double asDouble(String str) {
        return asDouble(str, 0.0D);
    }

    public static double asDouble(String str, double defaultValue) {
        try {
            return new Double(str.trim());
        } catch (NullPointerException var4) {
            return defaultValue;
        } catch (NumberFormatException var5) {
            return defaultValue;
        }
    }

    public static float asFloat(String str) {
        return asFloat(str, 0.0F);
    }

    public static float asFloat(String str, float defaultValue) {
        try {
            return new Float(str.trim());
        } catch (NullPointerException var3) {
            return defaultValue;
        } catch (NumberFormatException var4) {
            return defaultValue;
        }
    }

    public static int asInteger(String str) {
        return asInteger(str, 0);
    }

    public static int asInteger(String str, int defaultValue) {
        try {
            return Integer.decode(str.trim());
        } catch (NullPointerException var3) {
            return defaultValue;
        } catch (NumberFormatException var4) {
            return defaultValue;
        }
    }

    public static long asLong(String str) {
        return asLong(str, 0L);
    }

    public static long asLong(String str, long defaultValue) {
        try {
            return Long.decode(str.trim());
        } catch (NullPointerException var4) {
            return defaultValue;
        } catch (NumberFormatException var5) {
            return defaultValue;
        }
    }

    public static short asShort(String str) {
        return asShort(str, (short)0);
    }

    public static short asShort(String str, short defaultValue) {
        try {
            return Short.decode(str.trim());
        } catch (NullPointerException var3) {
            return defaultValue;
        } catch (NumberFormatException var4) {
            return defaultValue;
        }
    }

    public static Date asDate(String str) {
        return asDate(str, new Date(), (String)null);
    }

    public static Date asDate(String str, Date defaultValue) {
        return asDate(str, defaultValue, (String)null);
    }

    public static Date asDate(String str, Date defaultValue, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern != null ? pattern : "yyyy-MM-dd");

        try {
            return formatter.parse(str);
        } catch (ParseException var5) {
            return defaultValue;
        } catch (NullPointerException var6) {
            return defaultValue;
        }
    }

    public static Object asType(Class type, String str) {
        if (!type.isAssignableFrom(Integer.class) && !type.equals(Integer.TYPE)) {
            if (!type.isAssignableFrom(Double.class) && !type.equals(Double.TYPE)) {
                if (!type.isAssignableFrom(Boolean.class) && !type.equals(Boolean.TYPE)) {
                    if (!type.isAssignableFrom(Float.class) && !type.equals(Float.TYPE)) {
                        if (!type.isAssignableFrom(Long.class) && !type.equals(Long.TYPE)) {
                            if (!type.isAssignableFrom(Short.class) && !type.equals(Short.TYPE)) {
                                if (!type.isAssignableFrom(Byte.class) && !type.equals(Byte.TYPE)) {
                                    if (!type.isAssignableFrom(Character.class) && !type.equals(Character.TYPE)) {
                                        if (type.isAssignableFrom(BigDecimal.class)) {
                                            return asBigDecimal(str, new BigDecimal(BigInteger.ZERO));
                                        } else if (type.isAssignableFrom(BigInteger.class)) {
                                            return asBigInteger(str, BigInteger.ZERO);
                                        } else {
                                            return type.isAssignableFrom(Date.class) ? asDate(str, new Date(), (String)null) : null;
                                        }
                                    } else {
                                        return asCharacter(str, new Character('\u0000'));
                                    }
                                } else {
                                    return asByte(str, new Byte((byte)0));
                                }
                            } else {
                                return asShort(str, new Short((short)0));
                            }
                        } else {
                            return asLong(str, new Long(0L));
                        }
                    } else {
                        return asFloat(str, new Float(0.0F));
                    }
                } else {
                    return asBoolean(str, Boolean.FALSE);
                }
            } else {
                return asDouble(str, new Double(0.0D));
            }
        } else {
            return asInteger(str, new Integer(0));
        }
    }

    public static Object asType(Class type, String str, Object defaultValue) {
        if (!type.isAssignableFrom(Integer.class) && !type.equals(Integer.TYPE)) {
            if (!type.isAssignableFrom(Double.class) && !type.equals(Double.TYPE)) {
                if (!type.isAssignableFrom(Boolean.class) && !type.equals(Boolean.TYPE)) {
                    if (!type.isAssignableFrom(Float.class) && !type.equals(Float.TYPE)) {
                        if (!type.isAssignableFrom(Long.class) && !type.equals(Long.TYPE)) {
                            if (!type.isAssignableFrom(Short.class) && !type.equals(Short.TYPE)) {
                                if (!type.isAssignableFrom(Byte.class) && !type.equals(Byte.TYPE)) {
                                    if (!type.isAssignableFrom(Character.class) && !type.equals(Character.TYPE)) {
                                        if (type.isAssignableFrom(BigDecimal.class)) {
                                            return asBigDecimal(str, (BigDecimal)defaultValue);
                                        } else if (type.isAssignableFrom(BigInteger.class)) {
                                            return asBigInteger(str, (BigInteger)defaultValue);
                                        } else {
                                            return type.isAssignableFrom(Date.class) ? asDate(str, (Date)defaultValue) : null;
                                        }
                                    } else {
                                        return asCharacter(str, (Character)defaultValue);
                                    }
                                } else {
                                    return asByte(str, (Byte)defaultValue);
                                }
                            } else {
                                return asShort(str, (Short)defaultValue);
                            }
                        } else {
                            return asLong(str, (Long)defaultValue);
                        }
                    } else {
                        return asFloat(str, (Float)defaultValue);
                    }
                } else {
                    return asBoolean(str, (Boolean)defaultValue);
                }
            } else {
                return asDouble(str, (Double)defaultValue);
            }
        } else {
            return asInteger(str, (Integer)defaultValue);
        }
    }

    public static Object asType(Class type, Object obj) {
        if (!type.equals(String.class) && type.isInstance(obj)) {
            return obj;
        } else if (obj != null && !(obj instanceof String)) {
            if (obj instanceof Date && String.class.isAssignableFrom(type)) {
                return (new SimpleDateFormat("yyyy-MM-dd")).format((Date)obj);
            } else {
                if (obj instanceof Number && Number.class.isAssignableFrom(type)) {
                    Number num = (Number)obj;
                    if (type.isAssignableFrom(Number.class)) {
                        return num;
                    }

                    if (type.isAssignableFrom(Integer.class)) {
                        return new Integer(num.intValue());
                    }

                    if (type.isAssignableFrom(Double.class)) {
                        return new Double(num.doubleValue());
                    }

                    if (type.isAssignableFrom(Float.class)) {
                        return new Float(num.floatValue());
                    }

                    if (type.isAssignableFrom(Long.class)) {
                        return new Long(num.longValue());
                    }

                    if (type.isAssignableFrom(Short.class)) {
                        return new Short(num.shortValue());
                    }

                    if (type.isAssignableFrom(Byte.class)) {
                        return new Byte(num.byteValue());
                    }

                    if (type.isAssignableFrom(BigInteger.class)) {
                        return (new BigDecimal(num.toString())).toBigInteger();
                    }

                    if (type.isAssignableFrom(BigDecimal.class)) {
                        return new BigDecimal(num.toString());
                    }
                }

                return asType(type, obj.toString());
            }
        } else {
            return asType(type, (String)obj);
        }
    }

    public static Object asType(Class type, Object obj, Object defaultValue) {
        if (!type.equals(String.class) && type.isInstance(obj)) {
            return obj;
        } else if (obj != null && !(obj instanceof String)) {
            if (obj instanceof Date && String.class.isAssignableFrom(type)) {
                return (new SimpleDateFormat("yyyy-MM-dd")).format((Date)obj);
            } else {
                if (obj instanceof Number && Number.class.isAssignableFrom(type)) {
                    Number num = (Number)obj;
                    if (type.isAssignableFrom(Number.class)) {
                        return num;
                    }

                    if (type.isAssignableFrom(Integer.class)) {
                        return new Integer(num.intValue());
                    }

                    if (type.isAssignableFrom(Double.class)) {
                        return new Double(num.doubleValue());
                    }

                    if (type.isAssignableFrom(Float.class)) {
                        return new Float(num.floatValue());
                    }

                    if (type.isAssignableFrom(Long.class)) {
                        return new Long(num.longValue());
                    }

                    if (type.isAssignableFrom(Short.class)) {
                        return new Short(num.shortValue());
                    }

                    if (type.isAssignableFrom(Byte.class)) {
                        return new Byte(num.byteValue());
                    }

                    if (type.isAssignableFrom(BigInteger.class)) {
                        return (new BigDecimal(num.toString())).toBigInteger();
                    }

                    if (type.isAssignableFrom(BigDecimal.class)) {
                        return new BigDecimal(num.toString());
                    }
                }

                return asType(type, obj.toString(), defaultValue);
            }
        } else {
            return asType(type, (String)obj, defaultValue);
        }
    }

    public static String getClassName(Class cls) {
        return getClassName(cls.getName());
    }

    public static String getClassName(String fullName) {
        if (fullName == null) {
            return null;
        } else {
            fullName = fullName.trim();
            String className = fullName.substring(fullName.lastIndexOf(46) + 1).replace('$', '.').trim();
            return className.equals("") ? null : className;
        }
    }

//    public static String convertUTF8String2Unicode(String instr) throws IOException {
//        int charindex = instr.length();
//        StringBuffer sbtemp = new StringBuffer();
//
//        int actualValue;
//        for(int i = 0; i < charindex; sbtemp.append((char)actualValue)) {
//            actualValue = -1;
//            int inputValue = instr.charAt(i++);
//            int inputValue = inputValue & 255;
//            if ((inputValue & 128) == 0) {
//                actualValue = inputValue;
//            } else {
//                int nextByte;
//                if ((inputValue & 248) == 240) {
//                    actualValue = (inputValue & 31) << 18;
//                    nextByte = instr.charAt(i++) & 255;
//                    if ((nextByte & 192) != 128) {
//                        throw new IOException("Invalid UTF-8 format");
//                    }
//
//                    actualValue += (nextByte & 63) << 12;
//                    nextByte = instr.charAt(i++) & 255;
//                    if ((nextByte & 192) != 128) {
//                        throw new IOException("Invalid UTF-8 format");
//                    }
//
//                    actualValue += (nextByte & 63) << 6;
//                    nextByte = instr.charAt(i++) & 255;
//                    if ((nextByte & 192) != 128) {
//                        throw new IOException("Invalid UTF-8 format");
//                    }
//
//                    actualValue += nextByte & 63;
//                } else if ((inputValue & 240) == 224) {
//                    actualValue = (inputValue & 31) << 12;
//                    nextByte = instr.charAt(i++) & 255;
//                    if ((nextByte & 192) != 128) {
//                        throw new IOException("Invalid UTF-8 format");
//                    }
//
//                    actualValue += (nextByte & 63) << 6;
//                    nextByte = instr.charAt(i++) & 255;
//                    if ((nextByte & 192) != 128) {
//                        throw new IOException("Invalid UTF-8 format");
//                    }
//
//                    actualValue += nextByte & 63;
//                } else if ((inputValue & 224) == 192) {
//                    actualValue = (inputValue & 31) << 6;
//                    nextByte = instr.charAt(i++) & 255;
//                    if ((nextByte & 192) != 128) {
//                        throw new IOException("Invalid UTF-8 format");
//                    }
//
//                    actualValue += nextByte & 63;
//                }
//            }
//        }
//
//        return sbtemp.toString();
//    }

    public static byte[] convertUnicode2UTF8Byte(String instr) {
        int len = instr.length();
        byte[] abyte = new byte[len << 2];
        int j = 0;

        for(int i = 0; i < len; ++i) {
            char c = instr.charAt(i);
            if (c < 128) {
                abyte[j++] = (byte)c;
            } else if (c < 2048) {
                abyte[j++] = (byte)(c >> 6 & 31 | 192);
                abyte[j++] = (byte)(c & 63 | 128);
            } else if (c < 65536) {
                abyte[j++] = (byte)(c >> 12 & 15 | 224);
                abyte[j++] = (byte)(c >> 6 & 63 | 128);
                abyte[j++] = (byte)(c & 63 | 128);
            } else if (c < 2097152) {
                abyte[j++] = (byte)(c >> 18 & 7 | 248);
                abyte[j++] = (byte)(c >> 12 & 63 | 128);
                abyte[j++] = (byte)(c >> 6 & 63 | 128);
                abyte[j++] = (byte)(c & 63 | 128);
            }
        }

        byte[] retbyte = new byte[j];

        for(int i = 0; i < j; ++i) {
            retbyte[i] = abyte[i];
        }

        return retbyte;
    }

    public static byte[] unicode2Byte(String s) {
        int len = s.length();
        byte[] abyte = new byte[len << 1];
        int j = 0;

        for(int i = 0; i < len; ++i) {
            char c = s.charAt(i);
            abyte[j++] = (byte)(c & 255);
            abyte[j++] = (byte)(c >> 8);
        }

        return abyte;
    }

    public static String lowerCaseTrans(String transStr) {
        if (null == transStr) {
            return null;
        } else {
            StringBuffer sbTmp = new StringBuffer();

            for(int i = 0; i < transStr.length(); ++i) {
                String stmp = String.valueOf(transStr.charAt(i));
                if ("0123456789".indexOf(stmp) >= 0) {
                    int irec = Integer.parseInt(stmp);
                    sbTmp.append(LOWERCASES[irec]);
                } else {
                    sbTmp.append(stmp);
                }
            }

            return sbTmp.toString();
        }
    }

    public static String upperCaseTrans(String transStr) {
        if (null == transStr) {
            return null;
        } else {
            StringBuffer sbTmp = new StringBuffer();

            for(int i = 0; i < transStr.length(); ++i) {
                String stmp = String.valueOf(transStr.charAt(i));
                if ("0123456789".indexOf(stmp) >= 0) {
                    int irec = Integer.parseInt(stmp);
                    sbTmp.append(UPPERCASES[irec]);
                } else {
                    sbTmp.append(stmp);
                }
            }

            return sbTmp.toString();
        }
    }

    public static String lowerDateTrans(String transStr) {
        if (null == transStr) {
            return null;
        } else {
            String sbTmp = "";

            try {
                String syear = transStr.substring(0, 4);
                int imon = Integer.parseInt(transStr.substring(5, 7));
                int iday = Integer.parseInt(transStr.substring(8, transStr.length() - 1));
                sbTmp = sbTmp + lowerCaseTrans(syear) + "年";
                String dtmp;
                if (imon > 9 && imon < 19) {
                    dtmp = String.valueOf(imon).substring(1, 2);
                    if ("0".equals(dtmp)) {
                        sbTmp = sbTmp + "十";
                    } else {
                        sbTmp = sbTmp + "十" + lowerCaseTrans(dtmp);
                    }
                } else if (imon > 19) {
                    dtmp = lowerCaseTrans(String.valueOf(imon));
                    sbTmp = sbTmp + dtmp.substring(0, 1) + "十" + dtmp.substring(1, 2);
                } else {
                    sbTmp = sbTmp + lowerCaseTrans(String.valueOf(imon));
                }

                sbTmp = sbTmp + "月";
                if (iday > 9 && iday < 19) {
                    dtmp = String.valueOf(iday).substring(1, 2);
                    if ("0".equals(dtmp)) {
                        sbTmp = sbTmp + "十";
                    } else {
                        sbTmp = sbTmp + "十" + lowerCaseTrans(dtmp);
                    }
                } else if (iday > 19) {
                    dtmp = lowerCaseTrans(String.valueOf(iday));
                    sbTmp = sbTmp + dtmp.substring(0, 1) + "十" + dtmp.substring(1, 2);
                } else {
                    sbTmp = sbTmp + lowerCaseTrans(String.valueOf(iday));
                }

                sbTmp = sbTmp + "号";
                return sbTmp;
            } catch (Exception var6) {
                return null;
            }
        }
    }

    public static String upperDateTrans(String transStr) {
        if (null == transStr) {
            return null;
        } else {
            String sbTmp = "";

            try {
                String syear = transStr.substring(0, 4);
                int imon = Integer.parseInt(transStr.substring(5, 7));
                int iday = Integer.parseInt(transStr.substring(8, transStr.length() - 1));
                sbTmp = sbTmp + upperCaseTrans(syear) + "年";
                String dtmp;
                if (imon > 9 && imon < 19) {
                    dtmp = String.valueOf(imon).substring(1, 2);
                    if ("0".equals(dtmp)) {
                        sbTmp = sbTmp + "拾";
                    } else {
                        sbTmp = sbTmp + "拾" + upperCaseTrans(dtmp);
                    }
                } else if (imon > 19) {
                    dtmp = upperCaseTrans(String.valueOf(imon));
                    sbTmp = sbTmp + dtmp.substring(0, 1) + "拾" + dtmp.substring(1, 2);
                } else {
                    sbTmp = sbTmp + upperCaseTrans(String.valueOf(imon));
                }

                sbTmp = sbTmp + "月";
                if (iday > 9 && iday < 19) {
                    dtmp = String.valueOf(iday).substring(1, 2);
                    if ("0".equals(dtmp)) {
                        sbTmp = sbTmp + "拾";
                    } else {
                        sbTmp = sbTmp + "拾" + upperCaseTrans(dtmp);
                    }
                } else if (iday > 19) {
                    dtmp = upperCaseTrans(String.valueOf(iday));
                    sbTmp = sbTmp + dtmp.substring(0, 1) + "拾" + dtmp.substring(1, 2);
                } else {
                    sbTmp = sbTmp + upperCaseTrans(String.valueOf(iday));
                }

                sbTmp = sbTmp + "号";
                return sbTmp;
            } catch (Exception var6) {
                return null;
            }
        }
    }

    public static String removeRigthChar(String str, char chr) {
        if (str != null && str.trim().length() >= 1) {
            char[] chrArray = str.toCharArray();
            int iCount = 0;

            for(int i = chrArray.length - 1; i >= 0 && chrArray[i] == chr; --i) {
                ++iCount;
            }

            StringBuffer buf = new StringBuffer();

            for(int j = 0; j < chrArray.length - iCount; ++j) {
                buf.append(chrArray[j]);
            }

            return buf.toString();
        } else {
            return null;
        }
    }

    public static String warp(String str, int len) {
        if (isEmpty(str)) {
            return str;
        } else if (len > 0 && len < str.length()) {
            StringBuffer buffer = new StringBuffer();
            StringReader reader = new StringReader(str);
            BufferedReader r = new BufferedReader(reader);

            try {
                String line;
                try {
                    while((line = r.readLine()) != null) {
                        for(int i = 0; i < line.length(); i += len) {
                            if (i + len < line.length()) {
                                buffer.append(line.substring(i, i + len));
                            } else {
                                buffer.append(line.substring(i, line.length()));
                            }

                            buffer.append("\n");
                        }
                    }
                } catch (IOException var15) {
                    var15.printStackTrace();
                }
            } finally {
                try {
                    r.close();
                } catch (IOException var14) {
                    var14.printStackTrace();
                }

            }

            return buffer.toString();
        } else {
            return str;
        }
    }

    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            StringBuffer buffer = new StringBuffer(str);
            buffer.setCharAt(0, str.substring(0, 1).toUpperCase().charAt(0));
            return buffer.toString();
        }
    }

    public static String deCapitalize(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            StringBuffer buffer = new StringBuffer(str);
            buffer.setCharAt(0, str.substring(0, 1).toLowerCase().charAt(0));
            return buffer.toString();
        }
    }

    public static String html2Text(String inputString) {
        String textStr = "";
        String regExScript = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
        String regExStyle = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
        String regExHtml = "<[^>]+>";
        Pattern pScript = Pattern.compile(regExScript, 2);
        Matcher mScript = pScript.matcher(inputString);
        String htmlStr = mScript.replaceAll("");
        Pattern pStyle = Pattern.compile(regExStyle, 2);
        Matcher mStyle = pStyle.matcher(htmlStr);
        htmlStr = mStyle.replaceAll("");
        Pattern phtml = Pattern.compile(regExHtml, 2);
        Matcher mHtml = phtml.matcher(htmlStr);
        htmlStr = mHtml.replaceAll("");
        return htmlStr;
    }

    public static String transferSql(String str, char escapeCh) {
        if (str != null && !"".equals(str)) {
            StringBuffer sbf = new StringBuffer(str.length());

            for(int i = 0; i < str.length(); ++i) {
                char ch = str.charAt(i);
                if (ch != '%' && ch != '_' && ch != escapeCh) {
                    sbf.append(ch);
                } else {
                    sbf.append(escapeCh).append(ch);
                }
            }

            return sbf.toString();
        } else {
            return str;
        }
    }

    public static boolean isHasCn(String value) {
        if (value == null) {
            return false;
        } else if (value.equals("")) {
            return false;
        } else {
            char[] cs = value.toCharArray();

            for(int i = 0; i < cs.length; ++i) {
                if (Character.getType(cs[i]) != 5) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isInteger(String str) {
        return str == null ? true : str.matches("^[-\\+]?\\d+$");
    }

    public static boolean isFloat(String str) {
        if (str == null) {
            return true;
        } else {
            return str.matches("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$") || isInteger(str);
        }
    }

    public static boolean analyiz(String text, int ip) {
        char temp = text.charAt(ip);
        if (temp != '(' && temp != '[' && temp != '{') {
            char a;
            if (temp == ')') {
                a = (Character)stack.getLast();
                if (a == '(') {
                    stack.removeLast();
                }
            } else if (temp == ']') {
                a = (Character)stack.getLast();
                if (a == '[') {
                    stack.removeLast();
                }
            } else if (temp == '}') {
                a = (Character)stack.getLast();
                if (a == '{') {
                    stack.removeLast();
                }
            }
        } else {
            stack.add(temp);
        }

        if (stack.size() == 0 && ip == text.length() - 1) {
            return true;
        } else {
            return stack.size() != 0 && ip == text.length() - 1 ? false : analyiz(text, ip + 1);
        }
    }

    public static String leftStrByBytes(String source, int index) {
        byte[] bytes = source.getBytes();
        int nameLength = bytes.length;
        if (nameLength > index) {
            if (bytes[index - 1] < 0) {
                int count = 0;

                for(int i = 0; i < index; ++i) {
                    if (bytes[i] < 0) {
                        ++count;
                    }
                }

                if (count % 2 == 0) {
                    return new String(bytes, 0, index);
                } else {
                    return new String(bytes, 0, index - 1);
                }
            } else {
                return new String(bytes, 0, index);
            }
        } else {
            return source;
        }
    }

    public static String leftStr(String source, int maxByteLen, int flag) {
        if (source != null && maxByteLen > 0) {
            byte[] bStr = source.getBytes();
            if (maxByteLen >= bStr.length) {
                return source;
            } else {
                String cStr = new String(bStr, maxByteLen - 1, 2);
                if (cStr.length() == 1 && source.contains(cStr)) {
                    maxByteLen += flag;
                }

                return new String(bStr, 0, maxByteLen);
            }
        } else {
            return "";
        }
    }

//    public static String getQueryCode(String character) {
//        StringBuffer result = new StringBuffer("");
//        int j = 0;
//        int k = 0;
//        int l = 0;
//        char[] zm1 = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z'};
//        String[] strhz = new String[]{"锕 捱 嗳 嗌 嫒 瑷 暧 砹 锿 霭 谙 埯 揞 犴 庵 桉 铵 鹌 黯  嚣 坳 拗 嗷 岙 廒 遨 媪 骜 獒 聱 螯 鏊 鳌 鏖 ", " 茇 菝 岜 灞 钯 粑 鲅 魃 捭 掰 阪 坂 钣 瘢 癍 舨 蒡 浜 曝  葆 孢 煲 鸨 褓 趵 龅 孛 陂 邶 埤 萆 蓓 呗 悖 碚 鹎 褙 鐾 鞴 夯  畚 坌 贲 锛 嘣 甏 匕 俾 埤 芘 荜 荸 萆 薜 吡 哔 狴 庳 愎 滗 濞  弼 妣 婢 嬖 璧 贲 睥 畀 铋 秕 裨 筚 箅 篦 舭 襞 跸 髀 匾 弁 苄  忭 汴 缏 飚 煸 砭 碥 窆 褊 蝙 笾 鳊 婊 骠 杓 飑 飙 镖 镳 瘭 裱  鳔 髟 蹩 傧 豳 缤 玢 槟 殡 膑 镔 髌 鬓 禀 邴 摒 槟 柏 卜 孛 亳  啵 饽 檗 掰 擘 礴 钹 鹁 簸 趵 跛 踣 卟 逋 瓿 晡 钚 钸 醭 ", " 嚓 礤 孱 骖 璨 粲 黪 嘈 漕 螬 艚 恻 岑 涔 刹 喳 嚓 猹 馇 汊 姹 杈 楂  槎 檫 锸 镲 衩 侪 钗 瘥 虿 冁 谄 蒇 廛 忏 潺 澶 孱 羼 婵 骣 觇  禅 镡 蟾 躔 倘 伥 鬯 苌 菖 徜 怅 惝 阊 娼 嫦 昶 氅 鲳 绰 剿 怊  晁 焯 耖 坼 砗 郴 伧 谌 谶 抻 嗔 宸 琛 榇 碜 龀 骋 秤 丞 埕 噌  枨 柽 塍 瞠 铖 铛 裎 蛏 酲 傺 坻 墀 茌 叱 哧 啻 嗤 饬 媸 敕 眵  鸱 瘛 褫 蚩 螭 笞 篪 豉 踟 魑 茺 忡 憧 铳 舂 艟 俦 帱 惆 瘳 雠  畜 亍 刍 怵 憷 绌 杵 楮 樗 褚 蜍 蹰 黜 搋 膪 踹 啜 嘬 舛 遄 巛  氚 钏 舡 怆 陲 棰 槌 茈 呲 祠 鹚 糍 楱 辏 腠 蔟 徂 猝 殂 酢 蹙  蹴 攒 汆 撺 爨 镩 萃 啐 悴 璀 榱 毳 隹 忖 皴 厝 嵯 脞 锉 矬 痤 瘥 鹾 蹉 ", " 耷 哒 嗒 怛 妲 沓 疸 褡 笪 靼 鞑 埭 甙 呔 岱 迨 骀 绐 玳 黛  诞 儋 萏 啖 澹 殚 赕 眈 疸 瘅 聃 箪 谠 凼 菪 宕 砀 裆 叨 帱 忉  氘 焘 纛 锝 噔 嶝 戥 磴 镫 簦 氐 籴 诋 谛 邸 坻 荻 嘀 娣 绨 柢  棣 觌 砥 碲 睇 镝 羝 骶 嗲 阽 坫 巅 玷 钿 癜 癫 簟 踮 铞 铫 貂  鲷 垤 堞 揲 喋 牒 瓞 耋 踮 蹀 鲽 仃 啶 玎 腚 碇 町 铤 疔 耵 酊  铥 垌 咚 岽 峒 氡 胨 胴 硐 鸫 蔸 窦 蚪 篼 芏 嘟 渎 椟 牍 蠹 笃  髑 黩 椴 煅 簖 怼 憝 碓 沌 炖 砘 礅 盹 镦 趸 驮 咄 哚 沲 缍 柁 铎 裰 踱 ", " 噩 谔 垩 苊 莪 萼 呃 愕 屙 婀 轭 腭 锇 锷 鹗 颚 鳄 诶 蒽 摁 佴 迩 珥 铒 鸸 鲕 ", " 垡 砝 蕃 蘩 幡 梵 燔 畈 蹯 坊 邡 彷 枋 钫 舫 鲂 芾 狒 悱 淝 妃 绯 榧 腓  斐 扉 镄 痱 蜚 篚 翡 霏 鲱 偾 瀵 玢 棼 鲼 鼢 俸 酆 葑 唪 沣 砜 缶  匐 凫 郛 芙 芾 苻 茯 莩 菔 拊 呋 幞 怫 滏 艴 孚 驸 绂 绋 桴 赙  祓 砩 黻 黼 罘 稃 馥 蚨 蜉 蝠 蝮 麸 趺 跗 鲋 鳆 ", " 尬 尕 尜 旮 钆 丐 陔 垓 戤 赅 坩 苷 尴 擀 泔 淦 澉 绀 橄 旰 矸 疳 酐  戆 罡 筻 睾 诰 郜 藁 缟 槔 槁 杲 锆 咯 屹 鬲 仡 哿 圪 塥 嗝 纥  搿 膈 硌 镉 袼 颌 虼 舸 骼 亘 茛 哏 艮 哽 赓 绠 鲠 珙 肱 蚣 觥  佝 诟 岣 遘 媾 缑 枸 觏 彀 笱 篝 鞲 嘏 诂 菰 呱 崮 汩 梏 轱 牯  牿 臌 毂 瞽 罟 钴 锢 鸪 鹄 痼 蛄 酤 觚 鲴 鹘 卦 诖 呱 栝 胍 鸹  纶 倌 莞 掼 涫 盥 鹳 矜 鳏 咣 犷 桄 胱 傀 炔 匦 刿 庋 宄 妫 桧  炅 晷 皈 簋 鲑 鳜 衮 绲 磙 鲧 涡 馘 埚 掴 呙 帼 崞 猓 椁 虢 聒 蜾 蝈 ", " 蛤 虾 铪 嘿 咳 嗨 胲 醢 邗 菡 撖 阚 瀚 晗 焓 顸 颔 蚶 鼾 沆 绗 珩 颃 镐  貉 蒿 薅 嗥 嚆 濠 灏 昊 皓 颢 蚝 诃 劾 壑 嗬 阖 纥 曷 盍 颌 蚵 翮  蘅 珩 桁 黉 訇 讧 荭 蕻 薨 闳 泓 堠 後 逅 瘊 篌 糇 鲎 骺 冱 唿 囫  岵 猢 怙 惚 浒 滹 琥 槲 轷 觳 烀 煳 戽 扈 祜 瓠 鹄 鹕 鹱 笏 醐 斛  骅 桦 砉 铧 踝 郇 奂 萑 擐 圜 獾 洹 浣 漶 寰 逭 缳 锾 鲩 鬟 隍 徨  湟 潢 遑 璜 肓 癀 蟥 篁 鳇 茴 荟 蕙 咴 哕 喙 隳 洄 浍 彗 缋 珲 桧  晖 恚 虺 蟪 麾 诨 馄 阍 溷 劐 藿 攉 嚯 夥 钬 锪 镬 耠 蠖 ", " 丌 亟 乩 剞 佶 偈 诘 墼 芨 芰 荠 蒺 蕺 掎 叽 咭 哜 唧 岌 嵴 洎 屐 骥 畿  玑 楫 殛 戟 戢 赍 觊 犄 齑 矶 羁 嵇 稷 瘠 虮 笈 笄 暨 跻 跽 霁 鲚  鲫 髻 麂 嘏 伽 郏 葭 岬 浃 迦 珈 戛 胛 恝 铗 镓 痂 瘕 袷 蛱 笳  袈 跏 僭 谏 谫 菅 蒹 搛 囝 湔 蹇 謇 缣 枧 楗 戋 戬 牮 犍 毽 腱  睑 锏 鹣 裥 笕 翦 趼 踺 鲣 鞯 吉 安 茳 洚 绛 缰 犟 礓 耩 糨 豇  僬 艽 茭 挢 噍 峤 徼 湫 姣 敫 皎 鹪 蛟 醮 跤 鲛 偈 讦 诘 拮 喈  嗟 婕 孑 桀 碣 疖 颉 蚧 羯 鲒 骱 饥 饿 卺 荩 堇 噤 馑 廑 妗 缙  瑾 槿 赆 觐 衿 刭 儆 阱 菁 獍 憬 泾 迳 弪 婧 肼 胫 腈 旌 靓 迥  炅 扃 僦 啾 阄 柩 桕 鸠 鹫 赳 鬏 倨 讵 苣 苴 莒 掬 遽 屦 琚 枸  椐 榘 榉 橘 犋 飓 钜 锔 窭 裾 趄 醵 踽 龃 雎 瞿 鞫 鄄 狷 涓 桊  蠲 锩 镌 隽 厥 劂 谲 矍 蕨 嗟 噘 噱 崛 獗 孓 珏 桷 橛 爝 镢 蹶  觖 巨 额 捃 皲 筠 麇 ", " 佧 咔 胩 剀 垲 蒈 忾 恺 铠 锎 锴 侃 莰 阚 戡 龛 瞰 伉 闶 钪 尻 栲  犒 铐 嗑 岢 恪 溘 骒 缂 珂 轲 氪 瞌 钶 锞 稞 疴 窠 颏 蝌 髁  裉 铿 倥 崆 箜 芤 蔻 叩 眍 筘 刳 堀 喾 绔 骷 侉 侩 蒯 郐 哙  狯 浍 脍 髋 诓 诳 邝 圹 夼 哐 纩 贶 馗 匮 夔 隗 蒉 揆 喹 喟  悝 愦 逵 暌 睽 聩 蝰 篑 跬 悃 阃 琨 锟 醌 鲲 髡 栝 蛞 ", " 剌 邋 旯 砬 瘌 崃 徕 涞 濑 赉 睐 铼 癞 籁 岚 漤 榄 斓 罱 镧 褴  莨 蒗 啷 阆 锒 稂 螂 潦 唠 崂 栳 铑 铹 痨 耢 醪 仂 叻 泐 鳓  羸 诔 嘞 嫘 缧 檑 耒 酹 塄 愣 鬲 俪 俚 郦 坜 苈 莅 蓠 藜 呖  唳 喱 猁 悝 溧 澧 逦 娌 嫠 骊 缡 枥 栎 轹 戾 砺 詈 罹 锂 鹂  疠 疬 蛎 蜊 蠡 笠 篥 粝 醴 跞 雳 鲡 鳢 黧 蔹 奁 潋 濂 琏 楝  殓 臁 裢 裣 蠊 鲢 立 案 墚 莨 椋 踉 靓 魉 蓼 尥 嘹 獠 寮 缭  钌 鹩 冽 埒 捩 咧 洌 趔 躐 鬣 蔺 啉 嶙 廪 懔 遴 檩 辚 膦 瞵  粼 躏 酃 苓 呤 囹 泠 绫 柃 棂 瓴 聆 蛉 翎 鲮 浏 遛 骝 绺 旒  熘 锍 镏 鹨 鎏 茏 泷 珑 栊 胧 砻 癃 偻 蒌 喽 嵝 镂 瘘 耧 蝼  髅 垆 蓼 撸 噜 闾 泸 渌 漉 逯 璐 栌 榈 橹 轳 辂 辘 氇 胪 膂  镥 稆 鸬 鹭 褛 簏 舻 鲈 挛 孪 滦 脔 娈 栾 鸾 銮 锊 囵 倮 蠃  荦 捋 摞 猡 泺 漯 珞 椤 脶 硌 镙 瘰 雒 麟 ", " 唛 犸 嬷 杩 蟆 劢 荬 霾 墁 幔 缦 熳 镘 颟 螨 鳗 鞔 邙 漭 硭 蟒  袤 茆 峁 泖 瑁 昴 牦 耄 旄 懋 瞀 蟊 髦 麽 莓 嵋 猸 浼 湄 楣  镅 鹛 袂 魅 扪 焖 懑 钔 勐 甍 瞢 懵 朦 礞 虻 蜢 蠓 艋 艨 芈  谧 蘼 咪 嘧 猕 汨 宓 弭 脒 祢 敉 糸 縻 麋 沔 渑 湎 腼 眄 喵  邈 缈 缪 杪 淼 眇 鹋 乜 咩 蠛 篾 苠 岷 闵 泯 缗 玟 珉 愍 黾  鳘 冥 茗 溟 暝 瞑 酩 谟 茉 蓦 馍 嫫 殁 镆 秣 瘼 耱 貊 貘 侔  哞 缪 眸 蛑 蝥 鍪 仫 坶 苜 沐 毪 钼 ", " 捺 肭 镎 衲 鼐 艿 萘 柰 喃 囝 囡 楠 腩 蝻 赧 攮 囔 馕 曩 孬 垴 呶 猱  瑙 硇 铙 蛲 讷 恁 伲 坭 猊 怩 昵 旎 祢 慝 睨 铌 鲵 廿 埝 辇  黏 鲇 鲶 茑 嬲 脲 袅 乜 陧 蘖 嗫 颞 臬 蹑 佞 苎 咛 甯 聍 拗  狃 忸 妞 侬 哝 耨 弩 胬 孥 驽 恧 钕 衄 傩 搦 喏 锘 ", " 讴 怄 瓯 耦 噢", " 葩 杷 钯 筢 俳 蒎 拚 爿 泮 袢 襻 蟠 蹒 彷 滂 逄 螃 匏 狍 庖 脬 疱 辔  帔 旆 锫 醅 霈 湓 堋 嘭 怦 蟛 丕 仳 陂 陴 邳 郫 圮 埤 鼙 芘  擗 吡 噼 庀 淠 媲 纰 枇 甓 睥 罴 铍 癖 裨 疋 蚍 蜱 貔 谝 骈  缏 犏 胼 翩 蹁 剽 莩 嘌 嫖 骠 缥 殍 瞟 螵 苤 氕 姘 嫔 榀 牝  颦 俜 娉 鲆 叵 陂 鄱 泺 珀 攴 钋 钷 钹 皤 笸 裒 掊 匍 噗 溥  濮 璞 氆 镤 镨 蹼 ", " 亟 亓 俟 圻 芑 芪 荠 萁 萋 葺 蕲 嘁 屺 岐 岖 汔 淇 骐 绮 琪 琦 杞 桤  槭 耆 欹 祺 憩 碛 颀 蛴 蜞 綦 綮 蹊 鳍 麒 葜 袷 髂 倩 佥 阡  芊 芡 茜 荨 掮 岍 悭 慊 骞 搴 褰 缱 椠 犍 肷 愆 钤 虔 箬 箝  戕 嫱 樯 戗 炝 锖 锵 镪 襁 蜣 羟 跄 劁 诮 谯 荞 峤 愀 憔 缲  樵 硗 跷 鞒 郄 惬 慊 妾 挈 锲 箧 芩 揿 吣 嗪 噙 廑 溱 檎 锓  矜 覃 螓 衾 苘 圊 檠 磬 蜻 罄 箐 綮 謦 鲭 黥 邛 茕 穹 蛩 筇  跫 銎 俅 巯 犰 湫 逑 遒 楸 赇 虬 蚯 蝤 裘 糗 鳅 鼽 诎 劬 苣  蕖 蘧 岖 衢 阒 璩 觑 氍 朐 祛 磲 鸲 癯 蛐 蠼 麴 瞿 黢 诠 荃  悛 绻 辁 畎 铨 蜷 筌 鬈 阕 阙 悫 逡 ", " 苒 蚺 髯 禳 穰 荛 娆 桡 仞 荏 葚 饪 轫 嵘 狨 榕 肜 蝾 糅 蹂 鞣 蓐 薷  嚅 洳 溽 濡 缛 铷 襦 颥 朊 芮 蕤 枘 睿 蚋 偌 ", " 卅 仨 挲 脎 飒 噻 馓 毵 糁 磉 颡 埽 缫 缲 臊 瘙 鳋 啬 铯 唼 嗄 挲 歃  铩 痧 裟 霎 鲨 剡 讪 鄯 埏 芟 潸 姗 嬗 骟 膻 禅 钐 疝 蟮 舢  跚 鳝 垧 绱 殇 熵 觞 劭 苕 潲 杓 蛸 筲 艄 慑 厍 佘 摺 猞 滠  歙 畲 麝 诜 谂 莘 葚 哂 渖 椹 胂 矧 蜃 嵊 渑 晟 眚 笙 嗜 噬  仕 侍 恃 谥 埘 莳 蓍 弑 轼 贳 炻 铈 螫 舐 筮 酾 豕 鲥 鲺 狩  绶 艏 黍 倏 塾 菽 摅 沭 澍 姝 纾 毹 腧 殳 秫 唰 蟀 闩 涮 泷  孀 蒴 搠 妁 槊 铄 嗣 伺 巳 厮 俟 兕 厶 咝 汜 泗 澌 姒 驷 缌  祀 锶 鸶 耜 蛳 笥 怂 讼 诵 凇 菘 崧 嵩 忪 悚 淞 竦 薮 嗖 嗾  馊 溲 飕 瞍 锼 螋 夙 谡 蔌 嗉 愫 涑 簌 觫 稣 蒜 狻 隋 绥 髓  遂 隧 祟 谇 荽 濉 邃 燧 眭 睢 荪 狲 飧 榫 隼 蓑 嗍 娑 桫 挲 睃 羧 ", " 獭 挞 蹋 拓 嗒 闼 溻 漯 遢 榻 沓 铊 趿 鳎 苔 酞 汰 邰 薹 呔 骀 肽 炱  钛 跆 鲐 檀 痰 潭 谭 毯 袒 叹 郯 澹 昙 忐 钽 锬 镡 傥 帑 溏  瑭 樘 铴 镗 耥 螗 螳 羰 醣 鼗 啕 洮 韬 焘 饕 忒 忑 慝 铽 藤  誊 滕 锑 蹄 啼 嚏 涕 剃 屉 倜 荑 悌 逖 绨 缇 鹈 裼 醍 舔 恬  腆 掭 忝 阗 殄 畋 佻 苕 祧 窕 蜩 笤 粜 龆 鲦 髫 萜 餮 烃 汀  亭 艇 莛 葶 婷 梃 町 铤 蜓 霆 侗 恫 桐 酮 瞳 彤 捅 佟 仝 垌  茼 嗵 峒 恸 潼 砼 钭 骰 堍 荼 菟 钍 酴 抟 彖 疃 蜕 忒 煺 饨  暾 豚 乇 佗 坨 庹 沱 柝 柁 橐 砣 铊 箨 酡 跎 鼍  ", " 佤 娲 腽 烷 皖 惋 宛 婉 腕 剜 芄 莞 菀 纨 绾 琬 脘 畹 蜿 罔 尢 惘 辋  魍 诿 隈 隗 圩 葳 薇 帏 帷 崴 嵬 猥 猬 闱 沩 洧 涠 逶 娓 玮  韪 軎 炜 煨 痿 艉 鲔 紊 刎 阌 汶 璺 雯 蓊 蕹 斡 倭 莴 喔 幄  渥 肟 硪 龌 兀 仵 阢 邬 圬 芴 唔 庑 怃 忤 浯 寤 迕 妩 婺 骛  杌 牾 焐 鹉 鹜 痦 蜈 鋈 鼯 ", " 僖 兮 隰 郗 茜 菥 葸 蓰 奚 唏 徙 饩 阋 浠 淅 屣 玺 樨 曦 觋 欷 歙 熹  禊 禧 皙 穸 裼 蜥 螅 蟋 舄 舾 羲 粞 翕 醯 蹊 鼷 呷 狎 遐 瑕  柙 硖 瘕 罅 黠 冼 苋 莶 藓 岘 猃 暹 娴 氙 燹 祆 鹇 痫 蚬 筅  籼 酰 跣 跹 霰 芗 葙 饷 庠 骧 缃 蟓 鲞 飨 哓 崤 潇 逍 骁 绡  枭 枵 蛸 筱 箫 魈 偕 亵 勰 燮 薤 撷 獬 廨 渫 瀣 邂 绁 缬 榭  榍 颉 躞 忻 衅 囟 馨 昕 歆 镡 鑫 陉 荇 荥 擤 饧 悻 硎 芎 咻  岫 馐 庥 溴 鸺 貅 髹 诩 勖 圩 蓿 洫 浒 溆 顼 栩 煦 砉 盱 胥  糈 醑 儇 谖 萱 揎 泫 渲 漩 璇 楦 暄 炫 煊 碹 铉 镟 痃 薛 谑  噱 泶 踅 鳕 浚 巽 郇 埙 荀 蕈 獯 恂 洵 浔 曛 窨 醺 鲟 ", " 垭 揠 岈 迓 娅 琊 桠 氩 砑 睚 痖 厣 赝 剡 俨 偃 兖 谳 阽 郾 鄢 芫 菸  崦 恹 闫 阏 湮 滟 妍 嫣 琰 檐 晏 胭 腌 焱 罨 筵 酽 魇 餍 鼹  疡 炀 烊 恙 蛘 舀 钥 夭 爻 吆 崾 徭 幺 珧 杳 轺 曜 肴 铫 鹞  窈 繇 鳐 噎 耶 掖 曳 腋 拽 靥 谒 邺 揶 晔 烨 铘 铱 翌 绎 刈  劓 仡 佚 佾 诒 圯 埸 懿 苡 荑 薏 弈 奕 挹 弋 呓 咦 咿 嗌 噫  峄 嶷 猗 饴 怿 怡 悒 殪 轶 贻 欹 旖 熠 眙 钇 镒 镱 痍 瘗 癔  翊 蜴 舣 羿 翳 酏 黟 胤 鄞 圻 垠 堙 茚 吲 喑 狺 夤 洇 湮 氤  铟 瘾 窨 蚓 霪 龈 嬴 郢 茔 荥 莺 萦 蓥 撄 嘤 膺 滢 潆 瀛 瑛  璎 楹 媵 鹦 瘿 颍 罂 臃 痈 雍 踊 咏 泳 恿 俑 壅 墉 喁 慵 邕  镛 甬 鳙 饔 釉 卣 攸 侑 莠 莜 莸 尢 呦 囿 宥 柚 猷 牖 铕 疣  蚰 蚴 蝣 蝤 繇 鱿 黝 鼬 禺 毓 伛 俣 谀 谕 萸 菀 蓣 揄 圄 圉  嵛 狳 饫 馀 庾 阈 鬻 妪 妤 纡 瑜 昱 觎 腴 腧 欤 於 煜 熨 燠  聿 钰 鹆 鹬 瘐 瘀 窬 窳 蜮 蝓 竽 臾 舁 雩 龉 垸 塬 芫 掾 圜  沅 媛 瑗 橼 爰 眢 鸢 螈 箢 鼋 龠 瀹 栎 樾 刖 钺 郧 陨 蕴 酝  晕 韵 郓 芸 狁 恽 愠 纭 韫 殒 昀 氲 熨 ", " 咂 甾 拶 瓒 昝 簪 糌 趱 錾 奘 驵 臧 唣 仄 赜 啧 帻 迮 昃 笮 箦 舴 谮  缯 甑 罾 锃 揸 吒 哳 喋 楂 砟 痄 蚱 齄 砦 瘵 谵 搌 旃 瘴 仉  鄣 幛 嶂 獐 嫜 璋 蟑 肇 诏 啁 棹 钊 笊 锗 蔗 谪 摺 柘 辄 磔  鹧 褶 蜇 赭 甄 砧 臻 贞 侦 枕 疹 圳 蓁 浈 溱 缜 桢 椹 榛 轸  赈 胗 朕 祯 畛 稹 鸩 箴 帧 诤 峥 徵 钲 卮 陟 郅 埴 芷 摭 帙  徵 忮 彘 咫 骘 栉 枳 栀 桎 轵 轾 贽 胝 膣 祉 祗 黹 雉 鸷 痣  蛭 絷 酯 跖 踬 踯 豸 觯 冢 忪 锺 螽 舯 踵 荮 啁 妯 纣 绉 胄  碡 籀 繇 酎 伫 侏 邾 茱 洙 渚 潴 杼 槠 橥 炷 铢 疰 瘃 褚 竺  箸 舳 翥 躅 麈 挝 曳 拽 篆 啭 馔 沌 颛 僮 奘 骓 缒 倬 诼 擢  浞 涿 濯 棹 焯 禚 斫 镯 茈 呲 嵫 姊 孳 缁 梓 辎 赀 恣 眦 锱  秭 耔 笫 粢 趑 觜 訾 龇 鲻 髭 偬 枞 腙 粽 诹 陬 鄹 驺 鲰 诅  俎 菹 镞 攥 缵 躜 蕞 撙 樽 鳟 柞 阼 唑 嘬 怍 胙 祚 笮 "};
//        int len = character.getBytes().length;
//        int i = 0;
//        int var19 = -1;
//
//        while(true) {
//            while(i < len) {
//                int j = character.getBytes()[i] & 255;
//                if (j < 128) {
//                    ++var19;
//                    result.append((char)character.getBytes()[i]);
//                    ++i;
//                } else {
//                    ++var19;
//                    long n = (long)(character.getBytes()[i] & 255);
//                    n *= 256L;
//                    long m = (long)(character.getBytes()[i + 1] & 255);
//                    long p = n + m;
//                    char firstzm;
//                    if (p == 41891L) {
//                        firstzm = '#';
//                    } else if (p == 41892L) {
//                        firstzm = '$';
//                    } else if (p == 41901L) {
//                        firstzm = '-';
//                    } else if (p == 41458L) {
//                        firstzm = '@';
//                    } else if (p == 41407L) {
//                        firstzm = ']';
//                    } else if (p == 41406L) {
//                        firstzm = '[';
//                    } else if (p == 41399L) {
//                        firstzm = '>';
//                    } else if (p == 41398L) {
//                        firstzm = '<';
//                    } else if (p == 41279L) {
//                        firstzm = '.';
//                    } else if (p == 41896L) {
//                        firstzm = '(';
//                    } else if (p == 41897L) {
//                        firstzm = ')';
//                    } else if (p == 41387L) {
//                        firstzm = '~';
//                    } else if (p == 41893L) {
//                        firstzm = '%';
//                    } else if (p == 41465L) {
//                        firstzm = '&';
//                    } else if (p == 41915L) {
//                        firstzm = ';';
//                    } else if (p == 41900L) {
//                        firstzm = ',';
//                    } else if (p == 41889L) {
//                        firstzm = '?';
//                    } else if (p == 41919L) {
//                        firstzm = '!';
//                    } else if (p == 41904L) {
//                        firstzm = '0';
//                    } else if (p == 41905L) {
//                        firstzm = '1';
//                    } else if (p == 41906L) {
//                        firstzm = '2';
//                    } else if (p == 41907L) {
//                        firstzm = '3';
//                    } else if (p == 41908L) {
//                        firstzm = '4';
//                    } else if (p == 41909L) {
//                        firstzm = '5';
//                    } else if (p == 41910L) {
//                        firstzm = '6';
//                    } else if (p == 41911L) {
//                        firstzm = '7';
//                    } else if (p == 41912L) {
//                        firstzm = '8';
//                    } else if (p == 41913L) {
//                        firstzm = '9';
//                    } else if (p == 41921L) {
//                        firstzm = 'A';
//                    } else if (p == 41922L) {
//                        firstzm = 'B';
//                    } else if (p == 41923L) {
//                        firstzm = 'C';
//                    } else if (p == 41924L) {
//                        firstzm = 'D';
//                    } else if (p == 41925L) {
//                        firstzm = 'E';
//                    } else if (p == 41926L) {
//                        firstzm = 'F';
//                    } else if (p == 41927L) {
//                        firstzm = 'G';
//                    } else if (p == 41928L) {
//                        firstzm = 'H';
//                    } else if (p == 41929L) {
//                        firstzm = 'I';
//                    } else if (p == 41930L) {
//                        firstzm = 'G';
//                    } else if (p == 41931L) {
//                        firstzm = 'K';
//                    } else if (p == 41932L) {
//                        firstzm = 'L';
//                    } else if (p == 41933L) {
//                        firstzm = 'M';
//                    } else if (p == 41934L) {
//                        firstzm = 'N';
//                    } else if (p == 41935L) {
//                        firstzm = 'O';
//                    } else if (p == 41936L) {
//                        firstzm = 'P';
//                    } else if (p == 41937L) {
//                        firstzm = 'Q';
//                    } else if (p == 41938L) {
//                        firstzm = 'R';
//                    } else if (p == 41939L) {
//                        firstzm = 'S';
//                    } else if (p == 41940L) {
//                        firstzm = 'T';
//                    } else if (p == 41941L) {
//                        firstzm = 'U';
//                    } else if (p == 41942L) {
//                        firstzm = 'V';
//                    } else if (p == 41943L) {
//                        firstzm = 'W';
//                    } else if (p == 41944L) {
//                        firstzm = 'X';
//                    } else if (p == 41945L) {
//                        firstzm = 'Y';
//                    } else if (p == 41946L) {
//                        firstzm = 'Z';
//                    } else if (p >= 45217L && p <= 45252L) {
//                        firstzm = 'A';
//                    } else if (p >= 45253L && p <= 45760L) {
//                        firstzm = 'B';
//                    } else if (p >= 45761L && p <= 46317L) {
//                        firstzm = 'C';
//                    } else if (p >= 46318L && p <= 46825L) {
//                        firstzm = 'D';
//                    } else if (p >= 46826L && p <= 47009L) {
//                        firstzm = 'E';
//                    } else if (p >= 47010L && p <= 47296L) {
//                        firstzm = 'F';
//                    } else if (p >= 47297L && p <= 47613L) {
//                        firstzm = 'G';
//                    } else if (p >= 47614L && p <= 48118L) {
//                        firstzm = 'H';
//                    } else if (p >= 48119L && p <= 49061L) {
//                        firstzm = 'J';
//                    } else if (p >= 49062L && p <= 49323L) {
//                        firstzm = 'K';
//                    } else if (p >= 49324L && p <= 49895L) {
//                        firstzm = 'L';
//                    } else if (p >= 49896L && p <= 50370L) {
//                        firstzm = 'M';
//                    } else if (p >= 50371L && p <= 50613L) {
//                        firstzm = 'N';
//                    } else if (p >= 50614L && p <= 50621L) {
//                        firstzm = 'O';
//                    } else if (p >= 50622L && p <= 50905L) {
//                        firstzm = 'P';
//                    } else if (p >= 50906L && p <= 51386L) {
//                        firstzm = 'Q';
//                    } else if (p >= 51387L && p <= 51445L) {
//                        firstzm = 'R';
//                    } else if (p >= 51446L && p <= 52217L) {
//                        firstzm = 'S';
//                    } else if (p >= 52218L && p <= 52697L) {
//                        firstzm = 'T';
//                    } else if (p >= 52698L && p <= 52979L) {
//                        firstzm = 'W';
//                    } else if (p >= 52980L && p <= 53688L) {
//                        firstzm = 'X';
//                    } else if (p >= 53689L && p <= 54480L) {
//                        firstzm = 'Y';
//                    } else if (p >= 54481L && p <= 55289L) {
//                        firstzm = 'Z';
//                    } else {
//                        firstzm = '*';
//                    }
//
//                    if (firstzm == '*') {
//                        byte[] tmp = new byte[]{character.getBytes()[i], character.getBytes()[i + 1]};
//                        String s = new String(tmp);
//
//                        int l;
//                        for(l = 0; l < 23; ++l) {
//                            if (strhz[l].indexOf(s) >= 0) {
//                                result.append(zm1[l]);
//                                break;
//                            }
//                        }
//
//                        if (l == 23) {
//                            result.append('*');
//                        }
//                    } else {
//                        result.append(firstzm);
//                    }
//
//                    ++i;
//                    ++i;
//                }
//            }
//
//            return result.toString();
//        }
//    }

    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();

        for(int i = 0; i < bGBK.length; ++i) {
            strBuf.append(Integer.toHexString(bGBK[i] & 255));
        }

        return strBuf.toString();
    }

    public static String getMatchedString(String regex, String text) {
        Pattern pattern = Pattern.compile(regex, 2);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(2) : null;
    }

    public static boolean isContains(String lineText, String word) {
        Pattern pattern = Pattern.compile(word, 2);
        Matcher matcher = pattern.matcher(lineText);
        return matcher.find();
    }

    public static String parseSqlCols(String sql) {
        String regex = "(select)(.+)(from)";
        return getMatchedString(regex, sql);
    }

    public static String parseSqlTables(String sql) {
        String regex = "";
        if (isContains(sql, "\\s+where\\s+")) {
            regex = "(from)(.+)(where)";
        } else {
            regex = "(from)(.+)($)";
        }

        return getMatchedString(regex, sql);
    }

    public static String parseSqlConditions(String sql) {
        String regex = "";
        if (isContains(sql, "\\s+where\\s+")) {
            if (isContains(sql, "group\\s+by")) {
                regex = "(where)(.+)(group\\s+by)";
            } else if (isContains(sql, "order\\s+by")) {
                regex = "(where)(.+)(order\\s+by)";
            } else {
                regex = "(where)(.+)($)";
            }

            return getMatchedString(regex, sql);
        } else {
            return "";
        }
    }

    public static String parseSqlGroupCols(String sql) {
        String regex = "";
        if (isContains(sql, "group\\s+by")) {
            if (isContains(sql, "order\\s+by")) {
                regex = "(group\\s+by)(.+)(order\\s+by)";
            } else {
                regex = "(group\\s+by)(.+)($)";
            }

            return getMatchedString(regex, sql);
        } else {
            return "";
        }
    }

    public static String parseSqlOrderCols(String sql) {
        String regex = "";
        if (isContains(sql, "order\\s+by")) {
            regex = "(order\\s+by)(.+)($)";
            return getMatchedString(regex, sql);
        } else {
            return "";
        }
    }

    public static String xmlEncode(String s) {
        if (s == null) {
            return s;
        } else {
            for(int i = 0; i < SIGN_GENERAL.length; ++i) {
                String sign = SIGN_GENERAL[i];
                String signt = SIGN_TRANSLATE[i];
                s = replace(s, sign, signt, false);
            }

            return s;
        }
    }

    public static String xmlDecode(String s) {
        if (s == null) {
            return s;
        } else {
            for(int i = 0; i < SIGN_GENERAL.length; ++i) {
                String sign = SIGN_GENERAL[i];
                String signt = SIGN_TRANSLATE[i];
                s = replace(s, signt, sign, false);
            }

            return s;
        }
    }

    public static String getRandomString(int length) {
        String str = "abcdefghjkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ23456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(56);
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }

    public static String getRandomNum(int length) {
        long c = (long)((Math.random() * 9.0D + 1.0D) * Math.pow(10.0D, (double)(length - 1)));
        return c + "";
    }

    public static String utf8ToUnicode(String inStr) {
        char[] myBuffer = inStr.toCharArray();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < inStr.length(); ++i) {
            UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
            if (ub == UnicodeBlock.BASIC_LATIN) {
                sb.append(myBuffer[i]);
            } else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                int j = myBuffer[i] - 'ﻠ';
                sb.append((char)j);
            } else {
                short s = (short)myBuffer[i];
                String hexS = Integer.toHexString(s);
                String unicode = "\\u" + hexS;
                sb.append(unicode.toLowerCase());
            }
        }

        return sb.toString();
    }

    public static String unicodeToUtf8(String theString) {
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        int x = 0;

        while(true) {
            while(true) {
                while(x < len) {
                    char aChar = theString.charAt(x++);
                    if (aChar == '\\') {
                        aChar = theString.charAt(x++);
                        if (aChar == 'u') {
                            int value = 0;

                            for(int i = 0; i < 4; ++i) {
                                aChar = theString.charAt(x++);
                                switch(aChar) {
                                    case '0':
                                    case '1':
                                    case '2':
                                    case '3':
                                    case '4':
                                    case '5':
                                    case '6':
                                    case '7':
                                    case '8':
                                    case '9':
                                        value = (value << 4) + aChar - 48;
                                        break;
                                    case ':':
                                    case ';':
                                    case '<':
                                    case '=':
                                    case '>':
                                    case '?':
                                    case '@':
                                    case 'G':
                                    case 'H':
                                    case 'I':
                                    case 'J':
                                    case 'K':
                                    case 'L':
                                    case 'M':
                                    case 'N':
                                    case 'O':
                                    case 'P':
                                    case 'Q':
                                    case 'R':
                                    case 'S':
                                    case 'T':
                                    case 'U':
                                    case 'V':
                                    case 'W':
                                    case 'X':
                                    case 'Y':
                                    case 'Z':
                                    case '[':
                                    case '\\':
                                    case ']':
                                    case '^':
                                    case '_':
                                    case '`':
                                    default:
                                        throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                                    case 'A':
                                    case 'B':
                                    case 'C':
                                    case 'D':
                                    case 'E':
                                    case 'F':
                                        value = (value << 4) + 10 + aChar - 65;
                                        break;
                                    case 'a':
                                    case 'b':
                                    case 'c':
                                    case 'd':
                                    case 'e':
                                    case 'f':
                                        value = (value << 4) + 10 + aChar - 97;
                                }
                            }

                            outBuffer.append((char)value);
                        } else {
                            if (aChar == 't') {
                                aChar = '\t';
                            } else if (aChar == 'r') {
                                aChar = '\r';
                            } else if (aChar == 'n') {
                                aChar = '\n';
                            } else if (aChar == 'f') {
                                aChar = '\f';
                            }

                            outBuffer.append(aChar);
                        }
                    } else {
                        outBuffer.append(aChar);
                    }
                }

                return outBuffer.toString();
            }
        }
    }

    public static String getDistance(Integer d) {
        if (d == null) {
            return "";
        } else if (d < 1000) {
            return d + "m";
        } else {
            DecimalFormat format = new DecimalFormat("#0.0");
            return format.format(d.doubleValue() / 1000.0D) + "km";
        }
    }

    public static String getDistance(Long d) {
        if (d == null) {
            return "";
        } else if (d < 1000L) {
            return d + "m";
        } else {
            DecimalFormat format = new DecimalFormat("#0.0");
            return format.format(d.doubleValue() / 1000.0D) + "km";
        }
    }

    public static String converToString(String[] ig) {
        String str = "";
        if (ig != null && ig.length > 0) {
            for(int i = 0; i < ig.length; ++i) {
                str = str + ig[i] + ",";
            }
        }

        str = str.substring(0, str.length() - 1);
        return str;
    }

    public static String converToString(String[] ig, String split) {
        String str = "";
        if (ig != null && ig.length > 0) {
            for(int i = 0; i < ig.length; ++i) {
                str = str + ig[i] + split;
            }
        }

        str = str.substring(0, str.length() - 1);
        return str;
    }

    public static String listToString(List list, String split) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for(int i = 0; i < list.size(); ++i) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + split);
                } else {
                    sb.append(list.get(i));
                }
            }
        }

        return sb.toString();
    }

    public static String listToString(List list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for(int i = 0; i < list.size(); ++i) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }

        return sb.toString();
    }

    public static String bigToString(BigDecimal a) {
        if (a == null) {
            return "";
        } else if (!a.toString().endsWith("0")) {
            return a.toString();
        } else {
            int i = a.scale();
            String str = "";
            int k = a.toString().indexOf(".");
            if (k == -1) {
                return a.toString();
            } else if (k == a.toString().length() - 1) {
                return a.toString().substring(0, k);
            } else if (a.toString().substring(k + 1).equals(getZero(i))) {
                return a.toString().substring(0, k);
            } else {
                for(int j = k + 2; j < a.toString().length(); ++j) {
                    String var10000 = a.toString().substring(j);
                    --i;
                    if (var10000.equals(getZero(i))) {
                        str = a.toString().substring(0, j);
                        break;
                    }
                }

                return str;
            }
        }
    }

    private static String getZero(int a) {
        if (a <= 0) {
            return "0";
        } else {
            StringBuffer buffer = new StringBuffer();

            for(int i = 0; i < a; ++i) {
                buffer.append('0');
            }

            return buffer.toString();
        }
    }

    public static String filterEmoji(String source) {
        if (source != null) {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[☀-⟿]", 66);
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("?");
                return source;
            } else {
                return source;
            }
        } else {
            return source;
        }
    }

    public static String formatPhone(String phone) {
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] = (char)(arr[0] + 32);
            return new String(arr);
        } else {
            return str;
        }
    }

    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] = (char)(arr[0] - 32);
            return new String(arr);
        } else {
            return str;
        }
    }

    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        } else {
            int len = str.length();
            if (len == 0) {
                return true;
            } else {
                int i = 0;

                while(i < len) {
                    switch(str.charAt(i)) {
                        case '\t':
                        case '\n':
                        case '\r':
                        case ' ':
                            ++i;
                            break;
                        default:
                            return false;
                    }
                }

                return true;
            }
        }
    }

    public static boolean notBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean notBlank(String... strings) {
        if (strings == null) {
            return false;
        } else {
            String[] var1 = strings;
            int var2 = strings.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String str = var1[var3];
                if (isBlank(str)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean notNull(Object... paras) {
        if (paras == null) {
            return false;
        } else {
            Object[] var1 = paras;
            int var2 = paras.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Object obj = var1[var3];
                if (obj == null) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String join(String[] stringArray) {
        StringBuilder sb = new StringBuilder();
        String[] var2 = stringArray;
        int var3 = stringArray.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String s = var2[var4];
            sb.append(s);
        }

        return sb.toString();
    }

    public static String join(String[] stringArray, String separator) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < stringArray.length; ++i) {
            if (i > 0) {
                sb.append(separator);
            }

            sb.append(stringArray[i]);
        }

        return sb.toString();
    }

    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
