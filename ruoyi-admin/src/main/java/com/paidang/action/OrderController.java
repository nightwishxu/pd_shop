package com.paidang.action;

import com.base.action.CoreController;
import com.base.entity.QueryParams;

import com.item.dao.model.UserNotify;
import com.item.service.UserNotifyService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.OrderEx;
import com.paidang.service.ExpressService;
import com.paidang.service.OrderService;
import com.paidang.service.PawnOrgService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import com.util.MExpressAddress;
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
@RequestMapping("order")
@Controller
public class OrderController extends CoreController{

    @Autowired
    private OrderService orderService;
	@Autowired
	private PawnOrgService pawnOrgService;
	@Autowired
	private ExpressService expressService;
	@Autowired
	private UserNotifyService userNotifyService;
    
    @RequestMapping("/all")
	@ResponseBody
    public Ret list(){
    	OrderExample example = new OrderExample();
    	
    	List<Order> list = orderService.selectByExample(example);
      	return ok(list);
    }

//	@RequestMapping("/orderList")
//	@ResponseBody
//	public Ret serviceList(Integer page, Integer rows,String goodsName,Integer goodsSource){
//		QueryParams.Builder builder = QueryParams.newBuilder();
//		Integer id = UserUtils.getDefaultPrincipal().getId();
//		builder.put("orgId",0);
//		builder.put("goodsSource",goodsSource);
//		builder.put("goodsName",goodsName);
//		PaginationSupport.byPage(page, rows);
//		List<OrderEx> list = orderService.selectOrderList(builder);
//		return page(list);
//	}

	@RequestMapping("/orderAdminList")
	@ResponseBody
	public TableDataInfo orderAdminList(Date createTimeStart, Date createTimeEnd, String goodsName,
										Integer goodsSource, String orderCode, Integer state, Integer payType){
		startPage();
		Map<String, Object> map = new HashMap<>();
		map.put("goodsSource",goodsSource);  //goodsSource =1 --平台  =2  --宝祥  3淘宝贝
		map.put("goodsName",goodsName);
		map.put("orderCode",orderCode);
		map.put("payType",payType);
		map.put("createTimeStart",createTimeStart);
		map.put("createTimeEnd",createTimeEnd);
		if(null != state){
			map.put("state",state);
		}
		List<OrderEx> list = orderService.selectOrderAdminList(map);
		return page(list);
	}


    
    @RequestMapping("/save")
	@ResponseBody
    public Ret save(Order order,Integer goodsSource){
		order.setShipFirm(MExpressAddress.xfAddress);
    	if (order.getId() == null){
    		order.setIsBalance(0);
    		order.setUserId(0);
    		order.setGoodsSource(goodsSource);
    		order.setOrgId(0);
    		order.setIsBalance(0);
    		order.setState(1);
			order.setRefState(0);
    		orderService.insert(order);
    	}else{
    		orderService.updateByPrimaryKeySelective(order);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	OrderEx order = orderService.selectByKey(id);
       	return ok(order);
    }
    
    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		orderService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }
	@RequestMapping("/updateState")
	@ResponseBody
	public Ret updateState(Order order){
    		order.setState(3);
		order.setShipFirm(MExpressAddress.xfAddress);
		order.setShipCode(order.getShipCode());
			//orderService.updateState(order);
		orderService.updateByPrimaryKeySelective(order);
			//？

		Order c = orderService.selectByPrimaryKey(order.getId());
		PawnOrg org = pawnOrgService.selectByPrimaryKey(c.getOrgId());
		if (org==null){
			return msg(-1, "机构信息异常！");
		}
		synchronized (order.getId()) {
			ExpressExample example = new ExpressExample();
			example.createCriteria().andFidEqualTo(order.getId());
			List<Express> list = expressService.selectByExample(example);
			if (list != null && list.size() > 0) {
				return msg(-1, "该订单已有物流信息！");
			}
			Express express = new Express();
			express.setSource(2);
			express.setSourceId(c.getOrgId());
			express.setFid(c.getId());
			//express.setType(4);
			express.setType(3);
			express.setExpressState(0);
			express.setExpressName(MExpressAddress.xfAddress);
			express.setExpressCode(c.getShipCode());
			express.setPostName(org.getName());
			express.setPostPhone(org.getPhone());
			express.setReceiveName(c.getShipUser());
			express.setReceviceAddress(c.getShipUser());
			express.setReceivePhone(c.getShipPhone());
			expressService.insert(express);
		}
		return ok();
	}

	//机构端后台绝当订单
//	@RequestMapping("/orgOrderList")
//	@ResponseBody
//	public Ret orgOrderList(Integer page, Integer rows,String goodsName,Integer goodsSource){
//		QueryParams.Builder builder = QueryParams.newBuilder();
//		Integer id = UserUtils.getDefaultPrincipal().getId();
//		builder.put("orgId",id);
//		builder.put("goodsSource",goodsSource);
//		builder.put("goodsName",goodsName);
//		PaginationSupport.byPage(page, rows);
//		List<OrderEx> list = orderService.getOrgOrderList(builder);
//		return page(list);
//	}

	//新品商场订单退款
	/*@RequestMapping("/refund")
	@ResponseBody
	public Ret refund(Order data){
		Order order = orderService.selectByPrimaryKey(data.getId());
		order.setState(data.getState());
		orderService.updateByPrimaryKeySelective(order);

		//发送消息到客户端
		UserNotify userNotify = new UserNotify();
		userNotify.setIsRead(0);
		userNotify.setType(0); //不是系统消息
		userNotify.setUserId(order.getUserId());
		if(data.getState() == 8){
			//退款成功
			userNotify.setRedirectType(5);  //邮寄通知
			userNotify.setTitle(order.getGoodsName() + "退款审核成功");
			userNotify.setContent(order.getGoodsName() + "退款审核成功");
			userNotify.setRedirectContent("退款审核成功请邮寄至"+data.getBackAddress()+"收件人"+data.getBackUser()+"联系电话"+data.getBackPhone());
		}else if(data.getState() == 9){
			//拒绝退款
			userNotify.setRedirectType(10);  // 交易消息
			userNotify.setTitle("退款拒绝");
			userNotify.setContent(order.getGoodsName() + "退款拒绝"+"原因:"+data.getRefundNotVerifyReason());
			userNotify.setRedirectContent(order.getGoodsName() + "退款拒绝"+"原因:"+data.getRefundNotVerifyReason());
		}

		userNotifyService.insert(userNotify);

		return ok();
	}*/

	//新品商场订单同意退款 --发送退款信息  or 拒绝退款  --发送拒绝信息
	@RequestMapping("/sendRefData")
	@ResponseBody
	public Ret sendRefData(Order data){
		Order order = orderService.selectByPrimaryKey(data.getId());
		order.setRefState(data.getRefState());
		order.setBackAddress(data.getBackAddress());
		order.setBackUser(data.getBackUser());
		order.setBackPhone(data.getBackPhone());
		if(data.getRefState() == 5){
			order.setRefundNotVerifyReason(data.getRefundNotVerifyReason());
		}
		orderService.updateByPrimaryKeySelective(order);
		//发送消息到客户端

		UserNotify userNotify = new UserNotify();
		userNotify.setIsRead(0);
		userNotify.setType(0); //不是系统消息
		userNotify.setUserId(order.getUserId());

		if(data.getRefState() == 5){
			//拒绝退款
			userNotify.setRedirectType(10);  // 交易消息
			userNotify.setTitle("退款拒绝");
			userNotify.setContent(order.getGoodsName() + "退款拒绝"+"原因:"+data.getRefundNotVerifyReason());
			userNotify.setRedirectContent(order.getGoodsName() + "退款拒绝"+"原因:"+data.getRefundNotVerifyReason());
		}else if(data.getRefState() == 2){
			//同意退款  --发送回寄信息
			userNotify.setRedirectType(5);  //邮寄通知
			userNotify.setTitle(order.getGoodsName() + "退款审核成功");
			userNotify.setContent("退款审核成功请邮寄至"+data.getBackAddress()+"收件人"+data.getBackUser()+"联系电话"+data.getBackPhone());
			userNotify.setRedirectContent("退款审核成功请邮寄至"+data.getBackAddress()+"收件人"+data.getBackUser()+"联系电话"+data.getBackPhone());
		}
		userNotifyService.insert(userNotify);
		return ok();
	}
}