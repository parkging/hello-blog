package com.parkging.helloblog.service;

import com.parkging.helloblog.domain.Category;
import com.parkging.helloblog.exception.NoParentCategoryException;
import com.parkging.helloblog.web.boarder.form.CategoryForm;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Slf4j
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    EntityManager em;

    @Test
    public void 카테고리추가() {

        Long newCategoryId = categoryService.addCategory("카테고리1", null);

        Category newCategory = categoryService.findById(newCategoryId);

        Assertions.assertThat(newCategoryId).isEqualTo(newCategory.getId());
    }

    @Test
    public void 카테고리추가_잘못된부모() {
        org.junit.jupiter.api.Assertions.assertThrows(NoParentCategoryException.class,
                () -> {
            categoryService.addCategory("카테고리1", 11L);
        });
    }

    @Test
    public void 카테고리_부모_추가() {
        Long parentId = categoryService.addCategory("부모카테고리", null);
        Long childId = categoryService.addCategory("자식카테고리", parentId);

        Category parent = categoryService.findById(parentId);
        Category child = categoryService.findById(childId);

        Assertions.assertThat(child).isEqualTo(parent.getChild().get(0));
        Assertions.assertThat(child.getParent()).isEqualTo(parent);

    }

    @Test
    public void jpqlTest() {
        Object o = em.createQuery("select p.id, p.title, substring(p.content, 1, 20) from Post p")
                .getResultList()
                .get(0);



    }

    @Test
    public void findByAllTest() {
        List<Category> categories = categoryService.findByAll();

        List<CategoryForm> categoryForms = new ArrayList<>();
        for (Category category : categories) {
            categoryForms.add(CategoryForm.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .parentId(category.getParentId())
                    .hasChild(category.getChild().size() > 0? true:false)
//                    .postCount(postService.countByCategory(category))
                    .build());

        }
    }

}