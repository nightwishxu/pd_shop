package com.item.action;

import com.base.annotation.ApiMethod;
import com.paidang.service.ExpressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xj
 * @DateTime: 2020/6/11 9:18
 * @Description:
 */
@Api(tags = "快递接口")
@RestController
public class ExpressApi {
    @Autowired
    private ExpressService expressService;


    @ApiMethod
    @ApiOperation(value = "手动修改快递状态")
    @PostMapping(value="/setExpressAllToSuccess")
    public void getVersion(@ApiParam(value = "快递单号", required = true) String expressCode){
        expressService.queryAutoSpecialAllToSuccess(expressCode);
    }


}
