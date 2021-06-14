package com.item.task;

import com.paidang.service.ExpressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ExpressTask {
    private static Logger logger = LoggerFactory.getLogger(ExpressTask.class);

    @Autowired
    private ExpressService expressService;

    /**
     * 定时查询快递(每1小时执行一次)
     */
    @Scheduled(cron = "0 0 */1 * * ?")
    public void query(){
        // 2021年6月14日 改为每1小时执行一次
        logger.debug("查询快递");
        logger.info("定时任务-ExpressCancle开始" );
        expressService.queryAuto();
    }

    //查询更新机构从平台取回绝当品的快递状态
    public void updateOrgFetchBackExpress(){
        logger.debug("查询快递--机构取回");
        expressService.updateOrgFetchBackExpress();
    }
    //更新快递表机构/平台寄给当户的数据，并更新相应字段
    public void updateSendToWinnerExpress(){
        logger.debug("查询快递--机构/平台寄给当户");
        expressService.updateSendToWinnerExpress();
    }

}
