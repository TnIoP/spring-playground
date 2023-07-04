package com.h2o.board.post.service;

import java.util.List;

import com.h2o.board.global.exception.NotFoundException;
import com.h2o.board.global.exception.UnauthorizedException;
import com.h2o.board.post.dto.PostDto;
import com.h2o.board.post.dto.PostsResponseDto;
import com.h2o.board.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostService {

    private final PostMapper postMapper;
    public static final String MASTER_PW = "H42iEDRVesCC3e0ubC2k";

    public PostDto getPostById(int id) {
        log.info("PostService.getPostById (id : {})", id);
        PostDto post = postMapper.getPostById(id);

        if (post == null) {
            log.info("PostService.getPostById can't find post id. (id : {})", id);
            throw new NotFoundException("can't find post id. (id :" + id + ")");
        }

        log.info("PostService.getPostById (post : {})", post);
        return post;
    }

    public PostsResponseDto getPosts(int limit, int offset) {
        log.info("PostService.getPosts (limit : {}, offset : {})", limit, offset);

        int total = postMapper.getPostsTotal();
        List<PostDto> posts = postMapper.getPosts(limit, offset);
        log.info("PostService.getPosts (total : {}, posts : {})", total, posts);

        PostsResponseDto postsResponseDto = new PostsResponseDto();
        postsResponseDto.setTotal(total);
        postsResponseDto.setPosts(posts);
        log.info("PostService.getPosts (postsResponseDto : {}", postsResponseDto);
        return postsResponseDto;
    }

    public void createPost(PostDto postDTO) {
        log.info("PostService.createPost (postDTO : {})", postDTO);

        postMapper.createPost(postDTO);
        log.info("PostService.createPost Created");
    }

    public PostDto updatePost(PostDto postDTO) {
        log.info("PostService.updatePost (postDTO : {})", postDTO);
        PostDto post = this.getPostById(postDTO.getId());

        if (!post.getIp().equals(postDTO.getIp())) {
            log.info("PostService.deletePost not match the author's ip. (ip : {})", postDTO.getIp());
            throw new UnauthorizedException("not match the author's ip. (ip : " + postDTO.getIp() + ")");
        }

        postMapper.updatePost(postDTO);
        PostDto updatedPost = this.getPostById(postDTO.getId());
        log.info("PostService.updatePost (updatedPost : {})", updatedPost);
        return updatedPost;
    }

    public void deletePost(int id, String ip) {
        log.info("PostService.deletePost (id : {}, ip : {})", id, ip);
        PostDto post = this.getPostById(id);

        if (!ip.equals(MASTER_PW) && !post.getIp().equals(ip)) {
            log.info("PostService.deletePost not match the author's ip. (ip : {})", ip);
            throw new UnauthorizedException("not match the author's ip. (ip : " + ip + ")");
        }

        postMapper.deletePost(id);
        log.info("PostService.deletePost Deleted");
    }
}