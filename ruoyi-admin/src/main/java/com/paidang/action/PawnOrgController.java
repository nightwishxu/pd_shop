package com.paidang.action;

import com.base.action.CoreController;

import com.base.util.Md5;
import com.github.sd4324530.fastweixin.util.CollectionUtil;
import com.item.dao.model.Code;
import com.item.service.CodeService;
import com.paidang.dao.model.PawnOrg;
import com.paidang.dao.model.PawnOrgExample;
import com.paidang.daoEx.model.PawnOrgEx;
import com.paidang.service.PawnOrgService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import com.util.PaidangConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
@author sun
*/
@RequestMapping("pawnOrg")
@Controller
public class PawnOrgController extends CoreController{

    @Autowired
    private PawnOrgService pawnOrgService;
	@Autowired
	private CodeService codeService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public TableDataInfo list(Integer page, Integer rows, String name, Integer type){
    	startPage();
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("name",name);
    	map.put("type",type);
    	List<PawnOrgEx> list = pawnOrgService.selectList(map);
      	return page(list);
    }

	@RequestMapping("/pawnMsg")
	@ResponseBody
	public Ret pawnMsg(){
		PawnOrgExample example = new PawnOrgExample();
		example.createCriteria();
		example.setOrderByClause("create_time desc");
		List<PawnOrg> list = pawnOrgService.selectByExample(example);
		return ok(list);
	}
    
    @RequestMapping("/save")
	@ResponseBody 
    public Ret save(PawnOrg pawnOrg)throws Exception{

    	if (pawnOrg.getId() == null){
			PawnOrgExample example=new PawnOrgExample();
			example.createCriteria().andAccountEqualTo(pawnOrg.getAccount());
			List<PawnOrg> list=pawnOrgService.selectByExample(example);
			if (CollectionUtil.isNotEmpty(list)){
				return msg(-1,"不能添加重复的机构和供应商");
			}
			if(pawnOrg.getType()==1){
				pawnOrg.setRoleCode(PaidangConst.ORG_ROLE);
			}else if(pawnOrg.getType()==3){
				pawnOrg.setRoleCode(PaidangConst.SUPPLIER_ROLE);
			}
			pawnOrg.setPassword(Md5.md5("1"));
			pawnOrg.setBalance(BigDecimal.ZERO);
			pawnOrg.setState(2);
			//pawnOrg.setType(1);
			pawnOrgService.insert(pawnOrg);
    	}else{
    		pawnOrgService.updateByPrimaryKeySelective(pawnOrg);
    		//宝祥设置逾期费率的时候同步宝祥兜底的逾期费率
    		if(pawnOrg.getId()==0){
				Code code = codeService.selectByPrimaryKey("overRate");
				code.setValue(pawnOrg.getRedeemOverrate().toString());
				codeService.updateByPrimaryKeySelective(code);
			}
    	}
       	return ok();
    }

	@RequestMapping("/saveService")
	@ResponseBody
	public Ret saveService(PawnOrg pawnOrg)throws Exception{

		if (pawnOrg.getId() == null){
			pawnOrg.setBalance(BigDecimal.ZERO);
			pawnOrg.setRoleCode(PaidangConst.SERVICE_ORG_ROLE);
			pawnOrg.setPassword(Md5.md5(PaidangConst.INIT_PASSWORD));
			pawnOrg.setType(2);
			pawnOrgService.insert(pawnOrg);
		}else{
			pawnOrgService.updateByPrimaryKeySelective(pawnOrg);
		}
		return ok();
	}

	@RequestMapping("/changePwds")
	@ResponseBody
	public Ret changePwds(PawnOrg pawnOrg)throws Exception{
			pawnOrgService.changePwd(pawnOrg.getId(),pawnOrg.getPassword());
		return ok();
	}

    @RequestMapping("/findById")
	@ResponseBody 
    public Ret find(Integer id){
    	PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(id);
       	return ok(pawnOrg);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		pawnOrgService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }

	@RequestMapping("/changeState")
	@ResponseBody
	public Ret changeState(Integer id,Integer v){
		PawnOrg pawnOrg=new PawnOrg();
		pawnOrg.setId(id);
		Integer state=null;
		if (v==0){
			state=1;
		}else if(v==1){
			state=0;
		}else if(v==2 || v==null){
			state=1;
		}
		pawnOrg.setState(state);
		pawnOrgService.changeState(pawnOrg);
		return ok();
	}
}