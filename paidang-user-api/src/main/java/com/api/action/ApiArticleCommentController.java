package com.api.action;


import com.api.util.PageLimit;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.service.SensitivWordsService;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.ArticleCommentEx;
import com.paidang.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/api/articleComment", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
@Api(tags = "用户评价")
public class ApiArticleCommentController  extends ApiBaseController {


    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private ArticleCommentReplyService articleCommentReplyService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private SensitivWordsService sensitivWordsService;

    @Autowired
    private UserBlackService userBlackService;

    @Autowired
    private CommentPraiseService commentPraiseService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private JPushService pushService;

    @Autowired
    private CommentReportLogService commentReportLogService;



    @ApiOperation(value = "新增评价", notes = "登陆")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer add(MobileInfo mobileInfo,
                            @RequestParam @ApiParam(value = "评论", required = true)String content,
                            @RequestParam(required = false) @ApiParam(value = "头像", required =false)String icon,
                            @RequestParam @ApiParam(value = "昵称", required = true)String nickname,
                            @RequestParam @ApiParam(value = "动态id", required = true)Integer articleId
                      ){

        ArticleComment entity=new ArticleComment();
        Article article=articleService.selectByPrimaryKey(articleId);
        if (article==null){
            throw new ApiException("动态不存在！");
        }
//        if (userBlackService.isBlackMember(mobileInfo.getMemberId(),article.getMemberId())>0){
//            throw new ApiException("已被拉黑无法评论动态");
//        }

        //敏感词汇过滤
        entity.setContent(sensitivWordsService.relpSensitivWords(content));
        entity.setIsHot(0);
        entity.setUserId(mobileInfo.getUserId());
        entity.setLikeNum(0);
        entity.setIsReply(0);
        entity.setReplyNum(0);
        entity.setIsTop(0);
        entity.setIcon(icon);
        entity.setNickname(nickname);
        entity.setArticleId(articleId);
        entity.setType(1);
        entity.setStatus(1);
        entity.setIsReport(0);
        entity.setAuthorId(article.getUserId());
        entity.setCreateTime(new Date());
        if (article.getUserId().equals(mobileInfo.getUserId())){
            entity.setIsAuthor(2);
        }else {
            entity.setIsAuthor(0);
        }
        //更新评论数
        articleService.updateArticleCount(articleId,1,0);
        return articleCommentService.insert(entity);
    }


    @ApiOperation(value = "回复评价", notes = "登陆")
    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer reply(MobileInfo mobileInfo,
                      @RequestParam @ApiParam(value = "评论", required = true)String content,
                        @RequestParam @ApiParam(value = "动态id", required = true)Integer articleId,
                      @RequestParam(required = false) @ApiParam(value = "头像", required = false)String icon,
                      @RequestParam @ApiParam(value = "昵称", required = true)String nickname,
                      @RequestParam @ApiParam(value = "评论id", required = true)Integer commentId,
                      @RequestParam @ApiParam(value = "1为回复评论，2为回复别人的回复", required = true)Integer replyType,
                      @RequestParam(required = false) @ApiParam(value = "replyType为2时传值，回复评论id", required = false)Integer replyId

    ){

        Article article=articleService.selectByPrimaryKey(articleId);
        if (article==null){
            throw new ApiException(400,"动态不存在！");
        }
//        if (userBlackService.isBlackMember(mobileInfo.getMemberId(),article.getMemberId())>0){
//            throw new ApiException("已被拉黑无法评论动态");
//        }
        ArticleCommentReply entity=new ArticleCommentReply();
        entity.setReplyType(replyType);
        entity.setStatus(1);
        entity.setIsShow(1);
        ArticleComment comment=articleCommentService.selectByPrimaryKey(commentId);
        if (comment==null || comment.getStatus()==-1){
            throw new ApiException(400,"该评论已删除");
        }
        if (article.getUserId().equals(mobileInfo.getUserId())){
            entity.setIsAuthor(2);
        }else {
            entity.setIsAuthor(0);
        }
        //敏感词汇过滤
        entity.setContent(sensitivWordsService.relpSensitivWords(content));
        entity.setCommentId(commentId);
        if (replyType==2){
            if (replyId==null){
                throw new ApiException("replyId");
            }
            entity.setReplyId(replyId);
            ArticleCommentReply reply=articleCommentReplyService.selectByPrimaryKey(replyId);
            if (reply==null || reply.getStatus()==-1){
                throw new ApiException(400,"该评论已删除");
            }
            entity.setToUid(reply.getFromUid());
            entity.setToNickname(entity.getFromNickname());
        }else {
            entity.setReplyId(commentId);
            entity.setToUid(comment.getUserId());
            entity.setToNickname(comment.getNickname());
        }
        entity.setFromUid(mobileInfo.getUserId());
        entity.setFromNickname(nickname);
        entity.setFromThumbImg(icon);
        entity.setCreateTime(new Date());
        entity.setIsReport(0);
        entity.setReplyNum(0);
        articleCommentReplyService.insert(entity);
        if (replyType==1){
            articleCommentService.updateReplyNum(commentId);
        }else {
            articleCommentReplyService.updateReplyNum(commentId);
        }

        //更新评论数
        articleService.updateArticleCount(articleId,1,0);
        return 1;

    }


    @ApiOperation(value = "点赞评价", notes = "登陆")
    @RequestMapping(value = "/praise", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer praise(MobileInfo mobileInfo,
                            @RequestParam @ApiParam(value = "评论id", required = true)Integer commentId,
                            @RequestParam @ApiParam(value = "评论的用户id", required = true)Integer commentMemberId,
                            @RequestParam @ApiParam(value = "评论类型 1 评论2评论回复", required =true)Integer commentType,
                            @RequestParam @ApiParam(value = "0取消点赞 1点赞", required = true)Integer type,
                            @RequestParam @ApiParam(value = "动态id", required = true)Integer articleId
    ){

        if (commentId==null || commentType==null || type==null || articleId==null){
            throw new ApiException("");
        }
        CommentPraiseExample example = new CommentPraiseExample();
        CommentPraiseExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(mobileInfo.getUserId()).andTypeEqualTo(commentType).andCommentIdEqualTo(commentId);
        if (type==0){
            int num =commentPraiseService.deleteByExample(example);
            if (commentType==1 && num>0){
                articleCommentService.updateLikeNum(commentId,-1);
            }
            return 1;
        }else if (type==1){
            List<CommentPraise> list = commentPraiseService.selectByExample(example);
            if (CollectionUtils.isEmpty(list)){
                CommentPraise commentPraise = new CommentPraise();
                commentPraise.setCommentId(commentId);
                commentPraise.setType(commentType);
                commentPraise.setUserId(mobileInfo.getUserId());
                commentPraise.setStatus(1);
                commentPraise.setCreateTime(new Date());
                commentPraise.setArticleId(articleId);
                commentPraise.setCommentUserId(commentMemberId);
                commentPraiseService.insert(commentPraise);
                if (commentType==1){
                    articleCommentService.updateLikeNum(commentId,1);
                }
//                UmsMember umsMember = umsMemberService.getById(commentMemberId);
//                if (StringUtils.isNoneBlank(umsMember.getUnionId())){
//                    pushService.jpushSendMsg(umsMember.getUnionId(),"破壳联盟","您有一条精彩评论被点赞了",null,null);
//                }

            }
        }


        return 1;
    }


    @ApiOperation(value = "动态评论列表", notes = "分页")
    @RequestMapping(value = "/article/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = false,isPage = true)
    public List<ArticleCommentEx> reply(MobileInfo mobileInfo, PageLimit pageLimit,
                                        @RequestParam @ApiParam(value="动态id", required =true)Integer articleId){
        if (articleId==null){
            throw new ApiException("articleId");
        }
        Integer memberId = mobileInfo==null?null:mobileInfo.getUserId();
        startPage();
        List<ArticleCommentEx> list = articleCommentService.findList(articleId,memberId);
        return list;
    }


    @ApiOperation(value = "删除评价", notes = "登陆")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer deleteComment(MobileInfo mobileInfo,@RequestParam @ApiParam(value="评论id", required =true)Integer commentId
            ,@RequestParam @ApiParam(value="评论类型1回复动态评论 2 回复评论", required =true)Integer commentType){
//        if (!RedisClient.getLock("memberDeleteComment:"+mobileInfo.getMemberId()+":"+commentId,3)){
//            throw new ApiException("操作太频繁，请稍后");
//        }
        if (commentType==1){
            ArticleComment comment = new ArticleComment();
            comment.setId(commentId);
            comment.setStatus(-1);
            articleCommentService.updateByPrimaryKeySelective(comment);
        }else if (commentType==2){
            ArticleCommentReply commentReply = new ArticleCommentReply();
            commentReply.setId(commentId);
            commentReply.setStatus(-1);
            articleCommentReplyService.updateByPrimaryKeySelective(commentReply);
        }
        return 1;
    }




    @ApiOperation(value = "举报评价", notes = "登陆")
    @RequestMapping(value = "/report", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer reportComment(MobileInfo mobileInfo,@RequestParam @ApiParam(value="评论id", required =true)Integer commentId
            ,@RequestParam @ApiParam(value="评论类型1回复动态评论 2 回复评论", required =true)Integer commentType){
//        if (!RedisClient.getLock("memberReportComment:"+mobileInfo.getMemberId()+":"+commentId,3)){
//            throw new ApiException("操作太频繁，请稍后");
//        }
        CommentReportLogExample example = new CommentReportLogExample();
        example.createCriteria().andCommentIdEqualTo(commentId).andUserIdEqualTo(mobileInfo.getUserId()).andCommentTypeEqualTo(commentType);
        if (!CollectionUtils.isEmpty(commentReportLogService.selectByExample(example))){
            throw new ApiException(400,"评论已举报");
        }
        CommentReportLog log = new CommentReportLog();
        if (commentType==1){
            ArticleComment comment = articleCommentService.selectByPrimaryKey(commentId);
            if (comment==null || comment.getStatus()==-1){
                throw new ApiException(400,"评论已删除");
            }

            log.setCommentId(commentId);
            log.setStatus(1);
            log.setCommentType(commentType);
            log.setUserId(mobileInfo.getUserId());
            log.setCreateTime(new Date());
            commentReportLogService.insert(log);
            ArticleComment tmp =new ArticleComment();
            tmp.setId(commentId);
            tmp.setIsReport(1);
            articleCommentService.updateByPrimaryKeySelective(tmp);
        }else if (commentType==2){
            ArticleCommentReply comment = articleCommentReplyService.selectByPrimaryKey(commentId);
            if (comment==null || comment.getStatus()==-1){
                throw new ApiException(400,"评论已删除");
            }
            log.setCommentId(commentId);
            log.setStatus(1);
            log.setCommentType(commentType);
            log.setUserId(mobileInfo.getUserId());
            log.setCreateTime(new Date());
            commentReportLogService.insert(log);
            ArticleCommentReply tmp =new ArticleCommentReply();
            tmp.setId(commentId);
            tmp.setIsReport(1);
            articleCommentReplyService.updateByPrimaryKeySelective(tmp);
        }
        log.setCommentId(commentId);
        log.setStatus(1);
        log.setCommentType(commentType);
        log.setUserId(mobileInfo.getUserId());
        log.setCreateTime(new Date());
        commentReportLogService.insert(log);
        return 1;
    }


//    @ApiOperation(value = "我的回复", notes = "分页")
//    @RequestMapping(value = "/myReply/list", method = RequestMethod.POST)
//    @ApiMethod(isLogin = true,isPage = true)
//    public List<MyCommentVo> myComment(MobileInfo mobileInfo, PageLimit pageLimit){
//        startPage();
//        List<MyCommentVo> list = articleCommentService.find(mobileInfo.getUserid());
//        return list;
//    }



}
