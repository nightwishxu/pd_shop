package com.item.action;

import com.base.action.CoreController;

import com.item.service.UserAmountLogService;
import com.paidang.dao.model.UserAmountLog;
import com.paidang.dao.model.UserAmountLogExample;
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
@RequestMapping("userAmountLog")
@Controller
public class UserAmountLogController extends CoreController {

    @Autowired
    private UserAmountLogService userAmountLogService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	UserAmountLogExample example = new UserAmountLogExample();
    	
    	List<UserAmountLog> list = userAmountLogService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(UserAmountLog userAmountLog){
    	if (userAmountLog.getId() == null){
    		userAmountLogService.insert(userAmountLog);
    	}else{
    		userAmountLogService.updateByPrimaryKeySelective(userAmountLog);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(String id){
    	UserAmountLog userAmountLog = userAmountLogService.selectByPrimaryKey(id);
       	return ok(userAmountLog);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userAmountLogService.deleteByPrimaryKey(str);
    	}
       	return ok();
    }


}