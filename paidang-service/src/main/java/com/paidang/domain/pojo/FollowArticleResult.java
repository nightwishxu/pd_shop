package com.paidang.domain.pojo;

import com.paidang.daoEx.model.ArticleEx;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xww
 * @create: 2020-10-22 21:28
 * @description:
 * @modified By:
 * @version:
 **/
public class FollowArticleResult {


    @ApiModelProperty("0没有关注用户1有关注用户")
    private Integer hasFollow;

    private List<ArticleEx> articleList;

    public Integer getHasFollow() {
        return hasFollow;
    }

    public void setHasFollow(Integer hasFollow) {
        this.hasFollow = hasFollow;
    }

    public List<ArticleEx> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleEx> articleList) {
        this.articleList = articleList;
    }
}
