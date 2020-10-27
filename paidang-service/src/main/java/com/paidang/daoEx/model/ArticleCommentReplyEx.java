package com.paidang.daoEx.model;

import com.paidang.dao.model.ArticleCommentReply;
import io.swagger.annotations.ApiModelProperty;

/**
@author sun
*/
public class ArticleCommentReplyEx extends ArticleCommentReply {

    @ApiModelProperty("1已点赞")
    private Integer praiseStatus;

    public Integer getPraiseStatus() {
        return praiseStatus;
    }

    public void setPraiseStatus(Integer praiseStatus) {
        this.praiseStatus = praiseStatus;
    }
	
}