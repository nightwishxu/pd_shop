package com.api.action;


import com.api.util.PageLimit;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.dao.model.Result;
import com.base.util.StringUtils;
import com.demo.constant.HttpConnector;
import com.item.daoEx.model.UserEx;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.ArticleCommentEx;
import com.paidang.daoEx.model.ArticleEx;
import com.paidang.service.*;
import com.ruoyi.common.core.page.PageDomain;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/api/article")
@Api(tags = "ArticleController", description = "动态相关")
public class ApiArticleController extends ApiBaseController {

    @Autowired
    private ArticleService articleService;


    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private UserReportService userReportService;

    @Autowired
    private UserFollowService userFollowService;

    @Autowired
    private ArticleCollectPraiseService articleCollectPraiseService;

//    @Autowired
//    private WxMemberInfoService wxMemberInfoService;

    @Autowired
    private UserBlackService userBlackService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增动态", notes = "")
    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    @ApiMethod(isLogin = true)
    public Integer add(MobileInfo mobileInfo,
                            @RequestParam @ApiParam(value = "内容", required = true) String content,
                            @RequestParam @ApiParam(value = "1 用户帖子 3用户视频", required = true) Integer type,
                            @RequestParam(required = false) @ApiParam(value = "图片", required = false) String imgs,
                            @RequestParam(required = false) @ApiParam(value = "视频", required = false) String video,
//                            @RequestParam @ApiParam(value = "标签信息", required = false)String labels,
                            @RequestParam @ApiParam(value = "状态：2发布", required = true) Integer status){

        if (StringUtils.isBlank(content)){
            throw new ApiException(400,"动态内容不能为空");
        }
        if (content.length()>500){
            throw new ApiException(400,"动态内容长度不能超过500");
        }

        Date date = new Date();
        Article article=new Article();
        article.setUserId(mobileInfo.getUserId());
        article.setStatus(status);
        article.setCollectCount(0);
        article.setCommentCount(0);
        article.setClickCount(0);
        article.setIsShow(1);
        article.setReportCount(0);
        article.setPraiseCount(0);
        article.setContent(StringUtils.unescapeXss(content));
        article.setImgs(StringUtils.unescapeXss(imgs));
        article.setType(type);
        article.setVideo(video);
//        article.setLabels(labels);
        article.setCreateTime(date);
        article.setReleaseTime(date);
        article.setCreateName(String.valueOf(mobileInfo.getUserId()));
        int num=articleService.insert(article);
        if (num>0){
            userService.updateUserCount(mobileInfo.getUserId(),1);
        }
//        return CommonResult.success(num);
        return num;
    }

    @ApiOperation(value = "编辑动态", notes = "")
    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ApiMethod(isLogin = true)
    public Integer edit(MobileInfo mobileInfo,
                        @RequestParam(required = false) @ApiParam(value = "内容", required = false)String content,
                        @RequestParam(required = false) @ApiParam(value = "图片", required = false)String imgs,
                        @RequestParam(required = false) @ApiParam(value = "视频", required = false)String video,
//                        @RequestParam @ApiParam(value = "标签信息", required = false)String labels,
                        @RequestParam @ApiParam(value = "状态：2发布", required = true)Integer status,
                        @RequestParam @ApiParam(value = "动态id", required = true) Integer id){
        Article article=articleService.selectByPrimaryKey(id);
        article.setContent(StringUtils.unescapeXss(content));
        article.setImgs(StringUtils.unescapeXss(imgs));
//        article.setLabels(labels);
        article.setStatus(status);
        article.setUpdateTime(new Date());
        article.setVideo(video);
//        wxMemberInfoService.updateArticleCount(mobileInfo.getMemberid());
        return articleService.updateByPrimaryKeyWithBLOBs(article);
    }

//    @ApiOperation(value = "收藏动态", notes = "")
//    @RequestMapping(value = "/collect",method = {RequestMethod.POST})
//    @ApiMethod(isLogin = true)
//    public CommonResult collect(MobileInfo mobileInfo,
//                        @RequestParam @ApiParam(value = "0取消收藏，1收藏",required = true) Integer type,
//                        @RequestParam @ApiParam(value = "用户头像",required = true) String icon,
//                        @RequestParam @ApiParam(value = "昵称",required = true) String nickname,
//                        @RequestParam(required = false) @ApiParam(value = "动态图片",required = false) String img,
//                           @RequestParam @ApiParam(value = "动态作者id",required = true) Long authorId,
//                        @RequestParam @ApiParam(value = "动态id", required = true)Long id){
//        Integer num=0;
//        if (type==0){
//            num=-1;
//            CollectPraiseExample example=new CollectPraiseExample();
//            example.createCriteria().andMemberIdEqualTo(mobileInfo.getMemberId()).andArticleIdEqualTo(id).andTypeEqualTo(1);
//            collectPraiseService.deleteByExample(example);
//        }else if (type==1){
////            if (memberBlackService.isBlackMember(mobileInfo.getMemberId(),authorId)>0){
////                throw new ApiException("已被拉黑无法收藏动态");
////            }
//            num=1;
//            CollectPraise entity=new CollectPraise();
//            entity.setArticleId(id);
//            entity.setMemberId(mobileInfo.getMemberId());
//            entity.setCreateTime(new Date());
//            entity.setType(1);
//            entity.setIcon(icon);
//            entity.setNickname(nickname);
//            entity.setImg(img);
//            entity.setAuthorId(authorId);
//            collectPraiseService.insert(entity);
//        }else {
//            throw new ApiException(400,"非法参数");
//        }
//        return CommonResult.success(articleService.updateArticleCount(id,num,1));
//    }

    @ApiOperation(value = "点赞动态", notes = "登陆")
    @RequestMapping(value = "/praise",method = {RequestMethod.POST})
    @ApiMethod(isLogin = true)
    public Integer praise(MobileInfo mobileInfo,
                           @RequestParam @ApiParam(value = "0取消点赞，1点赞",required = true) Integer type,
                          @RequestParam(required = false) @ApiParam(value = "用户头像",required = false) String icon,
                          @RequestParam @ApiParam(value = "昵称",required = true) String nickName,
                          @RequestParam(required = false) @ApiParam(value = "动态图片",required = false) String img,
                          @RequestParam @ApiParam(value = "动态作者id",required = true) Integer authorId,
                           @RequestParam @ApiParam(value = "动态id", required = true)Integer id){
        Integer num=0;
        if (type==0){
            ArticleCollectPraiseExample example=new ArticleCollectPraiseExample();
            example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId()).andArticleIdEqualTo(id).andTypeEqualTo(0).andUseTypeEqualTo(1);
            int result =articleCollectPraiseService.deleteByExample(example);
            if (result>0){
                num=-1;
            }
        }else if (type==1){
            ArticleCollectPraiseExample example=new ArticleCollectPraiseExample();
            example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId()).andArticleIdEqualTo(id).andTypeEqualTo(0).andUseTypeEqualTo(1);
            List<ArticleCollectPraise> list = articleCollectPraiseService.selectByExample(example);
            if (CollectionUtils.isEmpty(list)){
                ArticleCollectPraise entity=new ArticleCollectPraise();
                entity.setArticleId(id);
                entity.setUserId(mobileInfo.getUserId());
                entity.setCreateTime(new Date());
                entity.setType(0);
                entity.setIcon(StringUtils.unescapeXss(icon));
                entity.setStatus(1);
                entity.setNickName(nickName);
                entity.setImg(StringUtils.unescapeXss(img));
                entity.setAuthorId(authorId);
                entity.setUseType(1);
                articleCollectPraiseService.insert(entity);
                num=1;
            }

        }else {
            throw new ApiException(400,"非法参数");
        }
        if (num!=0){
            articleService.updateArticleCount(id,num,2);
        }
        return num;
    }





//    @ApiOperation(value = "动态评价列表", notes = "分页")
//    @RequestMapping(value = "/comment/list",method = {RequestMethod.POST})
//    @ApiMethod(isLogin = false)
//    public CommonResult commentList(@RequestParam @ApiParam(value = "动态id", required = true)Long id){
//        List<CommentEx> list = commentService.findList(id);
//        return CommonResult.success(list);
//    }


    @ApiOperation(value = "动态明细", notes = "")
    @RequestMapping(value = "/detail",method = {RequestMethod.POST})
    @ApiMethod(isLogin = false)
    public ArticleEx detail(MobileInfo mobileInfo,@RequestParam @ApiParam(value = "动态id", required = true)Integer id){
        //更新点击数
        articleService.updateArticleCount(id,1,3);
        ArticleEx ex = new ArticleEx();
        ex.setId(id);
        ex.setUserId(mobileInfo.getUserId());
        List<ArticleEx> list = articleService.findList(ex);
        if (CollectionUtils.isEmpty(list)){
            throw new ApiException(400,"未找到相关内容");
        }
        ArticleEx articleEx = list.get(0);
        //评论列表
//        List<CommentEx> commentExes = commentService.findList(id,mobileInfo==null?null:mobileInfo.getMemberId());
//        articleEx.setCommentList(commentExes);
        //精彩评论
        List<ArticleCommentEx> wonderfuls= articleCommentService.findWonderfulCommentList(id, 10,10,mobileInfo==null?null:mobileInfo.getUserId());
        articleEx.setWonderfulCommentList(wonderfuls);
        //最新点赞用户头像
        List<String> icons = articleCollectPraiseService.lastPraiseIcon(id);
        articleEx.setLastPraiseIcon(icons);
        return articleEx;
    }

    @ApiOperation(value = "最新动态列表", notes = "分页")
    @RequestMapping(value = "/list",method = {RequestMethod.POST})
    @ApiMethod(isLogin = false,isPage = true)
    public List<ArticleEx> list(MobileInfo mobileInfo,
                                @ApiParam(value = "类型 1用户帖子 2后台帖子 3用户视频", required = true)Integer type
        , PageDomain page){
//        startPage();
        ArticleEx ex = new ArticleEx();
        ex.setIsShow(1);
        ex.setStatus(2);
        ex.setType(type);
        ex.setUserId(mobileInfo.getUserId());
        List<ArticleEx> list = articleService.findList(ex);
        return list;
    }


    @ApiOperation(value = "举报动态", notes = "登陆")
    @RequestMapping(value = "/report",method = {RequestMethod.POST})
    @ApiMethod(isLogin = true)
    public Integer report(MobileInfo mobileInfo,@RequestParam @ApiParam(value = "动态id", required = true)Integer articleId,
    @RequestParam @ApiParam(value = "举报信息", required = true)String info,
//    @RequestParam @ApiParam(value = "图片", required = true)String imgs,
    @RequestParam @ApiParam(value = "昵称", required = true)String nickName
    ){
        UserReport report=new UserReport();
        report.setArticleId(articleId);
        report.setNickName(nickName);
        report.setCreateTime(new Date());
//        report.setImgs(imgs);
        report.setStatus(1);
        report.setInfo(info);
        report.setUserId(mobileInfo.getUserId());
        articleService.updateArticleCount(articleId,1,4);
        return  userReportService.insert(report);
    }



//
//    @ApiOperation(value = "关注用户的动态列表", notes = "登陆，分页")
//    @RequestMapping(value = "/follow/list",method = {RequestMethod.POST})
//    @ApiMethod(isLogin = true)
//    public Object followArticle(MobileInfo mobileInfo){
//
//       return   memberService.getMemberFollow(mobileInfo.getMemberId());
//    }


    @ApiOperation(value = "动态推荐列表", notes = "分页")
    @RequestMapping(value = "/recommend/list",method = {RequestMethod.POST})
    @ApiMethod(isLogin = false,isPage = true)
    public List<ArticleEx> recommendArticle(MobileInfo mobileInfo, PageLimit pageLimit){
        List<Integer> follows =null;

        Integer memberId=mobileInfo==null?null:mobileInfo.getUserId();
        if (memberId!=null){
            follows = userFollowService.getUserFlowIds(mobileInfo.getUserId());
        }
        startPage();
        List<ArticleEx> articles = articleService.recommendList(follows,memberId);
        return articles;
    }

    @ApiOperation(value = "test", notes = "分页")
    @RequestMapping(value = "/test/list",method = {RequestMethod.GET})
    @ApiMethod(isLogin = false,isPage = false)
    public Result test(){
        return new Result<>(HttpConnector.JKS_PATH);

    }


//    @ApiOperation(value = "动态推荐列表(包含推荐用户和推荐文章)", notes = "分页")
//    @RequestMapping(value = "/recommend/index/list",method = {RequestMethod.POST})
//    @ApiMethod(isLogin = false,isPage = true)
//    public CommonResult<List<ArticleEx>> recommendArticleIndex(MobileInfo mobileInfo, PageLimit pageLimit){
//        ArticleEx ex1 = null;
//        ArticleEx ex2 = null;
//        Integer noticeIndex =null;
//        Integer recommendMemberIndex =  null;
//        if (pageLimit.getPageNum()==1){
//            //推荐文章
//            ArticleEx ex = new ArticleEx();
//            ex.setIsShow(1);
//            ex.setStatus(2);
//            ex.setType(2);
//            List<ArticleEx> list = articleService.findNoticeIndex(ex);
//            noticeIndex = Integer.parseInt(SystemData.getDictionaryByName("index.notice.location"));
//            recommendMemberIndex = Integer.parseInt(SystemData.getDictionaryByName("index.recommendMember.location"));
//           if (!CollectionUtils.isEmpty(list)){
//               ex1 = new ArticleEx();
//               ex1.setType(3);
//               ex1.setNoticeList(list);
//           }
//            //推荐用户
//            PageHelper.startPage(0,3);
//            List<UmsMemberEx> memberExes = memberService.recommendList(mobileInfo.getMemberId());
//            if (!CollectionUtils.isEmpty(memberExes)){
//                ex2 = new ArticleEx();
//                ex2.setType(4);
//                ex2.setRecommendMemberList(memberExes);
//            }
//        }
//        List<Long> follows =null;
//        Long memberId=mobileInfo==null?null:mobileInfo.getMemberId();
//        if (memberId!=null){
//            follows = memberFollowService.getMemberFlowIds(mobileInfo.getMemberId());
//        }
//        PageUtils.startPage(pageLimit);
//        List<ArticleEx> articles = articleService.recommendList(follows,memberId);
//        if (articles==null){
//            articles = new ArrayList<>();
//        }
//
//        int size = articles.size();
//
//        int flag =0;
//        if (size==0){
//            if (noticeIndex!=null && recommendMemberIndex!=null){
//                if (noticeIndex<=recommendMemberIndex){
//                    if (ex1!=null){
//                        articles.add(flag,ex1);
//                        flag++;
//                    }
//                    if (ex2!=null){
//                        articles.add(flag,ex2);
//                        flag++;
//                    }
//                }else {
//                    if (ex2!=null){
//                        articles.add(flag,ex2);
//                        flag++;
//                    }
//                    if (ex1!=null){
//                        articles.add(flag,ex1);
//                        flag++;
//                    }
//                }
//            }else {
//                if (ex1!=null && noticeIndex!=null){
//                    articles.add(0,ex1);
//                }
//                if (ex2!=null && recommendMemberIndex!=null){
//                    articles.add(0,ex2);
//
//                }
//            }
//        }else {
//            if (ex1!=null && noticeIndex!=null){
//                if (noticeIndex<=size){
//                    articles.add(noticeIndex-1,ex1);
//                }else {
//                    articles.add(size-1,ex1);
//                }
//                flag=1;
//
//            }
//
//            if (ex2!=null && recommendMemberIndex!=null){
//                if (recommendMemberIndex<=size){
//                    articles.add(recommendMemberIndex-1,ex2);
//                }else {
//                    articles.add(size+flag-1,ex2);
//                }
//
//            }
//        }
//
//        return CommonResult.success(articles);
//    }


//    @ApiOperation(value = "动态查询列表", notes = "分页")
//    @RequestMapping(value = "/search/list",method = {RequestMethod.POST})
//    @ApiMethod(isLogin = false)
//    public CommonResult search( @RequestParam @ApiParam(value = "查询内容", required = true)String label){
//        if (StringUtils.isBlank(label)){
//            throw new ApiException("查询内容不能为空！");
//        }
//        ArticleExample example=new ArticleExample();
//        example.createCriteria().andLabelsLike(label).andStatusNotEqualTo(0).andIsShowNotEqualTo(0);
//        example.setOrderByClause("create_time desc");
//        List<Article> articles=articleService.selectByExample(example);
//        return CommonResult.success(articles);
//    }
//
//
    @ApiOperation(value = "我的动态", notes = "分页,登陆")
    @RequestMapping(value = "/myList",method = {RequestMethod.POST})
    @ApiMethod(isLogin = true,isPage = true)
    public List<ArticleEx> myArticle( MobileInfo mobileInfo,@RequestParam @ApiParam(value = "2发布", required = true)Integer status){
        startPage();
        ArticleEx ex = new ArticleEx();
        ex.setIsShow(1);
        ex.setStatus(2);
        ex.setType(1);
        ex.setAuthorId(mobileInfo.getUserId());
        ex.setUserId(mobileInfo.getUserId());
        List<ArticleEx> list = articleService.findList(ex);
        return list;
    }

    @ApiOperation(value = "指定用户动态", notes = "分页")
    @RequestMapping(value = "/member/list",method = {RequestMethod.POST})
    @ApiMethod(isLogin = false,isPage = true)
    public List<ArticleEx> memberArticleList(MobileInfo mobileInfo,@RequestParam @ApiParam(value = "好友用户id", required = true)Integer followMemberId,PageLimit pageLimit){
        startPage();
        ArticleEx ex = new ArticleEx();
        ex.setIsShow(1);
        ex.setStatus(2);
        ex.setType(1);
        ex.setAuthorId(followMemberId);
        ex.setUserId(mobileInfo.getUserId());
        List<ArticleEx> list = articleService.findList(ex);
        return list;
    }


    @ApiOperation(value = "推荐鉴定列表", notes = "分页")
    @RequestMapping(value = "/recommendAppraise/list",method = {RequestMethod.POST})
    @ApiMethod(isLogin = false,isPage = true)
    public List<ArticleEx> noticeList(PageLimit pageLimit){
        startPage();
        ArticleEx ex = new ArticleEx();
        ex.setIsShow(1);
        ex.setStatus(2);
        ex.setType(2);
        List<ArticleEx> list = articleService.findNoticeList(ex);
        return list;
    }


    @ApiOperation(value = "推荐鉴定首页", notes = "")
    @RequestMapping(value = "/recommendAppraise/index",method = {RequestMethod.POST})
    @ApiMethod(isLogin = false)
    public List<ArticleEx> noticeIndex(){
        ArticleEx ex = new ArticleEx();
        ex.setIsShow(1);
        ex.setStatus(2);
        ex.setType(2);
        List<ArticleEx> list = articleService.findNoticeIndex(ex);
        return list;
    }



    @ApiOperation(value = "搜动态、用户、推荐鉴定", notes = "分页")
    @RequestMapping(value = "/search",method = {RequestMethod.POST})
    @ApiMethod(isLogin = false,isPage = true)
    public Object searchMemberArticle(@RequestParam @ApiParam(value = "搜索类型 1推荐鉴定2动态3用户",required = true)Integer type,
                                            MobileInfo mobileInfo,
                                            @RequestParam @ApiParam(value = "搜索内容",required = true)String keyword,PageLimit pageLimit){
        if (type==null || StringUtils.isBlank(keyword)) {
            throw new ApiException("");
        }
        if (keyword.length()>20){
            throw new ApiException(400,"搜索内容太长");
        }
        startPage();
        if (type==1){
            ArticleEx ex = new ArticleEx();
            ex.setIsShow(1);
            ex.setStatus(2);
            ex.setType(2);
            ex.setTitle(keyword);
            List<ArticleEx> list = articleService.findNoticeList(ex);
            return list;
        }else if (type==2){
            ArticleEx ex = new ArticleEx();
            ex.setIsShow(1);
            ex.setStatus(2);
            ex.setType(1);
            ex.setContent(keyword);
            List<ArticleEx> list = articleService.findList(ex);
            return list;
        }else if (type==3){
            List<UserEx> memberExes = userService.findList(mobileInfo.getUserId(),keyword);
            return memberExes;
        }else {
            throw new ApiException("参数异常");
        }
    }


    @ApiOperation(value = "关注动态列表", notes = "分页")
    @RequestMapping(value = "/follow/list",method = {RequestMethod.POST})
    @ApiMethod(isLogin = true,isPage = true)
    public List<ArticleEx> followArticle(MobileInfo mobileInfo, PageLimit pageLimit){
        List<ArticleEx> articles =null;
//        User member = userService.selectByPrimaryKey(mobileInfo.getUserId());
        startPage();
//        if (member.getFollowCount()==null || member.getFollowCount()==0 ){
//            result.setHasFollow(0);
//            articles = articleService.recommendList(null,mobileInfo.getUserid());
//        }else {
//            result.setHasFollow(1);
//            articles = articleService.followArticleList(mobileInfo.getUserid());
//        }
//        articles = articleService.recommendList(null,mobileInfo.getUserId());
        articles = articleService.followArticleList(mobileInfo.getUserId(),null,null);

//        result.setArticleList(articles);

        return articles;
    }

    @ApiOperation(value = "动态关注首页", notes = "返回 map , key = followArticleList 关注列表 ，key = recommendArticleList 推荐列表")
    @RequestMapping(value = "/focus/index",method = {RequestMethod.POST})
    @ApiMethod(isLogin = false)
    public Map<String,Object> focusIndex(MobileInfo mobileInfo){
        ArticleEx ex = new ArticleEx();
        ex.setIsShow(1);
        ex.setStatus(2);
        ex.setType(2);
        List<ArticleEx> list = articleService.findNoticeIndex(ex);
        List<ArticleEx> followArticleList = null;
        if (mobileInfo.getUserId()!=null){
            //最近7天关注用户动态
            followArticleList = articleService.followArticleList(mobileInfo.getUserId(),1,null);
        }
        //这边推荐的是以动态为推荐，不是推荐用户，都是近7天点赞排在前20名的动态随机8条
        List<ArticleEx> recommendArticleList = articleService.recommendNewList(mobileInfo.getUserId(),8);
        Map<String,Object> result = new HashMap<>();
        result.put("followArticleList",followArticleList);
        result.put("recommendArticleList",recommendArticleList);
        return result;
    }


}
