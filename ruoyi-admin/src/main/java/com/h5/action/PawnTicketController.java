package com.h5.action;

import com.alibaba.fastjson.JSONObject;
import com.base.annotation.ApiMethod;
import com.base.util.CoreConstants;
import com.base.util.JSONUtils;
import com.base.util.Md5;
import com.item.dao.model.User;
import com.item.service.UserService;
import com.paidang.daoEx.model.PawnContinueEx;
import com.paidang.daoEx.model.UserPawnEx;
import com.paidang.service.PawnContinueService;
import com.paidang.service.UserPawnService;
import com.qiyuesuo.QysService;
import com.ruoyi.common.core.domain.Ret;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by Lee on 2017/11/9.
 *
 * 查看当票
 *
 */
@RequestMapping("/m/pawn")
@Controller
@Api(tags = "h5")
public class PawnTicketController extends H5BaseController {

    @Autowired
    private UserPawnService userPawnService;
    @Autowired
    private PawnContinueService pawnContinueService;
    @Autowired
    private UserService userService;
    @Autowired
    private QysService qysService;


    public static final String  URL= CoreConstants.getProperty("electronic_signature_url")+"/v2/seal/image";
    public static final String  APP_TOKEN= CoreConstants.getProperty("electronic_signature_app_token");
    public static final String  APP_SECRET= CoreConstants.getProperty("electronic_signature_app_secret");

    @ApiOperation(value = "查看合同")
    @RequestMapping(value = "/showContract/{contractId}",method = {RequestMethod.POST})
    @ResponseBody
    @ApiMethod
    public Object showContract(@PathVariable String contractId){
        String pageUrl = qysService.getPageUrl(Long.valueOf(contractId));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageUrl",pageUrl);
        return jsonObject;
    }

    @PostMapping("/loadTicketData")
    @ResponseBody
    @ApiOperation("加载当票数据")
    @ApiMethod
    public Object loadTicketData(String pawnId){
        if (StringUtils.isEmpty(pawnId)){
            return JSONUtils.serialize(new Ret(0,"典当id不能为空"));
        }
        Integer pid ;
        try{
            pid = Integer.valueOf(pawnId);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return JSONUtils.serialize(new Ret(0,"请输入正确的典当id"));
        }
        UserPawnEx userPawnEx = userPawnService.getUserPawnExById(pid.toString());
        if (userPawnEx == null){
            return JSONUtils.serialize(new Ret(0,"请输入正确的典当id"));
        }
        if (userPawnEx.getOrgId()==null){
            return JSONUtils.serialize(new Ret(0,"中标的典当机构信息为空"));
        }
        User pawner = userService.selectByPrimaryKey(userPawnEx.getUserId());
        if (pawner==null){
            return JSONUtils.serialize(new Ret(0,"未找到相应当户信息"));
        }
        List<Object> ret = new ArrayList<>();
        ret.add(userPawnEx.getContractId());
        //所有续当票
        List<PawnContinueEx> list = pawnContinueService.getRepawnTickInfo(pawnId);
        List<Object> repanRetList = new ArrayList<>();
        if (list.size() != 0){
            for (int i = 0 ; i < list.size() ; i++){
                repanRetList.add(list.get(i).getContractId());
            }
            ret.add(repanRetList);
        }
        return ret;
    }




    @RequestMapping("/getElectronicSignature/{sealId}")
    @ResponseBody
    public Object getElectronicSignature(@PathVariable(value = "sealId")String sealId, HttpServletResponse response1) throws IOException {
        Map<String,String> param = new HashMap<>();
        Map<String,String> header = setHeader();
        param.put("sealId",sealId);
        //创建一个默认的链接
        CloseableHttpClient client = HttpClients.createDefault();
        //创建一个请求
        HttpGet httpGet=new HttpGet(URL);
        //时间戳
        String timestamp = String.valueOf(new Date().getTime());
        //apptoken
        httpGet.setHeader("x-qys-open-accesstoken",APP_TOKEN);
        //timestamp
        httpGet.setHeader("x-qys-open-timestamp",timestamp);
        //Md5(AppToken + AppSecret + timestamp)
        httpGet.setHeader("x-qys-open-signature", Md5.mD5(APP_TOKEN+APP_SECRET+timestamp));
        //执行请求获取响应的结果
        CloseableHttpResponse response = client.execute(httpGet);
        //获取响应的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(entity, "utf-8"));
        System.out.println(jsonObject.toJSONString());
         System.out.println("服务器返回状态码："+statusCode);
         //服务器正常响应
         if(statusCode ==200&&(jsonObject.get("code").toString().equals("0"))){
         //得到实体
             byte[] data = EntityUtils.toByteArray(entity);
             OutputStream fos = response1.getOutputStream();
             fos.write(data);
             fos.close();
         }
         //关闭结果对象
         response.close();
         //关闭链接
         client.close();
         return jsonObject;
    }

    public Map<String,String> setHeader(){
        Map<String,String> header = new HashMap<>();


        return header;
    }



    @RequestMapping("/toPawnTicket/{id}")
    public String toPage(@PathVariable(value = "id") String pawnId, ModelMap map){
        map.put("pawnId",pawnId);
        return BASE_PATH+"/pawnTicket";
    }

/*    @RequestMapping("/toPawnTicket")
    public void toView(HttpServletRequest request,HttpServletResponse response){
       //String pawnId = request.getParameter("pawnId");
        try {
            request.getRequestDispatcher("/WEB-INF/page/h5/pawnTicket.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/




//    @RequestMapping("/loadTicketData")
//    @ResponseBody
//    public String loadTicketData(String pawnId){
//        if (StringUtils.isEmpty(pawnId)){
//            return JSONUtils.serialize(new Ret(0,"典当id不能为空"));
//        }
//        Integer pid ;
//        try{
//            pid = Integer.valueOf(pawnId);
//        }catch (NumberFormatException e){
//            e.printStackTrace();
//            return JSONUtils.serialize(new Ret(0,"请输入正确的典当id"));
//        }
//        UserPawnEx userPawnEx = userPawnService.getUserPawnExById(pid.toString());
//        if (userPawnEx == null){
//            return JSONUtils.serialize(new Ret(0,"请输入正确的典当id"));
//        }
//        if (userPawnEx.getOrgId()==null){
//            return JSONUtils.serialize(new Ret(0,"中标的典当机构信息为空"));
//        }
//        PawnOrg pawnOrg = pawnOrgService.selectByPrimaryKey(userPawnEx.getOrgId());
//        User pawner = userService.selectByPrimaryKey(userPawnEx.getUserId());
//        if (pawner==null){
//            return JSONUtils.serialize(new Ret(0,"未找到相应当户信息"));
//        }
//        List<Object> ret = new ArrayList<>();
//        BigDecimal beginMoney = userPawnEx.getBeginMoney();
//        BigDecimal pawnMoney = userPawnEx.getPawnMoney();// 首期综合费
//        BigDecimal userMoney;
//        PawnTicketModel pawnTicketModel = new PawnTicketModel();
//        pawnTicketModel.setPawnTicketCode(userPawnEx.getPawnTicketCode()!=null?userPawnEx.getPawnTicketCode():"");
//        pawnTicketModel.setAuthPrice(userPawnEx.getAuthPrice()!=null?userPawnEx.getAuthPrice().toString():"");
//        pawnTicketModel.setAuthPriceTest(userPawnEx.getAuthPrice()!=null?userPawnEx.getAuthPrice().toString():"");
//        pawnTicketModel.setBusinessLicense(pawnOrg.getBusinessLicenseCode()!=null?pawnOrg.getBusinessLicenseCode():"");//显示许可证图还是编码?
//        pawnTicketModel.setContactor(userPawnEx.getUserName()!=null?userPawnEx.getUserName():"");
//        pawnTicketModel.setEquivalentRatio("100%");
//        pawnTicketModel.setGoodsName(userPawnEx.getGoodsName1()!=null?userPawnEx.getGoodsName1():"");
//        pawnTicketModel.setLoanMoney(beginMoney!=null?beginMoney.toString():"");
//        pawnTicketModel.setPawnMoney(pawnMoney!=null?pawnMoney.toString():"");
//        if(beginMoney!=null && pawnMoney!=null){
//            userMoney = beginMoney.subtract(pawnMoney);
//            pawnTicketModel.setUserMoney(userMoney.toString());
//            pawnTicketModel.setUserMoneyCN(NumberToCN.number2CNMontrayUnit(userMoney));
//        }else{
//            pawnTicketModel.setUserMoney("");
//            pawnTicketModel.setUserMoneyCN("");
//        }
//        pawnTicketModel.setRate(userPawnEx.getRate()!=null?userPawnEx.getRate().toString():"");
//        pawnTicketModel.setMoneyRate(userPawnEx.getMoneyRate()!=null?userPawnEx.getMoneyRate().toString():"");
//        pawnTicketModel.setOrgName(userPawnEx.getOrgName()!=null?userPawnEx.getOrgName():"");
//        pawnTicketModel.setOrgAddr(userPawnEx.getOrgAddress()!=null?userPawnEx.getOrgAddress():"");
//        pawnTicketModel.setOrgTel(userPawnEx.getOrgTel()!=null?userPawnEx.getOrgTel():"");
//        pawnTicketModel.setBusinessLicense(pawnOrg.getBusinessLicenseCode()!=null?pawnOrg.getBusinessLicenseCode():"");
////      pawnTicketModel.setOrgSeal(userPawnEx.getOrgSeal()!=null?userPawnEx.getOrgSeal():"");
//        pawnTicketModel.setOrgSeal(pawnOrg.getSeal()!=null?pawnOrg.getSeal():"");
//        pawnTicketModel.setPawnerName(userPawnEx.getUserName()!=null?userPawnEx.getUserName().toString():"");
//        pawnTicketModel.setContactor(userPawnEx.getUserName()!=null?userPawnEx.getUserName().toString():"");
//        pawnTicketModel.setPawnerTel(userPawnEx.getUserPhone()!=null?userPawnEx.getUserPhone().toString():"");
//        pawnTicketModel.setPawnerAddr(pawner.getIdCardHand()!=null?pawner.getIdCardHand():"");
//        pawnTicketModel.setPawnerIdCard(userPawnEx.getUserIdCard()!=null?userPawnEx.getUserIdCard():"");
//        pawnTicketModel.setPawnerCert("身份证");
//        //典当开始日期
//        pawnTicketModel.setPawnBeginTime(userPawnEx.getPawnBeginTime()!=null? DateUtil.format(userPawnEx.getPawnBeginTime(),"yyyy-MM-dd"):"");
//        pawnTicketModel.setPawnEndTime(userPawnEx.getPawnEndTime()!=null?DateUtil.format(userPawnEx.getPawnEndTime(),"yyyy-MM-dd"):"");
//        //典当结束日期
//        pawnTicketModel.setBeginPawnEndTime(userPawnEx.getBeginPawnEndTime()!=null?DateUtil.format(userPawnEx.getBeginPawnEndTime(),"yyyy-MM-dd"):"");
//        pawnTicketModel.setLoanMoneyCN(beginMoney!=null?NumberToCN.number2CNMontrayUnit(beginMoney):"");
//        pawnTicketModel.setPawnMoneyCN(pawnMoney!=null?NumberToCN.number2CNMontrayUnit(pawnMoney):"");
//        ret.add(pawnTicketModel);
//        //所有续当票
//        List<PawnContinueEx> list = pawnContinueService.getRepawnTickInfo(pawnId);
//        List<RepawnTicketModel> repanRetList = new ArrayList<>();
//        if (list.size() != 0){
//            for (int i = 0 ; i < list.size() ; i++){
//                RepawnTicketModel repawnTicketModel = new RepawnTicketModel();
//                repawnTicketModel.setContactor(list.get(i).getUserName()!=null?list.get(i).getUserName():"");
//                repawnTicketModel.setMoneyRate(list.get(i).getMoneyRate()!=null?list.get(0).getMoneyRate().toString():"");
//                repawnTicketModel.setRate(list.get(i).getRate()!=null?list.get(i).getRate().toString():"");
//                repawnTicketModel.setLoanMoney(list.get(i).getLoanMoney()!=null?list.get(i).getLoanMoney().toString():"");
//                repawnTicketModel.setCost(list.get(i).getPawnMoney()!=null?list.get(i).getPawnMoney().toString():"");
//                repawnTicketModel.setMoneyCost(list.get(i).getPawnInterest()!=null?list.get(i).getPawnInterest().toString():"");
//                repawnTicketModel.setOrgName(list.get(i).getOrgName()!=null?list.get(i).getOrgName():"");
////              repawnTicketModel.setOrgSeal(list.get(i).getOrgSeal()!=null?list.get(i).getOrgSeal():"");
//                repawnTicketModel.setOrgSeal(pawnOrg.getSeal()!=null?pawnOrg.getSeal():"");
//                repawnTicketModel.setPawnerName(list.get(i).getPawnerName()!=null?list.get(i).getPawnerName():"");
//                repawnTicketModel.setPawnTicketCode(list.get(i).getPawnTicketCode()!=null?list.get(i).getPawnTicketCode():"");
//                //续当开始时间
//                repawnTicketModel.setRepawnBeginTime(list.get(i).getPawnLastEndTime()!=null?DateUtil.format(list.get(i).getPawnLastEndTime(),"yyyy-MM-dd"):"");
//                //续当结束时间
//                repawnTicketModel.setRepawnEndTime(list.get(i).getPawnEndTime()!=null?DateUtil.format(list.get(i).getPawnEndTime(),"yyyy-MM-dd"):"");
//                repawnTicketModel.setLoanMoneyCN(list.get(i).getLoanMoney()!=null?NumberToCN.number2CNMontrayUnit(list.get(i).getLoanMoney()):"");
//                repawnTicketModel.setCostCN(list.get(i).getPawnMoney()!=null?NumberToCN.number2CNMontrayUnit(list.get(i).getPawnMoney()):"");
//                repawnTicketModel.setMoneyCostCN(list.get(i).getPawnInterest()!=null?NumberToCN.number2CNMontrayUnit(list.get(i).getPawnInterest()):"");
//                if (list.get(i).getPawnMoney()==null || list.get(i).getPawnInterest()==null){
//                    repawnTicketModel.setRepawnTotal("");
//                    repawnTicketModel.setRepawnTotalCN("");
//                }else {
//                    BigDecimal repawnTotal = list.get(i).getPawnMoney().add(list.get(i).getPawnInterest());
//                    repawnTicketModel.setRepawnTotal(repawnTotal.toString());
//                    repawnTicketModel.setRepawnTotalCN(NumberToCN.number2CNMontrayUnit(repawnTotal));
//                }
//                repanRetList.add(repawnTicketModel);
//            }
//            ret.add(repanRetList);
//        }
//        return JSONUtils.serialize(ret);
//    }



}
