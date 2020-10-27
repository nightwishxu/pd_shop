package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.ArticleCollectPraise;
import com.paidang.dao.model.ArticleCollectPraiseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCollectPraiseMapper {
	int countByExample(ArticleCollectPraiseExample example);

	int deleteByExample(ArticleCollectPraiseExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(ArticleCollectPraise record);

	int insertSelective(ArticleCollectPraise record);

	List<ArticleCollectPraise> selectByExample(ArticleCollectPraiseExample example);

	ArticleCollectPraise selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") ArticleCollectPraise record,@Param("example") ArticleCollectPraiseExample example);

	int updateByExample(@Param("record") ArticleCollectPraise record,@Param("example") ArticleCollectPraiseExample example);

	int updateByPrimaryKeySelective(ArticleCollectPraise record);

	int updateByPrimaryKey(ArticleCollectPraise record);

	List<ArticleCollectPraise> selectByWrapper(EntityWrapper<ArticleCollectPraise> wrapper);

}
