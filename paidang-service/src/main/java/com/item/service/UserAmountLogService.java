package com.item.service;

import com.base.mybatis.plus.EntityWrapper;
import com.base.util.BaseUtils;
import com.item.daoEx.UserMapperEx;
import com.paidang.dao.UserAmountLogMapper;
import com.paidang.dao.model.UserAmountLog;
import com.paidang.dao.model.UserAmountLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class UserAmountLogService {
	@Autowired
	private UserAmountLogMapper userAmountLogMapper;

	@Autowired
	private BaseService baseService;

	@Autowired
	private UserMapperEx userMapperEx;

	public int countByExample(UserAmountLogExample example) {
		return this.userAmountLogMapper.countByExample(example);
	}

	public UserAmountLog selectByPrimaryKey(String id) {
		return this.userAmountLogMapper.selectByPrimaryKey(id);
	}

	public List<UserAmountLog> selectByExample(UserAmountLogExample example) {
		return this.userAmountLogMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(String id) {
		return this.userAmountLogMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(UserAmountLog record) {
		return this.userAmountLogMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(UserAmountLog record) {
		return this.userAmountLogMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(UserAmountLogExample example) {
		return this.userAmountLogMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(UserAmountLog record, UserAmountLogExample example) {
		return this.userAmountLogMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(UserAmountLog record, UserAmountLogExample example) {
		return this.userAmountLogMapper.updateByExample(record, example);
	}

	public int insert(UserAmountLog record) {
		return this.userAmountLogMapper.insert(record);
	}

	public int insertSelective(UserAmountLog record) {
		return this.userAmountLogMapper.insertSelective(record);
	}

	public List<UserAmountLog> selectByWrapper(EntityWrapper<UserAmountLog> wrapper) {
		return this.userAmountLogMapper.selectByWrapper(wrapper);
	}

	/**
	 * 机构流水保存
	 * @param amount
	 * @param item 1订单收入 2 提现 3 手续费 4 退款
	 * @param info
	 * @param fid
	 * @param payLogId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public UserAmountLog saveLog( Integer userId, BigDecimal amount, String item, String info, Integer fid, String payLogId){
		UserAmountLog log = new UserAmountLog();
		log.setId(BaseUtils.getRandomOrderId(""));
		log.setUserId(userId);
		log.setAmount(amount);
		log.setItem(item);
		log.setInfo(info);
		log.setFid(fid);
		log.setPayLogId(payLogId);
		//1订单收入 2 提现 3 手续费 4 退款
		if ("1".equals(item)){
			log.setType(2);
			log.setAmountNew(amount);
		}else {
			log.setType(1);
			log.setAmountNew(new BigDecimal(-1).multiply(amount));
		}
		log.setCreateTime(new Date());
		insertSelective(log);
//		baseService.updateNumById("b_user","balance",log.getAmountNew(),userId);
		userMapperEx.saveBalance(userId,log.getAmountNew());
		return log;
	}


}
