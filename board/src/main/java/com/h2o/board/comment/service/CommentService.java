package com.h2o.board.comment.service;

import com.h2o.board.comment.dto.CommentDTO;
import com.h2o.board.comment.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentMapper commentMapper;

    public List<CommentDTO> getCommentsByPostId(int postId) throws Exception {
        return commentMapper.getCommentsByPostId(postId);
    }

    public void createComment(CommentDTO commentDTO) throws Exception {
        commentMapper.createComment(commentDTO);
    }

    public void updateComment(CommentDTO commentDTO) throws Exception {
        commentMapper.updateComment(commentDTO);
    }

    public void deleteComment(int id) throws Exception {
        commentMapper.deleteComment(id);
    }
}
