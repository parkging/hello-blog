package com.parkging.helloblog.repository;

import com.parkging.helloblog.domain.Post;
import com.parkging.helloblog.domain.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThumbnailRepository extends JpaRepository<Thumbnail, Long> {
    Optional<Thumbnail> findByPost(Post post);

    Optional<Thumbnail> findByPostId(Long postId);

    void deleteByPostId(Long postId);
}
