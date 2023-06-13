package com.h2o.board.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.h2o.board.post.dto.PostDto;

@Mapper
public interface PostMapper {

    PostDto getPostById(int id);
    List<PostDto> getPosts(int limit, int offset);
    int getPostsTotal() ;
    void createPost(PostDto postDTO);
    void updatePost(PostDto postDTO);
    void deletePost(int id);
}