package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.CollectPraise;
import com.paidang.dao.model.CollectPraiseExample;
import com.paidang.service.CollectPraiseService;
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
@RequestMapping("collectPraise")
@Controller
public class CollectPraiseController extends CoreController{

    @Autowired
    private CollectPraiseService collectPraiseService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	CollectPraiseExample example = new CollectPraiseExample();
    	
    	List<CollectPraise> list = collectPraiseService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(CollectPraise collectPraise){
    	if (collectPraise.getId() == null){
    		collectPraiseService.insert(collectPraise);
    	}else{
    		collectPraiseService.updateByPrimaryKeySelective(collectPraise);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	CollectPraise collectPraise = collectPraiseService.selectByPrimaryKey(id);
       	return ok(collectPraise);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		collectPraiseService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}