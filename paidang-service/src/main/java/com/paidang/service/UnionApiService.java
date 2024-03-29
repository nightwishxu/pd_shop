package com.paidang.service;

import com.alibaba.fastjson.JSONObject;
import com.api.util.HttpsUtil;
import com.base.api.ApiException;
import com.base.util.ImageUtil;
import com.base.util.JSONUtils;
import com.demo.constant.DSPConsts;
import com.google.common.collect.Maps;
import com.item.dao.model.User;
import com.item.dao.model.UserExample;
import com.item.service.UserService;
import com.paidang.dao.model.UserFaceLog;
import com.paidang.domain.vo.UserFaceVo;
import cpcn.dsp.institution.api.notice.NoticeRequest;
import cpcn.dsp.institution.api.notice.NoticeResponse;
import cpcn.dsp.institution.api.security.EncryptAndDecrypt;
import cpcn.dsp.institution.api.tx.file.Tx3201Request;
import cpcn.dsp.institution.api.tx.file.Tx3201Response;
import cpcn.dsp.institution.api.tx.personalinfo.*;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2117Request;
import cpcn.dsp.institution.api.util.GUIDGenerator;
import cpcn.dsp.institution.api.vo.ImageFile;
import cpcn.dsp.institution.api.util.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;


@Service
public class UnionApiService {


    private static final Logger logger = LoggerFactory.getLogger(UnionApiService.class);



    @Autowired
    private UserService userService;

    @Autowired
    private UserFaceLogService userFaceLogService;





    /**
     * 2316 + 3201 身份证照片比对
     */


    /**
     * 2118 银行卡四要素验证
     */



    public static String uploadFile(String url) throws Exception{
        //1.初始化证书
        EncryptAndDecrypt.init(DSPConsts.Keystore, DSPConsts.myKeystorePassword, DSPConsts.publicKey);
        Tx3201Request tx3201Request = new Tx3201Request();
        tx3201Request.setTxSN(GUIDGenerator.genGUID());
        tx3201Request.setInstitutionID(DSPConsts.institutionID);// 机构号由中金分配
        ImageFile imageFile = new ImageFile();
        //图片转换为base64编码字符串
        String content = ImageUtil.ImageToBase64ByOnline(url);
        //去除空行
        content.replaceAll("\\\\n","");
        imageFile.setFileContent(content);
        imageFile.setFileName("aaa.jpg");
        List<ImageFile> list = new ArrayList<ImageFile>();
        list.add(imageFile);
        tx3201Request.setFiles(list);
        tx3201Request.setRemark("11");//仅测试环境判断该字段1->不一致,2->未知，3->请求失败，其他->一致

        //报文处理
        tx3201Request.process();
        String plainText = tx3201Request.getRequestPlainText();
        System.out.println("请求明文：" + plainText);

        String reqMessage = tx3201Request.getRequestMessage();
        String reqSignature = tx3201Request.getRequestSignature();
        String requestDgtlEnvlp = tx3201Request.getRequestDgtlEnvlp();
        String requestSignSN = tx3201Request.getRequestSignSN();
        String requestEncryptSN = tx3201Request.getRequestEncryptSN();
        //生成参数
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("message", reqMessage);
        paramMap.put("signature", reqSignature);
        paramMap.put("dgtlenvlp", requestDgtlEnvlp);
        paramMap.put("signSN", requestSignSN);
        paramMap.put("encryptSN", requestEncryptSN);
        //生成请求参数
        String requestString = EncryptAndDecrypt.createLinkStringByGet(paramMap);
        System.out.println("请求密文：" + requestString);
        //请求接口
        String result = HttpsUtil.post(DSPConsts.FILE_URL, requestString, null);
        logger.info("响应密文：" + result);
        String respPlainText = null;
        String[] respMsg = result.split(",");
        if (respMsg.length == 1 && -1 == respMsg[0].indexOf(',')) {
            respPlainText = Base64.decode(respMsg[0], "UTF-8");
            logger.info("uploadFile url:"+ url + " result:"+respPlainText);
            throw new ApiException(400,"图片上传失败");
        } else if (respMsg.length == 5) {
            String responseMessage = respMsg[0];
            String responseSignature = respMsg[1];
            String responseDgtlEnvlp = respMsg[2];
            String respSignSN = respMsg[3];
            String respEncryptSN = respMsg[4];

            Tx3201Response tx3201Response = new Tx3201Response(responseMessage, responseSignature, responseDgtlEnvlp, respSignSN, respEncryptSN);
            respPlainText = tx3201Response.getResponsePlainText();
            logger.info("uploadFile url:"+ url + " result:"+respPlainText);
            if ("2000".equals(tx3201Response.getCode())){
                //tx3201Response.getFiles().forEach(s -> System.out.println("文件id:" + s.getFileID()));
                //响应明文：{"Code":"2000","Files":[{"FileID":"2012090025213364119807720","FileName":"aaa.jpg"}],"InstitutionID":"100710","Message":"SUCCESS","TraceNo":"2012090025212566488950602","TxCode":"3201","TxSN":"202012090024378754551898318"}
                if (!"SUCCESS".equals(tx3201Response.getMessage())){
                    throw new ApiException(400,"图片验证失败");
                }
                String fileId = tx3201Response.getFiles().get(0).getFileID();
                return fileId;
            }else {
                throw new ApiException(400,"图片验证失败");
            }

            //也可通过如下方式获取明文
            //EncryptAndDecrypt encryptAndDecrypt = EncryptAndDecrypt.getEncryptAndDecrypt();
            //String randomKeyData2 = encryptAndDecrypt.getDecryptKeyByteByRSA(responseDgtlEnvlp);
            //plainText = EncryptAndDecrypt.decodeByAES(randomKeyData2, responseMessage);
        }
        throw new ApiException(400,"图片验证失败");

    }

    /**
     * 2316 身份证照片比对
     * @throws Exception
     */
    public static void validIdCard(String name,String idCard,String fileId ) throws Exception {

        //1.初始化
        EncryptAndDecrypt.init(DSPConsts.Keystore, DSPConsts.myKeystorePassword, DSPConsts.publicKey);
        //2.生成请求明文
        Tx2316Request Tx2316Request = new Tx2316Request();
        Tx2316Request.setInstitutionID(DSPConsts.institutionID);// 机构号由中金分配
        Tx2316Request.setTxSN(GUIDGenerator.genGUID());
        //原始请求流水号
        Tx2316Request.setName(name);
        Tx2316Request.setIdentificationNumber(idCard);
        Tx2316Request.setUploadFileID(fileId);
        Tx2316Request.setRemark("11");//仅测试环境判断该字段1->不一致,2->未知，3->请求失败，其他->一致
        //响应明文：{"Code":"2000","InstitutionID":"100710","Message":"SUCCESS","ResponseCode":"0000","ResponseMessage":"匹配","TraceNo":"2012081002419523479908262","TxCode":"2316","TxSN":"202012081002374255841440780","Verification":"20"}
        //响应明文：{"Code":"3005","InstitutionID":"100710","Message":"未查询到结果","TxCode":"2316","TxSN":"202012081002111044089663543"}
        //响应明文：{"Code":"2000","InstitutionID":"100710","Message":"SUCCESS","ResponseCode":"0001","ResponseMessage":"不匹配","TraceNo":"2012081003063106472566078","TxCode":"2316","TxSN":"202012081003048968564720074","Verification":"30"}
        //响应明文：{"Message":"请求通道出错","Code":"2008"}
        //报文处理
        Tx2316Request.process();
        String plainText = Tx2316Request.getRequestPlainText();
        System.out.println("请求明文：" + plainText);

        String reqMessage = Tx2316Request.getRequestMessage();
        String reqSignature = Tx2316Request.getRequestSignature();
        String requestDgtlEnvlp = Tx2316Request.getRequestDgtlEnvlp();
        String requestSignSN = Tx2316Request.getRequestSignSN();
        String requestEncryptSN = Tx2316Request.getRequestEncryptSN();
        //生成参数
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("message", reqMessage);
        paramMap.put("signature", reqSignature);
        paramMap.put("dgtlenvlp", requestDgtlEnvlp);
        paramMap.put("signSN", requestSignSN);
        paramMap.put("encryptSN", requestEncryptSN);
        //生成请求参数
        String requestString = EncryptAndDecrypt.createLinkStringByGet(paramMap);
        System.out.println("请求密文：" + requestString);
        //请求接口
        String result = HttpsUtil.post(DSPConsts.TX_URL, requestString, null);
        System.out.println("响应密文：" + result);

        String respPlainText = null;
        String[] respMsg = result.split(",");
        if (respMsg.length == 1 && -1 == respMsg[0].indexOf(',')) {
            respPlainText = Base64.decode(respMsg[0], "UTF-8");
        } else if (respMsg.length == 5) {
            String responseMessage = respMsg[0];
            String responseSignature = respMsg[1];
            String responseDgtlEnvlp = respMsg[2];
            String respSignSN = respMsg[3];
            String respEncryptSN = respMsg[4];

            Tx2316Response Tx2316Response = new Tx2316Response(responseMessage, responseSignature, responseDgtlEnvlp, respSignSN, respEncryptSN);
            respPlainText = Tx2316Response.getResponsePlainText();
            if ("2000".equals(Tx2316Response.getCode())){
                String verification = Tx2316Response.getVerification();
                if (!"20".equals(verification)){
                    throw new ApiException(400,"认证结果不匹配");
                }
                System.out.println("验证结果" + verification);
            }else {
                throw new ApiException(400,"验证失败："+Tx2316Response.getMessage());
            }

            //也可通过如下方式获取明文
            //EncryptAndDecrypt encryptAndDecrypt = EncryptAndDecrypt.getEncryptAndDecrypt();
            //String randomKeyData2 = encryptAndDecrypt.getDecryptKeyByteByRSA(responseDgtlEnvlp);
            //plainText = EncryptAndDecrypt.decodeByAES(randomKeyData2, responseMessage);
        }
        logger.info("响应明文：" + respPlainText);

    }


    /**
     * 银行卡校验
     * @param serialNm
     * @param name
     * @param cardNo
     * @param idCard
     * @param phone
     * @return
     * @throws Exception
     */
    public static Map<String,String>  validBankCard(String serialNm,String name,String cardNo,String idCard ,String phone) throws Exception {

        //1.初始化
        EncryptAndDecrypt.init(DSPConsts.Keystore, DSPConsts.myKeystorePassword, DSPConsts.publicKey);
        //2.生成请求明文
        Tx2117Request Tx2117Request = new Tx2117Request();
        Tx2117Request.setInstitutionID(DSPConsts.institutionID);// 机构号由中金分配
        Tx2117Request.setTxSN(GUIDGenerator.genGUID());
        //原始请求流水号
        Tx2117Request.setName(name);
        Tx2117Request.setScName("江苏誉道");
        Tx2117Request.setScUsageScenarios("银行卡绑卡核验");
        Tx2117Request.setScUsePurpose("交易使用");
        Tx2117Request.setIdentificationNumber(idCard);
        Tx2117Request.setProtocolVerNm("协议 v1.0.0");//授权协议版本号：客户签署授权机构方使用其个人信息的电子协议名称以及版本号
        Tx2117Request.setIdentificationType("0");
        Tx2117Request.setAccountNumber(cardNo);
        Tx2117Request.setTransCode("99");
        Tx2117Request.setSerialNm(serialNm);
        Tx2117Request.setRemark("12");//仅测试环境判断该字段1->不一致,2->未知，3->请求失败，其他->一致
        //响应明文：{"Code":"2000","InstitutionID":"100710","Message":"SUCCESS","ResponseCode":"0000","ResponseMessage":"匹配","TraceNo":"2012081002419523479908262","TxCode":"2316","TxSN":"202012081002374255841440780","Verification":"20"}
        //响应明文：{"Code":"3005","InstitutionID":"100710","Message":"未查询到结果","TxCode":"2316","TxSN":"202012081002111044089663543"}
        //响应明文：{"Code":"2000","InstitutionID":"100710","Message":"SUCCESS","ResponseCode":"0001","ResponseMessage":"不匹配","TraceNo":"2012081003063106472566078","TxCode":"2316","TxSN":"202012081003048968564720074","Verification":"30"}
        //响应明文：{"Message":"请求通道出错","Code":"2008"}
        //报文处理
        Tx2117Request.process();
        String plainText = Tx2117Request.getRequestPlainText();
        System.out.println("请求明文：" + plainText);

        String reqMessage = Tx2117Request.getRequestMessage();
        String reqSignature = Tx2117Request.getRequestSignature();
        String requestDgtlEnvlp = Tx2117Request.getRequestDgtlEnvlp();
        String requestSignSN = Tx2117Request.getRequestSignSN();
        String requestEncryptSN = Tx2117Request.getRequestEncryptSN();
        //生成参数
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("message", reqMessage);
        paramMap.put("signature", reqSignature);
        paramMap.put("dgtlenvlp", requestDgtlEnvlp);
        paramMap.put("signSN", requestSignSN);
        paramMap.put("encryptSN", requestEncryptSN);
        //生成请求参数
        String requestString = EncryptAndDecrypt.createLinkStringByGet(paramMap);
        System.out.println("请求密文：" + requestString);
        //请求接口
        String result = HttpsUtil.post(DSPConsts.TX_URL, requestString, null);
        System.out.println("响应密文：" + result);

        String respPlainText = null;
        String[] respMsg = result.split(",");
        if (respMsg.length == 1 && -1 == respMsg[0].indexOf(',')) {
            respPlainText = Base64.decode(respMsg[0], "UTF-8");
            throw new ApiException(400,"验证失败 1: "+respPlainText);
        } else if (respMsg.length == 5) {
            String responseMessage = respMsg[0];
            String responseSignature = respMsg[1];
            String responseDgtlEnvlp = respMsg[2];
            String respSignSN = respMsg[3];
            String respEncryptSN = respMsg[4];

            Tx2118Response Tx2118Response = new Tx2118Response(responseMessage, responseSignature, responseDgtlEnvlp, respSignSN, respEncryptSN);
            respPlainText = Tx2118Response.getResponsePlainText();
            logger.info("validBankCard ,response:"+respPlainText);
            if ("2000".equals(Tx2118Response.getCode())){
                String verification = Tx2118Response.getVerification();
                if (!"20".equals(verification)){
                    throw new ApiException(400,"认证结果不匹配");
                }
                String bankId = Tx2118Response.getBankID();
                String carType = Tx2118Response.getCardType();
                Map<String,String> res = Maps.newHashMap();
                res.put("bankId",bankId);
                res.put("carType",carType);
                return res;

            }else {
                throw new ApiException(400,"验证失败："+Tx2118Response.getMessage());
            }

            //也可通过如下方式获取明文
            //EncryptAndDecrypt encryptAndDecrypt = EncryptAndDecrypt.getEncryptAndDecrypt();
            //String randomKeyData2 = encryptAndDecrypt.getDecryptKeyByteByRSA(responseDgtlEnvlp);
            //plainText = EncryptAndDecrypt.decodeByAES(randomKeyData2, responseMessage);
        }else {
            throw new ApiException(400,"验证失败");
        }


    }



    public static Tx2324Request userFace(String userName,String idCard) throws Exception{

        //1.初始化
        EncryptAndDecrypt.init(DSPConsts.Keystore, DSPConsts.myKeystorePassword, DSPConsts.publicKey);
        //2.生成请求明文
        JSONObject json = new JSONObject();
        Tx2324Request tx2324Request = new Tx2324Request();
        tx2324Request.setInstitutionID(DSPConsts.institutionID);// 机构号由中金分配
        tx2324Request.setTxSN(GUIDGenerator.genGUID());
        tx2324Request.setName(userName);
        tx2324Request.setIdentificationNumber(idCard);
        tx2324Request.setFrontUrl(DSPConsts.hostUrl +"h5/userFaceResult");//需要更换为外网可以访问的ip
        tx2324Request.setFrontMethod("0");
        tx2324Request.setReturnImage("0");
        tx2324Request.setBackUrl(DSPConsts.adminHostUrl + "notify/receiveNoticeBackground");//需要更换为外网可以访问的ip
        tx2324Request.setRemark("33");//仅测试环境判断该字段1->不一致,2->未知，3->请求失败，其他->一致
        //报文处理
        tx2324Request.process();


        String plainText = tx2324Request.getRequestPlainText();
        System.out.println("请求明文：" + plainText);

        String reqMessage = tx2324Request.getRequestMessage();
        String reqSignature = tx2324Request.getRequestSignature();
        String requestDgtlEnvlp = tx2324Request.getRequestDgtlEnvlp();
        String requestSignSN = tx2324Request.getRequestSignSN();
        String requestEncryptSN = tx2324Request.getRequestEncryptSN();

        ///////////////////////////////////////////////
        //在活体验证提交页面.html中以FORM表单形式进行提交///
        ///////////////////////////////////////////////
        System.out.println("reqMessage=>" + reqMessage);
        System.out.println("reqSignature=>" + reqSignature);
        System.out.println("requestDgtlEnvlp=>" + requestDgtlEnvlp);
        System.out.println("requestSignSN=>" + requestSignSN);
        System.out.println("requestEncryptSN=>"+ requestEncryptSN);

        return tx2324Request;
        //提交地址： LIVENESS_URL

    }

    public  UserFaceVo receiveNotifyBack(HttpServletRequest request){
        logger.info("receiveNotifyBack start");
        String message = request.getParameter("Message");
        String signature = request.getParameter("Signature");
        String dgtlenvlp = request.getParameter("Dgtlenvlp");
        String signSN = request.getParameter("SignSN");
        String encryptSN = request.getParameter("EncryptSN");
        //解析
        //1.初始化
        logger.info("receiveNotifyBack: message:{},signature:{},dgt:{},sign:{},encrypt:{}",message,signature,dgtlenvlp,signSN,encryptSN);
        try {
            EncryptAndDecrypt.init(DSPConsts.Keystore, DSPConsts.myKeystorePassword, DSPConsts.publicKey);
            NoticeRequest noticeRequest = new NoticeRequest(message, signature, dgtlenvlp, signSN, encryptSN);
            logger.info(JSONUtils.serialize(noticeRequest));
            String plainText = noticeRequest.getPlainText();
            String txCode = noticeRequest.getTxCode();
            String code = noticeRequest.getCode();
            String noticeMessage = noticeRequest.getMessage();

            Map<String,String> map = JSONUtils.deserialize(plainText, Map.class);
            String txsn = map.get("TxSN");
            String Verification = map.get("Verification");
            String ResponseMessage = map.get("ResponseMessage");
            UserFaceVo vo = new UserFaceVo();
            vo.setState(Verification);
            vo.setMessage(ResponseMessage);
            UserFaceLog log = userFaceLogService.getByTxsn(txsn);
            if (log!=null){
                log.setResult(noticeMessage);
                log.setModifyTime(new Date());
                User user = userService.selectByPrimaryKey(log.getUserId());
                if ("20".equals(Verification)){
                    //成功

                    if (log.getType()==1){
                        User temp = new User();
                        temp.setId(user.getId());
                        temp.setAuthStatus(4);
                        temp.setIsBind(1);
                        userService.updateByPrimaryKeySelective(temp);
                    }else if (log.getType()==2){

                    }
                }else {
                    if (log.getType()==1){
                        User temp = new User();
                        temp.setId(user.getId());
                        temp.setAuthStatus(3);
                        userService.updateByPrimaryKeySelective(temp);
                    }else if (log.getType()==2){

                    }
                }
                userFaceLogService.updateByPrimaryKeySelective(log);
            }
            logger.info("receiveNotifyBack ： " + noticeMessage);
            logger.info("receiveNotifyBack end");
            return vo;

//            //////////////////////////////////
//            //////解析明文后商户自己实现逻辑/////
//            /////////////////////////////////
//            //接收页面通知，响应的内容将会在识别结果页面展示
//            PrintWriter out = response.getWriter();
//            out.write(plainText);
//            out.flush();
//            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 活体验证后台回调
     * @param request
     * @param response
     */
    public void receiveNoticeBackground(HttpServletRequest request, HttpServletResponse response){
        logger.info("receiveNoticeBackground  start");
        String message = request.getParameter("Message");
        String signature = request.getParameter("Signature");
        String dgtlenvlp = request.getParameter("Dgtlenvlp");
        String signSN = request.getParameter("SignSN");
        String encryptSN = request.getParameter("EncryptSN");
        logger.info("receiveNoticeBackground: message:{},signature:{},dgt:{},sign:{},encrypt:{}",message,signature,dgtlenvlp,signSN,encryptSN);

        //解析
        //1.初始化
        try {
            EncryptAndDecrypt.init(DSPConsts.Keystore, DSPConsts.myKeystorePassword, DSPConsts.publicKey);
            NoticeRequest noticeRequest = new NoticeRequest(message, signature, dgtlenvlp, signSN, encryptSN);
            logger.info("receiveNoticeBackground result:{}",JSONUtils.serialize(noticeRequest));

            String plainText = noticeRequest.getPlainText();
            String txCode = noticeRequest.getTxCode();
            String code = noticeRequest.getCode();
            String noticeMessage = noticeRequest.getMessage();
            System.out.println(plainText);
            Map<String,String> map = JSONUtils.deserialize(plainText, Map.class);
            String Verification = map.get("Verification");
            String ResponseMessage = map.get("ResponseMessage");
            String txsn = map.get("TxSN");
//            UserExample example = new UserExample();
//            example.createCriteria().andTxsnEqualTo(txsn);
//            List<User> users = userService.selectByExample(example);
            UserFaceLog log = userFaceLogService.getByTxsn(txsn);
            if (log!=null){
                log.setResult(noticeMessage);
                log.setModifyTime(new Date());
                User user = userService.selectByPrimaryKey(log.getUserId());
                if ("20".equals(Verification)){
                    //成功

                    if (log.getType()==1){
                        User temp = new User();
                        temp.setId(user.getId());
                        temp.setAuthStatus(4);
                        temp.setIsBind(1);
                        userService.updateByPrimaryKeySelective(temp);
                    }else if (log.getType()==2){

                    }
                }else {
                    if (log.getType()==1){
                        User temp = new User();
                        temp.setId(user.getId());
                        temp.setAuthStatus(3);
                        userService.updateByPrimaryKeySelective(temp);
                    }else if (log.getType()==2){

                    }
                }
                userFaceLogService.updateByPrimaryKeySelective(log);
            }

            logger.info("receiveNoticeBackground ： " + plainText);
            logger.info("receiveNoticeBackground noticeMessage: " + noticeMessage);
            ////////////////////////////////////////
            //////后台通知需要响应给数据服务SUCCESS/////
            ////////////////////////////////////////
            PrintWriter out = response.getWriter();
            BASE64Encoder encoder = new BASE64Encoder();
            String noticeResponse = NoticeResponse.getMessage();
            String data = encoder.encode(noticeResponse.getBytes("UTF-8"));
            out.write(data);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    public static void main(String[] args) throws Exception{
//        String fileId = uploadFile("http://app-cdn.starcharge.com/5e3da272-7282-41c4-ac3e-7dad4026b881.PNG");

        validBankCard("123","许","6227001260550180113","32021919870228002X","13771228227");
//        validIdCard("1","320483199112215013",fileId);
//        List<Integer> list = new ArrayList<>();
//        for (int i =1 ;i<=10;i++){
//            list.add(i);
//        }
//        saveBatch(3,list,null);
//        userFace();

    }




}
