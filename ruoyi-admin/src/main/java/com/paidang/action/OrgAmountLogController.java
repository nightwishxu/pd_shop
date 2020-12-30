package com.paidang.action;

import com.base.action.CoreController;

import com.item.service.OrgAmountLogService;
import com.paidang.dao.model.OrgAmountLog;
import com.paidang.dao.model.OrgAmountLogExample;
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
@RequestMapping("orgAmountLog")
@Controller
public class OrgAmountLogController extends CoreController {

    @Autowired
    private OrgAmountLogService orgAmountLogService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	OrgAmountLogExample example = new OrgAmountLogExample();
    	
    	List<OrgAmountLog> list = orgAmountLogService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(OrgAmountLog orgAmountLog){
    	if (orgAmountLog.getId() == null){
    		orgAmountLogService.insert(orgAmountLog);
    	}else{
    		orgAmountLogService.updateByPrimaryKeySelective(orgAmountLog);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(String id){
    	OrgAmountLog orgAmountLog = orgAmountLogService.selectByPrimaryKey(id);
       	return ok(orgAmountLog);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		orgAmountLogService.deleteByPrimaryKey(str);
    	}
       	return ok();
    }


}