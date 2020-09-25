package com.paidang.action;

import com.base.action.CoreController;
import com.base.util.StringUtil;
import com.paidang.dao.model.Domain;
import com.paidang.dao.model.DomainExample;
import com.paidang.service.DomainService;
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
@RequestMapping("domain")
@Controller
public class DomainController extends CoreController{

    @Autowired
    private DomainService domainService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows, String domainName){
    	startPage();
    	DomainExample example = new DomainExample();
    	if(StringUtil.isNotBlank(domainName)){
    		example.createCriteria().andDomainNameLike("%"+domainName+"%");
		}
    	
    	List<Domain> list = domainService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(Domain domain){
    	if (domain.getId() == null){
    		domainService.insert(domain);
    	}else{
    		domainService.updateByPrimaryKeySelective(domain);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	Domain domain = domainService.selectByPrimaryKey(id);
       	return ok(domain);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		domainService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }

	@RequestMapping("/domainList")
	@ResponseBody
	public List<Domain> domainList(){
		DomainExample example = new DomainExample();

		List<Domain> list = domainService.selectByExample(example);
		return list;
	}


}