package com.parkging.helloblog.web.boarder;

import com.parkging.helloblog.service.BoarderService;
import com.parkging.helloblog.web.boarder.form.BoardForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoarderController {

    public static final int PAAGE_SIZE = 5;

    private final BoarderService boarderService;

    @GetMapping("/boarder")
    public String boarder(Model model,
                          @PageableDefault(size = PAAGE_SIZE, sort = "id",
                                  direction = Sort.Direction.DESC) Pageable pageable) {
        BoardForm boardForm = boarderService.getBoardForm(null, pageable);

        model.addAttribute("boardForm", boardForm);
        return "fragment/board";
    }

    @GetMapping("/boarder/{categoryName}")
    public String boarder(@PathVariable String categoryName, Model model,
                          @PageableDefault(size = PAAGE_SIZE, sort = "id",
                                  direction = Sort.Direction.DESC) Pageable pageable) {
        BoardForm boardForm = boarderService.getBoardForm(categoryName, pageable);

        model.addAttribute("boardForm", boardForm);
        return "index";
    }
}
