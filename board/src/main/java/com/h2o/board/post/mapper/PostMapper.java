package com.h2o.board.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.h2o.board.post.dto.PostDto;

@Mapper
public interface PostMapper {

    public PostDto getPostById(int id) throws Exception;
    public List<PostDto> getPosts(int limit, int offset) throws Exception;

    public int getPostsTotal() throws Exception;

    public void createPost(PostDto postDTO) throws Exception;
    public void updatePost(PostDto postDTO) throws Exception;
    public void deletePost(int id) throws Exception;
}