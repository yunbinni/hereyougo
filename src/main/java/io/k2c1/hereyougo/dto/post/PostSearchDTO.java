package io.k2c1.hereyougo.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PostSearchDTO {

    private Long id;
    private String title;
    private Integer quantity;

    @QueryProjection
    public PostSearchDTO(Long id, String title, Integer quantity) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
    }
}
