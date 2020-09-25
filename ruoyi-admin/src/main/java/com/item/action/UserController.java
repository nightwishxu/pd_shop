package com.item.action;

import com.base.action.CoreController;

import com.base.util.Md5;
import com.item.dao.model.User;
import com.item.dao.model.UserExample;
import com.item.daoEx.model.UserEx;
import com.item.service.UserService;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("users/user")
@RestController
public class UserController extends CoreController{
	@Autowired
	private UserService userService;
	
	@RequestMapping("/orgUsersList")
	@ResponseBody
	public TableDataInfo selectOrgUsersList(String name, Integer page, Integer rows) throws Exception{
		startPage();
		Map<String,Object> map = new HashMap<String,Object>();
		String orgId = getSessionParameter("id");

		map.put("orgId",orgId);
	   //map.put("key", "'%"+name+"%'");
	    map.put("key",name);
		List<UserEx> list= userService.selectOrgUsersList(map);
		return page(list);
	}


	@RequestMapping("/list")
	@ResponseBody
	public TableDataInfo selectUserList( User user) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		startPage();
		List<UserEx> list= userService.selectList(user);
		return page(list);
	}

	@RequestMapping("/duplicateCheck")
	@ResponseBody
	public Ret duplicateCheck(String accountInput) throws Exception{
		UserExample example = new UserExample();
		example.createCriteria().andTypeEqualTo(1);
		List<User> allOrgUsers = userService.selectByExample(example);
		for (User element:allOrgUsers) {
			if (element.getAccount().equals(accountInput)){
				return msg(-1,"您输入的账户名已存在，请重新填写用户名");
			}
		}
		return ok();
	}


	@RequestMapping("/saveOrgUser")
	@ResponseBody
	public Ret saveOrgUser(User user) throws Exception{
		String orgId = getSessionParameter("id");
		if (StringUtils.isEmpty(user.getAccount())){
			return msg(-1,"用户账户不能为空");
		}
		if (user.getId() == null){
			//添加用户时判断输入的账号是否已存在
			UserExample example = new UserExample();
			example.createCriteria().andTypeEqualTo(1);
			List<User> allOrgUsers = userService.selectByExample(example);
			for (User element:allOrgUsers) {
				if (user.getAccount().equals(element.getAccount())){
					return msg(-1,"您输入的账户名已存在，请重新填写用户名");
				}
			}
			user.setPassword(Md5.md5("1"));
			user.setType(1);//表示机构用户
			user.setState(1);
			user.setOrgId(Integer.valueOf(orgId));
			userService.insert(user);
		}else{
			userService.updateByPrimaryKeySelective(user);
		}
		return ok();
	}

//	@PutMapping
//	public Ret update(@RequestBody User user){
//		if(!StringUtils.isBlank(user.getPassword()))
//		    user.setPassword(Md5.mD5(user.getPassword()));
//		this.userService.updateByPrimaryKeySelective(user);
//		return ok();
//	}

	@RequestMapping("{id}")
	@ResponseBody
	public Ret find(@PathVariable Integer id){
		User user = userService.selectByPrimaryKey(id);
		return ok(user);
	}


	@DeleteMapping("{id}")
	public Ret del(@PathVariable String id){
		String[] ids = id.split(",");
		for (String str : ids){
			userService.deleteByPrimaryKey(Integer.parseInt(str));
		}
		return ok();
	}
	/*@RequestMapping("detail")
	@ResponseBody
	public Ret detail() throws Exception{
		UserExample example = new UserExample();
		List<User> list = userService.selectByExample(example);
		return list.get(0);
	}*/

	@PostMapping("update")
	public Ret save(@RequestBody User user){
		if(user.getId() == null){
			userService.insert(user);
		}else{
			userService.updateByPrimaryKeySelective(user);
		}
		return ok();
	}
}
