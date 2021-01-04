package com.item.action;

import com.base.action.CoreController;
import com.base.entity.QueryParams;
import com.base.util.BaseUtils;
import com.base.util.StringUtil;
import com.item.dao.model.Ad;
import com.item.dao.model.AdExample;
import com.item.service.AdService;
import com.paidang.dao.model.PawnOrg;
import com.paidang.dao.model.PawnOrgExample;
import com.paidang.dao.model.VideoOnline;
import com.paidang.dao.model.VideoOnlineExample;
import com.paidang.daoEx.model.GoodsEx;
import com.paidang.service.GoodsService;
import com.paidang.service.PawnOrgService;
import com.paidang.service.VideoOnlineService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
@author sun
*/
@RequestMapping("ad")
@Controller
public class AdController extends CoreController{

    @Autowired
    private AdService adService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private VideoOnlineService videoOnlineService;
	@Autowired
	private PawnOrgService pawnOrgService;



	public enum MStoreGoodsCateList {
		zb("1","钟表","0"),
		fc("2","翡翠","0"),
		hty("3","和田玉","0"),
		gdysp("4","古董艺术品","0"),
		sh("5","书画","0"),
		cszb("6","彩色珠宝","0"),
		zs("7","钻石","0"),
		other("8","更多","0"),

		mqyt("9","明清砚台","4"),
		ww("10","文玩","4"),
		zx("11","杂项","4"),
		hlbs("12","红蓝宝石","6"),
		zml("13","祖母绿","6"),
		zz("14","珍珠","6"),
		bx("15","碧玺","6");

		private String code;
		private String name;
		private String fid;
		private MStoreGoodsCateList(String code,String name,String fid) {
			this.code = code;
			this.name = name;
			this.fid = fid;
		}

		public static String getName(String code) {
			for (MStoreGoodsCateList c : MStoreGoodsCateList.values()) {
				if (c.code.equals(code)) {
					return c.name;
				}
			}
			return null;
		}
	}

    @RequestMapping("/list")
	@ResponseBody
    public TableDataInfo list(Integer location){
		startPage();
    	AdExample example = new AdExample();
		AdExample.Criteria criteria = example.createCriteria();
		if (location!=null){
			criteria.andLocationEqualTo(location);
		}

		example.setOrderByClause("create_time desc");

    	List<Ad> list = adService.selectByExampleWithBLOBs(example);
    	list.stream().forEach(ad->ad.setImg(BaseUtils.processImg(ad.getImg())));
      	return page(list);
    }

    @RequestMapping("/save")
	@ResponseBody
    public Ret save(Ad ad){
		if (StringUtils.isNotBlank(ad.getImg())){
			ad.setImg(BaseUtils.removeUrl(ad.getImg()));
		}
    	if (ad.getId() == null){
    		if (ad.getLocation() != 4) {
    			ad.setDiscription(null);
			}
			if (ad.getType() == 0) {//不跳转
				//ad.setContent(null);
			}
			if (ad.getType() == 1) {//网址

			}
			if (ad.getType() == 3) {//认证商城商品详情页

			}
			if (ad.getType() == 4) {//绝当商城商品详情页

			}
			if (ad.getType() == 5) {//视频详情页

			}
			if (ad.getSortOrder() == null) {
				ad.setSortOrder(0);
			}
			ad.setCreateTime(new Date());
    		adService.insert(ad);
    	}else{
    		adService.updateByPrimaryKeySelective(ad);
    	}
       	return ok();
    }

    @RequestMapping("/findById")
	@ResponseBody
    public Ret find(Integer id){
    	Ad ad = adService.selectByPrimaryKey(id);
        if (StringUtils.isNotBlank(ad.getImg())){
            ad.setImg(BaseUtils.processImg(ad.getImg()));
        }
       	return ok(ad);
    }

    @RequestMapping("/del")
	@ResponseBody
    public Ret del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		adService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }

	@RequestMapping("/goodsList")
	@ResponseBody
	public Ret goodsList(String q, Integer type){
		QueryParams.Builder builder = QueryParams.newBuilder();
		builder.put("name", q);
		builder.put("type", type);
		List<GoodsEx> list = goodsService.selectGoodsList(builder);
		List res = new ArrayList<>();
		for(GoodsEx item : list) {
		    Map<String,String> map = new HashMap<>();
//			item.setName("【" + MStoreGoodsCateList.getName(item.getCateCode().toString()) + "】" + item.getName());
//			res.add(item);
			map.put("id",item.getId().toString());
			map.put("name","【" + MStoreGoodsCateList.getName(item.getCateCode().toString()) + "】" + item.getName());
            res.add(map);
		}
		return ok(res);
	}

	@RequestMapping("/videoList")
	@ResponseBody
	public Ret videoList(String q){
		VideoOnlineExample example = new VideoOnlineExample();
		if (q != null && q != "") {
			example.createCriteria().andTitleLike(q);
		}
		example.setOrderByClause("create_time desc");
		List<VideoOnline> list = videoOnlineService.selectByExample(example);
		return ok(list);
	}

	@RequestMapping("/dpList")
	@ResponseBody
	public Ret dpList(String q){
		PawnOrgExample pawnOrgExample = new PawnOrgExample();
		pawnOrgExample.createCriteria().andIdNotEqualTo(1);//店铺不能是保管仓库
		List<PawnOrg> pawnOrgs= pawnOrgService.selectByExample(pawnOrgExample);
		List res = new ArrayList();
        for (PawnOrg org : pawnOrgs) {
            Map map = new HashMap();
            map.put("id",org.getId().toString());
            map.put("name",org.getName());
            res.add(map);
        }

        return ok(res);
	}

}
