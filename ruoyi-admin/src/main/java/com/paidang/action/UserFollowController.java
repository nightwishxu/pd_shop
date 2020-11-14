package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.UserFollow;
import com.paidang.dao.model.UserFollowExample;
import com.paidang.service.UserFollowService;
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
@RequestMapping("userFollow")
@Controller
public class UserFollowController extends CoreController{

    @Autowired
    private UserFollowService userFollowService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	UserFollowExample example = new UserFollowExample();
    	
    	List<UserFollow> list = userFollowService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(UserFollow userFollow){
    	if (userFollow.getId() == null){
    		userFollowService.insert(userFollow);
    	}else{
    		userFollowService.updateByPrimaryKeySelective(userFollow);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	UserFollow userFollow = userFollowService.selectByPrimaryKey(id);
       	return ok(userFollow);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userFollowService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}