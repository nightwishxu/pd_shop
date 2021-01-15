package com.api.action;

import com.api.MEnumError;
import com.api.util.PageLimit;
import com.api.view.indexInfo.ApiIndexMenu;
import com.api.view.store.AppJdGoods;
import com.api.view.store.AppOrgName;
import com.api.view.store.AppStoreBanner;
import com.api.view.store.AppStoreGoodsDetail;
import com.api.view.user.AppUserCoupon;
import com.api.view.video.AppVideoOnline;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.BaseUtils;
import com.base.util.DateUtil;
import com.base.util.StringUtil;
import com.google.common.collect.Lists;
import com.item.daoEx.model.AdEx;
import com.item.service.AdService;
import com.item.service.FocusService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.GoodsAuctionEx;
import com.paidang.daoEx.model.GoodsEx;
import com.paidang.daoEx.model.PawnOrgEx;
import com.paidang.daoEx.model.VideoOnlineEx;
import com.paidang.domain.qo.GoodsQo;
import com.paidang.domain.vo.PawnOrgVo;
import com.paidang.service.*;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.util.PaidangConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping(value = "/api/storeGoods", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
@Api(tags = "商城(用户端)")
public class ApiStoreController extends ApiBaseController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private FocusService focusService;
    @Autowired
    private PawnOrgService pawnOrgService;
    @Autowired
    private GoodsAuctionService goodsAuctionService;
    @Autowired
    private AdService adService;
    @Autowired
    private VideoOnlineService videoOnlineService;
    @Autowired
    private AuthPersonalService authPersonalService;
    @Autowired
    private AuthEnterpriseService authEnterpriseService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserFollowService userFollowService;

    @Autowired
    private UserReportService userReportService;


    public enum MStoreGoodsCateList {
        zb("1","钟表","0"),
        fc("2","翡翠","0"),
        hty("3","和田玉","0"),
        gdysp("4","古董艺术品","0"),
        sh("5","书画","0"),
        cszb("6","彩色珠宝","0"),
        zs("7","钻石","0"),
        other("8","更多","0"),

        mqyt("9","明清砚台","4"),
        ww("10","文玩","4"),
        zx("11","杂项","4"),
        hlbs("12","红蓝宝石","6"),
        zml("13","祖母绿","6"),
        zz("14","珍珠","6"),
        bx("15","碧玺","6"),


        yczb("101","玉翠珠宝","0"),
        gyzp("102","工艺作品","0"),
        wwzx("103","文玩杂项","0"),
        zstc("104","紫砂陶瓷","0"),
        qbyp("105","钱币邮票","0"),
        shzk("106","书画篆刻","0"),
        hnwy("107","花鸟文娱","0"),
        qt("108","其他","0"),

        ;

        public static MStoreGoodsCateList getByCode(String type) {
            if (StringUtils.isBlank(type)) {
                return null;
            }

            MStoreGoodsCateList result = null;
            for (MStoreGoodsCateList tmp : MStoreGoodsCateList.values()) {
                if (Objects.equals(tmp.getCode(), type)) {
                    result = tmp;
                    break;
                }
            }
            return result;
        }

        private String code;
        private String name;
        private String fid;
        private MStoreGoodsCateList(String code,String name,String fid) {
            this.code = code;
            this.name = name;
            this.fid = fid;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getFid() {
            return fid;
        }
    }

    /**
     * 认证商城首页轮播
     * @return
     */
    @ApiOperation(value = "认证商城首页轮播", notes = "不需要登录")
    @RequestMapping("/storeBanner")
    @ApiMethod(isLogin = false)
    public List<AppStoreBanner> storeBanner(){
        List<AppStoreBanner> ret = new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("location",2);
        List<AdEx> list = adService.selectRzList(map);
        for(AdEx ex : list){
            AppStoreBanner record = new AppStoreBanner();
                record.setId(ex.getId());
                record.setUrl(ex.getImg());
                record.setType(ex.getType());
                if(0 == ex.getType()){
                    record.setContent("");
                }else if(1 == ex.getType()){
                    record.setContent(ex.getContent());
                }else if(2 == ex.getType()){
                    record.setContent(getPage(ex.getId().toString(),10));
                }else if(3 == ex.getType()){
                    record.setContent(ex.getContent());
                }else if(4 == ex.getType()){
                    record.setContent(ex.getContent());
                }else if(5 == ex.getType()){
                    VideoOnlineExample example = new VideoOnlineExample();
                    example.createCriteria().andIdEqualTo(Integer.parseInt(ex.getContent()));
                    String video= videoOnlineService.selectByExample(example).get(0).getVideo();
//                    BFileExample example1 = new BFileExample();
//                    example1.createCriteria().andFileIdEqualTo(video);
//                    String file_path = fileService.selectByExample(example1).get(0).getFilePath();
                    record.setContent(video);
                }
            if(4 == ex.getType()){
                //如果是绝当商品的商品
                if(null == ex.getGoodsId()){
                    //如果是后台上传的,则不区分30000
                    record.setState(2);
                }else{
                    if(new BigDecimal(ex.getCost()).compareTo(new BigDecimal(30000) )== -1){
                        //走流程小于三万则是普通商品
                        record.setState(2);
                    }else{
                        record.setState(1);
                    }
                }
            }
            ret.add(record);
        }
        return ret;
    }


    /**
     * 认证商场列表
     */
    @ApiOperation(value = "认证商场列表", notes = "分页")
    @RequestMapping("/storeGoodsList")
    @ApiMethod(isLogin = false)
    public List<AppStoreGoodsDetail> storeGoodsList(MobileInfo mobileInfo,
                                        @ApiParam(value="旧类型(1钟表，2翡翠，3和田玉，4古董艺术品，5书画，6彩色珠宝，7钻石，8其他，9明清砚台，10文玩，" +
                                                            "11杂项,12红蓝宝石，13祖母绿，14珍珠，15碧玺)， 新类型（101:玉翠珠宝,102:工艺作品,103:文玩杂项,104:紫砂陶瓷,105:钱币邮票,106:书画篆刻,107:花鸟文娱,108:其他）",required = true) Integer type,
                                        PageLimit pageLimit){
        startPage();
        List<AppStoreGoodsDetail> list2 = new ArrayList<AppStoreGoodsDetail>();
        GoodsExample goodsExample = new GoodsExample();

        GoodsQo qo = new GoodsQo();
        qo.setStartTotal(1);
        qo.setType(type);
        qo.setState(1);
        qo.setIsOnline(1);
        qo.setIsVerfiy(2);
        qo.setType(1);
        qo.setStartTotal(1);
        List<Integer> sources = Lists.newArrayList();
        sources.add(2);
        sources.add(3);
        sources.add(4);
        sources.add(6);
        sources.add(7);
        qo.setSources(sources);

        List<GoodsEx> list = goodsService.findListEx(qo);

//
//        if(null != type){
//            goodsExample.or().andCateCodeEqualTo(type).andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andTypeEqualTo(1).andSourceEqualTo(3).andTotalGreaterThanOrEqualTo(1);
//            goodsExample.or().andCateCodeEqualTo(type).andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andTypeEqualTo(1).andSourceEqualTo(4).andTotalGreaterThanOrEqualTo(1);
//            goodsExample.or().andCateCodeEqualTo(type).andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andTypeEqualTo(1).andSourceEqualTo(2).andTotalGreaterThanOrEqualTo(1);
//            goodsExample.or().andCateCodeEqualTo(type).andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andTypeEqualTo(1).andSourceEqualTo(6).andTotalGreaterThanOrEqualTo(1);
//            goodsExample.or().andCateCodeEqualTo(type).andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andTypeEqualTo(1).andSourceEqualTo(7).andTotalGreaterThanOrEqualTo(1);
//        }else{
//            goodsExample.or().andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(3).andTypeEqualTo(1).andTotalGreaterThanOrEqualTo(1);
//            goodsExample.or().andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(4).andTypeEqualTo(1).andTotalGreaterThanOrEqualTo(1);
//            goodsExample.or().andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(2).andTypeEqualTo(1).andTotalGreaterThanOrEqualTo(1);
//            goodsExample.or().andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(6).andTypeEqualTo(1).andTotalGreaterThanOrEqualTo(1);
//            goodsExample.or().andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(7).andTypeEqualTo(1).andTotalGreaterThanOrEqualTo(1);
//        }
//
//        List<Goods> list = goodsService.selectByExample(goodsExample);
        for(GoodsEx ex : list){
            AppStoreGoodsDetail record = new AppStoreGoodsDetail();
            record.setId(ex.getId());
            record.setTitle(ex.getName());
            record.setImg(ex.getImg());
            record.setImages(ex.getImgs());
            record.setWidth(ex.getWidth());
            record.setHeight(ex.getHeight());
            record.setAuthPrice(ex.getPrice()+"");
            record.setPrice(ex.getPrice()+"");
            record.setOrgId(ex.getOrgId());
            record.setDealType(BaseUtils.getDefaultDealType(ex.getDealType()));
            record.setOrgIntegral(ex.getOrgIntegral());
            list2.add(record);
        }
        return list2;
    }

    /**
     * 绝当商场类型列表
     */
    @ApiOperation(value = "绝当商场类型列表", notes = "分页")
    @RequestMapping("/storeJDGoodsLists")
    @ApiMethod(isLogin = false)
    public List<AppStoreGoodsDetail> storeJDGoodsLists(MobileInfo mobileInfo,
                                                       @ApiParam(value="code(1奢侈品珠宝，2手表，3钻石，4贵金属，5翡翠玉石，6和田玉，7其他) 新类型（101:玉翠珠宝,102:工艺作品,103:文玩杂项,104:紫砂陶瓷,105:钱币邮票,106:书画篆刻,107:花鸟文娱,108:其他）",required = true) Integer type,
                                                       PageLimit pageLimit){
        startPage();
        List<AppStoreGoodsDetail> list2 = new ArrayList<AppStoreGoodsDetail>();
//        GoodsExample goodsExample = new GoodsExample();
//        goodsExample.or().andCateCodeEqualTo(type).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(3).andTypeEqualTo(2);
//        goodsExample.or().andCateCodeEqualTo(type).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(2).andTypeEqualTo(2);
//        List<Goods> list = goodsService.selectByExample(goodsExample);
        GoodsQo qo = new GoodsQo();
        qo.setType(type);
        qo.setIsOnline(1);
        qo.setIsVerfiy(2);
        qo.setType(2);
        qo.setStartTotal(1);
        List<Integer> sources = Lists.newArrayList();
        sources.add(2);
        sources.add(3);
        qo.setSources(sources);

        List<GoodsEx> list = goodsService.findListEx(qo);
        for(GoodsEx ex : list){
            AppStoreGoodsDetail record = new AppStoreGoodsDetail();
            record.setId(ex.getId());
            record.setTitle(ex.getName());
            record.setImg(ex.getImg());
            record.setImages(ex.getImgs());
            record.setWidth(ex.getWidth());
            record.setHeight(ex.getHeight());
            record.setAuthPrice(ex.getPrice()+"");
            record.setPrice(ex.getPrice()+"");
            record.setOrgId(ex.getOrgId());
            record.setDealType(BaseUtils.getDefaultDealType(ex.getDealType()));
            record.setOrgIntegral(ex.getOrgIntegral());
            list2.add(record);
        }
        return list2;
    }

    @ApiOperation(value = "商品推荐", notes = "")
    @RequestMapping("/recommendGoods")
    @ApiMethod(isLogin = false)
    public List<AppStoreGoodsDetail> recommendGoods(MobileInfo mobileInfo
            ,@ApiParam(value="商品编号",required = true) Integer goodsId

    ){
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
        if (goods==null){
            throw new ApiException(400,"商品不存在");
        }
        Integer dealType = goods.getDealType();
        if (dealType==null){
            dealType = 1;
        }
        GoodsQo qo = new GoodsQo();
        qo.setDealType(dealType);
        qo.setOrgId(goods.getOrgId());
        qo.setIsOnline(1);
        qo.setIsVerfiy(2);
        qo.setLimit(6);
        qo.setStartTotal(1);
        List<GoodsEx> listEx = goodsService.findListEx(qo);
        if (listEx.size()<6){
            qo.setDealType(dealType==1?2:1);
            qo.setLimit(6-listEx.size());
            List<GoodsEx> ex = goodsService.findListEx(qo);
            if (CollectionUtils.isNotEmpty(ex)){
                listEx.addAll(ex);
            }
        }
        List<AppStoreGoodsDetail> result = new ArrayList<AppStoreGoodsDetail>();
        for(GoodsEx ex : listEx){
            AppStoreGoodsDetail record = new AppStoreGoodsDetail();
            record.setId(ex.getId());
            record.setTitle(ex.getName());
            record.setImg(ex.getImg());
            record.setImages(ex.getImgs());
            record.setWidth(ex.getWidth());
            record.setHeight(ex.getHeight());
            record.setAuthPrice(ex.getPrice()+"");
            record.setPrice(ex.getPrice()+"");
            record.setOrgId(ex.getOrgId());
            record.setDealType(BaseUtils.getDefaultDealType(ex.getDealType()));
            record.setOrgIntegral(ex.getOrgIntegral());
            result.add(record);
        }
        return result;
    }


    /**
     * 认证商场热门列表
     */
    @ApiOperation(value = "认证商场热门列表", notes = "分页")
    @RequestMapping("/storeHotGoodsList")
    @ApiMethod()
    public List<AppStoreGoodsDetail> storeHotGoodsList(MobileInfo mobileInfo,
                                                       PageLimit pageLimit){
        startPage();
        List<AppStoreGoodsDetail> list2 = new ArrayList<AppStoreGoodsDetail>();
//        GoodsExample goodsExample = new GoodsExample();
//        //goodsExample.createCriteria().andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(3).andTotalGreaterThanOrEqualTo(1);
//
//        goodsExample.or().andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(3).andTypeEqualTo(1);
//        goodsExample.or().andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(2).andTypeEqualTo(1);
//        goodsExample.or().andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(4).andTypeEqualTo(1);
//        goodsExample.or().andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(6).andTypeEqualTo(1);
//        goodsExample.or().andStateEqualTo(1).andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andSourceEqualTo(7).andTypeEqualTo(1);
//        goodsExample.setOrderByClause("sort_order desc,is_suggest desc,create_time desc");
//        List<Goods> list = goodsService.selectByExample(goodsExample);

        GoodsQo qo = new GoodsQo();
        qo.setState(1);
        qo.setIsOnline(1);
        qo.setIsVerfiy(2);
        qo.setType(1);
        List<Integer> sources = Lists.newArrayList();
        sources.add(2);
        sources.add(3);
        sources.add(4);
        sources.add(6);
        sources.add(7);
        qo.setStartTotal(1);
        qo.setSources(sources);

        List<GoodsEx> list = goodsService.findListEx(qo);
        for(GoodsEx ex : list) {
            AppStoreGoodsDetail record = new AppStoreGoodsDetail();
            record.setId(ex.getId());
            record.setImg(ex.getImg());
            record.setImages(ex.getImgs());
            record.setWidth(ex.getWidth());
            record.setHeight(ex.getHeight());
            record.setTitle(ex.getName());
            record.setPrice(ex.getPrice() + "");
            record.setAuthPrice(ex.getPrice()+"");
            record.setSource(ex.getSource());
            record.setOrgId(ex.getOrgId());
            record.setOrgIntegral(ex.getOrgIntegral());
            record.setDealType(BaseUtils.getDefaultDealType(ex.getDealType()));
            record.setStartPrice(ex.getStartPrice());
            record.setGoodsAttribute(ex.getGoodsAttribute());
            record.setLabels(ex.getLabels());
            record.setAuctionStartTime(ex.getAuctionStartTime());
            record.setAuctionEndTime(ex.getAuctionEndTime());
            record.setRaisePriceRange(ex.getRaisePriceRange());
            record.setOnlineTime(ex.getOnlineTime());
            list2.add(record);
        }
        return list2;
    }

    @ApiOperation(value = "物品详情", notes = "不需要登录")
    @RequestMapping("/goodsDetail")
    @ApiMethod(isLogin = false)
    public AppStoreGoodsDetail goodsDetail(@ApiParam(value="id",required = true) Integer id,MobileInfo mobileInfo){
        AppStoreGoodsDetail appStoreGoodsDetail = new AppStoreGoodsDetail();
        Goods ex = goodsService.selectByPrimaryKey(id);
        appStoreGoodsDetail.setId(ex.getId());
        appStoreGoodsDetail.setImages(ex.getImgs());
        appStoreGoodsDetail.setTitle(ex.getName());
        appStoreGoodsDetail.setPrice(ex.getPrice()+"");
        appStoreGoodsDetail.setAuthPrice(ex.getCost()+"");
        appStoreGoodsDetail.setDeclare(ex.getInfo());
        appStoreGoodsDetail.setGoodsDescription(getPage(id.toString(),1));
        appStoreGoodsDetail.setSource(ex.getSource());
        appStoreGoodsDetail.setBannerVideoFace(ex.getBannerVideoFace());
        appStoreGoodsDetail.setBannerVideo(ex.getBannerVideo());
        appStoreGoodsDetail.setIntroduction(ex.getIntroduction());
        appStoreGoodsDetail.setGoodsAttribute(ex.getGoodsAttribute());
        appStoreGoodsDetail.setOnlineTime(ex.getOnlineTime());
        appStoreGoodsDetail.setAuctionStartTime(ex.getAuctionStartTime());
        appStoreGoodsDetail.setAuctionEndTime(ex.getAuctionEndTime());
        appStoreGoodsDetail.setStartPrice(ex.getStartPrice());
        appStoreGoodsDetail.setRaisePriceRange(ex.getRaisePriceRange());
        appStoreGoodsDetail.setLabels(ex.getLabels());
        appStoreGoodsDetail.setMaxAuction(ex.getMaxAuction());
        appStoreGoodsDetail.setCateCode(ex.getCateCode());
        if (mobileInfo!=null && mobileInfo.getUserId()!=null){
            appStoreGoodsDetail.setIsFollow(userFollowService.getIsFollow(mobileInfo.getUserId(),id,2));
        }else {
            appStoreGoodsDetail.setIsFollow(0);
        }
        PawnOrgEx pawnOrg=pawnOrgService.getInfo(ex.getOrgId());
        if (pawnOrg!=null){
            appStoreGoodsDetail.setOrgId(pawnOrg.getId());
            appStoreGoodsDetail.setOrgName(pawnOrg.getName());
            appStoreGoodsDetail.setOrgIntroduction(pawnOrg.getIntroduction());
            appStoreGoodsDetail.setOrgLogo(pawnOrg.getOrgLogo());
            appStoreGoodsDetail.setImg(ex.getImg());
            appStoreGoodsDetail.setOrgIntegral(pawnOrg.getIntegral());
        }


        if (ex.getDealType()!=null &&  ex.getDealType()==2 && ex.getAuctionOnlineLogId()!=null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("goods_id",id);
            map.put("onlineLogId",ex.getAuctionOnlineLogId());
            List<GoodsAuctionEx> goodsAuctionExes = goodsAuctionService.selectByAuctionUser(map);
            appStoreGoodsDetail.setGoodsAuctionList(goodsAuctionExes);
            appStoreGoodsDetail.setHotScore(goodsAuctionExes.size()*15);
        }

        return  appStoreGoodsDetail;
    }



        /**
         * 认证商场物品详情
         */
    @ApiOperation(value = "认证商场物品详情", notes = "不需要登录")
    @RequestMapping("/storeGoodsDetail")
    @ApiMethod(isLogin = false)
    public AppStoreGoodsDetail storeGoodsDetail(@ApiParam(value="id",required = true) Integer id){
        AppStoreGoodsDetail appStoreGoodsDetail = new AppStoreGoodsDetail();
        Goods ex = goodsService.selectByPrimaryKey(id);
        appStoreGoodsDetail.setId(ex.getId());
        appStoreGoodsDetail.setImages(ex.getImgs());
        appStoreGoodsDetail.setTitle(ex.getName());
        appStoreGoodsDetail.setPrice(ex.getPrice()+"");
        appStoreGoodsDetail.setAuthPrice(ex.getCost()+"");
        appStoreGoodsDetail.setDeclare(ex.getInfo());
        appStoreGoodsDetail.setGoodsDescription(getPage(id.toString(),1));
        appStoreGoodsDetail.setSource(ex.getSource());
        appStoreGoodsDetail.setBannerVideoFace(ex.getBannerVideoFace());
        appStoreGoodsDetail.setBannerVideo(ex.getBannerVideo());
        appStoreGoodsDetail.setIntroduction(ex.getIntroduction());
        appStoreGoodsDetail.setGoodsAttribute(ex.getGoodsAttribute());
        appStoreGoodsDetail.setOnlineTime(ex.getOnlineTime());
        appStoreGoodsDetail.setAuctionStartTime(ex.getAuctionStartTime());
        appStoreGoodsDetail.setAuctionEndTime(ex.getAuctionEndTime());
        appStoreGoodsDetail.setStartPrice(ex.getStartPrice());
        appStoreGoodsDetail.setRaisePriceRange(ex.getRaisePriceRange());
        appStoreGoodsDetail.setLabels(ex.getLabels());
        appStoreGoodsDetail.setDealType(BaseUtils.getDefaultDealType(ex.getDealType()));
        //机构上传
        if(ex.getOrgId()!=null){
            PawnOrg pawnOrg=pawnOrgService.selectByPrimaryKey(ex.getOrgId());
            appStoreGoodsDetail.setOrgId(pawnOrg.getId());
            appStoreGoodsDetail.setOrgName(pawnOrg.getName());
            appStoreGoodsDetail.setOrgIntroduction(pawnOrg.getIntroduction());
            appStoreGoodsDetail.setOrgLogo(pawnOrg.getOrgLogo());
            appStoreGoodsDetail.setImg(ex.getImg());
            appStoreGoodsDetail.setOrgIntegral(pawnOrg.getIntegral());
        }
//        //商家端(个人)
//        if(ex.getSource()==6){
//            AuthPersonalExample authPersonalExample = new AuthPersonalExample();
//            authPersonalExample.createCriteria().andCreateUserEqualTo(ex.getGoodsOwner());
//            List<AuthPersonal> authPersonalList = authPersonalService.selectByExample(authPersonalExample);
//            if(authPersonalList.size() == 1){
//                AuthPersonal authPersonal = authPersonalList.get(0);
//                appStoreGoodsDetail.setOrgName(authPersonal.getStoreName());
//                appStoreGoodsDetail.setOrgLogo(authPersonal.getLogo());
//                appStoreGoodsDetail.setOrgIntroduction(authPersonal.getStoreIntroduce());
//            }
//        }
//        //商家端(企业)
//        if(ex.getSource()==7){
//            AuthEnterpriseExample authEnterpriseExample = new AuthEnterpriseExample();
//            authEnterpriseExample.createCriteria().andCreateUserEqualTo(ex.getGoodsOwner());
//            List<AuthEnterprise> authEnterpriseList = authEnterpriseService.selectByExample(authEnterpriseExample);
//            if(authEnterpriseList.size() == 1){
//                AuthEnterprise authEnterprise = authEnterpriseList.get(0);
//                appStoreGoodsDetail.setOrgName(authEnterprise.getStoreName());
//                appStoreGoodsDetail.setOrgLogo(authEnterprise.getLogo());
//                appStoreGoodsDetail.setOrgIntroduction(authEnterprise.getStoreIntroduce());
//            }
//        }
        appStoreGoodsDetail.setCateCode(ex.getCateCode());
        appStoreGoodsDetail.setCateCodeSon(ex.getCateCodeSon());
        appStoreGoodsDetail.setWeight(ex.getWeight());
        appStoreGoodsDetail.setMaterial(ex.getMaterial());
        appStoreGoodsDetail.setMainMaterial(ex.getMainMaterial());
        appStoreGoodsDetail.setOtherMaterial(ex.getOtherMaterial());
        appStoreGoodsDetail.setCcAll(ex.getCcAll());
        appStoreGoodsDetail.setCreateYear(ex.getCreateYear());
        appStoreGoodsDetail.setBrand(ex.getBrand());
        appStoreGoodsDetail.setNewPercent(ex.getNewPercent());
        appStoreGoodsDetail.setTheme(ex.getTheme());
        appStoreGoodsDetail.setStyle(ex.getStyle());
        appStoreGoodsDetail.setMaterialName(ex.getMaterialName());
        appStoreGoodsDetail.setWordDescript(ex.getWordDescript());
        appStoreGoodsDetail.setReasonOfDismounting(ex.getReasonOfDismounting());
        return appStoreGoodsDetail;
    }

    /**
     *认证商场购买商品优惠券列表
     * @param id
     * @return
     */
    @ApiOperation(value = "认证商场购买商品选择优惠券", notes = "不需要登录")
    @RequestMapping("/userStroeCouponList")
    @ApiMethod(isLogin = true)
    public List<AppUserCoupon> userStroeCouponList(MobileInfo mobileInfo,
                                                   @ApiParam(value="商品id",required = true) Integer id){
        Goods goods = goodsService.selectByPrimaryKey(id);
        //商品售价
        BigDecimal price = goods.getPrice();
        UserCouponExample userCouponExample = new UserCouponExample();
        userCouponExample.createCriteria().andUserIdEqualTo(mobileInfo.getUserId()).andFullLessThanOrEqualTo(price).andEndTimeGreaterThanOrEqualTo(new Date()).andStateEqualTo(1);
        List<UserCoupon> list = userCouponService.selectByExample(userCouponExample);
        List<AppUserCoupon> list2 = new ArrayList<AppUserCoupon>();
        for(UserCoupon ex : list){
            AppUserCoupon record = new AppUserCoupon();
            record.setId(ex.getId());
            //record.setFull(ex.getFull()+"");
            record.setCouponId(ex.getId());
            record.setValue(ex.getFull()+"");
            list2.add(record);
        }
        return list2;
    }

    /**
     * 绝当商城轮播图
     */
    @ApiOperation(value = "绝当商城轮播图", notes = "分页")
    @RequestMapping("/jdGoodsBanner")
    @ApiMethod(isPage = false)
    public List<AppStoreBanner> jdGoodsList(MobileInfo mobileInfo){
        List<AppStoreBanner> ret = new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("location",3);
        List<AdEx> list = adService.selectRzList(map);
        for(AdEx ex : list){
            AppStoreBanner record = new AppStoreBanner();
            record.setId(ex.getId());
            record.setUrl(ex.getImg());
            record.setType(ex.getType());
            if(0 == ex.getType()){
                record.setContent("");
            }else if(1 == ex.getType()){
                record.setContent(getPage(ex.getId().toString(),10));
                record.setContent(ex.getContent());
            }else if(2 == ex.getType()){
                record.setContent(getPage(ex.getId().toString(),10));
            }else if(3 == ex.getType()){
                record.setContent(ex.getContent());
            }else if(4 == ex.getType()){
                record.setContent(ex.getContent());
            }else if(5 == ex.getType()){
                VideoOnlineExample example = new VideoOnlineExample();
                example.createCriteria().andIdEqualTo(Integer.parseInt(ex.getContent()));
                String video= videoOnlineService.selectByExample(example).get(0).getVideo();
//                BFileExample example1 = new BFileExample();
//                example1.createCriteria().andFileIdEqualTo(video);
//                String file_path = fileService.selectByExample(example1).get(0).getFilePath();
                record.setContent(video);
            }
            if(4 == ex.getType()){
                //如果是绝当商品的商品
                if(null == ex.getGoodsId()){
                    //如果是后台上传的,则不区分30000
                    record.setState(2);
                }else{
                    if(new BigDecimal(ex.getCost()).compareTo(new BigDecimal(30000) )== -1){
                        //走流程小于三万则是普通商品
                        record.setState(2);
                    }else{
                        record.setState(1);
                    }
                }
            }
            ret.add(record);
        }
        return ret;
    }

    /**
     * 绝当商场商家名称
     */
    @ApiOperation(value = "绝当商城商家名称", notes = "分页")
    @RequestMapping("/orgName")
    @ApiMethod(isPage = true)
    public List<AppOrgName> orgName(MobileInfo mobileInfo,
                                    PageLimit pageLimit){
        List<AppOrgName> ret = new ArrayList<AppOrgName>();
        startPage();
        PawnOrgExample pawnOrgExample = new PawnOrgExample();
        pawnOrgExample.createCriteria().andTypeEqualTo(1);
        pawnOrgExample.setOrderByClause("create_time desc");
        List<PawnOrg> list = pawnOrgService.selectByExample(pawnOrgExample);
        for(PawnOrg ex : list){
            AppOrgName appOrgName = new AppOrgName();
            appOrgName.setOrgId(ex.getId());
            appOrgName.setOrgName(ex.getName());
            ret.add(appOrgName);
        }
        return ret;
    }


    /**
     * 绝当商城列表
     */
    @ApiOperation(value = "绝当商城列表", notes = "不需要登陆")
    @RequestMapping("/storeJDGoodsList")
    @ApiMethod()
    public List<AppStoreGoodsDetail> storeJDGoodsList(MobileInfo mobileInfo,
                                                      PageLimit pageLimit,
                                                      @ApiParam(value="时间排序 0升序 1降序",required = false) String timeUp,
                                                      @ApiParam(value="价格 0升序 1降序",required = false) String priceUp,
                                                      @ApiParam(value="商铺id",required = false) String orgId){
        List<AppStoreGoodsDetail> ret = new ArrayList<AppStoreGoodsDetail>();
        startPage();
        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria goodsExample = example.createCriteria().andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andTotalGreaterThanOrEqualTo(1).andTypeEqualTo(2).andSourceEqualTo(2);
        //goodsExample.andSourceEqualTo(3);
        if(StringUtil.isNotBlank(orgId)){
            goodsExample.andOrgIdEqualTo(Integer.parseInt(orgId));
        }

        if(StringUtil.isBlank(timeUp)){
            timeUp = "";
        }

        if(StringUtil.isBlank(priceUp)){
            priceUp = "";
        }

        if(StringUtil.isBlank(orgId)){
            orgId = "";
        }

        if(timeUp.equals("") && priceUp.equals("")){
            example.setOrderByClause("sort_order desc,create_time desc,price desc");
        }
        if(timeUp.equals("") && !priceUp.equals("")){
            //0降序，1升序，-1默认降序
            if("1".equals(priceUp)){
                example.setOrderByClause("price asc");
            }else{
                example.setOrderByClause("price desc");
            }
        }
        if(priceUp.equals("") && !timeUp.equals("")){
            if("1".equals(timeUp)){
                example.setOrderByClause("create_time asc");
            }else{
                example.setOrderByClause("create_time desc");
            }
        }
        if(!timeUp.equals("") && !priceUp.equals("")){
            if("0".equals(priceUp) && "0".equals(timeUp)){
                example.setOrderByClause("create_time desc,price desc");
            }else if("0".equals(priceUp) && "1".equals(timeUp)){
                example.setOrderByClause("create_time asc,price desc");
            }else if("1".equals(priceUp) && "0".equals(timeUp)){
                example.setOrderByClause("price asc,create_time desc");
            }else if("1".equals(priceUp) && "1".equals(timeUp)){
                example.setOrderByClause("create_time asc,price asc");
            }else{//默认时间降序，价格降序
                example.setOrderByClause("sort_order desc,create_time desc,price desc");
            }
        }






        List<Goods> goodsList = goodsService.selectByExample(example);
        for(Goods ex : goodsList){
            AppStoreGoodsDetail c = new AppStoreGoodsDetail();
            c.setSource(ex.getSource());
            c.setOrgId(ex.getOrgId());
            c.setDealType(BaseUtils.getDefaultDealType(ex.getDealType()));
            c.setStartPrice(ex.getStartPrice());
            c.setGoodsAttribute(ex.getGoodsAttribute());
            c.setLabels(ex.getLabels());
            c.setAuctionStartTime(ex.getAuctionStartTime());
            c.setAuctionEndTime(ex.getAuctionEndTime());
            c.setRaisePriceRange(ex.getRaisePriceRange());
            c.setOnlineTime(ex.getOnlineTime());
            c.setMaxAuction(ex.getMaxAuction());
                if(ex.getPrice().compareTo(new BigDecimal("30000")) == -1 || null == ex.getGoodsId()){
                    //普通绝当商品
                    c.setType(0);
                    c.setId(ex.getId());
                    c.setTitle(ex.getName());
                    c.setImg(ex.getImg());
                    c.setImages(ex.getImgs());
                    c.setWidth(ex.getWidth());
                    c.setHeight(ex.getHeight());
                    c.setPrice(ex.getPrice()+"");
                    c.setAuthPrice(ex.getPrice()+"");
                }else{
                    //竞拍的商品
                    long second = DateUtil.secondsAfter(DateUtil.addMinute(ex.getCreateTime(),(PaidangConst.JD_GOODS_TIME)/60),new Date());
                    if(second > 0){
                        //正在竞拍的商品
                        c.setType(1);
                        c.setId(ex.getId());
                        c.setTitle(ex.getName());
                        c.setImg(ex.getImg());
                        c.setImages(ex.getImgs());
                        c.setWidth(ex.getWidth());
                        c.setHeight(ex.getHeight());
                        c.setPrice(ex.getMaxAuction() == null? ex.getPrice()+"" : ex.getMaxAuction()+"");
                        c.setAuthPrice(ex.getPrice()+"");
                        //查找该物品的竞拍次数
                        GoodsAuctionExample goodsAuctionExample = new GoodsAuctionExample();
                        goodsAuctionExample.createCriteria().andGoodsIdEqualTo(ex.getId());
                        int count = goodsAuctionService.countByExample(goodsAuctionExample);
                        c.setCount(count);
                    }else{
                        //超过时间不显示，并且修改他为竞拍失效
                        ex.setState(0);
                        goodsService.updateByPrimaryKeySelective(ex);
                        continue;
                    }
            }

            ret.add(c);
        }
        return ret;
    }

    @ApiOperation(value = "绝当商城竞拍出价", notes = "登陆")
    @RequestMapping("/autionBid")
    @ApiMethod(isLogin = true)
    public void auctionBid(MobileInfo mobileInfo,
                              @ApiParam(value="商品id",required = true) Integer id,
                              @ApiParam(value="出价",required = true) BigDecimal price){
        if (redisCache.exists("auctionBid:"+id)){
            throw new ApiException(400,"请求频繁，请稍后");
        }
        redisCache.expire("auctionBid:"+id,6);
        Goods goods = goodsService.selectByPrimaryKey(id);
        BaseUtils.check(goods==null,"商品不存在");

        BaseUtils.check(goods.getDealType()!=2,"该商品不允许竞拍");

        Date date = new Date();

        BaseUtils.check(date.compareTo(goods.getAuctionEndTime())>=0,"竞拍已结束");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goods_id",id);
        BigDecimal maxPrice = goodsAuctionService.selectMaxPrice(map);
        BigDecimal raise = BigDecimal.ZERO;
        if (maxPrice == null){
            raise = price.subtract(goods.getStartPrice());

        }else {
            raise = price.subtract(maxPrice);
        }
        BaseUtils.check(raise.compareTo(BigDecimal.ZERO)<=0,"出价不能小于起拍价");
        BigDecimal divide = raise.divide(goods.getRaisePriceRange(), 0, BigDecimal.ROUND_HALF_UP);
        BaseUtils.check(new BigDecimal(divide.intValue()).compareTo(divide)!=0,"出价必须是加价幅度的倍数");
        BaseUtils.check(divide.intValue()>2 || divide.intValue()<1,"出价必须是加价幅度的1-2倍");


        //插入竞拍表 goods_auction
        GoodsAuction c = new GoodsAuction();
        c.setGoodsId(id);
        c.setOnlineLogId(goods.getAuctionOnlineLogId());
        c.setUserId(mobileInfo.getUserId());
        c.setPrice(price);
        c.setCreateTime(new Date());
        int result = goodsAuctionService.insert(c);
        if(result == 0){
            throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
        }
        //更新商品表的最新消息
        goods.setMaxAutionId(c.getId());
        goods.setMaxAuction(price);
        goods.setModifyTime(new Date());
        goods.setUserId(mobileInfo.getUserId());
        int result2 = goodsService.updateByPrimaryKey(goods);
        if(result2 == 0){
            throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
        }
    }

    /**
     * 绝当商城竞拍--出价
     * @param mobileInfo
     * @return
     */
//    @ApiOperation(value = "绝当商城竞拍出价", notes = "登陆")
//    @RequestMapping("/storeJDGoodsJp")
//    @ApiMethod(isLogin = true)
//    public Ret storeJDGoodsJp(MobileInfo mobileInfo,
//                              @ApiParam(value="id",required = true) Integer id,
//                              @ApiParam(value="出价",required = true) String price)throws Exception{
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("goods_id",id);
//
//        Goods goods = goodsService.selectByPrimaryKey(id);
//        //判断是否超时
//        int second = DateUtil.secondsAfter(DateUtil.addMinute(goods.getCreateTime(),(PaidangConst.JD_GOODS_TIME)/60),new Date());
//        if(second < 0){
//            throw new ApiException(MEnumError.OPER_FAILURE_ERROE);
//        }
//
//        BigDecimal maxPrice = goodsAuctionService.selectMaxPrice(map);
//        if(null != maxPrice){
//            //没人出价，第一次出价
//            if(new BigDecimal(price).compareTo(maxPrice) != 1){
//                //如果出价小于等于最大价格，抛异常
//                throw new ApiException(MEnumError.MAX_PRICE_ERROR);
//            }
//        }
//
//
//        //插入竞拍表 goods_auction
//        GoodsAuction c = new GoodsAuction();
//        c.setGoodsId(id);
//        c.setUserId(mobileInfo.getUserId());
//        c.setPrice(new BigDecimal(price));
//        int result = goodsAuctionService.insert(c);
//        if(result == 0){
//            throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
//        }
//         //更新商品表的最新消息
//        goods.setMaxAutionId(c.getId());
//        goods.setMaxAuction(new BigDecimal(price));
//        goods.setUserId(mobileInfo.getUserId());
//        int result2 = goodsService.updateByPrimaryKey(goods);
//        if(result2 == 0){
//            throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
//        }
//        return ok();
//    }

    /**
     * 绝当商城详情
     * @param mobileInfo
     * @return
     */
    @ApiOperation(value = "绝当商城详情", notes = "登陆")
    @RequestMapping("/storeJDGoodsDetail")
    @ApiMethod(isLogin = false)
    public AppJdGoods storeJDGoodsDetail(MobileInfo mobileInfo,
                                         @ApiParam(value="id",required = true) Integer id)throws Exception{
        AppJdGoods c = new AppJdGoods();
        Goods goods = goodsService.selectByPrimaryKey(id);
        if (goods==null){
            throw new ApiException(-1,"商品不存在");
        }
        PawnOrg pawnOrg=pawnOrgService.selectByPrimaryKey(goods.getOrgId());
//        GoodsAuctionExample goodsAuctionExample = new GoodsAuctionExample();
//        goodsAuctionExample.createCriteria().andGoodsIdEqualTo(id);
//        goodsAuctionExample.setOrderByClause("price asc,create_time desc");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goods_id",id);
        //选出最高价
        BigDecimal maxPirce = null;
        List<GoodsAuctionEx> list = goodsAuctionService.selectByAuctionUser(map);
        c.setSource(goods.getSource());
        c.setId(goods.getId());
        c.setImages(goods.getImgs());
        c.setGoodsName(goods.getName());
        c.setAuthPrice(goods.getCost()+"");
        c.setPrice(goods.getPrice()+"");
        c.setDeclare(goods.getInfo());
        if(0 != list.size()){
            if(list.size()>1){
                for(int i = 1;i<list.size();i++){
                    maxPirce = list.get(0).getPrice();
                    if(list.get(i-1).getPrice().compareTo(list.get(i).getPrice()) == -1){
                        maxPirce = list.get(i).getPrice();
                    }
                }
            }else{
                maxPirce = list.get(0).getPrice();
            }
        }
        c.setPrice(maxPirce==null? goods.getPrice()+"":maxPirce+"");
        long second = DateUtil.secondsAfter(DateUtil.addMinute(goods.getCreateTime(),(PaidangConst.JD_GOODS_TIME)/60),new Date());
        c.setTime(second+"");
        c.setGoodsAuctionList(list);
        c.setContent(getPage(id.toString(),1));
        c.setOrgId(pawnOrg.getId());
        c.setOrgName(pawnOrg.getName());
        c.setOrgIntroduction(pawnOrg.getIntroduction());
        c.setOrgLogo(pawnOrg.getOrgLogo());
        c.setImg(goods.getImg());
        return c;
    }

    /**
     * 首页下的视频列表
     */
    @ApiOperation(value = "首页下的视频列表", notes = "不需要登陆")
    @RequestMapping("/getVideoForIndex")
    @ApiMethod()
    public List<AppVideoOnline> getVideoForIndex(MobileInfo mobileInfo){
        List<VideoOnlineEx> list = videoOnlineService.selectByComment(mobileInfo.getUserId(),1,null);

        List<AppVideoOnline> ret = new ArrayList<AppVideoOnline>();
        for(VideoOnlineEx ex : list){
            AppVideoOnline record = new AppVideoOnline();
            record.setId(ex.getId());
            record.setTitle(ex.getTitle());
            record.setImg(ex.getImg());
            record.setVideo(ex.getVideo()+ PaidangConst.VIDEO_NORMAL);
            record.setComCnt(ex.getCommCount());
            record.setState(ex.getState());
            record.setViewCnt(ex.getViewCnt());
            record.setLabels(ex.getLabels());
            record.setPraiseCount(ex.getPraiseNum());
            record.setPraiseStatus(ex.getPraiseStatus());
            ret.add(record);
        }
        return ret;
    }

    @ApiOperation(value="首页下的列表 ", notes = "不登录")
    @RequestMapping("/getIndexMenu")
    @ApiMethod(isPage = false, isLogin = false)
    public List<ApiIndexMenu> getIndexMenu(MobileInfo mobileInfo){
        List<ApiIndexMenu> ret = new ArrayList<ApiIndexMenu>();
        GoodsExample example = new GoodsExample();
        example.createCriteria().andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andTotalGreaterThanOrEqualTo(1).andIsSuggestEqualTo(1);
        example.setOrderByClause("create_time desc");
        List<Goods> goodsList = goodsService.selectByExample(example);
        int cnt = 1;
        int cnt2 = 1;
        for(Goods ex : goodsList){
            ApiIndexMenu c = new ApiIndexMenu();
            c.setOrgId(ex.getOrgId());
            if(3 == ex.getSource()||4 == ex.getSource()){
                if(cnt <= 6){
                    //如果是认证商场商品
                    c.setId(ex.getId());
                    c.setPrice(ex.getPrice()+"");
                    c.setImg(ex.getImg());
                    c.setTitle(ex.getName());
                    c.setState(1);
                    c.setGoodsType(MStoreGoodsCateList.getByCode(ex.getCateCode().toString()).getName());
                    cnt++;
                }else{
                    continue;
                }

            }else if(2 == ex.getSource()){
                //如果是绝当商场商品
                if(ex.getPrice().compareTo(new BigDecimal(30000)) == -1){

                    if(cnt2 <= 4){
                        //如果他的价格不满三万则是最新绝当商品
                        c.setId(ex.getId());
                        c.setImg(ex.getImg());
                        c.setPrice(ex.getPrice()+"");
                        c.setTitle(ex.getName());
                        c.setState(2);
                        c.setGoodsType(MStoreGoodsCateList.getByCode(ex.getCateCode().toString()).getName());
                        cnt2++;
                    }else{
                        continue;
                    }
                }else{
                    if(cnt2 <= 4){
                        //价格超过三万则是最新绝当竞拍
                        long second = com.base.util.DateUtil.secondsAfter(com.base.util.DateUtil.addMinute(ex.getCreateTime(),(PaidangConst.JD_GOODS_TIME)/60),new Date());
                        if(second > 0){
                            c.setId(ex.getId());
                            c.setEndTime(com.base.util.DateUtil.dateToStr(com.base.util.DateUtil.addHour(ex.getCreateTime(),24)));
                            c.setEndTime2(second+"");
                            c.setImg(ex.getImg());
                            c.setTitle(ex.getName());
                            c.setPrice(ex.getPrice()+"");
                            //查找该物品的竞拍次数
                            GoodsAuctionExample goodsAuctionExample = new GoodsAuctionExample();
                            goodsAuctionExample.createCriteria().andGoodsIdEqualTo(ex.getId());
                            int count = goodsAuctionService.countByExample(goodsAuctionExample);
                            c.setAucCount(count);
                            c.setState(3);
                            c.setGoodsType(MStoreGoodsCateList.getByCode(ex.getCateCode().toString()).getName());
                            cnt2++;
                        }else{
                            //超过时间不显示，并且修改他为竞拍失效
                            ex.setState(0);
                            goodsService.updateByPrimaryKeySelective(ex);
                            continue;
                        }
                    }else{
                        continue;
                    }

                }
            }
            ret.add(c);
        }
        return ret;

    }


    /**
     * 商品列表
     *  6个人7企业  臻品
     * 	 * 2 绝当品
     * 	 *  3   自营
     */
    @ApiOperation(value = "商品列表", notes = "分页")
    @RequestMapping("/goods/list")
    @ApiMethod(isLogin = false,isPage = true)
    public List<AppStoreGoodsDetail> goodsList(MobileInfo mobileInfo,
                                                    @ApiParam(value="商品名称",required = true) String name,
                                                    PageLimit pageLimit){
        startPage();
        List<AppStoreGoodsDetail> list2 = new ArrayList<AppStoreGoodsDetail>();

        GoodsQo qo = new GoodsQo();
        qo.setStartTotal(1);
        qo.setState(1);
        qo.setIsOnline(1);
        qo.setIsVerfiy(2);
        qo.setType(1);
        List<Integer> sources = Lists.newArrayList();
        sources.add(2);
        sources.add(3);
        sources.add(1);
        sources.add(4);
        sources.add(6);
        sources.add(7);
        qo.setSources(sources);
        qo.setName(name);

        List<GoodsEx> list = goodsService.findListEx(qo);

//        List<Goods> list = goodsService.selectByExample(goodsExample);
        for(GoodsEx ex : list){
            AppStoreGoodsDetail record = new AppStoreGoodsDetail();
            record.setId(ex.getId());
            record.setTitle(ex.getName());
            record.setOrgName(ex.getOrgName());
            record.setImg(ex.getImg());
            record.setImages(ex.getImgs());
            record.setWidth(ex.getWidth());
            record.setHeight(ex.getHeight());
            record.setAuthPrice(ex.getPrice()+"");
            record.setPrice(ex.getPrice()+"");
            record.setOrgId(ex.getOrgId());
            record.setDealType(BaseUtils.getDefaultDealType(ex.getDealType()));
            record.setOrgIntegral(ex.getOrgIntegral());
            record.setGoodsSource(ex.getSource());
            list2.add(record);
        }
        return list2;
    }

    /**
     * 店铺列表
     */
    @ApiOperation(value = "店铺列表", notes = "分页")
    @RequestMapping("/org/list")
    @ApiMethod(isLogin = false,isPage = true)
    public List<PawnOrgVo> orgList(MobileInfo mobileInfo,
                                               @ApiParam(value="店铺名称",required = true) String name,
                                               PageLimit pageLimit){
        startPage();
        PawnOrgExample example = new PawnOrgExample();
        example.createCriteria().andNameLike("%"+name+"%").andStateEqualTo(1).andIdNotEqualTo(1);
        List<PawnOrg> pawnOrgs = pawnOrgService.selectByExample(example);
        List<PawnOrgVo> result = new ArrayList<>();
        for (PawnOrg pawnOrg : pawnOrgs) {
            PawnOrgVo vo = new PawnOrgVo();
            BeanUtils.copyProperties(pawnOrg,vo);
            result.add(vo);
        }

        return result;
    }

    @ApiOperation(value="视频搜索 ", notes = "不登录")
    @RequestMapping("/searchVideo")
    @ApiMethod(isPage = true, isLogin = false)
    public Object searchIndexVideo(@ApiParam(value="name",required = true)String name){
        startPage();
        VideoOnlineExample example=new VideoOnlineExample();
        example.createCriteria().andTitleLike("%"+name+"%");
        example.setOrderByClause("create_time desc");
        return videoOnlineService.selectByExample(example);
    }


    @ApiOperation(value = "关注(取消关注)商品", notes = "登陆")
    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer goodsFollow(MobileInfo mobileInfo,
                              @RequestParam @ApiParam(value = "目标商品id", required = true)Integer goodsId,
                              @RequestParam @ApiParam(value = "操作类型0取消关注1关注", required = true)Integer type){

        //获取关注状态
        UserFollowExample example=new UserFollowExample();
        example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId()).andFollowIdEqualTo(goodsId).andTypeEqualTo(2);
        UserFollow follow=new UserFollow();
        if (type==0){
            //取消关注
            int num = userFollowService.deleteByExample(example);
            return 0;
        }else if (type==1){

            List<UserFollow> list =  userFollowService.selectByExample(example);
            if (org.springframework.util.CollectionUtils.isEmpty(list)){
                follow.setUserId(mobileInfo.getUserId());
                follow.setFollowId(goodsId);
                follow.setCreateTime(new Date());
                follow.setStatus(1);
                follow.setType(2);
                int num = userFollowService.insert(follow);
                return 1;
            }

            return 1;
        }else {
            throw new ApiException("参数非法");
        }
    }


    @ApiOperation(value = "举报商品", notes = "登陆")
    @RequestMapping(value = "/report",method = {RequestMethod.POST})
    @ApiMethod(isLogin = true)
    public Integer report(MobileInfo mobileInfo,@RequestParam @ApiParam(value = "商品id", required = true)Integer goodsId,
                          @RequestParam @ApiParam(value = "举报信息", required = true)String info,
//    @RequestParam @ApiParam(value = "图片", required = true)String imgs,
                          @RequestParam @ApiParam(value = "昵称", required = true)String nickName
    ){
        UserReport report=new UserReport();
        report.setTargetId(goodsId);
        report.setType(2);
        report.setNickName(nickName);
        report.setCreateTime(new Date());
        report.setStatus(1);
        report.setInfo(info);
        report.setUserId(mobileInfo.getUserId());
        report.setCreateAccount(mobileInfo.getUserId().toString());
        return  userReportService.insert(report);
    }

}
