package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.Comment;
import com.paidang.dao.model.CommentExample;
import com.paidang.service.CommentService;
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
@RequestMapping("comment")
@Controller
public class CommentController extends CoreController{

    @Autowired
    private CommentService commentService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	CommentExample example = new CommentExample();
    	
    	List<Comment> list = commentService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(Comment comment){
    	if (comment.getId() == null){
    		commentService.insert(comment);
    	}else{
    		commentService.updateByPrimaryKeySelective(comment);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	Comment comment = commentService.selectByPrimaryKey(id);
       	return ok(comment);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		commentService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}