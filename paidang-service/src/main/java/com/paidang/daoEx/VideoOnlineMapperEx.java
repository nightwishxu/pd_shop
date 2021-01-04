package com.paidang.daoEx;

import com.paidang.daoEx.model.VideoOnlineEx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
@author sun
*/
public interface VideoOnlineMapperEx {
    List<VideoOnlineEx> selectByComment(@Param("userId")Integer userId,@Param("state")Integer state,@Param("name")String name);

    int updateCount(@Param("id")Integer id,@Param("num")Integer num,@Param("type")Integer type);
}
