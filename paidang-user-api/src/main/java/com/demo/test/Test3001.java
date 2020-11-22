package com.demo.test;

import cfca.sadk.algorithm.common.PKIException;
import cfca.trustsign.common.vo.BankCardInfoVO;
import cfca.trustsign.common.vo.cs.HeadVO;
import cfca.trustsign.common.vo.cs.PersonVO;
import cfca.trustsign.common.vo.request.tx3.Tx3001ReqVO;
import com.demo.connector.HttpConnector;
import com.demo.constant.Request;
import com.demo.converter.JsonObjectMapper;
import com.demo.util.SecurityUtil;
import com.demo.util.TimeUtil;


public class Test3001 {
    public static void main(String[] args) throws PKIException {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3001ReqVO tx3001ReqVO = new Tx3001ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        PersonVO person = new PersonVO();
        // person.setPersonName("xrma");
        // person.setIdentTypeCode("1");
        // person.setIdentNo("123123");
        person.setPersonName("刘**Liu Peig");
        person.setIdentTypeCode("Z");
        person.setIdentNo("110102200001071155");
        person.setMobilePhone("95010117759");
        // person.setEmail("235211@cfca.com.cn");
        // person.setAddress("北京");
        person.setAuthenticationMode("公安部");
        person.setIsOpenSM2(1);

        tx3001ReqVO.setHead(head);
        tx3001ReqVO.setPerson(person);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3001ReqVO);
        System.out.println("req:" + req);

        String txCode = "3001";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        System.out.println("res:" + res);
    }
}
