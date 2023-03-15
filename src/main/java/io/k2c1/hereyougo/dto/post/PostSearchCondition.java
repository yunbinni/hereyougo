package io.k2c1.hereyougo.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSearchCondition {

    private String sido;
    private String sgg;
    private Long categoryId;
    private String searchKey;

}
