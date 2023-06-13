package com.h2o.board.comment.service;

import com.h2o.board.comment.dto.CommentDto;
import com.h2o.board.comment.mapper.CommentMapper;
import com.h2o.board.global.exception.NotFoundException;
import com.h2o.board.global.exception.UnauthorizedException;
import com.h2o.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommentService {

    private final CommentMapper commentMapper;
    private final PostService postService;

    public CommentDto getCommentById(int id) {
        log.info("CommentService.getCommentById (id : {})", id);
        CommentDto comment = commentMapper.getCommentById(id);

        if (comment == null) {
            log.info("CommentService.getCommentById can't find comment id. (id : {})", id);
            throw new NotFoundException("can't find comment id. (id :" + id + ")");
        }

        log.info("CommentService.getCommentById (comment : {})", comment);
        return comment;
    }

    public List<CommentDto> getCommentsByPostId(int postId) {
        log.info("CommentService.getCommentsByPostId (postId : {})", postId);
        this.postService.getPostById(postId);

        List<CommentDto> comments = commentMapper.getCommentsByPostId(postId);
        log.info("CommentService.getCommentsByPostId (comments : {})", comments);
        return comments;
    }

    public void createComment(CommentDto commentDTO) {
        log.info("CommentService.createComment (commentDTO : {})", commentDTO);
        this.postService.getPostById(commentDTO.getPostId());

        commentMapper.createComment(commentDTO);
        log.info("CommentService.createComment Created");
    }

    public CommentDto updateComment(CommentDto commentDTO) {
        log.info("CommentService.updateComment (commentDTO : {})", commentDTO);
        CommentDto comment = this.getCommentById(commentDTO.getId());

        if (!comment.getIp().equals(commentDTO.getIp())) {
            log.info("CommentService.updateComment not match the author's ip. (ip : {})", commentDTO.getIp());
            throw new UnauthorizedException("not match the author's ip. (ip : " + commentDTO.getIp() + ")");
        }

        commentMapper.updateComment(commentDTO);
        CommentDto updatedComment = this.getCommentById(commentDTO.getId());
        log.info("CommentService.updateComment (updatedComment : {})", updatedComment);
        return updatedComment;
    }

    public void deleteComment(int id, String ip) {
        log.info("CommentService.deleteComment (id : {}, ip : {})", id, ip);
        CommentDto comment = this.getCommentById(id);

        if (!comment.getIp().equals(ip)) {
            log.info("CommentService.deleteComment not match the author's ip. (ip : {})", ip);
            throw new UnauthorizedException("not match the author's ip. (ip : " + ip + ")");
        }

        commentMapper.deleteComment(id);
        log.info("CommentService.deleteComment Deleted");
    }
}
