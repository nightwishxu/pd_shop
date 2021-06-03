package com.h5.action;

import com.base.util.CoreConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class H5Controller {



    @RequestMapping(value = "/h5/appraisal")
    public String appraisal(HttpServletRequest request, Model model) {
        //id
        String id = request.getParameter("id");
        model.addAttribute("id",id);
        model.addAttribute("serverUrl", CoreConstants.SERVER_URL);
        return "appraisal";
    }


    @RequestMapping(value = "/h5/getRegistrations")
    public String getRegistrations(HttpServletRequest request, Model model) {
        return "registration";
    }
}
