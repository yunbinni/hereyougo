package io.k2c1.hereyougo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryForm {

    private String categoryName;
    private Long parentId;
    private Long childrenId;
}
