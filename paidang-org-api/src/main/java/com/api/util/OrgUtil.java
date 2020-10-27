package com.api.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Lee on 2017/12/20.
 */
public class OrgUtil {

    public static String getSingleImage(String images){
       if (StringUtils.isEmpty(images))
           return "";
       String[] i = images.split(",");
       if (i.length == 0)
           return "";
       return i[0];
    }

    public static void main(String[] args) {
    /* //   System.out.println(getSingleImage("f6da8c2302634f26bbba037a7cd5758b"));
        Date today = new Date(2017,12,21);
        Date d2 = new Date(2017,12,20);
        System.out.println(today.compareTo(d2));
        System.out.println(today.after(d2));
        System.out.println(DateUtil.between(today,d2, DateUnit.DAY));*/

    //    System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));

        //fastJson测试
       /* JSONObject object = JSON.parseObject("{\"data\":[{\"context\":\"已签收,感谢使用顺丰,期待再次为您服务\",\"ftime\":\"2017-12-21 08:13:55\",\"time\":\"2017-12-21 08:13:55\"},{\"context\":\"快件交给徐振辉，正在派送途中（联系电话：18241816543）\",\"ftime\":\"2017-12-21 07:50:02\",\"time\":\"2017-12-21 07:50:02\"},{\"context\":\"快件到达 【沈阳于洪区中海营业点】\",\"ftime\":\"2017-12-21 07:22:36\",\"time\":\"2017-12-21 07:22:36\"},{\"context\":\"快件在【沈阳苏家屯集散中心】装车，已发往 【沈阳于洪区中海营业点】\",\"ftime\":\"2017-12-21 05:03:22\",\"time\":\"2017-12-21 05:03:22\"},{\"context\":\"快件到达 【沈阳苏家屯集散中心】\",\"ftime\":\"2017-12-20 23:04:15\",\"time\":\"2017-12-20 23:04:15\"},{\"context\":\"快件在【沈阳浑南新区集散中心】装车，已发往 【沈阳苏家屯集散中心】\",\"ftime\":\"2017-12-20 18:58:43\",\"time\":\"2017-12-20 18:58:43\"},{\"context\":\"快件到达 【沈阳浑南新区集散中心】\",\"ftime\":\"2017-12-20 18:35:29\",\"time\":\"2017-12-20 18:35:29\"},{\"context\":\"快件在【深圳集散中心】装车，已发往 【沈阳浑南新区集散中心】\",\"ftime\":\"2017-12-18 22:24:24\",\"time\":\"2017-12-18 22:24:24\"},{\"context\":\"快件到达 【深圳集散中心】\",\"ftime\":\"2017-12-18 22:20:59\",\"time\":\"2017-12-18 22:20:59\"},{\"context\":\"快件在【深圳彩田集散中心】装车，已发往 【深圳集散中心】\",\"ftime\":\"2017-12-18 20:42:46\",\"time\":\"2017-12-18 20:42:46\"},{\"context\":\"快件到达 【深圳彩田集散中心】\",\"ftime\":\"2017-12-18 19:51:28\",\"time\":\"2017-12-18 19:51:28\"},{\"context\":\"快件在【深圳福田华强北营业部】装车，已发往 【深圳彩田集散中心】\",\"ftime\":\"2017-12-18 18:13:31\",\"time\":\"2017-12-18 18:13:31\"},{\"context\":\"顺丰速运 已收取快件\",\"ftime\":\"2017-12-18 17:31:15\",\"time\":\"2017-12-18 17:31:15\"}],\"state\":3,\"status\":200}");
        JSONArray jsonArray = object.getJSONArray("data");
        Integer state = (Integer) object.get("state");
        Integer status = (Integer) object.get("status");
        List<ExpressContext> expressContexts = JSON.parseArray(jsonArray.toJSONString(), ExpressContext.class);
        ExpressDetail expressDetail = new ExpressDetail();
        expressDetail.setData(expressContexts);
        expressDetail.setState(state+"");
        expressDetail.setStatus(status+"");*/
    }

}
