//package com.api.service;
//
//import com.alibaba.fastjson.JSONObject;
//import com.api.MEnumError;
//import com.api.util.MyMapUtils;
//import com.base.api.ApiException;
//import com.base.dao.model.Result;
//import com.paidang.dao.model.*;
//import com.paidang.service.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@Slf4j
//public class AuthService {
//    @Autowired
//    private AuthPersonalService authPersonalService;
//    @Autowired
//    private AuthEnterpriseService authEnterpriseService;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private GoodsService goodsService;
//    @Autowired
//    private BusinessUserInfoService businessUserInfoService;
//
//    @Autowired
//    private BusinessUserBalanceLogService businessUserBalanceLogService;
//
//    public Result passOrNot(int userId){
//        //查询当前人有没有申请个人认证
//        AuthPersonalExample authPersonalExample = new AuthPersonalExample();
//        authPersonalExample.createCriteria().andCreateUserEqualTo(userId).andStateNotEqualTo("3");
//        List<AuthPersonal> authPersonalList = authPersonalService.selectByExample(authPersonalExample);
//        //查询当前人有没有申请企业认证
//        AuthEnterpriseExample authEnterpriseExample = new AuthEnterpriseExample();
//        authEnterpriseExample.createCriteria().andCreateUserEqualTo(userId).andStateNotEqualTo("3");
//        List<AuthEnterprise> authEnterpriseList = authEnterpriseService.selectByExample(authEnterpriseExample);
//        //存在多次提交验证的情况，直接抛出异常
//        if(authPersonalList.size()+authEnterpriseList.size()>1){
//            throw new ApiException(MEnumError.MULTIPLE_SUBMISSIONS);
//        }
//        //不存在提交验证
//        if(authPersonalList.size()+authEnterpriseList.size()==0){
//            return new Result(-1,"当前人还未提交过认证");
//        }
//
//        if(authPersonalList.size() == 1){
//            AuthPersonal authPersonal = authPersonalList.get(0);
//            //审核中
//            if("0".equals(authPersonal.getState())){
//                return new Result(0,"认证审核中");
//            }
//            //审核不通过
//            if("2".equals(authPersonal.getState())){
//                Result result = new Result(2,"认证不通过");
//                result.setData(new MyMapUtils().put("refuseInfo",authPersonal.getRefuseInfo())
//                        .put("type","1").put("remark","个人认证"));
//                return result;
//            }
//            //审核已通过
//            if("1".equals(authPersonal.getState())){
//                Result result = new Result(1,"认证审核通过");
//                return result;
//            }
//        }
//        if(authEnterpriseList.size() == 1){
//            AuthEnterprise authEnterprise = authEnterpriseList.get(0);
//            if("0".equals(authEnterprise.getState())){
//                return new Result(0,"认证审核中");
//            }
//            if("2".equals(authEnterprise.getState())){
//                Result result = new Result(2,"认证不通过");
//                result.setData(new MyMapUtils().put("refuseInfo",authEnterprise.getRefuseInfo())
//                        .put("type","2").put("remark","企业认证"));
//                return result;
//            }
//            if("1".equals(authEnterprise.getState())){
//                Result result = new Result(1,"审核通过");
//                return result;
//            }
//        }
//        return new Result(MEnumError.APPROVAL_ERROR.getErrorCode(), MEnumError.APPROVAL_ERROR.getErrorMsg());
//    }
//
//
//    /**
//     * 判断当前人是个人还是企业
//     * @param userId
//     * @return
//     */
//    public JSONObject isPersonal(int userId){
//        //查询当前人有没有申请个人认证
//        AuthPersonalExample authPersonalExample = new AuthPersonalExample();
//        authPersonalExample.createCriteria().andCreateUserEqualTo(userId).andStateNotEqualTo("3");
//        List<AuthPersonal> authPersonalList = authPersonalService.selectByExample(authPersonalExample);
//        //查询当前人有没有申请企业认证
//        AuthEnterpriseExample authEnterpriseExample = new AuthEnterpriseExample();
//        authEnterpriseExample.createCriteria().andCreateUserEqualTo(userId).andStateNotEqualTo("3");
//        List<AuthEnterprise> authEnterpriseList = authEnterpriseService.selectByExample(authEnterpriseExample);
//        //存在多次提交验证的情况，直接抛出异常
//        if(authPersonalList.size()+authEnterpriseList.size()<1){
//            throw new ApiException(MEnumError.MULTIPLE_SUBMISSIONS);
//        }else if(authPersonalList.size()+authEnterpriseList.size()>1){
//            throw new ApiException(MEnumError.NONE_SUBMISSIONS);
//        }
//        JSONObject ret = new JSONObject();
//        if(authPersonalList.size()==1){
//            ret.put("code",1);
//            ret.put("org_id",authPersonalList.get(0).getOrgId());
//            return ret;//个人认证
//        }else if(authEnterpriseList.size()==1){
//            ret.put("code",2);
//            ret.put("org_id",authEnterpriseList.get(0).getOrgId());
//            return ret;//个人认证
//        }else{
//            throw new ApiException(MEnumError.APPROVAL_ERROR);
//        }
//    }
//
//    public BigDecimal getTotalByOrderState(List<Integer> goodsIds,int state){
//        OrderExample orderExample = new OrderExample();
//        if(goodsIds.isEmpty()){
//            return BigDecimal.ZERO;
//        }
//        List<Integer> refState = new ArrayList<>();
//        refState.add(0);
//        refState.add(5);
//        OrderExample.Criteria criteria = orderExample.createCriteria();
//        criteria.andStateEqualTo(state);
//        criteria.andGoodsIdIn(goodsIds);
//        criteria.andRefStateIn(refState);
//        criteria.andIsDelEqualTo(0);
//        List<Order> orders = orderService.selectByExample(orderExample);
//        BigDecimal total = BigDecimal.ZERO;
//        for(Order order: orders){
//            total = total.add(order.getGoodsPrice());
//        }
//        return total;
//    }
//}
