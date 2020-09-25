package com.paidang.action;

import com.base.action.CoreController;
import com.paidang.dao.model.AuthEnterprise;
import com.paidang.dao.model.AuthEnterpriseExample;
import com.paidang.dao.model.BusinessUserInfo;
import com.paidang.dao.model.PawnOrg;
import com.paidang.service.AuthEnterpriseService;
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
@RequestMapping("authEnterprise")
@Controller
public class AuthEnterpriseController extends CoreController{

    @Autowired
    private AuthEnterpriseService authEnterpriseService;
	@Autowired
	private BusinessUserInfoService businessUserInfoService;
	@Autowired
	private PawnOrgService pawnOrgService;

    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows, String enterpriseName){
		startPage();
		AuthEnterpriseExample example = new AuthEnterpriseExample();
		AuthEnterpriseExample.Criteria criteria=example.createCriteria();
		if(StringUtils.isNotBlank(enterpriseName)){
			criteria.andEnterpriseNameLike("%"+enterpriseName+"%");
		}
		criteria.andStateNotEqualTo("3");
		example.setOrderByClause("id desc");
    	List<AuthEnterprise> list = authEnterpriseService.selectByExample(example);
      	return page(list);
    }

    @RequestMapping("/save")
	@ResponseBody
    public Ret save(AuthEnterprise authEnterprise){
    	if (authEnterprise.getId() == null){
    		authEnterpriseService.insert(authEnterprise);
    	}else{
    		authEnterpriseService.updateByPrimaryKeySelective(authEnterprise);
    	}
       	return ok();
    }

    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	AuthEnterprise authEnterprise = authEnterpriseService.selectByPrimaryKey(id);
       	return ok(authEnterprise);
    }

    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		authEnterpriseService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


	@RequestMapping("/changeState")
	@ResponseBody
	public Ret changeState(Integer id, String v, AuthEnterprise authEnterprise){
		authEnterprise.setId(id);
		authEnterprise.setState(v);
		if("1".equals(v)){//审核通过，需要在p_business_user_info中插入一条数据
			AuthEnterprise personal = authEnterpriseService.selectByPrimaryKey(id);
			BusinessUserInfo businessUserInfo = new BusinessUserInfo();
			businessUserInfo.setUserId(personal.getCreateUser());
			businessUserInfo.setTotal(BigDecimal.ZERO);
			businessUserInfoService.insert(businessUserInfo);
			PawnOrg pawnOrg = new PawnOrg();
			AuthEnterprise authEnterprise1=authEnterpriseService.selectByPrimaryKey(id);
			pawnOrg.setType(6);
			pawnOrg.setRoleCode("aaa");
			pawnOrg.setName(authEnterprise1.getStoreName());
			pawnOrg.setLegalPerson(authEnterprise1.getLegalPersonName());
			pawnOrg.setCreateTime(new Date());
			pawnOrg.setState(1);
			pawnOrg.setIntroduction(authEnterprise1.getStoreIntroduce());
			pawnOrg.setOrgLogo(authEnterprise1.getLogo());
			pawnOrg.setBusinessLicense(authEnterprise1.getBusinessLicensePhoto());
			pawnOrgService.insert(pawnOrg);
			authEnterprise.setOrgId(pawnOrg.getId());
		}
		authEnterpriseService.updateByPrimaryKeySelective(authEnterprise);
		return ok();
	}
}
