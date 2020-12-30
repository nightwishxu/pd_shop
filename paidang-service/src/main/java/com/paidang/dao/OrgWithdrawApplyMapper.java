package com.paidang.dao;

import com.base.mybatis.plus.EntityWrapper;
import com.paidang.dao.model.OrgWithdrawApply;
import com.paidang.dao.model.OrgWithdrawApplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrgWithdrawApplyMapper {
	int countByExample(OrgWithdrawApplyExample example);

	int deleteByExample(OrgWithdrawApplyExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(OrgWithdrawApply record);

	int insertSelective(OrgWithdrawApply record);

	List<OrgWithdrawApply> selectByExample(OrgWithdrawApplyExample example);

	OrgWithdrawApply selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") OrgWithdrawApply record, @Param("example") OrgWithdrawApplyExample example);

	int updateByExample(@Param("record") OrgWithdrawApply record, @Param("example") OrgWithdrawApplyExample example);

	int updateByPrimaryKeySelective(OrgWithdrawApply record);

	int updateByPrimaryKey(OrgWithdrawApply record);

	List<OrgWithdrawApply> selectByWrapper(EntityWrapper<OrgWithdrawApply> wrapper);

}
