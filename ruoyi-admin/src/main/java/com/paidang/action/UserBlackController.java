package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.UserBlack;
import com.paidang.dao.model.UserBlackExample;
import com.paidang.service.UserBlackService;
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
@RequestMapping("userBlack")
@Controller
public class UserBlackController extends CoreController{

    @Autowired
    private UserBlackService userBlackService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	UserBlackExample example = new UserBlackExample();
    	
    	List<UserBlack> list = userBlackService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(UserBlack userBlack){
    	if (userBlack.getId() == null){
    		userBlackService.insert(userBlack);
    	}else{
    		userBlackService.updateByPrimaryKeySelective(userBlack);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	UserBlack userBlack = userBlackService.selectByPrimaryKey(id);
       	return ok(userBlack);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userBlackService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}