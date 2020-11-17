package com.item.service;

import com.base.web.annotation.LoginMethod;
import com.item.dao.MobileVerifyMapper;
import com.item.dao.UserMapper;
import com.item.dao.model.User;
import com.item.dao.model.UserExample;
import com.item.daoEx.UserMapperEx;
import com.item.daoEx.model.UserEx;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserMapperEx userMapperEx;
	@Autowired
	private MobileVerifyMapper verifyMapper;
	@Autowired
	private NotifyService notifyService;

	public int countByExample(UserExample example) {
		return this.userMapper.countByExample(example);
	}

	public User selectByPrimaryKey(Integer id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	public List<User> selectByExample(UserExample example) {
		return this.userMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.userMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(User record) {
		return this.userMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(User record) {
		return this.userMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(UserExample example) {
		return this.userMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(User record, UserExample example) {
		return this.userMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(User record, UserExample example) {
		return this.userMapper.updateByExample(record, example);
	}

	public int insert(User record) {
		int i = this.userMapper.insert(record);
		notifyService.insertNewUser(record.getId());
		//OpenImUtil.add(record.getId()+"",record.getId()+"");
		return i;
	}

	public int insertSelective(User record) {
		int i =  this.userMapper.insertSelective(record);
		notifyService.insertNewUser(record.getId());
		//OpenImUtil.add(record.getId()+"",record.getId()+"");
		return i;
	}

	public void updateBalance(String userId, BigDecimal goodsTotal) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", userId);
		map.put("num", goodsTotal);
		userMapperEx.updateBalance(map);
	}

	public List<UserEx> selectList(User user) {
		return userMapperEx.selectList(user);
	}
	
	public List<Integer> selectIdByAccounts(List<String> accounts){
		return userMapperEx.selectIdByAccounts(accounts);
	}
	
	public Integer selectIdByAccount(String account){
		return userMapperEx.selectIdByAccount(account);
	}
	
	public String selectAccountById(String id){
		return userMapperEx.selectAccountById(id);
	}
	
    public boolean autoH5Login(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		Cookie c = null;
		String account = "";
		String pwd = "";
		for (int i = 0; cookies!=null && i < cookies.length; i++){
			c = cookies[i];
			if (c.getName().equals("account")){
				account = c.getValue();
			}else if(c.getName().equals("pwd")){
				pwd = c.getValue();
			}
		}
		if(account.length()>0 && pwd.length()>0){
			UserExample example = new UserExample();
			example.createCriteria().andAccountEqualTo(account).andPasswordEqualTo(pwd);
			List<User> list = this.selectByExample(example);
			if(list.size()>0){
				User user = list.get(0);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", user.getId());
				map.put("account", user.getAccount());
				request.getSession().setAttribute(LoginMethod.PHONE.getName(), map);
				return true;
			}
		}
    	return false;
    }

    public Integer getOrgIdByUserId(Integer userId){
    	if(userId == null){
    		return -1;
		}
    	User user = userMapper.selectByPrimaryKey(userId);
    	if (user==null){
    		return -1;
		}
		return user.getOrgId()!=null?user.getOrgId():-1;
	}

	//机构后台，用户列表
	public List<UserEx> selectOrgUsersList(Map<String,Object> param){
    	return userMapperEx.selectOrgUsersList(param);
	}
	public User selectByAccount(String record) {
		return this.userMapperEx.selectByAccount(record);
	}


	public List<UserEx> findList(Integer userId, String keyword){
		return userMapperEx.findList(userId,keyword);
	}

	public List<UserEx> findFollowList(Integer userId){
		return userMapperEx.findFollowList(userId);
	}

	public List<UserEx> findFansList(Integer userId){
		return userMapperEx.findFansList(userId);
	}

	public List<UserEx> recommendList(Integer userId){
		return userMapperEx.recommendList(userId);
	}

	/**
	 * 更新用户统计数量
	 * @param id
	 * @param type
	 * @return
	 */
	public int updateUserCount(Integer id,Integer type){
		return userMapperEx.updateUserCount(id,type);
	}
}