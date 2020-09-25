package com.paidang.action;

import com.base.action.CoreController;
import com.base.util.StringUtil;
import com.paidang.dao.model.Certificate;
import com.paidang.dao.model.CertificateExample;
import com.paidang.dao.model.CertificateLog;
import com.paidang.dao.model.CertificateLogExample;
import com.paidang.daoEx.model.CertificateEx;
import com.paidang.service.CertificateLogService;
import com.paidang.service.CertificateService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
@author sun
*/
@RequestMapping("certificate")
@Controller
public class CertificateController extends CoreController{

    @Autowired
    private CertificateService certificateService;
	@Autowired
	private CertificateLogService certificateLogService;
    
    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer page, Integer rows, String name, String code){
    	startPage();
    	CertificateExample example = new CertificateExample();
		CertificateExample.Criteria criteria=example.createCriteria();
    	if(StringUtil.isNotBlank(name)){
    		name="%"+name+"%";
			criteria.andNameLike(name);
		}
		if(StringUtil.isNotBlank(code)){
			code="%"+code+"%";
			criteria.andCodeLike(code);
		}
		example.setOrderByClause("id desc");
    	List<Certificate> list = certificateService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(Certificate certificate){
    	if (certificate.getId() == null){
    		certificateService.insert(certificate);
    	}else{
    		certificateService.updateByPrimaryKeySelective(certificate);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	Certificate certificate = certificateService.selectByPrimaryKey(id);
       	return ok(certificate);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		certificateService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }
	@RequestMapping("/checkDetail")
	@ResponseBody
	public Ret checkDetail(Integer id){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id",id);
		Certificate c = certificateService.selectByPrimaryKey(id);

		CertificateLogExample certificateLogExample = new CertificateLogExample();
		certificateLogExample.createCriteria().andCertificateIdEqualTo(id);
		certificateLogExample.setOrderByClause("create_time desc");
		List<CertificateLog> list = certificateLogService.selectByExample(certificateLogExample);

		CertificateEx certificateEx = new CertificateEx();
		certificateEx.setList(list);
		certificateEx.setName(c.getName());
		certificateEx.setCode(c.getCode());
		certificateEx.setLength(c.getLength());
		certificateEx.setWidth(c.getWidth());
		certificateEx.setHeight(c.getHeight());
		certificateEx.setWeight(c.getWeight());
		certificateEx.setMaterial(c.getMaterial());
		certificateEx.setMainMaterial(c.getMainMaterial());
		certificateEx.setOtherMaterial(c.getOtherMaterial());
		certificateEx.setCreateYear(c.getCreateYear());
		certificateEx.setOther(c.getOther());
		certificateEx.setImgs(c.getImgs());
		certificateEx.setMarketLiquidity(c.getMarketLiquidity());
		certificateEx.setValueStability(c.getValueStability());
		certificateEx.setMaterialVulnerability(c.getMaterialVulnerability());
		certificateEx.setStorageCondition(c.getStorageCondition());
		certificateEx.setNakedEyeDefect(c.getNakedEyeDefect());
		certificateEx.setFinanceLog(c.getFinanceLog());
		certificateEx.setOtherBusiness(c.getOtherBusiness());
		return ok(certificateEx);
	}

}