package com.api.action;


import com.alibaba.fastjson.JSONObject;
import com.api.MEnumError;
import com.api.MErrorEnum;
import com.api.service.AuthService;
import com.api.view.orgHome.bankCard.BankCard;
import com.base.annotation.ApiMethod;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.item.service.OrgAmountLogService;
import com.item.service.UserAmountLogService;
import com.item.service.UserService;
import com.item.service.UserWithdrawApplyService;
import com.paidang.dao.model.*;
import com.paidang.service.OrgBankCardService;
import com.paidang.service.OrgWithdrawApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user/finance", produces = { "application/json;charset=UTF-8" })
@Api(tags = "财务 (用户端)")
public class ApiUserFinanceController {


    @Autowired
    private UserWithdrawApplyService userWithdrawApplyService;



    @Autowired
    private UserAmountLogService userAmountLogService;





    @ApiOperation(value = "提现申请",notes="")
    @RequestMapping(value = "/withdraw/apply", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = false)
    public Integer withdrawApply(MobileInfo mobileInfo, @ApiParam(value = "提现金额",required = true) BigDecimal amount){
        if (amount==null && amount.compareTo(BigDecimal.ZERO)<=0){
            throw new ApiException(400,"提现金额异常");
        }

        UserWithdrawApply i = userWithdrawApplyService.withdrawApply(mobileInfo.getUserId(), amount);
        userAmountLogService.saveLog(mobileInfo.getUserId(),amount,"2","提现申请",i.getId(),null);
        return 1;
    }


    @ApiOperation(value = "提现申请列表",notes="")
    @RequestMapping(value = "/withdraw/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = false)
    public List<UserWithdrawApply> withdrawApply(MobileInfo mobileInfo){
        UserWithdrawApplyExample example = new UserWithdrawApplyExample();
        example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId());
        example.setOrderByClause("create_time desc");
        return userWithdrawApplyService.selectByExample(example);
    }

    @ApiOperation(value = "金额流水",notes="")
    @RequestMapping(value = "/amount/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<UserAmountLog> amountList(MobileInfo mobileInfo){
        UserAmountLogExample example = new UserAmountLogExample();
        example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId());
        example.setOrderByClause("create_time desc");
        return userAmountLogService.selectByExample(example);
    }

}
