package com.paidang.service;

import cfca.trustsign.common.constant.SealColor;
import cfca.trustsign.common.vo.cs.*;
import cfca.trustsign.common.vo.request.tx3.*;
import com.base.api.ApiException;
import com.base.util.DateUtil;
import com.base.util.JSONUtils;
import com.base.util.PropertySupport;
import com.demo.constant.HttpConnector;
import com.demo.constant.Request;
import com.demo.converter.JsonObjectMapper;
import com.demo.util.SecurityUtil;
import com.demo.util.TimeUtil;
import com.paidang.dao.model.PawnContinue;
import com.paidang.dao.model.PawnTicket;
import com.paidang.dao.model.UserPawn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
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
//        System.out.println(personRegister("320483199112215013","许文炜","18861269725"));
//        sendSmsCode("B49A56D031274CF2E05311016B0A242C","123456",null);
//        confirmSmsCode("B49A56D031274CF2E05311016B0A242C","123456","038945");
//
//        System.out.println(companyRegister("常州宝祥典当行有限公司", "913204007796879842", "18861269725", "05198738328",
//                "320483199112215013", "320483199112215013"));
        //B6E70740DFCD2236E05311016B0AAAF2

//        sendSmsCode("B6E70740DFCD2236E05311016B0AAAF2","123456",null);
        //https://IP:Port/FEP/platId/{platId}/contractNo/{contractNo}/downloading
        confirmSmsCode("B6E70740DFCD2236E05311016B0AAAF2","123456","471318");
    }


    public static String getContractUrl(String contractNo){
        String anxinUrl = PropertySupport.getProperty("anxinSign.url");
        String url =  "platId/"+Request.PLAT_ID+"/contractNo/"+contractNo+"/downloading";
        return url;
    }

    public static void getContractFile(String contractNo) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        byte[] fileBtye = httpConnector.getFile("platId/" + Request.PLAT_ID + "/contractNo/" + contractNo + "/downloading");
        if (fileBtye == null || fileBtye.length == 0) {
            return;
        }

        try {
            String filePath = "./file";
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(filePath + File.separator + contractNo + ".pdf");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(fileBtye);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public static String  companyRegister(String orgName,String identNo,String mobilePhone,String laneLinePhone,String transactorName
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
        logger.info("companyRegister req:{}",req);

        String txCode = "3002";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        logger.info("companyRegister res:{}",res);
        Map map = JSONUtils.deserialize(res, Map.class);
        if (map.containsKey("errorMessage")){
            throw new ApiException(400,(String)map.get("errorMessage"));
        }
        Map enterpriseMap = (Map)map.get("enterprise");
        String userId = (String)enterpriseMap.get("userId");
        return userId;

    }

    /**
     * 3001or3002开户接口——3101+3102短信授权接口——3201上传合同模板并签署接口
     */


    /**
     * 发送短信验证码
     * @param userId
     * @param projectCode
     */
    public static void sendSmsCode(String userId,String projectCode,String smsTemplateId)  throws Exception {
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
        logger.info("sendSmsCode req:{}",req);

        String txCode = "3101";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        logger.info("sendSmsCode res:{}",res);
        Map map = JSONUtils.deserialize(res, Map.class);
        if (map.containsKey("errorCode")){
            throw new ApiException(400,(String)map.get("errorMessage"));
        }
    }

    /**
     * 确认验证码
     * @param userId
     * @param projectCode
     * @param checkCode
     * @throws Exception
     */
    public static Date confirmSmsCode(String userId,String projectCode,String checkCode) throws Exception{
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
        logger.info("confirmSmsCode req:" + req);

        String txCode = "3102";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        logger.info("confirmSmsCode res:" + res);
        Map map = JSONUtils.deserialize(res, Map.class);
        if (map.containsKey("errorCode")){
            throw new ApiException(400,(String)map.get("errorMessage"));
        }
        return TimeUtil.getDateInFormat(head.getTxTime(),TimeUtil.FORMAT_14);
    }

    /**
     * 创建典当合同
     * @param templateId
     * @param contractName
     * @param userId
     * @param userLocation
     * @param projectCode
     * @param authorizationTime
     * @param orgUserId
     * @param orgLocation
     * @param orgAuthorizationTime
     * @param ticket
     * @return
     * @throws Exception
     */
    public  static String createContract(String templateId, String contractName, String userId, String userLocation,
                                           String projectCode,  Date authorizationTime,String orgUserId,String orgLocation,
                                           Date orgAuthorizationTime, PawnTicket ticket) throws Exception{
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3201ReqVO tx3201ReqVO = new Tx3201ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        Map<String, String> fieldMap = new HashMap<String, String>();
        CreateContractVO createContract = new CreateContractVO();

        createContract.setIsSign(0);
        // createContract.setSealId("16CD3E1B621E402ABC179A646A717BA7");
        createContract.setTemplateId(templateId);
        createContract.setContractName(contractName);
        createContract.setSealColor(SealColor.RED);

        fieldMap.put("1", ticket.getPawnTicketCode());//当票号
        fieldMap.put("2", ticket.getOrgName());//典当行名称
        fieldMap.put("3", ticket.getOrgTel());//典当行电话
        fieldMap.put("4", ticket.getOrgAdress());//典当行地址
        fieldMap.put("5", ticket.getBusinessLicense());//典当行经营许可证编号
        fieldMap.put("6", ticket.getPawnerName());//当户名称
        fieldMap.put("7", ticket.getPawnerTel());//当户联系电话
        fieldMap.put("8", ticket.getPawnerAddr());//当户地址
        fieldMap.put("9", ticket.getPawnerName());//当户联系人
        fieldMap.put("10", ticket.getPawnerCert());//当户证件名称
        fieldMap.put("11", ticket.getPawnerIdCard());//当户证件号码
        fieldMap.put("12", ticket.getGoodsName());//当物名称
        fieldMap.put("13", "件");//当物规格及状况
        fieldMap.put("14", "1");//当物数量
        fieldMap.put("15", ticket.getAuthPriceTest());//当物估价
        fieldMap.put("16", ticket.getEquivalentRatio());//折当率
        fieldMap.put("17",ticket.getLoanMoney());//典当金额
        fieldMap.put("18", ticket.getLoanMoneyCN());//典当金额大写
        fieldMap.put("19", ticket.getLoanMoney());//合计
        fieldMap.put("20", ticket.getRate()+"%");//月费率
        fieldMap.put("21", ticket.getMoneyRate()+"%");//月利率
        fieldMap.put("22", ticket.getCostCN());//综合费用大写
        fieldMap.put("23", ticket.getCost());//综合费用小写
        fieldMap.put("24", ticket.getUserMoneyCN());//实付金额大写
        fieldMap.put("25", ticket.getUserMoney());//实付金额小写
        fieldMap.put("26", ticket.getPawnBeginTime());//开始时间
        fieldMap.put("27", ticket.getPawnEndTime());//结束时间
//        fieldMap.put("28", ticket.getRemark());//备注
        fieldMap.put("29", ticket.getOtherOrder());//其它约定
        fieldMap.put("30", ticket.getChecker());//复核
        fieldMap.put("31", ticket.getHandler());//经办
        fieldMap.put("32", DateUtil.format(new Date(),"yyyy-MM-dd"));//制单时间
//        fieldMap.put("33", "");//当户签章
//        fieldMap.put("34", "");//典当行签章



        // setInvestmentInfo方法废弃，使用setTextValueInfo代替
        // createContract.setInvestmentInfo(fieldMap);
        createContract.setTextValueInfo(fieldMap);
        createContract.setIsSaveTextValue(1);
        createContract.setIsFillInFont(1);

        SignInfoVO[] signInfos = new SignInfoVO[2];
        SignInfoVO signInfoVO0 = new SignInfoVO();
        signInfoVO0.setUserId(userId);
        signInfoVO0.setIsProxySign(1);//0：不代签；1：代签；默认为0。不代签的用户，可自行登录安心签使用自己的数字证书完成协议签署。
        signInfoVO0.setLocation(userLocation);//客户平台将采集到的用户IP或所在地发送给安心签
        signInfoVO0.setProjectCode(projectCode);//如果确定代签，必须保证代签的用户已经对该项目进行了授权
        // signInfoVO0.setIsCheckProjectCode(1);
        signInfoVO0.setSignLocation("33");//签名域的标签值
        signInfoVO0.setAuthorizationTime(TimeUtil.getTimeInFormat(authorizationTime,TimeUtil.FORMAT_14));//客户平台将采集到的用户输入验证码的时间发送给安心签，格式：yyyyMMddHHmmss
        // signInfoVO0.setSealId("6ACEC0B88FC44FD2AB2166D1320ADCEF");
        signInfos[0] = signInfoVO0;

        SignInfoVO signInfoVO1 = new SignInfoVO();
        signInfoVO1.setUserId(orgUserId);
        signInfoVO1.setIsProxySign(1);
        signInfoVO1.setAuthorizationTime(TimeUtil.getTimeInFormat(orgAuthorizationTime,TimeUtil.FORMAT_14));
        signInfoVO1.setSignLocation("34");
        signInfoVO1.setProjectCode(projectCode);
        signInfoVO1.setLocation(orgLocation);

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
//        AttachmentVO attachment = new AttachmentVO();
//        attachment.setAttachmentName("123.txt");
//        attachment.setAttachmentContent("11111王五");
//        createContract.setAttachment(attachment);
        // createContract.setIsFillInContractNo(1);

        tx3201ReqVO.setHead(head);
        tx3201ReqVO.setCreateContract(createContract);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3201ReqVO);
        System.out.println("createContract req:" + req);

        String txCode = "3201";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        logger.info("createContract res {}",res);
        Map map = JSONUtils.deserialize(res, Map.class);
        Map contractMap =(Map) map.get("contract");
        String contractNo = (String)contractMap.get("contractNo");
        return contractNo;
    }


    /**
     * 创建续合同
     * @param templateId
     * @param contractName
     * @param userId
     * @param userLocation
     * @param projectCode
     * @param authorizationTime
     * @param orgUserId
     * @param orgLocation
     * @param orgAuthorizationTime
     * @param ticket
     * @return
     * @throws Exception
     */
    public  static String createXdContract(String templateId, String contractName, String userId, String userLocation,
                                           String projectCode, Date authorizationTime, String orgUserId, String orgLocation,
                                           Date orgAuthorizationTime, PawnTicket ticket, UserPawn userPawn) throws Exception{
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3201ReqVO tx3201ReqVO = new Tx3201ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        Map<String, String> fieldMap = new HashMap<String, String>();
        CreateContractVO createContract = new CreateContractVO();

        createContract.setIsSign(0);
        // createContract.setSealId("16CD3E1B621E402ABC179A646A717BA7");
        createContract.setTemplateId(templateId);
        createContract.setContractName(contractName);
        createContract.setSealColor(SealColor.RED);

        fieldMap.put("1", ticket.getPawnTicketCode());//当票号
        fieldMap.put("2", ticket.getOrgName());//典当行名称
        fieldMap.put("3", userPawn.getPawnTicketCode());//原当票好
        fieldMap.put("4", ticket.getPawnerName());//当户名称
        fieldMap.put("5", ticket.getPawnerTel());//当户联系电话
        fieldMap.put("6", ticket.getLoanMoneyCN());//原典当金额大写
        fieldMap.put("7", ticket.getLoanMoney());//原典当金额
        fieldMap.put("8", ticket.getCostCN());//典当综合费用大
        fieldMap.put("9", ticket.getCost());//典当综合费用
        fieldMap.put("10", ticket.getMoneyCostCN());//当户应付上期利息 大
        fieldMap.put("11", ticket.getMoneyCost());//当户应付上期利息
        fieldMap.put("12", ticket.getRepawnTotalCN());//当户总计交付金额 大
        fieldMap.put("13", ticket.getRepawnTotal());//当户总计交付金额
        fieldMap.put("14", ticket.getRepawnBeginTime());//开始日期
        fieldMap.put("15", ticket.getRepawnEndTime());//结束日期
        fieldMap.put("16", ticket.getRate());//月费率
        fieldMap.put("17",ticket.getMoneyRate());//月利率
        fieldMap.put("20", ticket.getChecker());//复核
        fieldMap.put("21", ticket.getHandler());//经办
        fieldMap.put("22", ticket.getChecker());//保管
        fieldMap.put("23", DateUtil.format(new Date(),"yyyy-MM-dd"));//制单时间


//        fieldMap.put("33", "");//当户签章
//        fieldMap.put("34", "");//典当行签章



        // setInvestmentInfo方法废弃，使用setTextValueInfo代替
        // createContract.setInvestmentInfo(fieldMap);
        createContract.setTextValueInfo(fieldMap);
        createContract.setIsSaveTextValue(1);
        createContract.setIsFillInFont(1);

        SignInfoVO[] signInfos = new SignInfoVO[2];
        SignInfoVO signInfoVO0 = new SignInfoVO();
        signInfoVO0.setUserId(userId);
        signInfoVO0.setIsProxySign(1);//0：不代签；1：代签；默认为0。不代签的用户，可自行登录安心签使用自己的数字证书完成协议签署。
        signInfoVO0.setLocation(userLocation);//客户平台将采集到的用户IP或所在地发送给安心签
        signInfoVO0.setProjectCode(projectCode);//如果确定代签，必须保证代签的用户已经对该项目进行了授权
        // signInfoVO0.setIsCheckProjectCode(1);
        signInfoVO0.setSignLocation("18");//签名域的标签值
        signInfoVO0.setAuthorizationTime(TimeUtil.getTimeInFormat(authorizationTime,TimeUtil.FORMAT_14));//客户平台将采集到的用户输入验证码的时间发送给安心签，格式：yyyyMMddHHmmss
        // signInfoVO0.setSealId("6ACEC0B88FC44FD2AB2166D1320ADCEF");
        signInfos[0] = signInfoVO0;

        SignInfoVO signInfoVO1 = new SignInfoVO();
        signInfoVO1.setUserId(orgUserId);
        signInfoVO1.setIsProxySign(1);
        signInfoVO1.setAuthorizationTime(TimeUtil.getTimeInFormat(orgAuthorizationTime,TimeUtil.FORMAT_14));
        signInfoVO1.setSignLocation("19");
        signInfoVO1.setProjectCode(projectCode);
        signInfoVO1.setLocation(orgLocation);

        signInfos[1] = signInfoVO1;

        createContract.setSignInfos(signInfos);

        tx3201ReqVO.setHead(head);
        tx3201ReqVO.setCreateContract(createContract);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3201ReqVO);
        logger.info("createXdContract req:" + req);

        String txCode = "3201";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        logger.info("createXdContract res {}",res);
        Map map = JSONUtils.deserialize(res, Map.class);
        Map contractMap =(Map) map.get("contract");
        String contractNo = (String)contractMap.get("contractNo");
        return contractNo;
    }
}
