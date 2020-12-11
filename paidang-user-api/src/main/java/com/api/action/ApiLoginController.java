package com.api.action;

import com.api.MEnumError;
import com.api.view.account.SmsCode;
import com.base.Const;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.api.sms.SmsError;

import com.base.util.StringUtil;
import com.item.dao.CodeMapper;
import com.item.dao.model.Code;
import com.item.dao.model.User;
import com.item.dao.model.UserExample;
import com.item.service.*;
import com.paidang.service.BFileService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.redis.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//import com.api.util.UserRongCloudUtil;

@RestController
@RequestMapping(value = "/api/account", produces = { "application/json;charset=UTF-8" })
@Api(tags = "账号登陆/注册")
public class ApiLoginController extends ApiBaseController {

	@Autowired
	private BaseService baseService;
	@Autowired
	private BFileService fileService;
	@Autowired
	private UserService userService;
	@Autowired
	private MobileVerifyService verifyService;
	@Autowired
	private UserOauthService oauthService;
	@Autowired
	private SmsSendLogService smsSendLogService;
	@Autowired
	private CodeMapper codeMapper;

	@Autowired
	private RedisCache redisCache;

	private enum MobileMsgEnum {
		REGIST(1), FORGET(2), LOGIN(3);
		private int code;

		private MobileMsgEnum(int code) {
			this.code = code;
		}

		public int getCode() {
			return this.code;
		}
		
		public String getPhone(String phone){
			return phone+ Const.SEP+this.name();
		}
	}

	@ApiOperation("清除对应userId APP打开次数记录")
	@RequestMapping(value="/delUserAppOpenCount",method = RequestMethod.POST)
	@ApiMethod
	public Object delUserAppOpenCount(@ApiParam(value="用户Id", required=true) String userId)
	{
		String key="AppOpenCount"+userId;
		redisCache.deleteObject(key);
		return new Ret();
	}

	@ApiOperation("根据APP打开次数是否去评论")
	@RequestMapping(value="/isNeedComment",method = RequestMethod.POST)
	@ApiMethod
	public Object countAppOpen(@ApiParam(value="用户Id", required=true) String userId)
	{
		//获取设置的记录次数
		Code open_count = codeMapper.selectByPrimaryKey("open_count");
		int openCount= Integer.valueOf(open_count.getValue());
		String key="AppOpenCount"+userId;
		Integer count=1;
		//获取配置
		if(redisCache.exists(key)){
			count=Integer.valueOf(redisCache.getCacheObject(key).toString())+1;
		}
		redisCache.setCacheObject(key,count);
		return count==openCount;
	}


	/**
	 * 获取验证码
	 * 
	 * @param phone
	 *            手机号
	 * @param type
	 *            1:注册,绑定手机 2:忘记密码
	 */
	@ApiOperation(value = "获取验证码", notes = "不需要登录")
	@RequestMapping(value = "/getMobileMsg", method = RequestMethod.POST)
	@ApiMethod
	public SmsCode getMobileMsg(
			@ApiParam(value = "手机号", required = true) String phone,
			@ApiParam(value = "1:注册,绑定手机 2:忘记密码 3:验证码登陆", required = true) Integer type,
			@ApiParam(value = "设备唯一识别码", required = true) String deviceid)
			throws Exception {
		if (StringUtils.isBlank(deviceid)) {
			throw new ApiException("deviceid");
		}
		if (StringUtils.isBlank(phone)) {
			throw new ApiException("phone");
		}
		if (type == null) {
			throw new ApiException("type");
		}

		// 查询
		UserExample example = new UserExample();
		example.createCriteria().andAccountEqualTo(phone).andTypeEqualTo(0);

		String key = phone;
		
		if (type == MobileMsgEnum.REGIST.getCode()) {// 注册
			int count = userService.countByExample(example);
			if (count > 0) {
				throw new ApiException(MEnumError.PHONE_EXISTS_ERROR);
			}
			key = MobileMsgEnum.REGIST.getPhone(phone);
		} else if (type == MobileMsgEnum.FORGET.getCode()) {
			int count = userService.countByExample(example);
			if (count == 0) {
				throw new ApiException(MEnumError.PHONE_NOTEXISTS_ERROR);
			}
			key = MobileMsgEnum.FORGET.getPhone(phone);
		}else if (type == MobileMsgEnum.LOGIN.getCode()) {
			key = MobileMsgEnum.LOGIN.getPhone(phone);
		}else {
			throw new ApiException(-1,"参数错误");
		}

		// 发送短信
		String mobileCode = StringUtil.getRandomNum(6);
		String content = new String("您的验证码是：" + mobileCode + "。不要告诉别人哦~");

		String result = smsSendLogService.sendIdentifySms(phone, deviceid,
				null, content,type);
		if ("2".equals(result)) {
			// 发送成功
			redisCache.expire(key, mobileCode,300L);
		} else {
			// 发送失败,可以细化错误原因
			String error = SmsError.getSmsError(result);
			if (StringUtils.isNotBlank(error)) {
				logger.debug(error);
				throw new ApiException(MEnumError.SMS_ERROR.getCode(),
						error);
			}
			throw new ApiException(MEnumError.SMS_ERROR);
		}

		SmsCode ret = new SmsCode();
		ret.setCode(mobileCode + "");
		return ret;
	}

	/**
	 * 验证验证码
	 * 
	 * @param phone
	 *            手机号
	 * @param code
	 *            验证码
	 */
	@ApiOperation(value = "验证验证码", notes = "不需要登录")
	@RequestMapping(value = "/checkCode", method = RequestMethod.POST)
	@ApiMethod
	public Ret checkCode(
			@ApiParam(value = "手机号", required = true) String phone,
			@ApiParam(value = "验证码", required = true) String code,
			@ApiParam(value = "1:注册,绑定手机 2:忘记密码 3:验证码登陆", required = true) Integer type)
			throws Exception {
		if (StringUtils.isBlank(phone)) {
			throw new ApiException("phone");
		}
		if (StringUtils.isBlank(code)) {
			throw new ApiException("code");
		}
		if (type == null) {
			throw new ApiException("type");
		}
		String key = phone;
		
		if (type == MobileMsgEnum.REGIST.getCode()) {// 注册
			key = MobileMsgEnum.REGIST.getPhone(phone);
		} else if (type == MobileMsgEnum.FORGET.getCode()) {
			key = MobileMsgEnum.FORGET.getPhone(phone);
		}else if (type == MobileMsgEnum.LOGIN.getCode()) {
			key = MobileMsgEnum.LOGIN.getPhone(phone);
		}else {
			throw new ApiException(-1,"参数错误");
		}
//		System.out.println(MobileCodeRedisCache.get(key));
		// 验证码验证
		String value = redisCache.getCacheObject(key);
		
		if (!code.equals(value)) {
			throw new ApiException(MEnumError.MOBILE_CODE_ERROR);
		}

		return new Ret(0);
	}

	/** 验证昵称
	 *
	 * @param nickName
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "验证昵称", notes = "不需要登录")
	@RequestMapping(value = "/checkNick", method = RequestMethod.POST)
	@ApiMethod
	public Ret checkNick(
			@ApiParam(value = "昵称", required = true) String nickName)
			throws Exception {
		if (StringUtils.isBlank(nickName)) {
			throw new ApiException("nickName");
		}
		UserExample example = new UserExample();
		example.createCriteria().andNickNameEqualTo(nickName);
		int i = userService.countByExample(example);
		if (i > 0) {
			throw new ApiException(MEnumError.NICK_EXISTS_ERROR);
		}
		return new Ret(0);
	}

	/**
	 * 注册
	 * 
	 * @param phone
	 *            手机号
	 * @param code
	 *            验证码
	 * @param password
	 *            密码(需要加密)
	 * @param deviceType
	 *            //设备类型 1:android 2:ios
	 * @param cid
	 *            //推送cid
	 */
	@ApiOperation(value = "注册", notes = "不需要登录")
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	@ApiMethod
	public MobileInfo regist(
			@ApiParam(value = "手机号", required = true) String phone,
			@ApiParam(value = "验证码", required = true) String code,
			@ApiParam(value = "密码(需要MD5加密)", required = true) String password,
			@ApiParam(value = "设备类型 1:android 2:ios", required = true) Integer deviceType,
			@ApiParam(value = "设备唯一识别码", required = false) String deviceid,
			@ApiParam(value = "推送cid", required = false) String cid)
			throws Exception {

		// 参数校验
		if (StringUtils.isBlank(phone)) {
			throw new ApiException("phone");
		}
		if (StringUtils.isBlank(code)) {
			throw new ApiException("code");
		}
		if (StringUtils.isBlank(password)) {
			throw new ApiException("password");
		}
		if (StringUtils.isBlank(deviceid)) {
			throw new ApiException("deviceid");
		}
		if (deviceType == null) {
			throw new ApiException("deviceType");
		}

		// 验证码验证
		String value = redisCache.getCacheObject(MobileMsgEnum.REGIST.getPhone(phone));
		
		if (!code.equals(value)) {
			throw new ApiException(MEnumError.MOBILE_CODE_ERROR);
		} else {
			redisCache.deleteObject(MobileMsgEnum.REGIST.getPhone(phone));
		}

		UserExample example = new UserExample();
		example.createCriteria().andAccountEqualTo(phone).andTypeEqualTo(0);
		int i = userService.countByExample(example);
		if (i > 0) {
			throw new ApiException(MEnumError.PHONE_EXISTS_ERROR);
		}



		User record = regist(phone, password, deviceType, deviceid, cid);

		MobileInfo mobileInfo = new MobileInfo();
		mobileInfo.setUserId(record.getId());
		mobileInfo.setDeviceid(deviceid);
		String verify = verifyService.updateMobileVerify(mobileInfo,
				deviceType, cid);
		mobileInfo.setDeviceType(deviceType);
		mobileInfo.setToken(verify);

		//融云用户注册
//		TokenResult imToken = UserRongCloudUtil.getToken(record.getId().toString(),"","");
//		record.setImToken(imToken.getToken());
//		userService.updateByPrimaryKeySelective(record);

		return mobileInfo;
	}

	private User regist(String phone, String password, Integer deviceType,
                        String deviceid, String cid) throws Exception {
		// 添加数据
		Date date = new Date();
		User record = new User();
		record.setAccount(phone);
		record.setPhone(phone);
		record.setCreateTime(date);
		record.setState(1);
		record.setType(0);
		record.setPassword(password);
		record.setNickName("匿名用户");
		record.setBalance(BigDecimal.ZERO);
		record.setSex(1);
		record.setCredit(0);
		record.setIsBind(0);
		record.setLoginTime(date);

		// 保存
		int cnt = userService.insertSelective(record);
		if (cnt == 0) {
			throw new ApiException(MEnumError.CREATE_ACCOUNT_ERROR);
		}

		return record;
	}

	/**
	 * @api 密码登录
	 * @param phone
	 *            手机号
	 * @param password
	 *            密码(需要加密)
	 * @param deviceType
	 *            设备类型 1:android 2:ios
	 * @param cid
	 *            //推送cid
	 */
	@ApiOperation(value = "密码登录")
	@RequestMapping(value = "/loginByPwd", method = RequestMethod.POST)
	@ApiMethod
	public MobileInfo loginByPwd(
			@ApiParam(value = "手机号", required = true) String phone,
			@ApiParam(value = "密码(需要MD5加密)", required = true) String password,
			@ApiParam(value = "设备类型 1:android 2:ios", required = true) Integer deviceType,
			@ApiParam(value = "设备唯一识别码", required = false) String deviceid,
			@ApiParam(value = "推送cid", required = false) String cid)
			throws Exception {
		// 参数校验
		if (StringUtils.isBlank(phone)) {
			throw new ApiException("phone");
		}
		if (StringUtils.isBlank(password)) {
			throw new ApiException("password");
		}
		if (deviceType == null) {
			throw new ApiException("deviceType");
		}
		if (StringUtils.isBlank(deviceid)) {
			throw new ApiException("deviceid");
		}
		// 校验登录
		UserExample example = new UserExample();
		example.createCriteria().andAccountEqualTo(phone).andTypeEqualTo(0);
		List<User> list = userService.selectByExample(example);
		if (list.size() == 0) {
			throw new ApiException(MEnumError.LOGIN_FAILURE_ERROR);
		}
		User user = list.get(0);
		if (!password.equals(user.getPassword())) {
			throw new ApiException(MEnumError.LOGIN_FAILURE_ERROR);
		}
		// 禁用
		if (user.getState() == 0) {
			throw new ApiException(MEnumError.ACCOUNT_STOP_ERROR);
		}
		MobileInfo mobileInfo = new MobileInfo();
		mobileInfo.setUserId(user.getId());
		mobileInfo.setDeviceid(deviceid);
		mobileInfo.setDeviceType(deviceType);
		String verify = verifyService.updateMobileVerify(mobileInfo,
				deviceType, cid);
		mobileInfo.setToken(verify);
		return mobileInfo;
	}


	/**
	 * @api 密码登录
	 * @param phone
	 *            手机号
	 * @param password
	 *            密码(需要加密)
	 * @param deviceType
	 *            设备类型 1:android 2:ios
	 * @param cid
	 *            //推送cid
	 */
	@ApiOperation(value = "验证码登录")
	@RequestMapping(value = "/loginByCode", method = RequestMethod.POST)
	@ApiMethod
	public MobileInfo loginByCode(
			@ApiParam(value = "手机号", required = true) String phone,
			@ApiParam(value = "验证码", required = true) String code,
			@ApiParam(value = "设备类型 1:android 2:ios", required = true) Integer deviceType,
			@ApiParam(value = "设备唯一识别码", required = false) String deviceid,
			@ApiParam(value = "推送cid", required = false) String cid)
			throws Exception {
		// 参数校验
		if (StringUtils.isBlank(phone)) {
			throw new ApiException("phone");
		}
		if (StringUtils.isBlank(code)) {
			throw new ApiException("code");
		}
		if (deviceType == null) {
			throw new ApiException("deviceType");
		}
		if (StringUtils.isBlank(deviceid)) {
			throw new ApiException("deviceid");
		}
		// 校验登录
		UserExample example = new UserExample();
		example.createCriteria().andAccountEqualTo(phone).andTypeEqualTo(0);
		List<User> list = userService.selectByExample(example);
		if (list.size() == 0) {
			throw new ApiException(MEnumError.LOGIN_FAILURE_ERROR);
		}
		User user = list.get(0);
		// 验证码验证
		String value = redisCache.getCacheObject(MobileMsgEnum.LOGIN.getPhone(phone));

		if (!code.equals(value)) {
			throw new ApiException(MEnumError.MOBILE_CODE_ERROR);
		} else {
			redisCache.deleteObject(MobileMsgEnum.LOGIN.getPhone(phone));
		}
		// 禁用
		if (user.getState() == 0) {
			throw new ApiException(MEnumError.ACCOUNT_STOP_ERROR);
		}
		MobileInfo mobileInfo = new MobileInfo();
		mobileInfo.setUserId(user.getId());
		mobileInfo.setDeviceid(deviceid);
		mobileInfo.setDeviceType(deviceType);
		String verify = verifyService.updateMobileVerify(mobileInfo,
				deviceType, cid);
		mobileInfo.setToken(verify);
		return mobileInfo;
	}

	/**
	 * 忘记密码
	 * 
	 * @param phone
	 *            手机号
	 * @param code
	 *            验证码
	 * @param password
	 *            密码(需要加密)
	 * @param deviceType
	 *            //设备类型 1:android 2:ios
	 * @param cid
	 *            //推送cid
	 */
	@ApiOperation(value = "忘记密码")
	@RequestMapping(value = "/forgetPwd", method = RequestMethod.POST)
	@ApiMethod
	public MobileInfo forgetPwd(
			@ApiParam(value = "手机号", required = true) String phone,
			@ApiParam(value = "验证码", required = true) String code,
			@ApiParam(value = "密码(需要加密)", required = true) String password,
			@ApiParam(value = "设备类型 1:android 2:ios", required = true) Integer deviceType,
			@ApiParam(value = "设备唯一识别码", required = false) String deviceid,
			@ApiParam(value = "推送cid", required = false) String cid)
			throws Exception {
		// 参数校验
		if (StringUtils.isBlank(phone)) {
			throw new ApiException("phone");
		}
		if (StringUtils.isBlank(code)) {
			throw new ApiException("code");
		}
		if (deviceType == null) {
			throw new ApiException("deviceType");
		}
		if (StringUtils.isBlank(deviceid)) {
			throw new ApiException("deviceid");
		}
		// 验证码验证
		String value = redisCache.getCacheObject(MobileMsgEnum.FORGET.getPhone(phone));
		if (!code.equals(value)) {
			throw new ApiException(MEnumError.MOBILE_CODE_ERROR);
		} else {
			redisCache.deleteObject(MobileMsgEnum.FORGET.getPhone(phone));
		}

		// 修改数据
		UserExample uexample = new UserExample();
		uexample.createCriteria().andAccountEqualTo(phone);
		List<User> list = userService.selectByExample(uexample);

		if (list.size() == 0) {
			throw new ApiException(MEnumError.PHONE_NOTEXISTS_ERROR);
		}

		User record = list.get(0);
		record.setPassword(password);
		int cnt = userService.updateByPrimaryKeySelective(record);
		if (cnt == 0) {
			throw new ApiException(MEnumError.UPDATE_ACCOUNT_ERROR);
		}
		

		MobileInfo mobileInfo = new MobileInfo();
		mobileInfo.setUserId(record.getId());
		mobileInfo.setDeviceType(deviceType);
		mobileInfo.setDeviceid(deviceid);
		//删除其他登陆设备
		verifyService.deleteOther(mobileInfo);
		String verify = verifyService.updateMobileVerify(mobileInfo,
				deviceType, cid);
		mobileInfo.setToken(verify);
		return mobileInfo;
	}

	/**
	 * 修改密码
	 * 
	 * @param oldPwd
	 *            旧密码(需要加密)
	 * @param newPwd
	 *            新密码(需要加密)
	 */

	/**
	 * 注销
	 */
	@ApiOperation(value = "注销*", notes = "")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ApiMethod(isLogin = true)
	public Ret logout(MobileInfo mobileInfo) throws Exception {
		verifyService.logout(mobileInfo);
		return new Ret();
	}

	/**
	 * @api 绑定手机
	 * @param phone
	 *            手机号
	 * @param password
	 *            密码
	 * @param code
	 *            短信验证码
	 */

}
