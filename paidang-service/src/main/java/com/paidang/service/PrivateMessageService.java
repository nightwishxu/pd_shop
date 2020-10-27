package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.PrivateMessageMapper;
import com.paidang.dao.model.PrivateMessage;
import com.paidang.dao.model.PrivateMessageExample;
import com.paidang.daoEx.PrivateMessageMapperEx;
import com.paidang.daoEx.model.PrivateMessageEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateMessageService {
	@Autowired
	private PrivateMessageMapper privateMessageMapper;
	
	@Autowired
	private PrivateMessageMapperEx privateMessageMapperEx;

	public int countByExample(PrivateMessageExample example) {
		return this.privateMessageMapper.countByExample(example);
	}

	public PrivateMessage selectByPrimaryKey(Integer id) {
		return this.privateMessageMapper.selectByPrimaryKey(id);
	}

	public List<PrivateMessage> selectByExample(PrivateMessageExample example) {
		return this.privateMessageMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.privateMessageMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(PrivateMessage record) {
		return this.privateMessageMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(PrivateMessage record) {
		return this.privateMessageMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(PrivateMessageExample example) {
		return this.privateMessageMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(PrivateMessage record, PrivateMessageExample example) {
		return this.privateMessageMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(PrivateMessage record, PrivateMessageExample example) {
		return this.privateMessageMapper.updateByExample(record, example);
	}

	public int insert(PrivateMessage record) {
		return this.privateMessageMapper.insert(record);
	}

	public int insertSelective(PrivateMessage record) {
		return this.privateMessageMapper.insertSelective(record);
	}

	public List<PrivateMessage> selectByWrapper(EntityWrapper<PrivateMessage> wrapper) {
		return this.privateMessageMapper.selectByWrapper(wrapper);
	}



	public List<PrivateMessageEx> myPrivateMessageList(Integer userId){
		return privateMessageMapperEx.myPrivateMessageList(userId);
	}


	public List<PrivateMessageEx> myPrivateMessageInfo(Integer userId,Integer friendId){
		return privateMessageMapperEx.myPrivateMessageInfo(userId,friendId);
	}

	public int deleteAll(Integer userId,Integer friendId){
		return privateMessageMapperEx.deleteAll(userId,friendId);
	}

	//    readMsg
	public int readMsg(Integer userId,Integer friendId){
		return privateMessageMapperEx.readMsg(userId,friendId);
	}

	public int countUnreadMsg(Integer userId,Integer friendId){
		return privateMessageMapperEx.countUnreadMsg(userId,friendId);
	}

	public int deleteSingle(Integer id){
		return privateMessageMapperEx.deleteSingle(id);
	}

}
