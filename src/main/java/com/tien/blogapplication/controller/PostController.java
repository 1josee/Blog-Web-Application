package com.tien.blogapplication.controller;

import com.tien.blogapplication.model.Post;
import com.tien.blogapplication.response.CommentResponse;
import com.tien.blogapplication.response.PostResponse;
import com.tien.blogapplication.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping("/create-new-post")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PostResponse> createPost(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam List<String> categories,
            @RequestParam MultipartFile photoUpload
            ) throws SQLException, IOException {
        System.out.println(title);
        Post post = Post.builder()
                .title(title)
                .description(description)
                .username(username)
                .email(email)
                .categories(categories)
                .photoUpload(photoUpload)
                .timeStamp(new Date().toString())
                .build();
       PostResponse postResponse = postService.addPost(post);
        return ResponseEntity.ok(postResponse);
    }


    @GetMapping("/all-posts")
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        List<PostResponse> postResponseList = postService.findAllPost();
        return ResponseEntity.ok(postResponseList);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPostDetailById(
            @PathVariable("id") Long postId
    ){
        try{
            PostResponse postResponse = postService.findPostById(postId);
            return ResponseEntity.ok(postResponse);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/post/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePost(
            @PathVariable("id") Long postId
    ){
        postService.deletePostById(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/user/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<PostResponse>> getAllUserPosts(
            @PathVariable("email") String email
    ){
        List<PostResponse> postResponseList = postService.findAllPostsByEmail(email);
        return ResponseEntity.ok(postResponseList);
    }



}
