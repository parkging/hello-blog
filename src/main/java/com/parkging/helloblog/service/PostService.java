package com.parkging.helloblog.service;

import com.parkging.helloblog.domain.Category;
import com.parkging.helloblog.domain.Post;
import com.parkging.helloblog.domain.Thumbnail;
import com.parkging.helloblog.repository.CategoryRepository;
import com.parkging.helloblog.repository.PostPreview;
import com.parkging.helloblog.repository.PostRepository;
import com.parkging.helloblog.repository.ThumbnailRepository;
import com.parkging.helloblog.web.util.BASE64DecodedMultipartFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final ThumbnailRepository thumbnailRepository;

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Transactional
    public Post save(Long categoryId, String title, String content, BASE64DecodedMultipartFile based64ThumbnailImage) throws IOException {

        Category category = categoryRepository.findById(categoryId).orElse(null);

        if(category == null) {
            throw new NoSuchElementException("카테고리가 존재하지 않습니다");
        }

        Post savedPost = postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .category(category)
                .preview(getPreview(content))
                .build());

        saveThumbnail(based64ThumbnailImage, savedPost);

        return savedPost;
    }
    @Transactional
    public Long update(Long postId, Long categoryId, String title, String content, BASE64DecodedMultipartFile based64ThumbnailImage) throws IOException {

        Category category = categoryRepository.findById(categoryId).orElse(null);
        if(category == null) {
            throw new NoSuchElementException("카테고리가 존재하지 않습니다");
        }

        Post post = postRepository.findById(postId).orElse(null);
        if(post == null) {
            throw new NoSuchElementException("포스트가 존재하지 않습니다");
        }

        post.update(category, title, content, getPreview(content));

        saveThumbnail(based64ThumbnailImage, post);

        return postId;
    }

    public List<PostPreview> findAll(Pageable pageable) {
        return postRepository.findAllBy(pageable);
    }

    public Long countBy() {
        return postRepository.countBy();
    }

    public Long countByCategory(Category category) {
        return postRepository.countByCategory(category);
    }

    public List<PostPreview> findAllByCategory(Category category, Pageable pageable) {
        return postRepository.findAllByCategory(category, pageable);
    }

    @Transactional
    public void deleteById(Long postId) {

        thumbnailRepository.deleteByPostId(postId);
        postRepository.deleteById(postId);
    }

    private void saveThumbnail(BASE64DecodedMultipartFile based64ThumbnailImage, Post post) throws IOException {

        if(based64ThumbnailImage != null && based64ThumbnailImage.getSize() == 0) {
             based64ThumbnailImage = null;
        }
        Thumbnail thumbnail = thumbnailRepository.findByPost(post).orElse(null);

        if(based64ThumbnailImage == null && thumbnail == null) {
            //do-nothing.
        } else if(based64ThumbnailImage == null && thumbnail != null) {
            //do-nothing.
//            thumbnailRepository.deleteById(thumbnail.getId());
        } else if(based64ThumbnailImage != null && thumbnail == null) {
            thumbnail = new Thumbnail(based64ThumbnailImage.getOriginalFilename(), based64ThumbnailImage.getOriginalFilename(),
                    based64ThumbnailImage.getContentType(), based64ThumbnailImage.getBased64ImgContent(), post);
            thumbnailRepository.save(thumbnail);
        } else if(based64ThumbnailImage != null && thumbnail != null){
            thumbnail.setImage(based64ThumbnailImage.getBased64ImgContent());
            thumbnail.setName(based64ThumbnailImage.getOriginalFilename());
            thumbnail.setOriginalFilename(based64ThumbnailImage.getOriginalFilename());
            thumbnail.setContentType(based64ThumbnailImage.getContentType());
        }

    }

    private static String getPreview(String content) {
        return content.substring(0, content.length() < 100 ? content.length() : 100)
                + (content.length() < 100 ? "" : "...");
    }
}
