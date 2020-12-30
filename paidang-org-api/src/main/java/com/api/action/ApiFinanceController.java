package com.api.action;


import com.base.annotation.ApiMethod;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.item.service.OrgAmountLogService;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.service.OrgWithdrawApplyService;
import com.paidang.service.PawnOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/finance", produces = { "application/json;charset=UTF-8" })
@Api(tags = "机构财务")
public class ApiFinanceController {


    @Autowired
    private OrgWithdrawApplyService orgWithdrawApplyService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrgAmountLogService orgAmountLogService;



    @ApiOperation(value = "提现申请",notes="")
    @RequestMapping(value = "/withdraw/apply", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = false)
    public Integer withdrawApply(MobileInfo mobileInfo, @ApiParam(value = "提现金额",required = true) BigDecimal amount){
        if (amount==null && amount.compareTo(BigDecimal.ZERO)<=0){
            throw new ApiException(400,"提现金额异常");
        }
        Integer orgId = userService.getOrgIdByUserIdChecked(mobileInfo.getUserId());
        return orgWithdrawApplyService.withdrawApply(mobileInfo.getUserId(),orgId,amount);
    }


    @ApiOperation(value = "提现申请列表",notes="")
    @RequestMapping(value = "/withdraw/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = false)
    public List<OrgWithdrawApply> withdrawApply(MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserIdChecked(mobileInfo.getUserId());
        OrgWithdrawApplyExample example = new OrgWithdrawApplyExample();
        example.createCriteria().andOrgIdEqualTo(orgId);
        example.setOrderByClause("create_time desc");
        return orgWithdrawApplyService.selectByExample(example);
    }

    @ApiOperation(value = "金额流水",notes="")
    @RequestMapping(value = "/amount/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<OrgAmountLog> amountList(MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserIdChecked(mobileInfo.getUserId());
        OrgAmountLogExample example = new OrgAmountLogExample();
        example.createCriteria().andOrgIdEqualTo(orgId);
        example.setOrderByClause("create_time desc");
        return orgAmountLogService.selectByExample(example);
    }
}
