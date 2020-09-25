package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.VideoOnlineComment;
import com.paidang.dao.model.VideoOnlineCommentExample;
import com.paidang.service.VideoOnlineCommentService;
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
@RequestMapping("videoOnlineComment")
@Controller
public class VideoOnlineCommentController extends CoreController{

    @Autowired
    private VideoOnlineCommentService videoOnlineCommentService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	VideoOnlineCommentExample example = new VideoOnlineCommentExample();
    	
    	List<VideoOnlineComment> list = videoOnlineCommentService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(VideoOnlineComment videoOnlineComment){
    	if (videoOnlineComment.getId() == null){
    		videoOnlineCommentService.insert(videoOnlineComment);
    	}else{
    		videoOnlineCommentService.updateByPrimaryKeySelective(videoOnlineComment);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	VideoOnlineComment videoOnlineComment = videoOnlineCommentService.selectByPrimaryKey(id);
       	return ok(videoOnlineComment);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		videoOnlineCommentService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}