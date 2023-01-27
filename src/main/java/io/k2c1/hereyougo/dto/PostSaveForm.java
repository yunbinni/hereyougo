package io.k2c1.hereyougo.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PostSaveForm
{
    @NotEmpty
    private String title;

    @NotNull
    private String content;

    @Min(value = 0)
    private Integer width;

    @Min(value = 0)
    private Integer depth;

    @Min(value = 0)
    private Integer height;

    private Integer quantity;

    private String address;
}
