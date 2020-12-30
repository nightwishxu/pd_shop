package com.paidang.action;


import com.base.action.CoreController;
import com.base.api.ApiException;
import com.base.util.JSONUtils;
import com.item.daoEx.model.UserEx;
import com.paidang.dao.AuthUserMapper;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.GoodsEx;
import com.paidang.daoEx.model.OrderEx;
import com.paidang.service.BusinessUserBalanceLogService;
import com.paidang.service.BusinessUserInfoService;
import com.paidang.service.GoodsService;
import com.paidang.service.OrderService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController extends CoreController {
    @Resource
    private AuthUserMapper authUserMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BusinessUserInfoService businessUserInfoService;
    @Autowired
    private BusinessUserBalanceLogService businessUserBalanceLogService;

    /**
     * 商家端人员列表
     * @param page
     * @param rows
     * @param nickOrMobile
     * @return
     */
    @PostMapping("/user/list")
    public TableDataInfo list(String nickOrMobile){
        startPage();
        List<UserEx> userExes =  authUserMapper.authUserList(nickOrMobile);
        return page(userExes);
    }

    /**
     * 商家端 商品列表
     * @param page
     * @param rows
     * @param goodsName
     * @return
     */
    @PostMapping("/goods/list")
    public TableDataInfo goodsList(String goodsName){
        startPage();
        List<GoodsEx> goodsList = goodsService.selectBusinessGoodsList(goodsName);
        return page(goodsList);
    }

    @PostMapping("/goods/dismount")
    public Ret OrgGoodsDismount(int goodsId, String reasonOfDismounting){
        //判断当前商品是否是下架状态
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
        if(goods.getIsOnline()==0||goods.getIsOnline()==2){
           return new Ret(-1,"当前商品状态不能下架");
        }
        Goods updateGoods = new Goods();
        updateGoods.setId(goodsId);
        updateGoods.setIsOnline(0);
        updateGoods.setReasonOfDismounting(reasonOfDismounting);
        goodsService.updateByPrimaryKeySelective(updateGoods);
        return ok();
    }

    @PostMapping("/order/list")
    public TableDataInfo orderList(String goodsName,String orderCode,String state){
        startPage();
        List<OrderEx> orderExes = orderService.getBusinessOrder(goodsName,orderCode,state,null);
        return page(orderExes);
    }

    @PostMapping("/user/finance")
    public Map<String,Object> financeDetails(int userId){
        Map<String,Object> ret = new HashMap<>();
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria goodsCriteria = goodsExample.createCriteria();
        goodsCriteria.andGoodsOwnerEqualTo(userId);
        List<Goods> goodsList = goodsService.selectByExample(goodsExample);
        List<Integer> goodsIds = new ArrayList<>();
        for(Goods good : goodsList){
            goodsIds.add(good.getId());
        }
        BigDecimal toBePai = getTotalByOrderState(goodsIds,1);//待付款
        BigDecimal toBeDelivered = getTotalByOrderState(goodsIds,2);//待发货
        BigDecimal toBeHarvested = getTotalByOrderState(goodsIds,3);//待收货
        ret.put("toBePai",toBePai);
        ret.put("toBeDelivered",toBeDelivered);
        ret.put("toBeHarvested",toBeHarvested);
        BusinessUserInfoExample businessUserInfoExample = new BusinessUserInfoExample();
        BusinessUserInfoExample.Criteria criteria = businessUserInfoExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<BusinessUserInfo> businessUserInfos = businessUserInfoService.selectByExample(businessUserInfoExample);
        if (businessUserInfos.size()<1){
            ret.put("total",0);
        }else if(businessUserInfos.size()>1){
            throw new ApiException(-1,"存在异常数据");
        }else{
            BusinessUserInfo businessUserInfo = businessUserInfos.get(0);
            ret.put("total",businessUserInfo.getTotal());
        }
        ret.put("remark","toBePai:待付款,toBeDelivered:待发货,toBeHarvested:待收货,total:余额");
        return ret;
    }

    @PostMapping("/finance/info")
    public List<BusinessUserBalanceLog> getFinanceDetails(int userId){
        BusinessUserBalanceLogExample businessUserBalanceLogExample = new BusinessUserBalanceLogExample();
        BusinessUserBalanceLogExample.Criteria criteria = businessUserBalanceLogExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        businessUserBalanceLogExample.setOrderByClause("create_time");
        List<BusinessUserBalanceLog> userBalanceLogs = businessUserBalanceLogService.selectByExample(businessUserBalanceLogExample);
        return userBalanceLogs;
    }

    public BigDecimal getTotalByOrderState(List<Integer> goodsIds,int state){
        OrderExample orderExample = new OrderExample();
        if(goodsIds.isEmpty()){
            return BigDecimal.ZERO;
        }
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andStateEqualTo(state);
        criteria.andGoodsIdIn(goodsIds);
        List<Order> orders = orderService.selectByExample(orderExample);
        BigDecimal total = BigDecimal.ZERO;
        for(Order order: orders){
            total = total.add(order.getGoodsPrice());
        }
        return total;
    }

}
