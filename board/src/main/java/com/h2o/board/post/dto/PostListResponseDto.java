package com.h2o.board.post.dto;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostListResponseDto {
    private List<PostDto> posts;
    private int total;
}
