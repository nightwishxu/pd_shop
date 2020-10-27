package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.CommentReportLogMapper;
import com.paidang.dao.model.CommentReportLog;
import com.paidang.dao.model.CommentReportLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentReportLogService {
	@Autowired
	private CommentReportLogMapper commentReportLogMapper;

	public int countByExample(CommentReportLogExample example) {
		return this.commentReportLogMapper.countByExample(example);
	}

	public CommentReportLog selectByPrimaryKey(Integer id) {
		return this.commentReportLogMapper.selectByPrimaryKey(id);
	}

	public List<CommentReportLog> selectByExample(CommentReportLogExample example) {
		return this.commentReportLogMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.commentReportLogMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(CommentReportLog record) {
		return this.commentReportLogMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(CommentReportLog record) {
		return this.commentReportLogMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(CommentReportLogExample example) {
		return this.commentReportLogMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(CommentReportLog record, CommentReportLogExample example) {
		return this.commentReportLogMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(CommentReportLog record, CommentReportLogExample example) {
		return this.commentReportLogMapper.updateByExample(record, example);
	}

	public int insert(CommentReportLog record) {
		return this.commentReportLogMapper.insert(record);
	}

	public int insertSelective(CommentReportLog record) {
		return this.commentReportLogMapper.insertSelective(record);
	}

	public List<CommentReportLog> selectByWrapper(EntityWrapper<CommentReportLog> wrapper) {
		return this.commentReportLogMapper.selectByWrapper(wrapper);
	}

}
