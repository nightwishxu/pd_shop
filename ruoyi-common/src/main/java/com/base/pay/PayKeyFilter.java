package com.base.pay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PayKeyFilter {
    String[] getAliKey(String var1, String var2, String var3, String var4, HttpServletRequest var5, HttpServletResponse var6);

    String getWxKey(String var1, String var2, String var3, String var4, HttpServletRequest var5, HttpServletResponse var6);
        }
