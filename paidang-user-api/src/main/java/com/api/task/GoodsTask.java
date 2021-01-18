package com.api.task;


import com.api.service.ApiUserPayService;
import com.base.util.DateUtil;
import com.paidang.dao.model.Goods;
import com.paidang.dao.model.GoodsAuction;
import com.paidang.dao.model.GoodsExample;
import com.paidang.dao.model.OrderExample;
import com.paidang.daoEx.model.OrderEx;
import com.paidang.service.GoodsAuctionService;
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

    @Autowired
    private GoodsAuctionService goodsAuctionService;


    /**
     * 竞拍商品处理
     */
    @Scheduled(cron ="0 */3 * * * ?")
    public void endAuctionGoods(){

        goodsService.offlineAuctionGoods();

        GoodsExample example = new GoodsExample();
        example.createCriteria().andDealTypeEqualTo(2).andAuctionEndTimeLessThan(new Date())
                .andIsOnlineEqualTo(1).andIsVerfiyEqualTo(2).andStateEqualTo(1).andTotalGreaterThanOrEqualTo(1)
                .andMaxAutionIdIsNotNull();

        List<Goods> goods = goodsService.selectByExample(example);
        for (Goods good : goods) {
            try {
                GoodsAuction goodsAuction = goodsAuctionService.selectByPrimaryKey(good.getMaxAutionId());
                apiUserPayService.createOrder(goodsAuction.getUserId(),good.getId(),null,null,1);
                good.setIsOnline(0);
                good.setMaxAuction(null);
                good.setMaxAutionId(null);
                goodsService.sellAuctionGoods(good);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
