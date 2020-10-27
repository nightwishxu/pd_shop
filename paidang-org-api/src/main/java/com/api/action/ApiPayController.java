package com.api.action;

import com.api.MEnumError;
import com.api.view.pay.PayResult;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.pay.PayMethod;
import com.base.pay.ali.AlipayConfig;
import com.base.util.LogKit;
import com.base.util.StringUtil;
import com.item.dao.model.PayLog;
import com.item.service.PayLogService;
import com.paidang.dao.model.PawnContinue;
import com.paidang.dao.model.UserPawn;
import com.paidang.service.PawnContinueService;
import com.paidang.service.UserPawnService;
import com.util.MPaidangPayType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value="/api/pay", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
@Api(tags = "支付接口")
public class ApiPayController extends ApiBaseController{

	@Autowired
	private PayLogService payLogService;
	@Autowired
	private UserPawnService userPawnService;
	@Autowired
	private PawnContinueService pawnContinueService;
	
	@ApiOperation(value = "典当手续费", notes = "登陆")
	@RequestMapping("/pawnService")
	@ApiMethod(isLogin = true)
	public PayResult pawnService(MobileInfo mobileInfo,
			@ApiParam(value = "支付方式:1:支付宝 2:微信", required = true)Integer platform,
							 @ApiParam("典当id") Integer pawnId){
		LogKit.debug("===================典当手续费,platform = "+platform+"=====pawnId="+pawnId+"===============");
		if (platform == null){
			throw new ApiException("platform");
		}
		if (pawnId == null){
			throw new ApiException("money");
		}

		UserPawn pawn = userPawnService.selectByPrimaryKey(pawnId);
		if (pawn == null){
			LogKit.debug("==================UserPawn is null=================");
			throw new ApiException(MEnumError.CONTENT_NOEXIST_ERROR);
		}

		if (pawn.getPlatformState() == 1){
			LogKit.debug("==================UserPawn already pay=================");
			throw new ApiException(MEnumError.PLATFORM_ALREADY_PAY);
		}

		if (pawn.getPlatformMoney() == null || pawn.getPlatformMoney().compareTo(BigDecimal.ZERO) == 0){
			//不需要支付?抛异常?
			LogKit.debug("==================UserPawn state error=================");
			throw new ApiException(MEnumError.PAY_PLATFORM_ZERO);
		}

		
		PayLog log = new PayLog();
		log.setOrderId(pawn.getId());
		log.setUserId(mobileInfo.getUserId());
		log.setState(0);
		log.setMoney(pawn.getPlatformMoney());
		log.setParam(MPaidangPayType.ORG_RATE.name());
		payLogService.insertSelective(log);
		
		PayResult result = new PayResult();
		result.setPlatform(platform);
		switch (platform) {
		case 1:
			//支付宝
			result.setId(log.getId().toString()+"_"+MPaidangPayType.ORG_RATE.name());
			result.setMoney(pawn.getPlatformMoney().toString());
			result.setBackUrl(PayMethod.urlToUrl(AlipayConfig.notify_url));
			break;
		case 2:
			//微信
			String wxId = PayMethod.wxPrepayId(pawn.getPlatformMoney(), log.getId().toString(), "典当平台服务费",MPaidangPayType.ORG_RATE);
			if (StringUtil.isBlank(wxId)){
				throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
			}
			result.setId(wxId);
			break;
		default:
			throw new ApiException(MEnumError.PAY_TYPE_ERRPR);
		}
		if (platform == 2){
			LogKit.debug("======================wxId:"+result.getId()+"==================");
		}
		return result;
	}

	@ApiOperation(value = "续当手续费", notes = "登陆")
	@RequestMapping("/pawnContinue")
	@ApiMethod(isLogin = true)
	public PayResult pawnContinue(MobileInfo mobileInfo,
							  @ApiParam(value = "支付方式:1:支付宝 2:微信", required = true)Integer platform,
							  @ApiParam("续当id") Integer pawnContinueId){
		LogKit.debug("=================续当手续费,platform = "+platform+"=====pawnContinueId="+pawnContinueId+"===============");
		if (platform == null){
			throw new ApiException("platform");
		}
		if (pawnContinueId == null){
			throw new ApiException("money");
		}

		PawnContinue pawn = pawnContinueService.selectByPrimaryKey(pawnContinueId);
		if (pawn == null){
			LogKit.debug("==================PawnContinue is null=================");
			throw new ApiException(MEnumError.CONTENT_NOEXIST_ERROR);
		}

		if (pawn.getPlatformState() == 1){
			LogKit.debug("==================PawnContinue already pay=================");
			throw new ApiException(MEnumError.PLATFORM_ALREADY_PAY);
		}

		if (pawn.getPlatformMoney() == null || pawn.getPlatformMoney().compareTo(BigDecimal.ZERO) == 0){
			//不需要支付?抛异常?
			LogKit.debug("==================PawnContinue state error=================");
			throw new ApiException(MEnumError.PAY_PLATFORM_ZERO);
		}


		PayLog log = new PayLog();
		log.setOrderId(pawn.getId());
		log.setUserId(mobileInfo.getUserId());
		log.setState(0);
		log.setMoney(pawn.getPlatformMoney());
		log.setParam(MPaidangPayType.ORG_CONTINUE.name());
		payLogService.insertSelective(log);

		PayResult result = new PayResult();
		result.setPlatform(platform);
		switch (platform) {
			case 1:
				//支付宝
				result.setId(log.getId().toString()+"_"+MPaidangPayType.ORG_CONTINUE.name());
				result.setMoney(pawn.getPlatformMoney().toString());
				result.setBackUrl(PayMethod.urlToUrl(AlipayConfig.notify_url));
				break;
			case 2:
				//微信
				String wxId = PayMethod.wxPrepayId(pawn.getPlatformMoney(), log.getId().toString(), "续当平台服务费",MPaidangPayType.ORG_CONTINUE);
				if (StringUtil.isBlank(wxId)){
					throw new ApiException(MEnumError.SERVER_BUSY_ERROR);
				}
				result.setId(wxId);
				break;
			default:
				throw new ApiException(MEnumError.PAY_TYPE_ERRPR);
		}
		if (platform == 2){
			LogKit.debug("======================wxId:"+result.getId()+"==================");
		}
		return result;
	}
}
