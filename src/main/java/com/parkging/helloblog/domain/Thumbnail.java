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

    @Setter
    private String name;
    @Setter
    private String originalFilename;
    @Setter
    private String contentType;

    @Setter
    @Lob
    private String image;

    /**
     * 외래키를 POST에 둬야 했었다.. 후회막심 추후 수정예정
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

}
