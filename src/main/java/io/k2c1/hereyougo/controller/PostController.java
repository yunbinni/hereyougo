package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.config.FileUploader;
import io.k2c1.hereyougo.constant.SessionConst;
import io.k2c1.hereyougo.constant.SidoSgg;
import io.k2c1.hereyougo.domain.Address;
import io.k2c1.hereyougo.domain.Image;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.post.PostSaveForm;
import io.k2c1.hereyougo.dto.post.PostSearchCondition;
import io.k2c1.hereyougo.dto.post.PostSearchDTO;
import io.k2c1.hereyougo.repository.PostRepository;
import io.k2c1.hereyougo.repository.PostSearchRepository;
import io.k2c1.hereyougo.service.CategoryService;
import io.k2c1.hereyougo.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController
{
    private final PostService postService;
    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final FileUploader fileUploader;
    private final PostSearchRepository postSearchRepository;

    @GetMapping("/{postId}")
    public String getPost(
            @PathVariable("postId") Long postId,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model)
    {
        if (loginMember != null) model.addAttribute("member", loginMember);

        Post getPost = postRepository.findById(postId).get();
        log.info("Getting Post - ID: {}, TITLE : {}", getPost.getId(), getPost.getTitle());

        updateViews(loginMember, request, response, getPost);

        model.addAttribute("post", getPost);

        return "posts/post";
    }

    private void updateViews(Member loginMember, HttpServletRequest request, HttpServletResponse response, Post getPost) {
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("postView"))
                    oldCookie = cookie;
            }
        }

        if (oldCookie == null) {
            if (!getPost.getWriter().equals(loginMember)) {
                postService.updateView(getPost.getId());
            }
            Cookie newCookie = new Cookie("postView", getPost.getId().toString());
            newCookie.setMaxAge(24 * 60 * 60);
            response.addCookie(newCookie);
        }
    }

    @GetMapping("/updateRecommend")
    private String updateRecommend(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("postId") Long postId) throws IOException
    {
        log.info("postId = {}", postId);

        Post getPost = postRepository.findById(postId).get();

        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("postRecommend"))
                    oldCookie = cookie;
            }
        }

        if (oldCookie == null) {
            if (!getPost.getWriter().equals(loginMember)) {
                postService.updateRecommend(getPost.getId());
            }
            Cookie newCookie = new Cookie("postRecommend", getPost.getId().toString());
            newCookie.setMaxAge(24 * 60 * 60);
            response.addCookie(newCookie);
        }

        return "redirect:/posts/" + postId.toString();
    }

    @GetMapping("/add")
    public String addForm(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model)
    {
        if (loginMember != null) model.addAttribute("member", loginMember);
        model.addAttribute("form", new PostSaveForm());
        model.addAttribute("secondCategories", categoryService.getAllCategories());
        return "posts/addPost";
    }

    @PostMapping("/add")
    public String addPost(
            @Validated @ModelAttribute("form") PostSaveForm form,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) throws IOException {

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "posts/addPost";
        }

        Address address = Address.builder()
                .sido(form.getSiNm())
                .sgg(form.getSggNm())
                .doro(form.getRoadFullAddr())
                .jibun(form.getJibunAddr())
                .zipNo(form.getZipNo())
                .build();

        Post post = Post.builder()
                .writer(loginMember)
                .title(form.getTitle())
                .content(form.getContent())
                .views(0)
                .quantity(form.getQuantity())
                .address(address)
                .recommend(0)
                .reservationQuantity(0)
                .category(categoryService.getCategory(form.getCategoryId()))
                .build();

        Post savedPost = postRepository.save(post);

        List<MultipartFile> files = form.getFiles();
        List<Image> images = fileUploader.uploadFiles(files, post);
        savedPost.setImages(images);

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

    @GetMapping("/search")
    public String search(
            @RequestParam(value = "sido", defaultValue = "전국") String sido,
            @RequestParam(value = "sgg", defaultValue = "전체") String sgg,
            @RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
            @RequestParam(value = "searchKey", defaultValue = "") String searchKey,
            @PageableDefault(size = 16, sort = "Id", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        PostSearchCondition condition = new PostSearchCondition(sido, sgg, categoryId, searchKey);

        Page<PostSearchDTO> content = postSearchRepository.findByConditions(condition.getSido(), condition.getSgg(), condition.getCategoryId(), condition.getSearchKey(), pageable);

        int startPage = Math.max(1, content.getPageable().getPageNumber() - 4);
        int endPage = Math.min(content.getTotalPages(), content.getPageable().getPageNumber() + 4);

        List<SidoSgg> sidoList = Arrays.asList(SidoSgg.values());
        List<String> sggList = getSggListBySido(sido);

        model
                .addAttribute("startPage", startPage)
                .addAttribute("endPage", endPage)
                .addAttribute("posts", content)
                .addAttribute("condition", condition)
                .addAttribute("sidoList", sidoList)
                .addAttribute("sggList", sggList)
                .addAttribute("categoryList", categoryService.getAllCategories());

        return "posts/search";
    }

    private List<String> getSggListBySido(String sido) {
        return SidoSgg.valueOf(sido).getSgg();
    }

    @GetMapping("/list")
    public String getPostsByMember(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String searchText, Model model
    ) {

        if (loginMember != null) model.addAttribute("member", loginMember);

        Page<Post> posts = postRepository.findByWriter_Id(loginMember.getId(), pageable);

        int startPage = Math.max(1, posts.getPageable().getPageNumber() - 4);
        int endPage = Math.min(posts.getTotalPages(), posts.getPageable().getPageNumber() + 4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("posts", posts);

        return "posts/postList";
    }

}
