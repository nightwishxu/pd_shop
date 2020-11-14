package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.GoodsAttribute;
import com.paidang.dao.model.GoodsAttributeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsAttributeMapper {
	int countByExample(GoodsAttributeExample example);

	int deleteByExample(GoodsAttributeExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(GoodsAttribute record);

	int insertSelective(GoodsAttribute record);

	List<GoodsAttribute> selectByExample(GoodsAttributeExample example);

	GoodsAttribute selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") GoodsAttribute record,@Param("example") GoodsAttributeExample example);

	int updateByExample(@Param("record") GoodsAttribute record,@Param("example") GoodsAttributeExample example);

	int updateByPrimaryKeySelective(GoodsAttribute record);

	int updateByPrimaryKey(GoodsAttribute record);

	List<GoodsAttribute> selectByWrapper(EntityWrapper<GoodsAttribute> wrapper);

}
