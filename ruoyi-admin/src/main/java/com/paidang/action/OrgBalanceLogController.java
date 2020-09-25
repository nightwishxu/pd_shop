package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.OrgBalanceLog;
import com.paidang.dao.model.OrgBalanceLogExample;
import com.paidang.service.OrgBalanceLogService;
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
@RequestMapping("orgBalanceLog")
@Controller
public class OrgBalanceLogController extends CoreController{

    @Autowired
    private OrgBalanceLogService orgBalanceLogService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	OrgBalanceLogExample example = new OrgBalanceLogExample();
    	
    	List<OrgBalanceLog> list = orgBalanceLogService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(OrgBalanceLog orgBalanceLog){
    	if (orgBalanceLog.getId() == null){
    		orgBalanceLogService.insert(orgBalanceLog);
    	}else{
    		orgBalanceLogService.updateByPrimaryKeySelective(orgBalanceLog);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	OrgBalanceLog orgBalanceLog = orgBalanceLogService.selectByPrimaryKey(id);
       	return ok(orgBalanceLog);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		orgBalanceLogService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}