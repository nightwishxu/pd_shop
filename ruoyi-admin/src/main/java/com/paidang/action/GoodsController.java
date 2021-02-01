package com.paidang.action;

import com.base.action.CoreController;
import com.base.api.ApiException;
import com.base.entity.QueryParams;
import com.base.util.BaseUtils;
import com.base.util.DateUtil;
import com.item.dao.model.Admin;
import com.item.service.AdminService;
import com.paidang.dao.model.Goods;
import com.paidang.dao.model.GoodsAuctionOnlineLog;
import com.paidang.dao.model.GoodsExample;
import com.paidang.daoEx.model.GoodsEx;
import com.paidang.service.GoodsAuctionOnlineLogService;
import com.paidang.service.GoodsService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
@author sun
*/
@RequestMapping("goods")
@RestController
public class GoodsController extends CoreController{

    @Autowired
    private GoodsService goodsService;
	@Autowired
	private AdminService adminService;


	@Autowired
	private GoodsAuctionOnlineLogService goodsAuctionOnlineLogService;

//    @RequestMapping("/serviceList")
//	@ResponseBody
//	public Ret serviceList(Integer page, Integer rows,String name,Integer type,Integer source){
//		QueryParams.Builder builder = QueryParams.newBuilder();
//    	Integer id = UserUtils.getDefaultPrincipal().getId();
//		builder.put("source",source);
//		builder.put("type",type);
//    	builder.put("name",name);
//    	builder.put("orgId",id);
//
//		PaginationSupport.byPage(page, rows);
//		List<GoodsEx> list = goodsService.selectGoodsList(builder);
//		return page(list);
//	}

	@RequestMapping("/list")
	@ResponseBody
	public TableDataInfo serviceAdminList(Integer page, Integer rows, String name, Integer type, Integer source){
		QueryParams.Builder builder = QueryParams.newBuilder();
		builder.put("source",source);
		builder.put("type",type);
		builder.put("name",name);

		startPage();
		List<GoodsEx> list = goodsService.selectGoodsList(builder);
		return page(list);
	}

//	@RequestMapping("/exportServiceAdminList")
//	public void exportServiceAdminList(String name, Integer type, Integer source, HttpServletResponse resp){
//		QueryParams.Builder builder = QueryParams.newBuilder();
//		builder.put("source",source);
//		builder.put("type",type);
//		builder.put("name",name);
//		List<GoodsEx> list = goodsService.selectGoodsList(builder);
//		ExcelExporter excelExporter=new ExcelExporter();
//		ExcelColumns[] headers ={new ExcelColumns("name","名称"),new ExcelColumns("cost","出售价格")};
//		excelExporter.createWork(list,headers);
//		excelExporter.export(resp, DateUtil.dateToStr(new Date(), DateUtil.YYSSSS));
//	}

	@RequestMapping("/save")
	@ResponseBody
	public Ret save(@RequestBody Goods goods){
		if (goods.getCateCode()!=null && (goods.getCateCode()==4 || goods.getCateCode()==6)){
			goods.setCateCode(Integer.parseInt(goods.getCateCodeSon()));
			goods.setCateCodeSon(null);
		}
//		goods.setImgs(BaseUtils.removeUrl(goods.getImgs()));
//		goods.setImg(BaseUtils.removeUrl(goods.getImg()));
//		goods.setBannerVideo(BaseUtils.removeUrl(goods.getBannerVideo()));
//		goods.setBannerVideoFace(BaseUtils.removeUrl(goods.getBannerVideoFace()));
		if (goods.getDealType()!=null && goods.getDealType()==2 && goods.getTotal()!=1){
			throw new ApiException(400,"竞拍商品数量只能为1");
		}
		if (goods.getId() == null){
			if (goods.getSoldOut()==null){
				goods.setSoldOut(0);

			}
			goods.setState(1);//(针对竞拍)- 0已失效 1有效；现后台只能上传3万以下物品，且不是拍卖，是直接买卖
			goods.setSoldOut(0);//已售
			goods.setIsOnline(0);
			if (goods.getIsVerfiy() ==null){
				goods.setIsVerfiy(2);
			}
			goods.setCreateTime(new Date());
			goodsService.insert(goods);
//			if (goods.getDealType()!=null && goods.getDealType() == 2){
//				//保存竞拍日志
//				GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
//				log.setAuctionEndTime(goods.getAuctionEndTime());
//				log.setAuctionStartTime(goods.getAuctionStartTime());
//				log.setGoodsId(goods.getId());
//				log.setStatus(0);
//				log.setCreateTime(new Date());
//				goodsAuctionOnlineLogService.insertSelective(log);
//				Goods tmp = new Goods();
//				tmp.setId(goods.getId());
//				tmp.setAuctionOnlineLogId(log.getId());
//				goodsService.updateByPrimaryKeySelective(tmp);
//			}
		}else{
			Date date = new Date();

			Goods goods1 = goodsService.selectByPrimaryKey(goods.getId());
			if (goods1.getIsOnline()==1 ){
				throw new ApiException(400,"请先下架再修改商品信息");
			}
//			if (goods1.getDealType()==2  && date.compareTo(goods1.getAuctionStartTime())>=0 && date.compareTo(goods1.getAuctionEndTime())<=0){
//				throw new ApiException(400,"竞拍中禁止修改商品信息");
//			}
//			if (goods.getDealType()!=null && goods.getDealType()==2){
//				if(date.compareTo(goods.getAuctionStartTime())<0){
//					boolean flag = false;
//					if (goods.getAuctionStartTime()!=null && goods1.getAuctionStartTime().compareTo(goods.getAuctionStartTime())!=0){
//						flag = true;
//					}
//					if (goods.getAuctionEndTime()!=null && goods1.getAuctionEndTime().compareTo(goods.getAuctionEndTime())!=0){
//						flag = true;
//					}
//					//修改上架记录表
//					if (flag){
//						GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
//						log.setId(goods1.getAuctionOnlineLogId());
//						log.setAuctionStartTime(goods.getAuctionStartTime());
//						log.setAuctionEndTime(goods.getAuctionEndTime());
//						log.setModifyTime(date);
//						goodsAuctionOnlineLogService.updateByPrimaryKeySelective(log);
//					}
//				}else if (date.compareTo(goods.getAuctionEndTime())>0){
//					GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
//					log.setAuctionEndTime(goods.getAuctionEndTime());
//					log.setAuctionStartTime(goods.getAuctionStartTime());
//					log.setGoodsId(goods.getId());
//					log.setStatus(1);
//					log.setCreateTime(date);
//					goodsAuctionOnlineLogService.insertSelective(log);
//					goods.setAuctionOnlineLogId(log.getId());
//				}
//
//			}
			goodsService.updateByPrimaryKeySelective(goods);
		}
		return ok();
	}




	@RequestMapping("/dismount")
	@ResponseBody
	public int goodsDismount(Integer id, String reasonOfDismounting){
		//判断当前商品是否是下架状态
		Goods goods = goodsService.selectByPrimaryKey(id);
		Date date = new Date();
		if (goods.getDealType()==2 && date.compareTo(goods.getAuctionStartTime())>=0 && date.compareTo(goods.getAuctionEndTime())<=0){
			throw new ApiException(400,"竞拍中禁止下架");
		}


		if(goods.getIsOnline()==0||goods.getIsOnline()==2){
			throw new ApiException(400,"商品已经处于下架状态或者还是待上架状态");
		}
		if(goods.getIsOnline()==-1){
			throw new ApiException(400,"商品已经已被删除");
		}
		//修改上架信息
		if (goods.getDealType()==2){
			GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
			log.setId(goods.getAuctionOnlineLogId());
			log.setStatus(0);
			log.setModifyTime(date);
			log.setModifyAccount(null);
			goodsAuctionOnlineLogService.updateByPrimaryKeySelective(log);
		}
		Goods updateGoods = new Goods();
		updateGoods.setId(id);
		updateGoods.setIsOnline(0);
		updateGoods.setReasonOfDismounting(reasonOfDismounting);
		goodsService.updateByPrimaryKeySelective(updateGoods);
		return 1;
	}


	@RequestMapping("/online")
	@ResponseBody
	public Integer goodsonline(Integer id){
		//判断当前商品是否是下架状态
		Goods goods = goodsService.selectByPrimaryKey(id);
//		Integer orgId = userService.getOrgIdByUserId(mobileInfo.getUserId());
//		if (!Objects.equals(orgId,goods.getOrgId())){
//			throw new ApiException(400,"机构异常");
//		}
		if(goods.getIsVerfiy()!=2){
			throw new ApiException(400,"商品未审核通过无法上架");
		}
		if(goods.getIsOnline()==-1){
			throw new ApiException(40006,"商品已经已被删除");
		}
		Date date = new Date();
		if (goods.getDealType()==2 && date.compareTo(goods.getAuctionStartTime())>=0){
			throw new ApiException(400,"已过竞拍开始时间，无法上架");
		}
		if (goods.getDealType()==1 && goods.getTotal()<=0){
			throw new ApiException(400,"库存不足,无法上架");
		}
		if (goods.getDealType()==2 && goods.getTotal()<=0){
			goods.setTotal(1);
		}
		goods.setIsOnline(1);
		goods.setMaxAutionId(null);
		goods.setMaxAuction(null);
		goods.setOnlineTime(new Date());
		goods.setReasonOfDismounting("");
		if (goods.getDealType()==2){
			GoodsAuctionOnlineLog log = new GoodsAuctionOnlineLog();
			log.setAuctionEndTime(goods.getAuctionEndTime());
			log.setAuctionStartTime(goods.getAuctionStartTime());
			log.setGoodsId(goods.getId());
			log.setStatus(1);
			log.setCreateTime(date);
			goodsAuctionOnlineLogService.insertSelective(log);
			goods.setAuctionOnlineLogId(log.getId());
		}
		goodsService.sellAuctionGoods(goods);
		return 1;
	}










	@RequestMapping("/findByType")
	@ResponseBody
	public Ret findBySource(Integer type,Integer code){
		QueryParams.Builder builder = QueryParams.newBuilder();
		builder.put("type",type);
		builder.put("cateCode",code);
		List<GoodsEx> list = goodsService.selectGoodsList(builder);
		return ok(list);
	}
//	@RequestMapping("/serviceSave")
//	@ResponseBody
//	public Ret serviceSave(Goods goods,Integer cateCode,Integer type,Integer source){
//		if (goods.getPrice() == null){
//			return msg(-1,"请输入出售价格");
//		}
//		/*if (goods.getPrice().compareTo(PaidangConst.BOUNDARY_PRICE) > 0){
//			return msg(-1,"您上传的物品鉴定价格不能超过"+PaidangConst.BOUNDARY_PRICE.toString());
//		}*/
//		int w;
//		int h;
//		if (StringUtils.isEmpty(goods.getWidth()) && StringUtils.isEmpty(goods.getHeight())){
//			return msg(-1,"请输入封面宽高");
//		}
//		try{
//			w = Integer.valueOf(goods.getWidth());
//			h = Integer.valueOf(goods.getHeight());
//		}catch (NumberFormatException e){
//			return msg(-1,"您输入的数字不正确");
//		}
//		int gy = gongyue(w,h);
//		if (goods.getId() == null){
//			goods.getImg();
//			goods.setIsOnline(1);//1上架0下架
//			goods.setIsVerfiy(2);//1审核中2通过3不通过
//			goods.setSource(source);//1平台2机构3服务商
//			goods.setState(1);//(针对竞拍)- 0已失效 1有效；现后台只能上传3万以下物品，且不是拍卖，是直接买卖
//			goods.setSoldOut(0);//已售
//			if (goods.getTotal()==null){
//				goods.setTotal(1);//库存
//			}
//			goods.setCost(goods.getPrice());
//			goods.setType(type);//1新品2绝当品
//			goods.setCateCode(cateCode);
//
//			Admin admin = adminService.selectByPrimaryKey(UserUtils.getDefaultPrincipal().getId());
//			if(null != admin){
//				if("admin".equals(admin.getRoleCode()) || "authAdmin".equals(admin.getRoleCode())){
//					goods.setOrgId(0);
//				}
//			}else{
//				goods.setOrgId(UserUtils.getDefaultPrincipal().getId());
//			}
//
//
//			//
//			goods.setWidth(w/gy+"");
//			goods.setHeight(h/gy+"");
//			goodsService.insert(goods);
//		}else{
//			if(goods.getTotal()>0){
//				goods.setIsOnline(1);
//				goods.setState(1);//1上架0下架
//			}
//            if(goods.getTotal()<=0){
//                goods.setIsOnline(0);//1上架0下架
//				goods.setState(-1);
//            }
//			goodsService.updateByPrimaryKeySelective(goods);
//		}
//		return ok();
//	}

	//求最大公约数
	public static int gongyue(int a,int b)
	{
		int gongyue=0;
		if(a<b)
		{   //交换a、b的值
			a=a+b;
			b=a-b;
			a=a-b;
		}
		if(a%b==0)
		{
			gongyue = b;
		}
		while(a % b>0)
		{
			a=a%b;
			if(a<b)
			{
				a=a+b;
				b=a-b;
				a=a-b;
			}
			if(a%b==0)
			{
				gongyue = b;
			}
		}
		return gongyue;
	}

	@RequestMapping("/changeState")
	@ResponseBody
		public Ret changeState(Integer id,Integer v,GoodsEx goods){
			goods.setId(id);
			goods.setIsVerfiy(v);
			if(goods.getIsVerfiy() != 3){
				goodsService.changeStateByPrimaryKey(goods);
			}else{
				goods.setIsVerfiy(1);
				goodsService.changeStateByPrimaryKey(goods);
			}
		return ok();
	}

	@RequestMapping("/soldOut")
	@ResponseBody
	public Ret soldOut(Integer id){
			if(id != null){
				Goods goods = goodsService.selectByPrimaryKey(id);
				goods.setState(0);
				goodsService.updateCreatTime(goods);
			}
		return ok();
	}
    
    @GetMapping("/{id}")
	@ResponseBody 
    public Ret find(@PathVariable Integer id){
    	GoodsEx goods = goodsService.selectByPrimaryId(id);
       	return ok(goods);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		goodsService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


	@RequestMapping("/getOrgDeadPawnStoreList")
	@ResponseBody
	public Ret getOrgDeadPawnStoreList(){
    	String orgId = getSessionParameter("id");
		GoodsExample example = new GoodsExample();
		example.createCriteria().andOrgIdEqualTo(Integer.valueOf(orgId)).andSourceEqualTo(2);
		List<Goods> list = goodsService.selectByExample(example);
		return ok(list);
	}

	@RequestMapping("/saveOrgGoods")
	@ResponseBody
	public Ret saveOrgGoods(Goods goods){
		String orgId = getSessionParameter("id");
		if(goods.getId() == null){
			goods.setSource(2);//1平台2机构3服务商
			goods.setType(1);//1新品2绝当品
//			goods.setOrgId(Integer.valueOf(orgId));
			goods.setSoldOut(0);//已售
			goods.setIsOnline(1);//1上架0下架
			goods.setIsVerfiy(2);//1审核中2通过3不通过
			goodsService.insert(goods);
		}else{
			goodsService.updateByPrimaryKey(goods);
		}
		return ok();
	}

}