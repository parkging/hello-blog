package com.parkging.helloblog.web.boarder;

import com.parkging.helloblog.domain.post.domain.PostCategory;
import com.parkging.helloblog.domain.post.application.PostCategoryService;
import com.parkging.helloblog.domain.post.application.PostService;
import com.parkging.helloblog.domain.post.dto.PostCategoryForm;
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
    private final PostCategoryService postCategoryService;
    private final PostService postService;

    @GetMapping("/sidemenu-category")
    public String sidemenu(Model model) {
        List<PostCategory> categories = postCategoryService.findByAll();
        Long allPostCount = postService.countBy();
        List<PostCategoryForm> postCategoryForms = new ArrayList<>();
        for (PostCategory postCategory : categories) {
            postCategoryForms.add(PostCategoryForm.builder()
                    .id(postCategory.getId())
                    .name(postCategory.getName())
                    .parentId(postCategory.getParentId())
                    .hasChild(postCategory.getChild().size() > 0? true:false)
                    /**
                     *  todo N+1 이랑 동일하게 수행됨 -> category 조회 시 post갯수까지 한방쿼리 신규 메쏘드 추가해야함
                     * */
                    .postCount(postService.countByCategory(postCategory))
                    .build());

        }
        model.addAttribute("categoryForms", postCategoryForms);
        model.addAttribute("allPostCount", allPostCount);

        return "fragment/category";
    }
}
