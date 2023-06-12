package com.h2o.board.post.service;

import java.util.List;

import com.h2o.board.exception.NotFoundException;
import com.h2o.board.exception.UnauthorizedException;
import com.h2o.board.post.dto.PostDto;
import com.h2o.board.post.dto.PostListResponseDto;
import com.h2o.board.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostMapper postMapper;

    public PostDto getPostById(int id) {
        PostDto post = postMapper.getPostById(id);
        System.out.println("[id:" + id + "] Service 에서 연산을 수행합니다");
        if (post == null) {
            System.out.println("in ");
            throw new NotFoundException("can't find post id: "+id);
        }
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

    public PostDto updatePost(PostDto postDTO) throws Exception {
        postMapper.updatePost(postDTO);
        PostDto updatedPost = this.getPostById(postDTO.getId());
        return updatedPost;
    }

    public void deletePost(int id, String ip) throws Exception {
        PostDto post = this.getPostById(id);
        if (!post.getIp().equals(ip))
            throw new UnauthorizedException(String.format("not match the author's ip: IP[%s]", ip));
        postMapper.deletePost(id);
    }
}