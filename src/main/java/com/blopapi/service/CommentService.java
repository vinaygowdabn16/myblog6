package com.blopapi.service;

import com.blopapi.payload.CommentDto;
import com.blopapi.payload.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    public CommentDto createComment(Long postId, CommentDto commentDto);

  public  List<CommentDto> findCommentByPostId(long postId);

   public void deleteCommentById(Long postId, Long id);

   public CommentDto findCommentById(Long postId, Long id);

   public CommentDto updateComment(Long postId, Long id, CommentDto commentDto);
}
