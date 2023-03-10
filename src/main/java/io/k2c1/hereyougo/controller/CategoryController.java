package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.domain.Category;
import io.k2c1.hereyougo.dto.CategoryForm;
import io.k2c1.hereyougo.repository.CategoryRepository;
import io.k2c1.hereyougo.service.CategoryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categories")
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("categories", categoryService.getParentCategories());
        return "category/newCategory";
    }

    @PostMapping("/new")
    public String createCategory(CategoryForm categoryForm, Model model){
//        model.addAttribute("categoryies", categoryRepository.findByParentCategoryIsNull());
        System.out.println("카테고리 부모 아이디" + categoryForm.getParentId());
        return "category/newCategory";
    }

    @GetMapping("/child")
    public @ResponseBody List<Category> getSecondCategories(@RequestParam(value = "firstCategoryId") Long firstCategoryId, Model model){
        return categoryService.getChildCategories(firstCategoryId);
    }

}
