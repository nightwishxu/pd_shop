package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.GoodsAuctionOnlineLog;
import com.paidang.dao.model.GoodsAuctionOnlineLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsAuctionOnlineLogMapper {
	int countByExample(GoodsAuctionOnlineLogExample example);

	int deleteByExample(GoodsAuctionOnlineLogExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(GoodsAuctionOnlineLog record);

	int insertSelective(GoodsAuctionOnlineLog record);

	List<GoodsAuctionOnlineLog> selectByExample(GoodsAuctionOnlineLogExample example);

	GoodsAuctionOnlineLog selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") GoodsAuctionOnlineLog record, @Param("example") GoodsAuctionOnlineLogExample example);

	int updateByExample(@Param("record") GoodsAuctionOnlineLog record, @Param("example") GoodsAuctionOnlineLogExample example);

	int updateByPrimaryKeySelective(GoodsAuctionOnlineLog record);

	int updateByPrimaryKey(GoodsAuctionOnlineLog record);

	List<GoodsAuctionOnlineLog> selectByWrapper(EntityWrapper<GoodsAuctionOnlineLog> wrapper);

}
