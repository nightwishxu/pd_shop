package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.ArticleCommentReplyMapper;
import com.paidang.dao.model.ArticleCommentReply;
import com.paidang.dao.model.ArticleCommentReplyExample;
import com.paidang.daoEx.ArticleCommentReplyMapperEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCommentReplyService {
	@Autowired
	private ArticleCommentReplyMapper articleCommentReplyMapper;

	@Autowired
	private ArticleCommentReplyMapperEx articleCommentReplyMapperEx;

	public int countByExample(ArticleCommentReplyExample example) {
		return this.articleCommentReplyMapper.countByExample(example);
	}

	public ArticleCommentReply selectByPrimaryKey(Integer id) {
		return this.articleCommentReplyMapper.selectByPrimaryKey(id);
	}

	public List<ArticleCommentReply> selectByExample(ArticleCommentReplyExample example) {
		return this.articleCommentReplyMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.articleCommentReplyMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ArticleCommentReply record) {
		return this.articleCommentReplyMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(ArticleCommentReply record) {
		return this.articleCommentReplyMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(ArticleCommentReplyExample example) {
		return this.articleCommentReplyMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(ArticleCommentReply record, ArticleCommentReplyExample example) {
		return this.articleCommentReplyMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(ArticleCommentReply record, ArticleCommentReplyExample example) {
		return this.articleCommentReplyMapper.updateByExample(record, example);
	}

	public int insert(ArticleCommentReply record) {
		return this.articleCommentReplyMapper.insert(record);
	}

	public int insertSelective(ArticleCommentReply record) {
		return this.articleCommentReplyMapper.insertSelective(record);
	}

	public List<ArticleCommentReply> selectByWrapper(EntityWrapper<ArticleCommentReply> wrapper) {
		return this.articleCommentReplyMapper.selectByWrapper(wrapper);
	}

	public int updateReplyNum(Integer id){
		return articleCommentReplyMapperEx.updateReplyNum(id);
	}

}
