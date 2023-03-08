package com.parkging.helloblog.web.boarder.form;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryForm {

    private Long id;

    @NotEmpty
    private String name;

    private Long parentId;

    private Boolean hasChild;

    private Long postCount;

    @Builder
    public CategoryForm(Long id, String name, Long parentId, Boolean hasChild, Long postCount) {
        this.setId(id);
        this.setName(name);
        this.setParentId(parentId);
        this.setHasChild(hasChild);
        this.setPostCount(postCount);
    }

}
