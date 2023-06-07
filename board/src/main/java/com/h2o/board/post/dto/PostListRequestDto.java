package com.h2o.board.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostListRequestDto {
    private int limit;
    private int offset;
}
