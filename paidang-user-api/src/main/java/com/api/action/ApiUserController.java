package com.api.action;

import com.api.util.PageLimit;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.util.StringUtils;
import com.github.pagehelper.PageHelper;

import com.item.daoEx.model.UserEx;
import com.item.service.UserService;
import com.paidang.dao.model.*;
import com.paidang.daoEx.model.PrivateMessageEx;
import com.paidang.service.PrivateMessageService;
import com.paidang.service.UserBlackService;
import com.paidang.service.UserFollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces = { "application/json;charset=UTF-8" })
@Api(tags = "用户操作")
public class ApiUserController extends ApiBaseController {

    @Autowired
    private UserFollowService userFollowService;

    @Autowired
    private UserBlackService userBlackService;


//    @Autowired
//    private UserOptionService userOptionService;

    @Autowired
    private UserService userService;

    @Autowired
    private PrivateMessageService privateMessageService;

//    @Autowired
//    private OmsPortalOrderService omsPortalOrderService;


    @ApiOperation(value = "关注(取消关注)用户", notes = "登陆")
    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer userFollow(MobileInfo mobileInfo,
                              @RequestParam @ApiParam(value = "目标用户id", required = true)Integer followUserId,
                              @RequestParam @ApiParam(value = "操作类型0取消关注1关注", required = true)Integer type){

        if (mobileInfo.getUserId().equals(followUserId)){
            throw new ApiException(400,"无法关注自己");
        }
        //获取关注状态
        UserFollowExample example=new UserFollowExample();
        example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId()).andFollowIdEqualTo(followUserId).andTypeEqualTo(1);
        UserFollow follow=new UserFollow();
        if (type==0){
            //取消关注
            int num = userFollowService.deleteByExample(example);
            if (num>0){
                userService.updateUserCount(followUserId,3);
                userService.updateUserCount(mobileInfo.getUserId(),2);
            }
            return 0;
        }else if (type==1){

            List<UserFollow> list =  userFollowService.selectByExample(example);
            if (CollectionUtils.isEmpty(list)){
                follow.setUserId(mobileInfo.getUserId());
                follow.setFollowId(followUserId);
                follow.setCreateTime(new Date());
                follow.setStatus(1);
                follow.setType(1);
                int num = userFollowService.insert(follow);
                if (num>0){
                    userService.updateUserCount(mobileInfo.getUserId(),2);
                    userService.updateUserCount(followUserId,3);
                }
                return 1;
            }

            return 1;
        }else {
            throw new ApiException("参数非法");
        }
    }


    @ApiOperation(value = "拉黑用户", notes = "登陆")
    @RequestMapping(value = "/black", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer userBlack(MobileInfo mobileInfo,
                              @RequestParam @ApiParam(value = "目标用户id", required = true)Integer blackUserId,
                              @RequestParam @ApiParam(value = "操作类型0取消拉黑1拉黑", required = true)Integer type){

        if (type==1){
            UserBlack black=new UserBlack();
            black.setUserId(mobileInfo.getUserId());
            black.setBlackUserId(blackUserId);
            black.setCreateTime(new Date());
            int num=userBlackService.insert(black);
            //删除关注状态
            UserFollowExample example1=new UserFollowExample();
            example1.createCriteria().andUserIdEqualTo(mobileInfo.getUserId()).andFollowIdEqualTo(blackUserId);
            example1.or().andUserIdEqualTo(blackUserId).andFollowIdEqualTo(mobileInfo.getUserId());
            userFollowService.deleteByExample(example1);
            return num;
        }else if (type==0){
            UserBlackExample example=new UserBlackExample();
            example.createCriteria().andUserIdEqualTo(mobileInfo.getUserId()).andBlackUserIdEqualTo(blackUserId);
            return userBlackService.deleteByExample(example);
        }
        throw new ApiException("参数非法");
    }


    @ApiOperation(value = "用户私信", notes = "登陆")
    @RequestMapping(value = "/message/add", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer addUserMessage(MobileInfo mobileInfo,
                             @RequestParam @ApiParam(value = "目标用户id", required = true)Integer friendId,
                             @RequestParam @ApiParam(value = "1 文字 2图片(url)", required = true)Integer type,
                             @RequestParam @ApiParam(value = "内容", required = true)String content){
        Date date = new Date();
        PrivateMessage entity = new PrivateMessage();
        entity.setFriendId(friendId);
        entity.setUserId(mobileInfo.getUserId());
        entity.setSenderId(mobileInfo.getUserId());
        entity.setReceiverId(friendId);
        entity.setType(type);
        entity.setMessageContent(StringUtils.unescapeXss(content));
        entity.setMessageType(1);
        entity.setSendTime(date);
        entity.setStatus(2);
        privateMessageService.insert(entity);
        entity.setStatus(1);
        entity.setFriendId(mobileInfo.getUserId());
        entity.setUserId(friendId);
//        entity.setSenderId(friendId);
//        entity.setReceiverId(mobileInfo.getUserId());
        privateMessageService.insert(entity);
        return 1;
    }


    @ApiOperation(value = "用户私信列表", notes = "登陆,分页")
    @RequestMapping(value = "/message/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<PrivateMessageEx> userMessageList(MobileInfo mobileInfo,PageLimit pageLimit){
        startPage();
        List<PrivateMessageEx> list = privateMessageService.myPrivateMessageList(mobileInfo.getUserId());
        return list;
    }


    @ApiOperation(value = "用户私信对话列表", notes = "登陆,分页")
    @RequestMapping(value = "/message/info", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<PrivateMessageEx> userMessageInfo(MobileInfo mobileInfo,PageLimit pageLimit,
                                  @RequestParam @ApiParam(value = "目标用户id", required = true)Integer friendId){
        startPage();
        List<PrivateMessageEx> list = privateMessageService.myPrivateMessageInfo(mobileInfo.getUserId(),friendId);
        privateMessageService.readMsg(mobileInfo.getUserId(),friendId);
        return list;
    }


    @ApiOperation(value = "删除私信会话", notes = "登陆")
    @RequestMapping(value = "/message/deleteAll", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer messageDeleteAll(MobileInfo mobileInfo,
                                         @RequestParam @ApiParam(value = "目标用户id", required = true)Integer friendId){
        int a = privateMessageService.deleteAll(mobileInfo.getUserId(),friendId);
        return a;
    }

    @ApiOperation(value = "删除单个私信", notes = "登陆")
    @RequestMapping(value = "/message/deleteSingle", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Integer messageDeleteSingle(MobileInfo mobileInfo){
        int a = privateMessageService.deleteSingle(mobileInfo.getUserId());
        return a;
    }

    //好友接口
    @ApiOperation(value = "关注用户列表")
    @RequestMapping(value = "/follow/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<UserEx> followList(PageLimit pageLimit, MobileInfo mobileInfo){
        startPage();
        List<UserEx> list = userService.findFollowList(mobileInfo.getUserId());
        return list;
    }

    @ApiOperation(value = "粉丝用户列表")
    @RequestMapping(value = "/fans/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = true,isPage = true)
    public List<UserEx> fansList(PageLimit pageLimit, MobileInfo mobileInfo){
        startPage();
        List<UserEx> list = userService.findFansList(mobileInfo.getUserId());
        return list;
    }

//    @ApiOperation(value = "意见反馈", notes = "登陆")
//    @RequestMapping(value = "/option/add", method = RequestMethod.POST)
//    @ApiMethod(isLogin = true)
//    public Object addUserOption(MobileInfo mobileInfo,
//                                  @RequestParam @ApiParam(value = "图片", required = false)String img,
//                                  @RequestParam @ApiParam(value = "意见内容", required = true)String info
//    ){
//        UserOption option=new UserOption();
//        option.setCreateTime(new Date());
//        option.setStatus(1);
//        option.setUserId(mobileInfo.getUserid());
//        option.setProcessState(0);
//        option.setImg(img);
//        option.setInfo(info);
//        return userOptionService.insert(option);
//    }

    @ApiOperation(value = "推荐用户列表")
    @RequestMapping(value = "/recommend/list", method = RequestMethod.POST)
    @ApiMethod(isLogin = false,isPage = false)
    public List<UserEx> recommendList(@RequestParam(required = false) @ApiParam(value = "用户id", required = false)Integer userId){
        List<UserEx> list = userService.recommendList(userId,6);
        return list;
    }


    @ApiOperation(value = "推荐用户列表首页只返回3个")
    @RequestMapping(value = "/recommend/index", method = RequestMethod.POST)
    @ApiMethod(isLogin = false)
    public List<UserEx>  recommendIndex(@RequestParam(required = false) @ApiParam(value = "用户id", required = false)Integer userId){
        List<UserEx> list = userService.recommendList(userId,3);
        return list;
    }
//
//    @ApiOperation(value = "健康咨询顾问列表", notes = "登陆")
//    @RequestMapping(value = "/counselor/list", method = RequestMethod.POST)
//    @ApiMethod(isLogin = true)
//    public CommonResult<List<UserEx>> counselorList(MobileInfo mobileInfo){
//        List<UserEx> list = umsUserService.findCounselors(mobileInfo.getUserId());
//        return CommonResult.success(list);
//    }

//    @ApiOperation(value = "个人中心", notes = "登陆")
//    @RequestMapping(value = "/index", method = RequestMethod.POST)
//    @ApiMethod(isLogin = true)
//    public CommonResult<UserIndexVo> userIndex(MobileInfo mobileInfo){
//        User user = umsUserService.getById(mobileInfo.getUserId());
//        if (user.getStatus()==0){
//            throw new ApiException(400,"账户已被禁用");
//        }
//        UserIndexVo vo = new UserIndexVo();
//        BeanUtils.copyProperties(user,vo);
//        vo.setUnpaidOrder(omsPortalOrderService.countOrderByStatus(0,mobileInfo.getUserId()));
//        return CommonResult.success(vo);
//    }

//    @ApiOperation(value = "个人中心设置", notes = "登陆")
//    @RequestMapping(value = "/set", method = RequestMethod.POST)
//    @ApiMethod(isLogin = true)
//    public CommonResult userSet(MobileInfo mobileInfo, @RequestBody UserSetParam userSetParam){
//        User user = new User();
//        BeanUtils.copyProperties(userSetParam,user);
//        user.setId(mobileInfo.getUserId());
//        user.setIcon(StringUtils.unescapeXss(user.getIcon()));
//        umsUserService.updateByPrimaryKeySelective(user);
//        return CommonResult.success(1);
//    }
//
//
//    @ApiOperation(value = "用户名片信息", notes = "")
//    @RequestMapping(value = "/card/get", method = RequestMethod.POST)
//    @ApiMethod(isLogin = false)
//    public CommonResult<UserSetParam> getUserCard(@RequestParam(required = true) @ApiParam(value = "用户id", required = true)Integer userId){
//        User user = umsUserService.getById(userId);
//        if (user==null){
//            return CommonResult.failed("用户不存在");
//        }
//        UserSetParam result = new UserSetParam();
//        result.setNickname(user.getNickname());
//        result.setGender(user.getGender());
//        result.setIcon(user.getIcon());
//        return CommonResult.success(result);
//    }
}
