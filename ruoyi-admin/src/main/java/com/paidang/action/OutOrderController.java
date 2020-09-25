package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.OutOrder;
import com.paidang.dao.model.OutOrderExample;
import com.paidang.service.OutOrderService;
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
@RequestMapping("outOrder")
@Controller
public class OutOrderController extends CoreController{

    @Autowired
    private OutOrderService outOrderService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	OutOrderExample example = new OutOrderExample();
    	
    	List<OutOrder> list = outOrderService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(OutOrder outOrder){
    	if (outOrder.getId() == null){
    		outOrderService.insert(outOrder);
    	}else{
    		outOrderService.updateByPrimaryKeySelective(outOrder);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	OutOrder outOrder = outOrderService.selectByPrimaryKey(id);
       	return ok(outOrder);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		outOrderService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}