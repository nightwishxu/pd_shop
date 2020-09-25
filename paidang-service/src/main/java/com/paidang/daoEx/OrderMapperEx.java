package com.paidang.daoEx;

import com.base.entity.QueryParams;
import com.paidang.dao.model.Order;
import com.paidang.daoEx.model.OrderEx;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
@author sun
*/
public interface OrderMapperEx {

    public List<OrderEx> selectOrderList(QueryParams.Builder builder);

    //机构端后台绝当订单
    List<OrderEx> getOrgOrderList(QueryParams.Builder builder);

    public int updateState(Order order);

    OrderEx selectByKey(Integer id);

    //查询商场订单
    List<OrderEx> selectMyStoreOrderList(Map<String, Object> map);

    //查询超过24小时未付款订单
    List<OrderEx> selectByTask(Map<String, Object> map);

    List<OrderEx> selectOrderAdminList(Map<String, Object> map);

    //查询超过30分钟未付款
    List<OrderEx> selectRelease();

    int updateOrderList(List<OrderEx> list);

    int updateGoodsList(List<OrderEx> list);

    List<OrderEx> getBusinessOrder(@Param("goodsName") String goodsName,
                                   @Param("orderCode") String orderCode,
                                   @Param("state") String state,
                                   @Param("ref_state") String ref_state);
    List<OrderEx> getOrderByState(@Param("userId") int userId,
                                  @Param("goodsName") String goodsName,
                                  @Param("state") String state);
    List<OrderEx> getAfterSalesOrder(@Param("userId") String userId,
                                   @Param("goodsName") String goodsName);
}
