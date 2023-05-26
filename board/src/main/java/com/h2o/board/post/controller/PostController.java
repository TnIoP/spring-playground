package com.h2o.board.post.controller;

import com.h2o.board.post.dto.PostDTO;
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
    public PostDTO getPostById(@PathVariable int id) throws Exception {
        System.out.println(id);
        return postService.getPostById(id);
    }

    @GetMapping
    public List<PostDTO> getPosts() throws Exception{
        return postService.getPosts();
    }

    @PostMapping
    public void createPost(@RequestBody PostDTO postDTO) throws Exception {
        postService.createPost(postDTO);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable int id, @RequestBody PostDTO postDTO) throws Exception {
        postDTO.setId(id);
        postService.updatePost(postDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) throws Exception{
        postService.deletePost(id);
    }
}