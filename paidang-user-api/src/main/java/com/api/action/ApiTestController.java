package com.api.action;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.api.service.ApiUserPayService;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.MobileInfo;
import com.base.dao.model.Result;
import com.base.oauthLogin.api.OauthQQ;
import com.base.util.*;
import com.demo.constant.DSPConsts;
import com.google.common.collect.Lists;
import com.item.dao.model.User;
import com.item.daoEx.model.AdEx;
import com.item.service.AdService;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.service.*;
import com.qiyuesuo.QysService;
import com.ruoyi.common.core.redis.RedisCache;
import com.util.PaidangConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

    @Autowired
    private UserPawnService userPawnService;

    @Autowired
    private PawnOrgService pawnOrgService;

    @Autowired
    private PawnContinueService pawnContinueService;

    @Autowired
    private UserService userService;

    @Autowired
    private PawnTicketService pawnTicketService;

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

    @ApiOperation(value = "z", notes = "1")
    @RequestMapping("/userPawn/sign")
    @ApiMethod()
    public void test(Integer pawnId,Integer continuePawnId,String ip,String time) throws Exception{
        UserPawn userPawn = userPawnService.selectByPrimaryKey(pawnId);
        User user = userService.selectByPrimaryKey(userPawn.getUserId());
        PawnContinue pawnContinue = pawnContinueService.selectByPrimaryKey(continuePawnId);
        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(userPawn.getOrgId());
        PawnTicket pawnTicket = pawnTicketService.getByProjectCode(pawnContinue.getProjectCode());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //2021 02 22 15 15 49
        String contractNo = AnXinSignService.createXdContract(DSPConsts.contractRePawnTemplateId,"互联网典当续当凭证",
                user.getAnxinsignId(),ip,pawnContinue.getProjectCode(),sdf.parse(time),pawnOrg.getAnxinsignId(),
                pawnTicket.getOrgLocation(),pawnTicket.getOrgSignTime(),pawnTicket,userPawn);
        pawnContinue.setState(4);
        pawnContinue.setContractId(contractNo);
        PawnTicket temp = new PawnTicket();
        temp.setId(pawnTicket.getId());
        temp.setSignTime(sdf.parse(time));
        temp.setUserLocation(ip);
        temp.setUserStatus(2);
        temp.setStatus("2");
        temp.setContractId(contractNo);
        pawnContinueService.updateByPrimaryKeySelective(pawnContinue);
    }
}
