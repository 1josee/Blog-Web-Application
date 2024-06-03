package com.tien.blogapplication.service;

import com.tien.blogapplication.entity.CategoryEntity;
import com.tien.blogapplication.entity.PostEntity;
import com.tien.blogapplication.mapper.PostMapper;
import com.tien.blogapplication.model.Post;
import com.tien.blogapplication.repository.CategoryRepository;
import com.tien.blogapplication.repository.PostRepository;
import com.tien.blogapplication.response.PostResponse;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.postMapper = postMapper;
    }

    @Override
    public PostResponse addPost(Post postRequest) throws SQLException, IOException {
        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        for(String categoryName : postRequest.getCategories()){
            CategoryEntity category = categoryRepository.findByName(categoryName);
            if(category != null){
                categoryEntityList.add(category);
            } else {
                CategoryEntity categoryEntity = new CategoryEntity(categoryName);
                categoryRepository.save(categoryEntity);
                categoryEntityList.add(categoryEntity);
            }
        }
        if(!postRequest.getPhotoUpload().isEmpty()){
            byte[] photoBytes = postRequest.getPhotoUpload().getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            postRequest.setPhoto(photoBlob);
        }
        PostEntity post = postMapper.toPostEntity(postRequest, categoryEntityList);
        postRepository.save(post);
        PostResponse postResponse = postMapper.toPostResponse(post);
        return postResponse;
    }

    @Override
    public List<PostResponse> findAllPost() {
        List<PostResponse> postResponseList = postRepository.findAll()
                .stream()
                .map(post ->{
                    return postMapper.toPostResponse(post);
                })
                .collect(Collectors.toList());
        return postResponseList;
    }

    @Override
    public PostResponse findPostById(Long postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("No post found with ID:" + postId));
        return postMapper.toPostResponse(post);
    }

    @Override
    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }


    @Override
    public List<PostResponse> findAllPostsByEmail(String email) {
        List<PostResponse> postResponseList = postRepository.findByEmail(email)
                .stream()
                .map(post -> {
                    return postMapper.toPostResponse(post);
                })
                .collect(Collectors.toList());
        return postResponseList;
    }


}
