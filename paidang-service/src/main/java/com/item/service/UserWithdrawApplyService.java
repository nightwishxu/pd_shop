package com.item.service;

import com.base.api.ApiException;
import com.base.mybatis.plus.EntityWrapper;
import com.item.dao.model.User;
import com.paidang.dao.UserWithdrawApplyMapper;
import com.paidang.dao.model.UserAmountLog;
import com.paidang.dao.model.UserWithdrawApply;
import com.paidang.dao.model.UserWithdrawApplyExample;
import com.paidang.daoEx.UserWithdrawApplyMapperEx;
import com.paidang.daoEx.model.UserWithdrawApplyEx;
import com.paidang.domain.qo.UserWithdrawApplyQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class UserWithdrawApplyService {
	@Autowired
	private UserWithdrawApplyMapper userWithdrawApplyMapper;

	@Autowired
	private UserWithdrawApplyMapperEx userWithdrawApplyMapperEx;

	@Autowired
	private UserService userService;

	public int countByExample(UserWithdrawApplyExample example) {
		return this.userWithdrawApplyMapper.countByExample(example);
	}

	public UserWithdrawApply selectByPrimaryKey(Integer id) {
		return this.userWithdrawApplyMapper.selectByPrimaryKey(id);
	}

	public List<UserWithdrawApply> selectByExample(UserWithdrawApplyExample example) {
		return this.userWithdrawApplyMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.userWithdrawApplyMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(UserWithdrawApply record) {
		return this.userWithdrawApplyMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(UserWithdrawApply record) {
		return this.userWithdrawApplyMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(UserWithdrawApplyExample example) {
		return this.userWithdrawApplyMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(UserWithdrawApply record, UserWithdrawApplyExample example) {
		return this.userWithdrawApplyMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(UserWithdrawApply record, UserWithdrawApplyExample example) {
		return this.userWithdrawApplyMapper.updateByExample(record, example);
	}

	public int insert(UserWithdrawApply record) {
		return this.userWithdrawApplyMapper.insert(record);
	}

	public int insertSelective(UserWithdrawApply record) {
		return this.userWithdrawApplyMapper.insertSelective(record);
	}

	public List<UserWithdrawApply> selectByWrapper(EntityWrapper<UserWithdrawApply> wrapper) {
		return this.userWithdrawApplyMapper.selectByWrapper(wrapper);
	}

	/**
	 * 提现申请
	 * @param userId
	 * @param amount
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public UserWithdrawApply withdrawApply(Integer userId,  BigDecimal amount){
		userService.reSumBalance(userId);
		User user = userService.selectByPrimaryKey(userId);
		if (user.getBalance()==null){
			throw new ApiException(400,"余额不足");
		}
		if (amount.compareTo(user.getBalance())>0){
			throw new ApiException(400,"提现金额大于可提现金额");
		}
		UserWithdrawApply apply = new UserWithdrawApply();
		apply.setAmount(amount);
		apply.setTradeType(10);
		apply.setUserId(userId);
		apply.setStatus(0);
		apply.setCreateAccount(userId.toString());
		apply.setCreateTime(new Date());
		return apply;
	}

	public 	List<UserWithdrawApplyEx> findList(UserWithdrawApplyQo qo){
		return userWithdrawApplyMapperEx.findList(qo);
	}


}
