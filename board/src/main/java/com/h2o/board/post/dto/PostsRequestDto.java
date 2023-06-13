package com.h2o.board.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostsRequestDto {
    private int limit;
    private int offset;
}
