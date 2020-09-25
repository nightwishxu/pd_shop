package com.item.action;

import com.base.action.CoreController;
import com.base.util.DateUtil;
import com.item.dao.model.SmsSendLog;
import com.item.dao.model.SmsSendLogExample;
import com.item.daoEx.model.SmsSendLogEx;
import com.item.service.SmsSendLogService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("smsSendLog")
@Controller
public class SmsSendLogController extends CoreController{

    @Autowired
    private SmsSendLogService smsSendLogService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows, String phone, String device, String ip, String code, String time) throws Exception{
    	startPage();
    	SmsSendLogExample example = new SmsSendLogExample();
    	example.setOrderByClause("create_time desc");
    	SmsSendLogExample.Criteria criteria = example.createCriteria();
    	if(StringUtils.isNotBlank(phone)){
    		criteria.andPhoneLike(phone);
    	}
    	if (StringUtils.isNotBlank(device)){
    		criteria.andDeviceIdLike(device);
    	}
    	if (StringUtils.isNotBlank(ip)){
    		criteria.andIpLike(ip);
    	}
    	if (StringUtils.isNotBlank(code)){
    		criteria.andCodeEqualTo(code);
    	}
    	if (StringUtils.isNotBlank(time)){
    		Date date = DateUtil.strToDate(time, "yyyy-MM-dd");
    		criteria.andCreateTimeGreaterThan(date).andCreateTimeLessThan(DateUtil.add(date, 1));
    	}
    	List<SmsSendLog> list = smsSendLogService.selectByExample(example);
    	
    	List<SmsSendLogEx> exs = new ArrayList<SmsSendLogEx>();
    	for (SmsSendLog log : list){
    		SmsSendLogEx ex = new SmsSendLogEx();
//    		BeanUtils.copyProperties(ex, log);
//    		ex.setErrorName(SendsmsUtil.getSmsError(ex.getCode()));
    		exs.add(ex);
    	}
    	
      	return page(exs);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(SmsSendLog smsSendLog){
    	if (smsSendLog.getId() == null){
    		smsSendLogService.insert(smsSendLog);
    	}else{
    		smsSendLogService.updateByPrimaryKeySelective(smsSendLog);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	SmsSendLog smsSendLog = smsSendLogService.selectByPrimaryKey(id);
       	return ok(smsSendLog);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		smsSendLogService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }
}
