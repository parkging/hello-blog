package com.parkging.blog.apiapp.domain.post.domain;

import com.parkging.blog.apiapp.domain.member.domain.Member;
import com.parkging.blog.apiapp.domain.model.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@Getter
@EnableJpaAuditing
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long id;
    private String title;

    @Lob
    private String content;
    private String preview;
    private String thumbnailImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_category_id")
    private PostCategory postCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @Builder
    public Post(String title, String content, PostCategory postCategory, String preview, Member member, String thumbnailImageUrl) {
        this.title = title;
        this.content = content;
        this.postCategory = postCategory;
        this.preview = preview;
        this.member = member;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public Long update(PostCategory postCategory, String title, String content, String preview, String thumbnailImageUrl) {
        this.postCategory = postCategory;
        this.title = title;
        this.content = content;
        this.preview = preview;
        this.thumbnailImageUrl = thumbnailImageUrl;
        return this.id;
    }
}
