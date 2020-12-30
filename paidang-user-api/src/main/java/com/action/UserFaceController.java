package com.action;

import com.paidang.service.UnionApiService;
import com.base.api.ApiException;
import com.base.util.StringUtils;
import com.item.dao.model.User;
import com.item.service.UserService;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2324Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserFaceController {

    @Autowired
    private UserService userService;

    @Autowired
    private UnionApiService unionApiService;

    //html访问控制器
    /**
     * @return
     */
    @RequestMapping(value = "/h5/userFace")
    public String userFace(HttpServletRequest request, Model model)throws Exception {
        String userId = request.getParameter("userId");
        if (userId==null){
            throw new ApiException(400,"缺少必要参数");
        }
        User user = userService.selectByPrimaryKey(Integer.parseInt(userId));

        if (user.getIsBind()==1){
            throw new ApiException(400,"实名认证成功");
        }
//        if (user.getAuthStatus()!=null && user.getAuthStatus()==2){
//            throw new ApiException(400,"当前正在验证中");
//        }

        if (user.getAuthStatus()!=null && user.getAuthStatus()==4){
            throw new ApiException(400,"活体验证成功");
        }
        if (user.getAuthStatus()==null || user.getAuthStatus()==0 ||  StringUtils.isAnyBlank(user.getName(),user.getIdCard())){
            throw new ApiException(400,"请先进行实名认证");
        }

        Tx2324Request tx2324Request = UnionApiService.userFace(user.getName(), user.getIdCard());
        String txSN = tx2324Request.getTxSN();
        User tmp = new User();
        tmp.setTxsn(txSN);
        tmp.setAuthStatus(2);
        tmp.setId(user.getId());
        userService.updateByPrimaryKeySelective(tmp);
        model.addAttribute("message",tx2324Request.getRequestMessage());
        model.addAttribute("signature",tx2324Request.getRequestSignature());
        model.addAttribute("dgtlEnvlp",tx2324Request.getRequestDgtlEnvlp());
        model.addAttribute("signSN",tx2324Request.getRequestSignSN());
        model.addAttribute("encryptSN",tx2324Request.getRequestEncryptSN());

        return "userFace";
    }


    @RequestMapping(value = "/h5/userFaceResult")
    public String userFaceNotify(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {

        String result = UnionApiService.receiveNotifyBack(request);
        model.addAttribute("result",result);

        return "userFaceResult";
    }
}
