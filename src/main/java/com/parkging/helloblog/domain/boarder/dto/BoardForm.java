package com.parkging.helloblog.web.boarder.form;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class BoardForm {
    private String name;
    private Long postCount;
    private List<PostPreviewForm> posts;
    private Integer startPage;
    private Integer endPage;
    private Integer currentPage;
    private Integer pageSize;

    @Builder
    public BoardForm(String name, Long postCount, List<PostPreviewForm> posts, Integer startPage, Integer endPage, Integer currentPage, Integer pageSize) {
        this.setName(name);
        this.setPostCount(postCount);
        this.setPosts(posts);
        this.setStartPage(startPage);
        this.setEndPage(endPage);
        this.setCurrentPage(currentPage);
        this.setPageSize(pageSize);
    }
}
