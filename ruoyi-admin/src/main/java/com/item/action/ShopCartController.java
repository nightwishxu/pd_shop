package com.item.action;

import com.base.action.CoreController;
import com.item.dao.model.ShopCart;
import com.item.dao.model.ShopCartExample;
import com.item.service.ShopCartService;
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
@RequestMapping("shopCart")
@Controller
public class ShopCartController extends CoreController{

    @Autowired
    private ShopCartService shopCartService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	ShopCartExample example = new ShopCartExample();
    	
    	List<ShopCart> list = shopCartService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(ShopCart shopCart){
    	if (shopCart.getId() == null){
    		shopCartService.insert(shopCart);
    	}else{
    		shopCartService.updateByPrimaryKeySelective(shopCart);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	ShopCart shopCart = shopCartService.selectByPrimaryKey(id);
       	return ok(shopCart);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		shopCartService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}