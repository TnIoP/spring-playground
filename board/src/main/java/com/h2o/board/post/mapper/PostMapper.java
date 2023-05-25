package com.h2o.board.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.h2o.board.post.dto.PostDTO;

@Mapper
public interface PostMapper {

    public PostDTO getPostById(int id) throws Exception;
    public List<PostDTO> getPosts() throws Exception;

    public void createPost(PostDTO postDTO) throws Exception;
    public void updatePost(PostDTO postDTO) throws Exception;
    public void deletePost(int id) throws Exception;
}