package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.OrgAddress;
import com.paidang.dao.model.OrgAddressExample;
import com.paidang.service.OrgAddressService;
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
@RequestMapping("orgAddress")
@Controller
public class OrgAddressController extends CoreController{

    @Autowired
    private OrgAddressService orgAddressService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	OrgAddressExample example = new OrgAddressExample();
    	
    	List<OrgAddress> list = orgAddressService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(OrgAddress orgAddress){
    	if (orgAddress.getId() == null){
    		orgAddressService.insert(orgAddress);
    	}else{
    		orgAddressService.updateByPrimaryKeySelective(orgAddress);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	OrgAddress orgAddress = orgAddressService.selectByPrimaryKey(id);
       	return ok(orgAddress);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		orgAddressService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}