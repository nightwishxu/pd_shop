package com.api.action;

import com.api.MEnumError;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.BaseUtils;
import com.item.dao.model.User;
import com.item.dao.model.UserNotify;
import com.item.service.OrgAmountLogService;
import com.item.service.UserNotifyService;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.OrderEx;
import com.paidang.daoEx.model.OrgAmountLogEx;
import com.paidang.domain.qo.OrderQo;
import com.paidang.service.ExpressService;
import com.paidang.service.GoodsService;
import com.paidang.service.OrderService;
import com.ruoyi.common.core.domain.Ret;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author: xww
 * @create: 2020-11-08 14:38
 * @description: 订单管理
 * @modified By:
 * @version:
 **/
@RestController
@RequestMapping(value="/api/order", produces = {"application/json;charset=UTF-8"})
@Api(tags = "订单相关")
public class ApiOrderController extends ApiBaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserNotifyService userNotifyService;

    @Autowired
    private ExpressService expressService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrgAmountLogService orgAmountLogService;

    //订单状态-1已取消1待付款2已付款3已发货4确认收货5已评价
    //退款状态 0未退款 1申请退款 2同意退款 3提交单号 4已退款 5拒绝退款
    @ApiOperation(value = "订单列表",notes="订单列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<OrderEx> orderList(MobileInfo mobileInfo,
                                 @ApiParam(value = "订单 0全部 1待付款2待发货3待收货 4售后",required = true)Integer state){

        startPage();
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        OrderQo qo = new OrderQo();
        qo.setOrgId(orgId);
        List<Integer> states= new ArrayList<>();
        List<Integer> refStates= new ArrayList<>();
        if (state==0){

        }else if (state==3){
            //3已发货4确认收货5已评价
            states.add(3);
            states.add(4);
            states.add(5);
            refStates.add(0);
        }else if (state==4){
            refStates.add(1);
            refStates.add(2);
            refStates.add(3);
            refStates.add(4);
            refStates.add(5);

        }else {
            states.add(state);
        }
        qo.setStates(states);
        qo.setRefStates(refStates);
        List<OrderEx> list = orderService.findList(qo);
        return list;
    }


    //新品商场订单同意退款 --发送退款信息  or 拒绝退款  --发送拒绝信息
    @RequestMapping("/sendRefData")
    @ResponseBody
    public String sendRefData(Order data){
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
        return null;
    }


    /**
     * 驳回填写原因，同意就直接用户那边就有填写邮寄信息的入口，商家确认收货后款项就退到用户的电子钱包中
     */

    @ApiOperation(value = "售后-驳回,同意",notes="订单列表")
    @RequestMapping(value = "/return", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public void refuseRefund(MobileInfo mobileInfo,
                             @ApiParam(value = "退款状态2同意 5不同意") int refState,
                             @ApiParam(value = "拒绝退款理由",required = true)String  refundNotVerifyReason,
                             @ApiParam(value = "订单id",required = true)Integer  id
    ){
        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
        Order tmp = orderService.selectByPrimaryKey(id);
        if (!Objects.equals(orgId,tmp.getOrgId())){
            throw new ApiException("机构异常");
        }
        Order order = new Order();
        order.setId(id);
        if(tmp.getRefState() != 1){//已付款待发货
            throw new ApiException(-1,"此订单状态不是申请退款状态，不能执行此操作!");
        }
        if(refState == 5 && StringUtils.isBlank(refundNotVerifyReason)){
            throw new ApiException(-1,"不同意退款需要填写不同意理由!");
        }
        order.setRefState(refState);
        order.setRefundNotVerifyReason(refundNotVerifyReason);
        order.setModifyTime(new Date());
        orderService.updateByPrimaryKeySelective(order);

        if (refState==5){
            UserNotify userNotify = new UserNotify();
            userNotify.setIsRead(0);
            userNotify.setType(0); //不是系统消息
            userNotify.setUserId(order.getUserId());
            //拒绝退款
            userNotify.setRedirectType(10);  // 交易消息
            userNotify.setTitle("退款拒绝");
            userNotify.setContent(order.getGoodsName() + "退款拒绝"+"原因:"+order.getRefundNotVerifyReason());
            userNotify.setRedirectContent(order.getGoodsName() + "退款拒绝"+"原因:"+order.getRefundNotVerifyReason());
            userNotifyService.insert(userNotify);
        }else if (refState==2){
            //同意退款
            Order order1 = orderService.selectByPrimaryKey(id);
            if (order1.getGoodsSource()!=5 &&  order1.getState()>=4){
                OrgAmountLogExample example = new OrgAmountLogExample();
                example.createCriteria().andFidEqualTo(id).andOrgIdEqualTo(orgId).andItemEqualTo("1");
                example.setOrderByClause("id desc");
                List<OrgAmountLog> orgAmountLogs = orgAmountLogService.selectByExample(example);
                if (CollectionUtils.isNotEmpty(orgAmountLogs)){
                    OrgAmountLog log = orgAmountLogs.get(0);
                    orgAmountLogService.saveLog(orgId,log.getAmount(),"4","订单退款："+order.getCode(),id,null);
                }
            }
        }


    }

//    //    //退款状态 0未退款 1申请退款 2同意退款 3提交单号 4已退款 5拒绝退款
//    @ApiOperation(value = "售后-同意",notes="订单列表")
//    @RequestMapping(value = "/agreeRefund", method = RequestMethod.POST)
//    @ApiMethod(isLogin = true)
//    public void agreeRefund(MobileInfo mobileInfo,
//                            @ApiParam(value = "订单id",required = true)Integer  id
//    ){
//        Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
//        Order tmp = orderService.selectByPrimaryKey(id);
//        if (!Objects.equals(orgId,tmp.getOrgId())){
//            throw new ApiException("机构异常");
//        }
//        Order order = new Order();
//        order.setId(id);
//        order.setModifyTime(new Date());
//        order.setRefState(2);
//
//    }




    @GetMapping("/order/orderList")
    @ApiMethod(isLogin = true)
    @ApiOperation(value = "根据(状态)获取订单列表")
    public Object getOrderList(@ApiParam(value = "-1已取消1待付款2已付款3已发货4确认收货5已评价") String state,
                               MobileInfo mobileInfo,
                               @ApiParam(value = "分页(不传则不分页)") Integer pageSize,
                               @ApiParam(value = "分页(不传则不分页)") String pageNum,
                               @ApiParam(value = "商品名称")String goodsName){
        int userId = mobileInfo.getUserId();
        if (BaseUtils.isAnyBlank(pageNum,pageSize)){
            startPage();
        }


        List<OrderEx> orderExes = orderService.getOrderByState(userId,goodsName,state);
        if(BaseUtils.isAnyBlank(pageNum,pageSize)){
            return orderExes;
        }else{
            return orderExes;
        }
    }

    @GetMapping("/order/afterSales")
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
    public void writeExperterInfo(@ApiParam(value = "物流公司") String shipFirm,
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
            example.createCriteria().andFidEqualTo(order.getId());
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
            expressService.insert(express);
        }
    }


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


}
