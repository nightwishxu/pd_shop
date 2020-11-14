package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.UserReport;
import com.paidang.dao.model.UserReportExample;
import com.paidang.service.UserReportService;
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
@RequestMapping("userReport")
@Controller
public class UserReportController extends CoreController{

    @Autowired
    private UserReportService userReportService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	UserReportExample example = new UserReportExample();
    	
    	List<UserReport> list = userReportService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(UserReport userReport){
    	if (userReport.getId() == null){
    		userReportService.insert(userReport);
    	}else{
    		userReportService.updateByPrimaryKeySelective(userReport);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	UserReport userReport = userReportService.selectByPrimaryKey(id);
       	return ok(userReport);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userReportService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}