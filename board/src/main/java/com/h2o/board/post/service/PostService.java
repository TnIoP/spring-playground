package com.h2o.board.post.service;

import java.util.List;

import com.h2o.board.exception.NotFoundException;
import com.h2o.board.post.dto.PostDTO;
import com.h2o.board.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostMapper postMapper;

    public PostDTO getPostById(int id) throws Exception {
        PostDTO post = postMapper.getPostById(id);
        System.out.println("[id:" + id + "] Service 에서 연산을 수행합니다");
        if (post == null)
            throw new NotFoundException(String.format("can't find post id: ID[%d]", id));
        return post;
    }

    public List<PostDTO> getPosts() throws Exception {
        return postMapper.getPosts();
    }

    public void createPost(PostDTO postDTO) throws Exception {
        postMapper.createPost(postDTO);
    }

    public void updatePost(PostDTO postDTO) throws Exception {
        postMapper.updatePost(postDTO);
    }

    public void deletePost(int id) throws Exception {
        postMapper.deletePost(id);
    }
}