package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.UserCoupon;
import com.paidang.dao.model.UserCouponExample;
import com.paidang.service.UserCouponService;
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
@RequestMapping("userCoupon")
@Controller
public class UserCouponController extends CoreController{

    @Autowired
    private UserCouponService userCouponService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	UserCouponExample example = new UserCouponExample();
    	
    	List<UserCoupon> list = userCouponService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(UserCoupon userCoupon){
    	if (userCoupon.getId() == null){
    		userCouponService.insert(userCoupon);
    	}else{
    		userCouponService.updateByPrimaryKeySelective(userCoupon);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	UserCoupon userCoupon = userCouponService.selectByPrimaryKey(id);
       	return ok(userCoupon);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userCouponService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}