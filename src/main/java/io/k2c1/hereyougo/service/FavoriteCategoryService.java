package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.Category;
import io.k2c1.hereyougo.domain.FavoriteCategory;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.repository.CategoryRepository;
import io.k2c1.hereyougo.repository.FavoriteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteCategoryService {

    @Autowired
    private FavoriteCategoryRepository favoriteCategoryRepository;


    /*
    * 회원 관심 카테고리 등록
    * */
    public void addFavoriteCategory(Member member, Category category){
        FavoriteCategory favoriteCategory = new FavoriteCategory();

        favoriteCategory.setCategory(category);
        favoriteCategory.setMember(member);
        favoriteCategory.setCategoryName(category.getCategoryName());

        favoriteCategoryRepository.save(favoriteCategory);
    }

}
