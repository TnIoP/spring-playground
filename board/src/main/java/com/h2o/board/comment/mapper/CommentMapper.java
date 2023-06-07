package com.h2o.board.comment.mapper;

import com.h2o.board.comment.dto.CommentDto;

import java.util.List;

public interface CommentMapper {
    public List<CommentDto> getCommentsByPostId(int postId) throws Exception;
    public void createComment(CommentDto commentDTO) throws Exception;
    public void updateComment(CommentDto commentDTO) throws  Exception;
    public void deleteComment(int id) throws Exception;
}
