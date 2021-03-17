package com.paidang.action;

import com.base.action.CoreController;
import com.item.dao.model.User;
import com.item.service.UserService;
import com.paidang.dao.model.AuthPersonal;
import com.paidang.dao.model.AuthPersonalExample;
import com.paidang.dao.model.BusinessUserInfo;
import com.paidang.dao.model.PawnOrg;
import com.paidang.daoEx.model.AuthPersonalEx;
import com.paidang.domain.qo.AuthPersonalQo;
import com.paidang.service.AuthPersonalService;
import com.paidang.service.BusinessUserInfoService;
import com.paidang.service.PawnOrgService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
@author sun
*/
@RequestMapping("authPersonal")
@Controller
public class AuthPersonalController extends CoreController{

    @Autowired
    private AuthPersonalService authPersonalService;
    @Autowired
	private BusinessUserInfoService businessUserInfoService;
    @Autowired
	private PawnOrgService pawnOrgService;

    @Autowired
	private UserService userService;

    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows, String storeName){
    	startPage();
//    	AuthPersonalExample example = new AuthPersonalExample();
//		AuthPersonalExample.Criteria criteria =example.createCriteria();
//		if(StringUtils.isNotBlank(storeName)){
//			criteria.andStoreNameLike("%"+storeName+"%");
//		}
//		criteria.andStateNotEqualTo("3");
//		example.setOrderByClause("id desc");
		AuthPersonalQo qo = new AuthPersonalQo();
		qo.setStoreName(storeName);
    	List<AuthPersonalEx> list = authPersonalService.findList(qo);
      	return page(list);
    }

    @RequestMapping("/save")
	@ResponseBody
    public Ret save(AuthPersonal authPersonal){
    	if (authPersonal.getId() == null){
    		authPersonalService.insert(authPersonal);
    	}else{
    		authPersonalService.updateByPrimaryKeySelective(authPersonal);
    	}
       	return ok();
    }

    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	AuthPersonal authPersonal = authPersonalService.selectByPrimaryKey(id);
       	return ok(authPersonal);
    }

    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		authPersonalService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


	@RequestMapping("/changeState")
	@ResponseBody
	public Ret changeState(Integer id,String v,AuthPersonal authPersonal){
		authPersonal.setId(id);
		authPersonal.setState(v);
		if("1".equals(v)){//审核通过，需要在p_business_user_info中插入一条数据
			AuthPersonal personal = authPersonalService.selectByPrimaryKey(id);
			BusinessUserInfo businessUserInfo = new BusinessUserInfo();
			businessUserInfo.setUserId(personal.getCreateUser());
			User user = userService.selectByPrimaryKey(personal.getCreateUser());
			businessUserInfo.setTotal(BigDecimal.ZERO);
			businessUserInfoService.insert(businessUserInfo);
			PawnOrg pawnOrg = new PawnOrg();
			AuthPersonal authPersonal1= authPersonalService.selectByPrimaryKey(id);
			pawnOrg.setType(6);
			pawnOrg.setRoleCode("aaa");
			pawnOrg.setName(authPersonal1.getStoreName());
			pawnOrg.setLegalPerson(authPersonal1.getName());
			pawnOrg.setPhone(authPersonal1.getPhone());
			pawnOrg.setCreateTime(new Date());
			pawnOrg.setState(1);
			pawnOrg.setAnxinPhone(user.getAccount());
			pawnOrg.setIntroduction(authPersonal1.getStoreIntroduce());
			pawnOrg.setOrgLogo(authPersonal1.getLogo());
			pawnOrgService.insert(pawnOrg);
			authPersonal.setOrgId(pawnOrg.getId());
		}
		authPersonalService.updateByPrimaryKeySelective(authPersonal);
		return ok();
	}

}
