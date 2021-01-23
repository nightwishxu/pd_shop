package com.api.action;


import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONObject;
import com.api.MEnumError;
import com.api.MErrorEnum;
import com.api.service.AuthService;
import com.api.view.orgHome.bankCard.BankCard;
import com.base.annotation.ApiMethod;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.item.dao.model.User;
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
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    private UserService userService;





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


    @PostMapping("/pay/set/password")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "设置支付密码（第一次）")
    public Integer setPassword(@ApiParam(value = "支付密码") String password,
                               MobileInfo mobileInfo){
        User user = userService.selectByPrimaryKey(mobileInfo.getUserId());

        if(com.base.util.StringUtils.isNotBlank(user.getPassword())){
            throw new ApiException(-1,"存在异常数据。");
        }

        //密码对称加密 16进制
        String encryptHex = DigestUtil.md5Hex(password);
        User temp = new User();
        temp.setId(mobileInfo.getUserId());
        temp.setPayPassword(encryptHex);

        userService.updateByPrimaryKeySelective(temp);
        return 1;
    }


    @PostMapping("/pay/reset/password")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "重置支付密码")
    public Integer reSetPassword(@ApiParam(value = "原支付密码") String password,
                                 @ApiParam(value = "第一次输入新支付密码") String newPassword,
                                 @ApiParam(value = "第二次输入新支付密码") String reNewPassword,
                                 MobileInfo mobileInfo){

        if(!newPassword.equals(reNewPassword)){
            throw new ApiException(-1,"两次输入的密码不一致");
        }
        User user = userService.selectByPrimaryKey(mobileInfo.getUserId());


        //存在人员数据，但是人员已经设置过密码
        if(com.base.util.StringUtils.isBlank(user.getPayPassword())){
            throw new ApiException(-1,"还未设置过密码");
        }else{
            String pass = user.getPayPassword();
            //密码对称加密 16进制
            String encryptHex = DigestUtil.md5Hex(password);

            if(!pass.equals(encryptHex)){
                throw new ApiException(-1,"原支付密码错误");
            }
            String newEncryptHex = DigestUtil.md5Hex(newPassword);
            User temp = new User();
            temp.setId(mobileInfo.getUserId());
            temp.setPayPassword(newEncryptHex);

            userService.updateByPrimaryKeySelective(temp);

        }
        return 1;
    }


    @PostMapping("/pay/forget/password/set")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "忘记支付密码 设置" )
    public Integer setForgetPassword(
            @ApiParam(value = "第一次输入新支付密码") String newPassword,
            @ApiParam(value = "第二次输入新支付密码") String reNewPassword,
            MobileInfo mobileInfo){
        if(!newPassword.equals(reNewPassword)){
            throw new ApiException(-1,"两次输入的密码不一致");
        }
        User user = userService.selectByPrimaryKey(mobileInfo.getUserId());



        //存在人员数据，但是人员已经设置过密码
        if(com.base.util.StringUtils.isBlank(user.getPayPassword())){
            throw new ApiException(-1,"还未设置过密码");
        }else{
            String newEncryptHex = DigestUtil.md5Hex(newPassword);
            User temp = new User();
            temp.setId(mobileInfo.getUserId());
            temp.setPayPassword(newEncryptHex);

            userService.updateByPrimaryKeySelective(temp);

        }
        return 1;
    }

    @PostMapping("/pay/password/verification")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "支付密码验证")
    public Integer verification(@ApiParam(value = "支付密码") String password,
                                MobileInfo mobileInfo){
        User user = userService.selectByPrimaryKey(mobileInfo.getUserId());

        if(org.apache.commons.lang.StringUtils.isBlank(user.getPayPassword())){
            throw new ApiException(-1,"还未设置支付密码");
        }else{
            String pass = user.getPayPassword();
            String encryptHex = DigestUtil.md5Hex(password);
            if(!pass.equals(encryptHex)){
                throw new ApiException(-1,"支付密码不正确");
            }
        }
        return 1;
    }

}
