package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.PawnAuction;
import com.paidang.dao.model.PawnAuctionExample;
import com.paidang.service.PawnAuctionService;
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
@RequestMapping("pawnAuction")
@Controller
public class PawnAuctionController extends CoreController{

    @Autowired
    private PawnAuctionService pawnAuctionService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	PawnAuctionExample example = new PawnAuctionExample();
    	
    	List<PawnAuction> list = pawnAuctionService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(PawnAuction pawnAuction){
    	if (pawnAuction.getId() == null){
    		pawnAuctionService.insert(pawnAuction);
    	}else{
    		pawnAuctionService.updateByPrimaryKeySelective(pawnAuction);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	PawnAuction pawnAuction = pawnAuctionService.selectByPrimaryKey(id);
       	return ok(pawnAuction);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		pawnAuctionService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}