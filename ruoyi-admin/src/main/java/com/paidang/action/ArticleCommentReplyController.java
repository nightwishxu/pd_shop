package com.paidang.action;

import com.base.action.CoreController;

import com.paidang.dao.model.ArticleCommentReply;
import com.paidang.dao.model.ArticleCommentReplyExample;
import com.paidang.service.ArticleCommentReplyService;
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
@RequestMapping("articleCommentReply")
@Controller
public class ArticleCommentReplyController extends CoreController{

    @Autowired
    private ArticleCommentReplyService articleCommentReplyService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows,Integer commentId){
    	startPage();
    	ArticleCommentReplyExample example = new ArticleCommentReplyExample();

		ArticleCommentReplyExample.Criteria criteria = example.createCriteria();


		List<ArticleCommentReply> list = articleCommentReplyService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(ArticleCommentReply articleCommentReply){
    	if (articleCommentReply.getId() == null){
    		articleCommentReplyService.insert(articleCommentReply);
    	}else{
    		articleCommentReplyService.updateByPrimaryKeySelective(articleCommentReply);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	ArticleCommentReply articleCommentReply = articleCommentReplyService.selectByPrimaryKey(id);
       	return ok(articleCommentReply);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		articleCommentReplyService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}