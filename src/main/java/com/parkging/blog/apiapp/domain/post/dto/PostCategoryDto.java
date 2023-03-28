package com.parkging.blog.apiapp.domain.post.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class PostCategoryDto {
    @NotEmpty
    private String name;
    private Long parentPostCategoryId;

}
