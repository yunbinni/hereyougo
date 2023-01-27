package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.SampleDataInit;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.PostSaveForm;
import io.k2c1.hereyougo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController
{
    @Autowired
    private final PostRepository postRepository;

    @GetMapping("/{postId}")
    public String getPost(@PathVariable("postId") Long postId, Model model)
    {
        Post findPost = postRepository.findById(postId).get();
        log.info("findPost - ID: {}, TITLE : {}", findPost.getId(), findPost.getTitle());
        model.addAttribute("post", findPost);
        return "post/post";
    }

    @GetMapping("/add")
    public String addForm(Model model)
    {
        model.addAttribute("post", new Post());
        return "post/addPost";
    }

    @PostMapping("/add")
    public String addPost(@Validated @ModelAttribute("post") PostSaveForm postSaveForm, BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
//        redirectAttributes.addAttribute("postId", );

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "post/addPost";
        }

        // 성공 로직 TODO TypeConverter 적용?
        Post post = new Post();
//        post.setWriter(); // TODO
        post.setTitle(postSaveForm.getTitle());
        post.setContent(postSaveForm.getContent());
        post.setWidth(postSaveForm.getWidth());
        post.setDepth(postSaveForm.getDepth());
        post.setHeight(postSaveForm.getHeight());
        post.setViews(0);
        post.setQuantity(postSaveForm.getQuantity());
        post.setAddress(postSaveForm.getAddress());

        Post savedPost = postRepository.save(post);
        log.info("id : {}", savedPost.getId());
        redirectAttributes.addAttribute("postId", savedPost.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/post/{postId}";
    }
}
