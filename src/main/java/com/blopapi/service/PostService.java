package com.blopapi.service;

import com.blopapi.payload.PostDto;

import java.util.List;

public interface PostService {

    public PostDto createPost(PostDto postDto);

    public PostDto getPostById(Long id);

   public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    public void deletePost(Long id);

  public  PostDto updatePost(Long id, PostDto postDto);
}
