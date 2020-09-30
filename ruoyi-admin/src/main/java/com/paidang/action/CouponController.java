package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.Coupon;
import com.paidang.dao.model.CouponExample;
import com.paidang.service.CouponService;
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
@RequestMapping("coupon")
@Controller
public class CouponController extends CoreController{

    @Autowired
    private CouponService couponService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	CouponExample example = new CouponExample();
    	
    	List<Coupon> list = couponService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(Coupon coupon){
    	if (coupon.getId() == null){
    		coupon.setCreateTime(new Date());
    		couponService.insert(coupon);
    	}else{
			coupon.setModifyTime(new Date());
    		couponService.updateByPrimaryKeySelective(coupon);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	Coupon coupon = couponService.selectByPrimaryKey(id);
       	return ok(coupon);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		couponService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}