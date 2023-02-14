package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.constant.SessionConst;
import io.k2c1.hereyougo.domain.Category;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model)
    {
        model.addAttribute("parentCategories", categoryService.getParentCategories());

        HttpSession session = request.getSession(false);
        if (session == null) return "home";
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember == null) return "home";

        model.addAttribute("parentCategories", categoryService.getParentCategories());
        model.addAttribute("member", loginMember);

        return "home";
    }
}
