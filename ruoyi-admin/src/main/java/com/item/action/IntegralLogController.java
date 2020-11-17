//package com.item.action;
//
//import com.base.action.CoreController;
//import com.paidang.dao.model.IntegralLog;
//import com.paidang.dao.model.IntegralLogExample;
//import com.paidang.service.IntegralLogService;
//import com.ruoyi.common.core.page.TableDataInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
///**
//@author sun
//*/
//@RequestMapping("integralLog")
//@Controller
//public class IntegralLogController extends CoreController{
//
//    @Autowired
//    private IntegralLogService integralLogService;
//
//    @RequestMapping("/list")
//	@ResponseBody
//    public TableDataInfo list(Integer page, Integer rows){
//    	startPage();
//    	IntegralLogExample example = new IntegralLogExample();
//
//    	List<IntegralLog> list = integralLogService.selectByExample(example);
//      	return page(list);
//    }
//
//    @RequestMapping("/save")
//	@ResponseBody
//    public String save(IntegralLog integralLog){
//    	if (integralLog.getId() == null){
//    		integralLogService.insert(integralLog);
//    	}else{
//    		integralLogService.updateByPrimaryKeySelective(integralLog);
//    	}
//       	return ok();
//    }
//
//    @RequestMapping("/findById")
//	@ResponseBody
//    public String find(Integer id){
//    	IntegralLog integralLog = integralLogService.selectByPrimaryKey(id);
//       	return ok(integralLog);
//    }
//
//    @RequestMapping("/del")
//	@ResponseBody
//    public String del(String id){
//    	String[] ids = id.split(",");
//    	for (String str : ids){
//    		integralLogService.deleteByPrimaryKey(Integer.parseInt(str));
//    	}
//       	return ok();
//    }
//
//
//}