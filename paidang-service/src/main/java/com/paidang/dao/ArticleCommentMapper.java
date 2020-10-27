package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.ArticleComment;
import com.paidang.dao.model.ArticleCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCommentMapper {
	int countByExample(ArticleCommentExample example);

	int deleteByExample(ArticleCommentExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(ArticleComment record);

	int insertSelective(ArticleComment record);

	List<ArticleComment> selectByExample(ArticleCommentExample example);

	ArticleComment selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") ArticleComment record,@Param("example") ArticleCommentExample example);

	int updateByExample(@Param("record") ArticleComment record,@Param("example") ArticleCommentExample example);

	int updateByPrimaryKeySelective(ArticleComment record);

	int updateByPrimaryKey(ArticleComment record);

	List<ArticleComment> selectByWrapper(EntityWrapper<ArticleComment> wrapper);

}
