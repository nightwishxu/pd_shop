package com.paidang.action;

import com.alibaba.fastjson.JSONObject;
import com.base.util.CoreConstants;
import com.paidang.dao.model.PawnTicket;
import com.paidang.dao.model.PawnTicketExample;
import com.paidang.service.PawnTicketService;
import com.qiyuesuo.sdk.v2.utils.CryptUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/qys")
@Api(tags = "契约锁回调")
public class QysCallback {
    public static Logger logger = LoggerFactory.getLogger(QysCallback.class);

    @Autowired
    PawnTicketService pawnTicketService;

    public static String pawnSecretkey = CoreConstants.getProperty("pawnSecretkey");
    public static String pawnContinuesecretkey = CoreConstants.getProperty("pawnContinuesecretkey");

    @ApiOperation(value = "典当回调函数*", notes = "登陆")
    @RequestMapping(value = "/pawnCallback", method = RequestMethod.POST)
    public void pawnCallback(String signature, String timestamp,String content,HttpServletResponse response) throws IOException {
        try {
            logger.info("===============典当回调签名：{}================",signature);
            logger.info("===============典当回调时间戳：{}================",timestamp);
            logger.info("===============典当回调数据：{}================",content);
            String result = CryptUtils.aesDerypt(content, pawnSecretkey);
            logger.info("===============典当回调数据解密：{}================",result);
            JSONObject obj = JSONObject.parseObject(result);
            String contractId = obj.getString("contractId");
            String contractStatus = obj.getString("contractStatus");
            updatePawnTicket(contractId,contractStatus);

        }catch (Exception e){
            logger.error(e.getMessage());
        }finally{
            response.getWriter().write("success");
        }
    }

    @ApiOperation(value = "续当回调函数*", notes = "登陆")
    @RequestMapping(value = "/pawnContinueCallback", method = RequestMethod.POST)
    public void pawnContinueCallback(String signature, String timestamp,String content,HttpServletResponse response) throws IOException {
        try {
            logger.info("===============续当回调签名：{}================",signature);
            logger.info("===============续当回调时间戳：{}================",timestamp);
            logger.info("===============续当回调数据：{}================",content);
            String result = CryptUtils.aesDerypt(content, pawnContinuesecretkey);
            logger.info("===============续当回调数据解密：{}================",result);
            JSONObject obj = JSONObject.parseObject(result);
            String contractId = obj.getString("contractId");
            String contractStatus = obj.getString("contractStatus");
            updatePawnTicket(contractId,contractStatus);

        }catch (Exception e){
            logger.error(e.getMessage());
        }finally{
            response.getWriter().write("success");
        }
    }


    /**
     * 更新合同状态
     * @param contractId 合同ID
     * @param contractStatus 合同状态
     */
    public void updatePawnTicket(String contractId,String contractStatus){
        PawnTicketExample pawnTicketExample = new PawnTicketExample();
        pawnTicketExample.createCriteria()
                .andContractIdEqualTo(contractId);
        List<PawnTicket> pawnTickets = pawnTicketService.selectByExample(pawnTicketExample);
        PawnTicket pawnTicket = new PawnTicket();
        if(pawnTickets.size()>0){
            pawnTicket.setId(pawnTickets.get(0).getId());
            if(contractStatus.equals("COMPLETE")){
                pawnTicket.setStatus("2");
                pawnTicket.setSignTime(new Date());
            }
            if(contractStatus.equals("REJECTED")){
                pawnTicket.setStatus("3");
            }
            if(contractStatus.equals("INVALIDED")){
                pawnTicket.setStatus("4");
            }
            pawnTicketService.updateByPrimaryKeySelective(pawnTicket);
            logger.info("合同：{} 状态已修改为{}，状态码：{}",contractId,contractStatus,pawnTicket.getStatus());
        }



    }


}
