package com.api.action;

import com.alibaba.fastjson.JSONObject;
import com.api.service.ApiUserPayService;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.MobileInfo;
import com.base.dao.model.Result;
import com.base.oauthLogin.api.OauthQQ;
import com.base.util.CoreConstants;
import com.base.util.JSONUtils;
import com.demo.constant.DSPConsts;
import com.google.common.collect.Lists;
import com.item.daoEx.model.AdEx;
import com.item.service.AdService;
import com.paidang.dao.model.BFileExample;
import com.paidang.dao.model.PawnOrg;
import com.paidang.dao.model.VideoOnlineExample;
import com.paidang.service.AnXinSignService;
import com.paidang.service.BFileService;
import com.paidang.service.PawnOrgService;
import com.paidang.service.VideoOnlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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


    @ApiOperation(value = "z", notes = "1")
    @RequestMapping("/pay")
    @ApiMethod()
    public Object pay(MobileInfo mobileInfo){
//        String get_user_info = OauthQQ.me().getAuthorizeUrl("get_user_info");
//        JSONObject userInfoByCode = OauthQQ.me().getUserInfoByCode(get_user_info);
//        System.out.println(JSONUtils.serialize(userInfoByCode));
//        ApiUserPayService.payTest();
        return new Result<>(DSPConsts.Keystore);
    }

    @ApiOperation(value = "z", notes = "1")
    @RequestMapping("/anxin/regist")
    @ApiMethod()
    public Object anxinRegist(String idCard,String name,String phone) throws Exception{
        String s = AnXinSignService.personRegister(idCard, name, phone);
        return new Result<>(s);
    }
}
