package com.weixin.service;

import com.weixin.dao.WxArticleMapper;
import com.weixin.dao.model.Article;
import com.weixin.dao.model.ArticleExample;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxArticleService {
    @Autowired
    private WxArticleMapper wxArticleMapper;

    private static final Logger logger = Logger.getLogger(WxArticleService.class);

    public int countByExample(ArticleExample example) {
        return this.wxArticleMapper.countByExample(example);
    }

    public Article selectByPrimaryKey(String id) {
        return this.wxArticleMapper.selectByPrimaryKey(id);
    }

    public List<Article> selectByExample(ArticleExample example) {
        return this.wxArticleMapper.selectByExampleWithBLOBs(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.wxArticleMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Article record) {
        return this.wxArticleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Article record) {
        return this.wxArticleMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    public int deleteByExample(ArticleExample example) {
        return this.wxArticleMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Article record, ArticleExample example) {
        return this.wxArticleMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Article record, ArticleExample example) {
        return this.wxArticleMapper.updateByExampleWithBLOBs(record, example);
    }

    public int insert(Article record) {
        return this.wxArticleMapper.insert(record);
    }

    public int insertSelective(Article record) {
        return this.wxArticleMapper.insertSelective(record);
    }
}