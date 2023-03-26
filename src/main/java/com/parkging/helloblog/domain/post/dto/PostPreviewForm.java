package com.parkging.helloblog.web.boarder.form;

import com.parkging.helloblog.web.util.BASE64DecodedMultipartFile;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostPreviewForm {

    private Long postId;
    private String title;
    private String categoryFullName;
    private String preview;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private BASE64DecodedMultipartFile based64ThumbnailImage;

    @Builder
    public PostPreviewForm(Long postId, String title, String categoryFullName,
                           String preview, LocalDateTime createdDate, LocalDateTime modifiedDate,
                           BASE64DecodedMultipartFile based64ThumbnailImage) {
        this.setPostId(postId);
        this.setTitle(title);
        this.setCategoryFullName(categoryFullName);
        this.setPreview(preview);
        this.setCreatedDate(createdDate);
        this.setModifiedDate(modifiedDate);
        this.setBased64ThumbnailImage(based64ThumbnailImage);
    }
}
