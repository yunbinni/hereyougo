package io.k2c1.hereyougo.dto.post;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PostSaveForm
{
    private Long categoryId;

    @NotEmpty
    private String title;

    @NotNull
    private String content;

    private Integer quantity;

    private String roadAddrPart1;
    private String roadAddrPart2;
    private String roadFullAddr;
    private String jibunAddr;
    private String addrDetail;
    private String siNm;
    private String sggNm;
    private String zipNo;

    @Size(min = 1, max = 5, message = "이미지는 최대 5개까지만 첨부할 수 있습니다.")
    private List<MultipartFile> images;
}
