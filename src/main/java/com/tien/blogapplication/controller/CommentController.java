package com.tien.blogapplication.controller;

import com.tien.blogapplication.model.Comment;
import com.tien.blogapplication.response.CommentResponse;
import com.tien.blogapplication.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createComment(
            @RequestBody Comment comment
            ){
        try {
            CommentResponse response = commentService.createComment(comment);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/post/{postId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllCommentsOfPost(
            @PathVariable("postId") Long postId
    ){
        try{
            List<CommentResponse> responses = commentService.getAllCommentsOfPost(postId);
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{commentId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteComment(
            @PathVariable("commentId") Long commentId
    ){
        commentService.deleteComment(commentId);
    }

    @PutMapping("/update/{commentId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateComment(
            @PathVariable("commentId") Long commentId,
            @RequestParam("content") String content
    ){
        try{
            CommentResponse response = commentService.updateComment(commentId, content);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}
