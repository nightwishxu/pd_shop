package com.paidang.action;

import com.alibaba.fastjson.JSONObject;
import com.base.action.CoreController;
import com.item.service.UserService;
import com.paidang.dao.model.PawnOrg;
import com.paidang.dao.model.PawnOrgExample;
import com.paidang.dao.model.PawnTicket;
import com.paidang.dao.model.PawnTicketExample;
import com.paidang.daoEx.model.PawnTicketEx;
import com.paidang.service.PawnOrgService;
import com.paidang.service.PawnTicketService;
import com.qiyuesuo.QysService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
@author sun
*/
@RequestMapping("pawnTicket")
@Controller
public class PawnTicketCenterController extends CoreController{

    @Autowired
    private PawnTicketService pawnTicketService;
    @Autowired
	private PawnOrgService pawnOrgService;
    @Autowired
	private UserService userService;
    @Autowired
	private QysService qysService;

    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows){
    	startPage();
    	PawnTicketExample example = new PawnTicketExample();
		example.setOrderByClause("create_time desc");
    	List<PawnTicket> list = pawnTicketService.selectByExample(example);
      	return page(list);
    }

    @RequestMapping("/save")
	@ResponseBody
    public Ret save(PawnTicketEx pawnTicketEx){
		PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(Integer.valueOf(pawnTicketEx.getOrgId()));
		PawnTicket pawnTicket = new PawnTicket();
		BeanUtils.copyProperties(pawnTicketEx,pawnTicket);
		pawnTicket.setOrgName(pawnOrg.getName()==null?"":pawnOrg.getName());
		pawnTicket.setOrgTel(pawnOrg.getPhone()==null?"":pawnOrg.getPhone());
		pawnTicket.setOrgAdress(pawnOrg.getAdress()==null?"":pawnOrg.getAdress());
		pawnTicket.setBusinessLicense(pawnOrg.getBusinessLicenseCode()==null?"":pawnOrg.getBusinessLicenseCode());
		pawnTicket.setPawnerCert("身份证");
		if (pawnTicket.getId() == null){
			pawnTicket.setStatus("0");
    		pawnTicketService.insert(pawnTicket);
    	}else{
    		pawnTicketService.updateByPrimaryKeySelective(pawnTicket);
    	}
       	return ok();
    }

    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	PawnTicket pawnTicket = pawnTicketService.selectByPrimaryKey(id);
       	return ok(pawnTicket);
    }

    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		pawnTicketService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }

	@RequestMapping("/findOrg")
	@ResponseBody
	public List<PawnOrg> findOrg(){
		PawnOrgExample pawnOrgExample = new PawnOrgExample();
		pawnOrgExample.createCriteria().andTypeEqualTo(1);
		List<PawnOrg> pawnOrgs = pawnOrgService.selectByExample(pawnOrgExample);
		return pawnOrgs;
	}

	/**
	 * 查看合同
	 */
	@RequestMapping(value = "/showContract")
	@ResponseBody
	public Object showContract(Integer pawnTicketId) {
		PawnTicket pawnTicket = pawnTicketService.selectByPrimaryKey(pawnTicketId);
		String contractId= pawnTicket.getContractId();
		String pageUrl = qysService.getPageUrl(Long.valueOf(contractId));
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pageUrl",pageUrl);
		logger.info("==================合同查看地址:{}",pageUrl);
		return jsonObject;
	}

}
