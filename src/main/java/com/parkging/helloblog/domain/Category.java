package com.parkging.helloblog.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue
    private Long Id;

    @Setter(AccessLevel.PROTECTED)
    @Column(nullable = false)
    private String name;

    @Setter(AccessLevel.PROTECTED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public void addChild(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

    public Long getParentId() {
        if(parent == null) return null;
        else return parent.getId();
    }

    @Builder
    public Category(String name) {
        this.setName(name);
    }

}
