package com.api.action;


import com.api.MEnumError;
import com.api.MErrorEnum;
import com.api.util.OrgUtil;
import com.api.util.PageLimit;
import com.api.view.orgDeadPawnage.*;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.DateUtil;
import com.item.dao.model.User;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.UserPawnEx;
import com.paidang.service.*;
import com.ruoyi.common.core.domain.Ret;
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
 * Created by Lee on 2017/10/23.
 */
@RestController
@RequestMapping(value = "/api/deadPawn", produces = { "application/json;charset=UTF-8" })
@Api(tags = "绝当")
public class ApiDeadPawnController extends ApiBaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserPawnService userPawnService;
    @Autowired
    private PawnContinueService pawnContinueService;
    @Autowired
    private UserGoodsService userGoodsService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrgBankCardService orgBankCardService;
    @Autowired
    private OrgAddressService orgAddressService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private ExpressService expressService;
    @Autowired
    private PawnOrgService pawnOrgService;
    @Autowired
    private PlatformGoodsBuyService platformGoodsBuyService;

    @ApiOperation(value = "绝当处理列表",notes="绝当处理列表")
    @RequestMapping(value = "/getOrgDeadPawnList", method = RequestMethod.POST)
    @ApiMethod(isPage = true,isLogin = true)
    public List<DeadPawnMini> getOrgDeadPawnList(MobileInfo mobileInfo, PageLimit pageLimit){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());//机构id
        startPage();
        List<UserPawnEx> userPawnExList = userPawnService.getOrgDeadPawnList(orgId.toString(),"","");
        List<DeadPawnMini> retList = new ArrayList<>();
        for (UserPawnEx element : userPawnExList){
            DeadPawnMini deadPawnMini = new DeadPawnMini();
            deadPawnMini.setPawnId(element.getId().toString());
            deadPawnMini.setGoodsId(element.getGoodsId().toString());
            deadPawnMini.setTitle(element.getGoodsName1()!=null?element.getGoodsName1():"");
            deadPawnMini.setAuthPrice(element.getAuthPrice()!=null?element.getAuthPrice().toString():"");
            deadPawnMini.setImages(OrgUtil.getSingleImage(element.getImages()));
            deadPawnMini.setPawnTicketCode(element.getPawnTicketCode()!=null?element.getPawnTicketCode():"");
            //已发放当金 = 贷款金额 - 首期综合费 若数据库已保存则取数据库，否则计算得到
            if(element.getUserMoney()!=null){
                deadPawnMini.setUserMoney(element.getUserMoney().toString());
            }else if (element.getPawnMoney()!=null && element.getBeginMoney()!=null){
                deadPawnMini.setUserMoney(element.getBeginMoney().subtract(element.getPawnMoney()).toString());
            }else {
                deadPawnMini.setUserMoney("");
            }
            retList.add(deadPawnMini);
        }
        return retList;
    }

    @ApiOperation(value = "绝当处理详情",notes="绝当处理详情")
    @RequestMapping(value = "/getOrgDeadPawnDetail", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public DeadPawnDetail getOrgDeadPawnDetail(@ApiParam(value = "典当表id",required = true) String pawnId,MobileInfo mobileInfo){
        User user = userService.selectByPrimaryKey(mobileInfo.getUserId());//登陆机构端用户
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());

        UserPawnEx userPawnEx = userPawnService.getUserPawnExById(pawnId);
        //获取本次典当成交时约定的逾期滞纳利率
        BigDecimal redeem_overrate = userPawnEx.getOverdueRate();
        if (redeem_overrate == null){
            throw  new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        BigDecimal authPrice = userPawnEx.getAuthPrice();
        if (authPrice == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }

        DeadPawnDetail deadPawnDetail = new DeadPawnDetail();
        deadPawnDetail.setPawnId(userPawnEx.getId().toString());
        deadPawnDetail.setGoodsId(userPawnEx.getGoodsId().toString());
        deadPawnDetail.setTitle(userPawnEx.getGoodsName1()!=null?userPawnEx.getGoodsName1():"");
        deadPawnDetail.setAuthPrice(authPrice.toString());
        deadPawnDetail.setImages(userPawnEx.getImages()!=null?userPawnEx.getImages():"");
        deadPawnDetail.setCollecterId(userPawnEx.getUserId().toString());
        deadPawnDetail.setPawnerName(userPawnEx.getUserName()!=null?userPawnEx.getUserName():"");
        deadPawnDetail.setPawnTicketCode(userPawnEx.getPawnTicketCode()!=null?userPawnEx.getPawnTicketCode():"");
        deadPawnDetail.setPawnerTelNum(userPawnEx.getUserPhone()!=null?userPawnEx.getUserPhone():"");

        deadPawnDetail.setLoanBeginTime(userPawnEx.getPawnBeginTime()!=null? DateUtil.dateToStr(userPawnEx.getPawnBeginTime(),"yyyy-MM-dd") :"");
        //当前应还款时期
        deadPawnDetail.setLoanCurrentEndTime(userPawnEx.getPawnEndTime()!=null?DateUtil.dateToStr(userPawnEx.getPawnEndTime(),"yyyy-MM-dd") :"");
        //详情用户地址
        String pawnerAddress="";
        UserAddressExample userAddressExample = new UserAddressExample();
        userAddressExample.createCriteria().andUserIdEqualTo(userPawnEx.getUserId()).andIsDefaultEqualTo(1);
        List<UserAddress> userAddresses = userAddressService.selectByExample(userAddressExample);
        if(userAddresses.size() != 0){
            pawnerAddress = userAddresses.get(0).getAddress()!=null&&userAddresses.get(0).getArea()!=null?userAddresses.get(0).getArea()+userAddresses.get(0).getAddress():"";
        }
        deadPawnDetail.setPawnerAddress(pawnerAddress);

        BigDecimal loan_money = userPawnEx.getBeginMoney();//本金
        if(loan_money==null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        deadPawnDetail.setLoanMoney(loan_money.toString());
        BigDecimal moneyRate = userPawnEx.getMoneyRate();//利息利率
        if(moneyRate==null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        deadPawnDetail.setMoneyRate(moneyRate.toString());
        BigDecimal rate = userPawnEx.getRate();//综合利息，费率
        deadPawnDetail.setRate(rate != null?rate.toString():"");
//        deadPawnDetail.setRedeemOverRate(PaidangConst.REDEEM_OVERRATE.toString());//逾期滞纳利率
        deadPawnDetail.setRedeemOverRate(redeem_overrate.toString());
        //已发放当金 = 贷款金额 - 首期综合费 若数据库已保存则取数据库，否则计算得到
        if(userPawnEx.getUserMoney()!=null){
            deadPawnDetail.setUserMoney(userPawnEx.getUserMoney().toString());
        }else if (userPawnEx.getPawnMoney()!=null && userPawnEx.getBeginMoney()!=null){
            deadPawnDetail.setUserMoney(userPawnEx.getBeginMoney().subtract(userPawnEx.getPawnMoney()).toString());
        }else {
            deadPawnDetail.setUserMoney("0");
        }

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
            //用户续当过至少一次,计算赎当利息的时长就是上一个续当约定的当期长
            PawnContinueExample pawnContinueExample = new PawnContinueExample();
            pawnContinueExample.createCriteria().andPawnIdEqualTo(Integer.valueOf(pawnId)).andStateEqualTo(4);
            pawnContinueExample.setOrderByClause("create_time desc");
            List<PawnContinue> pawnContinueList = pawnContinueService.selectByExample(pawnContinueExample);
            PawnContinue pawnContinue = pawnContinueList.get(0);// 有此pawnId的续当记录，取第一个，最新的
            Integer pawnMonth = pawnContinue.getPawnMonth();//续当表保存的该续当周期时长(当前时期所在续当周期)
            //计算赎当利息
            if(userPawnEx.getRedeemInterest()==null){
                redeem_interest = getInterest(loan_money,moneyRate,pawnMonth);
            }else{
                redeem_interest = userPawnEx.getRedeemInterest();
            }
        }
        //计算逾期费（若逾期）
        BigDecimal redeemOverdue;
        Date pawnEndTime = userPawnEx.getPawnEndTime();//典当结束时间
        if (userPawnEx.getRedeemOverdue() == null){
            redeemOverdue = getOverdue(loan_money,pawnEndTime,redeem_overrate);
        }else{
            redeemOverdue = userPawnEx.getRedeemOverdue();
        }
        deadPawnDetail.setMoneyCost(redeem_interest.setScale(2,BigDecimal.ROUND_HALF_UP).toString());//赎当利息
        deadPawnDetail.setRedeemOverdue(redeemOverdue.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        //到期应还款金额 = 本金 + 本期利息 + 逾期费（如果逾期）
        payBack = loan_money.add(redeem_interest).add(redeemOverdue);
        deadPawnDetail.setPayBack(payBack.setScale(2,BigDecimal.ROUND_HALF_UP).toString());

        //平台拍卖按钮
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andGoodsIdEqualTo(userPawnEx.getGoodsId());
        int goodsCounts = goodsService.countByExample(goodsExample);//此id的物品是否已经拍过了
        if (authPrice.compareTo(PaidangConst.BOUNDARY_PRICE) > 0){
            deadPawnDetail.setAuctionButton("0");
        }else {
            if (goodsCounts > 0){
                deadPawnDetail.setAuctionButton("0");
                deadPawnDetail.setSellButton("0");
                deadPawnDetail.setFectchBackButton("0");
                return deadPawnDetail;
            }else {
                deadPawnDetail.setAuctionButton("1");
            }
        }
        //卖给平台按钮
        PlatformGoodsBuyExample platformGoodsBuyExample = new PlatformGoodsBuyExample();
        platformGoodsBuyExample.createCriteria().andSourceEqualTo(2).andGoodsIdEqualTo(userPawnEx.getGoodsId());
        int platformGoodsBuyCounts = platformGoodsBuyService.countByExample(platformGoodsBuyExample);
        if (platformGoodsBuyCounts > 0){
            deadPawnDetail.setAuctionButton("0");
            deadPawnDetail.setSellButton("0");
            deadPawnDetail.setFectchBackButton("0");
            return deadPawnDetail;
        }else{
            deadPawnDetail.setSellButton("1");
        }
        //取回按钮
        UserGoods userGoodsRecord = userGoodsService.selectByPrimaryKey(userPawnEx.getGoodsId());
        ExpressExample expressExample = new ExpressExample();
        expressExample.createCriteria().andFidEqualTo(userGoodsRecord.getId()).andTypeEqualTo(6);
        int expressCount = expressService.countByExample(expressExample);
        if (expressCount > 0){
            deadPawnDetail.setAuctionButton("0");
            deadPawnDetail.setSellButton("0");
            deadPawnDetail.setFectchBackButton("0");
            return deadPawnDetail;
        }else{
            deadPawnDetail.setFectchBackButton("1");
        }
        return deadPawnDetail;
    }


    @ApiOperation(value = "平台拍卖详情",notes="")
    @RequestMapping(value = "/getOrgAuctionDetail", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public OrgAuctionDetail getOrgAuctionDetail(@ApiParam(value = "典当表id",required = true) String pawnId,MobileInfo mobileInfo){
        if(StringUtils.isEmpty(pawnId)){
            throw  new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        UserPawn userPawn = userPawnService.selectByPrimaryKey(Integer.valueOf(pawnId));
        Integer goodsId = userPawn.getGoodsId();
        if (goodsId == null){
            throw  new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        UserGoods userGoods = userGoodsService.selectByPrimaryKey(goodsId);
        String goodsName = userGoods.getName();
        String images = userGoods.getImages();
        BigDecimal authPrice = userGoods.getAuthPrice();


        OrgAuctionDetail orgAuctionDetail = new OrgAuctionDetail();
        orgAuctionDetail.setGoodsName(goodsName);
        orgAuctionDetail.setImages(images);
        orgAuctionDetail.setGoodsId(goodsId.toString());
        orgAuctionDetail.setPawnId(pawnId);
        orgAuctionDetail.setStartingPrice(authPrice.toString());//起拍价
        return orgAuctionDetail;
    }


    @ApiOperation(value = "平台拍卖发布",notes="机构端在平台上发布拍卖绝当品")
    @RequestMapping(value = "/orgPublishAuction", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer orgPublishAuction(@ApiParam(value = "典当表id",required = true) String pawnId,MobileInfo mobileInfo,
                                 @ApiParam(value = "绝当品id",required = true) String goodsId,
                                 @ApiParam(value = "物品描述",required = false) String goodsDesc,
                                 @ApiParam(value = "物品图片id（通过详情页面+号新上传的图片）",required = false) String extraImgs
                                 ){
            if(StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(pawnId)){
                throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
            }
            Integer gid;
            try{
                gid = Integer.valueOf(goodsId);
            }catch (NumberFormatException e){
                throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
            }
            Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
            UserGoods userGoods = userGoodsService.selectByPrimaryKey(gid);
            if (userGoods == null){
                throw  new ApiException(MErrorEnum.APPID_FAIL_ERROR);
            }
            //该goosId藏品是否已申请取回,若是,不能再平台拍卖
            if (userGoods.getPlatOrgState() != null && userGoods.getPlatOrgState() > 0){
                throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
            }
            //该物品是否已经卖给平台,若是,无法再平台拍卖
            PlatformGoodsBuyExample platformGoodsBuyExample = new PlatformGoodsBuyExample();
            platformGoodsBuyExample.createCriteria().andGoodsIdEqualTo(gid).andSourceEqualTo(2);
            int c = platformGoodsBuyService.countByExample(platformGoodsBuyExample);
            if (c > 0){
                throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
            }
            String goodsName = userGoods.getName();
            String cateCode = userGoods.getCateCode();
            String images = userGoods.getImages();
            String coverImg = images.split(",")[0];//第一张图作为封面
            BigDecimal authPrice = userGoods.getAuthPrice();

            Goods goods = new Goods();
            goods.setState(1);// 竞拍立刻生效
            goods.setSource(2);//来源：机构
            goods.setOrgId(orgId);//机构id
            goods.setType(2);//类型：绝当品
            goods.setCost(authPrice);//成本，当type=2绝当品，该字段表示该绝当品的起拍价
            goods.setPrice(authPrice);//售价
            goods.setTotal(1);//机构把绝当品挂到平台拍卖，数量为1
            goods.setSoldOut(0);//已售默认0
            goods.setIsOnline(1);//1上架0下架
            goods.setIsVerfiy(2);//1审核中2通过3不通过
            goods.setCateCode(Integer.parseInt(cateCode));//类别id
            goods.setGoodsId(userGoods.getId());
            goods.setName(goodsName);
            goods.setImg(coverImg);
            goods.setImgs(images+extraImgs);//绝当品之前的图片+拍卖时机构新上传的图
            goods.setWidth("20");//app绝当品拍卖页面没有这两个字段，但用户端需要这两字段，先接口写死
            goods.setHeight("30");
            goods.setInfo(goodsDesc);
            goods.setCreateTime(new Date());
            goodsService.insert(goods);
            return 1;
    }

    @ApiOperation(value = "卖给平台详情",notes="")
    @RequestMapping(value = "/sellToPlatform", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public SellToPlatformDetail sellToPlatform(@ApiParam(value = "典当表id",required = true) String pawnId, MobileInfo mobileInfo){
        if(StringUtils.isEmpty(pawnId)){
            throw  new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        UserPawn userPawn = userPawnService.selectByPrimaryKey(Integer.valueOf(pawnId));
        Integer goodsId = userPawn.getGoodsId();
        if (goodsId == null){
            throw  new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        UserGoods userGoods = userGoodsService.selectByPrimaryKey(goodsId);
        String goodsName = userGoods.getName();
        String images = userGoods.getImages();
        BigDecimal authPrice = userGoods.getAuthPrice();

        OrgBankCardExample orgBankCardExample = new OrgBankCardExample();
        orgBankCardExample.createCriteria().andOrgIdEqualTo(orgId);
        List<OrgBankCard> cardsList = orgBankCardService.selectByExample(orgBankCardExample);
        List<MyBankCard> memberList = new ArrayList<>();
        for (OrgBankCard orgBankCard:cardsList){
            MyBankCard myBankCard = new MyBankCard();
            myBankCard.setBankName(orgBankCard.getBankCardName());
            myBankCard.setBankCode(orgBankCard.getBankCardNo());
            myBankCard.setIsDefault(orgBankCard.getIsDefault());
            memberList.add(myBankCard);
        }
        SellToPlatformDetail sellToPlatformDetail = new SellToPlatformDetail();
        sellToPlatformDetail.setPawnId(pawnId);
        sellToPlatformDetail.setGoodsId(goodsId.toString());
        sellToPlatformDetail.setGoodsName(goodsName);
        sellToPlatformDetail.setImages(images);
        sellToPlatformDetail.setAuthPrice(authPrice.toString());
        sellToPlatformDetail.setPurchasingPrice(authPrice.toString());
        sellToPlatformDetail.setPayAccounts(memberList);
        return sellToPlatformDetail;
    }



    @ApiOperation(value = "卖给平台 确认卖出",notes="确认卖出按钮")
    @RequestMapping(value = "/comfirmSelling", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer comfirmSelling(@ApiParam(value = "(绝)当品id",required = true) String goodsId,
                              @ApiParam(value = "银行名称",required = true) String bankName,
                              @ApiParam(value = "银行卡号",required = true) String bankCode,
                              MobileInfo mobileInfo){
        if(StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(bankName) || StringUtils.isEmpty(bankCode)){
            throw  new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer gid;
        try{
            gid = Integer.valueOf(goodsId);
        }catch (NumberFormatException e){
            throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
        }
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        PawnOrg orgRecord = pawnOrgService.selectByPrimaryKey(orgId);
        if (orgRecord == null){
            throw  new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        UserGoods userGoods = userGoodsService.selectByPrimaryKey(gid);
        if (userGoods == null){
            throw  new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //该goodId藏品是否已去平台拍卖，若是,不能再卖给平台。
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andGoodsIdEqualTo(gid);
        int c = goodsService.countByExample(goodsExample);
        if (c > 0){
            throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
        }
        //该goosId藏品是否已申请取回,若是,不能再申请卖给平台
        if (userGoods.getPlatOrgState() != null && userGoods.getPlatOrgState() > 0){
            throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
        }
        BigDecimal authPrice = userGoods.getAuthPrice();
        PlatformGoodsBuy platformGoodsBuy = new PlatformGoodsBuy();
        platformGoodsBuy.setSource(2);//机构
        platformGoodsBuy.setGoodsId(Integer.valueOf(goodsId));
        platformGoodsBuy.setUserName(orgRecord.getLegalPerson()!=null?orgRecord.getLegalPerson():"");
        platformGoodsBuy.setUserPhone(orgRecord.getPhone()!=null?orgRecord.getPhone():"");
        platformGoodsBuy.setPrice(authPrice);
        platformGoodsBuy.setBankCardName(bankName);
        platformGoodsBuy.setBankCardNo(bankCode);
        platformGoodsBuy.setState(1);//卖给平台
        platformGoodsBuy.setCreateTime(new Date());
        platformGoodsBuyService.insert(platformGoodsBuy);
        return 1;
    }


    @ApiOperation(value = "取回信息确认",notes="取回信息确认页面的数据，如果返回值为空,表示没有任何地址或者没有设置默认地址")
    @RequestMapping(value = "/backInfoConfirm", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public OrgAddressDetail backInfoConfirm(MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        OrgAddressExample orgAddressExample = new OrgAddressExample();
        orgAddressExample.createCriteria().andOrgIdEqualTo(orgId).andIsDefaultEqualTo(1);
        List<OrgAddress> list =  orgAddressService.selectByExample(orgAddressExample);
        OrgAddressDetail orgAddressDetail = new OrgAddressDetail();
        if (list.size()==0){
            return  orgAddressDetail;
        }
        OrgAddress orgAddress = list.get(0);
        orgAddressDetail.setAddressId(orgAddress.getId().toString());
        orgAddressDetail.setAddress(orgAddress.getAddress());
        orgAddressDetail.setArea(orgAddress.getArea());
        orgAddressDetail.setIsDefault(orgAddress.getIsDefault());
        orgAddressDetail.setRecipients(orgAddress.getUserName());
        orgAddressDetail.setRecipientsTel(orgAddress.getPhone());
        return orgAddressDetail;
    }


    @ApiOperation(value = "确认取回按钮",notes="")
    @RequestMapping(value = "/confirmFetchBack", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer confirmFetchBack(@ApiParam(value = "(绝)当品id",required = true) String goodsId,
                                @ApiParam(value = "地址id",required = true) String addressId,
                                MobileInfo mobileInfo){

        if (StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(addressId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer gid;
        Integer addrId;
        try{
            gid = Integer.valueOf(goodsId);
            addrId =Integer.valueOf(addressId);
        }catch (NumberFormatException e){
            throw new ApiException(MEnumError.NUMINPUT_ILLEGAL_ERROR);
        }
        //该goodId藏品是否已去平台拍卖，若是,不能取回。
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andGoodsIdEqualTo(gid);
        int c1 = goodsService.countByExample(goodsExample);
        if (c1 > 0){
            throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
        }
        //该物品是否已经卖给平台,若是,也无法取回
        PlatformGoodsBuyExample platformGoodsBuyExample = new PlatformGoodsBuyExample();
        platformGoodsBuyExample.createCriteria().andGoodsIdEqualTo(gid).andSourceEqualTo(2);
        int c2 = platformGoodsBuyService.countByExample(platformGoodsBuyExample);
        if (c2 > 0){
            throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
        }
        UserGoods goodsRecord = userGoodsService.selectByPrimaryKey(gid);
        OrgAddress addressRecord = orgAddressService.selectByPrimaryKey(addrId);
        if(goodsRecord == null || addressRecord == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        goodsRecord.setOrgUserName(addressRecord.getUserName());//机构收件人名称
        goodsRecord.setOrgUserAddress(addressRecord.getArea()+addressRecord.getAddress());//取回收件人地址
        goodsRecord.setOrgUserPhone(addressRecord.getPhone());
        goodsRecord.setPlatOrgState(1);//机构取回的状态(0默认1申请取回2取回中3确认收货)
        userGoodsService.updateByPrimaryKey(goodsRecord);
        return 1;
    }



    @ApiOperation(value = "添加/修改地址",notes="")
    @RequestMapping(value = "/editAddress", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Ret editAddress(@ApiParam(value = "地址id",required = false) String addressId,
                          @ApiParam(value = "收件人姓名",required = true) String recipients,
                          @ApiParam(value = "电话",required = true) String telphone,
                          @ApiParam(value = "省市区",required = true) String area,
                          @ApiParam(value = "具体地址",required = true) String address,MobileInfo mobileInfo){
        if (StringUtils.isEmpty(recipients) || StringUtils.isEmpty(telphone) || StringUtils.isEmpty(area) || StringUtils.isEmpty(address)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        int result;
        if(StringUtils.isEmpty(addressId)){
            OrgAddress orgAddress = new OrgAddress();
            orgAddress.setOrgId(orgId);
            orgAddress.setUserName(recipients);
            orgAddress.setArea(area);
            orgAddress.setAddress(address);
            orgAddress.setPhone(telphone);
            orgAddress.setIsDefault(0);
            orgAddress.setCreateTime(new Date());
            result = orgAddressService.insert(orgAddress);
        }else {
            OrgAddress record = orgAddressService.selectByPrimaryKey(Integer.valueOf(addressId));
            if (record == null){
                throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
            }
            record.setUserName(recipients);
            record.setPhone(telphone);
            record.setArea(area);
            record.setAddress(address);
            result = orgAddressService.updateByPrimaryKey(record);
        }

        if (result != 1){
            throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
        }
        return msg(0,"操作成功");
    }



    @ApiOperation(value = "删除地址",notes="")
    @RequestMapping(value = "/delAddress", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Ret delAddress(@ApiParam(value = "地址id",required = true) String addressId,MobileInfo mobileInfo){
        if(StringUtils.isEmpty(addressId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        int result = orgAddressService.deleteByPrimaryKey(Integer.valueOf(addressId));
        if (result != 1){
            throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
        }
        return msg(0,"操作成功");
    }



    @ApiOperation(value = "我的地址列表",notes="")
    @RequestMapping(value = "/getMyAddresses", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public List<OrgAddressDetail> getMyAddresses(MobileInfo mobileInfo){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        OrgAddressExample orgAddressExample = new OrgAddressExample();
        orgAddressExample.createCriteria().andOrgIdEqualTo(orgId);
        List<OrgAddress> orgAddresses = orgAddressService.selectByExample(orgAddressExample);

        List<OrgAddressDetail> retList = new ArrayList<>();
        for (OrgAddress orgAddress: orgAddresses){
            OrgAddressDetail orgAddressDetail = new OrgAddressDetail();
            orgAddressDetail.setAddressId(orgAddress.getId().toString());
            orgAddressDetail.setArea(orgAddress.getArea());
            orgAddressDetail.setAddress(orgAddress.getAddress());
            orgAddressDetail.setIsDefault(orgAddress.getIsDefault());
            orgAddressDetail.setRecipients(orgAddress.getUserName());
            orgAddressDetail.setRecipientsTel(orgAddress.getPhone());
            retList.add(orgAddressDetail);
        }
        return  retList;
    }


    @ApiOperation(value = "设置某个地址为默认地址",notes="")
    @RequestMapping(value = "/setDefaultAddress", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Ret setDefaultAddress(@ApiParam(value = "地址id",required = true) String addressId,MobileInfo mobileInfo){
        if (StringUtils.isEmpty(addressId)){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        Integer addrId = Integer.valueOf(addressId);
        OrgAddress record = orgAddressService.selectByPrimaryKey(addrId);
        if (record == null){
            throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
        }
        //把当前id对应的地址改为默认的
        record.setIsDefault(1);
        int result = orgAddressService.updateByPrimaryKey(record);
        //除此以外其他的地址全改为非默认
        OrgAddressExample orgAddressExample = new OrgAddressExample();
        orgAddressExample.createCriteria().andOrgIdEqualTo(record.getOrgId());
        List<OrgAddress> others = orgAddressService.selectByExample(orgAddressExample);
        for (OrgAddress element:others) {
            if (element.getId() == addrId){
                continue;
            }
            if (element.getIsDefault() == 1){
                element.setIsDefault(0);
                orgAddressService.updateByPrimaryKey(element);
            }
        }
        if (result != 1){
            throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
        }
        return msg(0,"操作成功");
    }




}
