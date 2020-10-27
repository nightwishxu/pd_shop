package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.ArticleCommentReply;
import com.paidang.dao.model.ArticleCommentReplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCommentReplyMapper {
	int countByExample(ArticleCommentReplyExample example);

	int deleteByExample(ArticleCommentReplyExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(ArticleCommentReply record);

	int insertSelective(ArticleCommentReply record);

	List<ArticleCommentReply> selectByExample(ArticleCommentReplyExample example);

	ArticleCommentReply selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") ArticleCommentReply record,@Param("example") ArticleCommentReplyExample example);

	int updateByExample(@Param("record") ArticleCommentReply record,@Param("example") ArticleCommentReplyExample example);

	int updateByPrimaryKeySelective(ArticleCommentReply record);

	int updateByPrimaryKey(ArticleCommentReply record);

	List<ArticleCommentReply> selectByWrapper(EntityWrapper<ArticleCommentReply> wrapper);

}
