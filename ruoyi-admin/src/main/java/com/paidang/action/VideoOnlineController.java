package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.VideoOnline;
import com.paidang.dao.model.VideoOnlineExample;
import com.paidang.service.VideoOnlineService;
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
@RequestMapping("videoOnline")
@Controller
public class VideoOnlineController extends CoreController{

    @Autowired
    private VideoOnlineService videoOnlineService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	VideoOnlineExample example = new VideoOnlineExample();
    	example.setOrderByClause("create_time desc");
    	List<VideoOnline> list = videoOnlineService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(VideoOnline videoOnline){
    	if (videoOnline.getId() == null){
    		videoOnline.setViewCnt(0);
    		videoOnlineService.insert(videoOnline);
    	}else{
    		videoOnlineService.updateByPrimaryKeySelective(videoOnline);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	VideoOnline videoOnline = videoOnlineService.selectByPrimaryKey(id);
       	return ok(videoOnline);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		videoOnlineService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }
}