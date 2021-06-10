package com.api.action;

import com.api.MErrorEnum;
import com.api.util.PageLimit;
import com.api.view.home.UserInfo;
import com.api.view.toutiao.AppNewsComment;
import com.api.view.video.AppVideoOnline;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.DateUtil;
import com.base.util.StringUtil;
import com.base.util.StringUtils;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.VideoOnlineCommentEx;
import com.paidang.daoEx.model.VideoOnlineEx;
import com.paidang.service.*;
import com.util.PaidangConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/api/watchVideo", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
@Api(tags = "在线视频")
public class ApiWatchVideo extends ApiBaseController {
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private VideoOnlineService videoOnlineService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private VideoOnlineCommentService videoOnlineCommentService;

    @Autowired
    private ArticleCollectPraiseService articleCollectPraiseService;

    @ApiOperation(value = "视频列表", notes = "分页")
    @RequestMapping("/videoList")
    @ApiMethod(isPage = true, isLogin = false)
    public List<AppVideoOnline> orgList(MobileInfo mobileInfo, PageLimit pageLimit){
//        VideoOnlineExample example = new VideoOnlineExample();
//        example.createCriteria();
//        example.setOrderByClause("create_time desc");
//        PaginationSupport.byPage(pageLimit.getPage(),pageLimit.getLimit(),false);
//
//        List<VideoOnline> list = videoOnlineService.selectByExample(example);

        //视频列表
        startPage();
        List<VideoOnlineEx> list = videoOnlineService.selectByComment(mobileInfo.getUserId(),null,null);

        List<AppVideoOnline> ret = new ArrayList<AppVideoOnline>();
        for(VideoOnlineEx ex : list){
            AppVideoOnline record = new AppVideoOnline();
            record.setId(ex.getId());
            record.setTitle(ex.getTitle());
            record.setImg(ex.getImg());
            record.setPraiseStatus(ex.getPraiseStatus());
            record.setPraiseCount(ex.getPraiseNum());
            record.setVideo(ex.getVideo()+ PaidangConst.VIDEO_NORMAL);
            record.setComCnt(ex.getCommCount());
            record.setLabels(ex.getLabels());
            record.setViewCnt(ex.getViewCnt());
            ret.add(record);
        }
        return ret;
    }

    @ApiOperation(value = "视频评论列表", notes = "分页")
    @RequestMapping("/videoCommentList")
    @ApiMethod(isPage = true, isLogin = false)
    public List<AppNewsComment> orgVideoCommentList(MobileInfo mobileInfo,
                                                 PageLimit pageLimit,
                                                 @ApiParam(value = "视频id",required = true)Integer id){
        //查询待完善
        startPage();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("video_id",id);
        List<VideoOnlineCommentEx> list = videoOnlineCommentService.selectByUser(map);
        List<AppNewsComment> ret = new ArrayList<>();
        for (VideoOnlineCommentEx ex : list) {
            AppNewsComment c = new AppNewsComment();
            UserInfo userRecord = new UserInfo();
            userRecord.setNickName(ex.getNickName());
            userRecord.setHeadImg(ex.getHeadImg());
            c.setId(ex.getId());
            c.setUserInfo(userRecord);
            c.setContent(ex.getContent());
            c.setTime(DateUtil.dateToStr(ex.getCreateTime()));
            ret.add(c);
        }
        return ret;
    }

    @ApiOperation(value = "视频评论数量", notes = "分页")
    @RequestMapping("/videoCommentCnt")
    @ApiMethod()
    public AppNewsComment OrgVideoCommentCnt(MobileInfo mobileInfo,
                                          @ApiParam(value = "视频id",required = true)Integer id){

        VideoOnlineCommentExample example = new VideoOnlineCommentExample();
        example.createCriteria().andVideoIdEqualTo(id);

        Integer count = videoOnlineCommentService.countByExample(example);
        AppNewsComment c = new AppNewsComment();
        c.setReplyCnt(count);
        return c;
    }

    @ApiOperation(value = "视频评论", notes = "登录")
    @RequestMapping("/videoComment")
    @ApiMethod(isLogin = true)
    public Integer OrgVideoComment(MobileInfo mobileInfo,
                            @ApiParam("视频id") Integer id,
                            @ApiParam("评论内容") String content){
        if(id == null){
            throw new ApiException("id");
        }
        if (StringUtil.isBlank(content)){
            throw new ApiException("content");
        }

        VideoOnlineComment comment = new VideoOnlineComment();
        comment.setContent(content);
        comment.setUserId(mobileInfo.getUserId());
        comment.setIsDel(0);
        comment.setVideoId(id);
        comment.setCreateTime(new Date());
        videoOnlineCommentService.insert(comment);
        return 1;
    }

    @ApiOperation(value = "进入视频--领优惠券", notes = "登录")
    @RequestMapping("/watchVideo")
    @ApiMethod(isPage = false, isLogin = true)
    public Integer orgWatchVideo(MobileInfo mobileInfo,
                          @ApiParam(value = "视频id",required = true)Integer id){

        UserCouponExample example = new UserCouponExample();
        example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId()).andCreateTimeBetween(DateUtil.add(new Date(),1),DateUtil.add(new Date(),-1));
        List<UserCoupon> list = userCouponService.selectByExample(example);
        if(list.size()>0){
            //用户已经抢过券了
            throw new ApiException(-1,"领取失败...");
        }

//        CouponExample example2 = new CouponExample();
//        example2.createCriteria();
//        List<Coupon> list2 = couponService.selectByExample(example2);
//        if(list2.size()>1){
//            throw new ApiException(MErrorEnum.COUPON_FAILURE_ERROR);
//        }
//        Coupon coupon = list2.get(0);
//
//
//
//        UserCoupon userCoupon = new UserCoupon();
//        userCoupon.setUserId(mobileInfo.getUserId());
//        userCoupon.setCouponId(coupon.getId());
//        userCoupon.setFull(coupon.getFull());
//        userCoupon.setValue(coupon.getValue());
//        userCoupon.setEndTime(DateUtil.add(new Date(),coupon.getDays()));
//        userCoupon.setState(1);
//        userCoupon.setSource(2);
//        int result = userCouponService.insert(userCoupon);
//        if(result == 0){
//            throw new ApiException(MErrorEnum.OPERATION_FAILURE_ERROR);
//        }

        return 1;
    }

    /**
     * 新闻评论内容封装
     */
    private AppNewsComment getAppNewsComment(VideoOnlineCommentEx ex) {
        AppNewsComment mini = new AppNewsComment();
        mini.setId(ex.getId());
        mini.setContent(ex.getContent());
//        mini.setIsPraise(ex.getIsPraise());
//        mini.setPraiseCnt(ex.getPraiseCnt());
//        mini.setReplyCnt(ex.getReplyCnt());
        mini.setTime(getTime(ex.getCreateTime()));
        UserInfo user = new UserInfo();
        user.setId(ex.getUserId());
        user.setNickName(ex.getNickName());
        user.setHeadImg(ex.getHeadImg());
        mini.setUserInfo(user);
        return mini;
    }

    /**
     * 新闻评论列表封装
     */
    private List<AppNewsComment> getAppNewsComments(List<VideoOnlineCommentEx> list) {
        List<AppNewsComment> ret = new ArrayList<>(list.size());
        for (VideoOnlineCommentEx ex : list) {
            ret.add(getAppNewsComment(ex));
        }
        return ret;
    }

    private String getTime(Date date) {
        return DateUtil.dateAsQQ(date);
    }


    @ApiOperation(value = "点赞视频", notes = "登陆")
    @RequestMapping(value = "/praise",method = {RequestMethod.POST})
    @ApiMethod(isLogin = true)
    public Integer praise(MobileInfo mobileInfo,
                          @RequestParam @ApiParam(value = "0取消点赞，1点赞",required = true) Integer type,
                          @RequestParam @ApiParam(value = "视频id", required = true)Integer id){
        Integer num=0;
        if (type==0){
            ArticleCollectPraiseExample example=new ArticleCollectPraiseExample();
            example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId()).andArticleIdEqualTo(id).andTypeEqualTo(0).andUseTypeEqualTo(2);
            int result =articleCollectPraiseService.deleteByExample(example);
            if (result>0){
                num=-1;
            }
        }else if (type==1){
            ArticleCollectPraiseExample example=new ArticleCollectPraiseExample();
            example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId()).andArticleIdEqualTo(id).andTypeEqualTo(0).andUseTypeEqualTo(2);
            List<ArticleCollectPraise> list = articleCollectPraiseService.selectByExample(example);
            if (CollectionUtils.isEmpty(list)){
                ArticleCollectPraise entity=new ArticleCollectPraise();
                entity.setArticleId(id);
                entity.setUserId(mobileInfo.getUserId());
                entity.setCreateTime(new Date());
                entity.setType(0);
                entity.setStatus(1);
                entity.setUseType(2);
                articleCollectPraiseService.insert(entity);
                num=1;
            }

        }else {
            throw new ApiException(400,"非法参数");
        }
        if (num!=0){
            videoOnlineService.updateCount(id,num,1);
        }
        return num;
    }

    @ApiOperation(value = "观看视频", notes = "登陆")
    @RequestMapping(value = "/watch",method = {RequestMethod.POST})
    @ApiMethod(isLogin = true)
    public Integer watchVideo(MobileInfo mobileInfo,
                          @RequestParam @ApiParam(value = "视频id", required = true)Integer id){
        videoOnlineService.updateCount(id,1,0);
        VideoOnline videoOnline = videoOnlineService.selectByPrimaryKey(id);
        return videoOnline.getViewCnt();
    }


}
