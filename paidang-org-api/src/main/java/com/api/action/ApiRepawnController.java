package com.api.action;

import com.api.MEnumError;
import com.api.MErrorEnum;
import com.api.util.OrgUtil;
import com.api.util.PageLimit;
import com.api.view.orgRedeem.PlatformInterestInfo;
import com.api.view.orgRepawn.RepawnDetail;
import com.api.view.orgRepawn.RepawnMini;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.DateUtil;
import com.item.dao.model.User;
import com.item.service.UserService;
import com.paidang.dao.model.*;
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

import static com.paidang.utils.CostGenerator.getInterest;
import static com.paidang.utils.CostGenerator.getOverdue;

/**
 * Created by Lee on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/api/repawn", produces = { "application/json;charset=UTF-8" })
@Api(tags = "续当")
public class ApiRepawnController extends ApiBaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private UserPawnService userPawnService;
    @Autowired
    private PawnContinueService pawnContinueService;
    @Autowired
    private UserGoodsService userGoodsService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    OrgBankCardService orgBankCardService;
    @Autowired
    private PawnOrgService pawnOrgService;
    @Autowired
    private OrgBalanceLogService orgBalanceLogService;
    @Autowired
    private UserBalanceLogService userBalanceLogService;
    @Autowired
    private PawnLogService pawnLogService;


    @ApiOperation(value = "机构端续当列表",notes="机构端续当列表")
    @RequestMapping(value = "/getRepawnList", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public List<RepawnMini> getRepawnList(MobileInfo mobileInfo,PageLimit pageLimit){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (orgId == -1){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        startPage();
        //获得本机构所有正处于典当中的记录
        UserPawnExample userPawnExample = new UserPawnExample();
        userPawnExample.createCriteria().andOrgIdEqualTo(orgId).andStateEqualTo(2);
        userPawnExample.setOrderByClause("create_time desc");
        List<UserPawn> userPawnList = userPawnService.selectByExample(userPawnExample);

        List<RepawnMini> retList = new ArrayList<>();
        for (UserPawn userPawn : userPawnList){
            RepawnMini repawnMini = new RepawnMini();
            if(userPawn.getGoodsId()==null){
                throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
            }
            Integer goodId = userPawn.getGoodsId();
            UserGoods userGoods = userGoodsService.selectByPrimaryKey(goodId);
            String pawnTicketCode = StringUtils.isNotEmpty(userPawn.getPawnTicketCode())?userPawn.getPawnTicketCode():"";
            String goodName = StringUtils.isNotEmpty(userGoods.getName())?userGoods.getName():"";
            String authPrice = userGoods.getAuthPrice()!=null?userGoods.getAuthPrice().toString():"";
            String images = StringUtils.isNotEmpty(userGoods.getImages())?userGoods.getImages():"";
            repawnMini.setId(userPawn.getId().toString());//典当表id
            repawnMini.setGoodsid(userPawn.getGoodsId().toString());//当品id
            repawnMini.setGoodsName(goodName);//当品名称
            repawnMini.setAuthPrice(authPrice);//鉴定价格
            //已贷金额(本金)
            repawnMini.setLoanMoney(userPawn.getBeginMoney()!=null?userPawn.getBeginMoney().toString():"");
            //已发放当金 = 本金 - 首期综合费 若数据库已保存则取数据库，否则计算得到
            String userMoney = "";
            boolean isGo = true;
            if(userPawn.getUserMoney()!=null){
                userMoney = userPawn.getUserMoney().toString();
                isGo = false;
            }else if (isGo && userPawn.getPawnMoney()!=null && userPawn.getBeginMoney()!=null){
                BigDecimal um = userPawn.getBeginMoney().subtract(userPawn.getPawnMoney());
                if(um.compareTo(new BigDecimal("0"))== -1){
                    userMoney = "";
                }else{
                    userMoney = um.toString();
                }
            }
            repawnMini.setUserMoney(userMoney);//已发放当金
            repawnMini.setPawnCode(pawnTicketCode);//当号
            repawnMini.setImages(OrgUtil.getSingleImage(images));//图
           /* //成功续当次数
            Integer pawnedTimes = pawnContinueService.getRepawnTimes(userPawn.getId());
            repawnMini.setRepawnTimes(pawnedTimes!=null?pawnedTimes:0);*/

            //列表分家 pawnId的续当所有记录（申请中的和确认的），按create_time降序排列，取第一个即为最新提交的续当申请信息
            PawnContinueExample pawnContinueExample = new PawnContinueExample();
            pawnContinueExample.createCriteria().andPawnIdEqualTo(userPawn.getId());
            pawnContinueExample.setOrderByClause("create_time desc");
            List<PawnContinue> pawnContinueList = pawnContinueService.selectByExample(pawnContinueExample);
            if (pawnContinueList.size() == 0){ //此pawnid没有续当申请
                //set 典当日期至字段
                repawnMini.setDateRepawnTo(userPawn.getBeginPawnEndTime()!=null? DateUtil.format(userPawn.getBeginPawnEndTime(),"yyyy-MM-dd"):"");
            }else{
                PawnContinue newestOne = pawnContinueList.get(0);//时间倒序取最新一次续当申请
                if (newestOne.getState() == 1){ //续当申请中
                    //显示确认收款（续当手续费）按钮
                    repawnMini.setShowButton("1");
                    //最新续当id，set了repawnId后点击进入详情页面与未set的不同
                    repawnMini.setRepawnId(newestOne.getId().toString());
                    //续当日期至
                    repawnMini.setDateRepawnTo(newestOne.getPawnEndTime()!=null?DateUtil.format(newestOne.getPawnEndTime(),"yyyy-MM-dd"):"");
                }else if (newestOne.getState() == 4){//本次续当已被机构端确认
                    repawnMini.setRepawnId(newestOne.getId().toString());
                    repawnMini.setDateRepawnTo(newestOne.getPawnEndTime()!=null?DateUtil.format(newestOne.getPawnEndTime(),"yyyy-MM-dd"):"");
                }
            }
            retList.add(repawnMini);
        }
        return  retList;
    }


    @ApiOperation(value = "机构端续当详情",notes="机构端续当详情")
    @RequestMapping(value = "/getRepawnDetail", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public RepawnDetail getRepawnDetail(@ApiParam(value = "典当id",required = true) String pawnId,
                                        @ApiParam(value = "续当id 如果为空，返回数据是典当处理详情页面所需字段，如果不为空，返回续当处理所需字段",required = true) String repawnId,
                                        MobileInfo mobileInfo){
        Integer pid;
        Integer rpId;
        if(StringUtils.isEmpty(pawnId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        try{
            pid = Integer.valueOf(pawnId);
        }catch (NumberFormatException e){
            e.printStackTrace();
            throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
        }
        UserPawn userPawn = userPawnService.selectByPrimaryKey(pid);
        if (userPawn == null) {
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //获取本次典当成交时约定的逾期滞纳利率
        BigDecimal redeem_overrate = userPawn.getOverdueRate();
        if (redeem_overrate == null){
            throw  new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer goodId = userPawn.getGoodsId();
        if(goodId == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        User user = userService.selectByPrimaryKey(mobileInfo.getUserId());//登陆机构端用户
        if(user == null){
            throw new ApiException(MEnumError.USER_NOEXIST_ERROR);
        }
        UserGoods userGoods = userGoodsService.selectByPrimaryKey(goodId);
        if (userGoods == null){
            throw new ApiException(MEnumError.USER_NOEXIST_ERROR);
        }
        //取机构的银行卡，竞拍判断有没有绑定，没有跳转绑卡页面，机构卡现在只能有一张。
        OrgBankCardExample example = new OrgBankCardExample();
        example.createCriteria().andOrgIdEqualTo(user.getOrgId()).andBankCardTypeEqualTo(1);
        List<OrgBankCard> cards = orgBankCardService.selectByExample(example);
        if (cards.size() == 0){
            throw new ApiException("该机构还没有绑定自己的银行卡，请先去绑卡");
        }
        OrgBankCard card = cards.get(0);
        String goodName = StringUtils.isNotEmpty(userGoods.getName())?userGoods.getName():"";
        String pawnTicketCode = StringUtils.isNotEmpty(userPawn.getPawnTicketCode())?userPawn.getPawnTicketCode():"";
        String authPrice = userGoods.getAuthPrice()!=null?userGoods.getAuthPrice().toString():"";
        String images = StringUtils.isNotEmpty(userGoods.getImages())?userGoods.getImages():"";

        Integer pawnerUserId = userPawn.getUserId();//当户id
        if(pawnerUserId==null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        User pawner = userService.selectByPrimaryKey(pawnerUserId);//当户
        if(pawner == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //详情用户地址
        String pawnerAddress="";
        UserAddressExample userAddressExample = new UserAddressExample();
        userAddressExample.createCriteria().andUserIdEqualTo(pawnerUserId).andIsDefaultEqualTo(1);
        List<UserAddress> userAddresses = userAddressService.selectByExample(userAddressExample);
        if(userAddresses.size() != 0){
            pawnerAddress = userAddresses.get(0).getAddress()!=null&&userAddresses.get(0).getArea()!=null?userAddresses.get(0).getArea()+userAddresses.get(0).getAddress():"";
        }
//      String pawnerAddress = userPawn.getUserAddress()!=null?userPawn.getUserAddress():"";
        String pawnerName = pawner.getName()!=null?pawner.getName():"";
        String pawnerTelNum = StringUtils.isNotEmpty(pawner.getPhone())?pawner.getPhone():pawner.getAccount();
        String loanMoney = userPawn.getBeginMoney()!=null?userPawn.getBeginMoney().toString():"";//本金
        String firstCost = userPawn.getPawnMoney()!=null?userPawn.getPawnMoney().toString():"";//首期综合费
        String userMoney = "";//本金-综合费实际贷给用户金额
        String bankCardName;  // 收款银行卡银行名称
        String bankCardUserName ;//收款人
        String bankCardNo;       //收款银行卡
        String currentPayTicket;//打款凭证（机构打款给用户，或者用户打款给机构）
        boolean isGo = true;
        //已发放当金 = 贷款金额 - 首期综合费 若数据库已保存则取数据库，否则计算得到
        if(userPawn.getUserMoney()!=null){
            userMoney = userPawn.getUserMoney().toString();
            isGo = false;
        }else if (isGo && userPawn.getPawnMoney()!=null && userPawn.getBeginMoney()!=null){
            BigDecimal um = userPawn.getBeginMoney().subtract(userPawn.getPawnMoney());
            if(um.compareTo(new BigDecimal("0"))== -1){
                userMoney = "";
            }else{
                userMoney = um.toString();
            }
        }
        String rate = userPawn.getRate()!=null?userPawn.getRate().toString():"";// 综合费率
        String moneyRate = userPawn.getMoneyRate()!=null?userPawn.getMoneyRate().toString():"";//赎当利率
        // 当前应还款日期是指现在所处当期结束日期
        Date pawn_begin_time = userPawn.getPawnBeginTime();
        Date pawn_end_time = userPawn.getPawnEndTime();
        String pawnBeginTime = DateUtil.dateToStr(pawn_begin_time,"yyyy-MM-dd");//典当开始
        String pawnEndTime =  DateUtil.dateToStr(pawn_end_time,"yyyy-MM-dd");//典当结束

        BigDecimal preInterest; //上期利息费 由用户端计算并写入续当表
        BigDecimal cost ;    //下个续当周期综合费 由用户端计算并写入续当表
        BigDecimal overdue ; //逾期滞纳金 由用户端计算并写入续当表
      /*
        到期应还款额度payBack = 本金 + 滞纳金（若有）+当前当期对应的赎当利息【赎当利息=本金*月数或者天数(是指本当期包含月/天数，并不包括可能的下一个续当周期)*赎当利率】
        如果时间未到本当期截止时间而用户赎当，也按照当期包含天数计算赎当利息
        */
        String payBack;
        BigDecimal repawnMoney;
        BigDecimal loan_money = new BigDecimal(loanMoney);//本金
        RepawnDetail repawnDetail = new RepawnDetail();

        /*//pawnId的续当所有记录（申请中的和确认的），按create_time降序排列，取第一个即为最新提交的续当申请信息
        PawnContinueExample pawnContinueExample = new PawnContinueExample();
        pawnContinueExample.createCriteria().andPawnIdEqualTo(Integer.valueOf(pawnId));
        pawnContinueExample.setOrderByClause("create_time desc");
        List<PawnContinue> pawnContinueList = pawnContinueService.selectByExample(pawnContinueExample);
        Integer times = pawnContinueService.getRepawnTimes(userPawn.getId());//办理过多少次续当，set到返回model*/

        if(StringUtils.isEmpty(repawnId)){//从未续当过
            //计算赎当利息，如果从未续当过，当前所处当期即为首次办理典当的当期，赎当利息需要机构端来计算
            if(userPawn.getBeginPawnMonth()==null){//初始当期
                throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
            }
            Integer beginPawnMonth = userPawn.getBeginPawnMonth();//初次典当当期
            BigDecimal redeem_interest = getInterest(loan_money,userPawn.getMoneyRate(),beginPawnMonth);
            //计算滞纳金
            overdue = getOverdue(loan_money,pawn_end_time,redeem_overrate) ;
            BigDecimal redeem_money = loan_money.add(redeem_interest).add(overdue);
            payBack = redeem_money.toString();
            repawnDetail.setPayBack(payBack);
            repawnDetail.setFirstCost(firstCost);//典当处理页面：本期综合利息字段，实际上是首期综合费
            repawnDetail.setLoanCurrentRepayTime(DateUtil.dateToStr(userPawn.getBeginPawnEndTime(),"yyyy-MM-dd"));
            //首次典当的收款人，这个收款人是用户
            bankCardName = userPawn.getPayeeBankName()!=null?userPawn.getPayeeBankName():"";
            bankCardUserName = userPawn.getPayeeName()!=null?userPawn.getPayeeName():"";
            bankCardNo = userPawn.getPayeeBankCardCode()!=null?userPawn.getPayeeBankCardCode():"";
            currentPayTicket = userPawn.getPayeeTicket()!=null?userPawn.getPayeeTicket():"";
            repawnDetail.setIsPayFirst(userPawn.getPayeeTicket()!=null?"1":"0");//是否已经缴纳本期综合费 0-否 1-已缴
            repawnDetail.setIsPayPlatMoney(userPawn.getPlatformState()==null?"0":userPawn.getPlatformState()==1?"1":"0");
        }else {
            //从续当表获得该repawnId的本期续当综合费，上一期的利息（赎当）费，上一期逾期费（如果逾期）
            //这些数据在用户端提交了续当申请时由用户端计算写入数据库字段，机构端读取他们计算和展示
            try{
                rpId = Integer.valueOf(repawnId);
            }catch (NumberFormatException e){
                e.printStackTrace();
                throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
            }
            PawnContinue pawnContinue = pawnContinueService.selectByPrimaryKey(rpId);
            preInterest = pawnContinue.getPawnInterest();
            cost = pawnContinue.getPawnMoney();
            overdue =pawnContinue.getPawnOverdue();
            //续当的收款人,账号,银行名称(由于是续当,所以显示的是机构收款的银行名和账号)
            bankCardName = card.getBankCardName()!=null?card.getBankCardName():"";
            bankCardUserName = card.getBankCardUserName()!=null? card.getBankCardUserName():"";
            bankCardNo = userPawn.getPayBacnkCardCode()!=null?userPawn.getPayBacnkCardCode():"";
            currentPayTicket = pawnContinue.getPayTicket();//用户续当打款凭证
            //计算续当至日期
            String dateRepawnToStr ;  //续当至日期 字符串形式
            Date dateRepawnTo;//续当至日期
            //如果数据库保存了续当至日期，则使用数据库该字段，否则根据用户续当当期多长，计算得到
            if (pawnContinue.getPawnEndTime()!=null){
                dateRepawnTo = pawnContinue.getPawnEndTime();
                dateRepawnToStr = dateRepawnTo!=null ? DateUtil.dateToStr(dateRepawnTo,"yyyy-MM-dd"):"";
            }else{
                Integer pawnMonth = pawnContinue.getPawnMonth();//用户最新一期续当，想续多少个当期
                if (pawnMonth == null){ //若续当表续当月份字段为null，抛异常
                    throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
                }else {
                    if(pawnMonth % 2 == 0 ){//如果续当当期换算成月数正好是整数，直接pawn_end_time 的日期 + 该月数 得到 续当至日期
                        dateRepawnTo = DateUtil.addMonth(pawn_end_time,pawnMonth / 2);
                    }else{//否则先加整数个月数，不足一个月部分加15天
                        int i = pawnMonth/2;
                        Date d = DateUtil.addMonth(pawn_end_time,i);
                        dateRepawnTo = DateUtil.add(d,15);
                    }
                    dateRepawnToStr = DateUtil.dateToStr(dateRepawnTo,"yyyy-MM-dd");
                }
            }
            //到期应还款金额=本金+上期赎当利息+滞纳金（若有）
            payBack = loan_money.add(preInterest).add(overdue).toString();
            //续当费用合计=本期综合费（本期指准备续当的那个当期）+上期利息+逾期滞纳金（若逾期，否则为0）
            repawnMoney = cost.add(preInterest).add(overdue);
            repawnDetail.setPayBack(payBack);
            //当前应还款日期：总是上一期结束时间
            repawnDetail.setLoanCurrentRepayTime(DateUtil.dateToStr(pawnContinue.getPawnLastEndTime(),"yyyy-MM-dd"));
            repawnDetail.setRepawnMoney(repawnMoney.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
            //提交的这一期结束时间
            repawnDetail.setDateRepawnTo(dateRepawnToStr);
            repawnDetail.setCost(cost.toString());//续当处理页面，本期（下期）综合费
            repawnDetail.setPreInterest(preInterest.toString());//上期利息
            repawnDetail.setDemurrage(overdue.toString());//逾期滞纳金
//          repawnDetail.setDemurrageRate(PaidangConst.REDEEM_OVERRATE.toString());
            repawnDetail.setDemurrageRate(redeem_overrate.toString());
            repawnDetail.setIsPayRepawnMoney(pawnContinue.getState()==4?"1":"0");//是否已经缴纳续当费 0-否 1-已缴
            repawnDetail.setIsPayPlatMoney(pawnContinue.getPlatformState()==null?"0":pawnContinue.getPlatformState()==1?"1":"0");//是否已经缴纳平台利息 0-否 1-已缴
        }
        repawnDetail.setCollecterId(pawnerUserId.toString());
        repawnDetail.setLoanBeginTime(pawnBeginTime);
        repawnDetail.setMoneyRate(moneyRate);
        repawnDetail.setRate(rate);
        repawnDetail.setPawnerAddress(pawnerAddress);
        repawnDetail.setPawnerTelNum(pawnerTelNum);
        repawnDetail.setPawnerName(pawnerName);
        repawnDetail.setUserMoney(userMoney);//已发放当金
        repawnDetail.setLoanMoney(loanMoney);
        repawnDetail.setAuthPrice(authPrice);
        repawnDetail.setPawnCode(pawnTicketCode);
        repawnDetail.setGoodsid(goodId.toString());
        repawnDetail.setGoodsName(goodName);
        repawnDetail.setImages(images);
        repawnDetail.setId(userPawn.getId().toString());
        repawnDetail.setCurrentPayTicket(currentPayTicket);
        repawnDetail.setBankCardUserName(bankCardUserName);
        repawnDetail.setBankCardName(bankCardName);
        repawnDetail.setBankCardCode(bankCardNo);
        return repawnDetail;
    }



    @ApiOperation(value = "确认收款(续当费)",notes="用户提交续当请求,机构确认续当打款凭证有效后点击此按钮")
    @RequestMapping(value = "/confirmRepawnVoucher", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer confirmRepawnVoucher(@ApiParam(value = "典当id",required = true) String pawnId,
                                    @ApiParam(value = "续当id 该字段值是【续当列表】接口返回的repawnId",required = true) String repawnId,
                                    MobileInfo mobileInfo){
        if (StringUtils.isEmpty(repawnId) || StringUtils.isEmpty(pawnId) ){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer pid;
        Integer rpId;
        try{
            pid = Integer.valueOf(pawnId);
            rpId = Integer.valueOf(repawnId);
        }catch (NumberFormatException e){
            e.printStackTrace();
            throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
        }
        PawnContinue repawnRecord = pawnContinueService.selectByPrimaryKey(rpId);
        UserPawn pawnRecord = userPawnService.selectByPrimaryKey(pid);
        Integer goodsId = pawnRecord.getGoodsId();
        Integer userId = pawnRecord.getUserId();
        Integer orgId = pawnRecord.getOrgId();
        if (goodsId == null || orgId == null || userId == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        UserGoods userGoods = userGoodsService.selectByPrimaryKey(goodsId);
        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(orgId);
        User pawner = userService.selectByPrimaryKey(userId);//当户
        if (userGoods ==null || pawnOrg == null || pawner ==null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        if (repawnRecord == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        BigDecimal pawnMoney = repawnRecord.getPawnMoney();
        BigDecimal pawnInterest = repawnRecord.getPawnInterest();
        BigDecimal pawnOverdue = repawnRecord.getPawnOverdue();
        BigDecimal repawnTotal;//续当合计应缴
        if(pawnMoney != null && pawnInterest != null){
            if (pawnOverdue == null){
                repawnTotal = pawnMoney.add(pawnInterest);
            }else {
                repawnTotal = pawnMoney.add(pawnInterest).add(pawnOverdue);
            }
        }else if (pawnMoney != null){
            repawnTotal = pawnMoney;
        }else {
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //更新续当表 机构是否确认状态字段
        repawnRecord.setState(4);//机构端确认用户上传的续当打款凭证
        pawnContinueService.updateByPrimaryKey(repawnRecord);
        //更新典当表最新一期当金，当期，续当至日期字段
        /*
        pawnRecord.setLastPawnContinueId(repawnRecord.getId());*/
        pawnRecord.setLastMoney(pawnMoney);
        pawnRecord.setLastPawnMonth(repawnRecord.getPawnMonth());
        pawnRecord.setLastPawnTime(repawnRecord.getPawnEndTime());
        userPawnService.updateByPrimaryKey(pawnRecord);

        PawnLog pawnLog = new PawnLog();
        pawnLog.setGoodsId(goodsId);
        pawnLog.setUserId(userId);
        pawnLog.setOrgId(orgId);
        pawnLog.setGoodsName(userGoods.getName());
        pawnLog.setAuthPrice(userGoods.getAuthPrice());
        pawnLog.setGoodsCateId(userGoods.getCateId());
        pawnLog.setGoodsCate(userGoods.getCateCode());
        pawnLog.setOrgName(pawnOrg.getName());
        pawnLog.setMoney(pawnRecord.getBeginMoney());
        pawnLog.setPawnMonth(pawnRecord.getPawnTime());
        pawnLog.setType(2);// 续当
        pawnLog.setUserName(pawner.getName());
        pawnLog.setTradeCardBank(repawnRecord.getUserBank());
        pawnLog.setTradeCardCode(repawnRecord.getUserBankCard());
        pawnLogService.insert(pawnLog);

        OrgBalanceLog orgBalanceLog = new OrgBalanceLog();
        orgBalanceLog.setOrgId(orgId);
        orgBalanceLog.setMoney(repawnTotal.setScale(2,BigDecimal.ROUND_HALF_UP));//交易金额,这是续当
        orgBalanceLog.setType(2);//收入
        orgBalanceLog.setItem("2");//用户续当
        orgBalanceLog.setInfo("id是"+orgId+"的机构的"+pawnRecord.getPayBankName()+"卡号为"+pawnRecord.getPayBacnkCardCode()+"收到用户"+pawner.getId()+"的续当费"+repawnTotal.setScale(2,BigDecimal.ROUND_HALF_UP)+"元,续当当号："+repawnRecord.getPawnTicketCode());
        orgBalanceLog.setPawnCode(repawnRecord.getPawnTicketCode());//续当当号
        orgBalanceLog.setFid(repawnRecord.getId());//续当id
        orgBalanceLog.setTradeType(10);// 线下银行卡
        orgBalanceLog.setTradeCardBank(pawnRecord.getPayBankName());
        orgBalanceLog.setTradeCardCode(pawnRecord.getPayBacnkCardCode());
        orgBalanceLog.setTradeTicket(repawnRecord.getPayTicket());
        orgBalanceLog.setUserId(pawner.getId());
        orgBalanceLog.setUserName(pawner.getName());
        orgBalanceLog.setUserPhone(pawner.getPhone()!=null?pawner.getPhone():pawner.getAccount());
        orgBalanceLogService.insert(orgBalanceLog);

        UserBalanceLog userBalanceLog = new UserBalanceLog();
        userBalanceLog.setTradeNo(repawnRecord.getPawnTicketCode());//现在该字段作为当号
        userBalanceLog.setCid(repawnRecord.getId());//续当id
        userBalanceLog.setUserId(pawner.getId());
        userBalanceLog.setAmount(repawnTotal.setScale(2,BigDecimal.ROUND_HALF_UP));
        userBalanceLog.setType(2);//减
        userBalanceLog.setItem("2");//续当
        userBalanceLog.setInfo("用户"+pawner.getId()+repawnRecord.getUserBank()+"卡号是"+repawnRecord.getUserBankCard()+"的银行卡支付给机构"+orgId+"[续当费]"+repawnTotal.setScale(2,BigDecimal.ROUND_HALF_UP)+"元，本次续当当号："+repawnRecord.getPawnTicketCode());
        userBalanceLog.setTradeType(10);// 线下银行卡
        userBalanceLog.setTradeTicket(repawnRecord.getPayTicket());
        userBalanceLog.setTradeCardBank(repawnRecord.getUserBank());
        userBalanceLog.setTradeCardCode(repawnRecord.getUserBankCard());
        userBalanceLog.setOrgId(orgId);
        userBalanceLog.setOrgName(pawnOrg.getName());
        userBalanceLog.setOrgPhone(pawnOrg.getPhone());
        userBalanceLogService.insert(userBalanceLog);
        return 1;

    }


    @ApiOperation(value = "缴纳平台利息详情",notes="点击【缴纳平台利息】弹窗信息")
    @RequestMapping(value = "/getPlatformInterestInfo", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public PlatformInterestInfo getPlatformInterestInfo(@ApiParam(value = "典当id",required = true) String pawnId,
                                                        @ApiParam(value = "续当id 该字段值是【续当列表】接口返回的repawnId",required = true) String repawnId,
                                                        MobileInfo mobileInfo){
        if (StringUtils.isEmpty(pawnId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        String interset;
        if (StringUtils.isEmpty(repawnId)){
            UserPawn userPawn = userPawnService.selectByPrimaryKey(Integer.valueOf(pawnId));
            if (userPawn == null){
                throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
            }
            interset = userPawn.getPawnMoney().toString();
        }else{
            PawnContinue pawnContinue = pawnContinueService.selectByPrimaryKey(Integer.valueOf(repawnId));
            if (pawnContinue == null){
                throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
            }
            interset = pawnContinue.getPawnMoney().toString();
        }
        PlatformInterestInfo info = new PlatformInterestInfo();
        //百分数形式的平台利率
        info.setPlatformRate(PaidangConst.PLATFORM_INTEREST.multiply(new BigDecimal("100")).toString());
        info.setInterest(interset);
        return info;
    }


}
