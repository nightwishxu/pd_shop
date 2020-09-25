package com.paidang.daoEx;

import com.paidang.daoEx.model.CommentEx;

import java.util.List;

/**
@author sun
*/
public interface CommentMapperEx {
    List<CommentEx> findList(CommentEx commentEx);
}
