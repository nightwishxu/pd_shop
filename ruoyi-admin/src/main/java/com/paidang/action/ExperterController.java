package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.Experter;
import com.paidang.dao.model.ExperterExample;
import com.paidang.daoEx.model.ExperterEx;
import com.paidang.service.ExperterService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
@author sun
*/
@RequestMapping("experter")
@Controller
public class ExperterController extends CoreController{

    @Autowired
    private ExperterService experterService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows, Integer domainId){
    	startPage();
    	ExperterExample example = new ExperterExample();
		Map<String, Object> map = new HashMap<>();
		map.put("domainId",domainId);
		List<ExperterEx> list = experterService.selectList(map);
    	//List<Experter> list = experterService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(Experter experter){
    	if (experter.getId() == null){
    		experterService.insert(experter);
    	}else{
    		experterService.updateByPrimaryKeySelective(experter);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	Experter experter = experterService.selectByPrimaryKey(id);
       	return ok(experter);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		experterService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }

	@RequestMapping("/experterList")
	@ResponseBody
	public List<ExperterEx> experterList(Integer domainId){
		ExperterExample example = new ExperterExample();
		Map<String, Object> map = new HashMap<>();
		map.put("domainId",domainId);
		List<ExperterEx> list = experterService.selectList(map);
		//List<Experter> list = experterService.selectByExample(example);
		return list;
	}


}