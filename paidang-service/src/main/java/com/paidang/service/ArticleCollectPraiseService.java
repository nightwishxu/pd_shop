package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.ArticleCollectPraiseMapper;
import com.paidang.dao.model.ArticleCollectPraise;
import com.paidang.dao.model.ArticleCollectPraiseExample;
import com.paidang.daoEx.ArticleCollectPraiseMapperEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCollectPraiseService {
	@Autowired
	private ArticleCollectPraiseMapper articleCollectPraiseMapper;
	@Autowired
	private ArticleCollectPraiseMapperEx articleCollectPraiseMapperEx;

	public int countByExample(ArticleCollectPraiseExample example) {
		return this.articleCollectPraiseMapper.countByExample(example);
	}

	public ArticleCollectPraise selectByPrimaryKey(Integer id) {
		return this.articleCollectPraiseMapper.selectByPrimaryKey(id);
	}

	public List<ArticleCollectPraise> selectByExample(ArticleCollectPraiseExample example) {
		return this.articleCollectPraiseMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.articleCollectPraiseMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ArticleCollectPraise record) {
		return this.articleCollectPraiseMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(ArticleCollectPraise record) {
		return this.articleCollectPraiseMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(ArticleCollectPraiseExample example) {
		return this.articleCollectPraiseMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(ArticleCollectPraise record, ArticleCollectPraiseExample example) {
		return this.articleCollectPraiseMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(ArticleCollectPraise record, ArticleCollectPraiseExample example) {
		return this.articleCollectPraiseMapper.updateByExample(record, example);
	}

	public int insert(ArticleCollectPraise record) {
		return this.articleCollectPraiseMapper.insert(record);
	}

	public int insertSelective(ArticleCollectPraise record) {
		return this.articleCollectPraiseMapper.insertSelective(record);
	}

	public List<ArticleCollectPraise> selectByWrapper(EntityWrapper<ArticleCollectPraise> wrapper) {
		return this.articleCollectPraiseMapper.selectByWrapper(wrapper);
	}

	public List<String> lastPraiseIcon(Integer articleId){
		List<String> list = articleCollectPraiseMapperEx.lastPraiseIcon(articleId);
		return list;
	}
}
