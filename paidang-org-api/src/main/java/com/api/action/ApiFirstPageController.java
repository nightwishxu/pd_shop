package com.api.action;

import com.api.MEnumError;
import com.api.MErrorEnum;
import com.api.util.OrgUtil;
import com.api.util.PageLimit;
import com.api.view.orgAuctionPawn.PawnDetail;
import com.api.view.orgDeadPawnage.DeadPawnMini;
import com.api.view.orgDeadPawnage.ExpressInfo;
import com.api.view.orgFirstPage.message.MessageIndex;
import com.api.view.orgFirstPage.message.OrgNotifyInfo;
import com.api.view.orgFirstPage.search.Search;
import com.api.view.orgHome.OrgInfo;
import com.api.view.orgHome.deadPawnAuction.DeadPawnAuctionMini;
import com.api.view.orgRedeem.RedeemMini;
import com.api.view.orgRepawn.RepawnMini;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.DateUtil;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.ExpressEx;
import com.paidang.daoEx.model.PawnOrgEx;
import com.paidang.daoEx.model.UserPawnEx;
import com.paidang.service.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2017/10/12.
 */
@RestController
@RequestMapping(value = "/api/firstPage", produces = { "application/json;charset=UTF-8" })
@Api(tags = "APP首页")
public class ApiFirstPageController extends ApiBaseController {

    @Autowired
    private UserPawnService userPawnService;
    @Autowired
    private PawnContinueService pawnContinueService;
    @Autowired
    PawnAuctionService pawnAuctionService;
    @Autowired
    private UserGoodsService userGoodsService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ExpressService expressService;
    @Autowired
    private UserService userService;
    @Autowired
    private PawnOrgService pawnOrgService;
    @Autowired
    private OrgNotifyService orgNotifyService;
    @Autowired
    private OrgMessageTypeService orgMessageTypeService;
    @Autowired
    private ApiMyAppController myAppController;

    /**
     * 首页最新当品列表
     */
    @ApiOperation(value = "最新当品", notes = "最新当品")
    @RequestMapping(value = "/getLatestPawnList", method = RequestMethod.POST)
    @ApiMethod(isPage = true,isLogin = true)
    public List<PawnDetail> getLatestPawnList(PageLimit pageLimit,MobileInfo mobileInfo) {
        startPage();
        List<UserPawnEx> list = userPawnService.LatestPawnList(PaidangConst.AUCTION_TIME);

        List retList = new ArrayList();
        for (UserPawnEx userPawnEx : list) {
            PawnDetail pawnDetail = new PawnDetail();
            pawnDetail.setAuctionCount(userPawnEx.getCnt());
            pawnDetail.setId(userPawnEx.getId().toString());
            pawnDetail.setTitle(userPawnEx.getGoodsName1()!=null?userPawnEx.getGoodsName1():"");
            pawnDetail.setCollecterId(userPawnEx.getUserId()!=null?userPawnEx.getUserId().toString():"");
            pawnDetail.setCollecterMoney(userPawnEx.getLoansPrice()!=null?userPawnEx.getLoansPrice().toString():"");
            pawnDetail.setPawnBeginTime(userPawnEx.getPawnBeginTime()!=null? DateUtil.dateToStr(userPawnEx.getPawnBeginTime(),"yyyy-MM-dd"):"");
            pawnDetail.setPawnEndTime(userPawnEx.getPawnEndTime()!=null? DateUtil.dateToStr(userPawnEx.getPawnEndTime(),"yyyy-MM-dd"):"");
            pawnDetail.setImage(OrgUtil.getSingleImage(userPawnEx.getImages()));
            pawnDetail.setAuthPrice(userPawnEx.getAuthPrice()!=null? userPawnEx.getAuthPrice().toString():"");
            pawnDetail.setAuthPriceTest(userPawnEx.getAuthPriceTest()!=null? userPawnEx.getAuthPriceTest().toString():"");
            pawnDetail.setBidTimes(userPawnEx.getBidTimes() != null ? Integer.valueOf(userPawnEx.getBidTimes()) : null);
            //倒计时秒数
            if(userPawnEx.getOrgId() != null) {  //用户选择了中标机构，竞拍结束，set=0
                pawnDetail.setBidCountdownTime("0");
            }else if(userPawnEx.getUserState() == 1){//如果用户在倒计时结束前放弃竞拍，倒计时也set=0
                pawnDetail.setBidCountdownTime("0");
            }else {
                Date now = new Date();
                long auctionTime = PaidangConst.AUCTION_TIME * 1000;
                Long countDownSeconnds = ((userPawnEx.getCreateTime().getTime()  + auctionTime) - now.getTime()) / 1000;
                if (countDownSeconnds > 0) {
                    pawnDetail.setBidCountdownTime(countDownSeconnds.toString());
                }else{//倒计时已经走完 set=0
                    pawnDetail.setBidCountdownTime("0");
                }
            }
            retList.add(pawnDetail);
        }

        return retList;
    }


    /**
     * 热门竞拍
     */
    @ApiOperation(value = "热门竞拍", notes = "热门竞拍")
    @RequestMapping(value = "/getHotAuctionsList", method = RequestMethod.POST)
    @ApiMethod(isPage = false,isLogin = true)
    public List<PawnDetail> getHotAuctionsList(PageLimit pageLimit,MobileInfo mobileInfo) {
        startPage();
        List<UserPawnEx> list = userPawnService.hotAuctionsList(PaidangConst.AUCTION_TIME);

        List retList = new ArrayList();
        for (UserPawnEx userPawnEx : list) {
            PawnDetail pawnDetail = new PawnDetail();
            pawnDetail.setAuctionCount(userPawnEx.getCnt());
            pawnDetail.setId(userPawnEx.getId().toString());
            pawnDetail.setTitle(userPawnEx.getGoodsName1()!=null?userPawnEx.getGoodsName1():"");
            pawnDetail.setCollecterId(userPawnEx.getUserId()!=null?userPawnEx.getUserId().toString():"");
            pawnDetail.setCollecterMoney(userPawnEx.getLoansPrice()!=null?userPawnEx.getLoansPrice().toString():"");
            pawnDetail.setPawnBeginTime(userPawnEx.getPawnBeginTime()!=null? DateUtil.dateToStr(userPawnEx.getPawnBeginTime(),"yyyy-MM-dd"):"");
            pawnDetail.setPawnEndTime(userPawnEx.getPawnEndTime()!=null? DateUtil.dateToStr(userPawnEx.getPawnEndTime(),"yyyy-MM-dd"):"");
            pawnDetail.setImage(OrgUtil.getSingleImage(userPawnEx.getImages()));
            pawnDetail.setAuthPrice(userPawnEx.getAuthPrice()!=null? userPawnEx.getAuthPrice().toString():"");
            pawnDetail.setAuthPriceTest(userPawnEx.getAuthPriceTest()!=null? userPawnEx.getAuthPriceTest().toString():"");
            pawnDetail.setBidTimes(userPawnEx.getBidTimes() != null ? Integer.valueOf(userPawnEx.getBidTimes()) : null);
            //倒计时秒数
            if(userPawnEx.getOrgId() != null) {  //用户选择了中标机构，竞拍结束，set=0
                pawnDetail.setBidCountdownTime("0");
            }else if(userPawnEx.getUserState() == 1){//如果用户在倒计时结束前放弃竞拍，倒计时也set=0
                pawnDetail.setBidCountdownTime("0");
            }else {
                Date now = new Date();
                long auctionTime = PaidangConst.AUCTION_TIME * 1000;
                Long countDownSeconnds = ((userPawnEx.getCreateTime().getTime()  + auctionTime) - now.getTime()) / 1000;
                if (countDownSeconnds > 0) {
                    pawnDetail.setBidCountdownTime(countDownSeconnds.toString());
                }else{//倒计时已经走完 set=0
                    pawnDetail.setBidCountdownTime("0");
                }
            }
            retList.add(pawnDetail);
        }

        return retList;
    }

    /**
     * 搜索
     */
    @ApiOperation(value = "搜索", notes = "搜索")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ApiMethod(isPage = true,isLogin = true)
    public List<Search> search(@ApiParam(value = "关键词",required = true) String keyword,
                               MobileInfo mobileInfo, PageLimit pageLimit) {

        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        List<Search> retList = new ArrayList<>();
        //keyword查询藏品表结果集
        startPage();
        UserGoodsExample userGoodsExample = new UserGoodsExample();
        userGoodsExample.createCriteria().andNameLike("%"+keyword+"%").andAuthResultEqualTo(4);
        userGoodsExample.setOrderByClause("create_time desc");
        List<UserGoods> userGoodsList = userGoodsService.selectByExample(userGoodsExample);
        Date now = new Date();
        for (UserGoods userGoods:userGoodsList) {
            UserPawnExample userPawnExample = new UserPawnExample();
            userPawnExample.createCriteria().andGoodsIdEqualTo(userGoods.getId());
            userPawnExample.setOrderByClause("create_time desc");
            List<UserPawn> userPawns = userPawnService.selectByExample(userPawnExample);
            for (UserPawn userPawn:userPawns) {
                Integer org = userPawn.getOrgId();
                Integer state = userPawn.getState();
                Integer redeemState = userPawn.getRedeemState()==null?0:userPawn.getRedeemState();
                Date createTime = userPawn.getCreateTime();
                Date pawnEndTime = userPawn.getPawnEndTime();
                PawnContinueExample pawnContinueExample = new PawnContinueExample();
                pawnContinueExample.createCriteria().andPawnIdEqualTo(userPawn.getId());
                pawnContinueExample.setOrderByClause("create_time desc");//时间倒序排序
                List<PawnContinue> pawnContinues = pawnContinueService.selectByExample(pawnContinueExample);

                PawnAuctionExample pawnAuctionExample = new PawnAuctionExample();
                pawnAuctionExample.createCriteria().andPawnIdEqualTo(userPawn.getId()).andOrgIdEqualTo(orgId);
                int c = pawnAuctionService.countByExample(pawnAuctionExample);
                if (state == -1){
                    continue;
                }
                //典当
                if (state == 2 && org == orgId && now.compareTo(DateUtil.add(pawnEndTime,PaidangConst.BUFFER_DAYS)) == -1  && pawnContinues.size() == 0){
                    Search search = new Search();
                    search.setDes("1");
                    RepawnMini repawnMini = new RepawnMini();
                    repawnMini.setShowButton("0");//不显示 【确认收款】
                    repawnMini.setId(userPawn.getId().toString());
                    repawnMini.setRepawnId("");
                    repawnMini.setDateRepawnTo(userPawn.getPawnEndTime()!=null? DateUtil.dateToStr(userPawn.getPawnEndTime(),"yyyy-MM-dd"):"");
                    repawnMini.setGoodsid(userPawn.getId().toString());
                    repawnMini.setGoodsName(userPawn.getGoodsName());
                    repawnMini.setImages(OrgUtil.getSingleImage(userGoods.getImages()));
                    repawnMini.setLoanMoney(userPawn.getBeginMoney()!=null?userPawn.getBeginMoney().toString():"");
                    repawnMini.setAuthPrice(userGoods.getAuthPrice()!=null?userGoods.getAuthPrice().toString():"");
                    //已发放当金 = 本金 - 首期综合费 若数据库已保存则取数据库，否则计算得到
                    String userMoney = "";
                    boolean isGo = true;
                    if(userPawn.getUserMoney()!=null){
                        userMoney = userPawn.getUserMoney().toString();
                        isGo = false;
                    }else if (isGo && userPawn.getPawnMoney()!=null && userPawn.getBeginMoney()!=null){
                        BigDecimal um = userPawn.getBeginMoney().subtract(userPawn.getPawnMoney());
                        userMoney = um.compareTo(new BigDecimal("0"))== -1?"":um.toString();
                    }
                    repawnMini.setUserMoney(userMoney);//已发放当金
                    search.setRepawnMini(repawnMini);
                    retList.add(search);
                }
                //续当
                else if (state == 2 && org == orgId && now.compareTo(DateUtil.add(pawnEndTime,PaidangConst.BUFFER_DAYS)) == -1  && pawnContinues.size() > 0){
                    Search search = new Search();
                    search.setDes("2");
                    PawnContinue pawnContinue = pawnContinues.get(0);//取续当记录最新一条
                    RepawnMini repawnMini = new RepawnMini();
                    repawnMini.setId(userPawn.getId().toString());
                    repawnMini.setRepawnId(pawnContinue.getId().toString());
                    repawnMini.setShowButton((pawnContinue.getState() == 1 && pawnContinue.getPayTicket() == null)?"1":"0");//是否显示【确认收款】
                    repawnMini.setDateRepawnTo(pawnContinue.getPawnEndTime()!=null? DateUtil.dateToStr(pawnContinue.getPawnEndTime(),"yyyy-MM-dd"):"");
                    repawnMini.setGoodsid(userPawn.getId().toString());
                    repawnMini.setGoodsName(userPawn.getGoodsName());
                    repawnMini.setImages(OrgUtil.getSingleImage(userGoods.getImages()));
                    repawnMini.setLoanMoney(userPawn.getBeginMoney()!=null?userPawn.getBeginMoney().toString():"");
                    repawnMini.setAuthPrice(userGoods.getAuthPrice()!=null?userGoods.getAuthPrice().toString():"");
                    //已发放当金 = 本金 - 首期综合费 若数据库已保存则取数据库，否则计算得到
                    String userMoney = "";
                    boolean isGo = true;
                    if(userPawn.getUserMoney()!=null){
                        userMoney = userPawn.getUserMoney().toString();
                        isGo = false;
                    }else if (isGo && userPawn.getPawnMoney()!=null && userPawn.getBeginMoney()!=null){
                        BigDecimal um = userPawn.getBeginMoney().subtract(userPawn.getPawnMoney());
                        userMoney = um.compareTo(new BigDecimal("0"))== -1?"":um.toString();
                    }
                    repawnMini.setUserMoney(userMoney);//已发放当金
                    search.setRepawnMini(repawnMini);
                    retList.add(search);
                }
                //竞拍中
               else if (state == 1 && org == null && c > 0 ){
                    Search search = new Search();
                    search.setDes("3");
                    PawnDetail pawnDetail = new PawnDetail();
                    pawnDetail.setId(userPawn.getId().toString());
                    pawnDetail.setGoodsId(userPawn.getGoodsId().toString());
                    pawnDetail.setTitle(userGoods.getName());
                    pawnDetail.setImage(OrgUtil.getSingleImage(userGoods.getImages()));
                    pawnDetail.setAuthPrice(userGoods.getAuthPrice().toString());//当品鉴定价
                    pawnDetail.setCollecterMoney(userPawn.getLoansPrice().toString());//当户期望价格
                    pawnDetail.setBidTimes(pawnAuctionService.getAllBidOrgCount(userPawn.getId()));//对该pawId竞拍的所有机构总数
                    //倒计时秒数
                    if(userPawn.getOrgId() != null) {  //用户选择了中标机构，竞拍结束，set=0
                        pawnDetail.setBidCountdownTime("0");
                    }else if(userPawn.getUserState() == 1){//如果用户在倒计时结束前放弃竞拍，倒计时也set=0
                        pawnDetail.setBidCountdownTime("0");
                    }else {
                        long auctionTime = PaidangConst.AUCTION_TIME * 1000;
                        Long countDownSeconnds = ((userPawn.getCreateTime().getTime() + auctionTime) - now.getTime()) / 1000;
                        if (countDownSeconnds > 0) {
                            pawnDetail.setBidCountdownTime(countDownSeconnds.toString());
                        }else{//倒计时已经走完 set=0
                            pawnDetail.setBidCountdownTime("0");
                        }
                    }
                    search.setPawnDetail(pawnDetail);
                    retList.add(search);
                }
                //已中标
              else if ((state == 1 && org == orgId) || (state == 2 && org == orgId)){
                    Search search = new Search();
                    search.setDes("4");
                    PawnDetail pawnDetail = new PawnDetail();
                    pawnDetail.setId(userPawn.getId().toString());
                    pawnDetail.setGoodsId(userPawn.getGoodsId().toString());
                    pawnDetail.setTitle(userGoods.getName());
                    pawnDetail.setImage(OrgUtil.getSingleImage(userGoods.getImages()));
                    pawnDetail.setAuthPrice(userGoods.getAuthPrice().toString());//当品鉴定价
                    pawnDetail.setCollecterMoney(userPawn.getLoansPrice().toString());//当户期望价格
                    pawnDetail.setBidTimes(pawnAuctionService.getAllBidOrgCount(userPawn.getId()));//对该pawId竞拍的所有机构总数
                    pawnDetail.setBidCountdownTime("0");//已中标的典当，没有倒计时
                    //查看支付凭证
                    StringBuffer repawnTickets = new StringBuffer("");
                    if (pawnContinues.size() != 0){
                        for (int i = 0; i < pawnContinues.size(); i++) {
                            if(i == 0){
                                if (pawnContinues.get(i).getPawnTicket()!=null){
                                    repawnTickets.append(pawnContinues.get(i).getPawnTicket());
                                }
                            }else{
                                if (pawnContinues.get(i).getPawnTicket()!=null){
                                    repawnTickets.append(","+pawnContinues.get(i).getPawnTicket());
                                }

                            }
                        }
                    }
                    //续当付款凭证
                    pawnDetail.setRepawnVoucher(repawnTickets.toString());
                    //典当付款凭证
                    if (userPawn.getPayeeTicket()!=null){
                        pawnDetail.setState(1);
                        pawnDetail.setPawnVoucher(userPawn.getPayeeTicket());
                    }else {
                        pawnDetail.setState(0);
                        pawnDetail.setPawnVoucher("");
                    }
                    //赎当付款凭证
                    pawnDetail.setRedeemVoucher(userPawn.getRedeemTicket()!=null?userPawn.getRedeemTicket():"");
                    search.setPawnDetail(pawnDetail);
                    retList.add(search);
                }
                //赎当申请和已赎当
              else if ((state == 2 && redeemState <= 2 && org == orgId && now.compareTo(DateUtil.add(pawnEndTime,PaidangConst.BUFFER_DAYS)) == -1 ) || (state == 3 && redeemState ==3 && org == orgId && now.compareTo(DateUtil.add(pawnEndTime,PaidangConst.BUFFER_DAYS)) == -1)){
                    Search search = new Search();
                    search.setDes("5");
                    RedeemMini redeemMini = new RedeemMini();
                    redeemMini.setPawnId(userPawn.getId().toString());
                    redeemMini.setGoodsId(userPawn.getGoodsId().toString());
                    redeemMini.setTitle(userPawn.getGoodsName()!=null?userPawn.getGoodsName():"");
                    redeemMini.setAuthPrice(userGoods.getAuthPrice()!=null?userGoods.getAuthPrice().toString():"");
                    redeemMini.setImages(OrgUtil.getSingleImage(userGoods.getImages()));
                    redeemMini.setPawnTicketCode(userPawn.getPawnTicketCode()!=null?userPawn.getPawnTicketCode():"");
                    //已发放当金 = 贷款金额 - 首期综合费 若数据库已保存则取数据库，否则计算得到
                    if(userPawn.getUserMoney()!=null){
                        redeemMini.setUserMoney(userPawn.getUserMoney().toString());
                    }else if (userPawn.getPawnMoney()!=null && userPawn.getBeginMoney()!=null){
                        redeemMini.setUserMoney(userPawn.getBeginMoney().subtract(userPawn.getPawnMoney()).toString());
                    }else {
                        redeemMini.setUserMoney("0");
                    }
                    if (userPawn.getState()==3 && userPawn.getRedeemState()==3){
                        redeemMini.setRedeemState(1);//已赎当
                    }else {
                        redeemMini.setRedeemState(2);//赎当申请处理中
                    }
                    search.setRedeemMini(redeemMini);
                    retList.add(search);
                }
                //绝当
              else if (state == 4 && org == orgId && now.compareTo(DateUtil.add(pawnEndTime,PaidangConst.BUFFER_DAYS)) == 1){
                    Search search = new Search();
                    search.setDes("6");
                    DeadPawnMini deadPawnMini = new DeadPawnMini();
                    deadPawnMini.setPawnId(userPawn.getId().toString());
                    deadPawnMini.setGoodsId(userPawn.getGoodsId().toString());
                    deadPawnMini.setTitle(userPawn.getGoodsName()!=null?userPawn.getGoodsName():"");
                    deadPawnMini.setAuthPrice(userGoods.getAuthPrice()!=null?userGoods.getAuthPrice().toString():"");
                    deadPawnMini.setImages(OrgUtil.getSingleImage(userGoods.getImages()));
                    deadPawnMini.setPawnTicketCode(userPawn.getPawnTicketCode()!=null?userPawn.getPawnTicketCode():"");
                    //已发放当金 = 贷款金额 - 首期综合费 若数据库已保存则取数据库，否则计算得到
                    if(userPawn.getUserMoney()!=null){
                        deadPawnMini.setUserMoney(userPawn.getUserMoney().toString());
                    }else if (userPawn.getPawnMoney()!=null && userPawn.getBeginMoney()!=null){
                        deadPawnMini.setUserMoney(userPawn.getBeginMoney().subtract(userPawn.getPawnMoney()).toString());
                    }else {
                        deadPawnMini.setUserMoney("");
                    }
                    search.setDeadPawnMini(deadPawnMini);
                    retList.add(search);
                }
            }
        }

        //查商品表
        List<DeadPawnAuctionMini> jdAuctionList = myAppController.getDeadPawnAuctionList(keyword,mobileInfo,pageLimit);
        for (DeadPawnAuctionMini jd:jdAuctionList) {
            Search search = new Search();
            search.setDes("7");
            search.setDeadPawnAuctionMini(jd);
           retList.add(search);
        }

        //查快递表
        List<ExpressEx> expressExes = expressService.searchByName(keyword,orgId.toString());
        for (ExpressEx expressEx:expressExes) {
            ExpressInfo expressInfo = new ExpressInfo();
            expressInfo.setGoodsId(expressEx.getGoodsId());
            expressInfo.setGoodsName(expressEx.getGoodsName());
            expressInfo.setGoodsImage(OrgUtil.getSingleImage(expressEx.getGoodsImages()));
            expressInfo.setPawnTicketCode(expressEx.getPawnTicketCode());
            expressInfo.setExpressState(expressEx.getExpressState().toString());
            expressInfo.setExpressCode(expressEx.getExpressCode());
            Search search = new Search();
            search.setDes("8");
            search.setExpressInfo(expressInfo);
            retList.add(search);
        }

        return retList;
    }

    /**
     * 获取机构个人资料
     * */
    @ApiOperation(value = "获取机构资料",notes="获取机构基本信息")
    @RequestMapping(value="/orgInfo", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public OrgInfo getOrgInfo(MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());

//      测推送的
//       orgNotifyService.insertByTemplate(orgId,"2", PaidangMessage.USER_REPAWN_NOTIFY,"谁收到这条推送请联系我","3");
//       orgNotifyService.insertByTemplate(orgId,"2", PaidangMessage.USER_OVERDUE_NOTIFY,"Bill测试","3");
//       orgNotifyService.insertByTemplate(orgId,"5",PaidangMessage.PAWN_DEAD_NOTIFY,"Bill测试");
//       orgNotifyService.insertByTemplate(orgId, "3", PaidangMessage.ORG_BIDWON_NOFIFY, "This is a Test Msg");
//        orgNotifyService.insertByTemplate(orgId, "3", PaidangMessage.USER_REDEEM_NOTIFY, "巢江飞","2018-06-06");

        PawnOrgEx record = pawnOrgService.selectById(mobileInfo.getUserId().toString());
        if (record == null){
            throw new ApiException(MEnumError.USER_NOEXIST_ERROR);
        }
        OrgInfo orgInfo = new OrgInfo();
        orgInfo.setId(record.getId());
        orgInfo.setAccount(record.getAccount());
        orgInfo.setName(record.getName());
        orgInfo.setAdress(record.getAdress());
        orgInfo.setLegalPerson(record.getLegalPerson());
        orgInfo.setTel(record.getPhone());
        orgInfo.setBusinessLicense(record.getBusinessLicense());
        orgInfo.setBusinessLicenseCode(record.getBusinessLicenseCode());
        orgInfo.setOrgLogo(record.getOrgLogo());
        orgInfo.setRegisteredCapital(record.getRegisteredCapital());
        orgInfo.setPayPassword(record.getPayPassword());
        orgInfo.setAmount(record.getAmount());
        orgInfo.setStoreContacts(record.getStoreContacts());
        orgInfo.setStorePhone(record.getStorePhone());
        orgInfo.setSignature(record.getSignature());
        Integer auctionCount = goodsService.getAuctionCount(orgId, null);
        orgInfo.setAuctionCount(auctionCount==null?0:auctionCount);

        return orgInfo;
    }

    /**
     *
     * */
    @ApiOperation(value = "机构消息首页",notes="机构消息首页")
    @RequestMapping(value="/getMessageIndexs", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public List<MessageIndex> getMessageIndexs(MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (orgId == -1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //查询消息列表首页所有类型的消息  1:续当通知 2滞纳通知 3竞拍成功通知 4用户赎当 5绝当通知 等等...
        OrgMessageTypeExample orgMessageTypeExample = new OrgMessageTypeExample();
        List<OrgMessageType> types = orgMessageTypeService.selectByExample(orgMessageTypeExample);

        List<MessageIndex> retList = new ArrayList<>();
        for (OrgMessageType element:types){
            MessageIndex messageIndex = new MessageIndex();
            //set类型id，类型名称，类型图片名
            messageIndex.setTypeId(element.getId());
            messageIndex.setTypeName(element.getTypeName());
            messageIndex.setTypePic(element.getTypePic());
            OrgNotifyExample orgNotifyExample = new OrgNotifyExample();
            //找出每个类型消息里最新的一条，set到model的OrgNotifyInfo字段
            orgNotifyExample.setOrderByClause("create_time desc");
            orgNotifyExample.createCriteria().andOrgIdEqualTo(orgId).andRedirectTypeEqualTo(element.getId());
            List<OrgNotify> orgNotifys = orgNotifyService.selectByExample(orgNotifyExample);
            OrgNotifyInfo orgNotifyInfo = new OrgNotifyInfo();// 返回model
            if(orgNotifys.size()!=0){
                OrgNotify orgNotify = orgNotifys.get(0);//每个类型消息里最新的一条
                orgNotifyInfo.setId(orgNotify.getId());
                orgNotifyInfo.setContent(orgNotify.getContent());
                orgNotifyInfo.setCreateTime(orgNotify.getCreateTime()!=null?DateUtil.dateToStr(orgNotify.getCreateTime(),"yyyy-MM-dd"):"");
                orgNotifyInfo.setTitle(orgNotify.getTitle());
                orgNotifyInfo.setIsRead(orgNotify.getIsRead());
            }
            messageIndex.setOrgNotifyInfo(orgNotifyInfo);
            retList.add(messageIndex);
        }
        return retList;
    }
   /*
    public List<MessageIndex> getMessageIndexs(MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (orgId == -1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //消息列表首页 所有类型的消息
        OrgMessageTypeExample orgMessageTypeExample = new OrgMessageTypeExample();
        List<OrgMessageType> types = orgMessageTypeService.selectByExample(orgMessageTypeExample);

        List<MessageIndex> retList = new ArrayList<>();
        for (OrgMessageType element:types){
            MessageIndex messageIndex = new MessageIndex();
            //set类型名称，类型图片名
            messageIndex.setTypeName(element.getTypeName());
            messageIndex.setTypePic(element.getTypePic());
            OrgNotifyExample orgNotifyExample = new OrgNotifyExample();
            //找出每个类型消息里最新的一条，set到model的OrgNotifyInfo字段
            orgNotifyExample.setOrderByClause("create_time desc");
            orgNotifyExample.createCriteria().andOrgIdEqualTo(orgId).andRedirectTypeEqualTo(1)
                    .andRedirectContentEqualTo(element.getId().toString());
            List<OrgNotify> orgNotifys = orgNotifyService.selectByExample(orgNotifyExample);
            OrgNotifyInfo orgNotifyInfo = new OrgNotifyInfo();// 返回model
            if(orgNotifys.size()==0){
                 // do nothing
            }else{
                OrgNotify orgNotify = orgNotifys.get(0);//每个类型消息里最新的一条

                orgNotifyInfo.setId(orgNotify.getId());
                orgNotifyInfo.setContent(orgNotify.getContent());
                orgNotifyInfo.setCreateTime(orgNotify.getCreateTime()!=null?DateUtil.dateToStr(orgNotify.getCreateTime(),"yyyy-MM-dd"):"");
                orgNotifyInfo.setTitle(orgNotify.getTitle());
            }

            messageIndex.setOrgNotifyInfo(orgNotifyInfo);
            retList.add(messageIndex);
        }
        return retList;
    }
*/
    /**
     * 获取机构某一种类型消息的列表
     */
    @ApiOperation(value = "机构某类型所有消息",notes="获取机构某一种类型消息的列表，登陆,分页")
    @RequestMapping(value = "/getMsgTypeList", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<OrgNotifyInfo> getMsgTypeList(@ApiParam(value = "消息类型： 1-续当通知2-滞纳通知3-竞拍成功通知4-赎当通知5-绝当通知 ...", required = true)String typeId , MobileInfo mobileInfo, PageLimit pageLimit){
        if (StringUtils.isEmpty(typeId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (orgId == -1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        OrgNotifyExample example = new OrgNotifyExample();
        example.setOrderByClause("create_time desc");
        example.createCriteria().andOrgIdEqualTo(orgId).andRedirectTypeEqualTo(Integer.valueOf(typeId));
        startPage();
        List<OrgNotify> orgNotifies = orgNotifyService.selectByExample(example);
        List<OrgNotifyInfo> retList = new ArrayList<>();
        for (OrgNotify element : orgNotifies){
            OrgNotifyInfo orgNotifyInfo = new OrgNotifyInfo();
            orgNotifyInfo.setId(element.getId());
            orgNotifyInfo.setContent(element.getContent());
            orgNotifyInfo.setCreateTime(element.getCreateTime()!=null?DateUtil.dateToStr(element.getCreateTime(),"yyyy-MM-dd"):"");
            orgNotifyInfo.setIsRead(element.getIsRead());
            retList.add(orgNotifyInfo);
        }
        return retList;
    }
  /*  public List<OrgNotifyInfo> getMsgTypeList(@ApiParam(value = "类型id 1-续当通知2-滞纳通知3-竞拍通知4-赎当通知5-绝当通知", required = true)String type , MobileInfo mobileInfo, PageLimit pageLimit){
        if (StringUtils.isEmpty(type)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (orgId == -1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        OrgNotifyExample example = new OrgNotifyExample();
        example.setOrderByClause("create_time desc");
        example.createCriteria().andOrgIdEqualTo(orgId).andRedirectTypeEqualTo(1).andRedirectContentEqualTo(type);
        PaginationSupport.byPage(pageLimit.getPage(), pageLimit.getLimit(), false);
        List<OrgNotify> orgNotifies = orgNotifyService.selectByExample(example);
        List<OrgNotifyInfo> retList = new ArrayList<>();
        for (OrgNotify element : orgNotifies){
            OrgNotifyInfo orgNotifyInfo = new OrgNotifyInfo();
            orgNotifyInfo.setId(element.getId());
            orgNotifyInfo.setContent(element.getContent());
            orgNotifyInfo.setCreateTime(element.getCreateTime()!=null?DateUtil.dateToStr(element.getCreateTime(),"yyyy-MM-dd"):"");
            retList.add(orgNotifyInfo);
        }
        return retList;
    }*/

    /**
     * 删除机构消息
     */
    @ApiOperation(value = "机构删除消息",notes="登陆")
    @RequestMapping(value = "/delNotifyDetail", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer delNotifyDetail(MobileInfo mobileInfo, @ApiParam(value = "消息id", required = true)Integer id){
        if (id == null) {
            throw new ApiException("id");
        }
        OrgNotify record = orgNotifyService.selectByPrimaryKey(id);
        if(record == null){
            throw new ApiException(MEnumError.CONTENT_NOEXIST_ERROR);
        }
        int r = orgNotifyService.deleteByPrimaryKey(id);
        if (r != 1){
            throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
        }
        return r;
    }


}