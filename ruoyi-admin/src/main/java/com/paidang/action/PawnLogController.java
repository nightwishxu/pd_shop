package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.PawnLog;
import com.paidang.dao.model.PawnLogExample;
import com.paidang.service.PawnLogService;
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
@RequestMapping("pawnLog")
@Controller
public class PawnLogController extends CoreController{

    @Autowired
    private PawnLogService pawnLogService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	PawnLogExample example = new PawnLogExample();
    	
    	List<PawnLog> list = pawnLogService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(PawnLog pawnLog){
    	if (pawnLog.getId() == null){
    		pawnLogService.insert(pawnLog);
    	}else{
    		pawnLogService.updateByPrimaryKeySelective(pawnLog);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	PawnLog pawnLog = pawnLogService.selectByPrimaryKey(id);
       	return ok(pawnLog);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		pawnLogService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }

	@RequestMapping("/search")
	@ResponseBody
	public Ret list(Integer id){
		PawnLogExample example = new PawnLogExample();
		example.createCriteria().andGoodsIdEqualTo(id);
		example.setOrderByClause("create_time asc");
		List<PawnLog> list = pawnLogService.selectByExample(example);
		return ok(list);
	}
}