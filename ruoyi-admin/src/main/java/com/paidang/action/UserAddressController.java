package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.UserAddress;
import com.paidang.dao.model.UserAddressExample;
import com.paidang.service.UserAddressService;
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
@RequestMapping("userAddress")
@Controller
public class UserAddressController extends CoreController{

    @Autowired
    private UserAddressService userAddressService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	UserAddressExample example = new UserAddressExample();
    	
    	List<UserAddress> list = userAddressService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(UserAddress userAddress){
    	if (userAddress.getId() == null){
    		userAddressService.insert(userAddress);
    	}else{
    		userAddressService.updateByPrimaryKeySelective(userAddress);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	UserAddress userAddress = userAddressService.selectByPrimaryKey(id);
       	return ok(userAddress);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userAddressService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}