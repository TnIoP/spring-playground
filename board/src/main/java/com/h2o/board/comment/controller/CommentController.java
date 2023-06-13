package com.h2o.board.comment.controller;

import com.h2o.board.comment.dto.CommentDeleteRequestDto;
import com.h2o.board.comment.dto.CommentDto;
import com.h2o.board.comment.service.CommentService;
import com.h2o.board.global.response.dto.DataResponseDto;
import com.h2o.board.global.response.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/comments")
@RestController
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @Cacheable(value = "Comment", key = "#post_id")
    @GetMapping("/{post_id}")
    public DataResponseDto<List<CommentDto>> getCommentsByPostId(@PathVariable int post_id) {
        log.info("CommentController.getCommentsByPostId (post_id : {})", post_id);
        return DataResponseDto.of(commentService.getCommentsByPostId(post_id));
    }

    @CacheEvict(value = "Comment", allEntries = true)
    @PostMapping
    public ResponseDto createComment(@RequestBody CommentDto commentDTO) {
        log.info("CommentController.createComment (commentDTO : {})", commentDTO);
        commentService.createComment(commentDTO);
        return new ResponseDto(201, HttpStatus.CREATED, "Created success");
    }

    @CacheEvict(value = "Comment", allEntries = true)
    @PutMapping("/{id}")
    public DataResponseDto<CommentDto> updateComment(@PathVariable int id, @RequestBody CommentDto commentDTO) {
        log.info("CommentController.updateComment (id : {}, commentDTO : {})", id, commentDTO);
        commentDTO.setId(id);
        return DataResponseDto.of(commentService.updateComment(commentDTO));
    }

    @CacheEvict(value = "Comment", allEntries = true)
    @PostMapping("/{id}")
    public ResponseDto deleteComment(@PathVariable int id, @RequestBody CommentDeleteRequestDto commentDeleteRequestDto) {
        log.info("CommentController.deleteComment (id : {}, ip : {})", id, commentDeleteRequestDto.getIp());
        commentService.deleteComment(id, commentDeleteRequestDto.getIp());
        return new ResponseDto(204, HttpStatus.NO_CONTENT, "Deleted success");
    }
}
