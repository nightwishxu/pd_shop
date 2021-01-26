package com.action;

import com.api.model.CertificateLogModel;
import com.base.util.DateUtil;
import com.demo.constant.DSPConsts;
import com.item.dao.model.SinglePage;
import com.item.service.SinglePageService;
import com.paidang.dao.model.Certificate;
import com.paidang.dao.model.CertificateLog;
import com.paidang.dao.model.CertificateLogExample;
import com.paidang.dao.model.UserFaceLog;
import com.paidang.service.CertificateLogService;
import com.paidang.service.CertificateService;
import com.paidang.service.UnionApiService;
import com.base.api.ApiException;
import com.base.util.StringUtils;
import com.item.dao.model.User;
import com.item.service.UserService;
import com.paidang.service.UserFaceLogService;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2324Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserFaceController {

    @Autowired
    private UserService userService;

    @Autowired
    private SinglePageService singlePageService;

    @Autowired
    private UnionApiService unionApiService;

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private CertificateLogService certificateLogService;

    @Autowired
    private UserFaceLogService userFaceLogService;

    //html访问控制器
    /**
     * @return
     */
    @RequestMapping(value = "/h5/userFace")
    public String userFace(HttpServletRequest request, Model model)throws Exception {
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        if (userId==null){
            throw new ApiException(400,"缺少必要参数");
        }
        User user = userService.selectByPrimaryKey(Integer.parseInt(userId));
        User tmp = new User();
        if ("1".equals(type)){
            //实名认证时
            if (user.getIsBind()==1){
                throw new ApiException(400,"实名认证成功");
            }
            if (user.getAuthStatus()!=null && user.getAuthStatus()==4){
                throw new ApiException(400,"活体验证成功");
            }
        }else if ("2".equals(type)){
            //
        }else {
            throw new ApiException(400,"参数异常");
        }

        if (user.getIsBind()!=null && user.getIsBind()==1){
            if ( StringUtils.isAnyBlank(user.getName(),user.getIdCard())){
                throw new ApiException(400,"实名认证信息异常");
            }
        }else {
            if (user.getAuthStatus()==null || user.getAuthStatus()==0 ||  StringUtils.isAnyBlank(user.getName(),user.getIdCard())){
                throw new ApiException(400,"请先进行实名认证");
            }
        }

        Tx2324Request tx2324Request = UnionApiService.userFace(user.getName(), user.getIdCard());
        String txSN = tx2324Request.getTxSN();
        if ("1".equals(type)){
            tmp.setTxsn(txSN);
            tmp.setId(user.getId());
            userService.updateByPrimaryKeySelective(tmp);
        }
        model.addAttribute("url", DSPConsts.LIVENESS_URL);
        model.addAttribute("message",tx2324Request.getRequestMessage());
        model.addAttribute("signature",tx2324Request.getRequestSignature());
        model.addAttribute("dgtlEnvlp",tx2324Request.getRequestDgtlEnvlp());
        model.addAttribute("signSN",tx2324Request.getRequestSignSN());
        model.addAttribute("encryptSN",tx2324Request.getRequestEncryptSN());
        UserFaceLog log = new UserFaceLog();
        log.setUserId(user.getId());
        log.setCreateTime(new Date());
        log.setType(Integer.valueOf(type));
        log.setTxsn(txSN);
        userFaceLogService.insert(log);
        return "userFace";
    }


    @RequestMapping(value = "/h5/userFaceResult")
    public String userFaceNotify(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {

        String result = UnionApiService.receiveNotifyBack(request);
        model.addAttribute("result",result);
        model.addAttribute("code","SUCCESS".equals(result)?1:0);

        return "userFaceResult";
    }


    @RequestMapping(value = "/h5/userFaceResult/test")
    public String userFaceNotifyTest(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {

        String result = request.getParameter("result");
        model.addAttribute("result",result);
        model.addAttribute("code","SUCCESS".equals(result)?1:0);

        return "userFaceResult";
    }

    @RequestMapping(value = "/h5/singlePage")
    public String singlePage(HttpServletRequest request, Model model)throws Exception {
        String code = request.getParameter("code");
        SinglePage page = singlePageService.selectByPrimaryKey(code);
        model.addAttribute("content",page.getContent());
        return "detail";
    }

    @RequestMapping(value = "/h5/certificate")
    public String toCertificate(Integer id, ModelMap map){
        if (null == id){
            return "common/404";
        }
        Certificate certificate = certificateService.selectByPrimaryKey(id);
        if(null == certificate){
            return "common/404";
        }
        CertificateLogExample certificateLogExample = new CertificateLogExample();
        certificateLogExample.createCriteria().andCertificateIdEqualTo(id);
        certificateLogExample.setOrderByClause("create_time desc");

        List<CertificateLog> certificateLog = certificateLogService.selectByExample(certificateLogExample);

        map.put("certificate",certificate);
        if (StringUtils.isNotBlank(certificate.getImgs())){
            map.put("imgs",certificate.getImgs().split(","));
        }

        List<CertificateLogModel> ret = new ArrayList<>();
        for(CertificateLog ex : certificateLog){
            CertificateLogModel c = new CertificateLogModel();
            c.setId(ex.getId());
            c.setCertificateId(ex.getCertificateId());
            c.setLogTime(DateUtil.dateToStr(ex.getLogTime()).substring(0,10));
            c.setPrice(ex.getPrice());
            ret.add(c);

        }

        map.put("certificateLog",ret);
        return "certificate-dt";
    }

    @RequestMapping(value = "/h5/reference")
    public String reference(HttpServletRequest request, Model model)throws Exception {
        String code = request.getParameter("code");
        model.addAttribute("code",Integer.valueOf(code));
        return "reference";
    }
}
