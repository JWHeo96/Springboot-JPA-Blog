package org.cos.blog.controller;

import org.cos.blog.auth.PrincipalDetail;
import org.cos.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"", "/"})
    // @AuthenticationPrincipal PrincipalDetail principal 컨트롤러에서 세션을 어떻게 찾는지?
    public String index(Model model,
                        @PageableDefault(size=3, sort="id", direction= Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("boards", boardService.getBoardList(pageable));
        // WEB-INF/views/index.jsp
        System.out.println("BoardController ==> index() ==> " + SecurityContextHolder.getContext().getAuthentication());
        return "index";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.getBoardDetail(id));

        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.getBoardDetail(id));
        return "board/updateForm";
    }

    // USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
