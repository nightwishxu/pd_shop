package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.PawnContinue;
import com.paidang.dao.model.PawnContinueExample;
import com.paidang.service.PawnContinueService;
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
@RequestMapping("pawnContinue")
@Controller
public class PawnContinueController extends CoreController{

    @Autowired
    private PawnContinueService pawnContinueService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	PawnContinueExample example = new PawnContinueExample();
    	
    	List<PawnContinue> list = pawnContinueService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(PawnContinue pawnContinue){
    	if (pawnContinue.getId() == null){
    		pawnContinueService.insert(pawnContinue);
    	}else{
    		pawnContinueService.updateByPrimaryKeySelective(pawnContinue);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	PawnContinue pawnContinue = pawnContinueService.selectByPrimaryKey(id);
       	return ok(pawnContinue);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		pawnContinueService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}