package com.h2o.board.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.h2o.board.post.dto.PostDto;

@Mapper
public interface PostMapper {

    public PostDto getPostById(int id);
    public List<PostDto> getPosts(int limit, int offset);
    public int getPostsTotal() ;
    public void createPost(PostDto postDTO);
    public void updatePost(PostDto postDTO);
    public void deletePost(int id);
}