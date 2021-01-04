package com.demo.constant;

import com.base.util.PropertySupport;
import com.base.util.StringUtils;

public class Request {
    public static final String CHANNEL = "channel";
    public static final String LOCALE = "locale";
    public static final String DATA = "data";
    public static final String SIGNATURE = "signature";

//    public static final String PLAT_ID = "B444327100E45C1CE05311016B0ACDDD";
    // public static final String PLAT_ID = "2EC6FDB756C54718E050007F010041F3";
    // public static final String PLAT_ID = "30E917BA72FE6807E05311016B0AE73B";// 测试
    // public static final String PLAT_ID = "32015409EEB52975E0538E02030A2087";// 生产
    // public static final String PLAT_ID = "03B4B53D0887457B9B976A97BF7FF134";//贺伟


    public static  String PLAT_ID = PropertySupport.getProperty("plat.id");
    // public static final String PLAT_ID = "2EC6FDB756C54718E050007F010041F3";
    // public static final String PLAT_ID = "30E917BA72FE6807E05311016B0AE73B";// 测试
    // public static final String PLAT_ID = "32015409EEB52975E0538E02030A2087";// 生产
    // public static final String PLAT_ID = "03B4B53D0887457B9B976A97BF7FF134";//贺伟


    public static String getPlatId() {
        if (StringUtils.isBlank(PLAT_ID)){
            PLAT_ID = PropertySupport.getProperty("plat.id");
            return PLAT_ID;
        }
        return PLAT_ID;
    }

    public static void setPlatId(String platId) {
        PLAT_ID = platId;
    }
}
