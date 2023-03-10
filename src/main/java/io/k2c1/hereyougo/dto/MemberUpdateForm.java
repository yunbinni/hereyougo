package io.k2c1.hereyougo.dto;

import io.k2c1.hereyougo.domain.Category;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberUpdateForm {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private Category category;
}
