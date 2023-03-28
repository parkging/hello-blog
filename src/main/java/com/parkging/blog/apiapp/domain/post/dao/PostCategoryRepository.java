package com.parkging.blog.apiapp.domain.post.dao;

import com.parkging.blog.apiapp.domain.post.domain.PostCategory;
import com.parkging.blog.apiapp.domain.post.dto.PostCategoryViewDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
    Optional<PostCategory> findByName(String categoryName);
    Optional<PostCategoryViewDto> findViewById(Long categoryId);
    List<PostCategoryViewDto> findAllBy();
}
