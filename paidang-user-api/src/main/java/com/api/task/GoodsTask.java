package com.api.task;


import com.api.service.ApiUserPayService;
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
    private ApiUserPayService apiUserPayService;

    @Autowired
    private GoodsService goodsService;


    /**
     * 竞拍商品处理
     */
    @Scheduled(cron ="0 */5 * * * ?")
    public void endAuctionGoods(){

        GoodsExample example = new GoodsExample();
        example.createCriteria().andDealTypeEqualTo(2).andAuctionEndTimeGreaterThan(new Date())
                .andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andStateEqualTo(1).andTotalGreaterThan(1)
                .andMaxAutionIdIsNotNull().andMaxAuctionIsNotNull();

        List<Goods> goods = goodsService.selectByExample(example);
        for (Goods good : goods) {
            try {
                apiUserPayService.createOrder(good.getMaxAutionId(),good.getId(),null,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
