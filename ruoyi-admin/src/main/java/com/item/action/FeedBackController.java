package com.item.action;

import com.base.action.CoreController;

import com.item.daoEx.model.FeedbackEx;
import com.item.service.FeedbackService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("feedback")
@Controller
public class FeedBackController extends CoreController {
	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping("/list")
	@ResponseBody
	public TableDataInfo findlist(HttpSession session, Integer page, Integer rows) {
		startPage();
		Map<String,Object> map = new HashMap<String,Object>();
		List<FeedbackEx> list = this.feedbackService.selectList(map);
		return page(list);
	}

	@RequestMapping("/del")
	@ResponseBody
	public Ret del(String id) {
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			this.feedbackService.deleteByPrimaryKey(Integer.parseInt(ids[i]));
		}
		return ok();
	}

}
