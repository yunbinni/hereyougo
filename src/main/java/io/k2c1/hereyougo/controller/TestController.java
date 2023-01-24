package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.SampleDataInit;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.JoinForm;
import io.k2c1.hereyougo.dto.LoginForm;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import io.k2c1.hereyougo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * TODO 나중에 Controller가 비대해지면, 분류합시다!
 */
@Controller
@RequiredArgsConstructor
public class TestController {

    @Autowired
    private final PostRepository postRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @GetMapping("/join")
    public String join(@ModelAttribute("joinForm") JoinForm joinForm) {
        // TODO : 회원가입 검증로직도 추가해야함 (Service에서)
//        memberService.join(joinForm);
        return "join";
    }

    @GetMapping("/post/{postId}")
    public String postForDevelopment(@PathVariable("postId") Long postId, Model model)
    {
        /**
         * TODO
         * 현재 임시로 SampleDataInit을 사용중
         * 나중에 실제 Production 용으로 변환하기
         */
        Post findPost = postRepository.findById(postId).get();
        model.addAttribute("post", findPost);
        return "post";
    }
}
