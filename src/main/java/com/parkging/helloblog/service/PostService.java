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

    public static final int DO_NOTHING = 0;
    public static final int DO_CREATE = 1;
    public static final int DO_UPDATE = 2;
    public static final int MAX_PREVIEW_SIZE = 100;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final ThumbnailRepository thumbnailRepository;

    @Transactional
    public Post save(Long categoryId, String title, String content, BASE64DecodedMultipartFile based64ThumbnailImage) throws IOException {
        Category category = getCategoryWithValidate(categoryId);
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

        Category category = getCategoryWithValidate(categoryId);
        Post post = getPostWithValidate(postId);
        post.update(category, title, content, getPreview(content));
        saveThumbnail(based64ThumbnailImage, post);

        return post.getId();
    }

    @Transactional
    public void deleteById(Long postId) {
        thumbnailRepository.deleteByPostId(postId);
        postRepository.deleteById(postId);
    }

    public Post findById(Long postId) {
        return getPostWithValidate(postId);
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

    //********************************************//

    private void saveThumbnail(BASE64DecodedMultipartFile based64ThumbnailImage, Post post) throws IOException {

        based64ThumbnailImage = initBase64DecodedMultipartFile(based64ThumbnailImage);
        Thumbnail thumbnail = thumbnailRepository.findByPost(post).orElse(null);
        int modifyCase = getModifiyCase(based64ThumbnailImage, thumbnail);

        switch (modifyCase) {
            case DO_NOTHING:
                break;

            case DO_CREATE:
                thumbnailRepository.save(new Thumbnail(based64ThumbnailImage.getOriginalFilename(),
                                                        based64ThumbnailImage.getOriginalFilename(),
                                                        based64ThumbnailImage.getContentType(),
                                                        based64ThumbnailImage.getBased64ImgContent(),
                                                        post));
                break;

            case DO_UPDATE:
                thumbnail.update(based64ThumbnailImage.getBased64ImgContent(),
                                    based64ThumbnailImage.getOriginalFilename(),
                                    based64ThumbnailImage.getOriginalFilename(),
                                    based64ThumbnailImage.getContentType());
                break;

            default:
                break;

        }
    }

    private String getPreview(String content) {
        return content.replaceAll("\\!\\[.*?\\)", "")             //사진 링크 제거
                .replaceAll("\\<.*?\\>", "")                      //html테그 제거
                .replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9 .]", "")    //특수문자 제거
                .substring(0, content.length() < MAX_PREVIEW_SIZE ? content.length() : MAX_PREVIEW_SIZE)    //썸네일 글자수 제한
                + (content.length() < MAX_PREVIEW_SIZE ? "" : "...");
    }

    private Category getCategoryWithValidate(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        if(category == null) {
            throw new NoSuchElementException("카테고리가 존재하지 않습니다");
        }
        return category;
    }

    private Post getPostWithValidate(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null) {
            throw new NoSuchElementException("포스트가 존재하지 않습니다");
        }
        return post;
    }

    private static BASE64DecodedMultipartFile initBase64DecodedMultipartFile(BASE64DecodedMultipartFile based64ThumbnailImage) {
        if(based64ThumbnailImage != null && based64ThumbnailImage.getSize() == 0) {
            based64ThumbnailImage = null;
        }
        return based64ThumbnailImage;
    }

    private int getModifiyCase(Object source, Object target) {
        int modifyCase = DO_NOTHING;
        if(source == null && target == null) {
            modifyCase = DO_NOTHING;
        } else if(source == null && target != null) {
            modifyCase = DO_NOTHING;
        } else if (source != null && target == null) {
            modifyCase = DO_CREATE;
        } else if (source != null && target != null) {
            modifyCase = DO_UPDATE;
        }
        return modifyCase;
    }
}
