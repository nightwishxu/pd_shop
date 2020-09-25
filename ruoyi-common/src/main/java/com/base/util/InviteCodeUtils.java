package com.base.util;

public class InviteCodeUtils {


    static  char[] allCharacters = { '0','1','2','3','4','5','6','7','8','9',
            'A','B','C','D','E','F','G','H','I','J',
            'K','L','M','N','O','P','Q','R','S','T',
            'U','V','W','X','Y','Z','a','b','c','d',
            'e','f','g','h','i','j','k','l','m','n',
            'o','p','q','r','s','t','u','v','w','x',
            'y','z'};

    //默认长度10位
    public static final  int s = 10;

    public static String getCode(){
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<s;i++){
            int l = (int)(Math.random()*62);
            sb.append(allCharacters[l]);
        }
        return sb.toString();
    }
}
