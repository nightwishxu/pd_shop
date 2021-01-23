package com.api.action;

import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.MobileInfo;
import com.base.util.BaseUtils;
import com.base.util.CoreConstants;
import com.base.util.JSONUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.item.daoEx.model.AdEx;
import com.item.service.AdService;
import com.paidang.dao.model.BFileExample;
import com.paidang.dao.model.PawnOrg;
import com.paidang.dao.model.VideoOnline;
import com.paidang.dao.model.VideoOnlineExample;
import com.paidang.service.BFileService;
import com.paidang.service.PawnOrgService;
import com.paidang.service.VideoOnlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author: xww
 * @create: 2020-11-08 16:38
 * @description: 广告位
 * @modified By:
 * @version:
 **/
@RestController
@RequestMapping(value = "/api/ad", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
@Api(tags = "广告位")
public class ApiAdController  extends ApiBaseController {

    @Autowired
    private AdService adService;

    @Autowired
    private VideoOnlineService videoOnlineService;

    @Autowired
    private BFileService fileService;

    @Autowired
    private PawnOrgService pawnOrgService;

    @ApiOperation(value = "广告列表", notes = "广告列表")
    @RequestMapping("/list")
    @ApiMethod()
    public List<AdEx> adList(MobileInfo mobileInfo,
                             @ApiParam(value="广告展示位置 多种类型以,分割 : " +
                                     "1首页轮播图；2认证商城轮播图；3绝当商城轮播图; 4拍档头条 5关键词搜索" +
                                     "6 首页，7c端个人中心，8商家入驻引导页，9机构端个人中心，10商家端个人中心",required = true) String locations){
        BaseUtils.checkBlankParam(locations);
        String[] split = locations.split(",");
        List<Integer> list = Lists.newArrayList();
        for (String s : split) {
            list.add(Integer.parseInt(s));
        }
        List<AdEx> adExes = adService.findList(list);
        for (AdEx ex : adExes) {
            if(5 == ex.getType()){
                VideoOnlineExample example = new VideoOnlineExample();
                example.createCriteria().andIdEqualTo(Integer.parseInt(ex.getContent()));
                String video= videoOnlineService.selectByExample(example).get(0).getVideo();
                BFileExample example1 = new BFileExample();
                example1.createCriteria().andFileIdEqualTo(video);
                String file_path = fileService.selectByExample(example1).get(0).getFilePath();
                ex.setContent(CoreConstants.getProperty("video.path")+file_path);
            }else if(6 == ex.getType()){
                PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(Integer.parseInt(ex.getContent()));
                ex.setContent(JSONUtils.serialize(pawnOrg));
            }else if (4 == ex.getType()){
                //如果是绝当商品的商品
                if(null == ex.getGoodsId()){
                    //如果是后台上传的,则不区分30000
                    ex.setCstate(2);
                }else{
                    if(new BigDecimal(ex.getCost()).compareTo(new BigDecimal(30000) )== -1){
                        //走流程小于三万则是普通商品
                        ex.setCstate(2);
                    }else{
                        ex.setCstate(1);
                    }
                }
            }

        }

        return adExes;
    }
}
