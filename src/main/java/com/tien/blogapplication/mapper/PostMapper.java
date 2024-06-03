package com.tien.blogapplication.mapper;

import com.tien.blogapplication.entity.CategoryEntity;
import com.tien.blogapplication.entity.PostEntity;
import com.tien.blogapplication.model.Post;
import com.tien.blogapplication.response.PostResponse;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostMapper {
    public PostEntity toPostEntity(Post postRequest, List<CategoryEntity> categoryEntityList){
        PostEntity post = new PostEntity();
        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());
        post.setUsername(postRequest.getUsername());
        post.setEmail(postRequest.getEmail());
        post.setCategories(categoryEntityList);
        post.setPhoto(postRequest.getPhoto());
        post.setTimeStamp(postRequest.getTimeStamp());
        return post;
    }

    public PostResponse toPostResponse(PostEntity post){
        byte[] photoBytes = null;
        Blob photoBlob = post.getPhoto();
        if (photoBlob != null){
            try {
                photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
            } catch (SQLException e){
                throw new RuntimeException("Error retrieving photo");
            }
        }
        List<String> categoriesResponsesList = new ArrayList<>();
        for(CategoryEntity category: post.getCategories()){
            categoriesResponsesList.add(category.getName());
        }
        return new PostResponse(post.getId(), post.getTitle(), post.getDescription(), post.getUsername(), post.getEmail(), categoriesResponsesList, photoBytes , post.getTimeStamp());
    }



}
