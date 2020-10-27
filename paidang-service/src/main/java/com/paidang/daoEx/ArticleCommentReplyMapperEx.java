package com.paidang.daoEx;

import com.paidang.daoEx.model.ArticleCommentReplyEx;
import com.paidang.domain.vo.ArticleCommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
@author sun
*/
public interface ArticleCommentReplyMapperEx {

    List<ArticleCommentReplyEx> findList(@Param("commentId")Integer commentId, @Param("userId")Integer userId);


    int updateReplyNum(@Param("id")Integer id);

    List<ArticleCommentVo> findCommentReplyListBack(@Param("content")String content, @Param("isReport")Integer isReport);
}
