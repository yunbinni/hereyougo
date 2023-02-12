package io.k2c1.hereyougo.dto;

import io.k2c1.hereyougo.domain.Appointment;
import io.k2c1.hereyougo.domain.FavoriteCategory;
import io.k2c1.hereyougo.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MyPageForm {
    private String nickname;
    /***
     * TODO
     * 키워드 목록 필드 추가
     */

    private List<Appointment> appointments;

    private List<Post> posts;

    private List<FavoriteCategory> favoriteCategories;
}
