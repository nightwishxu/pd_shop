package com.paidang.service;

import java.util.List;

import com.paidang.daoEx.CommentMapperEx;
import com.paidang.daoEx.model.CommentEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paidang.dao.CommentMapper;
import com.paidang.dao.model.Comment;
import com.paidang.dao.model.CommentExample;

@Service
public class CommentService {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private CommentMapperEx commentMapperEx;

	public int countByExample(CommentExample example) {
		return this.commentMapper.countByExample(example);
	}

	public Comment selectByPrimaryKey(Integer id) {
		return this.commentMapper.selectByPrimaryKey(id);
	}

	public List<Comment> selectByExample(CommentExample example) {
		return this.commentMapper.selectByExample(example);
	}

	public List<Comment> selectByExampleWithBLOBs(CommentExample example) {
		return this.commentMapper.selectByExampleWithBLOBs(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.commentMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Comment record) {
		return this.commentMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(Comment record) {
		return this.commentMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(Comment record) {
		return this.commentMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(CommentExample example) {
		return this.commentMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(Comment record, CommentExample example) {
		return this.commentMapper.updateByExampleSelective(record, example);
	}

	public int updateByExampleWithBLOBs(Comment record, CommentExample example) {
		return this.commentMapper.updateByExampleWithBLOBs(record, example);
	}

	public int updateByExample(Comment record, CommentExample example) {
		return this.commentMapper.updateByExample(record, example);
	}

	public int insert(Comment record) {
		return this.commentMapper.insert(record);
	}

	public int insertSelective(Comment record) {
		return this.commentMapper.insertSelective(record);
	}

	public List<CommentEx> findList(CommentEx commentEx){
		return commentMapperEx.findList(commentEx);
	}

}
