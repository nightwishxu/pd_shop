package com.paidang.daoEx;

import com.base.entity.QueryParams;
import com.paidang.dao.model.Order;
import com.paidang.daoEx.model.OrderEx;
import com.paidang.domain.qo.OrderQo;
import com.paidang.domain.vo.OrderCollectVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
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

    List<OrderCollectVo> selectMyStoreOrderByState(Map<String, Object> map);

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

    List<OrderEx> findList(OrderQo qo);

    List<OrderEx> findSellGoodsOrder(OrderQo qo);

    Integer getCount(OrderQo qo);

    /**
     *
     * @param userId
     * @param queryType 1待打款 2 待发货 3 待收货 4 总金额
     * @return
     */
    BigDecimal getTotalOrderPrice(@Param("userId") Integer userId,@Param("queryType") Integer queryType);

    List<OrderCollectVo> getOrderCountByState(@Param("userId") Integer userId);
}
