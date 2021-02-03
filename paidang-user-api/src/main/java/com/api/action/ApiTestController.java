package com.api.action;

import com.alibaba.fastjson.JSONObject;
import com.api.service.ApiUserPayService;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.MobileInfo;
import com.base.dao.model.Result;
import com.base.oauthLogin.api.OauthQQ;
import com.base.util.BaseUtils;
import com.base.util.CoreConstants;
import com.base.util.JSONUtils;
import com.base.util.StringUtil;
import com.demo.constant.DSPConsts;
import com.google.common.collect.Lists;
import com.item.daoEx.model.AdEx;
import com.item.service.AdService;
import com.paidang.dao.model.BFileExample;
import com.paidang.dao.model.PawnOrg;
import com.paidang.dao.model.VideoOnlineExample;
import com.paidang.service.*;
import com.qiyuesuo.QysService;
import com.ruoyi.common.core.redis.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author: xww
 * @create: 2020-11-08 16:38
 * @description: 广告位
 * @modified By:
 * @version:
 **/
@RestController
@RequestMapping(value = "/api/test", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
@Api(tags = "广告位")
public class ApiTestController extends ApiBaseController {


    @Autowired
    private BFileService fileService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private QysService qysService;

    @ApiOperation(value = "z", notes = "1")
    @RequestMapping("/pay")
    @ApiMethod()
    public Object pay(MobileInfo mobileInfo,Integer type,String key) throws Exception{
//        String get_user_info = OauthQQ.me().getAuthorizeUrl("get_user_info");
//        JSONObject userInfoByCode = OauthQQ.me().getUserInfoByCode(get_user_info);
//        System.out.println(JSONUtils.serialize(userInfoByCode));
//        ApiUserPayService.payTest();
        if (type!=null && type==1){
            BaseUtils.checkBlankParam(key);
            Collection<String> keys = redisCache.keys(key);
            if (!keys.isEmpty()) {
                redisCache.deleteObject(keys);
            }
        }else if (type==2){
            UnionApiService.validBankCard("123","许","6227001260550180113","32021919870228002X","13771228227");

        }
        return new Result<>(DSPConsts.Keystore);
    }

    @ApiOperation(value = "z", notes = "1")
    @RequestMapping("/anxin/regist")
    @ApiMethod()
    public Object anxinRegist(String idCard,String name,String phone) throws Exception{
        String s = AnXinSignService.personRegister(idCard, name, phone);
        return new Result<>(s);
    }


    @ApiOperation(value = "z", notes = "1")
    @RequestMapping("/uploadFile")
    @ApiMethod()
    public Object uploadFile(MobileInfo mobileInfo,String startTime,String endTime) throws Exception{
        fileService.transferFile(startTime,endTime);
        return new Result<>(DSPConsts.Keystore);
    }

    //String pageUrl = qysService.getPageUrl(Long.valueOf(contractId));

    @ApiOperation(value = "z", notes = "1")
    @RequestMapping("/contractUrl/get")
    @ApiMethod()
    public Object contractUrlGet(Long contractId) throws Exception{
        String pageUrl = qysService.getPageUrl(Long.valueOf(contractId));
        return new Result<>(pageUrl);
    }
}
