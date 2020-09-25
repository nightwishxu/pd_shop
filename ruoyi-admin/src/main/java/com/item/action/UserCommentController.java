package com.item.action;

import com.base.action.CoreController;
import com.item.dao.model.UserComment;
import com.item.dao.model.UserCommentExample;
import com.item.service.UserCommentService;
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
@RequestMapping("userComment")
@Controller
public class UserCommentController extends CoreController{

    @Autowired
    private UserCommentService userCommentService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
		startPage();
    	UserCommentExample example = new UserCommentExample();
    	
    	List<UserComment> list = userCommentService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(UserComment userComment){
    	if (userComment.getId() == null){
    		userCommentService.insert(userComment);
    	}else{
    		userCommentService.updateByPrimaryKeySelective(userComment);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	UserComment userComment = userCommentService.selectByPrimaryKey(id);
       	return ok(userComment);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userCommentService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}