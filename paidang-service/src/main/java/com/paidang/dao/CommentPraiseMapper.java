package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.CommentPraise;
import com.paidang.dao.model.CommentPraiseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentPraiseMapper {
	int countByExample(CommentPraiseExample example);

	int deleteByExample(CommentPraiseExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(CommentPraise record);

	int insertSelective(CommentPraise record);

	List<CommentPraise> selectByExample(CommentPraiseExample example);

	CommentPraise selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") CommentPraise record,@Param("example") CommentPraiseExample example);

	int updateByExample(@Param("record") CommentPraise record,@Param("example") CommentPraiseExample example);

	int updateByPrimaryKeySelective(CommentPraise record);

	int updateByPrimaryKey(CommentPraise record);

	List<CommentPraise> selectByWrapper(EntityWrapper<CommentPraise> wrapper);

}
