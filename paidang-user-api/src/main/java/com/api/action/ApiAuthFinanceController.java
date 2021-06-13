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
import com.item.service.UserService;
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
@RequestMapping(value = "/api/auth/finance", produces = { "application/json;charset=UTF-8" })
@Api(tags = "财务 (商户端)")
public class ApiAuthFinanceController {


    @Autowired
    private OrgWithdrawApplyService orgWithdrawApplyService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrgAmountLogService orgAmountLogService;

    @Autowired
    private AuthService authService;

    @Autowired
    private OrgBankCardService orgBankCardService;



    @ApiOperation(value = "提现申请",notes="")
    @RequestMapping(value = "/withdraw/apply", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = false)
    public Integer withdrawApply(MobileInfo mobileInfo,
                                 @ApiParam(value = "提现金额",required = true) BigDecimal amount,
                                 @ApiParam(value = "提现方式, 1支付宝2微信10银行卡",required = true) Integer type,
                                 @ApiParam(value = "提现银行账号",required = false) String bankCardNo,
                                 @ApiParam(value = "提现银行名称",required = false) String bankCardName){
        if (amount==null && amount.compareTo(BigDecimal.ZERO)<=0){
            throw new ApiException(400,"提现金额异常");
        }
        JSONObject ret = authService.isPersonal(mobileInfo.getUserId());
        int code = ret.getInteger("code");
        int orgId = ret.getInteger("org_id");
        OrgWithdrawApply i = orgWithdrawApplyService.withdrawApply(mobileInfo.getUserId(), orgId, amount, type, bankCardNo, bankCardName);
        orgAmountLogService.saveLog(orgId,mobileInfo.getUserId(),amount,"2","提现申请",i.getId(),null);
        return 1;
    }


    @ApiOperation(value = "提现申请列表",notes="")
    @RequestMapping(value = "/withdraw/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = false)
    public List<OrgWithdrawApply> withdrawApply(MobileInfo mobileInfo){
        JSONObject ret = authService.isPersonal(mobileInfo.getUserId());
        int code = ret.getInteger("code");
        int orgId = ret.getInteger("org_id");
        OrgWithdrawApplyExample example = new OrgWithdrawApplyExample();
        example.createCriteria().andOrgIdEqualTo(orgId);
        example.setOrderByClause("create_time desc");
        return orgWithdrawApplyService.selectByExample(example);
    }

    @ApiOperation(value = "金额流水",notes="")
    @RequestMapping(value = "/amount/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<OrgAmountLog> amountList(MobileInfo mobileInfo){
        JSONObject ret = authService.isPersonal(mobileInfo.getUserId());
        int orgId = ret.getInteger("org_id");
        OrgAmountLogExample example = new OrgAmountLogExample();
        example.createCriteria().andOrgIdEqualTo(orgId);
        example.setOrderByClause("create_time desc");
        return orgAmountLogService.selectByExample(example);
    }



    @ApiOperation(value = "添加银行卡(机构)",notes="登陆")
    @RequestMapping(value="/addOrgBankCard", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer addOrgBankCard(MobileInfo mobileInfo,
                                  @ApiParam(value = "银行卡号", required = true)String bankCardNo,
                                  @ApiParam(value = "银行名称", required = true)String bankName,
                                  @ApiParam(value = "预留手机号", required = true)String account,
                                  @ApiParam(value = "姓名", required = true)String userName
//                           @ApiParam(value = "身份证号", required = true)String idCard
    ){
        JSONObject ret = authService.isPersonal(mobileInfo.getUserId());
        int orgId = ret.getInteger("org_id");
        //每个机构仅能绑定一张卡
        OrgBankCardExample orgBankCardExample = new OrgBankCardExample();
        orgBankCardExample.createCriteria().andOrgIdEqualTo(orgId);
        int cardCount = orgBankCardService.countByExample(orgBankCardExample);
        if (cardCount > 0){
            throw new ApiException(30012,"您只能绑定一张银行卡");
        }
        //银行卡验证--借记卡
       /* BankCardResult bankCardResult = ApiStoreBankUtil.verifyDC(userName,account,idCard,bankCardNo);
        if(null == bankCardResult){
            throw new ApiException(MEnumError.BANKCARD_BIND_ERROR);
        }*/
        //查询此卡是否被其他机构绑定过
        orgBankCardExample.clear();
        orgBankCardExample.createCriteria().andBankCardNoEqualTo(bankCardNo).andOrgIdNotEqualTo(orgId);
        Integer cnt = orgBankCardService.countByExample(orgBankCardExample);
        if (cnt >= 1){
            throw new ApiException(30007,"此卡已被其他机构绑定！");
        }


        OrgBankCard orgBankCard = new OrgBankCard();
        orgBankCard.setOrgId(orgId);
        orgBankCard.setBankCardType(1);
        orgBankCard.setBankCardNo(bankCardNo);
        orgBankCard.setBankCardUserName(userName);
        orgBankCard.setBankCardName(bankName);
        orgBankCard.setBankCardPhone(account);
        orgBankCard.setCreateTime(new Date());
        orgBankCard.setIsDefault(0);
        int result = orgBankCardService.insert(orgBankCard);
        if(result != 1){
            throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
        }
        return 1;
    }


    @ApiOperation(value = "删除银行卡",notes="")
    @RequestMapping(value = "/delOrgBankCard", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer addOrgBankCard(@ApiParam(value = "id",required = true)String cardId
            ,MobileInfo mobileInfo){

        if (StringUtils.isEmpty(cardId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        int result = orgBankCardService.deleteByPrimaryKey(Integer.valueOf(cardId));
        if (result!=1){
            throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
        }
        return 1;
    }


    @ApiOperation(value = "我的银行卡列表",notes="")
    @RequestMapping(value = "/myBankCards", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public List<BankCard> myBankCards(MobileInfo mobileInfo){
        JSONObject ret = authService.isPersonal(mobileInfo.getUserId());
        int orgId = ret.getInteger("org_id");
        OrgBankCardExample orgBankCardExample = new OrgBankCardExample();
        orgBankCardExample.createCriteria().andOrgIdEqualTo(orgId).andBankCardTypeEqualTo(1);
        List<OrgBankCard> list = orgBankCardService.selectByExample(orgBankCardExample);

        List<BankCard> retList = new ArrayList<>();
        for (OrgBankCard orgBankCard : list){
            BankCard bankCard = new BankCard();
            bankCard.setCardId(orgBankCard.getId().toString());
            bankCard.setBankCardNo(orgBankCard.getBankCardNo());
            bankCard.setBankName(orgBankCard.getBankCardName());
            retList.add(bankCard);
        }
        return retList;
    }
}
