package com.base.util;

import cn.hutool.http.HttpUtil;
import com.base.api.ApiException;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class BaseUtils {

    public static final String  areaUrl="http://mobsec-dianhua.baidu.com/dianhua_api/open/location?tel=";

    private static final int[] numArr = {0,1,2,3,4,5,6,7,8,9};


    public static String getRandomNum(int length){
        Random rand = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<length;i++){
            builder.append(rand.nextInt(10));
        }
        return builder.toString();
    }

    /**
     * @Description: 产生订单编号随机数
     * @param uuid
     *            文件id
     * @return String
     */
    public static String getRandomOrderId(String pre) {
        // 当前日期
        Date date = new Date();
        String dString = DateUtil.dateToStr(date, "yyyyMMddHHmmssSSS");

        // 产生三位的随机数
        int rndCount = 0;
        while (true) {
            rndCount = (int) (Math.floor(Math.random() * 1000));
            if (rndCount >= 100 && rndCount <= 999)
                break;
        }

        String random = pre +  dString + Integer.toString(rndCount)+StringUtil.getRandomString(2);

        return random;
    }

    /**
     * 0<时间<30s，显示：刚刚

     30s<时间<60s，显示：1分钟前

     60s<时间<60m，显示：x分钟前

     60m<时间<24h，显示：x小时前

     24h<时间<48h，显示：昨天

     1d<时间<7d，显示x天前

     7d<时间<30d，显示：m月d日

     30d<时间，显示：y年m月d日；
     * @param date
     * @return
     */

    public static String getTimeInfo(Date date){
        if (date==null) return null;
       Long time = date.getTime();
       Long now  =System.currentTimeMillis();
       Long left = (now-time)/1000;
       if (left<0){
           return "刚刚";
       }else if (left<=30){
           return "刚刚";
       }else if (left<=60){
           return "1分钟前";
       }else if (left<=3600){
           return (left/60)+"分钟前";
       }else if (left<=86400){
           return (left/3600)+"小时前";
       }else if (left<=172800){
           return "昨天";
       }else if (left<=604800){
           return (left/86400)+"天前";
       }else if (left<=2592000){
           return DateUtil.dateToStr(date,"MM月dd日");
       }else{
           return DateUtil.dateToStr(date,"yyyy年MM月dd日");
       }

    }

    public static String getAccountMast(String account) {
        if (StringUtils.isNotBlank(account)) {
            return account.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        return StringUtils.EMPTY;
    }

    public static String getCityName(String phone){
        try {
            String result = HttpUtil.get(areaUrl+phone);
            if(StringUtils.isNotBlank(result)){
                Map map= JSONUtils.deserialize(result,Map.class);
                Map phoneMap = (Map)((Map)map.get("response")).get(phone);
                List areas =(List)((Map)phoneMap.get("detail")).get("area");
                String city =(String)((Map)areas.get(0)).get("city");
                return city;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }


    public static boolean isAnyBlank(Object...arr){
        if (arr==null || arr.length==0){
            return false;
        }
        for (Object o : arr) {
            if (o ==null){
                return true;
            }
            if (o instanceof String && "".equals(o)){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(getCityName("18861269725"));
    }


    public static String removeUrl(String url){
        return url;
//        if (isAnyBlank(url)){
//            return url;
//        }
//        String[] urls = url.split(",");
//        for (int i=0;i<urls.length;i++){
//            String s = urls[i];
//            if (s.startsWith("http")){
//                int index = s.lastIndexOf("=");
//                if (index>=0){
//                    s = s.substring(index+1,s.length());
//                }
//                urls[i] = s;
//            }
//        }
//        return  String.join(",",urls);
    }

    public static String processImg(String path){
        if (StringUtils.isNoneBlank(path) && !path.startsWith("http")){
            return CoreConstants.SERVER_URL+"download?id="+path;
        }
        return path;
    }

    public static String processImgs(String imgs){
        if (StringUtils.isNoneBlank(imgs)){
            String[] paths = imgs.split(",");
            for (int i=0;i<paths.length;i++){
                paths[i] = processImg(paths[i]);
            }
            return String.join(",",paths);
        }
        return imgs;
    }


    /**
     * 检查不同过则抛出异常
     *
     * @param flag
     * @param error
     */
    public static void check(boolean flag, String error) {
        if (flag) {
            throw new ApiException(400,error);
        }
    }

    /**
     * 检查参数是否为空并抛一次
     *
     * @param objs
     */
    public static void checkBlankParam(Object... objs) {
        check(isAnyBlank(objs), "缺少必要参数");

    }

    public static Integer getDefaultDealType(Integer dealType){
        return dealType==null?1:dealType;
    }


    /**
     * 获取分页数据
     * @param dataList 进行分页的数据集合
     * @param pageNum  第几页
     * @param pageSize 每页显示多少条
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getPageLimit(List dataList, int pageNum, int pageSize){
        if(CollectionUtils.isEmpty(dataList)){
            return dataList;
        }
        List resultList = new ArrayList();
        // 所有dataList数据中的第几条
        int currIdx = pageNum > 1 ? (pageNum -1) * pageSize : 0;
        for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {
            resultList.add(dataList.get(currIdx + i));
        }
        return resultList;
    }

    public static String getSingleImage(String images){
        if (org.apache.commons.lang3.StringUtils.isEmpty(images))
            return "";
        String[] i = images.split(",");
        if (i.length == 0)
            return "";
        return i[0];
    }
}
