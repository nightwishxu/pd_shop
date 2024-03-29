package com.demo.test;

import cfca.sadk.algorithm.common.PKIException;
import cfca.trustsign.common.vo.cs.HeadVO;
import cfca.trustsign.common.vo.cs.ProxySignVO;
import cfca.trustsign.common.vo.request.tx3.Tx3101ReqVO;
import com.demo.constant.HttpConnector;
import com.demo.constant.Request;
import com.demo.converter.JsonObjectMapper;
import com.demo.util.SecurityUtil;
import com.demo.util.TimeUtil;


public class Test3101 {
    public static void main(String[] args) throws PKIException {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3101ReqVO tx3101ReqVO = new Tx3101ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        ProxySignVO proxySignVO = new ProxySignVO();
        proxySignVO.setUserId("67BBC2B9929C6DAAE055000000000001");
        proxySignVO.setProjectCode("003");
//        proxySignVO.setIsSendVoice(1);
//        proxySignVO.setSmsTemplateId("SMS-1");
//        proxySignVO.setSmsRemark("2333333");
//        proxySignVO.setEmailTemplateId("1");
//        proxySignVO.setEmailRemark("312313");

        tx3101ReqVO.setHead(head);
        tx3101ReqVO.setProxySign(proxySignVO);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3101ReqVO);
        System.out.println("req:" + req);

        String txCode = "3101";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.getPlatId() + "/txCode/" + txCode + "/transaction", req, signature);
        System.out.println("res:" + res);
    }
}
