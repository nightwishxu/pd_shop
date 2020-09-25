package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.Express;
import com.paidang.dao.model.ExpressExample;
import com.paidang.service.ExpressService;
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
@RequestMapping("express")
@Controller
public class ExpressController extends CoreController{

    @Autowired
    private ExpressService expressService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	ExpressExample example = new ExpressExample();
    	
    	List<Express> list = expressService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(Express express){
    	if (express.getId() == null){
    		expressService.insert(express);
    	}else{
    		expressService.updateByPrimaryKeySelective(express);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	Express express = expressService.selectByPrimaryKey(id);
       	return ok(express);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		expressService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}