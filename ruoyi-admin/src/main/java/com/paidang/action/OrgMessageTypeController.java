package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.OrgMessageType;
import com.paidang.dao.model.OrgMessageTypeExample;
import com.paidang.service.OrgMessageTypeService;
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
@RequestMapping("orgMessageType")
@Controller
public class OrgMessageTypeController extends CoreController{

    @Autowired
    private OrgMessageTypeService orgMessageTypeService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	OrgMessageTypeExample example = new OrgMessageTypeExample();
    	
    	List<OrgMessageType> list = orgMessageTypeService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(OrgMessageType orgMessageType){
    	if (orgMessageType.getId() == null){
    		orgMessageTypeService.insert(orgMessageType);
    	}else{
    		orgMessageTypeService.updateByPrimaryKeySelective(orgMessageType);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	OrgMessageType orgMessageType = orgMessageTypeService.selectByPrimaryKey(id);
       	return ok(orgMessageType);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		orgMessageTypeService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}