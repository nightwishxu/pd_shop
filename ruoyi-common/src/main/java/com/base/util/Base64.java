package com.base.util;

import com.base.util.StringUtil;
import java.nio.charset.Charset;

public final class Base64 {
    private static final byte[] STANDARD_ENCODE_TABLE = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] URL_SAFE_ENCODE_TABLE = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] DECODE_TABLE = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    private Base64() {
    }

    public static byte[] encode(byte[] arr, boolean lineSep) {
        return encode(arr, lineSep, false);
    }

    public static byte[] encode(byte[] arr, boolean isMultiLine, boolean isUrlSafe) {
        int len = arr != null ? arr.length : 0;
        if (len == 0) {
            return new byte[0];
        } else {
            int evenlen = len / 3 * 3;
            int cnt = (len - 1) / 3 + 1 << 2;
            int destlen = cnt + (isMultiLine ? (cnt - 1) / 76 << 1 : 0);
            byte[] dest = new byte[destlen];
            byte[] encodeTable = isUrlSafe ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
            int left = 0;
            int d = 0;
            int cc = 0;

            while(left < evenlen) {
                int i = (arr[left++] & 255) << 16 | (arr[left++] & 255) << 8 | arr[left++] & 255;
                dest[d++] = encodeTable[i >>> 18 & 63];
                dest[d++] = encodeTable[i >>> 12 & 63];
                dest[d++] = encodeTable[i >>> 6 & 63];
                dest[d++] = encodeTable[i & 63];
                if (isMultiLine) {
                    ++cc;
                    if (cc == 19 && d < destlen - 2) {
                        dest[d++] = 13;
                        dest[d++] = 10;
                        cc = 0;
                    }
                }
            }

            left = len - evenlen;
            if (left > 0) {
                d = (arr[evenlen] & 255) << 10 | (left == 2 ? (arr[len - 1] & 255) << 2 : 0);
                dest[destlen - 4] = encodeTable[d >> 12];
                dest[destlen - 3] = encodeTable[d >>> 6 & 63];
                dest[destlen - 2] = left == 2 ? encodeTable[d & 63] : 61;
                dest[destlen - 1] = 61;
            }

            return dest;
        }
    }

    public static String encode(String source) {
        return encode(source, Charset.defaultCharset());
    }

    public static String encode(String source, String charset) {
        return encode(StringUtil.bytes(source, charset), charset);
    }

    public static String encode(String source, Charset charset) {
        return encode(StringUtil.bytes(source, charset), charset);
    }

    public static String encode(byte[] source) {
        return encode(source, Charset.defaultCharset());
    }

    public static String encode(byte[] source, String charset) {
        return StringUtil.str(encode(source, false), charset);
    }

    public static String encode(byte[] source, Charset charset) {
        return StringUtil.str(encode(source, false), charset);
    }

    public static String decodeStr(String source) {
        return decodeStr(source, Charset.defaultCharset());
    }

    public static String decodeStr(String source, String charset) {
        return StringUtil.str(decode(source, charset), charset);
    }

    public static String decodeStr(String source, Charset charset) {
        return StringUtil.str(decode(source, charset), charset);
    }

    public static byte[] decode(String source) {
        return decode(source, Charset.defaultCharset());
    }

    public static byte[] decode(String source, String charset) {
        return decode(StringUtil.bytes(source, charset));
    }

    public static byte[] decode(String source, Charset charset) {
        return decode(StringUtil.bytes(source, charset));
    }

    public static byte[] decode(byte[] arr) {
        int length = arr.length;
        if (length == 0) {
            return new byte[0];
        } else {
            int sndx = 0;
            int endx = length - 1;
            int pad = arr[endx] == 61 ? (arr[endx - 1] == 61 ? 2 : 1) : 0;
            int cnt = endx - sndx + 1;
            int sepCnt = length > 76 ? (arr[76] == 13 ? cnt / 78 : 0) << 1 : 0;
            int len = ((cnt - sepCnt) * 6 >> 3) - pad;
            byte[] dest = new byte[len];
            byte[] decodeTable = DECODE_TABLE;
            int d = 0;
            int i = 0;
            int r = len / 3 * 3;

            while(d < r) {
                i = decodeTable[arr[sndx++]] << 18 | decodeTable[arr[sndx++]] << 12 | decodeTable[arr[sndx++]] << 6 | decodeTable[arr[sndx++]];
                dest[d++] = (byte)(i >> 16);
                dest[d++] = (byte)(i >> 8);
                dest[d++] = (byte)i;
                if (sepCnt > 0) {
                    ++i;
                    if (i == 19) {
                        sndx += 2;
                        i = 0;
                    }
                }
            }

            if (d < len) {
                i = 0;

                for(r = 0; sndx <= endx - pad; ++r) {
                    i |= decodeTable[arr[sndx++]] << 18 - r * 6;
                }

                for(r = 16; d < len; r -= 8) {
                    dest[d++] = (byte)(i >> r);
                }
            }

            return dest;
        }
    }
}
