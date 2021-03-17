package com.api.action;

import com.api.MEnumError;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.BaseUtils;
import com.base.util.DateUtil;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.GoodsEx;
import com.paidang.domain.qo.GoodsQo;
import com.paidang.service.GoodsAttributeService;
import com.paidang.service.GoodsAuctionOnlineLogService;
import com.paidang.service.GoodsService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author: xww
 * @create: 2020-11-07 21:02
 * @description: 机构商品维护
 * @modified By:
 * @version:
 **/
@RestController
@RequestMapping(value="/api/goods", produces = {"application/json;charset=UTF-8"})
@Api(tags = "商品相关")
public class ApiGoodsController extends ApiBaseController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsAttributeService goodsAttributeService;

    @Autowired
    private GoodsAuctionOnlineLogService goodsAuctionOnlineLogService;


    @ApiOperation(value = "新增修改商品",notes="新增修改商品")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public int save(MobileInfo mobileInfo, @ApiParam(value = "商品分类",required = true)Integer cateCode, @ApiParam(value = "商品分名称",required = true)String name, @ApiParam(value = "商品价格",required = false)BigDecimal price
                    , @ApiParam(value = "商品简介",required = true)String introduction, @ApiParam(value = "1 一口价 2 竞拍",required = true)Integer dealType
                    , @ApiParam(value = "拍卖开始时间",required = false)Date auctionStartTime,@ApiParam(value = "id 新增不传 id 修改传",required = false) Integer id
                    , @ApiParam(value = "拍卖结束时间",required = false)Date auctionEndTime, @ApiParam(value = "起拍价",required = false)BigDecimal startPrice
                    , @ApiParam(value = "加价幅度",required = false)BigDecimal raisePriceRange, @ApiParam(value = "标签",required = false)String labels
                    , @ApiParam(value = "商品属性",required = true)String goodsAttribute, @ApiParam(value = "商品图片",required = true)String imgs
                    , @ApiParam(value = "视频",required = false) String bannerVideo
                    , @ApiParam(value = "库存 一口价传值 ",required = false) Integer total
                    , @ApiParam(value = "商品编号",required = true) String goodsCode



    ){
//         , @ApiParam(value = "定时上架时间",required = false)Date onlineTime
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (dealType==1){
            BaseUtils.checkBlankParam(price,total);
        }else if (dealType==2){
            BaseUtils.checkBlankParam(auctionEndTime,auctionStartTime,startPrice,raisePriceRange);
            price = startPrice;
            total = 1;
            if (auctionStartTime.compareTo(auctionEndTime)>=0){
                throw new ApiException(-1,"竞拍结束时间不能早于开始时间");
            }
        }
        Date date = new Date();
        Goods goods = new Goods();
        goods.setCateCode(cateCode);
        goods.setName(name);
        goods.setPrice(price);
        goods.setIntroduction(introduction);
        goods.setDealType(dealType);
        goods.setBannerVideo(bannerVideo);
//        goods.setOnlineTime(onlineTime);
//        goods.setUserId(mobileInfo.getUserId());
        goods.setAuctionStartTime(auctionStartTime);
        goods.setAuctionEndTime(auctionEndTime);
        goods.setRaisePriceRange(raisePriceRange);
        goods.setLabels(labels);
        goods.setGoodsAttribute(goodsAttribute);
        goods.setImgs(imgs);
        goods.setStartPrice(startPrice);
        goods.setTotal(total);
        goods.setGoodsCode(goodsCode);
//        goods.setState();
        if (id==null){
            goods.setSource(2);
            goods.setType(2);
            goods.setIsOnline(0);
            goods.setOrgId(orgId);
            goods.setIsVerfiy(2);
            goods.setSoldOut(0);
            goods.setState(1);
            goods.setCreateTime(date);
            goodsService.insertSelective(goods);
//            if (dealType == 2){
//                //保存竞拍日志
//                GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
//                log.setAuctionEndTime(auctionEndTime);
//                log.setAuctionStartTime(auctionStartTime);
//                log.setGoodsId(goods.getId());
//                log.setStatus(0);
//                log.setCreateTime(date);
//                goodsAuctionOnlineLogService.insertSelective(log);
//                Goods tmp = new Goods();
//                tmp.setId(goods.getId());
//                tmp.setAuctionOnlineLogId(log.getId());
//                goodsService.updateByPrimaryKeySelective(tmp);
//            }
        }else {

            Goods goods1 = goodsService.selectByPrimaryKey(id);
            if (goods1.getIsOnline()==1 ){
                throw new ApiException(400,"请先下架再修改商品信息");
            }
//			if (goods1.getDealType()==2  && date.compareTo(goods1.getAuctionStartTime())>=0 && date.compareTo(goods1.getAuctionEndTime())<=0){
//				throw new ApiException(400,"竞拍中禁止修改商品信息");
//			}
            if(!Objects.equals(orgId,goods1.getOrgId())){
                throw new ApiException("机构异常");
            }
//            if (dealType!=null && dealType==2){
//                if(date.compareTo(auctionStartTime)<0){
//                    boolean flag = false;
//                    if (auctionStartTime!=null && goods1.getAuctionStartTime().compareTo(auctionStartTime)!=0){
//                        flag = true;
//                    }
//                    if (auctionEndTime!=null && goods1.getAuctionEndTime().compareTo(auctionEndTime)!=0){
//                        flag = true;
//                    }
//                    //修改上架记录表
//                    if (flag){
//                        GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
//                        log.setId(goods1.getAuctionOnlineLogId());
//                        log.setAuctionStartTime(auctionStartTime);
//                        log.setAuctionEndTime(auctionEndTime);
//                        log.setModifyTime(date);
//                        log.setModifyAccount(mobileInfo.getUserId().toString());
//                        goodsAuctionOnlineLogService.updateByPrimaryKeySelective(log);
//                    }
//                }else if (date.compareTo(auctionEndTime)>0){
//                    GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
//                    log.setAuctionEndTime(auctionEndTime);
//                    log.setAuctionStartTime(auctionStartTime);
//                    log.setGoodsId(id);
//                    log.setStatus(1);
//                    log.setCreateTime(date);
//                    goodsAuctionOnlineLogService.insertSelective(log);
//                    goods.setAuctionOnlineLogId(log.getId());
//                }
//
//            }
            goods.setId(id);
            goods.setModifyTime(new Date());
            goodsService.updateByPrimaryKeySelective(goods);
        }

        return 1;

    }

    @PostMapping("/dismount")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "商品下架")
    public int OrgGoodsDismount(@ApiParam(required = true,name = "goodsId",value = "商品id") int goodsId,
                                MobileInfo mobileInfo,
                                @ApiParam(required = false,name = "reasonOfDismounting",value = "下架原因") String reasonOfDismounting){
        //判断当前商品是否是下架状态
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
        Date date = new Date();
        if (goods.getDealType()==2 && date.compareTo(goods.getAuctionStartTime())>=0 && date.compareTo(goods.getAuctionEndTime())<=0){
            throw new ApiException(400,"竞拍中禁止下架");
        }

        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (!Objects.equals(orgId,goods.getOrgId())){
            throw new ApiException(400,"机构异常");
        }
        if(goods.getIsOnline()==0||goods.getIsOnline()==2){
            throw new ApiException(MEnumError.DISMOUNT_STATE);
        }
        if(goods.getIsOnline()==-1){
            throw new ApiException(MEnumError.DELETE_STATE);
        }
        //修改上架信息
        if (goods.getDealType()==2){
            GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
            log.setId(goods.getAuctionOnlineLogId());
            log.setStatus(0);
            log.setModifyTime(date);
            log.setModifyAccount(mobileInfo.getUserId().toString());
            goodsAuctionOnlineLogService.updateByPrimaryKeySelective(log);
        }
        Goods updateGoods = new Goods();
        updateGoods.setId(goodsId);
        updateGoods.setIsOnline(0);
        updateGoods.setReasonOfDismounting(reasonOfDismounting);
        goodsService.updateByPrimaryKeySelective(updateGoods);
        return 1;
    }

    @PostMapping("/online")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "商品上架")
    public int OrgGoodsonline(@ApiParam(required = true,name = "goodsId",value = "商品id") int goodsId,
                              MobileInfo mobileInfo){
        //判断当前商品是否是下架状态
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (!Objects.equals(orgId,goods.getOrgId())){
            throw new ApiException(400,"机构异常");
        }
        if(goods.getIsVerfiy()!=2){
            throw new ApiException(400,"商品未审核通过无法上架");
        }
        if(goods.getIsOnline()==-1){
            throw new ApiException(MEnumError.DELETE_STATE);
        }
        Date date = new Date();
        if (goods.getDealType()==2 && date.compareTo(goods.getAuctionStartTime())>=0){
            throw new ApiException(400,"已过竞拍开始时间，无法上架");
        }
        if (goods.getDealType()==1 && goods.getTotal()<=0){
            throw new ApiException(400,"库存不足,无法上架");
        }
        if (goods.getDealType()==2 && goods.getTotal()<=0){
            goods.setTotal(1);
        }
        goods.setIsOnline(1);
        goods.setMaxAutionId(null);
        goods.setMaxAuction(null);
        goods.setOnlineTime(new Date());
        goods.setReasonOfDismounting("");
        //
        if (goods.getDealType()==2){
            GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
            log.setAuctionEndTime(goods.getAuctionEndTime());
            log.setAuctionStartTime(goods.getAuctionStartTime());
            log.setGoodsId(goods.getId());
            log.setStatus(1);
            log.setCreateTime(date);
            goodsAuctionOnlineLogService.insertSelective(log);
            goods.setAuctionOnlineLogId(log.getId());
        }
        goodsService.sellAuctionGoods(goods);
        return 1;
    }

    @PostMapping("/getGoods")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "根据(状态)获取发布商品")
    public  List<GoodsEx> getGoodsByOnline(@ApiParam(required = false,value = "0下架1上架2新增待上架") Integer state,
                                   MobileInfo mobileInfo, @ApiParam(value = "1 一口价(产品库) 2 竞拍（拍品库）")Integer dealType,
                                   @ApiParam(value = "排序 1时间降序 2时间升序") Integer orderType,
                                   @ApiParam(value = "分页(不传则不分页)") Integer pageNum,
                                   @ApiParam(value = "分页(不传则不分页)") String pageSize,
                                   @ApiParam(value = "商品名称")String goodsName){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());

        if (!BaseUtils.isAnyBlank(pageNum,pageSize)){
            startPage();
        }

        List<Integer> sources = new ArrayList<>();
        sources.add(2);
        GoodsQo qo = new GoodsQo();
        qo.setSources(sources);
        qo.setOrgId(orgId);
        qo.setName(goodsName);
        qo.setIsOnline(state);
        qo.setDealType(dealType);
        qo.setIsOnlineCnt(1);
        qo.setOrderType(orderType);
        qo.setOrgState(1);
        List<GoodsEx> goodsList = goodsService.findListEx(qo);
        return  goodsList;
//        if(pageNum!=null && pageSize!=null){
//            return goodsList;
//        }else{
//            return ok(goodsList);
//        }
    }


    @PostMapping("/getGoodsDetail")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "获取商品详情")
    public GoodsEx goodsDetail(@ApiParam(required = true,value = "商品编号") Integer id){

        GoodsQo qo = new GoodsQo();
        qo.setId(id);
        qo.setOrgState(1);
        List<GoodsEx> goodsList = goodsService.findListEx(qo);
        if (CollectionUtils.isNotEmpty(goodsList)){
            return goodsList.get(0);
        }
        return null;
    }


    @PostMapping("/delete")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "删除发布的商品")
    public int deleteGood(MobileInfo mobileInfo,
                          @ApiParam(value = "商品id")int goodsId){
        //判断当前商品是否是下架状态
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (!Objects.equals(orgId,goods.getOrgId())){
            throw new ApiException(400,"机构异常");
        }
        if(goods == null){
            throw new ApiException(-1,"不存在该商品");
        }
        if(goods.getIsOnline()==1){
            throw new ApiException(-1,"商品处于上架状态，请先下架。");
        }
        if(goods.getIsOnline()==-1){
            throw new ApiException(-1,"商品已经处于删除状态");
        }
        goods.setIsOnline(-1);
        goods.setReasonOfDismounting("");
        goodsService.updateByPrimaryKeySelective(goods);

        return 1;
        
    }


    @PostMapping("/goodsAttribute/get")
    @ApiMethod(isLogin = false)
    @ApiOperation(value = "商品属性获取")
    public List<GoodsAttribute> getGoodsAttribute(@ApiParam(value = "分类code,101:玉翠珠宝,102:工艺作品,103:文玩杂项,104:紫砂陶瓷,105:钱币邮票,106:书画篆刻,107:花鸟文娱,108:其他")Integer cateCode){
        GoodsAttributeExample example = new GoodsAttributeExample();

        example.createCriteria().andBelongToEqualTo(cateCode);

        return goodsAttributeService.selectByExample(example);

    }


}
