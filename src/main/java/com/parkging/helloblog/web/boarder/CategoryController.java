package com.parkging.helloblog.web.boarder;

import com.parkging.helloblog.domain.Category;
import com.parkging.helloblog.exception.HasChildCategoryException;
import com.parkging.helloblog.exception.NoCategoryException;
import com.parkging.helloblog.exception.NoParentCategoryException;
import com.parkging.helloblog.exception.PostExistException;
import com.parkging.helloblog.service.CategoryService;
import com.parkging.helloblog.service.PostService;
import com.parkging.helloblog.web.boarder.form.CategoryForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping("category/edit")
    public String category(Model model) {
        addCategorys(model);
        model.addAttribute("deleteCategoryForm", CategoryForm.builder().build());
        return "editor/category-editor";
    }

    @PostMapping("category/edit")
    public String category(@Valid @ModelAttribute CategoryForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editor/category-editor";
        }

        try {
            categoryService.addCategory(form.getName(), form.getParentId());
        } catch (NoParentCategoryException e) {
            log.error(e.getMessage());
            bindingResult.reject("noParentCategory", e.getMessage());
            return "editor/category-editor";
        }

        return "redirect:/";
    }

    @GetMapping("category/add")
    public String addCategory(@ModelAttribute CategoryForm categoryForm) {
//        categoryForm.setParentId(parentId);
        return "editor/category-add";
    }

    @PostMapping("category/add")
    public String addCategory(@Valid @ModelAttribute CategoryForm form, BindingResult bindingResult) {
        log.info("form={}", form);
        if (bindingResult.hasErrors()) {
            return "editor/category-editor";
        }

        try {
            categoryService.addCategory(form.getName(), form.getParentId());
        } catch (NoParentCategoryException e) {
            log.error(e.getMessage());
            bindingResult.reject("noParentCategory", e.getMessage());
            return "editor/category-editor";
        }

        return "redirect:/category/edit";
    }

    @PostMapping("category/delete")
    public String deleteCategory(@Valid @ModelAttribute CategoryForm category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editor/category-editor";
        }

        try {
            categoryService.deleteCategory(category.getId());
        } catch (NoCategoryException | HasChildCategoryException e) {
            log.error(e.getMessage());
            bindingResult.reject("canNotDeleteCategory", e.getMessage());
            return "editor/category-editor";
        } catch (PostExistException e) {
            log.error(e.getMessage());
            bindingResult.reject("postRemainError", e.getMessage());
            return "editor/category-editor";
        }

        return "redirect:/category/edit";
    }

    private void addCategorys(Model model) {
        List<Category> categories = categoryService.findByAll();

        ArrayList<CategoryForm> categoryForms = new ArrayList<>();
        for (Category category : categories) {
            categoryForms.add(CategoryForm.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .parentId(category.getParentId())
                    .hasChild(category.getChild().size() > 0? true:false)
                    .build());
        }
        model.addAttribute("categoryForms", categoryForms);
    }
}
