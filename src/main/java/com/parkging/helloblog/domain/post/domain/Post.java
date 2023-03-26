package com.parkging.helloblog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@Getter
@EnableJpaAuditing
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String preview;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(String title, String content, Category category, String preview) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.preview = preview;
    }

    public void update(Category category, String title, String content, String preview) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.preview = preview;
    }
}
