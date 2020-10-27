package com.paidang.daoEx;

import com.item.daoEx.model.UserEx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
@author sun
*/
public interface UserFollowMapperEx {

    /**
     * 获取用户关注数
     * @param userId
     * @return
     */
    Integer getUserFollowCount(Integer userId);

    /**
     * 我的关注
     * @param userId
     * @return
     */
    List<UserEx> getUserFollow(Integer userId);

    /**
     * 获取用户粉丝数
     * @param userId
     * @return
     */
    Integer getUserFansCount(Integer userId);

    Integer getIsFollow(@Param("userId") Integer userId, @Param("followId") Integer followId);


    List<Integer> getUserFlowIds(@Param("userId")Integer userId);
}
