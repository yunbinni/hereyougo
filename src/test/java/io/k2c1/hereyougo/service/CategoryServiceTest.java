package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Test
    @DisplayName("모든 카테고리 나오는지 확인")
    void findAll() {

        List<Category> results = categoryService.getAllCategories();

        for (Category result : results) {
            System.out.println("Category Id = " + result.getId());
        }
    }
}