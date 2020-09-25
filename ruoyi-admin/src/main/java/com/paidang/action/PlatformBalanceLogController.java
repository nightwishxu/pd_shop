package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.PlatformBalanceLog;
import com.paidang.dao.model.PlatformBalanceLogExample;
import com.paidang.service.PlatformBalanceLogService;
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
@RequestMapping("platformBalanceLog")
@Controller
public class PlatformBalanceLogController extends CoreController{

    @Autowired
    private PlatformBalanceLogService platformBalanceLogService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
		startPage();
    	PlatformBalanceLogExample example = new PlatformBalanceLogExample();
    	
    	List<PlatformBalanceLog> list = platformBalanceLogService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(PlatformBalanceLog platformBalanceLog){
    	if (platformBalanceLog.getId() == null){
    		platformBalanceLogService.insert(platformBalanceLog);
    	}else{
    		platformBalanceLogService.updateByPrimaryKeySelective(platformBalanceLog);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	PlatformBalanceLog platformBalanceLog = platformBalanceLogService.selectByPrimaryKey(id);
       	return ok(platformBalanceLog);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		platformBalanceLogService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}