package com.paidang.daoEx;

import com.paidang.daoEx.model.ArticleCommentEx;
import com.paidang.domain.vo.ArticleCommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
@author sun
*/
public interface ArticleCommentMapperEx {

    List<ArticleCommentEx> findList(@Param("articleId")Integer articleId, @Param("userId")Integer userId);

    List<ArticleCommentVo> findCommentListBack(@Param("content")String content, @Param("isReport")Integer isReport);

    List<ArticleCommentEx> findWonderfulCommentList(@Param("articleId")Integer articleId,@Param("likeNum")Integer likeNum,@Param("replyNum")Integer replyNum,@Param("userId")Integer userId);

    /**
     * 增加回复数
     * @param id
     * @return
     */
    Integer updateReplyNum(Integer id);

    Integer updateLikeNum(@Param("id") Integer id,@Param("num")Integer num);

//    List<MyCommentVo> findMemberComment(@Param("memberId")Long memberId);
}
