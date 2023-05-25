package com.h2o.board.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class CommentDTO {
    private int id;
    private String ip;
    private String contents;
    private int parent_comment_id;
    private int depth;
    private Boolean is_hide;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Timestamp createdAt;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Timestamp updatedAt;
    private int post_id;
}
