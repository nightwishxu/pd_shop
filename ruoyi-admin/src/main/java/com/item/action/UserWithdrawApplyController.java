package com.item.action;

import com.base.action.CoreController;

import com.item.service.UserWithdrawApplyService;
import com.paidang.dao.model.UserWithdrawApply;
import com.paidang.dao.model.UserWithdrawApplyExample;
import com.paidang.daoEx.model.UserWithdrawApplyEx;
import com.paidang.domain.qo.UserWithdrawApplyQo;
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
@RequestMapping("userWithdrawApply")
@Controller
public class UserWithdrawApplyController extends CoreController {

    @Autowired
    private UserWithdrawApplyService userWithdrawApplyService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(UserWithdrawApplyQo qo){
    	startPage();
    	
    	List<UserWithdrawApplyEx> list = userWithdrawApplyService.findList(qo);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(UserWithdrawApply userWithdrawApply){
    	if (userWithdrawApply.getId() == null){
    		userWithdrawApplyService.insert(userWithdrawApply);
    	}else{
    		userWithdrawApplyService.updateByPrimaryKeySelective(userWithdrawApply);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	UserWithdrawApply userWithdrawApply = userWithdrawApplyService.selectByPrimaryKey(id);
       	return ok(userWithdrawApply);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userWithdrawApplyService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


	@RequestMapping("/changeState")
	@ResponseBody
	public Ret changeState(Integer id,Integer status){
		UserWithdrawApply apply1 = userWithdrawApplyService.selectByPrimaryKey(id);
		if (apply1.getStatus()==status){
			return Ret.error("状态异常");
		}
		UserWithdrawApply apply = new UserWithdrawApply();
		apply.setId(id);
		apply.setStatus(status);
		apply.setModifyTime(new Date());
		userWithdrawApplyService.updateByPrimaryKeySelective(apply);
//		if (status==1){
//			OrgWithdrawApply orgWithdrawApply = orgWithdrawApplyService.selectByPrimaryKey(id);
//			orgAmountLogService.saveLog(orgWithdrawApply.getOrgId(),null,orgWithdrawApply.getAmount(),"2","提现到账",orgWithdrawApply.getId(),null);
//		}
		return ok();
	}



}