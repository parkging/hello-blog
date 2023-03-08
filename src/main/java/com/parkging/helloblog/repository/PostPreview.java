package com.parkging.helloblog.repository;

import com.parkging.helloblog.domain.Category;

import java.time.LocalDateTime;

public interface PostPreview {
    Long getId();
    String getTitle();
    Category getCategory();
    String getPreview();
    LocalDateTime getCreatedDate();

}
