package com.demo.test;

import cfca.sadk.algorithm.common.PKIException;
import cfca.trustsign.common.vo.cs.EnterpriseTransactorVO;
import cfca.trustsign.common.vo.cs.EnterpriseVO;
import cfca.trustsign.common.vo.cs.HeadVO;
import cfca.trustsign.common.vo.request.tx3.Tx3002ReqVO;
import com.demo.constant.HttpConnector;
import com.demo.constant.Request;
import com.demo.converter.JsonObjectMapper;
import com.demo.util.SecurityUtil;


public class Test3002 {
    public static void main(String[] args) throws PKIException {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3002ReqVO tx3002ReqVO = new Tx3002ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime("20160102235959");

        EnterpriseVO enterprise = new EnterpriseVO();
        enterprise.setEnterpriseName("XX公司");
        enterprise.setIdentTypeCode("8");
        enterprise.setIdentNo("1100000179095628");
        enterprise.setMobilePhone("13809074508");
        enterprise.setLandlinePhone("69226906");
        // enterprise.setEmail("11900129@cfca.com.cn");
        enterprise.setAuthenticationMode("公安部");

        EnterpriseTransactorVO enterpriseTransactor = new EnterpriseTransactorVO();
        enterpriseTransactor.setTransactorName("王五");
        enterpriseTransactor.setIdentTypeCode("1");
        enterpriseTransactor.setIdentNo("362321199112050011");
        // enterpriseTransactor.setAddress("beijing");

        tx3002ReqVO.setHead(head);
        tx3002ReqVO.setEnterprise(enterprise);
        tx3002ReqVO.setEnterpriseTransactor(enterpriseTransactor);
        // tx3002ReqVO.setSealColor(2);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3002ReqVO);
        System.out.println("req:" + req);

        String txCode = "3002";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        System.out.println("res:" + res);
    }
}
