package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.constant.SessionConst;
import io.k2c1.hereyougo.domain.Category;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.repository.CategoryRepository;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.service.CategoryService;
import io.k2c1.hereyougo.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final PostService postService;

//    @GetMapping("/")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        model.addAttribute("parentCategories", categoryService.getParentCategories());
        model.addAttribute("recentPopularPosts", postService.getRecentPopularPosts());
        model.addAttribute("secondCategories", categoryService.getAllChildCategories());
        model.addAttribute("posts", postService.getAllPosts()); // 페이지네이션 필요

        HttpSession session = request.getSession(false);
        if (session == null) return "home";
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember == null) return "home";
        model.addAttribute("member", loginMember);

        return "home";
    }

    @GetMapping("/")
    public String home() {
        return "tempHome";
    }
}
