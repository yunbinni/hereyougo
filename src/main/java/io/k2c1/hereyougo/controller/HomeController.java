package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String home(
            @CookieValue(name = "memberId", required = false) Long memberId,
            Model model
    )
    {
        if (memberId == null) return "home";

        Member loginMember = memberRepository.findById(memberId).get();

        if (loginMember == null) return "home";

        model.addAttribute("member", loginMember);

        return "home";
    }
}
