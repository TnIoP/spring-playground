package com.h2o.board.post.controller;

import com.h2o.board.post.dto.PostDeleteRequestDto;
import com.h2o.board.post.dto.PostDto;
import com.h2o.board.post.dto.PostListResponseDto;
import com.h2o.board.post.service.PostService;
import com.h2o.board.response.dto.DataResponseDto;
import com.h2o.board.response.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
@RestController
@Slf4j
public class PostController {

    private final PostService postService;

    @Cacheable(value = "Post.id", key = "#id")
    @GetMapping("/{id}")
    public DataResponseDto<PostDto> getPostById(@PathVariable int id){
        System.out.println(id);
        log.info("create board : {}", id);


        return DataResponseDto.of(postService.getPostById(id));
    }

    @Cacheable(value = "Post.id")
    @GetMapping
    public DataResponseDto<PostListResponseDto> getPosts(@RequestParam int limit, @RequestParam int offset) throws Exception{
        return DataResponseDto.of(postService.getPosts(limit, offset));
    }

    @CacheEvict(value = "Post.id", allEntries = true)
    @PostMapping
    public ResponseDto createPost(@RequestBody PostDto postDTO) throws Exception {
        log.info("create board : {}", postDTO);
        postService.createPost(postDTO);
        return new ResponseDto(201, HttpStatus.CREATED, "Created success");
    }

    @CachePut(value = "Post.id", key = "#id")
    @PutMapping("/{id}")
    public DataResponseDto<PostDto> updatePost(@PathVariable int id, @RequestBody PostDto postDTO) throws Exception {
        postDTO.setId(id);
        return DataResponseDto.of(postService.updatePost(postDTO));
    }

    @CacheEvict(value = "Post.id", allEntries = true)
    @PostMapping("/{id}")
    public ResponseDto deletePost(@PathVariable int id, @RequestBody PostDeleteRequestDto postDeleteRequestDto) throws Exception{
        postService.deletePost(id, postDeleteRequestDto.getIp());
        return new ResponseDto(204, HttpStatus.NO_CONTENT, "Deleted success");
    }
}