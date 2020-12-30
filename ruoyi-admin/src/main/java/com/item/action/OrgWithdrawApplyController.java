package com.item.action;

import com.base.action.CoreController;
import com.base.api.ApiException;
import com.item.service.OrgAmountLogService;
import com.paidang.dao.model.OrgWithdrawApply;
import com.paidang.dao.model.OrgWithdrawApplyExample;
import com.paidang.dao.model.PawnOrg;
import com.paidang.daoEx.model.OrgWithdrawApplyEx;
import com.paidang.domain.qo.OrgWithdrawApplyQo;
import com.paidang.service.OrgWithdrawApplyService;
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
@RequestMapping("orgWithdrawApply")
@Controller
public class OrgWithdrawApplyController extends CoreController {

    @Autowired
    private OrgWithdrawApplyService orgWithdrawApplyService;

    @Autowired
	private OrgAmountLogService orgAmountLogService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(OrgWithdrawApplyQo qo){
    	startPage();
    	List<OrgWithdrawApplyEx> list = orgWithdrawApplyService.findList(qo);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(OrgWithdrawApply orgWithdrawApply){
    	if (orgWithdrawApply.getId() == null){
    		orgWithdrawApplyService.insert(orgWithdrawApply);
    	}else{
    		orgWithdrawApplyService.updateByPrimaryKeySelective(orgWithdrawApply);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	OrgWithdrawApply orgWithdrawApply = orgWithdrawApplyService.selectByPrimaryKey(id);
       	return ok(orgWithdrawApply);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		orgWithdrawApplyService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }

	@RequestMapping("/changeState")
	@ResponseBody
	public Ret changeState(Integer id,Integer status){
		OrgWithdrawApply apply1 = orgWithdrawApplyService.selectByPrimaryKey(id);
		if (apply1.getStatus()==status){
			return Ret.error("状态异常");
		}
		OrgWithdrawApply apply = new OrgWithdrawApply();
		apply.setId(id);
		apply.setStatus(status);
		apply.setModifyTime(new Date());
		orgWithdrawApplyService.updateByPrimaryKeySelective(apply);
		if (status==1){
			OrgWithdrawApply orgWithdrawApply = orgWithdrawApplyService.selectByPrimaryKey(id);
			orgAmountLogService.saveLog(orgWithdrawApply.getOrgId(),orgWithdrawApply.getAmount(),"2","提现到账",orgWithdrawApply.getId(),null);
		}
		return ok();
	}


}