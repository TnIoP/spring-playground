package com.h2o.board.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class CommentDto {
    private int id;
    private String ip;
    private String contents;
    private int parentCommentId;
    private int seq;
    private int depth;
    private Boolean isHide;
    private Boolean isDeleted;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Timestamp createdAt;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Timestamp updatedAt;
    private int postId;
}
