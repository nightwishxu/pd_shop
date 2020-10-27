package com.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.base.exception.SystemException;

public class JSONUtils {
    private static final SerializerFeature[] serializerFeatures;

    public JSONUtils() {
    }

    public static String serialize(Object obj) {
        String ret = "";

        try {
            ret = JSON.toJSONString(obj, serializerFeatures);
            return ret;
        } catch (Exception var3) {
            throw new SystemException("JSON序列化结果异常:" + var3.getMessage());
        }
    }

    public static <T> T deserialize(String jsonStr, Class<T> clazz) {
        if (StringUtil.isBlank(jsonStr)) {
            return null;
        } else {
            try {
                return JSON.parseObject(jsonStr.replace("\n", ""), clazz);
            } catch (Exception var3) {
                throw new SystemException("JSON反序列化结果异常:" + var3.getMessage());
            }
        }
    }


//    public static <T> List<T> deserializeList(String jsonStr, Class<T> clazz) {
//        List<T> result = new ArrayList();
//        List<Map> resultList = (List)deserialize(jsonStr, List.class);
//        if (CollectionUtils.isNotEmpty(resultList)) {
//            Iterator var4 = resultList.iterator();
//
//            while(var4.hasNext()) {
//                Map map = (Map)var4.next();
//                result.add(BeanUtilsEx.convert(map, clazz));
//            }
//        }
//
//        return result;
//    }

    static {
        serializerFeatures = new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.QuoteFieldNames, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse};
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    }
}
