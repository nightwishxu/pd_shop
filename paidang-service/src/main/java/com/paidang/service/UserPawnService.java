package com.paidang.service;


import cn.hutool.core.date.DateUnit;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.DateUtil;
import com.base.util.StringUtils;
import com.item.dao.model.User;
import com.item.service.UserService;
import com.paidang.dao.UserPawnMapper;
import com.paidang.dao.model.*;
import com.paidang.daoEx.UserPawnMapperEx;
import com.paidang.daoEx.model.UserGoodsEx;
import com.paidang.daoEx.model.UserPawnEx;
import com.ruoyi.common.core.redis.RedisCache;
import com.util.PaidangConst;
import com.util.PaidangMessage;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserPawnService {


	private static final Logger logger = LoggerFactory.getLogger(UserPawnService.class);

	@Autowired
	private UserPawnMapper userPawnMapper;

	@Autowired
	private UserPawnMapperEx userPawnMapperEx;

	@Autowired
	private PawnContinueService pawnContinueService;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private UserService userService;

	@Autowired
	private UserGoodsService userGoodsService;

	@Autowired
	private UserPawnService userPawnService;

	@Autowired
	private PawnOrgService pawnOrgService;

	@Autowired
	private OrgNotifyService orgNotifyService;

	public int countByExample(UserPawnExample example) {
		return this.userPawnMapper.countByExample(example);
	}

	public UserPawn selectByPrimaryKey(Integer id) {
		return this.userPawnMapper.selectByPrimaryKey(id);
	}

	public List<UserPawn> selectByExample(UserPawnExample example) {
		return this.userPawnMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.userPawnMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(UserPawn record) {
		return this.userPawnMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(UserPawn record) {
		return this.userPawnMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(UserPawnExample example) {
		return this.userPawnMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(UserPawn record, UserPawnExample example) {
		return this.userPawnMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(UserPawn record, UserPawnExample example) {
		return this.userPawnMapper.updateByExample(record, example);
	}

	public int insert(UserPawn record) {
		return this.userPawnMapper.insert(record);
	}

	public int insertSelective(UserPawn record) {
		return this.userPawnMapper.insertSelective(record);
	}

	//获取当票信息
	public UserPawnEx getPawnTicketInfo(String pawnId){
		return this.userPawnMapperEx.getPawnTicketInfo(pawnId);
	}

	//最新当品
	public List<UserPawnEx> LatestPawnList(int auctionTime){
		return userPawnMapperEx.LatestPawnList(auctionTime);
	}

	//热门竞拍
	public List<UserPawnEx> hotAuctionsList(int auctionTime){
		return userPawnMapperEx.hotAuctionsList(auctionTime);
	}

	//根据PawnId获得UserPawnEx
	public UserPawnEx getUserPawnExById(String pawnId){
		return userPawnMapperEx.getUserPawnExById(pawnId);
	}

	//获取中标典当记录List
	public List<UserPawnEx> getMyBidWonList(String orgId){
		return userPawnMapperEx.getMyBidWonList(orgId);
	}

	//宝祥兜底
	public List<UserPawnEx> getBaoXiangList(String orgId){
		return userPawnMapperEx.getBaoXiangList(orgId);
	}

	//获取点击我的当户后的列表(本机构所属当户)
	public List<UserPawnEx> getMyPawnersList(String orgId,String name){
		return userPawnMapperEx.getMyPawnersList(orgId,name);
	}

	//获取 我的->当户和当品信息->页面下部当品列表
	public List<UserPawnEx> getPawnerzPawnList(String org_id,String pawnerId){
		return userPawnMapperEx.getPawnerzPawnList(pawnerId,org_id);
	}

	//获取登陆机构的用户赎当申请列表
	public List<UserPawnEx> getOrgRedeemList(String orgId,String pawnerName,String pawnTickerCode){
		return userPawnMapperEx.getOrgRedeemList(orgId,pawnerName,pawnTickerCode);
	}

	//获取登陆机构绝当列表
	public List<UserPawnEx> getOrgDeadPawnList(String orgId,String pawnerName,String pawnTicketCode){
		return userPawnMapperEx.getOrgDeadPawnList(orgId,pawnerName,pawnTicketCode);
	}

	//获取合同记录列表
	public List<UserPawnEx> getContractPawnList(String orgId){
		return userPawnMapperEx.getContractPawnList(orgId);
	}

	//获取当品当前状态和到期/逾期天数
	public Map<String,String> getPawnState(Integer pawnId){
		if (pawnId == null)
			return null;
		Date today = new Date();
		Date pawnEndTime;//被机构确认的续当截至日期
		UserPawn record = selectByPrimaryKey(Integer.valueOf(pawnId));
		if (record == null)
			return null;
		PawnContinueExample pawnContinueExample = new PawnContinueExample();
		pawnContinueExample.createCriteria().andPawnIdEqualTo(record.getId()).andStateEqualTo(4);
		pawnContinueExample.setOrderByClause("create_time desc");
		//被机构确认的续当
		List<PawnContinue> comfirmedContinueList = pawnContinueService.selectByExample(pawnContinueExample);
		if (comfirmedContinueList.size() == 0){
			pawnEndTime = record.getBeginPawnEndTime();//典当结束日期
		}else {
			pawnEndTime = comfirmedContinueList.get(0).getPawnEndTime();//被确认的续当中，最新的那次续当的结束时间
		}
		Integer state = record.getState();
		Map ret = new HashMap();
		if (state == 2 && today.compareTo(pawnEndTime) <= 0){//正常
			long days = DateUtil.between(record.getPawnEndTime(),today, DateUnit.DAY);
			ret.put("pawnState","0");
			ret.put("days",String.valueOf(days));
		}else if (state == 2 && today.compareTo(DateUtil.add(pawnEndTime, PaidangConst.BUFFER_DAYS)) <= 0){//逾期
			long days = DateUtil.between(pawnEndTime,today, DateUnit.DAY)+1;
			if (days > PaidangConst.BUFFER_DAYS)
				days = PaidangConst.BUFFER_DAYS;
			ret.put("pawnState","1");
			ret.put("days",String.valueOf(days));
		}else if (state == 4 || today.compareTo(DateUtil.add(pawnEndTime,PaidangConst.BUFFER_DAYS)) == 1){//绝当
			ret.put("pawnState","2");
		}
		return ret;
	}


	public List<UserPawnEx> selectAppList(Map<String, Object> map) {
		return this.userPawnMapperEx.selectAppList(map);
	}

    public  UserPawnEx selectByPawnConinueDetail(Map<String, Object> map) {
		return this.userPawnMapperEx.selectByPawnConinueDetail(map);
    }

	public UserPawnEx selectUserName(Map<String, Object> map) {
		return this.userPawnMapperEx.selectUserName(map);
	}

	public int updatebyPawnContinue(Map<String, Object> map) {
		return this.userPawnMapperEx.updatebyPawnContinue(map);
	}

    public UserPawnEx selectPawningDetail(Map<String, Object> map) {
		return this.userPawnMapperEx.selectPawningDetail(map);
    }

    public List<UserPawnEx> selectCancelPawnList(Map<String, Object> map) {
		return this.userPawnMapperEx.selectCancelPawnList(map);
    }
	public UserPawn selectByGoodsId(Integer id) {
		return this.userPawnMapperEx.selectByGoodsId(id);
	}

	//定时任务--竞拍时间结束没有人竞拍
    public List<UserPawnEx> selectTask(Map<String, Object> map) {
		return this.userPawnMapperEx.selectTask(map);
    }

    //我的典当列表--竞拍中
    public List<UserPawnEx> selectPawningList(Map<String, Object> map) {
		return this.userPawnMapperEx.selectPawningList(map);
    }

    //我的典当列表--典当中
	public List<UserPawnEx> selectMyPawnedList(Map<String, Object> map) {
		return this.userPawnMapperEx.selectMyPawnedList(map);
	}

	//平台查看所有典当记录
    public List<UserPawnEx> selectPawnRecord(Map<String, Object> map) {
		return this.userPawnMapperEx.selectPawnRecord(map);
    }

    public int updateUserPawn(UserPawn userPawn) {
        return this.userPawnMapperEx.updateUserPawn(userPawn);
    }

    public List<UserPawnEx> selectConList(Map<String, Object> map) {
		return this.userPawnMapperEx.selectConList(map);
    }

    //宝祥兜底
    public List<UserPawnEx> selectBxGet(Map<String, Object> map) {
		return this.userPawnMapperEx.selectBxGet(map);
    }

    public List<UserPawnEx> selectTradeRecordList(Map<String, Object> map) {
		return this.userPawnMapperEx.selectTradeRecordList(map);
    }

    public UserPawn getByProjectCode(String projectCode){
		UserPawnExample example = new UserPawnExample();
		example.createCriteria().andProjectCodeEqualTo(projectCode);
		List<UserPawn> userPawns = userPawnMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(userPawns)){
			return userPawns.get(0);
		}else {
			return null;
		}
	}

	/**
	 * 未典当商品去典当
	 * @param userId
	 * @param id
	 * @param loansPrice
	 * @param loansRate
	 * @param pawnTime
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public int gotoPawn(Integer userId,
						Integer id,
						String loansPrice,
						String loansRate,
						Integer pawnTime) throws Exception{
		if (!redisCache.getLock("gotoPawn:"+id,5)){
			throw new ApiException(400,"请求频繁请稍后");
		}
		UserGoodsEx userGoods = userGoodsService.selectGotoPawn(id);
		if (userGoods.getGotoPawn()!=null && userGoods.getGotoPawn()==1){
			throw new ApiException(400,"已典当");
		}
		userGoods.setGotoPawn(1);

		int result = userGoodsService.updateByPrimaryKeySelective(userGoods);
		if(result == 0){
			throw new ApiException(1006,"操作失败");
		}
		User user = userService.selectByPrimaryKey(userId);
		if (user==null){
			throw new ApiException("用户不存在");
		}
		//注册安心签账户
		if (StringUtils.isBlank(user.getAnxinsignId())){
			if (user.getIsBind()!=1){
				throw new ApiException(400,"请先进行实名认证");
			}
			String anxinUserId = AnXinSignService.personRegister(user.getIdCard(), user.getName(), user.getPhone());
			User temp = new User();
			temp.setId(userId);
			temp.setAnxinsignId(anxinUserId);
			temp.setModifyTime(new Date());
			userService.updateByPrimaryKeySelective(temp);
		}


		UserPawn userPawn2 = new UserPawn();
		userPawn2.setUserId(userGoods.getBelongId());
		userPawn2.setGoodsId(id);
		userPawn2.setLoansPrice(new BigDecimal(loansPrice));
		userPawn2.setLoansRate(new BigDecimal(loansRate));
		userPawn2.setIsVerify(0);
		userPawn2.setPawnBeginTime(new Date());
		userPawn2.setPawnEndTime(DateUtil.add(new Date(),pawnTime * 15-1));
		userPawn2.setBeginPawnEndTime(DateUtil.add(new Date(),pawnTime * 15-1));
//        userPawn.setPayeeName(userGoods.getUserName());
//        userPawn.setPayeePhone(userGoods.getUserPhone());
		userPawn2.setPayeeState(0);
		userPawn2.setPawnTime(pawnTime);
		userPawn2.setBeginPawnMonth(pawnTime);
		userPawn2.setState(1);
		userPawn2.setUserState(0);
		userPawn2.setUserName(userGoods.getUserName());
		userPawn2.setUserPhone(userGoods.getUserPhone());
		userPawn2.setUserIdCard(userGoods.getIdCard());
		userPawn2.setGoodsName(userGoods.getName());
		userPawn2.setCreateTime(new Date());
		//userPawn2.setOverdueRate(PaidangConst.REDEEM_OVERRATE);
		int reuslt2 = userPawnService.insert(userPawn2);
		if(reuslt2 == 0){
			throw new ApiException(1006,"操作失败");
		}
		logger.debug("========================用户端典当成功通知所有的机构端========================");
		//获取所有的机构端编号
		PawnOrgExample orgExample = new PawnOrgExample();
		orgExample.createCriteria().andTypeEqualTo(1);
		List<PawnOrg> orgList = pawnOrgService.selectByExample(orgExample);
		if(orgList.size() > 0){
			for(PawnOrg org:orgList){
				//推送
				orgNotifyService.insertByTemplate(org.getId(), "3", PaidangMessage.USER_PAWN_NOTIFY, userGoods.getName());
			}
		}
		return 1;

	}
}
