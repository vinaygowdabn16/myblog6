package com.blopapi.service.impl;


import com.blopapi.entity.Comment;
import com.blopapi.entity.Post;
import com.blopapi.exceptions.ResourceNotFoundException;
import com.blopapi.payload.CommentDto;
import com.blopapi.repository.CommentRepository;
import com.blopapi.repository.PostRepository;
import com.blopapi.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepo;

    private CommentRepository commentRepo;

    public CommentServiceImpl(CommentRepository commentRepo,PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Post post=postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException(postId)
        );

    Comment comment = new Comment();

    comment.setName(commentDto.getName());
    comment.setEmail(commentDto.getEmail());
    comment.setBody(commentDto.getBody());
    comment.setPost(post);

    Comment savedDto= commentRepo.save(comment);

    CommentDto dto = new CommentDto();
    dto.setId(savedDto.getId());
    dto.setName(savedDto.getName());
    dto.setEmail(savedDto.getEmail());
    dto.setBody(savedDto.getBody());

    return dto;
    }

    @Override
    public List<CommentDto> findCommentByPostId(long postId) {


       Post post= postRepo.findById(postId).orElseThrow(

                ()->new ResourceNotFoundException(postId)
       );

        List<Comment> comments = commentRepo.findByPostId(postId);

        List<CommentDto> dtos =comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public void deleteCommentById(Long postId, Long id) {

        Post post=postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException(postId)
        );

        Comment comment = commentRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(id)
        );

        commentRepo.deleteById(id);

    }

    @Override
    public CommentDto findCommentById(Long postId, Long id) {

       Post post = postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException(postId)
        );

      Comment comment= commentRepo.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException(id)
       );

            CommentDto dto = mapToDto(comment);

        return dto;
    }

    @Override
    public CommentDto updateComment(Long postId, Long id, CommentDto commentDto) {

        Post post = postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException(postId)
        );

       Comment comment =  commentRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id)
        );

       comment.setName(commentDto.getName());
       comment.setEmail(commentDto.getEmail());
       comment.setBody(commentDto.getBody());

       Comment updatedComment = commentRepo.save(comment);

      CommentDto updatedDto =  mapToDto(updatedComment);

        return updatedDto;
    }

    CommentDto mapToDto(Comment comment){

        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        return dto;
    }
}
