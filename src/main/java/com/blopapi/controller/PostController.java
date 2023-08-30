package com.blopapi.controller;

import com.blopapi.entity.Post;
import com.blopapi.payload.PostDto;
import com.blopapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result) {

        if(result.hasErrors()){

            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
        PostDto savedDto = postService.createPost(postDto);

        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id")Long id){

        PostDto dto = postService.getPostById(id);

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping
    public List<PostDto> getAllPost(@RequestParam(value="pageNo",defaultValue="0",required=false) int pageNo,
                                    @RequestParam(value="pageSize",defaultValue="5",required=false) int pageSize,
                                    @RequestParam(value="sortBy", defaultValue="id",required=false) String sortBy,
                                    @RequestParam(value="sortDir",defaultValue="asc",required=false) String sortDir
                                    ){

        List<PostDto> postDtos = postService.getAllPost(pageNo,pageSize,sortBy,sortDir);

        return postDtos;


    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id){

        postService.deletePost(id);

        return new ResponseEntity<>("post is deleted",HttpStatus.OK);


    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id")Long id,
                                              @RequestBody PostDto postDto){

        PostDto dto = postService.updatePost(id,postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }



}
