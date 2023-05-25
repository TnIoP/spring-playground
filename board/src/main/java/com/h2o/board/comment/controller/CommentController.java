package com.h2o.board.comment.controller;

import com.h2o.board.comment.dto.CommentDTO;
import com.h2o.board.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{post_id}")
    public List<CommentDTO> getCommentsByPostId(@PathVariable int post_id) throws Exception {
        return commentService.getCommentsByPostId(post_id);
    }

    @PostMapping
    public void createComment(@RequestBody CommentDTO commentDTO) throws Exception {
        commentService.createComment(commentDTO);
    }

    @PutMapping("/{id}")
    public void updateComment(@PathVariable int id, @RequestBody CommentDTO commentDTO) throws Exception {
        commentDTO.setId(id);
        commentService.updateComment(commentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable int id) throws Exception {
        commentService.deleteComment(id);
    }
}
