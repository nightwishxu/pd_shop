package com.api.action;

import com.api.MEnumError;
import com.api.MErrorEnum;
import com.api.util.OrgUtil;
import com.api.util.PageLimit;
import com.api.view.orgAuctionPawn.BidWonDetail;
import com.api.view.orgAuctionPawn.PawnDetail;
import com.api.view.orgDeadPawnage.ExpressInfo;
import com.api.view.orgHome.CapitalRunningAccount.CapitalRunningAccountDetail;
import com.api.view.orgHome.bankCard.BankCard;
import com.api.view.orgHome.contractRecord.ContractDetail;
import com.api.view.orgHome.contractRecord.ContractPawn;
import com.api.view.orgHome.contractRecord.ContractType;
import com.api.view.orgHome.deadPawnAuction.DeadPawnAuctionDetail;
import com.api.view.orgHome.deadPawnAuction.DeadPawnAuctionMini;
import com.api.view.orgHome.deadPawnAuction.DeadPawnAuctionRecord;
import com.api.view.orgHome.deadPawnAuction.ToSendPageDetail;
import com.api.view.orgHome.myPawners.MyPawner;
import com.api.view.orgHome.myPawners.MyPawnerDetail;
import com.api.view.orgHome.myPawners.PawnedDetail;
import com.api.view.orgHome.myPawners.PawnedMini;
import com.base.ConstantsCode;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.dao.model.Result;
import com.base.util.BaseUtils;
import com.base.util.DateUtil;
import com.base.util.StringUtil;
import com.base.util.http.HttpUtil;
import com.demo.constant.DSPConsts;
import com.item.dao.model.*;
import com.item.daoEx.model.UserEx;
import com.item.service.AdminService;
import com.item.service.CodeService;
import com.item.service.UserNotifyService;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.OrgBalanceLogEx;
import com.paidang.daoEx.model.PawnTicketEx;
import com.paidang.daoEx.model.UserPawnEx;
import com.paidang.domain.qo.PawnTicketQo;
import com.paidang.service.*;
import com.paidang.utils.CostGenerator;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.ip.IpUtils;
import com.util.ApiStoreBankUtil;
import com.util.PaidangConst;
import com.util.PaidangMessage;
import com.util.apiStore.BankCardResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Lee on 2017/10/18.
 *
 * 个人中心
 */
@RestController
@RequestMapping(value = "/api/my", produces = { "application/json;charset=UTF-8" })
@Api(tags = "我的")
public class ApiMyAppController extends ApiBaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private UserPawnService userPawnService;
    @Autowired
    private PawnOrgService pawnOrgService;
    @Autowired
    private UserGoodsService userGoodsService;
    @Autowired
    private PawnAuctionService pawnAuctionService;
    @Autowired
    private PawnContinueService pawnContinueService;
    @Autowired
    private OrgBalanceLogService orgBalanceLogService;
    @Autowired
    private UserBalanceLogService userBalanceLogService;
    @Autowired
    private PawnLogService pawnLogService;
    @Autowired
    private OrgBankCardService orgBankCardService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsAuctionService goodsAuctionService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserNotifyService userNotifyService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private ExpressService expressService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private UserBankCardService userBankCardService;

    @Autowired
    private PawnTicketService pawnTicketService;

    @Autowired
    private BFileService fileService;


    @ApiOperation(value = "我参与的竞拍(竞拍标签页)",notes="我参与的竞拍(竞拍中)")
    @RequestMapping(value = "/getMyBiddingList", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<PawnDetail> getMyBiddingList(MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        //获得我竞拍过的pawnId
//        PaginationSupport.byPage(pageLimit.getPage(), pageLimit.getLimit(), false);
        startPage();
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer page = pageDomain.getPageNum();
        Integer limit = pageDomain.getPageSize();
        List<String> myAuctionList = pawnAuctionService.getMyBidPawnIdByOrgId(orgId.toString());
        List<PawnDetail> list = new ArrayList<>();
        for (String pawnId:myAuctionList){
            UserPawnEx userPawnEx = userPawnService.getUserPawnExById(pawnId);
            if (userPawnEx ==null){ //筛选出来的pawnId在典当表没有对应记录,正常情况下不应存在此类错误,除非手动改竞拍表pawnId字段
            //    throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
                continue;//若出现上面的情况，忽略这个pawnId
            }
            Integer state = userPawnEx.getState();
            PawnDetail pawnDetail = new PawnDetail();
            if(state == 1 && userPawnEx.getOrgId() == null ){
                pawnDetail.setId(userPawnEx.getId().toString());
                pawnDetail.setTitle(userPawnEx.getGoodsName1());
                pawnDetail.setCollecterId(userPawnEx.getUserId().toString());
                pawnDetail.setCollecterBankName(userPawnEx.getPayeeBankName());
                pawnDetail.setCollecterBankCardCode(userPawnEx.getPayeeBankCardCode());
                pawnDetail.setCollecterMoney(userPawnEx.getLoansPrice()!=null?userPawnEx.getLoansPrice().toString():"");
                pawnDetail.setPawnBeginTime(userPawnEx.getPawnBeginTime()!=null? DateUtil.dateToStr(userPawnEx.getPawnBeginTime(),"yyyy-MM-dd"):"");
                pawnDetail.setPawnEndTime(userPawnEx.getPawnEndTime()!=null?DateUtil.dateToStr(userPawnEx.getPawnEndTime(),"yyyy-MM-dd"):"");
                pawnDetail.setImage(OrgUtil.getSingleImage(userPawnEx.getImages()));
                pawnDetail.setAuthPrice(userPawnEx.getAuthPrice()!=null?userPawnEx.getAuthPrice().toString():"");
                pawnDetail.setAuthPriceTest(userPawnEx.getAuthPriceTest()!=null?userPawnEx.getAuthPriceTest().toString():"");
                pawnDetail.setBidTimes(userPawnEx.getBidTimes() != null ? Integer.valueOf(userPawnEx.getBidTimes()) : 0);
                //倒计时秒数
                if(userPawnEx.getOrgId() != null) {  //用户选择了中标机构，竞拍结束，set=0
                    pawnDetail.setBidCountdownTime("0");
                }else if(userPawnEx.getUserState() == 1){//如果用户在倒计时结束前放弃竞拍，倒计时也set=0
                    pawnDetail.setBidCountdownTime("0");
                }else {
                    Date now = new Date();
                    long auctionTime = PaidangConst.AUCTION_TIME * 1000;
                    Long countDownSeconnds = ((userPawnEx.getCreateTime().getTime() + auctionTime) - now.getTime()) / 1000;
                    if (countDownSeconnds > 0) {
                        pawnDetail.setBidCountdownTime(countDownSeconnds.toString());
                    }else{//倒计时已经走完 set=0
                        pawnDetail.setBidCountdownTime("0");
                    }
                }
                list.add(pawnDetail);
            }
        }

        if (list.size() <= limit){
            return list;
        }else {
            if ((page*limit)-1 > list.size()-1){
                return list.subList((page-1)*limit,list.size()-1);
            }else {
                return list.subList((page-1)*limit,(page*limit)-1);
            }
        }
    }

    /**
     *  TODO 需区分 业务中 和处理中（能操作的）
     *
     * @param mobileInfo
     * @param pageLimit
     * @return
     *  2 可处理 pawnStatus =1 完善当票 orgStatus=1 签署
     */
    @ApiOperation(value = "我参与的竞拍(已中标标签页)",notes="我参与的竞拍(已中标标签页)")
    @RequestMapping(value = "/getMyBidWonList", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<PawnDetail> getMyBidWonList(MobileInfo mobileInfo, @ApiParam(value = "1 业务中 2 处理中",required = false) Integer type){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        startPage();
        List<UserPawnEx> list;
        if (orgId == 0){//到期的典当被宝祥机构兜底
            list = userPawnService.getBaoXiangList("0");
        }else{
            list = userPawnService.getMyBidWonList(orgId.toString());
        }
        List<PawnDetail> retList = new ArrayList<>();
        for (UserPawnEx userPawnEx : list) {
            PawnDetail pawnDetail = new PawnDetail();
            pawnDetail.setLastPawnContinueId(userPawnEx.getLastPawnContinueId());
            pawnDetail.setAuctionCount(userPawnEx.getCnt());
            pawnDetail.setPawnStatus(userPawnEx.getPawnStatus());
            pawnDetail.setUserStatus(userPawnEx.getUserStatus());
            pawnDetail.setOrgStatus(userPawnEx.getOrgStatus());
            pawnDetail.setId(userPawnEx.getId().toString());
            pawnDetail.setTitle(userPawnEx.getGoodsName1());
            pawnDetail.setCollecterId(userPawnEx.getUserId()!=null?userPawnEx.getUserId().toString():"");
            pawnDetail.setCollecterBankName(userPawnEx.getPayeeBankName());
            pawnDetail.setCollecterBankCardCode(userPawnEx.getPayeeBankCardCode());
            pawnDetail.setCollecterMoney(userPawnEx.getLoansPrice()!=null?userPawnEx.getLoansPrice().toString():"");
            pawnDetail.setPawnBeginTime(userPawnEx.getPawnBeginTime()!=null?DateUtil.dateToStr(userPawnEx.getPawnBeginTime(),"yyyy-MM-dd"):"");
            pawnDetail.setPawnEndTime(userPawnEx.getPawnEndTime()!=null?DateUtil.dateToStr(userPawnEx.getPawnEndTime(),"yyyy-MM-dd"):"");
            pawnDetail.setImage(OrgUtil.getSingleImage(userPawnEx.getImages()));
            pawnDetail.setAuthPrice(userPawnEx.getAuthPrice()!=null?userPawnEx.getAuthPrice().toString():"");
            pawnDetail.setAuthPriceTest(userPawnEx.getAuthPriceTest()!=null?userPawnEx.getAuthPriceTest().toString():"");
            pawnDetail.setBidTimes(userPawnEx.getBidTimes() != null ? Integer.valueOf(userPawnEx.getBidTimes()) : null);
            pawnDetail.setBidCountdownTime("0");//已中标的典当，没有倒计时，不能竞拍
            //查看支付凭证
            PawnContinueExample pawnContinueExample = new PawnContinueExample();
            pawnContinueExample.createCriteria().andPawnIdEqualTo(userPawnEx.getId());
            pawnContinueExample.setOrderByClause("create_time");
            List<PawnContinue> pawnContinues = pawnContinueService.selectByExample(pawnContinueExample);
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
            if (userPawnEx.getPayeeTicket()!=null){
                pawnDetail.setState(1);
                pawnDetail.setPawnVoucher(userPawnEx.getPayeeTicket());
            }else {
                pawnDetail.setState(0);
                pawnDetail.setPawnVoucher("");
            }
            //赎当付款凭证
            pawnDetail.setRedeemVoucher(userPawnEx.getRedeemTicket()!=null?userPawnEx.getRedeemTicket():"");

           //订单是否失效,中标后PAY_LIMIT_TIME分钟以内未支付贷款即失效
            if (orgId != 0){
                Date now = new Date();
                Date orgSelectedTime = userPawnEx.getOrgSelectedTime();
                if (orgSelectedTime == null){
                    pawnDetail.setIsInvalid("0");
                }else {
                    Date validTime = DateUtil.offsetMinute(orgSelectedTime,PaidangConst.PAY_LIMIT_TIME);
                    if (now.compareTo(validTime) <= 0){
                        pawnDetail.setIsInvalid("1");
                    }else{
                        pawnDetail.setIsInvalid("0");
                    }
                }
            }else{
                pawnDetail.setIsInvalid("1");
            }

            retList.add(pawnDetail);
        }
        //排序
        List<PawnDetail> temp = new ArrayList();
        Iterator it = retList.iterator();
        while (it.hasNext()){
            PawnDetail pawnDetail = (PawnDetail) it.next();
            if (pawnDetail.getState()==0){
                temp.add(pawnDetail);
                it.remove();
            }
        }
        Collections.sort(retList, new Comparator<PawnDetail>() {
            @Override
            public int compare(PawnDetail o1, PawnDetail o2) {
                if (Integer.valueOf(o1.getId()) > Integer.valueOf(o2.getId())){
                    return -1;
                }else {
                    return 1;
                }
            }
        });
        Collections.sort(temp, new Comparator<PawnDetail>() {
            @Override
            public int compare(PawnDetail o1, PawnDetail o2) {
                if (Integer.valueOf(o1.getId()) > Integer.valueOf(o2.getId())){
                    return 1;
                }else {
                    return -1;
                }
            }
        });
        for (PawnDetail pd: temp){
            retList.add(0,pd);
        }
        return retList;
    }


    @ApiOperation(value = "中标处理详情页面",notes="中标处理详情页面")
    @RequestMapping(value = "/getMyBidWonDetail", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public BidWonDetail getMyBidWonDetail(@ApiParam(value = "典当表id",required = true) String pawnId,MobileInfo mobileInfo){
        if(StringUtils.isEmpty(pawnId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());//机构id
        UserPawnEx userPawnEx = userPawnService.getUserPawnExById(pawnId);
        if(userPawnEx == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer goodsId = userPawnEx.getGoodsId();
        String goodName = StringUtils.isNotEmpty(userPawnEx.getGoodsName1())?userPawnEx.getGoodsName1():"";
        String authPrice = userPawnEx.getAuthPrice()!=null?userPawnEx.getAuthPrice().toString():"";
        String images = StringUtils.isNotEmpty(userPawnEx.getImages())?userPawnEx.getImages():"";
        String pawnTicketCode = StringUtils.isNotEmpty(userPawnEx.getPawnTicketCode())?userPawnEx.getPawnTicketCode():"";
        String payeeTicket = userPawnEx.getPayeeTicket();//机构打款凭证
        User user = userService.selectByPrimaryKey(userPawnEx.getUserId());//当户
        if (user == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        String pawnerName = StringUtils.isNotEmpty(userPawnEx.getUserName())?userPawnEx.getUserName():"";
        String pawnerTelNum = StringUtils.isNotEmpty(userPawnEx.getUserPhone())?userPawnEx.getUserPhone():"";
        //当户地址
        String pawnerAddress = "";
        UserAddressExample userAddressExample = new UserAddressExample();
        userAddressExample.createCriteria().andUserIdEqualTo(user.getId()).andIsDefaultEqualTo(1);
        List<UserAddress> userAddresses=  userAddressService.selectByExample(userAddressExample);
        if (userAddresses.size() != 0){
            pawnerAddress = userAddresses.get(0).getAddress()!=null&&userAddresses.get(0).getArea()!=null?userAddresses.get(0).getArea()+userAddresses.get(0).getAddress():"";
        }

        String collecterMoney =  userPawnEx.getLoansPrice()!=null?userPawnEx.getLoansPrice().toString():"";//当户需求贷款额
        String collecterRate = userPawnEx.getLoansRate()!=null?userPawnEx.getLoansRate().toString():"";//当户期望利率
        BigDecimal beginMoney = userPawnEx.getBeginMoney();//机构放贷金额(本金)
        if (beginMoney == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        String rateMoney = userPawnEx.getPawnMoney()!=null?userPawnEx.getPawnMoney().toString():"";//首期综合费
        String rate = userPawnEx.getRate()!=null?userPawnEx.getRate().toString():"";//综合费率
        BigDecimal moneyRate = userPawnEx.getMoneyRate();//赎当利率
        if (moneyRate == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        // 赎当利息 = 本金 * 赎当利率 * 时长
        BigDecimal moneyCost = CostGenerator.getInterest(beginMoney,moneyRate,userPawnEx.getBeginPawnMonth());
        //用户收款账号 和 银行名称
//      String payeeName = StringUtils.isNotEmpty(userPawnEx.getPayeeName())?userPawnEx.getPayeeName():"";
        String pawnerBankCardCode = StringUtils.isNotEmpty(userPawnEx.getPayeeBankCardCode())?userPawnEx.getPayeeBankCardCode():"";
//      String pawnerBankName = StringUtils.isNotEmpty(userPawnEx.getPayeeBankName())?userPawnEx.getPayeeBankName():"";
        String payeeName="";
        String pawnerBankName="";
        if (StringUtils.isNotEmpty(pawnerBankCardCode)){
            UserBankCardExample userBankCardExample = new UserBankCardExample();
            userBankCardExample.createCriteria().andUserIdEqualTo(user.getId()).andBankCardNoEqualTo(pawnerBankCardCode);
            List<UserBankCard> userBankCards = userBankCardService.selectByExample(userBankCardExample);
            if (userBankCards!=null&&userBankCards.size()>0){
                payeeName = userBankCards.get(0).getBankCardUserName();
                pawnerBankName = userBankCards.get(0).getBankCardName();
            }
        }else {
            pawnerBankName = StringUtils.isNotEmpty(userPawnEx.getPayeeBankName()) ? userPawnEx.getPayeeBankName() : "";
        }
        //该页面贷款开始时间 (现定为中标时间即为开始典当日期)
        String loanBeginTime = userPawnEx.getPawnBeginTime()!= null?DateUtil.dateToStr(userPawnEx.getPawnBeginTime(),"yyyy-MM-dd"):"";
        //贷款结束时间
        String loanEndTime = userPawnEx.getBeginPawnEndTime()!=null?DateUtil.dateToStr(userPawnEx.getBeginPawnEndTime(),"yyyy-MM-dd"):"";

        //set页面返回model
        BidWonDetail bidWonDetail = new BidWonDetail();
        bidWonDetail.setPawnStatus(userPawnEx.getPawnStatus());
        bidWonDetail.setUserStatus(userPawnEx.getUserStatus());
        bidWonDetail.setOrgStatus(userPawnEx.getOrgStatus());
        bidWonDetail.setPawnId(pawnId);
        bidWonDetail.setGoodsId(goodsId.toString());
        bidWonDetail.setGoodsName(goodName);
        bidWonDetail.setImages(images);
        bidWonDetail.setAuthPrice(authPrice);
        bidWonDetail.setCollecterMoney(collecterMoney);
        bidWonDetail.setCollecterRate(collecterRate);
        bidWonDetail.setLoanAmount(beginMoney.toString());
        bidWonDetail.setRate(rate);// 综合费率
        bidWonDetail.setCost(rateMoney);//首期综合费
        bidWonDetail.setMoneyRate(moneyRate.toString());//赎当利率
        bidWonDetail.setMoneyCost(moneyCost.toString());//赎当利息
        bidWonDetail.setPawnerAddress(pawnerAddress);
        bidWonDetail.setPawnerName(pawnerName);
        bidWonDetail.setPawnerTelNum(pawnerTelNum);
        bidWonDetail.setPawnTicketCode(pawnTicketCode);
        bidWonDetail.setPayeeName(payeeName);
        bidWonDetail.setPawnerBankCardCode(pawnerBankCardCode);
        bidWonDetail.setPawnerBankName(pawnerBankName);
        bidWonDetail.setLoanBeginTime(loanBeginTime);
        bidWonDetail.setLoanEndTime(loanEndTime);
        bidWonDetail.setIsPay(StringUtils.isEmpty(payeeTicket)?"0":"1");
        //订单是否失效,中标后PAY_LIMIT_TIME分钟以内未支付贷款即失效
        if (orgId != 0){
            Date now = new Date();
            Date orgSelectedTime = userPawnEx.getOrgSelectedTime();
            if (orgSelectedTime == null){
                bidWonDetail.setIsInvalid("0");
            }else {
                Date validTime = DateUtil.offsetMinute(orgSelectedTime,PaidangConst.PAY_LIMIT_TIME);
                if (now.compareTo(validTime) <= 0){
                    bidWonDetail.setIsInvalid("1");
                }else
                    bidWonDetail.setIsInvalid("0");
            }
        }else
            bidWonDetail.setIsInvalid("1");
        return bidWonDetail;
    }


    @ApiOperation(value = "完善当票信息",notes="已经中标的典当竞拍,完善当票信息")
    @RequestMapping(value = "/completePawnTicket", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer completePawnTicket(@ApiParam(value = "典当id",required = true) Integer pawnId,
                           @ApiParam(value = "当票号",required = true) String pawnTicketCode,
                           @ApiParam(value = "复核",required = true) String checker,
                           @ApiParam(value = "经办",required = true) String handler,
                           MobileInfo mobileInfo){

        Integer orgUserId = mobileInfo.getUserId();
        User orgUser = userService.selectByPrimaryKey(orgUserId);
        if(orgUser == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        UserPawn userPawn = userPawnService.selectByPrimaryKey(pawnId);
        if(userPawn == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        if (userPawn.getPawnStatus()!=1){
            throw new ApiException(400,"典当状态异常");
        }
        //已经使用过的当号排除
        UserPawnExample userPawnExample = new UserPawnExample();
        userPawnExample.createCriteria().andPawnTicketCodeEqualTo(pawnTicketCode).andStateGreaterThanOrEqualTo(2);
        Integer cnt = userPawnService.countByExample(userPawnExample);
        if (cnt > 0){
            throw new ApiException(MEnumError.PAWNTICKETCODE_OCCUPPIED);
        }


        String projectCode = BaseUtils.getRandomOrderId("D");
        Integer ticketId = pawnTicketService.addDDTicket(userPawn, projectCode, pawnTicketCode, checker, handler, DSPConsts.CONTRACT_REMARK);
        UserPawn temp = new UserPawn();
        temp.setId(pawnId);
        temp.setProjectCode(projectCode);
        temp.setPawnTicketCode(pawnTicketCode);
        temp.setPawnTicket(ticketId.toString());
        temp.setPawnStatus(2);
        temp.setModifyTime(new Date());
        userPawnService.updateByPrimaryKeySelective(temp);
//        Convert.digitToChinese()

        return 1;

    }




//    @ApiOperation(value = "签署合同-确认短信",notes="完善当票信息后进行签署合同")
//    @RequestMapping(value = "/sign/confirmSmsCode", method = RequestMethod.POST)
//    @ApiMethod(isLogin = true)
//    public Integer signConfirmSmsCode(MobileInfo mobileInfo,@ApiParam(value = "典当id",required = true) Integer pawnId
//            ,@ApiParam(value = "验证码",required = true) String  checkCode) throws Exception{
//        Integer orgUserId = mobileInfo.getUserId();
//        User orgUser = userService.selectByPrimaryKey(orgUserId);
//        if(orgUser == null){
//            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
//        }
//        UserPawn userPawn = userPawnService.selectByPrimaryKey(pawnId);
//        if (userPawn==null){
//            throw new ApiException(400,"典当不存在");
//        }
//        PawnTicket pawnTicket = pawnTicketService.getByProjectCode(userPawn.getProjectCode());
//        if (pawnTicket==null){
//            throw new ApiException(400,"当票异常");
//        }
//        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String ip = IpUtils.getIpAddr(request);
//        User pawner = userService.selectByPrimaryKey(userPawn.getUserId());
//        Integer orgId = userPawn.getOrgId();
//        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(orgId);
//        Date signTime = AnXinSignService.confirmSmsCode(pawnOrg.getAnxinsignId(), userPawn.getProjectCode(), checkCode);
//        PawnTicket temp = new PawnTicket();
//        temp.setId(pawnTicket.getId());
//        temp.setOrgSignTime(signTime);
//        temp.setOrgLocation(ip);
//        temp.setOrgStatus(2);
//        boolean flag = false;
//        if (pawnTicket.getUserStatus()!=null && pawnTicket.getUserStatus()==2){
//            temp.setStatus("2");
//            flag = true;
//        }
//        if (flag){
//            String contractNo = AnXinSignService.createContract(DSPConsts.contractPawnTemplateId, "互联网电子典当凭证",
//                    pawner.getAnxinsignId(), pawnTicket.getUserLocation(), userPawn.getProjectCode(), pawnTicket.getSignTime(), pawnOrg.getAnxinsignId(),
//                    ip, signTime, pawnTicket);
//            userPawn.setPawnStatus(3);
//            userPawn.setContractId(contractNo);
//            temp.setContractId(contractNo);
//            userPawnService.updateByPrimaryKeySelective(userPawn);
//        }
//        pawnTicketService.updateByPrimaryKeySelective(temp);
//        return 1;
//    }

    @ApiOperation(value = "签署合同-发送短信",notes="完善当票信息后进行签署合同")
    @RequestMapping(value = "/sign/sendSmsCode", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer signSendSmsCode(MobileInfo mobileInfo,@ApiParam(value = "典当id",required = true) Integer pawnId
            ,@ApiParam(value = "续当id,续当时传",required = false) Integer lastPawnContinueId) throws Exception{
        UserPawn userPawn = userPawnService.selectByPrimaryKey(pawnId);
        if (userPawn==null){
            throw new ApiException(400,"典当异常");
        }
        String projectCode = null;
        if (lastPawnContinueId!=null){
            if (!Objects.equals(userPawn.getLastPawnContinueId(),lastPawnContinueId)){
                throw new ApiException(400,"续当编号异常");
            }
            PawnContinue pawnContinue = pawnContinueService.selectByPrimaryKey(lastPawnContinueId);
            if (pawnContinue==null){
                throw new ApiException(400,"续当异常");
            }
            if (StringUtils.isBlank(pawnContinue.getContinuePawnTicketCode())){
                throw new ApiException(400,"请先完善当票");
            }
            projectCode = pawnContinue.getProjectCode();
        }else {
            projectCode = userPawn.getProjectCode();
        }
        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(userPawn.getOrgId());

        if (StringUtils.isBlank(pawnOrg.getAnxinsignId())){
            boolean falg = false;
            String anxinPhone = pawnOrg.getAnxinPhone();
            if (StringUtils.isBlank(pawnOrg.getAnxinPhone())){
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("orgId",userPawn.getOrgId());
                List<UserEx> userExes = userService.selectOrgUsersList(map);
                anxinPhone = userExes.get(0).getPhone();
                falg = true;
            }
            String userId = AnXinSignService.companyRegister(pawnOrg.getName(), pawnOrg.getBusinessLicenseCode(),anxinPhone, pawnOrg.getLandLinePhone(), pawnOrg.getLegalPerson(), pawnOrg.getIdCard());
            pawnOrg.setAnxinsignId(userId);
            if (falg){
                pawnOrg.setAnxinPhone(anxinPhone);
            }
            pawnOrgService.updateByPrimaryKeySelective(pawnOrg);
        }
        AnXinSignService.sendSmsCode(pawnOrg.getAnxinsignId(),projectCode,null);

        return 1;
    }


    @ApiOperation(value = "签署合同-确认短信",notes="完善当票信息后进行签署合同")
    @RequestMapping(value = "/sign/confirmSmsCode", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer signConfirmSmsCode(MobileInfo mobileInfo,@ApiParam(value = "典当id",required = true) Integer pawnId
            ,@ApiParam(value = "续当id,续当时传",required = false) Integer lastPawnContinueId
            ,@ApiParam(value = "验证码",required = true) String  checkCode) throws Exception{
        UserPawn userPawn = userPawnService.selectByPrimaryKey(pawnId);
        if (userPawn==null){
            throw new ApiException(400,"典当异常");
        }
        String projectCode = null;
        PawnContinue pawnContinue =null;
        if (lastPawnContinueId!=null){
            if (!Objects.equals(userPawn.getLastPawnContinueId(),lastPawnContinueId)){
                throw new ApiException(400,"续当编号异常");
            }
            pawnContinue = pawnContinueService.selectByPrimaryKey(lastPawnContinueId);
            if (pawnContinue==null){
                throw new ApiException(400,"续当异常");
            }
            projectCode = pawnContinue.getProjectCode();
        }else {
            projectCode = userPawn.getProjectCode();
        }
        User user = userService.selectByPrimaryKey(userPawn.getUserId());
        PawnTicket pawnTicket = pawnTicketService.getByProjectCode(projectCode);
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = IpUtils.getIpAddr(request);
        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(userPawn.getOrgId());
        Date signTime = AnXinSignService.confirmSmsCode(pawnOrg.getAnxinsignId(), projectCode, checkCode);
        PawnTicket temp = new PawnTicket();
        temp.setId(pawnTicket.getId());
        temp.setOrgSignTime(signTime);
        temp.setOrgLocation(ip);
        temp.setOrgStatus(2);
        boolean flag = false;
        if (pawnTicket.getUserStatus()!=null && pawnTicket.getUserStatus()==2){
            temp.setStatus("2");
            flag = true;
        }
        if (flag){
            if (lastPawnContinueId ==null){
                String contractNo = AnXinSignService.createContract(DSPConsts.contractPawnTemplateId,"互联网电子典当凭证",
                        user.getAnxinsignId(),pawnTicket.getUserLocation(),userPawn.getProjectCode(),signTime,pawnOrg.getAnxinsignId(),
                        ip,signTime,pawnTicket);
                userPawn.setPawnStatus(3);
                userPawn.setContractId(contractNo);
                temp.setContractId(contractNo);
                userPawnService.updateByPrimaryKeySelective(userPawn);
            }else {
                String contractNo = AnXinSignService.createXdContract(DSPConsts.contractRePawnTemplateId,"互联网典当续当凭证",
                        user.getAnxinsignId(),pawnTicket.getUserLocation(),userPawn.getProjectCode(),signTime,pawnOrg.getAnxinsignId(),
                        ip,signTime,pawnTicket,userPawn);
                pawnContinue.setState(4);
                pawnContinue.setContractId(contractNo);
                temp.setContractId(contractNo);
                pawnContinueService.updateByPrimaryKeySelective(pawnContinue);
            }
        }
        pawnTicketService.updateByPrimaryKeySelective(temp);
        return 1;
    }




    @ApiOperation(value = "确认支付贷款按钮",notes="已经中标的典当竞拍,机构支付贷款")
    @RequestMapping(value = "/payLoan", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer payLoan(@ApiParam(value = "典当表id",required = true) String pawnId,
                       @ApiParam(value = "机构打款凭证图片id",required = true) String payeeTicketId,
                                   MobileInfo mobileInfo){
        Integer orgUserId = mobileInfo.getUserId();
        User orgUser = userService.selectByPrimaryKey(orgUserId);
        if(orgUser == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        if (StringUtils.isEmpty(pawnId) || StringUtils.isEmpty(payeeTicketId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        UserPawn userPawn = userPawnService.selectByPrimaryKey(Integer.valueOf(pawnId));
        if(userPawn == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer goodsId = userPawn.getGoodsId();
        Integer userId = userPawn.getUserId();
        Integer orgId = userPawn.getOrgId();
        UserGoods userGoods = userGoodsService.selectByPrimaryKey(goodsId);
        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(orgId);
        User pawner = userService.selectByPrimaryKey(userId);//当户

        //计算用户实际得款（当金-首期综合费），即机构银行卡实际支出
        BigDecimal money;
        if (userPawn.getPawnMoney() == null || userPawn.getBeginMoney() == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        money = userPawn.getBeginMoney().subtract(userPawn.getPawnMoney());
        //取机构的银行卡，竞拍判断有没有绑定，没有跳转绑卡页面，机构卡现在只能有一张。
        OrgBankCardExample example = new OrgBankCardExample();
        example.createCriteria().andOrgIdEqualTo(orgId).andBankCardTypeEqualTo(1);
        List<OrgBankCard> cards = orgBankCardService.selectByExample(example);
        if (cards.size() == 0){
            throw new ApiException(MEnumError.NO_ORG_BANKCARD_BIND);
        }
        OrgBankCard card = cards.get(0);
        //判断支付行为是否在发生在中标后PAY_LIMIT_TIME分钟以内
        if (orgId != 0){
            Date now = new Date();
            Date orgSelectedTime = userPawn.getOrgSelectedTime();
            if (orgSelectedTime == null){
                throw new ApiException(MEnumError.PAY_OUT_OF_TERM);
            }else {
                Date validTime = DateUtil.offsetMinute(orgSelectedTime,PaidangConst.PAY_LIMIT_TIME);
                if (now.compareTo(validTime) > 0){
                    throw new ApiException(MEnumError.PAY_OUT_OF_TERM);
                }
            }
        }
        //更改典当表有关状态
        userPawn.setIsVerify(1);
        userPawn.setPayeeTicket(payeeTicketId);//机构打款凭证
        userPawn.setPayeeState(1);//1-已到账
        userPawn.setState(2);//已典当
        userPawn.setLastPawnMonth(userPawn.getBeginPawnMonth());//最新一期当期
        //set典当表机构信息
        userPawn.setOrgName(pawnOrg.getName());
        userPawn.setOrgAddress(pawnOrg.getAdress());
        userPawn.setOrgLicense(pawnOrg.getBusinessLicenseCode());
        userPawn.setOrgSeal(pawnOrg.getSeal());
        userPawn.setOrgTel(pawnOrg.getPhone());
        //set典当表打款人信息（打款人:机构银行卡的持卡人名字），机构银行卡号（用于用户续当收取综合费）
        userPawn.setPayName(card.getBankCardUserName());
        userPawn.setPayPhone(orgUser.getPhone());
        userPawn.setPayBacnkCardCode(card.getBankCardNo());
        userPawn.setPayBankName(card.getBankCardName()!=null?card.getBankCardName():"");
        //计算本次典当机构需要向平台缴纳的服务费
        if (userPawn.getPawnMoney() == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        BigDecimal platformFee = CostGenerator.getPlatformFee(userPawn.getPawnMoney());
        userPawn.setPlatformState(0);//0未缴纳1已缴纳平台服务费
        userPawn.setPlatformRate(PaidangConst.PLATFORM_INTEREST);
        userPawn.setPlatformMoney(platformFee);
        userPawn.setPawnStatus(4);
        userPawnService.updateByPrimaryKey(userPawn);

        //把机构已经打款消息推送给用户
        userNotifyService.insertByTemplate(userId, "1", PaidangMessage.PAWN_SUCCESS_NOTIFY,userGoods.getName(),pawnOrg.getName(),userPawn.getPayeeBankCardCode());
        //典当记录表
        PawnLog pawnLog = new PawnLog();
        pawnLog.setGoodsId(userPawn.getGoodsId());
        pawnLog.setUserId(userPawn.getUserId());
        pawnLog.setRefId(userPawn.getId());
        pawnLog.setOrgId(userPawn.getOrgId());
        pawnLog.setGoodsName(userGoods.getName());
        pawnLog.setAuthPrice(userGoods.getAuthPrice());
        pawnLog.setGoodsCateId(userGoods.getCateId());
        pawnLog.setGoodsCate(userGoods.getCateCode());
        pawnLog.setOrgName(pawnOrg.getName());
        pawnLog.setMoney(userPawn.getBeginMoney());
        //pawnLog.setPawnMonth(userPawn.getPawnTime());
        if(null == userPawn.getLastPawnMonth()){
            pawnLog.setPawnMonth(userPawn.getPawnTime());
        }else{
            pawnLog.setPawnMonth(userPawn.getLastPawnMonth());
        }
        pawnLog.setType(1);// 典当
        pawnLog.setUserName(pawner.getName());
        pawnLog.setTradeCardBank(userPawn.getPayeeBankName());
        pawnLog.setTradeCardCode(userPawn.getPayeeBankCardCode());
        pawnOrg.setCreateTime(new Date());
        pawnLogService.insert(pawnLog);

        OrgBalanceLog orgBalanceLog = new OrgBalanceLog();
        orgBalanceLog.setOrgId(userPawn.getOrgId());
        orgBalanceLog.setMoney(money);
        orgBalanceLog.setType(1);//支出
        orgBalanceLog.setItem("1");//用户典当
        orgBalanceLog.setInfo("id是"+orgId+"的机构的"+userPawn.getPayBankName()+"卡号为"+userPawn.getPayBacnkCardCode()+"支付典当款"+money+"元给用户"+userId+",当号："+userPawn.getPawnTicketCode());
        orgBalanceLog.setPawnCode(userPawn.getPawnTicketCode());
        orgBalanceLog.setFid(userPawn.getId());
        orgBalanceLog.setTradeType(10);// 线下银行卡
        orgBalanceLog.setTradeCardBank(userPawn.getPayBankName());
        orgBalanceLog.setTradeCardCode(userPawn.getPayBacnkCardCode());
        orgBalanceLog.setTradeTicket(userPawn.getPayeeTicket());
        orgBalanceLog.setUserId(userId);
        orgBalanceLog.setUserName(pawner.getName());
        orgBalanceLog.setUserPhone(pawner.getPhone()!=null?pawner.getPhone():pawner.getAccount());
        orgBalanceLog.setCreateTime(new Date());
        orgBalanceLogService.insert(orgBalanceLog);

        UserBalanceLog userBalanceLog = new UserBalanceLog();
        userBalanceLog.setTradeNo(userPawn.getPawnTicketCode());//现在该字段作为当号
        userBalanceLog.setCid(userPawn.getId());
        userBalanceLog.setUserId(userPawn.getUserId());
        userBalanceLog.setAmount(money);
        userBalanceLog.setType(1);//增
        userBalanceLog.setItem("1");//典当
        userBalanceLog.setInfo("用户"+userPawn.getUserId()+"的"+userPawn.getPayeeBankName()+"卡号是"+userPawn.getPayeeBankCardCode()+"的银行卡收到机构"+orgId+"的当款"+money+"元，本次典当的当号："+userPawn.getPawnTicketCode());
        userBalanceLog.setTradeType(10);// 线下银行卡
        userBalanceLog.setTradeCardBank(userPawn.getPayeeBankName());
        userBalanceLog.setTradeCardCode(userPawn.getPayeeBankCardCode());
        userBalanceLog.setTradeTicket(userPawn.getPayeeTicket());
        userBalanceLog.setOrgId(orgId);
        userBalanceLog.setOrgName(pawnOrg.getName());
        userBalanceLog.setOrgPhone(pawnOrg.getPhone());
        userBalanceLog.setCreateTime(new Date());
        userBalanceLogService.insert(userBalanceLog);
        return 1;
    }


  /*  @ApiOperation(value = "拒绝放贷",notes="拒绝放贷")
    @RequestMapping(value = "/pawnReject", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Ret pawnReject(@ApiParam(value = "典当表id",required = true) String pawnId, MobileInfo mobileInfo){
        if(StringUtils.isEmpty(pawnId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer pawnId_int = Integer.valueOf(pawnId);
        UserPawn userPawn = new UserPawn();
        userPawn.setId(pawnId_int);
        userPawn.setState(-1);//典当状态:已取消
        userPawn.setUserState(4);//用户状态:机构拒绝
        int result = userPawnService.insert(userPawn);
        Ret ret = new Ret();
        if (result == 1){
            ret.setCode(1);
            ret.setMsg("操作成功");
            return  ret;
        }
        ret.setCode(0);
        ret.setMsg("操作失败");
        return ret;
    }*/

    @ApiOperation(value = "我的当户",notes="个人中心->我的当户")
    @RequestMapping(value = "/myPawners", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<MyPawner> getMyPawnersList(MobileInfo mobileInfo,PageLimit pageLimit){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if(orgId == -1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        startPage();
        List<UserPawnEx> userPawnExList = userPawnService.getMyPawnersList(orgId.toString(),"");
        List<MyPawner> retList = new ArrayList<>();
        for (UserPawnEx element:userPawnExList){
            MyPawner myPawner = new MyPawner();
            myPawner.setPawnerId(element.getUserId().toString());//当户id
            myPawner.setPawnerName(element.getUserName());
            myPawner.setPawnerTelNum(element.getUserPhone());
            myPawner.setUserPic(element.getHeadImg());
            myPawner.setTotalLoan(element.getTotalAmountForPawner().toString());
            myPawner.setIdCardNum(element.getUserIdCard());
            //用户地址
            /*String pawnerAddress="";
            UserAddressExample userAddressExample = new UserAddressExample();
            userAddressExample.createCriteria().andUserIdEqualTo(element.getUserId()).andIsDefaultEqualTo(1);
            List<UserAddress> userAddresses = userAddressService.selectByExample(userAddressExample);
            if(userAddresses.size() != 0){
                pawnerAddress = userAddresses.get(0).getAddress()!=null&&userAddresses.get(0).getArea()!=null?userAddresses.get(0).getArea()+userAddresses.get(0).getAddress():"";
            }
            myPawner.setPawnerAddress(pawnerAddress);*/
            myPawner.setPawnerAddress(element.getUserAddress());
            retList.add(myPawner);
        }
        return retList;
    }


    @ApiOperation(value = "当户和当品",notes="个人中心->我的当户->该当户和他的当品列表")
    @RequestMapping(value = "/pawnerWithPawns", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public MyPawnerDetail getPawnerWithPawns(@ApiParam(value = "当户id",required = true) String pawnerId,MobileInfo mobileInfo){
        if(StringUtils.isEmpty(pawnerId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
  //    页面下部当户当品列表  该接口表示的页面上部当户信息由上面的getMyPawnersList接口获取,请app端由上一页面传递到本页面,不再由本接口获取
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if(orgId == -1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
//      PaginationSupport.byPage(pageLimit.getPage(), pageLimit.getLimit(), false);
        List<UserPawnEx> userPawnExList = userPawnService.getPawnerzPawnList(orgId.toString(),pawnerId.toString());
        List<PawnedMini> pawnedMiniList = new ArrayList<>();
        for (UserPawnEx element:userPawnExList){
            PawnedMini pawnedMini = new PawnedMini();
            pawnedMini.setId(element.getId().toString());//典当表id
            pawnedMini.setTitle(element.getGoodsName1());
            pawnedMini.setPawnTicketCode(element.getPawnTicketCode()!=null?element.getPawnTicketCode():"");
            pawnedMini.setAuthPrice(element.getAuthPrice()!=null?element.getAuthPrice().toString():"");
            pawnedMini.setImage(OrgUtil.getSingleImage(element.getImages()));
            pawnedMini.setLoanMoney(element.getBeginMoney()!=null?element.getBeginMoney().toString():"");
            Map<String,String> map = userPawnService.getPawnState(element.getId());
            if (map == null){
                throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
            }
            if (map.get("pawnState").equals("0")){
                pawnedMini.setPawnState("0");
                pawnedMini.setDays(map.get("days"));
            }
            if (map.get("pawnState").equals("1")){
                pawnedMini.setPawnState("1");
                pawnedMini.setDays(map.get("days"));
            }
            if (map.get("pawnState").equals("2")){
                pawnedMini.setPawnState("2");
            }
            pawnedMiniList.add(pawnedMini);
        }
        MyPawnerDetail myPawnerDetail = new MyPawnerDetail();
        myPawnerDetail.setList(pawnedMiniList);

        return myPawnerDetail;
    }



    @ApiOperation(value = "当户当品详情",notes="个人中心->我的当户->该当户和他的当品列表->当品详情")
    @RequestMapping(value = "/getPawnerzPawnDetail", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public PawnedDetail getPawnerzPawnDetail(@ApiParam(value = "典当表id",required = true) String pawnId,MobileInfo mobileInfo){
        if(StringUtils.isEmpty(pawnId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        UserPawnEx userPawnEx = userPawnService.getUserPawnExById(pawnId);
        if (userPawnEx == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //获取本次典当成交时约定的逾期滞纳利率
        BigDecimal redeem_overrate = userPawnEx.getOverdueRate();
        if (redeem_overrate == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        PawnedDetail pawnedDetail = new PawnedDetail();
        pawnedDetail.setId(userPawnEx.getId().toString());//典当表id
        pawnedDetail.setCollecterId(userPawnEx.getUserId().toString());//当户id
        pawnedDetail.setPawnTicketCode(userPawnEx.getPawnTicketCode()!=null?userPawnEx.getPawnTicketCode():"");//当号
        pawnedDetail.setTitle(userPawnEx.getGoodsName1()!=null?userPawnEx.getGoodsName1():"");//当品名称
        pawnedDetail.setAuthPrice(userPawnEx.getAuthPrice()!=null?userPawnEx.getAuthPrice().toString():"");
        BigDecimal moneyRate = userPawnEx.getMoneyRate();//赎当利率
        if (moneyRate == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        pawnedDetail.setMoneyRate(moneyRate.toString());
        pawnedDetail.setRate(userPawnEx.getRate()!=null?userPawnEx.getRate().toString():"");//综合利率
        //贷款开始时间
        pawnedDetail.setLoanBeginTime(userPawnEx.getPawnBeginTime()!=null?DateUtil.format(userPawnEx.getPawnBeginTime(),"yyyy-MM-dd"):"");
        //用户地址
        String pawnerAddress="";
        UserAddressExample userAddressExample = new UserAddressExample();
        userAddressExample.createCriteria().andUserIdEqualTo(userPawnEx.getUserId()).andIsDefaultEqualTo(1);
        List<UserAddress> userAddresses = userAddressService.selectByExample(userAddressExample);
        if(userAddresses.size() != 0){
            pawnerAddress = userAddresses.get(0).getAddress()!=null&&userAddresses.get(0).getArea()!=null?userAddresses.get(0).getArea()+userAddresses.get(0).getAddress():"";
        }
        pawnedDetail.setPawnerAddress(pawnerAddress);
        pawnedDetail.setPawnerName(userPawnEx.getUserName()!=null?userPawnEx.getUserName():"");
        pawnedDetail.setPawnerTelNum(userPawnEx.getUserPhone()!=null?userPawnEx.getUserPhone():"");
        //本金
        BigDecimal beginMoney = userPawnEx.getBeginMoney();
        if (beginMoney == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        pawnedDetail.setLoanMoney(beginMoney.toString());
        //首期综合费
        BigDecimal first = userPawnEx.getPawnMoney();
        if (first == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        pawnedDetail.setFirstCost(first.toString());
        //已发放当金 = 本金 - 首期综合费
        if(beginMoney!=null && first!=null){
            pawnedDetail.setUserMoney(beginMoney.subtract(first).toString());
        }else{
            pawnedDetail.setUserMoney("");
        }

        // 赎当利息和已缴综合费(页面)  如果该当品是首次典当并未续当,该字段即首期综合费,否则,该字段应为最新一期续当的综合费
        BigDecimal redeem_interest ;
        BigDecimal overdue;
        BigDecimal payBack ;
        PawnContinueExample example = new PawnContinueExample();
        example.createCriteria().andPawnIdEqualTo(userPawnEx.getId()).andStateEqualTo(4);
        example.setOrderByClause("create_time desc");
        //被机构确认的续当
        List<PawnContinue> comfirmedContinueList = pawnContinueService.selectByExample(example);
        if (comfirmedContinueList.size() == 0){
            //已缴综合费(页面)
            pawnedDetail.setCost(first.toString());
            //赎当利息
            redeem_interest = CostGenerator.getInterest(beginMoney,moneyRate,userPawnEx.getBeginPawnMonth());
            //逾期费
            overdue = CostGenerator.getOverdue(beginMoney,userPawnEx.getBeginPawnEndTime(),redeem_overrate);
            //到期应还款额
            payBack = beginMoney.add(redeem_interest).add(overdue);
            //页面应还款日期,即典当结束日期
            pawnedDetail.setLoanCurrentRepayTime(userPawnEx.getPawnEndTime()!=null?DateUtil.format(userPawnEx.getPawnEndTime(),"yyyy-MM-dd"):"");
            //当品状态和到期/逾期天数
            Map<String,String> map = userPawnService.getPawnState(userPawnEx.getId());
            if (map == null){
                throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
            }
            if (map.get("pawnState").equals("0")){
                pawnedDetail.setPawnState("0");
                pawnedDetail.setDays(map.get("days"));
            }
            if (map.get("pawnState").equals("1")){
                pawnedDetail.setPawnState("1");
                pawnedDetail.setDays(map.get("days"));
            }
            if (map.get("pawnState").equals("2")){
                pawnedDetail.setPawnState("2");
            }
        }else{
                PawnContinue newestOne = comfirmedContinueList.get(0);
                //已缴综合费(页面)
                pawnedDetail.setCost(newestOne.getPawnMoney()!=null?newestOne.getPawnMoney().toString():"");
                //赎当利息
                redeem_interest = CostGenerator.getInterest(beginMoney,moneyRate,newestOne.getPawnMonth());
                //逾期费
                overdue = CostGenerator.getOverdue(beginMoney,newestOne.getPawnEndTime(),redeem_overrate);
                //到期应还款额
                payBack = beginMoney.add(redeem_interest).add(overdue);
                //页面应还款日期,即典当结束日期
                pawnedDetail.setLoanCurrentRepayTime(newestOne.getPawnEndTime()!=null?DateUtil.format(newestOne.getPawnEndTime(),"yyyy-MM-dd"):"");
                //当品状态和到期/逾期天数
                Map<String,String> map = userPawnService.getPawnState(newestOne.getPawnId());
                if (map == null){
                    throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
                }
                if (map.get("pawnState").equals("0")){
                    pawnedDetail.setPawnState("0");
                    pawnedDetail.setDays(map.get("days"));
                }
                if (map.get("pawnState").equals("1")){
                    pawnedDetail.setPawnState("1");
                    pawnedDetail.setDays(map.get("days"));
                }
                if (map.get("pawnState").equals("2")){
                    pawnedDetail.setPawnState("2");
                }
        }
        pawnedDetail.setMoneyCost(redeem_interest.toString());
        pawnedDetail.setPayBack(payBack.toString());
        return pawnedDetail;
    }



    @ApiOperation(value = "资金流水记录",notes="个人中心->资金流水记录")
    @RequestMapping(value = "/getCapitalRunningAccountList", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public List<CapitalRunningAccountDetail> getCapitalRunningAccountList(@ApiParam(value = "查询开始日期 yyyy-MM-dd",required = true) String beginDate,
                                                                          @ApiParam(value = "查询结束日期 yyyy-MM-dd",required = true) String endDate,
                                                                          MobileInfo mobileInfo,PageLimit pageLimit){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (orgId == -1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = null;
        Date end = null;
        try {
            if(StringUtils.isNotEmpty(beginDate)){
                begin =  simpleDateFormat.parse(beginDate+" 00:00:00");
            }
            if(StringUtils.isNotEmpty(endDate)){
                end =  simpleDateFormat.parse(endDate+" 23:59:59");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
        }
        startPage();
        List<OrgBalanceLogEx> list = orgBalanceLogService.getCapitalRunningAccountList(orgId,begin,end);
        List<CapitalRunningAccountDetail> retList = new ArrayList<>();
        for (OrgBalanceLogEx element : list){
            CapitalRunningAccountDetail capitalRunningAccountDetail = new CapitalRunningAccountDetail();
            capitalRunningAccountDetail.setAmount(element.getMoney()!=null?element.getMoney().toString():"");
            capitalRunningAccountDetail.setPawnerName(element.getUserName()!=null?element.getUserName():element.getPawnerName());
            capitalRunningAccountDetail.setPawnerTel(element.getUserPhone()!=null?element.getUserPhone():element.getPawnerTel()!=null?element.getPawnerTel():element.getPawnerAccount());
            capitalRunningAccountDetail.setPawnTicketCode(element.getPawnCode());
            capitalRunningAccountDetail.setTradeCardBank(element.getTradeCardBank());
            capitalRunningAccountDetail.setTradeCardCode(element.getTradeCardCode());
            capitalRunningAccountDetail.setTradeTime(element.getCreateTime()!=null?DateUtil.format(element.getCreateTime()," yyyy-MM-dd:ss"):"");
            capitalRunningAccountDetail.setType(element.getType()!=null?element.getType().toString():"");
            capitalRunningAccountDetail.setTradeTicket(element.getTradeTicket());
            retList.add(capitalRunningAccountDetail);
        }
        return retList;
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
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (orgId == -1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //每个机构仅能绑定一张卡
        OrgBankCardExample orgBankCardExample = new OrgBankCardExample();
        orgBankCardExample.createCriteria().andOrgIdEqualTo(orgId);
        int cardCount = orgBankCardService.countByExample(orgBankCardExample);
        if (cardCount > 0){
            throw new ApiException(MEnumError.ORG_TOO_MANY_BANK_CARD);
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
            throw new ApiException(MEnumError.BANKCARD_ALREADY_BIND);
        }
        //查询传入的该持卡人是否有其他银行卡绑定在其他机构上
       /* orgBankCardExample.clear();
        orgBankCardExample.createCriteria().andBankCardIdCardEqualTo(idCard).andOrgIdNotEqualTo(orgId);
        Integer c = orgBankCardService.countByExample(orgBankCardExample);
        if (c >= 1){
            throw new ApiException(MEnumError.BANKCARD_BIND_ERROR);
        }*/
        //同样的卡，身份证，持卡人，身份证只能绑定一次
        /*orgBankCardExample.clear();
        orgBankCardExample.createCriteria().andBankCardNoEqualTo(bankCardNo).andBankCardIdCardEqualTo(idCard)
                .andBankCardPhoneEqualTo(account).andOrgIdEqualTo(orgId);
        List<OrgBankCard> records = orgBankCardService.selectByExample(orgBankCardExample);
        if (records.size() >= 1){
            throw new ApiException(MEnumError.BANKCARD_ALREADY_INSERT);
        }*/
        OrgBankCard orgBankCard = new OrgBankCard();
        orgBankCard.setOrgId(orgId);
//        orgBankCard.setBankLogo(bankCardResult.getInformation().getBankimage());
        orgBankCard.setBankCardType(1);
        orgBankCard.setBankCardNo(bankCardNo);
//        orgBankCard.setBankCardUserName(bankCardResult.getRealName());
        orgBankCard.setBankCardUserName(userName);
//        orgBankCard.setBankCardName(bankCardResult.getInformation().getBankname());
        orgBankCard.setBankCardName(bankName);
        orgBankCard.setBankCardPhone(account);
//        orgBankCard.setBankCardIdCard(idCard);
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
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
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


    @ApiOperation(value = "绝当拍卖列表",notes="个人中心->绝当拍卖")
    @RequestMapping(value = "/getDeadPawnAuctionList", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<DeadPawnAuctionMini> getDeadPawnAuctionList(@ApiParam(value = "查询关键词，该参数可不传",required = false)String keyword,
                                                            MobileInfo mobileInfo, PageLimit pageLimit){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        GoodsExample goodsExample = new GoodsExample();
        if (StringUtils.isEmpty(keyword)){
            goodsExample.createCriteria().andSourceEqualTo(2).andOrgIdEqualTo(orgId)
                    .andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2);
        }else {
            goodsExample.createCriteria().andSourceEqualTo(2).andOrgIdEqualTo(orgId)
                    .andNameLike("%"+keyword+"%").andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2);
        }
        startPage();
        List<Goods> goodsList = goodsService.selectByExample(goodsExample);
        List<DeadPawnAuctionMini> retList = new ArrayList<>();
        for (Goods element:goodsList) {
            DeadPawnAuctionMini deadPawnAuctionMini = new DeadPawnAuctionMini();
            deadPawnAuctionMini.setGoodsTableId(element.getId().toString());//p_goods表的id
            deadPawnAuctionMini.setUserGoodsId(element.getGoodsId()!=null?element.getGoodsId().toString():"");//p_user_goods表id
            UserGoods record = userGoodsService.selectByPrimaryKey(element.getGoodsId());
            if (record == null){//后台上传物品
             //   throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
                deadPawnAuctionMini.setAuthPrice("无");
                deadPawnAuctionMini.setStartingPrice(element.getCost()!=null?element.getCost().toString():"");
                deadPawnAuctionMini.setCurrentPrice(element.getMaxAuction()!=null?element.getMaxAuction().toString():"");
                deadPawnAuctionMini.setGoodsName(element.getName());
                deadPawnAuctionMini.setPicture(element.getImg());
            }else{//绝当品去拍卖
                deadPawnAuctionMini.setAuthPrice(record.getAuthPrice()!=null?record.getAuthPrice().toString():"");
                deadPawnAuctionMini.setStartingPrice(element.getCost()!=null?element.getCost().toString():"");
                deadPawnAuctionMini.setCurrentPrice(element.getMaxAuction()!=null?element.getMaxAuction().toString():"");
                deadPawnAuctionMini.setGoodsName(element.getName());
                deadPawnAuctionMini.setPicture(element.getImg());
            }
            Date today = new Date();
            Date createTime = element.getCreateTime();
            long auctionTime = PaidangConst.JD_GOODS_TIME * 1000;
            Long countDownSeconds = ((createTime.getTime()  + auctionTime) - today.getTime()) / 1000;
            //按钮和状态文字
            if (element.getState() == 1 && countDownSeconds > 0){ //竞拍中
                deadPawnAuctionMini.setCountdown(countDownSeconds.toString());
                deadPawnAuctionMini.setAuctionState("1");//竞拍中
                deadPawnAuctionMini.setButtons("0");//不显示按钮
            }else{
                //非竞拍中，即已结束，竞拍倒计时结束后无人竞拍
                GoodsAuctionExample goodsAuctionExample = new GoodsAuctionExample();
                goodsAuctionExample.createCriteria().andGoodsIdEqualTo(element.getId());
                List<GoodsAuction> results = goodsAuctionService.selectByExample(goodsAuctionExample);
                if (results.size() == 0){//无人竞拍
                    deadPawnAuctionMini.setAuctionState("3");//状态文字：无人竞拍
                    deadPawnAuctionMini.setButtons("0");//不显示按钮
                }else{
                    //有人竞拍，竞拍结束后定时任务自动取价高者为中标者，并插入订单表形成一条记录，现在把这条记录查询出来
                    OrderExample orderExample = new OrderExample();
                    orderExample.createCriteria().andGoodsIdEqualTo(element.getId()).andUserIdEqualTo(element.getUserId());
                    List<Order> ordersList = orderService.selectByExample(orderExample);
                    if (ordersList.size() != 1){
                        throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
                    }
                    Order order = ordersList.get(0);
                    Integer state = order.getState();
                    switch (state){
                        case 2://当品已付款，需邮寄给中标者，
                            //此goods_id是p_user_goods用户藏品表的主键，若它为空，表示此商品是机构后台添加上去的，物品在机构本身，直接从机构处邮寄出去；否则此商品是APP端绝当拍卖的，物品在平台处，需通知平台代为邮寄
                            if (element.getGoodsId()==null){
                                deadPawnAuctionMini.setAuctionState("0");//不显示状态文字
                                deadPawnAuctionMini.setButtons("2");//按钮：去邮寄
                            }else {
                                deadPawnAuctionMini.setAuctionState("0");//不显示状态文字
                                deadPawnAuctionMini.setButtons("1");//按钮：通知平台邮寄
                            }
                            break;
                        case 3: //已发货
                            deadPawnAuctionMini.setAuctionState("0");//不显示状态文字
                            deadPawnAuctionMini.setButtons("0");//不显示按钮
                            break;
                        case 4://已收货
                            if (element.getPlatformState() == 0){
                                deadPawnAuctionMini.setButtons("3");//缴纳平台利息
                                deadPawnAuctionMini.setAuctionState("2");//状态文字：已完成
                            }else
                            deadPawnAuctionMini.setButtons("0");//不显示任何按钮
                            deadPawnAuctionMini.setAuctionState("2");//状态文字：已完成
                            break;
                    }
                }
            }
            retList.add(deadPawnAuctionMini);
        }
        return retList;
    }



    @ApiOperation(value = "绝当拍卖详情",notes="个人中心->绝当拍卖->详情")
    @RequestMapping(value = "/getDeadPawnAuctionDetail", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public DeadPawnAuctionDetail getDeadPawnAuctionDetail(@ApiParam(value = "商品表id",required = true)String goodsTableId,
                                                          MobileInfo mobileInfo){
        if (StringUtils.isEmpty(goodsTableId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Goods record = goodsService.selectByPrimaryKey(Integer.valueOf(goodsTableId));
        if (record == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        DeadPawnAuctionDetail deadPawnAuctionDetail = new DeadPawnAuctionDetail();
        if (record.getState() == 1){//表示该商品还在竞拍中,需添加 当前价 倒计时
            Date today = new Date();
            Date createTime = record.getCreateTime();
            long auctionTime = PaidangConst.JD_GOODS_TIME * 1000;
            Long cnt = ((createTime.getTime()  + auctionTime) - today.getTime()) / 1000;
            deadPawnAuctionDetail.setCountdown(cnt.toString());//倒计时秒数
            deadPawnAuctionDetail.setCurrentPrice(record.getMaxAuction()!=null?record.getMaxAuction().toString():"");//当前最新出价
        }
        //竞拍记录
        GoodsAuctionExample goodsAuctionExample = new GoodsAuctionExample();
        goodsAuctionExample.createCriteria().andGoodsIdEqualTo(Integer.valueOf(goodsTableId));
        goodsAuctionExample.setOrderByClause("price asc,create_time desc");
        List<GoodsAuction> list = goodsAuctionService.selectByExample(goodsAuctionExample);
        List<DeadPawnAuctionRecord> auctionRecords = new ArrayList<>();
        for (GoodsAuction element:list) {
            DeadPawnAuctionRecord deadPawnAuctionRecord = new DeadPawnAuctionRecord();
            deadPawnAuctionRecord.setBidPrice(element.getPrice()!=null?element.getPrice().toEngineeringString():"");
            deadPawnAuctionRecord.setBidTime(element.getCreateTime()!=null?DateUtil.dateToStr(element.getCreateTime(),"yyyy-MM-dd") :"");
            User userRecord = userService.selectByPrimaryKey(element.getUserId());
            deadPawnAuctionRecord.setBidderName(userRecord.getName());
            auctionRecords.add(deadPawnAuctionRecord);
        }
        deadPawnAuctionDetail.setRecords(auctionRecords);
        deadPawnAuctionDetail.setGoodsName(record.getName());
        deadPawnAuctionDetail.setImages(record.getImgs());
        deadPawnAuctionDetail.setGoodsDescription(record.getInfo());
        deadPawnAuctionDetail.setStartingPrice(record.getCost()!=null?record.getCost().toString():"");
        deadPawnAuctionDetail.setAuthPrice(record.getCost()!=null?record.getCost().toString():"");
        return deadPawnAuctionDetail;
    }


    @ApiOperation(value = "绝当拍卖去邮寄页面",notes="个人中心->绝当拍卖->去邮寄按钮展示的页面数据")
    @RequestMapping(value = "/fillExpressInfo", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public ToSendPageDetail fillExpressInfo(@ApiParam(value = "商品表id",required = true)String goodsTableId, MobileInfo mobileInfo){
        if (StringUtils.isEmpty(goodsTableId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Goods record = goodsService.selectByPrimaryKey(Integer.valueOf(goodsTableId));
        if (record == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer winnerId = record.getUserId();
        if (winnerId == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        User winner = userService.selectByPrimaryKey(winnerId);
        if (winner == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        String userAddress = null;
        UserAddressExample addressExample = new UserAddressExample();
        addressExample.setOrderByClause("create_time desc");
        addressExample.createCriteria().andUserIdEqualTo(winnerId);
        List<UserAddress> addresses = userAddressService.selectByExample(addressExample);
        for (UserAddress element:addresses) {
            if (element.getIsDefault() == 1){//找到绝当拍卖中标用户地址中默认的
                userAddress = element.getArea()+element.getAddress();
            }
        }
        if (StringUtils.isEmpty(userAddress) && addresses.size() != 0){//如果没有找到默认，取该用户地址第一条
            userAddress = addresses.get(0).getArea()+addresses.get(0).getAddress();
        }
        ToSendPageDetail expressInfo = new ToSendPageDetail();
        expressInfo.setName(winner.getName());
        expressInfo.setGoodsName(record.getName());
        expressInfo.setAddress(userAddress!=null?userAddress:"");
        expressInfo.setPicture(record.getImg());
        expressInfo.setTelphone(winner.getAccount());
        expressInfo.setExpressCompany(PaidangConst.EXPRESSS_COMPANY);
        return expressInfo;
    }



    @ApiOperation(value = "绝当拍卖去邮寄页面里的确认按钮",notes="个人中心->绝当拍卖->去邮寄按钮页面->确定")
    @RequestMapping(value = "/confirmExpressinfo", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer confirmExpressinfo(@ApiParam(value = "商品表id",required = true)String goodsTableId,
                                  @ApiParam(value = "收件人姓名",required = true)String recipients,
                                  @ApiParam(value = "收件人地址",required = true)String address,
                                  @ApiParam(value = "收件人电话",required = true)String telphone,
                                  @ApiParam(value = "打包视频id",required = true)String vedioId,
                                  @ApiParam(value = "快递公司",required = true)String expressName,
                                  @ApiParam(value = "快递单号",required = true)String expressCode,
                                  MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(orgId);
        if (StringUtils.isEmpty(goodsTableId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andGoodsIdEqualTo(Integer.valueOf(goodsTableId));
        List<Order> orders = orderService.selectByExample(orderExample);
        if (orders.size() != 1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //更新order相关字段
        Order order = orders.get(0);
        order.setState(3);//已发货
        order.setShipUser(recipients);
        order.setShipAddress(address);
        order.setShipPhone(telphone);
        order.setShipCode(expressCode);
        order.setShipFirm(expressName);
        order.setVideo(vedioId);
        orderService.updateByPrimaryKey(order);
        //去邮寄，插入记录到p_express快递表
        Express express = new Express();
        express.setSource(2);//1用户2机构
        express.setSourceId(orgId);//用户id/机构id
        express.setFid(order.getId());//相关id(藏品或订单id）
        express.setType(5);//1寄给平台2取回3商城4平台寄给当户5机构寄给当户6机构取回（绝当品）
        express.setExpressName(expressName);
        express.setExpressCode(expressCode);
        express.setReceiveName(recipients);
        express.setReceivePhone(telphone);
        express.setReceviceAddress(address);
        //寄件人信息是机构的名称与电话
        express.setPostName(pawnOrg.getName());
        express.setPostPhone(pawnOrg.getPhone());
        express.setCreateTime(new Date());
        expressService.insert(express);
        return 1;
    }


    @ApiOperation(value = "通知平台邮寄",notes="个人中心->绝当拍卖->通知平台邮寄 按钮")
    @RequestMapping(value = "/notifyPlatformToSend", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer notifyPlatformToSend(@ApiParam(value = "商品表id",required = true)String goodsTableId,
                                    MobileInfo mobileInfo){
        if (StringUtils.isEmpty(goodsTableId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Goods record = goodsService.selectByPrimaryKey(Integer.valueOf(goodsTableId));
        if (record == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer winnerId = record.getUserId();
        if (winnerId == null){//已被用户拍下的绝当品,中标人字段必须有中标用户id
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        User winner = userService.selectByPrimaryKey(winnerId);
        String winnerName = winner.getName();
        String goodsName = record.getName();
        AdminExample example = new AdminExample();
        example.createCriteria().andRoleCodeEqualTo("admin");
        List<Admin> admins = adminService.selectByExample(example);
        StringBuffer url = new StringBuffer();
        for (Admin element:admins) {//推送给所有角色code是admin的后台账户
            url.setLength(0);
            url.append(ConstantsCode.SERVER_PUSH_URL+"?account="+element.getId()+"&sys=admin&msg={msg:'绝当品"+goodsName+"已被用户"+winnerName+"拍得,请尽快给该用户邮寄此物品!'}");
            String result = HttpUtil.get(url.toString());
            if (!StringUtils.equals("true",result)){
//              return msg(-1,"在推送消息给账号id是"+element.getId()+"的管理员用户时出错了，请联系开发人员");
                throw new ApiException(MEnumError.NOTIFY_ADMIN_ERROR);
            }
        }
       return 1;
    }


    @ApiOperation(value = "物流 平台取回列表",notes="物流信息->平台取回页面")
    @RequestMapping(value = "/getFetchBackList", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public List<ExpressInfo> getFetchBackList(MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        UserGoodsExample userGoodsExample = new UserGoodsExample();
        userGoodsExample.setOrderByClause("create_time desc");
        userGoodsExample.createCriteria().andBelongTypeEqualTo(2).andBelongIdEqualTo(orgId).andPlatOrgStateGreaterThan(0);
        List<UserGoods> goodsList = userGoodsService.selectByExample(userGoodsExample);
        List<ExpressInfo> retList = new ArrayList<>();
        for (UserGoods element:goodsList) {
            ExpressInfo expressInfo = new ExpressInfo();
            expressInfo.setGoodsId(element.getId().toString());
            expressInfo.setGoodsName(element.getName()!=null?element.getName().toLowerCase():"");
            expressInfo.setGoodsImage(element.getImages()!=null?element.getImages():"");

            UserPawnExample userPawnExample = new UserPawnExample();
            userPawnExample.createCriteria().andGoodsIdEqualTo(element.getId()).andStateEqualTo(4);
            List<UserPawn> userPawnList = userPawnService.selectByExample(userPawnExample);
            if (userPawnList.size() == 1){
                expressInfo.setPawnTicketCode(userPawnList.get(0).getPawnTicketCode());
            }else
                throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);//同一绝当记录的goodsId发现不止一条

            ExpressExample expressExample = new ExpressExample();
            // expressExample.createCriteria().andSourceEqualTo(2).andSourceEqualTo(orgId).andFidEqualTo(element.getId());
            expressExample.createCriteria().andFidEqualTo(element.getId()).andTypeEqualTo(6);
            List<Express> expresses = expressService.selectByExample(expressExample);
            if (expresses.size() == 1){
                Express record = expresses.get(0);
                expressInfo.setExpressCode(record.getExpressCode());
                expressInfo.setExpressId(record.getId().toString());
                expressInfo.setExpressState(record.getExpressState().toString());
                //20181217修改只有有快递单号才能取回
                expressInfo.setIsSign(element.getPlatOrgVerify() == null ? "1" : element.getPlatOrgVerify() == 0 ? "1" : "0");
            }else if (expresses.size() == 0){
                //平台的后台未即时处理这条取回申请的，无法获得快递单号，以及快递状态
                expressInfo.setExpressCode("");
                expressInfo.setExpressId("");
                expressInfo.setExpressState("");
            }else
                throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
            //expressInfo.setIsSign(element.getPlatOrgVerify() == null ? "1" : element.getPlatOrgVerify() == 0 ? "1" : "0");
            retList.add(expressInfo);
        }
        return  retList;
    }


    @ApiOperation(value = "签收",notes="物流信息->取回列表->签收按钮")
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer sign(@ApiParam(value = "当品id",required = true)String goodId,MobileInfo mobileInfo){
        if(StringUtils.isEmpty(goodId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer id;
        try{
            id = Integer.valueOf(goodId);
        }catch (NumberFormatException e){
            throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
        }

        UserGoods record = userGoodsService.selectByPrimaryKey(id);
        if (record == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        record.setPlatOrgVerify(1);//机构取回时 0未确认 1确认宝贝完好
        record.setPlatOrgState(3);//20181217修改状态为确认收货
        userGoodsService.updateByPrimaryKey(record);
        return 1;
    }


    @ApiOperation(value = "物流 寄给当户列表",notes="物流信息->寄给当户页面")
    @RequestMapping(value = "/getSendToPawnerList", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public List<ExpressInfo> getSendToPawnerList(MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andGoodsSourceEqualTo(2).andOrgIdEqualTo(orgId).andStateGreaterThan(3);
        List<Order> orderList = orderService.selectByExample(orderExample);
        List<ExpressInfo> retList = new ArrayList<>();
        for (Order element:orderList) {
            ExpressInfo expressInfo = new ExpressInfo();
            expressInfo.setGoodsId(element.getGoodsId().toString());
            expressInfo.setGoodsImage(element.getGoodsImg());
            expressInfo.setGoodsName(element.getGoodsName());
            Goods goodsRecord = goodsService.selectByPrimaryKey(element.getGoodsId());
            Integer userGoodsId = goodsRecord.getGoodsId();
            if (userGoodsId != null){//表示此物品是绝当品去拍卖的
                UserPawnExample userPawnExample = new UserPawnExample();
                userPawnExample.createCriteria().andGoodsIdEqualTo(userGoodsId);
                List<UserPawn> list = userPawnService.selectByExample(userPawnExample);
                if (list.size() == 1){
                    UserPawn userPawnRecord = list.get(0);//找到该绝当品的典当表记录
                    expressInfo.setPawnTicketCode(userPawnRecord.getPawnTicketCode());
                }else{
                    throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);//同一goodsId发现不止一条典当记录
                }
            }else{// 由机构后台添加的物品，没当号
                expressInfo.setPawnTicketCode("");
            }
            //物流信息->寄给当户 1.机构是寄货方，这种情况是机构在绝当拍卖列表上点击去邮寄按钮操作，过程中必填单号，order表物流字段会即时插入
            //2.机构通知平台，平台来邮寄该绝当品给中标当户，由平台来更新orde表物流字段，order表物流相关字段可能有单号，也可能没有。
            String expressCode = element.getShipCode();
            if (StringUtils.isEmpty(expressCode)){
                expressInfo.setExpressCode("");
                expressInfo.setExpressId("");
                expressInfo.setExpressState("");
            }else {//有快递单号，不管是谁更新的，去快递表查询状态
                ExpressExample expressExample = new ExpressExample();
                ExpressExample.Criteria criteria=expressExample.createCriteria();
                criteria.andExpressCodeEqualTo(expressCode);
                List<Express> expresses = expressService.selectByExample(expressExample);
                if (expresses.size() == 1){
                    Express record = expresses.get(0);
                    expressInfo.setExpressCode(record.getExpressCode());
                    expressInfo.setExpressId(record.getId().toString());
                    expressInfo.setExpressState(record.getExpressState().toString());
                }else{
                    //同一快递单号，应该 只能更新记录，基于此，在p_express表里应该是唯一的
//                    throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
                    //如果goodsId不为空
                    for (int i = 0; i < expresses.size(); i++) {
                        Express record = expresses.get(i);
                        if(userGoodsId != null&&record.getFid().equals(userGoodsId)){
                            expressInfo.setExpressCode(record.getExpressCode());
                            expressInfo.setExpressId(record.getId().toString());
                            expressInfo.setExpressState(record.getExpressState().toString());
                            break;
                        }
                        if(record.getFid().equals(element.getId())){
                            expressInfo.setExpressCode(record.getExpressCode());
                            expressInfo.setExpressId(record.getId().toString());
                            expressInfo.setExpressState(record.getExpressState().toString());
                            break;
                        }
                    }
                }
            }
            retList.add(expressInfo);
        }
        return retList;
    }




    @ApiOperation(value = "设置到期提醒",notes="我的->到期提醒")
    @RequestMapping(value = "/setExpirationReminding", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer setExpirationReminding(@ApiParam(value = "提醒频率 1-每天提醒 2-每周提醒 3-到期前3天提醒",required = true)String frequency,MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if(orgId == -1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        PawnOrg record = pawnOrgService.selectByPrimaryKey(orgId);
        if (record == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        if (!StringUtils.isNumeric(frequency)){
            throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
        }
        Integer freq;
        try{
            freq = Integer.valueOf(frequency);
        }catch (NumberFormatException e){
            e.printStackTrace();
            throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
        }
        switch (freq){
            case 1: break;
            case 2: break;
            case 3: break;
            default:throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
        }
        record.setExpirationRemindType(freq);
        int r = pawnOrgService.updateByPrimaryKey(record);
        if (r != 1){
            throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
        }
        return 1;
    }


    @ApiOperation(value = "获取到期提醒",notes="我的->到期提醒 返回值：0-没有设置 1-每天提醒 2-每周提醒 3-到期前3天提醒")
    @RequestMapping(value = "/getExpirationReminding", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer getExpirationReminding(MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if(orgId == -1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        PawnOrg record = pawnOrgService.selectByPrimaryKey(orgId);
        if (record == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer r = record.getExpirationRemindType();
        if (r == null){
            return 0;
        }
        return r;
    }


    @ApiOperation(value = "合同记录列表",notes="我的->合同记录")
    @RequestMapping(value = "/getContractPawnList", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public List<ContractPawn> getContractPawnList(MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        List<UserPawnEx> list = userPawnService.getContractPawnList(orgId.toString());
        List<ContractPawn> retList = new ArrayList<>();
        for (UserPawnEx userPawnEx : list){
            ContractPawn contractPawn = new ContractPawn();
            contractPawn.setPawnId(userPawnEx.getId().toString());
            contractPawn.setGoodsId(userPawnEx.getGoodsId()!=null?userPawnEx.getGoodsId().toString():"");
            contractPawn.setGoodsName(userPawnEx.getGoodsName1()!=null?userPawnEx.getGoodsName1():"");
            contractPawn.setImages(OrgUtil.getSingleImage(userPawnEx.getImages()));
            contractPawn.setLoanMoney(userPawnEx.getBeginMoney()!=null?userPawnEx.getBeginMoney().toString():"");
            contractPawn.setPawnBeginTime(userPawnEx.getPawnBeginTime()!=null?DateUtil.dateToStr(userPawnEx.getPawnBeginTime(),"yyyy-MM-dd"):"");
            contractPawn.setPawnSignTime(userPawnEx.getPawnBeginTime()!=null?DateUtil.dateToStr(userPawnEx.getPawnBeginTime(),"yyyy-MM-dd"):"");
            contractPawn.setPawnEndTime(userPawnEx.getPawnEndTime()!=null?DateUtil.dateToStr(userPawnEx.getPawnEndTime(),"yyyy-MM-dd"):"");
            retList.add(contractPawn);
        }
        return retList;
    }


    @ApiOperation(value = "合同记录第二个页面",notes="我的->合同记录->第二个页面")
    @RequestMapping(value = "/getContractPawnType", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public List<ContractType> getContractPawnType(@ApiParam(value = "典当id",required = true)String pawnId,MobileInfo mobileInfo){
        if (StringUtils.isEmpty(pawnId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        UserPawn userPawn = userPawnService.selectByPrimaryKey(Integer.valueOf(pawnId));
        if (userPawn == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        List<ContractType> retList = new ArrayList<>();
        ContractType contractType = new ContractType();
        contractType.setPawnId(userPawn.getId().toString());
        contractType.setContractType("1");
        contractType.setPawnBeginTime(userPawn.getPawnBeginTime()!=null?DateUtil.dateToStr(userPawn.getPawnBeginTime()):"");
        retList.add(contractType);

        PawnContinueExample pawnContinueExample = new PawnContinueExample();
        pawnContinueExample.createCriteria().andPawnIdEqualTo(userPawn.getId());
        pawnContinueExample.setOrderByClause("create_time desc");
        List<PawnContinue> pawnContinues = pawnContinueService.selectByExample(pawnContinueExample);
        if (pawnContinues == null || pawnContinues.size()==0){
            return retList;
        }else{
            for (PawnContinue pawnContinue:pawnContinues) {
                ContractType c = new ContractType();
                c.setPawnId(pawnContinue.getPawnId().toString());
                c.setRepawnId(pawnContinue.getId().toString());
                c.setContractType("2");
                c.setPawnBeginTime(pawnContinue.getCreateTime()!=null?DateUtil.dateToStr(pawnContinue.getCreateTime()):"");//续当签订时间
                retList.add(c);
            }
            return retList;
        }
    }


    @ApiOperation(value = "合同详情",notes="我的->合同记录->第二个页面->合同详情")
    @RequestMapping(value = "/getContractDetail", method = RequestMethod.POST)
    @ApiMethod(isLogin = false)
    public ContractDetail getContractDetail(@ApiParam(value = "典当id",required = true)String pawnId,
                                            @ApiParam(value = "续当id",required = false)String repawnId,
                                            MobileInfo mobileInfo){
        if (StringUtils.isEmpty(pawnId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer pid;
        Integer rpId = null;
        try{
            pid = Integer.valueOf(pawnId);
            if (StringUtils.isNotEmpty(repawnId)){
                rpId = Integer.valueOf(repawnId);
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
        }

        ContractDetail contractDetail = new ContractDetail();
        UserPawn userPawn = userPawnService.selectByPrimaryKey(pid);
        if (userPawn == null || userPawn.getOrgId() == null || userPawn.getUserId()==null || userPawn.getGoodsId()==null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(userPawn.getOrgId());
        if (pawnOrg == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //获取本次典当成交时约定的逾期滞纳利率
        BigDecimal redeem_overrate = userPawn.getOverdueRate();
        if (redeem_overrate == null){
            throw  new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        User pawner = userService.selectByPrimaryKey(userPawn.getUserId());
        if (pawner == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        UserGoods userGoods = userGoodsService.selectByPrimaryKey(userPawn.getGoodsId());
        if (userGoods == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        contractDetail.setPawnTicketCode(userPawn.getPawnTicketCode());
        contractDetail.setPawnerName(pawner.getName());
        contractDetail.setAuthPrice(userGoods.getAuthPrice()!=null?userGoods.getAuthPrice().toString():"");
        contractDetail.setGoodsName(userPawn.getGoodsName());
        contractDetail.setOrgName(pawnOrg.getName());
        contractDetail.setOrgAddress(pawnOrg.getAdress());
        contractDetail.setOrgLegalPerson(pawnOrg.getLegalPerson());
        contractDetail.setOrgRegisteredCapital(pawnOrg.getRegisteredCapital());
        contractDetail.setLoanMoney(userPawn.getBeginMoney()!=null?userPawn.getBeginMoney().toString():"");
        contractDetail.setRate(userPawn.getRate()!=null?userPawn.getRate().toString():"");
        contractDetail.setMoneyRate(userPawn.getMoneyRate()!=null?userPawn.getMoneyRate().toString():"");
//      contractDetail.setDemurrageRate(PaidangConst.REDEEM_OVERRATE.toString());
        contractDetail.setDemurrageRate(redeem_overrate.toString());
        contractDetail.setBankCardCode(userPawn.getPayeeBankCardCode());
        //法律条文链接
        CodeExample example = new CodeExample();
        example.createCriteria().andCodeLike("%@law");
        List<Code> list = codeService.selectByExample(example);
        if (list!=null && list.size()!=0){
            for (Code code:list){
                //典当管理法
                if (StringUtils.contains(code.getCode(),"ddgl")){
                    contractDetail.setDiandangguanlifa(code.getValue());
                }
                //合同法
                if (StringUtils.contains(code.getCode(),"htf")){
                    contractDetail.setHetongfa(code.getValue());
                }
                //民法总则
                if (StringUtils.contains(code.getCode(),"mfzz")){
                    contractDetail.setMinshifa(code.getValue());
                }
            }
        }
        if (rpId == null){
           contractDetail.setPawnBeginTime(userPawn.getPawnBeginTime()!=null?DateUtil.dateToStr(userPawn.getPawnBeginTime()):"");
           contractDetail.setPawnEndTime(userPawn.getBeginPawnEndTime()!=null?DateUtil.dateToStr(userPawn.getBeginPawnEndTime()):"");
           contractDetail.setState(userPawn.getState()==2?"2":"1");
           contractDetail.setPawnSignTime(userPawn.getPawnBeginTime()!=null?DateUtil.dateToStr(userPawn.getPawnBeginTime()):"");
           return contractDetail;
        }else {
            PawnContinue pawnContinue = pawnContinueService.selectByPrimaryKey(rpId);
            contractDetail.setPawnBeginTime(pawnContinue.getPawnLastEndTime()!=null?DateUtil.dateToStr(pawnContinue.getPawnLastEndTime()):"");
            contractDetail.setPawnEndTime(pawnContinue.getPawnEndTime()!=null?DateUtil.dateToStr(pawnContinue.getPawnEndTime()):"");
            contractDetail.setState(pawnContinue.getState()==4?"2":"1");
            contractDetail.setRepawnSignTime(pawnContinue.getCreateTime()!=null?DateUtil.dateToStr(pawnContinue.getCreateTime()):"");
            return contractDetail;
        }
    }


    /**
     * 查看合同
     */
    @ApiOperation(value = "查看合同*", notes = "登陆")
    @RequestMapping(value = "/showContract", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Result showContract(MobileInfo mobileInfo, @ApiParam(value="id",required = false)Integer pawnTicketId,
                               @ApiParam(value="典当id",required = false)Integer pawnId,
                               @ApiParam(value="续当id",required = false)Integer repawnId) throws Exception {
        if (pawnTicketId ==null && pawnId==null && repawnId==null){
            throw new ApiException(400,"缺少必要参数");
        }
        PawnTicket pawnTicket = null;
        if (pawnId!=null){
            UserPawn userPawn = userPawnService.selectByPrimaryKey(pawnId);
            pawnTicket = pawnTicketService.getByProjectCode(userPawn.getProjectCode());
        }else if (repawnId!=null){
            PawnContinue pawnContinue = pawnContinueService.selectByPrimaryKey(repawnId);
            pawnTicket = pawnTicketService.getByProjectCode(pawnContinue.getProjectCode());
        }else if (pawnTicketId!=null){
            pawnTicket = pawnTicketService.selectByPrimaryKey(pawnTicketId);
        }

        if (org.springframework.util.StringUtils.isEmpty(pawnTicket.getContractUrl())){
            if (org.springframework.util.StringUtils.isEmpty(pawnTicket.getContractId())){
                throw new ApiException(400,"合同尚未签署完成，无法查看");
            }
            String contractUrl = AnXinSignService.getContractUrl(pawnTicket.getContractId());
            String s = fileService.downLoadFromUrl(contractUrl,pawnTicket.getContractId());
            PawnTicket temp = new PawnTicket();
            temp.setContractUrl(s);
            temp.setId(pawnTicketId);
            pawnTicketService.updateByPrimaryKeySelective(temp);
            return new Result(s);
        }else {
            return new Result(pawnTicket.getContractUrl());
        }
//        String contractId= pawnTicket.getContractId();
//        String pageUrl = qysService.getPageUrl(Long.valueOf(contractId));
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("pageUrl",pageUrl);
//        logger.info("==================合同查看地址:{}",pageUrl);
    }


//    @ApiOperation(value = "物流详情",notes="我的->物流->物流详情")
//    @RequestMapping(value = "/getExpressDetail", method = RequestMethod.POST)
//    @ApiMethod(isLogin = true)
//    public Result getExpressDetail(@ApiParam(value = "物流id",required = true)String expressId, MobileInfo mobileInfo){
//        if (StringUtils.isEmpty(expressId)){
//            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
//        }
//        Integer id;
//        try{
//            id = Integer.valueOf(expressId);
//        }catch (NumberFormatException e){
//            e.printStackTrace();
//            throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
//        }
//        Express express = expressService.selectByPrimaryKey(id);
//        if (express == null)
//            throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
//        if (StringUtils.isNotEmpty(express.getExpressData())){
//            return new Result(express.getExpressData());
//        }else
//            return new Result();
//    }




    /**
     * 票据列表
     */
    @ApiOperation(value = "票据列表*", notes = "登陆")
    @RequestMapping(value = "/pawnTicketList", method = RequestMethod.POST)
    @ApiMethod(isLogin = true, isPage = true)
    public List<PawnTicketEx> pawnTicketList(MobileInfo mobileInfo, PageLimit pageLimit, String status) {
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());

        PawnTicketExample pawnTicketExample = new PawnTicketExample();
        //查询用户真实姓名和手机号相同的票据
        List<String> statusList=new ArrayList<>();
        if(status.equals("1")){
            statusList.add("0");
            statusList.add("1");
        }
        if(status.equals("2")){
            statusList.add("2");
            statusList.add("3");
            statusList.add("4");
        }
        startPage();
        PawnTicketQo qo = new PawnTicketQo();
        qo.setStatusList(statusList);
        qo.setOrgId(orgId);
        List<PawnTicketEx> pawnTickets = pawnTicketService.findList(qo);
        return pawnTickets;
    }

    @ApiOperation(value = "物流详情",notes="物流信息")
    @RequestMapping(value = "/getExpressDetail", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Express getExpressDetail(@ApiParam(value = "物流id ，id expressCode fid三者必传一个",required = false)Integer id,
                                    @ApiParam(value = "快递单号",required = false)String expressCode,
                                    @ApiParam(value = "相关id(藏品或订单id） ",required = false)Integer fid,
                                    @ApiParam(value = "1寄给平台2取回3商城4平台寄给当户5机构寄给当户6机构取回（绝当品）7 退货快递单号",required = true)Integer type,
                                    MobileInfo mobileInfo){

        if (id==null && StringUtils.isBlank(expressCode) && fid==null){
            throw new ApiException(400,"缺少必要参数");
        }

        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(type);
        if (id!=null){
            criteria.andIdEqualTo(id);
        }
        if (StringUtil.isNotBlank(expressCode)){
            criteria.andExpressCodeEqualTo(expressCode);
        }
        if (fid!=null){
            criteria.andFidEqualTo(fid);
        }
        List<Express> expresses = expressService.selectByExampleWithBLOBs(example);
        if (CollectionUtils.isNotEmpty(expresses)){
            return expresses.get(0);
        }else{
            throw new ApiException(400,"物流信息不存在");
        }
    }



}
