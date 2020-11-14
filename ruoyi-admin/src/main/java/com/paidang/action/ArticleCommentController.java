package com.paidang.action;

import com.base.action.CoreController;
import com.base.util.StringUtil;
import com.base.util.StringUtils;
import com.paidang.dao.model.ArticleComment;
import com.paidang.dao.model.ArticleCommentExample;
import com.paidang.service.ArticleCommentService;
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
@RequestMapping("articleComment")
@Controller
public class ArticleCommentController extends CoreController{

    @Autowired
    private ArticleCommentService articleCommentService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows,String content,Integer isReport){
    	startPage();
    	ArticleCommentExample example = new ArticleCommentExample();
		ArticleCommentExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(content)){
			criteria.andContentLike("%"+content+"%");
		}
		if (isReport!=null){
			criteria.andIsReportEqualTo(isReport);
		}
		example.setOrderByClause("create_time desc");
		List<ArticleComment> list = articleCommentService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(ArticleComment articleComment){
    	if (articleComment.getId() == null){
    		articleCommentService.insert(articleComment);
    	}else{
    		articleCommentService.updateByPrimaryKeySelective(articleComment);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	ArticleComment articleComment = articleCommentService.selectByPrimaryKey(id);
       	return ok(articleComment);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		articleCommentService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}