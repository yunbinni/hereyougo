package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Category;
import io.k2c1.hereyougo.domain.FavoriteCategory;
import io.k2c1.hereyougo.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class FavoriteCategoryRepositoryTest {

    @Autowired
    private FavoriteCategoryRepository favoriteCategoryRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void 관심카테고리목록조회(){
//      given
        Member member = 회원생성();
        List<Category> categories = 카테고리목록생성();

        for(Category category : categories){
            FavoriteCategory favoriteCategory = new FavoriteCategory();
            favoriteCategory.setCategory(category);
            favoriteCategory.setMember(member);
            favoriteCategoryRepository.save(favoriteCategory);
        }

//      when
        List<FavoriteCategory> result = favoriteCategoryRepository.findByMember_id(member.getId());

//      then
        Assertions.assertEquals(3, result.size());

    }

   @Test
   public List<Category> 카테고리목록생성(){
        List<Category> categories = new ArrayList<>();

       Category category1 = new Category("요식업", null, 1);
       categories.add(category1);
       categoryRepository.save(category1);
       Category category2 = new Category("한식", category1, 2);
       categories.add(category2);
       categoryRepository.save(category2);
       Category category3 = new Category("서비스업", null, 1);
       categories.add(category3);
       categoryRepository.save(category3);

       return categories;
   }



    public Member 회원생성(){
        Member member = new Member();

        String email = "test@naver.com";
        String password = "123456";
        String nickname = "test";
        String businessType = "요식업";

        member.setEmail(email);
        member.setPassword(password);
        member.setNickname(nickname);
        member.setBusinessType(businessType);

        memberRepository.save(member);

        return member;
    }


}