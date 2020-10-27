package com.weixin.action;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.action.CoreController;
import com.base.util.JSONUtils;
import com.weixin.dao.model.Article;
import com.weixin.dao.model.ArticleExample;
import com.weixin.service.WxArticleService;

@RequestMapping("wxarticle")
@Controller
public class WxArticleController extends CoreController{

    @Autowired
    private WxArticleService wxArticleService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows, String resId){
    	startPage();
    	ArticleExample example = new ArticleExample();
    	example.createCriteria().andResIdEqualTo(resId);
    	example.setOrderByClause("sort_order,create_time");
    	List<Article> list = wxArticleService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public String save(Article article){
    	if (StringUtils.isBlank(article.getId())){
    		ArticleExample example = new ArticleExample();
    		example.createCriteria().andResIdEqualTo(article.getResId());
			int cnt = wxArticleService.countByExample(example);
			if(cnt>9){
				return JSONUtils.serialize(new Ret(1,"超出图文上限：10个"));
			}
    		
    		article.setCreateTime(new Date());
    		wxArticleService.insert(article);
    	}else{
    		wxArticleService.updateByPrimaryKeySelective(article);
    	}
       	return JSONUtils.serialize(new Ret(0));
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public String find(String id){
    	Article article = wxArticleService.selectByPrimaryKey(id);
       	return JSONUtils.serialize(article);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public String del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		wxArticleService.deleteByPrimaryKey(str);
    	}
       	return JSONUtils.serialize(new Ret(0));
    }

    
    @RequestMapping("/findByResId")
	@ResponseBody 
    public String findByResId(String id){
    	ArticleExample example = new ArticleExample();
    	example.createCriteria().andResIdEqualTo(id);
    	example.setOrderByClause("sort_order,create_time");
    	List<Article> list = wxArticleService.selectByExample(example);
       	return JSONUtils.serialize(list);
    }
}
