package com.item.action;

import com.base.action.CoreController;
import com.item.dao.model.Notify;
import com.item.daoEx.model.NotifyEx;
import com.item.service.NotifyService;
import com.item.service.UserNotifyService;
import com.item.service.UserService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("notify")
@Controller
public class NotifyController extends CoreController{
	@Autowired
	private NotifyService notifyService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserNotifyService userNotifyService;
	
	@RequestMapping("/list")
	@ResponseBody
	public TableDataInfo list(Integer page, Integer rows){
		startPage();
		Map<String,Object> map = new HashMap<String,Object>();
		List<NotifyEx> list = notifyService.selectList(map);
		return page(list);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Ret save(Notify notify){
		if(notify.getId() == null){//添加
			notify.setState(1);
			notify.setNum(0);
			notify.setTitle("系统消息");
			notify.setRedirectType(0);
			notify.setRedirectContent("");
			notify.setCreateTime(new Date());
			notifyService.insert(notify);

		}else{//update
			Notify temp = notifyService.selectByPrimaryKey(notify.getId());
			if(temp == null || temp.getState() == 2){
				return msg(1,"已发送的记录无法修改");
			}
			notify.setState(1);
			notify.setCreateTime(temp.getCreateTime());
			notifyService.updateByPrimaryKeySelective(notify);
		}
		return ok();
	}
	
	@RequestMapping("/send")
	@ResponseBody
	public Ret send(Integer id){
		notifyService.sendNotify(id);
		return ok();
	}

	@RequestMapping("del")
	@ResponseBody
	public Ret del(String id){
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			notifyService.deleteEx(Integer.parseInt(ids[i]));
		}
		return ok();
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public Ret findById(Integer id) throws Exception{
		Notify notify = notifyService.selectByPrimaryKey(id);
		return ok(notify);
	}
}
