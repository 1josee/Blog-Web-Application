package com.tien.blogapplication.mapper;

import com.tien.blogapplication.entity.CommentEntity;
import com.tien.blogapplication.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentMapper {
    public CommentResponse toCommentResponse(CommentEntity commentEntity){
        CommentResponse commentResponse = CommentResponse.builder()
                .id(commentEntity.getId())
                .content(commentEntity.getContent())
                .postId(commentEntity.getPost().getId())
                .user_name(commentEntity.getUser().getUser_name())
                .timeStamp(commentEntity.getTimeStamp())
                .build();
        return commentResponse;
    }

    public List<CommentResponse> toCommentResponseList(List<CommentEntity> commentEntityList){
        List<CommentResponse> commentResponseList = commentEntityList
                .stream()
                .map(commentEntity -> {
                    CommentResponse commentResponse = CommentResponse.builder()
                            .id(commentEntity.getId())
                            .content(commentEntity.getContent())
                            .postId(commentEntity.getPost().getId())
                            .user_name(commentEntity.getUser().getUser_name())
                            .timeStamp(commentEntity.getTimeStamp())
                            .build();
                    return commentResponse;
                        }
                ).collect(Collectors.toList());
        return commentResponseList;
    }
}
