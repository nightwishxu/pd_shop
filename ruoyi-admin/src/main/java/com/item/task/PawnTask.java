package com.item.task;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;

import com.base.exception.SystemException;

import com.base.util.DateUtil;
import com.item.dao.model.User;
import com.item.service.SmsSendLogService;
import com.item.service.UserNotifyService;
import com.item.service.UserService;
import com.paidang.dao.model.Goods;
import com.paidang.dao.model.UserGoods;
import com.paidang.dao.model.UserPawn;
import com.paidang.dao.model.UserPawnExample;
import com.paidang.daoEx.model.OrderEx;
import com.paidang.service.*;
import com.util.PaidangConst;
import com.util.PaidangMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class PawnTask {

	private static Logger logger = LoggerFactory.getLogger(PawnTask.class);

	@Autowired
	private UserService userService;
	@Autowired
	private UserPawnService userPawnService;
	@Autowired
	private PawnAuctionService pawnAuctionService;
//	@Autowired
//	private TaskLogService taskLogService;
	@Autowired
	private UserGoodsService userGoodsService;
	@Autowired
	private OrgNotifyService orgNotifyService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsAuctionService goodsAuctionService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserNotifyService userNotifyService;
	@Autowired
	private SmsSendLogService smsSendLogService;

	/**
	 * 当品竞拍时间结束定时任务
	 */
	//每一分钟执行一次 0 */1 * * * ?
//	@Scheduled(cron ="0 */1 * * * ?")
//	public void pawn(){
//		long start = System.currentTimeMillis();
////		UserPawnExample example = new UserPawnExample();
////		example.createCriteria().andStateEqualTo(1).andCreateTimeLessThanOrEqualTo(DateUtil.offsetSecond(new Date(),-PaidangConst.AUCTION_TIME));
////		List<UserPawn> list = userPawnService.selectByExample(example);
//
//		Map<String, Object> map = new HashMap<>();
//		map.put("time",DateUtil.offsetSecond(new Date(),-PaidangConst.AUCTION_TIME));
//		List<UserPawnEx> list = userPawnService.selectTask(map);
//
//		/*
//		* 竞拍时间结束后，如果该典当在竞拍有效时间内无人出价竞拍，state设置成 -1-已取消
//		* 若竞拍有效时间之内有至少一家机构对其发起竞拍，state字段还是保持为 1-竞拍中，竞拍时间结束后，这条典当任何机构将不能进行出价竞拍
//		* 但是用户还是可以选择之前竞拍过的机构作为中标机构，或者选择宝祥兜底
//		* */
//		//竞拍结束
//		for (UserPawnEx pawn : list){
//			//是否有人竞拍
// 			int cnt = pawn.getCnt();
// 			System.out.println("竞拍商品id=" + pawn.getId() + ",是否有人竞拍=" + cnt + ",goodId=" +pawn.getGoodsId());
//			//TODO
//			if(cnt == 0){
//				pawn.setState(-1);
////				//时间到还在竞拍中--等待用户选择宝祥兜底
////				int cnt1 = userPawnService.updateByPrimaryKeySelective(pawn);
////				if(cnt1 > 0) {
////					//再将用户典当物品放回到 “我的宝贝”中 ----20191209新增逻辑
////					UserGoods userGoods = userGoodsService.selectByPrimaryKey(pawn.getGoodsId());
////					if(userGoods.getGotoPawn() == 1) {
////						userGoods.setGotoPawn(0); //是否去典当 0否
////						userGoodsService.updateByPrimaryKeySelective(userGoods);
////					}
////				}
////			}else{
//				userPawnService.updateByPrimaryKeySelective(pawn);
//			}
//
//		}
//		logger.debug("典当时间任务时间-------"+(System.currentTimeMillis() - start));
//	}

	/*
	* 用户选择中标机构，该机构在接下来的1小时内未支付贷款定时任务
	*
	* 每分钟运行一次
	* */
	@Scheduled(cron ="0 */1 * * * ?")
	public void payLimit(){
		UserPawnExample userPawnExample = new UserPawnExample();
		userPawnExample.or().andOrgIdIsNotNull().andStateEqualTo(2).andUserStateEqualTo(2);
		userPawnExample.or().andOrgIdIsNotNull().andStateEqualTo(1).andUserStateEqualTo(2);
		userPawnExample.or().andOrgIdIsNotNull().andStateEqualTo(5).andUserStateEqualTo(2);
		List<UserPawn> records = userPawnService.selectByExample(userPawnExample);
		Date now = new Date();
		if(records.size() > 0){
			for (UserPawn element:records) {
				Date orgSelectedTime = element.getOrgSelectedTime();
				if (orgSelectedTime == null){
					throw new SystemException("======支付贷款限时定时任务：典当id是"+element.getId()+"的记录选择中标典当行字段为空======");
				}else {
					Date validTime = DateUtil.offsetMinute(orgSelectedTime, PaidangConst.PAY_LIMIT_TIME);
					int s = now.compareTo(validTime);
					if (now.compareTo(validTime) > 0 && element.getPayeeTicket() == null){
						element.setState(-1);
						element.setUserState(4);
						userPawnService.updateByPrimaryKeySelective(element);
						UserGoods userGoods = userGoodsService.selectByPrimaryKey(element.getGoodsId());
						if (userGoods == null){
							throw new SystemException("======支付贷款限时定时任务：典当id是"+element.getId()+"的记录其当品在藏品表为空======");
						}
						userGoods.setGotoPawn(0);
						userGoodsService.updateByPrimaryKeySelective(userGoods);
					}
				}
			}
		}
	}




	/**
	 *  绝当
	 *
	 */
	@Scheduled(cron ="0 */1 * * * ?")
	public void end(){
		logger.debug("------------------------end: 使物品绝当定时任务----------------------");
		Date now = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
		String time = sdf.format(now);
		if (StringUtils.equals(time,"00:00")){
			//过期绝当
			UserPawnExample example = new UserPawnExample();
			example.or().andStateEqualTo(2).andPawnEndTimeLessThan(DateUtil.add(new DateTime(DateUtil.getCurrentTime(DateUtil.YYMMDD),DateUtil.YYMMDD),-PaidangConst.BUFFER_DAYS));
			example.or().andStateEqualTo(5).andPawnEndTimeLessThan(DateUtil.add(new DateTime(DateUtil.getCurrentTime(DateUtil.YYMMDD),DateUtil.YYMMDD),-PaidangConst.BUFFER_DAYS));
			List<UserPawn> list = userPawnService.selectByExample(example);
			UserPawn userPawn = new UserPawn();
			userPawn.setState(4);
			UserGoods goods = new UserGoods();
			//更新绝当品归属
			for (UserPawn pawn : list){
				goods.setId(pawn.getGoodsId());
				goods.setBelongId(pawn.getOrgId());
				goods.setBelongType(2);
				userGoodsService.updateByPrimaryKeySelective(goods);
				//绝当写推送消息（机构端）,绝当消息对于每个当品只会有一次
				orgNotifyService.insertByTemplate(pawn.getOrgId(),"5", PaidangMessage.PAWN_DEAD_NOTIFY,pawn.getGoodsName());
				userNotifyService.insertByTemplate(pawn.getUserId(),"9", PaidangMessage.PAWN_DEAD_NOTIFY,pawn.getGoodsName());
			}
			//更新绝当状态
			userPawnService.updateByExampleSelective(userPawn,example);
		}else {
			logger.debug("------------------------end---return-------------------");
			return;
		}
	}


	/**
	 * 典当即将到期
	 *
	 */
	@Scheduled(cron ="0 */1 * * * ?")
	public void endLastZero(){
		logger.debug("------------------------endLastZero----------------------");
		Date now = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
		String time = sdf.format(now);
		if (StringUtils.equals(time,"07:00")){
			//查出最后一天
			//发送用户和机构端通知
			List<UserPawn> list = getUserPawnListByDay(0);
			for (UserPawn userPawn :list){
				String pawnerName = userService.selectByPrimaryKey(userPawn.getUserId()).getName();
				setOrgNotify(userPawn,pawnerName,0);
			}
		}else {
			logger.debug("------------------------endLastZero---return-----------------");
			return;
		}
	}

	@Scheduled(cron ="0 */1 * * * ?")
	public void endLastOne(){
		logger.debug("------------------------endLastOne----------------------");
		Date now = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
		String time = sdf.format(now);
		if (StringUtils.equals(time,"07:01")){
			//查出还有1天到期的
			//发送用户和机构端通知
			List<UserPawn> list = getUserPawnListByDay(1);
			for (UserPawn userPawn :list){
				String pawnerName = userService.selectByPrimaryKey(userPawn.getUserId()).getName();
				setOrgNotify(userPawn,pawnerName,1);
			}
		}else {
			logger.debug("------------------------endLastOne---return------------------");
			return;
		}

	}

	@Scheduled(cron ="0 */1 * * * ?")
	public void endLastTwo(){
		logger.debug("------------------------endLastTwo----------------------");
		Date now = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
		String time = sdf.format(now);
		if (StringUtils.equals(time,"07:02")){
			//查出还有2天到期的
			//发送用户和机构端通知
			List<UserPawn> list = getUserPawnListByDay(2);
			for (UserPawn userPawn :list){
				String pawnerName = userService.selectByPrimaryKey(userPawn.getUserId()).getName();
				setOrgNotify(userPawn,pawnerName,2);
			}
		}else {
			logger.debug("------------------------endLastTwo---return-------------------");
			return;
		}
	}

	@Scheduled(cron ="0 */1 * * * ?")
	public void endLastThree(){
		logger.debug("------------------------endLastThree----------------------");
		Date now = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
		String time = sdf.format(now);
		if (StringUtils.equals(time,"07:03")){
			//查出还有3天到期的
			//发送用户和机构端通知
			List<UserPawn> list = getUserPawnListByDay(3);
			for (UserPawn userPawn :list){
				String pawnerName = userService.selectByPrimaryKey(userPawn.getUserId()).getName();
				setOrgNotify(userPawn,pawnerName,3);
			}
		}else{
			logger.debug("------------------------endLastThree---return------------------");
			return;
		}
	}

	@Scheduled(cron ="0 */1 * * * ?")
	public void endLastFour(){
		logger.debug("------------------------endLastFour----------------------");
		Date now = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
		String time = sdf.format(now);
		if (StringUtils.equals(time,"07:04")){
			//查出还有4天到期的
			//发送用户和机构端通知
			List<UserPawn> list = getUserPawnListByDay(4);
			for (UserPawn userPawn :list){
				String pawnerName = userService.selectByPrimaryKey(userPawn.getUserId()).getName();
				setOrgNotify(userPawn,pawnerName,4);
			}
		}else {
			logger.debug("------------------------endLastFour---return-------------------");
			return;
		}

	}

	@Scheduled(cron ="0 */1 * * * ?")
	public void endLastFive(){
		logger.debug("------------------------endLastFive----------------------");
		Date now = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
		String time = sdf.format(now);
		if (StringUtils.equals(time,"07:05")){
			//查出还有5天到期的
			//发送用户和机构端通知
			List<UserPawn> list = getUserPawnListByDay(5);
			for (UserPawn userPawn :list){
				String pawnerName = userService.selectByPrimaryKey(userPawn.getUserId()).getName();
				setOrgNotify(userPawn,pawnerName,5);
			}
		}else {
			logger.debug("------------------------endLastFive---return-------------------");
			return;
		}

	}

	//典当到期提醒消息
	private void setOrgNotify(UserPawn userPawn,String pawnerName,Integer days){
		logger.debug("=====================执行setOrgNotify(典当到期提醒),目标用户id:"+userPawn.getUserId()+",其当品id:"+userPawn.getGoodsId()+"的物品,还有"+days+"即将典当到期===========================");
		Integer goodsId = userPawn.getGoodsId();
		Goods record = goodsService.selectByPrimaryKey(goodsId);

		if (("0".equals(days))){
			userNotifyService.insertByTemplate(userPawn.getUserId(),"2", PaidangMessage.PAWN_END_WARN_LAST);
		}else {
			userNotifyService.insertByTemplate(userPawn.getUserId(),"2", PaidangMessage.PAWN_END_WARN,days+"");
		}
		//userNotifyService.insertByTemplate(userPawn.getUserId(),"2",PaidangMessage.PAWN_END_NOTIFY,record.getName(),userPawn.getOrgName(),userPawn.getOrgName());

		//-----------短信
		String content = new String("您的"+userPawn.getPawnTicketCode()+"当号，快到期了，快去查看");
		User user = userService.selectByPrimaryKey(userPawn.getUserId());
		smsSendLogService.sendIdentifySms(user.getPhone(),null,
				null, content,11);
	}

	//查询出所有距离典当到期还有last天的记录（但还未到期）
	private List<UserPawn> getUserPawnListByDay(int last){
		Date today = new DateTime(DateUtil.getCurrentTime(DateUtil.YYMMDD),DateUtil.YYMMDD);
		UserPawnExample example = new UserPawnExample();
		example.or().andStateEqualTo(2).andPawnEndTimeEqualTo(DateUtil.add(today,last));
		example.or().andStateEqualTo(5).andPawnEndTimeEqualTo(DateUtil.add(today,last));
		List<UserPawn> list = userPawnService.selectByExample(example);
		logger.debug("=======================执行getUserPawnListByDay(典当即将到期查询方法):到期还有"+last+"天的典当订单(JSON)==================");
		for (UserPawn userPawn:list) {
			logger.debug(JSON.toJSONString(userPawn));
		}
		logger.debug("=========================================================================");
		return list;
	}



	/**
	 * 已经到期,即将绝当
	 *
	 */
	@Scheduled(cron ="0 */1 * * * ?")
	public void ExpireOneDay(){
		logger.debug("------------------------ExpireOneDay----------------------");
		Date now = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
		String time = sdf.format(now);
		if (StringUtils.equals(time,"06:00")){
			List<UserPawn> list = getExpirePawnByDays(1);
			for (UserPawn userPawn :list){
				sendExpireMsg(userPawn,"1");
			}
		}else {
			logger.debug("------------------------ExpireOneDay---return-------------------");
			return;
		}
	}

	@Scheduled(cron ="0 */1 * * * ?")
	public void ExpireTwoDay(){
		logger.debug("------------------------ExpireTwoDay----------------------");
		Date now = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
		String time = sdf.format(now);
		if (StringUtils.equals(time,"06:01")){
			List<UserPawn> list = getExpirePawnByDays(2);
			for (UserPawn userPawn :list){
				sendExpireMsg(userPawn,"2");
			}
		}else {
			logger.debug("------------------------ExpireTwoDay---return-------------------");
			return;
		}
	}

	@Scheduled(cron ="0 */1 * * * ?")
	public void ExpireThreeDay(){
		logger.debug("------------------------ExpireThreeDay----------------------");
		Date now = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
		String time = sdf.format(now);
		if (StringUtils.equals(time,"06:02")){
			List<UserPawn> list = getExpirePawnByDays(3);
			for (UserPawn userPawn :list){
				sendExpireMsg(userPawn,"3");
			}
		}else {
			logger.debug("------------------------ExpireThreeDay---return-------------------");
			return;
		}
	}

	@Scheduled(cron ="0 */1 * * * ?")
	public void ExpireFourDay(){
		logger.debug("------------------------ExpireFourDay----------------------");
		Date now = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
		String time = sdf.format(now);
		if (StringUtils.equals(time,"06:03")){
			List<UserPawn> list = getExpirePawnByDays(4);
			for (UserPawn userPawn :list){
				sendExpireMsg(userPawn,"4");
			}
		}else {
			logger.debug("------------------------ExpireFourDay---return-------------------");
			return;
		}
	}

	//推送机构逾期消息
	private void sendExpireMsg(UserPawn userPawn,String days){
		logger.debug("------------------执行sendExpireMsg(发送已到期即将绝当消息),目标用户id:"+userPawn.getUserId()+",此用户当品id:"+userPawn.getGoodsId()+"的物品,还有"+days+"天绝当------------------------");
		userNotifyService.insertByTemplate(userPawn.getUserId(),"2", PaidangMessage.PWAN_OVER_WARN,days);
		orgNotifyService.insertByTemplate(userPawn.getOrgId(),"2", PaidangMessage.USER_OVERDUE_NOTIFY,userPawn.getUserName(),days);
		//----------发送短信
		String content = new String("您的"+userPawn.getPawnTicketCode()+"当号，已经超过当款日期了，快去查看。");
		User user = userService.selectByPrimaryKey(userPawn.getUserId());
		smsSendLogService.sendIdentifySms(user.getPhone(),null,
				null, content,11);
	}

	//查询已逾期days天的典当(但尚未绝当)
	private List<UserPawn> getExpirePawnByDays(int days){
		Date today = new DateTime(DateUtil.getCurrentTime(DateUtil.YYMMDD),DateUtil.YYMMDD);
		UserPawnExample example = new UserPawnExample();
		example.createCriteria().andStateEqualTo(2).andPawnEndTimeEqualTo(DateUtil.add(today,-days));
		List<UserPawn> list = userPawnService.selectByExample(example);
		logger.debug("-------------------------执行getExpirePawnByDays(已到期即将绝当):还有"+days+"绝当的订单(JSON)-----------------------");
		for (UserPawn userPawn:list) {
			logger.debug(JSON.toJSONString(userPawn));
		}
		logger.debug("--------------------------------------------------------------------------------");
		return list;
	}



	//30分钟释放未付款--释放库存--并改为未付款
	@Scheduled(cron ="0/30 * * * * ?")
	public void release(){

		List<OrderEx> list = orderService.selectRelease();

		if(list.size() > 0){
			//修改成已取消订单
			int updateOrder = orderService.updateOrderList(list);

			//释放商品表库存
			int updateGoods = orderService.updateGoodsList(list);

			//修改寄拍商品状态
			for (OrderEx ex:list){
				if (ex.getGoodsSource()!=null && ex.getGoodsSource()==5){
					UserGoods userGoods=new UserGoods();
					userGoods.setId(ex.getUserGoodsId());
					userGoods.setSellStatus(1);
					userGoodsService.updateByPrimaryKey(userGoods);
				}
			}
		}
	}

	/*
	*
	* 绝当商城竞拍，倒计时结束后定时任务，每分钟运行一次
	*
	*/
	//  现机构处理其绝当品，不再使用用户竞拍形式，所以这段代码暂时注掉，cron.properties的任务也一并被注掉

/*	public void goodsAuctionEnd(){
		//p_goods里绝当竞拍倒计时结束的物品
		GoodsExample goodsExample = new GoodsExample();
		goodsExample.createCriteria().andCreateTimeLessThan(DateUtil.offsetSecond(new Date(),-PaidangConst.JD_GOODS_TIME));
		List<Goods> expiredGoods = goodsService.selectByExample(goodsExample);
		for (Goods element:expiredGoods) {
			if (element.getState() != 0){
				element.setState(0); //使竞拍失效
			}
			//有人竞拍，到竞拍结束后自动取价高者为中标者，并插入订单表
			GoodsAuctionExample example = new GoodsAuctionExample();//查找此物品的竞拍记录
			example.createCriteria().andGoodsIdEqualTo(element.getId());
			example.setOrderByClause("create_time desc");
			List<GoodsAuction> auctions = goodsAuctionService.selectByExample(example);
			if (element.getMaxAutionId() != null && auctions.size() != 0){
				//查询出中标的那次竞拍记录
				GoodsAuction record = goodsAuctionService.selectByPrimaryKey(element.getMaxAutionId());
				if (record == null){
					throw new SystemException("绝当竞拍id是【"+element.getMaxAutionId()+"】的记录数据异常");
				}
				Integer userId = record.getUserId();//中标用户
				if (userId == null){
					throw new SystemException("绝当竞拍id是【"+element.getMaxAutionId()+"】的记录数据异常");
				}
				OrderExample orderExample = new OrderExample();
				orderExample.createCriteria().andGoodsIdEqualTo(element.getId()).andUserIdEqualTo(element.getUserId()).andOrgIdEqualTo(element.getOrgId());
				int cnt = orderService.countByExample(orderExample);
				if (cnt == 0){
					Order order = new Order();
					//插入订单号
					order.setCode(UUID.randomUUID().toString().replaceAll("-", ""));
					order.setUserId(element.getUserId());
					order.setGoodsId(element.getId());//这里的goodsId是指p_goods表主键
					order.setGoodsName(element.getName());
					order.setGoodsImg(element.getImg());
					order.setGoodsSource(2);//1平台2机构3服务商
					order.setOrgId(element.getOrgId());
					order.setGoodsCost(element.getCost());// 成本 set起拍价（如果是绝当品来拍卖，起拍价就是鉴定价）
					order.setGoodsPrice(element.getMaxAuction());//价格 set此物品最终中标价
					order.setPrice(element.getMaxAuction());
					order.setState(1);//中标后等待用户付款 订单状态 -1已取消1待付款2已付款3已发货4确认收货5已评价
					order.setIsBalance(0);// 未结算
					orderService.insert(order);
					//更新数量
//					element.setSoldOut(element.getSoldOut()+1);
//					if (element.getTotal() > 0 ){
//						element.setTotal(element.getTotal()-1);
//					}
//					goodsService.updateByPrimaryKey(element);
				}
			}
		}
	}*/



}
