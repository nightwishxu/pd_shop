package com.paidang.action;

import com.base.action.CoreController;
import com.base.util.BaseUtils;
import com.base.util.StringUtil;
import com.base.util.StringUtils;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.CertificateEx;
import com.paidang.service.CertificateLogService;
import com.paidang.service.CertificateService;
import com.paidang.service.UserGoodsService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.apache.commons.collections.CollectionUtils;
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
@RequestMapping("certificate")
@Controller
public class CertificateController extends CoreController{

    @Autowired
    private CertificateService certificateService;
	@Autowired
	private CertificateLogService certificateLogService;

	@Autowired
	private UserGoodsService userGoodsService;

    
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
		for (Certificate certificate : list) {
			certificate.setImgs(BaseUtils.processImgs(certificate.getImgs()));
		}

		return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(Certificate certificate,Integer from){
    	certificate.setImgs(BaseUtils.removeUrl(certificate.getImgs()));
    	if (certificate.getId() == null){
    		certificate.setCreateTime(new Date());
    		certificateService.insert(certificate);
    	}else{
    		certificate.setModifyTime(new Date());
    		certificateService.updateByPrimaryKeySelective(certificate);
    	}
    	Integer userGoodsId = certificate.getUserGoodsId();
    	if (from==null && userGoodsId!=null){
    		UserGoods tmp = new UserGoods();
    		tmp.setId(userGoodsId);
    		tmp.setAppraisalDsc(certificate.getAppraisalDsc());
    		tmp.setModifyTime(new Date());
    		userGoodsService.updateByPrimaryKeySelective(tmp);
		}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	Certificate certificate = certificateService.selectByPrimaryKey(id);
    	certificate.setImgs(BaseUtils.processImgs(certificate.getImgs()));
       	return ok(certificate);
    }

	@RequestMapping("/getByUserGoodsId")
	@ResponseBody
	public Ret getByUserGoodsId(Integer userGoodsId){
		CertificateExample example = new CertificateExample();
		example.createCriteria().andUserGoodsIdEqualTo(userGoodsId);
		List<Certificate> certificates = certificateService.selectByExample(example);
		Certificate certificate = null;
		if (CollectionUtils.isNotEmpty(certificates)){
			certificate = certificates.get(0);
		}
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