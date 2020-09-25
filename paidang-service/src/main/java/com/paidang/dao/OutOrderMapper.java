package com.paidang.dao;

import java.util.List;
import com.base.mybatis.plus.EntityWrapper;
import org.apache.ibatis.annotations.Param;

import com.paidang.dao.model.OutOrder;
import com.paidang.dao.model.OutOrderExample;

public interface OutOrderMapper {
	int countByExample(OutOrderExample example);

	int deleteByExample(OutOrderExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(OutOrder record);

	int insertSelective(OutOrder record);

	List<OutOrder> selectByExample(OutOrderExample example);

	OutOrder selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") OutOrder record,@Param("example") OutOrderExample example);

	int updateByExample(@Param("record") OutOrder record,@Param("example") OutOrderExample example);

	int updateByPrimaryKeySelective(OutOrder record);

	int updateByPrimaryKey(OutOrder record);

	List<OutOrder> selectByWrapper(EntityWrapper<OutOrder> wrapper);

}
