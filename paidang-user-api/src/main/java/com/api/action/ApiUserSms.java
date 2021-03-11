package com.api.action;

import com.api.MEnumError;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.api.sms.SmsError;

import com.base.util.StringUtil;
import com.item.dao.model.User;
import com.item.dao.model.UserExample;
import com.item.service.MobileVerifyService;
import com.item.service.SmsSendLogService;
import com.item.service.UserService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.redis.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value={"/api/userSms"}, produces={"application/json;charset=UTF-8"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
@Api(tags={"典当用户短信"})
public class ApiUserSms
        extends ApiBaseController
{
    @Autowired
    private UserService userService;
    @Autowired
    private SmsSendLogService smsSendLogService;
    @Autowired
    private MobileVerifyService verifyService;

    @Autowired
    private RedisCache redisCache;



    static String getKey(String phone)
    {
        return "BIND" + phone;
    }

    @ApiOperation("是否需要绑定手机号")
    @RequestMapping({"/isNeedBindMobile"})
    @ApiMethod
    public Object isLogin(@ApiParam(value="微信openid", required=false) String wxOpenid, @ApiParam(value="QQopenid", required=false) String qqOpenid, @ApiParam(value="设备类型 1:android 2:ios", required=true) Integer deviceType, @ApiParam(value="设备唯一识别码", required=false) String deviceid, @ApiParam(value="推送cid", required=false) String cid)
    {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtils.isNotBlank(wxOpenid)) {
            if ("undefined".equals(qqOpenid)){
                throw new ApiException(400,"openId获取异常");
            }
            criteria.andWxOpenidEqualTo(wxOpenid);
        }
        if (StringUtils.isNotBlank(qqOpenid)) {
            if ("undefined".equals(qqOpenid)){
                throw new ApiException(400,"openId获取异常");
            }
            criteria.andQqOpenidEqualTo(qqOpenid);
        }
        List<User> users = this.userService.selectByExample(userExample);
        if (users.size() > 0)
        {
            User user = users.get(0);
            MobileInfo mobileInfo = new MobileInfo();
            mobileInfo.setUserId(user.getId());
            mobileInfo.setDeviceid(deviceid);
            mobileInfo.setDeviceType(deviceType);
            String verify = this.verifyService.updateMobileVerify(mobileInfo, deviceType, cid);
            mobileInfo.setToken(verify);
            return mobileInfo;
        }
        Map<String,Object> result = new HashMap<>();
        result.put("errorCode",1);
        result.put("errorMsg","该第三方账号未绑定过手机");
        return result;
    }

    @ApiOperation("发送短信验证码")
    @RequestMapping({"/sendSms"})
    @ApiMethod
    public Object sendSms(@ApiParam(value="手机号", required=true) String phone, @ApiParam(value="设备唯一识别码", required=false) String deviceid, @ApiParam(value="微信openid", required=false) String wxOpenid, @ApiParam(value="QQopenid", required=false) String qqOpenid)
            throws Exception
    {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        example.or().andAccountEqualTo(phone).andTypeEqualTo(Integer.valueOf(0));
        example.or().andPhoneEqualTo(phone).andTypeEqualTo(Integer.valueOf(0));
        List<User> users = this.userService.selectByExample(example);
        if(users.size()>0){
            User user=users.get(0);
            if (StringUtils.isNotBlank(wxOpenid)&&StringUtils.isNotBlank(user.getWxOpenid())) {
                throw new ApiException(MEnumError.PHONE_HAS_BIND);
            }
            if (StringUtils.isNotBlank(qqOpenid)&&StringUtils.isNotBlank(user.getQqOpenid())) {
                throw new ApiException(MEnumError.PHONE_HAS_BIND);
            }

        }
        String mobileCode = StringUtil.getRandomNum(6);
        String content = new String("您的验证码是" + mobileCode + "不要告诉别人哦~");

        String result = this.smsSendLogService.sendIdentifySms(phone, deviceid, null, content,
                Integer.valueOf(1));
        if ("2".equals(result))
        {
            redisCache.expire(getKey(phone),mobileCode,300L);
        }
        else
        {
            String error = SmsError.getSmsError(result);
            if (StringUtils.isNotBlank(error))
            {
                this.logger.debug(error);
                throw new ApiException(MEnumError.SMS_ERROR.getCode(), error);
            }
            throw new ApiException(MEnumError.SMS_ERROR);
        }
        return new Ret(0);
    }

    public boolean checkCode(@ApiParam(value="手机号", required=true) String phone, @ApiParam(value="验证码", required=true) String code)
            throws Exception
    {
        if (StringUtils.isBlank(phone)) {
            throw new ApiException("phone");
        }
        if (StringUtils.isBlank(code)) {
            throw new ApiException("code");
        }
        String value = redisCache.getCacheObject(getKey(phone));
        if (!code.equals(value)) {
            throw new ApiException(MEnumError.MOBILE_CODE_ERROR);
        }
        redisCache.deleteObject(getKey(phone));

        return true;
    }

    @ApiOperation("确认绑定")
    @RequestMapping({"/bindMobile"})
    @ApiMethod
    public Object bindMobile(@ApiParam(value="手机号", required=true) String phone, @ApiParam(value="微信openid", required=false) String wxOpenid, @ApiParam(value="QQopenid", required=false) String qqOpenid, @ApiParam(value="������", required=true) String code, @ApiParam(value="�������� 1:android 2:ios", required=true) Integer deviceType, @ApiParam(value="��������������", required=false) String deviceid, @ApiParam(value="����cid", required=false) String cid)
            throws Exception
    {
        if (StringUtils.isBlank(phone)) {
            throw new ApiException("phone");
        }
        if (StringUtils.isBlank(code)) {
            throw new ApiException("code");
        }
        if (checkCode(phone, code))
        {
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            example.or().andAccountEqualTo(phone).andTypeEqualTo(Integer.valueOf(0));
            example.or().andPhoneEqualTo(phone).andTypeEqualTo(Integer.valueOf(0));
            List<User> users = this.userService.selectByExample(example);
            User user = null;
            if (users.size() > 0) {
                user = (User)users.get(0);
            } else {
                user = regist(phone);
            }
            if (StringUtils.isNotBlank(wxOpenid)) {
                if ("undefined".equals(wxOpenid)){
                    throw new ApiException(400,"openId获取异常");
                }
                user.setWxOpenid(wxOpenid);
            }
            if (StringUtils.isNotBlank(qqOpenid)) {
                if ("undefined".equals(qqOpenid)){
                    throw new ApiException(400,"openId获取异常");
                }
                user.setQqOpenid(qqOpenid);
            }
            this.userService.updateByPrimaryKeySelective(user);
            MobileInfo mobileInfo = new MobileInfo();
            mobileInfo.setUserId(user.getId());
            mobileInfo.setDeviceid(deviceid);
            mobileInfo.setDeviceType(deviceType);
            String verify = this.verifyService.updateMobileVerify(mobileInfo, deviceType, cid);
            mobileInfo.setToken(verify);
            return  mobileInfo;
        }
        throw new ApiException(-1, "验证码不正确");
    }

    private User regist(String phone)
            throws Exception
    {
        Date date = new Date();
        User record = new User();
        record.setAccount(phone);
        record.setPhone(phone);
        record.setCreateTime(date);
        record.setState(Integer.valueOf(1));
        record.setNickName("匿名用户");
        record.setBalance(BigDecimal.ZERO);
        record.setSex(Integer.valueOf(1));
        record.setCredit(Integer.valueOf(0));
        record.setIsBind(Integer.valueOf(0));
        record.setLoginTime(date);

        int cnt = this.userService.insertSelective(record);
        if (cnt == 0) {
            throw new ApiException(MEnumError.CREATE_ACCOUNT_ERROR);
        }
        return record;
    }
}
