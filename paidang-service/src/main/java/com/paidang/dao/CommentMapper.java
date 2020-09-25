package com.paidang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paidang.dao.model.Comment;
import com.paidang.dao.model.CommentExample;

public interface CommentMapper {
	int countByExample(CommentExample example);

	int deleteByExample(CommentExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Comment record);

	int insertSelective(Comment record);

	List<Comment> selectByExample(CommentExample example);

	List<Comment> selectByExampleWithBLOBs(CommentExample example);

	Comment selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Comment record,@Param("example") CommentExample example);

	int updateByExampleWithBLOBs(@Param("record") Comment record, @Param("example") CommentExample example);

	int updateByExample(@Param("record") Comment record,@Param("example") CommentExample example);

	int updateByPrimaryKeySelective(Comment record);

	int updateByPrimaryKeyWithBLOBs(Comment record);

	int updateByPrimaryKey(Comment record);

}
