package io.k2c1.hereyougo.dto;

import io.k2c1.hereyougo.domain.Address;
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

    @NotEmpty
    private String size;

    private Integer quantity;

    private String roadAddrPart1;
    private String roadAddrPart2;
    private String roadFullAddr;
    private String jibunAddr;
    private String addrDetail;
    private String siNm;
    private String sggNm;
    private String zipNo;
}
