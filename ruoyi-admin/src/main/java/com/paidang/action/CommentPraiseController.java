package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.CommentPraise;
import com.paidang.dao.model.CommentPraiseExample;
import com.paidang.service.CommentPraiseService;
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
@RequestMapping("commentPraise")
@Controller
public class CommentPraiseController extends CoreController{

    @Autowired
    private CommentPraiseService commentPraiseService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows){
		startPage();
    	CommentPraiseExample example = new CommentPraiseExample();
    	
    	List<CommentPraise> list = commentPraiseService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(CommentPraise commentPraise){
    	if (commentPraise.getId() == null){
    		commentPraiseService.insert(commentPraise);
    	}else{
    		commentPraiseService.updateByPrimaryKeySelective(commentPraise);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	CommentPraise commentPraise = commentPraiseService.selectByPrimaryKey(id);
       	return ok(commentPraise);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		commentPraiseService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}