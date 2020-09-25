package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.UserBankCard;
import com.paidang.dao.model.UserBankCardExample;
import com.paidang.service.UserBankCardService;
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
@RequestMapping("userBankCard")
@Controller
public class UserBankCardController extends CoreController{

    @Autowired
    private UserBankCardService userBankCardService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	UserBankCardExample example = new UserBankCardExample();
    	
    	List<UserBankCard> list = userBankCardService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(UserBankCard userBankCard){
    	if (userBankCard.getId() == null){
    		userBankCardService.insert(userBankCard);
    	}else{
    		userBankCardService.updateByPrimaryKeySelective(userBankCard);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	UserBankCard userBankCard = userBankCardService.selectByPrimaryKey(id);
       	return ok(userBankCard);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userBankCardService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}