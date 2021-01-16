package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.GoodsAttribute;
import com.paidang.dao.model.GoodsAttributeExample;
import com.paidang.service.GoodsAttributeService;
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
@RequestMapping("goodsAttribute")
@Controller
public class GoodsAttributeController extends CoreController{

    @Autowired
    private GoodsAttributeService goodsAttributeService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows,Integer cateCode){
		startPage();
    	GoodsAttributeExample example = new GoodsAttributeExample();
    	if (cateCode!=null){
			example.createCriteria().andBelongToEqualTo(cateCode);
		}
    	List<GoodsAttribute> list = goodsAttributeService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(GoodsAttribute goodsAttribute){
    	if (goodsAttribute.getId() == null){
    		goodsAttributeService.insert(goodsAttribute);
    	}else{
    		goodsAttributeService.updateByPrimaryKeySelective(goodsAttribute);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	GoodsAttribute goodsAttribute = goodsAttributeService.selectByPrimaryKey(id);
       	return ok(goodsAttribute);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		goodsAttributeService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}