package com.item.task;


import com.base.util.DateUtil;
import com.paidang.dao.model.Goods;
import com.paidang.dao.model.GoodsExample;
import com.paidang.dao.model.OrderExample;
import com.paidang.daoEx.model.OrderEx;
import com.paidang.service.GoodsService;
import com.paidang.service.OrderService;
import com.util.PaidangConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class GoodsTask {
    private static Logger logger = LoggerFactory.getLogger(GoodsTask.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;


    /**
     * 订单列表查询
     */
    //每24小时执行一次 0 */1 * * * ?
//    @Scheduled(cron ="0 */2 * * * ?")
//    public void pawn(){
//        long start = System.currentTimeMillis();
//
//        //查询24小时未付款的订单
//        OrderExample example = new OrderExample();
//        example.createCriteria().andStateEqualTo(1);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("time", DateUtil.offsetSecond(new Date(),-PaidangConst.ORDER_TIME));
//        List<OrderEx> list =   orderService.selectByTask(map);
//
//        for (OrderEx ex : list){
//                ex.setState(-1);
//                orderService.updateByPrimaryKeySelective(ex);
//            }
//
//        }


    /**
     * 竞拍商品处理
     */
//    @Scheduled(cron ="0 */5 * * * ?")
//    public void endAuctionGoods(){
//
//        GoodsExample example = new GoodsExample();
//        example.createCriteria().andDealTypeEqualTo(2).andAuctionEndTimeGreaterThan(new Date())
//                .andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andStateEqualTo(1).andTotalGreaterThan(1)
//                .andMaxAutionIdIsNotNull().andMaxAuctionIsNotNull();
//
//        List<Goods> goods = goodsService.selectByExample(example);
//        for (Goods good : goods) {
//
//        }
//
//
//    }

}
