package com.paidang.dao;

import java.util.List;
import com.base.mybatis.plus.EntityWrapper;
import org.apache.ibatis.annotations.Param;

import com.paidang.dao.model.Goods;
import com.paidang.dao.model.GoodsExample;

public interface GoodsMapper {
	int countByExample(GoodsExample example);

	int deleteByExample(GoodsExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Goods record);

	int insertSelective(Goods record);

	List<Goods> selectByExample(GoodsExample example);

	List<Goods> selectByExampleWithBLOBs(GoodsExample example);

	Goods selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Goods record,@Param("example") GoodsExample example);

	int updateByExampleWithBLOBs(@Param("record") Goods record, @Param("example") GoodsExample example);

	int updateByExample(@Param("record") Goods record,@Param("example") GoodsExample example);

	int updateByPrimaryKeySelective(Goods record);

	int updateByPrimaryKeyWithBLOBs(Goods record);

	int updateByPrimaryKey(Goods record);

	List<Goods> selectByWrapper(EntityWrapper<Goods> wrapper);

	List<Goods> selectByWrapperWithBLOBs(EntityWrapper<Goods> wrapper);

}
