package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.GoodsAuction;
import com.paidang.dao.model.GoodsAuctionExample;
import com.paidang.service.GoodsAuctionService;
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
@RequestMapping("goodsAuction")
@Controller
public class GoodsAuctionController extends CoreController{

    @Autowired
    private GoodsAuctionService goodsAuctionService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	GoodsAuctionExample example = new GoodsAuctionExample();
    	
    	List<GoodsAuction> list = goodsAuctionService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(GoodsAuction goodsAuction){
    	if (goodsAuction.getId() == null){
    		goodsAuctionService.insert(goodsAuction);
    	}else{
    		goodsAuctionService.updateByPrimaryKeySelective(goodsAuction);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	GoodsAuction goodsAuction = goodsAuctionService.selectByPrimaryKey(id);
       	return ok(goodsAuction);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		goodsAuctionService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}