package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.ExperterInfo;
import com.paidang.dao.model.ExperterInfoExample;
import com.paidang.dao.model.UserGoods;
import com.paidang.daoEx.model.ExperterInfoEx;
import com.paidang.service.ExperterInfoService;
import com.paidang.service.UserGoodsService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
@author sun
*/
@RequestMapping("experterInfo")
@Controller
public class ExperterInfoController extends CoreController{

    @Autowired
    private ExperterInfoService experterInfoService;
	@Autowired
	private UserGoodsService userGoodsService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows, String experterName, String goodsName){
    	startPage();
    	ExperterInfoExample example = new ExperterInfoExample();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("experterName",experterName);
		map.put("goodsName",goodsName);
    	//List<ExperterInfo> list = experterInfoService.selectByExample(example);
		List<ExperterInfoEx> list = experterInfoService.selectList(map);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(ExperterInfo experterInfo){
    	if (experterInfo.getId() == null){
    		experterInfo.setCreateTime(new Date());
    		experterInfoService.insert(experterInfo);
    	}else{
    		experterInfoService.updateByPrimaryKeySelective(experterInfo);
    	}
       	return ok();
    }

	@RequestMapping("/saveInfo")
	@ResponseBody
	public Ret saveInfo(ExperterInfo experterInfo){
    	//插入专家表意见表
		experterInfo.setCreateTime(new Date());
		experterInfoService.insert(experterInfo);
		//修改p_user_gooods表
		UserGoods userGoods = new UserGoods();
		userGoods.setId(experterInfo.getGoodsId());
		userGoods.setExperterInfoId(experterInfo.getId());
		userGoodsService.updateByPrimaryKeySelective(userGoods);
		return ok();
	}
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	ExperterInfo experterInfo = experterInfoService.selectByPrimaryKey(id);
       	return ok(experterInfo);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		experterInfoService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}