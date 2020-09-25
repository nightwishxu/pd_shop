package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.UserReturnAddress;
import com.paidang.dao.model.UserReturnAddressExample;
import com.paidang.service.UserReturnAddressService;
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
@RequestMapping("userReturnAddress")
@Controller
public class UserReturnAddressController extends CoreController{

    @Autowired
    private UserReturnAddressService userReturnAddressService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	UserReturnAddressExample example = new UserReturnAddressExample();
    	
    	List<UserReturnAddress> list = userReturnAddressService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(UserReturnAddress userReturnAddress){
    	if (userReturnAddress.getId() == null){
    		userReturnAddressService.insert(userReturnAddress);
    	}else{
    		userReturnAddressService.updateByPrimaryKeySelective(userReturnAddress);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	UserReturnAddress userReturnAddress = userReturnAddressService.selectByPrimaryKey(id);
       	return ok(userReturnAddress);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userReturnAddressService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


	@RequestMapping("/findByUserId")
	@ResponseBody
	public Ret findByUserId(Integer id){
    	UserReturnAddressExample userReturnAddressExample = new UserReturnAddressExample();
    	UserReturnAddressExample.Criteria criteria = userReturnAddressExample.createCriteria();
    	criteria.andUserIdEqualTo(id);
		List<UserReturnAddress> userReturnAddressList = userReturnAddressService.selectByExample(userReturnAddressExample);
		return ok(userReturnAddressList);
	}

}