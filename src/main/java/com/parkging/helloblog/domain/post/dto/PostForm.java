package com.parkging.helloblog.web.boarder.form;

import com.parkging.helloblog.web.util.BASE64DecodedMultipartFile;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class PostForm {

    private Long postId;
    @NotNull
    private Long categoryId;
    @NotEmpty
    private String title;
//    @NotEmpty
    private String content;

    private String categoryFullName;

    private String preview;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private MultipartFile thumbnailImage;

    private BASE64DecodedMultipartFile based64ThumbnailImage;

    @Builder
    public PostForm(Long postId, Long categoryId, String title,
                    String content, String categoryFullName, String preview,
                    LocalDateTime createdDate, LocalDateTime modifiedDate,
                    MultipartFile thumbnailImage, BASE64DecodedMultipartFile based64ThumbnailImage) {
        this.setPostId(postId);
        this.setCategoryId(categoryId);
        this.setTitle(title);
        this.setContent(content);
        this.setCategoryFullName(categoryFullName);
        this.setPreview(preview);
        this.setCreatedDate(createdDate);
        this.setModifiedDate(modifiedDate);
        this.setThumbnailImage(thumbnailImage);
        this.setBased64ThumbnailImage(based64ThumbnailImage);
    }
}
