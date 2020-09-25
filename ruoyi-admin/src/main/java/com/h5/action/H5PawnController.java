package com.h5.action;

import com.base.util.JSONUtils;
import com.paidang.dao.model.Goods;
import com.paidang.dao.model.PawnOrg;
import com.paidang.dao.model.PawnOrgExample;
import com.paidang.service.GoodsService;
import com.paidang.service.PawnOrgService;
import com.ruoyi.common.core.domain.Ret;
import com.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/p/pawn")
@Controller
public class H5PawnController extends H5BaseController{

    @Autowired
    private PawnOrgService pawnOrgService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return BASE_PATH+"/pawn/login";
    }

    /**
     * 商品页面
     */
    @RequestMapping("/shop")
    public String shop(Integer id, Integer type, ModelMap map){
        map.put("id", id);
        map.put("type", type);
        return BASE_PATH+"/pawn/shop";
    }

    /**
     * 账号密码登录
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public String checkLogin(String phone, String pwd){
        PawnOrgExample pawnOrgExample = new PawnOrgExample();
        pawnOrgExample.or().andAccountEqualTo(phone).andTypeEqualTo(1); //典当机构
        pawnOrgExample.or().andAccountEqualTo(phone).andTypeEqualTo(3); //供应商机构
        List<PawnOrg> list = pawnOrgService.selectByExample(pawnOrgExample);

        Map<String, Object> map = new HashMap<>();

        if(list.size() < 0){
            map.put("errorCode", 300);
            map.put("errorMsg", "账号不存在");
        }else{
            PawnOrg pawnOrg = list.get(0);
            if(!pawnOrg.getPassword().equals(MD5Util.MD5Encode(pwd,"UTF-8"))){
                map.put("errorCode", 301);
                map.put("errorMsg", "密码不正确");
            }else{
                map.put("errorCode", 200);
                map.put("errorMsg", "");
                map.put("data", pawnOrg);
            }
        }
        return JSONUtils.serialize(map);
    }

    /*
    * 上传商品
    */
    @ResponseBody
    @RequestMapping("/save")
    public String checkEx(Integer source, Integer type, Integer cateCode, Integer orgId, String name,
                          BigDecimal cost, BigDecimal price, Integer total, Integer sort, String imgs, String img,
                          String bannerVideoFace, String bannerVideo, String ccLength, String ccWidth, String ccHeight,
                          String weight, String material, String createYear,String spec, String info){

//        int w;
//        int h;
//        if (StringUtils.isEmpty(goods.getWidth()) && StringUtils.isEmpty(goods.getHeight())){
//            return msg(-1,"请输入封面宽高");
//        }
//        try{
//            w = Integer.valueOf(goods.getWidth());
//            h = Integer.valueOf(goods.getHeight());
//        }catch (NumberFormatException e){
//            return msg(-1,"您输入的数字不正确");
//        }
//        int gy = gongyue(w,h);

        Goods goods = new Goods();
        goods.setType(type);
        goods.setSource(source);
        goods.setCateCode(cateCode);
        goods.setName(name);
//        goods.setCost(cost);
        goods.setCost(price);
        goods.setOrgId(orgId);
        goods.setPrice(price);
        goods.setSortOrder(sort);
        goods.setTotal(total == null ? 1: total);
        goods.setImgs(imgs);
        goods.setImg(img);
        goods.setBannerVideoFace(bannerVideoFace);
        goods.setBannerVideo(bannerVideo);
        goods.setCcLength(ccLength);
        goods.setCcWidth(ccWidth);
        goods.setCcHeight(ccHeight);
        goods.setWeight(weight);
        goods.setMaterial(material);
        goods.setCreateYear(createYear);
        goods.setSpec(spec);
        goods.setInfo(info);
//        goods.setWidth(w/gy+"");
//        goods.setHeight(h/gy+"");
        goods.setIsOnline(1);//1上架0下架
        goods.setIsVerfiy(2);//1审核中2通过3不通过
        goods.setState(1);//(针对竞拍)- 0已失效 1有效；现后台只能上传3万以下物品，且不是拍卖，是直接买卖
        goods.setSoldOut(0);//已售

       int cnt = goodsService.insert(goods);
        if(cnt > 0){
            return JSONUtils.serialize(new Ret(1));
        }else{
            return JSONUtils.serialize(new Ret(0));
        }
    }

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

}
