package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.constant.SessionConst;
import io.k2c1.hereyougo.domain.Category;
import io.k2c1.hereyougo.domain.FavoriteCategory;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.dto.CategoryForm;
import io.k2c1.hereyougo.dto.FavoriteCategoryForm;
import io.k2c1.hereyougo.service.CategoryService;
import io.k2c1.hereyougo.service.FavoriteCategoryService;
import io.k2c1.hereyougo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/favorites")
@Controller
public class FavoritesController {

    @Autowired
    private FavoriteCategoryService favoriteCategoryService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MemberService memberService;

    /**
     * 회원 관심 카테고리 등록 페이지로 이동
     */
    @GetMapping("/new")
    public String addFavoriteCategoryForm(Model model){
        model.addAttribute("categories", categoryService.getParentCategories());
        model.addAttribute("favoriteCategoryForm", new FavoriteCategoryForm());
        return "favorites/newFavoriteCategory";
    }

    /**
     * 회원 관심 카테고리 등록
     */
    @PostMapping("/new")
    public String addFavoriteCategory(CategoryForm categoryForm,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model){
        Member member = memberService.findMember(loginMember.getId());
        Category category;

        Long parentCategoryId = categoryForm.getParentId();
        Long secondCategoryId = categoryForm.getChildrenId();

//      중분류를 선택한 경우
        if(secondCategoryId != null){
            category =  categoryService.getCategory(secondCategoryId);
        }
//      중분류를 선택하지않은 경우
        else{
            category = categoryService.getCategory(parentCategoryId);
        }
//        try {
            favoriteCategoryService.addFavoriteCategory(member, category);
//        }catch (Exception e){
//            model.addAttribute("error", "이미 신청한 카테고리입니다");
//        }
//        model.addAttribute("categoryies", categoryRepository.findByParentCategoryIsNull());
        return "redirect:/members/";
    }

    /**
     * 회원 관심 카테고리 삭제
     * @DeleteMapping 사용 시, 405에러가 발생하여
     * PostMapping으로 대체
     */
    @PostMapping("/delete/{categoryId}")
    public String removeFavoriteCategory(@PathVariable(name = "categoryId") Long categoryId){
        favoriteCategoryService.removeFavoriteCategory(categoryId);
        return "redirect:/members/";
    }
}
