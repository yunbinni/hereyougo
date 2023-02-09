package io.k2c1.hereyougo.dto;

import io.k2c1.hereyougo.domain.Category;
import io.k2c1.hereyougo.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
public class FavoriteCategoryForm {

    private String categoryName;
    private Long memberId;
    private Long parentCategoryId;
    private Long childCategoryId;
}
