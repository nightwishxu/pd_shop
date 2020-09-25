package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.OrgNotify;
import com.paidang.dao.model.OrgNotifyExample;
import com.paidang.service.OrgNotifyService;
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
@RequestMapping("orgNotify")
@Controller
public class OrgNotifyController extends CoreController{

    @Autowired
    private OrgNotifyService orgNotifyService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	OrgNotifyExample example = new OrgNotifyExample();
    	
    	List<OrgNotify> list = orgNotifyService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(OrgNotify orgNotify){
    	if (orgNotify.getId() == null){
    		orgNotifyService.insert(orgNotify);
    	}else{
    		orgNotifyService.updateByPrimaryKeySelective(orgNotify);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	OrgNotify orgNotify = orgNotifyService.selectByPrimaryKey(id);
       	return ok(orgNotify);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		orgNotifyService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}