package com.paidang.action;

import com.base.action.CoreController;
import com.base.dao.model.Result;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.PawnLogEx;
import com.paidang.service.*;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
@author sun
*/
@RequestMapping("pawnLog")
@Controller
public class PawnLogController extends CoreController{

    @Autowired
    private PawnLogService pawnLogService;


    @Autowired
    private PawnTicketService pawnTicketService;
    
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
	public Ret list(Integer id) throws Exception{
		PawnLogExample example = new PawnLogExample();
		example.createCriteria().andGoodsIdEqualTo(id);
		example.setOrderByClause("create_time asc");
		List<PawnLog> list = pawnLogService.selectByExample(example);
		List<PawnLogEx> result = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(list)){
			for (PawnLog log : list) {
				PawnLogEx ex = new PawnLogEx();
				BeanUtils.copyProperties(log,ex);
				if (log.getType()==1){
					//典当
                    if (log.getRefId()!=null){
                        String url =pawnTicketService.showContractUrl(log.getRefId(),null,null,1);
                        ex.setContractUrl(url);
                    }
				}else if (log.getType()==2){
					//续当
                    if (log.getRefId()!=null){
                        String url =pawnTicketService.showContractUrl(null,log.getRefId(),null,1);
                        ex.setContractUrl(url);
                    }
				}
                result.add(ex);
			}

		}
		return ok(result);
	}
}