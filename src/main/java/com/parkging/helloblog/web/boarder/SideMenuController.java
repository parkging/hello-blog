package com.parkging.helloblog.web.boarder;

import com.parkging.helloblog.domain.Category;
import com.parkging.helloblog.service.CategoryService;
import com.parkging.helloblog.service.PostService;
import com.parkging.helloblog.web.boarder.form.CategoryForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SideMenuController {
    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping("/sidemenu-category")
    public String sidemenu(Model model) {
        List<Category> categories = categoryService.findByAll();
        Long allPostCount = postService.countBy();
        List<CategoryForm> categoryForms = new ArrayList<>();
        for (Category category : categories) {
            categoryForms.add(CategoryForm.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .parentId(category.getParentId())
                    .hasChild(category.getChild().size() > 0? true:false)
                    /**
                     *  todo N+1 이랑 동일하게 수행됨 -> category 조회 시 post갯수까지 한방쿼리 신규 메쏘드 추가해야함
                     * */
                    .postCount(postService.countByCategory(category))
                    .build());

        }
        model.addAttribute("categoryForms", categoryForms);
        model.addAttribute("allPostCount", allPostCount);

        return "fragment/category";
    }
}
