package com.paidang.dao;


import com.paidang.dao.model.BFile;
import com.paidang.dao.model.BFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BFileMapper {
    long countByExample(BFileExample example);

    int deleteByExample(BFileExample example);

    int deleteByPrimaryKey(String fileId);

    int insert(BFile record);

    int insertSelective(BFile record);

    List<BFile> selectByExample(BFileExample example);

    BFile selectByPrimaryKey(String fileId);

    int updateByExampleSelective(@Param("record") BFile record, @Param("example") BFileExample example);

    int updateByExample(@Param("record") BFile record, @Param("example") BFileExample example);

    int updateByPrimaryKeySelective(BFile record);

    int updateByPrimaryKey(BFile record);
}