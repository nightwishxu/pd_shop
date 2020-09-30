package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.CertificateLog;
import com.paidang.dao.model.CertificateLogExample;
import com.paidang.service.CertificateLogService;
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
@RequestMapping("certificateLog")
@Controller
public class CertificateLogController extends CoreController{

    @Autowired
    private CertificateLogService certificateLogService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	CertificateLogExample example = new CertificateLogExample();
    	
    	List<CertificateLog> list = certificateLogService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(CertificateLog certificateLog, String times){
//    	if(StringUtil.isNotBlank(times)){
//
//			certificateLog.setLogTime(DateUtil.strToDate(times));
//		}
    	if (certificateLog.getId() == null){
    		certificateLog.setCreateTime(new Date());
    		certificateLogService.insert(certificateLog);
    	}else{
    		certificateLogService.updateByPrimaryKeySelective(certificateLog);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	CertificateLog certificateLog = certificateLogService.selectByPrimaryKey(id);
       	return ok(certificateLog);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		certificateLogService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }

}