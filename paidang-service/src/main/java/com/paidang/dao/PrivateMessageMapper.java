package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.PrivateMessage;
import com.paidang.dao.model.PrivateMessageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrivateMessageMapper {
	int countByExample(PrivateMessageExample example);

	int deleteByExample(PrivateMessageExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(PrivateMessage record);

	int insertSelective(PrivateMessage record);

	List<PrivateMessage> selectByExample(PrivateMessageExample example);

	PrivateMessage selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") PrivateMessage record,@Param("example") PrivateMessageExample example);

	int updateByExample(@Param("record") PrivateMessage record,@Param("example") PrivateMessageExample example);

	int updateByPrimaryKeySelective(PrivateMessage record);

	int updateByPrimaryKey(PrivateMessage record);

	List<PrivateMessage> selectByWrapper(EntityWrapper<PrivateMessage> wrapper);

}
