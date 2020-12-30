package com.api.action;

import com.paidang.service.UnionApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UnionNotifyController {

    @Autowired
    private UnionApiService unionApiService;

    @RequestMapping(value = "/notify/receiveNoticeBackground")
    public void receiveNoticeBackground(HttpServletRequest request, HttpServletResponse response) throws Exception {
        unionApiService.receiveNoticeBackground(request,response);
    }
}
