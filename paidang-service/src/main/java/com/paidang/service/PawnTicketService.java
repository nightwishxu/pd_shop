package com.paidang.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.hutool.core.convert.Convert;
import com.base.api.ApiException;
import com.base.dao.model.Result;
import com.item.dao.UserMapper;
import com.item.dao.model.User;
import com.paidang.dao.PawnOrgMapper;
import com.paidang.dao.UserGoodsMapper;
import com.paidang.dao.model.*;
import com.paidang.daoEx.PawnTicketMapperEx;
import com.paidang.daoEx.model.PawnTicketEx;
import com.paidang.domain.pojo.RepawnTicketModel;
import com.paidang.domain.qo.PawnTicketQo;
import com.ruoyi.common.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.mybatis.plus.EntityWrapper;

import com.paidang.dao.PawnTicketMapper;
import org.springframework.util.StringUtils;

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

	@Autowired
	private PawnTicketMapperEx pawnTicketMapperEx;

	@Autowired
	private UserPawnService userPawnService;

	@Autowired
	private PawnContinueService pawnContinueService;

	@Autowired
	private BFileService fileService;

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

	public PawnTicket getByProjectCode(String projectCode){
		PawnTicketExample example = new PawnTicketExample();
		example.createCriteria().andProjectCodeEqualTo(projectCode);
		List<PawnTicket> pawnTickets = selectByExample(example);
		if (CollectionUtils.isNotEmpty(pawnTickets)){
			return pawnTickets.get(0);
		}
		return null;
	}

	/**
	 * 机构端注册时需注册安心签
	 * 中标后，签署前完善信息，合同签署完成后，上传支付凭证
	 */

	public Integer addDDTicket(UserPawn userPawn,String projectCode,
							String pawnTicketCode,String checker,String handler,String remark){
		User user  = userMapper.selectByPrimaryKey(userPawn.getUserId());
		PawnOrg pawnOrg = pawnOrgMapper.selectByPrimaryKey(userPawn.getOrgId());
		UserGoods userGoods = userGoodsMapper.selectByPrimaryKey(userPawn.getGoodsId());
		PawnTicket entity = new PawnTicket();
		entity.setOrgName(pawnOrg.getName()==null?"":pawnOrg.getName());
		entity.setOrgTel(pawnOrg.getAnxinPhone()==null?"":pawnOrg.getAnxinPhone());
		entity.setOrgAdress(pawnOrg.getAdress()==null?"":pawnOrg.getAdress());
		entity.setBusinessLicense(pawnOrg.getBusinessLicenseCode()==null?"":pawnOrg.getBusinessLicenseCode());
		entity.setPawnerCert("身份证");
		entity.setPawnerName(user.getName());
		entity.setPawnerTel(user.getPhone());
		entity.setPawnerIdCard(user.getIdCard());
		entity.setContactor(user.getName());
		entity.setPawnerAddr(user.getIdCardHand()); //当户地址
		entity.setGoodsName(userPawn.getGoodsName());
		entity.setAuthPriceTest(userGoods.getAuthPrice().toString());
		entity.setAuthPrice(userPawn.getMoney());
		BigDecimal total = userPawn.getMoney();
		BigDecimal pawnMoney = total.multiply(userPawn.getRate()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(userPawn.getBeginPawnMonth()).divide(new BigDecimal("2"),2,BigDecimal.ROUND_HALF_DOWN));
		BigDecimal userMoney = total.subtract(pawnMoney).setScale(2,BigDecimal.ROUND_HALF_DOWN);
		entity.setCost(pawnMoney.setScale(2,BigDecimal.ROUND_HALF_DOWN).toString());
		entity.setCostCN(Convert.digitToChinese(pawnMoney));
		entity.setLoanMoney(total.toString());
		entity.setUserMoney(userMoney.toString());
		entity.setPawnMoney(pawnMoney.toString());
		entity.setLoanMoneyCN(Convert.digitToChinese(total));
		entity.setUserMoneyCN(Convert.digitToChinese(userMoney));
		entity.setPawnMoneyCN(Convert.digitToChinese(pawnMoney));
		entity.setPawnBeginTime(DateFormatUtils.format(userPawn.getPawnBeginTime(),"yyyy-MM-dd"));
		entity.setPawnEndTime(DateFormatUtils.format(userPawn.getPawnEndTime(),"yyyy-MM-dd"));
		entity.setBeginPawnEndTime(DateFormatUtils.format(userPawn.getBeginPawnEndTime(),"yyyy-MM-dd"));
		entity.setRate(userPawn.getRate()==null?"0":userPawn.getRate().toString() );
		entity.setMoneyRate(userPawn.getMoneyRate()==null?"0":userPawn.getMoneyRate().toString());
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
		entity.setStatus("1");
		entity.setCreateTime(new Date());
		entity.setEquivalentRatio(entity.getAuthPrice().divide(new BigDecimal(entity.getAuthPriceTest()),2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100))+"%");
		entity.setProjectCode(projectCode);
		entity.setPawnTicketCode(pawnTicketCode);
		entity.setChecker(checker);
		entity.setHandler(handler);
		entity.setOtherOrder("无");
		entity.setRemark(remark);
		entity.setOrgStatus(1);
		entity.setUserStatus(1);
		insert(entity);
		return  entity.getId();

	}


	//先是用户续当，生成合同，然后签署，然后上传付款凭证，典当行确认

	// 先是用户续当  典当行确认 完善当票  签署合同

	public void adXDTicket(String projectCode,String userName, String userTel,  PawnOrg pawnOrg, RepawnTicketModel repawn,UserPawn userPawn){
		PawnTicket ticket = new PawnTicket();
		ticket.setPawnTicketCode(repawn.getPawnTicketCode());
		ticket.setOrgName(pawnOrg.getName());
		ticket.setOrgTel(pawnOrg.getAnxinPhone());
		ticket.setOrgAdress(pawnOrg.getAdress());
		ticket.setPawnerName(repawn.getPawnerName());
		ticket.setBusinessLicense(pawnOrg.getBusinessLicenseCode());
		ticket.setOrgId(pawnOrg.getId());
		ticket.setUserId(userPawn.getUserId());
		ticket.setPawnerName(userName);
		ticket.setGoodsName(userPawn.getGoodsName());
		ticket.setPawnerTel(userTel);
		ticket.setRepawnBeginTime(repawn.getRepawnBeginTime());
		ticket.setRepawnEndTime(repawn.getRepawnEndTime());
		ticket.setContactor(userName);
		ticket.setLoanMoney(repawn.getLoanMoney());
		ticket.setLoanMoneyCN(Convert.digitToChinese(new BigDecimal(repawn.getLoanMoney())));
		ticket.setCost(repawn.getCost());
		ticket.setCostCN(Convert.digitToChinese(new BigDecimal(repawn.getCost())));
		ticket.setMoneyCost(repawn.getMoneyCost());
		ticket.setMoneyCostCN(Convert.digitToChinese(new BigDecimal(repawn.getMoneyCost())));
		ticket.setRepawnTotal(repawn.getRepawnTotal());
		ticket.setRepawnTotalCN(Convert.digitToChinese(new BigDecimal(repawn.getRepawnTotal())));
		ticket.setRate(repawn.getRate());
		ticket.setMoneyRate(repawn.getMoneyRate());
		ticket.setHandler(repawn.getHandler());
		ticket.setChecker(repawn.getChecker());
		ticket.setCreateTime(new Date());
		ticket.setProjectCode(projectCode);
		ticket.setStatus("1");
		ticket.setType("2");
		ticket.setUserStatus(1);
		ticket.setOrgStatus(1);
		insert(ticket);
	}

	public List<PawnTicketEx> findList(PawnTicketQo qo){
		return pawnTicketMapperEx.findList(qo);
	}

	public String showContractUrl(Integer pawnId,Integer repawnId,Integer pawnTicketId,Integer type) throws Exception{
		PawnTicket pawnTicket = null;
		if (pawnId!=null){
			UserPawn userPawn = userPawnService.selectByPrimaryKey(pawnId);
			pawnTicket = getByProjectCode(userPawn.getProjectCode());
		}else if (repawnId!=null){
			PawnContinue pawnContinue = pawnContinueService.selectByPrimaryKey(repawnId);
			pawnTicket = getByProjectCode(pawnContinue.getProjectCode());
		}else if (pawnTicketId!=null){
			pawnTicket = selectByPrimaryKey(pawnTicketId);
		}

		if (StringUtils.isEmpty(pawnTicket.getContractUrl())){
			if (StringUtils.isEmpty(pawnTicket.getContractId())){
				if (type!=null && type==1){
					return null;
				}
				throw new ApiException(400,"合同尚未签署完成，无法查看");
			}
			String contractUrl = AnXinSignService.getContractUrl(pawnTicket.getContractId());
			String s = fileService.downLoadFromUrl(contractUrl,pawnTicket.getContractId());
			PawnTicket temp = new PawnTicket();
			temp.setContractUrl(s);
			temp.setId(pawnTicketId);
			updateByPrimaryKeySelective(temp);
			return s;
		}else {
			return pawnTicket.getContractUrl();
		}
	}
}
