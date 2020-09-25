package com.base.pay;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PayFilter {
    String aliPayReturn(HttpServletRequest var1, HttpServletResponse var2, String var3);

    String upmpReturn(HttpServletRequest var1, HttpServletResponse var2, String var3);

    void afterPay(String var1, String var2, String var3, String var4, BigDecimal var5, Integer var6, String var7, HttpServletRequest var8, HttpServletResponse var9) throws Exception;
}
