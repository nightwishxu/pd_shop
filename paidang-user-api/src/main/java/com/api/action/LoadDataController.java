package com.api.action;

import com.api.model.PawnTicketModel;
import com.api.model.RepawnTicketModel;
import com.base.annotation.ApiMethod;
import com.base.api.ApiException;
import com.base.util.DateUtil;
import com.item.dao.model.User;
import com.item.service.UserService;
import com.paidang.dao.model.PawnOrg;
import com.paidang.daoEx.model.PawnContinueEx;
import com.paidang.daoEx.model.UserPawnEx;
import com.paidang.service.PawnContinueService;
import com.paidang.service.PawnOrgService;
import com.paidang.service.UserPawnService;
import com.util.NumberToCN;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 获取典当续当数据
 */
@RequestMapping(value="/api/loadData", produces = {"application/json;charset=UTF-8"})
@RestController
@Api(tags = "获取典当续当数据")
public class LoadDataController {
    @Autowired
    UserService userService;
    @Autowired
    PawnOrgService pawnOrgService;
    @Autowired
    UserPawnService userPawnService;
    @Autowired
    PawnContinueService pawnContinueService;

    /**
     * 查询典当续当数据
     * @param pawnId
     * @return
     */
    @PostMapping("/loadPawnData")
    @ApiMethod
    @ApiOperation("查询典当续当数据")
    public PawnTicketModel loadPawnData(Integer pawnId){
        if (StringUtils.isEmpty(pawnId.toString())){
            throw new ApiException(-1,"典当id不能为空");
        }
        Integer pid ;
        try{
            pid = Integer.valueOf(pawnId);
        }catch (NumberFormatException e){
            e.printStackTrace();
            throw new ApiException(-1,"请输入正确的典当id");
        }
        UserPawnEx userPawnEx = userPawnService.getUserPawnExById(pid.toString());
        if (userPawnEx == null){
            throw new ApiException(-1,"请输入正确的典当id");
        }
        if (userPawnEx.getOrgId()==null){
            throw new ApiException(-1,"中标的典当机构信息为空");
        }
        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(userPawnEx.getOrgId());
        User pawner = userService.selectByPrimaryKey(userPawnEx.getUserId());
        if (pawner==null){
            throw new ApiException(-1,"未找到相应当户信息");
        }
        BigDecimal beginMoney = userPawnEx.getBeginMoney();
        BigDecimal pawnMoney = userPawnEx.getPawnMoney();// 首期综合费
        BigDecimal userMoney;
        PawnTicketModel pawnTicketModel = new PawnTicketModel();
        pawnTicketModel.setPawnTicketCode(userPawnEx.getPawnTicketCode()!=null?userPawnEx.getPawnTicketCode():"");
        pawnTicketModel.setAuthPrice(userPawnEx.getAuthPrice()!=null?userPawnEx.getAuthPrice().toString():"");
        pawnTicketModel.setAuthPriceTest(userPawnEx.getAuthPrice()!=null?userPawnEx.getAuthPrice().toString():"");
        pawnTicketModel.setBusinessLicense(pawnOrg.getBusinessLicenseCode()!=null?pawnOrg.getBusinessLicenseCode():"");//显示许可证图还是编码?
        pawnTicketModel.setContactor(userPawnEx.getUserName()!=null?userPawnEx.getUserName():"");
        pawnTicketModel.setEquivalentRatio("100%");
        pawnTicketModel.setGoodsName(userPawnEx.getGoodsName1()!=null?userPawnEx.getGoodsName1():"");
        pawnTicketModel.setLoanMoney(beginMoney!=null?beginMoney.toString():"");
        pawnTicketModel.setPawnMoney(pawnMoney!=null?pawnMoney.toString():"");
        if(beginMoney!=null && pawnMoney!=null){
            userMoney = beginMoney.subtract(pawnMoney);
            pawnTicketModel.setUserMoney(userMoney.toString());
            pawnTicketModel.setUserMoneyCN(NumberToCN.number2CNMontrayUnit(userMoney));
        }else{
            pawnTicketModel.setUserMoney("");
            pawnTicketModel.setUserMoneyCN("");
        }
        pawnTicketModel.setRate(userPawnEx.getRate()!=null?userPawnEx.getRate().toString():"");
        pawnTicketModel.setMoneyRate(userPawnEx.getMoneyRate()!=null?userPawnEx.getMoneyRate().toString():"");
        pawnTicketModel.setOrgName(pawnOrg.getName()!=null?pawnOrg.getName():"");
        pawnTicketModel.setOrgAddr(pawnOrg.getAdress()!=null?pawnOrg.getAdress():"");
        pawnTicketModel.setOrgTel(pawnOrg.getPhone()!=null?pawnOrg.getPhone():"");
        pawnTicketModel.setBusinessLicense(pawnOrg.getBusinessLicenseCode()!=null?pawnOrg.getBusinessLicenseCode():"");
//      pawnTicketModel.setOrgSeal(userPawnEx.getOrgSeal()!=null?userPawnEx.getOrgSeal():"");
        pawnTicketModel.setOrgSeal(pawnOrg.getSeal()!=null?pawnOrg.getSeal():"");
        pawnTicketModel.setPawnerName(userPawnEx.getUserName()!=null?userPawnEx.getUserName().toString():"");
        pawnTicketModel.setContactor(userPawnEx.getUserName()!=null?userPawnEx.getUserName().toString():"");
        pawnTicketModel.setPawnerTel(userPawnEx.getUserPhone()!=null?userPawnEx.getUserPhone().toString():"");
        pawnTicketModel.setPawnerAddr(pawner.getIdCardHand()!=null?pawner.getIdCardHand():"");
        pawnTicketModel.setPawnerIdCard(userPawnEx.getUserIdCard()!=null?userPawnEx.getUserIdCard():"");
        pawnTicketModel.setPawnerCert("身份证");
        //典当开始日期
        pawnTicketModel.setPawnBeginTime(userPawnEx.getPawnBeginTime()!=null? DateUtil.format(userPawnEx.getPawnBeginTime(),"yyyy-MM-dd"):"");
        pawnTicketModel.setPawnEndTime(userPawnEx.getPawnEndTime()!=null? DateUtil.format(userPawnEx.getPawnEndTime(),"yyyy-MM-dd"):"");
        //典当结束日期
        pawnTicketModel.setBeginPawnEndTime(userPawnEx.getBeginPawnEndTime()!=null? DateUtil.format(userPawnEx.getBeginPawnEndTime(),"yyyy-MM-dd"):"");
        pawnTicketModel.setLoanMoneyCN(beginMoney!=null? NumberToCN.number2CNMontrayUnit(beginMoney):"");
        pawnTicketModel.setPawnMoneyCN(pawnMoney!=null? NumberToCN.number2CNMontrayUnit(pawnMoney):"");
        return pawnTicketModel;

    }





    /**
     * 续当信息
     * @param pawnContinueId
     * @return
     */
    public RepawnTicketModel loadRePawnData(Integer pawnContinueId){
        if (StringUtils.isEmpty(pawnContinueId.toString())){
            throw new ApiException(-1,"续当id不能为空");
        }
        PawnContinueEx pawnContinueEx =pawnContinueService.getRepawnTickInfoById(pawnContinueId.toString());
        UserPawnEx userPawnEx = userPawnService.getUserPawnExById(pawnContinueEx.getPawnId().toString());
        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(userPawnEx.getOrgId());
        User pawner = userService.selectByPrimaryKey(userPawnEx.getUserId());
        //所有续当票
        RepawnTicketModel repawnTicketModel = new RepawnTicketModel();
        repawnTicketModel.setContactor(pawnContinueEx.getUserName()!=null?pawnContinueEx.getUserName():"");
        repawnTicketModel.setMoneyRate(pawnContinueEx.getMoneyRate()!=null?pawnContinueEx.getMoneyRate().toString():"");
        repawnTicketModel.setRate(pawnContinueEx.getRate()!=null?pawnContinueEx.getRate().toString():"");
        repawnTicketModel.setLoanMoney(pawnContinueEx.getLoanMoney()!=null?pawnContinueEx.getLoanMoney().toString():"");
        repawnTicketModel.setCost(pawnContinueEx.getPawnMoney()!=null?pawnContinueEx.getPawnMoney().toString():"");
        repawnTicketModel.setMoneyCost(pawnContinueEx.getPawnInterest()!=null?pawnContinueEx.getPawnInterest().toString():"");
        repawnTicketModel.setOrgName(pawnContinueEx.getOrgName()!=null?pawnContinueEx.getOrgName():"");
//              repawnTicketModel.setOrgSeal(pawnContinueEx.getOrgSeal()!=null?pawnContinueEx.getOrgSeal():"");
        repawnTicketModel.setOrgSeal(pawnOrg.getSeal()!=null?pawnOrg.getSeal():"");
        repawnTicketModel.setPawnerName(pawnContinueEx.getPawnerName()!=null?pawnContinueEx.getPawnerName():"");
        repawnTicketModel.setPawnTicketCode(pawnContinueEx.getPawnTicketCode()!=null?pawnContinueEx.getPawnTicketCode():"");
        //续当开始时间
        repawnTicketModel.setRepawnBeginTime(pawnContinueEx.getPawnLastEndTime()!=null? DateUtil.format(pawnContinueEx.getPawnLastEndTime(),"yyyy-MM-dd"):"");
        //续当结束时间
        repawnTicketModel.setRepawnEndTime(pawnContinueEx.getPawnEndTime()!=null? DateUtil.format(pawnContinueEx.getPawnEndTime(),"yyyy-MM-dd"):"");
        repawnTicketModel.setLoanMoneyCN(pawnContinueEx.getLoanMoney()!=null? NumberToCN.number2CNMontrayUnit(pawnContinueEx.getLoanMoney()):"");
        repawnTicketModel.setCostCN(pawnContinueEx.getPawnMoney()!=null? NumberToCN.number2CNMontrayUnit(pawnContinueEx.getPawnMoney()):"");
        repawnTicketModel.setMoneyCostCN(pawnContinueEx.getPawnInterest()!=null? NumberToCN.number2CNMontrayUnit(pawnContinueEx.getPawnInterest()):"");
        if (pawnContinueEx.getPawnMoney()==null || pawnContinueEx.getPawnInterest()==null){
            repawnTicketModel.setRepawnTotal("");
            repawnTicketModel.setRepawnTotalCN("");
        }else {
            BigDecimal repawnTotal = pawnContinueEx.getPawnMoney().add(pawnContinueEx.getPawnInterest());
            repawnTicketModel.setRepawnTotal(repawnTotal.toString());
            repawnTicketModel.setRepawnTotalCN(NumberToCN.number2CNMontrayUnit(repawnTotal));
        }
        return repawnTicketModel;
    }
}
