package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.UserBalanceLog;
import com.paidang.dao.model.UserBalanceLogExample;
import com.paidang.service.UserBalanceLogService;
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
@RequestMapping("userBalanceLog")
@Controller
public class UserBalanceLogController extends CoreController{

    @Autowired
    private UserBalanceLogService userBalanceLogService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	UserBalanceLogExample example = new UserBalanceLogExample();
    	
    	List<UserBalanceLog> list = userBalanceLogService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(UserBalanceLog userBalanceLog){
    	if (userBalanceLog.getId() == null){
    		userBalanceLogService.insert(userBalanceLog);
    	}else{
    		userBalanceLogService.updateByPrimaryKeySelective(userBalanceLog);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	UserBalanceLog userBalanceLog = userBalanceLogService.selectByPrimaryKey(id);
       	return ok(userBalanceLog);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userBalanceLogService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}