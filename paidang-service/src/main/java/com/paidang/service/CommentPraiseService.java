package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.CommentPraiseMapper;
import com.paidang.dao.model.CommentPraise;
import com.paidang.dao.model.CommentPraiseExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentPraiseService {
	@Autowired
	private CommentPraiseMapper commentPraiseMapper;

	public int countByExample(CommentPraiseExample example) {
		return this.commentPraiseMapper.countByExample(example);
	}

	public CommentPraise selectByPrimaryKey(Integer id) {
		return this.commentPraiseMapper.selectByPrimaryKey(id);
	}

	public List<CommentPraise> selectByExample(CommentPraiseExample example) {
		return this.commentPraiseMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.commentPraiseMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(CommentPraise record) {
		return this.commentPraiseMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(CommentPraise record) {
		return this.commentPraiseMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(CommentPraiseExample example) {
		return this.commentPraiseMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(CommentPraise record, CommentPraiseExample example) {
		return this.commentPraiseMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(CommentPraise record, CommentPraiseExample example) {
		return this.commentPraiseMapper.updateByExample(record, example);
	}

	public int insert(CommentPraise record) {
		return this.commentPraiseMapper.insert(record);
	}

	public int insertSelective(CommentPraise record) {
		return this.commentPraiseMapper.insertSelective(record);
	}

	public List<CommentPraise> selectByWrapper(EntityWrapper<CommentPraise> wrapper) {
		return this.commentPraiseMapper.selectByWrapper(wrapper);
	}

}
