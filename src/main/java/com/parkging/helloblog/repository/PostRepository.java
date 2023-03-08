package com.parkging.helloblog.repository;

import com.parkging.helloblog.domain.Category;
import com.parkging.helloblog.domain.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<PostPreview> findAllBy(Pageable pageable);
    Long countByCategory(Category category);
    Long countBy();
    List<PostPreview> findAllByCategory(Category category, Pageable pageable);

}

