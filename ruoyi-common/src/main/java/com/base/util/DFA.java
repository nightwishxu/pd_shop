package com.base.util;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class DFA {
    public static Map<Character, DFA.SingleChar> keyMap = null;
    static boolean isMax = true;
    private static final Logger logger = Logger.getLogger(DFA.class);

    public DFA() {
    }

    private static void loadKeywords(String keyStr, String separator) {
        String[] keys = keyStr.split(separator);
        String[] var3 = keys;
        int var4 = keys.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String key = var3[var5];
            if (key != null && !key.isEmpty()) {
                char firstC = key.charAt(0);
                int len = key.length();
                DFA.SingleChar keyChar = (DFA.SingleChar)keyMap.get(firstC);
                if (keyChar == null) {
                    keyChar = new DFA.SingleChar();
                    keyChar.setCh(firstC);
                    keyMap.put(firstC, keyChar);
                }

                if (len == 1) {
                    keyChar.setEnd(true);
                }

                for(int i = 1; i < len; ++i) {
                    boolean isEnd = i == len - 1;
                    char c = key.charAt(i);
                    if (keyChar.hasNext()) {
                        DFA.SingleChar sc = keyChar.findNextChar(c);
                        if (sc == null) {
                            keyChar = keyChar.addNextChar(c, isEnd);
                        } else {
                            keyChar = sc;
                            if (isEnd) {
                                sc.setEnd(isEnd);
                            }
                        }
                    } else {
                        keyChar.setNextChars(new DFA.CharList());
                        keyChar = keyChar.addNextChar(c, isEnd);
                    }
                }
            }
        }

    }

    public static void reloadData() {
        keyMap.clear();
        String txt = "";
        String filePath = CoreConstants.getProperty("social.sensitive_word_filepath");
        if (StringUtil.isNotBlank(filePath)) {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                try {
                    txt = IoUtil.read(new FileInputStream(file), "utf-8");
                } catch (Exception var4) {
                    var4.printStackTrace();
                    logger.error("敏感词加载失败~");
                }
            }
        }

        if (txt.length() > 0) {
            loadKeywords(txt, ",");
        }

    }

    private static void loadData() {
        if (keyMap == null) {
            keyMap = new HashMap();
        }

        String txt = "";
        String filePath = CoreConstants.getProperty("social.sensitive_word_filepath");
        if (StringUtil.isNotBlank(filePath)) {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                try {
                    txt = IoUtil.read(new FileInputStream(file), "utf-8");
                } catch (Exception var4) {
                    var4.printStackTrace();
                    logger.error("敏感词加载失败~");
                }
            }
        }

        if (txt.length() > 0) {
            loadKeywords(txt, ",");
        }

    }

    public static boolean hasKeys(String str) {
        if (keyMap == null) {
            keyMap = new HashMap();
            loadData();
        }

        if (StringUtil.isBlank(str)) {
            return false;
        } else {
            char[] chars = str.toCharArray();
            int klen = 0;
            int maxlen = 0;
            int i = 0;
            int len = chars.length;

            while(true) {
                while(i < len) {
                    char c = chars[i];
                    DFA.SingleChar keyChar = (DFA.SingleChar)keyMap.get(c);
                    if (keyChar != null) {
                        do {
                            ++klen;
                            if (i + klen >= len) {
                                if (keyChar.isEnd()) {
                                    maxlen = klen;
                                }
                                break;
                            }

                            c = chars[i + klen];
                            if (keyChar.isEnd()) {
                                maxlen = klen;
                                if (!isMax) {
                                    break;
                                }
                            }
                        } while((keyChar = keyChar.findNextChar(c)) != null);

                        if (maxlen > 0) {
                            return true;
                        }

                        ++i;
                        klen = 0;
                        maxlen = 0;
                    } else {
                        ++i;
                    }
                }

                return false;
            }
        }
    }

    public static String replaceKeys(String str) {
        if (keyMap == null) {
            keyMap = new HashMap();
            loadData();
        }

        if (StringUtil.isBlank(str)) {
            return "";
        } else {
            char[] chars = str.toCharArray();
            int klen = 0;
            int maxlen = 0;
            int i = 0;
            int len = chars.length;

            while(true) {
                while(i < len) {
                    char c = chars[i];
                    DFA.SingleChar keyChar = (DFA.SingleChar)keyMap.get(c);
                    if (keyChar != null) {
                        do {
                            ++klen;
                            if (i + klen >= len) {
                                if (keyChar.isEnd()) {
                                    maxlen = klen;
                                }
                                break;
                            }

                            c = chars[i + klen];
                            if (keyChar.isEnd()) {
                                maxlen = klen;
                                if (!isMax) {
                                    break;
                                }
                            }
                        } while((keyChar = keyChar.findNextChar(c)) != null);

                        if (maxlen > 0) {
                            Arrays.fill(chars, i, i += maxlen, '*');
                        } else {
                            ++i;
                        }

                        klen = 0;
                        maxlen = 0;
                    } else {
                        ++i;
                    }
                }

                return String.valueOf(chars);
            }
        }
    }

    public static void main(String[] args) {
        String txt = "安局办公楼你好中国把学生人大主任我真是需要办理资格女优干他乱交，他练过法轮功我不是骗人的安局办公楼安局办公楼安局办";
        System.out.println(replaceKeys(txt));
    }

    static class CharList {
        private int startChar;
        private int endChar;
        private List<DFA.SingleChar> chars = new ArrayList();

        CharList() {
        }

        public DFA.SingleChar findChar(char c) {
            if (c < this.startChar) {
                return null;
            } else if (c > this.endChar) {
                return null;
            } else {
                int i = 0;

                for(int len = this.chars.size(); i < len; ++i) {
                    DFA.SingleChar sc = (DFA.SingleChar)this.chars.get(i);
                    if (sc.getCh() == c) {
                        return sc;
                    }
                }

                return null;
            }
        }

        public DFA.SingleChar addNewChar(char c, boolean isEnd) {
            DFA.SingleChar sc = new DFA.SingleChar();
            sc.setCh(c);
            sc.setEnd(isEnd);
            if (c <= this.startChar) {
                this.chars.add(0, sc);
                this.startChar = c;
            } else if (c >= this.endChar) {
                this.chars.add(this.chars.size(), sc);
                this.endChar = c;
            } else {
                this.chars.add(sc);
                Collections.sort(this.chars);
            }

            return sc;
        }

        public int getStartChar() {
            return this.startChar;
        }

        public void setStartChar(int startChar) {
            this.startChar = startChar;
        }

        public int getEndChar() {
            return this.endChar;
        }

        public void setEndChar(int endChar) {
            this.endChar = endChar;
        }
    }

    static class SingleChar implements Comparable<DFA.SingleChar> {
        private Character ch;
        private boolean isEnd = false;
        private DFA.CharList nextChars = null;

        SingleChar() {
        }

        public DFA.SingleChar findNextChar(char c) {
            return this.hasNext() ? this.nextChars.findChar(c) : null;
        }

        public DFA.SingleChar addNextChar(char c, boolean flag) {
            return this.nextChars.addNewChar(c, flag);
        }

        public boolean hasNext() {
            return this.nextChars != null;
        }

        public Character getCh() {
            return this.ch;
        }

        public void setCh(Character ch) {
            this.ch = ch;
        }

        public boolean isEnd() {
            return this.isEnd;
        }

        public void setEnd(boolean isEnd) {
            this.isEnd = isEnd;
        }

        public DFA.CharList getNextChars() {
            return this.nextChars;
        }

        public void setNextChars(DFA.CharList nextChars) {
            this.nextChars = nextChars;
        }

        public int compareTo(DFA.SingleChar o) {
            if (o.ch > this.ch) {
                return -1;
            } else {
                return o.ch < this.ch ? 1 : 0;
            }
        }
    }
}
