package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.GoodsAuctionOnlineLogMapper;
import com.paidang.dao.model.GoodsAuctionOnlineLog;
import com.paidang.dao.model.GoodsAuctionOnlineLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsAuctionOnlineLogService {
	@Autowired
	private GoodsAuctionOnlineLogMapper goodsAuctionOnlineLogMapper;

	public int countByExample(GoodsAuctionOnlineLogExample example) {
		return this.goodsAuctionOnlineLogMapper.countByExample(example);
	}

	public GoodsAuctionOnlineLog selectByPrimaryKey(Integer id) {
		return this.goodsAuctionOnlineLogMapper.selectByPrimaryKey(id);
	}

	public List<GoodsAuctionOnlineLog> selectByExample(GoodsAuctionOnlineLogExample example) {
		return this.goodsAuctionOnlineLogMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.goodsAuctionOnlineLogMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(GoodsAuctionOnlineLog record) {
		return this.goodsAuctionOnlineLogMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(GoodsAuctionOnlineLog record) {
		return this.goodsAuctionOnlineLogMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(GoodsAuctionOnlineLogExample example) {
		return this.goodsAuctionOnlineLogMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(GoodsAuctionOnlineLog record, GoodsAuctionOnlineLogExample example) {
		return this.goodsAuctionOnlineLogMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(GoodsAuctionOnlineLog record, GoodsAuctionOnlineLogExample example) {
		return this.goodsAuctionOnlineLogMapper.updateByExample(record, example);
	}

	public int insert(GoodsAuctionOnlineLog record) {
		return this.goodsAuctionOnlineLogMapper.insert(record);
	}

	public int insertSelective(GoodsAuctionOnlineLog record) {
		return this.goodsAuctionOnlineLogMapper.insertSelective(record);
	}

	public List<GoodsAuctionOnlineLog> selectByWrapper(EntityWrapper<GoodsAuctionOnlineLog> wrapper) {
		return this.goodsAuctionOnlineLogMapper.selectByWrapper(wrapper);
	}

}
