package com.item.action;

import com.base.action.CoreController;
import com.item.dao.model.SysDict;
import com.item.dao.model.SysDictExample;
import com.item.service.SysDictService;
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
@RequestMapping("sysDict")
@Controller
public class SysDictController extends CoreController{

    @Autowired
    private SysDictService sysDictService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
		startPage();
    	SysDictExample example = new SysDictExample();
    	
    	List<SysDict> list = sysDictService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(SysDict sysDict){
    	if (sysDict.getId() == null){
    		sysDictService.insert(sysDict);
    	}else{
    		sysDictService.updateByPrimaryKeySelective(sysDict);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	SysDict sysDict = sysDictService.selectByPrimaryKey(id);
       	return ok(sysDict);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		sysDictService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}