package com.h2o.board.post.controller;

import com.h2o.board.post.dto.PostDeleteRequestDto;
import com.h2o.board.post.dto.PostDto;
import com.h2o.board.post.dto.PostListResponseDto;
import com.h2o.board.post.service.PostService;
import com.h2o.board.response.dto.DataResponseDto;
import com.h2o.board.response.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
@RestController
public class PostController {

    private final PostService postService;

    @Cacheable(value = "PostDTO.id", key = "#id", cacheManager = "cacheManager", unless = "#id == ''", condition = "#id > 4")
    @GetMapping("/{id}")
    public DataResponseDto<PostDto> getPostById(@PathVariable int id){
        System.out.println(id);
        return DataResponseDto.of(postService.getPostById(id));
    }

    @GetMapping
    public DataResponseDto<PostListResponseDto> getPosts(@RequestParam int limit, @RequestParam int offset) throws Exception{
        return DataResponseDto.of(postService.getPosts(limit, offset));
    }

    @PostMapping
    public void createPost(@RequestBody PostDto postDTO) throws Exception {
        postService.createPost(postDTO);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable int id, @RequestBody PostDto postDTO) throws Exception {
        postDTO.setId(id);
        postService.updatePost(postDTO);
    }

    @PostMapping("/{id}")
    public void deletePost(@PathVariable int id, @RequestBody PostDeleteRequestDto postDeleteRequestDto) throws Exception{
        postService.deletePost(id, postDeleteRequestDto.getIp());
    }
}