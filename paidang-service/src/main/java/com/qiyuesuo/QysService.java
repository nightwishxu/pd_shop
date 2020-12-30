package com.qiyuesuo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.api.ApiException;
import com.base.util.CoreConstants;
import com.paidang.dao.model.PawnContinue;
import com.paidang.dao.model.UserPawn;
import com.paidang.service.PawnContinueService;
import com.paidang.service.UserPawnService;
import com.qiyuesuo.sdk.v2.SdkClient;
import com.qiyuesuo.sdk.v2.bean.*;
import com.qiyuesuo.sdk.v2.json.JSONUtils;
import com.qiyuesuo.sdk.v2.request.ContractDraftRequest;
import com.qiyuesuo.sdk.v2.request.ContractShorturlRequest;
import com.qiyuesuo.sdk.v2.request.ContractViewPageRequest;
import com.qiyuesuo.sdk.v2.response.ContractPageResult;
import com.qiyuesuo.sdk.v2.response.ContractShorturlResult;
import com.qiyuesuo.sdk.v2.response.SdkResponse;
import com.util.LsmSmsSendUtil;
import com.util.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QysService {

    public static  Logger logger = LoggerFactory.getLogger(QysService.class);

    @Autowired
    UserPawnService userPawnService;
    @Autowired
    PawnContinueService pawnContinueService;

    // 初始化sdkClient
//    public static String serverUrl = CoreConstants.getProperty("electronic_signature_url");
//    public static String accessKey = CoreConstants.getProperty("electronic_signature_app_token");
//    public static String accessSecret = CoreConstants.getProperty("electronic_signature_app_secret");
//    //契约锁典当业务id
//    public static Long pawnCategoryId = Long.valueOf(CoreConstants.getProperty("pawnCategoryId"));
//    //契约锁续当业务id
//    public static Long pawnContinueCategoryId = Long.valueOf(CoreConstants.getProperty("pawnContinueCategoryId"));
//    //短信提醒文字
//    public static String msg="您有一个合同待签署，请点击连接签署：%s";
//
//    /**
//     * 获取业务id
//     * @param type 1典当2续当
//     * @return
//     */
//    public Long getCategoryId(String type){
//        //典当
//        if(type.equals("1")){
//            return pawnCategoryId;
//        }else if(type.equals("2")){
//            //续当
//            return pawnContinueCategoryId;
//        }
//        else{
//            throw new ApiException(9999,"没有当前业务合同");
//        }
//    }

    /**
     *创建合同
     * @param categoryId 业务id
     * @param userName 用户名
     * @param userTel 用户手机号
     * @param orgName 机构名
     * @param orgTel 机构手机号
     * @param categoryParam 合同参数
     * @return
     */
//    public String createSendContract(Long categoryId,String userName,String userTel,String orgName,String orgTel,Object categoryParam){
//        logger.info("===============================开始创建合同===============================" +
//                        "categoryId:{},categoryId:{},categoryId:{},categoryId:{},categoryId:{},categoryId:{}",
//                categoryId,userName,userTel,orgName,orgTel, JSON.toJSONString(categoryParam));
//        SdkClient sdkClient = new SdkClient(serverUrl, accessKey, accessSecret);
//        // 合同基本参数
//        Contract contract = new Contract();
//        contract.setSubject("互联网电子典当凭证");
//        Category category = new Category();
//        //业务id
//        category.setId(categoryId);
//        contract.setCategory(category);
//        contract.setSend(true);
//        // 对接方1
//        Signatory signatory1 = new Signatory();
//        signatory1.setTenantName(orgName);
//        signatory1.setTenantType("COMPANY");
//        signatory1.setReceiver(new User(orgName, orgTel, "MOBILE"));
//        Action action = new Action();
//        action.setType("COMPANY");
//        signatory1.addAction(action);
//        // 对接方2
//        Signatory signatory2 = new Signatory();
////        signatory1.setTenantName(userEx.getName());
//        signatory2.setTenantName(userName);
//        signatory2.setTenantType("PERSONAL");
//        signatory2.setReceiver(new User(userName, userTel, "MOBILE"));
//
//// 设置签署方
//        contract.addSignatory(signatory1);
//        contract.addSignatory(signatory2);
//        //设置合同参数
//        List<TemplateParam> paramList = new ArrayList<>();
//        JSONObject params = JSONObject.parseObject(JSON.toJSONString(categoryParam));
//        for (String key :params.keySet()) {
//            TemplateParam templateParam = new TemplateParam(key,params.getString(key));
//            paramList.add(templateParam);
//        }
//        contract.setTemplateParams(paramList);
//// 创建合同
//        ContractDraftRequest request = new ContractDraftRequest(contract);
//        String response = sdkClient.service(request);
//        SdkResponse<Contract> responseObj = JSONUtils.toQysResponse(response, Contract.class);
//// 返回结果
//        if(responseObj.getCode() == 0) {
//            Contract result = responseObj.getResult();
//            //保存合同id到数据库
////            logger.info("创建合同成功，合同ID:{}", result.getId());
//            logger.info("===============================合同创建成功===============================合同id:{}",result.getId());
//            return result.getId().toString();
//        } else {
//            logger.error("===============================合同创建失败===============================：{}",responseObj.getCode()+responseObj.getMessage());
//            throw new ApiException(9999,responseObj.getMessage());
////            logger.info("请求失败，错误码:{}，错误信息:{}", responseObj.getCode(), responseObj.getMessage());
//        }
//
//    }



    /**
     * 获取查看合同的链接
     * @param contractId 合同ID
     * @return
     */
    public String getPageUrl(Long contractId){
        return "";
//        SdkClient sdkClient = new SdkClient(serverUrl, accessKey, accessSecret);
//        ContractViewPageRequest request=new ContractViewPageRequest(contractId);
//        String response = sdkClient.service(request);
//        SdkResponse<ContractPageResult> responseObj = JSONUtils.toQysResponse(response, ContractPageResult.class);
//        if(responseObj.getCode() == 0) {
//            ContractPageResult result = responseObj.getResult();
//            logger.info("合同页面地址为:{}", result.getPageUrl());
//            return result.getPageUrl();
//        } else {
//            logger.info("请求失败，错误码:{}，错误信息:{}", responseObj.getCode(), responseObj.getMessage());
//            throw new ApiException(9999,responseObj.getMessage());
//        }

    }


    /**
     * 添加典当合同Id
     * @param pawnId
     * @param contractId
     */
    public void addPawnContractId(Integer pawnId,String contractId){
        //将合同id更新到典当表
        UserPawn userPawn = new UserPawn();
        userPawn.setId(pawnId);
        userPawn.setContractId(contractId);
        userPawnService.updateByPrimaryKeySelective(userPawn);
    }


    /**
     * 添加续当合同Id
     * @param pawnContinueId
     * @param contractId
     */
    public void addPawnContinueContractId(Integer pawnContinueId,String contractId){
        //将合同id更新到续当表
        PawnContinue pawnContinue = new PawnContinue();
        pawnContinue.setId(pawnContinueId);
        pawnContinue.setContractId(contractId);
        pawnContinueService.updateByPrimaryKeySelective(pawnContinue);
    }
    /**
     * 获取合同签署短链接
     * @param contractId
     */
//    public String getShortUrl(Long contractId,String phone){
//        SdkClient sdkClient = new SdkClient(serverUrl, accessKey, accessSecret);
//        ContractShorturlRequest request=new ContractShorturlRequest(contractId);
//        request.setContact(phone);
//        String response = sdkClient.service(request);
//        SdkResponse<ContractShorturlResult> responseObj = JSONUtils.toQysResponse(response,     ContractShorturlResult.class);
//        if(responseObj.getCode() == 0) {
//            ContractShorturlResult result = responseObj.getResult();
//            logger.info("合同短链接为:{}", result.getShorturl());
//            return result.getShorturl();
//        } else {
//            logger.error("请求失败，错误码:{}，错误信息:{}", responseObj.getCode(),responseObj.getMessage());
//            throw new ApiException(9999,"获取合同短连接失败");
//        }
//    }

    /**
     * 获取短链接并短信发送给用户
     * @param contractId
     */
//    public void getAndSendShortUrl(Long contractId,String phone){
//        String url= getShortUrl(contractId,phone);
//        String content= String.format(msg,url);
//        Res res = LsmSmsSendUtil.send(phone, content);
//        if(0==res.getError()){
//            logger.info("~~~~~~~~~~~~~~发送短信,目标号码:"+phone+",发送内容："+content+",发送结果:"+res.getMsg()+"~~~~~~~~~~~~~~~~~~");
//        }else{
//            throw new ApiException(9999,"合同签署通知短信发送失败，原因："+res.getMsg());
//        }
//    }




}
