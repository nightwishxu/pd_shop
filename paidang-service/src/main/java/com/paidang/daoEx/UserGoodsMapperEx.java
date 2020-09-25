package com.paidang.daoEx;

import com.paidang.dao.model.UserGoods;
import com.paidang.daoEx.model.UserGoodsEx;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map; /**
@author sun
*/
public interface UserGoodsMapperEx {
    int updateResAuthPrice(Map<String, Object> map);

    List<UserGoodsEx> selectList(Map<String, Object> map);

    List<UserGoodsEx> checkDetail(Map<String, Object> map);

    UserGoodsEx selectGotoPawn(Integer id);

    List<UserGoodsEx> selectByAppList(Map<String, Object> map);

    List<UserGoodsEx> selectMyGoods(Map<String, Object> map);

    //查看视频监控
    List<UserGoodsEx> selectMyVideo(Map<String, Object> map);

    //机构取回宝贝
    List<UserGoodsEx> selectGoodsByOrgList(Map<String, Object> map);

    //未典当列表
    List<UserGoodsEx> selectNoPawnList(Map<String, Object> map);

    //已取消列表
    List<UserGoodsEx> selectCancelPawnList(Map<String, Object> map);

    List<UserGoodsEx> selectTradeRecordList(Map<String, Object> map);

    List<UserGoodsEx> selectExpressList(Map<String, Object> map);

    /**
     *
     * @param id
     * @param num
     * @param type 0更新评论数1更新收藏数2更新点赞数
     * @return
     */
    Integer updateUserGoodsCount(@Param("id")Integer id, @Param("num")Integer num, @Param("type")Integer type);

    List<UserGoodsEx> findCollectList(UserGoodsEx ex);

    List<UserGoodsEx> findList(UserGoodsEx ex);

    UserGoodsEx getById(Integer id);


}
