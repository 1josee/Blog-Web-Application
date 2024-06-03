package com.tien.blogapplication.repository;

import com.tien.blogapplication.entity.CategoryEntity;
import com.tien.blogapplication.entity.PostEntity;
import com.tien.blogapplication.response.PostResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findByEmail(String email);
}
