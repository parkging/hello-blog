package com.parkging.helloblog.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Thumbnail {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String originalFilename;
    private String contentType;
    @Lob
    private String image;

    /**
     * 외래키 삭제 예정
     */
//    @OneToOne(cascade = CascadeType.REMOVE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @NotNull
    private Post post;

    @Builder
    public Thumbnail(String name, String originalFilename, String contentType, String image, Post post) {
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.image = image;
        this.post = post;
    }

    public void update(String image, String name, String originalFilename, String contentType) {
        this.image = image;
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
    }




}
