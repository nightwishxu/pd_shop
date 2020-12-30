package com.api.action;

import com.api.view.orgHome.OrgInfo;
import com.base.annotation.ApiMethod;
import com.base.util.StringUtils;
import com.google.protobuf.ServiceException;
import com.paidang.dao.model.PawnOrg;
import com.paidang.daoEx.model.PawnOrgEx;
import com.paidang.service.PawnOrgService;
import com.ruoyi.common.core.redis.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.MEnumError;
import com.api.util.PageLimit;
import com.api.view.home.UserInfo;
import com.api.view.home.UserIsReadCountInfo;
import com.api.view.home.UserNotifyInfo;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.DateUtil;
import com.base.util.StringUtil;
import com.item.dao.model.User;
import com.item.dao.model.UserExample;
import com.item.dao.model.UserNotify;
import com.item.dao.model.UserNotifyExample;
import com.item.service.UserNotifyService;
import com.item.service.UserService;

@RestController
@RequestMapping(value = "/api/home", produces = { "application/json;charset=UTF-8" })
//@Api(tags = "机构端个人中心")
public class ApiHomeController extends ApiBaseController{

	@Autowired
	private UserNotifyService userNotifyService;

	@Autowired
	private UserService userService;

	@Autowired
	private PawnOrgService pawnOrgService;

	@Autowired
	private RedisCache redisCache;



	@ApiOperation(value = "企业认证",notes="登陆")
	@RequestMapping(value="/editOrg", method = RequestMethod.POST)
	@ApiMethod(isLogin = true)
	public Integer editOrg(MobileInfo mobileInfo
			,@ApiParam(value = "机构名称", required = true)String name
			,@ApiParam(value = "法人名称", required = true)String legalPerson
			,@ApiParam(value = "身份证号码", required = true)String idCard
			,@ApiParam(value = "营业执照副本照片", required = true)String businessLicense
			,@ApiParam(value = "身份证正面", required = true)String idCardImg
			,@ApiParam(value = "身份证反面", required = true)String idCardReverse
			,@ApiParam(value = "典当许可证", required = true)String pawnExequatur
			,@ApiParam(value = "其他辅助文件", required = false)String otherFile
			,@ApiParam(value = "工商许可证号", required = true)String businessLicenseCode
			,@ApiParam(value = "机构电话", required = true)String phone
			,@ApiParam(value = "法人电话", required = true)String legalPersonPhone
			,@ApiParam(value = "固定电话", required = true)String landLinePhone

	){
		if (!redisCache.getLock("editOrg:"+mobileInfo.getUserId(),10)){
			throw new ApiException(400,"请求频繁，请稍后");
		}
		User user = userService.selectByPrimaryKey(mobileInfo.getUserId());
		if (user==null || user.getType()!=1){
			throw new ApiException(400,"用户信息异常");
		}
		Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
		PawnOrg pawnOrg  = new PawnOrg();
		if (orgId==-1){
			//没有认证企业
			pawnOrg.setName(name);
			pawnOrg.setLegalPerson(legalPerson);
			pawnOrg.setIdCard(idCard);
			pawnOrg.setBusinessLicense(businessLicense);
			pawnOrg.setIdCardImg(idCardImg);
			pawnOrg.setIdCardReverse(idCardReverse);
			pawnOrg.setPawnExequatur(pawnExequatur);
			pawnOrg.setOtherFile(otherFile);
			pawnOrg.setBusinessLicenseCode(businessLicenseCode);
			pawnOrg.setPhone(phone);
			pawnOrg.setLandLinePhone(landLinePhone);
			pawnOrg.setLegalPersonPhone(legalPersonPhone);
			pawnOrg.setCreateTime(new Date());
			pawnOrg.setType(1);
			pawnOrg.setAnxinPhone(user.getPhone());

			pawnOrg.setBalance(BigDecimal.ZERO);
			pawnOrg.setState(2);
			int i = pawnOrgService.insertSelective(pawnOrg);
			User tmp = new User();
			tmp.setId(mobileInfo.getUserId());
			tmp.setOrgId(pawnOrg.getId());
			tmp.setModifyTime(new Date());
			userService.updateByPrimaryKeySelective(tmp);
		}else {
			PawnOrg orgTmp = pawnOrgService.selectByPrimaryKey(orgId);
			//审核失败才可以修改资料
			if (orgTmp.getState()==3){
				pawnOrg.setId(orgId);
				pawnOrg.setName(name);
				pawnOrg.setBusinessLicenseCode(businessLicenseCode);
				pawnOrg.setLegalPerson(legalPerson);
				pawnOrg.setIdCard(idCard);
				pawnOrg.setBusinessLicense(businessLicense);
				pawnOrg.setIdCardImg(idCardImg);
				pawnOrg.setIdCardReverse(idCardReverse);
				pawnOrg.setPawnExequatur(pawnExequatur);
				pawnOrg.setLegalPersonPhone(legalPersonPhone);
				pawnOrg.setOtherFile(otherFile);
				pawnOrg.setModifyTime(new Date());
				pawnOrg.setPhone(phone);
				pawnOrg.setLandLinePhone(landLinePhone);
				pawnOrg.setState(2);
				if (StringUtils.isBlank(orgTmp.getAnxinPhone())){
					pawnOrg.setAnxinPhone(user.getAccount());
				}
				pawnOrgService.updateByPrimaryKeySelective(pawnOrg);
			}else {
				throw new ApiException(400,"当前状态不允许审核");
			}

		}
		return 1;
	}


	@ApiOperation(value = "获取企业认证资料 返回state = -1 时未认证",notes="登陆")
	@RequestMapping(value="/getOrgInfo", method = RequestMethod.POST)
	@ApiMethod(isLogin = true)
	public PawnOrg getOrgInfo(MobileInfo mobileInfo){
		Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
		if (orgId==-1){
			PawnOrg pawnOrg = new PawnOrg();
			pawnOrg.setState(-1);
			return pawnOrg;
		}
		PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(orgId);
		return pawnOrg;
	}



	@ApiOperation(value = "设置店铺基本信息")
	@PostMapping("/storeBaseInfo/set")
	@ApiMethod(isLogin = true)
	public Integer setUserStoreInfo(
			MobileInfo mobileInfo
			,@ApiParam(required = false,value = "签名")String signature
			,@ApiParam(required = false,value = "联系人")String storeContacts
			,@ApiParam(required = false,value = "联系电话")String storePhone

	){
		Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
		if (orgId==-1){
			throw new ApiException("机构信息异常");
		}
		PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(orgId);
		if (pawnOrg==null){
			throw new ApiException(400,"机构信息异常");
		}
		PawnOrg tmp =new PawnOrg();
		tmp.setId(orgId);
		tmp.setSignature(signature);
		tmp.setStoreContacts(storeContacts);
		tmp.setStorePhone(storePhone);
		return pawnOrgService.updateByPrimaryKeySelective(tmp);
	}


	/**
	 * 修改个人资料
	 * */
/*	@ApiOperation(value = "修改个人资料*",notes="登陆")
	@RequestMapping(value="/editUser", method = RequestMethod.POST)
	@ApiMethod(isLogin = true)
	public UserInfo editUser(MobileInfo mobileInfo,UserInfo userInfo){
		boolean update = false;
		User temp = new User();
		temp.setId(mobileInfo.getUserId());
		userInfo.setId(mobileInfo.getUserId());
		if (StringUtil.isNotBlank(userInfo.getHeadImg())){
			update = true;
			temp.setHeadImg(userInfo.getHeadImg());
		}
		if (StringUtil.isNotBlank(userInfo.getNickName())){
			update = true;
			// 校验登录
			UserExample example = new UserExample();
			example.createCriteria().andNickNameEqualTo(userInfo.getNickName());
			int i = userService.countByExample(example);
			if (i > 0) {
				throw new ApiException(MEnumError.NICK_EXISTS_ERROR);
			}
			temp.setNickName(userInfo.getNickName());
		}
		if (userInfo.getSex() != null){
			update = true;
			temp.setSex(userInfo.getSex());
		}
		if (update){
			//更新
			userService.updateByPrimaryKeySelective(temp);
		}
		temp = userService.selectByPrimaryKey(mobileInfo.getUserId());
		if (temp != null)
			userInfo.setAccount(temp.getAccount());
		return userInfo;
	}*/
	
	/**
	 * 获取用户未读消息数量
	 */
	@ApiOperation(value = "获取用户未读消息数量",notes="登陆")
	@RequestMapping(value = "/getUserIsReadCount", method = RequestMethod.POST)
	@ApiMethod(isLogin = true)
	public UserIsReadCountInfo getUserIsReadCount(MobileInfo mobileInfo){
		int count = 0;
		UserNotifyExample example = new UserNotifyExample();
		example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId()).andIsReadEqualTo(0);
	    count = userNotifyService.countByExample(example);
	    UserIsReadCountInfo userIsReadCountInfo = new UserIsReadCountInfo();
	    userIsReadCountInfo.setCount(count);
		return userIsReadCountInfo;
	}
	
	/**
	 * 获取用户消息列表
	 */
	@ApiOperation(value = "获取用户消息列表",notes="登陆,分页")
	@RequestMapping(value = "/getUserMsgList", method = RequestMethod.POST)
	@ApiMethod(isLogin = true,isPage = true)
	public List<UserNotifyInfo> getUserMsgList(MobileInfo mobileInfo,
											   PageLimit pageLimit){
		UserNotifyExample example = new UserNotifyExample();
		example.setOrderByClause("create_time desc");
		example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId());
		startPage();
		List<UserNotify> list = userNotifyService.selectByExample(example);		
		return getAppInfo(list);
	}
	
	/**
	 * 封装返回用户列表数据
	 * 
	 * @param list
	 * @return
	 */
	private List<UserNotifyInfo> getAppInfo(List<UserNotify> list) {
		List<UserNotifyInfo> ret = new ArrayList<>(list.size());
		for (UserNotify ex : list) {
			ret.add(getAppInfo(ex));
		}
		return ret;
	}

	/**
	 * 封装返回UserNotifyInfo
	 * 
	 * @param ex
	 * @return
	 */
	private UserNotifyInfo getAppInfo(UserNotify ex) {
		UserNotifyInfo mini = new UserNotifyInfo();
		mini.setId(ex.getId());
		mini.setTitle(ex.getTitle());
		mini.setContent(ex.getContent());
		mini.setCreateTime(DateUtil.dateToStr(ex.getCreateTime()));
		mini.setType(ex.getType());
		mini.setRedirectType(ex.getRedirectType());
		mini.setRedirectContent(ex.getRedirectContent());
		return mini;
	}
	
	/**
	 * 用户点击详情
	 */
	@ApiOperation(value = "用户点击详情",notes="可以不登陆")
	@RequestMapping(value = "/enterUserNotifyDetail", method = RequestMethod.POST)
	@ApiMethod
	public UserNotifyInfo enterUserNotifyDetail(MobileInfo mobileInfo,
			@ApiParam(value = "消息id", required = true)Integer id){
		if (id == null) {
			throw new ApiException("id");
		}
		UserNotify ex = userNotifyService.selectByPrimaryKey(id);
		if(ex == null){
			throw new ApiException(MEnumError.CONTENT_NOEXIST_ERROR);
		}
		UserNotifyInfo record = new UserNotifyInfo();
		record.setTitle(ex.getTitle());
		record.setId(ex.getId());
		record.setType(ex.getType());
		record.setContent(ex.getContent());
		record.setRedirectType(ex.getRedirectType());
		record.setIsRead(ex.getIsRead());
		if(mobileInfo.getUserId()!=null){			
			
			ex.setIsRead(1);
			userNotifyService.updateByPrimaryKey(ex);
		}
		return record;
	}
	
	/**
	 * 删除用户消息列表
	 */
	@ApiOperation(value = "删除用户消息列表",notes="登陆")
	@RequestMapping(value = "/delNotifyDetail", method = RequestMethod.POST)
	@ApiMethod(isLogin = true)
	public Integer delNotifyDetail(MobileInfo mobileInfo, 
			@ApiParam(value = "消息id", required = true)Integer id){
		if (id == null) {
			throw new ApiException("id");
		}
		UserNotify record = userNotifyService.selectByPrimaryKey(id);
		if(record == null){
			throw new ApiException(MEnumError.CONTENT_NOEXIST_ERROR);
		}
		userNotifyService.deleteByPrimaryKey(id);
		return 1;
	}
	
}
