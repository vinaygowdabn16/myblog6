package com.blopapi.controller;

import com.blopapi.payload.CommentDto;
import com.blopapi.payload.PostDto;
import com.blopapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8080/api/posts/postId/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId")Long postId,
                                                 @RequestBody CommentDto commentDto){

        CommentDto dto = commentService.createComment(postId,commentDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postId}/comments")
     public List<CommentDto> findCommentByPostId(@PathVariable(value= "postId") long postId) {

        List<CommentDto> dtos =commentService.findCommentByPostId(postId);

        return dtos;

    }

    //http://localhost:8080/api/posts/1/comments/1
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value="postId")Long postId,
                                                @PathVariable(value="id")Long id){

        commentService.deleteCommentById(postId,id);

        return new ResponseEntity<>("comment is deleted",HttpStatus.OK);
    }

    //  http://localhost:8080/api/posts/1/comments/1
    @GetMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> findCommentById(@PathVariable(value="postId")Long postId,
                                                      @PathVariable(value="id") Long id){

        CommentDto dto = commentService.findCommentById(postId,id);

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //  http//localhost:8080/api/posts/1/comments/1
    @PutMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value="postId") Long postId,
                                                    @PathVariable(value="id") Long id,
                                                    @RequestBody CommentDto commentDto){

        CommentDto updatedDto= commentService.updateComment(postId,id,commentDto);

        return new ResponseEntity<>(updatedDto,HttpStatus.OK);
    }


}
