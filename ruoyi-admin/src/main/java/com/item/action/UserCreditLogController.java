package com.item.action;

import com.base.action.CoreController;
import com.item.dao.model.UserCreditLog;
import com.item.dao.model.UserCreditLogExample;
import com.item.dao.model.UserCreditLogExample.Criteria;
import com.item.service.UserCreditLogService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("userCreditLog")
@Controller
public class UserCreditLogController extends CoreController{
	@Autowired
	private UserCreditLogService userCreditLogService;

	@RequestMapping("/listByUserId")
	@ResponseBody
	public TableDataInfo list(String id, Integer type, Integer page, Integer rows){
		UserCreditLogExample example = new UserCreditLogExample();
		example.setOrderByClause("create_time desc");
		startPage();
		Criteria criteria = example.createCriteria().andUserIdEqualTo(id);
		if(type != null){
			criteria.andTypeEqualTo(type);
		}
		List<UserCreditLog> list = this.userCreditLogService.selectByExample(example);
		return page(list);
	}
}
