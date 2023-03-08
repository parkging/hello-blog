package com.parkging.helloblog.service;

import com.parkging.helloblog.domain.Category;
import com.parkging.helloblog.domain.Thumbnail;
import com.parkging.helloblog.repository.PostPreview;
import com.parkging.helloblog.repository.ThumbnailRepository;
import com.parkging.helloblog.web.boarder.form.BoardForm;
import com.parkging.helloblog.web.boarder.form.PostPreviewForm;
import com.parkging.helloblog.web.util.BASE64DecodedMultipartFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoarderService {
    private final PostService postService;
    private final CategoryService categoryService;
    private final ThumbnailRepository thumbnailRepository;

    public BoardForm getBoardForm(String categoryName, Pageable pageable) {

        List<PostPreview> postPreviews;
        Long postCount;
        String boardName;

        if (categoryName == null) {
            postPreviews = postService.findAll(pageable);
            postCount = postService.countBy();
            boardName = "전체 글";
        } else {
            Category category = categoryService.findByName(categoryName);
            postPreviews = postService.findAllByCategory(category, pageable);
            postCount = postService.countByCategory(category);
            boardName = category.getName();

        }

        List<PostPreviewForm> posts = new ArrayList<>();

        for (PostPreview postPreview : postPreviews) {

            posts.add(PostPreviewForm.builder()
                    .postId(postPreview.getId())
                    .title(postPreview.getTitle())
                    .categoryFullName(getCategoryFullName(postPreview.getCategory().getName(), postPreview))
                    .preview(postPreview.getPreview())
                    .createdDate(postPreview.getCreatedDate())
                    .based64ThumbnailImage(getBased64ThumbnailImage(postPreview))
                    .build());
        }

        int currentPage = getCurrentPage(pageable);    //현재페이지
        int pageSize = getPageSize(pageable);
        int startPage = getStartPage(currentPage, pageSize);
        int endPage = getEndPage(pageSize, startPage, postCount);

        BoardForm boardForm = BoardForm.builder()
                .name(boardName)
                .postCount(postCount)
                .posts(posts)
                .startPage(startPage)
                .currentPage(currentPage)
                .endPage(endPage)
                .pageSize(pageSize)
                .build();

        return boardForm;
    }

    private static String getCategoryFullName(String categoryName, PostPreview postPreview) {
        Category parent = postPreview.getCategory().getParent();
        String categoryFullName = "" + categoryName;
        if(parent != null) {
            categoryFullName += "/"+parent.getName();
        }
        return categoryFullName;
    }

    private static int getEndPage(int pageSize, int startPage, Long postCount) {
        int endPage = startPage + pageSize - 1;
        int maxPage = (int) (((postCount - 1) / pageSize) + 1);

        return (endPage < maxPage) ? endPage : maxPage;
    }

    private static int getStartPage(int currentPage, int pageSize) {
        return ((currentPage - 1) / pageSize) * pageSize + 1;
    }

    private static int getPageSize(Pageable pageable) {
        return pageable.getPageSize();
    }

    private static int getCurrentPage(Pageable pageable) {
        return pageable.getPageNumber() + 1;
    }

    private BASE64DecodedMultipartFile getBased64ThumbnailImage(PostPreview postPreview) {
        Thumbnail thumbnail = thumbnailRepository.findByPostId(postPreview.getId()).orElse(null);
        BASE64DecodedMultipartFile based64ThumbnailImage = null;
        if(thumbnail != null) {
            based64ThumbnailImage = new BASE64DecodedMultipartFile(thumbnail.getImage(),
                    thumbnail.getName(), thumbnail.getOriginalFilename(), thumbnail.getContentType());
        }
        return based64ThumbnailImage;
    }
}
