package com.api.service;

import cfca.trustsign.common.constant.SealColor;
import cfca.trustsign.common.vo.cs.*;
import cfca.trustsign.common.vo.request.tx3.*;
import com.base.util.JSONUtils;
import com.demo.connector.HttpConnector;
import com.demo.constant.Request;
import com.demo.converter.JsonObjectMapper;
import com.demo.util.SecurityUtil;
import com.demo.util.TimeUtil;
import cpcn.dsp.institution.api.security.EncryptAndDecrypt;
import cpcn.dsp.institution.api.tx.file.Tx3201Request;
import cpcn.dsp.institution.api.util.GUIDGenerator;
import cpcn.dsp.institution.api.vo.ImageFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: xww
 * @create: 2020-11-20 21:34
 * @description: 安心签
 * @modified By:
 * @version:
 **/
@Service
public class AnXinSignService {

    private static final Logger logger = LoggerFactory.getLogger(AnXinSignService.class);

    public static void main(String[] args)  throws Exception{
        System.out.println(personRegister("320483199112215013","许文炜","18861269725"));
    }

    /**
     * 个人注册
     * @param identNo
     * @param personName
     * @param phone
     * @return
     * @throws Exception
     */
    public static String personRegister(String identNo,String personName,String phone) throws Exception{
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3001ReqVO tx3001ReqVO = new Tx3001ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        PersonVO person = new PersonVO();
        // person.setPersonName("xrma");`
        // person.setIdentTypeCode("1");
        // person.setIdentNo("123123");
        person.setPersonName(personName);
        person.setIdentTypeCode("0");//证件类型 0 身份证
        person.setIdentNo(identNo);//证据号
        person.setMobilePhone(phone);//手机号
        // person.setEmail("235211@cfca.com.cn");
        // person.setAddress("北京");
        person.setAuthenticationMode("公安部");
//        person.setIsOpenSM2(1);

        tx3001ReqVO.setHead(head);
        tx3001ReqVO.setPerson(person);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3001ReqVO);
        logger.info("personRegister req:{}",req);
        String txCode = "3001";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        logger.info("personRegister res:{}",res);
        Map map = JSONUtils.deserialize(res, Map.class);
        Map person1 = (Map)map.get("person");
        String userId = (String)person1.get("userId");
        //B49A56D031274CF2E05311016B0A242C
        System.out.println(userId);
        return userId;
    }


    /**
     * 企业注册
     * @param orgName  机构名称
     * @param identNo 证件号码
     * @param mobilePhone  手机号 电子签章负责人或经办人
     * @param laneLinePhone  企业联系电话
     * @param transactorName  经办人名称
     * @param transactorIndentNo
     * @throws Exception
     */
    public void companyRegister(String orgName,String identNo,String mobilePhone,String laneLinePhone,String transactorName
    ,String transactorIndentNo) throws Exception{
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3002ReqVO tx3002ReqVO = new Tx3002ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        EnterpriseVO enterprise = new EnterpriseVO();
        enterprise.setEnterpriseName(orgName);
        enterprise.setIdentTypeCode("8");// 证件类型 企业营业执照企业营业执照
        enterprise.setIdentNo(identNo);
        enterprise.setMobilePhone(mobilePhone);
        enterprise.setLandlinePhone(laneLinePhone);
        // enterprise.setEmail("11900129@cfca.com.cn");
        enterprise.setAuthenticationMode("公安部");

        EnterpriseTransactorVO enterpriseTransactor = new EnterpriseTransactorVO();
        enterpriseTransactor.setTransactorName(transactorName);
        enterpriseTransactor.setIdentTypeCode("1");
        enterpriseTransactor.setIdentNo(transactorIndentNo);
        // enterpriseTransactor.setAddress("beijing");

        tx3002ReqVO.setHead(head);
        tx3002ReqVO.setEnterprise(enterprise);
        tx3002ReqVO.setEnterpriseTransactor(enterpriseTransactor);
        // tx3002ReqVO.setSealColor(2);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3002ReqVO);
        logger.info("personRegister req:{}",req);

        String txCode = "3002";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        logger.info("personRegister res:{}",res);
//        Map map = JSONUtils.deserialize(res, Map.class);
//        String userId = (String)map.get("userId");

    }

    /**
     * 3001or3002开户接口——3101+3102短信授权接口——3201上传合同模板并签署接口
     */


    /**
     * 发送短信验证码
     * @param userId
     * @param projectCode
     */
    public void sendSmsCode(String userId,String projectCode,String smsTemplateId)  throws Exception {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3101ReqVO tx3101ReqVO = new Tx3101ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        ProxySignVO proxySignVO = new ProxySignVO();
        proxySignVO.setUserId(userId);
        proxySignVO.setProjectCode(projectCode);
//        proxySignVO.setIsSendVoice(1);
        proxySignVO.setSmsTemplateId(smsTemplateId);
//        proxySignVO.setSmsRemark("2333333");
//        proxySignVO.setEmailTemplateId("1");
//        proxySignVO.setEmailRemark("312313");

        tx3101ReqVO.setHead(head);
        tx3101ReqVO.setProxySign(proxySignVO);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3101ReqVO);
        logger.info("personRegister req:{}",req);

        String txCode = "3101";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        logger.info("personRegister res:{}",res);
    }

    /**
     * 确认验证码
     * @param userId
     * @param projectCode
     * @param checkCode
     * @throws Exception
     */
    public void confirmSmsCode(String userId,String projectCode,String checkCode) throws Exception{
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3102ReqVO tx3102ReqVO = new Tx3102ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        ProxySignVO proxySignVO = new ProxySignVO();
        proxySignVO.setUserId(userId);
        proxySignVO.setProjectCode(projectCode);
        proxySignVO.setCheckCode(checkCode);

        tx3102ReqVO.setHead(head);
        tx3102ReqVO.setProxySign(proxySignVO);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3102ReqVO);
        logger.info("req:" + req);

        String txCode = "3102";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        logger.info("res:" + res);
    }

    public void createConstract(String templateId,String contractName,String userId,String location,
                                String projectCode,String signLocation,String authorizationTime) throws Exception{
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3201ReqVO tx3201ReqVO = new Tx3201ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        Map<String, String> fieldMap = new HashMap<String, String>();
        CreateContractVO createContract = new CreateContractVO();

        // createContract.setIsSign(1);
        // createContract.setSealId("16CD3E1B621E402ABC179A646A717BA7");
        createContract.setTemplateId(templateId);
        createContract.setContractName(contractName);
        createContract.setSealColor(SealColor.RED);
        // createContract.setSignLocation("Signature1");
//        fieldMap.put("1", "Text1");
//        fieldMap.put("2", "Text2");
//        fieldMap.put("3", "Text3");
//        fieldMap.put("4", "孙一");
//        fieldMap.put("5", "222321199112050001");
//        fieldMap.put("6", "1");
//        fieldMap.put("Text7", "1");
//        fieldMap.put("Text8", "1");
//        fieldMap.put("Text9", "10000");
//        fieldMap.put("Text10", "1");
//        fieldMap.put("Text11", "10000");
//        fieldMap.put("Text12", "1");
//        fieldMap.put("Text13", "10000");
//        fieldMap.put("Text14", "1");
//        fieldMap.put("Text15", "30000");
//        fieldMap.put("Text16", "10000");
//        fieldMap.put("Text17", "10000");
//        fieldMap.put("Text18", "10000");
//        fieldMap.put("Text19", "1");
//        fieldMap.put("Text20", "1");

        fieldMap.put("Text1", "");
        fieldMap.put("Text1", "");

        // setInvestmentInfo方法废弃，使用setTextValueInfo代替
        // createContract.setInvestmentInfo(fieldMap);
        createContract.setTextValueInfo(fieldMap);
        createContract.setIsSaveTextValue(1);
        createContract.setIsFillInFont(1);

        SignInfoVO[] signInfos = new SignInfoVO[2];
        SignInfoVO signInfoVO0 = new SignInfoVO();
        signInfoVO0.setUserId(userId);
        signInfoVO0.setIsProxySign(1);
        signInfoVO0.setLocation(location);
        signInfoVO0.setProjectCode(projectCode);
        // signInfoVO0.setIsCheckProjectCode(1);
        signInfoVO0.setSignLocation(signLocation);
        signInfoVO0.setAuthorizationTime(authorizationTime);
        // signInfoVO0.setSealId("6ACEC0B88FC44FD2AB2166D1320ADCEF");
        signInfos[0] = signInfoVO0;

        SignInfoVO signInfoVO1 = new SignInfoVO();
        signInfoVO1.setUserId("96428FD7E441461DA91C797EC0B02CED");
        signInfoVO1.setIsProxySign(0);
        signInfos[1] = signInfoVO1;

        createContract.setSignInfos(signInfos);

        // 使用水印时用到
        // WatermarkVO watermark = new WatermarkVO();
        // watermark.setWatermarkOnPage("1");
        // watermark.setWatermarkLBX("100");
        // watermark.setWatermarkLBY("100");
        // watermark.setWatermarkWidth("100");
        // watermark.setWatermarkHeight("100");
        // watermark.setWatermarkData(Base64.toBase64String(Files.readAllBytes(Paths.get("./image/王五.png"))));
        // createContract.setWatermark(watermark);

        // 使用附件时用到
        AttachmentVO attachment = new AttachmentVO();
        attachment.setAttachmentName("123.txt");
        attachment.setAttachmentContent("11111王五");
        createContract.setAttachment(attachment);
        // createContract.setIsFillInContractNo(1);

        tx3201ReqVO.setHead(head);
        tx3201ReqVO.setCreateContract(createContract);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3201ReqVO);
        System.out.println("req:" + req);

        String txCode = "3201";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        System.out.println("res:" + res);
    }
}
