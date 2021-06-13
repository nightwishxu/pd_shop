package com.paidang.service;

import com.base.api.ApiException;
import com.base.mybatis.plus.EntityWrapper;
import com.base.util.StringUtil;
import com.item.service.OrgAmountLogService;
import com.paidang.dao.OrgWithdrawApplyMapper;
import com.paidang.dao.model.OrgWithdrawApply;
import com.paidang.dao.model.OrgWithdrawApplyExample;
import com.paidang.dao.model.PawnOrg;
import com.paidang.daoEx.OrgWithdrawApplyMapperEx;
import com.paidang.daoEx.model.OrgWithdrawApplyEx;
import com.paidang.domain.qo.OrgWithdrawApplyQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrgWithdrawApplyService {
	@Autowired
	private OrgWithdrawApplyMapper orgWithdrawApplyMapper;

	@Autowired
	private OrgWithdrawApplyMapperEx orgWithdrawApplyMapperEx;

	@Autowired
	private PawnOrgService pawnOrgService;

	@Autowired
	private OrgAmountLogService orgAmountLogService;

	public int countByExample(OrgWithdrawApplyExample example) {
		return this.orgWithdrawApplyMapper.countByExample(example);
	}

	public OrgWithdrawApply selectByPrimaryKey(Integer id) {
		return this.orgWithdrawApplyMapper.selectByPrimaryKey(id);
	}

	public List<OrgWithdrawApply> selectByExample(OrgWithdrawApplyExample example) {
		return this.orgWithdrawApplyMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.orgWithdrawApplyMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(OrgWithdrawApply record) {
		return this.orgWithdrawApplyMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(OrgWithdrawApply record) {
		return this.orgWithdrawApplyMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(OrgWithdrawApplyExample example) {
		return this.orgWithdrawApplyMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(OrgWithdrawApply record, OrgWithdrawApplyExample example) {
		return this.orgWithdrawApplyMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(OrgWithdrawApply record, OrgWithdrawApplyExample example) {
		return this.orgWithdrawApplyMapper.updateByExample(record, example);
	}

	public int insert(OrgWithdrawApply record) {
		return this.orgWithdrawApplyMapper.insert(record);
	}

	public int insertSelective(OrgWithdrawApply record) {
		return this.orgWithdrawApplyMapper.insertSelective(record);
	}

	public List<OrgWithdrawApply> selectByWrapper(EntityWrapper<OrgWithdrawApply> wrapper) {
		return this.orgWithdrawApplyMapper.selectByWrapper(wrapper);
	}

	/**
	 * 提现申请
	 * @param userId
	 * @param orgId
	 * @param amount
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public OrgWithdrawApply withdrawApply(Integer userId,Integer orgId, BigDecimal amount, Integer type, String bankCardNo, String bankCardName){
		pawnOrgService.reSumAmount(orgId);
		PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(orgId);
		if (pawnOrg.getAmount()==null){
			throw new ApiException(400,"余额不足");
		}
		if (amount.compareTo(pawnOrg.getAmount())>0){
			throw new ApiException(400,"提现金额大于可提现金额");
		}

		if (type.intValue() == 10 && (StringUtil.isEmpty(bankCardName) || StringUtil.isEmpty(bankCardNo))) {
			throw new ApiException(400,"银行卡提现，卡号和银行不能为空");
		}

		OrgWithdrawApply apply = new OrgWithdrawApply();
		apply.setOrgId(orgId);
		apply.setAmount(amount);
		apply.setTradeType(type);
		apply.setUserId(userId);
		apply.setStatus(0);
		apply.setBankCardName(bankCardName);
		apply.setBankCardNo(bankCardNo);
		apply.setCreateAccount(userId.toString());
		apply.setCreateTime(new Date());
		orgWithdrawApplyMapper.insert(apply);
		return apply;
	}

	public List<OrgWithdrawApplyEx> findList(OrgWithdrawApplyQo qo){
		return orgWithdrawApplyMapperEx.findList(qo);
	}
}
