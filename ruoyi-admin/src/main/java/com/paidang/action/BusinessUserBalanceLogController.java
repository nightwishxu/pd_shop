package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.BusinessUserBalanceLog;
import com.paidang.dao.model.BusinessUserBalanceLogExample;
import com.paidang.service.BusinessUserBalanceLogService;
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
@RequestMapping("businessUserBalanceLog")
@Controller
public class BusinessUserBalanceLogController extends CoreController{

    @Autowired
    private BusinessUserBalanceLogService businessUserBalanceLogService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	BusinessUserBalanceLogExample example = new BusinessUserBalanceLogExample();
    	
    	List<BusinessUserBalanceLog> list = businessUserBalanceLogService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(BusinessUserBalanceLog businessUserBalanceLog){
    	if (businessUserBalanceLog.getId() == null){
    		businessUserBalanceLogService.insert(businessUserBalanceLog);
    	}else{
    		businessUserBalanceLogService.updateByPrimaryKeySelective(businessUserBalanceLog);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	BusinessUserBalanceLog businessUserBalanceLog = businessUserBalanceLogService.selectByPrimaryKey(id);
       	return ok(businessUserBalanceLog);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		businessUserBalanceLogService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}