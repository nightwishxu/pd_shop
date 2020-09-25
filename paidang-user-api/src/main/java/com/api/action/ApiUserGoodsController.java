package com.api.action;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.api.MEnumError;
import com.api.MErrorEnum;
import com.api.MPawnMsg;
import com.api.MPostExpressAddress;
import com.api.service.ApiUserGoodsService;
import com.api.util.PageLimit;
import com.api.view.indexInfo.ApiIndexHotMenu;
import com.api.view.indexInfo.ApiIndexMenu;
import com.api.view.indexInfo.IndexInfo;
import com.api.view.myGoods.AppLogisticsDetail;
import com.api.view.myGoods.AppMyGoods;
import com.api.view.pay.PayResult;
import com.api.view.user.AppBXAddress;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.service.SensitivWordsService;
import com.base.util.CoreConstants;
import com.base.util.DateUtil;
import com.base.util.JSONUtils;
import com.base.util.StringUtil;
import com.item.dao.model.*;
import com.item.daoEx.model.AdEx;
import com.item.service.AdService;
import com.item.service.CodeService;
import com.item.service.FocusService;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.CommentEx;
import com.paidang.daoEx.model.GoodsEx;
import com.paidang.daoEx.model.PawnOrgEx;
import com.paidang.daoEx.model.UserGoodsEx;
import com.paidang.service.*;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.redis.RedisCache;
import com.util.PaidangConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/api/userGoods", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
@Api(tags = "用户藏品(用户端)")
public class ApiUserGoodsController extends ApiBaseController {

    @Autowired
    private UserGoodsService userGoodsService;
    @Autowired
    private PawnCateService pawnCateService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private UserService userService;
    @Autowired
    private DiamondService diamondService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private UserBankCardService userBankCardService;
    @Autowired
    private PlatformGoodsBuyService platformGoodsBuyService;
    @Autowired
    private ExpressService expressService;
    @Autowired
    private FocusService focusService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsAuctionService goodsAuctionService;
    @Autowired
    private UserBalanceLogService userBalanceLogService;
    @Autowired
    private AdService adService;
    @Autowired
    private BFileService fileService;
    @Autowired
    private VideoOnlineService videoOnlineService;
    @Autowired
    private SensitivWordsService sensitivWordsService;

    @Autowired
    private CommentService commentService;
    @Autowired
    private CollectPraiseService collectPraiseService;
    @Autowired
    private ApiUserGoodsService apiUserGoodsService;
    @Autowired
    private CommentReplyService commentReplyService;
    @Autowired
    private PawnOrgService pawnOrgService;
    @Autowired
    private ExperterInfoService experterInfoService;

    @Autowired
    private RedisCache redisCache;

    public enum MGoodsCateList {
        SCPZB("1","奢侈品珠宝"),
        sb("2","手表"),
        zs("3","钻石"),
        gjs("4","贵金属"),
        fcys("5","翡翠玉石"),
        hty("6","和田玉"),
        qt("7","其他"),
        csbs("8","彩色宝石");
        private String code;
        private String name;
        private MGoodsCateList(String code,String name) {
            this.code = code;
            this.name = name;
        }

        public static String getNameByCode(String code){
            if (StringUtil.isBlank(code)){
                return null;
            }
            switch (code){
                case "1":
                    return SCPZB.name;
                case "2":
                    return sb.name;
                case "3":
                    return zs.name;
                case "4":
                    return gjs.name;
                case "5":
                    return fcys.name;
                case "6":
                    return hty.name;
                case "7":
                    return qt.name;
                case "8":
                    return csbs.name;
                default:
                    return null;
            }

        }
    }

    @ApiOperation(value = "寄拍商品评价列表", notes = "登陆")
    @RequestMapping(value = "/userGoodsCommentList", method = RequestMethod.POST)
    @ApiMethod(isLogin = false)
    public  Object userGoodsCommentList(MobileInfo mobileInfo, @ApiParam(value = "商品id", required = true)Integer userGoodsId){
        CommentEx commentEx=new CommentEx();
        commentEx.setTopicId(userGoodsId);
        List<CommentEx> list=commentService.findList(commentEx);
        return list;
    }

    @ApiOperation(value="获取寄卖商品信息 ", notes = "登录")
    @RequestMapping("/getSell")
    @ApiMethod(isPage = false, isLogin = true)
    public String getSell(MobileInfo mobileInfo, @ApiParam(value="id",required = true)Integer id){
        UserGoods userGoods=userGoodsService.selectByPrimaryKey(id);
        return JSONUtils.serialize(userGoods);
    }

    @ApiOperation(value="编辑 ", notes = "登录")
    @RequestMapping("/updateSell")
    @ApiMethod(isPage = false, isLogin = true)
    public Object updateSell(MobileInfo mobileInfo, @ApiParam(value="id",required = true)Integer id
            , @ApiParam(value="寄拍图片以,分割",required = true)String  sellImgs
            , @ApiParam(value="寄拍视频以,分隔",required = false)String  sellVideo
            , @ApiParam(value="寄拍信息",required = true)String sellInfo
            , @ApiParam(value="一口价",required = true)BigDecimal sellPrice
            , @ApiParam(value="类别code",required = true)String sellPawnCode){
        UserGoods userGoods=userGoodsService.selectByPrimaryKey(id);
        if (userGoods==null){
            throw new ApiException(1100,"该商品不存在！");
        }
        if (userGoods.getIsSell()==0){
            throw new ApiException(1100,"该商品未寄拍请先寄拍！");
        }
        if (userGoods.getSellStatus()==2){
            throw new ApiException(1100,"该商品已售出！");
        }
        if (userGoods.getSellStatus()==1){
            throw new ApiException(1100,"请先下架该商品再编辑！");
        }
        if (StringUtils.isBlank(sellPawnCode)){
            throw new ApiException(1100,"类别不能为空！");
        }

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        Date date=new Date();

        UserGoods entity=new UserGoods();
//        if (Integer.valueOf(jedisTemplate.get("onLineUserGoods:"+sdf.format(userGoods.getSellStartTime())+id))>3){
//            throw new ApiException("上架后只能修改三次价格！");
//        }t
        if (userGoods.getSellStartTime()!=null){
            String key="onLineUserGoods:"+sdf.format(userGoods.getSellStartTime())+id;
            if ( redisCache.exists(key) && (Integer)redisCache.getCacheObject(key)>3){
                throw new ApiException(1100,"上架后只能修改三次价格！");
            }
            if (userGoods.getSellEndTime()!=null && date.compareTo(userGoods.getSellEndTime())<=0 && sellPrice.compareTo(userGoods.getSellPrice())!=0){
                redisCache.redisTemplate.opsForValue().increment(key,1);
            }
        }
        if(StringUtil.isBlank(sellVideo)){
            sellVideo = "";
        }
        entity.setId(id);
        entity.setSellImgs(sellImgs);
        entity.setSellVideo(sellVideo);
        entity.setSellInfo(sellInfo);
        entity.setSellPrice(sellPrice);
        entity.setSellCheck(3);//默认值3 无实际意义
        entity.setSellPawnCode(sellPawnCode);
        return userGoodsService.updateByPrimaryKeySelective(entity);
    }

    @ApiOperation(value="上架下架 ", notes = "登录")
    @RequestMapping("/updateOnline")
    @ApiMethod(isPage = false, isLogin = true)
    public Object updateOnline(MobileInfo mobileInfo, @ApiParam(value="id",required = true)Integer id
            , @ApiParam(value="sellStatus 0未上架，1上架",required = true)Integer sellStatus){
        UserGoods userGoods=userGoodsService.selectByPrimaryKey(id);
        if (userGoods.getIsSell()==0){
            throw new ApiException(1100,"该商品未寄拍请先寄拍！");
        }
        if (userGoods.getSellStatus()==2){
            throw new ApiException(1100,"该商品已售出！");
        }
        if (sellStatus==1 && (StringUtil.isBlank(userGoods.getSellInfo()) || userGoods.getSellPrice() == null || StringUtil.isBlank(userGoods.getSellImgs()))){
            throw new ApiException(1100,"上架前请先完善商品寄拍信息！");
        }
        if (userGoods.getSellCheck()==2){
            throw new ApiException(1100,"寄卖上架审核中，请等待寄卖审核完成！");
        }
        Date date=new Date();
        UserGoods entity=new UserGoods();
        //用户点击上架，后台进行审核
        if(sellStatus==1){
            entity.setSellCheck(2);
            entity.setSellStatus(0); //
        }else{
            entity.setSellStatus(sellStatus);
        }
        entity.setId(id);
//        entity.setSellStatus(sellStatus);
        //上架的话初次设置当前时间为上架时间或者上次上架已结束
        if (sellStatus==1 && (userGoods.getSellStartTime()==null || userGoods.getSellEndTime()==null || date.compareTo(userGoods.getSellEndTime())>0)){
            entity.setSellStartTime(date);
            entity.setSellEndTime(DateUtil.addMonth(date,1));
        }
        //下架的话，清除上次的上架时间
        if(sellStatus == 0){
            entity.setSellStartTime(null);
        }
        return userGoodsService.updateByPrimaryKeySelective(entity);
    }

    @ApiOperation(value="寄拍 ", notes = "登录")
    @RequestMapping("/sellUserGoods")
    @ApiMethod(isPage = false, isLogin = true)
    public Object sellUserGoods(MobileInfo mobileInfo, @ApiParam(value="id",required = true)Integer id){
        UserGoods userGoods=userGoodsService.selectByPrimaryKey(id);
        if (userGoods==null){
            throw new ApiException(1100,"该商品不存在");
        }
        if (userGoods.getGotoPawn()!=0){
            throw new ApiException(1100,"该商品已典当");
        }
        if (userGoods.getGoSell()!=0){
            throw new ApiException(1100,"该商品已申请卖给平台或已卖出");
        }
        if (userGoods.getPostState()!=3 && userGoods.getPostState()!=4){
            throw new ApiException(1100,"平台未确认收货");
        }
        if (userGoods.getPostState()==1){
            throw new ApiException(1100,"该商品已寄拍");
        }
        if (userGoods.getAuthResult()!=null&&userGoods.getAuthResult()==4){
            UserGoods entity=new UserGoods();
            entity.setId(id);
            entity.setIsSell(1);
            entity.setSellStatus(0);
            return userGoodsService.updateByPrimaryKeySelective(entity);
        }else {
            throw new ApiException(1100,"鉴定结果未出或鉴定为赝品无法寄拍！");
        }
    }

    @ApiOperation(value="寄拍首页 ", notes = "不登录")
    @RequestMapping("/sellIndex")
    @ApiMethod(isPage = true, isLogin = false)
    public Object sellIndex(MobileInfo mobileInfo, @ApiParam(value="name",required = false)String name, PageLimit pageLimit){
        startPage();
//        UserGoodsExample example=new UserGoodsExample();
////        UserGoodsExample.Criteria criteria=example.createCriteria();
////        if (StringUtil.isNotBlank(name)){
////            criteria.andNameLike("%"+name+"%");
////        }
////        Date date=new Date();
////        criteria.andIsSellEqualTo(1).andSellStatusEqualTo(1).andSellEndTimeGreaterThan(date).andSellStartTimeLessThan(date);
////        example.setOrderByClause("sell_start_time desc");
////        List<UserGoods> list= userGoodsService.selectByExample(example);
        UserGoodsEx ex=new UserGoodsEx();
        ex.setName(name);
        ex.setUseDate(new Date());
        ex.setSellStatus(1);
        ex.setCollectCount(0);
        ex.setPraiseCount(0);
        ex.setSellCheck(1);
        List<UserGoodsEx> list=userGoodsService.findList(ex);
        return list;
    }

    @ApiOperation(value="寄拍详情 ", notes = "不登录")
    @RequestMapping("/sellDetail")
    @ApiMethod(isPage = false, isLogin = false)
    public UserGoods getSellDetail(MobileInfo mobileInfo, @ApiParam(value="id",required = false)Integer id){

        UserGoodsEx entity=userGoodsService.getById(id);
        if (entity!=null){
            ExperterInfoExample example=new ExperterInfoExample();
            example.createCriteria().andGoodsIdEqualTo(id).andStateEqualTo(1);
            List<ExperterInfo> infos=experterInfoService.selectByExample(example);
            if (infos!=null && infos.size()>0){
                entity.setExperterInfo(infos.get(0).getInfo());
            }
            return entity;
        }else {
            throw new ApiException(1100,"商品不存在！");
        }
    }

    @ApiOperation(value="视频搜索 ", notes = "不登录")
    @RequestMapping("/searchIndexVideo")
    @ApiMethod(isPage = false, isLogin = false)
    public Object searchIndexVideo(@ApiParam(value="name",required = true)String name){
        VideoOnlineExample example=new VideoOnlineExample();
        example.createCriteria().andTitleLike("%"+name+"%");
        example.setOrderByClause("create_time desc");
        return videoOnlineService.selectByExample(example);
    }

    @ApiOperation(value="店铺搜索 ", notes = "不登录")
    @RequestMapping("/searchIndexOrg")
    @ApiMethod(isPage = false, isLogin = false)
    public Object searchIndexOrg(@ApiParam(value="name",required = true)String name){
        //同首页商品搜索，简单实现，后续可以改成elatsicsearch
        if(!StringUtil.isNotBlank(name)||name.length()>15){
            return null;
        }
        List<PawnOrg> list =new ArrayList<>();
        String[] names=name.split("");
        Map<Integer,PawnOrg> orgsMap =new HashMap<>();
        for (String n:names){
            PawnOrgExample pawnOrgExample=new PawnOrgExample();
            pawnOrgExample.createCriteria().andNameLike("%"+n+"%").andStateEqualTo(1);
            List<PawnOrg> orgList=pawnOrgService.selectByExample(pawnOrgExample);
            for (PawnOrg g:orgList) {
                if(orgsMap.get(g.getId())==null){
                    orgsMap.put(g.getId(),g);
                }else{
                    continue;
                }
            }
        }
        list.addAll(orgsMap.values());
//
//        PawnOrgExample pawnOrgExample=new PawnOrgExample();
//        pawnOrgExample.createCriteria().andNameLike("%"+name+"%").andStateEqualTo(1);
//        // pawnOrgExample.setOrderByClause("create_time desc");
//        List<PawnOrg> list=pawnOrgService.selectByExample(pawnOrgExample);

        if (list!=null && list.size()>0){
            List<PawnOrgEx> exList=new ArrayList<>(list.size());
            for(PawnOrg org:list){
                PawnOrgEx ex=new PawnOrgEx();
                try {
                    BeanUtils.copyProperties(org,ex);
//                    BeanUtils.copyProperties(org,ex);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<GoodsEx> goodsExList=goodsService.getMostThreeGoods(ex.getId());
                String img="";
                for (int i=0;i<goodsExList.size();i++){
                    if (StringUtil.isNotBlank(goodsExList.get(i).getImg())){
                        if (i!=goodsExList.size()-1){
                            img+=goodsExList.get(i).getImg()+",";
                        }else {
                            img+=goodsExList.get(i).getImg();
                        }
                    }
                }
                ex.setGoodsImgs(img);
                exList.add(ex);
            }
            return exList;
        }
        return list;
    }

    @ApiOperation(value = "回复评价", notes = "登陆")
    @RequestMapping(value = "/replyUserGoodsComment", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Object reply(MobileInfo mobileInfo,
                        @ApiParam(value = "评论", required = true)String content,
                        @ApiParam(value = "商品id", required = true)Integer userGoodsId,
//                        @ApiParam(value = "头像", required = false)String headImg,
//                        @ApiParam(value = "昵称", required = true)String nickName,
                        @ApiParam(value = "评论id", required = true)Integer commentId,
                        @ApiParam(value = "1为回复评论，2为回复别人的回复", required = true)Integer replyType,
                        @ApiParam(value = "replyType为2时传值，回复评论id", required = false)Integer replyId

    ){

        UserGoods userGoods=userGoodsService.selectByPrimaryKey(userGoodsId);
        if (userGoods==null){
            throw new ApiException(1100,"商品不存在！");
        }

        User user=userService.selectByPrimaryKey(mobileInfo.getUserid());
        if (user==null){
            throw new ApiException(1100,"用户不存在");
        }

        CommentReply entity=new CommentReply();
        entity.setReplyType(replyType);
        Comment comment=commentService.selectByPrimaryKey(commentId);
        if (comment==null){
            throw new ApiException(1100,"评论不存在！");
        }
        if (userGoods.getUserId()==mobileInfo.getUserid()){
            entity.setIsAuthor(2);
        }else {
            entity.setIsAuthor(0);
        }
        //敏感词汇过滤
        entity.setContent(sensitivWordsService.relpSensitivWords(content));
        entity.setReplyId(commentId);
        entity.setCommentId(commentId);
        if (replyType==2){
            if (replyId==null){
                throw new ApiException(1100,"缺少必要参数replyId");
            }
            entity.setReplyId(replyId);
            CommentReply reply=commentReplyService.selectByPrimaryKey(replyId);
            if (reply==null){
                throw new ApiException(1100,"回复对象不存在");
            }
            entity.setToUid(reply.getFromUid());
            entity.setToNickname(entity.getFromNickname());
        }else {
            entity.setReplyId(commentId);
            entity.setToUid(comment.getUserId());
            entity.setToNickname(comment.getNickName());
        }
        entity.setFromUid(mobileInfo.getUserid());
        entity.setFromNickname(user.getNickName());
        entity.setFromThumbImg(user.getHeadImg());
        entity.setCreateTime(new Date());
//        commentService.updateReplyNum(commentId);
        //更新评论数
        userGoodsService.updateUserGoodsCount(userGoodsId,1,0);
        return commentReplyService.insert(entity);

    }

    @ApiOperation(value="寄拍我的", notes = "登录")
    @RequestMapping("/mySell")
    @ApiMethod(isPage = true, isLogin = true)
    public Object mySell(MobileInfo mobileInfo, PageLimit pageLimit, @ApiParam(value="name",required = false)String name){
        startPage();
        UserGoodsEx ex=new UserGoodsEx();
        ex.setUserId(mobileInfo.getUserid());
        if (StringUtil.isNotBlank(name)){
            ex.setName(name);
        }
        return userGoodsService.findList(ex);
    }

    @ApiOperation(value = "寄拍商品购买", notes = "登陆")
    @RequestMapping(value = "/createUserGoodsOrder", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public PayResult createUserGoodsOrder(MobileInfo mobileInfo, @ApiParam(value = "商品id", required = true)Integer userGoodsId,
                                          @ApiParam(value = "地址id", required = true)Integer addressId){

        if (!redisCache.exists("createUserGoodsOrder:"+userGoodsId)){
            redisCache.expire("createUserGoodsOrder:"+userGoodsId,userGoodsId,30L);
            return apiUserGoodsService.createUserGoodsOrder(mobileInfo.getUserid(),userGoodsId,addressId);
        }else {
            throw new ApiException(1100,"该商品已经被下单！");
        }

    }


    @ApiOperation(value = "我收藏的寄拍商品", notes = "登陆")
    @RequestMapping(value = "/collectSellUserGoods/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Object myCollectUserGoods(MobileInfo mobileInfo){
        UserGoodsEx userGoodsEx=new UserGoodsEx();
        userGoodsEx.setCollectUserId(mobileInfo.getUserid());
        userGoodsEx.setCollectPraiseType(1);
        List<UserGoodsEx> list=userGoodsService.findCollectList(userGoodsEx);
        processUerGoods(list);
        return list;
    }
    public static void processUerGoods(List<UserGoodsEx> list){
        if (CollectionUtil.isNotEmpty(list)){
            for (UserGoodsEx ex:list){
                ex.setSellPawnCodeInfo(MGoodsCateList.getNameByCode(ex.getSellPawnCode()));
            }
        }

    }

    @ApiOperation(value = "收藏点赞寄拍商品", notes = "登陆")
    @RequestMapping(value = "/collectPraiseUserGoods", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Ret collectPraiseUserGoods(MobileInfo mobileInfo, @ApiParam(value = "商品id", required = true)Integer userGoodsId, @ApiParam(value = "0点赞1收藏",required = true)Integer type){
        if (type ==null || (type !=0 && type!=1)){
            throw new ApiException(1100,"非法参数");
        }
        UserGoods userGoods=userGoodsService.selectByPrimaryKey(userGoodsId);
        CollectPraise entity=new CollectPraise();
        entity.setType(type);
        entity.setCreateTime(new Date());
        entity.setUserId(mobileInfo.getUserid());
        entity.setStatus(1);
        entity.setUserGoodsId(userGoodsId);
        entity.setOwnerId(userGoods.getUserId());
        if (StringUtil.isNotBlank(userGoods.getSellImgs())){
            String[] imgs = userGoods.getSellImgs().split(",");
            entity.setImg(imgs[0]);
        }
        CollectPraiseExample example=new CollectPraiseExample();
        example.createCriteria().andUserIdEqualTo(mobileInfo.getUserid()).andUserGoodsIdEqualTo(userGoodsId).andTypeEqualTo(type);
        if (CollectionUtil.isEmpty(collectPraiseService.selectByExample(example))){
            if (type==0){
                userGoodsService.updateUserGoodsCount(userGoodsId,1,2);
            }else if (type==1){
                userGoodsService.updateUserGoodsCount(userGoodsId,1,1);

            }
            collectPraiseService.insert(entity);
        }
        return ok();
    }

    @ApiOperation(value = "新增评价", notes = "登陆")
    @RequestMapping(value = "/addUserGoodsComment", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Object addUserGoodsComment(MobileInfo mobileInfo,
                                      @ApiParam(value = "评论", required = true)String content,
//                      @ApiParam(value = "头像", required =false)String headImg,
//                      @ApiParam(value = "昵称", required = true)String nickName,
                                      @ApiParam(value = "商品id", required = true)Integer userGoodsId
    ){

        Comment entity=new Comment();
        UserGoods userGoods=userGoodsService.selectByPrimaryKey(userGoodsId);

        if (userGoods==null){
            throw new ApiException(1100,"商品不存在！");
        }
//        if (userBlackService.isBlackUser(mobileInfo.getUserid(),article.getUserId())>0){
//            throw new ApiException("已被拉黑无法评论动态");
//        }

        //敏感词汇过滤
        entity.setContent(sensitivWordsService.relpSensitivWords(content));
        User user=userService.selectByPrimaryKey(mobileInfo.getUserid());
        if (user==null){
            throw new ApiException(1100,"用户不存在");
        }
        entity.setIsHot(0);
        entity.setUserId(mobileInfo.getUserid());
        entity.setLikeNum(0);
        entity.setIsReply(0);
        entity.setReplyNum(0);
        entity.setIsTop(0);
        entity.setHeadImg(user.getHeadImg());
        entity.setNickName(user.getNickName());
        entity.setTopicId(userGoodsId);
        entity.setType(1);
        entity.setStatus(1);
        entity.setTopicUserId(userGoods.getUserId());
        entity.setCreateTime(new Date());
        if (userGoods.getUserId()==mobileInfo.getUserid()){
            entity.setIsAuthor(2);
        }else {
            entity.setIsAuthor(0);
        }
        //更新评论数
        userGoodsService.updateUserGoodsCount(userGoodsId,1,0);
        return commentService.insert(entity);
    }


    /**
     * 首页轮播图加拍档头条
     */
    @ApiOperation(value = "首页轮播图(用户端)", notes = "")
    @RequestMapping("/indexBanner")
    @ApiMethod()
    public List<IndexInfo> indexBanner(MobileInfo mobileInfo){
        List<IndexInfo> ret = new ArrayList<IndexInfo>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("location",1);
        List<AdEx> list = adService.selectRzList(map);
        for(AdEx ex : list){
            IndexInfo record = new IndexInfo();
            record.setId(ex.getId());
            record.setIndex_images(ex.getImg());
            record.setType(ex.getType());
            if(0 == ex.getType()){
                record.setContent(ex.getContent());
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
                BFileExample example1 = new BFileExample();
                example1.createCriteria().andFileIdEqualTo(video);
                String file_path = fileService.selectByExample(example1).get(0).getFilePath();
                record.setContent(CoreConstants.getProperty("video.path")+file_path);
            }else if(6 == ex.getType()){
                PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(Integer.parseInt(ex.getContent()));
                record.setContent(JSONUtils.serialize(pawnOrg));
            }

            if(4 == ex.getType()){
                //如果是绝当商品的商品
                if(null == ex.getGoodsId()){
                    //如果是后台上传的,则不区分30000
                    record.setCstate(2);
                }else{
                    if(new BigDecimal(ex.getCost()).compareTo(new BigDecimal(30000) )== -1){
                        //走流程小于三万则是普通商品
                        record.setCstate(2);
                    }else{
                        record.setCstate(1);
                    }
                }
            }
            record.setState(1);
            ret.add(record);
        }

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("location",4);
        List<AdEx> list2 = adService.selectRzList(map2);
        for(AdEx ex : list2){
            IndexInfo record = new IndexInfo();
            record.setId(ex.getId());
            record.setPawnMsg(ex.getDiscription());
            record.setIndex_images(ex.getImg());
            record.setType(ex.getType());
            if(0 == ex.getType()){
                record.setContent(ex.getContent());
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
                BFileExample example1 = new BFileExample();
                example1.createCriteria().andFileIdEqualTo(video);
                String file_path = fileService.selectByExample(example1).get(0).getFilePath();
                record.setContent(CoreConstants.getProperty("video.path")+file_path);
            }else if(6 == ex.getType()){
                PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(Integer.parseInt(ex.getContent()));
                record.setContent(JSONUtils.serialize(pawnOrg));
            }
            if(4 == ex.getType()){
                //如果是绝当商品的商品
                if(null == ex.getGoodsId()){
                    //如果是后台上传的,则不区分30000
                    record.setCstate(2);
                }else{
                    if(new BigDecimal(ex.getCost()).compareTo(new BigDecimal(30000) )== -1){
                        //走流程小于三万则是普通商品
                        record.setCstate(2);
                    }else{
                        record.setCstate(1);
                    }
                }
            }
            record.setState(2);
            ret.add(record);
        }
        return ret;
    }

    /**
     * 去鉴宝--在线鉴定
     */
    @ApiOperation(value = "去鉴宝--在线鉴定(用户端)", notes = "登陆")
    @RequestMapping("/gotoAuthGood")
    @ApiMethod(isLogin = true)
    public Ret gotoAuthGood(MobileInfo mobileInfo,
                            @ApiParam(value="图片",required = true) String images,
                            @ApiParam(value="宝贝视频",required = false) String video,
                            @ApiParam(value="内容[{'name':'品牌','content':'卡地亚','contentType':'1'},{'name':'主题材质','content':'宇宙大金刚','contentType':'1'}]---contentType:1普通2时间3图片4视频'",required = true) String content,
                            //[{"name":"品牌","content":"卡地亚","contentType":"1"},{"name":"主题材质","content":"宇宙大金刚","contentType":"1"},{"name":"镶石材质","content":"超级大钻石","contentType":"1"},{"name":"使用情况","content":"使用情况良好","contentType":"1"},{"name":"附件","content":"保卡,证书","contentType":"1"},{"name":"购买价格","content":"9899","contentType":"1"},{"name":"购买时间","content":"2013-05-09","contentType":"1"}]
                            @ApiParam(value="购买价格",required = false) String price,
                            @ApiParam(value="类型code",required = true) Integer code,
                            @ApiParam(value="购买时间",required = false) String buyTime,
                            @ApiParam(value="鉴定要求",required = false) String authenticateRequire,
                            @ApiParam(value="附件照",required = false) String goodsImgs,
                            @ApiParam(value="描述",required = false) String info){
        //JSONObject jsonObject = JSON.parseObject(content);
        //JSONArray array = jsonObject.getJSONArray("json");
        //String name = "";

        Ret ret = new Ret();
        PawnCateExample example = new PawnCateExample();

        example.createCriteria().andCodeEqualTo(code+"");
        List<PawnCate> list = pawnCateService.selectByExample(example);
        PawnCate pawnCate = list.get(0);

        UserGoods userGoods = new UserGoods();
        //如果是钻石或者贵金属，不形成订单--并且除去类别为其他
        if(!MGoodsCateList.zs.code.equals(pawnCate.getCode()) && !MGoodsCateList.gjs.code.equals(pawnCate.getCode()) && !MGoodsCateList.qt.code.equals(pawnCate.getCode())
                && !MGoodsCateList.fcys.equals(pawnCate.getCode())){
                //JSONObject jsonObj = (JSONObject)object;
                //userGoods.setBuyPrice(new BigDecimal(jsonObj.get("buyPrice")+""));
                //userGoods.setImages(jsonObj.get("image")+"");
                if(StringUtil.isNotBlank(price)){
                    userGoods.setBuyPrice(new BigDecimal(price));
                }
                userGoods.setImages(images);
                userGoods.setContent(content);
                userGoods.setUserId(mobileInfo.getUserid());
                userGoods.setBelongId(mobileInfo.getUserid());
                userGoods.setBelongType(1);
                userGoods.setLocation(0);
                if(StringUtil.isNotBlank(buyTime)){
                    userGoods.setBuyTime(DateUtil.strToDate(buyTime));
                }

                userGoods.setGoodsImgs(goodsImgs);
               // userGoods.setPostState(-1);
                userGoods.setGoSell(0);
                userGoods.setPostState(1);
                userGoods.setGotoPawn(0);
                userGoods.setCateCode(pawnCate.getCode());
                userGoods.setBackState(0);
                userGoods.setAuthResult(0);
                userGoods.setCateId(pawnCate.getId());
                userGoods.setIsVerify(0);
                userGoods.setVideo(video);
                userGoods.setInfo(info);
                userGoods.setIsRedeem(0);
                int result = userGoodsService.insert(userGoods);
                if(result == 0){
                    throw new ApiException(MErrorEnum.OPERATION_FAILURE_ERROR);
                }else{
                    //新增成功
                    ret.setCode(1);
                }

        }else if (MGoodsCateList.zs.code.equals(pawnCate.getCode())){
            //在线鉴定出价格--钻石
            JSONArray jsonArray = JSON.parseArray(content);
            String color = null;
            String weigth = null;
            String light = null;
            for(Object object : jsonArray){
                JSONObject jsonObj = (JSONObject)object;
                if(jsonObj.get("name").equals("克拉重量")){
                    weigth = jsonObj.get("content")+"";
                }
                if(jsonObj.get("name").equals("颜色")){
                    color = jsonObj.get("content")+"";
                }
                if(jsonObj.get("name").equals("净度")){
                    light = jsonObj.get("content")+"";
                }
            }

            //最终重量
            Double weigthEnd = (Double.parseDouble(weigth))*100;
            if(weigthEnd <= 30){
                ret.setCode(-1);
                ret.setMsg(" 0.3克拉以下的不予鉴定");
                return ret;
            }
            if(light.equals("SI1") || light.equals("SI2") || light.equals("SI3") || light.equals("I1") || light.equals("I2") || light.equals("I3")){
                ret.setData("参考价格，以邮件审核价格为准");
            }
            DiamondExample example1 = new DiamondExample();
            example1.createCriteria().andWeightBeginLessThanOrEqualTo(weigthEnd.floatValue()).andWeigthEndGreaterThanOrEqualTo(weigthEnd.floatValue()).andColorEqualTo(color).andLightEqualTo(light);
            List<Diamond> list1 = diamondService.selectByExample(example1);
            if(null == list1 || list1.size() ==0){
                ret.setCode(-1);
                ret.setMsg("找不到该商品的价格，请到店或者邮寄");
                return ret;
            }
            Diamond diamond = list1.get(0);
            if(null == diamond){
                throw new ApiException(MErrorEnum.APPID_FAIL_ERROR);
            }

            Double price1 = Integer.parseInt(diamond.getValue())*weigthEnd*7* PaidangConst.num;

            ret.setCode(1);

            DecimalFormat testPrice = new DecimalFormat("#.00");
            ret.setMsg(testPrice.format(price1));


        }else if(MGoodsCateList.gjs.code.equals(pawnCate.getCode())){
            //在线鉴定出价格--黄金
            JSONArray jsonArray = JSON.parseArray(content);
            //种类
            String type = null;
            //克重
            String weigth = null;
            //纯度
            String cd = null;
            for(Object object : jsonArray){
                JSONObject jsonObj = (JSONObject)object;
                if(jsonObj.get("name").equals("金属种类")){
                    if(jsonObj.get("content").equals("黄金")){
                        type = "gold";
                    }else if(jsonObj.get("content").equals("铂金")){
                        type = "bj";
                    }
                }
                if(jsonObj.get("name").equals("克重")){
                    weigth = jsonObj.get("content")+"";
                }
                if(jsonObj.get("name").equals("纯度")){
                    cd = jsonObj.get("content")+"";
                }
            }
            CodeExample codeExample = new CodeExample();
            codeExample.createCriteria().andCodeEqualTo(type);
            List<Code> list1 = codeService.selectByExample(codeExample);
            Code code2 = list1.get(0);

            Double price1 = Double.parseDouble(code2.getValue())*Double.parseDouble(weigth)*Double.parseDouble(cd);
            DecimalFormat testPrice = new DecimalFormat("#.00");
            ret.setMsg(testPrice.format(price1));


        }else if(MGoodsCateList.qt.code.equals(pawnCate.getCode())){
            //其他
            userGoods.setImages(images);
            userGoods.setUserId(mobileInfo.getUserid());
            userGoods.setBelongId(mobileInfo.getUserid());
            userGoods.setBelongType(1);
            userGoods.setLocation(0);
            //userGoods.setPostState(-1);
            userGoods.setPostState(1);
            userGoods.setGotoPawn(0);
            userGoods.setContent(content);
            userGoods.setBackState(0);
            userGoods.setAuthResult(0);
            userGoods.setGoSell(0);
            userGoods.setCateCode(pawnCate.getCode());
            userGoods.setCateId(pawnCate.getId());
            userGoods.setAuthenticateRequire(authenticateRequire);
            userGoods.setVideo(video);
            userGoods.setIsVerify(0);
            userGoods.setIsRedeem(0);
            int result = userGoodsService.insert(userGoods);
            if(result == 0){
                throw new ApiException(MErrorEnum.OPERATION_FAILURE_ERROR);
            }else{
                //新增成功
                ret.setCode(1);
            }
        }



        return ret;
    }

    /**
     * 我的鉴定列表
     */
    @ApiOperation(value="我的鉴定列表", notes = "登录")
    @RequestMapping("/myGoods")
    @ApiMethod(isPage = true, isLogin = true)
    public List<AppMyGoods> list(MobileInfo mobileInfo,
                                 PageLimit pageLimit){
        startPage();
//        UserGoodsExample example = new UserGoodsExample();
//        example.createCriteria().andBelongIdEqualTo(mobileInfo.getUserid()).andGotoPawnEqualTo(0).andBackStateEqualTo(0);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("belong_id",mobileInfo.getUserid());
        List<UserGoodsEx> list = userGoodsService.selectMyGoods(map);
        return getUserGoodsList(list);
    }

    /**
     * 封装返回我的鉴定列表
     *
     * @param list
     * @return
     */
    private List<AppMyGoods> getUserGoodsList(List<UserGoodsEx> list) {
        List<AppMyGoods> appMyGoodsList = new ArrayList<AppMyGoods>();

        for(UserGoodsEx ex : list){
            appMyGoodsList.add(getAppMyGoodsList(ex));
        }
        return appMyGoodsList;
    }
    /**
     * 封装返回AppMyGoods
     *
     * @param ex
     * @return
     */
    private AppMyGoods getAppMyGoodsList(UserGoodsEx ex) {
        AppMyGoods mini = new AppMyGoods();
        mini.setId(ex.getId());
        if(null == ex.getName() || "".equals(ex.getName())){
            if(MGoodsCateList.zs.code.equals(ex.getCateCode())){
                mini.setTitle(MGoodsCateList.zs.name);
            }else if(MGoodsCateList.qt.code.equals(ex.getCateCode())){
                mini.setTitle(MGoodsCateList.qt.name);
            }else if(MGoodsCateList.gjs.code.equals(ex.getCateCode())){
                mini.setTitle(MGoodsCateList.gjs.name);
            }else if(MGoodsCateList.fcys.code.equals(ex.getCateCode())){
                mini.setTitle(MGoodsCateList.fcys.name);
            }else if(MGoodsCateList.sb.code.equals(ex.getCateCode())){
                mini.setTitle(MGoodsCateList.sb.name);
            }else if(MGoodsCateList.SCPZB.code.equals(ex.getCateCode())){
                mini.setTitle(MGoodsCateList.SCPZB.name);
            }else if(MGoodsCateList.hty.code.equals(ex.getCateCode())){
                mini.setTitle(MGoodsCateList.hty.name);
            }else if(MGoodsCateList.csbs.code.equals(ex.getCateCode())){
                mini.setTitle(MGoodsCateList.csbs.name);
            }
        }else{
            mini.setTitle(ex.getName());
        }
        String[] strs = ex.getImages().split(",");
        mini.setImage(ex.getImages());
        mini.setAppraisalDsc(ex.getAppraisalDsc());
//        if(0 == ex.getPostState()){
//            //如果宝贝在用户手中，说明是在线鉴定
//            mini.setResult(ex.getAuthResult());
//            mini.setPriceTest(ex.getAuthPriceTest()+"");
//            mini.setPrice(ex.getAuthPrice()+"");
//        }else{
//            if(1 == ex.getIsVerify()){
//                mini.setResult(ex.getAuthResult());
//                mini.setPriceTest(ex.getAuthPriceTest()+"");
//                mini.setPrice(ex.getAuthPrice()+"");
//            }
//        }

        if(1 == ex.getPostState()){
            //用户没寄出--代表是在线鉴定
            mini.setResult(ex.getAuthResult());
            mini.setPriceTest(ex.getAuthPriceTest()+"");
            mini.setPrice(ex.getAuthPrice()+"");
        }else if(3 == ex.getPostState() || 4 == ex.getPostState()){
            //平台收到宝贝--判断是否有二次鉴定
            if(1 == ex.getIsVerify()){
                //已经二次鉴定
                mini.setResult(ex.getAuthResult());
                mini.setPriceTest(ex.getAuthPriceTest()+"");
                mini.setPrice(ex.getAuthPrice()+"");
            }else{
                //没有二次鉴定 -- 结果是鉴定中，--价格空
                mini.setResult(1);
                mini.setPriceTest("");
                mini.setPrice("");

            }
        }


        mini.setPostState(ex.getPostState());
        if(null != ex.getGoSell()){
            if(2 == ex.getGoSell()){
                //平台已经确认打款并且上传打款凭证
                mini.setPlatformIsVerify(1);
                mini.setPlatformPayTicket(ex.getPlatformTicket());
            }else if(1 == ex.getGoSell()){
                mini.setPlatformIsVerify(0);
            }else if(0 == ex.getGoSell()){
                mini.setPlatformIsVerify(-1);
            }
        }else{
            mini.setPlatformIsVerify(-1);
        }


        return mini;
    }

    /**
     * 用户把宝贝卖给卖给平台
     * @param mobileInfo
     * @param id
     * @return
     */
    @ApiOperation(value="用户把宝贝卖给卖给平台", notes = "登录")
    @RequestMapping("/saleToPlatform")
    @ApiMethod(isLogin = true)
    public Ret saleToPlatform(MobileInfo mobileInfo,
                               @ApiParam(value="id",required = true)Integer id,
                               @ApiParam(value="银行卡id",required = true)Integer bankCardId) {
        User user = userService.selectByPrimaryKey(mobileInfo.getUserid());
        UserBankCard userBankCard = userBankCardService.selectByPrimaryKey(bankCardId);
        //UserGoods userGoods = userGoodsService.selectByPrimaryKey(id);
        UserGoodsExample example = new UserGoodsExample();
        example.createCriteria().andIdEqualTo(id).andGoSellNotEqualTo(2);
        List<UserGoods> list = userGoodsService.selectByExample(example);
        UserGoods userGoods = list.get(0);
        if(null != userGoods.getGoSell()){
            if(1 == userGoods.getGoSell()){
                throw new ApiException(MErrorEnum.APPLY_IS_WORKING);
            }
        }
        //申请卖给平台
        userGoods.setGoSell(1);
        userGoodsService.updateByPrimaryKeySelective(userGoods);
        if(4 == userGoods.getAuthResult() && 1 == userGoods.getLocation()){
            //宝贝是真品并且宝贝现在在平台
            //平台商品收购表插入数据
            PlatformGoodsBuy platformGoodsBuy = new PlatformGoodsBuy();
            platformGoodsBuy.setSource(1);
            platformGoodsBuy.setGoodsId(userGoods.getId());
            platformGoodsBuy.setPrice(userGoods.getAuthPrice());
            platformGoodsBuy.setBankCardName(userBankCard.getBankCardName());
            platformGoodsBuy.setBankCardNo(userBankCard.getBankCardNo());
            platformGoodsBuy.setUserName(user.getName());
            platformGoodsBuy.setUserPhone(user.getAccount());
            platformGoodsBuy.setState(1);
            int result = platformGoodsBuyService.insert(platformGoodsBuy);
            if(result == 0){
                throw new ApiException(MErrorEnum.SERVER_BUSY_ERROR);
            }
//            //插入用户余额日志表
//            userBalanceLogService.updateUserBalanceLog(
//                    mobileInfo.getUserid(),
//                    id,
//                    userGoods.getAuthPrice(),
//                    1,
//                    5,
//                    10,
//                    "",
//                    "",
//                    userBankCard.getBankCardName(),
//                    userBankCard.getBankCardNo(),
//                    0,
//                    "宝祥",
//                    MPawnMsg.phone
//            );
        }
        return ok();
    }

    /**
     * 物流信息
     * @param mobileInfo
     * @param pageLimit
     * @param type
     * @return
     */
    @ApiOperation(value="物流信息列表", notes = "登录")
    @RequestMapping("/postList")
    @ApiMethod(isPage = true, isLogin = true)
    public List<AppLogisticsDetail> waitToPost(MobileInfo mobileInfo,
                                               PageLimit pageLimit,
                                               @ApiParam(value="状态(1寄往平台  2平台取回)",required = true)Integer type){
       startPage();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("source_id",mobileInfo.getUserid());
        map.put("type",type);
//        List<ExpressEx> list = expressService.selectByGoods(map);
//        List<AppLogisticsDetail> list2 = new ArrayList<AppLogisticsDetail>();
//        for(ExpressEx ex : list){
//            AppLogisticsDetail record = new AppLogisticsDetail();
//            record.setId(ex.getId());
//            record.setImages(ex.getGoodsImages());
//            record.setName(ex.getGoodsName());
//            record.setPostState(ex.getExpressState());
//            record.setBackState(ex.getExpressState());
//            record.setPostExpressCode(ex.getExpressCode() == null ? "" : ex.getExpressCode());
//            list2.add(record);
//        }
        List<AppLogisticsDetail> list2 = new ArrayList<AppLogisticsDetail>();
        List<UserGoodsEx> list = userGoodsService.selectExpressList(map);
        for(UserGoodsEx ex : list){
            AppLogisticsDetail c = new AppLogisticsDetail();
            c.setId(ex.getExpressId());
            c.setImages(ex.getImages());
            c.setName(ex.getName());
            c.setPostState(ex.getExpressState() == 0? null : ex.getExpressState());
            c.setBackState(ex.getExpressState() == 0? null : ex.getExpressState());
            c.setPostExpressCode(ex.getExpressCode());
            list2.add(c);
        }

        return list2;
    }

    /**
     * 去邮寄--宝祥典当地址
     */
    @ApiOperation(value="去邮寄--宝祥典当地址", notes = "不需要登录")
    @RequestMapping("/bxAddress")
    @ApiMethod(isPage = false, isLogin = false)
    public AppBXAddress bxAddress(){
        AppBXAddress c = new AppBXAddress();
        c.setName(MPawnMsg.name);
        c.setPhone(MPawnMsg.phone);
        c.setAddress(MPawnMsg.address);
        return c;
    }

    /**
     * 去邮寄--填写物流信息
     */
    @ApiOperation(value="去邮寄--填写物流信息", notes = "登录")
    @RequestMapping("/gotoPost")
    @ApiMethod(isPage = false, isLogin = true)
    public Ret gotoPost(MobileInfo mobileInfo,
                        @ApiParam(value="id(不是必填--若在线申请过必填，)",required = true)Integer id,
                        @ApiParam(value="鉴定code(不是必填--若直接邮寄必填，)",required = true)String pawnCateCode,
                        @ApiParam(value="图片(不是必填--若直接邮寄必填，)",required = true)String images,
                        @ApiParam(value="物流单号",required = true)String pid,
                        @ApiParam(value="邮寄打包视频",required = true)String video,
                        @ApiParam(value="顺风保价",required = true)BigDecimal sfProtectPrice){
//       UserAddressExample userAddressExample = new UserAddressExample();
//       userAddressExample.createCriteria().andUserIdEqualTo(mobileInfo.getUserid()).andIsDefaultEqualTo(1);
//       List<UserAddress> list = userAddressService.selectByExample(userAddressExample);
//       UserAddress ex = list.get(0);

        Express express = new Express();
        UserGoodsExample example = new UserGoodsExample();
        if(null != id){
            example.createCriteria().andIdEqualTo(id);
        }


        UserGoods userGoods = userGoodsService.selectByPrimaryKey(id);
        UserGoods record = new UserGoods();




        if(null !=  userGoods){
            //经过在线申请

            userGoods.setPostExpressCode(pid);
            userGoods.setPostState(2);
            userGoods.setPostExpress(MPostExpressAddress.xfAddress);
            userGoods.setPostExpressCode(pid);
            userGoods.setBackState(0);
            userGoods.setGoVideo(video);
            userGoods.setGoSell(0);
            userGoods.setIsVerify(0);
            userGoods.setSfProtectPrice(sfProtectPrice);
            int result = userGoodsService.updateByPrimaryKeySelective(userGoods);
            if(result == 0){
                throw new ApiException(1000, "请求失败");
            }
            express.setFid(userGoods.getId());
        }else{
            //直接邮寄
            record.setUserId(mobileInfo.getUserid());
            record.setBelongId(mobileInfo.getUserid());
            record.setBelongType(1);
            record.setPostExpressCode(pid);
            record.setGotoPawn(0);
            record.setPostState(2);
            record.setPostExpress(MPostExpressAddress.xfAddress);
            record.setBackState(0);
            record.setGoVideo(video);
            record.setImages(images);
            record.setGoSell(0);
            record.setCateCode(pawnCateCode);
            record.setSfProtectPrice(sfProtectPrice);
            record.setIsVerify(0);
            PawnCateExample pawnCateExample = new PawnCateExample();
            pawnCateExample.createCriteria().andCodeEqualTo(pawnCateCode);
            List<PawnCate> list1 = pawnCateService.selectByExample(pawnCateExample);
            PawnCate pawnCate = list1.get(0);

            record.setCateId(pawnCate.getId());
            record.setIsRedeem(0);
            int result = userGoodsService.insert(record);
            if(result == 0){
                throw new ApiException(MErrorEnum.OPERATION_FAILURE_ERROR);
            }
            express.setFid(record.getId());


        }
        //插入物流记录

        express.setSource(1);
        express.setSourceId(mobileInfo.getUserid());

        express.setType(1);
        express.setExpressName(MPostExpressAddress.xfAddress);
        express.setExpressCode(pid);
        express.setExpressState(0);
        express.setExpressData("");
//            express.setPostName(ex.getUserName());
//            express.setPostPhone(ex.getPhone());
        express.setReceiveName(MPawnMsg.name);
        express.setReceivePhone(MPawnMsg.phone);
        express.setReceviceAddress(MPawnMsg.address);
        int expressResult = expressService.insert(express);
        if(expressResult == 0){
            throw new ApiException(MErrorEnum.OPERATION_FAILURE_ERROR);
        }

            return ok();
    }

    @ApiOperation(value="我的鉴定--取回", notes = "登录")
    @RequestMapping("/getBackGoods")
    @ApiMethod(isPage = false, isLogin = true)
    public Ret getBackGoods(MobileInfo mobileInfo,
                            @ApiParam(value="id",required = true)Integer id,
                            @ApiParam(value="地址id",required = true)Integer addressId){
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("user_id",mobileInfo.getUserid());
//        map.put("id",id);
//
//        UserAddressEx userAddressEx = userAddressService.selectAppList(map);
        User user = userService.selectByPrimaryKey(mobileInfo.getUserid());
        //UserGoods userGoods = new UserGoods();
        //userGoods.setId(id);
        UserGoods userGoods = userGoodsService.selectByPrimaryKey(id);
        userGoods.setBackUserName(user.getName());
        userGoods.setBackState(1);
        UserAddress userAddress = userAddressService.selectByPrimaryKey(addressId);
        if(!userAddress.getUserName().equals(user.getName())){
            throw new ApiException(MEnumError.USER_SELF_NAME);
        }
        userGoods.setBackUserPhone(userAddress.getPhone());
        userGoods.setBackUserExpress(userAddress.getArea()+userAddress.getAddress());
        int result = userGoodsService.updateByPrimaryKeySelective(userGoods);
        if(result == 0){
            throw new ApiException(MErrorEnum.OPERATION_FAILURE_ERROR);
        }

        //插入express表
        Express express = new Express();
        express.setSourceId(userGoods.getBelongId());
        express.setFid(id);
        express.setType(2);
        express.setExpressState(0);
        express.setReceiveName(user.getName());
        express.setReceivePhone(userAddress.getPhone());
        express.setReceviceAddress(userAddress.getArea()+userAddress.getAddress());
        expressService.insert(express);
        return ok();
    }


    /**
     * 待签收列表
     */
    @ApiOperation(value="物流信息--待签收列表", notes = "登录")
    @RequestMapping("/waitGetList")
    @ApiMethod(isPage = true, isLogin = true)
    public List<AppLogisticsDetail> waitGetList(MobileInfo mobileInfo,
                                                PageLimit pageLimit){
        startPage();
        UserGoodsExample userGoodsExample = new UserGoodsExample();
        userGoodsExample.or().andBelongIdEqualTo(mobileInfo.getUserid()).andPostStateEqualTo(2);
        userGoodsExample.or().andBelongIdEqualTo(mobileInfo.getUserid()).andPostStateEqualTo(3);
        List<UserGoods> list = userGoodsService.selectByExample(userGoodsExample);
        List<AppLogisticsDetail> array = new ArrayList<AppLogisticsDetail>();
        for(UserGoods ex : list){
            AppLogisticsDetail record = new AppLogisticsDetail();
            record.setId(ex.getId());
            record.setImages(ex.getImages());
            record.setName(ex.getName());
            record.setPostExpressCode(ex.getPostExpressCode());
            record.setPostState(ex.getPostState());
            array.add(record);
        }

        return array;
    }

    @ApiOperation(value="物流信息--签收宝贝", notes = "登录")
    @RequestMapping("/getGoods")
    @ApiMethod(isPage = false, isLogin = true)
    public Ret getGoods(MobileInfo mobileInfo,
                        @ApiParam(value="id",required = true)Integer id){
        UserGoods userGoods = userGoodsService.selectByPrimaryKey(id);
        userGoods.setBackState(3);
        userGoodsService.updateByPrimaryKeySelective(userGoods);
        return ok();
    }


    @ApiOperation(value="首页搜索 ", notes = "不登录")
    @RequestMapping("/searchIndexMenu")
    @ApiMethod(isPage = false, isLogin = false)
    public List<ApiIndexMenu> searchIndexMenu(MobileInfo mobileInfo,
                                              @ApiParam(value="name",required = true)String name){
        List<ApiIndexMenu> ret = new ArrayList<ApiIndexMenu>();
//        GoodsExample example = new GoodsExample();
//        example.createCriteria().andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andNameLike("%"+name+"%");
//        example.setOrderByClause("create_time desc");
//        List<Goods> goodsList = goodsService.selectByExample(example);
        //分词检索，因为要求分解成一个字，时间有限，所以简单实现，有兴趣的可以使用elasticsearch实现
        //如果没填或者长度过长，防止sql执行时间过长，直接返回空列表
        if(!StringUtil.isNotBlank(name)||name.length()>15){
            return ret;
        }
        List<GoodsEx> goodsList =new ArrayList<>();
        String[] names=name.split("");
        Map<Integer,GoodsEx> goodsMap =new HashMap<>();
        for (String n:names){
            GoodsEx goodsEx=new GoodsEx();
            goodsEx.setName(n);
            List<GoodsEx> gLists= goodsService.findList(goodsEx);
            for (GoodsEx g:gLists) {
                if(goodsMap.get(g.getId())==null){
                    goodsMap.put(g.getId(),g);
                }else{
                    continue;
                }
            }
        }
        goodsList.addAll(goodsMap.values());
        Map<String, Object> map = new HashMap<String, Object>();
        for(GoodsEx ex : goodsList){
            ApiIndexMenu c = new ApiIndexMenu();
            /*if(ex.getPrice().compareTo(new BigDecimal(30000)) == -1){
                //如果他的价格不满三万则是最新绝当商品
                c.setId(ex.getId());
                c.setImg(ex.getImg());
                c.setTitle(ex.getName());
                c.setPrice(ex.getPrice()+"");
                c.setState(2);
            }else{
                long second = DateUtil.secondsAfter(DateUtil.addMinute(ex.getCreateTime(),(PaidangConst.JD_GOODS_TIME)/60),new Date());
                if(second > 0){
                    c.setId(ex.getId());
                    c.setEndTime(DateUtil.dateToStr(DateUtil.addHour(ex.getCreateTime(),24)));
                    c.setEndTime2(second+"");
                    c.setImg(ex.getImg());
                    c.setTitle(ex.getName());
                    c.setPrice(ex.getPrice()+"");
                    //查找该物品的当前出价--最高价

                    map.put("goods_id",ex.getId());
                    BigDecimal maxPrice = goodsAuctionService.selectMaxPrice(map);
                    c.setMaxPrice(maxPrice == null? "0" : maxPrice.toString());

                    //查找该物品我的出价
                    GoodsAuctionExample example1 = new GoodsAuctionExample();
                    example1.createCriteria().andGoodsIdEqualTo(ex.getId()).andUserIdEqualTo(mobileInfo.getUserid());
                    example1.setOrderByClause("price desc");
                    List<GoodsAuction> list = goodsAuctionService.selectByExample(example1);
                    if(list.size() != 0 && list != null){
                        c.setMyPrice(list.get(0).getPrice()+"");
                    }
                    c.setState(3);
                }else{
                    //超过时间不显示，并且修改他为竞拍失效
                    ex.setState(0);
                    goodsService.updateByPrimaryKeySelective(ex);
                    continue;
                }
                //               }

            }*/
            c.setId(ex.getId());
            c.setImg(ex.getImg());
            c.setTitle(ex.getName());
            c.setPrice(ex.getPrice()+"");
            c.setState(2);
            c.setGoodsType(ex.getType().toString());
            c.setOrgId(ex.getOrgId());
            c.setOrgName(ex.getOrgName());
            c.setSource(ex.getSource());
            ret.add(c);
        }
        return ret;
    }

    @ApiOperation(value="首页热门搜索 ", notes = "不登录")
    @RequestMapping("/searchIndexHotMenu")
    @ApiMethod(isPage = false, isLogin = false)
    public List<ApiIndexHotMenu> searchIndexHotMenu(MobileInfo mobileInfo){
        List<ApiIndexHotMenu> ret = new ArrayList<ApiIndexHotMenu>();
        AdExample example = new AdExample();
        example.createCriteria().andLocationEqualTo(5);
        example.setOrderByClause("sort_order asc, create_time desc");
        List<Ad> list = adService.selectByExampleWithBLOBs(example);
        for(Ad ex : list){
            ApiIndexHotMenu c = new ApiIndexHotMenu();
            c.setId(ex.getId());
            c.setName(ex.getContent());
            ret.add(c);
        }
        return ret;
    }

    @ApiOperation(value="生成分享文字", notes = "不登录")
    @RequestMapping("/getShareText")
    @ApiMethod(isPage = false, isLogin = false)
    public String getShareText(@ApiParam(value="id",required = true)Integer id,
                               @ApiParam(value="type 1新品2绝当",required = true)Integer type){
        String name;
        Goods goods=goodsService.selectByPrimaryKey(id);
        name=goods.getName();
        String str = "abcdefghij";
        String[] ids=String.valueOf(id).split("");
        char[] chars=new char[ids.length];
        for (int i=0;i<ids.length;i++) {
            chars[i]=str.charAt(Integer.parseInt(ids[i]));
        }
        String strFromNum= new String(chars);
        String text="【"+name+"】复制这段描述￥"+strFromNum+"￥后打开蚌蚌拍当";
        return JSONUtils.serialize(text);
    }

    @ApiOperation(value="获取分享详情", notes = "不登录")
    @RequestMapping("/getShareInfo")
    @ApiMethod(isPage = false, isLogin = false)
    public String getShareInfo(@ApiParam(value="id",required = true)String id){
        if(StringUtil.isBlank(id)){
            throw new ApiException(1001,"参数错误");
        }
        String[] realId=new String[id.length()];
        String[] str = "abcdefghij".split("");
        String[] ids = id.trim().split("");
        for (int i = 0; i <ids.length ; i++) {
            for (int j = 0; j < str.length; j++) {
                if(ids[i].equals(str[j])){
                    realId[i]=String.valueOf(j);
                    break;
                }
            }
        }
        if(StringUtil.isBlank(StringUtils.join(realId))){
            throw new ApiException(1001,"参数错误");
        }
        Integer goodsId=Integer.parseInt(StringUtils.join(realId));
        Goods goods=goodsService.selectByPrimaryKey(goodsId);
        return JSONUtils.serialize(goods);


    }


}
