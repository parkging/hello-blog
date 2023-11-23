package com.parkging.blog.apiapp.domain.post.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_category_id")
    private long id;

    @Setter(AccessLevel.PROTECTED)
    @Column(nullable = false)
    private String name;

    @Setter(AccessLevel.PROTECTED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private PostCategory parent;

    @Builder
    public PostCategory(String name, PostCategory parent) {
        this.setName(name);
        if(parent != null) {
            this.setParent(parent);
        }
    }

    public Long update(String name, PostCategory parent) {
        this.setName(name);
        if(parent != null) {
            this.setParent(parent);
        }
        return this.getId();
    }
}
