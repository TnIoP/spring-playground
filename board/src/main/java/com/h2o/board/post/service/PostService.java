package com.h2o.board.post.service;

import java.util.List;

import com.h2o.board.exception.NotFoundException;
import com.h2o.board.post.dto.PostDto;
import com.h2o.board.post.dto.PostListResponseDto;
import com.h2o.board.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostMapper postMapper;

    public PostDto getPostById(int id) throws Exception {
        PostDto post = postMapper.getPostById(id);
        System.out.println("[id:" + id + "] Service 에서 연산을 수행합니다");
        if (post == null)
            throw new NotFoundException(String.format("can't find post id: ID[%d]", id));
        return post;
    }

    public PostListResponseDto getPosts(int limit, int offset) throws Exception {
        PostListResponseDto postListResponseDto = new PostListResponseDto();

        List<PostDto> posts = postMapper.getPosts(limit, offset);
        int total = postMapper.getPostsTotal();

        postListResponseDto.setPosts(posts);
        postListResponseDto.setTotal(total);
        return postListResponseDto;
    }

    public void createPost(PostDto postDTO) throws Exception {
        postMapper.createPost(postDTO);
    }

    public void updatePost(PostDto postDTO) throws Exception {
        postMapper.updatePost(postDTO);
    }

    public void deletePost(int id) throws Exception {
        postMapper.deletePost(id);
    }
}