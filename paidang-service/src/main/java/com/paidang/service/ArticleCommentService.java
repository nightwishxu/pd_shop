package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.ArticleCommentMapper;
import com.paidang.dao.model.ArticleComment;
import com.paidang.dao.model.ArticleCommentExample;
import com.paidang.daoEx.ArticleCommentMapperEx;
import com.paidang.daoEx.model.ArticleCommentEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCommentService {
	@Autowired
	private ArticleCommentMapper articleCommentMapper;

	@Autowired
	private ArticleCommentMapperEx articleCommentMapperEx;

	public int countByExample(ArticleCommentExample example) {
		return this.articleCommentMapper.countByExample(example);
	}

	public ArticleComment selectByPrimaryKey(Integer id) {
		return this.articleCommentMapper.selectByPrimaryKey(id);
	}

	public List<ArticleComment> selectByExample(ArticleCommentExample example) {
		return this.articleCommentMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.articleCommentMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ArticleComment record) {
		return this.articleCommentMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(ArticleComment record) {
		return this.articleCommentMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(ArticleCommentExample example) {
		return this.articleCommentMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(ArticleComment record, ArticleCommentExample example) {
		return this.articleCommentMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(ArticleComment record, ArticleCommentExample example) {
		return this.articleCommentMapper.updateByExample(record, example);
	}

	public int insert(ArticleComment record) {
		return this.articleCommentMapper.insert(record);
	}

	public int insertSelective(ArticleComment record) {
		return this.articleCommentMapper.insertSelective(record);
	}

	public List<ArticleComment> selectByWrapper(EntityWrapper<ArticleComment> wrapper) {
		return this.articleCommentMapper.selectByWrapper(wrapper);
	}

	/**
	 * 评论列表查查询
	 * @param articleId
	 * @return
	 */
	public List<ArticleCommentEx> findList(Integer articleId, Integer userId){
		return articleCommentMapperEx.findList(articleId,userId);
	}

	/**
	 * 新增评论数
	 * @param id
	 * @return
	 */
	public  Integer updateReplyNum(Integer id){
		return articleCommentMapperEx.updateReplyNum(id);
	}


	public List<ArticleCommentEx> findWonderfulCommentList(Integer articleId,Integer likeNum,Integer replyNum,Integer userId){
		return articleCommentMapperEx.findWonderfulCommentList(articleId,likeNum,replyNum,userId);
	}

	public Integer updateLikeNum(Integer id,Integer num){
		return articleCommentMapperEx.updateLikeNum(id,num);
	}


//	public List<MyCommentVo> finduserComment(Integer userId){
//		return articleCommentMapperEx.finduserComment(userId);
//	}

}
