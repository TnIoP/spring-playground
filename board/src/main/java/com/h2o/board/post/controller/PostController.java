package com.h2o.board.post.controller;

import com.h2o.board.post.dto.PostDto;
import com.h2o.board.post.dto.PostListResponseDto;
import com.h2o.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
@RestController
public class PostController {

    private final PostService postService;

    @Cacheable(value = "PostDTO.id", key = "#id", cacheManager = "cacheManager", unless = "#id == ''", condition = "#id > 4")
    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable int id) throws Exception {
        System.out.println(id);
        return postService.getPostById(id);
    }

    @GetMapping
    public PostListResponseDto getPosts(@RequestParam int limit, @RequestParam int offset) throws Exception{
        return postService.getPosts(limit, offset);
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

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) throws Exception{
        postService.deletePost(id);
    }
}