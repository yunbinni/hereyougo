package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.Category;
import io.k2c1.hereyougo.dto.CategoryForm;
import io.k2c1.hereyougo.dto.FavoriteCategoryForm;
import io.k2c1.hereyougo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    /*
    * 카테고리 등록은 필요없다고 해서
    * 주석 처리
    * */

//    public void createCategory(CategoryForm categoryForm){
//        Category category = new Category();
//        Long parentId = categoryForm.getParentId();
//        if(parentId != null){
//            categoryRepository.findOne()
//            category.setParent();
//        }
//        category.setParent(categoryForm.getParentId());

//        if()
//        categoryRepository.save()
//    }

    /**
     * 카테고리 정보 조회
     */
    public Category getCategory(Long categoryId){
        return categoryRepository.findById(categoryId).get();
    }

    /**
     * 부모 카테고리(1차) 목록 조회
     */
    public  List<Category> getParentCategories(){
        return categoryRepository.findByParentIsNull();
    }

    /**
     * 2차 카테고리 목록 조회
     */
    public List<Category> getChildCategories(Long parentId){
        return categoryRepository.getCategoriesByParentId(parentId);
    }

    public List<Category> getAllChildCategories() {
        return categoryRepository.findAll();
    }
}
