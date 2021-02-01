package com.item.service;

import com.base.mybatis.plus.EntityWrapper;
import com.base.util.BaseUtils;
import com.paidang.dao.OrgAmountLogMapper;
import com.paidang.dao.model.OrgAmountLog;
import com.paidang.dao.model.OrgAmountLogExample;
import com.paidang.daoEx.PawnOrgMapperEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrgAmountLogService {
	@Autowired
	private OrgAmountLogMapper orgAmountLogMapper;

	@Autowired
	private BaseService baseService;

	@Autowired
	private PawnOrgMapperEx pawnOrgMapperEx;

	public int countByExample(OrgAmountLogExample example) {
		return this.orgAmountLogMapper.countByExample(example);
	}

	public OrgAmountLog selectByPrimaryKey(String id) {
		return this.orgAmountLogMapper.selectByPrimaryKey(id);
	}

	public List<OrgAmountLog> selectByExample(OrgAmountLogExample example) {
		return this.orgAmountLogMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(String id) {
		return this.orgAmountLogMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(OrgAmountLog record) {
		return this.orgAmountLogMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(OrgAmountLog record) {
		return this.orgAmountLogMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(OrgAmountLogExample example) {
		return this.orgAmountLogMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(OrgAmountLog record, OrgAmountLogExample example) {
		return this.orgAmountLogMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(OrgAmountLog record, OrgAmountLogExample example) {
		return this.orgAmountLogMapper.updateByExample(record, example);
	}

	public int insert(OrgAmountLog record) {
		return this.orgAmountLogMapper.insert(record);
	}

	public int insertSelective(OrgAmountLog record) {
		return this.orgAmountLogMapper.insertSelective(record);
	}

	public List<OrgAmountLog> selectByWrapper(EntityWrapper<OrgAmountLog> wrapper) {
		return this.orgAmountLogMapper.selectByWrapper(wrapper);
	}

	/**
	 * 机构流水保存
	 * @param orgId
	 * @param amount
	 * @param item 1订单收入 2 提现 3 手续费 4 退款
	 * @param info
	 * @param fid
	 * @param payLogId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public OrgAmountLog saveLog(Integer orgId,Integer userId, BigDecimal amount,String item,String info,Integer fid,String payLogId){
		OrgAmountLog log = new OrgAmountLog();
		log.setId(BaseUtils.getRandomOrderId(""));
		log.setOrgId(orgId);
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
//		baseService.updateNumById("p_pawn_org","amount",log.getAmountNew(),orgId);
		pawnOrgMapperEx.saveAmount(orgId,log.getAmountNew());
		return log;
	}


}
