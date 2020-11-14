package com.paidang.action;

import com.base.action.CoreController;

import com.paidang.dao.model.CommentReportLog;
import com.paidang.dao.model.CommentReportLogExample;
import com.paidang.service.CommentReportLogService;
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
@RequestMapping("commentReportLog")
@Controller

public class CommentReportLogController extends CoreController{

    @Autowired
    private CommentReportLogService commentReportLogService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows){
    	CommentReportLogExample example = new CommentReportLogExample();
    	
    	List<CommentReportLog> list = commentReportLogService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(CommentReportLog commentReportLog){
    	if (commentReportLog.getId() == null){
    		commentReportLogService.insert(commentReportLog);
    	}else{
    		commentReportLogService.updateByPrimaryKeySelective(commentReportLog);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	CommentReportLog commentReportLog = commentReportLogService.selectByPrimaryKey(id);
       	return ok(commentReportLog);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		commentReportLogService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}