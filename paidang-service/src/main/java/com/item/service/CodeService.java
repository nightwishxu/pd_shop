package com.item.service;

import java.util.List;

import com.base.util.JSONUtils;
import com.paidang.domain.pojo.AppVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.ConstantsCode;

import com.item.dao.CodeMapper;
import com.item.dao.model.Code;
import com.item.dao.model.CodeExample;

@Service
public class CodeService {
    @Autowired
    private CodeMapper codeMapper;

    public int countByExample(CodeExample example) {
        return this.codeMapper.countByExample(example);
    }

    public Code selectByPrimaryKey(String code) {
        return this.codeMapper.selectByPrimaryKey(code);
    }

    public List<Code> selectByExample(CodeExample example) {
        return this.codeMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String code) {
        return codeMapper.deleteByPrimaryKey(code);
    }

    public int updateByPrimaryKeySelective(Code record) {
        return codeMapper.updateByPrimaryKeySelective(record);
    }
    
    public int updateByPrimaryKey(Code record) {
        return codeMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(CodeExample example) {
        return codeMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Code record, CodeExample example) {
        return codeMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Code record, CodeExample example) {
        return codeMapper.updateByExample(record, example);
    }

    public int insert(Code record) {
        return codeMapper.insert(record);
    }

    public int insertSelective(Code record) {
        return codeMapper.insertSelective(record);
    }
    
    public String getCode(String code){
    	Code c = selectByPrimaryKey(code);
    	return c.getValue();
    }
    
    public Code getByCode(String code){
    	return selectByPrimaryKey(code);
    }


    /**
     * 获取版本信息
     * @param platForm
     * @param system
     * @return
     */
    public AppVersion getAppVersion(String  platForm,Integer system){
        Code byCode = getByCode(platForm + "@" + system);
        return JSONUtils.deserialize(byCode.getValue(),AppVersion.class);
    }

    public static void main(String[] args) {
        AppVersion a = new AppVersion();
        a.setMiniVersion("3.0.0");
        a.setMaxVersion("3.0.0");
        a.setAppChargeLog("");
        a.setUpdateEnabled(0);
        a.setUrl("");
        a.setSystem(0);
        a.setPlatForm("user");
        System.out.println(JSONUtils.serialize(a));
    }
}