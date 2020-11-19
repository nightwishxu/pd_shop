package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.ArticleMapper;
import com.paidang.dao.model.Article;
import com.paidang.dao.model.ArticleExample;
import com.paidang.daoEx.ArticleMapperEx;
import com.paidang.daoEx.model.ArticleEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private ArticleMapperEx articleMapperEx;

	public int countByExample(ArticleExample example) {
		return this.articleMapper.countByExample(example);
	}

	public Article selectByPrimaryKey(Integer id) {
		return this.articleMapper.selectByPrimaryKey(id);
	}

	public List<Article> selectByExample(ArticleExample example) {
		return this.articleMapper.selectByExample(example);
	}

	public List<Article> selectByExampleWithBLOBs(ArticleExample example) {
		return this.articleMapper.selectByExampleWithBLOBs(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.articleMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Article record) {
		return this.articleMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(Article record) {
		return this.articleMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(Article record) {
		return this.articleMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(ArticleExample example) {
		return this.articleMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(Article record, ArticleExample example) {
		return this.articleMapper.updateByExampleSelective(record, example);
	}

	public int updateByExampleWithBLOBs(Article record, ArticleExample example) {
		return this.articleMapper.updateByExampleWithBLOBs(record, example);
	}

	public int updateByExample(Article record, ArticleExample example) {
		return this.articleMapper.updateByExample(record, example);
	}

	public int insert(Article record) {
		return this.articleMapper.insert(record);
	}

	public int insertSelective(Article record) {
		return this.articleMapper.insertSelective(record);
	}

	public List<Article> selectByWrapper(EntityWrapper<Article> wrapper) {
		return this.articleMapper.selectByWrapper(wrapper);
	}

	public List<Article> selectByWrapperWithBLOBs(EntityWrapper<Article> wrapper) {
		return this.articleMapper.selectByWrapperWithBLOBs(wrapper);
	}


	/**
	 *
	 * @param id
	 * @param num
	 * @param type 0更新评论数1更新收藏数2更新点赞数3点击数4举报数
	 * @return
	 */
	public Integer updateArticleCount(Integer id,Integer num,Integer type){

		return articleMapperEx.updateArticleCount(id,num,type);
	}

	/**
	 * 获取用户发布动态数
	 * @param userId
	 * @return
	 */
	public Integer getArticleCount(Integer userId){
		return  articleMapperEx.getArticleCount(userId);
	}


	public List<ArticleEx> findList(ArticleEx ex){
		return articleMapperEx.findList(ex);
	}

	public List<ArticleEx> findNoticeList(ArticleEx ex){
		return articleMapperEx.findNoticeList(ex);
	}

	public List<ArticleEx> findNoticeIndex(ArticleEx ex){
		return articleMapperEx.findNoticeIndex(ex);
	}

	public  List<ArticleEx> recommendList(List<Integer> userIds,Integer userId){
		return articleMapperEx.recommendList(userIds,userId);
	}

	/**
	 * 关注用户近7天动态
	 * @param userId
	 * @param limit
	 * @return
	 */
	public List<ArticleEx> followArticleList(Integer userId,Integer flag,Integer limit){
		return articleMapperEx.followArticleList(userId,flag,limit);
	}


	/**
	 * 非关注用户近7天动态（点赞前20 随机8）
	 * @param userId
	 * @param limit
	 * @return
	 */
	public List<ArticleEx> recommendNewList(Integer userId,Integer limit){
		return articleMapperEx.recommendNewList(userId,limit);
	}
}
