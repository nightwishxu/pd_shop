package com.api.action;

import com.alibaba.fastjson.JSONObject;
import com.api.util.PageLimit;
import com.base.annotation.ApiMethod;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.item.dao.model.User;
import com.item.service.UserService;
import com.paidang.dao.model.PawnTicket;
import com.paidang.dao.model.PawnTicketExample;
import com.paidang.service.PawnTicketService;
import com.qiyuesuo.QysService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/pawnTicketCenter", produces = {"application/json;charset=UTF-8"})
@Api(tags = "票据中心")
public class ApiPawnTicketCenterController  extends ApiBaseController{
    public static Logger logger = LoggerFactory.getLogger(ApiPawnTicketCenterController.class);

    @Autowired
    QysService qysService;
    @Autowired
    PawnTicketService pawnTicketService;
    @Autowired
    UserService userService;

    /**
     * 票据列表
     */
    @ApiOperation(value = "票据列表*", notes = "登陆")
    @RequestMapping(value = "/pawnTicketList", method = RequestMethod.POST)
    @ApiMethod(isLogin = true, isPage = true)
    public List<PawnTicket> pawnTicketList(MobileInfo mobileInfo, PageLimit pageLimit, String status) {
        Integer userid = mobileInfo.getUserId();
        User user = userService.selectByPrimaryKey(userid);
        PawnTicketExample pawnTicketExample = new PawnTicketExample();
        //查询用户真实姓名和手机号相同的票据
        PawnTicketExample.Criteria criteria = pawnTicketExample.createCriteria();
        criteria.andPawnerNameEqualTo(user.getName())
                .andPawnerTelEqualTo(user.getPhone());
        List<String> statusList=new ArrayList<>();
        if(status.equals("1")){
            statusList.add("0");
            statusList.add("1");
        }
        if(status.equals("2")){
            statusList.add("2");
            statusList.add("3");
            statusList.add("4");
        }
        criteria.andStatusIn(statusList);
        pawnTicketExample.setOrderByClause("create_time desc");
        startPage();
        List<PawnTicket> pawnTickets = pawnTicketService.selectByExample(pawnTicketExample);
        return pawnTickets;
    }

    /**
     * 签署
     */
    @ApiOperation(value = "生成合同开始签署*", notes = "登陆")
    @RequestMapping(value = "/signPawnTicket", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Object signPawnTicket(MobileInfo mobileInfo, Integer pawnTicketId) {
        PawnTicket pawnTicket = pawnTicketService.selectByPrimaryKey(pawnTicketId);
        if(ObjectUtils.isEmpty(pawnTicket)){
            throw new ApiException(9999,"票据不存在");
        }
        //如果已经开始签署了，不能重新生成合同
        if(!pawnTicket.getStatus().equals("0")){
            throw new ApiException(9999,"合同已经生成，不能重复生成");
        }
        //业务ID
//        Long categoryId = qysService.getCategoryId(pawnTicket.getType());
//        String contractId = qysService.createSendContract(categoryId, pawnTicket.getPawnerName(), pawnTicket.getPawnerTel(), pawnTicket.getOrgName(), pawnTicket.getOrgTel(), pawnTicket);
//        //通知用户
//        qysService.getAndSendShortUrl(Long.valueOf(contractId),pawnTicket.getPawnerTel());
//        qysService.getAndSendShortUrl(Long.valueOf(contractId),pawnTicket.getOrgTel());
//        //更新合同
//        PawnTicket pawnTicket1 = new PawnTicket();
//        pawnTicket1.setId(pawnTicket.getId());
//        pawnTicket1.setContractId(contractId);//合同文档Id
//        pawnTicket1.setStatus("1");//待签署
//        pawnTicketService.updateByPrimaryKeySelective(pawnTicket1);
        return new Ret();
    }


    /**
     * 查看合同
     */
    @ApiOperation(value = "查看合同*", notes = "登陆")
    @RequestMapping(value = "/showContract", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Object showContract(MobileInfo mobileInfo, Integer pawnTicketId) {
        PawnTicket pawnTicket = pawnTicketService.selectByPrimaryKey(pawnTicketId);
        String contractId= pawnTicket.getContractId();
        String pageUrl = qysService.getPageUrl(Long.valueOf(contractId));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageUrl",pageUrl);
        logger.info("==================合同查看地址:{}",pageUrl);
        return jsonObject;
    }


}
