package com.paidang.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.api.view.orgRepawn.RepawnMini;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.BaseUtils;
import com.base.util.DateUtil;
import com.paidang.dao.model.*;
import com.paidang.daoEx.PawnContinueMapperEx;
import com.paidang.daoEx.model.PawnContinueEx;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.paidang.dao.PawnContinueMapper;

@Service
public class PawnContinueService {
	@Autowired
	private PawnContinueMapper pawnContinueMapper;
	@Autowired
	private PawnContinueMapperEx pawnContinueMapperEx;

	@Lazy
	@Autowired
	private PawnTicketService pawnTicketService;

	@Autowired
	private UserPawnService userPawnService;

	@Autowired
	private UserGoodsService userGoodsService;

	public int countByExample(PawnContinueExample example) {
		return this.pawnContinueMapper.countByExample(example);
	}

	public PawnContinue selectByPrimaryKey(Integer id1) {
		return this.pawnContinueMapper.selectByPrimaryKey(id1);
	}

	public List<PawnContinue> selectByExample(PawnContinueExample example) {
		return this.pawnContinueMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id1) {
		return this.pawnContinueMapper.deleteByPrimaryKey(id1);
	}

	public int updateByPrimaryKeySelective(PawnContinue record) {
		return this.pawnContinueMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(PawnContinue record) {
		return this.pawnContinueMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(PawnContinueExample example) {
		return this.pawnContinueMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(PawnContinue record, PawnContinueExample example) {
		return this.pawnContinueMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(PawnContinue record, PawnContinueExample example) {
		return this.pawnContinueMapper.updateByExample(record, example);
	}

	public int insert(PawnContinue record) {
		return this.pawnContinueMapper.insert(record);
	}

	public int insertSelective(PawnContinue record) {
		return this.pawnContinueMapper.insertSelective(record);
	}

	//查看 续当当票
	public List<PawnContinueEx> getRepawnTickInfo(String pawnId){
		return this.pawnContinueMapperEx.getRepawnTickInfo(pawnId);
	}

	public PawnContinueEx getRepawnTickInfoById(String pawnContinueId){
		return this.pawnContinueMapperEx.getRepawnTickInfoById(pawnContinueId);
	}


	//返回指定pawnId办理过的续当次数(办理过:机构确认过用户打款凭证)
	public int getRepawnTimes(Integer pawnId){
		PawnContinueExample pawnContinueExample = new PawnContinueExample();
	//	pawnContinueExample.createCriteria().andPawnIdEqualTo(pawnId);
		pawnContinueExample.createCriteria().andPawnIdEqualTo(pawnId).andStateEqualTo(4);
		return pawnContinueMapper.countByExample(pawnContinueExample);
	}

	public PawnContinue getByProjectCode(String projectCode){
		PawnContinueExample example = new PawnContinueExample();
		example.createCriteria().andProjectCodeEqualTo(projectCode);
		List<PawnContinue> userPawns = pawnContinueMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(userPawns)){
			return userPawns.get(0);
		}else {
			return null;
		}
	}


	/**
	 * 机构端续当列表
	 * @param orgId
	 * @param type  1 只返回又操作数据
	 * @return
	 */
	public List<RepawnMini> getRepawnList(Integer orgId,Integer type){
		//获得本机构所有正处于典当中的记录
		UserPawnExample userPawnExample = new UserPawnExample();
		userPawnExample.createCriteria().andOrgIdEqualTo(orgId).andStateEqualTo(2);
		userPawnExample.setOrderByClause("create_time desc");
		List<UserPawn> userPawnList = userPawnService.selectByExample(userPawnExample);

		List<RepawnMini> retList = new ArrayList<>();
		for (UserPawn userPawn : userPawnList){
			RepawnMini repawnMini = new RepawnMini();
			if(userPawn.getGoodsId()==null){
				throw new ApiException(100,"系统异常");
			}
			Integer goodId = userPawn.getGoodsId();
			UserGoods userGoods = userGoodsService.selectByPrimaryKey(goodId);
			String pawnTicketCode = StringUtils.isNotEmpty(userPawn.getPawnTicketCode())?userPawn.getPawnTicketCode():"";
			String goodName = StringUtils.isNotEmpty(userGoods.getName())?userGoods.getName():"";
			String authPrice = userGoods.getAuthPrice()!=null?userGoods.getAuthPrice().toString():"";
			String images = StringUtils.isNotEmpty(userGoods.getImages())?userGoods.getImages():"";
			repawnMini.setId(userPawn.getId().toString());//典当表id
			repawnMini.setGoodsid(userPawn.getGoodsId().toString());//当品id
			repawnMini.setGoodsName(goodName);//当品名称
			repawnMini.setAuthPrice(authPrice);//鉴定价格
			repawnMini.setLastPawnContinueId(userPawn.getLastPawnContinueId());
			//已贷金额(本金)
			repawnMini.setLoanMoney(userPawn.getBeginMoney()!=null?userPawn.getBeginMoney().toString():"");
			//已发放当金 = 本金 - 首期综合费 若数据库已保存则取数据库，否则计算得到
			String userMoney = "";
			boolean isGo = true;
			if(userPawn.getUserMoney()!=null){
				userMoney = userPawn.getUserMoney().toString();
				isGo = false;
			}else if (isGo && userPawn.getPawnMoney()!=null && userPawn.getBeginMoney()!=null){
				BigDecimal um = userPawn.getBeginMoney().subtract(userPawn.getPawnMoney());
				if(um.compareTo(new BigDecimal("0"))== -1){
					userMoney = "";
				}else{
					userMoney = um.toString();
				}
			}
			repawnMini.setUserMoney(userMoney);//已发放当金
			repawnMini.setPawnCode(pawnTicketCode);//当号
			repawnMini.setImages(BaseUtils.getSingleImage(images));//图
			repawnMini.setSignButton("0");
			repawnMini.setCompleteTicketButton("0");
           /* //成功续当次数
            Integer pawnedTimes = pawnContinueService.getRepawnTimes(userPawn.getId());
            repawnMini.setRepawnTimes(pawnedTimes!=null?pawnedTimes:0);*/

			//列表分家 pawnId的续当所有记录（申请中的和确认的），按create_time降序排列，取第一个即为最新提交的续当申请信息
			PawnContinueExample pawnContinueExample = new PawnContinueExample();
			pawnContinueExample.createCriteria().andPawnIdEqualTo(userPawn.getId());
			pawnContinueExample.setOrderByClause("create_time desc");
			List<PawnContinue> pawnContinueList = selectByExample(pawnContinueExample);
			if (pawnContinueList.size() == 0){ //此pawnid没有续当申请
				//set 典当日期至字段
				repawnMini.setDateRepawnTo(userPawn.getBeginPawnEndTime()!=null? DateUtil.format(userPawn.getBeginPawnEndTime(),"yyyy-MM-dd"):"");
			}else{
				PawnContinue newestOne = pawnContinueList.get(0);//时间倒序取最新一次续当申请
				repawnMini.setProjectCode(newestOne.getProjectCode());
				if (StringUtils.isNotBlank(repawnMini.getProjectCode())){
					PawnTicket pawnTicket = pawnTicketService.getByProjectCode(repawnMini.getProjectCode());
					if (pawnTicket!=null){
						repawnMini.setUserStatus(pawnTicket.getUserStatus());
						repawnMini.setOrgStatus(pawnTicket.getOrgStatus());
						if (pawnTicket.getOrgStatus()!=null && pawnTicket.getOrgStatus()==1 && StringUtils.isNotBlank(newestOne.getContinuePawnTicketCode())){
							repawnMini.setSignButton("1");
						}
						if (pawnTicket.getOrgStatus()!=null && pawnTicket.getOrgStatus()==1 && StringUtils.isBlank(newestOne.getContinuePawnTicketCode())){
							repawnMini.setCompleteTicketButton("1");
						}

					}
				}
				if (newestOne.getState() == 3){ //续当申请中
					//显示确认收款（续当手续费）按钮
					repawnMini.setShowButton("1");
					//最新续当id，set了repawnId后点击进入详情页面与未set的不同
					repawnMini.setRepawnId(newestOne.getId().toString());
					//续当日期至
					repawnMini.setDateRepawnTo(newestOne.getPawnEndTime()!=null?DateUtil.format(newestOne.getPawnEndTime(),"yyyy-MM-dd"):"");
				}else if (newestOne.getState() == 4){//本次续当已被机构端确认
					repawnMini.setRepawnId(newestOne.getId().toString());
					repawnMini.setDateRepawnTo(newestOne.getPawnEndTime()!=null?DateUtil.format(newestOne.getPawnEndTime(),"yyyy-MM-dd"):"");
				}
			}
			if (type==1){
				if ("1".equals(repawnMini.getShowButton()) || "1".equals(repawnMini.getSignButton())
				|| "1".equals(repawnMini.getCompleteTicketButton())){
					retList.add(repawnMini);
				}
			}else {
				retList.add(repawnMini);
			}
		}
		return  retList;
	}
}
