package com.tien.blogapplication.service;

import com.tien.blogapplication.entity.CommentEntity;
import com.tien.blogapplication.entity.PostEntity;
import com.tien.blogapplication.entity.UserEntity;
import com.tien.blogapplication.mapper.CommentMapper;
import com.tien.blogapplication.model.Comment;
import com.tien.blogapplication.repository.CommentRepository;
import com.tien.blogapplication.repository.PostRepository;
import com.tien.blogapplication.repository.UserRepository;
import com.tien.blogapplication.response.CommentResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    @Override
    public CommentResponse createComment(Comment commentRequest) {
        PostEntity post = postRepository.findById(commentRequest.getPostId()).orElseThrow(() -> new EntityNotFoundException("Post not found"));
        UserEntity user = userRepository.findByEmail(commentRequest.getEmail()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        System.out.println(commentRequest.getEmail());
        CommentEntity comment = CommentEntity.builder()
                .content(commentRequest.getContent())
                .post(post)
                .user(user)
                .timeStamp(new Date().toString())
                .build();
        commentRepository.save(comment);
        CommentResponse commentResponse = commentMapper.toCommentResponse(comment);
        return commentResponse;
    }

    @Override
    public List<CommentResponse> getAllCommentsOfPost(Long postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found"));
        List<CommentResponse> commentResponseList = commentMapper.toCommentResponseList(post.getComments());
        return commentResponseList;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentResponse updateComment(Long commentId, String content) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.setContent(content);
        commentRepository.save(comment);
        return commentMapper.toCommentResponse(comment);
    }
}
