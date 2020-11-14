package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.PrivateMessage;
import com.paidang.dao.model.PrivateMessageExample;
import com.paidang.service.PrivateMessageService;
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
@RequestMapping("privateMessage")
@Controller
public class PrivateMessageController extends CoreController{

    @Autowired
    private PrivateMessageService privateMessageService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows){

    	startPage();
    	PrivateMessageExample example = new PrivateMessageExample();
    	
    	List<PrivateMessage> list = privateMessageService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(PrivateMessage privateMessage){
    	if (privateMessage.getId() == null){
    		privateMessageService.insert(privateMessage);
    	}else{
    		privateMessageService.updateByPrimaryKeySelective(privateMessage);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	PrivateMessage privateMessage = privateMessageService.selectByPrimaryKey(id);
       	return ok(privateMessage);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		privateMessageService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}