package com.paidang.daoEx;

import com.paidang.daoEx.model.PrivateMessageEx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
@author sun
*/
public interface PrivateMessageMapperEx {

    List<PrivateMessageEx> myPrivateMessageList(@Param("userId")Integer userId);


    List<PrivateMessageEx> myPrivateMessageInfo(@Param("userId")Integer userId, @Param("friendId")Integer friendId);

    int deleteAll(@Param("userId")Integer userId,@Param("friendId")Integer friendId);

    int readMsg(@Param("userId")Integer userId,@Param("friendId")Integer friendId);

    int countUnreadMsg(@Param("userId")Integer userId,@Param("friendId")Integer friendId);

    int deleteSingle(@Param("id")Integer id);
}
