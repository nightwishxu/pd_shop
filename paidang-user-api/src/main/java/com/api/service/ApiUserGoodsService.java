package com.api.service;

import com.api.view.pay.PayResult;
import com.base.api.ApiException;
import com.paidang.dao.GoodsMapper;
import com.paidang.dao.OrderMapper;
import com.paidang.dao.UserGoodsMapper;
import com.paidang.dao.model.Goods;
import com.paidang.dao.model.GoodsExample;
import com.paidang.dao.model.UserGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class ApiUserGoodsService {

    @Autowired
    private UserGoodsMapper userGoodsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ApiUserPayService apiUserPayService;

    public PayResult createUserGoodsOrder(Integer userId, Integer userGoodsId, Integer addressId){

        UserGoods userGoods=userGoodsMapper.selectByPrimaryKey(userGoodsId);
        if (Objects.equals(userGoods.getUserId(),userId)){
            throw new ApiException(1100,"自身商品不可购买");
        }
        GoodsExample example=new GoodsExample();
        example.createCriteria().andGoodsIdEqualTo(userGoodsId);

        List<Goods> list=goodsMapper.selectByExample(example);

        if (list!=null && list.size()>0){
            Goods goods=list.get(0);
            if ((goods.getTotal()-goods.getSoldOut())<=0){
                throw new ApiException(1100,"商品已卖出");
            }
//            OrderExample example1=new OrderExample();
//            example1.createCriteria().andGoodsIdEqualTo(goods.getId());
//            List<Order> orderList=orderMapper.selectByExample(example1);
//            if (orderList!=null && orderList.size()>0){
//                Order order=orderList.get(0);
//
//            }

            UserGoods entity=new UserGoods();
            entity.setSellStatus(2);
            entity.setId(userGoodsId);
            userGoodsMapper.updateByPrimaryKeySelective(entity);
            return apiUserPayService.createOrder(userId,goods.getId(),null,addressId);
        }else {
            Goods goods=new Goods();
            goods.setCateCode(Integer.valueOf(userGoods.getSellPawnCode()));
            goods.setPrice(userGoods.getSellPrice());
            goods.setGoodsId(userGoods.getId());
            goods.setSource(5);
            goods.setType(2);
            //固定机构 寄卖保管仓库
            goods.setOrgId(1);
            goods.setName(userGoods.getName());
            goods.setCost(userGoods.getSellPrice());
            goods.setTotal(1);
            goods.setSoldOut(0);
            goods.setIsOnline(1);
            goods.setIsVerfiy(2);
            goods.setImg(userGoods.getSellImgs().split(",")[0]);
            goods.setImgs(userGoods.getSellImgs());
            goods.setInfo(userGoods.getSellInfo());
            goods.setWidth("20");
            goods.setHeight("30");
            goods.setCreateTime(new Date());
            goodsMapper.insert(goods);
            UserGoods entity=new UserGoods();
            entity.setSellStatus(2);
            entity.setId(userGoodsId);
            userGoodsMapper.updateByPrimaryKeySelective(entity);
            return apiUserPayService.createOrder(userId,goods.getId(),null,addressId);
        }
    }
}
