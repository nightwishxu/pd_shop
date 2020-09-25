package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.CommentReply;
import com.paidang.dao.model.CommentReplyExample;
import com.paidang.service.CommentReplyService;
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
@RequestMapping("commentReply")
@Controller
public class CommentReplyController extends CoreController{

    @Autowired
    private CommentReplyService commentReplyService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	CommentReplyExample example = new CommentReplyExample();
    	
    	List<CommentReply> list = commentReplyService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(CommentReply commentReply){
    	if (commentReply.getId() == null){
    		commentReplyService.insert(commentReply);
    	}else{
    		commentReplyService.updateByPrimaryKeySelective(commentReply);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	CommentReply commentReply = commentReplyService.selectByPrimaryKey(id);
       	return ok(commentReply);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		commentReplyService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}