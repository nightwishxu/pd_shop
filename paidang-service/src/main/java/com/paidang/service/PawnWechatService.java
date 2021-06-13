package com.paidang.service;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.base.dao.model.Result;
import com.base.util.CoreConstants;
import com.base.util.JSONUtils;
import com.base.util.StringUtil;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.redis.RedisCache;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xww
 * @Description:
 * @date 2021/4/26 23:59
 */
@Service
public class PawnWechatService
{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private RedisCache redisCache;

    public String getToken(){
        try {
            String token = redisCache.get("pawnWechatToken");
            if (StringUtils.isBlank(token)){
                HttpResponse response = HttpUtil.createPost(CoreConstants.PAWN_WECHAT_URL + "/pawn/app/mini/project/getToken")
                        .form("uid", "-1").execute();
                String body = response.body();
                Map<String,Object> res = JSONUtils.deserialize(body, Map.class);
                token = (String)res.get("token");
                redisCache.setCacheObject("pawnWechatToken",token,86400, TimeUnit.SECONDS);
            }
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("pawnWechat getToken error:{}",e);
            return null;
        }
    }


    /**
     * 邮寄鉴定
     */
    public String  kdSave( Map<String,Object> map){
        try {
            map.put("source","06");
            map.put("jdid","-1");
            String response = HttpUtil.createPost(CoreConstants.PAWN_WECHAT_URL + "/pawn/app/mini/project/kd/save")
                    .header("token", getToken())
                    .form(map)
                    .execute().body();
            logger.info("pawnWechat 在线鉴定 param:{},result:{}",JSONUtils.serialize(map),response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("pawnWechat kdSave param:{},error:{}",JSONUtils.serialize(map),e);
            return null;
        }
    }


    public  List<Map> findPawnList(String userGoodsIds){
        Map<String,Object> map = Maps.newHashMap();
        try {
            String response = HttpUtil.createPost(CoreConstants.PAWN_WECHAT_URL + "/pawn/app/mini/project/pawnList/find?userGoodsIds="+userGoodsIds)
                    .execute().body();
            logger.info("pawnWechat  findPawnList param:{},result:{}",userGoodsIds,response);
            if (StringUtils.isBlank(response)){
                return null;
            }

            Map result = JSONUtils.deserialize(response, Map.class);
            if (result.get("code").equals(0)){
                List<Map> mapList  = (List<Map>)result.get("data");
                return mapList;

            }else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("pawnWechat findPawnList param:{},error:{}",JSONUtils.serialize(map),e);
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String,Object> map = Maps.newHashMap();
            String userGoodsIds = "3688,3689";
            String response = HttpUtil.createPost("http://180.76.186.228:8080" + "/pawn/app/mini/project/pawnList/find?userGoodsIds="+userGoodsIds)
                    .execute().body();
            if (StringUtils.isBlank(response)){

            }




    }
}
