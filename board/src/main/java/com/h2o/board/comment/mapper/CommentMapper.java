package com.h2o.board.comment.mapper;

import com.h2o.board.comment.dto.CommentDto;

import java.util.List;

public interface CommentMapper {
    CommentDto getCommentById(int id);
    CommentDto getLatestCommentId();
    List<CommentDto> getCommentsByPostId(int postId);
    void createComment(CommentDto commentDTO);
    void updateComment(CommentDto commentDTO);
    void deleteComment(int id);
}
