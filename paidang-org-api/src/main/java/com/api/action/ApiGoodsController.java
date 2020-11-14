package com.api.action;

import com.api.MEnumError;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.BaseUtils;
import com.item.service.UserService;
import com.paidang.dao.model.Goods;
import com.paidang.dao.model.GoodsExample;
import com.paidang.service.GoodsService;
import com.ruoyi.common.core.domain.Ret;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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


    @ApiOperation(value = "新增修改商品",notes="新增修改商品")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public void save(MobileInfo mobileInfo, @ApiParam(value = "商品分类",required = true)Integer cateCode, @ApiParam(value = "商品分名称",required = true)String name, @ApiParam(value = "商品价格",required = false)BigDecimal price
                    , @ApiParam(value = "商品简介",required = true)String introduction, @ApiParam(value = "1 一口价 2 竞拍",required = true)Integer dealType
                    , @ApiParam(value = "拍卖开始时间",required = false)Date auctionStartTime,@ApiParam(value = "id 新增不传 id 修改传",required = false) Integer id
                    , @ApiParam(value = "拍卖结束时间",required = false)Date auctionEndTime, @ApiParam(value = "起拍价",required = false)BigDecimal startPrice
                    , @ApiParam(value = "加价幅度",required = false)BigDecimal raisePriceRange, @ApiParam(value = "标签",required = false)String labels
                    , @ApiParam(value = "商品属性",required = true)String goodsAttribute, @ApiParam(value = "商品图片",required = true)String imgs
                     ,@ApiParam(value = "库存",required = true) Integer total



    ){
//         , @ApiParam(value = "定时上架时间",required = false)Date onlineTime
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (dealType==1){
            BaseUtils.checkBlankParam(price);
        }else if (dealType==2){
            BaseUtils.checkBlankParam(auctionEndTime,auctionStartTime,startPrice,raisePriceRange);

        }
        Goods goods = new Goods();
        goods.setCateCode(cateCode);
        goods.setName(name);
        goods.setPrice(price);
        goods.setIntroduction(introduction);
        goods.setDealType(dealType);
//        goods.setOnlineTime(onlineTime);
        goods.setUserId(mobileInfo.getUserId());
        goods.setAuctionStartTime(auctionStartTime);
        goods.setAuctionEndTime(auctionEndTime);
        goods.setRaisePriceRange(raisePriceRange);
        goods.setLabels(labels);
        goods.setGoodsAttribute(goodsAttribute);
        goods.setImgs(imgs);
        goods.setTotal(total);
//        goods.setState();
        if (id==null){
            goods.setSource(2);
            goods.setType(2);
            goods.setIsOnline(1);
            goods.setOrgId(orgId);

            goods.setSoldOut(0);
            goods.setCreateTime(new Date());
            goodsService.insert(goods);
        }else {
            Goods goods1 = goodsService.selectByPrimaryKey(id);
            if(!Objects.equals(orgId,goods1.getOrgId())){
                throw new ApiException("机构异常");
            }
            goods.setId(id);
            goods.setModifyTime(new Date());
            goodsService.updateByPrimaryKeySelective(goods);
        }

    }

    @PostMapping("/dismount")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "商品下架")
    public void OrgGoodsDismount(@ApiParam(required = true,name = "goodsId",value = "商品id") int goodsId,
                                MobileInfo mobileInfo,
                                @ApiParam(required = true,name = "reasonOfDismounting",value = "下架原因") String reasonOfDismounting){
        //判断当前商品是否是下架状态
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
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
        Goods updateGoods = new Goods();
        updateGoods.setId(goodsId);
        updateGoods.setIsOnline(0);
        updateGoods.setReasonOfDismounting(reasonOfDismounting);
        goodsService.updateByPrimaryKeySelective(updateGoods);
    }

    @PostMapping("/online")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "商品上架")
    public void OrgGoodsonline(@ApiParam(required = true,name = "goodsId",value = "商品id") int goodsId,
                              MobileInfo mobileInfo){
        //判断当前商品是否是下架状态
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        if (!Objects.equals(orgId,goods.getOrgId())){
            throw new ApiException(400,"机构异常");
        }
        if(goods.getIsOnline()==1){
            throw new ApiException(MEnumError.ONLINE_STATE);
        }
        if(goods.getIsOnline()==-1){
            throw new ApiException(MEnumError.DELETE_STATE);
        }
        goods.setIsOnline(1);
        goods.setReasonOfDismounting("");
        goodsService.updateByPrimaryKeySelective(goods);
    }

    @GetMapping("/getGoods")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "根据(状态)获取发布商品")
    public  List<Goods> getGoodsByOnline(@ApiParam(required = true,value = "0下架1上架2新增待上架") int state,
                                   MobileInfo mobileInfo,
                                   @ApiParam(value = "分页(不传则不分页)") Integer pageNum,
                                   @ApiParam(value = "分页(不传则不分页)") String pageSize,
                                   @ApiParam(value = "商品名称")String goodsName){
        int userId = mobileInfo.getUserId();
        if (BaseUtils.isAnyBlank(pageNum,pageSize)){
            startPage();
        }
        List<Integer> sources = new ArrayList<>();
        sources.add(6);sources.add(7);
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andSourceIn(sources);
        criteria.andGoodsOwnerEqualTo(userId);
        criteria.andIsOnlineEqualTo(state);
        if(StringUtils.isNotBlank(goodsName)){
            criteria.andNameLike("%"+goodsName+"%");
        }
        goodsExample.setOrderByClause(" create_time desc");
        List<Goods> goodsList  = goodsService.selectByExample(goodsExample);
        return  goodsList;
//        if(pageNum!=null && pageSize!=null){
//            return goodsList;
//        }else{
//            return ok(goodsList);
//        }
    }

    @GetMapping("/goods/delete")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "删除发布的商品")
    public void deleteGood(MobileInfo mobileInfo,
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
        
    }


}
