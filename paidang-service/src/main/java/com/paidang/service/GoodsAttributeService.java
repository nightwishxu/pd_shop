package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.GoodsAttributeMapper;
import com.paidang.dao.model.GoodsAttribute;
import com.paidang.dao.model.GoodsAttributeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsAttributeService {
	@Autowired
	private GoodsAttributeMapper goodsAttributeMapper;

	public int countByExample(GoodsAttributeExample example) {
		return this.goodsAttributeMapper.countByExample(example);
	}

	public GoodsAttribute selectByPrimaryKey(Integer id) {
		return this.goodsAttributeMapper.selectByPrimaryKey(id);
	}

	public List<GoodsAttribute> selectByExample(GoodsAttributeExample example) {
		return this.goodsAttributeMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.goodsAttributeMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(GoodsAttribute record) {
		return this.goodsAttributeMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(GoodsAttribute record) {
		return this.goodsAttributeMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(GoodsAttributeExample example) {
		return this.goodsAttributeMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(GoodsAttribute record, GoodsAttributeExample example) {
		return this.goodsAttributeMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(GoodsAttribute record, GoodsAttributeExample example) {
		return this.goodsAttributeMapper.updateByExample(record, example);
	}

	public int insert(GoodsAttribute record) {
		return this.goodsAttributeMapper.insert(record);
	}

	public int insertSelective(GoodsAttribute record) {
		return this.goodsAttributeMapper.insertSelective(record);
	}

	public List<GoodsAttribute> selectByWrapper(EntityWrapper<GoodsAttribute> wrapper) {
		return this.goodsAttributeMapper.selectByWrapper(wrapper);
	}


	public  List<GoodsAttribute> getByGoodsType(Integer cateCode){
		GoodsAttributeExample example = new GoodsAttributeExample();
		example.createCriteria().andBelongToEqualTo(cateCode);
		return goodsAttributeMapper.selectByExample(example);
	}

}
