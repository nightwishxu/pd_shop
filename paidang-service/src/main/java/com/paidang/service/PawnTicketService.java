package com.paidang.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.hutool.core.convert.Convert;
import com.item.dao.UserMapper;
import com.item.dao.model.User;
import com.paidang.dao.PawnOrgMapper;
import com.paidang.dao.UserGoodsMapper;
import com.paidang.dao.model.*;
import com.ruoyi.common.utils.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.mybatis.plus.EntityWrapper;

import com.paidang.dao.PawnTicketMapper;

@Service
public class PawnTicketService {
	@Autowired
	private PawnTicketMapper pawnTicketMapper;

	@Autowired
	private PawnOrgMapper pawnOrgMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserGoodsMapper userGoodsMapper;

	public int countByExample(PawnTicketExample example) {
		return this.pawnTicketMapper.countByExample(example);
	}

	public PawnTicket selectByPrimaryKey(Integer id) {
		return this.pawnTicketMapper.selectByPrimaryKey(id);
	}

	public List<PawnTicket> selectByExample(PawnTicketExample example) {
		return this.pawnTicketMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.pawnTicketMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(PawnTicket record) {
		return this.pawnTicketMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(PawnTicket record) {
		return this.pawnTicketMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(PawnTicketExample example) {
		return this.pawnTicketMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(PawnTicket record, PawnTicketExample example) {
		return this.pawnTicketMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(PawnTicket record, PawnTicketExample example) {
		return this.pawnTicketMapper.updateByExample(record, example);
	}

	public int insert(PawnTicket record) {
		return this.pawnTicketMapper.insert(record);
	}

	public int insertSelective(PawnTicket record) {
		return this.pawnTicketMapper.insertSelective(record);
	}

	public List<PawnTicket> selectByWrapper(EntityWrapper<PawnTicket> wrapper) {
		return this.pawnTicketMapper.selectByWrapper(wrapper);
	}

	/**
	 * 机构端注册时需注册安心签
	 * 中标后，签署前完善信息，合同签署完成后，上传支付凭证
	 */

	public void addDDTicket(UserPawn userPawn){
		User user  = userMapper.selectByPrimaryKey(userPawn.getUserId());
		PawnOrg pawnOrg = pawnOrgMapper.selectByPrimaryKey(userPawn.getOrgId());
		UserGoods userGoods = userGoodsMapper.selectByPrimaryKey(userPawn.getGoodsId());
		PawnTicket entity = new PawnTicket();
		entity.setOrgName(pawnOrg.getName()==null?"":pawnOrg.getName());
		entity.setOrgTel(pawnOrg.getPhone()==null?"":pawnOrg.getPhone());
		entity.setOrgAdress(pawnOrg.getAdress()==null?"":pawnOrg.getAdress());
		entity.setBusinessLicense(pawnOrg.getBusinessLicenseCode()==null?"":pawnOrg.getBusinessLicenseCode());
		entity.setPawnerCert("身份证");
		entity.setPawnerName(user.getName());
		entity.setPawnerTel(user.getPhone());
		entity.setPawnerIdCard(user.getIdCard());
		entity.setContactor(user.getName());
//		entity.setPawnerAddr(); 当户地址
		entity.setGoodsName(userPawn.getGoodsName());
		entity.setAuthPriceTest(userGoods.getAuthPriceTest()==null?null:userGoods.getAuthPriceTest().toString());
		entity.setAuthPrice(userGoods.getAuthPrice());
//		entity.setEquivalentRatio();
		BigDecimal total = userPawn.getMoney();
		BigDecimal pawnMoney = total.multiply(userPawn.getRate());
		BigDecimal userMoney = total.subtract(pawnMoney);
		entity.setLoanMoney(total.toString());
		entity.setUserMoney(userMoney.toString());
		entity.setPawnMoney(pawnMoney.toString());
		entity.setLoanMoneyCN(Convert.digitToChinese(total));
		entity.setUserMoneyCN(Convert.digitToChinese(userMoney));
		entity.setPawnMoneyCN(Convert.digitToChinese(pawnMoney));
		entity.setPawnBeginTime(DateFormatUtils.format(userPawn.getPawnBeginTime(),"yyyy-MM-dd"));
		entity.setPawnEndTime(DateFormatUtils.format(userPawn.getPawnEndTime(),"yyyy-MM-dd"));
		entity.setBeginPawnEndTime(DateFormatUtils.format(userPawn.getBeginPawnEndTime(),"yyyy-MM-dd"));
		entity.setRate(userPawn.getRate()==null?null:userPawn.getRate().toString());
		entity.setMoneyRate(userPawn.getMoneyRate()==null?null:userPawn.getMoneyRate().toString());
//		entity.setCost();
//		entity.setCostCN();
//		entity.setRepawnTotalCN();
//		entity.setMoneyCostCN();
//		entity.setRepawnBeginTime();
//		entity.setRepawnEndTime();
		entity.setUserId(userPawn.getUserId());
		entity.setOtherOrder(null);
		entity.setType("1");
		entity.setOrgId(userPawn.getOrgId());
		entity.setStatus("0");
		entity.setCreateTime(new Date());

	}


	public void adXDTicket(){

	}
}
