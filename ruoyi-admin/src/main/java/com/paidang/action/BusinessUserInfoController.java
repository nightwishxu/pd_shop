package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.BusinessUserInfo;
import com.paidang.dao.model.BusinessUserInfoExample;
import com.paidang.service.BusinessUserInfoService;
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
@RequestMapping("businessUserInfo")
@Controller
public class BusinessUserInfoController extends CoreController{

    @Autowired
    private BusinessUserInfoService businessUserInfoService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	BusinessUserInfoExample example = new BusinessUserInfoExample();
    	
    	List<BusinessUserInfo> list = businessUserInfoService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(BusinessUserInfo businessUserInfo){
    	if (businessUserInfo.getId() == null){
    		businessUserInfoService.insert(businessUserInfo);
    	}else{
    		businessUserInfoService.updateByPrimaryKeySelective(businessUserInfo);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	BusinessUserInfo businessUserInfo = businessUserInfoService.selectByPrimaryKey(id);
       	return ok(businessUserInfo);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		businessUserInfoService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}