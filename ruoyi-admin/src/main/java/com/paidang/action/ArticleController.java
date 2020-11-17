package com.paidang.action;

import com.base.action.CoreController;
import com.base.util.StringUtils;
import com.paidang.dao.model.Article;
import com.paidang.dao.model.ArticleExample;
import com.paidang.daoEx.model.ArticleEx;
import com.paidang.service.ArticleService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
@author sun
*/
@RequestMapping("article")
@Controller
public class ArticleController extends CoreController{

    @Autowired
    private ArticleService articleService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(ArticleEx articleEx){
    	startPage();

		List<ArticleEx> list = articleService.findList(articleEx);

      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(Article article){
    	if (article.getId() == null){
    		article.setCreateTime(new Date());
			article.setCommentCount(0);
			article.setCollectCount(0);
			article.setPraiseCount(0);
			article.setClickCount(0);
			article.setReleaseTime(new Date());
    		articleService.insert(article);
    	}else{
    		article.setUpdateTime(new Date());
    		articleService.updateByPrimaryKeySelective(article);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	Article article = articleService.selectByPrimaryKey(id);
       	return ok(article);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		articleService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}