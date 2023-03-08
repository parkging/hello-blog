package com.parkging.helloblog.service;

import com.parkging.helloblog.domain.Category;
import com.parkging.helloblog.exception.HasChildCategoryException;
import com.parkging.helloblog.exception.NoCategoryException;
import com.parkging.helloblog.exception.NoParentCategoryException;
import com.parkging.helloblog.exception.PostExistException;
import com.parkging.helloblog.repository.CategoryRepository;
import com.parkging.helloblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long addCategory(String name, Long parentId) {
        Category newCategory = Category.builder()
                .name(name)
                .build();

        if(parentId != null) {
            Category parent = categoryRepository.findById(parentId).orElse(null);
            if(parent == null) {
                throw new NoParentCategoryException("상위 카테고리가 존재하지 않습니다.");
            }
            parent.addChild(newCategory);
        }

        return categoryRepository.save(newCategory).getId();
    }

    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category findByName(String categoryName) {
        return categoryRepository.findByName(categoryName).orElse(null);
    }

    public List<Category> findByAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null) {
            throw new NoCategoryException("카테고리가 존재하지 않습니다");
        }

        if(category.getChild().size() > 0) {
            throw new HasChildCategoryException("하위 카테고리가 존재합니다.");
        }

        Long postCnt = postRepository.countByCategory(category);

        if(postCnt > 0) {
            throw new PostExistException("하위 포스트가 존재합니다.");
        }

        categoryRepository.delete(category);
    }


}
