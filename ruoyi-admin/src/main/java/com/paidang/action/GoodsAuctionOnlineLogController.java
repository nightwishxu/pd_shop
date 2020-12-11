package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.GoodsAuctionOnlineLog;
import com.paidang.dao.model.GoodsAuctionOnlineLogExample;
import com.paidang.service.GoodsAuctionOnlineLogService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
@author sun
*/
@RequestMapping("goodsAuctionOnlineLog")
@Controller
public class GoodsAuctionOnlineLogController extends CoreController {

    @Autowired
    private GoodsAuctionOnlineLogService goodsAuctionOnlineLogService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	GoodsAuctionOnlineLogExample example = new GoodsAuctionOnlineLogExample();
    	
    	List<GoodsAuctionOnlineLog> list = goodsAuctionOnlineLogService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(GoodsAuctionOnlineLog goodsAuctionOnlineLog){
    	if (goodsAuctionOnlineLog.getId() == null){
    		goodsAuctionOnlineLogService.insert(goodsAuctionOnlineLog);
    	}else{
    		goodsAuctionOnlineLogService.updateByPrimaryKeySelective(goodsAuctionOnlineLog);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	GoodsAuctionOnlineLog goodsAuctionOnlineLog = goodsAuctionOnlineLogService.selectByPrimaryKey(id);
       	return ok(goodsAuctionOnlineLog);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		goodsAuctionOnlineLogService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}