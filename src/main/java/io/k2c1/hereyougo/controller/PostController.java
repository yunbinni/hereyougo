package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.constant.SessionConst;
import io.k2c1.hereyougo.domain.Address;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.PostSaveForm;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import io.k2c1.hereyougo.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController
{
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final PostService postService;

    @GetMapping("/{postId}")
    public String getPost(
            @PathVariable("postId") Long postId,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model)
    {
        if (loginMember != null) model.addAttribute("member", loginMember);

        Post getPost = postRepository.findById(postId).get();
        log.info("Getting Post - ID: {}, TITLE : {}", getPost.getId(), getPost.getTitle());
        model.addAttribute("post", getPost);
        getPost.plusViews();
        return "posts/post";
    }

    @GetMapping("/add")
    public String addForm(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model)
    {
        if (loginMember != null) model.addAttribute("member", loginMember);
        model.addAttribute("form", new PostSaveForm());
        return "posts/addPost";
    }

    @PostMapping("/add")
    public String addPost(
            @Validated @ModelAttribute("form") PostSaveForm form,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    )
    {
//        redirectAttributes.addAttribute("postId", );

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "posts/addPost";
        }

        // 성공 로직 TODO TypeConverter 적용?
        Post post = new Post();
        post.setWriter(loginMember);
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setSize(form.getSize());
        post.setViews(0);
        post.setQuantity(form.getQuantity());
//        post.setAddress(form.getAddress());

        Address address = new Address();
        address.setSido(form.getSiNm());
        address.setSgg(form.getSggNm());
        address.setDoro(form.getRoadFullAddr());
        address.setJibun(form.getJibunAddr());
        address.setZipNo(form.getZipNo());
        post.setAddress(address);

        Post savedPost = postRepository.save(post);
        log.info("id : {}", savedPost.getId());
        redirectAttributes.addAttribute("postId", savedPost.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(
            @PathVariable Long postId,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model)
    {
        if (loginMember != null) model.addAttribute("member", loginMember);

        Post post = postRepository.findById(postId).get();
        model.addAttribute("post", post);
        return "posts/editPost";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, @ModelAttribute Post post)
    {
        return "redirect:/posts/{postId}";
    }

    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId)
    {
        postRepository.deleteById(postId);
        return "redirect:/";
    }

    @GetMapping("/filtered")
    public String getFilteredPosts(
            @RequestParam("sido") String sido,
            @RequestParam("sgg") String sgg,
            @RequestParam("categoryId") Long categoryId,
            Model model
    ) {
        List<Post> posts = postRepository.findAll().stream()
                .filter(post -> {
                    if(sido.equals("시/도 전체")) return true;
                    else return post.getAddress().getSido().equals(sido);
                })
                .filter(post -> {
                    if(sgg.equals("0")) return true;
                    else return post.getAddress().getSgg().equals(sgg);
                })
//                .filter(post -> {
//                    if(categoryId == 0L) return true;
//                    else return post.getCategory().getId() == categoryId;
//                })
                .collect(Collectors.toList());

        model.addAttribute("posts", posts);

        return "fragments/filtered";
    }
}
