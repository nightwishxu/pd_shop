package com.paidang.daoEx.model;

import com.paidang.dao.model.ArticleComment;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
@author sun
*/
public class ArticleCommentEx extends ArticleComment {

    /**
     * 回复
     */
    @ApiModelProperty("评论回复")
    private List<CommentReplyEx> commentReplyExList;

    @ApiModelProperty("1已点赞")
    private Integer praiseStatus;

    public List<CommentReplyEx> getCommentReplyExList() {
        return commentReplyExList;
    }

    public void setCommentReplyExList(List<CommentReplyEx> commentReplyExList) {
        this.commentReplyExList = commentReplyExList;
    }

    public Integer getPraiseStatus() {
        return praiseStatus;
    }

    public void setPraiseStatus(Integer praiseStatus) {
        this.praiseStatus = praiseStatus;
    }
	
}