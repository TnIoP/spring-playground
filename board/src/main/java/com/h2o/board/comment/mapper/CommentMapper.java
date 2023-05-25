package com.h2o.board.comment.mapper;

import com.h2o.board.comment.dto.CommentDTO;

import java.util.List;

public interface CommentMapper {
    public List<CommentDTO> getCommentsByPostId(int postId) throws Exception;
    public void createComment(CommentDTO commentDTO) throws Exception;
    public void updateComment(CommentDTO commentDTO) throws  Exception;
    public void deleteComment(int id) throws Exception;
}
