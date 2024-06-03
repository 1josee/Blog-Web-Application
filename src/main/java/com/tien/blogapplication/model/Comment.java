package com.tien.blogapplication.model;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String content;
    private Long postId;
    private String email;
}
