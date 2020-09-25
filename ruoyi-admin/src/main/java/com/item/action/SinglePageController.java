package com.item.action;

import com.base.action.CoreController;
import com.item.dao.model.SinglePage;
import com.item.dao.model.SinglePageExample;
import com.item.service.SinglePageService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "sp")
public class SinglePageController extends CoreController{
	@Autowired
	SinglePageService singlePageService;
	
	@RequestMapping("/list")
	@ResponseBody
	public TableDataInfo list(Integer page, Integer rows){
		startPage();
		SinglePageExample example = new SinglePageExample();
		example.setOrderByClause("sort_order desc");
		if(!getSessionParameter("sys").equals("administrator")){
			example.createCriteria().andIsShowEqualTo(1);
		}
		List<SinglePage> list = singlePageService.selectByExample(example);
		return page(list);
	}
	
	@RequestMapping(value="/save",method= RequestMethod.POST)
	@ResponseBody
	public Ret save(SinglePage news)throws Exception{
		int i = singlePageService.updateByPrimaryKeySelective(news);
		if(i == 0){
			singlePageService.insertSelective(news);
		}
		return ok();
	}
	
	@RequestMapping("/findByCode")
	@ResponseBody
	public Ret findByCode(String code){
		SinglePage singlePage = singlePageService.selectByPrimaryKey(code);
		return ok(singlePage);
	}
	
	@RequestMapping("del")
	@ResponseBody
	public Ret del(String id){
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			singlePageService.deleteByPrimaryKey(ids[i]);
		}
		return ok();
	}

	@RequestMapping("/checkExists")
	@ResponseBody
	public Ret checkExists(String value) throws Exception {
		SinglePageExample example = new SinglePageExample();
		example.createCriteria().andCodeEqualTo(value);
		int cnt = singlePageService.countByExample(example);
		if (cnt > 0) return msg(1);
		return ok();
	}
}
