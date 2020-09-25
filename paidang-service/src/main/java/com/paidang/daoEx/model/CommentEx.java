package com.paidang.daoEx.model;

import com.paidang.dao.model.Comment;

import java.util.List;

/**
@author sun
*/
public class CommentEx extends Comment {
    List<CommentReplyEx> commentReplyExList;


    public List<CommentReplyEx> getCommentReplyExList() {
        return commentReplyExList;
    }

    public void setCommentReplyExList(List<CommentReplyEx> commentReplyExList) {
        this.commentReplyExList = commentReplyExList;
    }
}