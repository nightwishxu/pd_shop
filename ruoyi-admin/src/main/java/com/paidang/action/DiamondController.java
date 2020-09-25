package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.Diamond;
import com.paidang.dao.model.DiamondExample;
import com.paidang.service.DiamondService;
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
@RequestMapping("diamond")
@Controller
public class DiamondController extends CoreController{

    @Autowired
    private DiamondService diamondService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	DiamondExample example = new DiamondExample();
    	
    	List<Diamond> list = diamondService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(Diamond diamond){
    	if (diamond.getId() == null){
    		diamondService.insert(diamond);
    	}else{
    		diamondService.updateByPrimaryKeySelective(diamond);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	Diamond diamond = diamondService.selectByPrimaryKey(id);
       	return ok(diamond);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		diamondService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}