package com.tien.blogapplication.service;

import com.tien.blogapplication.model.Comment;
import com.tien.blogapplication.response.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(Comment comment);

    List<CommentResponse> getAllCommentsOfPost(Long postId);

    void deleteComment(Long commentId);

    CommentResponse updateComment(Long commentId, String content);
}
