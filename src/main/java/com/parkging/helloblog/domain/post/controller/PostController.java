package com.parkging.helloblog.web.boarder;

import com.parkging.helloblog.domain.Category;
import com.parkging.helloblog.domain.Post;
import com.parkging.helloblog.domain.Thumbnail;
import com.parkging.helloblog.repository.ThumbnailRepository;
import com.parkging.helloblog.service.CategoryService;
import com.parkging.helloblog.service.PostService;
import com.parkging.helloblog.web.boarder.form.CategoryForm;
import com.parkging.helloblog.web.boarder.form.PostForm;
import com.parkging.helloblog.web.util.BASE64DecodedMultipartFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final CategoryService categoryService;
    private final PostService postService;
    private final ThumbnailRepository thumbnailRepository;

    @GetMapping("post/{postId}")
    public String post(@PathVariable(name = "postId") Long postId, Model model) {

        addCategorys(model);
        Post post = postService.findById(postId);
        if(post == null) {
            throw new NoSuchElementException("포스트가 존재하지 않습니다.");
        }

        PostForm postForm = PostForm.builder()
                .postId(post.getId())
                .categoryId(post.getCategory().getId())
                .title(post.getTitle())
                .content(post.getContent())
                .categoryFullName(getCategoryFullName(post.getCategory().getName(), post))
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .based64ThumbnailImage(getThumbnailImage(post))
                .build();

        model.addAttribute("postForm", postForm);

        return "editor/post-viewer";
    }

    @GetMapping("post/0/add")
    public String editor(Model model) {

        addCategorys(model);
        model.addAttribute("postForm", PostForm.builder().build());

        return "editor/post-editor";
    }

    @GetMapping("post/{postId}/edit")
    public String updatePost(@PathVariable(name = "postId") Long postId, Model model) {

        Post post = postService.findById(postId);
        PostForm postForm = PostForm.builder()
                .postId(post.getId())
                .categoryId(post.getCategory().getId())
                .title(post.getTitle())
                .content(post.getContent())
                .thumbnailImage(getThumbnailImage(post))
                .build();
        addCategorys(model);

        model.addAttribute("postForm", postForm);

        return "editor/post-editor";
    }

    @PostMapping("post/0/add")
    public String addPost(@Valid @ModelAttribute PostForm postForm, BindingResult bindingResult) throws IOException {

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            return "editor/post-editor";
        }

        Long savedPostId = postService.save(postForm.getCategoryId(), postForm.getTitle(), postForm.getContent(), getBase64DecodedMultipartFile(postForm.getThumbnailImage())).getId();

        return "redirect:/post/" + savedPostId + "/edit";
    }

    @PostMapping("post/{postId}/update")
    public String updatePost(@PathVariable Long postId, @Valid @ModelAttribute PostForm postForm, BindingResult bindingResult) throws IOException {

        log.info("post update : postForm={}", postForm);

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            return "editor/post-editor";
        }

        Long savedPostId = postService.update(postForm.getPostId(), postForm.getCategoryId(), postForm.getTitle(), postForm.getContent(), getBase64DecodedMultipartFile(postForm.getThumbnailImage()));

        return "redirect:/post/" + savedPostId + "/edit";
    }

    @RequestMapping("post/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {

        /*post 삭제 시 thumbnail도 같이 삭제됨*/
        postService.deleteById(postId);

        return "redirect:/";
    }

    private static BASE64DecodedMultipartFile getBase64DecodedMultipartFile(MultipartFile multipartFile) throws IOException {
        MultipartFile thumbnailImage = multipartFile;
        BASE64DecodedMultipartFile based64ThumbnailImage = null;

        if(thumbnailImage != null) {
            log.debug("image.getName={}", thumbnailImage.getName());
            log.debug("image.getOriginalFilename={}", thumbnailImage.getOriginalFilename());
            log.debug("image.getBytes={}", thumbnailImage.getBytes());
            log.debug("image.getSize={}", thumbnailImage.getSize());
            based64ThumbnailImage = new BASE64DecodedMultipartFile(thumbnailImage.getBytes(), thumbnailImage.getName(), thumbnailImage.getOriginalFilename(), thumbnailImage.getContentType());
        }
        return based64ThumbnailImage;
    }

    private void addCategorys(Model model) {
        List<Category> categories = categoryService.findByAll();
        ArrayList<CategoryForm> categoryForms = new ArrayList<>();
        for (Category category : categories) {
            categoryForms.add(CategoryForm.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .parentId(category.getParentId())
                    .hasChild(category.getChild().size() > 0 ? true : false)
                    .build());
        }
        model.addAttribute("categoryForms", categoryForms);
    }

    private static String getCategoryFullName(String categoryName, Post post) {
        Category parent = post.getCategory().getParent();
        String categoryFullName = categoryName;
        if(parent != null) {
            categoryFullName += "/"+parent.getName();
        }
        return categoryFullName;
    }

    private BASE64DecodedMultipartFile getThumbnailImage(Post post) {
        Thumbnail thumbnail = thumbnailRepository.findByPost(post).orElse(null);
        BASE64DecodedMultipartFile thumbnailImage = null;

        if(thumbnail != null) {
            thumbnailImage = new BASE64DecodedMultipartFile(thumbnail.getImage(),
                    thumbnail.getName(), thumbnail.getOriginalFilename(), thumbnail.getContentType());
            log.debug("thumbnailImage.getBased64ImgContent={}", thumbnailImage.getBased64ImgContent());
        }

        return thumbnailImage;
    }

}
