package com.paidang.action;

import com.base.ConstantsCode;
import com.base.action.CoreController;
import com.base.util.CoreConstants;
import com.base.util.StringUtil;
import com.base.util.http.HttpUtil;
import com.item.dao.model.Admin;
import com.item.dao.model.AdminExample;
import com.item.dao.model.User;
import com.item.service.AdminService;
import com.item.service.MobileVerifyService;
import com.item.service.UserNotifyService;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.UserGoodsEx;
import com.paidang.service.*;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.util.MExpressAddress;
import com.util.MPawnMsg;
import com.util.PaidangMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
@author sun
*/
@RequestMapping("userGoods")
@Controller
public class UserGoodsController extends CoreController{

    @Autowired
	private UserGoodsService userGoodsService;
	@Autowired
	private PawnLogService pawnLogService;
	@Autowired
	private UserNotifyService userNotifyService;
	@Autowired
	private UserService userService;
	@Autowired
	private MobileVerifyService mobileVerifyService;
	@Autowired
	private ExpressService expressService;
	@Autowired
	private UserPawnService userPawnService;
	@Autowired
	private ExperterInfoService experterInfoService;
	@Autowired
	private AdminService adminService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private CertificateService certificateService;


	@RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(String name, String account, String type){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("name",name);
		map.put("account",account);
    	map.put("type",type);
		startPage();
		List<UserGoodsEx> list = userGoodsService.selectList(map);
		for(UserGoodsEx ex : list){
			UserGoodsEx c = new UserGoodsEx();
			LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

			if("authAdmin".equals(loginUser.getUser().getUserType())){
				//是审核管理员
				ex.setPower(1);
			}else{
				ex.setPower(0);
			}
		}
		return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(UserGoods userGoods){
		if(StringUtil.isNotBlank(userGoods.getName())){
			//查询是否名字重复
			UserGoodsExample example = new UserGoodsExample();
			example.createCriteria().andNameEqualTo(userGoods.getName());
			int cnt = userGoodsService.countByExample(example);
			if(cnt > 0){
				return msg(-1,userGoods.getName()+"已有命名，请重新命名");
			}
		}
    	if (userGoods.getId() == null){
    		userGoodsService.insert(userGoods);
    	}else{
    		//然后查询是否是专家评定
			UserGoods record = userGoodsService.selectByPrimaryKey(userGoods.getId());
			if(null != record.getExperterInfoId()){
				//邀请了专家鉴定
				//更新experterInfo表

				ExperterInfo experterInfo = experterInfoService.selectByPrimaryKey(record.getExperterInfoId());
				experterInfo.setGoodsId(userGoods.getId());
				experterInfoService.updateByPrimaryKeySelective(experterInfo);
			}

			userGoodsService.updateByPrimaryKeySelective(userGoods);
			//修改物流单号同时修改物流表中的物流单号
			ExpressExample expressExample=new ExpressExample();
			expressExample.createCriteria().andFidEqualTo(userGoods.getId());
			List<Express> elist = expressService.selectByExample(expressExample);
			if(elist.size()>0){
				for (Express express :elist) {
					express.setExpressCode(userGoods.getPostExpressCode());
					expressService.updateByPrimaryKeySelective(express);
				}
			}
			//修改当品名称时同时修改典当记录中的当品名称
			UserPawnExample example=new UserPawnExample();
            example.createCriteria().andGoodsIdEqualTo(userGoods.getId());
            List<UserPawn> userPawnList = userPawnService.selectByExample(example);
            if(userPawnList.size()>0){
                for (UserPawn userPawn:userPawnList) {
                    userPawn.setGoodsName(userGoods.getName());
                    userPawnService.updateByPrimaryKeySelective(userPawn);
                }
            }


    	}
       	return ok();
    }

	/**
	 * 回寄给用户并且插入express表
	 * @param userGoods
	 * @return
	 */
	@RequestMapping("/saveByBackToUser")
	@ResponseBody
	public Ret saveByBackToUser(UserGoods userGoods){
		if (userGoods.getId() == null){
			userGoodsService.insert(userGoods);
		}else{
			userGoodsService.updateByPrimaryKeySelective(userGoods);
		}
		UserGoods record = userGoodsService.selectByPrimaryKey(userGoods.getId());


		ExpressExample example = new ExpressExample();
		example.createCriteria().andFidEqualTo(record.getId()).andSourceIdEqualTo(record.getUserId()).andTypeEqualTo(2);
		List<Express> list = expressService.selectByExample(example);
		Express c = list.get(0);
//		c.setSource(1);
//		c.setSourceId(record.getUserId());
//		c.setFid(userGoods.getId());
//		c.setType(2);
//		c.setExpressName(userGoods.getBackExpress());
//		c.setExpressCode(userGoods.getBackExpressCode());
//		c.setExpressState(0);
//		c.setPostName(MPawnMsg.name);
//		c.setPostPhone(MPawnMsg.phone);
//		c.setReceiveName(userGoods.getBackUserName());
//		c.setReceivePhone(userGoods.getBackUserPhone());
//		c.setReceviceAddress(userGoods.getBackUserExpress());
//		expressService.updateByPrimaryKeySelective(c);

		Map<String, Object> map = new HashMap<>();
		map.put("id",c.getId());
		map.put("source",1);
		map.put("expressName",userGoods.getBackExpress());
		map.put("expressCode",userGoods.getBackExpressCode());
		map.put("expressState",0);
		map.put("postName", MPawnMsg.name);
		map.put("postPhone", MPawnMsg.phone);
		map.put("receiveName",userGoods.getBackUserName());
		map.put("receivePhone",userGoods.getBackUserPhone());
		map.put("receviceAddress",userGoods.getBackUserExpress());


		expressService.updateData(map);
		return ok();
	}
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	UserGoods userGoods = userGoodsService.selectByPrimaryKey(id);
       	return ok(userGoods);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userGoodsService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }

    //开始拆箱--并且鉴定
	@RequestMapping("/beginToOper")
	@ResponseBody
	public Ret beginToOper(UserGoodsEx userGoods){
    	if(4 != userGoods.getAuthResult()){
    		Map<String, Object> map = new HashMap<>();
    		map.put("id",userGoods.getId());
    		map.put("authResult", userGoods.getAuthResult());
    		map.put("authPrice","");
    		map.put("notVerifyReason",userGoods.getNotVerifyReason());
			int result = userGoodsService.updateResAuthPrice(map);
			//鉴定是赝品，或者是无法鉴定
			UserGoods userGoods2 = userGoodsService.selectByPrimaryKey(userGoods.getId());
			User user = userService.selectByPrimaryKey(userGoods2.getUserId());
			if(1 == userGoods2.getIsVerify()){
				if(2 == userGoods.getAuthResult()){
					//无法鉴定
					if (user.getId() != null) {
						//推送
						userNotifyService.insertByTemplate(user.getId(), "7", PaidangMessage.AUTH_MAIL_OPER_CANNOT_AUTH,userGoods2.getName(),userGoods.getNotVerifyReason());
					}
				}else if(3 == userGoods.getAuthResult()){
					//赝品
					if (user.getId() != null) {
						//推送
						userNotifyService.insertByTemplate(user.getId(), "7", PaidangMessage.AUTH_MAIL_OPER_YP, userGoods2.getName());
					}
				}
			}

		}else{
			userGoods.setId(userGoods.getId());
			userGoods.setAuthResult(userGoods.getAuthResult());
			userGoods.setAuthPrice(userGoods.getAuthPrice());
			userGoodsService.updateByPrimaryKeySelective(userGoods);
			if (StringUtils.isNotEmpty(userGoods.getCerName())){
				Certificate entity = new Certificate();
				entity.setCreateTime(new Date());
				entity.setCreateYear(userGoods.getCreateYear());
				entity.setName(userGoods.getCerName());
				entity.setSize(userGoods.getSize());
				entity.setUserGoodsId(userGoods.getId());
				entity.setWeight(userGoods.getWeight());
				entity.setMainMaterial(userGoods.getMainMaterial());
				entity.setMaterial(userGoods.getMaterial());
				entity.setMarketLiquidity(userGoods.getMarketLiquidity());
				entity.setValueStability(userGoods.getValueStability());
				entity.setMaterialVulnerability(userGoods.getMaterialVulnerability());
				entity.setPrice(userGoods.getAuthPrice());
				certificateService.insert(entity);
			}
			UserGoods userGoods2 = userGoodsService.selectByPrimaryKey(userGoods.getId());
			User user = userService.selectByPrimaryKey(userGoods2.getUserId());
			if(1 == userGoods2.getIsVerify()){
				//经过鉴定者鉴定
				if(user != null) {
					//鉴定是真品
					//插入典当记录表
					pawnLogService.updatePawnlog(
							userGoods2.getId(),
							userGoods2.getBelongId(),
							null,
							userGoods2.getName(),
							userGoods.getAuthPrice(),
							userGoods2.getCateCode(),
							userGoods2.getCateId(),
							"",
							userGoods.getAuthPrice(),
							null,
							0,
							user.getName(),
							"",
							"");
					if (user.getId() != null) {
						//推送
						userNotifyService.insertByTemplate(user.getId(), "7", PaidangMessage.AUTH_MAIL_OPER, userGoods2.getName());
						userGoodsService.updateByPrimaryKeySelective(userGoods);


					}
				}
			}else{
				//通讯到authAdmin
				StringBuffer url = new StringBuffer();
				//查找admin权限--推送给admin
				AdminExample example = new AdminExample();
				example.createCriteria().andRoleCodeEqualTo("authAdmin");
				List<Admin> list = adminService.selectByExample(example);
				for(Admin admin : list){
					//url.append(ConstantsCode.SERVER_PUSH_URL+"?account="+admin.getId()+"&sys=authAdmin&msg={msg:'宝贝"+userGoods2.getName()+"请复审'}");
					try{
						Map<String, Object> pars = new HashMap<>();
						pars.put("account",admin.getId());
						pars.put("sys","admin");
						pars.put("msg","{msg:'宝贝"+userGoods2.getName()+"请复审'}");
						String result = HttpUtil.get(ConstantsCode.SERVER_PUSH_URL,pars);
					}catch (Exception e){
						e.printStackTrace();
						return ok();
					}

				}
			}

		}
    	return ok();
	}


	//鉴定者鉴定
	@RequestMapping("/authAdminVerify")
	@ResponseBody
	public Ret authAdminVerify(UserGoods userGoods) throws Exception {
		UserGoods userGoods2 = userGoodsService.selectByPrimaryKey(userGoods.getId());
		User user = userService.selectByPrimaryKey(userGoods2.getUserId());
		if(1 == userGoods.getIsVerify()){
			//经过鉴定者鉴定
			if(user != null) {
				//鉴定是真品
				//插入典当记录表
				if(4 == userGoods2.getAuthResult()){
					userNotifyService.insertByTemplate(user.getId(), "7", PaidangMessage.AUTH_MAIL_OPER, userGoods2.getName());
				}else if(2 == userGoods2.getAuthResult()){
					//无法鉴定
					if (user.getId() != null) {
						//推送
						userNotifyService.insertByTemplate(user.getId(), "7", PaidangMessage.AUTH_MAIL_OPER_CANNOT_AUTH,userGoods2.getName(),userGoods2.getNotVerifyReason());
					}
				}else if(3 == userGoods2.getAuthResult()){
					//赝品
					if (user.getId() != null) {
						//推送
						userNotifyService.insertByTemplate(user.getId(), "7", PaidangMessage.AUTH_MAIL_OPER_YP, userGoods2.getName());
					}
				}
				pawnLogService.updatePawnlog(
						userGoods2.getId(),
						userGoods2.getBelongId(),
						null,
						userGoods2.getName(),
						userGoods.getAuthPrice(),
						userGoods2.getCateCode(),
						userGoods2.getCateId(),
						"",
						userGoods2.getAuthPrice(),
						null,
						0,
						user.getName(),
						"",
						"");
				userGoodsService.updateByPrimaryKeySelective(userGoods);


				//通讯
				StringBuffer url = new StringBuffer();
				//查找admin权限--推送给admin
				AdminExample example = new AdminExample();
				example.createCriteria().andRoleCodeEqualTo("admin");
				List<Admin> list = adminService.selectByExample(example);
				for(Admin admin : list){
					url.append(ConstantsCode.SERVER_PUSH_URL+"?account="+admin.getId()+"&sys=admin&msg={msg:'宝贝"+userGoods2.getName()+"已经通过复审'}");
					try{
						String result = HttpUtil.get(url.toString());
					}catch (Exception e){
						return Ret.error(e.getMessage());
					}

				}

			}
		}
		return ok();
	}

	//查看在线鉴定详情
	@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
	@RequestMapping("/checkDetail")
	@ResponseBody
	public Ret checkDetail(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id",id);
		List<UserGoodsEx> list = userGoodsService.checkDetail(map);
        UserGoodsEx userGoodsEx = list.get(0);
        return ok(userGoodsEx);
	}

	@RequestMapping("/detail")
	@ResponseBody
	//卖给平台展示商品详情
	public Ret detail(Integer id){
		UserGoods userGoods = userGoodsService.selectByPrimaryKey(id);
		return ok(userGoods);
	}


	@RequestMapping("/goodsByOrgList")
	@ResponseBody
	//机构取回宝贝
	public TableDataInfo goodsByOrgList(Integer page, Integer rows, String name,Integer belongType, String code){
		startPage();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name",name);
		map.put("belongType",belongType);
		map.put("code",code);
		List<UserGoodsEx> list = userGoodsService.selectGoodsByOrgList(map);
		return page(list);
	}


	@RequestMapping("/paltBackToOrg")
	@ResponseBody
	//机构取回--平台邮寄
	public Ret paltBackToOrg(UserGoods userGoods){

		userGoods.setPlatUserName(MPawnMsg.comName);
		userGoods.setPlatUserPhone(MPawnMsg.phone);
		userGoods.setPlatUserAddress(MPawnMsg.comAddress);
		userGoods.setPlatOrgState(2);
		userGoodsService.updateByPrimaryKeySelective(userGoods);

		//插入物流表
		Express express = new Express();
		express.setSource(2);
		express.setSourceId(userGoods.getBelongId());
		express.setFid(userGoods.getId());
		express.setType(6);
		express.setExpressName(MExpressAddress.xfAddress);
		express.setExpressCode(userGoods.getPlatOrgExpressCode());
		express.setExpressState(0);
		express.setPostName(MPawnMsg.comName);
		express.setPostPhone(MPawnMsg.phone);
		express.setReceiveName(userGoods.getPlatUserName());
		express.setReceivePhone(userGoods.getPlatUserPhone());
		express.setReceviceAddress(userGoods.getPlatUserAddress());
		express.setCreateTime(new Date());
		expressService.insert(express);
		return ok();
	}

	@RequestMapping("/sellList")
	@ResponseBody
	public Object sellList(Integer page, Integer rows, String name){
		startPage();
		UserGoodsEx entity=new UserGoodsEx();
		entity.setName(name);
		List<UserGoodsEx> list=userGoodsService.findList(entity);
		return page(list);
	}

	@RequestMapping("/settle")
	@ResponseBody
	public Object settle(Integer id, BigDecimal settleMoney, String sellRemark){
		UserGoods entity=new UserGoods();
		entity.setId(id);
		entity.setSettleMoney(settleMoney);
		entity.setSellRemark(sellRemark);
		entity.setSettleStatus(1);
		userGoodsService.updateByPrimaryKeySelective(entity);
		return ok();
	}

	@RequestMapping("/changeCheck")
	@ResponseBody
	public Ret changeCheck(Integer id,Integer v,UserGoods userGoods){
		userGoods.setId(id);
		userGoods.setSellCheck(v==1?0:1); //审核状态：0不通过 1通过 2审核中
		userGoods.setSellStatus(v==0 || v==2?1:0); //上架状态：0未上架，1上架，2已卖出
		//未上架的时候清掉上架时间
		if(userGoods.getSellStatus() == 0){
			userGoods.setSellStartTime(null);
		}
		userGoodsService.updateByPrimaryKeySelective(userGoods);
		return ok();
	}

	@RequestMapping("/serverUrl/get")
	@ResponseBody
	public Ret getBaseUrl(){
		return ok(CoreConstants.SERVER_URL);
	}

}