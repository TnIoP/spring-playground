package com.h2o.board.post.service;

import java.util.List;

import com.h2o.board.exception.NotFoundException;
import com.h2o.board.exception.UnauthorizedException;
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

    public PostDto getPostById(int id) {
        log.info("PostService.getPostById (id : {})", id);
        PostDto post;
        try {
            post = postMapper.getPostById(id);
        } catch (Exception e) {
            log.error("PostService.getPostById error : {}", e.getMessage());
            return null;
        }

        if (post == null) {
            log.info("PostService.getPostById can't find post id. (id : {})", id);
            throw new NotFoundException("can't find post id. (id :" + id + ")");
        }

        log.info("PostService.getPostById (post : {})", post);
        return post;
    }

    public PostsResponseDto getPosts(int limit, int offset) {
        log.info("PostService.getPosts (limit : {}, offset : {})", limit, offset);
        try {
            int total = postMapper.getPostsTotal();
            List<PostDto> posts = postMapper.getPosts(limit, offset);
            log.info("PostService.getPosts (total : {}, posts : {})", total, posts);

            PostsResponseDto postsResponseDto = new PostsResponseDto();
            postsResponseDto.setTotal(total);
            postsResponseDto.setPosts(posts);
            log.info("PostService.getPosts (postsResponseDto : {}", postsResponseDto);
            return postsResponseDto;
        } catch (Exception e) {
            log.error("PostService.getPosts error : {}", e.getMessage());
            return null;
        }
    }

    public void createPost(PostDto postDTO) {
        log.info("PostService.createPost (postDTO : {})", postDTO);
        try {
            postMapper.createPost(postDTO);
            log.info("PostService.createPost Created");
        } catch (Exception e) {
            log.error("PostService.createPost error : {}", e.getMessage());
        }
    }

    public PostDto updatePost(PostDto postDTO) {
        log.info("PostService.updatePost (postDTO : {})", postDTO);
        this.getPostById(postDTO.getId());
        try {
            postMapper.updatePost(postDTO);
            PostDto updatedPost = this.getPostById(postDTO.getId());
            log.info("PostService.updatePost (updatedPost : {})", updatedPost);
            return updatedPost;
        } catch (Exception e) {
            log.error("PostService.updatePost error : {}", e.getMessage());
            return null;
        }
    }

    public void deletePost(int id, String ip) {
        log.info("PostService.deletePost (id : {}, ip : {})", id, ip);
        PostDto post = this.getPostById(id);
        if (!post.getIp().equals(ip)) {
            log.info("PostService.deletePost not match the author's ip. (ip : {})", ip);
            throw new UnauthorizedException("not match the author's ip. (ip : " + ip + ")");
        }
        try {
            postMapper.deletePost(id);
            log.info("PostService.deletePost Deleted");
        } catch (Exception e) {
            log.error("PostService.deletePost error : {}", e.getMessage());
        }
    }
}