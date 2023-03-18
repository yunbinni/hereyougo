package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.constant.SessionConst;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.service.CategoryService;
import io.k2c1.hereyougo.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        model
                .addAttribute("recentPopularPosts", postService.getRecentPopularPosts())
                .addAttribute("secondCategories", categoryService.getAllCategories())
                .addAttribute("posts", postService.getAllPosts());

        HttpSession session = request.getSession(false);
        if (session == null) return "home";
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (loginMember == null)
            return "home";
        else
        {
            model
                    .addAttribute("member", loginMember)
                    .addAttribute("nearPosts", postService.getNearPosts(loginMember));
        }

        return "home";
    }
}
