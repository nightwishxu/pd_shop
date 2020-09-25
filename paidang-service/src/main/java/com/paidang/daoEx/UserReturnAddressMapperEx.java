package com.paidang.daoEx;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
@author sun
*/
public interface UserReturnAddressMapperEx {

    @Update("update p_user_return_address set is_default=0 where user_id=#{userId}")
    int updateNotDefault(@Param("userId") int userId);

    @Update("update p_user_return_address set is_default=1 where id=#{addressId}")
    int updateDefault(@Param("addressId") int addressId);
}
