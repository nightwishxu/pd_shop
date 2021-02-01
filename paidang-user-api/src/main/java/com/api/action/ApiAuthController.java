package com.api.action;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONObject;
import com.api.MEnumError;
import com.api.service.AuthService;
import com.base.action.CoreController;
import com.base.annotation.ApiMethod;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.dao.model.Result;
import com.base.util.BaseUtils;
import com.item.dao.model.User;
import com.item.dao.model.UserNotify;
import com.item.service.OrgAmountLogService;
import com.item.service.UserNotifyService;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.GoodsEx;
import com.paidang.daoEx.model.OrderEx;
import com.paidang.domain.qo.GoodsQo;
import com.paidang.domain.vo.AuthResultVo;
import com.paidang.domain.vo.OrderCollectInfoVo;
import com.paidang.domain.vo.OrderCollectVo;
import com.paidang.domain.vo.OrderPriceCollectVo;
import com.paidang.service.*;
import com.ruoyi.common.core.domain.Ret;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
@author sun
*/
@RequestMapping(value = "/api/auth", produces = { "application/json;charset=UTF-8" })
@RestController
@Api(tags = "商家端")
public class ApiAuthController extends CoreController {

    @Autowired
    private AuthPersonalService authPersonalService;

    @Autowired
	private AuthEnterpriseService authEnterpriseService;

    @Autowired
	private AuthService authService;

    @Autowired
	private UserService userService;

    @Autowired
	private UserReturnAddressService userReturnAddressService;

    @Autowired
	private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
	private ExpressService expressService;

//    @Autowired
//	private BusinessUserBalanceLogService businessUserBalanceLogService;


    @Autowired
	private BusinessUserInfoService businessUserInfoService;
    
    @Autowired
	private GoodsAttributeService goodsAttributeService;
    
    @Autowired
	private GoodsAuctionOnlineLogService goodsAuctionOnlineLogService;

    @Autowired
	private PawnOrgService pawnOrgService;

    @Autowired
	private OrgAmountLogService orgAmountLogService;

    @Autowired
	private UserNotifyService userNotifyService;

	@ApiOperation(value = "查询是否通过验证")
	@PostMapping("/passOrNot")
	@ApiMethod(isLogin = true)
	public AuthResultVo passOrNot(MobileInfo mobileInfo){
		int userId = mobileInfo.getUserId();
		return authService.passOrNot(userId);
	}


	@ApiOperation(value = "（重新）提交个人认证")
    @PostMapping("/save/personal")
	@ApiMethod(isLogin = true)
    public Integer savePersonal(MobileInfo mobileInfo, AuthPersonal authPersonal){
		int userId = mobileInfo.getUserId();
		authPersonal.setCreateUser(userId);
		authPersonal.setCreateTime(new Date());
		authPersonal.setState("0");
		AuthResultVo result = authService.passOrNot(userId);
		if(result.getStatus()!=-1 && authPersonal.getId() == null){
			throw new ApiException(-1,"当前人员已经提交过验证，不能重复提交");
		}
    	if (authPersonal.getId() == null){
			authPersonal.setCreateTime(new Date());
    		authPersonalService.insert(authPersonal);
    	}else{
    		authPersonalService.updateByPrimaryKeySelective(authPersonal);
    	}
       	return 1;
    }

	@PostMapping("/save/enterprise")
	@ApiOperation(value = "（重新）提交企业认证")
	@ApiMethod(isLogin = true)
	public Integer saveEnterprise(MobileInfo mobileInfo, AuthEnterprise authEnterprise){
		int userId = mobileInfo.getUserId();
		authEnterprise.setCreateTime(new Date());
		authEnterprise.setState("0");
		authEnterprise.setCreateUser(userId);
		AuthResultVo result = authService.passOrNot(userId);
		if(result.getStatus()!=-1 && authEnterprise.getId() == null){
			throw new ApiException(-1,"当前人员已经提交过验证，不能重复提交");
		}
		if (authEnterprise.getId() == null){
			authEnterprise.setCreateTime(new Date());
			authEnterpriseService.insert(authEnterprise);
		}else{
			authEnterpriseService.updateByPrimaryKeySelective(authEnterprise);
		}
		return 1;
	}

	@ApiOperation(value = "获取个人信息")
	@PostMapping("/get/userInfo")
	@ApiMethod(isLogin = true)
	public Object getUserInfo(MobileInfo mobileInfo){
		int userId = mobileInfo.getUserId();
		User user = userService.selectByPrimaryKey(userId);
		if(user == null){
			throw new ApiException(-1,"不存在该人员");
		}
		UserReturnAddressExample userReturnAddressExample = new UserReturnAddressExample();
		UserReturnAddressExample.Criteria criteria = userReturnAddressExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		userReturnAddressExample.setOrderByClause("is_default desc");
		List<UserReturnAddress> userReturnAddressList = userReturnAddressService.selectByExample(userReturnAddressExample);
		JSONObject userObject = (JSONObject) JSONObject.toJSON(user);
		userObject.put("returnAddress",userReturnAddressList);

		//查询当前人有没有申请个人认证
		AuthPersonalExample authPersonalExample = new AuthPersonalExample();
		authPersonalExample.createCriteria().andCreateUserEqualTo(userId).andStateNotEqualTo("3");
		List<AuthPersonal> authPersonals = authPersonalService.selectByExample(authPersonalExample);
		//查询当前人有没有申请企业认证
		AuthEnterpriseExample authEnterpriseExample = new AuthEnterpriseExample();
		authEnterpriseExample.createCriteria().andCreateUserEqualTo(userId).andStateNotEqualTo("3");
		List<AuthEnterprise> authEnterprises = authEnterpriseService.selectByExample(authEnterpriseExample);
		Integer orgId = null;
		if(authPersonals.size()==1 && authEnterprises.size()==0){
			userObject.put("authTypeInfo","个人用户");
			userObject.put("authType","1");
			userObject.put("store",authPersonals.get(0).getStoreName());
			userObject.put("logo",authPersonals.get(0).getLogo());
			orgId = authPersonals.get(0).getOrgId();
		}
		if(authPersonals.size()==0 && authEnterprises.size()==1){
			userObject.put("authTypeInfo","企业用户");
			userObject.put("authType","2");
			userObject.put("store",authEnterprises.get(0).getStoreName());
			userObject.put("logo",authEnterprises.get(0).getLogo());
			orgId = authEnterprises.get(0).getOrgId();
		}
		if (orgId!=null){
			PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(orgId);
			userObject.put("orgId",orgId);
			if (pawnOrg!=null){
				userObject.put("isStoreDeposit",pawnOrg.getIsStoreDeposit());
				userObject.put("depositAmount",pawnOrg.getDepositAmount());
				userObject.put("signature",pawnOrg.getSignature());
				userObject.put("storeContacts",pawnOrg.getStoreContacts());
				userObject.put("storePhone",pawnOrg.getStorePhone());
				userObject.put("logo",pawnOrg.getOrgLogo());
				userObject.put("auctionGoodsCount",goodsService.getAuctionCount(orgId,null));
				userObject.put("amount",pawnOrg.getAmount()==null?BigDecimal.ZERO:pawnOrg.getAmount());
				BusinessUserInfoExample example = new BusinessUserInfoExample();
				example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId());
				List<BusinessUserInfo> businessUserInfos = businessUserInfoService.selectByExample(example);
				if (CollectionUtils.isNotEmpty(businessUserInfos)){
					userObject.put("payPassword",businessUserInfos.get(0).getPayPassword());
				}
			}
		}
		return userObject;
	}

	@ApiOperation(value = "设置店铺基本信息")
	@PostMapping("/storeBaseInfo/set")
	@ApiMethod(isLogin = true)
	public Integer setUserStoreInfo(
			MobileInfo mobileInfo
			,@ApiParam(required = false,value = "签名")String signature
			,@ApiParam(required = false,value = "logo")String logo
			,@ApiParam(required = false,value = "联系人")String storeContacts
			,@ApiParam(required = false,value = "联系电话")String storePhone

	){
		JSONObject ret = authService.isPersonal(mobileInfo.getUserId());
		int code = ret.getInteger("code");
		int org_id = ret.getInteger("org_id");

		PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(org_id);
		if (pawnOrg==null){
			throw new ApiException(400,"机构信息异常");
		}
		PawnOrg tmp =new PawnOrg();
		tmp.setId(org_id);
		tmp.setOrgLogo(logo);
		tmp.setSignature(signature);
		tmp.setStoreContacts(storeContacts);
		tmp.setStorePhone(storePhone);
		return pawnOrgService.updateByPrimaryKeySelective(tmp);
	}

	@ApiOperation(value = "保存退货地址")
	@PostMapping("/saveOrUpdate/returnAddress")
	@ApiMethod(isLogin = true)
	public Integer saveReturnAddress(MobileInfo mobileInfo, UserReturnAddress userReturnAddress){
		int userId = mobileInfo.getUserId();
		userReturnAddress.setUserId(userId);
		if (userReturnAddress.getId() == null){
			userReturnAddress.setType(2);
			userReturnAddress.setCreateTime(new Date());
			userReturnAddress.setIsDefault(0);
			userReturnAddressService.insert(userReturnAddress);
		}else{
			userReturnAddress.setModifyTime(new Date());
			userReturnAddressService.updateByPrimaryKeySelective(userReturnAddress);
		}
		return 1;
	}

	@ApiOperation(value = "退货地址列表")
	@PostMapping("/returnAddress/list")
	@ApiMethod(isLogin = true)
	public List<UserReturnAddress> getReturnAddress(MobileInfo mobileInfo){
		int userId = mobileInfo.getUserId();
		UserReturnAddressExample userReturnAddressExample = new UserReturnAddressExample();
		UserReturnAddressExample.Criteria criteria = userReturnAddressExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<UserReturnAddress> userReturnAddressList = userReturnAddressService.selectByExample(userReturnAddressExample);
		return userReturnAddressList;
	}



	@ApiOperation(value = "设置默认地址")
	@PostMapping("/set/default")
	@ApiMethod(isLogin = true)
	public Integer setDefault(MobileInfo mobileInfo, @ApiParam(required = true,value = "退货地址id") @RequestParam("addressId") int addressId){
		int userId = mobileInfo.getUserId();
		UserReturnAddress userReturnAddress = userReturnAddressService.selectByPrimaryKey(addressId);
		if(userReturnAddress == null){
			throw new ApiException(-1,"不存在的退货地址");
		}
		if(userReturnAddress.getUserId() != userId){
			throw new ApiException(-1,"传入的地址与人员不匹配");
		}
		userReturnAddressService.updateNotDefault(userId);
		userReturnAddressService.updateDefault(addressId);
		return 1;
	}


	@ApiOperation(value="删除退货地址" )
	@PostMapping("/delete/returnAddress")
	@ApiMethod(isLogin = true)
	public Integer deleteReturnAddress(MobileInfo mobileInfo, @ApiParam(required = true,value = "退货地址id") @RequestParam("addressId") int addressId){
		int userId = mobileInfo.getUserId();
		UserReturnAddress userReturnAddress = userReturnAddressService.selectByPrimaryKey(addressId);
		if(userReturnAddress == null){
			throw new ApiException(-1,"不存在的退货地址");
		}
		if(userReturnAddress.getUserId() != userId){
			throw new ApiException(-1,"传入的地址与人员不匹配");
		}
		int isDefault = userReturnAddress.getIsDefault();
		userReturnAddressService.deleteByPrimaryKey(addressId);
		if(isDefault == 1){
			UserReturnAddressExample userReturnAddressExample = new UserReturnAddressExample();
			UserReturnAddressExample.Criteria criteria = userReturnAddressExample.createCriteria();
			criteria.andUserIdEqualTo(userId);
			List<UserReturnAddress> userReturnAddressList = userReturnAddressService.selectByExample(userReturnAddressExample);
			if(userReturnAddressList.size() >0){
				UserReturnAddress userReturnAddress1 = userReturnAddressList.get(0);
				userReturnAddressService.updateDefault(userReturnAddress1.getId());
			}
		}
		return 1;
	}

	@ApiOperation(value = "查看提交的认证信息")
	@PostMapping("/view/authInfo")
	@ApiMethod(isLogin = true)
	public Object authInfo(@ApiParam(required = true,value = "认证类型1个人 2企业") String type, MobileInfo mobileInfo){
		if("1".equals(type)){
			AuthPersonalExample authPersonalExample = new AuthPersonalExample();
			AuthPersonalExample.Criteria criteria  = authPersonalExample.createCriteria();
			criteria.andCreateUserEqualTo(mobileInfo.getUserId()).andStateNotEqualTo("3");
			return authPersonalService.selectByExample(authPersonalExample);
		}else if("2".equals(type)){
			AuthEnterpriseExample authEnterpriseExample = new AuthEnterpriseExample();
			AuthEnterpriseExample.Criteria criteria  = authEnterpriseExample.createCriteria();
			criteria.andCreateUserEqualTo(mobileInfo.getUserId()).andStateNotEqualTo("3");
			return authEnterpriseService.selectByExample(authEnterpriseExample);
		}
		return null;
	}

//	@PostMapping("/goods/save")
//	@ApiMethod(isLogin = true)
//	@ApiOperation(value = "商家端商品发布(更新)")
//	public Ret OrgGoodsSave(Goods goods, MobileInfo mobileInfo){
//		int userId = mobileInfo.getUserId();
//		//判断当前登录商家端的人是个人还是企业
//		JSONObject ret = authService.isPersonal(userId);
//		int code = ret.getInteger("code");
//		int org_id = ret.getInteger("org_id");
//		if(code==1){
//			goods.setSource(6);
//		}else if(code==2){
//			goods.setSource(7);
//		}else{
//			throw new ApiException(-1,"您还未验证，请验证过后再操作。");
//		}
//		String images = goods.getImgs();
//		String coverImg = images.split(",")[0];//第一张图作为封面
//		goods.setImg(coverImg);
//		if(goods.getId()!=null){
//			goods.setModifyTime(new Date());
//			goodsService.updateByPrimaryKeySelective(goods);
//			return ok("更新商品成功");
//		}
//
//		goods.setOrgId(org_id);
//		goods.setGoodsOwner(userId);//设置商品属于谁
//		goods.setIsOnline(2);//新增待上架
//		goods.setIsVerfiy(2);//默认通过
//		goods.setType(1);//类型：新品
//		goods.setSoldOut(0);//已售默认0
//		goods.setState(1);
//		goods.setWidth("20");//app绝当品拍卖页面没有这两个字段，但用户端需要这两字段，先接口写死
//		goods.setHeight("30");
//		goods.setCreateTime(new Date());
//		goodsService.insert(goods);
//		return ok("新增商品成功");
//	}


	@PostMapping("/goods/save")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "商家端商品发布(更新)")
	public Integer save(MobileInfo mobileInfo, @ApiParam(value = "商品分类",required = true)Integer cateCode, @ApiParam(value = "商品分名称",required = true)String name, @ApiParam(value = "商品价格",required = false)BigDecimal price
			, @ApiParam(value = "商品简介",required = true)String introduction, @ApiParam(value = "1 一口价 2 竞拍",required = true)Integer dealType
			, @ApiParam(value = "拍卖开始时间",required = false)Date auctionStartTime,@ApiParam(value = "id 新增不传 id 修改传",required = false) Integer id
			, @ApiParam(value = "拍卖结束时间",required = false)Date auctionEndTime, @ApiParam(value = "起拍价",required = false)BigDecimal startPrice
			, @ApiParam(value = "加价幅度",required = false)BigDecimal raisePriceRange, @ApiParam(value = "标签",required = false)String labels
			, @ApiParam(value = "商品属性",required = true)String goodsAttribute, @ApiParam(value = "商品图片",required = true)String imgs
            , @ApiParam(value = "视频",required = false) String bannerVideo
            ,@ApiParam(value = "库存 一口价传值 ",required = false) Integer total
			,@ApiParam(value = "商品编号",required = true) String goodsCode



	){
		if (dealType==1){
			BaseUtils.checkBlankParam(price,total);
		}else if (dealType==2){
			BaseUtils.checkBlankParam(auctionEndTime,auctionStartTime,startPrice,raisePriceRange);
			price = startPrice;
			total = 1;
			if (auctionStartTime.compareTo(auctionEndTime)>=0){
				throw new ApiException(-1,"竞拍结束时间不能早于开始时间");
			}
		}
		Date date = new Date();
		Goods goods = new Goods();


		int userId = mobileInfo.getUserId();
		//判断当前登录商家端的人是个人还是企业
		JSONObject ret = authService.isPersonal(userId);
		int code = ret.getInteger("code");
		int org_id = ret.getInteger("org_id");
		if(code==1){
			goods.setSource(6);
		}else if(code==2){
			goods.setSource(7);
		}else{
			throw new ApiException(-1,"您还未验证，请验证过后再操作。");
		}
		
		goods.setCateCode(cateCode);
		goods.setName(name);
		goods.setPrice(price);
		goods.setIntroduction(introduction);
		goods.setDealType(dealType);
//        goods.setOnlineTime(onlineTime);
//        goods.setUserId(mobileInfo.getUserId());
        goods.setBannerVideo(bannerVideo);
		goods.setAuctionStartTime(auctionStartTime);
		goods.setAuctionEndTime(auctionEndTime);
		goods.setRaisePriceRange(raisePriceRange);
		goods.setLabels(labels);
		goods.setGoodsAttribute(goodsAttribute);
		goods.setImgs(imgs);
		goods.setStartPrice(startPrice);
		goods.setTotal(total);
		goods.setGoodsCode(goodsCode);
//        goods.setState();
		if (id==null){
			goods.setGoodsOwner(userId);//设置商品属于谁
			goods.setType(1);
			goods.setIsOnline(0);
			goods.setOrgId(org_id);
			goods.setIsVerfiy(2);
			goods.setSoldOut(0);
			goods.setState(1);
			goods.setCreateTime(date);
			goodsService.insertSelective(goods);
//			if (dealType == 2){
//				//保存竞拍日志
//				GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
//				log.setAuctionEndTime(auctionEndTime);
//				log.setAuctionStartTime(auctionStartTime);
//				log.setGoodsId(goods.getId());
//				log.setStatus(0);
//				log.setCreateTime(date);
//				goodsAuctionOnlineLogService.insertSelective(log);
//				Goods tmp = new Goods();
//				tmp.setId(goods.getId());
//				tmp.setAuctionOnlineLogId(log.getId());
//				goodsService.updateByPrimaryKeySelective(tmp);
//			}
		}else {

			Goods goods1 = goodsService.selectByPrimaryKey(id);
			if (goods1.getIsOnline()==1 ){
				throw new ApiException(400,"请先下架再修改商品信息");
			}
//			if (goods1.getDealType()==2  && date.compareTo(goods1.getAuctionStartTime())>=0 && date.compareTo(goods1.getAuctionEndTime())<=0){
//				throw new ApiException(400,"竞拍中禁止修改商品信息");
//			}
			if(!Objects.equals(goods1.getGoodsOwner(),mobileInfo.getUserId())){
				throw new ApiException("用户信息异常");
			}
			goods.setId(id);
			goods.setModifyTime(new Date());
			goodsService.updateByPrimaryKeySelective(goods);
		}

		return 1;

	}


	@PostMapping("/goods/dismount")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "商品下架")
	public int OrgGoodsDismount(@ApiParam(required = true,name = "goodsId",value = "商品id") int goodsId,
								MobileInfo mobileInfo,
								@ApiParam(required = false,name = "reasonOfDismounting",value = "下架原因") String reasonOfDismounting){
		//判断当前商品是否是下架状态
		Goods goods = goodsService.selectByPrimaryKey(goodsId);
		Date date = new Date();
		if (goods.getDealType()==2 && date.compareTo(goods.getAuctionStartTime())>=0 && date.compareTo(goods.getAuctionEndTime())<=0){
			throw new ApiException(400,"竞拍中禁止下架");
		}

//		Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
		if (!Objects.equals(goods.getGoodsOwner(),mobileInfo.getUserId())){
			throw new ApiException(400,"用户信息异常");
		}
		if(goods.getIsOnline()==0||goods.getIsOnline()==2){
			throw new ApiException(MEnumError.DISMOUNT_STATE);
		}
		if(goods.getIsOnline()==-1){
			throw new ApiException(MEnumError.DELETE_STATE);
		}
		//修改上架信息
		if (goods.getDealType()==2){
			GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
			log.setId(goods.getAuctionOnlineLogId());
			log.setStatus(0);
			log.setModifyTime(date);
			log.setModifyAccount(mobileInfo.getUserId().toString());
			goodsAuctionOnlineLogService.updateByPrimaryKeySelective(log);
		}
		Goods updateGoods = new Goods();
		updateGoods.setId(goodsId);
		updateGoods.setIsOnline(0);
		updateGoods.setReasonOfDismounting(reasonOfDismounting);
		goodsService.updateByPrimaryKeySelective(updateGoods);
		return 1;
	}


	@PostMapping("/goods/online")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "商品上架")
	public Integer OrgGoodsonline(@ApiParam(required = true,name = "goodsId",value = "商品id") int goodsId,
							  MobileInfo mobileInfo){
		//判断当前商品是否是下架状态
		Goods goods = goodsService.selectByPrimaryKey(goodsId);
//		Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
//		if (!Objects.equals(orgId,goods.getOrgId())){
//			throw new ApiException(400,"机构异常");
//		}
		if(goods.getIsVerfiy()!=2){
			throw new ApiException(400,"商品未审核通过无法上架");
		}
		if(goods.getIsOnline()==-1){
			throw new ApiException(MEnumError.DELETE_STATE);
		}
		Date date = new Date();
		if (goods.getDealType()==2 && date.compareTo(goods.getAuctionStartTime())>=0){
			throw new ApiException(400,"已过竞拍开始时间，无法上架");
		}
		if (goods.getDealType()==1 && goods.getTotal()<=0){
			throw new ApiException(400,"库存不足,无法上架");
		}
		if (goods.getDealType()==2 && goods.getTotal()<=0){
			goods.setTotal(1);
		}
		goods.setIsOnline(1);
		goods.setMaxAutionId(null);
		goods.setMaxAuction(null);
		goods.setOnlineTime(new Date());
		goods.setReasonOfDismounting("");
		if (goods.getDealType()==2){
			GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
	 		log.setAuctionEndTime(goods.getAuctionEndTime());
	 		log.setAuctionStartTime(goods.getAuctionStartTime());
	 		log.setGoodsId(goods.getId());
	 		log.setStatus(1);
			log.setCreateTime(date);
			goodsAuctionOnlineLogService.insertSelective(log);
			goods.setAuctionOnlineLogId(log.getId());
		}
		goodsService.sellAuctionGoods(goods);
		return 1;
	}

	/**
	 * 			if (dealType!=null && dealType==2){
	 * 				if(date.compareTo(auctionStartTime)<0){
	 * 					boolean flag = false;
	 * 					if (auctionStartTime!=null && goods1.getAuctionStartTime().compareTo(auctionStartTime)!=0){
	 * 						flag = true;
	 *                                        }
	 * 					if (auctionEndTime!=null && goods1.getAuctionEndTime().compareTo(auctionEndTime)!=0){
	 * 						flag = true;
	 *                    }
	 * 					//修改上架记录表
	 * 					if (flag){
	 * 						GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
	 * 						log.setId(goods1.getAuctionOnlineLogId());
	 * 						log.setAuctionStartTime(auctionStartTime);
	 * 						log.setAuctionEndTime(auctionEndTime);
	 * 						log.setModifyTime(date);
	 * 						log.setModifyAccount(mobileInfo.getUserId().toString());
	 * 						goodsAuctionOnlineLogService.updateByPrimaryKeySelective(log);
	 *                    }* 				}else if (date.compareTo(auctionEndTime)>0){
	 * 					GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
	 * 					log.setAuctionEndTime(auctionEndTime);
	 * 					log.setAuctionStartTime(auctionStartTime);
	 * 					log.setGoodsId(id);
	 * 					log.setStatus(1);
	 * 					log.setCreateTime(date);
	 * 					goodsAuctionOnlineLogService.insertSelective(log);
	 * 					goods.setAuctionOnlineLogId(log.getId());
	 * 				}
	 *
	 * 			}
	 * @param state
	 * @param mobileInfo
	 * @param dealType
	 * @param pageNum
	 * @param pageSize
	 * @param goodsName
	 * @return
	 */

//	@PostMapping("/goods/dismount")
//	@ApiMethod(isLogin = true)
//	@ApiOperation(value = "商家端商品下架")
//	public Ret OrgGoodsDismount(@ApiParam(required = true,name = "goodsId",value = "商品id") int goodsId,
//							   MobileInfo mobileInfo,
//							   @ApiParam(required = true,name = "reasonOfDismounting",value = "下架原因") String reasonOfDismounting){
//		//判断当前商品是否是下架状态
//		Goods goods = goodsService.selectByPrimaryKey(goodsId);
//		if(goods.getIsOnline()==0||goods.getIsOnline()==2){
//			throw new ApiException(MEnumError.DISMOUNT_STATE);
//		}
//		if(goods.getIsOnline()==-1){
//			throw new ApiException(MEnumError.DELETE_STATE);
//		}
//		Goods updateGoods = new Goods();
//		updateGoods.setId(goodsId);
//		updateGoods.setIsOnline(0);
//		updateGoods.setReasonOfDismounting(reasonOfDismounting);
//		goodsService.updateByPrimaryKeySelective(updateGoods);
//		return ok("下架成功");
//	}
//
//	@PostMapping("/goods/online")
//	@ApiMethod(isLogin = true)
//	@ApiOperation(value = "商家端商品上架")
//	public Ret OrgGoodsonline(@ApiParam(required = true,name = "goodsId",value = "商品id") int goodsId,
//							   MobileInfo mobileInfo){
//		//判断当前商品是否是下架状态
//		Goods goods = goodsService.selectByPrimaryKey(goodsId);
//		if(goods.getIsOnline()==1){
//			throw new ApiException(MEnumError.ONLINE_STATE);
//		}
//		if(goods.getIsOnline()==-1){
//			throw new ApiException(MEnumError.DELETE_STATE);
//		}
//		goods.setIsOnline(1);
//		goods.setReasonOfDismounting("");
//		goodsService.updateByPrimaryKeySelective(goods);
//		return ok("上架成功");
//	}

	@PostMapping("/goods/getGoods")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "根据(状态)获取发布商品")
	public List<GoodsEx> getGoodsByOnline(@ApiParam(required = true,value = "0下架1上架2新增待上架") Integer state,
								 	MobileInfo mobileInfo, @ApiParam(value = "1 一口价(产品库) 2 竞拍（拍品库）")Integer dealType,
								   @ApiParam(value = "分页(不传则不分页)") Integer pageNum,
								   @ApiParam(value = "分页(不传则不分页)") String pageSize,
								   @ApiParam(value = "商品名称")String goodsName){
		int userId = mobileInfo.getUserId();
		if (BaseUtils.isAnyBlank(pageNum,pageSize)){
			startPage();
		}
		List<Integer> sources = new ArrayList<>();
		sources.add(6);sources.add(7);
		GoodsQo qo = new GoodsQo();
		qo.setSources(sources);
		qo.setGoodsOwner(userId);
		qo.setName(goodsName);
		qo.setIsOnline(state);
		qo.setDealType(dealType);
		qo.setIsOnlineCnt(1);
		List<GoodsEx> goodsList = goodsService.findListEx(qo);
		return goodsList;
	}


    @PostMapping("/goods/getGoodsDetail")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "获取商品详情")
    public GoodsEx goodsDetail(@ApiParam(required = true,value = "商品编号") Integer id){

        GoodsQo qo = new GoodsQo();
        qo.setId(id);
        List<GoodsEx> goodsList = goodsService.findListEx(qo);
        if (CollectionUtils.isNotEmpty(goodsList)){
            return goodsList.get(0);
        }
        return null;
    }

	@PostMapping("/goods/delete")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "删除发布的商品")
	public Integer deleteGood(MobileInfo mobileInfo,
								   @ApiParam(value = "商品id")int goodsId){
		//判断当前商品是否是下架状态
		Goods goods = goodsService.selectByPrimaryKey(goodsId);
		if(goods == null){
			throw new ApiException(-1,"不存在该商品");
		}
		if(goods.getIsOnline()==1){
			throw new ApiException(-1,"商品处于上架状态，请先下架。");
		}
		if(goods.getIsOnline()==-1){
			throw new ApiException(-1,"商品已经处于删除状态");
		}
		goods.setIsOnline(-1);
		goods.setReasonOfDismounting("");
		goodsService.updateByPrimaryKeySelective(goods);
		return 1;
	}

	@PostMapping("/order/orderList")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "根据(状态)获取订单列表")
	public Object getOrderList(@ApiParam(value = "-1已取消1待付款2已付款(待发货)3已发货（待收货）4确认收货5已评价") String state,
							   MobileInfo mobileInfo,
							   @ApiParam(value = "分页(不传则不分页)") Integer pageSize,
							   @ApiParam(value = "分页(不传则不分页)") String pageNum,
							   @ApiParam(value = "商品名称")String goodsName){
		int userId = mobileInfo.getUserId();
		if (BaseUtils.isAnyBlank(pageNum,pageSize)){
			startPage();
		}


		List<OrderEx> orderExes = orderService.getOrderByState(userId,goodsName,state);
		return orderExes;
	}

	@PostMapping("/order/collectInfo/get")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "订单汇总状态数量"	)
	public OrderCollectInfoVo getOrderCollectInfo(MobileInfo mobileInfo){
		List<OrderCollectVo> orderCountByState = orderService.getOrderCountByState(mobileInfo.getUserId());
		OrderCollectInfoVo result = new OrderCollectInfoVo();
		if (CollectionUtils.isNotEmpty(orderCountByState)){
			for (OrderCollectVo vo : orderCountByState) {
				if (vo.getState()==-1){
					result.setStateRefund(vo.getNum());
				}else if (vo.getState()==1){
					result.setState_1(vo.getNum());
				}else if (vo.getState()==2){
					result.setState_2(vo.getNum());
				}else if (vo.getState()==3){
					result.setState_3(vo.getNum());
				}else if (vo.getState()==4){
					result.setState_4(vo.getNum());
				}else if (vo.getState()==5){
					result.setState_5(vo.getNum());
				}
			}

		}
		return result;
	}

	@PostMapping("/financeInfo/get")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "机构财务汇总")
	public OrderPriceCollectVo  getOrderCollect(MobileInfo mobileInfo){
//		User user = userService.selectByPrimaryKey(mobileInfo.getUserId());
//		int orgId =user.getOrgId();
//		OrderPriceCollectVo vo = new OrderPriceCollectVo();
		OrderPriceCollectVo vo = new OrderPriceCollectVo();
		JSONObject ret = authService.isPersonal(mobileInfo.getUserId());
		int orgId = ret.getInteger("org_id");
		//待打款
		vo.setToBePay(orderService.getTotalOrderPrice(orgId,1));
		//待发货
		vo.setToBeDeliver(orderService.getTotalOrderPrice(orgId,2));
		//待收货
		vo.setToBeReceipt(orderService.getTotalOrderPrice(orgId,3));
		PawnOrg org = pawnOrgService.selectByPrimaryKey(orgId);
		//总计
		vo.setTotal(org.getAmount()==null?BigDecimal.ZERO:org.getAmount());
		return vo;
	}

	@PostMapping("/order/afterSales")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "获取售后订单列表")
	public Object afterSales(MobileInfo mobileInfo , @ApiParam(value = "分页(不传则不分页)") Integer pageNum,
                             @ApiParam(value = "分页(不传则不分页)") Integer pageSize,
                             @ApiParam(value = "商品名称")String goodsName){
		int userId = mobileInfo.getUserId();
		if (BaseUtils.isAnyBlank(pageNum,pageSize)){
			startPage();
		}
		List<OrderEx> orderExes = orderService.getAfterSalesOrder(String.valueOf(userId),goodsName);
		return orderExes;
	}

    @PostMapping("/order/experter")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "填写物流信息")
	@Transactional
    public Integer writeExperterInfo(@ApiParam(value = "物流公司") String shipFirm,
                                    @ApiParam(value = "物流单号") String shipCode,
                                    @ApiParam(value = "订单号") String orderCode,
                                    MobileInfo mobileInfo){
		int userId = mobileInfo.getUserId();
	    //先根据订单号查询订单信息
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andCodeEqualTo(orderCode);
        List<Order> orderList = orderService.selectByExample(orderExample);
        if(orderList.size()!=1){
            throw new ApiException(-1,"不存在此订单");
        }
        Order order = orderList.get(0);
        if(order.getState() != 2){//已付款待发货
            throw new ApiException(-1,"此订单状态不是已付款待发货，不能执行此操作!");
        }
        order.setShipFirm(shipFirm);
        order.setShipCode(shipCode);
        order.setState(3);
        orderService.updateByPrimaryKeySelective(order);

		User user = userService.selectByPrimaryKey(userId);

		synchronized (order.getId()) {
			ExpressExample example = new ExpressExample();
			example.createCriteria().andFidEqualTo(order.getId()).andTypeEqualTo(3);
			List<Express> list = expressService.selectByExample(example);
			if (list != null && list.size() > 0) {
				throw new ApiException(-1,"该订单已有物流信息!");
			}
			Express express = new Express();
			express.setSource(1);
			express.setSourceId(order.getUserId());
			express.setType(3);
			express.setFid(order.getId());
			express.setExpressState(0);
			express.setExpressName(shipFirm);
			express.setExpressCode(shipCode);
			express.setPostName(user.getName());
			express.setPostPhone(user.getPhone());
			express.setReceiveName(order.getShipUser());
			express.setReceviceAddress(order.getShipUser());
			express.setReceivePhone(order.getShipPhone());
			express.setCreateTime(new Date());
			expressService.insert(express);
		}

        return 1;
    }

    @PostMapping("/order/return")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "同意/不同意退货")
    public Integer writeExperterInfo(@ApiParam(value = "退款状态2同意 5不同意") int refState,
                                    @ApiParam(value = "不同意退款理由") String refundNotVerifyReason,
                                    @ApiParam(value = "订单号") String orderCode,
                                    @ApiParam(value = "退货地址编号") Integer returnAddressId,
                                    MobileInfo mobileInfo){
        //先根据订单号查询订单信息
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(orderCode);
        List<Order> orderList = orderService.selectByExample(example);
        if(orderList.size()!=1){
            throw new ApiException(-1,"不存在此订单");
        }
        Order order = orderList.get(0);
        if(order.getRefState() != 1){//已付款待发货
            throw new ApiException(-1,"此订单状态不是申请退款状态，不能执行此操作!");
        }
        if(refState == 5 && StringUtils.isBlank(refundNotVerifyReason)){
            throw new ApiException(-1,"不同意退款需要填写不同意理由!");
        }
        if (refState==2){
			UserReturnAddress userReturnAddress = userReturnAddressService.selectByPrimaryKey(returnAddressId);
			if (userReturnAddress ==null){
				throw new ApiException(400,"退货地址不存在");
			}
			order.setBackAddress(userReturnAddress.getAddress());
			order.setBackPhone(userReturnAddress.getPhone());
			order.setBackUser(userReturnAddress.getUserName());
		}
        order.setRefState(refState);
        order.setRefundNotVerifyReason(refundNotVerifyReason);
		int i = orderService.updateByPrimaryKeySelective(order);

		UserNotify userNotify = new UserNotify();
		userNotify.setIsRead(0);
		userNotify.setType(0); //不是系统消息
		userNotify.setUserId(order.getUserId());
		if(refState == 5){
			//拒绝退款
			userNotify.setRedirectType(10);  // 交易消息
			userNotify.setTitle("退款拒绝");
			userNotify.setContent(order.getGoodsName() + "退款拒绝"+"原因:"+refundNotVerifyReason);
			userNotify.setRedirectContent(order.getGoodsName() + "退款拒绝"+"原因:"+refundNotVerifyReason);
		}else if(refState == 2){
			//同意退款  --发送回寄信息
			userNotify.setRedirectType(5);  //邮寄通知
			userNotify.setTitle(order.getGoodsName() + "退款审核成功");
			userNotify.setContent("退款审核成功请邮寄至"+order.getBackAddress()+"收件人"+order.getBackUser()+"联系电话"+order.getBackPhone());
			userNotify.setRedirectContent("退款审核成功请邮寄至"+order.getBackAddress()+"收件人"+order.getBackUser()+"联系电话"+order.getBackPhone());
		}
		userNotify.setCreateTime(new Date());
		userNotifyService.insert(userNotify);
        return i;
    }


	@PostMapping("/order/return/confirm")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "确认退货")
	public Integer confirmReturnOrder(@ApiParam(value = "订单号") String orderCode,
						   MobileInfo mobileInfo){
		//先根据订单号查询订单信息
		OrderExample example = new OrderExample();
		OrderExample.Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(orderCode);
		List<Order> orderList = orderService.selectByExample(example);
		if(orderList.size()!=1){
			throw new ApiException(-1,"不存在此订单");
		}
		Order order = orderList.get(0);
		if (order.getRefState()!=3){
			throw new ApiException(400,"用户尚未完善退款信息");
		}
		Order temp = new Order();
		temp.setId(order.getId());
		temp.setRefState(4);
		temp.setModifyTime(new Date());
		orderService.updateByPrimaryKeySelective(temp);

		//同意退款
		if (order.getGoodsSource()!=5 &&  order.getState()>=4){
			OrgAmountLogExample logExample = new OrgAmountLogExample();
			logExample.createCriteria().andFidEqualTo(order.getId()).andOrgIdEqualTo(order.getOrgId()).andItemEqualTo("1");
			logExample.setOrderByClause("id desc");
			List<OrgAmountLog> orgAmountLogs = orgAmountLogService.selectByExample(logExample);
			if (CollectionUtils.isNotEmpty(orgAmountLogs)){
				OrgAmountLog log = orgAmountLogs.get(0);
				orgAmountLogService.saveLog(order.getOrgId(),null,log.getAmount(),"4","订单退款："+order.getCode(),order.getId(),null);
			}
		}
		return 1;
	}

//	@PostMapping("/order/return/express")
//	@ApiMethod(isLogin = true)
//	@ApiOperation(value = "退款信息")
//	public void getBackExpress(@ApiParam(value = "订单号") String expressCode,MobileInfo){
//
//	}

    @PostMapping("/order/info")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "订单详情")
    public Order orderInfo(@ApiParam(value = "订单号") String orderCode,
                           MobileInfo mobileInfo){
        //先根据订单号查询订单信息
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(orderCode);
        List<Order> orderList = orderService.selectByExample(example);
        if(orderList.size()!=1){
            throw new ApiException(-1,"不存在此订单");
        }
        return orderList.get(0);
    }

    @PostMapping("/pay/set/password")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "设置支付密码（第一次）")
	public Integer setPassword(@ApiParam(value = "支付密码") String password,
						 MobileInfo mobileInfo){
		int userId = mobileInfo.getUserId();
		//密码MD5加密
		String encryptHex = DigestUtil.md5Hex(password);
		BusinessUserInfoExample businessUserInfoExample = new BusinessUserInfoExample();
		BusinessUserInfoExample.Criteria criteria = businessUserInfoExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<BusinessUserInfo> businessUserInfos = businessUserInfoService.selectByExample(businessUserInfoExample);
		if(businessUserInfos.size()>1){
			throw new ApiException(-1,"存在异常数据。");
		}
		//存在人员数据，但是人员已经设置过密码
		if(businessUserInfos.size() == 0){
			throw new ApiException(-1,"当前人员还没有提交认证，或者认证还未通过审核，不能进行此操作。");
		}else if(businessUserInfos.size()==1&&StringUtils.isNotBlank(businessUserInfos.get(0).getPayPassword())){
			throw new ApiException(-1,"已经设置过密码，请使用重置密码功能。");
		}else if(businessUserInfos.size()==1&&StringUtils.isBlank(businessUserInfos.get(0).getPayPassword())){
			BusinessUserInfo businessUserInfo = businessUserInfos.get(0);
			businessUserInfo.setPayPassword(encryptHex);
			businessUserInfoService.updateByPrimaryKeySelective(businessUserInfo);
		}
		return 1;
	}


	@PostMapping("/pay/reset/password")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "重置支付密码")
	public Integer reSetPassword(@ApiParam(value = "原支付密码") String password,
							  @ApiParam(value = "第一次输入新支付密码") String newPassword,
							  @ApiParam(value = "第二次输入新支付密码") String reNewPassword,
							  MobileInfo mobileInfo){
		int userId = mobileInfo.getUserId();
		if(!newPassword.equals(reNewPassword)){
			throw new ApiException(-1,"两次输入的密码不一致");
		}
		BusinessUserInfoExample businessUserInfoExample = new BusinessUserInfoExample();
		BusinessUserInfoExample.Criteria criteria = businessUserInfoExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<BusinessUserInfo> businessUserInfos = businessUserInfoService.selectByExample(businessUserInfoExample);
		if(businessUserInfos.size()>1){
			throw new ApiException(-1,"存在异常数据。");
		}
		//存在人员数据，但是人员已经设置过密码
		if(businessUserInfos.size() == 0){
			throw new ApiException(-1,"当前人员还没有提交认证，或者认证还未通过审核，不能进行此操作。");
		}else if(businessUserInfos.size()==1){
			BusinessUserInfo businessUserInfo = businessUserInfos.get(0);
			if(StringUtils.isBlank(businessUserInfo.getPayPassword())&&StringUtils.isBlank(password)){
				setPassword(newPassword,mobileInfo);
			}else{
				String pass = businessUserInfo.getPayPassword();
				//密码对称加密 16进制
				String encryptHex = DigestUtil.md5Hex(password);

				if(!pass.equals(encryptHex)){
					throw new ApiException(-1,"原支付密码错误");
				}
				String newEncryptHex = DigestUtil.md5Hex(newPassword);
				businessUserInfo.setPayPassword(newEncryptHex);
				businessUserInfoService.updateByPrimaryKeySelective(businessUserInfo);
			}

		}
		return 1;
	}


	@PostMapping("/pay/forget/password/set")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "忘记支付密码 设置" )
	public Integer setForgetPassword(
								 @ApiParam(value = "第一次输入新支付密码") String newPassword,
								 @ApiParam(value = "第二次输入新支付密码") String reNewPassword,
								 MobileInfo mobileInfo){
		int userId = mobileInfo.getUserId();
		if(!newPassword.equals(reNewPassword)){
			throw new ApiException(-1,"两次输入的密码不一致");
		}
		BusinessUserInfoExample businessUserInfoExample = new BusinessUserInfoExample();
		BusinessUserInfoExample.Criteria criteria = businessUserInfoExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<BusinessUserInfo> businessUserInfos = businessUserInfoService.selectByExample(businessUserInfoExample);
		if(businessUserInfos.size()>1){
			throw new ApiException(-1,"存在异常数据。");
		}
		//存在人员数据，但是人员已经设置过密码
		if(businessUserInfos.size() == 0){
			throw new ApiException(-1,"当前人员还没有提交认证，或者认证还未通过审核，不能进行此操作。");
		}else if(businessUserInfos.size()==1){
			BusinessUserInfo businessUserInfo = businessUserInfos.get(0);
			if(StringUtils.isBlank(businessUserInfo.getPayPassword())){
				setPassword(newPassword,mobileInfo);
			}else{
				String newEncryptHex = DigestUtil.md5Hex(newPassword);
				businessUserInfo.setPayPassword(newEncryptHex);
				businessUserInfoService.updateByPrimaryKeySelective(businessUserInfo);
			}

		}
		return 1;
	}

	@PostMapping("/pay/password/verification")
	@ApiMethod(isLogin = true)
	@ApiOperation(value = "支付密码验证")
	public Integer verification(@ApiParam(value = "支付密码") String password,
								MobileInfo mobileInfo){
		int userId = mobileInfo.getUserId();
		BusinessUserInfoExample businessUserInfoExample = new BusinessUserInfoExample();
		BusinessUserInfoExample.Criteria criteria = businessUserInfoExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<BusinessUserInfo> businessUserInfos = businessUserInfoService.selectByExample(businessUserInfoExample);
		if(businessUserInfos.size()>1){
			throw new ApiException(-1,"存在异常数据。");
		}
		//存在人员数据，但是人员已经设置过密码
		if(businessUserInfos.size() == 0){
			throw new ApiException(-1,"当前人员还没有提交认证，或者认证还未通过审核，不能进行此操作。");
		}else if(businessUserInfos.size()==1){
			BusinessUserInfo businessUserInfo = businessUserInfos.get(0);
			if(StringUtils.isBlank(businessUserInfo.getPayPassword())){
				throw new ApiException(-1,"还未设置支付密码");
			}else{
				String pass = businessUserInfo.getPayPassword();
				String encryptHex = DigestUtil.md5Hex(password);
				if(!pass.equals(encryptHex)){
					throw new ApiException(-1,"支付密码不正确");
				}
			}
		}
		return 1;
	}

//	@PostMapping("/finance/info")
//	@ApiMethod(isLogin = true)
//	@ApiOperation(value = "查看个人财务情况")
//	public Map<String,Object> finance(MobileInfo mobileInfo){
//		int userId = mobileInfo.getUserId();
//		Map<String,Object> ret = new HashMap<>();
//		GoodsExample goodsExample = new GoodsExample();
//		GoodsExample.Criteria goodsCriteria = goodsExample.createCriteria();
//		goodsCriteria.andGoodsOwnerEqualTo(userId);
//		List<Goods> goodsList = goodsService.selectByExample(goodsExample);
//		List<Integer> goodsIds = new ArrayList<>();
//		for(Goods good : goodsList){
//			goodsIds.add(good.getId());
//		}
//		BigDecimal toBePai = authService.getTotalByOrderState(goodsIds,1);//待付款
//		BigDecimal toBeDelivered = authService.getTotalByOrderState(goodsIds,2);//待发货
//		BigDecimal toBeHarvested = authService.getTotalByOrderState(goodsIds,3);//待收货
//		ret.put("toBePai",toBePai);
//		ret.put("toBeDelivered",toBeDelivered);
//		ret.put("toBeHarvested",toBeHarvested);
//		BusinessUserInfoExample businessUserInfoExample = new BusinessUserInfoExample();
//		BusinessUserInfoExample.Criteria criteria = businessUserInfoExample.createCriteria();
//		criteria.andUserIdEqualTo(userId);
//		List<BusinessUserInfo> businessUserInfos = businessUserInfoService.selectByExample(businessUserInfoExample);
//		if (businessUserInfos.size()<1){
//			ret.put("total",0);
//		}else if(businessUserInfos.size()>1){
//			throw new ApiException(-1,"存在异常数据");
//		}else{
//			BusinessUserInfo businessUserInfo = businessUserInfos.get(0);
//			ret.put("total",businessUserInfo.getTotal());
//		}
//		ret.put("remark","toBePai:待付款,toBeDelivered:待发货,toBeHarvested:待收货,total:总收入");
//		return ret;
//	}

//
//	@PostMapping("/finance/details")
//	@ApiMethod(isLogin = true)
//	@ApiOperation(value = "查看个人资金记录")
//	public List<BusinessUserBalanceLog> getFinanceDetails(MobileInfo mobileInfo){
//		int userId = mobileInfo.getUserId();
//		BusinessUserBalanceLogExample businessUserBalanceLogExample = new BusinessUserBalanceLogExample();
//		BusinessUserBalanceLogExample.Criteria criteria = businessUserBalanceLogExample.createCriteria();
//		criteria.andUserIdEqualTo(userId);
//		businessUserBalanceLogExample.setOrderByClause("create_time");
//		List<BusinessUserBalanceLog> userBalanceLogs = businessUserBalanceLogService.selectByExample(businessUserBalanceLogExample);
//		return userBalanceLogs;
//	}

	/*@PostMapping("/finance/pay")
	@ApiMethod(isLogin = false)
	@ApiOperation(value = "转账到支付宝")
	public void pay() throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
				AlipayConfig.app_id,
				AlipayConfig.private_key,
				"json",
				"GBK",
				AlipayConfig.ali_public_key,
				"RSA2");
		AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
		request.setBizContent("{" +
				"\"out_biz_no\":\"2020060910130001\"," +
				"\"trans_amount\":0.01," +
				"\"product_code\":\"TRANS_ACCOUNT_NO_PWD\"," +
				"\"biz_scene\":\"DIRECT_TRANSFER\"," +
				"\"order_title\":\"测试转账\"," +
				"\"payee_info\":{" +
				"\"identity\":\"2088502842796333\"," +
				"\"identity_type\":\"ALIPAY_USER_ID\"," +
				"\"name\":\"黄龙国际有限公司\"" +
				"    }," +
				"\"remark\":\"单笔转账\"," +
				"\"business_params\":\"{\\\"sub_biz_scene\\\":\\\"REDPACKET\\\"}\"" +
				"  }");
		AlipayFundTransUniTransferResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
		}
	}*/

	@PostMapping("/goodsAttribute/get")
	@ApiMethod(isLogin = false)
	@ApiOperation(value = "商品属性获取")
	public List<GoodsAttribute> getGoodsAttribute(@ApiParam(value = "分类code,101:玉翠珠宝,102:工艺作品,103:文玩杂项,104:紫砂陶瓷,105:钱币邮票,106:书画篆刻,107:花鸟文娱,108:其他")Integer cateCode){
		GoodsAttributeExample example = new GoodsAttributeExample();

		example.createCriteria().andBelongToEqualTo(cateCode);

		return goodsAttributeService.selectByExample(example);

	}


}
