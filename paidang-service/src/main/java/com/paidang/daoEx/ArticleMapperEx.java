package com.paidang.daoEx;

import com.paidang.daoEx.model.ArticleEx;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
@author sun
*/
public interface ArticleMapperEx {

    /**
     *
     * @param id
     * @param num
     * @param type 0更新评论数1更新收藏数2更新点赞数
     * @return
     */
    Integer updateArticleCount(@Param("id") Integer id, @Param("num") Integer num, @Param("type") Integer type);

    /**
     * 获取用户发布的动态数
     * @param userId
     * @return
     */
    Integer getArticleCount(Integer userId);

    List<ArticleEx> findList(ArticleEx ex);

    List<ArticleEx> findReportList(ArticleEx ex);

    List<ArticleEx> findNoticeList(ArticleEx ex);

    List<ArticleEx> findNoticeIndex(ArticleEx ex);

    List<ArticleEx> recommendList(@Param("userIds")List<Integer> userIds,@Param("userId")Integer userId);

    List<ArticleEx> followArticleList(@Param("userId")Integer userId, @Param("limit")Integer limit);

    List<ArticleEx> recommendNewList(@Param("userId")Integer userId, @Param("limit")Integer limit);
}
