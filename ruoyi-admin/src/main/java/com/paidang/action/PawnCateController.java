package com.paidang.action;

import com.base.action.CoreController;
import com.base.util.BaseUtils;
import com.paidang.dao.model.PawnCate;
import com.paidang.dao.model.PawnCateExample;
import com.paidang.service.PawnCateService;
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
@RequestMapping("pawnCate")
@Controller
public class PawnCateController extends CoreController{

    @Autowired
    private PawnCateService pawnCateService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	PawnCateExample example = new PawnCateExample();
		example.setOrderByClause("sort_order asc,create_time desc");
    	List<PawnCate> list = pawnCateService.selectByExample(example);
		for (PawnCate cate : list) {
			cate.setIcon(BaseUtils.processImgs(cate.getIcon()));
		}

		return page(list);
    }

	@RequestMapping("/all")
	@ResponseBody
	public Ret all(){
		PawnCateExample example = new PawnCateExample();
		example.setOrderByClause("sort_order asc,create_time desc");
		List<PawnCate> list = pawnCateService.selectByExample(example);
		for (PawnCate cate : list) {
			cate.setIcon(BaseUtils.processImgs(cate.getIcon()));
		}
		return ok(list);
	}
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(PawnCate pawnCate){
    	pawnCate.setIcon(BaseUtils.removeUrl(pawnCate.getIcon()));
    	if (pawnCate.getId() == null){
    		pawnCate.setCreateTime(new Date());
    		pawnCateService.insert(pawnCate);
    	}else{
    		pawnCateService.updateByPrimaryKeySelective(pawnCate);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	PawnCate pawnCate = pawnCateService.selectByPrimaryKey(id);
    	pawnCate.setIcon(BaseUtils.removeUrl(pawnCate.getIcon()));
       	return ok(pawnCate);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		pawnCateService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}