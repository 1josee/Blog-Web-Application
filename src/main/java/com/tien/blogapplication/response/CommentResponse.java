package com.tien.blogapplication.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;
    private Long postId;
    private String user_name;
    private String timeStamp;
}
