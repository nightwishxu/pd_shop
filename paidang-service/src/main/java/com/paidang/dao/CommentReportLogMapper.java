package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.CommentReportLog;
import com.paidang.dao.model.CommentReportLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentReportLogMapper {
	int countByExample(CommentReportLogExample example);

	int deleteByExample(CommentReportLogExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(CommentReportLog record);

	int insertSelective(CommentReportLog record);

	List<CommentReportLog> selectByExample(CommentReportLogExample example);

	CommentReportLog selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") CommentReportLog record,@Param("example") CommentReportLogExample example);

	int updateByExample(@Param("record") CommentReportLog record,@Param("example") CommentReportLogExample example);

	int updateByPrimaryKeySelective(CommentReportLog record);

	int updateByPrimaryKey(CommentReportLog record);

	List<CommentReportLog> selectByWrapper(EntityWrapper<CommentReportLog> wrapper);

}
