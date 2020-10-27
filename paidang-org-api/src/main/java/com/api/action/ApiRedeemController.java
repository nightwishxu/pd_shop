package com.api.action;

import com.api.MErrorEnum;
import com.api.util.OrgUtil;
import com.api.util.PageLimit;
import com.api.view.orgRedeem.RedeemDetail;
import com.api.view.orgRedeem.RedeemMini;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.DateUtil;
import com.item.dao.model.User;
import com.item.service.UserNotifyService;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.UserPawnEx;
import com.paidang.service.*;
import com.util.PaidangMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

import static com.paidang.utils.CostGenerator.getInterest;
import static com.paidang.utils.CostGenerator.getOverdue;

/**
 * Created by Lee on 2017/10/22.
 */
@RestController
@RequestMapping(value = "/api/redeem", produces = { "application/json;charset=UTF-8" })
@Api(tags = "赎当")
public class ApiRedeemController extends ApiBaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private PawnOrgService pawnOrgService;
    @Autowired
    private UserPawnService userPawnService;
    @Autowired
    private PawnContinueService pawnContinueService;
    @Autowired
    private UserGoodsService userGoodsService;
    @Autowired
    private PawnLogService pawnLogService;
    @Autowired
    private OrgBalanceLogService orgBalanceLogService;
    @Autowired
    private UserBalanceLogService userBalanceLogService;
    @Autowired
    private UserNotifyService userNotifyService;
    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation(value = "赎当处理列表",notes="赎当处理列表")
    @RequestMapping(value = "/getOrgRedeemList", method = RequestMethod.POST)
    @ApiMethod(isPage = true,isLogin = true)
    public List<RedeemMini> getOrgRedeemList(MobileInfo mobileInfo, PageLimit pageLimit){
        startPage();
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());//机构id
        List<UserPawnEx> userPawnExList = userPawnService.getOrgRedeemList(orgId.toString(),"","");
        List<RedeemMini> retList = new ArrayList<>();
        for (UserPawnEx element : userPawnExList){
            RedeemMini redeemMini = new RedeemMini();
            redeemMini.setPawnId(element.getId().toString());
            redeemMini.setGoodsId(element.getGoodsId().toString());
            redeemMini.setTitle(element.getGoodsName1()!=null?element.getGoodsName1():"");
            redeemMini.setAuthPrice(element.getAuthPrice()!=null?element.getAuthPrice().toString():"");
            redeemMini.setImages(OrgUtil.getSingleImage(element.getImages()));
            redeemMini.setPawnTicketCode(element.getPawnTicketCode()!=null?element.getPawnTicketCode():"");
            //已发放当金 = 贷款金额 - 首期综合费 若数据库已保存则取数据库，否则计算得到
            if(element.getUserMoney()!=null){
                redeemMini.setUserMoney(element.getUserMoney().toString());
            }else if (element.getPawnMoney()!=null && element.getBeginMoney()!=null){
                redeemMini.setUserMoney(element.getBeginMoney().subtract(element.getPawnMoney()).toString());
            }else {
                redeemMini.setUserMoney("0");
            }
            if (element.getState()!=null && element.getRedeemState()!=null && element.getRedeemState()==3 && element.getRedeemState()==3){
                redeemMini.setRedeemState(1);//已赎当
            }else {
                redeemMini.setRedeemState(2);//赎当申请处理中
            }
            retList.add(redeemMini);
        }

        //排序
        List<RedeemMini> temp = new ArrayList();
        Iterator it = retList.iterator();
        while (it.hasNext()){
            RedeemMini mini = (RedeemMini) it.next();
            if (mini.getRedeemState()==2){
                temp.add(mini);
                it.remove();
            }
        }
        Collections.sort(retList, new Comparator<RedeemMini>() {
            @Override
            public int compare(RedeemMini o1, RedeemMini o2) {
                if (Integer.valueOf(o1.getPawnId()) > Integer.valueOf(o2.getPawnId())){
                    return -1;
                }else {
                    return 1;
                }
            }
        });
        Collections.sort(temp, new Comparator<RedeemMini>() {
            @Override
            public int compare(RedeemMini o1, RedeemMini o2) {
                if (Integer.valueOf(o1.getPawnId()) > Integer.valueOf(o2.getPawnId())){
                    return 1;
                }else {
                    return -1;
                }
            }
        });
        for (RedeemMini mn: temp){
            retList.add(0,mn);
        }
        return retList;
    }


    @ApiOperation(value = "赎当处理详情",notes="赎当处理详情")
    @RequestMapping(value = "/getOrgRedeemDetail", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public RedeemDetail getOrgRedeemDetail(@ApiParam(value = "典当表id",required = true) String pawnId,MobileInfo mobileInfo){
        User user = userService.selectByPrimaryKey(mobileInfo.getUserId());//登陆机构端用户
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());

        UserPawnEx userPawnEx = userPawnService.getUserPawnExById(pawnId);
        //获取本次典当成交时约定的逾期滞纳利率
        BigDecimal redeem_overrate = userPawnEx.getOverdueRate();
        if (redeem_overrate == null){
            throw  new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        RedeemDetail redeemDetail = new RedeemDetail();

        redeemDetail.setPawnId(userPawnEx.getId().toString());
        redeemDetail.setGoodsId(userPawnEx.getGoodsId().toString());
        redeemDetail.setTitle(userPawnEx.getGoodsName1()!=null?userPawnEx.getGoodsName1():"");
        redeemDetail.setAuthPrice(userPawnEx.getAuthPrice()!=null?userPawnEx.getAuthPrice().toString():"");
        redeemDetail.setImages(userPawnEx.getImages()!=null?userPawnEx.getImages():"");
        redeemDetail.setCollecterId(userPawnEx.getUserId().toString());
        redeemDetail.setPawnerName(userPawnEx.getUserName()!=null?userPawnEx.getUserName():"");
        redeemDetail.setPawnTicketCode(userPawnEx.getPawnTicketCode()!=null?userPawnEx.getPawnTicketCode():"");
        redeemDetail.setPawnerTelNum(userPawnEx.getUserPhone()!=null?userPawnEx.getUserPhone():"");
        redeemDetail.setLoanBeginTime(userPawnEx.getPawnBeginTime()!=null? DateUtil.dateToStr(userPawnEx.getPawnBeginTime(),"yyyy-MM-dd") :"");
        //当前应还款时期
        redeemDetail.setLoanCurrentEndTime(userPawnEx.getPawnEndTime()!=null?DateUtil.dateToStr(userPawnEx.getPawnEndTime(),"yyyy-MM-dd") :"");
        //详情用户地址
        String pawnerAddress="";
        UserAddressExample userAddressExample = new UserAddressExample();
        userAddressExample.createCriteria().andUserIdEqualTo(userPawnEx.getUserId()).andIsDefaultEqualTo(1);
        List<UserAddress> userAddresses = userAddressService.selectByExample(userAddressExample);
        if(userAddresses.size() != 0){
            pawnerAddress = userAddresses.get(0).getAddress()!=null&&userAddresses.get(0).getArea()!=null?userAddresses.get(0).getArea()+userAddresses.get(0).getAddress():"";
        }
        redeemDetail.setPawnerAddress(pawnerAddress);
        //已发放当金 = 贷款金额 - 首期综合费 若数据库已保存则取数据库，否则计算得到
        if(userPawnEx.getUserMoney()!=null){
            redeemDetail.setUserMoney(userPawnEx.getUserMoney().toString());
        }else if (userPawnEx.getPawnMoney()!=null && userPawnEx.getBeginMoney()!=null){
            redeemDetail.setUserMoney(userPawnEx.getBeginMoney().subtract(userPawnEx.getPawnMoney()).toString());
        }else {
            redeemDetail.setUserMoney("0");
        }

        BigDecimal loan_money = userPawnEx.getBeginMoney();//本金
        if(loan_money==null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        redeemDetail.setLoanMoney(loan_money.toString());
        BigDecimal moneyRate = userPawnEx.getMoneyRate();//利息利率
        if(moneyRate==null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        redeemDetail.setMoneyRate(moneyRate.toString());
//      redeemDetail.setRedeemOverRate(PaidangConst.REDEEM_OVERRATE.toString());//逾期滞纳利率
        redeemDetail.setRedeemOverRate(redeem_overrate.toString());//逾期滞纳利率redeem_overrate

        //计算赎当利息
        Integer times = pawnContinueService.getRepawnTimes(userPawnEx.getId());//办理过多少次续当
        BigDecimal redeem_interest ;
        BigDecimal payBack ;
        if(times == null || times == 0){//用户并没有续当过,计算赎当利息的时长就是首次典当约定的当期长
            if(userPawnEx.getBeginPawnMonth()==null){
                throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
            }
            if(userPawnEx.getRedeemInterest()==null){
                redeem_interest = getInterest(loan_money,moneyRate,userPawnEx.getBeginPawnMonth());
            }else{
                redeem_interest = userPawnEx.getRedeemInterest();
            }
        }else{
            //用户续当过至少一次,计算赎当利息的时长就是上一个续当的当期
            PawnContinueExample pawnContinueExample = new PawnContinueExample();
            pawnContinueExample.createCriteria().andPawnIdEqualTo(Integer.valueOf(pawnId)).andStateEqualTo(4);
            pawnContinueExample.setOrderByClause("create_time desc");
            List<PawnContinue> pawnContinueList = pawnContinueService.selectByExample(pawnContinueExample);
            PawnContinue pawnContinue = pawnContinueList.get(0);// 有此pawnId的续当记录，取第一个，最新的
            Integer pawnMonth = pawnContinue.getPawnMonth();//续当表保存的该续当周期长(当前时期所在续当周期)
            //计算赎当利息
            if(userPawnEx.getRedeemInterest()==null){
                redeem_interest = getInterest(loan_money,moneyRate,pawnMonth);
            }else{
                redeem_interest = userPawnEx.getRedeemInterest();
            }
        }
        //计算逾期利息(若逾期)
        BigDecimal redeemOverdue ;
        Date pawnEndTime = userPawnEx.getPawnEndTime();//典当结束时间
        if (userPawnEx.getRedeemOverdue() == null){
            redeemOverdue = getOverdue(loan_money,pawnEndTime,redeem_overrate);
        }else{
            redeemOverdue = userPawnEx.getRedeemOverdue();
        }
        redeemDetail.setMoneyCost(redeem_interest.setScale(2,BigDecimal.ROUND_HALF_UP).toString());//赎当利息
        redeemDetail.setRedeemOverdue(redeemOverdue.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        //到期应还款金额 = 本金 + 本期利息 + 逾期利息(可能产生)
        payBack = loan_money.add(redeem_interest).add(redeemOverdue);
        redeemDetail.setPayBack(payBack.setScale(2,BigDecimal.ROUND_HALF_UP).toString());

        //赎当详情页面收款人姓名，收款银行名，收款卡号
        String bankCardName = userPawnEx.getPayBankName()!=null?userPawnEx.getPayBankName():"";
        String bankCardUserName = userPawnEx.getPayName()!=null? userPawnEx.getPayName():"";
        String bankCardNo = userPawnEx.getPayBacnkCardCode()!=null?userPawnEx.getPayBacnkCardCode():"";
        String currentPayTicket = userPawnEx.getRedeemTicket();//用户赎当时上传的打款凭证
        Integer state = userPawnEx.getState();
        Integer redeemState = userPawnEx.getRedeemState();
        if (state+redeemState == 6){
            redeemDetail.setIsConfirmed("1");
        }else {
            redeemDetail.setIsConfirmed("0");
        }
        redeemDetail.setBankCardName(bankCardName);
        redeemDetail.setBankCardCode(bankCardNo);
        redeemDetail.setBankCardUserName(bankCardUserName);
        redeemDetail.setCurrentPayTicket(currentPayTicket);
        redeemDetail.setIsPay("1");
        return redeemDetail;
    }

    @ApiOperation(value = "确认用户赎当",notes="")
    @RequestMapping(value = "/userRedeemConfirm", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer userRedeemConfirm(@ApiParam(value = "典当表id",required = true) String pawnId, MobileInfo mobileInfo){
        if (StringUtils.isEmpty(pawnId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer pid = Integer.valueOf(pawnId);
        UserPawn userPawn = userPawnService.selectByPrimaryKey(pid);
        Integer goodsId = userPawn.getGoodsId();
        Integer orgId = userPawn.getOrgId();
        Integer userId = userPawn.getUserId();
        if (goodsId == null || orgId == null || userId ==null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        UserGoods userGoods = userGoodsService.selectByPrimaryKey(goodsId);
        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(orgId);
        User pawner = userService.selectByPrimaryKey(userId);//当户
        if(userPawn == null || userGoods == null || pawnOrg == null || pawner ==null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //获取赎当详情
        RedeemDetail redeemDetail = getOrgRedeemDetail(pawnId,mobileInfo);
        //改变典当状态，赎当状态，赎当利息，赎当滞纳金，赎当打款凭证无须set，此字段是用户赎当申请时由用户端set
        userPawn.setState(3);
        userPawn.setRedeemState(3);
        userPawn.setRedeemTime(new Date());
        userPawn.setRedeemInterest(new BigDecimal(redeemDetail.getMoneyCost()));
        userPawn.setRedeemOverdue(new BigDecimal(redeemDetail.getRedeemOverdue()));
        userPawnService.updateByPrimaryKey(userPawn);


        userGoods.setGotoPawn(0);//'是否去典当0否  1是',
        userGoods.setIsRedeem(1);
        userGoodsService.updateByPrimaryKey(userGoods);

        //推送用户端，表示机构已经处理其赎当请求
        userNotifyService.insertByTemplate(userId, "3", PaidangMessage.PAWN_REDEEM_NOTIFY,userGoods.getName(),pawnOrg.getName());

        //记录赎当日志
        PawnLog pawnLog = new PawnLog();
        pawnLog.setUserName(userGoods.getUserName());
        pawnLog.setAuthPrice(userGoods.getAuthPrice());
        pawnLog.setGoodsCate(userGoods.getCateCode());
        pawnLog.setGoodsCateId(userGoods.getCateId());
        pawnLog.setGoodsId(goodsId);
        pawnLog.setUserId(userGoods.getUserId());
        pawnLog.setOrgId(orgId);
        pawnLog.setOrgName(pawnOrg.getName());
        pawnLog.setMoney(userPawn.getBeginMoney());
        pawnLog.setPawnMonth(userPawn.getPawnTime());
        pawnLog.setType(3);// 赎当
        pawnLog.setUserName(pawner.getName());
        pawnLog.setTradeCardBank(userPawn.getPayeeBankName());
        pawnLog.setTradeCardCode(userPawn.getPayeeBankCardCode());
        pawnLogService.insert(pawnLog);

        OrgBalanceLog orgBalanceLog = new OrgBalanceLog();
        orgBalanceLog.setOrgId(orgId);
        orgBalanceLog.setMoney(new BigDecimal(redeemDetail.getPayBack()));//赎当费用
        orgBalanceLog.setType(2);//收入
        orgBalanceLog.setItem("3");//用户赎当
        orgBalanceLog.setInfo("id是"+orgId+"的机构的"+userPawn.getPayBankName()+"卡号为"+userPawn.getPayBacnkCardCode()+"收到用户"+pawner.getId()+"的赎当费"+redeemDetail.getPayBack()+"元,其首次典当的当号："+userPawn.getPawnTicketCode());
        orgBalanceLog.setPawnCode(userPawn.getPawnTicketCode());
        orgBalanceLog.setFid(userPawn.getId());
        orgBalanceLog.setTradeType(10);// 线下银行卡
        orgBalanceLog.setTradeCardBank(userPawn.getPayBankName());
        orgBalanceLog.setTradeCardCode(userPawn.getPayBacnkCardCode());
        orgBalanceLog.setTradeTicket(userPawn.getRedeemTicket());//赎当打款凭证
        orgBalanceLog.setUserId(pawner.getId());
        orgBalanceLog.setUserName(pawner.getName());
        orgBalanceLog.setUserPhone(pawner.getPhone()!=null?pawner.getPhone():pawner.getAccount());
        orgBalanceLogService.insert(orgBalanceLog);

        UserBalanceLog userBalanceLog = new UserBalanceLog();
        userBalanceLog.setTradeNo(userPawn.getPawnTicketCode());//现在该字段作为当号
        userBalanceLog.setCid(userPawn.getId());
        userBalanceLog.setUserId(pawner.getId());
        userBalanceLog.setAmount(new BigDecimal(redeemDetail.getPayBack()));//赎当费用
        userBalanceLog.setType(2);//减
        userBalanceLog.setItem("3");//赎当
        userBalanceLog.setInfo("用户"+pawner.getId()+userPawn.getPayeeBankName()+"卡号是"+userPawn.getPayeeBankCardCode()+"的银行卡支付给机构"+orgId+"【赎当费】"+redeemDetail.getPayBack()+"元，首次典当当号："+userPawn.getPawnTicketCode());
        userBalanceLog.setTradeType(10);// 线下银行卡
        userBalanceLog.setTradeTicket(userPawn.getRedeemTicket());//赎当打款凭证
        userBalanceLog.setTradeCardBank(userPawn.getPayeeBankName());
        userBalanceLog.setTradeCardCode(userPawn.getPayeeBankCardCode());
        userBalanceLog.setOrgId(orgId);
        userBalanceLog.setOrgName(pawnOrg.getName());
        userBalanceLog.setOrgPhone(pawnOrg.getPhone());
        userBalanceLogService.insert(userBalanceLog);
        return 1;
    }

}
