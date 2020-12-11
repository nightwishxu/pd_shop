package com.api.action;

import com.api.MEnumError;
import com.api.MErrorEnum;
import com.api.util.PageLimit;
import com.api.view.orgAuctionPawn.*;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.DateUtil;
import com.item.service.UserNotifyService;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.PawnAuctionEx;
import com.paidang.daoEx.model.UserPawnEx;
import com.paidang.service.*;
import com.util.PaidangConst;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2017/10/13.
 */
@RestController
@RequestMapping(value = "/api/auctionPawn", produces = { "application/json;charset=UTF-8" })
@Api(tags = "竞拍与典当")
public class ApiAuctionPawnController extends ApiBaseController {

    @Autowired
    private UserPawnService userPawnService;
    @Autowired
    private PawnAuctionService pawnAuctionService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrgBankCardService orgBankCardService;
    @Autowired
    private UserGoodsService userGoodsService;
    @Autowired
    private UserNotifyService userNotifyService;
    @Autowired
    private PawnOrgService pawnOrgService;

    @ApiOperation(value = "当品详情与竞拍记录",notes="当品详情与竞拍记录")
    @RequestMapping(value = "/getAuctionPawnDetails", method = RequestMethod.POST)
    @ApiMethod(isPage = true,isLogin = true)
    public BidDetails getAuctionPawnDetails(@ApiParam(value = "典当表id",required = true) String pawnId, MobileInfo mobileInfo,PageLimit pageLimit){
        startPage();
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if(orgId==-1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        BidDetails bidDetails = new BidDetails();
        PawnDetail pawnDetail = new PawnDetail();
        //当品信息
        UserPawnEx userPawnEx = userPawnService.getUserPawnExById(pawnId);
        pawnDetail.setId(userPawnEx.getId().toString());
        pawnDetail.setTitle(userPawnEx.getGoodsName1());
        pawnDetail.setCollecterId(userPawnEx.getUserId()!=null?userPawnEx.getUserId().toString():"");
        pawnDetail.setCollecterMoney(userPawnEx.getLoansPrice()!=null?userPawnEx.getLoansPrice().toString():"");
        pawnDetail.setCollecterRate(userPawnEx.getLoansRate()!=null?userPawnEx.getLoansRate().toString():"");
        pawnDetail.setImage(userPawnEx.getImages());
        pawnDetail.setAuthPrice(userPawnEx.getAuthPrice()!=null?userPawnEx.getAuthPrice().toString():null);
        pawnDetail.setAuthPriceTest(userPawnEx.getAuthPriceTest()!=null?userPawnEx.getAuthPriceTest().toString():null);
        pawnDetail.setBidTimes(userPawnEx.getBidTimes()!=null ? Integer.valueOf(userPawnEx.getBidTimes()):null);
        pawnDetail.setPawnDays(userPawnEx.getPawnTime()!=null?(userPawnEx.getPawnTime()*15)+"":"");
        //倒计时秒数
        if(userPawnEx.getOrgId() != null) {  //用户选择了中标机构，竞拍结束，set=0
            pawnDetail.setBidCountdownTime("0");
            bidDetails.setIsShowBidButton(0);
        }else if(userPawnEx.getUserState() == 1){//如果用户在倒计时结束前放弃竞拍，倒计时也set=0
            pawnDetail.setBidCountdownTime("0");
            bidDetails.setIsShowBidButton(0);
        }else {
            Date now = new Date();
            long auctionTime = PaidangConst.AUCTION_TIME * 1000;
            Long countDownSeconnds = ((userPawnEx.getCreateTime().getTime()  + auctionTime) - now.getTime()) / 1000;
            if (countDownSeconnds > 0) {
                pawnDetail.setBidCountdownTime(countDownSeconnds.toString());
                bidDetails.setIsShowBidButton(1);
            }else{//倒计时已经走完 set=0
                pawnDetail.setBidCountdownTime("0");
                bidDetails.setIsShowBidButton(0);
            }
        }
        //我（当前正登陆的机构）对该当品竞拍次数
        PawnAuctionEx pawnAuctionEx = pawnAuctionService.getPreviousBidInfo(pawnId,orgId.toString());
        Integer myBidTimes ;
        if(pawnAuctionEx==null || StringUtils.isEmpty(pawnAuctionEx.getTimes()) || Integer.valueOf(pawnAuctionEx.getTimes())==0){
            myBidTimes = 0;
        }else{
            myBidTimes = Integer.valueOf(pawnAuctionEx.getTimes());
        }
        //获得参与对典当的竞拍记录
        List<PawnAuctionEx> list = pawnAuctionService.getAuctionDetailsByPawnId(pawnId);
        List<BidRecord> retBidRecords = new ArrayList();
        for (PawnAuctionEx element:list) {
            BidRecord bidRecord = new BidRecord();
            bidRecord.setAuctionId(element.getId().toString());
            bidRecord.setMoney(element.getMoney()!=null?element.getMoney().toString():"");
            bidRecord.setOrgName(element.getAuctionOrgname());
            bidRecord.setOrgId(element.getOrgId()!=null?element.getOrgId().toString():"");
            bidRecord.setRate(element.getRate()!=null?element.getRate().toString():"");
            bidRecord.setMoneyRate(element.getMoneyRate()!=null?element.getMoneyRate().toString():"");
            bidRecord.setTime(element.getCreateTime()!=null? DateUtil.dateToStr(element.getCreateTime(),"yyyy-MM-dd"):"");
            retBidRecords.add(bidRecord);
        }
        //该机构是否绑银行卡
        OrgBankCardExample orgBankCardExample = new OrgBankCardExample();
        orgBankCardExample.createCriteria().andOrgIdEqualTo(orgId).andBankCardTypeEqualTo(1);
        List<OrgBankCard> cards = orgBankCardService.selectByExample(orgBankCardExample);
        //set接口返回model
        if (cards.size() != 0){
            bidDetails.setIsBindCard(1);
        }else {
            bidDetails.setIsBindCard(0);
        }
        bidDetails.setPawnDetail(pawnDetail);
        bidDetails.setMybidTimes(myBidTimes);
        bidDetails.setBidRecords(retBidRecords);
        return bidDetails;
    }


    @ApiOperation(value = "竞拍出价",notes="竞拍出价")
    @RequestMapping(value = "/doAuction", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer doAuction(@ApiParam(value = "典当表id",required = true) String pawnId, MobileInfo mobileInfo,
                         @ApiParam(value = "机构放贷金额",required=true)String money,
                         @ApiParam(value = "综合利率",required=true)String rate,
                         @ApiParam(value = "利息利率",required=true)String moneyRate
                            ){
//        @ApiParam(value = "当号",required=true)String pawnTicketCode,

        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if(orgId==-1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }else if (orgId == 0){ //宝祥平台登陆的，不能竞拍
            throw new ApiException(MEnumError.PT_ACCOUNT_CANNOT_BID);
        }
        if(StringUtils.isEmpty(money) || StringUtils.isEmpty(rate) || StringUtils.isEmpty(moneyRate)){
            throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
        }
        Integer pid = Integer.valueOf(pawnId);
        UserPawn userPawn = userPawnService.selectByPrimaryKey(pid);
        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(orgId);
        if (userPawn == null || pawnOrg == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //检查对该pawnId的竞拍是否在合法的时间范围内
        Date createTime = userPawn.getCreateTime();
        Date deadline = DateUtil.offsetSecond(createTime,PaidangConst.AUCTION_TIME);
        Date now = new Date();
        if (now.compareTo(deadline) > 0){
            throw new ApiException(MEnumError.BID_OUT_OF_TIME);
        }
        //已经使用过的当号排除
//        UserPawnExample userPawnExample = new UserPawnExample();
//        userPawnExample.createCriteria().andPawnTicketCodeEqualTo(pawnTicketCode).andStateGreaterThanOrEqualTo(2);
//        Integer cnt = userPawnService.countByExample(userPawnExample);
//        if (cnt > 0){
//            throw new ApiException(MEnumError.PAWNTICKETCODE_OCCUPPIED);
//        }
        //检查机构竞拍同一pawnId时输入的当号，确保没有和其他机构竞拍此pawnId输入的当号重复
//        PawnAuctionExample pawnAuctionExample = new PawnAuctionExample();
//        pawnAuctionExample.createCriteria().andPawnIdEqualTo(pid).andPawnCodeEqualTo(pawnTicketCode).andOrgIdNotEqualTo(orgId);
//        Integer c = pawnAuctionService.countByExample(pawnAuctionExample);
//        if(c != 0){
//            throw new ApiException(MEnumError.PAWNTICKETCODE_OCCUPPIED);
//        }
//        pawnAuctionExample.clear();
//        //对同一物品的可能的前后数次竞拍当号要保持一致
//        pawnAuctionExample.setOrderByClause("create_time");
//        pawnAuctionExample.createCriteria().andPawnIdEqualTo(pid).andOrgIdEqualTo(orgId);
//        List<PawnAuction> auctions = pawnAuctionService.selectByExample(pawnAuctionExample);
//        if (auctions.size() != 0){
//            PawnAuction record = auctions.get(0);
//            if (record != null){
//                if (!record.getPawnCode().equals(pawnTicketCode)){
//                    throw new ApiException(MEnumError.PAWNTICKETCODE_NOTSAME);
//                }
//            }
//        }
        BigDecimal m ;
        BigDecimal r ;
        BigDecimal mr ;
        try{
            m = new BigDecimal(money);
            r = new BigDecimal(rate);
            mr = new BigDecimal(moneyRate);
        }catch (NumberFormatException e){
            e.printStackTrace();
            throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
        }
        PawnAuction pawnAuction = new PawnAuction();
        pawnAuction.setPawnId(Integer.valueOf(pawnId));
//        pawnAuction.setPawnCode(pawnTicketCode);
        pawnAuction.setOrgId(orgId);
        pawnAuction.setMoney(m);
        pawnAuction.setRate(r);
        pawnAuction.setMoneyRate(mr);
        pawnAuction.setOrgUserId(mobileInfo.getUserId());
        pawnAuction.setCreateTime(new Date());
        int result = pawnAuctionService.insert(pawnAuction);
        if (result!=1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //推送用户端，当品被竞拍消息
        userNotifyService.insertByTemplate(userPawn.getUserId(),"8", PaidangMessage.PAWN_WAS_BEEN_BID,pawnOrg.getName());
        return 1;
    }


    @ApiOperation(value = "机构上次出价信息",notes="该机构上次出价信息：包括上次出价价格，费率，利率和我（登陆机构）之前出价次数")
    @RequestMapping(value = "/getPreviousBidInfo", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public BidPreInfo getPreviousBidInfo(@ApiParam(value = "典当表id",required = true) String pawnId, MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if(orgId == -1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
    }
        PawnAuctionEx pawnAuctionEx = pawnAuctionService.getPreviousBidInfo(pawnId,orgId.toString());
        UserPawn userPawn = userPawnService.selectByPrimaryKey(Integer.valueOf(pawnId));
        if (userPawn == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer goodsId = userPawn.getGoodsId();
        UserGoods userGoods = userGoodsService.selectByPrimaryKey(goodsId);
        if (userGoods == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        BidPreInfo bidPreInfo = new BidPreInfo();
        bidPreInfo.setMoney(pawnAuctionEx.getMoney()!=null?pawnAuctionEx.getMoney().toString():null);
        bidPreInfo.setMoneyRate(pawnAuctionEx.getMoneyRate()!=null?pawnAuctionEx.getMoneyRate().toString():null);
        bidPreInfo.setRate(pawnAuctionEx.getRate()!=null?pawnAuctionEx.getRate().toString():null);
        bidPreInfo.setTimes(pawnAuctionEx.getTimes());
        bidPreInfo.setBxRate(userGoods.getRate()!=null?userGoods.getRate().toString():"");
        bidPreInfo.setBxMoneyRate(userGoods.getMoneyRate()!=null?userGoods.getMoneyRate().toEngineeringString():"");
        return bidPreInfo;
    }


    @ApiOperation(value = "系统推荐的中标机构",notes="返回所有竞拍中，费率最低的一条记录，若费率同样低不止一家，选择其中典当成交量多的，若成交量一样多时，选择利率最低的，利率一样低时，选择最先下单的机构为系统推荐中标机构")
    @RequestMapping(value = "/getSysRecommendOne", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public SysRecommendOrg getSysRecommendOne(@ApiParam(value = "典当表id",required = true) String pawnId,MobileInfo mobileInfo){
        PawnAuctionEx pawnAuctionEx =  pawnAuctionService.getSysRecommendOne(pawnId);
        SysRecommendOrg sysRecommendOrg = new SysRecommendOrg();
        sysRecommendOrg.setAuctionId(pawnAuctionEx.getId().toString());
        sysRecommendOrg.setMoney(pawnAuctionEx.getMoney()!=null?pawnAuctionEx.getMoney().toString():"");
        sysRecommendOrg.setOrgId(pawnAuctionEx.getOrgId().toString());
        sysRecommendOrg.setAuctionOrgname(pawnAuctionEx.getAuctionOrgname());
        sysRecommendOrg.setRate(pawnAuctionEx.getRate()!=null?pawnAuctionEx.getRate().toString():"");
        sysRecommendOrg.setMoneyRate(pawnAuctionEx.getMoneyRate()!=null?pawnAuctionEx.getMoneyRate().toString():"");
        return sysRecommendOrg;
    }

}
