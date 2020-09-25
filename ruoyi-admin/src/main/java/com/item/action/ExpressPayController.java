package com.item.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.action.CoreController;
import com.paidang.dao.model.Express;
import com.paidang.service.ExpressService;
import com.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class ExpressPayController extends CoreController {
	@Autowired
	private ExpressService expressService;

	@RequestMapping(value = "/expressPush")
	public void expressPush(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("@@@收到快递推送");
		//返回结果
		JSONObject ret = new JSONObject();
		try {
			String param = request.getParameter("param");
			JSONObject result = JSONObject.parseObject(param);

			String status = result.getString("status");
			JSONObject lastResult = result.getJSONObject("lastResult");
			//快递状态
			String state = lastResult.getString("state");
			String ischeck = lastResult.getString("ischeck");
			//快递单号
			String nu = lastResult.getString("nu");
			//快递数据
			JSONArray data = lastResult.getJSONArray("data");
			//处理逻辑
			doExpress(nu,state,data);

			ret.put("result",true);
			ret.put("returnCode",200);
			ret.put("message","成功");
		}catch (Exception e){
			ret.put("result",false);
			ret.put("returnCode",500);
			ret.put("message","失败");
		}
		ResponseUtils.renderJson(response,ret);
	}

	private void doExpress(String nu,String state,JSONArray data){
		synchronized (this) {
			logger.debug("@@@进入快递业务处理");
			Express express = expressService.queryByCode(nu);
			if (express != null){
				express.setExpressState(Integer.parseInt(state));
				express.setExpressData(data.toJSONString());
				express.setModifyTime(new Date());
				if ("3".equals(state)){
					expressService.expressOk(express);
				}
				//更新快递状态
				expressService.updateByPrimaryKey(express);
			}
		}
	}
}
