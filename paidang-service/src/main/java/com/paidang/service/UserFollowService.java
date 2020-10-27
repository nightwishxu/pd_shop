package com.paidang.service;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.UserFollowMapper;
import com.paidang.dao.model.UserFollow;
import com.paidang.dao.model.UserFollowExample;
import com.paidang.daoEx.UserFollowMapperEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFollowService {
	@Autowired
	private UserFollowMapper userFollowMapper;

	@Autowired
	private UserFollowMapperEx userFollowMapperEx;

	public int countByExample(UserFollowExample example) {
		return this.userFollowMapper.countByExample(example);
	}

	public UserFollow selectByPrimaryKey(Integer id) {
		return this.userFollowMapper.selectByPrimaryKey(id);
	}

	public List<UserFollow> selectByExample(UserFollowExample example) {
		return this.userFollowMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.userFollowMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(UserFollow record) {
		return this.userFollowMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(UserFollow record) {
		return this.userFollowMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(UserFollowExample example) {
		return this.userFollowMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(UserFollow record, UserFollowExample example) {
		return this.userFollowMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(UserFollow record, UserFollowExample example) {
		return this.userFollowMapper.updateByExample(record, example);
	}

	public int insert(UserFollow record) {
		return this.userFollowMapper.insert(record);
	}

	public int insertSelective(UserFollow record) {
		return this.userFollowMapper.insertSelective(record);
	}

	public List<UserFollow> selectByWrapper(EntityWrapper<UserFollow> wrapper) {
		return this.userFollowMapper.selectByWrapper(wrapper);
	}


	/**
	 * 判断是否关注
	 * @param userId 关注者
	 * @param followuserId 被关注者
	 * @return
	 */
	public Integer getIsFollow(Integer userId,Integer followuserId){
		return userFollowMapperEx.getIsFollow(userId,followuserId);
	}


	/**
	 * 查看关注状态
	 * @param userId 用户id
	 * @param followuserId 被关注用户
	 * @return  0没有关注关系1关注2被关注3互相关注
	 */
	public Integer getFollowStatus(Integer userId,Integer followuserId){
		UserFollowExample example1=new UserFollowExample();
		example1.createCriteria().andUserIdEqualTo(userId).andFollowIdEqualTo(followuserId);
		example1.or().andUserIdEqualTo(followuserId).andFollowIdEqualTo(userId);
		List<UserFollow> list=userFollowMapper.selectByExample(example1);
		Integer status=0;
		if (list!=null && list.size()>0){
//        	userFollow follow=list.get(0);
//            map.put("userFollow",follow);
//            if (follow.getuserId()==userId){
//                if (follow.getStatus()==3){
//                    status= 3;
//                }else if (follow.getStatus()==1){
//					status= 1;
//                }else if (follow.getStatus()==2){
//					status= 2;
//                }
//            }else if (follow.getuserId()==followuserId){
//                if (follow.getStatus()==3){
//					status= 4;
//                }else if (follow.getStatus()==1){
//					status= 2;
//                }else if (follow.getStatus()==2){
//					status= 1;
//                }
//            }
			if (list.size()==2){
				status= 3;
			}else {
				if (list.get(0).getUserId().equals(userId)){
					status=1;
				}else {
					status=2;
				}
			}

		}else {
			status= 0;
		}
		return status;
	}

	public  List<Integer> getUserFlowIds(Integer userId){
		return userFollowMapperEx.getUserFlowIds(userId);
	}
}
