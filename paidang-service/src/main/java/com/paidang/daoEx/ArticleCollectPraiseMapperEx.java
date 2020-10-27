package com.paidang.daoEx;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
@author sun
*/
public interface ArticleCollectPraiseMapperEx {

    List<String> lastPraiseIcon(@Param("articleId")Integer articleId);

}
