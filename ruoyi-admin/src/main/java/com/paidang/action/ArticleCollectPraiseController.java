package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.ArticleCollectPraise;
import com.paidang.dao.model.ArticleCollectPraiseExample;
import com.paidang.service.ArticleCollectPraiseService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.StringTokenizer;

/**
@author sun
*/
@RequestMapping("articleCollectPraise")
@Controller
public class ArticleCollectPraiseController extends CoreController{

    @Autowired
    private ArticleCollectPraiseService articleCollectPraiseService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	ArticleCollectPraiseExample example = new ArticleCollectPraiseExample();
    	
    	List<ArticleCollectPraise> list = articleCollectPraiseService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(ArticleCollectPraise articleCollectPraise){
    	if (articleCollectPraise.getId() == null){
    		articleCollectPraiseService.insert(articleCollectPraise);
    	}else{
    		articleCollectPraiseService.updateByPrimaryKeySelective(articleCollectPraise);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	ArticleCollectPraise articleCollectPraise = articleCollectPraiseService.selectByPrimaryKey(id);
       	return ok(articleCollectPraise);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		articleCollectPraiseService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}