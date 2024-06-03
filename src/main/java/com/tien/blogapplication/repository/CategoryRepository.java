package com.tien.blogapplication.repository;

import com.tien.blogapplication.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String categoryName);

}
