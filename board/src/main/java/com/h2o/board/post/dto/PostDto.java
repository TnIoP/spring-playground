package com.h2o.board.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class PostDto {
    private int id;
    private String title;
    private String ip;
    private String contents;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Timestamp createdAt;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Timestamp updatedAt;
}
