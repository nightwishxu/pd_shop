package com.item.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.IntegralLogMapper;
import com.paidang.dao.model.IntegralLog;
import com.paidang.dao.model.IntegralLogExample;
import com.paidang.daoEx.PawnOrgMapperEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class IntegralLogService {
	@Autowired
	private IntegralLogMapper integralLogMapper;

	@Autowired
	private PawnOrgMapperEx pawnOrgMapperEx;

	public int countByExample(IntegralLogExample example) {
		return this.integralLogMapper.countByExample(example);
	}

	public IntegralLog selectByPrimaryKey(Integer id) {
		return this.integralLogMapper.selectByPrimaryKey(id);
	}

	public List<IntegralLog> selectByExample(IntegralLogExample example) {
		return this.integralLogMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.integralLogMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(IntegralLog record) {
		return this.integralLogMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(IntegralLog record) {
		return this.integralLogMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(IntegralLogExample example) {
		return this.integralLogMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(IntegralLog record, IntegralLogExample example) {
		return this.integralLogMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(IntegralLog record, IntegralLogExample example) {
		return this.integralLogMapper.updateByExample(record, example);
	}

	public int insert(IntegralLog record) {
		return this.integralLogMapper.insert(record);
	}

	public int insertSelective(IntegralLog record) {
		return this.integralLogMapper.insertSelective(record);
	}

	public List<IntegralLog> selectByWrapper(EntityWrapper<IntegralLog> wrapper) {
		return this.integralLogMapper.selectByWrapper(wrapper);
	}


	/**
	 * 添加积分
	 * @param userId
	 * @param integral
	 * @param type
	 * @param accountType
	 * @param logId
	 * @param info
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addIntegral(Integer userId, BigDecimal integral, Integer type,
							Integer accountType, String logId, String info){
		//检查重复
		IntegralLog log = new IntegralLog();
		log.setAccountType(accountType);
		log.setUserId(userId);
		log.setIntegral(integral);
		log.setType(type);
		log.setLogId(logId);
		log.setInfo(info);
		log.setCreateAccount(userId.toString());
		log.setCreateTime(new Date());
		if (type==0){
			log.setIntegralNew(integral);
		}
		if (accountType==1){
			pawnOrgMapperEx.addIntegral(userId,log.getIntegralNew());
		}
		insert(log);
	}
}
