package com.tien.blogapplication.service;

import com.tien.blogapplication.model.Post;
import com.tien.blogapplication.response.PostResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PostService {
    PostResponse addPost(Post post) throws SQLException, IOException;

    List<PostResponse> findAllPost();

    PostResponse findPostById(Long postId);

    void deletePostById(Long postId);

    List<PostResponse> findAllPostsByEmail(String email);
}
